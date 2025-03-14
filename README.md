# Product Manager API

API para gerenciamento de produtos (CRUD completo), desenvolvida com Spring Boot seguindo o padrão MVC.

## Tecnologias Utilizadas
- Java 17
- Spring Boot 3.4.3
- Spring Data JPA
- Spring Security
- H2 Database
- PostgreSQL
- Docker
- Lombok
- Maven

## Funcionalidades
- Criar, listar, atualizar e excluir produtos
- Consultar produtos por nome e faixa de preço
- Segurança com autenticação básica (usuário: `user`, senha: `password`)
- Documentação com Swagger
- Logs detalhados para depuração

## Testes
 - Método: POST
 - 1. Criar Produto (POST)
 - URL: http://localhost:8080/products
 - Clique em Send.
   ```sh
      {
       "nome": "Torta de Limão",
       "preco": 120.50
      }

   ```
   ![image](https://github.com/user-attachments/assets/65a58713-54b0-4ec8-9c01-6ab4447bb467)
   
 - Método: GET 
 - 2. Listar Todos os Produtos (GET)
 - URL: http://localhost:8080/products
 -  Clique em Send
   ![image](https://github.com/user-attachments/assets/3276fab3-a381-473b-a041-a5b3f79d6b02)

- Método: GET
- 3. Buscar Produto por ID (GET)
- URL: http://localhost:8080/products/{id}

- Substitua {id} pelo ID de um produto existente, por exemplo, 1.
- Clique em Send
 ![image](https://github.com/user-attachments/assets/5487bdaf-f0eb-4b91-be44-892feae57dcd)

-Método: GET
4. Buscar Produtos por Nome (GET)
URL: http://localhost:8080/products/search?name=Limão

Clique em Send.

Espera-se: Código 200 OK e uma lista de produtos cujo nome contenha o termo "Limão".
![image](https://github.com/user-attachments/assets/4355e5ad-fff9-420b-9019-27bc71ba3703)

- Método: GET
- 5. Buscar Produtos por Faixa de Preço (GET)
- URL: http://localhost:8080/products/range?min=50&max=150
- Substitua os valores 50 (mínimo) e 150 (máximo) pela faixa de preço desejada.
- Clique em Send.

Espera-se: Código 200 OK e uma lista de produtos dentro da faixa de preço especificada.
![image](https://github.com/user-attachments/assets/0bb37743-2f8e-4dc9-942a-ef953f47093a)

- Método: PUT
- URL: http://localhost:8080/products/{id}

- Substitua {id} pelo ID de um produto existente.
- Body:

- Vá para a aba Body no Postman.

- Escolha raw e selecione JSON.
   
- Insira o seguinte conteúdo: 
  ```sh
      {
       "nome": "Torta Atualizada",
       "preco": 80.00
      }

   ``

![image](https://github.com/user-attachments/assets/8163a4ea-d6ff-4503-97c9-99f81bbf3060)

- Método: DELETE
- 7. Deletar Produto (DELETE)
- URL: http://localhost:8080/products/{id}

- Substitua {id} pelo ID de um produto existente
- Clique em Send.

- ![image](https://github.com/user-attachments/assets/0e91626e-b434-413e-be5e-8173846578b4)












. 

   



### Requisitos
- Java 17+
- Maven 3+
- Docker (opcional para containerização)

### Configuração do Banco de Dados
Por padrão, a aplicação utiliza o banco de dados em memória H2(testes). E posteriormente ultiliza  PostgreSQL no Docker.`.



## Execução da Aplicação

### Executar Localmente
```sh
mvn spring-boot:run
```

### Construir o JAR
```sh
mvn clean package
java -jar target/product-manager-0.0.1-SNAPSHOT.jar
```

### Executar com Docker
Criar a imagem e rodar o container:
```sh
docker build -t product-manager .
docker run -p 8080:8080 product-manager
```

## Endpoints Principais

| Método  | Endpoint             | Descrição                |
|---------|----------------------|--------------------------|
| GET     | `/products`          | Lista todos os produtos  |
| GET     | `/products/{id}`     | Busca um produto por ID  |
| POST    | `/products`          | Cria um novo produto     |
| PUT     | `/products/{id}`     | Atualiza um produto      |
| DELETE  | `/products/{id}`     | Remove um produto        |

## Testes
Executar testes com:
```sh
mvn test
```

## Repositório
(https://github.com/teofilonicolau/product_manager_api.git)
