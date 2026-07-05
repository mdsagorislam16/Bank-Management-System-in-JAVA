# 🏦 Bank Management System (Java)

A desktop-based **Bank Management System** built with **Java Swing** (GUI) and **MySQL** (database) via **JDBC**. The application simulates a real-world ATM/banking workflow — from opening a new account (multi-page signup form) to logging in with a card number & PIN, and performing everyday banking operations like deposits, withdrawals, balance enquiry, mini statements, and PIN change.

---

## 🎥 Demo Video

Watch the full walkthrough of the project here:

▶️ **[Project Demo Playlist](https://www.youtube.com/watch?v=pMR_48AF-A0&list=PL_6klLfS1WqG8mRCW5a-bIViq1DbzQkp9)**

## 📂 Source Code

The complete source code is available on GitHub:

🔗 **[github.com/mdsagorislam16/Bank-Management-System-in-JAVA](https://github.com/mdsagorislam16/Bank-Management-System-in-JAVA)**

---

## 🛠️ Tech Stack

| Layer          | Technology                          |
|----------------|--------------------------------------|
| Language       | Java (JDK)                          |
| GUI Framework  | Java Swing (`javax.swing`, `java.awt`) |
| Database       | MySQL                               |
| DB Connectivity| JDBC (`java.sql`)                   |
| Date Handling  | `java.text.SimpleDateFormat`        |
| Date Picker    | JCalendar (`com.toedter.calendar.JDateChooser`) |

---

## ✨ Features / Functionalities Implemented

### 👤 Account Creation (Multi-Page Signup)
- **Page 1 — Personal Details:** Name, Father's Name, Date of Birth (with date picker), Gender, Email, Marital Status, Address, City, Pin Code, State
- **Page 2 — Additional Details:** Religion, Category, Income Range, Educational Qualification, Occupation, PAN Number, Aadhar Number, Senior Citizen status, Existing Account Holder status
- **Page 3 — Account Details:** Account Type selection (Saving / Fixed Deposit / Current / Recurring Deposit), auto-generated 16-digit Card Number & 4-digit PIN, additional service selection (ATM Card, Internet Banking, Mobile Banking, Email/SMS Alerts, Cheque Book, E-Statement), and a declaration checkbox
- Full **form validation** at every step (required fields, PIN confirmation match, declaration acceptance)

### 🔐 Login System
- Secure login using **Card Number + PIN**
- Invalid credential handling with clear error messages

### 💰 Core Banking Transactions
- **Deposit** — Add money to the account with real-time database update
- **Cash Withdrawal** — Custom amount withdrawal with balance validation (prevents overdraft)
- **Fast Cash** — One-tap withdrawal for common preset amounts (TAKA 100 / 500 / 1000 / 2000 / 5000 / 10000)
- **Balance Enquiry** — Instantly view current account balance, calculated live from transaction history
- **Mini Statement** — Tabular view of all past transactions (date, type, amount) with running balance
- **PIN Change** — Securely update PIN across all related tables (bank, login, account records)

### 🎨 UI/UX
- Consistent, modern **navy-blue & teal** themed interface across all screens
- Clean side-panel layout (inspired by modern banking apps) instead of static background images
- Styled input fields, buttons with hover effects, and readable, appropriately sized fonts
- Full-size, properly proportioned windows (no cramped or overlapping form fields)
- User-friendly error dialogs for every database/validation failure (instead of silent crashes)

---

## 🗄️ Database Schema

The project uses a MySQL database named **`bankmanagement`**. Below is the complete schema used to set up all required tables:

```sql
create database bankmanagement;
show databases;
use bankmanagement;

-- Page 1: Personal details captured during signup
create table signup(
    formno varchar(20),
    name varchar(20),
    father_name varchar(20),
    dob varchar(20),
    gender varchar(20),
    email varchar(40),
    marital varchar(20),
    address varchar(40),
    city varchar(20),
    pincode varchar(20),
    state varchar(20)
);
show tables;
select * from signup;

-- Page 2: Additional KYC-style details
create table signuptwo(
    formno varchar(20),
    religion varchar(20),
    category varchar(20),
    income varchar(20),
    educationa varchar(20),
    occupation varchar(40),
    pan varchar(20),
    adhar varchar(40),
    senior varchar(20),
    existing varchar(20)
);
select * from signuptwo;

-- Page 3: Account type, card number, PIN, and requested facilities
create table signupthree(
    formno varchar(20),
    accountType varchar(40),
    cardnumber varchar(25),
    pin varchar(20),
    facility varchar(120)
);

-- Login credentials (card number + PIN)
create table login(
    formno varchar(20),
    cardnumber varchar(40),
    pin varchar(20)
);
select * from signupthree;
select * from login;

-- Transaction ledger: every deposit/withdrawal is logged here
drop table bank;
create table bank(
    pin varchar(10),
    date varchar(20),
    type varchar(20),
    amount varchar(20)
);
select * from bank;
```

> 💡 **Tip:** If you plan to extend the project, consider widening `pin` to `varchar(20)` and `date` to `varchar(50)` in the `bank` table to leave extra headroom for longer identifiers and date formats.

---

## ⚙️ Setup & Installation

1. **Install prerequisites**
   - JDK 8 or above
   - MySQL Server (with a MySQL client such as MySQL Workbench or phpMyAdmin/XAMPP)
   - JCalendar library (`.jar`) added to the project's build path (used for the Date of Birth picker)
   - MySQL Connector/J (JDBC driver) added to the project's build path

2. **Set up the database**
   - Run the SQL script above in your MySQL client to create the `bankmanagement` database and all required tables.

3. **Configure the database connection**
   - Open the `Conn.java` file and update the database URL, username, and password to match your local MySQL setup.

4. **Run the application**
   - Compile all `.java` files in the `bankmanagment` package.
   - Run `Login.java` as the entry point (`main` method) to launch the application.

---

## 🚀 Future Enhancements

A few directions this project could be extended in:

- [ ] Convert to a **web application** (Spring Boot + HTML/CSS/JS or React) to enable a real, shareable **live deployment link**
- [ ] Add **password hashing / encryption** for PINs instead of storing them as plain text
- [ ] Add **transaction receipts** (printable/exportable as PDF)
- [ ] Add **fund transfer** between two accounts
- [ ] Add **admin dashboard** for bank staff (view all accounts, freeze/unfreeze accounts, audit logs)
- [ ] Add **email/SMS notifications** for every transaction
- [ ] Replace raw string-concatenated SQL queries with **PreparedStatement** to prevent SQL injection
- [ ] Add **unit tests** for core business logic (balance calculation, validation rules)
- [ ] Add **multi-currency support**

---

## 📌 Notes

- This is an academic/learning project intended to demonstrate core Java Swing GUI development and JDBC-based database integration.
- Since it's a desktop application (not a web app), it cannot be deployed with a browser-accessible "live link" without rewriting the frontend in a web technology stack.

---

## 📄 License

This project is created for academic purposes. Feel free to fork, learn from, and build upon it.
