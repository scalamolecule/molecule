import org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv
import org.scalajs.linker.interface.ESVersion


val scala212 = "2.12.18"
val scala213 = "2.13.11"
val scala3   = "3.3.0"
val allScala = Seq(scala212, scala213, scala3)

val akkaVersion = "2.8.3"
val zioVersion  = "2.0.15"

inThisBuild(
  List(
    organization := "org.scalamolecule",
    organizationName := "ScalaMolecule",
    organizationHomepage := Some(url("http://www.scalamolecule.org")),
    version := "0.2.0-SNAPSHOT",
    versionScheme := Some("early-semver"),
    //    scalaVersion := scala212,
    scalaVersion := scala213,
    //    scalaVersion := scala3,
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
  .aggregate(
    base.js,
    base.jvm,
    boilerplate.js,
    boilerplate.jvm,
    core.js,
    core.jvm,
    coreTests.js,
    coreTests.jvm,
    datalogCore.js,
    datalogCore.jvm,
    datalogDatomic.js,
    datalogDatomic.jvm,
    sqlCore.js,
    sqlCore.jvm,
    sqlJdbc.js,
    sqlJdbc.jvm
  )

lazy val base = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .settings(name := "molecule-base")
  .settings(compilerArgs)
  .settings(doPublish)


lazy val boilerplate = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .settings(name := "molecule-boilerplate")
  .dependsOn(base)
  .settings(doPublish)
  .settings(
    libraryDependencies ++= Seq(
      "com.lihaoyi" %%% "utest" % "0.8.1",

      // Logging
      "com.outr" %%% "scribe" % "3.11.8",

      // Tolerant roundings with triple equal on js platform
      "org.scalactic" %%% "scalactic" % "3.2.16"
    ),
    testFrameworks += new TestFramework("utest.runner.Framework")
  )


lazy val core = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .settings(name := "molecule-core")
  .dependsOn(boilerplate)
  .settings(doPublish)
  .settings(
    libraryDependencies ++= Seq(
      "io.suzaku" %%% "boopickle" % "1.4.0",
      "dev.zio" %%% "zio" % zioVersion,
      "dev.zio" %%% "zio-streams" % zioVersion,
      "dev.zio" %%% "zio-test" % zioVersion % Test,
      "dev.zio" %%% "zio-test-sbt" % zioVersion, // % Test // todo: why does this collide?
    )
  )
  .jsSettings(
    libraryDependencies ++= Seq(
      "org.scala-js" %%% "scalajs-dom" % "2.6.0",
      "org.scala-js" %%% "scala-js-macrotask-executor" % "1.1.1",
      "org.scala-js" %%% "scalajs-java-securerandom" % "1.0.0" cross CrossVersion.for3Use2_13
    )
  )
  .jvmSettings(
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-stream" % akkaVersion cross CrossVersion.for3Use2_13,
      "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion cross CrossVersion.for3Use2_13,
      "ch.megard" %% "akka-http-cors" % "1.2.0",
      // Enforce one version to avoid warnings of multiple dependency versions when running tests
      "org.slf4j" % "slf4j-api" % "1.7.36",
      "org.slf4j" % "slf4j-nop" % "1.7.36"
    )
  )


lazy val coreTests = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .settings(name := "molecule-coreTests")
  .dependsOn(core)
  .settings(publish / skip := true)
  .enablePlugins(MoleculePlugin)
  .settings(
    // Generate Molecule boilerplate code for tests with `sbt clean compile -Dmolecule=true`
    moleculePluginActive := sys.props.get("molecule").contains("true"),

    // Multiple directories with data models
    moleculeDataModelPaths := Seq(
      "molecule/coreTests/dataModels/core"
    ),
    // moleculeMakeJars := false, // default: true

    // Suppress "un-used" keys warning
    Global / excludeLintKeys ++= Set(
      moleculePluginActive,
      moleculeDataModelPaths,
      moleculeMakeJars
    ),

    // Let IntelliJ detect sbt-molecule-created jars in unmanaged lib directories
    exportJars := true,

    // Find scala version specific jars in respective libs
    unmanagedBase := {
      CrossVersion.partialVersion(scalaVersion.value) match {
        case Some((2, 13)) => file(unmanagedBase.value.getPath ++ "/2.13")
        case Some((2, 12)) => file(unmanagedBase.value.getPath ++ "/2.12")
        case _             => file(unmanagedBase.value.getPath ++ "/3.3")
      }
    },
    testFrameworks += new TestFramework("utest.runner.Framework"),
  )
  .jsSettings(jsEnvironment)


lazy val datalogCore = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("datalog/core"))
  .settings(name := "molecule-datalog-core")
  .settings(doPublish)
  .dependsOn(core)
  .dependsOn(coreTests % "test->test")
  .settings(
    testFrameworks := Seq(
      new TestFramework("utest.runner.Framework"),
      new TestFramework("zio.test.sbt.ZTestFramework")
    )
  )
  .jvmSettings(
    libraryDependencies += "com.datomic" % "peer" % "1.0.6735"
  )
  .jsSettings(jsEnvironment)

lazy val datalogDatomic = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("datalog/datomic"))
  .settings(name := "molecule-datalog-datomic")
  .settings(doPublish)
  .dependsOn(datalogCore % "compile->compile;test->test")
  .settings(
    // Temporarily limit number of tests to be compiled by sbt (comment out this whole sbt setting to test all)
    // Note that intellij doesn't recognize this setting - there you can right click on files and exclude
    //    unmanagedSources / excludeFilter := {
    //      val test = "src/test/scala/molecule/datalog/datomic/test"
    //      def path(platform: String) = (baseDirectory.value / s"../$platform/$test").getCanonicalPath
    //      val jsTests     = path("js")
    //      val jvmTests    = path("jvm")
    //      val sharedTests = path("shared")
    //      val allowed     = Seq(
    //        //        jvmTests + "/restore",
    //        //        sharedTests + "/aggr",
    //        //        sharedTests + "/api",
    //        //        sharedTests + "/composite",
    //        //        sharedTests + "/crud",
    //        //        sharedTests + "/expr",
    //        //        sharedTests + "/pagination",
    //        //        sharedTests + "/relation",
    //        //        sharedTests + "/sort",
    //        //        sharedTests + "/SyncApi.scala"
    //        //        sharedTests + "/txMetaData",
    //        //        sharedTests + "/validation",
    //        //                sharedTests + "/time",
    //        //        sharedTests,
    //        //        jvmTests,
    //        //        jsTests,
    //        jvmTests + "/AdhocJVM.scala",
    //        sharedTests + "/Adhoc.scala",
    //      )
    //      new SimpleFileFilter(f =>
    //        (f.getCanonicalPath.startsWith(jsTests)
    //          || f.getCanonicalPath.startsWith(jvmTests)
    //          || f.getCanonicalPath.startsWith(sharedTests)) &&
    //          !allowed.exists(p => f.getCanonicalPath.startsWith(p))
    //      )
    //    },

    testFrameworks := Seq(
      new TestFramework("utest.runner.Framework"),
      new TestFramework("zio.test.sbt.ZTestFramework")
    )
  )
  .jsSettings(jsEnvironment)


lazy val sqlCore = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("sql/core"))
  .settings(name := "molecule-sql-core")
  .settings(doPublish)
  .dependsOn(core)
  .dependsOn(coreTests % "test->test")
  .settings(
    testFrameworks := Seq(
      new TestFramework("utest.runner.Framework"),
      new TestFramework("zio.test.sbt.ZTestFramework")
    )
  )
  .jvmSettings(
    libraryDependencies ++= Seq(
      "com.datomic" % "peer" % "1.0.6735",
      "com.h2database" % "h2" % "2.1.214" % Provided
    )
  )
  .jsSettings(jsEnvironment)

lazy val sqlJdbc = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("sql/jdbc"))
  .settings(name := "molecule-sql-jdbc")
  .settings(doPublish)
  .dependsOn(sqlCore % "compile->compile;test->test")
  .settings(
    testFrameworks := Seq(
      new TestFramework("utest.runner.Framework"),
      new TestFramework("zio.test.sbt.ZTestFramework")
    )
  )
  .jsSettings(jsEnvironment)


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
    //  jsEnv := new org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv()

    // Allowing unicode characters in regex expressions (used in email regex)
    // https://www.scala-js.org/doc/regular-expressions.html
    scalaJSLinkerConfig ~= (_.withESFeatures(_.withESVersion(ESVersion.ES2018)))
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
    "-Xfatal-warnings"
  ) ++ (CrossVersion.partialVersion(scalaVersion.value) match {
    case Some((2, 12)) =>
      Seq(
        "-Xsource:2.13",
        "-Yno-adapted-args",
        "-Ypartial-unification",
        "-Ywarn-extra-implicit",
        "-Ywarn-inaccessible",
        "-Ywarn-infer-any",
        "-Ywarn-unused:-nowarn",
        "-Ywarn-nullary-override",
        "-Ywarn-nullary-unit",
        "-opt-inline-from:<source>",
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
        "-Ykind-projector"
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

lazy val withDocs = Seq(
  publishMavenStyle := true,
  publishTo := (if (isSnapshot.value) Some(snapshots) else Some(releases)),
  Test / publishArtifact := false,
  Compile / doc / scalacOptions ++= Seq(
    "-doc-root-content",
    baseDirectory.value + "/src/main/scaladoc/rootdoc.txt",
    "-diagrams",
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

lazy val withoutDocs = Seq(
  Test / publishArtifact := false,
  doc / sources := Seq.empty,
  packageDoc / publishArtifact := false
)
