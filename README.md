## Project Overview

The Hotel Management System is designed to streamline and automate the various processes involved in running a hotel. It offers functionalities for managing guests, employees, rooms, reservations, billing, and housekeeping tasks. This system helps ensure efficient operations and enhanced guest experiences.

## Features

- **Guest Management**: Register, login, update profiles, and change passwords for guests.
- **Employee Management**: Add, update, retrieve, and delete employee records.
- **Room Management**: Add, update, retrieve, delete rooms, and check availability.
- **Billing Management**: Generate invoices and retrieve billing details.
- **Reservation Management**: Create, update, retrieve, and cancel reservations.
- **Housekeeping Management**: Assign and update housekeeping tasks.
- **Search Functionality**: Search for reservations and available rooms.
- **Check-In/Check-Out**: Manage guest check-ins and check-outs.

## API Endpoints

### Guest Management

| HTTP Method | Endpoint                      | Description                       |
|-------------|-------------------------------|-----------------------------------|
| POST        | /api/guests/register          | Register a new guest.             |
| POST        | /api/guests/login             | Guest login.                      |
| GET         | /api/guests/{id}              | Retrieve a guest's profile.       |
| PUT         | /api/guests/{id}              | Update a guest's profile.         |
| POST        | /api/guests/change-password   | Change a guest's password.        |

### Employee Management

| HTTP Method | Endpoint                      | Description                       |
|-------------|-------------------------------|-----------------------------------|
| POST        | /api/employees/               | Add a new employee.               |
| GET         | /api/employees/{id}           | Get details of a specific employee.|
| PUT         | /api/employees/{id}           | Update employee information.      |
| DELETE      | /api/employees/{id}           | Remove an employee.               |

### Room Management

| HTTP Method | Endpoint                      | Description                       |
|-------------|-------------------------------|-----------------------------------|
| POST        | /api/rooms/                   | Add new room.                     |
| GET         | /api/rooms/{id}               | Retrieve room details.            |
| PUT         | /api/rooms/{id}               | Update room details.              |
| DELETE      | /api/rooms/{id}               | Delete a room.                    |
| GET         | /api/rooms/availability       | Check room availability.          |

### Billing Management

| HTTP Method | Endpoint                      | Description                       |
|-------------|-------------------------------|-----------------------------------|
| GET         | /api/billing/{reservationId}  | Get billing details for a reservation. |
| POST        | /api/billing/                 | Generate an invoice.              |

### Reservation Management

| HTTP Method | Endpoint                      | Description                       |
|-------------|-------------------------------|-----------------------------------|
| POST        | /api/reservations/            | Create a new reservation.         |
| GET         | /api/reservations/{id}        | Get details of a specific reservation.|
| PUT         | /api/reservations/{id}        | Update reservation details.       |
| DELETE      | /api/reservations/{id}        | Cancel a reservation (requires admin approval).|

### Housekeeping Management

| HTTP Method | Endpoint                      | Description                       |
|-------------|-------------------------------|-----------------------------------|
| GET         | /api/housekeeping/            | List housekeeping tasks.          |
| POST        | /api/housekeeping/            | Assign a new housekeeping task.   |
| PUT         | /api/housekeeping/{taskId}    | Update a housekeeping task.       |

### Search Functionality

| HTTP Method | Endpoint                      | Description                       |
|-------------|-------------------------------|-----------------------------------|
| GET         | /api/search/reservations      | Search reservations by guest name, ID, and date. |
| GET         | /api/search/rooms             | Search available rooms with details.|

### Check-In/Check-Out

| HTTP Method | Endpoint                      | Description                       |
|-------------|-------------------------------|-----------------------------------|
| POST        | /api/check-in/{reservationId} | Check-in a guest.                 |
| POST        | /api/check-out/{reservationId}| Check-out a guest.                |

## Database Schema

The system's database is structured to manage the various aspects of hotel operations. Here is a summary of the core tables:

1. **Guests**
   - Stores guest information including contact details and credentials.

2. **Employees**
   - Contains employee records with personal and professional details.

3. **Rooms**
   - Manages room details such as type, availability, and pricing.

4. **Reservations**
   - Tracks reservation details including guest, room, and dates.

5. **Billing**
   - Manages billing information related to reservations.

6. **Housekeeping**
   - Tracks housekeeping tasks and assignments.

### Sample Data For DB Schema

#### Guests Table

| guestId | firstName | lastName | emailAddress     | phoneNumber   | password          |
|---------|-----------|----------|------------------|---------------|-------------------|
| 1       | John      | Doe      | john.doe@mail.com| 123-456-7890  | encrypted_password|

#### Employees Table

| employeeId | firstName | lastName | emailAddress       | phoneNumber   | role     |
|------------|-----------|----------|--------------------|---------------|----------|
| 1          | Alice     | Smith    | alice.smith@mail.com| 987-654-3210  | Manager  |

#### Rooms Table

| roomId | roomNumber | type    | price | availability |
|--------|------------|---------|-------|--------------|
| 1      | 101        | Single  | 100   | true         |

#### Reservations Table

| reservationId | guestId | roomId | checkInDate | checkOutDate | status   |
|---------------|---------|--------|-------------|--------------|----------|
| 1             | 1       | 1      | 2024-06-01  | 2024-06-07   | Confirmed|

#### Billing Table

| billingId | reservationId | amount | invoiceDate  |
|-----------|----------------|--------|--------------|
| 1         | 1              | 700    | 2024-06-01   |

#### Housekeeping Table

| taskId | roomId | taskDescription | assignedTo | status   |
|--------|--------|-----------------|------------|----------|
| 1      | 1      | Clean bathroom  | 2          | Pending  |

## Getting Started

To set up and run the application, follow these steps:

1. **Clone the Repository**
   - `git clone <repository-url>`
   
2. **Database Setup**
   - Ensure you have a SQL database system (e.g., MySQL) installed and running.
   - Execute the SQL scripts provided in the `SQL/` directory to create the database and tables.
   - Load the sample data if provided to get started quickly.

3. **Application Configuration**
   - Update the `application.properties` file with your database connection details.

4. **Build and Package the Application**
   - Use Maven to clean and build the project which will generate the JAR file:
     ```sh
     mvn clean install
     ```

5. **Docker Setup**
   - Ensure Docker is installed and running on your system.

6. **Build the Docker Image**
   - Create a Docker image using the Dockerfile provided:
     ```sh
     docker build -t zaidzitawi/hotelmanagment:tag .
     ```

7. **Push the Docker Image to DockerHub**
   - Log in to DockerHub:
     ```sh
     docker login
     ```
   - Push the Docker image to your DockerHub repository:
     ```sh
     docker push zaidzitawi/hotelmanagment:tag
     ```

8. **Run the Application using Docker Compose**
   - Create a `docker-compose.yaml` file with the following content:
     ```yaml
     version: '3.8'

     services:
       app:
         image: zaidzitawi/hotelmanagment:tag
         container_name: app-container
         ports:
           - "8080:8080"
         environment:
           SPRING_DATASOURCE_URL: jdbc:mysql://db:3306/hm
           SPRING_DATASOURCE_USERNAME: zaidzitawi
           SPRING_DATASOURCE_PASSWORD: 0568718460zaid
           SPRING_DATASOURCE_DRIVER_CLASS_NAME: com.mysql.cj.jdbc.Driver
         depends_on:
           - db

       db:
         image: mysql:8
         container_name: db-container
         environment:
           MYSQL_DATABASE: hm
           MYSQL_USER: zaidzitawi
           MYSQL_PASSWORD: 0568718460zaid
         ports:
           - "3307:3306"
     ```

9. **Start the Application**
   - Run the following command to start the application using Docker Compose:
     ```sh
     docker-compose up
     ```

10. **Access the Application**
    - Open your web browser and navigate to `http://localhost:8080` to access the application.

## Documentation for Each Resource

API documentation will be available on Swagger once the application is running. Visit `[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)` to explore the API endpoints and test them directly from the interface.

### ER Diagram

![web_ER drawio](https://github.com/ZaidZitawi/HotelManagment/assets/132776309/9e22c437-aaea-4b54-8c91-60f0e2423309)

### Link to the Docker Image

[Hotel Management System Docker Image](https://hub.docker.com/repository/docker/zaidzitawi/hotelmanagment/general)

## Postman Collection of APIs

[Hotel Management System.postman_collection.json](https://github.com/user-attachments/files/15930026/Hotel.Managment.System.postman_collection.json)


## What I Learned from the Project

Throughout the development of the Hotel Management System, I gained valuable experience in several areas:

- **RESTful API Development**: Implementing various API endpoints and ensuring they follow REST principles.
- **Spring Boot**: Utilizing Spring Boot to quickly set up and develop the backend application.
- **Database Management**: Designing and interacting with a relational database, understanding relationships, and writing efficient queries.
- **Docker**: Creating Docker images and using Docker Compose to manage multi-container applications.
- **Security**: Implementing JWT-based authentication and authorization to secure the APIs.
- **Project Management**: Collaborating with team members, managing tasks, and ensuring timely completion of project milestones.
- **Error Handling**: Learning to implement proper exception handling to ensure robustness and reliability.
- **API Documentation**: Using OAS 3.1.0 to document APIs, which helps in maintaining clear and accessible API documentation.
- **Continuous Integration and Deployment (CI/CD)**: Understanding the importance of CI/CD pipelines to automate the building, testing, and deployment processes.
- **Version Control**: Using Git and GitHub effectively for version control and collaboration, ensuring a smooth workflow and history tracking.
- **Best Practices**: Applying software engineering best practices, including code readability, maintainability, and scalability.
- **User Experience Considerations**: Understanding the importance of user roles and permissions in creating a secure and user-friendly application.
- **Problem-Solving**: Enhancing my problem-solving skills by debugging and resolving issues encountered during the development process.
