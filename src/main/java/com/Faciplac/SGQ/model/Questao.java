package com.Faciplac.SGQ.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.Faciplac.SGQ.util.TaxonomiaDeBloomEnum;

//https://www.callicoder.com/hibernate-spring-boot-jpa-one-to-many-mapping-example/
@Entity
@Table(name = "questao")
public class Questao extends BaseModel {
	
	@GenericGenerator(
            name = "questaoSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "questaoSequence"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
	
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "questaoSequenceGenerator", sequenceName = "questaoSequence")
	@GeneratedValue(generator = "questaoSequenceGenerator")
	@Column(name = "idquestao")
	private long idQuestao;
	
	@NotNull
	@Column(name = "descricao")
	private String descricao;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iddisciplina", nullable = false)
    private Disciplina disciplina;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idusuario", nullable = true)
    private Usuario usuario;
	
	@Column(name = "datacriacao")
	private LocalDateTime dataCriacao;
	
	@Column(name = "datadeuso")
	private LocalDateTime dataDeUso;
	/*
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "questao")
	private List<OpcaoQuestaoObjetiva> opcoesObjetivas;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "questao")
	private Set<OpcaoQuestaoPorque> opcoesPorque = new HashSet<>();
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "questao")
	private Set<OpcaoQuestaoVerdOuFalso> opcoesVerdOuFalso = new HashSet<>();
	*/
	@ManyToMany(mappedBy = "questoes")
	private List<Prova> provas;
	
	@Column(name = "dificuldade")
	private String dificuldade;
	
	@Column(name = "enunciado")
	private String enunciado;
	
	@Transient
	private String conteudoEnunciado;
	
	@Column(name = "tipoquestao")
	@NotNull
	private String tipoQuestao;
	
	@Column(name = "situacao")
	private String situacao;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "classificacaodebloom")
	@NotNull
	private TaxonomiaDeBloomEnum classificacaoTaxonomiaBloom;


	public long getIdQuestao() {
		return idQuestao;
	}

	public void setIdQuestao(long idQuestao) {
		this.idQuestao = idQuestao;
	}

	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	/*
	public List<OpcaoQuestaoObjetiva> getOpcoesObjetivas() {
		if (this.opcoesObjetivas == null) {
            this.opcoesObjetivas = new ArrayList<OpcaoQuestaoObjetiva>();
        }
		return opcoesObjetivas;
	}

	public void setOpcoesObjetivas(List<OpcaoQuestaoObjetiva> opcoesObjetivas) {
		this.opcoesObjetivas = opcoesObjetivas;
	}

	public Set<OpcaoQuestaoPorque> getOpcoesPorque() {
		return opcoesPorque;
	}

	public void setOpcoesPorque(Set<OpcaoQuestaoPorque> opcoesPorque) {
		this.opcoesPorque = opcoesPorque;
	}

	public Set<OpcaoQuestaoVerdOuFalso> getOpcoesVerdOuFalso() {
		return opcoesVerdOuFalso;
	}

	public void setOpcoesVerdOuFalso(Set<OpcaoQuestaoVerdOuFalso> opcoesVerdOuFalso) {
		this.opcoesVerdOuFalso = opcoesVerdOuFalso;
	}*/

	public String getDificuldade() {
		return dificuldade;
	}

	public void setDificuldade(String dificuldade) {
		this.dificuldade = dificuldade;
	}

	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	public String getTipoQuestao() {
		return tipoQuestao;
	}

	public void setTipoQuestao(String tipoQuestao) {
		this.tipoQuestao = tipoQuestao;
	}

	
	public String getDataCriacao() {
		return dtf.format(dataCriacao);
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getDataDeUso() {
		
		return dtf.format(dataDeUso);
	}

	public void setDataDeUso(LocalDateTime dataDeUso) {
		this.dataDeUso = dataDeUso;
	}

	public List<Prova> getProvas() {
		if(provas == null) {
			provas = new ArrayList<Prova>();
		}
		
		return provas;
	}

	public void setProvas(List<Prova> provas) {
		this.provas = provas;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getConteudoEnunciado() {
		return conteudoEnunciado;
	}

	public void setConteudoEnunciado(String conteudoEnunciado) {
		this.conteudoEnunciado = conteudoEnunciado;
	}

	public TaxonomiaDeBloomEnum getClassificacaoTaxonomiaBloom() {
		return classificacaoTaxonomiaBloom;
	}

	public void setClassificacaoTaxonomiaBloom(TaxonomiaDeBloomEnum classificacaoTaxonomiaBloom) {
		this.classificacaoTaxonomiaBloom = classificacaoTaxonomiaBloom;
	}

	
}
