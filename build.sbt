import org.scalajs.linker.interface.ESVersion


val scala212 = "2.12.20"
val scala213 = "2.13.16"
val scala3   = "3.3.5"
val allScala = Seq(scala212, scala213, scala3)

val munitVersion           = "1.1.0"
val munitCatsEffectVersion = "2.1.0"
val tapirVersion           = "1.11.24"
val http4sVersion          = "0.23.17" // latest possible to satisfy blaze + tapir
val pekkoVersion           = "1.1.3"
val catsVersion            = "3.12.0"
val catsEffectVersion      = "3.6.1"
val zioVersion             = "2.0.21"

// Db test containers
val dimafengContainerVersion = "0.43.0"
val testContainerVersion     = "1.20.6"
val logbackVersion           = "1.5.0"


inThisBuild(
  List(
    organization := "org.scalamolecule",
    organizationName := "ScalaMolecule",
    organizationHomepage := Some(url("http://www.scalamolecule.org")),
    versionScheme := Some("early-semver"),
    version := "0.16.0",
    scalaVersion := scala213,
    crossScalaVersions := allScala,

    // Run tests for all systems sequentially to avoid data locks with db
    // Only applies on JVM. On JS platform there's no parallelism anyway.
    Test / parallelExecution := false,

    publishTo := Some(releases)
  )
)

lazy val root = project
  .in(file("."))
  .settings(
    publish / skip := true,
    name := "molecule"
  )
  .enablePlugins(ScalaJSPlugin)
  .aggregate(
    base.js,
    base.jvm,
    core.js,
    core.jvm,
    coreTests.js,
    coreTests.jvm,
    datalogCore.js,
    datalogCore.jvm,
    datalogDatomic.js,
    datalogDatomic.jvm,
    server,
    sqlCore.js,
    sqlCore.jvm,
    sqlH2.js,
    sqlH2.jvm,
    sqlMariaDB.js,
    sqlMariaDB.jvm,
    sqlMySQL.js,
    sqlMySQL.jvm,
    sqlPostgreSQL.js,
    sqlPostgreSQL.jvm,
    sqlSQlite.js,
    sqlSQlite.jvm,
    //    graphql.js,
    //    graphql.jvm,
  )

lazy val base = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .settings(compilerArgs, doPublish,
    name := "molecule-base"
  )


lazy val core = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .settings(compilerArgs, doPublish,
    name := "molecule-core",
    libraryDependencies ++= Seq(
      // logging
      "com.outr" %%% "scribe" % "3.13.0",

      // Effect APIs
      "dev.zio" %%% "zio" % zioVersion,
      "dev.zio" %%% "zio-streams" % zioVersion,
      //      "org.typelevel" %%% "cats-effect" % "3.5.7",
      "org.typelevel" %%% "cats-effect" % catsEffectVersion,

      // RPC
      "io.suzaku" %%% "boopickle" % "1.5.0", // binary serialization
      "com.softwaremill.sttp.tapir" %%% "tapir-core" % tapirVersion,

      // Streaming
      "com.lihaoyi" %%% "geny" % "1.1.1",
      "co.fs2" %%% "fs2-core" % catsVersion,
    )
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

    // Generate Molecule boilerplate code for tests with `sbt clean compile -Dmolecule=true`
    moleculePluginActive := sys.props.get("molecule").contains("true"),
    moleculeDomainPaths := Seq("molecule/coreTests/domains"),
    //    moleculeMakeJars := false,

    // Find scala version specific jars in respective libs
    unmanagedBase := {
      CrossVersion.partialVersion(scalaVersion.value) match {
        case Some((2, 13)) => file(unmanagedBase.value.getPath ++ "/2.13")
        case Some((2, 12)) => file(unmanagedBase.value.getPath ++ "/2.12")
        case _             => file(unmanagedBase.value.getPath ++ "/3.3")
      }
    },

    libraryDependencies ++= Seq(
      "com.zaxxer" % "HikariCP" % "6.2.1" % Test,
      "org.scalactic" %%% "scalactic" % "3.2.19" % Test, // Tolerant roundings with triple equal on js platform
      "io.github.cquiroz" %%% "scala-java-time" % "2.6.0", // % Test, // we need main for time zone plugin

      // Test frameworks
      "org.scalameta" %%% "munit" % munitVersion % Test,
      "org.typelevel" %%% "munit-cats-effect" % munitCatsEffectVersion % Test,

      "dev.zio" %%% "zio-streams" % zioVersion % Test,
      "dev.zio" %%% "zio-test" % zioVersion % Test,
      "dev.zio" %%% "zio-test-sbt" % zioVersion % Test,
    ),
  )
  .jsConfigure(_.enablePlugins(TzdbPlugin))
  .jsSettings(
    jsEnvironment,
    libraryDependencies ++= Seq(
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


lazy val datalogCore = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("datalog/core"))
  .settings(compilerArgs, doPublish,
    name := "molecule-datalog-core"
  )
  .jvmSettings(
    libraryDependencies += "com.datomic" % "peer" % "1.0.7277" // Requires Java 11
  )
  .jsSettings(jsEnvironment)
  .dependsOn(core)


lazy val datalogDatomic = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("datalog/datomic"))
  .settings(compilerArgs, doPublish,
    name := "molecule-datalog-datomic",
    testFrameworks := testingFrameworks
  )
  .jsSettings(jsEnvironment)
  .dependsOn(datalogCore, coreTests % "test->test")


lazy val sqlCore = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("sql/core"))
  .settings(compilerArgs, doPublish,
    name := "molecule-sql-core",
    libraryDependencies ++= Seq(
      // For json de-serialisation in molecule.sql.core.query.LambdasMap
      "com.lihaoyi" %%% "upickle" % "4.0.2",
    ),
  )
  .jsSettings(jsEnvironment)
  .dependsOn(core)


lazy val sqlH2 = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("sql/h2"))
  .settings(compilerArgs, doPublish,
    name := "molecule-sql-h2",
    testFrameworks := testingFrameworks
  )
  .jsSettings(jsEnvironment)
  .jvmSettings(
    libraryDependencies ++= Seq(
      "com.h2database" % "h2" % "2.3.232",
    ),
    Test / fork := true // necessary for sbt testing
  )
  .dependsOn(sqlCore, coreTests % "test->test")


lazy val sqlMariaDB = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("sql/mariadb"))
  .settings(compilerArgs, doPublish,
    name := "molecule-sql-mariadb",
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
  .dependsOn(sqlCore, coreTests % "test->test")


lazy val sqlMySQL = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("sql/mysql"))
  .settings(compilerArgs, doPublish,
    name := "molecule-sql-mysql",
    testFrameworks := testingFrameworks)
  .jsSettings(jsEnvironment)
  .jvmSettings(
    libraryDependencies ++= Seq(
      "org.testcontainers" % "mysql" % testContainerVersion,
      "com.mysql" % "mysql-connector-j" % "9.2.0",
    ),
    Test / fork := true
  )
  .dependsOn(sqlCore, coreTests % "test->test")


lazy val sqlPostgreSQL = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("sql/postgres"))
  .settings(compilerArgs, doPublish,
    name := "molecule-sql-postgres",
    testFrameworks := testingFrameworks)
  .jsSettings(jsEnvironment)
  .jvmSettings(
    libraryDependencies ++= Seq(
      //      "org.slf4j" % "slf4j-api" % "2.0.17",
      //      "org.slf4j" % "slf4j-nop" % "2.0.17", //% Test


      "org.testcontainers" % "postgresql" % testContainerVersion,
      "org.postgresql" % "postgresql" % "42.7.5",
      "ch.qos.logback" % "logback-classic" % logbackVersion % Test,


    ),
    Test / fork := true
  )
  .dependsOn(sqlCore, coreTests % "test->test")


lazy val sqlSQlite = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("sql/sqlite"))
  .settings(compilerArgs, doPublish,
    name := "molecule-sql-sqlite",
    testFrameworks := testingFrameworks
  )
  .jsSettings(jsEnvironment)
  .jvmSettings(
    libraryDependencies ++= Seq(
      "org.xerial" % "sqlite-jdbc" % "3.49.1.0"
    ),
    Test / fork := true
  )
  .dependsOn(sqlCore, coreTests % "test->test")


// Tapir backend examples serving Molecule RPC
lazy val server = project
  .in(file("server"))
  .settings(
    name := "molecule-server",
    publish / skip := true,
    libraryDependencies ++= Seq(
      // 1. Akka Http
      "com.softwaremill.sttp.tapir" %% "tapir-akka-http-server" % tapirVersion,

      // 2. Armeria
      // Lower version to avoid conflicts with pekko!
      "com.softwaremill.sttp.tapir" %% "tapir-armeria-server" % "1.11.9",

      // 3. Http4s
      "org.http4s" %% "http4s-ember-server" % http4sVersion,
      "org.http4s" %% "http4s-blaze-server" % http4sVersion,
      "org.http4s" %% "http4s-dsl" % http4sVersion,
      "org.http4s" %% "http4s-core" % http4sVersion,
      "com.softwaremill.sttp.tapir" %% "tapir-http4s-server" % tapirVersion,

      // 4. Netty
      "com.softwaremill.sttp.tapir" %% "tapir-netty-server" % tapirVersion,

      // 5. Pekko
      // Force same Pekko version
      "org.apache.pekko" %% "pekko-actor-typed" % pekkoVersion,
      "org.apache.pekko" %% "pekko-actor" % pekkoVersion,
      "org.apache.pekko" %% "pekko-serialization-jackson" % pekkoVersion,
      "org.apache.pekko" %% "pekko-protobuf-v3" % pekkoVersion,
      "org.apache.pekko" %% "pekko-slf4j" % pekkoVersion,
      "org.apache.pekko" %% "pekko-stream" % pekkoVersion,
      "com.softwaremill.sttp.tapir" %% "tapir-pekko-http-server" % tapirVersion,

      // 6. Play (with PekkoHttpServer)
      "com.softwaremill.sttp.tapir" %% "tapir-play-server" % tapirVersion,

      // 7. Vert.X
      "com.softwaremill.sttp.tapir" %% "tapir-vertx-server" % tapirVersion,
      "com.softwaremill.sttp.tapir" %% "tapir-zio-http-server" % tapirVersion,

      // 8. ZioHttp
      // Avoid "No SLF4J providers were found." errors
      "org.slf4j" % "slf4j-nop" % "2.0.17"
    )
  )
  .dependsOn(
    sqlH2.jvm,
    sqlMySQL.jvm,
    sqlMariaDB.jvm,
    sqlPostgreSQL.jvm,
    sqlSQlite.jvm,
  )


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
  scalacOptions ++= Seq(
    "-deprecation",
    "-encoding",
    "UTF-8",
    "-feature",
    "-language:higherKinds",
    "-language:existentials",
    "-unchecked",
    //    "-Xfatal-warnings",
  ) ++ (CrossVersion.partialVersion(scalaVersion.value) match {
    case Some((2, 12)) =>
      Seq(
        "-Xsource:2.13",
        "-Ypartial-unification",
        "-Ywarn-extra-implicit",
        "-Ywarn-inaccessible",
        "-Ywarn-infer-any",
        "-Ywarn-unused:-nowarn",
        "-Ywarn-nullary-override",
        "-Ywarn-nullary-unit",
        "-opt-warnings",
        "-opt:l:inline",
        "-explaintypes"
      )
    case Some((2, 13)) =>
      Seq(
        "-Xlint:-byname-implicit",
        "-explaintypes"
      )

    case Some((3, _)) =>
      Seq(
        "-explain-types",
        //        "-Ykind-projector"
        //        "-experimental",
        //        "-rewrite",
        //        "-source:3.4-migration",
      )
    case _            => Nil
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
