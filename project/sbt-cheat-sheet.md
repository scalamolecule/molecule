# Sbt cheat sheet

## Compile

    sbt compile
    // or
    sbt clean compile


To have molecule jars generated, add `-Dmolecule=true`

    sbt compile -Dmolecule=true
    // or
    sbt clean compile -Dmolecule=true



## Test JVM

    sbt
    project sqlH2JVM

    // some of:
    test
    testOnly moleculemolecule.db.sql.H2.AdhocJS_H2
    testOnly moleculemolecule.db.sql.H2.*
    testOnly moleculemolecule.db.sql.*

## Test JS

Process 1:

    sbt
    sqlH2JVM/run

Process 2:

    sbt
    project sqlH2JS
    test
    // or
    testOnly moleculemolecule.db.sql.H2.AdhocJS_H2
    testOnly moleculemolecule.db.sql.H2.*
    testOnly moleculemolecule.db.sql.*

Compilation on JS side can take some time and be quite memory hungry. You might give sbt some extra memory:

    sbt -mem 10000


## Changes to molecule and sbt-molecule

1) molecule: make changes
1) `sbt ++2.12.20 "project baseJVM" publishLocal` (using old sbt-molecule version)
1) sbt-molecule: make changes
1) sbt-molecule: `sbt +publishLocal`
1) molecule: `sbt clean compile -Dmolecule=true`
1) molecule: test all

## Publish with sbt-molecule update too

1) Snapshot, molecule: `sbt +publishLocal`
1) Snapshot, sbt-molecule: `./test-all.sh` (with molecule snapshot dependency)
1) Snapshot, molecule-samples: `./test-all.sh` (with molecule snapshot dependency)
1) Snapshot, Fix molecule/sbt-molecule until all tests pass
1) Snapshot, sbt-molecule: `sbt publishLocal` (still snapshot version)
1) Snapshot, molecule: `sbt clean compile -Dmolecule=true`
1) Snapshot, when all molecule tests pass, set molecule build version to new version
1) molecule: `sbt ++2.12.20 "project baseJVM" publishLocal`
1) Set sbt-molecule to new version and `molecule-base` dep to new molecule version in all sbt/sbt test build files
1) sbt-molecule: `sbt publishLocal`
1) sbt-molecule: `sbt publishSigned`
1) molecule: set sbt-molecule plugin version to new plugin version
1) molecule: `sbt +publishSigned -Ddocs=true` (publish for 2.12 and 3.3)
1) commit and push molecule, sbt-molecule and molecule-samples to github
1) molecule github: create release
                           

## Publish without sbt-molecule update

1) Set molecule build version to new version + "-SNAPSHOT"
1) molecule: `sbt +publishLocal`
1) molecule-samples: update to new version
1) molecule-samples: `./test-all.sh`
2) when all tests pass, take away -SNAPSHOT from molecule version in molecule and samples
1) molecule: `sbt +publishSigned -Ddocs=true`
1) commit and push molecule and molecule-samples to github
1) molecule github: create release


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
                 