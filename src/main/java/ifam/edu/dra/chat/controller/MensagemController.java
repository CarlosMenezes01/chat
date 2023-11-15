package ifam.edu.dra.chat.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ifam.edu.dra.chat.dto.MensagemDTO;
import ifam.edu.dra.chat.model.Contato;
import ifam.edu.dra.chat.model.Mensagem;
import ifam.edu.dra.chat.repositories.ContatoRepository;
import ifam.edu.dra.chat.repositories.MensagemRepository;
import ifam.edu.dra.chat.service.MensagemService;

@RestController
@RequestMapping
public class MensagemController {
	
	@Autowired
	MensagemService mensagemService;
	
	@Autowired
    private MensagemRepository mensagemRepository;

    @Autowired
    private ContatoRepository contatoRepository;

    @PostMapping("/mensagem")
    public ResponseEntity<?> criarMensagem(@RequestBody MensagemDTO mensagemDTO) {
        // Busque o Contato com base no emissor e receptor
        Optional<Contato> emissorOptional = contatoRepository.findById(mensagemDTO.getEmissor());
        Optional<Contato> receptorOptional = contatoRepository.findById(mensagemDTO.getReceptor());

        if (emissorOptional.isPresent() && receptorOptional.isPresent()) {
            Contato emissor = emissorOptional.get();
            Contato receptor = receptorOptional.get();

            // Crie uma instância de Mensagem a partir do DTO
            Mensagem mensagem = new Mensagem();
            mensagem.setId(mensagemDTO.getId());
            //mensagem.setDataHora(mensagemDTO.getDataHora());
            mensagem.setConteudo(mensagemDTO.getConteudo());
            mensagem.setEmissor(emissor);
            mensagem.setReceptor(receptor);

            // Salve a mensagem
            mensagemRepository.save(mensagem);
            
        	
            //return ResponseEntity.ok("Mensagem criada com sucesso");
            //return ResponseEntity.created(null).body();
            return ResponseEntity.ok(mensagem);
        } else {
            return ResponseEntity.badRequest().body("Contato do emissor ou receptor não encontrado");
        }
    }


    // Outros métodos do controlador

    
    @GetMapping("/mensagem")
    ResponseEntity<List<MensagemDTO>> getMensagens() {
        List<Mensagem> mensagens = mensagemService.getMensagens();
        if (mensagens.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());

        // Transformar as mensagens em DTOs com IDs de emissores e receptores
        List<MensagemDTO> mensagemDTOs = mensagens.stream().map(mensagem -> {
            MensagemDTO mensagemDTO = new MensagemDTO();
            mensagemDTO.setId(mensagem.getId());
            mensagemDTO.setDataHora(mensagem.getDataHora());
            mensagemDTO.setConteudo(mensagem.getConteudo());
            mensagemDTO.setEmissor(mensagem.getEmissor().getId());
            mensagemDTO.setReceptor(mensagem.getReceptor().getId());
            return mensagemDTO;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(mensagemDTOs);
    }
       
	/*@GetMapping("/mensagem")
	ResponseEntity<List<Mensagem>> getMensagens() {
		// Obtém a lista de mensagens do serviço
		List<Mensagem> mensagens = mensagemService.getMensagens();
		
		// Verifica se a lista de mensagens está vazia
		if(mensagens.isEmpty())
			// Retorna uma resposta com status HTTP 404 (Not Found) e uma lista vazia caso não haja mensagens
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensagens);
		
		// Transformar as mensagens em DTOs com nomes de emissores e receptores
	    List<MensagemDTO> mensagemDTOs = mensagens.stream().map(mensagem -> {
	    	// Cria um novo DTO para cada mensagem
	        MensagemDTO mensagemDTO = new MensagemDTO();
	        
	        // Define os campos do DTO com base nos dados da mensagem
	        mensagemDTO.setId(mensagem.getId());
	        mensagemDTO.setDataHora(mensagem.getDataHora());
	        mensagemDTO.setConteudo(mensagem.getConteudo());
	        
	        // Define o campo "emissor" do DTO como o nome do emissor da mensagem
	        mensagemDTO.setEmissor(mensagem.getEmissor().getNome());
	        
	        // Define o campo "receptor" do DTO como o nome do receptor da mensagem
	        mensagemDTO.setReceptor(mensagem.getReceptor().getNome());
	        
	        // Retorna o DTO criado
	        return mensagemDTO; // Coleta os DTOs em uma lista
	    }).collect(Collectors.toList());
		
	    // Retorna uma resposta com status HTTP 200 (OK) e a lista de DTOs como corpo da resposta
		return ResponseEntity.ok(mensagens);
	}*/

	@GetMapping("/{id}")
	ResponseEntity<Mensagem> getMensagem(@PathVariable Long id) {
		try {
			return ResponseEntity.ok(mensagemService.getMensagem(id));	
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Mensagem());
		}
	}

	/*Esses metodos foram copiados do contato controle
	 * porem a classe mensagem tem 2 atrbiutos que são objetos
	 * de outra classe, por isso não posso criar a mensagem direto
	 * estou adequando para usar um dto
	 * @PostMapping
	ResponseEntity<Mensagem> setMensagem(@RequestBody Mensagem mensagem) {
		mensagemService.setMensagem(mensagem);
		return ResponseEntity.created(null).body(mensagem);
	}
	
	@PostMapping("/mensagem")
	public ResponseEntity<?> criarMensagem(@RequestBody Mensagem mensagem) {
	    // Use mensagem.getEmissorId() para buscar o Contato correspondente
	    // e configure o Contato na mensagem
	    // Outro processamento
	    return ResponseEntity.ok("Mensagem criada com sucesso");
	}*/

	@PutMapping("/{id}")
	 ResponseEntity<Mensagem> setMensagem(@RequestBody Mensagem mensagem, @PathVariable Long id) {
		try {
			return ResponseEntity.accepted().body(mensagemService.setMensagem(id, mensagem));	
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Mensagem());
		}
	}
}
