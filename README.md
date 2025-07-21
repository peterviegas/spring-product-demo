# 🛍️ Product API - Spring Boot

A simple RESTful API for product management, built with Spring Boot and Java 17. This project is designed for educational purposes and demonstrates clean architecture, modern testing practices, and Swagger documentation.

---

## 🚀 Technologies Used

- Java 17
- Spring Boot 3.4.7
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
<img width="1667" height="867" alt="image" src="https://github.com/user-attachments/assets/baa7c6b5-10e1-49dc-a8fb-bfba2deb2ed0" />


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
## ✅ Swagger

Need put in POM the dependency

```
<dependency>
			<groupId>org.springdoc</groupId>
			<artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
			<version>2.2.0</version>
		</dependency>
```
<img width="1833" height="957" alt="image" src="https://github.com/user-attachments/assets/b6225d7e-d204-422c-a15c-4539dd75444e" />

---
## ✅ Basic Sample for H2 and JPA

```
# ========== H2 CONFIG ==========
spring.datasource.url=jdbc:h2:mem:productdb
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# ========== JPA ==========
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# ========== H2 Console (opcional) ==========
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```
📌 Acessar o console do H2:
After execute the app, go to:
```
http://localhost:8080/h2-console
```
<img width="552" height="422" alt="image" src="https://github.com/user-attachments/assets/a5b54bb5-bdae-4f6f-90f0-3f2905e9549d" />
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
