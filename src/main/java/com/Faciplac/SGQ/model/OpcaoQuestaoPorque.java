package com.Faciplac.SGQ.model;


/*
@Entity()
@Table(name = "opcaoquestaoporque")
public class OpcaoQuestaoPorque{
	
	@GenericGenerator(
            name = "opcaoQuestaoPorqueSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "opcaoQuestaoPorqueSequence"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
	
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "opcaoQuestaoPorqueSequenceGenerator", sequenceName = "opcaoQuestaoPorqueSequence")
	@GeneratedValue(generator = "opcaoQuestaoPorqueSequenceGenerator")
	@Column(name = "idopcaoquestaoporque")
	private Long idOpcaoQuestaoPorque;
	
	@Column(name = "enunciado1")
	private String enunciado1;
	
	@Column(name = "enunciado2")
	private String enunciado2;
	
	@Column(name = "respostacerta")
	boolean respostaCerta;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idquestao", nullable = true)
    private Disciplina questao;
	
	public String getEnunciado1() {
		return enunciado1;
	}
	public void setEnunciado1(String enunciado1) {
		this.enunciado1 = enunciado1;
	}
	public String getEnunciado2() {
		return enunciado2;
	}
	public void setEnunciado2(String enunciado2) {
		this.enunciado2 = enunciado2;
	}
	public boolean isRespostaCerta() {
		return respostaCerta;
	}
	public void setRespostaCerta(boolean respostaCerta) {
		this.respostaCerta = respostaCerta;
	}
	
	
	
}*/
