(work in progress)

![](project/resources/Molecule-logo.png)

Molecule is a library to write type-inferred Scala code that translates to queries for multiple databases.

An sbt-molecule plugin generates boilerplate code from your domain data model definition so that you can write intuitive type-inferred queries with the words of your domain.

- Works with [Datomic](http://www.datomic.com) and JDBC-compliant databases (and more to come)
- Targets Scala 3.3, 2.13 and 2.12
- Scala and Scalajs platforms
- All 1500+ Datomic tests pass (jdbc module is wip)
- No macros
- No complex type class implicits
- Maximum type inference
- Synchronous/Asynchronous/ZIO APIs
- Nested data structures
- Associative relationships (composites)
- Validation
- Pagination (offset/cursor)
- Sorting
- Subscriptions and more...

Documentation at [scalamolecule.org](http://scalamolecule.org) still documents the old macro-based version of molecule but will be updated to the new version. Most concepts overlap.

This Molecule library is a complete re-write of the old macro-base molecule library that is now archived in the molecule-old github repo. The old molecule library has been under active development for several years. 


### Examples

Same molecule query in all APIs returns the same data in different type wrappings:

Synchronous API, Datomic
```scala
import molecule.datalog.datomic.sync._
val persons: List[(String, Int, String)] = 
  Person.name.age.Adress.street.query.get
```
Synchronous API, JDBC
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
sbt.version=1.9.3
```

`project/plugins.sbt`:

```scala
addSbtPlugin("org.scalamolecule" % "sbt-molecule" % "1.1.0")
```

`build.sbt`:

```scala
lazy val yourProject = project.in(file("app"))
  .enablePlugins(MoleculePlugin)
  .settings(
    libraryDependencies ++= Seq(
      "org.scalamolecule" %% "molecule-datalog-datomic" % "0.1.0",
      // and/or
      "org.scalamolecule" %% "molecule-sql-jdbc" % "0.1.0",
    ),
    moleculeSchemas := Seq("app") // paths to your data model definitions...
  )
```



## Explore

The `coreTests` module in this repo has sevel data model definitions and more than 1500 tests that you can get inspiration from.

 
### Run jvm tests

Run the same test suite on jvm targeting both Datomic and JDBC-compliant databases (in-memory H2 db here):

    sbt datalogDatomicJVM/test

or

    sbt sqlJdbcJVM/test


### Run js tests

To run tests from the client side with Scala.js, first run a jvm server (Akka Http) in one process:

    sbt datalogDatomicJVM/run

Then in another process/terminal window:

    sbt datalogDatomicJS/test

(Scalajs tests don't work with Scala 3.x yet)


### Publish locally

To be completely up-to-date, you can pull the latest changes from Github and publish molecule locally:

    git pull
    sbt publishLocal

Or target other Scala versions:

    sbt ++3.3.0 publishLocal
    sbt ++2.12.18 publishLocal


### Author

Marc Grue

### License

Molecule is licensed under the [Apache License 2.0](http://en.wikipedia.org/wiki/Apache_license)