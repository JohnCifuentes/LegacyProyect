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

### Backend Setup

1. Create a PostgreSQL database.
2. Configure database credentials in `application.yml`.
3. From the backend directory, run:

```bash
./gradlew bootRun
```

The backend API will be available at:

```
http://localhost:8080
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

The database schema follows normalization principles to ensure data consistency and flexibility:

* **transaction**: Stores raw and normalized transaction data (amount, date, description, currency).
* **category**: Represents high-level spending categories (e.g., Food, Transportation).
* **category_detail**: Defines categorization rules based on keywords and priority.
* **merchant**: Stores normalized merchant information to avoid duplication.

Transactions reference categories and merchants by ID, allowing categorization rules to evolve without modifying historical transaction data.

---

## Data Ingestion Logic

Transaction data is ingested from a legacy XML-based API.

The ingestion flow:

1. Fetch XML data from the legacy source.
2. Parse XML into DTOs using JAXB.
3. Normalize values such as dates, amounts, and descriptions.
4. Apply categorization rules based on configured keywords.
5. Persist normalized entities into the database.

This process is designed to be repeatable and resilient to partial failures.

---

## Error Handling Strategy

The system applies defensive error handling to manage incorrect or malformed legacy data:

* XML parsing errors are logged and skipped without stopping the ingestion process.
* Invalid dates or amounts fall back to safe defaults or are ignored when necessary.
* Transactions that do not match any categorization rule are stored under an `UNCATEGORIZED` category.
* All errors include contextual logging to facilitate debugging and monitoring.

This ensures the ingestion pipeline remains robust even when upstream data quality is inconsistent.

---

## (Optional) Docker

If Docker is available, the application can be containerized:

```bash
docker build -t expense-categorization-app .
docker run -p 8080:8080 expense-categorization-app
```

---

## Future Improvements

* User-defined categorization rules via the frontend
* Rule prioritization and versioning
* Asynchronous ingestion using message queues
* Enhanced monitoring and metrics
* Role-based access control
