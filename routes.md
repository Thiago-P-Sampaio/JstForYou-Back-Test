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
---
# Sumário:
- [Autenticação Controller](#controller-autenticacaocontroller)
- [AvatarController](#controller-avatarcontroller)
- [ConteudoController](#controller-conteudocontroller)
- [ListaConteudoController](#controller-listaconteudocontroller)
- [PreferenciaController](#controller-preferenciacontroller)
- [UsuarioController](#controller-usuariocontroller)
- [Swagger](#swagger-)

---

---

# Controller: `AutenticacaoController`:

Responsável por lidar com as requisições que permitirão:
- Cadastrar-se;
- Logar-se;
- Redefinir senha.<br>
``Controlando o acesso ao sistema!``
---
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

Erros típicos:
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


```JSON
{
    "timestamp": "2025-10-17T20:23:12.117495600Z",
    "status": 400,
    "error": "Requisição inconsistente!",
    "message": "O corpo da requisição é invalido ou mal formado. Verifique os tipos de dados dos campos!",
    "path": "/auth/register"
}
```

```JSON
{
    "timestamp": "2025-10-22T22:38:42.419336600Z",
    "status": 409,
    "error": "Conteúdo existente",
    "message": "Não foi possível criar o usuário",
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
- [ ] Resposta de erros: 401 UNAUTHORIZED;

Erros típicos:
```JSON
{
    "timestamp": "2025-10-17T20:26:39.458972100Z",
    "status": 400,
    "error": "Requisição inconsistente!",
    "message": "O corpo da requisição é invalido ou mal formado. Verifique os tipos de dados dos campos!",
    "path": "/auth/login"
}
```
```JSON
{
    "timestamp": "2025-10-22T23:08:57.443063400Z",
    "status": 401,
    "error": "Credenciais inválidas",
    "message": "Usuário ou senha incorretos.",
    "path": "/auth/login"
}
```

---
### `POST` Solicitação para redefinir senha: http://localhost:8080/auth/forgot-password
Recebe como parâmetros um corpo JSON:
```JSON
{
  "email": ""
}

```

Resposta comum:

```JSON

Se um e-mail cadastrado for encontrado, um link de redefinição será enviado.
```

Encontra-se na caixa de e-mail do usuário, o link para página de redefinição
de senha com o respectivo `token` para a operação:

- [x] Resposta comum: 200 OK;
- [ ] Resposta de erros: 400 BAD_REQUEST;

Erros típicos:
```JSON
{
    "timestamp": "2025-10-20T13:13:49.264344800Z",
    "status": 400,
    "error": "Erro de validação",
    "message": {
        "email": "deve ser um endereço de e-mail bem formado"
    },
    "path": "/auth/forgot-password"
}

```
```JSON
{
    "timestamp": "2025-10-20T13:14:36.976837200Z",
    "status": 400,
    "error": "Requisição inconsistente!",
    "message": "O corpo da requisição é invalido ou mal formado. Verifique os tipos de dados dos campos!",
    "path": "/auth/forgot-password"


```
---
### `POST` Redefinir senha: http://localhost:8080/auth/reset-password
Recebe como parâmetros um corpo JSON:
```JSON
{
  "senha": "",
  "confirmarSenha": "",
  "token": ""
}

```

Resposta comum
```JSON
Senha alterada com sucesso.
```

- [x] Resposta comum: 201 CREATED;
- [ ] Resposta de erros: 400 BAD_REQUEST;


```JSON
Token inválido ou expirado!
```
```JSON
As senhas não coincidem!
```

```JSON
{
    "timestamp": "2025-10-22T23:05:44.219196100Z",
    "status": 400,
    "error": "Erro de validação",
    "message": {
        "confirmarSenha": "must not be blank"
    },
    "path": "/auth/reset-password"
}
```

```JSON
{
    "timestamp": "2025-10-22T23:06:15.566312700Z",
    "status": 400,
    "error": "Erro de validação",
    "message": {
        "senha": "A senha deve ter no mínimo 6 caracteres!",
        "confirmarSenha": "A senha deve ter no mínimo 6 caracteres!"
    },
    "path": "/auth/reset-password"
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
---
### `POST` Adicionar: http://localhost:8080/api/jfy/avatar/add `(ADMIN)`
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

Erros típicos:
```JSON
{
    "timestamp": "2025-10-17T20:33:22.819055100Z",
    "status": 409,
    "error": "Conteúdo existente",
    "message": "Já existe um avatar com a URL informada!",
    "path": "/api/jfy/avatar/add"
}
```

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
```JSON
{
    "timestamp": "2025-10-22T23:12:46.521070800Z",
    "status": 403,
    "error": "Acesso Negado!",
    "message": "Você não tem permissão para acessar este recurso!",
    "path": "/api/jfy/avatar/add"
}
```

```JSON
{
    "timestamp": "2025-10-25T22:51:09.169131Z",
    "status": 400,
    "error": "Erro de validação",
    "message": {
        "url": "A URL informada não pertence ao domínio aceito ou não está situada no protocolo HTTPS"
    },
    "path": "/api/jfy/avatar/add"
}
```

---
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

```JSON
{
    "timestamp": "2025-10-22T23:21:04.130042300Z",
    "status": 400,
    "error": "Erro de validação",
    "message": {
        "url": "must be a valid URL",
        "descricao": "Formato de texto inválido!"
    },
    "path": "/api/jfy/avatar/edit/3"
}
```
```JSON
{
    "timestamp": "2025-10-22T23:19:18.166985700Z",
    "status": 403,
    "error": "Acesso Negado!",
    "message": "Você não tem permissão para acessar este recurso!",
    "path": "/api/jfy/avatar/edit/3"
}
```

```JSON
{
    "timestamp": "2025-10-25T22:51:09.169131Z",
    "status": 400,
    "error": "Erro de validação",
    "message": {
        "url": "A URL informada não pertence ao domínio aceito ou não está situada no protocolo HTTPS"
    },
    "path": "/api/jfy/avatar/edit/4"
}
```

---
### `DELETE` Remover:  http://localhost:8080/api/jfy/avatar/dell/{id}  `ADMIN`
Recebe somente o ID  no `PATH`

- [x] Resposta comum: 204 OK NOT_CONTENT;
- [ ] Resposta de erros: 404 NOT_FOUND;
- [ ] Resposta de erros: 500 INTERNAL_ERROR;
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
```JSON
{
    "timestamp": "2025-10-22T23:22:04.520753Z",
    "status": 403,
    "error": "Acesso Negado!",
    "message": "Você não tem permissão para acessar este recurso!",
    "path": "/api/jfy/avatar/dell/2"
}
```

```JSON
{
  "timestamp": "2025-10-25T22:54:23.045747100Z",
  "status": 400,
  "error": "Parâmetro inválido",
  "message": "Method parameter 'id': Failed to convert value of type 'java.lang.String' to required type 'java.lang.Long'; For input string: \"3e\"",
  "path": "/api/jfy/avatar/dell/3e"
}
```
---
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
---
### `POST` Adicionar:  http://localhost:8080/api/jfy/content
Recebe como parâmetros um corpo JSON:
```JSON
{
"titulo": "O Gambito da Rainha",
"tipoMedia": "tv",
"mediaId": 1221212122
}
```

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
- [ ] Resposta de erros: 403 FORBIDDEN;

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
```JSON
{
    "timestamp": "2025-10-22T23:43:22.755840400Z",
    "status": 400,
    "error": "Requisição Inválida",
    "message": "Tipo de mídia inválido: filme",
    "path": "/api/jfy/content"
}
```

---
### `GET` Buscar:  http://localhost:8080/api/jfy/content/...
- Todos: `/all`;
- Todos(paginação): `/all?page={pagina}&size={quantidade}`;
- Por id(próprio): `/get/{id}`
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
- [ ] Resposta de erros: 400 BAD_REQUEST;
- [ ] Resposta de erros: 403 FORBIDDEN;

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

```JSON
{
    "timestamp": "2025-10-22T23:51:24.045106900Z",
    "status": 400,
    "error": "Requisição Inválida",
    "message": "Tipo de mídia inválido: SERIE",
    "path": "/api/jfy/content/media"
}
```
---
### `PUT` Editar:  http://localhost:8080/api/jfy/content/edit/{id} `ADMIN`
Recebe um corpo JSON e uma parâmetro via `PATH` para o `id`(próprio) correspondente:
```JSON
{
  "titulo": "",
  "mediaTipo": "",
  "mediaId":
} 
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
---
### `DELETE` Deletar:  http://localhost:8080/api/jfy/content/dell/{id} `ADMIN`
Recebe por parâmetros na url o ID(próprio) do conteúdo que deseja ser deletado.

- [x] Resposta comum: 204 NOT_CONTENT;
- [ ] Resposta de erros: 404 NOT_FOUND;
- [ ] Resposta de erros: 403 FORBBIDEN;

Erros típicos: 
```JSON
{
    "timestamp": "2025-10-23T00:34:11.100644100Z",
    "status": 404,
    "error": "Recurso não encontrado!",
    "message": "Conteúdo com ID: 2 não encontrado!",
    "path": "/api/jfy/content/dell/2"
}
```

```JSON
{
    "timestamp": "2025-10-23T00:35:33.390326500Z",
    "status": 403,
    "error": "Acesso Negado!",
    "message": "Você não tem permissão para acessar este recurso!",
    "path": "/api/jfy/content/dell/2"
}
```

---
# Controller: `ListaConteudoController`:
Atua nas operações que envolvem a lista do usuário, tendo a capacidade de
adicionar e remover itens da lista.

- Adicionar;
- Deletar;
- Buscar.



---
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
Sendo `mediaId` o id do serviço(API Externa) e `tipoMedia`: `tv` ou `movie`

- [x] Resposta comum: 201 CREATED;
- [ ] Resposta de erros: 404 NOT_FOUND;
- [ ] Resposta de erros: 400 BAD_REQUEST;
- [ ] Resposta de erros: 409 CONFLICT;
- [ ] Resposta de erros: 403 FORBIDDEN;

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

```JSON
{
    "timestamp": "2025-10-23T20:45:30.062940800Z",
    "status": 409,
    "error": "Conteúdo existente",
    "message": " Já existe o conteúdo com ID: 11111 na lista com ID: 1",
    "path": "/api/jfy/listcontent/usuario/1/conteudos"
}
```

---
### `DELETE` REMOVER:  http://localhost:8080/api/jfy/listcontent/usuario/{usuarioId}/conteudos
Para a removação do conteúdo em lista é necessário fornecer o `id` do usuário junto com
um corpo JSON:

```JSON
{
  "titulo": "Inception",
  "tipoMedia": "movie",
  "mediaId": ,
}
```

- [x] Resposta comum: 204 NO_CONTENT;
- [ ] Resposta de erros: 404 NOT_FOUND;
- [ ] Resposta de erros: 400 BAD_REQUEST;
- [ ] Resposta de erros: 403 FORBIDDEN;

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

```JSON
{
    "timestamp": "2025-10-23T20:52:55.126112100Z",
    "status": 403,
    "error": "Acesso negado",
    "message": "Acesso negado! Você não tem permissão para realizar essa operação",
    "path": "/api/jfy/listcontent/usuario/7/conteudos"
}
```


---
### `GET` Buscar conteúdos da lista:  http://localhost:8080/api/jfy/listcontent/usuario/{usuarioId}
Recebe como parâmetro o `id` do usuário no  `PATH`:

Resposta comum:
```JSON

{
    "listaId": 2,
    "conteudos": [
        {
            "conteudoId": 5,
            "titulo": "Mistérios da Vila",
            "mediaTipo": "tv",
            "mediaId": 900404
        },
        {
            "conteudoId": 4,
            "titulo": "Horizonte Azul",
            "mediaTipo": "movie",
            "mediaId": 900303
        },
        {
            "conteudoId": 1,
            "titulo": "Inception",
            "mediaTipo": "movie",
            "mediaId": 106
        },
        {
            "conteudoId": 2,
            "titulo": "Nebula Rising",
            "mediaTipo": "movie",
            "mediaId": 900101
        },
        {
            "conteudoId": 3,
            "titulo": "Rua das Lembranças",
            "mediaTipo": "tv",
            "mediaId": 900202
        }
    ]
}
```

- [x] Resposta comum: 200 OK;
- [ ] Resposta de erros: 404 NOT_FOUND;
- [ ] Resposta de erros: 403 FORBIDDEN;


Erros típicos:
```JSON
{
  "timestamp": "2025-10-20T13:32:49.648333Z",
  "status": 404,
  "error": "Recurso não encontrado!",
  "message": "Usuário não encontrado com ID: 4",
  "path": "/api/jfy/listcontent/usuario/4"
}
```

```JSON
{
    "timestamp": "2025-10-23T22:39:10.027113900Z",
    "status": 403,
    "error": "Acesso negado",
    "message": "Acesso negado! Você não tem permissão para realizar essa operação",
    "path": "/api/jfy/listcontent/usuario/12"
}
```




---
# Controller: `PreferenciaController`:
Lida com as operações que rege as preferências de determinado usuário.
- Criar e relacionar preferência a usuário;
- Deletar;
- Buscar;
- Editar.


---
### `POST` Adicionar preferência:  http://localhost:8080/api/jfy/preferences/usuario
Recebe como parâmetros um corpo JSON com:
```JSON
{
  "usuarioId": 1,
  "descricao": "Violência fantasiosa"
}

```

Resposta comum:
```JSON
{
    "preferenciaId": 1,
    "descricao": "Violência fantasiosa"
}
```

- [x] Resposta comum: 201 CREATED;
- [ ] Resposta de erros: 404 NOT_FOUND;
- [ ] Resposta de erros: 400 BAD_REQUEST;
- [ ] Resposta de erros: 403 FORBIDDEN;


Erros típicos:
```JSON
{
    "timestamp": "2025-10-20T13:43:46.963723100Z",
    "status": 400,
    "error": "Requisição inconsistente!",
    "message": "O corpo da requisição é invalido ou mal formado. Verifique os tipos de dados dos campos!",
    "path": "/api/jfy/preferences/usuario"
}

```
```JSON
{
    "timestamp": "2025-10-20T13:44:32.040976Z",
    "status": 404,
    "error": "Recurso não encontrado!",
    "message": "Usuário com ID 10 não encontrado.",
    "path": "/api/jfy/preferences/usuario"
}
```
```JSON
 {
    "timestamp": "2025-10-20T13:47:33.195580900Z",
    "status": 400,
    "error": "Erro de validação",
    "message": {
    "descricao": "Formato de texto inválido!"
    },
    "path": "/api/jfy/preferences/usuario"
 }
```

```JSON
{
    "timestamp": "2025-10-23T22:55:35.946513400Z",
    "status": 403,
    "error": "Acesso negado",
    "message": "Acesso negado! Você não tem permissão para realizar essa operação",
    "path": "/api/jfy/preferences/usuario"
}
```


---
### `DELETAR` Remover preferência:  http://localhost:8080/api/jfy/preferences/{preferenciaId}
Recebe um `id` da preferência no `PATH`

- [x] Resposta comum: 204 NO_CONTENT;
- [ ] Resposta de erros: 404 NOT_FOUND;
- [ ] Resposta de erros: 403 FORBIDDEN;

Erros típicos:
```JSON
{
    "timestamp": "2025-10-20T13:50:43.694903200Z",
    "status": 404,
    "error": "Recurso não encontrado!",
    "message": "Preferencia com id: 1 não encontrado!",
    "path": "/api/jfy/preferences/1"
}
``` 
```JSON
{
    "timestamp": "2025-10-23T22:55:35.946513400Z",
    "status": 403,
    "error": "Acesso negado",
    "message": "Acesso negado! Você não tem permissão para realizar essa operação",
    "path": "/api/jfy/preferences/1"
}
```




### `GET` Buscar preferências:  http://localhost:8080/api/jfy/preferences/
- Preferências do usuário: `/usuario/{usuarioId}`;
- Todas: `/all` com parâmetros para paginação: `?page={}&size={}`


Respostas comuns:
```JSON
[
    {
        "preferenciaId": 2,
        "descricao": "Violência fantasiosa"
    },
    {
        "preferenciaId": 3,
        "descricao": "Violência fantasiosa"
    }
]
```

```JSON
{
  "content": [
    {
      "preferenciaId": 2,
      "descricao": "Violência fantasiosa"
    },
    {
      "preferenciaId": 3,
      "descricao": "Violência fantasiosa"
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 20,
    "sort": {
      "empty": true,
      "sorted": false,
      "unsorted": true
    },
    "offset": 0,
    "paged": true,
    "unpaged": false
  },
  "last": true,
  "totalElements": 2,
  "totalPages": 1,
  "size": 20,
  "number": 0,
  "sort": {
    "empty": true,
    "sorted": false,
    "unsorted": true
  },
  "numberOfElements": 2,
  "first": true,
  "empty": false
}
```

- [x] Resposta comum: 200 OK;
- [ ] Resposta de erros: 404 NOT_FOUND;
- [ ] Resposta de erros: 403 FORBIDDEN;

Erros típicos:
```JSON
{
    "timestamp": "2025-10-20T13:55:23.607183Z",
    "status": 404,
    "error": "Recurso não encontrado!",
    "message": "Usuário com ID 10 não encontrado.",
    "path": "/api/jfy/preferences/usuario/10"
}
```
```JSON
{
    "timestamp": "2025-10-24T00:16:17.107318Z",
    "status": 403,
    "error": "Acesso negado",
    "message": "Acesso negado! Você não tem permissão para realizar essa operação",
    "path": "/api/jfy/preferences/usuario/1"
}
```

---
### `PUT` Editar preferência:  http://localhost:8080/api/jfy/preferences/edit/{id}/{usuarioId}
Recebe por parâmetros o `id` da preferência e do usuário além de um corpo JSON:
```JSON
{
  "descricao": ""
}
 ```

Resposta comum:

```JSON
{
    "preferenciaId": 2,
    "descricao": "Desenho animado"
}
```

- [x] Resposta comum: 200 OK;
- [ ] Resposta de erros: 404 NOT_FOUND;
- [ ] Resposta de erros: 403 FORBIDDEN;

Erros típicos:

```JSON
{
    "timestamp": "2025-10-21T12:06:29.894153Z",
    "status": 403,
    "error": "Acesso negado",
    "message": "Preferência não pertence ao usuário",
    "path": "/api/jfy/preferences/edit/1/3"
}
```

```JSON
{
    "timestamp": "2025-10-24T00:34:33.808564400Z",
    "status": 400,
    "error": "Erro de validação",
    "message": {
        "descricao": "must not be blank"
    },
    "path": "/api/jfy/preferences/edit/6/2"
}
```




---
# Controller: `UsuarioController`:
Lida com operações na camada de usuário, nas quais consitem em:
- Buscar;
- Deletar;
- Remover.


---
### `GET` Buscar usuário:  http://localhost:8080/api/jfy/user
- Buscar usuário por `id`: `/{id}`;
- Buscar usuários por paginação: `/all`  com parâmetros: `?page={}&size={}`; 

Resposta comum:

```JSON
{
    "usuarioId": 1,
    "nome": "Admin",
    "email": "jfy@gmail.com",
    "preferencias": [],
    "listaUsuario": {
        "listaId": 1,
        "conteudos": []
    },
    "avatar": null
}
```
```JSON
{
    "content": [
        {
            "usuarioId": 1,
            "nome": "Admin",
            "email": "jfy@gmail.com",
            "preferencias": [],
            "listaUsuario": {
                "listaId": 1,
                "conteudos": []
            },
            "avatar": null
        }
    ],
    "pageable": {
        "pageNumber": 0,
        "pageSize": 1,
        "sort": {
            "sorted": false,
            "unsorted": true,
            "empty": true
        },
        "offset": 0,
        "paged": true,
        "unpaged": false
    },
    "last": false,
    "totalPages": 4,
    "totalElements": 4,
    "numberOfElements": 1,
    "first": true,
    "size": 1,
    "number": 0,
    "sort": {
        "sorted": false,
        "unsorted": true,
        "empty": true
    },
    "empty": false
}
```

- [x] Resposta comum: 200 OK;
- [ ] Resposta de erros: 404 NOT_FOUND;
- [ ] Resposta de erros: 403 FORBIDDEN;

Erros típicos:

```JSON
{
    "timestamp": "2025-10-20T17:15:56.078164700Z",
    "status": 404,
    "error": "Recurso não encontrado!",
    "message": "Usuário com ID: 6 não encontrado!",
    "path": "/api/jfy/user/6"
}
```

```JSON
{
    "timestamp": "2025-10-24T00:37:04.210047700Z",
    "status": 403,
    "error": "Acesso negado",
    "message": "Acesso negado! Você não tem permissão para realizar essa operação",
    "path": "/api/jfy/user/1"
}
```


---
### `DELETE` Remover um usuário:  http://localhost:8080/api/jfy/user/{id}  `ADMIN`
Recebe como um parâmetro o `id` do usuário!

- [x] Resposta comum: 204 NOT_CONTENT;
- [ ] Resposta de erros: 404 NOT_FOUND;
- [ ] Resposta de erros: 403 FORBIDDEN;
- [ ] Resposta de erros: 500 INTERNAL_ERROR;

Erros típicos:

```JSON
{
    "timestamp": "2025-10-21T11:56:17.337645100Z",
    "status": 404,
    "error": "Recurso não encontrado!",
    "message": "Usuário com ID: 1 não encontrado!",
    "path": "/api/jfy/user/1"
}
```

```JSON
{
    "timestamp": "2025-10-21T11:56:28.210740Z",
    "status": 500,
    "error": "Erro interno do Servidor",
    "message": "Ocorreu um erro inesperado",
    "path": "/api/jfy/user/eee"
}

```

```JSON
{
    "timestamp": "2025-10-25T21:50:32.056061900Z",
    "status": 403,
    "error": "Acesso Negado!",
    "message": "Você não tem permissão para acessar este recurso!",
    "path": "/api/jfy/user/3"
}
```


---
### `PUT` Editar usuário usuário:  http://localhost:8080/api/jfy/user/edit/{id}
Recebe como parâmetros o `id` do usuário no  `PATH` e um corpo JSON:

```JSON
{
  "nome": "",
  "email": "",
  "avatar_id": null
}
```

Resposta comum:

```JSON
{
    "usuarioId": 2,
    "nome": "Thiago",
    "email": "alice@gmail.com",
    "preferencias": [],
    "listaUsuario": {
        "listaId": 2,
        "conteudos": []
    },
    "avatar": null
}

```

- [x] Resposta comum: 200 OK;
- [ ] Resposta de erros: 404 NOT_FOUND;
- [ ] Resposta de erros: 403 FORBIDDEN;
- [ ] Resposta de erros: 500 INTERNAL_ERROR;
- [ ] Resposta de erros: 400 BAD_REQUEST;


Erros típicos
```JSON
{
  "timestamp": "2025-10-21T12:54:55.777881900Z",
  "status": 500,
  "error": "Erro interno do Servidor",
  "message": "Ocorreu um erro inesperado",
  "path": "/auth/user/edit/10"
}
```

```JSON
{
  "timestamp": "2025-10-21T13:02:28.908784700Z",
  "status": 400,
  "error": "Erro de validação",
  "message": {
    "email": "deve ser um endereço de e-mail bem formado"
  },
  "path": "/api/jfy/user/edit/2"
}
```

### `PUT` Alterar senha do usuário:  http://localhost:8080/api/jfy/user/change-password/{id}
Recebe como um parâmetro no `PATH` um ID e um corpo JSON:

```JSON
{
  "senhaAtual": "",
  "novaSenha": "",
  "confirmarSenha": ""
}
```

- [x] Resposta comum: 204 NO_CONTENT;
- [ ] Resposta de erros: 404 NOT_FOUND;
- [ ] Resposta de erros: 403 FORBIDDEN;
- [ ] Resposta de erros: 400 BAD_REQUEST;

```JSON
{
    "timestamp": "2025-10-29T21:50:52.841328800Z",
    "status": 400,
    "error": "Erro de validação",
    "message": {
        "novaSenha": "A senha deve conter no mínimo 6 caracteres!",
        "confirmarSenha": "A senha deve conter no mínimo 6 caracteres!",
        "senhaAtual": "O campo deve ser preenchido!"
    },
    "path": "/api/jfy/user/change-password/6"
}
```

```JSON
{
    "timestamp": "2025-10-29T21:51:29.539519600Z",
    "status": 400,
    "error": "Erro de validação",
    "message": "Senha incorreta!",
    "path": "/api/jfy/user/change-password/8"
}
```

```JSON

```

```JSON
{
    "timestamp": "2025-10-29T21:50:32.657102700Z",
    "status": 404,
    "error": "Recurso não encontrado!",
    "message": "Usuário com ID: 6 não encontrado!",
    "path": "/api/jfy/user/change-password/6"
}
```


---
# Swagger: 
### http://localhost:8080/swagger-ui/index.html `ADMIN`
As rotas também estão documentadas e presentes no `swagger`.




















