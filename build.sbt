import org.scalajs.linker.interface.ESVersion

val moleculeVersion = "0.20.1-SNAPSHOT"

val scala212 = "2.12.20"
val scala3   = "3.3.6"

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
    boilerplate,

    dbBase.js,
    dbBase.jvm,
    dbCompliance.js,
    dbCompliance.jvm,
    dbCore.js,
    dbCore.jvm,

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

    graphql.js,
    graphql.jvm,
  )

// Generate internal boilerplate code
lazy val boilerplate = project
  .in(file("boilerplate"))
  .settings(publish / skip := true)

lazy val dbBase = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("db/base"))
  .settings(compilerArgs, doPublish,
    name := "molecule-db-base",
    // 2.12 for sbt-molecule plugin on sbt 1.x
    crossScalaVersions := Seq(scala212, scala3),
  )

lazy val dbCore = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("db/core"))
  .settings(compilerArgs, doPublish,
    name := "molecule-db-core",
    libraryDependencies ++= Seq(
      // logging
      "com.outr" %%% "scribe" % "3.16.1",

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
  .dependsOn(dbBase)


lazy val dbCompliance = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("db/compliance"))
  .enablePlugins(MoleculePlugin)
  .settings(compilerArgs,
    name := "molecule-db-compliance",
    publish / skip := true,
    libraryDependencies ++= Seq(
      "com.zaxxer" % "HikariCP" % "6.2.1" % Test,
      "org.scalactic" %%% "scalactic" % "3.2.19" % Test, // Tolerant roundings with triple equal on js platform
      "io.github.cquiroz" %%% "scala-java-time" % "2.6.0", // % Test, // we need main for time zone plugin

      // Test frameworks
      "org.scalameta" %%% "munit" % "1.1.1" % Test,
      "org.typelevel" %%% "munit-cats-effect" % "2.1.0" % Test,
      "dev.zio" %%% "zio-test" % zioVersion % Test,
      "dev.zio" %%% "zio-test-sbt" % zioVersion % Test,
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
  .dependsOn(dbCore)


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
  .dependsOn(dbCore)


lazy val dbDatalogDatomic = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("db/datalog/datomic"))
  .settings(compilerArgs, doPublish,
    name := "molecule-db-datalog-datomic",
    testFrameworks := testingFrameworks
  )
  .jsSettings(jsEnvironment)
  .dependsOn(dbDatalogCore, dbCompliance % "test->test")


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
  .dependsOn(dbCore)


lazy val dbSqlH2 = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("db/sql/h2"))
  .settings(compilerArgs, doPublish,
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
  .settings(compilerArgs, doPublish,
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
  .settings(compilerArgs, doPublish,
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
  .settings(compilerArgs, doPublish,
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
  .settings(compilerArgs, doPublish,
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
  .settings(doPublish,
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


lazy val graphql = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("graphql/client"))
  .settings(name := "molecule-graphql-client")
  //  .settings(doPublish)
  .settings(compilerArgs)
  .enablePlugins(MoleculePlugin)
  .settings(
    //    testFrameworks += new TestFramework("munit.runner.Framework"),
  )
  .jvmSettings(
    libraryDependencies ++= Seq(
      "com.lihaoyi" %% "requests" % "0.9.0",
      "com.lihaoyi" %% "upickle" % "4.2.1",

      "com.github.ghostdogpr" %% "caliban" % "2.10.0",
      "com.github.ghostdogpr" %% "caliban-tools" % "2.10.0",
      "com.github.ghostdogpr" %% "caliban-client" % "2.10.0",
    ),
  )
  .jsSettings(jsEnvironment)
  .dependsOn(dbCore)
  .dependsOn(dbCompliance % "test->test")

