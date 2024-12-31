package molecule.coreTests.spi.pagination.cursor.noUnique

import molecule.base.error.ModelError
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.domains.dsl.Types._
import molecule.coreTests.setup._
import scala.annotation.tailrec
import scala.util.Random

case class MutationDelete(
  suite: MUnitSuite,
  api: Api_async with Spi_async with DbProviders
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

  import api._
  import suite._

  "Forward: Delete row before" - types { implicit conn =>
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

  "Forward: Delete last edge row" - types { implicit conn =>
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

  "Forward: Delete 2 last edge rows" - types { implicit conn =>
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

  "Forward: Delete 3 last edge rows" - types { implicit conn =>
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

  "Forward: Delete row after" - types { implicit conn =>
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


  "Backwards: Delete row before" - types { implicit conn =>
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

  "Backwards: Delete first edge row" - types { implicit conn =>
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

  "Backwards: Delete 2 first edge rows" - types { implicit conn =>
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

  "Backwards: Delete 3 first edge rows" - types { implicit conn =>
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

  "Backwards: Delete row after" - types { implicit conn =>
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