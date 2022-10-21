import sbt._

object DatomicSettings {
  val notAvailable = "<not available>"

  // todo: make configurable
  // Replace with path to your Datomic downloads directory
  val distributionsDir = "/Users/mg/lib/datomic"

  if (!new File(distributionsDir).isDirectory)
    throw new IllegalArgumentException(
      "Please set your datomic downloads directory path in project.SettingsDatomic"
    )

  val testDatomicDir = new File(distributionsDir)

  // Force specific free version with `sbt compile -Ddatomic.free=0.9.9999`
  val freeVersion = sys.props
    .get("datomic.free")
    .getOrElse(
      // Use last published version
      "0.9.5697"
    )

  if (
    !testDatomicDir
      .listFiles()
      .exists(_.getName == s"datomic-free-$freeVersion")
  )
    throw new IllegalArgumentException(
      s"Please download datomic-free-$freeVersion to `$distributionsDir` " +
        s"and run `bin/maven-install`.\n" +
        s""
    )

  // Force Datomic to use a specific protocol (and db) by adding a protocol flag:
  // `sbt <cmd> -Dprotocol=free` where <cmd> can be `compile`, `publish` etc.
  val (protocol, useFree) = sys.props.get("protocol") match {
    case Some("mem-free") => ("mem", true)
    case Some("free")     => ("free", true)
    case Some("dev")      => ("dev", false)
    case _                => ("mem", false) // default: in-mem protocol with pro db
  }

  val freeVersions     = datomicVersions("datomic-free")
  val proVersions      = datomicVersions("datomic-pro")
  val devLocalVersions = datomicVersions("dev-local")

  if (protocol != "free" && proVersions.isEmpty)
    throw new IllegalArgumentException(
      s"Please download Datomic starter/pro or " +
        s"switch to free version (see README_free and README_pro)"
    )

  // Force specific pro version with `sbt compile -Ddatomic.pro=1.0.6202`
  val proVersion = sys.props
    .get("datomic.pro")
    .getOrElse(
      if (proVersions.nonEmpty) proVersions.max else notAvailable
    )

  // Force specific dev-local version with `sbt compile -Ddatomic.dev-local=0.9.225`
  val devLocalVersion = sys.props
    .get("datomic.dev-local")
    .getOrElse(
      if (devLocalVersions.nonEmpty) devLocalVersions.max else notAvailable
    )

  val home = distributionsDir + "/datomic-" + (
    if (useFree) "free-" + freeVersion else "pro-" + proVersion
  )
  //    val home = protocol match {
  //      case "dev" | "pro" => distributionsDir + "/datomic-pro-" + proVersion
  //      case _             => distributionsDir + "/datomic-free-" + freeVersion
  //    }

  val dbType = if (protocol == "mem") if (useFree) "(free)" else "(pro)" else ""

  // print current datomic setup to console when running sbt commands from terminal
  println(
    s"""------------------------------------------------------------------------
       |  Datomic protocol : $protocol $dbType
       |  Datomic home     : $home
       |
       |  Available versions
       |  free      : $freeVersions
       |  pro       : $proVersions
       |  dev-Local : $devLocalVersions
       |
       |  Current versions
       |  free      : $freeVersion
       |  pro       : $proVersion
       |  dev-Local : $devLocalVersion
       |------------------------------------------------------------------------""".stripMargin
  )

  private def datomicVersions(system: String): Seq[String] = {
    val datomicPath =
      Path.userHome + (System.getProperty("os.name").toLowerCase match {
        case os if os.contains("win")                        => "\\.m2\\repository\\com\\datomic\\"
        case os if os.contains("mac") | os.contains("linux") =>
          "/.m2/repository/com/datomic/"
        case osName                                          =>
          throw new RuntimeException(s"Unknown operating system $osName")
      })
    val dir         = new File(datomicPath + system)
    if (!dir.isDirectory || dir.listFiles() == null) {
      val cmd     = system match {
        case "datomic-pro" => "bin/maven-install"
        case "dev-local"   => "./install"
        case _             => "<no cmd>"
      }
      val distDir = new File(distributionsDir)
      if (
        distDir.listFiles() == null
        || !distDir.listFiles
          .filter(_.isDirectory)
          .exists(_.getName.contains(system))
      ) {
        // Need to download Datomic distribution
        throw new RuntimeException(
          s"Couldn't find any $system Datomic distribution in $distributionsDir" +
            s"\nPlease download a $system distribution." +
            s"\nThen run `$cmd` in the distribution directory to install libraries to local .m2 repository."
        )
      }
      // Need to install Datomic distribution
      throw new RuntimeException(
        s"Please run `$cmd` in the $system distribution in $distDir to install " +
          s"the system libraries to local .m2 repository."
      )
    }
    // Get list of Datomic system version names
    dir.listFiles.filter(_.isDirectory).map(_.getName).toList.sorted
  }
}
