package com.simplogics.baseapplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.simplogics.baseapplication.exception.CustException;
import com.simplogics.baseapplication.service.DatabaseInitializationService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ApplicationContextRefreshedEventListener {

	@Autowired
	private DatabaseInitializationService databaseInitializationService;

	@EventListener
	public void onApplicationEvent(ContextRefreshedEvent event) {
		try {
			databaseInitializationService.insertDefaultValuesInDatabase();
		} catch (CustException e) {
			log.error(String.format("Database Initialization Failed %s", e.getMessage()));
//			System.out.println("Database Initialization Failed " + e.getMessage());
		}
	}
}
