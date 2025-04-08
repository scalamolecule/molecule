import org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv
import org.scalajs.linker.interface.ESVersion
import sbt.Keys.libraryDependencies


val scala212 = "2.12.20"
val scala213 = "2.13.16"
val scala3   = "3.3.5"
val allScala = Seq(scala212, scala213, scala3)

val akkaVersion              = "2.8.3"
val dimafengContainerVersion = "0.43.0"
val logbackVersion           = "1.5.0"
val http4sVersion            = "0.23.30"
//val sttpVersion              = "3.10.3"
val sttpVersion              = "4.0.0-RC4"
val tapirVersion             = "1.11.22"
val testContainerVersion     = "1.20.6"
val zioVersion               = "2.0.21"


//val akkaVersion               = "2.6.20"
val akkaHttpVersion           = "10.2.10"
val catsEffect3Version        = "3.5.7"
val catsMtlVersion            = "1.5.0"
val circeVersion              = "0.14.10"
val fs2Version                = "3.11.0"
val javaTimeVersion           = "2.6.0"
val jsoniterVersion           = "2.33.2"
val laminextVersion           = "0.17.0"
val magnoliaScala2Version     = "1.1.10"
val magnoliaScala3Version     = "1.3.16"
val pekkoHttpVersion          = "1.1.0"
val playVersion               = "3.0.6"
val playJsonVersion           = "3.0.4"
val scalafmtVersion           = "3.8.0"
val zioInteropCats2Version    = "22.0.0.0"
val zioInteropCats3Version    = "23.1.0.4"
val zioInteropReactiveVersion = "2.0.2"
val zioConfigVersion          = "4.0.3"
val zqueryVersion             = "0.7.6"
val zioJsonVersion            = "0.7.39"
val zioHttpVersion            = "3.0.1"
val zioOpenTelemetryVersion   = "3.1.2"

val CompileAndTest = "compile->compile;test->test"


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
  .settings(name := "molecule")
  .settings(publish / skip := true)
  .enablePlugins(ScalaJSPlugin)
  .aggregate(
    base.js,
    base.jvm,
    core.js,
    core.jvm,
    coreTests.js,
    coreTests.jvm,
    adhoc.js,
    adhoc.jvm,
    datalogCore.js,
    datalogCore.jvm,
    datalogDatomic.js,
    datalogDatomic.jvm,
    //    graphql.js,
    //    graphql.jvm,
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
  )

lazy val base = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .settings(name := "molecule-base")
  .settings(compilerArgs)
  .settings(doPublish)


lazy val core = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .settings(name := "molecule-core")
  .settings(doPublish)
  .settings(compilerArgs)
  .settings(
    libraryDependencies ++= Seq(
      "com.outr" %%% "scribe" % "3.13.0", // logging
      "io.suzaku" %%% "boopickle" % "1.5.0", // binary serialization for rpc
      "dev.zio" %%% "zio" % zioVersion, // zio api
      "dev.zio" %%% "zio-streams" % zioVersion,
      "org.typelevel" %%% "cats-effect" % "3.5.7", // cats api

      // Streaming
      "com.lihaoyi" %%% "geny" % "1.1.1",
      "co.fs2" %% "fs2-core" % "3.11.0",
    )
  )
  .jvmSettings(
    libraryDependencies ++= Seq(
      // rpc akka server
      "com.typesafe.akka" %% "akka-stream" % akkaVersion cross CrossVersion.for3Use2_13,
      "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion cross CrossVersion.for3Use2_13,
      "ch.megard" %% "akka-http-cors" % "1.2.0",
    )
  )
  .dependsOn(base)


lazy val coreTests = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .settings(name := "molecule-coreTests")
  .settings(publish / skip := true)
  .settings(compilerArgs)
  .enablePlugins(MoleculePlugin)
  .settings(
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
      "org.scalameta" %%% "munit" % "1.0.3" % Test,
      "org.scalactic" %%% "scalactic" % "3.2.19" % Test, // Tolerant roundings with triple equal on js platform
      "io.github.cquiroz" %%% "scala-java-time" % "2.6.0", // % Test, // we need main for time zone plugin
    ),
  )
  .jsConfigure(_.enablePlugins(TzdbPlugin))
  .jsSettings(
    jsEnvironment,
    libraryDependencies ++= Seq(
      "org.scala-js" %%% "scalajs-dom" % "2.8.0",
      "org.scala-js" %%% "scala-js-macrotask-executor" % "1.1.1",
      "org.scala-js" %%% "scalajs-java-securerandom" % "1.0.0" cross CrossVersion.for3Use2_13
    ),
    zonesFilter := { (z: String) =>
      List(
        // Add your time zone...
        "America/Santiago",
        "Pacific/Honolulu",
        "Europe/Stockholm",
      ).contains(z)
    },
  )
  .jvmSettings(
    libraryDependencies ++= Seq(
      // Enforce one version to avoid warnings of multiple dependency versions when running tests
      "org.slf4j" % "slf4j-api" % "1.7.36",
      "org.slf4j" % "slf4j-nop" % "1.7.36"
    )
  )
  .dependsOn(core)


lazy val adhoc = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .settings(name := "molecule-adhoc")
  .settings(publish / skip := true)
  .settings(compilerArgs)
//  .enablePlugins(MoleculePlugin)
  .settings(
//    // Generate Molecule boilerplate code for tests with `sbt clean compile -Dmolecule=true`
//    moleculePluginActive := sys.props.get("molecule").contains("true"),
//    moleculeDomainPaths := Seq("molecule/adhoc/domains"),
//    //    moleculeMakeJars := false,

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
      "org.scalameta" %%% "munit" % "1.0.3" % Test,
      "org.scalactic" %%% "scalactic" % "3.2.19" % Test, // Tolerant roundings with triple equal on js platform
      "io.github.cquiroz" %%% "scala-java-time" % "2.6.0",
      "io.suzaku" %%% "boopickle" % "1.5.0", // Boopickle serialization

      "com.softwaremill.sttp.client4" %%% "core" % sttpVersion, // STTP core for HTTP client/server
//      "com.softwaremill.sttp.client3" %%% "core" % sttpVersion, // STTP core for HTTP client/server
      //      "com.softwaremill.sttp.client3" %%% "fetch" % sttpVersion,

      //      "com.softwaremill.sttp.tapir" %%% "tapir-core" % tapirVersion // Tapir API

      "com.softwaremill.sttp.tapir" %%% "tapir-sttp-client4" % tapirVersion,



      // Tapir for HTTP endpoints with Http4s support
      "com.softwaremill.sttp.tapir" %%% "tapir-core" % tapirVersion, // Core Tapir API
    ),
  )
  //  .jsConfigure(_.enablePlugins(TzdbPlugin))
  .jsSettings(
//    jsEnvironment,
    libraryDependencies ++= Seq(
      "org.scala-js" %%% "scalajs-dom" % "2.8.0",
      "org.scala-js" %%% "scala-js-macrotask-executor" % "1.1.1",
      "org.scala-js" %%% "scalajs-java-securerandom" % "1.0.0" cross CrossVersion.for3Use2_13,

    ),
    //    zonesFilter := { (z: String) =>
    //      List(
    //        // Add your time zone...
    //        "America/Santiago",
    //        "Pacific/Honolulu",
    //        "Europe/Stockholm",
    //      ).contains(z)
    //    },
    scalaJSUseMainModuleInitializer := true
    //      Compile / mainClass := Some("molecule.adhoc.Main")
    //      Compile / mainClass := Some("Main")

    //        jsEnvInput := jsLinkingInfo.value.mainModule.map(artifact => org.scalajs.jsenv.Input.Script(artifact))
  )
  .jvmSettings(
    libraryDependencies ++= Seq(
      // Enforce one version to avoid warnings of multiple dependency versions when running tests
//      "org.slf4j" % "slf4j-api" % "1.7.36",
//      "org.slf4j" % "slf4j-nop" % "1.7.36",


//      "com.softwaremill.sttp.tapir" %% "tapir-http4s-server-zio" % tapirVersion, // Tapir with Http4s and ZIO

      // Http4s dependencies for the backend
//      "org.http4s" %% "http4s-ember-server" % http4sVersion, // Http4s Ember server
//      "org.http4s" %% "http4s-dsl" % http4sVersion, // Http4s DSL for routing
//      "org.http4s" %% "http4s-blaze-server" % "0.23.17",

      // STTP client for backend (if you need backend HTTP requests)
//      "com.softwaremill.sttp.client3" %% "async-http-client-backend-zio" % "3.10.3",

      "com.softwaremill.sttp.tapir" %% "tapir-netty-server" % tapirVersion,
//      "com.softwaremill.sttp.tapir" %% "tapir-netty-server-sync" % tapirVersion,

      // ZIO for async effects
//      "dev.zio" %% "zio" % "2.0.21", // ZIO
//      "dev.zio" %% "zio-interop-cats" % "23.1.0.4" // ZIO interop with Cats
    ),
  )
  //  .dependsOn(core) // sql only for now
  .dependsOn(
    core,
//    sqlH2 % CompileAndTest,
//    sqlMariaDB % CompileAndTest,
//    sqlMySQL % CompileAndTest,
//    sqlPostgreSQL % CompileAndTest,
//    sqlSQlite % CompileAndTest,
  ) // sql only for now


lazy val datalogCore = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("datalog/core"))
  .settings(name := "molecule-datalog-core")
  .settings(doPublish)
  .settings(compilerArgs)
  .jvmSettings(
    libraryDependencies += "com.datomic" % "peer" % "1.0.7277" // Requires Java 11
  )
  .jsSettings(jsEnvironment)
  .dependsOn(core)


lazy val datalogDatomic = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("datalog/datomic"))
  .settings(name := "molecule-datalog-datomic")
  .settings(doPublish)
  .settings(compilerArgs)
  .settings(testFrameworks := testingFrameworks)
  .jsSettings(jsEnvironment)
  .dependsOn(datalogCore)
  .dependsOn(coreTests % "test->test")


lazy val sqlCore = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("sql/core"))
  .settings(name := "molecule-sql-core")
  .settings(doPublish)
  .settings(compilerArgs)
  .settings(
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
  .settings(name := "molecule-sql-h2")
  .settings(doPublish)
  .settings(compilerArgs)
  .settings(testFrameworks := testingFrameworks)
  .jsSettings(jsEnvironment)
  .jvmSettings(
    libraryDependencies ++= Seq(
      "com.h2database" % "h2" % "2.3.232"
    ),
    Test / fork := true
  )
  .dependsOn(sqlCore)
  .dependsOn(coreTests % "test->test")


lazy val sqlMariaDB = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("sql/mariadb"))
  .settings(name := "molecule-sql-mariadb")
  .settings(doPublish)
  .settings(compilerArgs)
  .settings(testFrameworks := testingFrameworks)
  .jsSettings(jsEnvironment)
  .jvmSettings(
    libraryDependencies ++= Seq(
      "com.dimafeng" %% "testcontainers-scala-mariadb" % dimafengContainerVersion,
      "org.mariadb.jdbc" % "mariadb-java-client" % "3.5.1",
      "ch.qos.logback" % "logback-classic" % logbackVersion % Test
    ),
    Test / fork := true
  )
  .dependsOn(sqlCore)
  .dependsOn(coreTests % "test->test")


lazy val sqlMySQL = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("sql/mysql"))
  .settings(name := "molecule-sql-mysql")
  .settings(doPublish)
  .settings(compilerArgs)
  .settings(testFrameworks := testingFrameworks)
  .jsSettings(jsEnvironment)
  .jvmSettings(
    libraryDependencies ++= Seq(
      "org.testcontainers" % "mysql" % testContainerVersion,
      "com.mysql" % "mysql-connector-j" % "9.2.0",
    ),
    Test / fork := true
  )
  .dependsOn(sqlCore)
  .dependsOn(coreTests % "test->test")


lazy val sqlPostgreSQL = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("sql/postgres"))
  .settings(name := "molecule-sql-postgres")
  .settings(doPublish)
  .settings(compilerArgs)
  .settings(testFrameworks := testingFrameworks)
  .jsSettings(jsEnvironment)
  .jvmSettings(
    libraryDependencies ++= Seq(
      "org.testcontainers" % "postgresql" % testContainerVersion,
      "org.postgresql" % "postgresql" % "42.7.5",
      "ch.qos.logback" % "logback-classic" % logbackVersion % Test
    ),
    Test / fork := true
  )
  .dependsOn(sqlCore)
  .dependsOn(coreTests % "test->test")


lazy val sqlSQlite = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("sql/sqlite"))
  .settings(name := "molecule-sql-sqlite")
  .settings(doPublish)
  .settings(compilerArgs)
  .settings(testFrameworks := testingFrameworks)
  .jsSettings(jsEnvironment)
  .jvmSettings(
    libraryDependencies ++= Seq(
      "org.xerial" % "sqlite-jdbc" % "3.49.1.0"
    ),
    Test / fork := true
  )
  .dependsOn(sqlCore)
  .dependsOn(coreTests % "test->test")


//lazy val http4s = crossProject(JSPlatform, JVMPlatform)
//  .crossType(CrossType.Full)
lazy val adapterHttp4s = project
  .in(file("adapters/http4s"))
  .settings(name := "molecule-adapter-http4s")
  //  .settings(commonSettings)
  //  .settings(enableMimaSettingsJVM)
  //  .disablePlugins(AssemblyPlugin)
  .settings(
    libraryDependencies ++= {
      if (scalaVersion.value == scala3) Seq()
      else Seq(compilerPlugin(("org.typelevel" %% "kind-projector" % "0.13.3")
        .cross(CrossVersion.full)))
    } ++
      Seq(
        "dev.zio" %% "zio-interop-cats" % zioInteropCats3Version,
        "org.typelevel" %% "cats-effect" % catsEffect3Version,
        "com.softwaremill.sttp.tapir" %% "tapir-http4s-server-zio" % tapirVersion,
        "org.http4s" %% "http4s-ember-server" % http4sVersion % Test,
      )
  )
  .dependsOn(
    core.jvm % "compile->compile;test->test"
    //    tapirInterop % "compile->compile;test->test",
    //    catsInterop
  )

//lazy val zioHttp = project
//  .in(file("adapters/zio-http"))
//  .settings(name := "caliban-zio-http")
//  .settings(commonSettings)
//  .settings(enableMimaSettingsJVM)
//  .disablePlugins(AssemblyPlugin)
//  .dependsOn(core, quickAdapter)
//
//lazy val quickAdapter = project
//  .in(file("adapters/quick"))
//  .settings(name := "caliban-quick")
//  .settings(commonSettings)
//  .settings(enableMimaSettingsJVM)
//  .disablePlugins(AssemblyPlugin)
//  .settings(
//    libraryDependencies ++= Seq(
//      "dev.zio" %% "zio-http" % zioHttpVersion
//    )
//  )
//  .dependsOn(core, tapirInterop % "test->test")
//
//lazy val akkaHttp = project
//  .in(file("adapters/akka-http"))
//  .settings(name := "caliban-akka-http")
//  .settings(commonSettings)
//  .settings(enableMimaSettingsJVM)
//  .disablePlugins(AssemblyPlugin)
//  .settings(
//    skip           := (scalaVersion.value == scala3),
//    ideSkipProject := (scalaVersion.value == scala3),
//    crossScalaVersions -= scala3,
//    libraryDependencies ++= Seq(
//      "com.typesafe.akka"           %% "akka-http"                  % akkaHttpVersion,
//      "com.typesafe.akka"           %% "akka-serialization-jackson" % akkaVersion,
//      "com.softwaremill.sttp.tapir" %% "tapir-akka-http-server"     % tapirVersion,
//      compilerPlugin(("org.typelevel" %% "kind-projector" % "0.13.3").cross(CrossVersion.full))
//    )
//  )
//  .dependsOn(core, tapirInterop % "compile->compile;test->test")
//
//lazy val pekkoHttp = project
//  .in(file("adapters/pekko-http"))
//  .settings(name := "caliban-pekko-http")
//  .settings(commonSettings)
//  .settings(enableMimaSettingsJVM)
//  .disablePlugins(AssemblyPlugin)
//  .settings(
//    libraryDependencies ++= {
//      if (scalaVersion.value == scala3) Seq()
//      else Seq(compilerPlugin(("org.typelevel" %% "kind-projector" % "0.13.3").cross(CrossVersion.full)))
//    } ++ Seq(
//      "org.apache.pekko"            %% "pekko-http"              % pekkoHttpVersion,
//      "com.softwaremill.sttp.tapir" %% "tapir-pekko-http-server" % tapirVersion
//    )
//  )
//  .dependsOn(core, tapirInterop % "compile->compile;test->test")
//
//lazy val play = project
//  .in(file("adapters/play"))
//  .settings(name := "caliban-play")
//  .settings(commonSettings)
//  .settings(enableMimaSettingsJVM)
//  .disablePlugins(AssemblyPlugin)
//  .settings(
//    skip           := (scalaVersion.value == scala212),
//    ideSkipProject := (scalaVersion.value == scala212),
//    crossScalaVersions -= scala212,
//    libraryDependencies ++= {
//      if (scalaVersion.value == scala3) Seq()
//      else Seq(compilerPlugin(("org.typelevel" %% "kind-projector" % "0.13.3").cross(CrossVersion.full)))
//    },
//    libraryDependencies ++= Seq(
//      "org.playframework"           %% "play"                   % playVersion,
//      "com.softwaremill.sttp.tapir" %% "tapir-play-server"      % tapirVersion,
//      "dev.zio"                     %% "zio-test"               % zioVersion  % Test,
//      "dev.zio"                     %% "zio-test-sbt"           % zioVersion  % Test,
//      "org.playframework"           %% "play-pekko-http-server" % playVersion % Test
//    )
//  )
//  .dependsOn(core, tapirInterop % "compile->compile;test->test")

lazy val testingFrameworks = Seq(new TestFramework("munit.Framework"))

lazy val jsEnvironment = {
  Seq(
    jsEnv := new JSDOMNodeJSEnv(
      JSDOMNodeJSEnv
        .Config()
        // for some reason still needed...
        // https://github.com/scala-js/scala-js-js-envs/issues/12
        .withArgs(List("--dns-result-order=ipv4first"))
    ),
    // "Error: connect ECONNREFUSED ::1:8080" with this one alone... shouldn't it work?
    // jsEnv := new org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv(),

    //    scalaJSLinkerConfig ~= {
    //      // Allowing unicode characters in regex expressions (used in email regex)
    //      // https://www.scala-js.org/doc/regular-expressions.html
    //      //        .withESFeatures(_.withESVersion(ESVersion.ES2018))
    //      _.withESFeatures(_.withESVersion(ESVersion.ES2018))
    //    },
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
