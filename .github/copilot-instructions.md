# Copilot instructions for this repository

## Build, test, and lint commands

This repository is **not** a single Maven multi-module build: `bike`, `cars`, and `garage` are three independent Spring Boot projects, each with its own `pom.xml`.

| Service | Build | Test suite | Run one test class | Run one test method |
|---|---|---|---|---|
| `bike` | `cd bike && mvn clean package` | `cd bike && mvn test` | `cd bike && mvn -Dtest=BikeApplicationTests test` | `cd bike && mvn -Dtest=BikeApplicationTests#contextLoads test` |
| `cars` | `cd cars && .\mvnw.cmd clean package` | `cd cars && .\mvnw.cmd test` | `cd cars && .\mvnw.cmd -Dtest=CarsApplicationTests test` | `cd cars && .\mvnw.cmd -Dtest=CarsApplicationTests#contextLoads test` |
| `garage` | `cd garage && .\mvnw.cmd clean package` | `cd garage && .\mvnw.cmd test` | `cd garage && .\mvnw.cmd -Dtest=GarageApplicationTests test` | `cd garage && .\mvnw.cmd -Dtest=GarageApplicationTests#contextLoads test` |

Linting is not configured via Maven plugins or separate lint scripts in this repository.

## High-level architecture

- The system is split into three services with separate persistence:
  - `bike` (port `8081`, DB `bikesdb`)
  - `cars` (port `8082`, DB `autodb`)
  - `garage` (port `8083`, DB `garagedb`)
- Architecture follows **database-per-service**: each service owns its own schema/tables.
- `garage` orchestrates cross-service behavior:
  - It stores `Garage` and `Vehicle` entities locally.
  - `Vehicle` stores `vehicleType` (`BIKE`/`CAR`) and `externalId`.
  - Before accepting a vehicle, `garage` validates that referenced vehicle IDs exist by calling:
    - `bike`: `GET /api/bikes/{id}`
    - `cars`: `GET /api/cars/{id}`
  - Cross-service HTTP clients are configured as named `RestClient` beans (`bikesClient`, `carsClient`).

## Key conventions in this codebase

- Each service generally uses the same vertical slice shape:
  - `Controller` → `Service` → `Repository` (`JpaRepository`)
  - DTO classes for input/output
  - Explicit mapper class (e.g., `BikeMapper`, `CarsMapper`, `GarageMapper`) instead of MapStruct-generated mappers.
- Controllers expose REST resources under:
  - `/api/bikes`
  - `/api/cars`
  - `/api/garages`
- CRUD endpoints return `ResponseEntity` and usually map `Optional` to `404` at controller level.
- Lombok is used heavily for entities/DTOs (`@Data`, `@Builder`, etc.); favor existing Lombok patterns when extending models.
- JPA validation annotations are used directly on entities and (in several cases) also on DTOs.
- `bike` test setup includes a `TestcontainersConfiguration` class wired via `@Import` in `BikeApplicationTests`; `cars` and `garage` currently keep only minimal `@SpringBootTest` context-load tests.
- Configuration keys for service URLs must be kept aligned between `application.properties` and `@Value(...)` usage in `garage` (`RestClientConfig` and `GarageService`) when changing inter-service wiring.
