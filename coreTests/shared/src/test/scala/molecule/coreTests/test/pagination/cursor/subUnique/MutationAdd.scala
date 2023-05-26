package molecule.coreTests.test.pagination.cursor.subUnique

import molecule.coreTests.api.ApiAsyncImplicits
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Unique._
import molecule.coreTests.async._
import molecule.coreTests.setup.CoreTestSuite
import molecule.core.spi.SpiAsync
import utest._
import scala.annotation.nowarn
import scala.util.Random

trait MutationAdd extends CoreTestSuite with ApiAsyncImplicits { self: SpiAsync  =>

  val query = Unique.i.a1.int.a2.query

  @nowarn lazy val tests = Tests {

    "Forward" - {

      "Add row before" - unique { implicit conn =>
        val pairs               = (1 to 5).toList.map((Random.between(1, 3), _))
        val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
        for {
          _ <- Unique.i.int.insert(a, c, e).transact
          cur <- query.from("").limit(2).get.map { case (List(`a`, `c`), cur, true) => cur }

          // Add row before next page
          _ <- Unique.i.int.insert(b).transact

          // Next page unaffected
          _ <- query.from(cur).limit(2).get.map { case (List(`e`), _, false) => () }
        } yield ()
      }

      "Add row after" - unique { implicit conn =>
        val pairs               = (1 to 5).toList.map((Random.between(1, 3), _))
        val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
        for {
          _ <- Unique.i.int.insert(a, c, e).transact
          cur <- query.from("").limit(2).get.map { case (List(`a`, `c`), cur, true) => cur }

          // Add row after this page
          _ <- Unique.i.int.insert(d).transact

          // Next page includes new row
          _ <- query.from(cur).limit(2).get.map { case (List(`d`, `e`), _, false) => () }
        } yield ()
      }
    }


    "Backwards" - {

      "Add row before" - unique { implicit conn =>
        val pairs               = (1 to 5).toList.map((Random.between(1, 3), _))
        val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
        for {
          _ <- Unique.i.int.insert(a, c, e).transact
          cur <- query.from("").limit(-2).get.map { case (List(`c`, `e`), cur, true) => cur }

          // Add row before next page
          _ <- Unique.i.int.insert(d).transact

          // Next page unaffected
          _ <- query.from(cur).limit(-2).get.map { case (List(`a`), _, false) => () }
        } yield ()
      }

      "Add row after" - unique { implicit conn =>
        val pairs               = (1 to 5).toList.map((Random.between(1, 3), _))
        val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
        for {
          _ <- Unique.i.int.insert(a, c, e).transact
          cur <- query.from("").limit(-2).get.map { case (List(`c`, `e`), cur, true) => cur }

          // Add row after this page
          _ <- Unique.i.int.insert(b).transact

          // Next page includes new row
          _ <- query.from(cur).limit(-2).get.map { case (List(`a`, `b`), _, false) => () }
        } yield ()
      }
    }

  }
}