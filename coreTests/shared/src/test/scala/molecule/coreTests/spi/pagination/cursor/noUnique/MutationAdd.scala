package molecule.coreTests.spi.pagination.cursor.noUnique

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.domains.dsl.Types._
import molecule.coreTests.setup._
import scala.annotation.{nowarn, tailrec}
import scala.util.Random

@nowarn
case class MutationAdd(
  suite: Test,
  api: Api_async with Spi_async with DbProviders
) extends TestUtils {

  @tailrec
  final def getPairs(acc: List[(Int, Int)]): List[(Int, Int)] = {
    if (acc.length == 5) {
      acc
    } else {
      val pair = (Random.nextInt(3) + 1, Random.nextInt(6) + 1)
      // No duplicate rows
      if (!acc.contains(pair)) getPairs(acc :+ pair) else getPairs(acc)
    }
  }

  val query = Entity.i.a1.int.a2.query

  import api._
  import suite._

  "Forward: Add row before" - types { implicit conn =>
    val pairs               = getPairs(Nil)
    val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
    for {
      _ <- Entity.i.int.insert(a, c, e).transact
      cur <- query.from("").limit(2).get.map { case (List(`a`, `c`), cur, true) => cur }

      // Add row before next page
      _ <- Entity.i.int.insert(b).transact

      // Next page unaffected
      _ <- query.from(cur).limit(2).get.map { case (List(`e`), _, false) => () }
    } yield ()
  }

  "Forward: Add row after" - types { implicit conn =>
    val pairs               = getPairs(Nil)
    val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
    for {
      _ <- Entity.i.int.insert(a, c, e).transact
      cur <- query.from("").limit(2).get.map { case (List(`a`, `c`), cur, true) => cur }

      // Add row after this page
      _ <- Entity.i.int.insert(d).transact

      // Next page includes new row
      _ <- query.from(cur).limit(2).get.map { case (List(`d`, `e`), _, false) => () }
    } yield ()
  }


  "Backwards: Add row before" - types { implicit conn =>
    val pairs               = getPairs(Nil)
    val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
    for {
      _ <- Entity.i.int.insert(a, c, e).transact
      cur <- query.from("").limit(-2).get.map { case (List(`c`, `e`), cur, true) => cur }

      // Add row before next page
      _ <- Entity.i.int.insert(d).transact

      // Next page unaffected
      _ <- query.from(cur).limit(-2).get.map { case (List(`a`), _, false) => () }
    } yield ()
  }

  "Backwards: Add row after" - types { implicit conn =>
    val pairs               = getPairs(Nil)
    val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
    for {
      _ <- Entity.i.int.insert(a, c, e).transact
      cur <- query.from("").limit(-2).get.map { case (List(`c`, `e`), cur, true) => cur }

      // Add row after this page
      _ <- Entity.i.int.insert(b).transact

      // Next page includes new row
      _ <- query.from(cur).limit(-2).get.map { case (List(`a`, `b`), _, false) => () }
    } yield ()
  }
}