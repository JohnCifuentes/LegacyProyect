# Expense Categorization Platform

## Overview

This project is an expense categorization platform designed to ingest transaction data from a legacy XML-based API, normalize and persist it in a relational database, and provide a frontend application to visualize spending by category and merchant.

The main objective is to transform raw financial transactions into meaningful, categorized insights that help stakeholders better understand spending patterns and make informed business decisions.

---

## Architecture

The solution follows a layered and decoupled architecture:

* **Backend API**: Handles business logic, data ingestion, categorization rules, and persistence.
* **Ingestion Module**: Responsible for consuming and parsing legacy XML data.
* **Database**: Stores normalized transactions, categories, merchants, and rule definitions.
* **Frontend Application**: Provides a user-friendly interface for visualizing expenses.

This separation allows the ingestion process to evolve independently from the frontend and ensures scalability and maintainability.

---

## Tech Stack

### Backend

* Java 23
* Spring Boot 3.4.1
* Spring Data JPA
* JAXB (XML parsing)
* Gradle 8.10

### Frontend

* Angular 19.2.9
* TypeScript 5.7.3
* HTML / CSS

### Database

* PostgreSQL 16.11

---

## How to Run Locally

### Prerequisites

* Java 23 or higher
* Node.js 22.15.0
* PostgreSQL 16.11
* Gradle 8.10
* Git
* GitHub

---

### Step 1: Pull PostgreSQL Image

Download the official PostgreSQL image from Docker Hub:

```bash
docker pull postgres:16.11
```

---

### Step 2: Pull Preconfigured Database Image

Download the preconfigured database image used by this project:

```bash
docker pull johncifuentes/legacy-bridge:tagname
```

---

### Step 3: Start Database (legacy-bridge)

Run the database container:

```bash
docker run -d \
  --name legacy-bridge-db \
  -p 5432:5432 \
  johncifuentes/legacy-bridge:tagname
```

Ensure the database is running before starting the backend.

---

### Step 4: Clone Backend Repository

Clone the backend source code from GitHub:

```bash
git clone https://github.com/JohnCifuentes/LegacyProyect.git
cd LegacyProyect
```

---

### Step 5: Build and Run Backend (Gradle)

The backend is built using Gradle.

1. Download dependencies and build the project:

```bash
./gradlew build
```

2. Run the Spring Boot application:

```bash
./gradlew bootRun
```

The backend API will be available at:

```
http://localhost:8080
```

---

### Step 6: Clone Frontend Repository

Clone the frontend source code from GitHub:

```bash
git clone https://github.com/JohnCifuentes/legacy-bridge.git
cd legacy-bridge
```

---

### Step 7: Build and Run Frontend (Angular)

The frontend application is built using Angular.

1. Install dependencies:

```bash
npm install
```

2. Start the development server:

```bash
ng serve
```

The frontend will be available at:

```
http://localhost:4200
```

---

### Frontend Setup

1. Navigate to the frontend directory.
2. Install dependencies:

```bash
npm install
```

3. Start the application:

```bash
ng serve
```

The frontend will be available at:

```
http://localhost:4200
```

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

---
