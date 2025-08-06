
# 💰 Digital Banking System

A robust and secure digital banking system built using Spring Boot, providing a range of banking operations through clean and efficient RESTful APIs.

---

## ✨ Features

- 🔐 **Secure Authentication & Authorization** (JWT-based)
- 👤 **User and Account Management**
- 💼 **Wallet and Fund Transfers**
- 📇 **Beneficiary Handling**
- 🧾 **Bill Payments**
- 📈 **Transaction History**
- ⚡ **Real-time Balance Updates**

---

## 💪 Tech Stack

- **Backend Framework**: Spring Boot  
- **Security**: Spring Security with JWT  
- **ORM**: Spring Data JPA (Hibernate)  
- **Database**: PostgreSQL  
- **Build Tool**: Maven  
- **Testing**: Postman / Unit Testing (Optional)

---

## 📌 Prerequisites

- Java 17 or higher  
- Maven  
- PostgreSQL Server

---

## 🔧 Setup & Installation

### 1. Clone the repository

```bash
git clone https://github.com/yourusername/Digital-Banking-System.git
cd Digital-Banking-System
```

### 2. Configure the database

Edit `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/your_db_name
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 3. Build the project

```bash
mvn clean install
```

### 4. Run the application

```bash
mvn spring-boot:run
```

- The application will run at: [http://localhost:8080](http://localhost:8080)

---

## 📨 API Endpoints

### 🔑 Authentication (`/auth`)

| Method | Endpoint               | Description                          |
|--------|------------------------|--------------------------------------|
| POST   | `/auth/register`       | Register a new customer              |
| POST   | `/auth/login`          | User login                           |
| POST   | `/auth/refresh`        | Refresh a JWT token                  |
| POST   | `/auth/logout`         | Logout (stateless, client-side)      |
| DELETE | `/auth/delete`         | Delete customer and all data         |

---

### 🧝 Account Management (`/customers/account`, `/customers`)

| Method | Endpoint                                      | Description                                 |
|--------|-----------------------------------------------|---------------------------------------------|
| POST   | `/customers/account`                          | Add a new account                           |
| DELETE | `/customers/account`                          | Delete an account                           |
| GET    | `/customers/account?walletId={id}`            | View all accounts (by wallet ID)            |
| POST   | `/customers/account/deposit`                  | Deposit into an account                     |
| POST   | `/customers/account/transferToWallet`         | Account → Wallet transfer                   |
| POST   | `/customers/account/transferBetweenAccounts`  | Transfer between customer’s own accounts    |
| POST   | `/customers/account/transferToOtherCustomerAccount` | Transfer to another customer's account |
| POST   | `/customers/account/transferToBeneficiary`    | Account → Beneficiary transfer              |

---

### 💳 Wallet Operations (`/customers/wallet`)

| Method | Endpoint                                      | Description                         |
|--------|-----------------------------------------------|-------------------------------------|
| GET    | `/customers/wallet/balance`                   | View wallet balance + customer info |
| POST   | `/customers/wallet/transferToAccount`         | Wallet → Customer’s Account         |
| POST   | `/customers/wallet/transferToBeneficiary`     | Wallet → Beneficiary transfer       |

---

### 🧾 Transactions (`/customers/transactions`)

| Method | Endpoint                       | Description                              |
|--------|--------------------------------|------------------------------------------|
| GET    | `/customers/transactions`      | Get all transactions of logged-in user   |

---

### 👥 Beneficiaries (`/customers/beneficiaries`)

| Method | Endpoint                                      | Description                   |
|--------|-----------------------------------------------|-------------------------------|
| POST   | `/customers/beneficiaries`                    | Add a new beneficiary         |
| GET    | `/customers/beneficiaries`                    | Get all beneficiaries         |
| DELETE | `/customers/beneficiaries/{beneficiaryId}`    | Delete a beneficiary by ID    |

---

### 💵 Bill Payments (`/payments/bills`)

| Method | Endpoint              | Description                            |
|--------|-----------------------|----------------------------------------|
| POST   | `/payments/bills`     | Add a new bill payment                 |
| GET    | `/payments/bills`     | View bill payments for the user       |

---

## 📘 Sample JSONs

### ✅ Register Customer (POST `/auth/register`)

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

---

### 🔐 Login DTO (POST `/auth/login`)

```json
{
  "mobileNo": "888030303",
  "password": "pass@123"
}
```

---

## 📜 License

This project is open-source and available under the **MIT License**.
