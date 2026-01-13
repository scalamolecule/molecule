# Molecule Quick Reference

**Version**: 0.29.0
**Purpose**: Type-safe, boilerplate-free database access for Scala
**Test Files**: 346 compliance tests
**Supported DBs**: H2, PostgreSQL, MySQL, MariaDB, SQLite (all JVM + JS)

---

## Project Structure

```
molecule/
├── core/                    # Core DataModel, Element types
├── boilerplate/             # Code generation logic
├── db/
│   ├── common/             # Shared query/transaction resolution
│   ├── h2/                 # H2 backend (JVM only initially)
│   ├── postgresql/         # PostgreSQL backend
│   ├── mysql/              # MySQL backend
│   ├── mariadb/            # MariaDB backend
│   └── sqlite/             # SQLite backend
└── db/compliance/          # 346 test files across 14 categories
```

**External**: `sbt-molecule` plugin at `/Users/mg/molecule/sbt/sbt-molecule/`

---

## Documentation Index

1. **[compliance-test-taxonomy.md](./compliance-test-taxonomy.md)** - Complete feature set (346 tests)
2. **[boilerplate-dsl-generation.md](./boilerplate-dsl-generation.md)** - Generated code patterns
3. **[sbt-molecule-codegen.md](./sbt-molecule-codegen.md)** - Code generation pipeline
4. **[compilation-pipeline.md](./compilation-pipeline.md)** - DSL → SQL transformation
5. **[database-backends.md](./database-backends.md)** - Backend comparison

---

## Naming Conventions (STRICT)

**Enforced by sbt-molecule validation:**

### Entity Names: `[A-Z][a-zA-Z0-9]*`
- MUST start with uppercase letter
- Followed by any letters/digits only
- NO underscores, NO hyphens
- ✅ Valid: `Person`, `Invoice`, `UserProfile`, `Item2`
- ❌ Invalid: `person`, `Person_`, `_Entity`, `2Entity`

### Segment Names: `[a-z][a-z0-9]*`
- MUST start with lowercase letter
- Followed by lowercase letters and/or digits only
- NO uppercase, NO underscores, NO hyphens, NO leading digits
- ✅ Valid: `accounting`, `warehouse`, `gen2`, `lit`
- ❌ Invalid: `Accounting`, `Ware_house`, `Gen-Person`, `_segment`, `2gen`

**Note**: These rules are validated by `ParseDomainStructure` in sbt-molecule

---

## Core Concepts

### 1. Schema Definition (User Input)
```scala
trait Person extends Entity {
  val name     = oneString.unique
  val age      = oneInt
  val hobbies  = manyString
  val address  = manyToOne[Address]
}

// Join trait for many-to-many relationships
trait StudentCourse extends Join {
  val student = manyToOne[Student]
  val course  = manyToOne[Course]
}
```

### 2. Generated DSL (Boilerplate)
```scala
// Type-safe molecule building
Person.name("Bob").age(42).hobbies("chess", "coding")
```

### 3. DataModel Elements
- **Attr**: Attributes with value, cardinality, operation
- **Ref**: Many-to-one relationships
- **BackRef**: Reverse navigation
- **Nested**: One-to-many embedded data
- **SubQuery**: Subqueries in SELECT/WHERE/FROM

### 4. Compilation Flow
```
DSL → DataModel → Model2Query → SQL → ResultSet → Typed Tuples
```

---

## Key Operations

### Query Building
```scala
Person.name.age.>(30).query.get              // Get
Person.name.age.query.i.inspect              // Inspect SQL
Person.name_(startsWith("B")).age.query.get  // Filter (tacit)
```

### CRUD
```scala
Person.name("Alice").age(25).save           // Insert/Upsert
Person(id).age(26).update                   // Update
Person.id(id).delete                        // Delete
```

### Aggregations
```scala
Person.age(avg)                             // Average age
Person.name.hobbies(count).>(2)             // People with 3+ hobbies
```

### Relationships
```scala
Person.name.Address.city                    // Many-to-one
Person.name.Hobbies.*(Hobby.name)          // One-to-many nested

// Many-to-many via join table (explicit)
A.i.Js.i.a1.B.i.query                      // Through join table J
A.i.Js.*(J.i.B.i.a1).query                 // Nested via join table

// Many-to-many bridging (implicit)
A.i.Bs.i.a1.query                          // Direct bridge to B (skips J)
A.i.Bs.**(B.i.a1).query                    // Nested bridge to B (skips J)
```

### Subqueries (NEW)
```scala
Person.name.age.>(Person.age(avg))          // Age > average
Country.name.sub(City.name.population.d1)   // Subquery in FROM
```

---

## Cardinality Modifiers

| Syntax | Meaning | Example |
|--------|---------|---------|
| `attr` | Mandatory, returned | `Person.name` |
| `attr_` | Tacit filter | `Person.name_("Bob").age` |
| `attr_?` | Optional | `Person.name_?` → `Option[String]` |
| `attr.*` | Nested one-to-many | `Person.Addresses.*(Address.city)` |

---

## Type System (25+ types)

Scala 3 tuples support **unlimited arity** (no 22-limit from Scala 2).
**Note**: Bindings have a max of 22 parameters.

**String**: String
**Integer**: Int, Long, Short, Byte, Char
**Decimal**: Float, Double, BigInt, BigDecimal
**Boolean**: Boolean
**Date/Time**: Date, Duration, Instant, LocalDate, LocalTime, LocalDateTime, OffsetTime, OffsetDateTime, ZonedDateTime
**Binary**: UUID, URI, Byte Array

**Card**: One, Set, Seq, Map
**Mode**: Mandatory, Optional, Tacit

---

## Operators by Type

### Comparison (all types)
`apply`, `not`, `<`, `>`, `<=`, `>=`, `!=`

### String-specific
`startsWith`, `endsWith`, `contains`, `matches` (regex)

### Collection-specific
`has`, `hasNo`, `intersect`, `overlap`, `union`, `subset`

### Aggregations
`count`, `countDistinct`, `sum`, `avg`, `median`, `variance`, `stddev`, `min`, `max`, `sample`, `rand`, `distinct`

---

## Feature Categories (14)

1. **Aggregation** (43 files) - count, sum, avg, median, variance, stddev, min, max, sample
2. **API** (3 files) - Async (Future), IO (Cats Effect), ZIO
3. **Authorization** (6 files) - Role-based, attribute-level access control
4. **Bind** (27 files) - Runtime query parameterization
5. **CRUD** (51 files) - Insert, Save, Update, Delete with validations
6. **Filter** (100+ files) - Equals, comparison, string, collection operators
7. **FilterAttr** (14 files) - Self-joins, cross-attribute comparisons
8. **Pagination** (26 files) - Offset-based, cursor-based (3 strategies)
9. **Relationship** (15 files) - Flat refs, nested, bidirectional, many-to-many
10. **Segments** (1 file) - Multi-tenant namespace prefixes
11. **Sorting** (4 files) - Compile-time, dynamic, with aggregates
12. **Subscription** (1 file) - Real-time live queries
13. **Types** (9 files) - All 25+ types with cardinalities (Scala 3: unlimited arity)
14. **Validation** (33 files) - Mandatory, enums, format, allowed values

---

## Database Differences

| Feature | H2 | PostgreSQL | MySQL | MariaDB | SQLite |
|---------|----|-----------:|-------|---------|--------|
| **Arrays** | Native | Native | JSON | JSON | JSON |
| **UPSERT** | MERGE | ON CONFLICT | ON DUPLICATE | ON DUPLICATE | ON CONFLICT |
| **Regex** | REGEXP | ~ | REGEXP | REGEXP | Custom UDF |
| **Case-sens** | Configurable | Yes | No (default) | No (default) | Yes |
| **Platform** | JVM | JVM+JS | JVM+JS | JVM+JS | JVM+JS |

---

## Key Files by Function

### Core Compilation
- `core/dataModel/Element.scala` - Element hierarchy
- `db/common/query/Model2Query.scala` - Query resolution
- `db/common/query/Model2SqlQuery.scala` - SQL generation
- `db/common/ops/ModelTransformations_.scala` - Optimizations

### Transaction
- `db/common/transaction/ResolveInsert.scala`
- `db/common/transaction/ResolveSave.scala`
- `db/common/transaction/ResolveUpdate.scala`
- `db/common/transaction/ResolveDelete.scala`

### Marshalling
- `db/common/marshalling/serialize/PickleTpls.scala` - Tuple → bytes
- `db/common/marshalling/deserialize/UnpickleTpls.scala` - bytes → Tuple

### Code Generation
- `sbt-molecule/src/main/scala/sbtmolecule/MoleculePlugin.scala` - Entry point
- `sbt-molecule/src/main/scala/sbtmolecule/parse/SchemaParser.scala` - Schema parsing
- `sbt-molecule/src/main/scala/sbtmolecule/generate/` - Code generators

---

## Common Patterns

### Simple Query
```scala
Person.name.age.query.get
// → SELECT Person.name, Person.age FROM Person
```

### Filtered Query
```scala
Person.name.age_.>(30).query.get
// → SELECT Person.name FROM Person WHERE Person.age > 30
```

### Join Query
```scala
Person.name.Address.city.query.get
// → SELECT Person.name, Address.city FROM Person
//    JOIN Address ON Address.id = Person.address_id
```

### Aggregation
```scala
Person.hobbies(count).query.get
// → SELECT COUNT(Person.hobbies) FROM Person
```

### Nested Pull
```scala
Person.name.Addresses.*(Address.city).query.get
// Returns: List[(String, List[String])]
```

### Subquery (NEW - in development)
```scala
Person.name.age.>(Person.age(avg))
// → SELECT Person.name FROM Person
//    WHERE Person.age > (SELECT AVG(age) FROM Person)
```

---

## Join Trait (Many-to-Many Relationships)

When an entity extends `Join`, it represents a **join table** for many-to-many relationships:

```scala
trait J extends Join {
  val a = manyToOne[A]
  val b = manyToOne[B]
  // Optional join properties
  val i = oneInt
}
```

**Navigation Syntax**:

**Explicit (via join table J)**:
```scala
A.i.Js.i.a1.B.i.query                      // Access join property i
A.i.Js.B.i.a1.query                        // Skip join property
A.i.a1.Js.*(J.i.B.i.a1).query              // Nested via join
```

**Implicit (bridge directly)**:
```scala
A.i.Bs.i.a1.query                          // Direct to B values (flat)
A.i.Bs.**(B.i.a1).query                    // Direct to B values (nested)
```

**Key Differences**:
- `Js.*` - Explicit join table navigation (can access join properties)
- `Bs.**` - Implicit bridge (skips join table, cleaner for simple many-to-many)
- Both support nested syntax with `*` and `**`

**Usage**:
- Each `Join` entity must have at least two `manyToOne` references
- Automatically creates bidirectional many-to-many navigation
- Can include additional attributes (enrollment date, grade, etc.)

**Example**: See `/Users/mg/molecule/molecule/molecule/db/compliance/shared/src/test/scala/molecule/db/compliance/test/relationship/ManyToMany.scala`

---

## Testing Domains

**Types** - Entity, Ref, Other, Color - main test domain
**Refs** - A-H entities - relationship testing
**JoinTable** - A, B, J - many-to-many (J extends Join)
**Uniques** - unique constraint testing
**Validation** - validation rules
**SocialApp** variants - authorization examples
**Segments** - namespaced entities

---

## Current Work: Subqueries

**Implementation Status**: In development
**Test Location**: `db/h2/jvm/src/test/scala/molecule/db/h2/subquery/`

**Subquery Types**:
1. WHERE clause - `Entity.attr.>(Ref.attr(max))`
2. SELECT clause - Computed columns (TBD syntax)
3. FROM clause - `Country.name.sub(City.population.d1)`

**Key Methods**:
- `.sub()` - Mark subquery boundary
- Aggregations in comparisons trigger subquery generation

---

## Token Optimization

**This index enables:**
- Quick orientation without scanning full codebase
- Targeted file access via references
- Persistent knowledge across sessions
- Lower token usage per question

**When to use which doc:**
- Feature questions → `compliance-test-taxonomy.md`
- DSL syntax → `boilerplate-dsl-generation.md`
- Code generation → `sbt-molecule-codegen.md`
- Query compilation → `compilation-pipeline.md`
- DB differences → `database-backends.md`
