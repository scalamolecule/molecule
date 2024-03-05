addSbtPlugin("com.jsuereth" % "sbt-pgp" % "1.1.1")

addSbtPlugin("org.scala-js" % "sbt-scalajs" % "1.15.0")
addSbtPlugin("org.portable-scala" % "sbt-scalajs-crossproject" % "1.3.2")

addSbtPlugin("org.scalamolecule" % "sbt-molecule" % "1.7.1-SNAPSHOT")


//addSbtPlugin("com.github.ghostdogpr" % "caliban-codegen-sbt" % "2.5.2")

libraryDependencies += "org.scala-js" %% "scalajs-env-jsdom-nodejs" % "1.1.0"
