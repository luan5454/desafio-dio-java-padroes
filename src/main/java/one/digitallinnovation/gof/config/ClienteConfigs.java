package one.digitallinnovation.gof.config;

import one.digitallinnovation.gof.model.ClienteRepository;
import one.digitallinnovation.gof.model.EnderecoRepository;
import one.digitallinnovation.gof.service.ClienteService;
import one.digitallinnovation.gof.service.ViaCepService;
import one.digitallinnovation.gof.service.impl.ClienteServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class ClienteConfigs {



    @Bean
    public ClienteService clienteService(ClienteRepository cliente, EnderecoRepository enderecoRepository, ViaCepService viaCepService){

        return new ClienteServiceImpl(cliente, enderecoRepository, viaCepService);
    }
}
