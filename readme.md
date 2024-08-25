
# User Service

## Overview

The User Service is a robust Spring Boot application designed to manage user information efficiently. It employs an event-driven design pattern to enhance decoupling, scalability, and maintainability of components.

## Event-Driven Design Pattern

This service uses an event-driven architecture to enable asynchronous communication and decouple service components. Events are generated following the execution of commands and are handled independently by other parts of the application.

## Project Structure

- `api`: Core API interfaces.
- `command`: Classes encapsulating command details.
- `controller`: REST controllers managing HTTP requests.
- `event`: Event classes representing system actions.
- `executors`: Business logic processors for commands.
- `kafka`: Kafka message producers and consumers.
- `model`: Business entity data models.
- `query`: Data retrieval operations.
- `repo`: Database interaction.
- `service`: Business logic and service layer.

## Process Flow Diagram

```mermaid
graph LR
    L[UserQueryController] -->|Get User| M[GetUserByIdQuery]
    L -->|List Users| N[GetAllUsersQuery]
    
    M -->|Dispatch to Service| E[UserService]
    N -->|Dispatch to Service| E
    
    E -->|Executes Query| O[GetUserByIdQueryExecutor]
    E -->|Executes Query| P[GetAllUsersQueryExecutor]
    
    O -->|Fetch from| I[UserRepository]
    P -->|Fetch from| I
    
    A[UserCommandController] -->|Create| B[CreateUserCommand]
    A -->|Update| C[UpdateUserCommand]
    A -->|Delete| D[DeleteUserCommand]
    
    B -->|Dispatch to Service| E
    C -->|Dispatch to Service| E
    D -->|Dispatch to Service| E
    
    E -->|Executes| F[CreateUserCommandExecutor]
    E -->|Executes| G[UpdateUserCommandExecutor]
    E -->|Executes| H[DeleteUserCommandExecutor]
    
    F -->|Saves| I
    G -->|Updates| I
    H -->|Deletes| I
    H -->|Publish Event| J[KafkaProducer]
    J -->|Message on Topic| K[Kafka]
```

## Commands

### CreateUserCommand

- **Purpose**: Initiates the creation of a new user.
- **Details**: Carries user data required for creating a new user record.
- **Executor**: Handled by `CreateUserCommandExecutor`, which adds the user to the database.

```mermaid
classDiagram
    class CreateUserCommand {
        -String username
        -String emailId
        CreateUserCommand(String username, String emailId)
    }
```

### UpdateUserCommand

- **Purpose**: Updates an existing user's details.
- **Details**: Contains user ID and new data such as email or username.
- **Executor**: `UpdateUserCommandExecutor` updates the user's details in the database.

```mermaid
classDiagram
    class UpdateUserCommand {
        -Long userId
        -String username
        -String emailId
        UpdateUserCommand(Long userId, String username, String emailId)
    }
```

### DeleteUserCommand

- **Purpose**: Removes a user from the system.
- **Details**: Includes the ID of the user to be deleted.
- **Executor**: `DeleteUserCommandExecutor` removes the user from the database and triggers a `UserDeletedEvent`.

```mermaid
classDiagram
    class DeleteUserCommand {
        -Long userId
        DeleteUserCommand(Long userId)
    }
```

## Queries

### GetUserByIdQuery

- **Purpose**: Retrieves detailed information about a specific user.
- **Details**: Carries the unique ID of the user.
- **Executor**: `GetUserByIdQueryExecutor` fetches user details from the database.

```mermaid
classDiagram
    class GetUserByIdQuery {
        -Long userId
        +GetUserByIdQuery(Long userId)
    }
```

### GetAllUsersQuery

- **Purpose**: Retrieves a list of all users in the system.
- **Details**: Does not require specific input parameters.
- **Executor**: `GetAllUsersQueryExecutor` fetches all users from the database.

```mermaid
classDiagram
    class GetAllUsersQuery {
        +GetAllUsersQuery()
    }
```

## Events

### UserDeletedEvent

- **Triggered by**: Successful execution of `DeleteUserCommand`.
- **Purpose**: Indicates that a user has been removed from the system.

```mermaid
classDiagram
    class UserDeletedEvent {
        -Long userId
        UserDeletedEvent(Long userId)
    }
```

## Entity Details

The `User` entity represents a user in the system and is stored in the "users" table in the database. The entity is defined with annotations to enforce constraints like non-null values and uniqueness.

### User Entity Class Diagram

Here is the class diagram for the `User` entity, which includes the fields and their constraints:

```mermaid
classDiagram
    class User {
        -Long userId
        -String username
        -String emailId
        User(Long userId, String username, String emailId)
    }
```

## Getting Started

To run the service locally:

1. **Clone the repository**:
   ```bash
   git clone https://github.com/viswarajramji/user.git
   cd user
   ```

2. **Build the application**:
   ```bash
   ./mvnw clean install
   ```

3. **Start the application**:
   ```bash
   ./mvnw spring-boot:run
   ```

4. **Access the application** at `http://localhost:8080`.

5. **Access the database** at `http://localhost:8080/h2-console`.

**Note**: Ensure Kafka is running and the topic `userservice` is created.

## Swagger Endpoint

Access the Swagger UI to interact with the API:

- **URL**: `http://localhost:8080/swagger-ui.html`
