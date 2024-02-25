

![](project/resources/Molecule-logo.png)



Molecule is a Scala library to build queries and transactions with the words of your domain for various databases:

- Document
    - [MongoDB](http://www.mongodb.com)    
- Datalog
    - [Datomic](http://www.datomic.com)
- SQL
    - [PostgreSQL](https://www.postgresql.org)
    - [MySQL](https://www.mysql.com)
    - [MariaDB](https://mariadb.com)
    - [H2](https://h2database.com/html/main.html)


Molecule generates boilerplate code from your domain data model. You can then access multiple databases in a uniform way with
"molecules" like this:

```scala
val persons = Person.name.age.Adress.street.query.get
```
The returned `persons` are typed as `List[(String, Int, String)]` and can also be fetched asynchronously as a `Future` or a `ZIO`. 
Notice how the relationship from Person to Adress is easily created. Much more complex queries can also be created 
easily without knowing the query languages of the underlying databases.


### Features

- Targets Scala 3.3, 2.13 and 2.12 on JVM and JS platforms
- Typed database calls directly from Client with no need for Server implementation or JSON encoding/decoding
- Fast transparent binary serialization between Client and Server with [Boopickle](https://boopickle.suzaku.io) (no manual setup)
- Single SPI of +1800 tests adhered to by each database implementation
- No macros
- No complex type class implicits
- Maximum type inference
- Synchronous/Asynchronous/ZIO APIs
- Scala/Java types transparently mapped to all databases
    - Scala primitives/wrappers
        - `String`
        - `Int`
        - `Long`
        - `Float`
        - `Double`
        - `Boolean`
        - `Byte`
        - `Short`
        - `Char`
        - `BigInt`
        - `BigDecimal`
    - java.util/net
        - `Date`
        - `UUID`
        - `URI`
    - java.time
        - `Duration`
        - `Instant`
        - `LocalDate`
        - `LocalTime`
        - `LocalDateTime`
        - `OffsetTime`
        - `OffsetDateTime`
        - `ZonedDateTime`
- `Set`s of all above types supported as field/column type for all databases
- Collision of valid Scala field/column name with reserved keywords of database transparently resolved
- Nested data structures
- Validation
- Pagination (offset/cursor)
- Sorting
- Subscriptions 

Documentation at [scalamolecule.org](http://scalamolecule.org) still documents the old macro-based version of molecule
but will be updated to the new version. Most concepts overlap.

### How does it work?

1) Define a domain data model.
2) Run `sbt compile -Dmolecule=true` once to generate molecule-enabling boilerplate code from
your [domain data model definition](https://github.com/scalamolecule/molecule/tree/main/coreTests/shared/src/main/scala/molecule/coreTests/dataModels/core/dataModel).
The [sbt-molecule](https://github.com/scalamolecule/sbt-molecule) plugin automatically also creates database schemas for
all database types. Now you can easily read and write data to/from a database with plain vanilla Scala code in a fluent
style (see examples below).
3) Write molecule transactions/queries.

### Examples

Same molecule query in all APIs returns the same data in different type wrappings:

Synchronous API, Datomic

```scala
import molecule.datalog.datomic.sync._

val persons: List[(String, Int, String)] =
  Person.name.age.Adress.street.query.get
```

Synchronous API, PostgreSQL

```scala
import molecule.sql.postgres.sync._

val persons: List[(String, Int, String)] =
  Person.name.age.Adress.street.query.get
```

Asynchronous API

```scala
import molecule.sql.postgres.async._

val persons: Future[List[(String, Int, String)]] =
  Person.name.age.Adress.street.query.get
```

ZIO API

```scala
import molecule.sql.postgres.zio._

val persons: ZIO[Conn, MoleculeError, List[(String, Int, String)]] =
  Person.name.age.Adress.street.query.get
```

Save one entity

```scala
Person.name("Bob").age(42).Adress.street("Baker st").save.transact
```

Insert multiple entities

```scala
Person.name.age.Adress.street.insert(
  ("Bob", 42, "Baker st"),
  ("Liz", 38, "Bond road")
).transact
```

Update

```scala
Person(bobId).age(43).update.transact
```

Delete

```scala
Person(bobId).delete.transact
```

## Get started

Please clone [molecule-samples](https://github.com/scalamolecule/molecule-samples) and use one of the template projects
to get started.

    git clone https://github.com/scalamolecule/molecule-samples.git

### Basic sbt setup

Add the following to your build files:

`project/build.properties`:

```
sbt.version = 1.9.8
```

`project/plugins.sbt`:

```scala
addSbtPlugin("org.scalamolecule" % "sbt-molecule" % "1.7.0")
```

`build.sbt`:

```scala
lazy val yourProject = project.in(file("app"))
  .enablePlugins(MoleculePlugin)
  .settings(
    libraryDependencies ++= Seq(
      // One or more of:
      "org.scalamolecule" %%% "molecule-document-mongodb" % "0.8.0",
      "org.scalamolecule" %%% "molecule-datalog-datomic" % "0.8.0",
      "org.scalamolecule" %%% "molecule-sql-h2" % "0.8.0",
      "org.scalamolecule" %%% "molecule-sql-mariadb" % "0.8.0",
      "org.scalamolecule" %%% "molecule-sql-mysql" % "0.8.0",
      "org.scalamolecule" %%% "molecule-sql-postgres" % "0.8.0",
    ),
    moleculeSchemas := Seq("app") // paths to your data model definitions
  )
```

## Explore

The `coreTests` module in this repo has several data model definitions and more than 1600 tests that show all details of
how molecule can be used. This forms the Service Provider Interface that each database implementation needs to comply to
in order to offer all functionality of Molecule.

### Run jvm tests

Run the same test suite on jvm targeting various databases:

    sbt datalogDatomicJVM/test
    sbt documentMongodbJVM/test
    sbt sqlH2JVM/test
    sbt sqlMariadbJVM/test
    sbt sqlMysqlJVM/test
    sbt sqlPostgresJVM/test

### Run js tests

To run tests from the client side with Scala.js, first run a jvm server (Akka Http) in one process:

    sbt sqlPostgresJVM/run

Then in another process/terminal window:

    sbt sqlPostgresJS/test

(Scalajs tests don't work with Scala 3.x yet)

### Publish locally

To be completely up-to-date, you can pull the latest changes from Github and publish molecule locally (for Scala 2.13):

    git pull
    sbt publishLocal

Or target other Scala versions:

    sbt ++3.3.1 publishLocal
    sbt ++2.12.18 publishLocal

### Author

Marc Grue

### License

Molecule is licensed under the [Apache License 2.0](http://en.wikipedia.org/wiki/Apache_license)