package one.digitallinnovation.gof.model;

import one.digitallinnovation.gof.model.entities.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {


}