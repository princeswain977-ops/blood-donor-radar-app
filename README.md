# Blood Donor Radar

## Project Description

Blood Donor Radar is a web application that helps manage blood donors and find donors by blood group and city. Users log in to add, edit, search, and delete donors, and they can export the donor list as a CSV file. The application is built with Spring Boot, JSP/JSTL, Hibernate (JPA), and MySQL.

## Technologies and Tools

- Java 21 (JDK) and Java 17 language level
- Spring Boot 3.2.5 (Spring MVC, Spring Data JPA, Spring Security Crypto for password hashing)
- JSP and JSTL (Jakarta Tags) for the user interface
- Hibernate ORM 6 (through Spring Data JPA)
- MySQL / MariaDB database (XAMPP for local use, Railway MySQL for deployment)
- Apache Tomcat (embedded, version 10.1)
- Lombok
- Maven (build tool)
- Docker (deployment)
- Apache NetBeans IDE 25, phpMyAdmin, GitHub, Railway

## Features and Functionalities

- Secure login and logout with BCrypt-hashed passwords
- Session handling with a 30-minute timeout and access protection for all pages
- Dashboard showing the total number of donors
- Add, view, edit, and delete donor records (CRUD)
- Client-side and server-side form validation with error messages
- Search donors by blood group and city (radar search)
- Export the donor list to a CSV file
- Delete protection: donors with donation records cannot be deleted

## Project Structure

```
blood-donor-radar/
├── Dockerfile
├── pom.xml
├── README.md
├── .gitignore
├── database/
│   └── blood_donor_radar.sql
└── src/
    ├── main/
    │   ├── java/com/marwadi/bdr/
    │   │   ├── entity/        (Donor, BloodBank, DonationCamp, DonationRecord, AppUser)
    │   │   ├── repository/    (DonorRepository, BloodBankRepository, AppUserRepository)
    │   │   ├── service/       (DonorService, AuthService)
    │   │   ├── web/           (AuthController, DashboardController, DonorController)
    │   │   ├── web/form/      (DonorForm)
    │   │   └── web/servlet/   (AuthFilter, DonorExportServlet, ServletConfig, SessionCounterListener)
    │   ├── resources/
    │   │   └── application.properties
    │   └── webapp/
    │       ├── css/style.css
    │       ├── js/validate.js
    │       └── WEB-INF/jsp/   (login, dashboard, donor-list, donor-form, search, common/header and footer)
    └── test/
```

## Installation and Execution (Local)

### Requirements
- JDK 21 installed
- Apache NetBeans IDE 25 (or any Maven-compatible IDE)
- XAMPP with MySQL started
- Maven (bundled with NetBeans)

### Steps
1. Start **Apache** and **MySQL** in the XAMPP Control Panel.
2. Set up the database (see the Database Setup section below).
3. Clone or download this repository and open the `blood-donor-radar` folder in NetBeans.
4. In NetBeans, set the project's Java Platform to JDK 21 (Project Properties → Build → Compiling).
5. Right-click the project and choose **Clean and Build**.
6. Open `src/main/java/com/marwadi/bdr/BloodDonorRadarApplication.java` and choose **Run File**.
7. Wait for the message `Started BloodDonorRadarApplication` in the Output window.
8. Open **http://localhost:8080/login** in your browser.

Default login: username **admin**, password **admin123**.

## Database Setup

1. Open **phpMyAdmin** at **http://localhost/phpmyadmin**.
2. Click **Import**, choose the file `database/blood_donor_radar.sql`, and click **Go**.
   (This creates the `blood_donor_radar` database with its tables and sample data.)

The application also creates missing tables automatically on startup (`spring.jpa.hibernate.ddl-auto=update`).

Local connection settings (defaults in `application.properties`):
- URL: `jdbc:mysql://localhost:3306/blood_donor_radar`
- Username: `root`
- Password: (empty)

For deployment, the database settings are read from environment variables: `DB_URL`, `DB_USERNAME`, and `DB_PASSWORD`.

## Screenshots

Screenshots are stored in the `screenshots/` folder.

## Live Application

Deployed URL: https://blood-donor-radar-app-production.up.railway.app

Login: admin / admin123

## Author

[Your Name] - [Your Enrollment No.]