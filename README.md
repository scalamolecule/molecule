![Molecule logo](project/resources/Molecule-logo.png)



Molecule is a Scala 3 library for querying and updating SQL databases using a type-safe DSL — not one for SQL, but one generated from your own domain structure.

Most libraries offer a DSL to express _their_ concepts — like SQL or JSON. Molecule inverts that: it builds a DSL directly from your domain structure, letting you model and query data in your own terms.

After defining your domain structure, Molecule generates boilerplate code for your custom DSL. You can then declare *what* data you want using this DSL, and Molecule handles *how* to retrieve or modify it.

Instead of stitching SQL by hand, you compose molecules — immutable, type-safe data models that intuitively describe the structure of the data you’re working with - using the words or your domain.

For instance, get name, age and street address of persons with this molecule
```scala
// Query in your domain terms
Person.name.age.Address.street
```
instead of writing
```sql
-- Equivalent SQL
SELECT
  Person.name,
  Person.age,
  Address.street
FROM Person
  JOIN Address 
    ON Person.address = Address.id;
```

The molecule then gets data back with full static typing:
```scala
val persons: List[(String, Int, String)] =
  Person.name.age.Address.street.query.get
```
Data can also be fetched asynchronously in a `Future`, cats `IO` or `ZIO`.


## Why Molecule?

- Type-safe from end to end — no string queries, no surprises
- Write in your own domain language — not SQL
- Declarative, not imperative — express intent, not mechanics
- Composable and immutable — functional by design
- Cross-platform — JVM and Scala.js with built-in RPC and serialization
- Same behavior across backends — Postgres, MySQL, MariaDB, SQLite, H2


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
    val address  = one[Address]
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

All Molecule queries behave identically across these databases. Each backend passes the same SPI compliance test suite with +1800 tests.


## Features

- Scala 3.7.1 support (JVM + Scala.js)
- Type-safe and composable molecules
- Synchronous and asynchronous APIs:
    - `Future`, `cats.effect.IO`, `ZIO`
- Rich query capabilities:
    - Filtering and aggregation
    - Sorting and pagination (offset/cursor)
    - Optional/nested relationships
    - Validation
    - Subscriptions
- No macros
- No complex type class implicit hierarchies
- Maximum IDE type inference
- No JSON setup for Scala.js — fully automatic binary serialization via [Boopickle](https://github.com/suzaku-io/boopickle)


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
Save(
  Entity(
    INSERT INTO Person (
      name,
      age
    ) VALUES (?, ?)
  )
)
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


#### Quick inspect

There's also a shorthand alternative `i` that you can add like in `query.i.get` in order to both inspect and perform the query. You can use it on the mutations too, but be aware that the mutation will still perform! `inspect` is more safe to use on mutations since it only inspects.



## SBT Setup

`project/build.properties`:
```
sbt.version = 1.11.3
```

`project/plugins.sbt`:
```scala
addSbtPlugin("org.scalamolecule" % "sbt-molecule" % "1.19.3")
```

`build.sbt`:
```scala
lazy val yourProject = project.in(file("app"))
  .enablePlugins(MoleculePlugin)
  .settings(
    libraryDependencies ++= Seq(
      "org.scalamolecule" %% "molecule-db-postgres" % "0.24.0",
      "org.scalamolecule" %% "molecule-db-sqlite" % "0.24.0",
      "org.scalamolecule" %% "molecule-db-mysql" % "0.24.0",
      "org.scalamolecule" %% "molecule-db-mariadb" % "0.24.0",
      "org.scalamolecule" %% "molecule-db-h2" % "0.24.0",
    )
  )
```

Use `%%%` instead of `%%` for Scala.js.


## Explore code

The `dbCompliance` module in this repo has several domain structure definitions and +1800 tests that show all details of
how molecule can be used. This forms the tests that each database implementation needs to comply with
in order to offer all functionality of Molecule and be a compliant implementation.

First, clone the molecule project to your computer (or `git pull` to get the latest changes):
```
git clone https://github.com/scalamolecule/molecule.git
cd molecule
```
Then run some tests on either ScalaJVM or ScalaJS:


### Run JVM tests

Make sure Docker is running to run tests for Postgres, SQlite, Mysql and MariaDB. H2 can be run in memory for tests.
On a mac you can for instance start Docker Desktop.

Run the tests on the jvm with a databases of your choice:

    sbt dbPostgresJVM/test
    sbt dbSQliteJVM/test
    sbt dbMysqlJVM/test
    sbt dbMariadbJVM/test
    sbt dbH2JVM/test


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

In another process you can then run one of the following commands to run the coreTests on ScalaJS with the database of your choice:

```
sbt dbH2JS/test
sbt dbMariaDBJS/test
sbt dbMySQLJS/test
sbt dbPostgreSQLJS/test
sbt dbSQliteJS/test
```
The tests are then automatically fetching data from the running backend - Molecule takes care of marshalling and fetching transparently with boopickle binary serialization!


## Author

Marc Grue

## License

Apache License 2.0