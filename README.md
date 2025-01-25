# Identity document validator

**Identity document validator** is a lightweight and efficient library designed to validate various types of identity documents, including national identity cards, passports, and other government-issued IDs. This library provides easy-to-use methods to ensure that the document format, length, and check digits conform to the official standards.

## Compatibility

This library is compatible with:

- **Java**: 11 or higher

## Dependencies

This library uses the following dependencies:

- **SLF4J API**: A simple logging facade for Java.
  - Group: `org.slf4j`
  - Artifact: `slf4j-api`
  - Version: `2.0.16`

- **SLF4J Simple**: A simple logger implementation for SLF4J.
  - Group: `org.slf4j`
  - Artifact: `slf4j-simple`
  - Version: `2.0.16`

- **Jakarta Validation API**: API for Bean Validation.
  - Group: `jakarta.validation`
  - Artifact: `jakarta.validation-api`
  - Version: `3.1.0`

- **JUnit Jupiter**: A testing framework for JUnit 5.
  - Group: `org.junit.jupiter`
  - Artifact: `junit-jupiter`
  - Version: `5.11.4`
  - Scope: `test`

## Installation

You can add **Identity document validator** to your project using Maven or Gradle.

### Maven Dependency

Add the following to your `pom.xml`:

```xml
<dependency>
    <groupId>io.github.afcarrera</groupId>
    <artifactId>identity-document-validator</artifactId>
    <version>0.0.1</version>
</dependency>
```

### Gradle Dependency

Add the following to your `gradle.build`:

```
dependencies {
    implementation 'io.github.afcarrera:identity-document-validator:0.0.1'
}
```

## Usage

Here’s how to use Identity Document Validator for document validation.

### Spring boot application

#### Option 1: Create a Spring Boot Project using Spring Initializr

You can easily create a Spring Boot project with **Spring Validation** using [Spring Initializr](https://start.spring.io/).

1. Go to [Spring Initializr](https://start.spring.io/).
2. Select your preferred **Project** (Maven/Gradle) and **Java Version**.
3. Under **Dependencies**, search for and add **Spring Web** and **Validation**.
4. Generate the project and import it into your IDE.

This will automatically add the necessary dependencies for Spring Validation and Spring Web, including `spring-boot-starter-validation` and `spring-boot-starter-web`.

#### Option 2: Manually Configure Spring Validation

If you prefer to configure Spring Validation and Spring Web manually, add the following dependencies to your `pom.xml` or `build.gradle`:

- **For Maven**:

    ```xml
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
    </dependencies>
    ```

- **For Gradle**:

    ```gradle
    dependencies {
        implementation 'org.springframework.boot:spring-boot-starter-validation'
        implementation 'org.springframework.boot:spring-boot-starter-web'
    }
    ```

#### Model class

Use @CI annotation in a string field of your model class

```
package com.example.demo.model;

import io.github.afcarrera.identity.ec.annotation.CI;

public class Customer {

    @CI
    String ci;

    public Customer(String ci) {
        this.ci = ci;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }
}
```

#### Controller class
Use @Valid annotation for the model class in a controller or service class
```
package com.example.demo.controller;

import com.example.demo.model.Customer;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
    @PostMapping("/customers")
    public Customer getCustomer(@RequestBody @Valid Customer customer) {
        return customer;
    }
}
```