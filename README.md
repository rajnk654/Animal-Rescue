# 🐾 Animal Rescue and Adoption – Backend

Welcome to the backend of the **Animal Rescue and Adoption Web Application**! This Spring Boot project powers secure, scalable APIs for user management, pet listings, adoptions, foster care, and payment processing.

---

## 📝 Project Description

The **Animal Rescue and Adoption Web Application (Backend)** is designed to streamline and digitalize the entire process of animal welfare, from rescuing stray animals to facilitating their adoption or foster care. Built with a modern tech stack, this backend serves as the core for managing users, pets, and organizational operations—ensuring transparency, efficiency, and security.

### Key Objectives

- **Centralized Animal Records:** Maintain comprehensive profiles for each animal, including health status, vaccination records, rescue details, and adoption/foster history.
- **Efficient Adoption Workflow:** Facilitate quick and reliable application, review, and approval processes for potential adopters, minimizing the time animals spend in shelters.
- **Empowering Rescuers and Volunteers:** Provide tools for authorized users to update animal statuses, assign foster care, and track ongoing cases.
- **Community Engagement:** Support donors and volunteers by making it easy to contribute funds or participate in animal welfare activities through secure payment processing and user-friendly APIs.
- **Safety and Compliance:** Employ robust authentication and access control to protect sensitive information and ensure that only authorized personnel can perform critical operations.

### How It Works

- **User Roles & Security:** The system supports multiple user roles—Adopters, Rescuers, and Admins—each with specific permissions managed through secure JWT tokens and Spring Security.
- **Pet Listings:** Animals available for adoption or fostering are listed with detailed filters, making it easy for users to find pets that match their needs and preferences.
- **Adoption & Foster Management:** Applicants can submit forms, track their application status, and schedule interviews. Admins and rescuers can manage, approve, or reject these processes efficiently.
- **Integrated Payments:** The backend supports online payments for adoption fees and donations, with transaction history tracking to ensure transparency for donors and administrators.
- **REST API:** The backend follows RESTful practices, with clear endpoints, request/response validation, and standardized error messages—making it easy to build robust frontend or mobile apps on top.

### Who Can Use This System?

- **Animal Shelters & NGOs** seeking to modernize their operations
- **Rescue Volunteers** who want to coordinate efforts more effectively
- **Potential Adopters and Foster Families** looking for a streamlined way to offer homes to animals
- **Donors** who want a transparent and secure way to support animal welfare

### Extensibility

The backend is designed with scalability and future enhancements in mind—supporting custom workflows, external integrations (e.g., SMS/email notifications), and easy onboarding of new features as the organization grows.

---

## 🚀 Features

- **Role-Based Access Control (RBAC):** Adopters, Rescuers, and Admins
- **Pet Listings API:** Advanced search & filtering
- **Adoption Management:** Application submission, tracking, and interview scheduling
- **Foster Care Management:** Track and update animal status
- **Secure Payments:** Integration with Stripe or Razorpay for adoption fees and donations
- **RESTful API:** Robust validation and error handling
- **Scalable Architecture:** Built with MVC principles
- **JWT Authentication:** Secure login and access control

---

## 🛠 Tech Stack

- **Backend:** Spring Boot
- **Database:** PostgreSQL
- **ORM:** Hibernate / JPA
- **Authentication:** Spring Security with JWT
- **Payment Integration:** Stripe / Razorpay (as implemented)
- **Build Tool:** Maven
- **API Documentation:** Swagger (optional)

---

## 📂 Project Structure

```
backend/
 ├── src/main/java/com/example/animalrescue/
 │    ├── controller/         # REST API Controllers
 │    ├── model/              # Entity Models
 │    ├── repository/         # JPA Repositories
 │    ├── service/            # Business Logic
 │    ├── config/             # Security & Configurations
 │    └── dto/                # Data Transfer Objects
 ├── src/main/resources/
 │    ├── application.properties
 │    └── static/
 └── pom.xml
```

---

## ⚙️ Setup Instructions

### 1️⃣ Prerequisites

- Java 17+
- Maven
- PostgreSQL (running and accessible)
- Git

### 2️⃣ Clone the Repository

```bash
git clone <your-repository-url>
cd backend
git checkout master
```

### 3️⃣ Configure the Database

Edit `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/animalrescue
spring.datasource.username=postgres
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Payment Gateway Keys
payment.key=your_payment_gateway_key
payment.secret=your_payment_gateway_secret
```

### 4️⃣ Build and Run

```bash
# Build the backend
mvn clean install

# Run the backend
mvn spring-boot:run
```

Server starts at: [http://localhost:8080](http://localhost:8080)

---

## 📡 API Documentation

If Swagger is enabled, visit: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

## 🔗 API Endpoints

### **Authentication**

| Method | Endpoint              | Description                     |
|--------|----------------------|---------------------------------|
| POST   | `/api/auth/register` | Register a new user             |
| POST   | `/api/auth/login`    | Authenticate & return JWT token |

### **Pet Management**

| Method | Endpoint           | Description                       |
|--------|-------------------|-----------------------------------|
| GET    | `/api/pets`            | Get all pets                    |
| GET    | `/api/pets/{id}`       | Get pet details by ID           |
| POST   | `/api/pets`            | Add a new pet (Admin/Rescuer)   |
| PUT    | `/api/pets/{id}`       | Update pet details              |
| DELETE | `/api/pets/{id}`       | Delete a pet record             |

### **Adoption**

| Method | Endpoint                   | Description                          |
|--------|----------------------------|--------------------------------------|
| POST   | `/api/adoptions/apply`     | Apply for pet adoption               |
| GET    | `/api/adoptions/status`    | Get adoption application status      |
| PUT    | `/api/adoptions/{id}`      | Update adoption status (Admin/Rescuer)|

### **Foster Care**

| Method | Endpoint             | Description                            |
|--------|---------------------|----------------------------------------|
| GET    | `/api/foster`       | Get all foster care assignments        |
| POST   | `/api/foster`       | Assign a pet to foster care            |
| PUT    | `/api/foster/{id}`  | Update foster care details             |

### **Payments**

| Method | Endpoint                  | Description                      |
|--------|--------------------------|----------------------------------|
| POST   | `/api/payments/initiate` | Initiate payment for adoption fee|
| POST   | `/api/payments/donation` | Make a donation                  |
| GET    | `/api/payments/history`  | View payment history             |

---

## 🙌 Contributing

Contributions are welcome! Please fork the repository, create a new branch, and submit a pull request.

---

## 📝 License

This project is licensed under the MIT License.

---

## 📬 Contact

For questions or support, open an issue or contact the project maintainer.

---
