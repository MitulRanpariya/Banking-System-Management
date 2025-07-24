# Digital Banking System

A robust and secure digital banking system built using **Spring Boot**, providing a range of banking operations through clean and efficient RESTful APIs.

## ✨ Features

* 🔐 Secure Authentication & Authorization (JWT-based)
* 👤 User and Account Management
* 💼 Wallet and Fund Transfers
* 📇 Beneficiary Handling
* 🧾 Bill Payments
* 📈 Transaction History
* ⚡ Real-time Balance Updates

## 💪 Tech Stack

* **Backend Framework:** Spring Boot
* **Security:** Spring Security with JWT
* **ORM:** Spring Data JPA (Hibernate)
* **Database:** PostgreSQL
* **Build Tool:** Maven
* **Testing:** Postman / Unit Testing (Optional)

## 📌 Prerequisites

* Java 17 or higher
* Maven
* PostgreSQL Server

## 🔧 Setup & Installation

1. **Clone the repository**

   ```bash
   git clone https://github.com/yourusername/Digital-Banking-System.git
   cd Digital-Banking-System
   ```

2. **Configure the database**

   * Open `src/main/resources/application.properties`
   * Replace the database credentials with your own:

     ```properties
      spring.datasource.url=jdbc:postgresql://localhost:5432/your_db_name
      spring.datasource.username=your_username
      spring.datasource.password=your_password
     ```

3. **Build the project**

   ```bash
   mvn clean install
   ```

4. **Run the application**

   ```bash
   mvn spring-boot:run
   ```

5. The application will be live at:
   [http://localhost:8080](http://localhost:8080)

---

## 📨 API Endpoints

### 🔑 Authentication

* `POST /api/auth/login` – User login
* `POST /api/auth/refresh` – Refresh JWT token

### 🧝 Account Management

* `POST /api/accounts` – Create account
* `GET /api/accounts/{id}` – Get account by ID
* `PUT /api/accounts/{id}` – Update account

### 💳 Wallet Operations

* `GET /api/wallets/{id}` – Wallet details
* `POST /api/wallets/transfer` – Transfer money

### 🧾 Transactions

* `GET /api/transactions/customer/{cid}` – Get all transactions of customer
* `GET /api/transactions/{tid}` – Get single transaction detail

### 👥 Beneficiaries

* `POST /api/beneficiaries` – Add beneficiary
* `GET /api/beneficiaries/customer/{cid}` – Get all beneficiaries of customer
* `DELETE /api/beneficiaries/{bid}` – Delete a beneficiary

### 👤 Customers

* `POST /api/customers/register` – Register new customer
* `GET /api/customers/{cid}` – View profile
* `PUT /api/customers/{cid}` – Update profile
* `DELETE /api/customers/{cid}` – Delete account

---

## 📘 Sample JSONs

### ✅ Register Customer (POST `/api/customers/register`)

```json
{
  "name": "Mitul Ranpariya",
  "mobileNo": "888030303",
  "password": "pass@123",
  "wallet": {
    "balance": 1000.0
  }
}
```

### 🔐 Login DTO (POST `/api/auth/login`)

```json
{
  "mobileNo": "888030303",
  "password": "pass@123"
}
```

---


## 📜 License

This project is open-source and available under the [MIT License](LICENSE).
