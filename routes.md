# Mapeando rotas

 - [x] Controle das rotas da aplicação API RESTFUL 
em JAVA SPRING BOOT: **JustForYou**(17/10)

---
### Definições:
 - Aplicação em situação de localhost: `http://localhost:8080/`
 - Definição de perfis(execução) em `properties`: `${APP_PROFILE:perfil}`

 - Definição de TOKEN de segurança em `properties`:
```java
api.secret.token.secret=${JWT_SECRET:s3cr3t-K3!}
```
- Definição de ADM primário em `properties`:
```java
api.secret.admin.email=
api.secret.admin.password=
```

- Definição das credênciais para serviço de emails no `properties`:
```java
spring.mail.host=
spring.mail.port=
spring.mail.username=
spring.mail.password=
spring.mail.properties.mail.smtp.auth=
spring.mail.properties.mail.smtp.starttls.enable=
```

- Definição de credências com banco de dados:

```java
spring.datasource.url=jdbc:mysql://localhost:3306/
spring.datasource.username=
spring.datasource.password=
```


### Inicialização da aplicação:

- Defina um perfil de inicialização, criando um `application-{perfil}.properties`;
- Coloque as credênciais para conexão com banco de dados;
- (Opcional) Recursos de desenvolvimento:
```java
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

spring.jpa.open-in-view=false
```

---

# Controller: `AutenticacaoController`:

Responsável por lidar com as requisições que permitirão:
- Cadastrar-se;
- Logar-se. <br>
``Controlando o acesso ao sistema!``

### `POST` Registrar-se: http://localhost:8080/auth/register
recebe um JSON com os parâmetros:

```json
{
"nome": "João da Silva",
"email": "joao.silva@example.com",
"senha": "umaSenhaForte123@",
"dataNascimento": "1990-08-25"
}
```

- [x] Resposta comum: 201 CREATED;
- [ ] Resposta incomum: 409 CONFLICT;
- [ ] Resposta de erros: 400 BAD_REQUEST;

Erro típico:
```JSON
{
    "timestamp": "2025-10-17T20:22:23.821278Z",
    "status": 400,
    "error": "Erro de validação",
    "message": {
        "senha": "A senha do usuário precisa ser informado!",
        "nome": "O nome do usuário precisa ser informado!",
        "dataNascimento": "É necessário informar a data de nascimento do usuário!",
        "email": "O email do usuário precisa ser informado!"
    },
    "path": "/auth/register"
}

```

Erro típico: 

```JSON
{
    "timestamp": "2025-10-17T20:23:12.117495600Z",
    "status": 400,
    "error": "Requisição inconsistente!",
    "message": "O corpo da requisição é invalido ou mal formado. Verifique os tipos de dados dos campos!",
    "path": "/auth/register"
}
```


---

### `POST` Logar-se: http://localhost:8080/auth/login
Recebe os parâmetros JSON para:
```JSON
{
  "email": "joao.silva@example.com",
  "senha": "umaSenhaForte123@"
}
```

Recebe uma resposta JSON:
```JSON
{
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJqc3Q0eW91Iiwic3ViIjoiam9hby5zaWx2YUBleGFtcGxlLmNvbSIsImV4cCI6MTc2MDczNjI4Nn0.wzV83tFc7PdGOIGbOLdi-S-e8uXgg1Kipi5sNd5tsYM",
    "nome": "João da Silva",
    "id": 7,
    "avatarUrl": null
}
```

- [x] Resposta comum: 200 OK;
- [ ] Resposta de erros: 400 BAD_REQUEST;
- [ ] Resposta de erros: 403 FORBIDDEN;

Erro típico:
```JSON
{
    "timestamp": "2025-10-17T20:26:39.458972100Z",
    "status": 400,
    "error": "Requisição inconsistente!",
    "message": "O corpo da requisição é invalido ou mal formado. Verifique os tipos de dados dos campos!",
    "path": "/auth/login"
}
```

---

# Controller: `AvatarController`:
Responsável por definir os avatares a serem associados a algum perfil, que permitirão

- Adicionar;
- Editar;
- Deletar;
- Buscar por ID;
- Buscar tudo;
- Buscar por páginas:

### `POST` Adicionar: http://localhost:8080/api/jfy/avatar `(ADMIN)`
Recebe como parâmetros para um JSON:
```JSON
{
"url": "https://cdn.example.com/avatars/avatar7.png",
"descricao": "Descrição descritiva"
}
```

Resposta comum:
```JSON
{
    "id": 2,
    "url": "https://cdn.example.com/avatars/avatar7.png",
    "descricao": "Descrição descritiva"
}
```

- [x] Resposta comum: 201 CREATED;
- [ ] Resposta de erros: 400 BAD_REQUEST;
- [ ] Resposta de erros: 403 FORBIDDEN;
- [ ] Resposta de erros: 409 CONFLICT;

Erro típico:
```JSON
{
    "timestamp": "2025-10-17T20:33:22.819055100Z",
    "status": 409,
    "error": "Conteúdo existente",
    "message": "Já existe um avatar com a URL informada!",
    "path": "/api/jfy/avatar/add"
}
```

Erro típico:
```JSON
{
    "timestamp": "2025-10-17T20:34:22.057934Z",
    "status": 400,
    "error": "Erro de validação",
    "message": {
        "url": "A URL não pode estar em branco!",
        "descricao": "A descrição não pode estar em branco!"
    },
    "path": "/api/jfy/avatar/add"
}
```

### `PUT` Editar: http://localhost:8080/api/jfy/avatar/edit/{id} `ADMIN`
Recebe como parâmetros um ID do tipo `LONG`, e um corpo JSON:
```JSON
{
  "url": "https://placehold.co/600x5502",
  "descricao": "descrição"
}
```

Resposta comum:
```JSON
{
    "id": 2,
    "url": "https://placehold.co/600x5502",
    "descricao": "descrição"
}
```
- [x] Resposta comum: 200 OK;
- [ ] Resposta de erros: 400 BAD_REQUEST;
- [ ] Resposta de erros: 403 FORBIDDEN;

### `DELETE` Remover:  http://localhost:8080/api/jfy/avatar/dell/{id}  `ADMIN`
Recebe somente o ID na no `PATH`

- [x] Resposta comum: 204 OK NOT_CONTENT;
- [ ] Resposta de erros: 404 NOT_FOUND;
- [ ] Resposta de erros: 400 BAD_REQUEST;
- [ ] Resposta de erros: 403 FORBIDDEN;


Erro típico:
```JSON
{
  "timestamp": "2025-10-17T20:42:36.694440300Z",
  "status": 404,
  "error": "Recurso não encontrado!",
  "message": "Avatar com ID: 1 Não encontrado",
  "path": "/api/jfy/avatar/dell/1"
}
```

### `GET` Buscar:  http://localhost:8080/api/jfy/avatar/get 
 - Todos: `/all`;
 - Id: `/{id}`;
 - Página: `?page={quantidadePaginas}&size={tamanho}`

Respostas comuns:

```JSON
[
  {
    "id": 2,
    "url": "https://p",
    "descricao": "descrião"
  }
]
```

```JSON
{
  "id": 2,
  "url": "https://p",
  "descricao": "descrião"
}
```

```JSON
{
  "content": [
    {
      "id": 2,
      "url": "https://p",
      "descricao": "descrião"
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 2,
    "sort": {
      "empty": true,
      "sorted": false,
      "unsorted": true
    },
    "offset": 0,
    "unpaged": false,
    "paged": true
  },
  "last": true,
  "totalPages": 1,
  "totalElements": 1,
  "size": 2,
  "number": 0,
  "sort": {
    "empty": true,
    "sorted": false,
    "unsorted": true
  },
  "first": true,
  "numberOfElements": 1,
  "empty": false
}
```

Erros típicos:

```JSON
{
  "timestamp": "2025-10-17T20:46:15.024345400Z",
  "status": 404,
  "error": "Recurso não encontrado!",
  "message": "Avatar com ID: 1 não encontrado.",
  "path": "/api/jfy/avatar/get/1"
}
```


- [x] Resposta comum: 200 OK;
- [ ] Resposta de erros: 404 NOT_FOUND;
- [ ] Resposta de erros: 400 BAD_REQUEST;
- [ ] Resposta de erros: 403 FORBIDDEN;

---
# Controller: `ConteudoController`:
Viabiliza a inserção e controle dos conteúdos, seja SÉRIES ou FILMES. Suas
ações permitem:

- Adicionar;
- Deletar;
- Buscar;
- Editar.

### `POST` Adicionar:  http://localhost:8080/api/jfy/content/
Recebe como parâmetros um corpo JSON:
{
"titulo": "O Gambito da Rainha",
"tipoMedia": "tv",
"mediaId": 1221212122
}

Sendo `tipoMedia` um `ENUM` que possui dois valores: `tv` e `movie`;


Resposta comum:
```JSON
{
  "conteudoId": 3,
  "titulo": "O Gambito da Rainha",
  "mediaTipo": "tv",
  "mediaId": 1221212122
}
```

- [x] Resposta comum: 201 CREATED;
- [ ] Resposta de erros: 409 CONFLICT;
- [ ] Resposta de erros: 400 BAD_REQUEST;

Erros típicos:
```JSON
{
  "timestamp": "2025-10-17T20:56:49.245133600Z",
  "status": 409,
  "error": "Conteúdo existente",
  "message": "Conteúdo com ID(midia): 1221212122 e mídia: tv já existe!",
  "path": "/api/jfy/content"
}
```
```JSON
{
  "timestamp": "2025-10-17T20:57:34.334163200Z",
  "status": 400,
  "error": "Requisição inconsistente!",
  "message": "O corpo da requisição é invalido ou mal formado. Verifique os tipos de dados dos campos!",
  "path": "/api/jfy/content"
}
```
```JSON
{
  "timestamp": "2025-10-17T20:57:57.576647500Z",
  "status": 400,
  "error": "Erro de validação",
  "message": {
    "mediaId": "O ID da mídia deve ser informado!",
    "tipoMedia": "O tipo da mídia( movie / tv ) deve ser informado!"
  },
  "path": "/api/jfy/content"
}
```

### `GET` Buscar:  http://localhost:8080/api/jfy/content/...
- Todos: `/all`;
- Todos(paginação): `/all?page={pagina}&size={quantidade}`;
- Por id(próprio): `/{id}`
- Por parâmetros de url: `/media?mediaId={idServico}&tipoMedia={tv ou movie}`

Respostas comuns:
```JSON
{
    "content": [
        {
            "conteudoId": 1,
            "titulo": "Breaking Bad1",
            "mediaTipo": "tv",
            "mediaId": 204
        },
        {
            "conteudoId": 2,
            "titulo": "eee",
            "mediaTipo": "tv",
            "mediaId": 123
        }
    ],
    "pageable": {
        "pageNumber": 0,
        "pageSize": 2,
        "sort": {
            "empty": true,
            "sorted": false,
            "unsorted": true
        },
        "offset": 0,
        "paged": true,
        "unpaged": false
    },
    "last": false,
    "totalPages": 2,
    "totalElements": 4,
    "size": 2,
    "number": 0,
    "sort": {
        "empty": true,
        "sorted": false,
        "unsorted": true
    },
    "first": true,
    "numberOfElements": 2,
    "empty": false
}
```

```JSON
{
    "conteudoId": 4,
    "titulo": "Velozes e Furiosos",
    "mediaTipo": "movie",
    "mediaId": 345
}
```

- [x] Resposta comum: 200 OK;
- [ ] Resposta de erros: 404 NOT_FOUND;

Erros típicos:

```JSON
{
    "timestamp": "2025-10-19T22:33:02.059651800Z",
    "status": 404,
    "error": "Recurso não encontrado!",
    "message": "Conteúdo com ID: 10 não encontrado!",
    "path": "/api/jfy/content/get/10"
}
```

```JSON
{
    "timestamp": "2025-10-19T22:33:17.326641400Z",
    "status": 404,
    "error": "Recurso não encontrado!",
    "message": "Conteudo(TV) e ID: 345 não encontrado!",
    "path": "/api/jfy/content/media"
}
```

### `PUT` Editar:  http://localhost:8080/api/jfy/content/edit/{id} `ADMIN`
Recebe um corpo JSON e uma parâmetro via `PATH` para o `id`(próprio) correspondente:
```JSON
  "titulo": "",
  "mediaTipo": "",
  "mediaId": 
```
Sendo `mediaTipo`: `tv` ou `movie`.

Resposta comum:
```JSON
{
    "conteudoId": 1,
    "titulo": "movie",
    "mediaTipo": "tv",
    "mediaId": 2121122121
}
```

- [x] Resposta comum: 200 OK;
- [ ] Resposta de erros: 500 INTERNAL_ERROR(investigar);
- [ ] Resposta de erros: 400 BAD_REQUEST;
- [ ] Resposta de erros: 403 FORBBIDEN;


```JSON
{
    "timestamp": "2025-10-19T22:57:37.160590500Z",
    "status": 400,
    "error": "Requisição inconsistente!",
    "message": "O corpo da requisição é invalido ou mal formado. Verifique os tipos de dados dos campos!",
    "path": "/api/jfy/content/edit/1"
}
```

```JSON
{
    "timestamp": "2025-10-19T22:58:56.844653600Z",
    "status": 400,
    "error": "Erro de validação",
    "message": {
        "mediaTipo": "Formato de texto inválido!"
    },
    "path": "/api/jfy/content/edit/1"
}
```
### `DELETE` Deletar:  http://localhost:8080/api/jfy/content/dell/{id} `ADMIN`
Recebe por parâmetros na url o ID(próprio) do conteúdo que deseja ser deletado.

- [x] Resposta comum: 204 NOT_CONTENT;
- [ ] Resposta de erros: 404 NOT_FOUND;
- [ ] Resposta de erros: 403 FORBBIDEN;

---
# Controller: `ListaConteudoController`:
Atua nas operações que envolvem a lista do usuário, tendo a capacidade de
adicionar e remover itens da lista.

- Adicionar;
- Deletar;
- Buscar.


### `POST` Adicionar:  http://localhost:8080/api/jfy/listcontent/usuario/{usuarioId}/conteudos
Recebe por parâmetro o `id` do usuário e um corpo JSON:
```JSON
{
  "titulo": "Inception",
  "tipoMedia": "movie",
  "mediaId": ,
  "avaliacao": true
}
```
Sendo `mediaId` o id do serviço(API Externa).

- [x] Resposta comum: 201 CREATED;
- [ ] Resposta de erros: 404 NOT_FOUND;
- [ ] Resposta de erros: 400 BAD_REQUEST;

 INVESTIGAR O MOTIVO PELO QUAL A EXCEÇÃO: `RecursoExistenteExcecao` não está sendo disparada!


Erros típicos
```JSON
{
    "timestamp": "2025-10-19T23:24:56.525240900Z",
    "status": 400,
    "error": "Requisição inconsistente!",
    "message": "O corpo da requisição é invalido ou mal formado. Verifique os tipos de dados dos campos!",
    "path": "/api/jfy/listcontent/usuario/3/conteudos"
}
```
```JSON
{
    "timestamp": "2025-10-19T23:25:16.088666300Z",
    "status": 404,
    "error": "Recurso não encontrado!",
    "message": "Usuário não encontrado com o ID: 10",
    "path": "/api/jfy/listcontent/usuario/10/conteudos"
} 
```
### `POST` Adicionar:  http://localhost:8080/api/jfy/listcontent/usuario/{usuarioId}/conteudos
Para a removação do conteúdo em lista é necessário fornecer o `id` do usuário junto com
um corpo JSON:

```JSON
{
  "titulo": "Inception",
  "tipoMedia": "movie",
  "mediaId": ,
  "avaliacao": true
}
```

- [x] Resposta comum: 204 NO_CONTENT;
- [ ] Resposta de erros: 404 NOT_FOUND;
- [ ] Resposta de erros: 400 BAD_REQUEST;
- [ ] Resposta de erros: 500 INTERNAL_ERROR(investigar);

```JSON
{
    "timestamp": "2025-10-19T23:30:52.915858600Z",
    "status": 400,
    "error": "Requisição inconsistente!",
    "message": "O corpo da requisição é invalido ou mal formado. Verifique os tipos de dados dos campos!",
    "path": "/api/jfy/listcontent/usuario/1/conteudos"
}
```

```JSON
{
    "timestamp": "2025-10-19T23:31:20.401512400Z",
    "status": 404,
    "error": "Recurso não encontrado!",
    "message": "O conteúdo não está presente na lista do usuário.",
    "path": "/api/jfy/listcontent/usuario/1/conteudos"
}
```

```JSON
{
  "timestamp": "2025-10-19T23:31:36.237939900Z",
  "status": 404,
  "error": "Recurso não encontrado!",
  "message": "Conteúdo não encontrado na base para o mediaId: 111 e tipo: SERIE",
  "path": "/api/jfy/listcontent/usuario/1/conteudos"
}
```






















