package com.Faciplac.SGQ.model;

import java.util.ArrayList;
import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "disciplina")
public class Disciplina {
	
	@GenericGenerator(
            name = "disciplinaSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "disciplinaSequence"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
	
	@Id
	@SequenceGenerator(name = "disciplinaSequenceGenerator", sequenceName = "disciplinaSequence")
	@GeneratedValue(generator = "disciplinaSequenceGenerator")
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "iddisciplina")
	private Long idDisciplina;

	@NotNull
	@Size(min = 1, max = 50)
	@Column(name = "nomedisciplina")
	private String nomeDisciplina;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "disciplina")
	private List<Questao> questoes;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "disciplina")
	private List<Prova> provas;
	
	@Transient
	private int qntQuestoesFaceis;
	@Transient
	private int qntQuestoesMedias;
	@Transient
	private int qntQuestoesDificeis;
	
	@Transient
	private int qntBloomConhecimento;
	@Transient
	private int qntBloomCompreensao;
	@Transient
	private int qntBloomAplicacao;
	@Transient
	private int qntBloomAnalise;
	@Transient
	private int qntBloomSintese;
	@Transient
	private int qntBloomAvaliacao;



	public Long getIdDisciplina() {
		return idDisciplina;
	}

	public void setIdDisciplina(Long idDisciplina) {
		this.idDisciplina = idDisciplina;
	}

	public String getNomeDisciplina() {
		return nomeDisciplina;
	}

	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}

	public List<Questao> getQuestoes() {
		if(this.questoes == null) {
			this.questoes = new ArrayList<Questao>();
		}
		return questoes;
	}

	public void setQuestoes(List<Questao> questoes) {
		this.questoes = questoes;
	}

	public int getQntQuestoesFaceis() {
		return qntQuestoesFaceis;
	}

	public void setQntQuestoesFaceis(int qntQuestoesFaceis) {
		this.qntQuestoesFaceis = qntQuestoesFaceis;
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
	
	
	

}
