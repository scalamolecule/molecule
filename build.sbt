import org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv
import org.scalajs.linker.interface.ModuleSplitStyle

val scala212 = "2.12.17"
val scala213 = "2.13.10"
val scala3   = "3.2.1"
val allScala = Seq(scala212, scala213, scala3)

val zioVersion  = "2.0.1"
val akkaVersion = "2.7.0"

//inThisBuild(
//  List(
//    scalaVersion             := scala213,
//    crossScalaVersions       := allScala,
//    organization             := "org.scalamolecule",
//    description              := "molecule",
//    homepage                 := Some(url("http://www.scalamolecule.org")),
//    licenses                 := List(License.Apache2),
//    resolvers += "Sonatype OSS Snapshots" at "https://s01.oss.sonatype.org/content/repositories/snapshots",
//    Test / parallelExecution := false,
//    scmInfo                  := Some(
//      ScmInfo(
//        url("https://github.com/scalamolecule/molecule"),
//        "scm:git:git@github.com:scalamolecule/molecule.git"
//      )
//    ),
//    developers               := List(
//      Developer(
//        id = "marcgrue",
//        name = "Marc Grue",
//        email = "marcgrue@gmail.com",
//        url = url("http://marcgrue.com")
//      )
//    )
//  )
//)
//name := "molecule"

lazy val root = project
  .in(file("."))
  .settings(name := "molecule")
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
  .settings(baseSettings)
  .settings(doPublish)

lazy val boilerplate = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Pure)
  .settings(name := "molecule-boilerplate")
  .dependsOn(base)
  .settings(sharedSettings ++ doPublish)
  .settings(boilerplateSettings)
  .enablePlugins(MoleculePlugin)
  .jsSettings(jsSettings)
//  .jvmSettings(jvmSettings)

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
//  .jvmSettings(jvmSettings)

lazy val datomic = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("db/datomic"))
  .settings(name := "molecule-datomic")
  .dependsOn(base, boilerplate, core, coreTests)
  .settings(sharedSettings ++ doPublish ++ testSettings)
  .jsSettings(jsSettings)
  .jvmSettings(datomicSettings)


lazy val baseSettings: Seq[Def.Setting[_]] = Seq(
  ThisBuild / organization := "org.scalamolecule",
  ThisBuild / organizationName := "ScalaMolecule",
  ThisBuild / organizationHomepage := Some(url("http://www.scalamolecule.org")),
  ThisBuild / version := "0.1.0-SNAPSHOT",
  ThisBuild / scalaVersion := scala213,
  ThisBuild / versionScheme := Some("early-semver"),
  crossScalaVersions := Seq(scala212, scala213),
  scalacOptions := List(
    "-feature",
    "-deprecation",
    "-language:implicitConversions",
    "-language:postfixOps",
    "-language:higherKinds",
    "-language:existentials",
    "-Yrangepos"
  ),
  libraryDependencies ++= Seq(
    "org.scalameta" %% "scalameta" % "4.5.10",
    "com.lihaoyi" %%% "utest" % "0.7.11" % Test,
    "dev.zio" %% "zio" % zioVersion,
    "dev.zio" %% "zio-streams" % zioVersion,
    "dev.zio" %% "zio-test" % zioVersion % Test
  ),

  //  libraryDependencies += "com.lihaoyi" %%% "utest" % "0.7.4" % "test",
  //  testFrameworks += new TestFramework("utest.runner.Framework"),
  testFrameworks += new TestFramework("molecule.db.datomic.setup.MoleculeTestFramework"),


  Compile / unmanagedSourceDirectories ++= {
    (Compile / unmanagedSourceDirectories).value.map { dir =>
      CrossVersion.partialVersion(scalaVersion.value) match {
        case Some((2, 13)) => file(dir.getPath ++ "-2.13+")
        case _             => file(dir.getPath ++ "-2.13-")
      }
    }
  },
)

lazy val sharedSettings: Seq[Def.Setting[_]] = baseSettings ++ Seq(
  resolvers ++= Seq(
    "clojars" at "https://clojars.org/repo"
  ),
  //  Compile / unmanagedSourceDirectories ++= {
  //    (Compile / unmanagedSourceDirectories).value.map { dir =>
  //      CrossVersion.partialVersion(scalaVersion.value) match {
  //        case Some((2, 13)) => file(dir.getPath ++ "-2.13+")
  //        case _             => file(dir.getPath ++ "-2.13-")
  //      }
  //    }
  //  },
  libraryDependencies ++= Seq(
    "org.scala-lang" % "scala-reflect" % scalaVersion.value,
    "io.suzaku" %%% "boopickle" % "1.4.0",
    "com.github.cornerman" %%% "sloth" % "0.6.5",
    //    "com.lihaoyi" %%% "utest" % "0.7.11",

    // For aggregate tolerant trippelequal
    "org.scalactic" %%% "scalactic" % "3.2.14" % Test
  ),

  // Let IntelliJ detect sbt-molecule-created jars in unmanaged lib directories
  exportJars := true
)

lazy val jsSettings: Seq[Def.Setting[_]] = Seq(
  libraryDependencies ++= Seq(
    "org.scala-js" %%% "scalajs-dom" % "2.1.0",
    "org.scala-js" %%% "scala-js-macrotask-executor" % "1.1.1",

    //    "io.github.cquiroz" %%% "scala-java-time" % "2.3.0",
    // This creates quite a lot of locales code but is needed on the js side.
    // See https://github.com/cquiroz/scala-java-time/issues/69
    "io.github.cquiroz" %%% "scala-java-time" % "2.5.0" % Test,
    "io.github.cquiroz" %%% "scala-java-time-tzdb" % "2.5.0" % Test,

    "org.scala-js" %%% "scalajs-java-securerandom" % "1.0.0",
    //    "org.scala-js" %%% "scalajs-java-securerandom" % "1.0.0" cross CrossVersion.for3Use2_13,


  ),

  //  Test / scalaJSLinkerConfig ~= {
  //    _.withModuleKind(ModuleKind.ESModule)
  //  },
  //  Test / scalaJSLinkerConfig ~= {
  //    _.withModuleSplitStyle(ModuleSplitStyle.FewestModules)
  //  },
  Test / scalaJSLinkerConfig ~= {
    //      _.withSourceMap(true)
    _.withSourceMap(false)
  },

  //  scalaJSLinkerConfig in (Compile, fullOptJS) ~= { _.withSourceMap(false) },

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
      "org.codehaus.janino" % "commons-compiler-jdk" % "3.0.12",

      // Datomic client dependencies transiently resolved
      //      "org.scalamolecule" %% "datomic-client-api-java-scala" % "1.0.3",

      //      // Akka dependencies for MoleculeRpcResponse
      //      "com.typesafe.akka" %% "akka-actor-typed" % "2.6.19",
      //      "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.13.2",

      //      // Enforce one version to avoid warnings of multiple dependency versions when running tests
      //      "org.slf4j" % "slf4j-api" % "1.7.36",
      //      "org.slf4j" % "slf4j-nop" % "1.7.36"
    ) ++ (CrossVersion.partialVersion(scalaVersion.value) match {
      case Some((2, 13)) => Nil
      case _             =>
        // For @TxFns macro annotation on Scala 2.12
        sbt.compilerPlugin(
          "org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.full
        ) :: Nil
    })
  )
}

lazy val jvmSettings: Seq[Def.Setting[_]] = {
  Seq(
    libraryDependencies ++= Seq(
      //      // Datomic peer dependency
      //      "com.datomic" % "datomic-free" % DatomicSettings.freeVersion,
      //      "javax.xml.bind" % "jaxb-api" % "2.4.0-b180830.0359",
      //
      //      // Force newer janino compiler than datomic-free uses (necessary for using tx fns with datomic-free)
      //      "org.codehaus.janino" % "commons-compiler" % "3.0.12",
      //      "org.codehaus.janino" % "commons-compiler-jdk" % "3.0.12",

      // Datomic client dependencies transiently resolved
      //      "org.scalamolecule" %% "datomic-client-api-java-scala" % "1.0.3",

      // Akka dependencies for MoleculeRpcResponse
      "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion,
      "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.13.2",

      // Enforce one version to avoid warnings of multiple dependency versions when running tests
      "org.slf4j" % "slf4j-api" % "1.7.36",
      "org.slf4j" % "slf4j-nop" % "1.7.36"
    )
  )
}

lazy val boilerplateSettings: Seq[Def.Setting[_]] = {
  // If boilerplate code generation changes we re-generate code in
  // <src>.molecule.boilerplate.api.generic.dsl with:
  // sbt compile -Dmolecule=generic
  val generateGeneric = sys.props.get("molecule").contains("generic")
  val dslPath         = "../src/main/scala/molecule/boilerplate/api/generic/"
  Seq(
    moleculePluginActive := generateGeneric,
    moleculeMakeJars := false,
    moleculeDataModelPaths := Seq("molecule/boilerplate/api/generic"),

    // Prevent current dsl files from colliding with generated code
    unmanagedSources / excludeFilter :=
      (if (generateGeneric) {
        val genericDir =
          (baseDirectory.value / dslPath).getCanonicalPath
        new SimpleFileFilter(f =>
          f.getCanonicalPath
            .startsWith(genericDir + "/")
        )
      } else NothingFilter),

    // Copy generated dsl code to project
    Compile / moleculeJars := Def
      .taskDyn {
        if (generateGeneric) {
          Def.task {
            val dirGenerated = new File(
              (sourceManaged.value / "main/molecule/boilerplate/api/generic").getCanonicalPath
            )
            val dirShared    =
              new File((baseDirectory.value / dslPath).getCanonicalPath)
            val isJvm        = baseDirectory.value.toString.endsWith("jvm")
            // Source files have been created at this point by the sbt-molecule plugin
            if (dirGenerated.exists() && dirShared.exists()) {
              IO.copyDirectory(dirGenerated, dirShared, true)
              println(
                "Copied generated generic dsl code to project source code."
              )
              IO.delete(dirGenerated)
              println("Deleted generated generic dsl code. " + dirGenerated)
            }
          }
        } else Def.task {}
      }
      .triggeredBy(Compile / compile)
      .value

    //    libraryDependencies ++= Seq(
    //      "org.scalameta" %% "scalameta" % "4.5.10"
    //    )
  )
}

lazy val testSettings: Seq[Def.Setting[_]] = {
  Seq(
    // Find scala version specific jars in respective libs
    unmanagedBase := {
      CrossVersion.partialVersion(scalaVersion.value) match {
        case Some((2, 13)) => file(unmanagedBase.value.getPath ++ "/2.13")
        case _             => file(unmanagedBase.value.getPath ++ "/2.12")
      }
    },

    //    libraryDependencies += "com.lihaoyi" %%% "utest" % "0.7.4" % "test",
    //    testFrameworks += new TestFramework("utest.runner.Framework"),
    //    //    testFrameworks += new TestFramework("molecule.db.datomic.setup.MoleculeTestFramework"),

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
      "molecule/coreTests/dataModels/core",

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
    //    moleculeMakeJars := false,

    // Temporarily limit number of tests to be compiled by sbt (comment out this whole sbt setting to test all)
    // Note that intellij doesn't recognize this setting - there you can right click on files and exclude
    unmanagedSources / excludeFilter := {
      val test = "src/test/scala/molecule/db/datomic/test"
      def path(platform: String) = (baseDirectory.value / s"../$platform/$test").getCanonicalPath
      val jsTests     = path("js")
      val jvmTests    = path("jvm")
      val sharedTests = path("shared")
      val allowed     = Seq(
        //        sharedTests + "/expr",
        //        jvmTests + "/restore",
        //        sharedTests + "/core/api"
        //        sharedTests + "/core/attribute",
        //        sharedTests + "/core/attrMap",
        //        sharedTests + "/core/bidirectionals",
        //        sharedTests + "/core/crud",
        //        sharedTests + "/core/expression/equality",
        //        sharedTests + "/core/input1",
        //        sharedTests + "/core/input2",
        //        sharedTests + "/core/input3",
        //        sharedTests + "/core/json",
        //        sharedTests + "/core/nested",
        //        sharedTests + "/core/obj",
        //        sharedTests + "/core/pagination",
        //        sharedTests + "/core/ref",
        //        sharedTests + "/core/sorting",
        //        sharedTests + "/db/datomic/composite",
        //        sharedTests + "/db/datomic/entity",
        //        sharedTests + "/db/datomic/generic",
        //        sharedTests + "/db/datomic/partitions",
        //        sharedTests + "/db/datomic/time",
        //        sharedTests + "/db/datomic/txMetaData",
        //        sharedTests + "/examples/datomic/dayOfDatomic",
        //        sharedTests + "/examples/datomic/mbrainz",
        //        sharedTests + "/examples/datomic/seattle",
        //        sharedTests + "/examples/gremlin/gettingStarted",
        //        sharedTests + "/sbtmolecule/codeGen",
        //            sharedTests + "/Adhoc.scala",

//        sharedTests + "/aggr/set/num",
        sharedTests + "/aggr",
        //        sharedTests + "/crud",
        //        sharedTests,
        jvmTests + "/AdhocJVM.scala",
        jsTests + "/AdhocJs.scala",
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
      "dev.zio" %% "zio-test-sbt" % zioVersion % "test",
      //      "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.13.2",
      "com.typesafe.akka" %% "akka-stream" % akkaVersion,
      "com.typesafe.akka" %% "akka-actor" % akkaVersion,
      "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion,
      "com.typesafe.akka" %% "akka-http" % "10.4.0",
      "ch.megard" %% "akka-http-cors" % "1.1.3",
      //
      //      // Free, but proprietary Client dev-local dependency needed for testing client/dev-local
      //      // Please download from https://cognitect.com/dev-tools and install locally per included instructions
      //      "com.datomic" % "dev-local" % DatomicSettings.devLocalVersion
    )
    //  )
    //}
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
