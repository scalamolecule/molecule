import org.scalajs.linker.interface.ESVersion

val moleculeVersion = "0.23.0-SNAPSHOT"

val scala212 = "2.12.20"
val scala3   = "3.3.6"
//val scala3   = "3.7.1"

val catsVersion              = "3.6.0"
val tapirVersion             = "1.11.25"
val pekkoVersion             = "1.1.3"
val http4sVersion            = "0.23.30"
val client4Version           = "4.0.2"
val zioVersion               = "2.1.17"
val dimafengContainerVersion = "0.43.0"
val testContainerVersion     = "1.20.6"
val logbackVersion           = "1.5.0"
val jacksonVersion           = "2.17.3"
val calibanVersion           = "2.10.0"


name := "molecule"
inThisBuild(
  List(
    organization := "org.scalamolecule",
    organizationName := "ScalaMolecule",
    organizationHomepage := Some(url("http://www.scalamolecule.org")),
    versionScheme := Some("early-semver"),
    version := moleculeVersion,
    scalaVersion := scala3,
    publishTo := localStaging.value,

    // Run tests for all systems sequentially to avoid data locks with dbs.
    // Only applies on JVM. On the JS platform there's no parallelism anyway.
    Test / parallelExecution := false,

    //    javaOptions ++= Seq(
    //      "-Xms8G",
    //      "-Xmx40G",
    //      "-Xss8M",
    //      "-XX:+UseG1GC",
    //      "-XX:+ParallelRefProcEnabled",
    //      "-XX:MaxGCPauseMillis=200",
    ////      "-XX:MaxGCPauseMillis=100",
    //      "-XX:G1HeapRegionSize=32m",
    //      "-XX:InitiatingHeapOccupancyPercent=45",
    //    )

    /*
-Xms8G
-Xmx40G
-Xss8M
-XX:+UseG1GC
-XX:+UseZGC
-XX:MaxGCPauseMillis=200
-XX:+ParallelRefProcEnabled
-XX:+UseStringDeduplication
-XX:+UnlockExperimentalVMOptions
-XX:G1HeapRegionSize=32m
-XX:InitiatingHeapOccupancyPercent=45
-Xlog:gc*:file=gc.log
     */

//    Compile / compile / scalacOptions += "-Xmax-inlines:32",
//    maxErrors := 1000,
//    Global / concurrentRestrictions += Tags.limit(Tags.Compile, 2)
  )
)


lazy val root = project
  .in(file("."))
  .settings(publish / skip := true)
  .enablePlugins(ScalaJSPlugin)
  .aggregate(
    boilerplate,

    base.js,
    base.jvm,
    core.js,
    core.jvm,

    dbCore.js,
    dbCore.jvm,

    dbCompliance.js,
    dbCompliance.jvm,

    dbDatalogCore.js,
    dbDatalogCore.jvm,
    dbDatalogDatomic.js,
    dbDatalogDatomic.jvm,

    dbSqlCore.js,
    dbSqlCore.jvm,
    dbSqlH2.js,
    dbSqlH2.jvm,
    dbSqlMariaDB.js,
    dbSqlMariaDB.jvm,
    dbSqlMySQL.js,
    dbSqlMySQL.jvm,
    dbSqlPostgreSQL.js,
    dbSqlPostgreSQL.jvm,
    dbSqlSQlite.js,
    dbSqlSQlite.jvm,

    dbServerHttp4s,
    dbServerNetty,
    dbServerPekko,
    dbServerPlay,
    dbServerZioHttp,
    dbServer,

    graphqlClient.js,
    graphqlClient.jvm,
    graphqlTest.js,
    graphqlTest.jvm,
  )

// Generate internal boilerplate code
lazy val boilerplate = project
  .in(file("boilerplate"))
  .settings(publish / skip := true)


lazy val base = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("base"))
  .settings(compilerArgs, checkPublishing,
    name := "molecule-base",
    // 2.12 for sbt-molecule plugin on sbt 1.x
    crossScalaVersions := Seq(scala212, scala3),
  )


lazy val core = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("core"))
  .settings(compilerArgs, checkPublishing,
    name := "molecule-core",
    libraryDependencies ++= Seq(
      "com.outr" %%% "scribe" % "3.16.1", // logging
      "org.scalactic" %%% "scalactic" % "3.2.19" % Test, // Tolerant roundings with triple equal on js platform
      "org.scalameta" %%% "munit" % "1.1.1" % Test,
      "org.typelevel" %%% "munit-cats-effect" % "2.1.0" % Test,
      "dev.zio" %%% "zio-test" % zioVersion % Test,
      "dev.zio" %%% "zio-test-sbt" % zioVersion % Test,
    ),
  )
  .dependsOn(base)


lazy val dbCore = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("db/core"))
  .settings(compilerArgs, checkPublishing,
    name := "molecule-db-core",
    libraryDependencies ++= Seq(
      // RPC
      "io.suzaku" %%% "boopickle" % "1.5.0",
      "com.softwaremill.sttp.tapir" %%% "tapir-core" % tapirVersion,

      // Streaming + effects
      "com.lihaoyi" %%% "geny" % "1.1.1",
      "dev.zio" %%% "zio-streams" % zioVersion, // includes ZIO
      "co.fs2" %%% "fs2-core" % "3.12.0", // includes cats IO
    ),
  )
  .jsSettings(
    libraryDependencies ++= Seq(
      "com.softwaremill.sttp.tapir" %%% "tapir-sttp-client4" % tapirVersion,
    ),
  )
  .dependsOn(core % "compile->compile;test->test")


lazy val dbCompliance = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("db/compliance"))
  .enablePlugins(MoleculePlugin)
  .settings(compilerArgs,
    name := "molecule-db-compliance",
    publish / skip := true,
    libraryDependencies ++= Seq(
      "com.zaxxer" % "HikariCP" % "6.2.1" % Test,
      "io.github.cquiroz" %%% "scala-java-time" % "2.6.0", // % Test, // we need main for time zone plugin
    ),
  )
  .jsConfigure(_.enablePlugins(TzdbPlugin))
  .jsSettings(
    jsEnvironment,
    libraryDependencies ++= Seq(
      "org.scala-js" %%% "scala-js-macrotask-executor" % "1.1.1",
      "org.scala-js" %%% "scalajs-java-securerandom" % "1.0.0" cross CrossVersion.for3Use2_13 // for randomUUID
    ),
    zonesFilter := { (z: String) =>
      List(
        // Add your time zone to have time-dependent test work correctly...
        "America/Santiago",
        "Pacific/Honolulu",
        "Europe/Stockholm",
      ).contains(z)
    },
  )
  .jvmSettings(
    libraryDependencies ++= Seq(
      // Enforce one version to avoid warnings of multiple dependency versions when running tests
      "org.slf4j" % "slf4j-nop" % "2.0.17" //% Test
    )
  )
  //  .dependsOn(dbCore % "test->test")
  .dependsOn(dbCore % "compile->compile;test->test")
//  .dependsOn(base, core, dbCore)


lazy val dbDatalogCore = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("db/datalog/core"))
  .settings(compilerArgs, checkPublishing,
    name := "molecule-db-datalog-core"
  )
  .jvmSettings(
    libraryDependencies += "com.datomic" % "peer" % "1.0.7277" // Requires Java 11
  )
  .jsSettings(jsEnvironment)
  .dependsOn(dbCore)


lazy val dbDatalogDatomic = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("db/datalog/datomic"))
  .settings(compilerArgs, checkPublishing,
    name := "molecule-db-datalog-datomic",
    testFrameworks := testingFrameworks
  )
  .jsSettings(jsEnvironment)
  .dependsOn(dbDatalogCore, dbCompliance % "test->test")


lazy val dbSqlCore = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("db/sql/core"))
  .settings(compilerArgs, checkPublishing,
    name := "molecule-db-sql-core",
    libraryDependencies ++= Seq(
      // For json de-serialisation in molecule.db.sql.core.query.LambdasMap
      "com.lihaoyi" %%% "upickle" % "4.0.2",
    ),
  )
  .jsSettings(jsEnvironment)
  .dependsOn(dbCore)


lazy val dbSqlH2 = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("db/sql/h2"))
  .settings(compilerArgs, checkPublishing,
    name := "molecule-db-sql-h2",
    testFrameworks := testingFrameworks
  )
  .jsSettings(jsEnvironment)
  .jvmSettings(
    libraryDependencies ++= Seq(
      "com.h2database" % "h2" % "2.3.232",
    ),
    Test / fork := true // necessary for sbt testing
  )
  .dependsOn(dbSqlCore, dbCompliance % "test->test")


lazy val dbSqlMariaDB = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("db/sql/mariadb"))
  .settings(compilerArgs, checkPublishing,
    name := "molecule-db-sql-mariadb",
    testFrameworks := testingFrameworks)
  .jsSettings(jsEnvironment)
  .jvmSettings(
    libraryDependencies ++= Seq(
      "com.dimafeng" %% "testcontainers-scala-mariadb" % dimafengContainerVersion,
      "org.mariadb.jdbc" % "mariadb-java-client" % "3.5.1",
      "ch.qos.logback" % "logback-classic" % logbackVersion % Test,
      "org.slf4j" % "slf4j-nop" % "2.0.17", // avoid slf4j warnings
    ),
    Test / fork := true
  )
  .dependsOn(dbSqlCore, dbCompliance % "test->test")


lazy val dbSqlMySQL = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("db/sql/mysql"))
  .settings(compilerArgs, checkPublishing,
    name := "molecule-db-sql-mysql",
    testFrameworks := testingFrameworks)
  .jsSettings(jsEnvironment)
  .jvmSettings(
    libraryDependencies ++= Seq(
      "org.testcontainers" % "mysql" % testContainerVersion,
      "com.mysql" % "mysql-connector-j" % "9.2.0",
      "org.slf4j" % "slf4j-nop" % "2.0.17", // avoid slf4j warnings
    ),
    Test / fork := true
  )
  .dependsOn(dbSqlCore, dbCompliance % "test->test")


lazy val dbSqlPostgreSQL = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("db/sql/postgres"))
  .settings(compilerArgs, checkPublishing,
    name := "molecule-db-sql-postgres",
    testFrameworks := testingFrameworks)
  .jsSettings(jsEnvironment)
  .jvmSettings(
    libraryDependencies ++= Seq(
      "org.testcontainers" % "postgresql" % testContainerVersion,
      "org.postgresql" % "postgresql" % "42.7.5",
      "ch.qos.logback" % "logback-classic" % logbackVersion % Test,
      //      "org.slf4j" % "slf4j-nop" % "2.0.17", // avoid slf4j warnings
    ),
    Test / fork := true
  )
  .dependsOn(dbSqlCore, dbCompliance % "test->test")


lazy val dbSqlSQlite = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("db/sql/sqlite"))
  .settings(compilerArgs, checkPublishing,
    name := "molecule-db-sql-sqlite",
    testFrameworks := testingFrameworks
  )
  .jsSettings(jsEnvironment)
  .jvmSettings(
    libraryDependencies ++= Seq(
      "org.xerial" % "sqlite-jdbc" % "3.49.1.0",
      "org.slf4j" % "slf4j-nop" % "2.0.17", // avoid slf4j warnings
    ),
    Test / fork := true
  )
  .dependsOn(dbSqlCore, dbCompliance % "test->test")


// CLI to run Tapir example backend servers
lazy val dbServer = project
  .in(file("db/server/cli"))
  .settings(
    publish / skip := true,
    dependencyOverrides ++= Seq(
      "com.fasterxml.jackson.core" % "jackson-databind" % jacksonVersion,
    ),
    libraryDependencies ++= Seq(
      // Avoid "No SLF4J providers were found" errors
      "org.slf4j" % "slf4j-nop" % "2.0.17",
    ),
  )
  .dependsOn(
    dbDatalogDatomic.jvm,
    dbSqlH2.jvm,
    dbSqlMySQL.jvm,
    dbSqlMariaDB.jvm,
    dbSqlPostgreSQL.jvm,
    dbSqlSQlite.jvm,

    dbServerHttp4s % "test->test",
    dbServerNetty % "test->test",
    dbServerPekko % "test->test",
    dbServerPlay % "test->test",
    dbServerZioHttp % "test->test",
  )


lazy val dbServerCore = project
  .in(file("db/server/core"))
  .settings(
    publish / skip := true,
    libraryDependencies ++= Seq(
      "com.softwaremill.sttp.tapir" %% "tapir-core" % tapirVersion,
    ),
  )
  .dependsOn(dbCore.jvm)

lazy val dbServerPekko = project
  .in(file("db/server/pekko"))
  .settings(checkPublishing,
    name := "molecule-db-server-pekko",
    publish / skip := true,
    libraryDependencies ++= Seq(
      // Enforce using same Pekko versions
      "org.apache.pekko" %% "pekko-actor-typed" % pekkoVersion,
      "org.apache.pekko" %% "pekko-serialization-jackson" % pekkoVersion,

      "com.softwaremill.sttp.tapir" %% "tapir-pekko-http-server" % tapirVersion,
    ),
  )
  .dependsOn(dbServerCore)


lazy val dbServerHttp4s = project
  .in(file("db/server/http4s"))
  .settings(
    publish / skip := true,
    libraryDependencies ++= Seq(
      "org.http4s" %% "http4s-ember-server" % http4sVersion,
      "com.softwaremill.sttp.tapir" %% "tapir-http4s-server" % tapirVersion,
    ),
  )
  .dependsOn(dbServerCore)

lazy val dbServerNetty = project
  .in(file("db/server/netty"))
  .settings(
    publish / skip := true,
    libraryDependencies ++= Seq(
      "com.softwaremill.sttp.tapir" %% "tapir-netty-server-cats" % tapirVersion,
    ),
  )
  .dependsOn(dbServerHttp4s)

lazy val dbServerPlay = project
  .in(file("db/server/play"))
  .settings(
    publish / skip := true,
    libraryDependencies ++= Seq(
      "com.softwaremill.sttp.tapir" %% "tapir-play-server" % tapirVersion,
    ),
  )
  .dependsOn(dbServerPekko)

lazy val dbServerZioHttp = project
  .in(file("db/server/zioHttp"))
  .settings(
    publish / skip := true,
    libraryDependencies ++= Seq(
      "com.softwaremill.sttp.tapir" %% "tapir-zio-http-server" % tapirVersion,
    ),
  )
  .dependsOn(dbServerCore)


lazy val graphqlClient = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("graphql/client"))
  .settings(name := "molecule-graphql-client")
  //  .settings(compilerArgs, checkPublishing)
  .settings(publish / skip := true)
  .jvmSettings(
    libraryDependencies ++= Seq(
      "com.lihaoyi" %% "requests" % "0.9.0",
      "com.lihaoyi" %% "upickle" % "4.2.1",

      "dev.zio" %% "zio" % zioVersion,
      "com.github.ghostdogpr" %% "caliban" % calibanVersion,
      "com.github.ghostdogpr" %% "caliban-tools" % calibanVersion,
      "com.github.ghostdogpr" %% "caliban-client" % calibanVersion,
    ),
  )
  .jsSettings(jsEnvironment)
  .dependsOn(core % "compile->compile;test->test")


lazy val graphqlTest = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("graphql/test"))
  .settings(name := "molecule-graphql-test")
  .settings(withoutDocs)
  .enablePlugins(MoleculePlugin)
  .settings(
    publish / skip := true,
    //    testFrameworks += new TestFramework("munit.runner.Framework"),
  )
  .dependsOn(graphqlClient % "compile->compile;test->test", dbCore)


lazy val testingFrameworks = Seq(
  new TestFramework("munit.Framework"),
  new TestFramework("zio.test.sbt.ZTestFramework"),
)

lazy val jsEnvironment = {
  Seq(
    scalaJSLinkerConfig ~= {
      // Allow unicode characters in regex expressions (emailRegex)
      // https://www.scala-js.org/doc/regular-expressions.html
      _.withESFeatures(_.withESVersion(ESVersion.ES2018))
    },
  )
}

lazy val compilerArgs = Def.settings(
  scalacOptions ++= (CrossVersion.partialVersion(scalaVersion.value) match {
    case Some((3, _)) =>
      Seq(
        "-deprecation",
        "-unchecked",
        "-explain-types",
        //        "-explain",
        //    "-Xfatal-warnings",
      )
    case _            => Nil // 2.12 base module for sbt-molecule plugin
  })
)


lazy val checkPublishing = if (sys.props.get("docs").contains("true")) withDocs else withoutDocs

lazy val withDocs = Def.settings(
  publishMavenStyle := true,
  Test / publishArtifact := false,
  Compile / doc / scalacOptions ++= Seq(
    "-doc-root-content",
    (baseDirectory.value / "src" / "main" / "scaladoc" / "rootdoc.txt").toString,
    "-groups",
    "-doc-version",
    version.value,
    "-doc-title",
    "Molecule",
    "-sourcepath",
    (ThisBuild / baseDirectory).value.toString,
    "-doc-source-url",
    s"https://github.com/scalamolecule/molecule/tree/master€{FILE_PATH}.scala#L1"
  ),
  homepage := Some(url("http://scalamolecule.org")),
  licenses := List(License.Apache2),
  scmInfo := Some(
    ScmInfo(
      url("https://github.com/scalamolecule/molecule"),
      "scm:git:git@github.com:scalamolecule/molecule.git"
    )
  ),
  description := "molecule",
  developers := List(
    Developer(
      "marcgrue",
      "Marc Grue",
      "marcgrue@gmail.com",
      url("http://marcgrue.com")
    )
  )
)

lazy val withoutDocs = Def.settings(
  Test / publishArtifact := false,
  doc / sources := Seq.empty,
  packageDoc / publishArtifact := false
)


