# 📔 JournalApp

### A secure RESTful Journal Management Backend built with Spring Boot and MongoDB

![Java](https://img.shields.io/badge/Java-ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F.svg?style=for-the-badge&logo=springboot&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring%20Security-6DB33F.svg?style=for-the-badge&logo=springsecurity&logoColor=white)
![MongoDB](https://img.shields.io/badge/MongoDB-4EA94B.svg?style=for-the-badge&logo=mongodb&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36.svg?style=for-the-badge&logo=apachemaven&logoColor=white)

<br>

![Last Commit](https://img.shields.io/github/last-commit/Divyansh0702/JournalApp?style=flat-square)
![Repo Size](https://img.shields.io/github/repo-size/Divyansh0702/JournalApp?style=flat-square)
![License](https://img.shields.io/github/license/Divyansh0702/JournalApp?style=flat-square)

</div>

---

## 📖 About the Project

**JournalApp** is a backend REST API built using **Spring Boot** that allows users to create and manage personal journal entries securely.

The project started as a simple CRUD application and gradually evolved into a structured backend system with:

- User management
- MongoDB persistence
- User-specific journal entries
- Spring Security authentication
- BCrypt password hashing
- Role-based authorization
- Separate public, user, journal, and admin APIs

Each authenticated user can access and manage only their own journal entries.

---

## ✨ Features

- 👤 User registration and management
- 🔐 Authentication using Spring Security
- 🔑 Secure password hashing using BCrypt
- 📔 Create journal entries
- 📖 Fetch journal entries
- ✏️ Update journal entries
- 🗑️ Delete journal entries
- 🔒 User-specific journal access
- 👮 Role-based access with `USER` and `ADMIN` roles
- 🛡️ Protected API endpoints
- 🌐 Public endpoint for user registration
- 🗄️ MongoDB database integration
- 🔗 User-to-JournalEntry relationship using `@DBRef`
- 📦 Layered architecture

---

## 🏗️ Architecture

The application follows a layered architecture:

```text
Client / Postman
       │
       ▼
┌─────────────────┐
│   Controller    │
│   REST APIs     │
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│     Service     │
│ Business Logic  │
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│   Repository    │
│  Data Access    │
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│     MongoDB     │
└─────────────────┘
