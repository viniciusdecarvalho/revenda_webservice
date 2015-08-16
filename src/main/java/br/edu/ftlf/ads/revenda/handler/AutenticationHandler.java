package br.edu.ftlf.ads.revenda.handler;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import javax.xml.ws.soap.SOAPFaultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.edu.ftlf.ads.revenda.dao.DaoFactory;
import br.edu.ftlf.ads.revenda.dao.FuncionariosDao;
import br.edu.ftlf.ads.revenda.model.Usuario;

public class AutenticationHandler implements SOAPHandler<SOAPMessageContext> {

	private static final String LOGIN_OR_SENHA_NOT_FOUND = "login e/ou senha nao informados.";
	private static final Logger log = LoggerFactory.getLogger(AutenticationHandler.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean handleMessage(SOAPMessageContext context) {
		
		if (!isRequest(context) || isAutentica(context))
			return true;
		
		Usuario usuario = new Usuario();
		SOAPMessage message = context.getMessage();
		try {
			
//			String user = context.get(BindingProvider.USERNAME_PROPERTY).toString();
//			String pass = context.get(BindingProvider.PASSWORD_PROPERTY).toString();
			
			Map<String, List<String>> header = (Map<String, List<String>>) context.get(MessageContext.HTTP_REQUEST_HEADERS);
			
			List<String> username = header.getOrDefault("username", Collections.emptyList());
			List<String> password = header.getOrDefault("password", Collections.emptyList());

			if (username.isEmpty() || password.isEmpty()) {
				generateSOAPErrMessage(message, LOGIN_OR_SENHA_NOT_FOUND);
			}
			
			String login = username.get(0);
			String senha = password.get(0);
			
			if (isNullOrEmpty(login) || isNullOrEmpty(senha)) {
				generateSOAPErrMessage(message, LOGIN_OR_SENHA_NOT_FOUND);
			}
			
			usuario.setLogin(username.get(0));
			usuario.setSenha(password.get(0));
			log.debug("autenticando login {}", usuario);
			
			FuncionariosDao dao = DaoFactory.getFuncionariosDao();
			boolean hasLogin = dao.hasLogin(usuario);

			log.debug("{} {} possui login", usuario, hasLogin ? "":"nao");
			
			if (!hasLogin) {
				generateSOAPErrMessage(message, "login e/ou senha invalidos");
			}
		} catch (SOAPException e) {
		}
		
		return true;
	}

	private boolean isAutentica(SOAPMessageContext context) {
		String method = (String) context.get(MessageContext.HTTP_REQUEST_METHOD);
		return ("autentica".equals(method));
	}

	private boolean isRequest(SOAPMessageContext context) {
		return (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY) == false;
	}
	
	private void generateSOAPErrMessage(SOAPMessage msg, String reason) throws SOAPException {
		SOAPBody soapBody = msg.getSOAPPart().getEnvelope().getBody();
		SOAPFault soapFault = soapBody.addFault();
		soapFault.setFaultString(reason);
		throw new SOAPFaultException(soapFault); 
    }

	private boolean isNullOrEmpty(String value) {
		if (value == null)
			return true;
		if (value.isEmpty())
			return true;
		return false;
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
		return null;
	}

}
