package molecule.db.compliance.test.pagination.subUnique

import molecule.db.compliance.domains.dsl.Uniques.*
import molecule.db.compliance.setup.{DbProviders, Test, TestUtils}
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*
import scala.annotation.nowarn
import scala.util.Random

@nowarn
case class MutationAdd(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  val query = Uniques.i.a1.int.a2.query

  import api.*
  import suite.*

  "Forward: Add row before" - unique { implicit conn =>
    val pairs               = (1 to 5).toList.map((Random.nextInt(3) + 1, _))
    val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
    for {
      _ <- Uniques.i.int.insert(a, c, e).transact
      cur <- query.from("").limit(2).get.map { case (List(`a`, `c`), cur, true) => cur }

      // Add row before next page
      _ <- Uniques.i.int.insert(b).transact

      // Next page unaffected
      _ <- query.from(cur).limit(2).get.map { case (List(`e`), _, false) => () }
    } yield ()
  }

  "Forward: Add row after" - unique { implicit conn =>
    val pairs               = (1 to 5).toList.map((Random.nextInt(3) + 1, _))
    val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
    for {
      _ <- Uniques.i.int.insert(a, c, e).transact
      cur <- query.from("").limit(2).get.map { case (List(`a`, `c`), cur, true) => cur }

      // Add row after this page
      _ <- Uniques.i.int.insert(d).transact

      // Next page includes new row
      _ <- query.from(cur).limit(2).get.map { case (List(`d`, `e`), _, false) => () }
    } yield ()
  }


  "Backwards: Add row before" - unique { implicit conn =>
    val pairs               = (1 to 5).toList.map((Random.nextInt(3) + 1, _))
    val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
    for {
      _ <- Uniques.i.int.insert(a, c, e).transact
      cur <- query.from("").limit(-2).get.map { case (List(`c`, `e`), cur, true) => cur }

      // Add row before next page
      _ <- Uniques.i.int.insert(d).transact

      // Next page unaffected
      _ <- query.from(cur).limit(-2).get.map { case (List(`a`), _, false) => () }
    } yield ()
  }

  "Backwards: Add row after" - unique { implicit conn =>
    val pairs               = (1 to 5).toList.map((Random.nextInt(3) + 1, _))
    val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
    for {
      _ <- Uniques.i.int.insert(a, c, e).transact
      cur <- query.from("").limit(-2).get.map { case (List(`c`, `e`), cur, true) => cur }

      // Add row after this page
      _ <- Uniques.i.int.insert(b).transact

      // Next page includes new row
      _ <- query.from(cur).limit(-2).get.map { case (List(`a`, `b`), _, false) => () }
    } yield ()
  }
}