The main purpose of this project is to be able to independently change and publish updated code that the [sbt-molecule plugin](https://github.com/scalamolecule/sbt-molecule) relies on to create boilerplate code.

So, relatively free from other changes in the molecule project we can publish this module when the sbt-molecule needs updated molecule library code:

    sbt ++2.12.20 "project baseJVM" publishLocal
