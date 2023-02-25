package molecule.datomic.test.pagination.cursor.subUnique

import molecule.base.util.exceptions.MoleculeError
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Unique._
import molecule.datomic.setup.DatomicTestSuite
import molecule.datomic.async._
import utest._
import scala.annotation.nowarn
import scala.util.Random

object MutationDelete extends DatomicTestSuite {

  val query = Unique.i.a1.int.a2.query

  @nowarn lazy val tests = Tests {

    "Forward" - {

      "Delete row before" - unique { implicit conn =>
        val pairs            = (1 to 4).toList.map((Random.between(1, 3), _))
        val List(a, b, c, d) = pairs.sortBy(p => (p._1, p._2))
        for {
          List(e1, e2, e3, e4) <- Unique.i.int.insert(a, b, c, d).transact.map(_.eids)
          cur <- query.from("").limit(2).get.map { case (List(`a`, `b`), cur, true) => cur }

          // Delete row before next page
          _ <- Unique(e1).delete.transact

          // Next page unaffected
          _ <- query.from(cur).limit(2).get.map { case (List(`c`, `d`), _, false) => () }
        } yield ()
      }

      "Delete last edge row" - unique { implicit conn =>
        val pairs                  = (1 to 6).toList.map((Random.between(1, 3), _))
        val List(a, b, c, d, e, f) = pairs.sortBy(p => (p._1, p._2))
        for {
          List(e1, e2, e3, e4, e5, e6) <- Unique.i.int.insert(a, b, c, d, e, f).transact.map(_.eids)
          cur <- query.from("").limit(4).get.map { case (List(`a`, `b`, `c`, `d`), cur, true) => cur }

          // Delete last row on this page
          _ <- Unique(e4).delete.transact

          // Next page unaffected
          _ <- query.from(cur).limit(4).get.map { case (List(`e`, `f`), _, false) => () }
        } yield ()
      }

      "Delete 2 last edge rows" - unique { implicit conn =>
        val pairs                  = (1 to 6).toList.map((Random.between(1, 3), _))
        val List(a, b, c, d, e, f) = pairs.sortBy(p => (p._1, p._2))
        for {
          List(e1, e2, e3, e4, e5, e6) <- Unique.i.int.insert(a, b, c, d, e, f).transact.map(_.eids)
          cur <- query.from("").limit(4).get.map { case (List(`a`, `b`, `c`, `d`), cur, true) => cur }

          // Delete 2 last row on this page
          _ <- Unique(e3, e4).delete.transact

          // Next page unaffected
          _ <- query.from(cur).limit(4).get.map { case (List(`e`, `f`), _, false) => () }
        } yield ()
      }

      "Delete 3 last edge rows" - unique { implicit conn =>
        val pairs                  = (1 to 6).toList.map((Random.between(1, 3), _))
        val List(a, b, c, d, e, f) = pairs.sortBy(p => (p._1, p._2))
        for {
          List(e1, e2, e3, e4, e5, e6) <- Unique.i.int.insert(a, b, c, d, e, f).transact.map(_.eids)
          cur <- query.from("").limit(4).get.map { case (List(`a`, `b`, `c`, `d`), cur, true) => cur }

          // Delete 3 last row on this page
          _ <- Unique(e2, e3, e4).delete.transact

          // Can't find next page with all 3 unique edge values deleted (or updated)
          _ <- query.from(cur).limit(4).get
            .map(_ ==> "Unexpected success")
            .recover { case MoleculeError(msg, _) =>
              msg ==> "Couldn't find next page. Edge rows were all deleted/updated."
            }
        } yield ()
      }

      "Delete row after" - unique { implicit conn =>
        val pairs            = (1 to 4).toList.map((Random.between(1, 3), _))
        val List(a, b, c, d) = pairs.sortBy(p => (p._1, p._2))
        for {
          List(e1, e2, e3, e4) <- Unique.i.int.insert(a, b, c, d).transact.map(_.eids)
          cur <- query.from("").limit(2).get.map { case (List(`a`, `b`), cur, true) => cur }

          // Delete row after this page
          _ <- Unique(e3).delete.transact

          // Next page without deleted row
          _ <- query.from(cur).limit(2).get.map { case (List(`d`), _, false) => () }
        } yield ()
      }
    }


    "Backwards" - {

      "Delete row before" - unique { implicit conn =>
        val pairs            = (1 to 4).toList.map((Random.between(1, 3), _))
        val List(a, b, c, d) = pairs.sortBy(p => (p._1, p._2))
        for {
          List(e1, e2, e3, e4) <- Unique.i.int.insert(a, b, c, d).transact.map(_.eids)
          cur <- query.from("").limit(-2).get.map { case (List(`c`, `d`), cur, true) => cur }

          // Delete row before next page
          _ <- Unique(e4).delete.transact

          // Next page unaffected
          _ <- query.from(cur).limit(-2).get.map { case (List(`a`, `b`), _, false) => () }
        } yield ()
      }

      "Delete first edge row" - unique { implicit conn =>
        val pairs                  = (1 to 6).toList.map((Random.between(1, 3), _))
        val List(a, b, c, d, e, f) = pairs.sortBy(p => (p._1, p._2))
        for {
          List(e1, e2, e3, e4, e5, e6) <- Unique.i.int.insert(a, b, c, d, e, f).transact.map(_.eids)
          cur <- query.from("").limit(-4).get.map { case (List(`c`, `d`, `e`, `f`), cur, true) => cur }

          // Delete first row on this page
          _ <- Unique(e3).delete.transact

          // Next page unaffected
          _ <- query.from(cur).limit(-4).get.map { case (List(`a`, `b`), _, false) => () }
        } yield ()
      }

      "Delete 2 first edge rows" - unique { implicit conn =>
        val pairs                  = (1 to 6).toList.map((Random.between(1, 3), _))
        val List(a, b, c, d, e, f) = pairs.sortBy(p => (p._1, p._2))
        for {
          List(e1, e2, e3, e4, e5, e6) <- Unique.i.int.insert(a, b, c, d, e, f).transact.map(_.eids)
          cur <- query.from("").limit(-4).get.map { case (List(`c`, `d`, `e`, `f`), cur, true) => cur }

          // Delete 2 first row on this page
          _ <- Unique(e3, e4).delete.transact

          // Next page unaffected
          _ <- query.from(cur).limit(-4).get.map { case (List(`a`, `b`), _, false) => () }
        } yield ()
      }

      "Delete 3 first edge rows" - unique { implicit conn =>
        val pairs                  = (1 to 6).toList.map((Random.between(1, 3), _))
        val List(a, b, c, d, e, f) = pairs.sortBy(p => (p._1, p._2))
        for {
          List(e1, e2, e3, e4, e5, e6) <- Unique.i.int.insert(a, b, c, d, e, f).transact.map(_.eids)
          cur <- query.from("").limit(-4).get.map { case (List(`c`, `d`, `e`, `f`), cur, true) => cur }

          // Delete 3 first row on this page
          _ <- Unique(e3, e4, e5).delete.transact

          // Can't find next page with all 3 unique edge values deleted (or updated)
          _ <- query.from(cur).limit(-4).get
            .map(_ ==> "Unexpected success")
            .recover { case MoleculeError(msg, _) =>
              msg ==> "Couldn't find next page. Edge rows were all deleted/updated."
            }
        } yield ()
      }

      "Delete row after" - unique { implicit conn =>
        val pairs            = (1 to 4).toList.map((Random.between(1, 3), _))
        val List(a, b, c, d) = pairs.sortBy(p => (p._1, p._2))
        for {
          List(e1, e2, e3, e4) <- Unique.i.int.insert(a, b, c, d).transact.map(_.eids)
          cur <- query.from("").limit(-2).get.map { case (List(`c`, `d`), cur, true) => cur }

          // Delete row after this page
          _ <- Unique(e2).delete.transact

          // Next page without deleted row
          _ <- query.from(cur).limit(-2).get.map { case (List(`a`), _, false) => () }
        } yield ()
      }
    }
  }
}