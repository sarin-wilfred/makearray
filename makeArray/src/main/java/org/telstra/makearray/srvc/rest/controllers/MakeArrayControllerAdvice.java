/**
 * 
 */
package org.telstra.makearray.srvc.rest.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.telstra.makearray.srvc.rest.exceptions.MakeArrayException;

/**
 * @author Sarin
 *
 */
@RestControllerAdvice
public class MakeArrayControllerAdvice {
	
	/**
	 * Method added to handle MakeArrayException
	 * 
	 * @param request
	 * @param maEx
	 */
	@ExceptionHandler(MakeArrayException.class)
	@ResponseStatus(code=HttpStatus.CONFLICT, reason="Make Array: Exception occurred during the generation array.")
	public void handleException(HttpServletRequest request, MakeArrayException maEx) {
	}
	
	/**
	 *  Method added to handle Exception
	 *  
	 * @param request
	 * @param ex
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(code=HttpStatus.METHOD_NOT_ALLOWED, reason="Make Array Application Error.")
	public void handleException(HttpServletRequest request, Exception ex) {
	}

}
