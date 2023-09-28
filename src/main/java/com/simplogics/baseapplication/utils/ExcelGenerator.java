package com.simplogics.baseapplication.utils;

import com.simplogics.baseapplication.dto.BaseDto;
import com.simplogics.baseapplication.dto.SalesReportDto;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExcelGenerator {
//    private static List<StudentExcelDto> resultList;
//    private static XSSFWorkbook workbook;
//    private static XSSFSheet sheet;

    //    public ExcelGenerator(List<StudentExcelDto> resultList){
//        this.resultList = resultList;
//        workbook = new XSSFWorkbook();
//    }
    private static void createCell(Row row, int columnCount, Object valueOfCell, CellStyle style, XSSFSheet sheet) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        System.out.println(valueOfCell);
        if (valueOfCell == null)
            return;
        if (valueOfCell instanceof Integer) {
            cell.setCellValue((Integer) valueOfCell);
        } else if (valueOfCell instanceof Long) {
            cell.setCellValue((Long) valueOfCell);
        } else if (valueOfCell instanceof String) {
            cell.setCellValue((String) valueOfCell);
        } else if (valueOfCell instanceof Date) {
            cell.setCellValue((Date) valueOfCell);
        } else {
            cell.setCellValue((Boolean) valueOfCell);
            cell.setCellStyle(style);
        }
    }
    private static void writeHeader(XSSFWorkbook workbook, XSSFSheet sheet){
//        sheet = workbook.createSheet("Result");
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
//        createCell(row,0,"ID",style);
        createCell(row,0,"Event Name",style,sheet);
        createCell(row,1,"Store Name",style,sheet);
        createCell(row,2,"Date",style,sheet);
        createCell(row,3,"Quantity",style,sheet);
    }
    private static void write(XSSFWorkbook workbook, XSSFSheet sheet, List<SalesReportDto> resultList){
        int rowCount = 1;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for(SalesReportDto result: resultList){
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
//            createCell(row,columnCount++,result.getId(),style);
            String date = formatter.format(result.getDate());
            createCell(row,columnCount++,result.getEventName(),style,sheet);
            createCell(row,columnCount++,result.getStoreName(),style,sheet);
            createCell(row,columnCount++,date,style,sheet);
            createCell(row,columnCount,result.getQuantity(),style,sheet);
        }
    }
    public static void generateExcelFile(HttpServletResponse response, XSSFWorkbook workbook, XSSFSheet sheet, List<SalesReportDto> resultList)throws IOException {
        writeHeader(workbook, sheet);
        write(workbook,sheet,resultList);
    }
}