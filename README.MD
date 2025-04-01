# Bank Project

A Java-based banking system application built using Spring Boot and Maven.

---

## 🛠️ Set Up

### ✅ Requirements

- **JDK**: Java 22  
- **Build Tool**: Maven 3.8+  
- **IDE (Optional)**: IntelliJ IDEA

---

## 📦 Installation

### 1. Download and install Java 22

- [Download JDK 22](https://jdk.java.net/22/)
- Set `JAVA_HOME` environment variable pointing to the JDK 22 directory.
- Verify installation:
  ```bash
  java -version
  ```

### 2. Download and install Maven

- [Download Maven](https://maven.apache.org/download.cgi)
- Set `MAVEN_HOME` and add Maven `bin` directory to your system `PATH`.
- Verify installation:
  ```bash
  mvn -version
  ```

---

## 🚀 Running the Project

### 🧪 Build the Project

Open a terminal or command prompt and navigate to the root folder of the project:

```bash
cd path/to/bank-project
mvn clean install
```

This will generate a JAR file inside the `target/` directory.

---

## 💻 Run in IntelliJ IDEA

1. Open IntelliJ IDEA.
2. Choose **Open** and select the root folder of the project.
3. IntelliJ will detect the Maven project and import dependencies.
4. Ensure JDK 22 is selected under:
   - `File > Project Structure > Project > Project SDK`
5. Run the `BankApplication` class (or the main class) with the green run button.

---

## 📁 Run via JAR (Command Line)

After building the project using Maven (`mvn clean install`), run the application using:

```bash
java -jar target/bank-project-1.0.0.jar
```

> Replace `bank-project-1.0.0.jar` with the actual JAR name if different.

---

## 🧾 Notes

- Make sure ports used by the application (e.g., `8080`) are not in use.
- Default profiles and configurations can be managed through `application.properties` or `application.yml`.

---




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
