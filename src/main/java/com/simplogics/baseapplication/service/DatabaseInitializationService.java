package com.simplogics.baseapplication.service;

import com.simplogics.baseapplication.exception.CustException;

public interface DatabaseInitializationService {
	
	void insertDefaultValuesInDatabase() throws CustException;

}
