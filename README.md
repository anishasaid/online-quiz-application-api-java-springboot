# Quiz Management API 🎯

A Spring Boot based Quiz Management System where you can:
- Create quizzes
- Add questions and options
- Submit answers
- Get scoring based on correct/incorrect answers

This project demonstrates **REST API development** with **Spring Boot, JPA, MySQL** and testing using **Postman**.

---

## 🚀 Features
- Create a quiz
- Add questions to a quiz (Single choice, Multiple choice, Text based)
- Fetch all quizzes
- Fetch questions of a quiz
- Submit quiz answers and calculate score

---

## 🛠️ Tech Stack
- **Java 17+**
- **Spring Boot 3+**
- **Spring Data JPA**
- **MySQL**
- **Postman (for testing APIs)**

---

## ⚙️ Setup Instructions

### 1️⃣ Clone the Repository
```bash
git clone https://github.com/<anishasaid>/quiz-api.git
cd quiz-api
```

### 2️⃣ Configure Database
Create a database in MySQL:
```sql
CREATE DATABASE quizdb;
```

Update your application.properties (or application.yml) with your DB credentials:
```sql
spring.datasource.url=jdbc:mysql://localhost:3306/quizdb
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 3️⃣ Build and Run
```bash
./mvnw spring-boot:run
```
Application will start at:
👉 http://localhost:8080

---

## 📌 API Endpoints

Create Quiz
```http
POST /api/quizzes
```

Request body
```json
{
  "title": "Java Basics Quiz"
}
```

Add Question to Quiz
```http
POST /api/quizzes/{quizId}/questions
```

Request body
```json
{
  "text": "Which of these is not a primitive type?",
  "type": "SINGLE_CHOICE",
  "options": [
    {"text": "int", "correct": false},
    {"text": "String", "correct": true},
    {"text": "float", "correct": false},
    {"text": "boolean", "correct": false}
  ]
}
```

Fetch All Quizzes
```http
GET /api/quizzes
```

Fetch Questions of a Quiz
```http
GET /api/quizzes/{quizId}/questions
```

Submit Quiz
```http
POST /api/quizzes/{quizId}/submissions
```

Request body
```json
{
  "answers": [
    { "questionId": 2, "selectedOptionIds": [2] },
    { "questionId": 3, "selectedOptionIds": [6] },
    { "questionId": 4, "selectedOptionIds": [11] }
  ]
}
```

Response
```json
{
  "score": 3,
  "total": 3
}
```

---

## ✅ Testing with Postman

- Import the endpoints into Postman.
- Start your Spring Boot app.
- Run requests step by step :
  Create quiz → Add questions → Fetch quizzes → Fetch questions → Submit answers.

---

## 📂 Project Structure
```bash
src/main/java/com/example/quiz
 ┣ controller/        # REST Controllers
 ┣ service/           # Business logic
 ┣ entity/            # JPA Entities
 ┣ repository/        # Spring Data JPA Repos
 ┣ dto/               # DTOs (Request/Response)
 ┗ QuizApplication.java
```

---














