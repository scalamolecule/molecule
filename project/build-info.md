# Build cheat sheet


## Compile

Compile to current version only:

    sbt compile
    sbt clean compile

Compile to 2.12 only:

    sbt ++2.12.19 compile
    sbt ++2.12.19 clean compile

Compile to 3.3 only:

    sbt ++3.3.3 compile
    sbt ++3.3.3 clean compile

Compile to all versions:

    sbt +compile
    sbt clean +compile

To have molecule jars generated, add `-Dmolecule=true`

    sbt +compile -Dmolecule=true
    sbt clean +compile -Dmolecule=true
      

## Compile JS

    sbt
    moleculeJS/fastLinkJS


## Publish

    sbt +publishLocal

To maven central with docs generated (required)
    
    sbt +publishSigned -Ddocs=true

Publish versions separately

    sbt ++2.12.19 publishLocal
    sbt ++2.12.19 publishSigned -Ddocs=true


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
                 