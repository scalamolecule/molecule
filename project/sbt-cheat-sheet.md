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

## Changes to molecule and sbt-molecule with snapshot versions

### Develop
1) molecule: make changes
1) `sbt ++2.12.20 "project baseJVM" publishLocal` (using old sbt-molecule version)
1) sbt-molecule: make changes
1) sbt-molecule: `./test.sh`
1) sbt-molecule: `sbt publishLocal`
1) molecule: `sbt moleculeGen`
1) molecule: test all
1) molecule-samples: set snapshot version
1) molecule-samples: `./test.sh`


### Publish snapshots locally

When changes are done and all tests pass, publish snapshots locally. Start with molecule 2.12:

    sbt ++2.12.20 "project baseJVM" publishLocal

Then sbt-molecule:

    sbt publishLocal

And lastly molecule for scala 3:

    sbt publishLocal


### Publish to Sonatype

Sonatype doesn't allow missing library dependencies. So we need to be careful to publish in the right order since we have a circular reference between molecule and sbt-molecule.

1. Set new non-snapshot molecule version in molecule, sbt-molecule and molecule-samples projects (search and replace for all occurrences in subproject sbt files). But keep sbt-molecule snapshot version in molecule plugins settings for now.

2. Publish molecule 2.12 first so that sbt-molecule can then refer to it:

```
sbt ++2.12.20 "project baseJVM" clean publishSigned -Ddocs=true
sbt sonaRelease
```

3. Set non-snapshot sbt-molecule version in sbt-molecule and publish it:

```
sbt clean publishSigned
sbt sonaRelease
```

4. Set new sbt-molecule version in molecule project/plugins. Publish molecule for scala 3 (depends on sbt-molecule):

```
sbt ++3.7.1 clean publishSigned -Ddocs=true
sbt sonaRelease
```

`clean` is used to remove previously staged releases locally before uploading to Sonatype. Otherwise `sbt sonaRelease` tries to release both the old and new versions which will fail.

If something goes wrong, then login to central.sonatype.com, chose Deployments and check the errors.

5. Update molecule README and write release note in project/releases.
 
6. Commit and push molecule, sbt-molecule and molecule-samples to github.

7. Make release on github.

    
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
                 