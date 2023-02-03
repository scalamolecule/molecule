package molecule.db.datomic.test.pagination.cursor

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Unique._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._
import scala.annotation.nowarn
import scala.util.Random

object CursorSomeUnique extends DatomicTestSuite {


  // (Allow pattern matching the result without warnings)
  @nowarn lazy val tests = Tests {

    // Testing Unique.int having attributes `i` and `s` that are not unique
    "standard-unique attr" - {

      "Forward, asc asc" - unique { implicit conn =>
        val pairs = (1 to 5).toList.map((Random.between(1, 3), _))
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
        val pairs = (1 to 5).toList.map((Random.between(1, 3), _))
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

      "Forward, asc desc" - unique { implicit conn =>
        val pairs = (1 to 5).toList.map((Random.between(1, 3), _))
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
        val pairs = (1 to 5).toList.map((Random.between(1, 3), _))
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


      "Backwards, asc asc" - unique { implicit conn =>
        val pairs = (1 to 5).toList.map((Random.between(1, 3), _))
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
        val pairs = (1 to 5).toList.map((Random.between(1, 3), _))
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

      "Backwards, asc desc" - unique { implicit conn =>
        val pairs = (1 to 5).toList.map((Random.between(1, 3), _))
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
        val pairs = (1 to 5).toList.map((Random.between(1, 3), _))
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


    "unique-standard attr" - {

      "Forward, asc asc" - unique { implicit conn =>
        val pairs = (1 to 5).toList.map((_, scala.util.Random.between(1, 3)))
        for {
          _ <- Unique.int.i.insert(pairs).transact
          List(a, b, c, d, e) = pairs.sortBy(p => (p._2, p._1))
          query = (cursor: String, limit: Int) => Unique.int.a2.i.a1.query.from(cursor).limit(limit)

          c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
          c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
          c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
        } yield ()
      }

      "Forward, desc asc" - unique { implicit conn =>
        val pairs = (1 to 5).toList.map((_, scala.util.Random.between(1, 3)))
        for {
          _ <- Unique.int.i.insert(pairs).transact
          List(a, b, c, d, e) = pairs.sortBy(p => (p._2, -p._1))
          query = (cursor: String, limit: Int) => Unique.int.d2.i.a1.query.from(cursor).limit(limit)

          c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
          c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
          c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
        } yield ()
      }

      "Forward, asc desc" - unique { implicit conn =>
        val pairs = (1 to 5).toList.map((_, scala.util.Random.between(1, 3)))
        for {
          _ <- Unique.int.i.insert(pairs).transact
          List(a, b, c, d, e) = pairs.sortBy(p => (-p._2, p._1))
          query = (cursor: String, limit: Int) => Unique.int.a2.i.d1.query.from(cursor).limit(limit)

          c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
          c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
          c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
        } yield ()
      }

      "Forward, desc desc" - unique { implicit conn =>
        val pairs = (1 to 5).toList.map((_, scala.util.Random.between(1, 3)))
        for {
          _ <- Unique.int.i.insert(pairs).transact
          List(a, b, c, d, e) = pairs.sortBy(p => (-p._2, -p._1))
          query = (cursor: String, limit: Int) => Unique.int.d2.i.d1.query.from(cursor).limit(limit)

          c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
          c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
          c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
        } yield ()
      }


      "Backwards, asc asc" - unique { implicit conn =>
        val pairs = (1 to 5).toList.map((_, scala.util.Random.between(1, 3)))
        for {
          _ <- Unique.int.i.insert(pairs).transact
          List(a, b, c, d, e) = pairs.sortBy(p => (p._2, p._1))
          query = (cursor: String, limit: Int) => Unique.int.a2.i.a1.query.from(cursor).limit(limit)

          c1 <- query("", -2).get.map { case (List(`d`, `e`), cursor, true) => cursor }
          c2 <- query(c1, -2).get.map { case (List(`b`, `c`), cursor, true) => cursor }
          c3 <- query(c2, -2).get.map { case (List(`a`), cursor, false) => cursor }
          c2 <- query(c3, 2).get.map { case (List(`b`, `c`), cursor, true) => cursor }
          _ <- query(c2, 2).get.map { case (List(`d`, `e`), _, false) => () }
        } yield ()
      }

      "Backwards, desc asc" - unique { implicit conn =>
        val pairs = (1 to 5).toList.map((_, scala.util.Random.between(1, 3)))
        for {
          _ <- Unique.int.i.insert(pairs).transact
          List(a, b, c, d, e) = pairs.sortBy(p => (p._2, -p._1))
          query = (cursor: String, limit: Int) => Unique.int.d2.i.a1.query.from(cursor).limit(limit)

          c1 <- query("", -2).get.map { case (List(`d`, `e`), cursor, true) => cursor }
          c2 <- query(c1, -2).get.map { case (List(`b`, `c`), cursor, true) => cursor }
          c3 <- query(c2, -2).get.map { case (List(`a`), cursor, false) => cursor }
          c2 <- query(c3, 2).get.map { case (List(`b`, `c`), cursor, true) => cursor }
          _ <- query(c2, 2).get.map { case (List(`d`, `e`), _, false) => () }
        } yield ()
      }

      "Backwards, asc desc" - unique { implicit conn =>
        val pairs = (1 to 5).toList.map((_, scala.util.Random.between(1, 3)))
        for {
          _ <- Unique.int.i.insert(pairs).transact
          List(a, b, c, d, e) = pairs.sortBy(p => (-p._2, p._1))
          query = (cursor: String, limit: Int) => Unique.int.a2.i.d1.query.from(cursor).limit(limit)

          c1 <- query("", -2).get.map { case (List(`d`, `e`), cursor, true) => cursor }
          c2 <- query(c1, -2).get.map { case (List(`b`, `c`), cursor, true) => cursor }
          c3 <- query(c2, -2).get.map { case (List(`a`), cursor, false) => cursor }
          c2 <- query(c3, 2).get.map { case (List(`b`, `c`), cursor, true) => cursor }
          _ <- query(c2, 2).get.map { case (List(`d`, `e`), _, false) => () }
        } yield ()
      }

      "Backwards, desc desc" - unique { implicit conn =>
        val pairs = (1 to 5).toList.map((_, scala.util.Random.between(1, 3)))
        for {
          _ <- Unique.int.i.insert(pairs).transact
          List(a, b, c, d, e) = pairs.sortBy(p => (-p._2, -p._1))
          query = (cursor: String, limit: Int) => Unique.int.d2.i.d1.query.from(cursor).limit(limit)
          c1 <- query("", -2).get.map { case (List(`d`, `e`), cursor, true) => cursor }
          c2 <- query(c1, -2).get.map { case (List(`b`, `c`), cursor, true) => cursor }
          c3 <- query(c2, -2).get.map { case (List(`a`), cursor, false) => cursor }
          c2 <- query(c3, 2).get.map { case (List(`b`, `c`), cursor, true) => cursor }
          _ <- query(c2, 2).get.map { case (List(`d`, `e`), _, false) => () }
        } yield ()
      }
    }


    "Multiple non-unique attrs" - {
      def getTriples: List[(String, Int, Int)] = (1 to 5).toList.map { int =>
        val s = ('a' + scala.util.Random.between(0, 2)).toChar.toString // "a" or "b"
        val i = scala.util.Random.between(1, 3) // 1 or 2
        (s, i, int)
      }

      "1-2-u3" - unique { implicit conn =>
        val triples = getTriples
        for {
          _ <- Unique.s.i.int.insert(triples).transact
          List(a, b, c, d, e) = triples.sortBy(p => (p._1, p._2, p._3))
          query = (cursor: String, limit: Int) => Unique.s.a1.i.a2.int.a3.query.from(cursor).limit(limit)

          c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
          c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
          c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
        } yield ()
      }

      "2-1-u3" - unique { implicit conn =>
        val triples = getTriples
        for {
          _ <- Unique.s.i.int.insert(triples).transact
          List(a, b, c, d, e) = triples.sortBy(p => (p._2, p._1, p._3))
          query = (cursor: String, limit: Int) => Unique.s.a2.i.a1.int.a3.query.from(cursor).limit(limit)

          c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
          c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
          c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
        } yield ()
      }

      "1-3-u2" - unique { implicit conn =>
        val triples = getTriples
        for {
          _ <- Unique.s.i.int.insert(triples).transact
          List(a, b, c, d, e) = triples.sortBy(p => (p._1, p._3, p._2))
          query = (cursor: String, limit: Int) => Unique.s.a1.i.a3.int.a2.query.from(cursor).limit(limit)

          c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
          c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
          c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
        } yield ()
      }

      "3-1-u2" - unique { implicit conn =>
        val triples = getTriples
        for {
          _ <- Unique.s.i.int.insert(triples).transact
          List(a, b, c, d, e) = triples.sortBy(p => (p._2, p._3, p._1))
          query = (cursor: String, limit: Int) => Unique.s.a3.i.a1.int.a2.query.from(cursor).limit(limit)

          c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
          c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
          c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
        } yield ()
      }


      "1-u3-2" - unique { implicit conn =>
        val triples = getTriples.map(t => (t._1, t._3, t._2))
        for {
          _ <- Unique.s.int.i.insert(triples).transact
          List(a, b, c, d, e) = triples.sortBy(p => (p._1, p._3, p._2))
          query = (cursor: String, limit: Int) => Unique.s.a1.int.a3.i.a2.query.from(cursor).limit(limit)

          c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
          c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
          c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
        } yield ()
      }

      "2-u3-1" - unique { implicit conn =>
        val triples = getTriples.map(t => (t._1, t._3, t._2))
        for {
          _ <- Unique.s.int.i.insert(triples).transact
          List(a, b, c, d, e) = triples.sortBy(p => (p._3, p._1, p._3))
          query = (cursor: String, limit: Int) => Unique.s.a2.int.a3.i.a1.query.from(cursor).limit(limit)

          c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
          c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
          c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
        } yield ()
      }

      "1-u2-3" - unique { implicit conn =>
        val triples = getTriples.map(t => (t._1, t._3, t._2))
        for {
          _ <- Unique.s.int.i.insert(triples).transact
          List(a, b, c, d, e) = triples.sortBy(p => (p._1, p._2, p._3))
          query = (cursor: String, limit: Int) => Unique.s.a1.int.a2.i.a3.query.from(cursor).limit(limit)

          c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
          c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
          c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
        } yield ()
      }

      "3-u2-1" - unique { implicit conn =>
        val triples = getTriples.map(t => (t._1, t._3, t._2))
        for {
          _ <- Unique.s.int.i.insert(triples).transact
          List(a, b, c, d, e) = triples.sortBy(p => (p._3, p._2, p._1))
          query = (cursor: String, limit: Int) => Unique.s.a3.int.a2.i.a1.query.from(cursor).limit(limit)

          c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
          c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
          c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
        } yield ()
      }


      "u3-1-2" - unique { implicit conn =>
        val triples = getTriples.map(t => (t._3, t._1, t._2))
        for {
          _ <- Unique.int.s.i.insert(triples).transact
          List(a, b, c, d, e) = triples.sortBy(p => (p._2, p._3, p._1))
          query = (cursor: String, limit: Int) => Unique.int.a3.s.a1.i.a2.query.from(cursor).limit(limit)

          c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
          c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
          c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
        } yield ()
      }

      "u3-2-1" - unique { implicit conn =>
        val triples = getTriples.map(t => (t._3, t._1, t._2))
        for {
          _ <- Unique.int.s.i.insert(triples).transact
          List(a, b, c, d, e) = triples.sortBy(p => (p._3, p._2, p._1))
          query = (cursor: String, limit: Int) => Unique.int.a3.s.a2.i.a1.query.from(cursor).limit(limit)

          c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
          c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
          c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
        } yield ()
      }

      "u2-1-3" - unique { implicit conn =>
        val triples = getTriples.map(t => (t._3, t._1, t._2))
        for {
          _ <- Unique.int.s.i.insert(triples).transact
          List(a, b, c, d, e) = triples.sortBy(p => (p._2, p._1, p._3))
          query = (cursor: String, limit: Int) => Unique.int.a2.s.a1.i.a3.query.from(cursor).limit(limit)

          c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
          c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
          c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
        } yield ()
      }

      "u2-3-1" - unique { implicit conn =>
        val triples = getTriples.map(t => (t._3, t._1, t._2))
        for {
          _ <- Unique.int.s.i.insert(triples).transact
          List(a, b, c, d, e) = triples.sortBy(p => (p._3, p._1, p._2))
          query = (cursor: String, limit: Int) => Unique.int.a2.s.a3.i.a1.query.from(cursor).limit(limit)

          c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
          c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
          c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
        } yield ()
      }
    }


    "Unique attr value type encoding" - {

      "String" - unique { implicit conn =>
        val pairs = List(
          (Random.between(1, 3), string1),
          (Random.between(1, 3), string2),
          (Random.between(1, 3), string3),
          (Random.between(1, 3), string4),
          (Random.between(1, 3), string5),
        )
        for {
          _ <- Unique.i.string.insert(pairs).transact
          List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
          query = (cursor: String, limit: Int) => Unique.i.a1.string.a2.query.from(cursor).limit(limit)

          c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
          c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
          c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
        } yield ()
      }

      "Int" - unique { implicit conn =>
        val pairs = List(
          (Random.between(1, 3), int1),
          (Random.between(1, 3), int2),
          (Random.between(1, 3), int3),
          (Random.between(1, 3), int4),
          (Random.between(1, 3), int5),
        )
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

      "Long" - unique { implicit conn =>
        val pairs = List(
          (Random.between(1, 3), long1),
          (Random.between(1, 3), long2),
          (Random.between(1, 3), long3),
          (Random.between(1, 3), long4),
          (Random.between(1, 3), long5),
        )
        for {
          _ <- Unique.i.long.insert(pairs).transact
          List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
          query = (cursor: String, limit: Int) => Unique.i.a1.long.a2.query.from(cursor).limit(limit)

          c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
          c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
          c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
        } yield ()
      }

      "Float" - unique { implicit conn =>
        val pairs = List(
          (Random.between(1, 3), float1),
          (Random.between(1, 3), float2),
          (Random.between(1, 3), float3),
          (Random.between(1, 3), float4),
          (Random.between(1, 3), float5),
        )
        for {
          _ <- Unique.i.float.insert(pairs).transact
          List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
          query = (cursor: String, limit: Int) => Unique.i.a1.float.a2.query.from(cursor).limit(limit)

          c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
          c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
          c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
        } yield ()
      }

      "Double" - unique { implicit conn =>
        val pairs = List(
          (Random.between(1, 3), double1),
          (Random.between(1, 3), double2),
          (Random.between(1, 3), double3),
          (Random.between(1, 3), double4),
          (Random.between(1, 3), double5),
        )
        for {
          _ <- Unique.i.double.insert(pairs).transact
          List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
          query = (cursor: String, limit: Int) => Unique.i.a1.double.a2.query.from(cursor).limit(limit)

          c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
          c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
          c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
        } yield ()
      }

      "Boolean" - unique { implicit conn =>
        // Can save only 2 unique boolean values
        val pairs = List(
          (Random.between(1, 3), boolean1),
          (Random.between(1, 3), boolean2),
        )
        for {
          _ <- Unique.i.boolean.insert(pairs).transact
          List(a, b) = pairs.sortBy(p => (p._1, p._2))
          query = (cursor: String, limit: Int) => Unique.i.a1.boolean.a2.query.from(cursor).limit(limit)

          c1 <- query("", 1).get.map { case (List(`a`), cursor, true) => cursor }
          c2 <- query(c1, 1).get.map { case (List(`b`), cursor, false) => cursor }
          _ <- query(c2, -1).get.map { case (List(`a`), _, false) => () }
        } yield ()
      }

      "BigInt" - unique { implicit conn =>
        val pairs  = List(
          (Random.between(1, 3), bigInt1),
          (Random.between(1, 3), bigInt2),
          (Random.between(1, 3), bigInt3),
          (Random.between(1, 3), bigInt4),
          (Random.between(1, 3), bigInt5),
        )
        val pairsx = List(
          (1, bigInt1),
          (1, bigInt3),
          (1, bigInt5),
          (2, bigInt2),
          (2, bigInt4),
        )
        for {
          _ <- Unique.i.bigInt.insert(pairs).transact
          List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
          query = (cursor: String, limit: Int) => Unique.i.a1.bigInt.a2.query.from(cursor).limit(limit)

          c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
          c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
          c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
        } yield ()
      }

      "BigDecimal" - unique { implicit conn =>
        val pairs = List(
          (Random.between(1, 3), bigDecimal1),
          (Random.between(1, 3), bigDecimal2),
          (Random.between(1, 3), bigDecimal3),
          (Random.between(1, 3), bigDecimal4),
          (Random.between(1, 3), bigDecimal5),
        )
        for {
          _ <- Unique.i.bigDecimal.insert(pairs).transact
          List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
          query = (cursor: String, limit: Int) => Unique.i.a1.bigDecimal.a2.query.from(cursor).limit(limit)

          c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
          c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
          c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
        } yield ()
      }

      "Date" - unique { implicit conn =>
        val pairs = List(
          (Random.between(1, 3), date1),
          (Random.between(1, 3), date2),
          (Random.between(1, 3), date3),
          (Random.between(1, 3), date4),
          (Random.between(1, 3), date5),
        )
        for {
          _ <- Unique.i.date.insert(pairs).transact
          List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
          query = (cursor: String, limit: Int) => Unique.i.a1.date.a2.query.from(cursor).limit(limit)

          c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
          c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
          c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
        } yield ()
      }

      "Uuid" - unique { implicit conn =>
        val pairs = List(
          (Random.between(1, 3), uuid1),
          (Random.between(1, 3), uuid2),
          (Random.between(1, 3), uuid3),
          (Random.between(1, 3), uuid4),
          (Random.between(1, 3), uuid5),
        )
        for {
          _ <- Unique.i.uuid.insert(pairs).transact
          List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
          query = (cursor: String, limit: Int) => Unique.i.a1.uuid.a2.query.from(cursor).limit(limit)

          c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
          c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
          c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
        } yield ()
      }

      "Uri" - unique { implicit conn =>
        val pairs = List(
          (Random.between(1, 3), uri1),
          (Random.between(1, 3), uri2),
          (Random.between(1, 3), uri3),
          (Random.between(1, 3), uri4),
          (Random.between(1, 3), uri5),
        )
        for {
          _ <- Unique.i.uri.insert(pairs).transact
          List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
          query = (cursor: String, limit: Int) => Unique.i.a1.uri.a2.query.from(cursor).limit(limit)

          c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
          c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
          c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
        } yield ()
      }

      "Byte" - unique { implicit conn =>
        val pairs = List(
          (Random.between(1, 3), byte1),
          (Random.between(1, 3), byte2),
          (Random.between(1, 3), byte3),
          (Random.between(1, 3), byte4),
          (Random.between(1, 3), byte5),
        )
        for {
          _ <- Unique.i.byte.insert(pairs).transact
          List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
          query = (cursor: String, limit: Int) => Unique.i.a1.byte.a2.query.from(cursor).limit(limit)

          c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
          c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
          c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
        } yield ()
      }

      "Short" - unique { implicit conn =>
        val pairs = List(
          (Random.between(1, 3), short1),
          (Random.between(1, 3), short2),
          (Random.between(1, 3), short3),
          (Random.between(1, 3), short4),
          (Random.between(1, 3), short5),
        )
        for {
          _ <- Unique.i.short.insert(pairs).transact
          List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
          query = (cursor: String, limit: Int) => Unique.i.a1.short.a2.query.from(cursor).limit(limit)

          c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
          c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
          c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
        } yield ()
      }

      "Char" - unique { implicit conn =>
        val pairs = List(
          (Random.between(1, 3), char1),
          (Random.between(1, 3), char2),
          (Random.between(1, 3), char3),
          (Random.between(1, 3), char4),
          (Random.between(1, 3), char5),
        )
        for {
          _ <- Unique.i.char.insert(pairs).transact
          List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
          query = (cursor: String, limit: Int) => Unique.i.a1.char.a2.query.from(cursor).limit(limit)

          c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
          c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
          c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
        } yield ()
      }
    }


    "Non-unique filter attr value type" - {
      import molecule.coreTests.dataModels.core.dsl.Types._

      "String" - types { implicit conn =>
        val pairs = List(
          (1, string1),
          (2, string1),
          (3, string1),
          (4, string2),
          (5, string2),
        )
        for {
          _ <- Ns.u.string.insert(pairs).transact
          List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
          query = (cursor: String, limit: Int) => Ns.u.a1.string.a2.query.from(cursor).limit(limit)

          c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
          c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
          c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
        } yield ()
      }

      "Int" - types { implicit conn =>
        val pairs = List(
          (1, int1),
          (2, int1),
          (3, int1),
          (4, int2),
          (5, int2),
        )
        for {
          _ <- Ns.u.int.insert(pairs).transact
          List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
          query = (cursor: String, limit: Int) => Ns.u.a1.int.a2.query.from(cursor).limit(limit)

          c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
          c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
          c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
        } yield ()
      }

      "Long" - types { implicit conn =>
        val pairs = List(
          (1, long1),
          (2, long1),
          (3, long1),
          (4, long2),
          (5, long2),
        )
        for {
          _ <- Ns.u.long.insert(pairs).transact
          List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
          query = (cursor: String, limit: Int) => Ns.u.a1.long.a2.query.from(cursor).limit(limit)

          c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
          c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
          c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
        } yield ()
      }

      "Float" - types { implicit conn =>
        val pairs = List(
          (1, float1),
          (2, float1),
          (3, float1),
          (4, float2),
          (5, float2),
        )
        for {
          _ <- Ns.u.float.insert(pairs).transact
          List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
          query = (cursor: String, limit: Int) => Ns.u.a1.float.a2.query.from(cursor).limit(limit)

          c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
          c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
          c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
        } yield ()
      }

      "Double" - types { implicit conn =>
        val pairs = List(
          (1, double1),
          (2, double1),
          (3, double1),
          (4, double2),
          (5, double2),
        )
        for {
          _ <- Ns.u.double.insert(pairs).transact
          List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
          query = (cursor: String, limit: Int) => Ns.u.a1.double.a2.query.from(cursor).limit(limit)

          c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
          c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
          c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
        } yield ()
      }

      "Boolean" - types { implicit conn =>
        // Can save only 2 ns boolean values
        val pairs = List(
          (1, boolean1),
          (2, boolean2),
        )
        for {
          _ <- Ns.u.boolean.insert(pairs).transact
          List(a, b) = pairs.sortBy(p => (p._1, p._2))
          query = (cursor: String, limit: Int) => Ns.u.a1.boolean.a2.query.from(cursor).limit(limit)

          c1 <- query("", 1).get.map { case (List(`a`), cursor, true) => cursor }
          c2 <- query(c1, 1).get.map { case (List(`b`), cursor, false) => cursor }
          _ <- query(c2, -1).get.map { case (List(`a`), _, false) => () }
        } yield ()
      }

      "BigInt" - types { implicit conn =>
        val pairs  = List(
          (1, bigInt1),
          (2, bigInt1),
          (3, bigInt1),
          (4, bigInt2),
          (5, bigInt2),
        )
        for {
          _ <- Ns.u.bigInt.insert(pairs).transact
          List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
          query = (cursor: String, limit: Int) => Ns.u.a1.bigInt.a2.query.from(cursor).limit(limit)

          c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
          c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
          c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
        } yield ()
      }

      "BigDecimal" - types { implicit conn =>
        val pairs = List(
          (1, bigDecimal1),
          (2, bigDecimal1),
          (3, bigDecimal1),
          (4, bigDecimal2),
          (5, bigDecimal2),
        )
        for {
          _ <- Ns.u.bigDecimal.insert(pairs).transact
          List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
          query = (cursor: String, limit: Int) => Ns.u.a1.bigDecimal.a2.query.from(cursor).limit(limit)

          c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
          c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
          c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
        } yield ()
      }

      "Date" - types { implicit conn =>
        val pairs = List(
          (1, date1),
          (2, date1),
          (3, date1),
          (4, date2),
          (5, date2),
        )
        for {
          _ <- Ns.u.date.insert(pairs).transact
          List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
          query = (cursor: String, limit: Int) => Ns.u.a1.date.a2.query.from(cursor).limit(limit)

          c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
          c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
          c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
        } yield ()
      }

      "Uuid" - types { implicit conn =>
        val pairs = List(
          (1, uuid1),
          (2, uuid1),
          (3, uuid1),
          (4, uuid2),
          (5, uuid2),
        )
        for {
          _ <- Ns.u.uuid.insert(pairs).transact
          List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
          query = (cursor: String, limit: Int) => Ns.u.a1.uuid.a2.query.from(cursor).limit(limit)

          c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
          c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
          c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
        } yield ()
      }

      "Uri" - types { implicit conn =>
        val pairs = List(
          (1, uri1),
          (2, uri1),
          (3, uri1),
          (4, uri2),
          (5, uri2),
        )
        for {
          _ <- Ns.u.uri.insert(pairs).transact
          List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
          query = (cursor: String, limit: Int) => Ns.u.a1.uri.a2.query.from(cursor).limit(limit)

          c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
          c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
          c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
        } yield ()
      }

      "Byte" - types { implicit conn =>
        val pairs = List(
          (1, byte1),
          (2, byte1),
          (3, byte1),
          (4, byte2),
          (5, byte2),
        )
        for {
          _ <- Ns.u.byte.insert(pairs).transact
          List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
          query = (cursor: String, limit: Int) => Ns.u.a1.byte.a2.query.from(cursor).limit(limit)

          c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
          c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
          c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
        } yield ()
      }

      "Short" - types { implicit conn =>
        val pairs = List(
          (1, short1),
          (2, short1),
          (3, short1),
          (4, short2),
          (5, short2),
        )
        for {
          _ <- Ns.u.short.insert(pairs).transact
          List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
          query = (cursor: String, limit: Int) => Ns.u.a1.short.a2.query.from(cursor).limit(limit)

          c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
          c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
          c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
        } yield ()
      }

      "Char" - types { implicit conn =>
        val pairs = List(
          (1, char1),
          (2, char1),
          (3, char1),
          (4, char2),
          (5, char2),
        )
        for {
          _ <- Ns.u.char.insert(pairs).transact
          List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
          query = (cursor: String, limit: Int) => Ns.u.a1.char.a2.query.from(cursor).limit(limit)

          c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
          c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
          c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
        } yield ()
      }
    }
  }
}