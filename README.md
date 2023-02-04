# Gerenciador de Pessoas
Esse é um projeto simples de API para gerenciamento de informações de pessoas e endereços. É desenvolvido em Java utilizando o framework Spring Boot.

## Requisitos
* Java.
* Spring Boot.
* Maven.
* Banco de dados H2.

## Endpoints

### Pessoa
* GET /Person: retorna uma lista de todas as pessoas cadastradas.
* GET /Person/{id}: retorna uma pessoa específica com base no id informado.
* GET /Person/{id}/main: retorna o endereço principal com base no id informado.
* GET /Person/{id}/Address: retorna uma lista do endereços cadastrados com base no id informado.
* POST /Person: cria uma nova pessoa.
* PUT /Person/{id}: atualiza uma pessoa existente com base no id informado.
* DELETE /Person/{id}: exclui uma pessoa existente com base no id informado.

### Endereço
* GET /Address: retorna uma lista de todos os endereços cadastrados.
* GET /Address/{id}: retorna um endereço específico com base no id informado.
* POST /Address: cria um novo endereço.
* PUT /Address/{id}: atualiza um endereço existente com base no id informado.
* DELETE /Address/{id}: exclui um endereço existente com base no id informado.

## Modelo de dados

### Pessoa
* id: identificador único (gerado automaticamente pelo banco de dados).
* name: nome da pessoa.
* birth: data de nascimento da pessoa.
* address: endereço da pessoa (referência ao objeto Endereço).

### Endereço
* id: identificador único (gerado automaticamente pelo banco de dados).
* street: nome da rua.
* cep: código de endereçamento postal.
* number: número da residência.
* city: cidade.
* main: endereço principal.
