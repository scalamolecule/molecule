package molecule.db.compliance.test.pagination.cursor.primaryUnique

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Uniques.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import scala.annotation.nowarn

@nowarn
case class Nested(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {
  val x    = ""
  val a    = (1, List(1))
  val b    = (2, List(1))
  val c    = (3, List(1))
  val d    = (4, List(1))
  val e    = (5, List(1))
  val data = List(a, b, c, d, e)

  // (Allow pattern matching the result without warnings)

  import api.*
  import suite.*

  "Nested: From start" - unique { implicit conn =>
    val query = Uniques.int.a1.Refs.*(Ref.i).query
    for {
      // Using attribute Uniques.int with only unique values
      _ <- Uniques.int.Refs.*(Ref.i).insert(data).transact

      // Sorted by unique int attribute
      c1 <- query.from(x).limit(2).get.map { case (List(`a`, `b`), cur, true) => cur }
      c2 <- query.from(c1).limit(2).get.map { case (List(`c`, `d`), cur, true) => cur }
      c3 <- query.from(c2).limit(2).get.map { case (List(`e`), cur, false) => cur }
      c2 <- query.from(c3).limit(-2).get.map { case (List(`c`, `d`), cur, true) => cur }
      _ <- query.from(c2).limit(-2).get.map { case (List(`a`, `b`), _, false) => () }
    } yield ()
  }

  "Nested: From end" - unique { implicit conn =>
    val query = Uniques.int.a1.Refs.*(Ref.i).query
    for {
      _ <- Uniques.int.Refs.*(Ref.i).insert(data).transact

      c1 <- query.from(x).limit(-2).get.map { case (List(`d`, `e`), cur, true) => cur }
      c2 <- query.from(c1).limit(-2).get.map { case (List(`b`, `c`), cur, true) => cur }
      c3 <- query.from(c2).limit(-2).get.map { case (List(`a`), cur, false) => cur }
      c2 <- query.from(c3).limit(2).get.map { case (List(`b`, `c`), cur, true) => cur }
      _ <- query.from(c2).limit(2).get.map { case (List(`d`, `e`), _, false) => () }
    } yield ()
  }


  "Optional nested: From start" - unique { implicit conn =>
    val query = Uniques.int.a1.Refs.*?(Ref.i).query
    for {
      _ <- Uniques.int.Refs.*(Ref.i).insert(data).transact
      c1 <- query.from(x).limit(2).get.map { case (List(`a`, `b`), cur, true) => cur }
      c2 <- query.from(c1).limit(2).get.map { case (List(`c`, `d`), cur, true) => cur }
      c3 <- query.from(c2).limit(2).get.map { case (List(`e`), cur, false) => cur }
      c2 <- query.from(c3).limit(-2).get.map { case (List(`c`, `d`), cur, true) => cur }
      _ <- query.from(c2).limit(-2).get.map { case (List(`a`, `b`), _, false) => () }
    } yield ()
  }

  "Optional nested: From end" - unique { implicit conn =>
    val query = Uniques.int.a1.Refs.*?(Ref.i).query
    for {
      _ <- Uniques.int.Refs.*(Ref.i).insert(data).transact
      c1 <- query.from(x).limit(-2).get.map { case (List(`d`, `e`), cur, true) => cur }
      c2 <- query.from(c1).limit(-2).get.map { case (List(`b`, `c`), cur, true) => cur }
      c3 <- query.from(c2).limit(-2).get.map { case (List(`a`), cur, false) => cur }
      c2 <- query.from(c3).limit(2).get.map { case (List(`b`, `c`), cur, true) => cur }
      _ <- query.from(c2).limit(2).get.map { case (List(`d`, `e`), _, false) => () }
    } yield ()
  }
}