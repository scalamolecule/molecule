![](project/resources/Molecule-logo.png)

Molecule is a Scala 3 library to query SQL and NoSQL databases with the words of your domain.

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


## Main features of Molecule

- Support for [PostgreSQL](https://www.postgresql.org), [SQlite](https://sqlite.org), [MySQL](https://www.mysql.com), [MariaDB](https://mariadb.com), [H2](https://h2database.com/html/main.html) and [Datomic](http://www.datomic.com) databases. More can easily be added
- Molecules for any database behave identically. Each db pass the same SPI compliance test suite (+1700 tests).
- Targets Scala >=3.3.5 on JVM and JS platforms
- Synchronous, Asynchronous (Future), ZIO and cats.effect.IO APIs
- All Scala primitive types and collection types available as molecule attributes (!)
- Typed methods to compose even complex molecules:
    - Filter/aggregation functions
    - Validation
    - Nested and optional relationships
    - Sorting
    - Pagination (offset/cursor)
    - Subscriptions
- Molecules on ScalaJS side transparently operates server database with no JSON marshalling/wiring setup
- Fast transparent binary serialization between Client and Server with [Boopickle](https://github.com/suzaku-io/boopickle) (no
  manual setup)
- No macros
- No complex type class implicits
- Maximum type inference in IDE to easily choose available attributes/expressions/relationships


## How does it work?

1) Define a model of your domain data with Molecule's meta DSL
```scala
object MyDomain extends DataModel(5) { 

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
2) Run `sbt compile -Dmolecule=true` once to generate molecule-enabling boilerplate code and db schemas.
3) Compose fluent molecules with your domain terms to save and read data from your database.


## Examples

Molecules using any Database/API combination return the same data, just in different wrappings:

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

IO API

```scala
import molecule.sql.postgres.io._

val persons: cats.effect.IO[List[(String, Int, String)]] =
  Person.name.age.Address.street.query.get
```

### Transact data

Save one entity

```scala
Person
  .name("Bob")
  .age(42)
  .Address
  .street("Baker st")
  .save.transact
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

Clone [molecule-samples](https://github.com/scalamolecule/molecule-samples) and use one of the template projects
to get started.

    git clone https://github.com/scalamolecule/molecule-samples.git

## Basic sbt setup

Add the following to your build files:

`project/build.properties`:

```
sbt.version = 1.10.11
```

`project/plugins.sbt`:

```scala
addSbtPlugin("org.scalamolecule" % "sbt-molecule" % "1.13.0")
```

`build.sbt`:

```scala
lazy val yourProject = project.in(file("app"))
  .enablePlugins(MoleculePlugin)
  .settings(
    libraryDependencies ++= Seq(
      // One or more of:
      "org.scalamolecule" %%% "molecule-sql-postgres" % "0.18.0",
      "org.scalamolecule" %%% "molecule-sql-sqlite" % "0.18.0",
      "org.scalamolecule" %%% "molecule-sql-mysql" % "0.18.0",
      "org.scalamolecule" %%% "molecule-sql-mariadb" % "0.18.0",
      "org.scalamolecule" %%% "molecule-sql-h2" % "0.18.0",
      "org.scalamolecule" %%% "molecule-datalog-datomic" % "0.18.0",
    ),
    
    // Paths to directories with your domain structure definition files
    moleculeDomainPaths := Seq("app") 
  )
```

## Explore code

The `coreTests` module in this repo has several data model definitions and +1700 tests that show all details of
how molecule can be used. This forms the Service Provider Interface (SPI) that each database implementation needs to comply with
in order to offer all functionality of Molecule and be a valid implementation.

First, clone the molecule project to your computer (or `git pull` to get the latest changes):
```
git clone https://github.com/scalamolecule/molecule.git
cd molecule
```
Then run some tests on either ScalaJVM or ScalaJS:

### Run JVM tests

Make sure Docker is running to run tests for Postgres, SQlite, Mysql and MariaDB. Datomic and H2 can be run in memory for tests.
On a mac you can for instance start Docker Desktop.

Run the coreTests on the jvm with a databases of your choice:

    sbt sqlPostgresJVM/test
    sbt sqlSQliteJVM/test
    sbt sqlMysqlJVM/test
    sbt sqlMariadbJVM/test
    sbt sqlH2JVM/test
    sbt datalogDatomicJVM/test


### Run JS tests


To test using molecules from ScalaJS, you need to have a ScalaJVM backend server running in a separate process that can receive the queries and send data back to ScalaJS.

In the `server` module you can see 8 different minimal Tapir backend setups that you can start out from and successively add your own api endpoints to. In one process you can start up one of those backends where you will be asked which backend you want to use:

```
sbt server/run

Multiple main classes detected. Select one to run:
 [1] molecule.server.Akka
 [2] molecule.server.Armeria
 [3] molecule.server.Http4s
 [4] molecule.server.Netty
 [5] molecule.server.Pekko
 [6] molecule.server.Play
 [7] molecule.server.VertX
 [8] molecule.server.ZioHttp

Enter number: 
```
Let's enter 4 to use the Netty server:
```
Enter number: 4
[info] running molecule.server.Netty 
Press ENTER to stop the server.
✅ Netty server running on http://localhost:8080
```
Now we have a backend running on ScalaJVM ready to take care of your molecule queries from ScalaJS! 

In another process you can then run one of the following commands to run the coreTests on ScalaJS with the database of your choice:

```
sbt datalogDatomicJS/test
sbt sqlH2JS/test
sbt sqlMariaDBJS/test
sbt sqlMySQLJS/test
sbt sqlPostgreSQLJS/test
sbt sqlSQliteJS/test
```
The tests are then automatically fetching data from the running backend - Molecule takes care of it transparently!



### Author

Marc Grue

### License

Molecule is licensed under the [Apache License 2.0](http://en.wikipedia.org/wiki/Apache_license)