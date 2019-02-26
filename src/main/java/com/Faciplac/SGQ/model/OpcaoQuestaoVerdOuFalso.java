package com.Faciplac.SGQ.model;


/*
@Entity
@Table(name = "opcaoQuestaoVerdOuFalso")
public class OpcaoQuestaoVerdOuFalso{
	
	@GenericGenerator(
            name = "opcaoQuestaoVerdOuFalsoSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "opcaoQuestaoVerdOuFalsoSequence"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
	
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "opcaoQuestaoVerdOuFalsoSequenceGenerator", sequenceName = "opcaoQuestaoVerdOuFalsoSequence")
	@GeneratedValue(generator = "opcaoQuestaoVerdOuFalsoSequenceGenerator")
	@Column(name = "idopcaoquestaoverdoufalso")
	private long idOpcaoQuestaoVerdOuFalso;
	
	@Column(name = "enunciado")
	private String enunciado;
	
	@Column(name = "respostacerta")
	private boolean respostaCerta;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idquestao", nullable = true)
    private Questao questao;
	
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
	
	
}*/
