# Build cheat sheet


## Compile

Compile to current version only:

    sbt compile
    sbt clean compile

Compile to 2.12 only:

    sbt ++2.12.18 compile
    sbt ++2.12.18 clean compile

Compile to 3.3 only:

    sbt ++3.3.1 compile
    sbt ++3.3.1 clean compile

Compile to all versions:

    sbt +compile
    sbt clean +compile

To have molecule jars generated, add `-Dmolecule=true`

    sbt +compile -Dmolecule=true
    sbt clean +compile -Dmolecule=true
      

## Compile JS

    sbt
    moleculeJS/fastOptJS


## Publish

    sbt +publishLocal

To maven central with docs generated (required)
    
    sbt +publishSigned -Ddocs=true

Publish versions separately

    sbt ++2.12.18 publishLocal
    sbt ++2.12.18 publishSigned -Ddocs=true

