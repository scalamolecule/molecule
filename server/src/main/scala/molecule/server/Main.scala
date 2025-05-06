package molecule.server

import cats.effect.unsafe.implicits.global
import io.vertx.core.Vertx
import molecule.core.marshalling.{MoleculeRpc, MoleculeServerEndpoints}
import molecule.core.util.Executor.*
import molecule.db.datalog.datomic.marshalling.Rpc_datomic
import molecule.db.sql.h2.marshalling.Rpc_h2
import molecule.db.sql.mariadb.marshalling.Rpc_mariadb
import molecule.db.sql.mysql.marshalling.Rpc_mysql
import molecule.db.sql.postgres.marshalling.Rpc_postgres
import molecule.db.sql.sqlite.marshalling.Rpc_sqlite
import sttp.tapir.server.netty.*
import zio.{Runtime, Unsafe}
import scala.annotation.tailrec
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.*
import scala.concurrent.{Await, Future}
import scala.io.StdIn


object Main extends App {

  println("\nPlease choose a database and a server backend to test the Molecule RPC API:\n")

  List("H2", "MariaDB", "MySQL", "PostgreSQL", "SQlite", "Datomic")
    .zipWithIndex.foreach((db, i) => println(s"  ${i + 1}  $db"))
  println()

  val (rpc, db): (MoleculeRpc, String) = {
    @tailrec
    def chooseDb(): (MoleculeRpc, String) = {
      Console.flush()
      StdIn.readLine("Database: ").trim.toIntOption match {
        case Some(1) => (Rpc_h2, "H2")
        case Some(2) => (Rpc_mariadb, "MariaDB")
        case Some(3) => (Rpc_mysql, "MySQL")
        case Some(4) => (Rpc_postgres, "PostgreSQL")
        case Some(5) => (Rpc_sqlite, "SQlite")
        case Some(6) => (Rpc_datomic, "Datomic")
        case _       =>
          println("Please choose a valid number.")
          chooseDb()
      }
    }
    chooseDb()
  }

  println()
  List("Armeria", "Http4s", "Netty", "Pekko", "Play", "Vert.x", "ZioHttp")
    .zipWithIndex.foreach((server, i) => println(s"  ${i + 1}  $server"))
  println()


  @tailrec
  private def chooseServer(): Any = {
    Console.flush()
    StdIn.readLine("Server: ").trim.toIntOption match {
      case Some(1) => Armeria(rpc).run(db)
      case Some(2) => Await.result(Http4s(rpc).run(db).unsafeToFuture(), Duration.Inf)
      case Some(3) => Netty(rpc).run(db)
      case Some(4) => Pekko(rpc).run(db)
      case Some(5) => Play(rpc).run(db)
      case Some(6) => VertX(rpc).run(db)
      case Some(7) =>
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
