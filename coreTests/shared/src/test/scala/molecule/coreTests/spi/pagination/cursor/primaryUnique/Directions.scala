package molecule.coreTests.spi.pagination.cursor.primaryUnique

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.domains.dsl.Types._
import molecule.coreTests.domains.dsl.Uniques._
import molecule.coreTests.setup._

case class Directions(
  suite: MUnitSuite,
  api: Api_async with Spi_async with DbProviders
) extends TestUtils {
  val x = ""

  // (Allow pattern matching results without warnings)

  import api._
  import suite._

  "Unique primary sort: From start" - unique { implicit conn =>
    val query = Uniques.int.a1.query
    for {
      // Using attribute Uniques.int with only unique values
      _ <- Uniques.int.insert(1, 2, 3, 4, 5).transact

      // Sorted by unique int attribute
      c1 <- query.from(x).limit(2).get.map { case (List(1, 2), c, true) => c }
      c2 <- query.from(c1).limit(2).get.map { case (List(3, 4), c, true) => c }
      c3 <- query.from(c2).limit(2).get.map { case (List(5), c, false) => c }
      c2 <- query.from(c3).limit(-2).get.map { case (List(3, 4), c, true) => c }
      _ <- query.from(c2).limit(-2).get.map { case (List(1, 2), _, false) => () }
    } yield ()
  }

  "Unique primary sort: From end" - unique { implicit conn =>
    val query = Uniques.int.a1.query
    for {
      _ <- Uniques.int.insert(1, 2, 3, 4, 5).transact

      c1 <- query.from(x).limit(-2).get.map { case (List(4, 5), c, true) => c }
      c2 <- query.from(c1).limit(-2).get.map { case (List(2, 3), c, true) => c }
      c3 <- query.from(c2).limit(-2).get.map { case (List(1), c, false) => c }
      c2 <- query.from(c3).limit(2).get.map { case (List(2, 3), c, true) => c }
      _ <- query.from(c2).limit(2).get.map { case (List(4, 5), _, false) => () }
    } yield ()
  }


  "Near-unique primary sort: From start" - types { implicit conn =>
    val query = Entity.date.a1.query
    for {
      // Using attribute Uniques.int with only unique values
      _ <- Entity.date.insert(date1, date2, date3, date4, date5).transact

      // Sorted by unique int attribute
      c1 <- query.from(x).limit(2).get.map { case (dateSet, c, true) if dateSet == List(date1, date2) => c }
      c2 <- query.from(c1).limit(2).get.map { case (dateSet, c, true) if dateSet == List(date3, date4) => c }
      c3 <- query.from(c2).limit(2).get.map { case (dateSet, c, false) if dateSet == List(date5) => c }
      c2 <- query.from(c3).limit(-2).get.map { case (dateSet, c, true) if dateSet == List(date3, date4) => c }
      _ <- query.from(c2).limit(-2).get.map { case (dateSet, _, false) if dateSet == List(date1, date2) => () }
    } yield ()
  }

  "Near-unique primary sort: From end" - types { implicit conn =>
    val query = Entity.date.a1.query
    for {
      _ <- Entity.date.insert(date1, date2, date3, date4, date5).transact

      c1 <- query.from(x).limit(-2).get.map { case (dateSet, c, true) if dateSet == List(date4, date5) => c }
      c2 <- query.from(c1).limit(-2).get.map { case (dateSet, c, true) if dateSet == List(date2, date3) => c }
      c3 <- query.from(c2).limit(-2).get.map { case (dateSet, c, false) if dateSet == List(date1) => c }
      c2 <- query.from(c3).limit(2).get.map { case (dateSet, c, true) if dateSet == List(date2, date3) => c }
      _ <- query.from(c2).limit(2).get.map { case (dateSet, _, false) if dateSet == List(date4, date5) => () }
    } yield ()
  }
}