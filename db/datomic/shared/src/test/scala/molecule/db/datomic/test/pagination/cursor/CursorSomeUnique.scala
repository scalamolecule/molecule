package molecule.db.datomic.test.pagination.cursor

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Unique._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._
import scala.annotation.nowarn

object CursorSomeUnique extends DatomicTestSuite {

  // (Allow pattern matching the result without warnings)
  @nowarn lazy val tests = Tests {

    "Unique sub sort" - {

      // i non-unique and int unique

      "Forward, asc" - unique { implicit conn =>
        for {
          // i non-unique and int unique
          _ <- Unique.i.int.insert(
            (2, 1),
            (2, 2),
            (1, 3),
            (1, 4),
            (1, 5)
          ).transact

          // Sorted by non-unique i attribute first, then unique int
          c1 <- Unique.i.a1.int.a2.query.from("").limit(2).get.map { case (List((1, 3), (1, 4)), c, true) => c }
          c2 <- Unique.i.a1.int.a2.query.from(c1).limit(2).get.map { case (List((1, 5), (2, 1)), c, true) => c }
          c3 <- Unique.i.a1.int.a2.query.from(c2).limit(2).get.map { case (List((2, 2)), c, false) => c }

          c2 <- Unique.i.a1.int.a2.query.from(c3).limit(-2).get.map { case (List((1, 5), (2, 1)), c, true) => c }
          _ <- Unique.i.a1.int.a2.query.from(c2).limit(-2).get.map { case (List((1, 3), (1, 4)), _, false) => () }
        } yield ()
      }

      "Forward, desc asc" - unique { implicit conn =>
        for {
          // i non-unique and int unique
          _ <- Unique.i.int.insert(
            (2, 1),
            (2, 2),
            (1, 3),
            (1, 4),
            (1, 5)
          ).transact

          // Sorted by non-unique i attribute first, then unique int
          c1 <- Unique.i.d1.int.a2.query.from("").limit(2).get.map { case (List((2, 1), (2, 2)), c, true) => c }
          c2 <- Unique.i.d1.int.a2.query.from(c1).limit(2).get.map { case (List((1, 3), (1, 4)), c, true) => c }
          c3 <- Unique.i.d1.int.a2.query.from(c2).limit(2).get.map { case (List((1, 5)), c, false) => c }

          c2 <- Unique.i.d1.int.a2.query.from(c3).limit(-2).get.map { case (List((1, 3), (1, 4)), c, true) => c }
          _ <- Unique.i.d1.int.a2.query.from(c2).limit(-2).get.map { case (List((2, 1), (2, 2)), _, false) => () }
        } yield ()
      }

      "Forward, asc asc" - unique { implicit conn =>
        val pairs = (1 to 5).toList.map((scala.util.Random.between(1, 3), _))
        for {
          _ <- Unique.i.int.insert(pairs).transact

          List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
          query = (cursor: String, limit: Int) => Unique.i.a1.int.a2.query.from(cursor).limit(limit)

          c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
          c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
          c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
        } yield ()
      }

      "Forward, desc asc" - unique { implicit conn =>
        val pairs = (1 to 5).toList.map((scala.util.Random.between(1, 3), _))
        for {
          _ <- Unique.i.int.insert(pairs).transact

          List(a, b, c, d, e) = pairs.sortBy(p => (-p._1, p._2))
          query = (cursor: String, limit: Int) => Unique.i.d1.int.a2.query.from(cursor).limit(limit)

          c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
          c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
          c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
        } yield ()
      }

      "Backwards, asc asc" - unique { implicit conn =>
        val pairs = (1 to 5).toList.map((scala.util.Random.between(1, 3), _))
        for {
          _ <- Unique.i.int.insert(pairs).transact

          List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
          query = (cursor: String, limit: Int) => Unique.i.a1.int.a2.query.from(cursor).limit(limit)

          c1 <- query("", -2).get.map { case (List(`d`, `e`), cursor, true) => cursor }
          c2 <- query(c1, -2).get.map { case (List(`b`, `c`), cursor, true) => cursor }
          c3 <- query(c2, -2).get.map { case (List(`a`), cursor, false) => cursor }
          c2 <- query(c3, 2).get.map { case (List(`b`, `c`), cursor, true) => cursor }
          _ <- query(c2, 2).get.map { case (List(`d`, `e`), _, false) => () }
        } yield ()
      }

      "Backwards, desc asc" - unique { implicit conn =>
        val pairs = (1 to 5).toList.map((scala.util.Random.between(1, 3), _))
        for {
          _ <- Unique.i.int.insert(pairs).transact

          List(a, b, c, d, e) = pairs.sortBy(p => (-p._1, p._2))
          query = (cursor: String, limit: Int) => Unique.i.d1.int.a2.query.from(cursor).limit(limit)

          c1 <- query("", -2).get.map { case (List(`d`, `e`), cursor, true) => cursor }
          c2 <- query(c1, -2).get.map { case (List(`b`, `c`), cursor, true) => cursor }
          c3 <- query(c2, -2).get.map { case (List(`a`), cursor, false) => cursor }
          c2 <- query(c3, 2).get.map { case (List(`b`, `c`), cursor, true) => cursor }
          _ <- query(c2, 2).get.map { case (List(`d`, `e`), _, false) => () }
        } yield ()
      }


      "Forward, asc desc" - unique { implicit conn =>
        val pairs = (1 to 5).toList.map((scala.util.Random.between(1, 3), _))
        //        val pairs = List(
        //          (1, 3),
        //          (1, 2),
        //          (1, 1),
        //          (2, 5),
        //          (2, 4),
        //        )
        for {
          _ <- Unique.i.int.insert(pairs).transact

          List(a, b, c, d, e) = pairs.sortBy(p => (p._1, -p._2))
          query = (cursor: String, limit: Int) => Unique.i.a1.int.d2.query.from(cursor).limit(limit)

          c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
          c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
          c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
        } yield ()
      }

      "Forward, desc desc" - unique { implicit conn =>
        val pairs = (1 to 5).toList.map((scala.util.Random.between(1, 3), _))
        for {
          _ <- Unique.i.int.insert(pairs).transact

          List(a, b, c, d, e) = pairs.sortBy(p => (-p._1, -p._2))
          query = (cursor: String, limit: Int) => Unique.i.d1.int.d2.query.from(cursor).limit(limit)

          c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
          c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
          c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
        } yield ()
      }

      "Backwards, asc desc" - unique { implicit conn =>
        val pairs = (1 to 5).toList.map((scala.util.Random.between(1, 3), _))
        for {
          _ <- Unique.i.int.insert(pairs).transact

          List(a, b, c, d, e) = pairs.sortBy(p => (p._1, -p._2))
          query = (cursor: String, limit: Int) => Unique.i.a1.int.d2.query.from(cursor).limit(limit)

          c1 <- query("", -2).get.map { case (List(`d`, `e`), cursor, true) => cursor }
          c2 <- query(c1, -2).get.map { case (List(`b`, `c`), cursor, true) => cursor }
          c3 <- query(c2, -2).get.map { case (List(`a`), cursor, false) => cursor }
          c2 <- query(c3, 2).get.map { case (List(`b`, `c`), cursor, true) => cursor }
          _ <- query(c2, 2).get.map { case (List(`d`, `e`), _, false) => () }
        } yield ()
      }

      "Backwards, desc desc" - unique { implicit conn =>
        val pairs = (1 to 5).toList.map((scala.util.Random.between(1, 3), _))
        for {
          _ <- Unique.i.int.insert(pairs).transact

          List(a, b, c, d, e) = pairs.sortBy(p => (-p._1, -p._2))
          query = (cursor: String, limit: Int) => Unique.i.d1.int.d2.query.from(cursor).limit(limit)

          c1 <- query("", -2).get.map { case (List(`d`, `e`), cursor, true) => cursor }
          c2 <- query(c1, -2).get.map { case (List(`b`, `c`), cursor, true) => cursor }
          c3 <- query(c2, -2).get.map { case (List(`a`), cursor, false) => cursor }
          c2 <- query(c3, 2).get.map { case (List(`b`, `c`), cursor, true) => cursor }
          _ <- query(c2, 2).get.map { case (List(`d`, `e`), _, false) => () }
        } yield ()
      }
    }
  }
}