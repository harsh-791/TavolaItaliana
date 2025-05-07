# Chef and Dish Management System

A Spring Boot application for managing chefs and their dishes, allowing users to create, read, and update information about chefs and their culinary creations.

## Features

- **Chef Management**

  - Create new chef profiles
  - View list of all chefs
  - Update chef information
  - View chef details including their dishes

- **Dish Management**
  - Add new dishes
  - View all dishes
  - Update dish information
  - Associate dishes with chefs

## Technical Stack

- **Backend**

  - Spring Boot 3.4.5
  - Spring Data JPA
  - Spring MVC
  - MySQL Database

- **Frontend**

  - JSP (JavaServer Pages)
  - Bootstrap 5
  - JSTL
  - CSS

- **Testing**
  - JUnit 5
  - Mockito

## Prerequisites

- Java 17 or higher
- MySQL 8.0 or higher
- Maven 3.6 or higher

## Database Setup

1. Create a MySQL database:

```sql
CREATE DATABASE chefapp;
```

2. Create a user and grant privileges:

```sql
CREATE USER 'chefapp_user'@'localhost' IDENTIFIED BY 'chefapp_password';
GRANT ALL PRIVILEGES ON chefapp.* TO 'chefapp_user'@'localhost';
FLUSH PRIVILEGES;
```

## Configuration

The application uses the following default configuration in `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/chefapp
spring.datasource.username=chefapp_user
spring.datasource.password=chefapp_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

## Building and Running

1. Clone the repository:

```bash
git clone [repository-url]
cd chefapp
```

2. Build the project:

```bash
mvn clean install
```

3. Run the application:

```bash
mvn spring-boot:run
```

The application will be available at `http://localhost:8080`

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── example/
│   │           └── chefapp/
│   │               ├── controller/
│   │               ├── model/
│   │               ├── repository/
│   │               ├── service/
│   │               └── ChefappApplication.java
│   └── resources/
│       ├── static/
│       ├── templates/
│       └── application.properties
└── test/
    └── java/
        └── com/
            └── example/
                └── chefapp/
                    ├── service/
                    └── repository/
```

## API Endpoints

### Chef Endpoints

- `GET /chefs` - List all chefs
- `GET /chefs/new` - Show form for new chef
- `POST /chefs` - Create new chef
- `GET /chefs/{id}/edit` - Show form for editing chef
- `POST /chefs/{id}` - Update chef

### Dish Endpoints

- `GET /dishes` - List all dishes
- `GET /dishes/new` - Show form for new dish
- `POST /dishes` - Create new dish
- `GET /dishes/{id}/edit` - Show form for editing dish
- `POST /dishes/{id}` - Update dish

## Testing

Run the test suite:

```bash
mvn test
```

