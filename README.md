# Inventory Service

A deliberately small Spring Boot service, used to exercise App Modernization
ingestion and analysis end to end (GAP-1304).

It is real code rather than filler: `ItemController` calls `ItemService`, which
calls `ItemRepository` and `PriceCalculator`, so the code graph has genuine
cross-file edges to find. `schema.sql` gives the database analysis agent two
tables, a foreign key and two indexes to read.

## Layout

| Path | What it is |
|---|---|
| `src/main/java/.../controller` | REST surface, five endpoints |
| `src/main/java/.../service` | Inventory operations and a domain exception |
| `src/main/java/.../repository` | Spring Data JPA, including one `@Query` |
| `src/main/java/.../model` | `Item` and `Category`, one-to-many |
| `src/main/java/.../util` | `PriceCalculator` — bulk discount and stock value |
| `src/main/resources/schema.sql` | Two tables, one FK, two indexes |
| `src/test/java/...` | Three unit tests covering the pricing boundary |

## Running

```sh
mvn spring-boot:run     # http://localhost:8080/api/items
mvn test
```

## Note for reviewers

The companion documentation set is **not** in this repository. It is uploaded
separately as a file group, so that repo ingestion and document ingestion can be
tested independently. See the manifest in the local `synthetic-corpus/` folder.
