# Aplicação ms-event

## Visão Geral
A aplicação `ms-event` é um microserviço baseado em Spring Boot projetado para gerenciar instituições e seus eventos associados. Esta aplicação fornece APIs RESTful para operações CRUD em instituições e inclui uma tarefa agendada para atualizar o status dos eventos periodicamente.

## Funcionalidades
- **Operações CRUD**
- **Atualização de Status de Eventos**: Tarefa agendada para atualizar o status dos eventos com base nas datas de início e término.

## Tecnologias
- Java 11
- Spring Boot
- Spring Data JPA
- Spring Validation
- Banco de Dados H2
- Lombok
- MapStruct
- Swagger

## Requisitos
- Java 11
- Maven

## Primeiros Passos

### Instalação
1. **Clone o repositório**:
    ```sh
    git clone https://github.com/seuusuario/ms-event.git
    cd ms-event
    ```

2. **Construa o projeto**:
    ```sh
    mvn clean install
    ```

3. **Execute a aplicação**:
    ```sh
    mvn spring-boot:run
    ```

### Acessando a API
A aplicação estará acessível em `http://localhost:8080`. Abaixo estão os endpoints disponíveis:

### Endpoints

#### Criar Instituição
- **URL**: `/api/v1/institutions`
- **Método**: `POST`
- **Corpo da Requisição**:
  ```json
  {
    "name": "Nome da Instituição",
    "description": "CONFEDERATION"
  }

#### Criar Evento
- **URL**: `/api/v1/events`
- **Método**: `POST`
- **Corpo da Requisição**:
  ```json
  {
    "name": "Nome do Evento",
    "description": "Descrição do evento",
    "startDate": "2023-06-01",
    "endDate": "2023-06-10",
    "idInstitution": 1  
  }  
