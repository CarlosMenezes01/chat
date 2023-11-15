package ifam.edu.dra.chat.dto;

import java.util.Date;

public class MensagemDTO {
	
	/*
	 * usar pra resolver o problema 
	 * do envio do json com 
	 * os 2 objetos de emissor 
	 * e receptor
	 * 
	 * 
	 * 
	 * */
	//dtoMensagem(){
	
	private Long id;
	private Date dataHora;
    private String conteudo;
    private Long emissor;
    private Long receptor;
    
    
    
    
	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
		
		
	}
	public Date getDataHora() {
        return dataHora;
	}
	
	//public String getDataHora() {		
	//	return dataHora;
	//}
	//public void setDataHora(String dataHora) {
	//	this.dataHora = dataHora;
	//}
	public String getConteudo() {
		return conteudo;
	}
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	public Long getEmissor() {
		return emissor;
	}
	public void setEmissor(Long emissor) {
		this.emissor = emissor;
	}
	/*public void setEmissor(String emissorNome) {
	    this.emissor = emissorNome;
	}*/
	public Long getReceptor() {
		return receptor;
	}
	public void setReceptor(Long receptor) {
		this.receptor = receptor;
	}
	//public void setReceptor(String receptorNome) {
	//    this.emissor = receptorNome;
	//}
    
    
	
	
		
		
	}


