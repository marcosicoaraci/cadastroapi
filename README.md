# cadastro-api
Api para cadastro de pessoas.
-----------------------------------------

Conforme recomendado, foi implementado uma API rest (crud) de cadastro de pessoa.

O projeto foi implementado utilizando as seguintes tecnologias:

 - Linguagem de programação Java na versão 8.0.
 
 - Spring Boot na versão 2.0.3.
 
 - Banco de dados Postgres na versão 10.
 
Instruções para Execução.
 -----------------------------
 
 Utilizando o seu SGBD de preferência, criar a base com o nome "contato_db" e executar os scripts de 
 criação das tabelas (ddl_contato_db ou backup_contato_db) inclusos no diretório "database", localizado 
 dentro da pasta "src" do projeto. Com o banco devidamente configurado agora podemos executar a
 aplicação.
 
 Para executar a aplicação, você pode utilizar a sua IDE de preferência(Eclipse,IntellIj, etc.)
 ou seguir as instruções de execução recomendadas pelo site da Spring conforme o link:
 https://spring.io/guides/gs/rest-service/.
 
Serviços criados.
----------------

Para efetuar o cadastro de pessoa, outros serviços
auxiliares também foram criados para complementar o cadastro como: contatos(para cadastrar
os contatos de telefone), dependentes (para cadastrar os dependentes) e endereços para cadastrar 
os endereços.

O arquivo json com a lista dos endpoints que poderão ser importados para o Postman estão inclusos no diretório 
"postman" localizado na pasta "src" do projeto. A seguir segue a descrição dos serviços:


Cadastrar Pessoa
----------------
REQUISIÇÃO: POST

URL: http://localhost:8080/cadastro-api/pessoas

CONTENT-TYPE: application/json

REQUEST:

{

    "nome": "Biff Howard Tannen",
    "apelido": "Biff", 
    "cpf": "99999999978",
    "profissao": "Student", 
    "salario": 10000, 
    "dataNascimento": "1980-09-17"
}

RESPONSE:

{

    "id": 5,
    "nome": "Biff Howard Tannen",
    "apelido": "Biff",
    "cpf": "99999999978",
    "profissao": "Student",
    "salario": 10000.0,
    "dataNascimento": "1980-09-17"
}

Editar Pessoa
-------------

REQUISIÇÃO: PUT

URL: http://localhost:8080/cadastro-api/pessoas/update

CONTENT-TYPE: application/json

REQUEST:

{

    "id": 2,
    "nome": "Martin Seamus McFly Junior",
    "apelido": "Marty McFly", 
    "cpf": "99999999999",
    "profissao": "Time Machine Driver", 
    "salario": 10000, 
    "dataNascimento": "1980-09-17"
}

RESPONSE:

{

    "id": 2,
    "nome": "Martin Seamus McFly Junior",
    "apelido": "Marty McFly",
    "cpf": "99999999999",
    "profissao": "Time Machine Driver",
    "salario": 10000.0,
    "dataNascimento": "1980-09-17"
}

Excluir Pessoa
--------------

REQUISIÇÃO: DELETE

PARÂMETRO: @PathVariable("id")

URL: http://localhost:8080/cadastro-api/pessoas/delete/2

CONTENT-TYPE: application/json

Buscar Pessoa
-------------

REQUISIÇÃO: GET

PARÂMETRO: @PathVariable("id")

URL: http://localhost:8080/cadastro-api/pessoas/3

CONTENT-TYPE: application/json

RESPONSE:

{

    "id": 3,
    "nome": "Lorraine Baines McFly",
    "apelido": "Lorraine",
    "cpf": "99999999998",
    "profissao": "Mother",
    "salario": 10000.0,
    "dataNascimento": "1960-09-16"
}

Listar Pessoas
--------------

REQUISIÇÃO: GET

URL: http://localhost:8080/cadastro-api/pessoas

CONTENT-TYPE: application/json

RESPONSE:

[

    {
        "id": 1,
        "nome": "Martin Seamus McFly",
        "apelido": "Marty McFly",
        "cpf": "99999999999",
        "profissao": "Time Machine Driver",
        "salario": 10000.0,
        "dataNascimento": "1980-09-16"
    },
    {
        "id": 2,
        "nome": "Biff Howard Tannen",
        "apelido": "Biff",
        "cpf": "99999999978",
        "profissao": "Student",
        "salario": 10000.0,
        "dataNascimento": "1980-09-16"
    },
    {
        "id": 3,
        "nome": "Lorraine Baines McFly",
        "apelido": "Lorraine",
        "cpf": "99999999998",
        "profissao": "Mother",
        "salario": 10000.0,
        "dataNascimento": "1960-09-16"
    }
]

Listar Contatos
---------------

Lista todos os contatos da pessoa.

REQUISIÇÃO: GET

PARÂMETRO: @PathVariable("idPessoa")

URL: http://localhost:8080/cadastro-api/pessoas/contatos/3

CONTENT-TYPE: application/json

RESPONSE:

[

    {
        "id": 1,
        "ddd": "91",
        "numero": "999999999",
        "tipoContato": "CONTATO",
        "pessoaContatoId": {
            "id": 3,
            "nome": "Lorraine Baines McFly",
            "apelido": "Lorraine",
            "cpf": "99999999998",
            "profissao": "Mother",
            "salario": 10000.0,
            "dataNascimento": "1960-09-16"
        }
    },
    {
        "id": 2,
        "ddd": "91",
        "numero": "99999999",
        "tipoContato": "RESIDENCIAL",
        "pessoaContatoId": {
            "id": 3,
            "nome": "Lorraine Baines McFly",
            "apelido": "Lorraine",
            "cpf": "99999999998",
            "profissao": "Mother",
            "salario": 10000.0,
            "dataNascimento": "1960-09-16"
        }
    }
]

Listar Dependentes
---------------

Lista todos os dependentes da pessoa.

REQUISIÇÃO: GET

PARÂMETRO: @PathVariable("idPessoa")

URL: http://localhost:8080/cadastro-api/pessoas/dependentes/3

CONTENT-TYPE: application/json

RESPONSE:

[

    {
        "id": 1,
        "nome": "Martin Seamus McFly",
        "tipoDependente": "FILHO",
        "pessoaDependenteId": {
            "id": 3,
            "nome": "Lorraine Baines McFly",
            "apelido": "Lorraine",
            "cpf": "99999999998",
            "profissao": "Mother",
            "salario": 10000.0,
            "dataNascimento": "1960-09-16"
        }
    },
    {
        "id": 2,
        "nome": "Farouk Bulsara",
        "tipoDependente": "FILHO",
        "pessoaDependenteId": {
            "id": 3,
            "nome": "Lorraine Baines McFly",
            "apelido": "Lorraine",
            "cpf": "99999999998",
            "profissao": "Mother",
            "salario": 10000.0,
            "dataNascimento": "1960-09-16"
        }
    }
]

Listar Endereços
----------------

Lista todos os endereços da pessoa.

REQUISIÇÃO: GET

PARÂMETRO: @PathVariable("idPessoa")

URL: http://localhost:8080/cadastro-api/pessoas/enderecos/3

CONTENT-TYPE: application/json

RESPONSE:

[

    {
        "id": 1,
        "tipoEndereco": "RESIDENCIAL",
        "nome": "BOAVENTURA DA SILVA",
        "numero": "s/n",
        "complemento": "ANTÔNIO BARRETO",
        "cep": "99999999",
        "bairro": "UMARIZAL",
        "cidade": "BELÉM",
        "estado": "PARÁ",
        "pais": "BRASIL",
        "pessoaEnderecoId": {
            "id": 3,
            "nome": "Lorraine Baines McFly",
            "apelido": "Lorraine",
            "cpf": "99999999998",
            "profissao": "Mother",
            "salario": 10000.0,
            "dataNascimento": "1960-09-16"
        }
    },
    {
        "id": 2,
        "tipoEndereco": "COMERCIAL",
        "nome": "VISCONDE DE SOUZA FRANCO",
        "numero": "s/n",
        "complemento": "RUA TIRADENTES",
        "cep": "99999999",
        "bairro": "UMARIZAL",
        "cidade": "BELÉM",
        "estado": "PARÁ",
        "pais": "BRASIL",
        "pessoaEnderecoId": {
            "id": 3,
            "nome": "Lorraine Baines McFly",
            "apelido": "Lorraine",
            "cpf": "99999999998",
            "profissao": "Mother",
            "salario": 10000.0,
            "dataNascimento": "1960-09-16"
        }
    }
]

Os recursos auxiliares seguem o mesmo padrão ao descrito acima. São os seguintes:

Cadastrar Dependente
------------------
REQUISIÇÃO: POST

PARÂMETRO: @PathVariable("idPessoa")

URL: http://localhost:8080/cadastro-api/dependentes/3/salvardependente

CONTENT-TYPE: application/json

REQUEST:

{

    "nome": "Martin Semus Mcfly",
    "tipoDependente": "FILHO"
}

RESPONSE:

    {
        "id": 1,
        "nome": "Martin Seamus McFly",
        "tipoDependente": "FILHO",
        "pessoaDependenteId": {
            "id": 3,
            "nome": "Lorraine Baines McFly",
            "apelido": "Lorraine",
            "cpf": "99999999998",
            "profissao": "Mother",
            "salario": 10000.0,
            "dataNascimento": "1960-09-16"
        }
    }

Editar Dependente
-------------

REQUISIÇÃO: PUT

PARÂMETRO: @PathVariable("idPessoa")

URL: http://localhost:8080/cadastro-api/dependentes/3/updatedependente

CONTENT-TYPE: application/json

REQUEST:

{

    "id":1,
    "nome": "Martin Seamus McFly de Azevedo",
    "tipoDependente": "FILHO"
}

RESPONSE:

{

    "id": 1,
    "nome": "Martin Seamus McFly de Azevedo",
    "apelido": "Marty McFly",
    "cpf": "99999999999",
    "profissao": "Time Machine Driver",
    "salario": 10000.0,
    "dataNascimento": "1980-09-17"
}

Excluir Dependente
--------------

REQUISIÇÃO: DELETE

PARÂMETRO: @PathVariable("id") neste caso é o id do dependente

URL: http://localhost:8080/cadastro-api/dependentes/delete/3

CONTENT-TYPE: application/json

Buscar Dependente
-------------

REQUISIÇÃO: GET

PARÂMETRO: @PathVariable("id") neste caso é o id do dependente

URL: http://localhost:8080/cadastro-api/dependentes/2

CONTENT-TYPE: application/json

RESPONSE:

{

    "id": 2,
    "nome": "Farouk Bulsara",
    "tipoDependente": "FILHO",
    "pessoaDependenteId": {
        "id": 3,
        "nome": "Lorraine Baines McFly",
        "apelido": "Lorraine",
        "cpf": "99999999998",
        "profissao": "Mother",
        "salario": 10000.0,
        "dataNascimento": "1960-09-16"
    }
}

Cadastrar Contato
------------------
REQUISIÇÃO: POST

PARÂMETRO: @PathVariable("idPessoa")

URL: http://localhost:8080/cadastro-api/contatos/3/salvarcontato

CONTENT-TYPE: application/json

REQUEST:

{

    "ddd": "91",
    "numero": "999799999", 
    "tipoContato": "COMERCIAL" 
}

RESPONSE:

    {
        "id": 1,
        "ddd": "91",
        "numero": "999999999",
        "tipoContato": "CONTATO",
        "pessoaContatoId": {
            "id": 3,
            "nome": "Lorraine Baines McFly",
            "apelido": "Lorraine",
            "cpf": "99999999998",
            "profissao": "Mother",
            "salario": 10000.0,
            "dataNascimento": "1960-09-16"
        }
    }

Editar Contato
-------------

REQUISIÇÃO: PUT

PARÂMETRO: @PathVariable("idPessoa")

URL: http://localhost:8080/cadastro-api/contatos/3/updatecontato

CONTENT-TYPE: application/json

REQUEST:

}
    
    "id": 1,
    "ddd": "98",
    "numero": "999799999",
    "tipoContato": "COMERCIAL"
}

RESPONSE:

    {
        "id": 1,
        "ddd": "98",
        "numero": "999799999",
        "tipoContato": "COMERCIAL",
        "pessoaContatoId": {
            "id": 3,
            "nome": "Lorraine Baines McFly",
            "apelido": "Lorraine",
            "cpf": "99999999998",
            "profissao": "Mother",
            "salario": 10000.0,
            "dataNascimento": "1960-09-16"
        }
    }

Excluir Contato
--------------

REQUISIÇÃO: DELETE

PARÂMETRO: @PathVariable("id") neste caso é o id do contato

URL: http://localhost:8080/cadastro-api/contatos/delete/3

CONTENT-TYPE: application/json

Buscar Contato
-------------

REQUISIÇÃO: GET

PARÂMETRO: @PathVariable("id") neste caso é o id do contato

URL: http://localhost:8080/cadastro-api/contatos/1

CONTENT-TYPE: application/json

RESPONSE:

{
    
    "id": 1,
    "ddd": "91",
    "numero": "999999999",
    "tipoContato": "CONTATO",
    "pessoaContatoId": {
        "id": 3,
        "nome": "Lorraine Baines McFly",
        "apelido": "Lorraine",
        "cpf": "99999999998",
        "profissao": "Mother",
        "salario": 10000.0,
        "dataNascimento": "1960-09-16"
    }
    
}

Cadastrar Endereço
------------------
REQUISIÇÃO: POST

PARÂMETRO: @PathVariable("idPessoa")

URL: http://localhost:8080/cadastro-api/enderecos/3/salvarendereco

CONTENT-TYPE: application/json

REQUEST:

{

    "tipoEndereco": "CONTATO",
    "nome": "VISCONDE DE INHAUMA", 
    "numero": "s/n",
    "complemento": "NAO TEM", 
    "cep": 99999999, 
    "bairro": "UMARIZAL",
    "cidade": "BELÉM",
    "estado": "PARÁ",
    "pais": "BRASIL"
}

RESPONSE:

    {
        "id": 1,
        "tipoEndereco": "RESIDENCIAL",
        "nome": "BOAVENTURA DA SILVA",
        "numero": "s/n",
        "complemento": "ANTÔNIO BARRETO",
        "cep": "99999999",
        "bairro": "UMARIZAL",
        "cidade": "BELÉM",
        "estado": "PARÁ",
        "pais": "BRASIL",
        "pessoaEnderecoId": {
            "id": 3,
            "nome": "Lorraine Baines McFly",
            "apelido": "Lorraine",
            "cpf": "99999999998",
            "profissao": "Mother",
            "salario": 10000.0,
            "dataNascimento": "1960-09-16"
        }
    },

Editar Endereço
-------------

REQUISIÇÃO: PUT

PARÂMETRO: @PathVariable("idPessoa")

URL: http://localhost:8080/cadastro-api/enderecos/3/updateendereco

CONTENT-TYPE: application/json

REQUEST:

{

    "id": 3,
    "tipoEndereco": "CONTATO",
    "nome": "VISCONDE DE INHAUMA",
    "numero": "s/n",
    "complemento": "ALFERES COSTA",
    "cep": "99999999",
    "bairro": "UMARIZAL",
    "cidade": "BELÉM",
    "estado": "PARÁ",
    "pais": "BRASIL"
}
RESPONSE:

{

    "id": 3,
    "tipoEndereco": "CONTATO",
    "nome": "VISCONDE DE INHAUMA",
    "numero": "10",
    "complemento": "ALFERES COSTA",
    "cep": "99999999",
    "bairro": "UMARIZAL",
    "cidade": "BELÉM",
    "estado": "PARÁ",
    "pais": "BRASIL",
    "pessoaEnderecoId": {
        "id": 3,
        "nome": "Lorraine Baines McFly",
        "apelido": "Lorraine",
        "cpf": "99999999998",
        "profissao": "Mother",
        "salario": 10000.0,
        "dataNascimento": "1960-09-16"
    }
}

Excluir Endereço
--------------

REQUISIÇÃO: DELETE

PARÂMETRO: @PathVariable("id") neste caso é o id do endereço

URL: http://localhost:8080/cadastro-api/enderecos/delete/3

CONTENT-TYPE: application/json

Buscar Endereço
-------------

REQUISIÇÃO: GET

PARÂMETRO: @PathVariable("id") neste caso é o id do endereço

URL: http://localhost:8080/cadastro-api/enderecos/1

CONTENT-TYPE: application/json

RESPONSE:

{

    "id": 1,
    "tipoEndereco": "RESIDENCIAL",
    "nome": "BOAVENTURA DA SILVA",
    "numero": "s/n",
    "complemento": "ANTÔNIO BARRETO",
    "cep": "99999999",
    "bairro": "UMARIZAL",
    "cidade": "BELÉM",
    "estado": "PARÁ",
    "pais": "BRASIL",
    "pessoaEnderecoId": {
        "id": 3,
        "nome": "Lorraine Baines McFly",
        "apelido": "Lorraine",
        "cpf": "99999999998",
        "profissao": "Mother",
        "salario": 10000.0,
        "dataNascimento": "1960-09-16"
    }
}





















 
 
 
 