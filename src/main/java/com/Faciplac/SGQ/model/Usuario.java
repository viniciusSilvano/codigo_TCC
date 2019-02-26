package com.Faciplac.SGQ.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


//http://www.baeldung.com/hibernate-inheritance
//https://stackoverflow.com/questions/15037349/creating-postgresql-tables-relationships-problems-with-relationships-one-t
@Entity
@Table(name="usuario")
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Usuario{
	@GenericGenerator(
            name = "userSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "userSequence"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
	
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "userSequenceGenerator", sequenceName = "userSequence")
	@GeneratedValue(generator = "userSequenceGenerator")
	@Column(name = "idusuario")
	private Long idUsuario;
	
	@NotNull
	@Size(min=11,max=14)
	@Column(name = "login")
	private String login;
	
	@Column(name = "senha")
	private String senha;
	
	@NotNull
	@Size(max=80)
	@Column(name = "nome")
	private String nome;
	
	@NotNull
	@Size(max=80)
	@Column(name = "email")
	private String email;
	
	@Column(name="ativo")
	private boolean ativo;
	
	@Column(name = "cargo")
	private String cargo;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usuario")
	private List<Questao> questoes;
	

	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "permissao_usuario", joinColumns = @JoinColumn(name = "idusuario", referencedColumnName = "idusuario"), inverseJoinColumns = @JoinColumn(name = "idpermissao", referencedColumnName = "idpermissao"))
	private List<Permissao> permissoes;
	
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<Questao> getQuestoes() {
		if(questoes == null) {
			questoes = new ArrayList<Questao>();
		}
		return questoes;
	}
	public void setQuestoes(List<Questao> questoes) {
		this.questoes = questoes;
	}
	public List<Permissao> getPermissoes() {
		if(this.permissoes == null) {
			permissoes = new ArrayList<Permissao>();
		}
		return permissoes;
	}
	
	
	public boolean isAtivo() {
		return ativo;
	}
	
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
	
	
}
