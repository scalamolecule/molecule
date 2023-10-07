![](project/resources/Molecule-logo.png)

Molecule is a library to write type-inferred Scala code that translates to queries for various databases.

An sbt-molecule plugin generates boilerplate code from your domain data model definition so that you can write intuitive type-inferred queries with the words of your domain.

- Uniformly works with databases from various query languages:
  - Datalog: [Datomic](http://www.datomic.com) 
  - SQL: [H2](https://h2database.com/html/main.html), [MariaDB](https://mariadb.com), [MySQL](https://www.mysql.com)  and [PostgreSQL](https://www.postgresql.org) (more JDBC-compliant databases to come)
- Targets Scala 3.3, 2.13 and 2.12 on JVM and JS platforms
- Single SPI of 1300+ tests adhered to by each database implementation 
- No macros
- No complex type class implicits
- Maximum type inference
- Synchronous/Asynchronous/ZIO APIs
- Nested data structures
- Validation
- Pagination (offset/cursor)
- Sorting
- Subscriptions and more...

Documentation at [scalamolecule.org](http://scalamolecule.org) still documents the old macro-based version of molecule but will be updated to the new version. Most concepts overlap.

### Examples

Same molecule query in all APIs returns the same data in different type wrappings:

Synchronous API, Datomic
```scala
import molecule.datalog.datomic.sync._
val persons: List[(String, Int, String)] = 
  Person.name.age.Adress.street.query.get
```
Synchronous API, H2
```scala
import molecule.sql.jdbc.sync._
val persons: List[(String, Int, String)] = 
  Person.name.age.Adress.street.query.get
```

Asynchronous API
```scala
import molecule.datalog.datomic.async._
val persons: Future[List[(String, Int, String)]] = 
  Person.name.age.Adress.street.query.get
```

ZIO API
```scala
import molecule.datalog.datomic.zio._
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

Please clone [molecule-samples](https://github.com/scalamolecule/molecule-samples) and use one of the template projects to get started.

    git clone https://github.com/scalamolecule/molecule-samples.git


### Basic sbt setup

Add the following to your build files:

`project/build.properties`:

```scala
sbt.version=1.9.6
```

`project/plugins.sbt`:

```scala
addSbtPlugin("org.scalamolecule" % "sbt-molecule" % "1.5.0")
```

`build.sbt`:

```scala
lazy val yourProject = project.in(file("app"))
  .enablePlugins(MoleculePlugin)
  .settings(
    libraryDependencies ++= Seq(
      // One or more of:
      "org.scalamolecule" %%% "molecule-datalog-datomic" % "0.5.1",
      "org.scalamolecule" %%% "molecule-sql-h2" % "0.5.1",
      "org.scalamolecule" %%% "molecule-sql-mariadb" % "0.5.1",
      "org.scalamolecule" %%% "molecule-sql-mysql" % "0.5.1",
      "org.scalamolecule" %%% "molecule-sql-postgres" % "0.5.1",
    ),
    moleculeSchemas := Seq("app") // paths to your data model definitions...
  )
```



## Explore

The `coreTests` module in this repo has several data model definitions and more than 1300 tests that show all details of how molecule can be used.

 
### Run jvm tests

Run the same test suite on jvm targeting both Datomic and JDBC-compliant databases:

    sbt datalogDatomicJVM/test
    sbt sqlH2JVM/test
    sbt sqlMariadbJVM/test
    sbt sqlMysqlJVM/test
    sbt sqlPostgresJVM/test


### Run js tests

To run tests from the client side with Scala.js, first run a jvm server (Akka Http) in one process:

    sbt datalogDatomicJVM/run

Then in another process/terminal window:

    sbt datalogDatomicJS/test

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