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
- Pagination: offset and cursor
- Advanced sorting
- Subscriptions

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

See tests in coreTest module for complete examples of use.



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

 
### Use in your project

The new molecule library is not yet published to Sonatype. Meanwhile, you can publish molecule locally. As soon molecule is published the following steps are not need anymore.

First you need to clone the sbt-molecule plugin library and publish locally:

    git clone https://github.com/scalamolecule/sbt-molecule.git
    cd sbt-molecule
    sbt publishLocal

Then clone molecule and publish its `base` module separately to Scala 2.12 (the sbt-molecule plugin depends on this) and then the molecule modules themselves to Scala 2.13:

    git clone https://github.com/scalamolecule/molecule.git
    cd molecule
    sbt ++2.12.18 "project baseJVM" publishLocal # is used by the sbt-molecule
    sbt publishLocal # publishing molecule modules to Scala 2.13

Or you can publish the molecule modules to other Scala versions:

    sbt ++3.3.0 publishLocal
    sbt ++2.12.18 publishLocal

Now you can generate molecule boilerplate code with the sbt-molecule plugin that packages the generated code in jars added to the lib folders in your project. You only need to do this once (or when your data model changes).

    cd <your sbt project>
    sbt compile -Dmolecule=true # this flag tells sbt-molecule to generate molecule boilerplate code

See definition of `coreTests` module in `build.sbt` for how to add and configure the Molecule plugin. There you can also see examples of data model definitions that the sbt-molecule plugin needs to know what boilerplate code to generate.


### License

Molecule is licensed under the [Apache License 2.0](http://en.wikipedia.org/wiki/Apache_license)