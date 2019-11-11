package hr.combis.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ErrorController {
//	
//	private static final Logger logger = LoggerFactory.getLogger(ErrorController.class);
//	public static final String SPRINGSECURITYLASTEXCEPTION = "SPRING_SECURITY_LAST_EXCEPTION";
//		
//	@Autowired
//	private MessageSource messageSource;
//	
//	@RequestMapping("errorThrowable")
//	public ResponseEntity<RestResponse> errorThrowable(HttpServletRequest pRequest, HttpServletResponse pResponse, Model pModel) {
//		logError(pRequest.getAttribute(SPRINGSECURITYLASTEXCEPTION));
//		RestResponse tResponse = new RestResponse();
//		tResponse.setError(new Error("0", messageSource.getMessage("error.throwable", null, null)));
//		return new ResponseEntity<>(tResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//	}
//	
//	@RequestMapping("error400")
//	public ResponseEntity<RestResponse> error400(HttpServletRequest pRequest, HttpServletResponse pResponse, Model pModel) {
//		logError(pRequest.getAttribute(SPRINGSECURITYLASTEXCEPTION));
//		RestResponse tResponse = new RestResponse();
//		tResponse.setError(new Error(HttpStatus.BAD_REQUEST.toString(), messageSource.getMessage("error.400", null, null)));
//		return new ResponseEntity<>(tResponse, HttpStatus.BAD_REQUEST);
//	}
//	
//	@RequestMapping("error401")
//	public ResponseEntity<RestResponse> error401(HttpServletRequest pRequest, HttpServletResponse pResponse, Model pModel) {
//		logError(pRequest.getAttribute(SPRINGSECURITYLASTEXCEPTION));
//		RestResponse tResponse = new RestResponse();
//		tResponse.setError(new Error(HttpStatus.UNAUTHORIZED.toString(), messageSource.getMessage("error.401", null, null)));
//		return new ResponseEntity<>(tResponse, HttpStatus.UNAUTHORIZED);
//	}
//	
//	@RequestMapping("error403")
//	public ResponseEntity<RestResponse> error403(HttpServletRequest pRequest, HttpServletResponse pResponse, Model pModel) {		
//		logError(pRequest.getAttribute(SPRINGSECURITYLASTEXCEPTION));
//		RestResponse tResponse = new RestResponse();
//		tResponse.setError(new Error(HttpStatus.FORBIDDEN.toString(), messageSource.getMessage("error.403", null, null)));
//		return new ResponseEntity<>(tResponse, HttpStatus.FORBIDDEN);
//	}
//	
//	@RequestMapping("error404")
//	public ResponseEntity<RestResponse> error404(HttpServletRequest pRequest, HttpServletResponse pResponse, Model pModel) {
//		logError(pRequest.getAttribute(SPRINGSECURITYLASTEXCEPTION));
//		RestResponse tResponse = new RestResponse();
//		tResponse.setError(new Error(HttpStatus.NOT_FOUND.toString(), messageSource.getMessage("error.404", null, null)));
//		return new ResponseEntity<>(tResponse, HttpStatus.NOT_FOUND);
//	}
//	
//	@RequestMapping("error405")
//	public ResponseEntity<RestResponse> error405(HttpServletRequest pRequest, HttpServletResponse pResponse, Model pModel) {
//		logError(pRequest.getAttribute(SPRINGSECURITYLASTEXCEPTION));
//		RestResponse tResponse = new RestResponse();
//		tResponse.setError(new Error(HttpStatus.METHOD_NOT_ALLOWED.toString(), messageSource.getMessage("error.405", null, null)));
//		return new ResponseEntity<>(tResponse, HttpStatus.METHOD_NOT_ALLOWED);
//	}
//	
//	
//	@RequestMapping("error500")
//	public ResponseEntity<RestResponse> error500(HttpServletRequest pRequest, HttpServletResponse pResponse, Model pModel) {
//		logError(pRequest.getAttribute(SPRINGSECURITYLASTEXCEPTION));
//		RestResponse tResponse = new RestResponse();
//		tResponse.setError(new Error(HttpStatus.INTERNAL_SERVER_ERROR.toString(), messageSource.getMessage("error.500", null, null)));
//		return new ResponseEntity<>(tResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//	}
//	
//	
//	/**
//	 * Logira iznimke koje se javljaju u controlleru za obradu grešaka.
//	 * 
//	 * @param pException Objekt greške
//	 */
//	private void logError(Object pException) {
//		if (pException != null){
//			final Exception e = (Exception) pException;
//			logger.error(e.getMessage());
//			if (e.getCause() != null){
//				logger.error("-> Reason: " + e.getCause().getMessage());
//			}
//		}
//	}

}
