package molecule.coreTests.test.pagination.cursor.noUnique

import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.api.ApiAsyncImplicits
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._
import scala.annotation.{nowarn, tailrec}
import scala.util.Random

trait AttrOrderMandatory extends CoreTestSuite with ApiAsyncImplicits { self: SpiAsync  =>

  @tailrec
  final def getTriples(acc: List[(String, Int, Int)]): List[(String, Int, Int)] = {
    if (acc.length != 5) {
      val pair = (
        ('a' + scala.util.Random.nextInt(3)).toChar.toString, // "a" or "b"
        Random.nextInt(3) + 1,
        Random.nextInt(6) + 1,
      )
      // No duplicate rows
      if (!acc.contains(pair)) getTriples(acc :+ pair) else getTriples(acc)
    } else {
      acc
    }
  }

  @nowarn lazy val tests = Tests {

    "Unique first" - {

      "u3-1-2" - types { implicit conn =>
        val triples             = getTriples(Nil).map(t => (t._3, t._1, t._2))
        val List(a, b, c, d, e) = triples.sortBy(p => (p._2, p._3, p._1))
        val query               = (c: String, l: Int) => Ns.int.a3.s.a1.i.a2.query.from(c).limit(l)
        for {
          _ <- Ns.int.s.i.insert(triples).transact
          c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
          c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
          c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
        } yield ()
      }

      "u3-2-1" - types { implicit conn =>
        val triples             = getTriples(Nil).map(t => (t._3, t._1, t._2))
        val List(a, b, c, d, e) = triples.sortBy(p => (p._3, p._2, p._1))
        val query               = (c: String, l: Int) => Ns.int.a3.s.a2.i.a1.query.from(c).limit(l)
        for {
          _ <- Ns.int.s.i.insert(triples).transact
          c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
          c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
          c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
        } yield ()
      }

      "u2-1-3" - types { implicit conn =>
        val triples             = getTriples(Nil).map(t => (t._3, t._1, t._2))
        val List(a, b, c, d, e) = triples.sortBy(p => (p._2, p._1, p._3))
        val query               = (c: String, l: Int) => Ns.int.a2.s.a1.i.a3.query.from(c).limit(l)
        for {
          _ <- Ns.int.s.i.insert(triples).transact
          c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
          c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
          c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
        } yield ()
      }

      "u2-3-1" - types { implicit conn =>
        val triples             = getTriples(Nil).map(t => (t._3, t._1, t._2))
        val List(a, b, c, d, e) = triples.sortBy(p => (p._3, p._1, p._2))
        val query               = (c: String, l: Int) => Ns.int.a2.s.a3.i.a1.query.from(c).limit(l)
        for {
          _ <- Ns.int.s.i.insert(triples).transact
          c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
          c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
          c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
        } yield ()
      }
    }


    "Unique middle" - {

      "1-u3-2" - types { implicit conn =>
        val triples             = getTriples(Nil).map(t => (t._1, t._3, t._2))
        val List(a, b, c, d, e) = triples.sortBy(p => (p._1, p._3, p._2))
        val query               = (c: String, l: Int) => Ns.s.a1.int.a3.i.a2.query.from(c).limit(l)
        for {
          _ <- Ns.s.int.i.insert(triples).transact
          c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
          c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
          c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
        } yield ()
      }

      "2-u3-1" - types { implicit conn =>
        val triples = getTriples(Nil).map(t => (t._1, t._3, t._2))
        val List(a, b, c, d, e) = triples.sortBy(p => (p._3, p._1, p._2))
        val query = (c: String, l: Int) => Ns.s.a2.int.a3.i.a1.query.from(c).limit(l)
        for {
          _ <- Ns.s.int.i.insert(triples).transact
          c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
          c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
          c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
        } yield ()
      }

      "1-u2-3" - types { implicit conn =>
        val triples             = getTriples(Nil).map(t => (t._1, t._3, t._2))
        val List(a, b, c, d, e) = triples.sortBy(p => (p._1, p._2, p._3))
        val query               = (c: String, l: Int) => Ns.s.a1.int.a2.i.a3.query.from(c).limit(l)
        for {
          _ <- Ns.s.int.i.insert(triples).transact
          c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
          c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
          c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
        } yield ()
      }

      "3-u2-1" - types { implicit conn =>
        val triples             = getTriples(Nil).map(t => (t._1, t._3, t._2))
        val List(a, b, c, d, e) = triples.sortBy(p => (p._3, p._2, p._1))
        val query               = (c: String, l: Int) => Ns.s.a3.int.a2.i.a1.query.from(c).limit(l)
        for {
          _ <- Ns.s.int.i.insert(triples).transact
          c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
          c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
          c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
        } yield ()
      }
    }


    "Unique last" - {

      "1-2-u3" - types { implicit conn =>
        val triples             = getTriples(Nil)
        val List(a, b, c, d, e) = triples.sortBy(p => (p._1, p._2, p._3))
        val query               = (c: String, l: Int) => Ns.s.a1.i.a2.int.a3.query.from(c).limit(l)
        for {
          _ <- Ns.s.i.int.insert(triples).transact
          c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
          c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
          c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
        } yield ()
      }

      "2-1-u3" - types { implicit conn =>
        val triples             = getTriples(Nil)
        val List(a, b, c, d, e) = triples.sortBy(p => (p._2, p._1, p._3))
        val query               = (c: String, l: Int) => Ns.s.a2.i.a1.int.a3.query.from(c).limit(l)
        for {
          _ <- Ns.s.i.int.insert(triples).transact
          c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
          c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
          c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
        } yield ()
      }

      "1-3-u2" - types { implicit conn =>
        val triples             = getTriples(Nil)
        val List(a, b, c, d, e) = triples.sortBy(p => (p._1, p._3, p._2))
        val query               = (c: String, l: Int) => Ns.s.a1.i.a3.int.a2.query.from(c).limit(l)
        for {
          _ <- Ns.s.i.int.insert(triples).transact
          c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
          c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
          c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
          _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
        } yield ()
      }

      "3-1-u2" - types { implicit conn =>
        val triples             = getTriples(Nil)
        val List(a, b, c, d, e) = triples.sortBy(p => (p._2, p._3, p._1))
        val query               = (c: String, l: Int) => Ns.s.a3.i.a1.int.a2.query.from(c).limit(l)
        for {
          _ <- Ns.s.i.int.insert(triples).transact
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