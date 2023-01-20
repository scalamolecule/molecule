import org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv

val scala212 = "2.12.17"
val scala213 = "2.13.10"
val scala3   = "3.2.1"
val allScala = Seq(scala212, scala213, scala3)

val zioVersion      = "2.0.1"
val akkaVersion     = "2.8.0-M3"
val akkaHttpVersion = "10.5.0-M1"


lazy val root = project
  .in(file("."))
  .settings(name := "molecule2")
  .settings(baseSettings ++ dontPublish)
  .aggregate(
    base.js,
    base.jvm,
    boilerplate.js,
    boilerplate.jvm,
    core.js,
    core.jvm,
    coreTests.js,
    coreTests.jvm,
    datomic.js,
    datomic.jvm
  )

lazy val base = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Pure)
  .settings(name := "molecule-base")
  .settings(commonSettings ++ baseSettings)
  .settings(doPublish)

lazy val boilerplate = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Pure)
  .settings(name := "molecule-boilerplate")
  .dependsOn(base)
  .settings(sharedSettings ++ doPublish)
  .jsSettings(jsSettings)

lazy val core = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .settings(name := "molecule-core")
  .dependsOn(boilerplate)
  .settings(sharedSettings ++ doPublish)
  .jsSettings(jsSettings)
  .jvmSettings(jvmSettings)

lazy val coreTests = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .settings(name := "molecule-coreTests")
  .dependsOn(core)
  .enablePlugins(BuildInfoPlugin, MoleculePlugin)
  .settings(sharedSettings ++ testSettings ++ dontPublish)
  .jsSettings(jsSettings)

lazy val datomic = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("db/datomic"))
  .settings(name := "molecule-datomic")
  .dependsOn(base, boilerplate, core, coreTests)
  .settings(sharedSettings ++ testSettings ++ doPublish)
  .jsSettings(jsSettings)
  .jvmSettings(datomicSettings)


lazy val baseSettings: Seq[Def.Setting[_]] = Seq(
  ThisBuild / organization := "org.scalamolecule",
  ThisBuild / organizationName := "ScalaMolecule",
  ThisBuild / organizationHomepage := Some(url("http://www.scalamolecule.org")),
  ThisBuild / version := "0.1.0-SNAPSHOT",
  ThisBuild / versionScheme := Some("early-semver"),
  //  ThisBuild / scalaVersion := scala213,
  ThisBuild / scalaVersion := scala3,
  crossScalaVersions := Seq(scala212, scala213, scala3),
  libraryDependencies ++= {
    if (scalaVersion.value == scala3) {
      Seq(

      )
    } else {
      Seq(
        "org.scalameta" %% "scalameta" % "4.7.1"
      )
    }
  } ++
    Seq(
      "com.lihaoyi" %%% "utest" % "0.8.1" % Test,
      "dev.zio" %% "zio" % zioVersion,
      "dev.zio" %% "zio-streams" % zioVersion,
      "dev.zio" %% "zio-test" % zioVersion % Test
    ),

  testFrameworks += new TestFramework("utest.runner.Framework")
  //  testFrameworks += new TestFramework("molecule.db.datomic.setup.MoleculeTestFramework"),
)

lazy val sharedSettings: Seq[Def.Setting[_]] = baseSettings ++ Seq(
  resolvers ++= Seq(
    "clojars" at "https://clojars.org/repo"
  ),

  libraryDependencies ++= Seq(
    //    "org.scala-lang" % "scala-reflect" % scalaVersion.value,
    "io.suzaku" %%% "boopickle" % "1.4.0",

    // Tolerant roundings with trippelequal in aggregate tests
    "org.scalactic" %%% "scalactic" % "3.2.14" % Test,

    // Logging
    "com.outr" %%% "scribe" % "3.10.6"
  ),

  // Let IntelliJ detect sbt-molecule-created jars in unmanaged lib directories
  exportJars := true
)

lazy val jsSettings: Seq[Def.Setting[_]] = Seq(
  libraryDependencies ++= Seq(
    "org.scala-js" %%% "scalajs-dom" % "2.1.0",
    "org.scala-js" %%% "scala-js-macrotask-executor" % "1.1.1",

    // This creates quite a lot of locales code but is needed on the js side.
    // See https://github.com/cquiroz/scala-java-time/issues/69
    "io.github.cquiroz" %%% "scala-java-time" % "2.5.0" % Test,
    "io.github.cquiroz" %%% "scala-java-time-tzdb" % "2.5.0" % Test,

    "org.scala-js" %%% "scalajs-java-securerandom" % "1.0.0" cross CrossVersion.for3Use2_13
  ),

  jsEnv := new JSDOMNodeJSEnv(
    JSDOMNodeJSEnv
      .Config()
      // for some reason still needed with Scala.js 1.9
      // https://github.com/scala-js/scala-js-js-envs/issues/12
      .withArgs(List("--dns-result-order=ipv4first"))
  )
  // "Error: connect ECONNREFUSED ::1:8080" with this one alone... shouldn't it work?
  //  jsEnv := new org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv()
)

lazy val datomicSettings: Seq[Def.Setting[_]] = {
  Seq(
    libraryDependencies ++= Seq(
      // Datomic peer dependency
      "com.datomic" % "datomic-free" % DatomicSettings.freeVersion,
      //      "javax.xml.bind" % "jaxb-api" % "2.3.0",
      //      "javax.xml.bind" % "jaxb-api" % "2.4.0-b180830.0359",

      // Force newer janino compiler than datomic-free uses (necessary for using tx fns with datomic-free)
      "org.codehaus.janino" % "commons-compiler" % "3.0.12",
      "org.codehaus.janino" % "commons-compiler-jdk" % "3.0.12"

      // Datomic client dependencies transiently resolved
      //      "org.scalamolecule" %% "datomic-client-api-java-scala" % "1.0.3",
    ) ++ (CrossVersion.partialVersion(scalaVersion.value) match {
      case Some((2, 13)) => Nil
      case Some((2, 12)) =>
        // For @TxFns macro annotation on Scala 2.12
        sbt.compilerPlugin("org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.full) :: Nil
      case _             => Nil
    })
  )
}

lazy val jvmSettings: Seq[Def.Setting[_]] = {
  Seq(
    libraryDependencies ++= Seq(
      // Akka dependencies for MoleculeRpcResponse
      "com.typesafe.akka" %% "akka-stream" % akkaVersion cross CrossVersion.for3Use2_13,
      "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion cross CrossVersion.for3Use2_13,
//      "com.typesafe.akka" %% "akka-http-core" % akkaHttpVersion,
//      "com.typesafe.akka" %% "akka-parsing" % akkaHttpVersion,





//            "com.typesafe.akka" %% "akka-actor" % akkaVersion,
//            "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion,
//            "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,

      //      "com.typesafe.akka" %% "akka-http" % "10.2.10",
      //      "com.typesafe.akka" %% "akka-serialization-jackson" % akkaVersion,











      "ch.megard" %% "akka-http-cors" % "1.1.3",
      "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.13.2",
      // Enforce one version to avoid warnings of multiple dependency versions when running tests
      "org.slf4j" % "slf4j-api" % "1.7.36",
      "org.slf4j" % "slf4j-nop" % "1.7.36"
    )
  )
}


lazy val testSettings: Seq[Def.Setting[_]] = {
  Seq(
    // Find scala version specific jars in respective libs
    unmanagedBase := {
      CrossVersion.partialVersion(scalaVersion.value) match {
        case Some((2, 13)) => file(unmanagedBase.value.getPath ++ "/2.13")
        case Some((2, 12)) => file(unmanagedBase.value.getPath ++ "/2.12")
        case _             => file(unmanagedBase.value.getPath ++ "/3.2")
        //        case _ =>
        //          println("=================== " + unmanagedBase.value.getPath)
        //          file("/Users/mg/molecule/molecule2/coreTests/jvm/lib/3.2")
        //        case _ => baseDirectory.value / "../jvm/lib/3.2"
        //        case _ => baseDirectory.value / "../../coreTests/jvm/lib/3.2"

        //        case Some((2, 13)) => baseDirectory.value / "lib" / "2.13"
        //        case Some((2, 12)) => baseDirectory.value / "lib" / "2.12"
        //        case _             => baseDirectory.value / "lib" / "3.2"
      }
    },

    //    unmanagedJars / includeFilter := "*.jar",

    // Run tests for all systems sequentially to avoid data locks with db
    // Only applies on JVM. On JS platform there's no parallelism anyway.
    Test / parallelExecution := false,

    // Ensure clojure loads correctly for async tests run from sbt
    Test / classLoaderLayeringStrategy := ClassLoaderLayeringStrategy.Flat,

    buildInfoPackage := "moleculeBuildInfo",
    buildInfoKeys := Seq[BuildInfoKey](
      name,
      version,
      scalaVersion,
      sbtVersion,
      "datomicDistributionsDir" -> DatomicSettings.distributionsDir,
      "datomicHome" -> DatomicSettings.home,
      "datomicProtocol" -> DatomicSettings.protocol,
      "datomicUseFree" -> DatomicSettings.useFree,
      "datomicFreeVersions" -> DatomicSettings.freeVersions,
      "datomicProVersions" -> DatomicSettings.proVersions,
      "datomicDevLocalVersions" -> DatomicSettings.devLocalVersions,
      "datomicFreeVersion" -> DatomicSettings.freeVersion,
      "datomicProVersion" -> DatomicSettings.proVersion,
      "datomicDevLocalVersion" -> DatomicSettings.devLocalVersion
    ),

    // Generate Molecule boilerplate code for tests with `sbt clean compile -Dmolecule=true`
    moleculePluginActive := sys.props.get("molecule").contains("true"),

    // We need schema conversions for mBrainz
    //    moleculeSchemaConversions := true, // (default is false)

    // Multiple directories with data models
    moleculeDataModelPaths := Seq(
      "molecule/coreTests/dataModels/core"

      //      "moleculeTests/dataModels/core/base"
      //      "moleculeTests/dataModels/core/bidirectionals",
      //      "moleculeTests/dataModels/core/ref",
      //      "moleculeTests/dataModels/core/schemaDef",
      //
      //      "moleculeTests/dataModels/examples/datomic/dayOfDatomic",
      //      "moleculeTests/dataModels/examples/datomic/mbrainz",
      //      "moleculeTests/dataModels/examples/datomic/seattle",
      //      "moleculeTests/dataModels/examples/gremlin/gettingStarted"
    ),
    moleculeMakeJars := false,

    Global / excludeLintKeys ++= Set(
      buildInfoPackage,
      buildInfoKeys,
      moleculePluginActive,
      moleculeDataModelPaths,
      //      moleculeMakeJars
    ),

    // Temporarily limit number of tests to be compiled by sbt (comment out this whole sbt setting to test all)
    // Note that intellij doesn't recognize this setting - there you can right click on files and exclude
    unmanagedSources / excludeFilter := {
      val test = "src/test/scala/molecule/db/datomic/test"
      def path(platform: String) = (baseDirectory.value / s"../$platform/$test").getCanonicalPath
      val jsTests     = path("js")
      val jvmTests    = path("jvm")
      val sharedTests = path("shared")
      val allowed     = Seq(
        //        jvmTests + "/restore",
        //        sharedTests + "/aggr",
        //        sharedTests + "/composite",
        //        sharedTests + "/crud",
        //        sharedTests + "/expr",
        //        sharedTests + "/relation",
        //        sharedTests + "/sort",
        //        sharedTests + "/txMetaData",
        sharedTests,
        jvmTests + "/AdhocJVM.scala",
        jsTests + "/AdhocJs.scala",
        sharedTests + "/Adhoc.scala",
      )
      new SimpleFileFilter(f =>
        (f.getCanonicalPath.startsWith(jsTests)
          || f.getCanonicalPath.startsWith(jvmTests)
          || f.getCanonicalPath.startsWith(sharedTests)) &&
          !allowed.exists(p => f.getCanonicalPath.startsWith(p))
      )
    },

    // Allow resolving local dependencies if using Datomic proprietary dev-local or pro
    resolvers += Resolver.mavenLocal,
    libraryDependencies ++= Seq(
      "dev.zio" %% "zio" % zioVersion,
      "dev.zio" %% "zio-schema" % "0.2.1",
      "dev.zio" %% "zio-test" % zioVersion % "test",
      "dev.zio" %% "zio-test-sbt" % zioVersion % "test"
    )
  ) ++ (
    if (DatomicSettings.useFree)
      Nil // Datomic free version is already default in `molecule` module
    else
      Seq(
        // To use Datomic Pro, please download from https://www.datomic.com/get-datomic.html
        // and install locally per included instructions
        libraryDependencies += "com.datomic" % "datomic-pro" % DatomicSettings.proVersion,
        excludeDependencies += ExclusionRule("com.datomic", "datomic-free"),
        credentials += Credentials(Path.userHome / ".sbt" / ".credentials")
      )
    )
}

lazy val commonSettings1 = Def.settings(
  scalacOptions := List(
    "-feature",
    "-deprecation",
    "-language:implicitConversions",
    "-language:postfixOps",
    "-language:higherKinds",
    "-language:existentials",
    "-Yrangepos"
  )
)
lazy val commonSettings  = Def.settings(
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
        "-Ykind-projector",


        //        "-explaintypes",
        //        "-explain",
        //        "-Yexplain-lowlevel",
        //        "-Yprint-debug",
        //        "-unchecked",
        //        "-source:3.0-migration",
        //        "-Ydebug",
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
  licenses := Seq(
    "Apache 2" -> url("http://www.apache.org/licenses/LICENSE-2.0.txt")
  ),
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

lazy val dontPublish = Seq(
  publish / skip := true,
  publish := ((): Unit),
  publishLocal := ((): Unit),
  Compile / packageDoc / publishArtifact := false,
  Compile / doc / sources := Seq.empty
)
