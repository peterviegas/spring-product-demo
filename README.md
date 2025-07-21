# 🛍️ Product API - Spring Boot

A simple RESTful API for product management, built with Spring Boot and Java 17. This project is designed for educational purposes and demonstrates clean architecture, modern testing practices, and Swagger documentation.

---

## 🚀 Technologies Used

- Java 17
- Spring Boot 3.5.3
- Spring Web
- Spring Data JPA
- H2 In-Memory Database
- Lombok
- JUnit 5 + Mockito
- Springdoc OpenAPI (Swagger UI)

---

✅ 1. Project Content:

. Separation of layers (controller, service, repository)

. Testing with JUnit/Mockito

. REST Patterns

. Swagger

. Use of best practices (DTOs, exception handling, etc.)


✅ 2. Project structure:
```
📁 springboot-product-demo/
│
├─ 🧱 spring-boot/
│   ├─ config/
│   ├─ controller/
│   ├─ dto/
│   ├─ entity/
│   ├─ exception/
│   ├─ mapper/
│   ├─ repository/
│   ├─ service/
│   └─ ProductApplication.java ✅
│
├─ 🌐 rest-api/
│   └─ SwaggerConfig, DTOs, versionamento
│
├─ 🧪 test/
│   └─ Unit e Integration (JUnit + Mockito)
```

✅ 3. Created Project using Spring Initializr:
<img width="1619" height="805" alt="image" src="https://github.com/user-attachments/assets/caaf6b31-7af5-488e-a34a-1ca0ca85050c" />

✅ 4. Estruct of Lombock

🧾 Explanation quickly:

@Data: gera getters, setters, equals, hashCode e toString.

@NoArgsConstructor: construtor sem argumentos.

@AllArgsConstructor: construtor com todos os argumentos.

BigDecimal: usado para representar o preço com precisão decimal, ideal para valores monetários.

---

## 🔁 API Endpoints

| Method | Endpoint           | Description               |
|--------|--------------------|---------------------------|
| GET    | /api/products      | Get all products          |
| GET    | /api/products/{id} | Get product by ID         |
| POST   | /api/products      | Create a new product      |
| PUT    | /api/products      | Update an existing product|
| DELETE | /api/products/{id} | Delete product by ID      |

---

## ✅ Unit Tests

This project includes controller-level tests using:

- `@WebMvcTest`
- `MockMvc`
- `@MockitoBean` (introduced in Spring Boot 3.4+)

To run tests:

```bash
./mvnw test
```

---
📖 Swagger UI Documentation

After running the app, access the API documentation at:
```
http://localhost:8081/swagger-ui/index.html
```
Swagger is powered by Springdoc OpenAPI 2.x.

---
⚙️ How to Run
Clone this repository:

´´´
git clone https://github.com/your-username/product-api.git
cd product-api
´´´

Start the application:
```
./mvnw spring-boot:run
```
⚠️ Notes
The project assumes Java 17 as the runtime environment.

The springdoc-openapi-starter-webmvc-ui is used for Swagger support.

If you encounter any startup issues (e.g., NoSuchMethodError related to ControllerAdviceBean), ensure your Java version is 17+ and that all dependencies are aligned with Spring Boot 3.5.x.

👤 Author
Developed by Peter Viegas
LinkedIn Profile
