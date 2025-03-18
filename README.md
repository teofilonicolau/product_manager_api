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

## Testeando no Postman
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

## . Teste de Login no Endpoint /auth/login
 ### Este teste é para garantir que o login funcione e retorne um token JWT válido.

- Abra o Postman.

- Clique em "New" → "Request".

- Configure a requisição:

- Method: POST

- URL: http://localhost:8080/auth/login

- Headers:

- Key: Content-Type

- Value: application/json

- Body:

- Escolha raw e selecione JSON no dropdown.

- Insira o seguinte JSON:
  
  ```sh
      {
       "username": "admin",
       "password": "admin123"
      }

   ```
  - Clique no botão Send.

  - Resultado Esperado
  - Você deverá receber uma resposta com o token JWT, como:
      ```sh
       {
       
         "token": "seu_jwt_aqui"
       }

    ```
   ![image](https://github.com/user-attachments/assets/a1bbc345-8fa0-4759-8b1c-2b9b6dac73d9)

  ### Teste de Endpoint Protegido
 - Agora que temos  o token JWT, vamos usá-lo para acessar um endpoint protegido.

 - Configuração
 - Crie uma nova requisição no Postman.

 - Configure a requisição:

- Method: GET

- URL: Substitua por um endpoint protegido, como:
 
   ```sh
        http://localhost:8080/produtos
    ```
- Headers:

- Key: Authorization

- Value: Bearer [seu_jwt_aqui] (substitua [seu_jwt_aqui] pelo token recebido no passo anterior).
- Clique no botão Send.
- Resultado Esperado
- Você deverá receber os dados do endpoint protegido.   
 
   












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

## Testes Unitários
### **4. Executando os Testes**
1. Abra sua IDE (como IntelliJ).
2. Clique com o botão direito na classe `ProductServiceTest` e selecione **Run 'ProductServiceTest'**.
3. Verifique se todos os testes passam com sucesso.

Executar testes com:
```sh
mvn test
```
### Teste Unitário da classe ProductService
![image](https://github.com/user-attachments/assets/9319f07e-aa72-4a12-9b24-603021838378)
### **3. Explicação dos Testes**
1. **`testFindAll`:** Testa se o método `findAll()` retorna a lista correta de produtos.
2. **`testFindById_ExistingId`:** Verifica se um produto existente é retornado corretamente.
3. **`testFindById_NonExistingId`:** Garante que o método lança `ResourceNotFoundException` para IDs inexistentes.
4. **`testSave`:** Testa se um produto é salvo corretamente.
5. **`testDelete_ExistingId`:** Testa se a exclusão ocorre corretamente para um ID existente.
6. **`testDelete_NonExistingId`:** Garante que `EmptyResultDataAccessException` seja lançado ao tentar deletar um ID inexistente.
7. ✅ Este é um teste unitário, pois verifica o comportamento de ProductService isoladamente.
8.🔹 Usa Mockito para simular o ProductRepository, evitando acessar um banco real.
9.⚡ Garante que o serviço retorna os valores esperados e lança exceções corretamente.

### Teste Unitário da classe ProductControllerTest
![image](https://github.com/user-attachments/assets/038d64de-496f-4a86-abec-c6d1abc63d76)
### **Explicação dos Testes**

1. **Configuração e Mocking**
   - **`@MockBean`**: Cria um mock do `ProductService` para interceptar chamadas e fornecer respostas simuladas. Isso impede o acesso ao banco de dados real.
   - **`@WithMockUser`**: Cria um usuário fictício para autenticação. Essencial porque a aplicação está protegida pelo Spring Security.

2. **`testGetAll()`**
   - Simula o método `findAll()` do `ProductService`, retornando uma lista fictícia de produtos.
   - Envia uma requisição `GET /products`.
   - Verifica:
     - O status da resposta é `200 OK`.
     - O tamanho da lista retornada é 2.
     - Os atributos dos produtos, como `nome` e `preco`, correspondem aos valores simulados.

3. **`testCreateProduct()`**
   - Simula o método `save()` do `ProductService`, retornando um produto fictício salvo.
   - Envia uma requisição `POST /products` com um JSON no corpo representando o produto a ser criado.
   - Verifica:
     - O status da resposta é `200 OK`.
     - Os valores do produto retornado correspondem aos esperados, como `id` e `nome`.

---

### **Pontos-Chave**
- O uso de mocks permite testar apenas a lógica do controlador, sem se preocupar com o funcionamento do serviço ou repositório.
- A validação é feita por meio de:
  - **Status HTTP** esperado.
  - **Conteúdo JSON** retornado, validado com `jsonPath`.

Esses testes garantem que o controlador está funcionando conforme o esperado, enquanto isola dependências externas.


   

---


### Teste Unitário da classe ProductDTOTest    

![image](https://github.com/user-attachments/assets/927d9288-669f-4f3b-941a-a2c6bc6b3d9f)
   

Esse teste unitário verifica a **validação do DTO (`ProductDTO`)** usando a API de validação do Jakarta (`jakarta.validation`). Vamos entender o que foi testado:

### **1️⃣ Configuração inicial**
- **`@BeforeAll setUp()`**:  
  - Cria uma fábrica de validação (`ValidatorFactory`) e um validador (`Validator`).
  - O `Validator` será usado nos testes para verificar se os dados inseridos no `ProductDTO` estão de acordo com as regras de validação.

---

### **2️⃣ Testes de validação**
#### ✅ **Testando um DTO válido (`testValidDTO`)**
- Cria um `ProductDTO` com um nome válido (`"Product Valid"`) e um preço positivo (`50.0`).
- **Verifica que não há violações de validação** (`assertEquals(0, violations.size())`).
- ✅ Esperado: **Nenhum erro de validação.**

---

#### ❌ **Testando um nome inválido (`testInvalidName`)**
- Cria um `ProductDTO` com um nome **vazio** (`""`), que provavelmente viola a regra de que o nome deve ter entre 2 e 100 caracteres.
- **Verifica que há uma violação de validação** (`assertEquals(1, violations.size())`).
- **Confirma que a mensagem de erro esperada é retornada** (`"O nome deve ter entre 2 e 100 caracteres"`).
- ✅ Esperado: **Erro de validação no nome.**

---

#### ❌ **Testando um preço inválido (`testInvalidPrice`)**
- Cria um `ProductDTO` com um preço **negativo** (`-10.0`), que viola a regra de que o preço deve ser maior ou igual a zero.
- **Verifica que há uma violação de validação** (`assertEquals(1, violations.size())`).
- **Confirma que a mensagem de erro esperada é retornada** (`"O preço deve ser maior ou igual a zero"`).
- ✅ Esperado: **Erro de validação no preço.**

---

### **📌 Conclusão**
- O teste verifica se as **restrições de validação** (como nome e preço) estão funcionando corretamente no `ProductDTO`.
- Ele simula **casos válidos e inválidos** e confirma que as mensagens de erro apropriadas são geradas quando necessário.



## Repositório
(https://github.com/teofilonicolau/product_manager_api.git)
