package molecule.coreTests.test.pagination.cursor.noUnique

import molecule.base.error.ModelError
import molecule.coreTests.api.ApiAsyncImplicits
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.async._
import molecule.coreTests.setup.CoreTestSuite
import molecule.core.spi.SpiAsync
import utest._
import scala.annotation.{nowarn, tailrec}
import scala.util.Random

trait MutationDelete extends CoreTestSuite with ApiAsyncImplicits { self: SpiAsync  =>

  @tailrec
  final def getPairs(n: Int, acc: List[(Int, Int)]): List[(Int, Int)] = {
    if (acc.length != n) {
      val pair = (Random.nextInt(3) + 1, Random.nextInt(n + 1) + 1)
      // No duplicate rows
      if (!acc.contains(pair)) getPairs(n, acc :+ pair) else getPairs(n, acc)
    } else {
      acc
    }
  }

  val query = Ns.i.a1.int.a2.query

  @nowarn lazy val tests = Tests {

    "Forward" - {

      "Delete row before" - types { implicit conn =>
        val pairs            = getPairs(4, Nil)
        val List(a, b, c, d) = pairs.sortBy(p => (p._1, p._2))
        for {
          List(e1, e2, e3, e4) <- Ns.i.int.insert(a, b, c, d).transact.map(_.ids)
          cur <- query.from("").limit(2).get.map { case (List(`a`, `b`), cur, true) => cur }

          // Delete row before next page
          _ <- Ns(e1).delete.transact

          // Next page unaffected
          _ <- query.from(cur).limit(2).get.map { case (List(`c`, `d`), _, false) => () }
        } yield ()
      }

      "Delete last edge row" - types { implicit conn =>
        val pairs                  = getPairs(6, Nil)
        val List(a, b, c, d, e, f) = pairs.sortBy(p => (p._1, p._2))
        for {
          List(e1, e2, e3, e4, e5, e6) <- Ns.i.int.insert(a, b, c, d, e, f).transact.map(_.ids)
          cur <- query.from("").limit(4).get.map { case (List(`a`, `b`, `c`, `d`), cur, true) => cur }

          // Delete last row on this page
          _ <- Ns(e4).delete.transact

          // Next page unaffected
          _ <- query.from(cur).limit(4).get.map { case (List(`e`, `f`), _, false) => () }
        } yield ()
      }

      "Delete 2 last edge rows" - types { implicit conn =>
        val pairs                  = getPairs(6, Nil)
        val List(a, b, c, d, e, f) = pairs.sortBy(p => (p._1, p._2))
        for {
          List(e1, e2, e3, e4, e5, e6) <- Ns.i.int.insert(a, b, c, d, e, f).transact.map(_.ids)
          cur <- query.from("").limit(4).get.map { case (List(`a`, `b`, `c`, `d`), cur, true) => cur }

          // Delete 2 last row on this page
          _ <- Ns(e3, e4).delete.transact

          // Next page unaffected
          _ <- query.from(cur).limit(4).get.map { case (List(`e`, `f`), _, false) => () }
        } yield ()
      }

      "Delete 3 last edge rows" - types { implicit conn =>
        val pairs                  = getPairs(6, Nil)
        val List(a, b, c, d, e, f) = pairs.sortBy(p => (p._1, p._2))
        for {
          List(e1, e2, e3, e4, e5, e6) <- Ns.i.int.insert(a, b, c, d, e, f).transact.map(_.ids)
          cur <- query.from("").limit(4).get.map { case (List(`a`, `b`, `c`, `d`), cur, true) => cur }

          // Delete 3 last row on this page
          _ <- Ns(e2, e3, e4).delete.transact

          // Can't find next page with all 3 unique edge values deleted (or updated)
          _ <- query.from(cur).limit(4).get
            .map(_ ==> "Unexpected success").recover { case ModelError(msg) =>
            msg ==> "Couldn't find next page. Edge rows were all deleted/updated."
          }
        } yield ()
      }

      "Delete row after" - types { implicit conn =>
        val pairs            = getPairs(4, Nil)
        val List(a, b, c, d) = pairs.sortBy(p => (p._1, p._2))
        for {
          List(e1, e2, e3, e4) <- Ns.i.int.insert(a, b, c, d).transact.map(_.ids)
          cur <- query.from("").limit(2).get.map { case (List(`a`, `b`), cur, true) => cur }

          // Delete row after this page
          _ <- Ns(e3).delete.transact

          // Next page without deleted row
          _ <- query.from(cur).limit(2).get.map { case (List(`d`), _, false) => () }
        } yield ()
      }
    }


    "Backwards" - {

      "Delete row before" - types { implicit conn =>
        val pairs            = getPairs(4, Nil)
        val List(a, b, c, d) = pairs.sortBy(p => (p._1, p._2))
        for {
          List(e1, e2, e3, e4) <- Ns.i.int.insert(a, b, c, d).transact.map(_.ids)
          cur <- query.from("").limit(-2).get.map { case (List(`c`, `d`), cur, true) => cur }

          // Delete row before next page
          _ <- Ns(e4).delete.transact

          // Next page unaffected
          _ <- query.from(cur).limit(-2).get.map { case (List(`a`, `b`), _, false) => () }
        } yield ()
      }

      "Delete first edge row" - types { implicit conn =>
        val pairs                  = getPairs(6, Nil)
        val List(a, b, c, d, e, f) = pairs.sortBy(p => (p._1, p._2))
        for {
          List(e1, e2, e3, e4, e5, e6) <- Ns.i.int.insert(a, b, c, d, e, f).transact.map(_.ids)
          cur <- query.from("").limit(-4).get.map { case (List(`c`, `d`, `e`, `f`), cur, true) => cur }

          // Delete first row on this page
          _ <- Ns(e3).delete.transact

          // Next page unaffected
          _ <- query.from(cur).limit(-4).get.map { case (List(`a`, `b`), _, false) => () }
        } yield ()
      }

      "Delete 2 first edge rows" - types { implicit conn =>
        val pairs                  = getPairs(6, Nil)
        val List(a, b, c, d, e, f) = pairs.sortBy(p => (p._1, p._2))
        for {
          List(e1, e2, e3, e4, e5, e6) <- Ns.i.int.insert(a, b, c, d, e, f).transact.map(_.ids)
          cur <- query.from("").limit(-4).get.map { case (List(`c`, `d`, `e`, `f`), cur, true) => cur }

          // Delete 2 first row on this page
          _ <- Ns(e3, e4).delete.transact

          // Next page unaffected
          _ <- query.from(cur).limit(-4).get.map { case (List(`a`, `b`), _, false) => () }
        } yield ()
      }

      "Delete 3 first edge rows" - types { implicit conn =>
        val pairs                  = getPairs(6, Nil)
        val List(a, b, c, d, e, f) = pairs.sortBy(p => (p._1, p._2))
        for {
          List(e1, e2, e3, e4, e5, e6) <- Ns.i.int.insert(a, b, c, d, e, f).transact.map(_.ids)
          cur <- query.from("").limit(-4).get.map { case (List(`c`, `d`, `e`, `f`), cur, true) => cur }

          // Delete 3 first row on this page
          _ <- Ns(e3, e4, e5).delete.transact

          // Can't find next page with all 3 unique edge values deleted (or updated)
          _ <- query.from(cur).limit(-4).get
            .map(_ ==> "Unexpected success").recover { case ModelError(msg) =>
            msg ==> "Couldn't find next page. Edge rows were all deleted/updated."
          }
        } yield ()
      }

      "Delete row after" - types { implicit conn =>
        val pairs            = getPairs(4, Nil)
        val List(a, b, c, d) = pairs.sortBy(p => (p._1, p._2))
        for {
          List(e1, e2, e3, e4) <- Ns.i.int.insert(a, b, c, d).transact.map(_.ids)
          cur <- query.from("").limit(-2).get.map { case (List(`c`, `d`), cur, true) => cur }

          // Delete row after this page
          _ <- Ns(e2).delete.transact

          // Next page without deleted row
          _ <- query.from(cur).limit(-2).get.map { case (List(`a`), _, false) => () }
        } yield ()
      }
    }
  }
}