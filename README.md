# JDBC Learning Repository 🚀

A beginner-friendly JDBC (Java Database Connectivity) repository that demonstrates how Java applications interact with a MySQL database.

This project covers the complete JDBC workflow, including:

* Database Connectivity
* SELECT Operations
* INSERT, UPDATE, DELETE Operations
* Statement Interface
* PreparedStatement Interface
* User Input Handling
* Transactions (Commit & Rollback)
* Resource Management
* JDBC Best Practices

---

# 📖 What is JDBC?

**JDBC (Java Database Connectivity)** is a Java API that enables Java applications to communicate with relational databases.

Using JDBC, Java applications can:

* Connect to databases
* Execute SQL queries
* Retrieve records
* Insert new data
* Update existing data
* Delete records
* Manage transactions

Supported databases include:

* MySQL
* Oracle
* PostgreSQL
* SQL Server
* MariaDB

---

# JDBC Architecture

```text
Java Application
        |
        v
      JDBC API
        |
        v
 JDBC Driver
        |
        v
     Database
```

The JDBC Driver acts as a bridge between the Java application and the database.

---

# Project Structure

```text
LauchApp1/
│
├── Select.java
├── NonSelect.java
├── common.java
├── PreparedStatementDemo.java
├── Transaction.java
└── Userinput.java
```

---

# Database Setup

## Create Database

```sql
CREATE DATABASE JDBClearning;
```

## Use Database

```sql
USE JDBClearning;
```

## Create Table

```sql
CREATE TABLE studentinfo (
    id INT PRIMARY KEY,
    sname VARCHAR(100),
    sage INT,
    scity VARCHAR(100)
);
```

---

# JDBC Workflow

Every JDBC program follows these steps:

```text
1. Load Driver
2. Establish Connection
3. Create Statement
4. Write SQL Query
5. Execute Query
6. Process Result
7. Close Resources
```

---

# Step 1: Load and Register JDBC Driver

```java
Class.forName("com.mysql.cj.jdbc.Driver");
```

### Why?

This loads the MySQL JDBC Driver into JVM memory and registers it with the DriverManager.

Without the driver, Java cannot communicate with MySQL.

---

# Step 2: Establish Database Connection

```java
String url = "jdbc:mysql://localhost:3306/JDBClearning";
String user = "root";
String password = "your_password";

Connection connection =
DriverManager.getConnection(url, user, password);
```

### URL Breakdown

```text
jdbc:mysql://localhost:3306/JDBClearning
│    │         │         │
│    │         │         └── Database Name
│    │         └────────── Port Number
│    └──────────────────── Database Type
└───────────────────────── JDBC Protocol
```

---

# Example 1: SELECT Operation

File:

```text
Select.java
```

Purpose:

Retrieve records from the database.

---

## SQL Query

```sql
SELECT * FROM studentinfo;
```

---

## Execute Query

```java
ResultSet rs = statement.executeQuery(sql);
```

### Why executeQuery()?

Used only for SELECT operations.

Returns:

```java
ResultSet
```

which contains rows returned by the database.

---

## Processing ResultSet

```java
while(rs.next()) {

    System.out.println(
        rs.getInt(1) + " " +
        rs.getString(2) + " " +
        rs.getInt(3) + " " +
        rs.getString(4)
    );
}
```

---

### Understanding rs.next()

Initially, the cursor points before the first row.

```text
Before First Row
```

Calling:

```java
rs.next();
```

moves the cursor to the next row.

Returns:

```text
true  → Row Exists
false → No More Rows
```

---

# Example 2: Non-Select Operations

File:

```text
NonSelect.java
```

Used for:

* INSERT
* UPDATE
* DELETE

---

## INSERT Example

```sql
INSERT INTO studentinfo
(id, sname, sage, scity)
VALUES
(1, 'usaid', 21, 'Pune');
```

Execute:

```java
int rowsAffected =
statement.executeUpdate(sql);
```

---

### executeUpdate()

Used for:

* INSERT
* UPDATE
* DELETE

Returns:

```text
Number of Rows Affected
```

Example:

```text
1
```

means one row was inserted successfully.

---

## DELETE Example

```sql
DELETE FROM studentinfo
WHERE id = 2;
```

Execution:

```java
int rowsAffected =
statement.executeUpdate(sql);
```

---

# executeQuery() vs executeUpdate()

| Feature        | executeQuery() | executeUpdate()          |
| -------------- | -------------- | ------------------------ |
| Used For       | SELECT         | INSERT / UPDATE / DELETE |
| Return Type    | ResultSet      | int                      |
| Retrieves Data | Yes            | No                       |
| Modifies Data  | No             | Yes                      |

---

# Example 3: Common Execution Method

File:

```text
common.java
```

JDBC provides a common execution method:

```java
boolean status =
statement.execute(sql);
```

Works for both:

* SELECT
* INSERT
* UPDATE
* DELETE

---

## Return Values

### If Query is SELECT

```java
true
```

Retrieve ResultSet:

```java
ResultSet rs =
statement.getResultSet();
```

---

### If Query is Non-Select

```java
false
```

Retrieve affected rows:

```java
int rowsAffected =
statement.getUpdateCount();
```

---

# Example 4: PreparedStatement

File:

```text
PreparedStatementDemo.java
```

One of the most important JDBC concepts.

---

# Why Not Use Statement?

Example:

```java
String sql =
"SELECT * FROM studentinfo WHERE id = 1";
```

Problems:

* Hardcoded values
* SQL Injection risk
* Poor readability
* Less efficient for repeated execution

---

# PreparedStatement Solution

Parameterized Query:

```java
String sql =
"SELECT * FROM studentinfo WHERE id = ?";
```

The question mark (`?`) acts as a placeholder.

---

## Create PreparedStatement

```java
PreparedStatement ps =
connection.prepareStatement(sql);
```

---

## Set Values

```java
ps.setInt(1, 1);
```

Meaning:

```text
Placeholder #1 = 1
```

---

## Execute Query

```java
ResultSet rs =
ps.executeQuery();
```

---

# Benefits of PreparedStatement

## Security

Prevents SQL Injection attacks.

---

## Better Performance

Database precompiles the query.

Execution plans can be reused.

---

## Cleaner Code

SQL structure remains fixed while values change dynamically.

---

# Example 5: Transactions

File:

```text
Transaction.java
```

Transactions ensure multiple SQL operations behave as one logical unit.

---

# Real World Example

Bank Transfer:

```text
Account A → Debit ₹1000
Account B → Credit ₹1000
```

Both operations must succeed together.

If one fails:

```text
Rollback Everything
```

---

# ACID Properties

## Atomicity

All operations succeed or none succeed.

---

## Consistency

Database remains in a valid state.

---

## Isolation

Transactions do not interfere with each other.

---

## Durability

Committed data remains permanently stored.

---

# Auto Commit

By default:

```java
connection.getAutoCommit();
```

returns:

```text
true
```

Every SQL statement is saved immediately.

---

# Disable Auto Commit

```java
connection.setAutoCommit(false);
```

Now JDBC waits until:

```java
connection.commit();
```

---

# Commit Changes

```java
connection.commit();
```

Permanently saves all modifications.

---

# Rollback Changes

```java
connection.rollback();
```

Undoes all changes if an error occurs.

---

## Recommended Pattern

```java
try {

    connection.setAutoCommit(false);

    // Execute Queries

    connection.commit();

} catch(Exception e) {

    connection.rollback();
}
```

---

# Example 6: User Input with PreparedStatement

File:

```text
Userinput.java
```

Purpose:

Insert data dynamically using user input.

---

## Scanner Input

```java
Scanner sc = new Scanner(System.in);
```

Input:

```text
Student ID
Student Name
Student Age
Student City
```

---

## Parameterized Query

```sql
INSERT INTO studentinfo
(id, sname, sage, scity)
VALUES (?, ?, ?, ?)
```

---

## Setting Values

```java
ps.setInt(1, id);
ps.setString(2, name);
ps.setInt(3, age);
ps.setString(4, city);
```

---

## Execute

```java
int rowsAffected =
ps.executeUpdate();
```

---

# Resource Management

Always close JDBC resources.

Example:

```java
rs.close();
statement.close();
connection.close();
```

Resources to close:

* ResultSet
* Statement
* PreparedStatement
* Connection
* Scanner

---

# Exception Handling

Common Exceptions:

## Driver Not Found

```java
ClassNotFoundException
```

Occurs when JDBC Driver is missing.

---

## Database Errors

```java
SQLException
```

Possible reasons:

* Wrong Username
* Wrong Password
* SQL Syntax Error
* Duplicate Primary Key
* Database Not Running

---

# Best Practices

## Prefer PreparedStatement

Use:

```java
PreparedStatement
```

Instead of:

```java
Statement
```

for dynamic queries.

---

## Avoid Hardcoded Credentials

Bad:

```java
String password =
"your_password";
```

Use:

* Environment Variables
* Properties Files
* Configuration Files

---

## Use Try-With-Resources

Modern JDBC approach:

```java
try (
    Connection con =
        DriverManager.getConnection(url,user,password);

    PreparedStatement ps =
        con.prepareStatement(sql)
) {

    // Code

}
```

Resources are automatically closed.

---

## Handle Transactions Properly

Always:

```java
commit();
```

on success and

```java
rollback();
```

on failure.

---

# Concepts Covered

* JDBC Fundamentals
* Driver Loading
* Database Connectivity
* Statement Interface
* PreparedStatement Interface
* ResultSet Processing
* SELECT Operations
* INSERT Operations
* DELETE Operations
* executeQuery()
* executeUpdate()
* execute()
* Transactions
* Commit & Rollback
* User Input Handling
* Resource Management
* Exception Handling

---

# Learning Outcomes

After completing this repository, you will be able to:

* Connect Java applications to MySQL.
* Execute SQL queries using JDBC.
* Retrieve records using ResultSet.
* Perform INSERT, UPDATE, and DELETE operations.
* Use PreparedStatement securely.
* Accept dynamic user input.
* Manage database transactions.
* Apply JDBC best practices in real-world projects.

---

# Future Enhancements

* Batch Processing
* CallableStatement
* Stored Procedures
* Connection Pooling
* DAO Design Pattern
* JDBC with Maven
* JDBC with Spring Boot
* Hibernate ORM
* Logging Framework Integration
* Configuration Files

---

# Author

This repository was created as a practical JDBC learning guide for beginners to understand database connectivity and SQL execution using Java and MySQL.
