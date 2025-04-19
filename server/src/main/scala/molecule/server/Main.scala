package molecule.server

import molecule.core.marshalling.MoleculeServerEndpoints
import molecule.core.util.Executor.*
import molecule.sql.h2.marshalling.Rpc_h2
import molecule.sql.mariadb.marshalling.Rpc_mariadb
import molecule.sql.mysql.marshalling.Rpc_mysql
import molecule.sql.postgres.marshalling.Rpc_postgres
import molecule.sql.sqlite.marshalling.Rpc_sqlite
import molecule.datalog.datomic.marshalling.Rpc_datomic
import scala.io.StdIn
import molecule.core.marshalling.{MoleculeRpc, MoleculeServerEndpoints}
import scala.annotation.tailrec
import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import cats.effect.unsafe.implicits.global
import io.vertx.core.Vertx
import zio.{Unsafe, Runtime}


object Main extends App {

  println("\nWhat database do you want to test?:")
  List("H2", "MariaDB", "MySQL", "PostgreSQL", "SQlite", "Datomic")
    .zipWithIndex.foreach((db, i) => println(s"  ${i + 1}  $db"))
  println()

  val (rpc, db): (MoleculeRpc, String) = {
    @tailrec
    def chooseDb(): (MoleculeRpc, String) = {
      StdIn.readLine("Choose DB: ").toIntOption match {
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

  println("\nWhat backend server do you want to test against?:")
  List("Armeria", "Http4s", "Netty", "Pekko", "Play", "Vert.x", "ZioHttp")
    .zipWithIndex.foreach((server, i) => println(s"  ${i + 1}  $server"))
  println()


  @tailrec
  private def chooseServer(): Any = {
    StdIn.readLine("Choose Server: ").toIntOption match {
      case Some(1) => Armeria(rpc).run(db).join()
      case Some(2) => Await.result(Http4s(rpc).run(db).unsafeToFuture(), Duration.Inf)
      case Some(3) => Await.result(Netty(rpc).run(db), Duration.Inf)
      case Some(4) => Pekko(rpc).run(db)
      case Some(5) => Await.result(Play(rpc).run(db), Duration.Inf)
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
