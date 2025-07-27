package molecule.db.compliance.test.pagination.cursor.noUnique

import molecule.base.error.ModelError
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import scala.annotation.{nowarn, tailrec}
import scala.util.Random

@nowarn
case class MutationDelete(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  @tailrec
  final def getPairs(n: Int, acc: List[(Int, Int)]): List[(Int, Int)] = {
    if (acc.length == n) {
      acc
    } else {
      val pair = (Random.nextInt(3) + 1, Random.nextInt(n + 1) + 1)
      // No duplicate rows
      if (!acc.contains(pair)) getPairs(n, acc :+ pair) else getPairs(n, acc)
    }
  }

  val query = Entity.i.a1.int.a2.query

  import api.*
  import suite.*

  "Forward: Delete row before" - types {
    val pairs            = getPairs(4, Nil)
    val List(a, b, c, d) = pairs.sortBy(p => (p._1, p._2))
    for {
      List(e1, e2, e3, e4) <- Entity.i.int.insert(a, b, c, d).transact.map(_.ids)
      cur <- query.from("").limit(2).get.map { case (List(`a`, `b`), cur, true) => cur }

      // Delete row before next page
      _ <- Entity(e1).delete.transact

      // Next page unaffected
      _ <- query.from(cur).limit(2).get.map { case (List(`c`, `d`), _, false) => () }
    } yield ()
  }

  "Forward: Delete last edge row" - types {
    val pairs                  = getPairs(6, Nil)
    val List(a, b, c, d, e, f) = pairs.sortBy(p => (p._1, p._2))
    for {
      List(e1, e2, e3, e4, e5, e6) <- Entity.i.int.insert(a, b, c, d, e, f).transact.map(_.ids)
      cur <- query.from("").limit(4).get.map { case (List(`a`, `b`, `c`, `d`), cur, true) => cur }

      // Delete last row on this page
      _ <- Entity(e4).delete.transact

      // Next page unaffected
      _ <- query.from(cur).limit(4).get.map { case (List(`e`, `f`), _, false) => () }
    } yield ()
  }

  "Forward: Delete 2 last edge rows" - types {
    val pairs                  = getPairs(6, Nil)
    val List(a, b, c, d, e, f) = pairs.sortBy(p => (p._1, p._2))
    for {
      List(e1, e2, e3, e4, e5, e6) <- Entity.i.int.insert(a, b, c, d, e, f).transact.map(_.ids)
      cur <- query.from("").limit(4).get.map { case (List(`a`, `b`, `c`, `d`), cur, true) => cur }

      // Delete 2 last row on this page
      _ <- Entity(e3, e4).delete.transact

      // Next page unaffected
      _ <- query.from(cur).limit(4).get.map { case (List(`e`, `f`), _, false) => () }
    } yield ()
  }

  "Forward: Delete 3 last edge rows" - types {
    val pairs                  = getPairs(6, Nil)
    val List(a, b, c, d, e, f) = pairs.sortBy(p => (p._1, p._2))
    for {
      List(e1, e2, e3, e4, e5, e6) <- Entity.i.int.insert(a, b, c, d, e, f).transact.map(_.ids)
      cur <- query.from("").limit(4).get.map { case (List(`a`, `b`, `c`, `d`), cur, true) => cur }

      // Delete 3 last row on this page
      _ <- Entity(e2, e3, e4).delete.transact

      // Can't find next page with all 3 unique edge values deleted (or updated)
      _ <- query.from(cur).limit(4).get
        .map(_ ==> "Unexpected success").recover { case ModelError(msg) =>
          msg ==> "Couldn't find next page. Edge rows were all deleted/updated."
        }
    } yield ()
  }

  "Forward: Delete row after" - types {
    val pairs            = getPairs(4, Nil)
    val List(a, b, c, d) = pairs.sortBy(p => (p._1, p._2))
    for {
      List(e1, e2, e3, e4) <- Entity.i.int.insert(a, b, c, d).transact.map(_.ids)
      cur <- query.from("").limit(2).get.map { case (List(`a`, `b`), cur, true) => cur }

      // Delete row after this page
      _ <- Entity(e3).delete.transact

      // Next page without deleted row
      _ <- query.from(cur).limit(2).get.map { case (List(`d`), _, false) => () }
    } yield ()
  }


  "Backwards: Delete row before" - types {
    val pairs            = getPairs(4, Nil)
    val List(a, b, c, d) = pairs.sortBy(p => (p._1, p._2))
    for {
      List(e1, e2, e3, e4) <- Entity.i.int.insert(a, b, c, d).transact.map(_.ids)
      cur <- query.from("").limit(-2).get.map { case (List(`c`, `d`), cur, true) => cur }

      // Delete row before next page
      _ <- Entity(e4).delete.transact

      // Next page unaffected
      _ <- query.from(cur).limit(-2).get.map { case (List(`a`, `b`), _, false) => () }
    } yield ()
  }

  "Backwards: Delete first edge row" - types {
    val pairs                  = getPairs(6, Nil)
    val List(a, b, c, d, e, f) = pairs.sortBy(p => (p._1, p._2))
    for {
      List(e1, e2, e3, e4, e5, e6) <- Entity.i.int.insert(a, b, c, d, e, f).transact.map(_.ids)
      cur <- query.from("").limit(-4).get.map { case (List(`c`, `d`, `e`, `f`), cur, true) => cur }

      // Delete first row on this page
      _ <- Entity(e3).delete.transact

      // Next page unaffected
      _ <- query.from(cur).limit(-4).get.map { case (List(`a`, `b`), _, false) => () }
    } yield ()
  }

  "Backwards: Delete 2 first edge rows" - types {
    val pairs                  = getPairs(6, Nil)
    val List(a, b, c, d, e, f) = pairs.sortBy(p => (p._1, p._2))
    for {
      List(e1, e2, e3, e4, e5, e6) <- Entity.i.int.insert(a, b, c, d, e, f).transact.map(_.ids)
      cur <- query.from("").limit(-4).get.map { case (List(`c`, `d`, `e`, `f`), cur, true) => cur }

      // Delete 2 first row on this page
      _ <- Entity(e3, e4).delete.transact

      // Next page unaffected
      _ <- query.from(cur).limit(-4).get.map { case (List(`a`, `b`), _, false) => () }
    } yield ()
  }

  "Backwards: Delete 3 first edge rows" - types {
    val pairs                  = getPairs(6, Nil)
    val List(a, b, c, d, e, f) = pairs.sortBy(p => (p._1, p._2))
    for {
      List(e1, e2, e3, e4, e5, e6) <- Entity.i.int.insert(a, b, c, d, e, f).transact.map(_.ids)
      cur <- query.from("").limit(-4).get.map { case (List(`c`, `d`, `e`, `f`), cur, true) => cur }

      // Delete 3 first row on this page
      _ <- Entity(e3, e4, e5).delete.transact

      // Can't find next page with all 3 unique edge values deleted (or updated)
      _ <- query.from(cur).limit(-4).get
        .map(_ ==> "Unexpected success").recover { case ModelError(msg) =>
          msg ==> "Couldn't find next page. Edge rows were all deleted/updated."
        }
    } yield ()
  }

  "Backwards: Delete row after" - types {
    val pairs            = getPairs(4, Nil)
    val List(a, b, c, d) = pairs.sortBy(p => (p._1, p._2))
    for {
      List(e1, e2, e3, e4) <- Entity.i.int.insert(a, b, c, d).transact.map(_.ids)
      cur <- query.from("").limit(-2).get.map { case (List(`c`, `d`), cur, true) => cur }

      // Delete row after this page
      _ <- Entity(e2).delete.transact

      // Next page without deleted row
      _ <- query.from(cur).limit(-2).get.map { case (List(`a`), _, false) => () }
    } yield ()
  }
}