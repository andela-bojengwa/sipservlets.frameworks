package org.mobicents.servlet.sip.weld.examples;

import java.io.IOException;
import java.util.HashMap;

import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.sip.Address;
import javax.servlet.sip.SipServlet;
import javax.servlet.sip.SipServletRequest;
import javax.servlet.sip.SipServletResponse;

import org.apache.log4j.Logger;



@Singleton
@Named("sipRegistar")
public class SipRegistar extends SipServlet {

	private static final long serialVersionUID = -4015553877084116786L;
	private static Logger logger = Logger.getLogger(SipRegistar.class);
	private static final String CONTACT_HEADER = "Contact";
	
	
	@Produces @Named("users") HashMap<String, String> users = new HashMap<String, String>();
	
	
	@Override
	public void doRegister(SipServletRequest req) throws ServletException,
			IOException {
		if(logger.isInfoEnabled()) {
			logger.info("Received register request: " + req.getTo());
		}
		int response = SipServletResponse.SC_OK;
		SipServletResponse resp = req.createResponse(response);

		
		Address address = req.getAddressHeader(CONTACT_HEADER);
		String fromURI = req.getFrom().getURI().toString();
		
		int expires = address.getExpires();
		if(expires < 0) {
			expires = req.getExpires();
		}
		if(expires == 0) {
			users.remove(fromURI);
			if(logger.isInfoEnabled()) {
				logger.info("User " + fromURI + " unregistered");
			}
		} else {
			resp.setAddressHeader(CONTACT_HEADER, address);
			users.put(fromURI, address.getURI().toString());
			if(logger.isInfoEnabled()) {
				logger.info("User " + fromURI + 
					" registered with an Expire time of " + expires);
			}
		}				
						
		resp.send();
	}
	
}