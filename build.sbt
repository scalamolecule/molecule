import org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv
import org.scalajs.linker.interface.ESVersion
import sbt.Keys.libraryDependencies


val scala212 = "2.12.20"
val scala213 = "2.13.16"
val scala3   = "3.3.5"
val allScala = Seq(scala212, scala213, scala3)

val akkaVersion              = "2.8.3"
val zioVersion               = "2.0.15"
val dimafengContainerVersion = "0.41.4"
val testContainerVersion     = "1.20.4"
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
      "org.typelevel" %%% "cats-effect" % "3.5.7", // cats api
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
    moleculeMakeJars := false,

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
      "org.scalameta" %% "munit" % "1.0.3" % Test,
      "org.scalactic" %%% "scalactic" % "3.2.19" % Test, // Tolerant roundings with triple equal on js platform
      "io.github.cquiroz" %%% "scala-java-time" % "2.6.0" % Test,
    ),
  )
  .jsSettings(
    jsEnvironment,
    libraryDependencies ++= Seq(
      "org.scala-js" %%% "scalajs-dom" % "2.8.0",
      "org.scala-js" %%% "scala-js-macrotask-executor" % "1.1.1",
      "org.scala-js" %%% "scalajs-java-securerandom" % "1.0.0" cross CrossVersion.for3Use2_13
    )
  )
  .jvmSettings(
    libraryDependencies ++= Seq(
      // Enforce one version to avoid warnings of multiple dependency versions when running tests
      "org.slf4j" % "slf4j-api" % "1.7.36",
      "org.slf4j" % "slf4j-nop" % "1.7.36"
    ),
  )
  .dependsOn(core)


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

      //      "com.dimafeng" %% "testcontainers-scala-mysql" % testContainerVersion % Test,
      "com.mysql" % "mysql-connector-j" % "9.1.0" % Test,
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
      "org.postgresql" % "postgresql" % "42.7.4",
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
      "org.xerial" % "sqlite-jdbc" % "3.47.1.0"
    ),
    Test / fork := true
  )
  .dependsOn(sqlCore)
  .dependsOn(coreTests % "test->test")


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

    scalaJSLinkerConfig ~= {
      // Allowing unicode characters in regex expressions (used in email regex)
      // https://www.scala-js.org/doc/regular-expressions.html
      //        .withESFeatures(_.withESVersion(ESVersion.ES2018))
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
