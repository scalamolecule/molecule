![](project/resources/Molecule-logo.png)

Molecule is a Scala library to query SQL and NoSQL databases with the words of your domain.

Compose an immutable "molecule" data structure:
```scala
Person.name.age.Address.street
```

instead of building queries
<table>
<tr>
<td> SQL </td> <td> Datalog </td>
</tr>
<tr>
<td valign="top">

```sql
SELECT
  Person.name,
  Person.age,
  Address.street
FROM Person
  INNER JOIN Address 
    ON Person.address = Address.id;
```
</td>
<td valign="top">

```clojure
[:find ?name ?age ?street
 :where [?a :Person/name ?name]
        [?a :Person/age ?age]
        [?a :Ns/address ?b]
        [?b :Address/street ?street]]
```
</td>
</tr>
</table>

and get typed data matching the molecule from the database:

```scala
val persons: List[(String, Int, String)] =
  Person.name.age.Address.street.query.get
```
Data can also be fetched asynchronously in a `Future` or `ZIO`.


### Main features of Molecule

- Support for [PostgreSQL](https://www.postgresql.org), [SQlite](https://sqlite.org), [MySQL](https://www.mysql.com), [MariaDB](https://mariadb.com), [H2](https://h2database.com/html/main.html) and [Datomic](http://www.datomic.com). More can easily be added.
- Molecules for any database behave identically (each pass the same 1400+ test suite)
- Targets Scala 3.3, 2.13 and 2.12 on JVM and JS platforms
- Multiple APIs: synchronous, asynchronous and ZIO
- All Scala primitive types and collection types available as molecule attributes
- Rich data structure elements:
    - Nested data structures
    - Validation
    - Pagination
    - Sorting
    - Subscriptions
- Typed database calls directly from Client with no need for Server implementation or JSON encoding/decoding
- Fast transparent binary serialization between Client and Server with [Boopickle](https://boopickle.suzaku.io) (no
  manual setup)
- No macros
- No complex type class implicits
- Maximum type inference


### How does it work?

1) Define a domain data model with Molecule's meta DSL
```scala
object Refs extends DataModel(5) {

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
2) Run `sbt compile -Dmolecule=true` once to generate molecule-enabling boilerplate code from
   your [domain data model definition](https://github.com/scalamolecule/molecule/tree/main/coreTests/shared/src/main/scala/molecule/coreTests/dataModels/core/dataModel).
   The [sbt-molecule](https://github.com/scalamolecule/sbt-molecule) plugin automatically also creates database schemas
   for all database types.
3) Now you can easily read and write from the database with plain vanilla Scala code in a fluent style (see examples below).


### Examples

Same molecule query in all APIs returns the same data in different type wrappings:

Synchronous API, Datomic

```scala
import molecule.datalog.datomic.sync._

val persons: List[(String, Int, String)] =
  Person.name.age.Address.street.query.get
```

Synchronous API, PostgreSQL

```scala
import molecule.sql.postgres.sync._

val persons: List[(String, Int, String)] =
  Person.name.age.Address.street.query.get
```

Asynchronous API

```scala
import molecule.sql.postgres.async._

val persons: Future[List[(String, Int, String)]] =
  Person.name.age.Address.street.query.get
```

ZIO API

```scala
import molecule.sql.postgres.zio._

val persons: ZIO[Conn, MoleculeError, List[(String, Int, String)]] =
  Person.name.age.Address.street.query.get
```

Save one entity

```scala
Person.name("Bob").age(42).Address.street("Baker st").save.transact
```

Insert multiple entities

```scala
Person.name.age.Address.street.insert(
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
sbt.version = 1.10.0
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

The `coreTests` module in this repo has several data model definitions and more than 1400 tests that show all details of
how molecule can be used. This forms the Service Provider Interface that each database implementation needs to comply to
in order to offer all functionality of Molecule.

### Run jvm tests

Make sure Docker is running to run tests for MariaDB, Mysql and Postgress (Datomic and H2 run in-memory).
For instance on a mac you can start Docker Desktop.

Run the same test suite on jvm targeting various databases:

    sbt sqlPostgresJVM/test
    sbt sqlSQliteJVM/test
    sbt sqlMysqlJVM/test
    sbt sqlMariadbJVM/test
    sbt sqlH2JVM/test
    sbt datalogDatomicJVM/test

### Run js tests

To run tests from the client side with Scala.js, first run a jvm server (Akka Http) in one process:

    sbt sqlPostgresJVM/run

Then in another process/terminal window:

    sbt sqlPostgresJS/test

(Scalajs tests don't work with Scala 3.x yet)

### Test latest snapshot locally

To be completely up-to-date, you can pull the latest snapshot from Github.
Initially you clone the `sbt-molecule` and `molecule` repositories

    git clone https://github.com/scalamolecule/sbt-molecule.git
    cd ..
    git clone https://github.com/scalamolecule/molecule.git

And hereafter you can just pull the latest changes in each repository directory

    cd sbt-molecule
    git pull
    cd ../molecule
    git pull

To generate the boilerplate code with the latest plugin, run the following commands:

    cd molecule
    sbt ++2.12.19 "project baseJVM" publishLocal  # Used by sbt-molecule
    cd ../sbt-molecule
    sbt publishLocal                              # Make the plugin available
    cd ../molecule
    sbt compile -Dmolecule=true                   # Generate boilerplate code

Now the boilerplate code for the core tests is generated and the various test suites can be run from your IDE
(be prepared that it takes a while to compile all the tests for all SPI implementations).

### Author

Marc Grue

### License

Molecule is licensed under the [Apache License 2.0](http://en.wikipedia.org/wiki/Apache_license)