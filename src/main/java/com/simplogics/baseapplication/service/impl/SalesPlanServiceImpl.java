package com.simplogics.baseapplication.service.impl;

import com.simplogics.baseapplication.dto.BaseDto;
import com.simplogics.baseapplication.dto.SalesPlanRequestDto;
import com.simplogics.baseapplication.dto.SalesReportDto;
import com.simplogics.baseapplication.entity.Event;
import com.simplogics.baseapplication.entity.SalesPlan;
import com.simplogics.baseapplication.exception.CustException;
import com.simplogics.baseapplication.repository.EventRepository;
import com.simplogics.baseapplication.repository.EventStoreMapRepository;
import com.simplogics.baseapplication.repository.SalesPlanRepository;
import com.simplogics.baseapplication.service.ISalesPlanService;
import com.simplogics.baseapplication.utils.ExcelGenerator;
import com.simplogics.baseapplication.utils.Mapper;
import com.simplogics.baseapplication.view.SalesReportView;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SalesPlanServiceImpl implements ISalesPlanService {
    @Autowired
    EventStoreMapRepository eventStoreMapRepository;
    @Autowired
    SalesPlanRepository repository;
    @Autowired
    EventRepository eventRepository;
    @Override
    public BaseDto addSalesPlan(@RequestBody SalesPlanRequestDto salesPlanRequestDto) throws CustException {
        int eventId = salesPlanRequestDto.getEventId();
        int storeId = salesPlanRequestDto.getStoreId();
        int id = eventStoreMapRepository.findId(eventId,storeId);
        Event event = eventRepository.findByEventCode(eventId);
        Date startDate = event.getStartDate();
        Date endDate = event.getEndDate();
        Date date = salesPlanRequestDto.getDate();
        boolean c1 = date.after(startDate);
        boolean c2 = date.before(endDate);
        int d1 = date.compareTo(startDate);
        int d2 = date.compareTo(endDate);
        if(!date.after(startDate) || !date.before(endDate)){
            throw new CustException("The Date is out of Bounds",500);
        }
        SalesPlan salesPlan = SalesPlan.builder()
                .esId(id)
                .date(salesPlanRequestDto.getDate())
                .quantity(salesPlanRequestDto.getQuantity())
                .build();
        repository.save(salesPlan);
        return Mapper.saleToSalesResponseDtoConv(salesPlan);
    }

//    @Override
//    public List<BaseDto> generateReport() {
//        return null;
//    }
    @Override
    public List<BaseDto> getAllSalesPlan(){
        return Mapper.salesListToSalesResponseDto(repository.findAll());
    }

    @Override
    public List<BaseDto> generateReport(HttpServletResponse response) throws IOException {
        setExcelHeader(response);
        List<SalesReportView> salesReport = repository.generateReport();
        List<BaseDto> salesReportDto = new ArrayList<>();
        List<SalesReportDto> salesReportDtosList = new ArrayList<>();
        for(SalesReportView view: salesReport){
            salesReportDto.add(Mapper.reportViewToReportDto(view));
            salesReportDtosList.add(Mapper.reportViewToReportDto(view));
        }
        XSSFWorkbook workbook=new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Result");
        ExcelGenerator.generateExcelFile(response,workbook,sheet,salesReportDtosList);
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
        return salesReportDto;
    }

    private void setExcelHeader(HttpServletResponse response){
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=student" + currentDateTime + ".xlsx";
        response.setHeader(headerKey,headerValue);
    }
}
