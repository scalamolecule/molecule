package molecule.db.compliance.test.pagination.subUnique

import scala.annotation.nowarn
import scala.util.Random
import molecule.core.error.ModelError
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Uniques.*
import molecule.db.compliance.setup.DbProviders

@nowarn
case class MutationDelete(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  val query = Uniques.i.a1.int.a2.query

  import api.*
  import suite.*

  "Forward: Delete row before" - unique {
    val pairs            = (1 to 4).toList.map((Random.nextInt(3) + 1, _))
    val List(a, b, c, d) = pairs.sortBy(p => (p._1, p._2))
    for {
      List(e1, e2, e3, e4) <- Uniques.i.int.insert(a, b, c, d).transact.map(_.ids)
      cur <- query.from("").limit(2).get.map { case (List(`a`, `b`), cur, true) => cur }

      // Delete row before next page
      _ <- Uniques(e1).delete.transact

      // Next page unaffected
      _ <- query.from(cur).limit(2).get.map { case (List(`c`, `d`), _, false) => () }
    } yield ()
  }

  "Forward: Delete last edge row" - unique {
    val pairs                  = (1 to 6).toList.map((Random.nextInt(3) + 1, _))
    val List(a, b, c, d, e, f) = pairs.sortBy(p => (p._1, p._2))
    for {
      List(e1, e2, e3, e4, e5, e6) <- Uniques.i.int.insert(a, b, c, d, e, f).transact.map(_.ids)
      cur <- query.from("").limit(4).get.map { case (List(`a`, `b`, `c`, `d`), cur, true) => cur }

      // Delete last row on this page
      _ <- Uniques(e4).delete.transact

      // Next page unaffected
      _ <- query.from(cur).limit(4).get.map { case (List(`e`, `f`), _, false) => () }
    } yield ()
  }

  "Forward: Delete 2 last edge rows" - unique {
    val pairs                  = (1 to 6).toList.map((Random.nextInt(3) + 1, _))
    val List(a, b, c, d, e, f) = pairs.sortBy(p => (p._1, p._2))
    for {
      List(e1, e2, e3, e4, e5, e6) <- Uniques.i.int.insert(a, b, c, d, e, f).transact.map(_.ids)
      cur <- query.from("").limit(4).get.map { case (List(`a`, `b`, `c`, `d`), cur, true) => cur }

      // Delete 2 last row on this page
      _ <- Uniques(e3, e4).delete.transact

      // Next page unaffected
      _ <- query.from(cur).limit(4).get.map { case (List(`e`, `f`), _, false) => () }
    } yield ()
  }

  "Forward: Delete 3 last edge rows" - unique {
    val pairs                  = (1 to 6).toList.map((Random.nextInt(3) + 1, _))
    val List(a, b, c, d, e, f) = pairs.sortBy(p => (p._1, p._2))
    for {
      List(e1, e2, e3, e4, e5, e6) <- Uniques.i.int.insert(a, b, c, d, e, f).transact.map(_.ids)
      cur <- query.from("").limit(4).get.map { case (List(`a`, `b`, `c`, `d`), cur, true) => cur }

      // Delete 3 last row on this page
      _ <- Uniques(e2, e3, e4).delete.transact

      // Can't find next page with all 3 unique edge values deleted (or updated)
      _ <- query.from(cur).limit(4).get
        .map(_ ==> "Unexpected success")
        .recover { case ModelError(msg) =>
          msg ==> "Couldn't find next page. Edge rows were all deleted/updated."
        }
    } yield ()
  }

  "Forward: Delete row after" - unique {
    val pairs            = (1 to 4).toList.map((Random.nextInt(3) + 1, _))
    val List(a, b, c, d) = pairs.sortBy(p => (p._1, p._2))
    for {
      List(e1, e2, e3, e4) <- Uniques.i.int.insert(a, b, c, d).transact.map(_.ids)
      cur <- query.from("").limit(2).get.map { case (List(`a`, `b`), cur, true) => cur }

      // Delete row after this page
      _ <- Uniques(e3).delete.transact

      // Next page without deleted row
      _ <- query.from(cur).limit(2).get.map { case (List(`d`), _, false) => () }
    } yield ()
  }


  "Backwards: Delete row before" - unique {
    val pairs            = (1 to 4).toList.map((Random.nextInt(3) + 1, _))
    val List(a, b, c, d) = pairs.sortBy(p => (p._1, p._2))
    for {
      List(e1, e2, e3, e4) <- Uniques.i.int.insert(a, b, c, d).transact.map(_.ids)
      cur <- query.from("").limit(-2).get.map { case (List(`c`, `d`), cur, true) => cur }

      // Delete row before next page
      _ <- Uniques(e4).delete.transact

      // Next page unaffected
      _ <- query.from(cur).limit(-2).get.map { case (List(`a`, `b`), _, false) => () }
    } yield ()
  }

  "Backwards: Delete first edge row" - unique {
    val pairs                  = (1 to 6).toList.map((Random.nextInt(3) + 1, _))
    val List(a, b, c, d, e, f) = pairs.sortBy(p => (p._1, p._2))
    for {
      List(e1, e2, e3, e4, e5, e6) <- Uniques.i.int.insert(a, b, c, d, e, f).transact.map(_.ids)
      cur <- query.from("").limit(-4).get.map { case (List(`c`, `d`, `e`, `f`), cur, true) => cur }

      // Delete first row on this page
      _ <- Uniques(e3).delete.transact

      // Next page unaffected
      _ <- query.from(cur).limit(-4).get.map { case (List(`a`, `b`), _, false) => () }
    } yield ()
  }

  "Backwards: Delete 2 first edge rows" - unique {
    val pairs                  = (1 to 6).toList.map((Random.nextInt(3) + 1, _))
    val List(a, b, c, d, e, f) = pairs.sortBy(p => (p._1, p._2))
    for {
      List(e1, e2, e3, e4, e5, e6) <- Uniques.i.int.insert(a, b, c, d, e, f).transact.map(_.ids)
      cur <- query.from("").limit(-4).get.map { case (List(`c`, `d`, `e`, `f`), cur, true) => cur }

      // Delete 2 first row on this page
      _ <- Uniques(e3, e4).delete.transact

      // Next page unaffected
      _ <- query.from(cur).limit(-4).get.map { case (List(`a`, `b`), _, false) => () }
    } yield ()
  }

  "Backwards: Delete 3 first edge rows" - unique {
    val pairs                  = (1 to 6).toList.map((Random.nextInt(3) + 1, _))
    val List(a, b, c, d, e, f) = pairs.sortBy(p => (p._1, p._2))
    for {
      List(e1, e2, e3, e4, e5, e6) <- Uniques.i.int.insert(a, b, c, d, e, f).transact.map(_.ids)
      cur <- query.from("").limit(-4).get.map { case (List(`c`, `d`, `e`, `f`), cur, true) => cur }

      // Delete 3 first row on this page
      _ <- Uniques(e3, e4, e5).delete.transact

      // Can't find next page with all 3 unique edge values deleted (or updated)
      _ <- query.from(cur).limit(-4).get
        .map(_ ==> "Unexpected success").recover { case ModelError(msg) =>
          msg ==> "Couldn't find next page. Edge rows were all deleted/updated."
        }
    } yield ()
  }

  "Backwards: Delete row after" - unique {
    val pairs            = (1 to 4).toList.map((Random.nextInt(3) + 1, _))
    val List(a, b, c, d) = pairs.sortBy(p => (p._1, p._2))
    for {
      List(e1, e2, e3, e4) <- Uniques.i.int.insert(a, b, c, d).transact.map(_.ids)
      cur <- query.from("").limit(-2).get.map { case (List(`c`, `d`), cur, true) => cur }

      // Delete row after this page
      _ <- Uniques(e2).delete.transact

      // Next page without deleted row
      _ <- query.from(cur).limit(-2).get.map { case (List(`a`), _, false) => () }
    } yield ()
  }
}