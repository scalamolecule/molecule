package molecule.db.datomic.test.pagination.cursor.primaryUnique

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Unique._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._
import scala.annotation.nowarn

object CursorPrimaryUnique extends DatomicTestSuite {
  val x = ""

  // (Allow pattern matching the result without warnings)
  @nowarn lazy val tests = Tests {

    "Unique primary sort" - {
      val query = Unique.int.a1.query

      "From start" - unique { implicit conn =>
        for {
          // Using attribute Unique.int with only unique values
          _ <- Unique.int.insert(1, 2, 3, 4, 5).transact

          // Sorted by unique int attribute
          c1 <- query.from(x).limit(2).get.map { case (List(1, 2), c, true) => c }
          c2 <- query.from(c1).limit(2).get.map { case (List(3, 4), c, true) => c }
          c3 <- query.from(c2).limit(2).get.map { case (List(5), c, false) => c }
          c2 <- query.from(c3).limit(-2).get.map { case (List(3, 4), c, true) => c }
          _ <- query.from(c2).limit(-2).get.map { case (List(1, 2), _, false) => () }
        } yield ()
      }

      "From end" - unique { implicit conn =>
        for {
          _ <- Unique.int.insert(1, 2, 3, 4, 5).transact

          c1 <- query.from(x).limit(-2).get.map { case (List(4, 5), c, true) => c }
          c2 <- query.from(c1).limit(-2).get.map { case (List(2, 3), c, true) => c }
          c3 <- query.from(c2).limit(-2).get.map { case (List(1), c, false) => c }
          c2 <- query.from(c3).limit(2).get.map { case (List(2, 3), c, true) => c }
          _ <- query.from(c2).limit(2).get.map { case (List(4, 5), _, false) => () }
        } yield ()
      }
    }


    "Near-unique primary sort" - {
      import molecule.coreTests.dataModels.core.dsl.Types._
      val query = Ns.date.a1.query

      "From start" - types { implicit conn =>
        for {
          // Using attribute Unique.int with only unique values
          _ <- Ns.date.insert(date1, date2, date3, date4, date5).transact

          // Sorted by unique int attribute
          c1 <- query.from(x).limit(2).get.map { case (List(`date1`, `date2`), c, true) => c }
          c2 <- query.from(c1).limit(2).get.map { case (List(`date3`, `date4`), c, true) => c }
          c3 <- query.from(c2).limit(2).get.map { case (List(`date5`), c, false) => c }
          c2 <- query.from(c3).limit(-2).get.map { case (List(`date3`, `date4`), c, true) => c }
          _ <- query.from(c2).limit(-2).get.map { case (List(`date1`, `date2`), _, false) => () }
        } yield ()
      }

      "From end" - types { implicit conn =>
        for {
          _ <- Ns.date.insert(date1, date2, date3, date4, date5).transact

          c1 <- query.from(x).limit(-2).get.map { case (List(`date4`, `date5`), c, true) => c }
          c2 <- query.from(c1).limit(-2).get.map { case (List(`date2`, `date3`), c, true) => c }
          c3 <- query.from(c2).limit(-2).get.map { case (List(`date1`), c, false) => c }
          c2 <- query.from(c3).limit(2).get.map { case (List(`date2`, `date3`), c, true) => c }
          _ <- query.from(c2).limit(2).get.map { case (List(`date4`, `date5`), _, false) => () }
        } yield ()
      }
    }
  }
}