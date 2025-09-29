package molecule.server

import scala.annotation.tailrec
import scala.concurrent.Await
import scala.concurrent.duration.*
import scala.io.StdIn
import boopickle.Default.*
import cats.effect.IO
import cats.effect.unsafe.implicits.global
import molecule.db.common.marshalling.Boopicklers.*
import molecule.db.common.marshalling.MoleculeRpc
import molecule.db.compliance.marshalling.PickleMetaDbs
import molecule.db.h2.marshalling.Rpc_h2
import molecule.db.mariadb.marshalling.Rpc_mariadb
import molecule.db.mysql.marshalling.Rpc_mysql
import molecule.db.postgresql.marshalling.Rpc_postgresql
import molecule.db.sqlite.marshalling.Rpc_sqlite
import molecule.server.http4s.Http4s
import molecule.server.netty.Netty
import molecule.server.pekko.Pekko
import molecule.server.play.Play
import molecule.server.ziohttp.ZioHttp
import zio.{Runtime, Unsafe}

/**
 * Test Molecule SPI test suite for various databases and servers in two steps:
 *
 * Select a server/database combination in one terminal process:
 *
 * sbt dbServer/Test/run
 *
 * Then, in another terminal process, select a scala JS project with the same database, for instance H2:
 *
 * sbt
 * project dbSqlH2JS
 * test
 *
 * or
 * testOnly molecule.db.h2.Adhoc_js_h2_async
 * etc.
 */
object Run extends App {

  // Add concrete meta database definitions for boopickle
  PickleMetaDbs(pickleMetaDb)

  println("\nPlease choose a database and a server backend to test the Molecule RPC API:\n")

  List("H2", "MariaDB", "MySQL", "PostgreSQL", "SQlite")
    .zipWithIndex.foreach((db, i) => println(s"  ${i + 1}  $db"))
  println()

  val (rpc, db): (MoleculeRpc, String) = {
    @tailrec
    def chooseDb(): (MoleculeRpc, String) = {
      StdIn.readLine("Database: ").trim.toIntOption match {
        case Some(1) => (Rpc_h2, "H2")
        case Some(2) => (Rpc_mariadb, "MariaDB")
        case Some(3) => (Rpc_mysql, "MySQL")
        case Some(4) => (Rpc_postgresql, "PostgreSQL")
        case Some(5) => (Rpc_sqlite, "SQlite")
        case _       =>
          println("Please choose a valid number.")
          chooseDb()
      }
    }
    chooseDb()
  }

  println()
  List("Http4s", "Netty", "Pekko", "Play", "ZioHttp")
    .zipWithIndex.foreach((server, i) => println(s"  ${i + 1}  $server"))
  println()


  @tailrec
  private def chooseServer(): Any = {
    StdIn.readLine("Server: ").trim.toIntOption match {
      case Some(1) => Await.result(Http4s(rpc).run(db).unsafeToFuture(), Duration.Inf)
      case Some(2) => Netty(rpc).run(db).use(_ => IO.unit).unsafeRunSync()
      case Some(3) => Pekko(rpc).run(db)
      case Some(4) => Play(rpc).run(db)
      case Some(5) =>
        Unsafe.unsafe { implicit u =>
          Runtime.default.unsafe.run(
            ZioHttp(rpc).run(db)
          )
        }
      case _       =>
        println("Please choose one of the numbers above to select a backend server.")
        chooseServer()
    }
  }

  chooseServer()
}
