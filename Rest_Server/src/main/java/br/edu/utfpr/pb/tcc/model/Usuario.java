package br.edu.utfpr.pb.tcc.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Setter;

@Entity
@Data
public class Usuario implements UserDetails{
	private static final long serialVersionUID = 1L;
	private static final BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 50, nullable = false)
	private String username;
	
	@Column(length = 150, nullable = false)
	private String nome;
	
	@Column(length = 512, nullable = false)
	@Setter
	private String password;
	
	@Column(length = 15, nullable = false)
	private String CPF;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idMercado", referencedColumnName="id")
	private Mercado mercado;
		
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Setter
	private Set<Permissao> permissoes;
	
	private Date lastPasswordReset;
	
	public Set<Permissao> getPermissoes() {
		return permissoes;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> auto = new ArrayList<>();
		auto.addAll(getPermissoes());
		return auto;
	}
	
	public void addPermissao(Permissao permissao){
		if (permissoes == null){
			permissoes = new HashSet<>();
		}
		permissoes.add(permissao);
	}
	
	public String getEncodedPassword(String pass){
		if (! pass.isEmpty()){
			return bCrypt.encode(pass);
		}
		return pass;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@JsonIgnore
	public Date getLastPasswordReset() {
		return this.lastPasswordReset;
	}

	public void setLastPasswordReset(Date lastPasswordReset) {
		this.lastPasswordReset = lastPasswordReset;
	}
}
