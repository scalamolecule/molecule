# molecule2

Macro-free molecule implementation.







### Scala.js

To run tests against Scala.js, first run the jvm server in one process:

    sbt datomicJVM/run

or 

    sbt
    datomicJVM/run

Then in another process/terminal window:

    sbt
    project datomicJS

And then run tests there

    testOnly molecule.datomic.test.Adhoc
    testOnly molecule.datomic.test.expr.*
    test

etc
    