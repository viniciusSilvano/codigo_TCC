package com.Faciplac.SGQ.model;

/*
@Entity
@Table(name = "opcaoquestaoobjetiva")
public class OpcaoQuestaoObjetiva{
	
	@GenericGenerator(
            name = "opcaoQuestaoObjetivaSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "opcaoQuestaoObjetivaSequence"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
	
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "opcaoQuestaoObjetivaSequenceGenerator", sequenceName = "opcaoQuestaoObjetivaSequence")
	@GeneratedValue(generator = "opcaoQuestaoObjetivaSequenceGenerator")
	@Column(name = "idopcaoquestaoobjetiva")
	private Long idOpcaoQuestaoObjetiva;
	
	@Column(name = "enunciado")
	private String enunciado;
	
	@Column(name = "respostacerta")
	private boolean respostaCerta;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idquestao", referencedColumnName = "idquestao", nullable = true)
    private Questao questao;
	
	
	
	public OpcaoQuestaoObjetiva(String enunciado, boolean respostaCerta) {
		super();
		this.enunciado = enunciado;
		this.respostaCerta = respostaCerta;
	}
	
	public OpcaoQuestaoObjetiva() {
		
	}
	
	public String getEnunciado() {
		return enunciado;
	}
	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}
	public boolean isRespostaCerta() {
		return respostaCerta;
	}
	public void setRespostaCerta(boolean respostaCerta) {
		this.respostaCerta = respostaCerta;
	}

	public Questao getQuestao() {
		return questao;
	}

	public void setQuestao(Questao questao) {
		this.questao = questao;
	}
	
}*/
