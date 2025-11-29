import org.scalajs.linker.interface.ESVersion

val moleculeVersion = "0.26.1-SNAPSHOT"

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
    scalaVersion := "3.7.4",
    publishTo := localStaging.value,

    // Run tests sequentially to avoid data locks with dbs.
    // Only applies on JVM. On the JS platform there's no parallelism anyway.
    Test / parallelExecution := false,
  )
)

lazy val root = project
  .in(file("."))
  .settings(publish / skip := true)
  .enablePlugins(ScalaJSPlugin)
  .aggregate(
    boilerplate,

    core.js,
    core.jvm,

    dbCommon.js,
    dbCommon.jvm,
    dbCompliance.js,
    dbCompliance.jvm,
    dbH2.js,
    dbH2.jvm,
    dbMariaDB.js,
    dbMariaDB.jvm,
    dbMySQL.js,
    dbMySQL.jvm,
    dbPostgreSQL.js,
    dbPostgreSQL.jvm,
    dbSQlite.js,
    dbSQlite.jvm,

    server,
    serverHttp4s,
    serverNetty,
    serverPekko,
    serverPlay,
    serverZioHttp,

    //    graphqlClient.js,
    //    graphqlClient.jvm,
    //    graphqlTest.js,
    //    graphqlTest.jvm,
  )

// Generate internal boilerplate code
lazy val boilerplate = project
  .in(file("boilerplate"))
  .settings(publish / skip := true)


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


// Db =============================================================================================

lazy val dbCommon = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("db/common"))
  .settings(compilerArgs, checkPublishing,
    name := "molecule-db-common",
    libraryDependencies ++= Seq(
      // RPC
      "io.suzaku" %%% "boopickle" % "1.5.0",
      "com.softwaremill.sttp.tapir" %%% "tapir-core" % tapirVersion,

      // Streaming + effects
      "com.lihaoyi" %%% "geny" % "1.1.1",
      "dev.zio" %%% "zio-streams" % zioVersion, // includes ZIO
      "co.fs2" %%% "fs2-core" % "3.12.0", // includes cats IO

      // For json de-serialisation in molecule.db.core.query.LambdasMap
      "com.lihaoyi" %%% "upickle" % "4.0.2",
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
  .settings(compilerArgs, checkPublishing,
    name := "molecule-db-compliance",
    //    publish / skip := true,
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
  .dependsOn(dbCommon % "compile->compile;test->test")


lazy val dbH2 = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("db/h2"))
  .settings(compilerArgs, checkPublishing,
    name := "molecule-db-h2",
    testFrameworks := testingFrameworks
  )
  .jsSettings(jsEnvironment)
  .jvmSettings(
    libraryDependencies ++= Seq(
      "com.h2database" % "h2" % "2.3.232",
    ),
    Test / fork := true // necessary for sbt testing
  )
  .dependsOn(dbCommon, dbCompliance % "compile->compile;test->test")


lazy val dbMariaDB = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("db/mariadb"))
  .settings(compilerArgs, checkPublishing,
    name := "molecule-db-mariadb",
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
  .dependsOn(dbCommon, dbCompliance % "compile->compile;test->test")


lazy val dbMySQL = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("db/mysql"))
  .settings(compilerArgs, checkPublishing,
    name := "molecule-db-mysql",
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
  .dependsOn(dbCommon, dbCompliance % "compile->compile;test->test")


lazy val dbPostgreSQL = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("db/postgresql"))
  .settings(compilerArgs, checkPublishing,
    name := "molecule-db-postgresql",
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
  .dependsOn(dbCommon, dbCompliance % "compile->compile;test->test")


lazy val dbSQlite = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("db/sqlite"))
  .settings(compilerArgs, checkPublishing,
    name := "molecule-db-sqlite",
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
  .dependsOn(dbCommon, dbCompliance % "compile->compile;test->test")


// Server =============================================================================================

// CLI to run Tapir example backend servers
lazy val server = project
  .in(file("server/cli"))
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

    dbH2.jvm,
    dbMySQL.jvm,
    dbMariaDB.jvm,
    dbPostgreSQL.jvm,
    dbSQlite.jvm,

    dbCompliance.jvm % "test->test",

    serverHttp4s % "test->test",
    serverNetty % "test->test",
    serverPekko % "test->test",
    serverPlay % "test->test",
    serverZioHttp % "test->test",
  )


lazy val serverEndpoints = project
  .in(file("server/endpoints"))
  .settings(
    publish / skip := true,
    libraryDependencies ++= Seq(
      "com.softwaremill.sttp.tapir" %% "tapir-core" % tapirVersion,
    ),
  )
  .dependsOn(dbCommon.jvm)

lazy val serverPekko = project
  .in(file("server/pekko"))
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
  .dependsOn(serverEndpoints)


lazy val serverHttp4s = project
  .in(file("server/http4s"))
  .settings(
    publish / skip := true,
    libraryDependencies ++= Seq(
      "org.http4s" %% "http4s-ember-server" % http4sVersion,
      "com.softwaremill.sttp.tapir" %% "tapir-http4s-server" % tapirVersion,
    ),
  )
  .dependsOn(serverEndpoints)

lazy val serverNetty = project
  .in(file("server/netty"))
  .settings(
    publish / skip := true,
    libraryDependencies ++= Seq(
      "com.softwaremill.sttp.tapir" %% "tapir-netty-server-cats" % tapirVersion,
    ),
  )
  .dependsOn(serverHttp4s)

lazy val serverPlay = project
  .in(file("server/play"))
  .settings(
    publish / skip := true,
    libraryDependencies ++= Seq(
      "com.softwaremill.sttp.tapir" %% "tapir-play-server" % tapirVersion,
    ),
  )
  .dependsOn(serverPekko)

lazy val serverZioHttp = project
  .in(file("server/zioHttp"))
  .settings(
    publish / skip := true,
    libraryDependencies ++= Seq(
      "com.softwaremill.sttp.tapir" %% "tapir-zio-http-server" % tapirVersion,
    ),
  )
  .dependsOn(serverEndpoints)


//// Graphql =========================================================================================
//
//lazy val graphqlClient = crossProject(JSPlatform, JVMPlatform)
//  .crossType(CrossType.Full)
//  .in(file("graphql/client"))
//  .settings(name := "molecule-graphql-client")
//  //  .settings(compilerArgs, checkPublishing)
//  .settings(publish / skip := true)
//  .jvmSettings(
//    libraryDependencies ++= Seq(
//      "com.lihaoyi" %% "requests" % "0.9.0",
//      "com.lihaoyi" %% "upickle" % "4.2.1",
//
//      "dev.zio" %% "zio" % zioVersion,
//      "com.github.ghostdogpr" %% "caliban" % calibanVersion,
//      "com.github.ghostdogpr" %% "caliban-tools" % calibanVersion,
//      "com.github.ghostdogpr" %% "caliban-client" % calibanVersion,
//    ),
//  )
//  .jsSettings(jsEnvironment)
//  .dependsOn(core % "compile->compile;test->test")
//
//
//lazy val graphqlTest = crossProject(JSPlatform, JVMPlatform)
//  .crossType(CrossType.Full)
//  .in(file("graphql/test"))
//  .settings(name := "molecule-graphql-test")
//  .settings(withoutDocs)
//  //  .enablePlugins(MoleculePlugin)
//  .settings(
//    publish / skip := true,
//    //    testFrameworks += new TestFramework("munit.runner.Framework"),
//  )
//  .dependsOn(graphqlClient % "compile->compile;test->test", dbCommon)


// Settings =========================================================================================

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
        //        "-Xfatal-warnings",
        //        "-Xprint:typer",
        //        "-Ylog:typer",
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


