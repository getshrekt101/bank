Once the app is running:

    URL: http://localhost:8080/h2-console

    JDBC URL: jdbc:h2:mem:testdb

    Username: bank

    Password: bank


Swagger URLs to Try in Browser:

    Swagger UI: http://localhost:8080/swagger-ui.html or /swagger-ui/index.html

    OpenAPI JSON: http://localhost:8080/v3/api-docs

    OpenAPI YAML: http://localhost:8080/v3/api-docs.yaml


**PREMADE DB AUTHENTICATIONS**

| **ROLE** | **USERNAME** | **PASSWROD** | 
|----------|--------------|--------------|
| ADMIN    | admin         | admin        | 
| TELLER   | teller         | teller | |
| USER     | user         | user | |



**Permissions**

| **Endpoint**         | **ADMIN** | **TELLER**     | **USER**           |
|----------------------|---------|----------------|---------------------|
| `/useraccount/**`    | Full    | None           | None               |
| `/user/**`           | Full    | GET / POST / PUT | GET / PUT  |
| `/account/**`        | Full    | GET / POST / PUT | GET    |
| `/transaction/**`    | Full    | GET / POST / PUT | GET / POST * |
| `DELETE /**`         | Full | Not allowed    | Not allowed        |
