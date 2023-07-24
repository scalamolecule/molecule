# molecule2

Macro-free molecule implementation.







### Scala.js

To run tests against Scala.js, first run the jvm server in one process:

    sbt datalogDatomicJVM/run

or 

    sbt
    datalogDatomicJVM/run

Then in another process/terminal window:

    sbt
    project datalogDatomicJS

And then run tests there

    testOnly molecule.datalog.datomic.test.Adhoc
    testOnly molecule.datalog.datomic.test.expr.*
    test

etc
    