package ifam.edu.dra.chat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ifam.edu.dra.chat.model.Contato;
import ifam.edu.dra.chat.model.Mensagem;
import java.util.List;


public interface MensagemRepository extends JpaRepository<Mensagem, Long> {
	
	List<Mensagem> findAllByReceptor(Contato receptor);
}
