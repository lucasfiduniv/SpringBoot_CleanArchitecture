---

# Java Spring Boot Certification API

Este projeto é uma API RESTful desenvolvida em Java usando Spring Boot. Ela gerencia processos de certificação de estudantes, incluindo perguntas, respostas e rankings de certificação. O sistema permite que os usuários interajam com questões relacionadas a várias tecnologias, submetam respostas e acompanhem o desempenho dos estudantes.

## Funcionalidades

- Gerenciamento de certificações para estudantes.
- Obtenção do ranking dos 10 melhores alunos.
- Busca de perguntas e alternativas relacionadas a tecnologias específicas.
- Submissão de respostas e verificação de respostas corretas para certificações.
- Persistência de dados em um banco de dados PostgreSQL.

---

## Índice

- [Tecnologias](#tecnologias)
- [Instalação](#instalação)
  - [Pré-requisitos](#pré-requisitos)
  - [Configuração](#configuração)
  - [Rodando o Projeto Localmente](#rodando-o-projeto-localmente)
  - [Execução com Docker](#execução-com-docker)
- [Documentação da API](#documentação-da-api)
  - [Endpoints de Ranking](#endpoints-de-ranking)
  - [Endpoints de Questões](#endpoints-de-questões)
  - [Endpoints de Certificação](#endpoints-de-certificação)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Licença](#licença)

---

## Tecnologias

- **Java 11**
- **Spring Boot 2.5+**
- **Hibernate (JPA)**
- **PostgreSQL** (Banco de Dados)
- **Maven** (Gerenciador de Dependências)
- **Docker** (Para ambiente containerizado)

---

## Instalação

### Pré-requisitos

Antes de rodar o projeto, certifique-se de que você tenha as seguintes ferramentas instaladas:

- Java 11+
- Maven
- PostgreSQL (para desenvolvimento local)
- Docker (opcional, para rodar em containers)

### Configuração

1. Crie um banco de dados PostgreSQL:

   ```sql
   CREATE DATABASE certifications;
   ```

2. Configure o arquivo `application.properties` ou `.env` com as informações de conexão ao banco de dados:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/certifications
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha
   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
   spring.jpa.hibernate.ddl-auto=update
   ```

### Rodando o Projeto Localmente

1. Clone o repositório:

   ```bash
   git clone https://github.com/lucasfiduniv/SpringBoot_CleanArchitecture.git
   cd SpringBoot_CleanArchitecture
   ```

2. Instale as dependências:

   ```bash
   mvn clean install
   ```

3. Execute o projeto:

   ```bash
   mvn spring-boot:run
   ```

A API estará disponível em `http://localhost:8085`.

### Execução com Docker

Se preferir usar o Docker, você pode rodar o projeto em containers:

1. Execute o comando abaixo para subir os serviços:

   ```bash
   docker-compose up --build
   ```

2. A API estará acessível em `http://localhost:8085` e o PostgreSQL será executado dentro do container.

---

## Documentação da API

### Endpoints de Ranking

- **Obter Top 10 Certificações**

  Retorna os 10 melhores estudantes com base em suas pontuações.

  ```http
  GET /ranking/top10
  ```

  **Exemplo de Resposta**:

  ```json
  [
    {
      "id": "uuid",
      "technology": "Java",
      "grade": 9,
      "studentID": "uuid"
    },
    {
      "id": "uuid",
      "technology": "Python",
      "grade": 8,
      "studentID": "uuid"
    }
  ]
  ```

### Endpoints de Questões

- **Buscar Perguntas por Tecnologia**

  Retorna uma lista de perguntas e alternativas relacionadas a uma tecnologia específica.

  ```http
  GET /questions/technology/{technology}
  ```

  **Exemplo de Requisição**:

  ```bash
  GET /questions/technology/Java
  ```

  **Exemplo de Resposta**:

  ```json
  [
    {
      "id": "uuid-da-pergunta",
      "description": "O que é Java?",
      "technology": "Java",
      "alternatives": [
        {
          "id": "uuid-da-alternativa-1",
          "description": "Uma linguagem de programação"
        },
        {
          "id": "uuid-da-alternativa-2",
          "description": "Uma bebida"
        }
      ]
    }
  ]
  ```

### Endpoints de Certificação

- **Verificar se o Estudante Já Fez a Prova**

  Verifica se o estudante já completou a certificação para uma tecnologia específica.

  ```http
  POST /students/verifyIfhasCertification
  ```

  **Payload Exemplo**:

  ```json
  {
    "email": "estudante@email.com",
    "technology": "Java"
  }
  ```

  **Exemplo de Resposta**:

  - `"usuario ja fez a prova"`: Se o usuário já completou a certificação.
  - `"usuario nao fez a prova"`: Se o usuário ainda não fez a prova.

- **Enviar Respostas de Certificação**

  Submete as respostas de um estudante e calcula o resultado.

  ```http
  POST /students/certification/answer
  ```

  **Payload Exemplo**:

  ```json
  {
    "email": "estudante@email.com",
    "technology": "Java",
    "questionsAnswers": [
      {
        "id": "uuid-da-pergunta",
        "alternativeId": "uuid-da-alternativa",
        "isCorrect": null
      }
    ]
  }
  ```

  **Exemplo de Resposta**:

  Retorna os dados da certificação e as respostas submetidas:

  ```json
  {
    "id": "uuid-da-certificacao",
    "technology": "Java",
    "grade": 7,
    "studentID": "uuid-do-estudante",
    "answersCertificationsEntities": [
      {
        "id": "uuid-da-resposta",
        "certificationID": "uuid-da-certificacao",
        "questionID": "uuid-da-pergunta",
        "answerID": "uuid-da-alternativa",
        "isCorrect": true
      }
    ]
  }
  ```

---

## Estrutura do Projeto

```
src/
│
├── main/
│   ├── java/
│   │   └── com/
│   │       └── example/
│   │           └── java_springboot/
│   │               ├── modules/
│   │               │   ├── certifications/
│   │               │   │   ├── controllers/
│   │               │   │   └── useCases/
│   │               │   ├── questions/
│   │               │   │   ├── controllers/
│   │               │   │   ├── dto/
│   │               │   │   ├── entities/
│   │               │   │   ├── repositories/
│   │               │   │   └── useCases/
│   │               │   └── students/
│   │               │       ├── controllers/
│   │               │       ├── dto/
│   │               │       ├── entities/
│   │               │       ├── repositories/
│   │               │       └── useCases/
│   └── resources/
│       ├── static/
│       ├── templates/
│       ├── application.properties
│       └── create.sql
│
└── test/
```

### Explicação das Principais Pastas

- **`controllers/`**: Contém os controladores REST que gerenciam as requisições HTTP e retornam respostas.
- **`useCases/`**: Contém a lógica de negócio de cada funcionalidade, como busca de perguntas e validação de respostas.
- **`repositories/`**: Interfaces do Spring Data JPA que lidam com operações no banco de dados.
- **`dto/`**: Objetos de transferência de dados usados para enviar e receber informações via API.
- **`entities/`**: Modelos de dados que mapeiam para as tabelas no banco de dados.

---

## Licença

Este projeto está licenciado sob os termos da licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---

## Contribuições

Se você deseja contribuir com o projeto, sinta-se à vontade para abrir uma issue ou enviar um pull request. Todas as contribuições são bem-vindas!

---

## Contato

Para mais informações, entre em contato:

- GitHub: [lucasfiduniv](https://github.com/lucasfiduniv)
- Email: lucfiduniv@gmail.com

---
