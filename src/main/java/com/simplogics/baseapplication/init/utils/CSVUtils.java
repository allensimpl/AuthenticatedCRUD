package com.simplogics.baseapplication.init.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.ClassPathResource;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;

import lombok.Getter;

public class CSVUtils {

	@Getter
	public enum MasterItems {

		Items("config/item_master_data.csv");

		private String filePath;

		private MasterItems(String filePath) {
			this.filePath = filePath;
		}

	}

	public static <T> List<T> parseFromCSVFileByClass(String filePath, Class<T> clazz) {
		List<T> values = new ArrayList<T>();
		try (CSVReader reader = new CSVReader(new BufferedReader(
				new InputStreamReader(new ClassPathResource(filePath).getInputStream(), StandardCharsets.UTF_8)))) {
			HeaderColumnNameMappingStrategy<T> beanStrategy = new HeaderColumnNameMappingStrategy<T>();
			beanStrategy.setType(clazz);
			CsvToBean<T> csvToBean = new CsvToBeanBuilder<T>(reader).withIgnoreQuotations(true).withType(clazz)
					.withIgnoreLeadingWhiteSpace(true).withMappingStrategy(beanStrategy).build();
			values = csvToBean.parse();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return values;
	}
}
