# Sbt cheat sheet

## Compile

Compile to current version only:

    sbt compile
    sbt clean compile

Compile to 2.12 only:

    sbt ++2.12.20 compile
    sbt ++2.12.20 clean compile

Compile to 3.3 only:

    sbt ++3.3.4 compile
    sbt ++3.3.4 clean compile

Compile to all versions:

    sbt +compile
    sbt clean +compile

To have molecule jars generated, add `-Dmolecule=true`

    sbt +compile -Dmolecule=true
    sbt clean +compile -Dmolecule=true

## Compile JS

    sbt
    moleculeJS/fastLinkJS
    Test/moleculeJS/fastLinkJS

## Test

Specific Scala version can be set initially

    sbt ++2.12.15

## Test JVM

    sbt
    project sqlSQliteJVM
    test
    // or
    testOnly moleculemolecule.sql.sqlite.AdhocJS_sqlite
    testOnly moleculemolecule.sql.sqlite.*
    testOnly moleculemolecule.sql.*

## Test JS

Process 1:

    sbt
    sqlSQliteJVM/run

Process 2:

    sbt
    project sqlSQliteJS
    test
    // or
    testOnly moleculemolecule.sql.sqlite.AdhocJS_sqlite
    testOnly moleculemolecule.sql.sqlite.*
    testOnly moleculemolecule.sql.*

Compilation on JS side can take some time.

## Changes to molecule and sbt-molecule

1) molecule: make changes
1) `sbt ++2.12.20 "project baseJVM" publishLocal` (using old sbt-molecule version)
1) sbt-molecule: make changes
1) sbt-molecule: `sbt +publishLocal`
1) molecule: `sbt clean compile -Dmolecule=true`
1) molecule: test all

## Publish with sbt-molecule update too

1) Snapshot, molecule: `sbt +publishLocal` with snapshot version
1) Snapshot, sbt-molecule: `./test-all.sh` with snapshot version and molecule snapshot dependency
1) Snapshot, Fix sbt-molecule until all tests pass
1) Snapshot, sbt-molecule: `sbt publishLocal` (still snapshot version)
1) Snapshot, moleucle: `sbt clean compile -Dmolecule=true`
1) Snapshot, when all molecule tests pass, set molecule build version to new version
1) molecule: `sbt ++2.12.20 "project baseJVM" publishLocal`
1) Set sbt-molecule to new version and `molecule-base` dep to new molecule version in all sbt/sbt test build files
1) sbt-molecule: `sbt publishLocal`
1) sbt-molecule: `sbt publishSigned`
1) molecule: set sbt-molecule plugin version to new plugin version
1) molecule-samples: set new molecule and sbt-molecule versions and compile+test each sample project
1) molecule: `sbt +publishSigned -Ddocs=true`
1) commit and push molecule and molecule-samples to github
1) molecule github: create release

## Publish without sbt-molecule update

1) Set molecule build version to new version
1) molecule: `sbt +publishLocal`
1) molecule-samples: update to new version
1) molecule-samples: `./test-all.sh`
1) molecule: `sbt +publishSigned -Ddocs=true`
1) commit and push molecule and molecule-samples to github
1) molecule github: create release

### Publish versions separately

    sbt ++2.12.20 publishLocal
    sbt ++2.12.20 publishSigned -Ddocs=true

### Misc

To see available paths for tests if generated code is missing on classpath

    sbt
    test:soureDirectories

or inspect

    sbt
    inspect tree compile:managedSourceDirectories
    inspect tree compile:unmanagedSourceDirectories
    inspect tree compile
    inspect tree test:managedSourceDirectories
    inspect tree test:unmanagedSourceDirectories
    inspect tree test

etc..
                 