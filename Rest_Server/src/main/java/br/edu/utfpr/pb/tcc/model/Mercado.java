package br.edu.utfpr.pb.tcc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(of = "id")
public class Mercado implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "razaoSocial", nullable = false)
	private String razaoSocial;
	
	@Column(name = "fantasia", nullable = false)
	private String fantasia;
	
	@Column(name = "CNPJ", nullable = false)
	private String CNPJ;
	
	@Column(name = "matriz", nullable = true)
	private boolean matriz;	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idCidade", referencedColumnName = "id")
	private Cidade cidade;	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idMercadoMatriz", referencedColumnName = "id")
	private Mercado idMatriz;	
	
}
