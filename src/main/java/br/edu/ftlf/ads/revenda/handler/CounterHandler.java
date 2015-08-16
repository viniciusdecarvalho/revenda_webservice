package br.edu.ftlf.ads.revenda.handler;

import java.util.HashSet;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CounterHandler implements SOAPHandler<SOAPMessageContext> {

	private static final Logger log = LoggerFactory.getLogger(CounterHandler.class);
	private static int requestCount = 0;
	private static int responseCount = 0;
	
	@Override
	public boolean handleMessage(SOAPMessageContext context) {
		
		boolean isRequest = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		
		if (isRequest) {
			log.debug("requests {}", ++requestCount);
		} else {
			log.debug("responses {}", ++responseCount);
		}
		
		return true;
	}

	@Override
	public boolean handleFault(SOAPMessageContext context) {
		return false;
	}

	@Override
	public void close(MessageContext context) {
	}

	@Override
	public Set<QName> getHeaders() {
		return new HashSet<QName>();
	}

}
