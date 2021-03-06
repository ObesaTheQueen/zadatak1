package hr.combis.controllers;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import hr.combis.exceptions.BusinessInfrastructureException;
import hr.combis.model.Error;
import hr.combis.model.RestResponse;


@ControllerAdvice
public class ExceptionController {
	
	private static final Logger logger = LoggerFactory.getLogger(ExceptionController.class);
	
	@Autowired
	private MessageSourceAccessor messageSource;
	

	
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<RestResponse> httpRequestMethodNotSupportedExceptionHandler(HttpServletRequest pHttpServletRequest, Exception pException){
    	logger.error("Failed URL: " + pHttpServletRequest.getRequestURL() + " Exception: ", pException);
		RestResponse tResponse = new RestResponse();
		tResponse.setError(new Error(HttpStatus.METHOD_NOT_ALLOWED.toString(), messageSource.getMessage("error.405")));
		return new ResponseEntity<>(tResponse, HttpStatus.METHOD_NOT_ALLOWED);
    }
    
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<RestResponse> httpMessageNotReadableExceptionHandler(HttpServletRequest pHttpServletRequest, HttpMessageNotReadableException pHttpMessageNotReadableException) {
    	logger.error("Failed URL: " + pHttpServletRequest.getRequestURL() + " Exception: ", pHttpMessageNotReadableException);
    	RestResponse tResponse = new RestResponse();
    	tResponse.setError(new Error(HttpStatus.BAD_REQUEST.toString(), messageSource.getMessage("error.400")));
    	return new ResponseEntity<>(tResponse, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(value = BusinessInfrastructureException.class)
    public ResponseEntity<RestResponse> businessinfrastructureException(HttpServletRequest pHttpServletRequest, BusinessInfrastructureException businessInfrastructureException) {
//    	logger.error("Failed URL: " + pHttpServletRequest.getRequestURL() + " Exception: ", businessInfrastructureException);
    	RestResponse tResponse = new RestResponse();
    	tResponse.setError(new Error(HttpStatus.INTERNAL_SERVER_ERROR.toString(), businessInfrastructureException.getMessage()));
    	return new ResponseEntity<>(tResponse, HttpStatus.OK);
    }
    
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<RestResponse> generalException(HttpServletRequest pHttpServletRequest, BusinessInfrastructureException businessInfrastructureException) {
    	logger.error("Failed URL: " + pHttpServletRequest.getRequestURL() + " Exception: ", businessInfrastructureException);
    	RestResponse tResponse = new RestResponse();
    	tResponse.setError(new Error(HttpStatus.INTERNAL_SERVER_ERROR.toString(), businessInfrastructureException.getMessage()));
    	return new ResponseEntity<>(tResponse, HttpStatus.BAD_REQUEST);
    }

}
