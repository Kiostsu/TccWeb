package br.edu.utfpr.pb.tcc.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import br.edu.utfpr.pb.tcc.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
//Classe que recupera diversas informa��es sobre o token do usu�rio
@Component
public class TokenUtils {
	
	private final String AUDIENCE_WEB = "web";
	private final String AUDIENCE_MOBILE = "mobile";
	private final String AUDIENCE_TABLET = "tablet";

	private String secret = "dGNjDQo=";
	private Long expiration = 604800L;
	
	public String getUsernameFromToken(String token){
		String username;
		try {
			final Claims claims = this.getClaimsFromToken(token);
			username = claims.getSubject();
		} catch (Exception e) {
			username = null;
		}
		return username;
	}

	private Claims getClaimsFromToken(String token) { //Esse metodo � usado para todos os outros, pois recupera as
		//informa��es necess�rias para o funcionamento do token, gerado a partir do segredo para o usu�rio
		Claims claims;
		try {
			claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			claims = null;
		}
		return claims;
	}
	
	public Date getCreatedDateFromToken(String token){ //Data da cria��o do token
		Date created;
		try {
			final Claims claims = this.getClaimsFromToken(token);
			created = new Date((Long) claims.get("created"));
		} catch (Exception e) {
			created = null;
		}
		return created;
	}
	
	public Date getExpirationDateFromToken(String token){ //Data da expira��o do token
		Date expiration;
		try {
			final Claims claims = this.getClaimsFromToken(token);
			expiration = claims.getExpiration();
		} catch (Exception e) {
			expiration = null;
		}
		return expiration;
	}
	
	public String getAudienceFromToken(String token){ //Identifica propositos para quais o token ser� usado
		String audience;
		try {
			final Claims claims = this.getClaimsFromToken(token);
			audience = (String) claims.get("audience");
		} catch (Exception e) {
			audience = null;
		}
		return audience;
	}
	
	private Date generateCurrentDate(){ //Data atual
		return new Date(System.currentTimeMillis());
	}
	
	private Date generateExpirationDate(){ //Gera a data de expira��o do token
		return new Date(System.currentTimeMillis() + this.expiration * 1000);
	}
	
	private Boolean isTokenExpired(String token){
		final Date expiration  = this.getExpirationDateFromToken(token);
		return expiration.before(this.generateCurrentDate());
	}
	
	private Boolean isCreatedBeforeLastPasswordReset( //Verifica se o usuario mudou a senha e est� usando o token com a senha antiga
			Date created, Date lastPasswordReset){
		return (lastPasswordReset !=null  && created.before(lastPasswordReset));
	}
	
	private Boolean ignoreTokenExpiration(String token){//Algumas regras para ignorar a expira��o (por exemplo logar pelo celular no FB)
		String audience = 	this.getAudienceFromToken(token);
		return (this.AUDIENCE_TABLET.equals(audience) ||
				this.AUDIENCE_MOBILE.equals(audience));
	}
	
	public String generateToken(UserDetails userDetails){ //Gera o token
		Map<String, Object> claims = new HashMap<>();
		claims.put("sub", userDetails.getUsername());
		claims.put("audience", this.AUDIENCE_WEB);
		claims.put("created", this.generateCurrentDate());
		return this.generateToken(claims);
	}

	private String generateToken(//Criptografa o token
			Map<String, Object> claims) {
		return Jwts.builder().setClaims(claims)
		.setExpiration(this.generateExpirationDate())
				.signWith(SignatureAlgorithm.HS512, this.secret)
				.compact();
	}
	
	public Boolean canTokenBeRefreshed(String token, //Verifica se � poss�vel atualizar o token
			Date lastPasswordReset){
		final Date created = this.getCreatedDateFromToken(token);
	
		return (!(this.isCreatedBeforeLastPasswordReset(created, lastPasswordReset)) &&
				(!(this.isTokenExpired(token)) || this.ignoreTokenExpiration(token)));
	}
	
	public String refreshToken(String token){ //Atualiza o token
		String refreshedToken;
		try {
			final Claims claims = 	this.getClaimsFromToken(token);
			claims.put("created",	this.generateCurrentDate());
			refreshedToken = this.generateToken(claims);
		} catch (Exception e) {
			refreshedToken = null;
		}
		return refreshedToken;
	}
	
	public Boolean validateToken(String token, //Valida a integridade do token
			UserDetails userDetails){
		Usuario user = (Usuario) userDetails;
		final String username =	this.getUsernameFromToken(token);
		final Date created  = 	this.getCreatedDateFromToken(token);
		
		return (username.equals(user.getUsername())
				&& !(this.isTokenExpired(token))
				&& !(this.isCreatedBeforeLastPasswordReset(created,
						user.getLastPasswordReset())));
				
	}
}

















