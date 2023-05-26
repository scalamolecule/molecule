package molecule.coreTests.test.pagination.cursor.noUnique

import molecule.coreTests.api.ApiAsyncImplicits
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.async._
import molecule.coreTests.setup.CoreTestSuite
import molecule.core.spi.SpiAsync
import utest._
import scala.annotation.{nowarn, tailrec}
import scala.util.Random

trait MutationAdd extends CoreTestSuite with ApiAsyncImplicits { self: SpiAsync  =>

  @tailrec
  final def getPairs(acc: List[(Int, Int)]): List[(Int, Int)] = {
    if (acc.length != 5) {
      val pair = (Random.between(1, 3), Random.between(1, 6))
      // No duplicate rows
      if (!acc.contains(pair)) getPairs(acc :+ pair) else getPairs(acc)
    } else {
      acc
    }
  }

  val query =  Ns.i.a1.int.a2.query

  @nowarn lazy val tests = Tests {

    "Forward" - {

      "Add row before" - types { implicit conn =>
        val pairs               = getPairs(Nil)
        val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
        for {
          _ <-  Ns.i.int.insert(a, c, e).transact
          cur <- query.from("").limit(2).get.map { case (List(`a`, `c`), cur, true) => cur }

          // Add row before next page
          _ <-  Ns.i.int.insert(b).transact

          // Next page unaffected
          _ <- query.from(cur).limit(2).get.map { case (List(`e`), _, false) => () }
        } yield ()
      }

      "Add row after" - types { implicit conn =>
        val pairs               = getPairs(Nil)
        val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
        for {
          _ <-  Ns.i.int.insert(a, c, e).transact
          cur <- query.from("").limit(2).get.map { case (List(`a`, `c`), cur, true) => cur }

          // Add row after this page
          _ <-  Ns.i.int.insert(d).transact

          // Next page includes new row
          _ <- query.from(cur).limit(2).get.map { case (List(`d`, `e`), _, false) => () }
        } yield ()
      }
    }


    "Backwards" - {

      "Add row before" - types { implicit conn =>
        val pairs               = getPairs(Nil)
        val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
        for {
          _ <-  Ns.i.int.insert(a, c, e).transact
          cur <- query.from("").limit(-2).get.map { case (List(`c`, `e`), cur, true) => cur }

          // Add row before next page
          _ <-  Ns.i.int.insert(d).transact

          // Next page unaffected
          _ <- query.from(cur).limit(-2).get.map { case (List(`a`), _, false) => () }
        } yield ()
      }

      "Add row after" - types { implicit conn =>
        val pairs               = getPairs(Nil)
        val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
        for {
          _ <-  Ns.i.int.insert(a, c, e).transact
          cur <- query.from("").limit(-2).get.map { case (List(`c`, `e`), cur, true) => cur }

          // Add row after this page
          _ <-  Ns.i.int.insert(b).transact

          // Next page includes new row
          _ <- query.from(cur).limit(-2).get.map { case (List(`a`, `b`), _, false) => () }
        } yield ()
      }
    }

  }
}