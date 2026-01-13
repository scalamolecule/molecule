# Molecule Project Structure

## Naming Conventions (Validated by sbt-molecule)

### Entity Names: `[A-Z][a-zA-Z0-9]*`
- MUST start with uppercase letter
- Followed by letters/digits only
- ✅ Valid: `Person`, `Invoice`, `UserProfile`, `Item2`
- ❌ Invalid: `person`, `Person_`, `_Entity`, `2Entity`

### Segment Names: `[a-z][a-z0-9]*`
- MUST start with lowercase letter
- Followed by lowercase letters and/or digits only
- ✅ Valid: `accounting`, `warehouse`, `gen2`, `lit`
- ❌ Invalid: `Accounting`, `Ware_house`, `_segment`, `2gen`

**Enforced by**: `ParseDomainStructure` in sbt-molecule plugin

---

## Repository Organization

```
molecule/
├── core/                           # Platform-agnostic core
│   ├── shared/                    # Scala.js cross-compiled
│   ├── jvm/                       # JVM-specific
│   └── js/                        # JavaScript-specific
├── boilerplate/                   # Code generation utilities
├── db/
│   ├── common/                    # Shared database logic
│   │   ├── shared/               # Query/transaction resolution
│   │   └── jvm/                  # JDBC execution
│   ├── h2/                        # H2 database backend
│   ├── postgresql/                # PostgreSQL backend
│   ├── mysql/                     # MySQL backend
│   ├── mariadb/                   # MariaDB backend
│   ├── sqlite/                    # SQLite backend
│   └── compliance/                # Compliance test suite
│       ├── shared/               # Platform-agnostic tests
│       ├── jvm/                  # JVM-specific tests
│       └── js/                   # JS-specific tests
└── project/                       # SBT build configuration
```

**External Repository**:
- `sbt-molecule` at `/Users/mg/molecule/sbt/sbt-molecule/` - Code generation plugin

---

## Module Breakdown

### 1. core/

**Purpose**: Platform-agnostic core data structures and types

**Key Components**:
- `dataModel/Element.scala` - Element type hierarchy (Attr, Ref, BackRef, Nested, etc.)
- `dataModel/Model.scala` - DataModel container
- `error/` - Error types
- `setup/` - Test infrastructure (MUnit)
- `spi/` - Service Provider Interface definitions
- `util/` - Utilities

**Cross-compilation**: Shared code compiles to both JVM and JavaScript

---

### 2. boilerplate/

**Purpose**: Runtime code generation support (used by sbt-molecule)

**Key Components**:
- `src/main/scala/boilerplate/db/common/ops/_ModelTransformations.scala` - Model transformation templates

**Note**: Most generation logic is in the sbt-molecule plugin

---

### 3. db/common/

**Purpose**: Database-agnostic query and transaction resolution

#### 3.1 db/common/shared/

**Query Resolution**:
- `query/Model2Query.scala` - Resolves DataModel → Query AST
- `query/Model2SqlQuery.scala` - Base SQL generation
- `query/QueryExpr.scala` - Expression interface (filters, aggregations)
- `query/QueryExprRef.scala` - Reference/relationship handling

**API**:
- `api/Sort.scala` - Sorting API
- `api/expression/` - Type-specific expression traits (ExprOneMan_1_String, etc.)

**Validation**:
- `validation/TxModelValidation.scala` - Transaction validation
- `validation/insert/InsertValidationExtraction.scala`

**Marshalling**:
- `marshalling/serialize/PickleTpls.scala` - Tuple → bytes (for JS)
- `marshalling/deserialize/UnpickleTpls.scala` - bytes → Tuple (for JS)

**Utilities**:
- `ops/ModelTransformations_.scala` - Query optimization
- `util/ModelUtils.scala` - Model manipulation

**SPI**:
- `spi/AccessControl.scala` - Authorization framework

#### 3.2 db/common/jvm/

**Transaction Resolution** (JVM-specific):
- `transaction/ResolveInsert.scala` - Insert compilation
- `transaction/ResolveSave.scala` - Upsert compilation
- `transaction/ResolveUpdate.scala` - Update compilation
- `transaction/ResolveDelete.scala` - Delete compilation

---

### 4. db/h2/, db/postgresql/, db/mysql/, db/mariadb/, db/sqlite/

**Structure** (same for all backends):

```
db/xxx/
├── shared/
│   ├── query/Model2SqlQuery_xxx.scala    # Database-specific SQL
│   ├── spi/Spi_xxx_base.scala            # Base SPI
│   └── ...
├── jvm/
│   ├── spi/Spi_xxx_sync.scala            # Synchronous API
│   ├── spi/Spi_xxx_async.scala           # Future-based async
│   ├── spi/Spi_xxx_io.scala              # Cats Effect IO
│   ├── spi/Spi_xxx_zio.scala             # ZIO
│   ├── facade/JdbcConn_xxx_JVM.scala     # JDBC connection
│   ├── javaSql/ResultSetImpl_xxx.scala   # ResultSet wrapper
│   ├── query/Model2SqlQuery_xxx.scala    # Query implementation
│   ├── transaction/Resolve*_xxx.scala    # Transaction implementations
│   └── setup/DbProviders_xxx.scala       # Test setup
└── js/
    ├── spi/Spi_xxx_async.scala           # JS async API
    ├── facade/JdbcConn_xxx_JS.scala      # JS database driver
    └── ...
```

**Key Differences**:
- **Query**: SQL dialect differences (UPSERT syntax, array handling, regex)
- **Transaction**: Database-specific insert/update strategies
- **Connection**: JDBC vs. JS driver wrappers

---

### 5. db/compliance/

**Purpose**: Cross-database test suite ensuring consistent semantics

#### Test Organization (346 files)

```
db/compliance/shared/src/test/scala/molecule/db/compliance/test/
├── aggregation/               # 43 files - count, sum, avg, etc.
│   ├── AggrRelations.scala
│   ├── any/                   # 7 files - countDistinct, rand, sample, distinct
│   ├── number/                # 13 files - sum, avg, median, variance, stddev
│   ├── ref/                   # 10 files - aggregations on relationships
│   └── refNum/                # 13 files - aggregations with grouped refs
├── api/                       # 3 files - execution models
│   ├── AsyncApi.scala         # Future-based
│   ├── IOApi.scala            # Cats Effect IO
│   └── ZioApi.scala           # ZIO
├── authorization/             # 6 files - access control
├── bind/                      # 27 files - runtime parameters
├── crud/                      # 51 files - CRUD operations
│   ├── delete/
│   ├── insert/
│   ├── save/
│   └── update/
├── filter/                    # 100+ files - filtering
│   ├── map/
│   ├── one/
│   ├── seq/
│   └── set/
├── filterAttr/                # 14 files - attribute comparisons
├── pagination/                # 26 files - offset/cursor
├── relationship/              # 15 files - refs, nested, bidirectional
├── segments/                  # 1 file - multi-tenant
├── sorting/                   # 4 files - ordering
├── subscription/              # 1 file - live queries
├── types/                     # 9 files - all 25+ types
└── validation/                # 33 files - constraints
```

#### Generated Test Domains

```
db/compliance/jvm/target/scala-3.7.4/src_managed/main/moleculeGen/molecule/db/compliance/domains/dsl/
├── Types/                     # Main test domain
│   ├── Entity.scala          # Primary entity
│   ├── Ref.scala             # Related entity
│   ├── Other.scala           # Secondary entity
│   ├── Color.scala           # Enum type
│   ├── ops/                  # Generated operations
│   └── metadb/               # Schema metadata
├── Refs/                      # A-H entities for relationship tests
├── JoinTable/                 # A-B-J for many-to-many tests
├── Uniques/                   # Unique constraint tests
├── Validation/                # Validation rule tests
├── SocialApp*/                # Authorization examples (5 variants)
└── Segments/                  # Multi-tenant namespace tests
```

---

## Build System (SBT)

### build.sbt Structure

**Cross-building**:
- Scala 3.7.4
- JVM + Scala.js for most modules

**Key Settings**:
- `moleculeSchemaGen` - Enabled for test domains
- Flyway migration for schema management

**Module Dependencies**:
```
core (shared)
  ↓
db/common (shared)
  ↓
db/xxx (shared) ← backend implementations
  ↓
db/xxx (jvm/js) ← platform-specific
  ↓
db/compliance (shared/jvm/js) ← tests
```

### project/plugins.sbt

**Plugins**:
- `sbt-molecule` - Code generation
- `scala-js` - JavaScript cross-compilation
- Flyway - Database migrations

---

## Code Generation Flow

```
1. User writes schema definition:
   src/main/scala/domain/schema/PersonSchema.scala

2. sbt-molecule plugin (moleculeGen task):
   - Parses schema files
   - Generates DSL code
   - Outputs to target/scala-3.7.4/src_managed/main/moleculeGen/

3. Generated code structure:
   moleculeGen/domain/dsl/
   ├── Person.scala          # Entry point
   ├── ops/
   │   ├── Person_0.scala   # Zero-arity builder
   │   ├── Person_1.scala   # One-arity builder
   │   ├── Person_n.scala   # N-arity builder
   │   └── Person_attrs.scala
   └── metadb/
       └── PersonResolver.scala

4. User writes queries using generated DSL:
   Person.name.age.>(30).query.get
```

---

## Testing Structure

### Test Inheritance Pattern

```scala
// Shared test (runs on all databases)
class AggregationTests extends MUnit with DbProviders with TestUtils {
  "count" - types { implicit conn =>
    for {
      _ <- Entity.i.insert(1, 2, 3).transact
      _ <- Entity.i(count).query.get.map(_ ==> 3)
    } yield ()
  }
}

// Database-specific instantiation
class AggregationTests_h2 extends AggregationTests with DbProviders_h2
class AggregationTests_postgres extends AggregationTests with DbProviders_postgres
// etc.
```

**Benefits**:
- Single test definition
- Runs on all databases
- Catches backend-specific bugs

### Test Domains

Tests use multiple domains to cover different scenarios:

1. **Types** - General attribute types, filters, aggregations
2. **Refs** - Complex relationship graphs (A-H)
3. **JoinTable** - Many-to-many relationships (J extends Join trait)
4. **Uniques** - Unique constraints and upserts
5. **Validation** - Mandatory fields, enums, custom validations
6. **SocialApp** - Authorization scenarios (role-based, attribute-level)
7. **Segments** - Multi-tenant with namespace prefixes

**Join Trait**: Used in JoinTable domain - entities extending `Join` represent many-to-many join tables. Example:
```scala
trait J extends Join {
  val a = manyToOne[A]
  val b = manyToOne[B]
  // Optional properties
  val i = oneInt
}
```

---

## Platform-Specific Code

### JVM-specific
- JDBC connection handling
- Synchronous execution
- Transaction management
- Batch operations

**Location**: `db/xxx/jvm/`

### JavaScript-specific
- Node.js/browser database drivers
- Promise-based async
- Client-side aggregations (SQLite)

**Location**: `db/xxx/js/`

### Shared (cross-compiled)
- Query resolution
- DataModel manipulation
- Validation logic
- API surface

**Location**: `core/shared/`, `db/common/shared/`, `db/xxx/shared/`

---

## External Dependencies

### Runtime
- **scala-reflect** - Compile-time type inspection
- **upickle** - Serialization (for JS marshalling)
- Database drivers (JDBC, better-sqlite3, etc.)

### Test
- **MUnit** - Test framework
- **Flyway** - Migration (JVM tests)

### Build
- **sbt-molecule** - Code generation plugin
- **scalameta** - Schema parsing

---

## Key Directories Summary

| Directory | Purpose | Lines of Code (est.) |
|-----------|---------|---------------------|
| `core/` | Core types, Element hierarchy | ~5K |
| `db/common/shared/` | Query/transaction resolution | ~15K |
| `db/common/jvm/` | Transaction implementations | ~8K |
| `db/xxx/shared/` | SQL generation per DB | ~3K each |
| `db/xxx/jvm/` | JDBC wrappers, SPI | ~5K each |
| `db/compliance/shared/test/` | 346 test files | ~50K |
| Generated code | DSL per domain | ~10K per domain |
| `sbt-molecule/` | Code generator | ~20K |

**Total**: ~200K lines of code across project + plugin

---

## Version History

**Current**: v0.29.0 - Flyway migration support
**Previous**: v0.28.0 - Database column properties

See git history for full changelog.

---

## Development Workflow

1. **Modify schema** → `src/main/scala/domain/schema/`
2. **Run generator** → `sbt moleculeGen`
3. **Write tests** → `db/compliance/shared/src/test/`
4. **Run tests** → `sbt "project h2-jvm" test`
5. **Verify all DBs** → `sbt test` (runs all platforms/databases)

---

## Navigation Tips

**Looking for...**
- How queries compile? → `db/common/shared/src/main/scala/molecule/db/common/query/`
- How transactions work? → `db/common/jvm/src/main/scala/molecule/db/common/transaction/`
- Database differences? → Compare `db/xxx/shared/src/main/scala/molecule/db/xxx/query/Model2SqlQuery_xxx.scala`
- Feature examples? → `db/compliance/shared/src/test/scala/molecule/db/compliance/test/[category]/`
- Generated code? → `db/compliance/jvm/target/scala-3.7.4/src_managed/main/moleculeGen/`
- Code generation logic? → `/Users/mg/molecule/sbt/sbt-molecule/src/main/scala/sbtmolecule/`
