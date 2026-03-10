# Distributed Event Processing & Fraud Detection System

A **FinTech-style event-driven microservices system** built using **Spring Boot, Apache Kafka, and MySQL** to process transactions asynchronously and perform real-time fraud detection.

This project demonstrates how modern financial systems process high volumes of transactions using **event streaming architecture**.

---

## Architecture

Client → Transaction Service → Kafka Topic → Fraud Detection Service

1. Client sends a transaction request.
2. Transaction Service stores the transaction in MySQL.
3. A **Kafka producer publishes the transaction event**.
4. Fraud Detection Service consumes the event from Kafka.
5. Fraud detection logic analyzes the transaction and flags suspicious activity.

---

## Tech Stack

- **Java 21**
- **Spring Boot**
- **Apache Kafka**
- **MySQL**
- **Docker**
- **Maven**
- **REST APIs**

---

## Project Structure
