package com.epam.liavitskaya.main.controller.command.impl;

import org.apache.log4j.Logger;

import com.epam.liavitskaya.main.bean.User;
import com.epam.liavitskaya.main.controller.command.Command;
import com.epam.liavitskaya.main.service.ClientService;
import com.epam.liavitskaya.main.service.exception.ServiceException;
import com.epam.liavitskaya.main.service.provider.ServiceProvider;

public class ReviewProfile implements Command {

	final Logger logger = Logger.getLogger(ReviewProfile.class);

	@Override
	public String execute(String request) {
		String response = null;

		int id = 0;		// !!!
		ServiceProvider serviceProvider = ServiceProvider.getInstance();
		try {
			ClientService clientService = serviceProvider.getClientServiceImpl();
			User user = clientService.reviewProfile(id);
			response = "user profile : " + user;
		} catch (ServiceException e) {
			logger.error("Error during user profile review procedure", e);
			response = "Error during user profile review procedure";
		}
		return response;
	}

}