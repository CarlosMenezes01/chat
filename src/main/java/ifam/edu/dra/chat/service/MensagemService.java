package ifam.edu.dra.chat.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ifam.edu.dra.chat.model.Contato;
import ifam.edu.dra.chat.model.Mensagem;
import ifam.edu.dra.chat.repositories.MensagemRepository;

@Service
public class MensagemService {
	
	@Autowired
	MensagemRepository mensagemRepository;
	
	public List<Mensagem> getMensagens(){
		return mensagemRepository.findAll();
	}
	
	public List<Mensagem> getMensagens(Contato receptor){
		return mensagemRepository.findAllByReceptor(receptor);
	}
	
	public Mensagem getMensagem(Long id) {
		Optional<Mensagem> optionalMensagem = mensagemRepository.findById(id);
		if(optionalMensagem.isPresent())
			return optionalMensagem.get();
		return new Mensagem();
	}
	
	public Mensagem setMensagem(Mensagem mensagem) {
		return mensagemRepository.save(mensagem);
	}
	
	public Mensagem setMensagem(Long id, Mensagem mensagem) {
		Optional<Mensagem> optionalMensagem = mensagemRepository.findById(id);
		if(optionalMensagem.isPresent()) {
			mensagem.setId(id);
			return mensagemRepository.save(mensagem);
		}
		return new Mensagem();
	}
}
