# Shopping List

**Shopping List** é uma plataforma para gerenciar listas de compras e itens, permitindo criar, atualizar, recuperar e excluir tanto as listas quanto seus respectivos itens. O projeto segue as melhores práticas de **API RESTful**, incluindo validação de entrada, tratamento de exceções e persistência de dados.

A aplicação foi desenvolvida utilizando:

- **Java 21**
- **Spring Boot** (Data JPA, Validation)
- **H2** (banco de dados em memória)
- **Lombok** para redução de código boilerplate

---

## 🚀 Como rodar o projeto localmente

### 1️⃣ Clonar o repositório

```sh
git clone git@github.com:HericlesSouza/shopping-list-api.git
cd shopping-list-api
```

### 2️⃣ Configurar o banco de dados

O projeto utiliza **H2** como banco de dados em memória, portanto não é necessária nenhuma configuração adicional.

### 3️⃣ Configurar variáveis de ambiente

As configurações do projeto foram definidas no arquivo `application.properties`, localizado em `src/main/resources/application.properties`.

### 4️⃣ Instalar as dependências

```sh
./mvnw clean install
```

### 5️⃣ Rodar a aplicação

```sh
./mvnw spring-boot:run
```

A aplicação estará disponível em: `http://localhost:8080`

Para acessar o banco de dados H2, utilize `http://localhost:8080/h2-console` e configure a URL de conexão conforme o `application.properties`.

---

## 📌 Endpoints da API

### 🛒 Listas de Compras

|Método|Endpoint|Responsabilidade|
|---|---|---|
|**POST**|`/purchaseList`|Criar uma nova lista de compras|
|**GET**|`/purchaseList`|Pegar todas as listas de compras|
|**GET**|`/purchaseList/{purchaseListId}`|Pegar uma lista de compras específica|
|**DELETE**|`/purchaseList/{purchaseListId}`|Deletar uma lista|

### 🛍️ Itens da Lista de Compras

|Método|Endpoint|Responsabilidade|
|---|---|---|
|**PATCH**|`/purchaseList/{purchaseListId}/{itemName}`|Atualizar os dados de um item da lista|
|**DELETE**|`/purchaseList/{purchaseListId}/{itemName}`|Deletar um item da lista|

#### 📌 Status de Respostas

- **`200 OK`** - Operação realizada com sucesso.
- **`201 CREATED`** - Recurso criado com sucesso.
- **`204 NO CONTENT`** - Recurso removido com sucesso.
- **`400 BAD REQUEST`** - Erro de validação nos dados enviados.
- **`404 NOT FOUND`** - Recurso não encontrado.

---

## 🔄 Importação de Rotas no Insomnia ou Postman

Para facilitar os testes da API, você pode importar todas as rotas diretamente no **Insomnia** ou no **Postman**.

📥 **Baixar export do Insomnia:** [Insomnia_Export.json](./RoutesJSON.json)

### Como Importar no Insomnia:
1. Abra o Insomnia
2. Vá para **Application > Import/Export**
3. Clique em **Import Data** e selecione o arquivo JSON baixado
4. Todas as rotas estarão disponíveis para uso

### Como Importar no Postman:
1. Abra o Postman
2. Vá para **File > Import**
3. Selecione o arquivo JSON baixado
4. As rotas serão adicionadas automaticamente

---

## 🛠 Tecnologias Utilizadas

- **Spring Boot** para construção da API
- **Spring Data JPA** para persistência de dados
- **H2 Database** para armazenamento em memória
- **Lombok** para redução de código repetitivo
- **Spring Validation** para validação de entrada

---

## 📩 Contato

Caso tenha dúvidas ou sugestões, entre em contato:

- **Email:** [hericlessouza01@gmail.com](mailto:hericlessouza01@gmail.com)
- **LinkedIn:** [Hericles Souza](https://www.linkedin.com/in/hericlessouza/)

🚀 **Shopping List - Organize suas compras com eficiência!**