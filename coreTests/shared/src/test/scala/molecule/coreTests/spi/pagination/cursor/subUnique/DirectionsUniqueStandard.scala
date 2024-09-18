package molecule.coreTests.spi.pagination.cursor.subUnique

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Uniques._
import molecule.coreTests.setup.CoreTestSuite
import utest._
import scala.annotation.nowarn

trait DirectionsUniqueStandard extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  // (Allow pattern matching the result without warnings)
  @nowarn lazy val tests = Tests {

    "Forward, asc asc" - unique { implicit conn =>
      val pairs               = (1 to 5).toList.map((_, scala.util.Random.nextInt(3) + 1))
      val List(a, b, c, d, e) = pairs.sortBy(p => (p._2, p._1))
      val query               = (cursor: String, limit: Int) => Uniques.int.a2.i.a1.query.from(cursor).limit(limit)
      for {
        _ <- Uniques.int.i.insert(pairs).transact
        c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
        c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
        c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
      } yield ()
    }

    "Forward, desc asc" - unique { implicit conn =>
      val pairs               = (1 to 5).toList.map((_, scala.util.Random.nextInt(3) + 1))
      val List(a, b, c, d, e) = pairs.sortBy(p => (p._2, -p._1))
      val query               = (cursor: String, limit: Int) => Uniques.int.d2.i.a1.query.from(cursor).limit(limit)
      for {
        _ <- Uniques.int.i.insert(pairs).transact
        c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
        c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
        c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
      } yield ()
    }

    "Forward, asc desc" - unique { implicit conn =>
      val pairs               = (1 to 5).toList.map((_, scala.util.Random.nextInt(3) + 1))
      val List(a, b, c, d, e) = pairs.sortBy(p => (-p._2, p._1))
      val query               = (cursor: String, limit: Int) => Uniques.int.a2.i.d1.query.from(cursor).limit(limit)
      for {
        _ <- Uniques.int.i.insert(pairs).transact
        c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
        c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
        c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
      } yield ()
    }

    "Forward, desc desc" - unique { implicit conn =>
      val pairs               = (1 to 5).toList.map((_, scala.util.Random.nextInt(3) + 1))
      val List(a, b, c, d, e) = pairs.sortBy(p => (-p._2, -p._1))
      val query               = (cursor: String, limit: Int) => Uniques.int.d2.i.d1.query.from(cursor).limit(limit)
      for {
        _ <- Uniques.int.i.insert(pairs).transact
        c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
        c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
        c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
      } yield ()
    }


    "Backwards, asc asc" - unique { implicit conn =>
      val pairs               = (1 to 5).toList.map((_, scala.util.Random.nextInt(3) + 1))
      val List(a, b, c, d, e) = pairs.sortBy(p => (p._2, p._1))
      val query               = (cursor: String, limit: Int) => Uniques.int.a2.i.a1.query.from(cursor).limit(limit)
      for {
        _ <- Uniques.int.i.insert(pairs).transact
        c1 <- query("", -2).get.map { case (List(`d`, `e`), cursor, true) => cursor }
        c2 <- query(c1, -2).get.map { case (List(`b`, `c`), cursor, true) => cursor }
        c3 <- query(c2, -2).get.map { case (List(`a`), cursor, false) => cursor }
        c2 <- query(c3, 2).get.map { case (List(`b`, `c`), cursor, true) => cursor }
        _ <- query(c2, 2).get.map { case (List(`d`, `e`), _, false) => () }
      } yield ()
    }

    "Backwards, desc asc" - unique { implicit conn =>
      val pairs               = (1 to 5).toList.map((_, scala.util.Random.nextInt(3) + 1))
      val List(a, b, c, d, e) = pairs.sortBy(p => (p._2, -p._1))
      val query               = (cursor: String, limit: Int) => Uniques.int.d2.i.a1.query.from(cursor).limit(limit)
      for {
        _ <- Uniques.int.i.insert(pairs).transact
        c1 <- query("", -2).get.map { case (List(`d`, `e`), cursor, true) => cursor }
        c2 <- query(c1, -2).get.map { case (List(`b`, `c`), cursor, true) => cursor }
        c3 <- query(c2, -2).get.map { case (List(`a`), cursor, false) => cursor }
        c2 <- query(c3, 2).get.map { case (List(`b`, `c`), cursor, true) => cursor }
        _ <- query(c2, 2).get.map { case (List(`d`, `e`), _, false) => () }
      } yield ()
    }

    "Backwards, asc desc" - unique { implicit conn =>
      val pairs               = (1 to 5).toList.map((_, scala.util.Random.nextInt(3) + 1))
      val List(a, b, c, d, e) = pairs.sortBy(p => (-p._2, p._1))
      val query               = (cursor: String, limit: Int) => Uniques.int.a2.i.d1.query.from(cursor).limit(limit)
      for {
        _ <- Uniques.int.i.insert(pairs).transact
        c1 <- query("", -2).get.map { case (List(`d`, `e`), cursor, true) => cursor }
        c2 <- query(c1, -2).get.map { case (List(`b`, `c`), cursor, true) => cursor }
        c3 <- query(c2, -2).get.map { case (List(`a`), cursor, false) => cursor }
        c2 <- query(c3, 2).get.map { case (List(`b`, `c`), cursor, true) => cursor }
        _ <- query(c2, 2).get.map { case (List(`d`, `e`), _, false) => () }
      } yield ()
    }

    "Backwards, desc desc" - unique { implicit conn =>
      val pairs               = (1 to 5).toList.map((_, scala.util.Random.nextInt(3) + 1))
      val List(a, b, c, d, e) = pairs.sortBy(p => (-p._2, -p._1))
      val query               = (cursor: String, limit: Int) => Uniques.int.d2.i.d1.query.from(cursor).limit(limit)
      for {
        _ <- Uniques.int.i.insert(pairs).transact
        c1 <- query("", -2).get.map { case (List(`d`, `e`), cursor, true) => cursor }
        c2 <- query(c1, -2).get.map { case (List(`b`, `c`), cursor, true) => cursor }
        c3 <- query(c2, -2).get.map { case (List(`a`), cursor, false) => cursor }
        c2 <- query(c3, 2).get.map { case (List(`b`, `c`), cursor, true) => cursor }
        _ <- query(c2, 2).get.map { case (List(`d`, `e`), _, false) => () }
      } yield ()
    }
  }
}