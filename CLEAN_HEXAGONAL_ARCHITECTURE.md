# Clean Hexagonal Architecture

This project demonstrates the implementation of Clean Architecture (also known as Hexagonal Architecture or Ports and Adapters) using Spring Boot for managing products.

## Architecture Overview

Clean Architecture organizes code into layers with clear dependency rules:
- **Dependencies point inward**: Outer layers depend on inner layers, never the reverse
- **Domain Layer**: Contains business logic and is independent of frameworks
- **Application Layer**: Contains use cases that orchestrate domain objects
- **Infrastructure Layer**: Contains adapters for external systems (Web, DB, etc.)

## Project Structure

```
src/main/java/com/example/product/
├── domain/                              # Domain Layer (Enterprise Business Rules)
│   └── model/
│       ├── Product.java                 # Product aggregate root
│       └── Money.java                   # Money value object
│
├── application/                         # Application Layer (Use Cases)
│   ├── dto/
│   │   ├── ProductCreateCommand.java    # Input DTO for creating products
│   │   └── ProductResponse.java         # Output DTO for product data
│   ├── port/
│   │   ├── in/
│   │   │   ├── CreateProductUseCase.java    # Input port (use case interface)
│   │   │   └── GetProductQuery.java         # Input port (query interface)
│   │   └── out/
│   │       └── ProductRepositoryPort.java   # Output port (repository interface)
│   └── service/
│       └── ProductService.java          # Use case implementation
│
└── infrastructure/                      # Infrastructure Layer (Adapters)
    ├── adapter/
    │   ├── in/
    │   │   └── web/
    │   │       └── ProductController.java   # REST API adapter (Input)
    │   └── out/
    │       └── persistence/
    │           ├── ProductJpaEntity.java    # JPA entity
    │           ├── ProductJpaRepository.java # Spring Data repository
    │           ├── ProductMapper.java        # Domain/Entity mapper
    │           └── ProductRepositoryAdapter.java # Repository adapter (Output)
    └── config/                          # Spring configuration (if needed)
```

## Layer Descriptions

### 1. Domain Layer
The innermost layer contains:
- **Aggregates**: Cluster of domain objects (e.g., `Product`)
- **Value Objects**: Immutable objects defined by their values (e.g., `Money`)
- **Domain Services**: Business logic that doesn't naturally fit in entities

**Key Principles:**
- No dependencies on outer layers
- No framework dependencies (no Spring, JPA annotations)
- Pure business logic

### 2. Application Layer
Contains application-specific business rules:
- **Use Cases**: Application services implementing business operations
- **Ports**: Interfaces defining contracts
  - **Input Ports**: Interfaces for use cases (e.g., `CreateProductUseCase`)
  - **Output Ports**: Interfaces for external dependencies (e.g., `ProductRepositoryPort`)
- **DTOs**: Data Transfer Objects for communication

**Key Principles:**
- Orchestrates domain objects to fulfill use cases
- Depends only on the domain layer
- Defines ports (interfaces) that infrastructure implements

### 3. Infrastructure Layer
The outermost layer contains:
- **Input Adapters**: Receive requests from external sources
  - REST Controllers, GraphQL resolvers, CLI, etc.
- **Output Adapters**: Implement output ports to interact with external systems
  - Database repositories, external APIs, message queues, etc.
- **Configuration**: Framework-specific configurations

**Key Principles:**
- Implements ports defined by application layer
- Contains all framework-specific code
- Depends on application and domain layers

## Dependency Rule

The Dependency Rule states that source code dependencies must point only inward:
- **Domain** ← **Application** ← **Infrastructure**

This is achieved through:
1. **Dependency Inversion Principle**: Application defines interfaces (ports), infrastructure implements them
2. **Dependency Injection**: Spring injects implementations at runtime

## Benefits

1. **Independence**: Business logic is independent of frameworks, UI, databases
2. **Testability**: Easy to test business rules without external dependencies
3. **Flexibility**: Easy to change infrastructure without affecting business logic
4. **Maintainability**: Clear separation of concerns makes code easier to understand and maintain

## Key Patterns Used

### Ports and Adapters
- **Ports**: Interfaces defining contracts (`CreateProductUseCase`, `ProductRepositoryPort`)
- **Adapters**: Implementations of ports (`ProductController`, `ProductRepositoryAdapter`)

### Dependency Inversion
- High-level modules (Application) don't depend on low-level modules (Infrastructure)
- Both depend on abstractions (Ports)

### Mappers
- Convert between layers to maintain separation
- `ProductMapper` converts between domain `Product` and JPA `ProductJpaEntity`

## Example Flow

### Creating a Product

1. **HTTP Request** arrives at `ProductController` (Infrastructure - Input Adapter)
2. **Controller** calls `CreateProductUseCase.createProduct()` (Application - Port)
3. **ProductService** (Application - Use Case Implementation):
   - Creates domain `Product` with `Money` value object
   - Calls `ProductRepositoryPort.save()` (Application - Port)
4. **ProductRepositoryAdapter** (Infrastructure - Output Adapter):
   - Converts domain `Product` to `ProductJpaEntity` using `ProductMapper`
   - Saves via `ProductJpaRepository` (Spring Data JPA)
   - Converts saved entity back to domain model
5. **ProductService** returns `ProductResponse` DTO
6. **Controller** returns HTTP response

## Running the Application

```bash
# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## API Endpoints

### Create a Product
```bash
POST /api/products
Content-Type: application/json

{
  "name": "Laptop",
  "description": "High-performance laptop",
  "price": 999.99,
  "currency": "USD"
}
```

### Get a Product
```bash
GET /api/products/{id}
```

## Database Console

H2 Console is available at: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:productdb`
- Username: `sa`
- Password: (empty)

## Testing

The architecture makes testing straightforward:
- **Unit Tests**: Test domain logic in isolation
- **Integration Tests**: Test use cases with mock repositories
- **End-to-End Tests**: Test complete flows through controllers

## Further Reading

- [Clean Architecture by Robert C. Martin](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
- [Hexagonal Architecture by Alistair Cockburn](https://alistair.cockburn.us/hexagonal-architecture/)
- [Ports and Adapters Pattern](https://softwarecampament.wordpress.com/portsadapters/)
