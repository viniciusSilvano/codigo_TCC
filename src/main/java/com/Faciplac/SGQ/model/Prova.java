package com.Faciplac.SGQ.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


//http://viralpatel.net/blogs/hibernate-self-join-annotations-one-to-many-mapping/
//http://www.guj.com.br/t/auto-relacionamento-jpa/206316/2
@Entity
public class Prova extends BaseModel{
	@GenericGenerator(
            name = "provaSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "provaSequence"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
	
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "provaSequenceGenerator", sequenceName = "provaSequence")
	@GeneratedValue(generator = "provaSequenceGenerator")
	@Column(name = "idprova")
	private Long idProva;
	
	@Column(name = "tipoprova")
	private String tipoProva;
	
	@Column(name = "descricao")
	@NotNull
	private String descricao;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iddisciplina", nullable = false)
	@NotNull
	private Disciplina disciplina;
	
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="idprovapai", referencedColumnName = "idprova")
	private Prova provapai;
	
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "prova_questao", joinColumns = @JoinColumn(name = "idprova", referencedColumnName = "idprova"), inverseJoinColumns = @JoinColumn(name = "idquestao", referencedColumnName = "idquestao"))
	private List<Questao> questoes;
	
	@OneToMany(mappedBy="provapai")
	private List<Prova> provasFilhas;
	
	@Column(name="datamodificacao")
	private LocalDateTime dataModificacao;
	
	@Column(name="datadecriacao")
	private LocalDateTime dataDeCriacao;
	
	@Column(name="responsavel")
	private String responsavel;
	
	@Column(name="qntquestoes")
	private int qntdQuestoes;
	
	@Transient
	private int qntQuestoesFaceis;
	@Transient
	private int qntQuestoesMedias;
	@Transient
	private int qntQuestoesDificeis;
	
	@Transient
	private int qntTiposProvas;
	@Transient
	private int qntBloomConhecimento = 0;
	@Transient
	private int qntBloomCompreensao = 0;
	@Transient
	private int qntBloomAplicacao = 0;
	@Transient
	private int qntBloomAnalise = 0;
	@Transient
	private int qntBloomSintese = 0;
	@Transient
	private int qntBloomAvaliacao = 0;

	
	public Long getIdProva() {
		return idProva;
	}
	public void setIdProva(Long idProva) {
		this.idProva = idProva;
	}
	public String getTipoProva() {
		return tipoProva;
	}
	public void setTipoProva(String tipoProva) {
		this.tipoProva = tipoProva;
	}
	public String getDescricao() {
		return descricao;
	}
	
	public Disciplina getDisciplina() {
		return disciplina;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	public Prova getProvapai() {
		return provapai;
	}
	public void setProvapai(Prova provapai) {
		this.provapai = provapai;
	}
	public List<Prova> getProvasFilhas() {
		return provasFilhas;
	}
	public void setProvasFilhas(List<Prova> provasFilhas) {
		this.provasFilhas = provasFilhas;
	}
	
	public String getResponsavel() {
		return responsavel;
	}
	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}
	public int getQntdQuestoes() {
		return qntdQuestoes;
	}
	public void setQntdQuestoes(int qntdQuestoes) {
		this.qntdQuestoes = qntdQuestoes;
	}
	public int getQntQuestoesFaceis() {
		return qntQuestoesFaceis;
	}
	
	public void setQntQuestoesFaceis(int qntQuestoesFaceis) {
		this.qntQuestoesFaceis = qntQuestoesFaceis;
	}
	
	public String getDataModificacao() {
		
		return dtf.format(dataModificacao);
	}
	public void setDataModificacao(LocalDateTime dataModificacao) {
		this.dataModificacao = dataModificacao;
	}
	public String getDataDeCriacao() {
		return dtf.format(dataDeCriacao);
	}
	public void setDataDeCriacao(LocalDateTime dataDeCriacao) {
		this.dataDeCriacao = dataDeCriacao;
	}
	public int getQntQuestoesMedias() {
		return qntQuestoesMedias;
	}
	public void setQntQuestoesMedias(int qntQuestoesMedias) {
		this.qntQuestoesMedias = qntQuestoesMedias;
	}
	public int getQntQuestoesDificeis() {
		return qntQuestoesDificeis;
	}
	public void setQntQuestoesDificeis(int qntQuestoesDificeis) {
		this.qntQuestoesDificeis = qntQuestoesDificeis;
	}
	public int getQntTiposProvas() {
		return qntTiposProvas;
	}
	public void setQntTiposProvas(int qntTiposProvas) {
		this.qntTiposProvas = qntTiposProvas;
	}
	
	
	public int getQntBloomConhecimento() {
		return qntBloomConhecimento;
	}
	public void setQntBloomConhecimento(int qntBloomConhecimento) {
		this.qntBloomConhecimento = qntBloomConhecimento;
	}
	public int getQntBloomCompreensao() {
		return qntBloomCompreensao;
	}
	public void setQntBloomCompreensao(int qntBloomCompreensao) {
		this.qntBloomCompreensao = qntBloomCompreensao;
	}
	public int getQntBloomAplicacao() {
		return qntBloomAplicacao;
	}
	public void setQntBloomAplicacao(int qntBloomAplicacao) {
		this.qntBloomAplicacao = qntBloomAplicacao;
	}
	public int getQntBloomAnalise() {
		return qntBloomAnalise;
	}
	public void setQntBloomAnalise(int qntBloomAnalise) {
		this.qntBloomAnalise = qntBloomAnalise;
	}
	public int getQntBloomSintese() {
		return qntBloomSintese;
	}
	public void setQntBloomSintese(int qntBloomSintese) {
		this.qntBloomSintese = qntBloomSintese;
	}
	public int getQntBloomAvaliacao() {
		return qntBloomAvaliacao;
	}
	public void setQntBloomAvaliacao(int qntBloomAvaliacao) {
		this.qntBloomAvaliacao = qntBloomAvaliacao;
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
	
	
	
}
