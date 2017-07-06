package com.epam.liavitskaya.main.controller.command.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.epam.liavitskaya.main.controller.command.Command;
import com.epam.liavitskaya.main.service.ClientService;
import com.epam.liavitskaya.main.service.exception.ServiceException;
import com.epam.liavitskaya.main.service.provider.ServiceProvider;

public class SignIn implements Command {
	
	final Logger logger = Logger.getLogger(SignIn.class);

	@Override
	public String execute(String request) {

		String login = null;
		String password = null;
		String response = null;

		ServiceProvider serviceProvider = ServiceProvider.getInstance();
		ClientService clientService = serviceProvider.getClientServiceImpl();
		try {
			if (isLoginExist(login, clientService) && isPasswordExist(password, clientService)) {
				clientService.singIn(login, password);
				response = "Hi";
			}else {
				response = "Login failed, please register";
			}
		} catch (ServiceException e) {
			logger.error("Error during login procedure", e);
			response = "Error during login procedure";
		}
		return response;
	}
	

	public boolean isPasswordExist(String password, ClientService clientService) {
		
		List<String> fetchAllPasswords = null;
		try {
			fetchAllPasswords = clientService.fetchAllPasswords();
		} catch (ServiceException e) {
			logger.error("Error during is Password Exist procedure", e); // каждом методе
																// свой или один
																// на класс ?

		}
		return fetchAllPasswords.contains(password);
	}

	public boolean isLoginExist(String login, ClientService clientService) {		
		List<String> fetchAllLogins = null;
		try {
			fetchAllLogins = clientService.fetchAllLogins();
		} catch (ServiceException e) {
			logger.error("Error during is Login exist procedure", e);
		}
		return fetchAllLogins.contains(login);
	}

}
