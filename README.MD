# Spring MVC Template 🚀

Template de projeto Spring Boot seguindo o padrão MVC (Model-View-Controller) com CRUD completo e integração com API externa.

![MVC Architecture](./template/MVC.png)

## 📋 Sobre

Projeto desenvolvido como parte do desafio do Bootcamp DIO, servindo como template reutilizável para futuros projetos Spring Boot. Implementa um CRUD de usuários com validação de CEP através da API ViaCEP.

## 🛠️ Tecnologias

- **Java 21**
- **Spring Boot 3.3.5**
- **Spring Data JPA**
- **H2 Database** (in-memory)
- **OpenFeign** (cliente HTTP)
- **Bean Validation**
- **Lombok**

## ⚡ Como Executar

```bash
# Clone o repositório
git clone https://github.com/seu-usuario/exemple-spring-mvc.git

# Entre no diretório
cd exemple-spring-mvc

# Execute o projeto
./mvnw spring-boot:run
```

**Aplicação rodará em:** http://localhost:8080

## 📡 Endpoints

| Método | Endpoint | Descrição |
|--------|----------|----------|
| POST | `/users` | Criar usuário |
| GET | `/users` | Listar usuários |
| PUT | `/users/{id}` | Atualizar usuário |
| DELETE | `/users/{id}` | Deletar usuário |

### Exemplo de Requisição

**POST** `/users`
```json
{
  "name": "João Victor",
  "email": "contato@joaovic.tech",
  "address": {
    "cep": "88780000"
  }
}
```

### Exemplo de Resposta
```json
{
  "idUser": "e483e037-49a2-4d5d-be9a-a0300868bc73",
  "name": "João Victor",
  "email": "contato@joaovic.tech",
  "address": {
    "cep": "88780-000",
    "logradouro": "",
    "complemento": "",
    "unidade": "",
    "bairro": "",
    "localidade": "Imbituba",
    "uf": "SC",
    "estado": "Santa Catarina",
    "regiao": "Sul",
    "ibge": "4207304",
    "gia": "",
    "ddd": "48",
    "siafi": "8143"
  }
}
```

## 📚 Documentação e Acesso ao Banco

- **Swagger-UI**: Acesse a documentação interativa da API em [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html).
- **Console H2**: Para acessar o banco de dados em memória, utilize [http://localhost:8080/h2-console](http://localhost:8080/h2-console) e configure o JDBC URL como `jdbc:h2:mem:testdb`.


Este projeto está aberto a contribuições! Sinta-se à vontade para abrir uma com dúvidas ou sugestões, ou faça um fork para propor melhorias por meio de pull requests.


*Desenvolvido por [joaovic-tech](https://github.com/joaovic-tech) - Projeto open source disponível para toda comunidade* 💙
