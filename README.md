Bank Transfers API & Frontend

#Sistema completo para agendamento de transferências financeiras com backend em Java 11 / Spring Boot 2.6.4 e frontend em Vue 3. Desenvolvido com foco em Clean Code, TDD, SOLID e arquitetura hexagonal.

  - Este projeto foi desenvolvido com base na arquitetura hexagonal para garantir um sistema desacoplado e fácil de manter.
  
  - Foco em abstrações: a lógica de negócio está isolada dos detalhes da infraestrutura (como banco e APIs externas), facilitando testes e manuntenções.
  
  - Separação clara de responsabilidades entre as camadas: domínio, aplicação, infraestrutura e interface web.
  
  - Uso dos princípios SOLID
  
  - Esse padrão garante um código limpo, modular e fácil de evoluir.

#Funcionalidades

  - Cadastro e login com autenticação via JWT + Spring Security
  
  - Agendamento de transferências financeiras
  
  - Cálculo automático de taxa conforme a data da transferência
  
  - Consulta do extrato de agendamentos
  
  - Sistema de log persistente no banco (com Envers)
  
  - Testes unitários com JUnit 5 + Mockito (em TDD)
  
  - API RESTful estruturada em MVC
  
  - Frontend com Vue 3 + Jest para testes unitários

#Requisitos

  - Java 11
  
  - Node.js 16+
  
  - NPM ou Yarn
  
  - Vue.js 3

#Como rodar o projeto

  -  Clone o repositório: 
     git clone https://github.com/Kaio-Alves/Project-Transfers-Bank.git
     cd Project-Transfers-Bank
     
  - Inicie o backend:
    cd back-end
    ./mvnw spring-boot:run

  - Inicie o frontend:
    cd front-end
    npm install
    npm run dev


#Testes

  - JUnit 5 e Mockito
  
  - Testes unitários em TDD cobrindo regras de negócio, controladores e integração com banco em memória H2

#Persistência

  - Banco de dados H2 em memória
  
  - JPA + Hibernate
  
  - Auditoria com Hibernate Envers (_AUD tables)
  
  - Sistema de logs persistente

#Acesso ao Console H2 (Banco em Memória)

  - O backend disponibiliza o console web do banco H2 para facilitar a visualização e depuração dos dados em memória.
  
  - URL para acesso: http://localhost:8080/h2-console
  
  - Use as seguintes credenciais para conectar:
  
    JDBC URL: jdbc:h2:mem:testdb
    User Name: sa
    Password: (deixe em branco)


#Endpoints da API
  - Login:
    POST /auth/login
    Realiza login do usuário e retorna token JWT.
    Body: { "username": "string", "password": "string" }
  
  - Registro:
    POST /auth/register
    Cria novo usuário.
    Body: { "username": "string", "password": "string" }

  - Transferências:
    POST /transfers
    Agenda uma nova transferência financeira.
    Headers: Authorization: Bearer <token>
    Body:
    {
      "accountOrigin": "string",
      "accountDestination": "string",
      "transferDate": "yyyy-MM-dd",
      "scheduledDate": "yyyy-MM-dd",
      "amount": "decimal"
    }

