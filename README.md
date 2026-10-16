# Library

A library management application built with **Java**, focused on learning clean backend architecture and database integration.

## Project Goals

- Practice clean backend architecture and separation of responsibilities.
- Implement database access using JDBC.
- Manage relational data using PostgreSQL.
- Learn object-oriented programming and exception handling.

## Tech Stack

- Java
- Maven
- JDBC
- PostgreSQL
- JUnit5

## Entities

- Author
- Book
- Member
- Librarian
- Loan

## Prerequisites

Before running the project, make sure you have installed:

- JDK 25 or a compatible version.
- Apache Maven.
- PostgreSQL.

## Setup

### 1. Clone the repository

```bash
git clone https://github.com/cristiancid-dev/library-api.git
cd library
```

### 2. Create the database

Create a PostgreSQL database named `library`.

### 3. Initialize the database schema

Execute the SQL script located at `database/schema.sql` against the `library` database.

You can do this using pgAdmin:

1. Connect to your PostgreSQL server.
2. Select the `library` database.
3. Open **Tools → Query Tool**.
4. Open `database/schema.sql`.
5. Execute the script.

### 4. Configure the database connection

Configure the PostgreSQL connection with the following settings:

- **Host:** `localhost`
- **Port:** `5432`
- **Database:** `library`

Provide your own PostgreSQL username and password through your local configuration.

**Do not commit database credentials to the repository.**

### 5. Build the project

From the project root directory, run:

```bash
mvn clean package
```

## Author

**Cristian Cid**

[GitHub](https://github.com/cristiancid-dev)
