![Molecule logo](project/resources/Molecule-logo.png)



Molecule is a Scala 3 library that lets you query and mutate SQL databases using type-inferred code written with the words of your domain.

Here's a query across two tables:
```scala
Person.name.age.Address.street
```

No SQL strings, no join syntax, no mapping boilerplate. Just your domain concepts composed together. The compiler ensures you only write valid queries, and you get back typed data:

```scala
val persons: List[(String, Int, String)] =
  Person.name.age.Address.street.query.get
```

You express intent; Molecule translates it into optimized SQL queries and handles the execution.

<details>
<summary>See generated SQL</summary>

```sql
SELECT
  Person.name,
  Person.age,
  Address.street
FROM Person
  INNER JOIN Address
    ON Person.address = Address.id
WHERE
  Person.name IS NOT NULL AND
  Person.age  IS NOT NULL;
```
</details>


## Why Molecule?

- Type-safe from end to end — no string queries, no surprises
- Write in your own domain language — not SQL
- Built-in authorization — move the security boundary to the data layer, not scattered across endpoints
- Declarative, not imperative — express intent, not mechanics
- Composable and immutable — functional by design
- Cross-platform — JVM and Scala.js with built-in RPC and serialization
- Same behavior across backends — Postgres, MySQL, MariaDB, SQLite, H2
- No macros
- No complex type class implicit hierarchies


## Documentation

Full documentation at [scalamolecule.org](https://www.scalamolecule.org)


## Quick start

Clone [molecule-samples](https://github.com/scalamolecule/molecule-samples) to get started:
```
git clone https://github.com/scalamolecule/molecule-samples.git
```


## Core Concepts

### Domain Structure

First, define the entities and attributes of your domain. This structure allows Molecule to generate a minimal and precise DSL tailored to your domain. Here's an example:

```scala
object MyDomain extends DomainStructure { 

  trait Person {
    val name     = oneString
    val age      = oneInt
    val birthday = oneLocalDate 
    val address  = manyToOne[Address]
  }

  trait Address {
    val street = oneString
    val zip    = oneString
    val city   = oneString
  }
}
```
Then run `sbt moleculeGen` - and Molecule generates the necessary DSL code to let you write molecules.


### Data Model

Now you can model data as we saw above. Behind the scenes, for every step in the composition of a molecule - or Data Model, two things happen:

1. The result type is extended by collecting attribute types into a composed tuple.
2. An immutable Data Model is built in order to generate SQL queries and mutations.

Once you've composed the desired data model, you can call `query.get` on it to fetch data, or one of the transaction commands:

- `save.transact`
- `insert(data...).transact` - multiples rows of data can be inserted
- `update.transact`
- `upsert.transact` (insert if not found)
- `delete.transact`


### Declarative, not Imperative

Instead of thinking in SQL terms (tables, joins, indexes), Molecule encourages staying in the mental model of the domain: what domain data are you trying to model?

This separation between *declarative intent* and *imperative implementation* is a core principle of Molecule. You express intent; Molecule translates it to optimized SQL queries and mutations.

## Supported Databases

Molecule supports:

- PostgreSQL
- SQLite
- MySQL
- MariaDB
- H2
- (more can easily be added...)

All Molecule queries behave identically across these databases. Each backend passes the same SPI compliance test suite with +2000 tests.


## Key Features

**Authorization** — Built-in RBAC (Role-Based Access Control) at the attribute level. Define roles and permissions in your domain structure, and Molecule enforces them automatically in all queries and transactions. No need to scatter security logic across endpoints and business code.

**Validation** — Validate data at insertion and update time with built-in validators or custom validation functions. Molecule ensures data integrity before it reaches the database.

**Filtering and Aggregations** — Filter data with intuitive operators and aggregate with functions like `count`, `sum`, `avg`, `min`, and `max`. Compose complex queries without leaving your domain language.

**Sorting and Pagination** — Sort results by any attribute in ascending or descending order. Paginate with offset-based or cursor-based pagination for efficient data loading.

**Optional and Nested Data** — Query optional attributes/relationships and traverse nested relationships naturally. Unlike other SQL libraries that return flat data, Molecule can return hierarchical nested data structures (up to 7 levels deep), eliminating the need to manually group and format results in your application code.

**Transaction Management** — Full transaction support with `unitOfWork` for composing multiple operations, `savepoint` for nested transactions, and `rollback` for fine-grained control. Execute complex transactional workflows with confidence.

**Subscriptions** — Subscribe to data changes and receive real-time updates when your queries match new or modified data. Keep your application state synchronized with the database.


## Examples

### Query

Synchronous (replace `postgres` to use any of the other databases):
```scala
import molecule.db.postgres.sync._

val persons: List[(String, Int, String)] =
  Person.name.age.Address.street.query.get
```

Asynchronous (Future):
```scala
import molecule.db.postgres.async._

val persons: Future[List[(String, Int, String)]] =
  Person.name.age.Address.street.query.get
```

ZIO:
```scala
import molecule.db.postgres.Zio._

val persons: ZIO[Conn, MoleculeError, List[(String, Int, String)]] =
  Person.name.age.Address.street.query.get
```

IO:
```scala
import molecule.db.postgres.io._

val persons: cats.effect.IO[List[(String, Int, String)]] =
  Person.name.age.Address.street.query.get
```

### Transact

Save one entity:
```scala
Person
  .name("Bob")
  .age(42)
  .Address
  .street("Baker st")
  .save.transact
```

Insert multiple entities:
```scala
Person.name.age.Address.street.insert(
  ("Bob", 42, "Baker st"),
  ("Liz", 38, "Bond road")
).transact
```

Update:
```scala
Person(bobId).age(43).update.transact
```

Delete:
```scala
Person(bobId).delete.transact
```


### Inspect

Molecule doesn't hide its inner workings in a magic black box. You can always `inspect` what a molecule translates to:

```scala
Person.name("Bob").age(42).save.inspect
```

Prints:
```
SAVE:
DataModel(...)

INSERT INTO Person (
  name,
  age
) VALUES (?, ?)
```

Query inspection:
```scala
Person.name.age.query.inspect
```

Prints:
```
QUERY:
DataModel(...)

SELECT DISTINCT
  Person.name,
  Person.age
FROM Person
WHERE
  Person.name IS NOT NULL AND
  Person.age  IS NOT NULL;
```

There's also a shorthand alternative `i` that you can add like in `query.i.get` in order to both inspect and perform the query. You can use it on the mutations too, but be aware that the mutation will still perform! `inspect` is more safe to use on mutations since it only inspects.

This way you can always inspect and see what will be sent to the database. And if you want to tweak a query or mutation, Molecule offers fallback alternatives too:


### Fallback

You’re always in control. Molecule provides full fallbacks — inspect what it does, or drop down to raw SQL/mutations when needed.

Query fallback:
```scala
rawQuery(
  """SELECT DISTINCT
     |  Person.name,
     |  Person.age
     |FROM Person
     |WHERE
     |  Person.name IS NOT NULL AND
     |  Person.age  IS NOT NULL;
     |""".stripMargin,
  true // print debug info
).map(_ ==> List(List("Bob", 42)))
```
This gives you back a List of rows where each row is a `List[Any]`. You'll have to cast the data yourself then. Or you can use another SQL library for manual queries, or even raw JDBC.

Transaction fallback:
```scala
rawTransact(
  """INSERT INTO Person (
     |  name,
     |  age
     |) VALUES ('Bob', 42)
     |""".stripMargin)
```
Note that only static input values are supported.

Most of the time you'll likely have enough expressiveness with Molecule without having to resort to manual SQL writing.


## What Molecule is Not

Molecule is not a complete database facade or SQL replacement. It focuses on type-safe queries and transactions from your domain model. Outside its scope:

- Administrative operations (creating indexes, connection pooling)
- Advanced SQL features (subqueries, window functions, CTEs)
- Database migrations and schema management
- Direct JDBC operations

For administrative tasks, use other SQL libraries or JDBC directly alongside Molecule. For advanced SQL features, Molecule provides fallback `rawQuery` and `rawTransact` methods that let you execute raw SQL when needed—you just lose type safety for those specific queries.


## SBT Setup

Molecule is available for Scala 3.7.4+ on JVM and Scala.js. You can add it to your project with sbt:

`project/build.properties`:
```
sbt.version = 1.11.7
```

`project/plugins.sbt`:
```scala
addSbtPlugin("org.scalamolecule" % "sbt-molecule" % "1.22.0")
```

`build.sbt`:
```scala
lazy val yourProject = project.in(file("app"))
  .enablePlugins(MoleculePlugin)
  .settings(
    libraryDependencies ++= Seq(
      // import database(s) that you need
      "org.scalamolecule" %% "molecule-db-h2" % "0.27.0",
      "org.scalamolecule" %% "molecule-db-mariadb" % "0.27.0",
      "org.scalamolecule" %% "molecule-db-mysql" % "0.27.0",
      "org.scalamolecule" %% "molecule-db-postgresql" % "0.27.0",
      "org.scalamolecule" %% "molecule-db-sqlite" % "0.27.0",
    )
  )
```

Use `%%%` instead of `%%` for Scala.js.


## Contributing / Running Tests

The `dbCompliance` module in this repo has several domain structure definitions and +2000 tests that show all details of
how molecule can be used. This forms the tests that each database implementation needs to comply with
in order to offer all functionality of Molecule and be a compliant implementation.

First, clone the molecule project to your computer (or `git pull` to get the latest changes):
```
git clone https://github.com/scalamolecule/molecule.git
cd molecule
```
Then run some tests on either ScalaJVM or ScalaJS:


### Run JVM tests

Make sure Docker is running to run tests for Postgres, SQlite, Mysql and MariaDB. H2 can be run in memory for tests. On a mac you can for instance start Docker Desktop.

Run the tests on the jvm with a databases of your choice:

    sbt dbH2JVM/test
    sbt dbMariaDBJVM/test
    sbt dbMySQLJVM/test
    sbt dbPostgreSQLJVM/test
    sbt dbSQliteJVM/test


### Run JS tests

To test using molecules from ScalaJS, you need to have a ScalaJVM backend server running in a separate process that can receive the queries and send data back to ScalaJS.

In the `server` module you can see 5 different minimal Tapir backend setups that you can start out from. In one process you can start up one of those backends where you will be asked which backend and database that you want to use:

```
sbt server/Test/run

Please choose a database and a server backend to test the Molecule RPC API:

  1  H2
  2  MariaDB
  3  MySQL
  4  PostgreSQL
  5  SQlite

Database: 1

  1  Http4s
  2  Netty
  3  Pekko
  4  Play
  5  ZioHttp

Server: 2

Press ENTER to stop the server.
✅ Netty server running on http://localhost:8080 for H2

// run tests in other process...

🛑 Shutting down server...
```
Now we have a backend running on ScalaJVM ready to take care of your molecule queries from ScalaJS using the H2 database!

In another process you can then run one of the following commands to run the compliance tests on ScalaJS with the database of your choice:

```
sbt dbH2JS/test
sbt dbMariaDBJS/test
sbt dbMySQLJS/test
sbt dbPostgreSQLJS/test
sbt dbSQliteJS/test
```
The tests are then automatically fetching data from the running backend - Molecule takes care of marshalling and fetching transparently with boopickle binary serialization!

You can also run single tests with:

```
sbt
project dbH2JS
testOnly molecule.db.h2.Adhoc_h2_js_async
```


## Author

Marc Grue

## License

Apache License 2.0