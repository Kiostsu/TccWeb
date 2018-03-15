package br.edu.utfpr.pb.tcc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "produto")
@Data
@EqualsAndHashCode(of = "id")
public class Produto implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome", length = 100, nullable = false)
	private String nome;

	@Column(name = "descricao", length = 1000, nullable = false)
	private String descricao;
	
	@Column(name = "valor", nullable = false)
	private Double valor;
	
	@Column(name = "valorPromocional", nullable = true)
	private Double valorPromocional;
	
	@Column(name = "dataPromo", nullable = true)
	private Date dataPromo;
	
	@Column(name = "fimDataPromo", nullable = true)
	private Date fimDataPromo;	
	
	@Column(name = "codigoApontamento", nullable = true)
	private String codigoApontamento;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idcategoria", referencedColumnName = "id")
	private Categoria categoria;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idMercado", referencedColumnName = "id")
	private Mercado mercado;
	
}







