import org.scalajs.linker.interface.ESVersion

val moleculeVersion = "0.19.0"

val scala212 = "2.12.20"
val scala3   = "3.3.5"

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


name := "molecule"
inThisBuild(
  List(
    organization := "org.scalamolecule",
    organizationName := "ScalaMolecule",
    organizationHomepage := Some(url("http://www.scalamolecule.org")),
    versionScheme := Some("early-semver"),
    version := moleculeVersion,
    scalaVersion := scala3,
    publishTo := Some(releases),

    // Run tests for all systems sequentially to avoid data locks with db
    // Only applies on JVM. On the JS platform there's no parallelism anyway.
    Test / parallelExecution := false,
  )
)


lazy val root = project
  .in(file("."))
  .settings(publish / skip := true)
  .enablePlugins(ScalaJSPlugin)
  .aggregate(
    base.js,
    base.jvm,
    core.js,
    core.jvm,
    coreTests.js,
    coreTests.jvm,

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

    serverHttp4s,
    serverNetty,
    serverPekko,
    serverPlay,
    serverVertX,
    serverZioHttp,
    server,
    //    graphql.js,
    //    graphql.jvm,
  )

lazy val base = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("base"))
  .settings(compilerArgs, doPublish,
    // 2.12 for sbt-molecule plugin on sbt 1.x
    crossScalaVersions := Seq(scala212, scala3),
    name := "molecule-base"
  )

lazy val core = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("core"))
  .settings(compilerArgs, doPublish,
    name := "molecule-core",
    libraryDependencies ++= Seq(
      // logging
      "com.outr" %%% "scribe" % "3.16.1",

      // RPC
      "io.suzaku" %%% "boopickle" % "1.5.0",
      "com.softwaremill.sttp.tapir" %%% "tapir-core" % tapirVersion,

      // Streaming + effects
      "com.lihaoyi" %%% "geny" % "1.1.1",
      "dev.zio" %%% "zio-streams" % zioVersion, // + ZIO
      "co.fs2" %%% "fs2-core" % "3.12.0", // + cats IO
    ),
  )
  .jsSettings(
    libraryDependencies ++= Seq(
      "com.softwaremill.sttp.tapir" %%% "tapir-sttp-client4" % tapirVersion,
    ),
  )
  .dependsOn(base)


lazy val coreTests = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .enablePlugins(MoleculePlugin)
  .settings(compilerArgs,
    publish / skip := true,
    name := "molecule-coreTests",
    libraryDependencies ++= Seq(
      "com.zaxxer" % "HikariCP" % "6.2.1" % Test,
      "org.scalactic" %%% "scalactic" % "3.2.19" % Test, // Tolerant roundings with triple equal on js platform
      "io.github.cquiroz" %%% "scala-java-time" % "2.6.0", // % Test, // we need main for time zone plugin

      // Test frameworks
      "org.scalameta" %%% "munit" % "1.1.0" % Test,
      "org.typelevel" %%% "munit-cats-effect" % "2.1.0" % Test,
      "dev.zio" %%% "zio-test" % zioVersion % Test,
      "dev.zio" %%% "zio-test-sbt" % zioVersion % Test,
      //      "dev.zio" %%% "zio-streams" % zioVersion % Test,
    ),
  )
  .jsConfigure(_.enablePlugins(TzdbPlugin))
  .jsSettings(
    jsEnvironment,
    libraryDependencies ++= Seq(
      //      "org.scala-js" %%% "scalajs-dom" % "2.8.0",
      "org.scala-js" %%% "scala-js-macrotask-executor" % "1.1.1",
      "org.scala-js" %%% "scalajs-java-securerandom" % "1.0.0" cross CrossVersion.for3Use2_13
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
  .dependsOn(core)


lazy val dbDatalogCore = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("db/datalog/core"))
  .settings(compilerArgs, doPublish,
    name := "molecule-db-datalog-core"
  )
  .jvmSettings(
    libraryDependencies += "com.datomic" % "peer" % "1.0.7277" // Requires Java 11
  )
  .jsSettings(jsEnvironment)
  .dependsOn(core)


lazy val dbDatalogDatomic = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("db/datalog/datomic"))
  .settings(compilerArgs, doPublish,
    name := "molecule-db-datalog-datomic",
    testFrameworks := testingFrameworks
  )
  .jsSettings(jsEnvironment)
  .dependsOn(dbDatalogCore, coreTests % "test->test")


lazy val dbSqlCore = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("db/sql/core"))
  .settings(compilerArgs, doPublish,
    name := "molecule-db-sql-core",
    libraryDependencies ++= Seq(
      // For json de-serialisation in molecule.db.sql.core.query.LambdasMap
      "com.lihaoyi" %%% "upickle" % "4.0.2",
    ),
  )
  .jsSettings(jsEnvironment)
  .dependsOn(core)


lazy val dbSqlH2 = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("db/sql/h2"))
  .settings(compilerArgs, doPublish,
    name := "molecule-db-sql-h2",
    testFrameworks := testingFrameworks
  )
  .jsSettings(
    jsEnvironment,
    //    libraryDependencies ++= Seq(
    //      "org.scala-js" %%% "scalajs-dom" % "2.8.0",
    //      //      "org.scala-js" %%% "scala-js-macrotask-executor" % "1.1.1",
    //      //      "org.scala-js" %%% "scalajs-java-securerandom" % "1.0.0" cross CrossVersion.for3Use2_13
    //    )
  )
  .jvmSettings(
    libraryDependencies ++= Seq(
      "com.h2database" % "h2" % "2.3.232",
    ),
    Test / fork := true // necessary for sbt testing
  )
  .dependsOn(dbSqlCore, coreTests % "test->test")


lazy val dbSqlMariaDB = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("db/sql/mariadb"))
  .settings(compilerArgs, doPublish,
    name := "molecule-db-sql-mariadb",
    testFrameworks := testingFrameworks)
  .jsSettings(jsEnvironment)
  .jvmSettings(
    libraryDependencies ++= Seq(
      "com.dimafeng" %% "testcontainers-scala-mariadb" % dimafengContainerVersion,
      "org.mariadb.jdbc" % "mariadb-java-client" % "3.5.1",
      "ch.qos.logback" % "logback-classic" % logbackVersion % Test
    ),
    Test / fork := true
  )
  .dependsOn(dbSqlCore, coreTests % "test->test")


lazy val dbSqlMySQL = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("db/sql/mysql"))
  .settings(compilerArgs, doPublish,
    name := "molecule-db-sql-mysql",
    testFrameworks := testingFrameworks)
  .jsSettings(jsEnvironment)
  .jvmSettings(
    libraryDependencies ++= Seq(
      "org.testcontainers" % "mysql" % testContainerVersion,
      "com.mysql" % "mysql-connector-j" % "9.2.0",
    ),
    Test / fork := true
  )
  .dependsOn(dbSqlCore, coreTests % "test->test")


lazy val dbSqlPostgreSQL = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("db/sql/postgres"))
  .settings(compilerArgs, doPublish,
    name := "molecule-db-sql-postgres",
    testFrameworks := testingFrameworks)
  .jsSettings(jsEnvironment)
  .jvmSettings(
    libraryDependencies ++= Seq(
      "org.testcontainers" % "postgresql" % testContainerVersion,
      "org.postgresql" % "postgresql" % "42.7.5",
      "ch.qos.logback" % "logback-classic" % logbackVersion % Test,
    ),
    Test / fork := true
  )
  .dependsOn(dbSqlCore, coreTests % "test->test")


lazy val dbSqlSQlite = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("db/sql/sqlite"))
  .settings(compilerArgs, doPublish,
    name := "molecule-db-sql-sqlite",
    testFrameworks := testingFrameworks
  )
  .jsSettings(jsEnvironment)
  .jvmSettings(
    libraryDependencies ++= Seq(
      "org.xerial" % "sqlite-jdbc" % "3.49.1.0"
    ),
    Test / fork := true
  )
  .dependsOn(dbSqlCore, coreTests % "test->test")


// Tapir example backend servers to test Molecule RPC
lazy val server = project
  .in(file("server"))
  .settings(
    publish / skip := true,
    dependencyOverrides ++= Seq(
      "com.fasterxml.jackson.core" % "jackson-databind" % jacksonVersion,
      //      "com.fasterxml.jackson.core" % "jackson-core" % jacksonVersion,
      //      "com.fasterxml.jackson.core" % "jackson-annotations" % jacksonVersion,
      //      "com.fasterxml.jackson.datatype" % "jackson-datatype-jsr310" % jacksonVersion,
      //      "com.fasterxml.jackson.datatype" % "jackson-datatype-jdk8" % jacksonVersion,
      //      "com.fasterxml.jackson.module" %% "jackson-module-scala" % jacksonVersion,
      //      "com.fasterxml.jackson.module" % "jackson-module-parameter-names" % jacksonVersion,
      //      "com.fasterxml.jackson.dataformat" % "jackson-dataformat-cbor" % jacksonVersion
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

//    serverArmeria % "test->test",
    serverHttp4s % "test->test",
    serverNetty % "test->test",
    serverPekko % "test->test",
    serverPlay % "test->test",
    serverVertX % "test->test",
    serverZioHttp % "test->test",
  )


lazy val serverCore = project
  .in(file("server/core"))
  .settings(
    publish / skip := true,
    libraryDependencies ++= Seq(
      "com.softwaremill.sttp.tapir" %% "tapir-core" % tapirVersion,
    ),
  )
  .dependsOn(core.jvm)

lazy val serverPekko = project
  .in(file("server/pekko"))
  .settings(
    name := "molecule-server-pekko",
    publish / skip := true,
    libraryDependencies ++= Seq(
      // Enforce using same Pekko versions
      "org.apache.pekko" %% "pekko-actor-typed" % pekkoVersion,
      "org.apache.pekko" %% "pekko-serialization-jackson" % pekkoVersion,

      "com.softwaremill.sttp.tapir" %% "tapir-pekko-http-server" % tapirVersion,
    ),
  )
  .dependsOn(serverCore)


lazy val serverHttp4s = project
  .in(file("server/http4s"))
  .settings(
    publish / skip := true,
    libraryDependencies ++= Seq(
      "org.http4s" %% "http4s-ember-server" % http4sVersion,
      "com.softwaremill.sttp.tapir" %% "tapir-http4s-server" % tapirVersion,
    ),
  )
  .dependsOn(serverCore)

lazy val serverNetty = project
  .in(file("server/netty"))
  .settings(
    publish / skip := true,
    libraryDependencies ++= Seq(

      //      "com.softwaremill.sttp.tapir" %% "tapir-netty-server" % tapirVersion,
      //      "com.softwaremill.sttp.tapir" %% "tapir-netty-server" % tapirVersion
      //        exclude("io.netty", "netty-handler"),

      //      "com.softwaremill.sttp.tapir" %% "tapir-ztapir" % tapirVersion,

      //      "org.typelevel" %% "cats-effect" % catsVersion,
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

lazy val serverVertX = project
  .in(file("server/vertx"))
  .settings(
    publish / skip := true,
    libraryDependencies ++= Seq(

      //      "org.apache.pekko" %% "pekko-actor-typed" % pekkoVersion,
      //      "org.apache.pekko" %% "pekko-serialization-jackson" % pekkoVersion,

      "org.scala-lang.modules" %% "scala-java8-compat" % "1.0.2",

      "com.softwaremill.sttp.tapir" %% "tapir-vertx-server" % tapirVersion,
    ),
  )
  .dependsOn(serverCore)

lazy val serverZioHttp = project
  .in(file("server/zioHttp"))
  .settings(
    publish / skip := true,
    libraryDependencies ++= Seq(
      "com.softwaremill.sttp.tapir" %% "tapir-zio-http-server" % tapirVersion,
    ),
  )
  .dependsOn(serverCore)


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


lazy val snapshots =
  "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"

lazy val releases =
  "Sonatype OSS Staging" at "https://oss.sonatype.org/service/local/staging/deploy/maven2/"

lazy val doPublish =
  if (sys.props.get("docs").contains("true")) withDocs else withoutDocs

lazy val withDocs = Def.settings(
  publishMavenStyle := true,
  publishTo := (if (isSnapshot.value) Some(snapshots) else Some(releases)),
  Test / publishArtifact := false,
  Compile / doc / scalacOptions ++= Seq(
    "-doc-root-content",
    baseDirectory.value + "/src/main/scaladoc/rootdoc.txt",
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
  pomIncludeRepository := (_ => false),
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
      id = "marcgrue",
      name = "Marc Grue",
      email = "marcgrue@gmail.com",
      url = url("http://marcgrue.com")
    )
  )
)

lazy val withoutDocs = Def.settings(
  Test / publishArtifact := false,
  doc / sources := Seq.empty,
  packageDoc / publishArtifact := false
)


//lazy val graphql = crossProject(JSPlatform, JVMPlatform)
//  .crossType(CrossType.Full)
//  .in(file("graphql/client"))
//  .settings(name := "molecule-graphql-client")
//  .settings(doPublish)
//  .settings(compilerArgs)
//  .enablePlugins(MoleculePlugin)
//  .settings(
//    // Generate Molecule boilerplate code for tests with `sbt clean compile -Dmolecule=true`
//    moleculePluginActive := sys.props.get("molecule").contains("true"),
//    //    moleculeMakeJars := !sys.props.get("moleculeJars").contains("false"), // default: true
//    moleculeMakeJars := false, // default: true
//
//    // Multiple directories with data models
//    moleculeDataModelPaths := Seq(
//      "molecule/graphql/client"
//    ),
//
//    // Suppress "un-used" keys warning
//    Global / excludeLintKeys ++= Set(
//      moleculePluginActive,
//      moleculeDataModelPaths,
//      moleculeMakeJars
//    ),
//
//    // Find scala version specific jars in respective libs
//    unmanagedBase := {
//      CrossVersion.partialVersion(scalaVersion.value) match {
//        case Some((2, 13)) => file(unmanagedBase.value.getPath ++ "/2.13")
//        case Some((2, 12)) => file(unmanagedBase.value.getPath ++ "/2.12")
//        case _             => file(unmanagedBase.value.getPath ++ "/3.3")
//      }
//    },
//    //    testFrameworks += new TestFramework("utest.runner.Framework"),
//  )
//  .settings(
//    libraryDependencies ++= Seq(
//      "com.github.ghostdogpr" %% "caliban-tools" % "2.5.2",
//      "com.github.ghostdogpr" %% "caliban-client" % "2.5.2",
//    ),
//  )
//  .jsSettings(jsEnvironment)
//  .dependsOn(core)
//  .dependsOn(coreTests % "test->test")

