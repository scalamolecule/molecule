# Molecule Database Backend Implementations

## Naming Conventions

**Entity Names**: `[A-Z][a-zA-Z0-9]*` (uppercase first, letters/digits only)
**Segment Names**: `[a-z][a-z0-9]*` (lowercase first, then letters/digits only)

These rules apply to all database backends and are enforced by sbt-molecule.

---

## Overview

Molecule supports 5 SQL database backends, each with implementations for both JVM and JavaScript (Scala.js) platforms. All backends share a common architecture but have database-specific optimizations and workarounds for SQL dialect differences.

**Supported Databases:**
- **H2** - In-memory/file-based database (JVM + JS)
- **PostgreSQL** - Full-featured open-source RDBMS (JVM + JS)
- **MySQL** - Popular open-source RDBMS (JVM + JS)
- **MariaDB** - MySQL fork with enhancements (JVM + JS)
- **SQLite** - Lightweight embedded database (JVM + JS)

**Platform Support:** All databases support both JVM and JavaScript platforms via cross-project compilation in sbt.

---

## Architecture Overview

Each database backend consists of:

1. **SPI Layer** (`Spi_xxx_[sync|async|io|zio]`) - Service Provider Interface for different effect types
2. **Query Layer** (`Model2SqlQuery_xxx`) - Translates Molecule's data model to SQL
3. **Transaction Layer** (`Save_xxx`, `Insert_xxx`, `Update_xxx`) - Handles data mutations
4. **Expression Layer** (`QueryExprOne_xxx`, `QueryExprSet_xxx`, etc.) - Database-specific query expressions
5. **Lambda Layer** (`LambdasOne_xxx`, etc.) - Type mappings and conversions
6. **Connection Layer** (`JdbcConn`) - JDBC connection management

---

## 1. H2 Database

### Platform Support
- **JVM**: Full support
- **JS**: Full support

### SPI Implementations
**Location**: `/db/h2/jvm/src/main/scala/molecule/db/h2/spi/` and `/db/h2/js/src/main/scala/molecule/db/h2/spi/`

**Available SPIs:**
- `Spi_h2_sync` - Synchronous operations returning values directly
- `Spi_h2_async` - Asynchronous operations returning `Future[T]`
- `Spi_h2_io` - Cats Effect IO operations
- `Spi_h2_zio` - ZIO operations

**Key Features:**
- Uses SQL standard transaction mixins (`SqlSave`, `SqlInsert`, `SqlUpdate`)
- Array-based validation: `validateUpdateSet_array`
- Custom connection initialization via `JdbcHandler_JVM.getSqlInit`

### Query Implementation
**File**: `/db/h2/shared/src/main/scala/molecule/db/h2/query/Model2SqlQuery_h2.scala`

**Extends**:
- `Model2SqlQuery` (base)
- `QueryExprOne_h2`
- `QueryExprSet_h2`
- `QueryExprSeq_h2`
- `QueryExprMap_h2`
- `QueryExprSetRefAttr_h2`
- `SqlQueryBase`

**Special Features:**
- **Subquery Support with ROW types**: H2 returns multi-column subqueries as ROW types
- **JVM-specific**: `Model2SqlQuery_h2_JVM` extends base with `castSubqueryRow` to handle H2's `ValueRow` objects
- Custom `RowResultSet_h2_JVM` wrapper to extract values from H2's ROW type

**Example ROW Handling:**
```scala
override protected def castSubqueryRow(rowCasts: List[Cast]): Cast = {
  (row: RS, paramIndex: Int) => {
    val rowObject = row.getObject(paramIndex)
    val rowResultSet = convertH2RowToResultSet(rowObject)
    // Extract values and return as tuple
  }
}
```

### Type Mappings
Arrays stored natively using H2 ARRAY types.

### Special Features
- **Arrays**: Native ARRAY support
- **JSON**: Via TEXT/VARCHAR
- **UPSERT**: Standard SQL MERGE
- **Regex**: `REGEXP_LIKE` function
- **Case-sensitive**: Default case-sensitive string matching

### Connection Pattern
```scala
val sqlConn = DriverManager.getConnection(proxy.url)
sqlConn.setAutoCommit(false)
val conn = JdbcConn_JVM_h2(proxy, sqlConn)
// Initialize with SQL init script
stmt.executeUpdate(JdbcHandler_JVM.getSqlInit(proxy))
```

### Limitations
- Multi-column subqueries require JVM-specific handling
- ROW type handling adds complexity on JVM platform

---

## 2. PostgreSQL

### Platform Support
- **JVM**: Full support
- **JS**: Full support

### SPI Implementations
**Location**: `/db/postgresql/jvm/src/main/scala/molecule/db/postgresql/spi/` and `/db/postgresql/js/src/main/scala/molecule/db/postgresql/spi/`

**Available SPIs:**
- `Spi_postgresql_sync`
- `Spi_postgresql_async`
- `Spi_postgresql_io`
- `Spi_postgresql_zio`

**Key Features:**
- PostgreSQL-specific transaction mixins: `Save_postgresql`, `Insert_postgresql`, `Update_postgresql`
- Array-based validation: `validateUpdateSet_array`
- Direct `DriverManager.getConnection` with `JdbcHandler_JVM.updateDb`

### Query Implementation
**File**: `/db/postgresql/shared/src/main/scala/molecule/db/postgresql/query/Model2SqlQuery_postgresql.scala`

**Special Features:**
- Most straightforward subquery implementation (no special ROW handling needed)
- Rich aggregate function support with PostgreSQL-specific functions
- Advanced array operations using `ARRAY_AGG`, `UNNEST`, `ANY`

**PostgreSQL-Specific Expressions** (`QueryExprOne_postgresql`):
- **Float handling**: Uses `ROUND(col::NUMERIC, 7)` for precision
- **UUID casting**: `CAST(? AS UUID)` for bind parameters
- **Sorting**: Explicit `NULLS FIRST` and `NULLS LAST`
- **Aggregates**: `percentile_cont`, `VAR_POP`, `STDDEV_POP`, `TRIM_ARRAY`

### Type Mappings
| Scala Type | PostgreSQL Type | Notes |
|------------|-----------------|-------|
| String | VARCHAR | |
| Int | INTEGER | |
| Long | BIGINT | |
| Float | DECIMAL | Use Double instead |
| Double | DECIMAL | |
| Boolean | BOOLEAN | |
| BigInt | DECIMAL | |
| BigDecimal | DECIMAL | |
| Date | BIGINT | Stored as epoch milliseconds |
| UUID | UUID | Requires `::uuid` cast |
| Array[T] | T[] | Native array types |
| Set[T] | T[] | Stored as array |
| Seq[T] | T[] | Stored as array |
| Map[String,T] | JSONB | JSON with `::jsonb` cast |

### Special Features
- **Arrays**: Native PostgreSQL array types with full operators (`ANY`, `UNNEST`)
- **JSON**: Native JSONB type with `::jsonb` casting
- **UPSERT**: Uses PostgreSQL's `ON CONFLICT` clause
- **Regex**: `REGEXP_REPLACE` with flags (e.g., `'g'` for global)
- **Case-sensitive**: Default case-sensitive
- **String replace**: `REGEXP_REPLACE($attr, ?, 'replacement', 'g')`

### Transaction Specifics
**Map Operations** (`Update_postgresql`):
```scala
// Map add uses JSONB concatenation
attr = CASE
  WHEN attr IS NULL THEN '{}'::jsonb
  ELSE attr::jsonb
END || ?::jsonb

// Map remove uses JSONB subtraction
attr = CASE
  WHEN attr::jsonb - ? = '{}' THEN NULL
  ELSE attr::jsonb - ?
END
```

**Array Operations**:
```scala
// Remove from array
attr = (
  SELECT ARRAY_AGG(_v)
  FROM UNNEST(attr::type[]) AS _v
  WHERE NOT _v = ANY(?::type[])
)
```

### Limitations
- Float precision requires rounding workarounds
- Complex array operations can be verbose

---

## 3. MySQL

### Platform Support
- **JVM**: Full support
- **JS**: Full support

### SPI Implementations
**Location**: `/db/mysql/jvm/src/main/scala/molecule/db/mysql/spi/` and `/db/mysql/js/src/main/scala/molecule/db/mysql/spi/`

**Available SPIs:**
- `Spi_mysql_sync`
- `Spi_mysql_async`
- `Spi_mysql_io`
- `Spi_mysql_zio`

**Key Features:**
- MySQL-specific transactions: `Save_mysql`, `Insert_mysql`, `Update_mysql`
- JSON-based validation: `validateUpdateSet_json` (no native array support)

### Query Implementation
**File**: `/db/mysql/shared/src/main/scala/molecule/db/mysql/query/Model2SqlQuery_mysql.scala`

**Special Features:**
- Custom `pagination` method with MySQL's two-argument LIMIT syntax: `LIMIT offset, count`
- JSON-based Set/Seq storage instead of arrays
- `getWhereClauses` override (commented complex join handling)

**Pagination Syntax:**
```scala
LIMIT 0, $limit                    // First N rows
LIMIT $offset, $limit              // Skip offset, take limit
LIMIT $offset, 18446744073709551615 // All rows after offset
```

### Type Mappings
| Scala Type | MySQL Type | Notes |
|------------|------------|-------|
| String | LONGTEXT | |
| Int | INT | |
| Long | BIGINT | |
| Float | REAL | |
| Double | DOUBLE | |
| Boolean | TINYINT(1) | 0 = false, 1 = true |
| BigInt | DECIMAL(65, 0) | |
| BigDecimal | DECIMAL(65, 30) | |
| Date | BIGINT | Epoch milliseconds |
| UUID | TINYTEXT | Stored as string |
| Set[T] | JSON | Stored as JSON array |
| Seq[T] | JSON | Stored as JSON array |
| Map[String,T] | JSON | Native JSON type |

### Special Features
- **Arrays**: NO native array support - uses JSON arrays
- **JSON**: Native JSON type with JSON functions
- **UPSERT**: `ON DUPLICATE KEY UPDATE`
- **Regex**: `REGEXP` operator (case-insensitive by default)
- **Case-sensitive**: Case-insensitive by default (depends on collation)
- **String concat**: `CONCAT($attr, ?$cast)` for append/prepend

### Transaction Specifics
**Set/Seq as JSON** (`Save_mysql`, `Insert_mysql`):
```scala
// Stored as JSON array string
cast = ""
addIterable(attr, paramIndex, optIterable, value2json)
```

**Map Operations** (`Update_mysql`):
```scala
// Map add
$ent.$attr = JSON_MERGE_PATCH(IFNULL($ent.$attr, JSON_OBJECT()), ?)

// Map remove
$ent.$attr = CASE JSON_REMOVE(IFNULL($ent.$attr, NULL), keys...)
  WHEN JSON_OBJECT() THEN NULL
  ELSE JSON_REMOVE($ent.$attr, keys...)
END
```

**Iterable Operations**:
```scala
// Add to set/seq
attr = JSON_MERGE(IFNULL(attr, '[]'), ?)

// Remove from set/seq using JSON_TABLE
attr = (
  SELECT JSON_ARRAYAGG(valueTable.v)
  FROM JSON_TABLE($ent.$attr, '$[*]' COLUMNS (v TYPE PATH '$')) valueTable
  WHERE valueTable.v NOT IN (retractValues)
)
```

### Limitations
- **No native array support** - all collections stored as JSON
- JSON operations more verbose than PostgreSQL's JSONB
- Case-insensitive string matching by default (collation-dependent)

---

## 4. MariaDB

### Platform Support
- **JVM**: Full support
- **JS**: Full support

### SPI Implementations
**Location**: `/db/mariadb/jvm/src/main/scala/molecule/db/mariadb/spi/` and `/db/mariadb/js/src/main/scala/molecule/db/mariadb/spi/`

**Available SPIs:**
- `Spi_mariadb_sync`
- `Spi_mariadb_async`
- `Spi_mariadb_io`
- `Spi_mariadb_zio`

**Key Features:**
- MariaDB-specific transactions: `Save_mariadb`, `Insert_mariadb`, `Update_mariadb`
- JSON-based validation: `validateUpdateSet_json`
- Shares much implementation with MySQL

### Query Implementation
**File**: `/db/mariadb/shared/src/main/scala/molecule/db/mariadb/query/Model2SqlQuery_mariadb.scala`

**Special Features:**
- Includes `Lambdas_mariadb` trait (must be last to override resolvers)
- Same pagination syntax as MySQL
- Custom type mappings in `Lambdas_mariadb`

**MariaDB-Specific Expressions** (`QueryExprOne_mariadb`):
- **Regex**: Uses `REGEXP BINARY ?` for case-sensitive matching
- Otherwise similar to MySQL

### Type Mappings (`Lambdas_mariadb`)
| Scala Type | MariaDB Type | Notes |
|------------|--------------|-------|
| String | LONGTEXT | |
| Int | INT | |
| Long | BIGINT | |
| Float | REAL | |
| Double | DOUBLE | |
| Boolean | TINYINT(1) | Stored as 1/0 |
| BigInt | DECIMAL(65, 0) | |
| BigDecimal | DECIMAL(65, 30) | |
| Date | BIGINT | Epoch milliseconds |
| UUID | TINYTEXT | |
| Set[T] | JSON | JSON array |
| Seq[T] | JSON | JSON array |
| Map[String,T] | JSON | JSON object |

**Custom Boolean Mapping:**
```scala
override protected lazy val one2sqlBoolean: Boolean => String =
  (v: Boolean) => if (v) "1" else "0"
```

### Special Features
- **Arrays**: NO native array support - uses JSON
- **JSON**: Native JSON type
- **UPSERT**: Similar to MySQL
- **Regex**: `REGEXP BINARY` for case-sensitive matching
- **Case-sensitive**: `REGEXP BINARY` ensures case-sensitivity
- **String operations**: Same as MySQL

### Transaction Specifics
Very similar to MySQL with JSON-based collections.

### Limitations
- Same as MySQL (no native arrays)
- Requires `BINARY` keyword for case-sensitive regex

---

## 5. SQLite

### Platform Support
- **JVM**: Full support
- **JS**: Full support

### SPI Implementations
**Location**: `/db/sqlite/jvm/src/main/scala/molecule/db/sqlite/spi/` and `/db/sqlite/js/src/main/scala/molecule/db/sqlite/spi/`

**Available SPIs:**
- `Spi_sqlite_sync`
- `Spi_sqlite_async`
- `Spi_sqlite_io`
- `Spi_sqlite_zio`

**Key Features:**
- SQLite-specific transactions: `Save_sqlite`, `Insert_sqlite`, `Update_sqlite`
- SQLite-specific validation: `validateUpdateSet_sqlite`
- Custom ID retrieval: `getIdsAndClose` gets max ID before insert, then queries new IDs after
- Custom `fallback_rawTransact` with `RETURNING id` support

### Query Implementation
**File**: `/db/sqlite/shared/src/main/scala/molecule/db/sqlite/query/Model2SqlQuery_sqlite.scala`

**Special Features:**
- Custom pagination: SQLite requires `LIMIT -1` to allow offset without limit
- Simplified subquery handling (similar to PostgreSQL)

**Pagination:**
```scala
// SQLite needs LIMIT to allow OFFSET
optLimit.fold {
  "\nLIMIT -1" // Allow offset
}(limit => s"\nLIMIT ${limit.abs}")

optOffset.fold("")(offset => s"\nOFFSET ${offset.abs}")
```

### Type Mappings
| Scala Type | SQLite Type | Notes |
|------------|-------------|-------|
| String | TEXT | |
| Int | INTEGER | |
| Long | INTEGER | SQLite's 8-byte int |
| Float | REAL | Stored as Double |
| Double | REAL | |
| Boolean | INTEGER | 0 = false, 1 = true |
| BigInt | TEXT | Stored as string |
| BigDecimal | TEXT | Stored as string |
| Date | INTEGER | Epoch milliseconds |
| Set[T] | TEXT | JSON array |
| Seq[T] | TEXT | JSON array |
| Map[String,T] | TEXT | JSON object |

**Float Precision Workaround** (`Save_sqlite`, `Insert_sqlite`):
```scala
// Save Floats as Doubles (REAL PRECISION) in SQLite
override protected lazy val transformFloat =
  (v: Float) => (ps: PS, n: Int) => ps.setDouble(n, v.toString.toDouble)
```

### Special Features
- **Arrays**: NO native array support - uses JSON
- **JSON**: JSON1 extension with `JSON_GROUP_ARRAY`, `JSON_EACH`, `JSON_SET`, `JSON_REMOVE`
- **UPSERT**: `INSERT ... ON CONFLICT ... DO UPDATE`
- **Regex**: Custom `REGEXP` function (must be registered on connection)
- **Case-sensitive**: Default case-sensitive
- **String replace**: `REPLACE($attr, ?, 'replacement')`

### Expression Specifics (`QueryExprOne_sqlite`)
**Regex Function Registration** (in `JdbcHandlerSQlite_JVM`):
```scala
Function.create(sqlConn, "REGEXP", new Function() {
  // Custom regex implementation
})
```

**Float Handling:**
```scala
// Uses ROUND for float comparisons
addBinding(s"ROUND($col, 7)", bind, "= ROUND(?, 7)")
```

**Aggregate Limitations:**
- **Median**: Calculated client-side from JSON array (no native `percentile_cont`)
- **Variance/StdDev**: Calculated client-side from JSON array
- Sorting by median/variance/stddev: Not supported

**Custom Aggregate Selects:**
```scala
// mins/maxs use custom subquery
select2 = select2 + (select.length -> minMaxSelect(ent, attr, n, "ASC"))

// sample uses random subquery
select2 = select2 + (select.length -> sampleSelect(ent, attr))
```

### Transaction Specifics
**Set Operations** (`Update_sqlite`):
```scala
// Add to set (UNION removes duplicates)
attr = (
  SELECT JSON_GROUP_ARRAY(VALUE)
  FROM (
    SELECT _vs.value FROM $ent AS _t, JSON_EACH($attr) AS _vs WHERE _t.id = $ent.id
    UNION
    SELECT _vs.value FROM JSON_EACH(?) AS _vs
  )
)

// Remove from set
attr = (
  SELECT (
    CASE JSON_GROUP_ARRAY(VALUE)
      WHEN '[]' THEN NULL
      ELSE JSON_GROUP_ARRAY(VALUE)
    END
  )
  FROM (
    SELECT _vs.value
    FROM $ent AS _t, JSON_EACH($attr) AS _vs
    WHERE
      _t.id = $ent.id AND
      _vs.VALUE NOT IN (retractValues)
  )
)
```

**Map Operations:**
```scala
// Map add
attr = JSON_SET(IFNULL(attr, JSON_OBJECT()), '$.key1', value1, '$.key2', value2, ...)

// Map remove
attr = (
  CASE JSON_REMOVE(attr, '$.key1', '$.key2', ...)
  WHEN '{}' THEN NULL
  WHEN NULL THEN attr
  ELSE JSON_REMOVE(attr, keys...)
  END
)
```

### Connection Pattern
```scala
val sqlConn = DriverManager.getConnection(proxy.url)
JdbcHandlerSQlite_JVM.recreateDb(proxy, sqlConn, true)
```

### Limitations
- **No native arrays** - uses JSON
- **Limited aggregate functions** - median/variance/stddev calculated client-side
- **Regex requires registration** - Custom function must be added to connection
- **Type system** - Dynamic typing can lead to unexpected behavior
- Float precision - stored as Double
- BigInt/BigDecimal - stored as TEXT

---

## Feature Comparison Matrix

| Feature | H2 | PostgreSQL | MySQL | MariaDB | SQLite |
|---------|----|-----------:|-------|---------|--------|
| **Platform Support** |
| JVM | ✅ | ✅ | ✅ | ✅ | ✅ |
| JavaScript | ✅ | ✅ | ✅ | ✅ | ✅ |
| **Collection Types** |
| Native Arrays | ✅ | ✅ | ❌ | ❌ | ❌ |
| JSON Arrays | ✅ | ✅ | ✅ | ✅ | ✅ |
| Set[T] Storage | ARRAY | ARRAY | JSON | JSON | JSON |
| Seq[T] Storage | ARRAY | ARRAY | JSON | JSON | JSON |
| Map[String,T] | TEXT | JSONB | JSON | JSON | JSON |
| **JSON Support** |
| Native JSON Type | ❌ | ✅ (JSONB) | ✅ | ✅ | ✅ (extension) |
| JSON Cast Required | ❌ | ✅ (`::jsonb`) | ❌ | ❌ | ❌ |
| JSON Operations | Basic | Advanced | Good | Good | Basic |
| **String Matching** |
| Default Case | Sensitive | Sensitive | Insensitive* | Sensitive** | Sensitive |
| LIKE | ✅ | ✅ | ✅ | ✅ | ✅ |
| Regex Function | `REGEXP_LIKE` | `~` / `REGEXP_REPLACE` | `REGEXP` | `REGEXP BINARY` | Custom `REGEXP` |
| **UPSERT** |
| Strategy | MERGE | ON CONFLICT | ON DUPLICATE KEY | ON DUPLICATE KEY | ON CONFLICT |
| Supported | ✅ | ✅ | ✅ | ✅ | ✅ |
| **Aggregate Functions** |
| MIN/MAX | ✅ | ✅ | ✅ | ✅ | ✅ |
| SUM/AVG | ✅ | ✅ | ✅ | ✅ | ✅ |
| COUNT | ✅ | ✅ | ✅ | ✅ | ✅ |
| MEDIAN | ✅ | ✅ (`percentile_cont`) | ✅ | ✅ | ⚠️ (client-side) |
| VARIANCE | ✅ | ✅ (`VAR_POP`) | ✅ | ✅ | ⚠️ (client-side) |
| STDDEV | ✅ | ✅ (`STDDEV_POP`) | ✅ | ✅ | ⚠️ (client-side) |
| **Pagination** |
| LIMIT/OFFSET | ✅ | ✅ | ✅ (special) | ✅ (special) | ✅ (special) |
| Syntax | Standard | Standard | `LIMIT offset, count` | `LIMIT offset, count` | Requires `LIMIT -1` for offset |
| **Type System** |
| Strong Typing | ✅ | ✅ | ✅ | ✅ | ⚠️ (dynamic) |
| UUID Type | ✅ | ✅ (native) | ❌ (TEXT) | ❌ (TEXT) | ❌ (TEXT) |
| Boolean Type | ✅ | ✅ | ⚠️ (TINYINT) | ⚠️ (TINYINT) | ⚠️ (INTEGER) |
| Date Storage | BIGINT | BIGINT | BIGINT | BIGINT | INTEGER |
| Float Storage | DECIMAL | DECIMAL | REAL | REAL | REAL (as Double) |
| **Special Features** |
| Window Functions | ✅ | ✅ | ✅ | ✅ | ✅ |
| CTEs | ✅ | ✅ | ✅ | ✅ | ✅ |
| Subqueries | ✅ (ROW type) | ✅ | ✅ | ✅ | ✅ |
| Full-text Search | ✅ | ✅ | ✅ | ✅ | ✅ (FTS5) |
| **Validation** |
| Update Set Strategy | `validateUpdateSet_array` | `validateUpdateSet_array` | `validateUpdateSet_json` | `validateUpdateSet_json` | `validateUpdateSet_sqlite` |
| **Connection** |
| Auto-commit | Off | Default | Default | Default | Default |
| Init Script | ✅ | ✅ | ✅ | ✅ | ✅ |
| Custom JDBC | ✅ (h2) | ❌ | ❌ | ❌ | ✅ (sqlite) |

\* MySQL: Depends on collation (typically case-insensitive with `utf8_general_ci`)
\** MariaDB: Uses `REGEXP BINARY` for case-sensitive matching

---

## Type Mapping Comparison

### Scalar Types

| Scala Type | H2 | PostgreSQL | MySQL | MariaDB | SQLite |
|------------|----|-----------:|-------|---------|--------|
| String | VARCHAR | VARCHAR | LONGTEXT | LONGTEXT | TEXT |
| Int | INTEGER | INTEGER | INT | INT | INTEGER |
| Long | BIGINT | BIGINT | BIGINT | BIGINT | INTEGER |
| Float | DECIMAL | DECIMAL | REAL | REAL | REAL* |
| Double | DECIMAL | DECIMAL | DOUBLE | DOUBLE | REAL |
| Boolean | BOOLEAN | BOOLEAN | TINYINT(1) | TINYINT(1) | INTEGER |
| BigInt | DECIMAL | DECIMAL | DECIMAL(65,0) | DECIMAL(65,0) | TEXT |
| BigDecimal | DECIMAL | DECIMAL | DECIMAL(65,30) | DECIMAL(65,30) | TEXT |
| Date | BIGINT | BIGINT | BIGINT | BIGINT | INTEGER |
| Duration | VARCHAR | VARCHAR | TINYTEXT | TEXT | TEXT |
| Instant | VARCHAR | VARCHAR | TINYTEXT | TEXT | TEXT |
| LocalDate | VARCHAR | VARCHAR | TINYTEXT | TEXT | TEXT |
| LocalTime | VARCHAR | VARCHAR | TINYTEXT | TEXT | TEXT |
| LocalDateTime | VARCHAR | VARCHAR | TINYTEXT | TEXT | TEXT |
| OffsetTime | VARCHAR | VARCHAR | TINYTEXT | TEXT | TEXT |
| OffsetDateTime | VARCHAR | VARCHAR | TINYTEXT | TEXT | TEXT |
| ZonedDateTime | VARCHAR | VARCHAR | TINYTEXT | TEXT | TEXT |
| UUID | UUID | UUID** | TINYTEXT | TINYTEXT | TEXT |
| URI | VARCHAR | VARCHAR | TEXT | TEXT | TEXT |
| Byte | SMALLINT | SMALLINT | TINYINT | TINYINT | INTEGER |
| Short | SMALLINT | SMALLINT | SMALLINT | SMALLINT | INTEGER |
| Char | TEXT | TEXT | CHAR | CHAR | TEXT |

\* SQLite: Float stored as Double (`v.toString.toDouble`)
\** PostgreSQL: Requires `::uuid` cast in queries

### Collection Types

| Scala Type | H2 | PostgreSQL | MySQL | MariaDB | SQLite |
|------------|----|-----------:|-------|---------|--------|
| Set[T] | T[] | T[] | JSON | JSON | TEXT (JSON) |
| Seq[T] | T[] | T[] | JSON | JSON | TEXT (JSON) |
| Map[String,T] | VARCHAR | JSONB | JSON | JSON | TEXT (JSON) |

---

## Connection Patterns by Effect Type

### Sync
All databases: Direct `Future` wrapping synchronous JDBC calls
```scala
override protected def getJdbcConn(proxy: ConnProxy): Future[JdbcConn_JVM] = {
  Future(
    getNewConnection(proxy.asInstanceOf[JdbcProxy])
  )
}
```

### Async
All databases: Returns `Future[T]` for asynchronous operations

### IO (Cats Effect)
All databases: Wraps operations in `IO[T]`

### ZIO
All databases: Wraps operations in `ZIO[Any, MoleculeError, T]`

---

## Transaction Differences

### Save Operations

**H2 & PostgreSQL**: Native array support
```scala
// H2/PostgreSQL use arrays directly
override protected def addSet[T](...): (PS, Product) => Unit = {
  cast = exts(2) // e.g., "::text" for PostgreSQL
  (ps: PS, tpl: Product) => {
    val conn = ps.getConnection
    val array = conn.createArrayOf(exts(3), set2array(set))
    ps.setArray(paramIndex, array)
  }
}
```

**MySQL, MariaDB, SQLite**: JSON-based storage
```scala
// MySQL/MariaDB/SQLite use JSON
override protected def addSet[T](...): (PS, Product) => Unit = {
  cast = ""
  addIterable(attr, paramIndex, tplIndex, value2json)
}

private def addIterable[T](...)  = {
  val json = iterable2json(iterable, value2json)
  ps.setString(paramIndex, json)
}
```

### Update Operations

**Array Removal (PostgreSQL)**:
```sql
attr = (
  SELECT ARRAY_AGG(_v)
  FROM UNNEST(attr::type[]) AS _v
  WHERE NOT _v = ANY(?::type[])
)
```

**JSON Removal (MySQL)**:
```sql
attr = (
  SELECT JSON_ARRAYAGG(valueTable.v)
  FROM JSON_TABLE($ent.$attr, '$[*]' COLUMNS (v TYPE PATH '$')) valueTable
  WHERE valueTable.v NOT IN (retractValues)
)
```

**JSON Removal (SQLite)**:
```sql
attr = (
  SELECT JSON_GROUP_ARRAY(VALUE)
  FROM (
    SELECT _vs.value
    FROM $ent AS _t, JSON_EACH($attr) AS _vs
    WHERE _t.id = $ent.id AND _vs.VALUE NOT IN (retractValues)
  )
)
```

---

## Migration Considerations

### Moving Between Databases

**Easy Migrations** (minimal changes):
- H2 ↔ PostgreSQL (both have native arrays)
- MySQL ↔ MariaDB (very similar JSON handling)

**Moderate Migrations** (data transformation required):
- PostgreSQL → MySQL/MariaDB (arrays → JSON arrays)
- H2 → MySQL/MariaDB/SQLite (arrays → JSON)

**Complex Migrations**:
- MySQL/MariaDB/SQLite → PostgreSQL/H2 (JSON → native arrays)
  - Requires data migration to convert JSON arrays to native arrays
  - Performance improvements expected

### Data Compatibility

**Portable across all databases:**
- Scalar types (String, Int, Long, Boolean, etc.)
- Date values (all use epoch milliseconds)
- Maps (all use JSON)

**Requires transformation:**
- Sets (ARRAY vs JSON)
- Sequences (ARRAY vs JSON)
- UUID (native type vs TEXT)
- Boolean (native vs TINYINT/INTEGER)

### Performance Considerations

**Best for arrays/collections:**
1. PostgreSQL (native arrays + JSONB)
2. H2 (native arrays)
3. MariaDB (JSON)
4. MySQL (JSON)
5. SQLite (JSON with limited functions)

**Best for JSON operations:**
1. PostgreSQL (JSONB with operators)
2. MySQL / MariaDB (native JSON functions)
3. H2 (basic support)
4. SQLite (JSON1 extension)

**Best for aggregates:**
1. PostgreSQL (full suite including percentile_cont)
2. H2 / MySQL / MariaDB (standard aggregates)
3. SQLite (limited, client-side median/variance)

---

## Testing Coverage

All databases have comprehensive test suites covering:

### Compliance Tests
Located in `/db/compliance/shared/src/test/scala/molecule/db/compliance/test/`

**Test Categories:**
- CRUD operations (insert, save, update, delete)
- Query expressions (equality, comparison, set operations)
- Aggregation functions
- Sorting and pagination
- Optional attributes
- Set/Seq/Map operations
- Binding variables
- Transactions
- Authorization/access control

### Database-Specific Tests
Each database has platform-specific tests:
- `/db/h2/jvm/src/test/scala/molecule/db/h2/compliance/`
- `/db/postgresql/jvm/src/test/scala/molecule/db/postgresql/compliance/`
- `/db/mysql/jvm/src/test/scala/molecule/db/mysql/compliance/`
- `/db/mariadb/jvm/src/test/scala/molecule/db/mariadb/compliance/`
- `/db/sqlite/jvm/src/test/scala/molecule/db/sqlite/compliance/`

### Transaction Tests
Each database has transaction tests for all effect types:
- `Transactions_sync.scala`
- `Transactions_async.scala`
- `Transactions_io.scala`
- `Transactions_zio_.scala`

### Ad-hoc Tests
Development test files for experimentation:
- `Adhoc_xxx_jvm_sync.scala`
- `Adhoc_xxx_jvm_async.scala`
- `Adhoc_xxx_js_async.scala`

---

## Implementation Details

### Validation Strategy Summary

| Database | Method | Collections Stored As |
|----------|--------|----------------------|
| H2 | `validateUpdateSet_array` | ARRAY |
| PostgreSQL | `validateUpdateSet_array` | ARRAY |
| MySQL | `validateUpdateSet_json` | JSON |
| MariaDB | `validateUpdateSet_json` | JSON |
| SQLite | `validateUpdateSet_sqlite` | JSON |

### Query Expression Trait Hierarchy

All databases follow this pattern:
```scala
trait QueryExprOne_xxx extends QueryExprOne with LambdasOne_xxx
trait QueryExprSet_xxx extends QueryExprSet with LambdasSet_xxx
trait QueryExprSeq_xxx extends QueryExprSeq with LambdasSeq_xxx
trait QueryExprMap_xxx extends QueryExprMap with LambdasMap_xxx
trait QueryExprSetRefAttr_xxx extends QueryExprSetRefAttr
```

### Custom Lambda Overrides

Only **MariaDB** and **SQLite** provide custom lambda traits:

**MariaDB** (`Lambdas_mariadb`):
- Boolean stored as TINYINT: `one2sqlBoolean = (v: Boolean) => if (v) "1" else "0"`
- Date stored as BIGINT epoch time

**SQLite** (no custom Lambdas file, but custom handling in Save/Insert):
- Float stored as Double: `transformFloat = (v: Float) => ps.setDouble(n, v.toString.toDouble)`
- Date stored as INTEGER epoch time

---

## Special Implementation Notes

### H2 ROW Type Handling
H2 returns multi-column subqueries as a ROW type, requiring special handling on JVM:
- `Model2SqlQuery_h2_JVM` extends `Model2SqlQuery_h2`
- Custom `RowResultSet_h2_JVM` wraps `org.h2.value.ValueRow`
- Extracts individual fields from ROW and applies casts

### PostgreSQL Precision Handling
PostgreSQL requires explicit rounding and casting for:
- Float comparisons: `ROUND(col::NUMERIC, 7)`
- UUID bind parameters: `CAST(? AS UUID)`
- Aggregates: `ROUND(SUM(col)::NUMERIC, 10)`

### SQLite Custom Functions
SQLite requires registering custom functions on connection:
```scala
Function.create(sqlConn, "REGEXP", new Function() {
  override def callback(narg: Array[String]): Unit = {
    // Custom regex implementation
  }
})
```

### MySQL/MariaDB Pagination
Uses two-argument LIMIT syntax:
```sql
LIMIT offset, count  -- NOT standard SQL
```
Requires special value for "all remaining":
```sql
LIMIT offset, 18446744073709551615  -- Max unsigned BIGINT
```

### SQLite Offset Requirements
SQLite requires LIMIT to use OFFSET:
```sql
LIMIT -1 OFFSET 10  -- Get all rows after offset 10
```

---

## Summary

### Choose H2 When:
- Development/testing environment
- In-memory database needed
- Native array support required
- JVM-only or want embedded database

### Choose PostgreSQL When:
- Production environment
- Advanced JSON operations needed (JSONB)
- Native array support required
- Full aggregate function suite needed
- Best performance for collections

### Choose MySQL When:
- Existing MySQL infrastructure
- JSON collections acceptable
- Wide ecosystem support needed
- Case-insensitive string matching preferred

### Choose MariaDB When:
- MySQL compatibility with enhancements
- Open-source preference
- JSON collections acceptable
- Case-sensitive regex needed

### Choose SQLite When:
- Embedded/serverless deployment
- Mobile or desktop applications
- Minimal setup required
- Single-user or low-concurrency
- File-based storage preferred

---

## References

### Source Code Locations
- **Common**: `/db/common/`
- **H2**: `/db/h2/`
- **PostgreSQL**: `/db/postgresql/`
- **MySQL**: `/db/mysql/`
- **MariaDB**: `/db/mariadb/`
- **SQLite**: `/db/sqlite/`

### Key Files by Database
Each database has this structure:
```
db/xxx/
  ├── js/src/main/scala/molecule/db/xxx/
  │   └── spi/
  │       ├── Spi_xxx_sync.scala
  │       ├── Spi_xxx_async.scala
  │       ├── Spi_xxx_io.scala
  │       └── Spi_xxx_zio.scala
  ├── jvm/src/main/scala/molecule/db/xxx/
  │   ├── spi/                          (same as JS)
  │   ├── transaction/
  │   │   ├── Save_xxx.scala
  │   │   ├── Insert_xxx.scala
  │   │   └── Update_xxx.scala
  │   └── facade/
  │       └── JdbcConn_JVM_xxx.scala   (if custom)
  └── shared/src/main/scala/molecule/db/xxx/
      └── query/
          ├── Model2SqlQuery_xxx.scala
          ├── QueryExprOne_xxx.scala
          ├── QueryExprSet_xxx.scala
          ├── QueryExprSeq_xxx.scala
          ├── QueryExprMap_xxx.scala
          ├── LambdasOne_xxx.scala      (if custom)
          └── Lambdas_xxx.scala         (if custom)
```

### Build Configuration
File: `/build.sbt`
```scala
lazy val dbH2 = crossProject(JSPlatform, JVMPlatform)
lazy val dbMariaDB = crossProject(JSPlatform, JVMPlatform)
lazy val dbMySQL = crossProject(JSPlatform, JVMPlatform)
lazy val dbPostgreSQL = crossProject(JSPlatform, JVMPlatform)
lazy val dbSQlite = crossProject(JSPlatform, JVMPlatform)
```
