# 🛍️ Storefront

A modern, premium e-commerce platform built with Spring Boot and cutting-edge web technologies. Features a stunning glassmorphic UI with the vibrant Cyber Emerald color palette.

![Java](https://img.shields.io/badge/Java-17-orange?style=flat-square)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen?style=flat-square)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?style=flat-square)
![License](https://img.shields.io/badge/License-MIT-yellow?style=flat-square)

---

## ✨ Features

### 🎨 Premium UI/UX
- **Glassmorphic Design**: Stunning glass-panel effects with enhanced blur and glowing borders
- **Cyber Emerald Theme**: Vibrant emerald green (#14f195) and electric cyan (#00d9ff) color palette
- **Animated Backgrounds**: Geometric grid patterns and floating gradient orbs
- **Micro-animations**: Smooth transitions, hover effects, and fade-in animations
- **Responsive Layout**: Optimized for all screen sizes

### 🔐 Authentication & Authorization
- Secure user registration and login with BCrypt password encryption
- Role-based access control (Admin & User roles)
- Session management with Spring Security
- Duplicate email validation with user-friendly error messages

### 📊 Admin Dashboard
- Real-time statistics (Total Users, Products, Orders, Revenue)
- Revenue analytics with Chart.js line charts
- Product management (Create, Read, Update, Delete)
- Order tracking and management
- Search and filter functionality

### 🛒 User Features
- Product catalog with modern grid layout
- Order placement and tracking
- Personal dashboard with order statistics
- Chart.js doughnut charts for visual insights
- Wishlist management

### 🚀 Technical Highlights
- RESTful API architecture
- JPA/Hibernate for ORM
- Thymeleaf for server-side rendering
- MySQL database integration
- Swagger/OpenAPI documentation
- Comprehensive error handling

---

## 📸 Screenshots

### Home Page
![Home Page](screenshots/home.png)

*Modern hero section with feature cards showcasing Lightning Fast, Secure & Safe, Smart Analytics, and Global Reach*

### Signup Page
![Signup Page](screenshots/signup.png)

*"Join Storefront" card with two-column layout for efficient registration*

### Admin Dashboard
![Admin Dashboard](screenshots/admin-dashboard.png)

*Real-time statistics and revenue analytics with Chart.js visualizations*

### Product Management
![Product Management](screenshots/admin-products.png)

*Comprehensive product CRUD interface with search functionality*

---

## 🛠️ Tech Stack

### Backend
- **Java 17**
- **Spring Boot 3.2.0**
  - Spring Web
  - Spring Security
  - Spring Data JPA
- **MySQL 8.0**
- **Hibernate ORM**

### Frontend
- **Thymeleaf** (Server-side templating)
- **HTML5 & CSS3**
- **JavaScript**
- **Chart.js** (Data visualization)
- **Custom CSS** (Glassmorphism & animations)

### Tools & Libraries
- **Maven** (Build automation)
- **Springdoc OpenAPI** (API documentation)
- **BCrypt** (Password encryption)
- **Lombok** (Boilerplate reduction)

---

## 🚀 Getting Started

### Prerequisites
- Java 17 or higher
- MySQL 8.0 or higher
- Maven 3.6+

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/storefront.git
   cd storefront
   ```

2. **Configure MySQL Database**
   
   Create a database named `admin`:
   ```sql
   CREATE DATABASE admin;
   ```

3. **Update `application.properties`**
   
   Edit `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/admin
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

4. **Build the project**
   ```bash
   mvn clean install
   ```

5. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

6. **Access the application**
   
   Open your browser and navigate to: `http://localhost:8796`

---

## 📁 Project Structure

```
Product_Management_API/
├── src/
│   ├── main/
│   │   ├── java/com/product/
│   │   │   ├── config/          # Security & configuration
│   │   │   ├── model/           # JPA entities
│   │   │   ├── repository/      # Data access layer
│   │   │   ├── service/         # Business logic
│   │   │   └── web/
│   │   │       ├── api/         # REST controllers
│   │   │       ├── controller/  # MVC controllers
│   │   │       └── exception/   # Error handling
│   │   └── resources/
│   │       ├── static/css/      # Stylesheets
│   │       └── templates/       # Thymeleaf templates
│   └── test/                    # Unit tests
├── pom.xml                      # Maven dependencies
└── README.md
```

---

## 🎨 Design System

### Color Palette (Cyber Emerald)
- **Primary**: `#14f195` (Bright Emerald)
- **Secondary**: `#00d9ff` (Electric Cyan)
- **Background**: `#0a0f1a` (Deep Navy)
- **Surface**: `rgba(10, 25, 40, 0.85)` (Glassmorphic)

### Typography
- **Font Family**: Plus Jakarta Sans
- **Weights**: 400, 500, 600, 700, 800

### Effects
- **Glassmorphism**: `backdrop-filter: blur(20px)`
- **Borders**: 2px solid with emerald tint
- **Shadows**: Multi-layer with emerald glow
- **Animations**: Fade-in, float, shimmer

---

## 📝 API Documentation

Once the application is running, access the Swagger UI at:
```
http://localhost:8796/swagger-ui.html
```

---

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## 👨‍💻 Author

**Your Name**
- GitHub: [@yourusername](https://github.com/yash-kumarsharma))


---

**Built with ❤️ using Spring Boot & Modern Web Technologies**
