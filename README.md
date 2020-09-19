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

Para efetuar o cadastro de pessoa alguns serviços foram criados,além do cadastro de pessoa, outros serviços
auxiliares também foram criados para complementar o cadastro de pessoa como: contato(para cadastrar
os contatos de telefone da pessoa), dependente (para cadastrar os dependentes) e endereço para cadastrar 
os endereços.

O arquivo json com a lista dos endpoints que poderão ser importados para o Postman estão inclusos no diretório 
"postman" localizado na pasta "src" do projeto. A seguir segue a descrição dos serviços:


Cadastrar de Pessoa
------------------
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

@PathVariable("id")

URL: http://localhost:8080/cadastro-api/pessoas/delete/2

CONTENT-TYPE: application/json

Buscar Pessoa
-------------

REQUISIÇÃO: GET

@PathVariable("id")

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

@PathVariable("idPessoa")

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

@PathVariable("idPessoa")

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

@PathVariable("idPessoa")

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






















 
 
 
 