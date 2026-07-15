# Personal Expense Management Backend

A RESTful backend service for the **Personal Expense Management (PEM)** application built with **Spring Boot**. The application helps users manage multiple financial accounts, record income and expense transactions, and maintain an accurate transaction history.

Instead of editing historical financial records, the system follows an immutable transaction model to preserve the integrity of account balances. Every transaction records the account balance before and after the transaction, ensuring a reliable financial ledger.

---

# Features

## User Authentication

* User registration and login
* JWT-based authentication
* Secure password encryption using BCrypt
* Protected REST APIs

---

## Multi-Account Management

Users can create and manage multiple financial accounts such as:

* SBI Savings
* HDFC Bank
* Cash Wallet
* Mom's SBI Account
* Dad's Bank Account
* Business Account

Each account maintains its own balance independently.

---

## Initial Account Balance

When creating an account, users specify an initial balance.

The system uses this value as the starting point for all future transactions associated with that account.

---

## Transaction Management

Users can record two types of transactions:

* Income
* Expense

Each transaction includes:

* Amount
* Transaction type
* Description
* Associated account
* Timestamp

---

## Immutable Transaction History

Once a transaction is created, it **cannot be modified or deleted**.

This design prevents inconsistencies in account balances and preserves a complete financial history.

Instead of allowing edits that could invalidate later transactions, every transaction remains a permanent record.

---

## Balance Tracking

Every transaction stores:

* Balance before the transaction
* Balance after the transaction

This creates a complete audit trail of account balance changes and makes it easy to verify how each transaction affected an account.

---

## Financial History

The application allows users to:

* View all transactions
* View transactions for a specific account
* Track account balances over time
* Review complete financial history

---

# Tech Stack

* Java
* Spring Boot
* Spring Security
* Spring Data JPA (Hibernate)
* JWT Authentication
* Maven
* MySQL

---

# Project Architecture

The backend follows a layered architecture:

```text
Client
    │
    ▼
Controllers
    │
    ▼
Services
    │
    ▼
Repositories
    │
    ▼
Database
```

Each layer has a single responsibility, improving maintainability, scalability, and testability.

---

# Project Structure

```text
src
├── config
├── controller
├── dto
├── entity
├── exception
├── repository
├── security
├── service
└── util
```

---

# Security

Authentication is implemented using Spring Security and JWT.

Security features include:

* Secure password hashing using BCrypt
* Stateless authentication
* Protected API endpoints
* User-specific data access
* Unauthorized request handling

---

# Running the Application

## Prerequisites

* Java 17+
* Maven
* MySQL

## Clone Repository

```bash
git clone https://github.com/TEJAYERR/PEM_BACKEND.git
```

## Configure Database

Update your database credentials in:

```text
application.properties
```

## Build

```bash
mvn clean install
```

## Run

```bash
mvn spring-boot:run
```

The application will be available at:

```text
http://localhost:8080
```

---

# Future Enhancements

* Budget planning
* Savings goals
* Recurring transactions
* Monthly and yearly financial reports
* Export transaction history
* Transaction filtering and search
* Charts and analytics
* Multi-currency support
* Account archiving

---

# Design Principle

The application treats financial transactions as immutable records.

Rather than allowing edits to historical transactions—which could invalidate every subsequent account balance—each transaction is permanently stored along with the balance before and after it. This approach provides a consistent financial ledger and maintains the integrity of account history.

---

# License

This project is intended for educational and portfolio purposes.

---

# Author

**Teja Yerriboyina**

GitHub: https://github.com/TEJAYERR
