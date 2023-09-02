package one.digitallinnovation.gof.model;

import one.digitallinnovation.gof.model.entities.Endereco;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends CrudRepository<Endereco, String> {
}
