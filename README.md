# Hotel Management System

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

4. **Run the Application**
   - Use your IDE or command line to build and run the application.

## Documentation for Each Resource
API documentation will be available on Swagger once the application is running. Visit `http://localhost:8080/swagger-ui.html` to explore the API endpoints and test them directly from the interface.

### ER Digrame:

![web_ER drawio](https://github.com/ZaidZitawi/HotelManagment/assets/132776309/9e22c437-aaea-4b54-8c91-60f0e2423309)

### link to the Dockerimage:
https://hub.docker.com/repository/docker/zaidzitawi/hotelmanagment/general


