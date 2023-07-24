The main purpose of this project is to be able to independently change and publish updated source-generating code that the [sbt-molecule plugin](https://github.com/scalamolecule/sbt-molecule) uses to create boilerplate code in the other molecule projects modules.

This way, we can experiment with how the boilerplate code should be constructed in a development loop by re-publishing this project and re-generate the generic boilerplate code in `<molecule-boilerplate>.molecule.boilerplate.api.generic.dsl` (and subsequently all generated domain boilerplate code) until it compiles and has the right structure. After changes, run this:

    sbt ++2.12.18 "project baseJVM" publishLocal

At the top level, this project also contains `DataModel` which is the main entry point API for [defining molecule data models](https://www.scalamolecule.org/setup/data-model/).

(The project is a crossproject to enable sub-crossprojects to depend on it, although `sbt-molecule` plugin only uses the scala 2.12 jvm version).


 