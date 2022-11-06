# Troubleshooting

Some tricks to handle common compilation errors:


## Weird compilation error

Try to and make the call explicit

Change

    _ <- Ns.int insert 1

to

    _ <- m(Ns.int).insert(1)



## Remove redundant api imports (sometimes created when pasting molecules into code)
    
Change

    import molecule.datomic.api.in1_out12._
    import molecule.datomic.api.out7._

to

    import molecule.datomic.api.in1_out12._


Sometimes an unnecessary import of Seq is added which can mess up. So simply remove this: 

    import scala.collection.Seq


## Some command on a molecule is "red"/not available

Make sure that a sufficiently large api arity is imported
    
    import molecule.datomic.api.out2._
    _ <- Ns.int.str.long.get // <-- `get` not available since molecule is of arity 3 and import of 2

Change to:

    import molecule.datomic.api.out3._
    _ <- Ns.int.str.long.get // ok


## Molecule boilerplate code in lib jar not available

Right click folder containing jars (like `2.13`) and "Add as Library..." 


## Stack overflow

Todo: understand better. But this configuration (Preferences > Build, Execution, Deployment > Compiler > Shared build process VM options) seems to help avoid it:

    -server
    -Xms8G
    -Xmx8G
    -Xss4m
    -XX:NewSize=512m
    -XX:MaxNewSize=512m
    -XX:+UseParNewGC
    -XX:ParallelGCThreads=4
    -XX:MaxTenuringThreshold=1
    -XX:SurvivorRatio=8
    -XX:+UseCodeCacheFlushing
    -XX:+UseConcMarkSweepGC
    -XX:+AggressiveOpts
    -XX:+CMSClassUnloadingEnabled
    -XX:+CMSIncrementalMode
    -XX:+CMSIncrementalPacing
    -XX:+CMSParallelRemarkEnabled
    -XX:CMSInitiatingOccupancyFraction=65
    -XX:+CMSScavengeBeforeRemark
    -XX:+UseCMSInitiatingOccupancyOnly
    -XX:ReservedCodeCacheSize=64m
    -XX:-TraceClassUnloading
    -ea
    -Dsun.io.useCanonCaches=false

And then give the heap size a healthy amount (+10000).