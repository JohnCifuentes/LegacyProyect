# Expense Categorization Platform

## Overview

This project is an expense categorization platform that:

- Consumes transaction data from a **simulated XML service** (Spring Boot).  
- Normalizes and persists the data into **PostgreSQL**.  
- Allows performing CRUD operations through a **Node.js backend**.  
- Provides a **simple frontend in HTML/CSS/JS** to visualize and manage expenses by category and merchant.  

The main goal is to transform raw financial transactions into categorized insights to understand spending patterns and make informed decisions.

---

## Architecture

The solution follows a **modular and decoupled architecture**:

* **Spring Boot (External Microservice)**: Generates random XML data.  
* **Node.js Backend**: Connects to the database, exposes CRUD endpoints, and cleans XML data.  
* **PostgreSQL Database**: Stores transactions, categories, merchants, and categorization rules.  
* **Frontend (HTML/CSS/JS)**: Consumes Node.js endpoints and provides visualization and management tools.

This separation allows each module to evolve independently while ensuring scalability and maintainability.

---

## Tech Stack

### Spring Boot (XML Simulator)

* Java 23  
* Spring Boot 3.4.1  
* Gradle 8.10  
* JAXB (XML parsing)  
* Swagger / OpenAPI  

### Node.js Backend

* Node.js 22+  
* Express.js  
* PostgreSQL  
* Swagger / OpenAPI  

### Frontend

* HTML / CSS / JavaScript  
* `npx serve` to serve the application locally  

### Database

* PostgreSQL 16.11  
* DBeaver (optional, for database inspection)  

---

## How to Run Locally

### Step 1: Pull Preconfigured Database Image

Download the preconfigured database image used by this project:

```bash
docker pull johncifuentes/legacy-bridge:v1
````

---

### Step 2: Start Database (legacy-bridge)

Run the database container:

```bash
docker run -d \
  --name legacy-bridge-db \
  -p 5432:5432 \
  johncifuentes/legacy-bridge:v1
```

Ensure the database is running before starting the backend.

---

### Step 3: Clone Spring Boot Repository

```bash
git clone https://github.com/JohnCifuentes/LegacyProyect.git
cd LegacyProyect
```

---

### Step 4: Build and Run Spring Boot Backend

Build the project and run Spring Boot to expose the XML microservice:

```bash
./gradlew build
./gradlew bootRun
```

**Swagger UI:**

```text
http://localhost:8080/swagger-ui.html
```

> This service generates XML data and is required to simulate the external microservice that provides the XML input.

---

### Step 5: Clone Node.js Backend Repository

```bash
git clone https://github.com/JohnCifuentes/legacy-bridge-backend.git
cd legacy-bridge-backend
```

**Install dependencies:**

```bash
npm install
```

**Run Node.js Backend:**

```bash
node index.js
```

**Swagger UI:**

```text
http://localhost:3001/swagger/#/
```

> Node.js connects to PostgreSQL and exposes all CRUD endpoints. It also implements the logic to clean XML data from the Spring Boot microservice.

---

### Step 6: Clone Frontend Repository

```bash
git clone https://github.com/JohnCifuentes/legacy-bridge-fronted.git
cd legacy-bridge-fronted
```

**Run Frontend:**

```bash
npx serve
```

* No additional libraries are required.
* It directly consumes the Node.js backend endpoints.

---

## Database Schema Design

The database schema follows normalization principles to ensure data consistency, flexibility, and extensibility:

* **transaction**: Stores raw and normalized transaction data (amount, date, description, currency).
* **category**: Represents high-level spending categories (e.g., Food, Transportation).
* **category_detail**: Defines categorization rules based on keywords and priority.
* **merchant**: Stores merchant definitions along with keyword sets used to identify merchants from transaction descriptions.

Both **category** and **merchant** entities are rule-driven and decoupled from transactions. Transactions reference categories and merchants by ID, allowing new rules or keywords to be added without modifying historical data.

If no matching rule is found for either category or merchant, a default value is assigned (e.g., `UNCATEGORIZED` or `UNKNOWN_MERCHANT`) to preserve data integrity.

---

## Data Ingestion Logic

Transaction data is ingested from a legacy XML-based API using a resilient and rule-driven process.

The ingestion flow:

1. Fetch XML data from the legacy source.
2. Parse XML into DTOs using JAXB.
3. Normalize values such as dates, amounts, and descriptions.
4. Resolve merchant and category using keyword-based matching rules.
5. Persist normalized entities into the database.

### Merchant Matching Logic

Merchant identification is performed using a keyword-matching algorithm:

* Each merchant contains a set of keywords.
* A matching function calculates the percentage of keyword coincidence between the transaction description and the merchant keywords.
* If the match percentage is greater than **90%**, the corresponding merchant is assigned to the transaction.
* If no merchant meets the threshold, a default merchant value is assigned.

### Category Matching Logic

Category classification follows a stricter rule:

* Category keywords must match **100%** to be assigned.
* This ensures deterministic and unambiguous categorization.
* If no category rule is satisfied, a default category is assigned.

### Date Normalization

* Multiple date formats are supported through predefined parsing rules.
* If an incoming date format does not match any known pattern, a default date value is assigned.

This approach ensures the ingestion process is tolerant to unexpected data formats while maintaining consistent and reliable outputs.

---

## Error Handling Strategy

The system applies defensive error handling to manage incorrect or malformed legacy data:

* XML parsing errors are logged and skipped without stopping the ingestion process.
* Invalid dates or amounts fall back to safe defaults or are ignored when necessary.
* Transactions that do not match any categorization rule are stored under an `UNCATEGORIZED` category.
* All errors include contextual logging to facilitate debugging and monitoring.

This ensures the ingestion pipeline remains robust even when upstream data quality is inconsistent.
