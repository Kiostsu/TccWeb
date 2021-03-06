package br.edu.utfpr.pb.tcc.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
//Caso a autentica��o falhe para o usu�rio
@Component(value = "authenticationEntryPoint")
public class EntryPointUnauthorizedHandler 
			implements AuthenticationEntryPoint{

	@Override
	public void commence(HttpServletRequest request, 
			HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		response.sendError(
				HttpServletResponse.SC_UNAUTHORIZED, 
				"Acesso negado!");
		
	}

}
