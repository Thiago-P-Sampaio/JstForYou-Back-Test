# ![logo](https://github.com/Projeto-JustForYou/Assets/blob/main/jfylogo.png?raw=true) JustForYou


### *Sistema inteligente de recomendações de Séries e Filmes*

---
## Navegação:
1. [Sobre](#sobre)
2. [Contextualização](#contextualização)
3. [Aplicação](#aplicação)
4. [Funcionalidades](#funcionalidades)
5. [Tecnologias e Dependências](#tecnologiasdependências-utilizadas)
6. [Ferramentas utilizadas](#ferramentas-utilizadas)
7. [Incialização do projeto](#incialização-do-projeto)
8. [Deploy](#deploy)
9. [Licenças e utilização](#licenças-e-utilização)
---


## Sobre:
A "JustForYou" é uma aplicação web/mobile com o foco de promover ao usuário recomendações quanto a filmes e séries com base em seus gostos únicos. Dispondo de um acervo vasto de obras cinematográficas e utilizando de Inteligência Artificial (IA), a "JustForYou" proporciona aos seus usuários a liberdade

## Contextualização:
Para a finalização do curso: *Tecnólogo em Análise e Desenvolvimento de Sistemas* e a respectiva obtenção do diploma, é solicitado aos alunos, enquanto se encaminham para o encerramento dos últimos semestres, o desenvolvimento do Trabalho de Término de Curso (TCC) que irá agregar todos os conhecimentos difundidos durante o curso, bem como exigirá dos grupos competências reais estimadas em verdadeiros ambientes de produção.

O grupo **"The Visionarys"**, composto por: [Thiago](https://github.com/Thiago-P-Sampaio), [Nícollas](https://github.com/Nicoo200), [Ricardo](https://github.com/RicardoOngari), [Kauã](https://github.com/DevMarquesx) e [Lívia](https://github.com/licavalentim), deverá apresentar ao final do ano letivo, a título de encerramento, um TCC coerente com as demandas do curso vigente.

### Surgimento da Ideia e o problema identificado

A iniciativa "JustForYou" surgiu da "dor" identificada, compartilhada pelo grupo e pelas pessoas:
- Dificuldade de escolher algum conteúdo para assistir;
- Tempo gasto na escolha de alguma série/filme;
- Incerteza e insatisfação perante as escolhas próprias;
- Mecanização e algoritmos nada versáteis nas recomendações para usuários.

### Objetivo:
O projeto "JustForYou" busca atender às necessidades de um público-alvo específico: voltado a consumidores de conteúdos audiovisuais, incluindo filmes, séries e produções de plataformas de streaming. Assim, a plataforma encarrega-se de propor ao usuário recomendações ***mais precisas***.

---

## Aplicação

> Repositório do BACK-END

### Descrição:
Este repositório foi desenvolvido em **Java Spring** com **JWT** e representa a camada que faz o intermédio das comunicações entre o usuário(Front-END) e o banco de dados. Responsável pela lógica de autenticação e pela persistência dos dados, essa interface fornece a capacidade de gerenciar usuários, preferências, listas, conteúdos(filmes/séries) e avatares.

### Funcionalidades:

#### Autenticação e Autorização:
- Cadastro;
- Login;
- Gestão e validação dos tokens
- Permanência de sessão do usuário(2 horas);
- Proteção das rotas;
- Recuperação de senha com envio de e-mails;



#### Gerenciamento dos usuários:
- CRUD dos usuários;
- Atualização dos dados pessoais;
- Redefinição de senha;
- Gestão de preferências e lista do usuário.

#### Preferências, conteúdos e Avatares:
- Gerenciamento de preferências por usuário;
- Gerenciamento dos conteúdos;
- Gerenciamento de avatares.

####  Tratamento de Erros:
- Lançamento de exceções personalizadas;
- Lançamento de respostas HTTP detalhadas.

### Validações:
- `DTOS`(Data Transfer Objetcs) únicos para cada operação;
- Validações de inputs em `DTOs`.

#### Acesso e Persistência dos dados(SQL)
- JPA/Hibernate;
- Validações;
- Repositórios, entidades e mapeamento.


### Estrutura de Pastas
```text
jstforyou-back-test
├── src
│   ├── main
│   │   ├── java/mediaapp/com/just4you
│   │   │   ├── Configurations         # Configurações Globais
│   │   │   │   └── Security           # Segurança (JWT, Filtros, WebSecurity)
│   │   │   │
│   │   │   ├── Controllers            # Camada de Controle (Endpoints REST)
│   │   │   │   ├── Access             # Controle de autenticação (Login/Registro)
│   │   │   │   └── ... (Avatar, Conteudo, Usuario, Preferencias)
│   │   │   │
│   │   │   ├── DTOs                   # Data Transfer Objects (Objetos de Transferência)
│   │   │   │   ├── Create             # DTOs para criação de registros
│   │   │   │   ├── Exception          # DTOs para respostas de erro
│   │   │   │   ├── Put                # DTOs para atualização de dados
│   │   │   │   ├── Response           # DTOs para resposta ao cliente
│   │   │   │   ├── Security           # DTOs de autenticação
│   │   │   │   └── UserAcess          # DTOs de acesso e recuperação de senha
│   │   │   │
│   │   │   ├── Entities               # Entidades JPA (Mapeamento Banco de Dados)
│   │   │   │
│   │   │   ├── Exceptions             # Tratamento de Exceções Customizadas
│   │   │   │   └── ManipuladorGlobalExcecoes.java
│   │   │   │
│   │   │   ├── Repositories           # Interfaces de acesso ao banco (Spring Data JPA)
│   │   │   │
│   │   │   ├── Roles                  # Enums de permissão (User/Admin)
│   │   │   │
│   │   │   ├── Services               # Regras de Negócio
│   │   │   │   ├── SecurityServices   # Serviços de Autenticação e Token JWT
│   │   │   │   └── domain             # Serviços de Domínio (Conteúdo, Usuário, Email, etc.)
│   │   │   │
│   │   │   ├── Validators             # Validações customizadas (Annotations)
│   │   │   │   ├── annotations
│   │   │   │   └── validator
│   │   │   │
│   │   │   └── Just4youApplication.java # Classe Principal (Main)
│   │   │
│   │   └── resources
│   │       ├── templates              # Templates de E-mail (HTML)
│   │       ├── application.properties # Configurações gerais
│   │       └── ... (perfis dev/local)
│   │
│   └── test                           # Testes Unitários e de Integração
│
├── .mvn/wrapper                       # Maven Wrapper
├── mvnw / mvnw.cmd                    # Scripts de execução Maven
├── pom.xml                            # Dependências e Build (Maven)
├── routes.md                          # Documentação das Rotas da API
└── .gitignore                         # Arquivos ignorados pelo Git
```

### Tecnologias/Dependências utilizadas:
- Java`(21)`;
- Spring Boot`(3.4.3)`;
- Auth0 Java JWT `4.5.0`
- SpringDoc OpenAPI (Swagger UI) `2.8.13`
- Spring Data JPA
- Spring Validation
- Spring Web
- Spring Security
- Spring Mail
- MySQL Connector J
- Spring Boot DevTools
- Spring Boot Starter Test
- Spring Security Test

### Ferramentas utilizadas:
- InteliJ IDE;
- MySQL Workbench 8.0 CE;
- Postman/Insomnia;
- Git e GitHub;
- WinSCP;


### Incialização do projeto
- Clonagem do repositório(se por cmd, necessário [git](https://git-scm.com/install/windows)):
```bash
    git clone https://github.com/Thiago-P-Sampaio/JstForYou-Back-Test.git
```

- Entrar no repositório:
```bash
  cd JstForYou-Back-Test
```

#### Configurações em `properties`:
- Definição de perfis(execução) em `properties`: `${APP_PROFILE:perfil}`
- Definição de TOKEN de segurança em `properties`:
 ```java
api.secret.token.secret=
```
- Definição de ADM primário em `properties`:
```java
api.secret.admin.email=
api.secret.admin.password=
```
- Definição das credenciais para serviço de emails no `properties`:
```java
spring.mail.host=
spring.mail.port=
spring.mail.username=
spring.mail.password=
spring.mail.properties.mail.smtp.auth=
spring.mail.properties.mail.smtp.starttls.enable=
```

- Definição de credenciais para conexão com banco de dados:

```java
spring.datasource.url=jdbc:mysql://localhost:3306/  
spring.datasource.username=
spring.datasource.password=
```
- Defina um perfil de inicialização, criando um `application-{perfil}.properties`;
- Coloque as credênciais para conexão com banco de dados;
- (Opcional) Recursos de desenvolvimento:
```java
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

spring.jpa.open-in-view=false
```

- A maioria das rotas se faz necessário a autênticação mínima de `USUARIO`. Abstém-se dessa regras
  as rotas que envolvem: `login` e `cadastro`.
- Portanto, é necessário preencher em cada requisição o token gerado na autenticação(login)

Exemplo de consumo FRONT-END:
```js
fetch('https://api.exemplo.com/recurso-protegido', {
  method: 'POST',
  
  // 2. O CABEÇALHO com o Token
  headers: {
    'Content-Type': 'application/json', // Informa que o corpo é JSON
    'Authorization': `Bearer ${seuToken}`
  },

  body: JSON.stringify(dadosParaEnviar) 
})
```

Exemplo de definições:
```java
### application.properties

spring.application.name=just4you
spring.profiles.active=${APP_PROFILE:dvlocal}
spring.jpa.open-in-view=false
api.secret.token.secret=${JWT_SECRET:tokenultrasecreto!}
api.secret.admin.email=jfy@gmail.com
api.secret.admin.password=SENHAFORTE!


spring.mail.host=smtp.gmail.com
spring.mail.port=
spring.mail.username=apenas.demonstracao@example.com.org.br
spring.mail.password= sua senha de aplicacoes google
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```
```java
### application-dvlocal.properties


spring.datasource.url=jdbc:mysql://localhost:3306/db_jfy
spring.datasource.username=root
spring.datasource.password=root

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

spring.jpa.open-in-view=false
```

---
### Rotas do sistema
Para mais detalhes sobre rotas, acesse o arquivo `ROUTES.MD` disponível no repositório.

| Path | Entidade | Sobre |
| :--- | :--- | :--- |
| `/auth/register` | Usuário (Autenticação) | Realiza o cadastro de um novo usuário no sistema. |
| `/auth/login` | Usuário (Autenticação) | Autentica o usuário e retorna um token JWT e dados do perfil. |
| `/auth/forgot-password` | Usuário (Autenticação) | Envia um e-mail com link para recuperação de senha caso o usuário tenha esquecido. |
| `/auth/reset-password` | Usuário (Autenticação) | Efetiva a redefinição da senha utilizando o token recebido por e-mail. |
| `/api/jfy/avatar/add` | Avatar | Adiciona uma nova URL de avatar ao banco de dados (Acesso Admin). |
| `/api/jfy/avatar/edit/{id}` | Avatar | Edita a URL ou descrição de um avatar existente (Acesso Admin). |
| `/api/jfy/avatar/dell/{id}` | Avatar | Remove um avatar do sistema pelo ID (Acesso Admin). |
| `/api/jfy/avatar/get/{id}` | Avatar | Busca os detalhes de um avatar específico pelo seu ID. |
| `/api/jfy/avatar/get/all` | Avatar | Retorna uma lista com todos os avatares cadastrados. |
| `/api/jfy/avatar/get` | Avatar | Retorna uma lista paginada de avatares. |
| `/api/jfy/content` | Conteúdo | Cadastra um novo conteúdo (Filme ou Série) no sistema. |
| `/api/jfy/content/dell/{id}` | Conteúdo | Remove um conteúdo da base de dados (Acesso Admin). |
| `/api/jfy/content/all` | Conteúdo | Lista todos os conteúdos disponíveis de forma paginada. |
| `/api/jfy/content/get/{id}` | Conteúdo | Busca um conteúdo específico pelo seu ID interno do banco. |
| `/api/jfy/content/media` | Conteúdo | Busca conteúdo utilizando o ID externo (da API de filmes) e o tipo de mídia. |
| `/api/jfy/content/edit/{id}` | Conteúdo | Atualiza informações de um conteúdo existente (Acesso Admin). |
| `/api/jfy/listcontent/usuario/{usuarioId}/conteudos` | Lista do Usuário | Adiciona (POST) ou Remove (DELETE) um conteúdo da lista pessoal do usuário. |
| `/api/jfy/listcontent/usuario/{usuarioId}` | Lista do Usuário | Busca a lista completa de conteúdos salvos por um usuário específico. |
| `/api/jfy/preferences/usuario` | Preferência | Cria uma nova preferência e a associa ao usuário. |
| `/api/jfy/preferences/{preferenciaId}` | Preferência | Deleta uma preferência específica. |
| `/api/jfy/preferences/usuario/{usuarioId}` | Preferência | Lista todas as preferências associadas a um determinado usuário. |
| `/api/jfy/preferences/all` | Preferência | Lista todas as preferências do sistema de forma paginada. |
| `/api/jfy/preferences/edit/{id}/{usuarioId}` | Preferência | Edita a descrição de uma preferência pertencente a um usuário. |
| `/api/jfy/user/{id}` | Usuário | Busca os dados de um usuário (GET) ou deleta a conta (DELETE - Admin). |
| `/api/jfy/user/all` | Usuário | Lista todos os usuários do sistema de forma paginada (Acesso Admin). |
| `/api/jfy/user/edit/{id}` | Usuário | Atualiza dados cadastrais do usuário, como nome, email ou avatar. |
| `/api/jfy/user/change-password/{id}` | Usuário | Permite a alteração da senha atual por uma nova, mediante confirmação. |

---

### Deploy:
A API foi hospedada no serviço da `Azure for Students` em `Serviços de Aplicativos`(`Aplicativo Web`). Foi gerado um pacote `.jar` em `target` e transferido para o ambiente via `FTP` por meio do software `WinSCP`.
O serviço está disponível em: https://justforyoutcc.azurewebsites.net


## Licenças e utilização:

Este projeto está licenciado sob a licença MIT. Consulte o arquivo `LICENSE` para mais informações.

---

[Volte para o incio](#logo-justforyou)
