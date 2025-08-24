package br.com.mrchagas.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()

                .info(new Info()
                        .title("Cadastro API")
                        .description("Crud Simples de Cadastro de Contatos")
                        .version("1.1.0")
                        .contact(new Contact()
                                .name("Marcos Chagas")
                                .url("https://github.com/marcosicoaraci/cadastroapi")
                                .email("marcos.roberto.icoaraci@gmail.com"))
                        .license(new License()
                                .name("Apache License Version 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0"))
                )
                .externalDocs(new ExternalDocumentation()
                        .description("Perfil no Linkedin")
                        .url("https://www.linkedin.com/in/marcos-roberto-0741a776/"));
    }

}

