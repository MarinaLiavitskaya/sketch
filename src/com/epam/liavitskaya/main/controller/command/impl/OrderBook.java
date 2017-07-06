package com.epam.liavitskaya.main.controller.command.impl;

import org.apache.log4j.Logger;

import com.epam.liavitskaya.main.controller.command.Command;
import com.epam.liavitskaya.main.service.LibraryService;
import com.epam.liavitskaya.main.service.exception.ServiceException;
import com.epam.liavitskaya.main.service.provider.ServiceProvider;

public class OrderBook implements Command {
	
	final Logger logger = Logger.getLogger(OrderBook.class);

	@Override
	public String execute(String request) {
		String response = null;

		ServiceProvider serviceProvider = ServiceProvider.getInstance();
		LibraryService libraryService = serviceProvider.getLibraryServiceImpl();
		try {
			libraryService.orderBookService(request);
			response = "Book is ordered";
		} catch (ServiceException e) {
			logger.error("Error during order book procedure", e);
			response = "Error during order book procedure";
		}
		return response;
	}

}