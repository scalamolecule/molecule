package molecule.coreTests.spi.pagination.cursor.primaryUnique

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Uniques._
import molecule.coreTests.setup.CoreTestSuite
import utest._
import scala.annotation.nowarn

trait Nested extends CoreTestSuite with ApiAsync { spi: SpiAsync =>
  val x    = ""
  val a    = (1, List(1))
  val b    = (2, List(1))
  val c    = (3, List(1))
  val d    = (4, List(1))
  val e    = (5, List(1))
  val data = List(a, b, c, d, e)

  // (Allow pattern matching the result without warnings)
  @nowarn lazy val tests = Tests {

    "Nested" - {
      val query = Uniques.int.a1.Refs.*(Ref.i).query

      "From start" - unique { implicit conn =>
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

      "From end" - unique { implicit conn =>
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

    "Optional nested" - {
      val query = Uniques.int.a1.Refs.*?(Ref.i).query

      "From start" - unique { implicit conn =>
        for {
          _ <- Uniques.int.Refs.*(Ref.i).insert(data).transact
          c1 <- query.from(x).limit(2).get.map { case (List(`a`, `b`), cur, true) => cur }
          c2 <- query.from(c1).limit(2).get.map { case (List(`c`, `d`), cur, true) => cur }
          c3 <- query.from(c2).limit(2).get.map { case (List(`e`), cur, false) => cur }
          c2 <- query.from(c3).limit(-2).get.map { case (List(`c`, `d`), cur, true) => cur }
          _ <- query.from(c2).limit(-2).get.map { case (List(`a`, `b`), _, false) => () }
        } yield ()
      }

      "From end" - unique { implicit conn =>
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
  }
}