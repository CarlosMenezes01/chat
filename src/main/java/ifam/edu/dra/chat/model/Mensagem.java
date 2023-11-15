package ifam.edu.dra.chat.model;

import javax.persistence.Id;
import javax.persistence.ManyToOne;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Mensagem {

	/*
	 {
   "id": "1",
   "dataHora": "17/08:17:52",
   "conteudo": "teste de mensagem",
   "emissor": "1",
   "receptor": "2"
   	}
	  */
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private final Date dataHora;
	private String conteudo;
	@ManyToOne
	private Contato emissor;
	@ManyToOne
	private Contato receptor;
	
	
	
		
	public Mensagem() {
		super();
		this.dataHora = new Date();
		
	}

	public Mensagem(Long id, Date dataHora, String conteudo, Contato emissor, Contato receptor) {
		super();
		this.id = id;
		this.conteudo = conteudo;
		this.emissor = emissor;
		this.receptor = receptor;
		this.dataHora = new Date();
	}

	public Date getDataHora() {
        return dataHora;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public Contato getEmissor() {
		return emissor;
	}

	public void setEmissor(Contato emissor) {
		this.emissor = emissor;
	}

	public Contato getReceptor() {
		return receptor;
	}

	public void setReceptor(Contato receptor) {
		this.receptor = receptor;
	}

	@Override
	public String toString() {
		return "Mensagem [id=" + id + ", dataHora=" + dataHora + ", conteudo=" + conteudo + ", emissor=" + emissor
				+ ", receptor=" + receptor + "]";
	}
	
}