package com.simplogics.baseapplication.service.impl;

import org.springframework.stereotype.Service;

import com.simplogics.baseapplication.exception.CustException;
import com.simplogics.baseapplication.service.DatabaseInitializationService;

import lombok.extern.slf4j.Slf4j;

@Service(value = "databaseInitializationService")
@Slf4j
public class DatabaseInitializationServiceImpl implements DatabaseInitializationService {

	@Override
	public void insertDefaultValuesInDatabase() throws CustException {
	}

}
