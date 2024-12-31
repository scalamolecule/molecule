package molecule.coreTests.spi.pagination.cursor.noUnique

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.domains.dsl.Types._
import molecule.coreTests.setup._
import scala.annotation.tailrec
import scala.util.Random

case class AttrOrderOptional(
  suite: MUnitSuite,
  api: Api_async with Spi_async with DbProviders
) extends TestUtils {

  @tailrec
  final def getTriples(acc: List[(String, Int, Option[Int])]): List[(String, Int, Option[Int])] = {
    if (acc.length == 5) {
      acc
    } else {
      val pair = (
        ('a' + scala.util.Random.nextInt(3)).toChar.toString, // "a" or "b"
        Random.nextInt(3) + 1, // 1 or 2
        if (Random.nextInt(3) + 1 == 1) Some(Random.nextInt(6) + 1) else None
      )
      // No duplicate rows
      if (!acc.contains(pair)) getTriples(acc :+ pair) else getTriples(acc)
    }
  }

  import api._
  import suite._

  "Unique first: u3-1-2" - types { implicit conn =>
    val triples             = getTriples(Nil).map(t => (t._3, t._1, t._2))
    val List(a, b, c, d, e) = triples.sortBy(p => (p._2, p._3, p._1))
    val query               = (c: String, l: Int) => Entity.int_?.a3.s.a1.i.a2.query.from(c).limit(l)
    for {
      _ <- Entity.int_?.s.i.insert(triples).transact
      c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
      c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
      c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
    } yield ()
  }

  "Unique first: u3-2-1" - types { implicit conn =>
    val triples             = getTriples(Nil).map(t => (t._3, t._1, t._2))
    val List(a, b, c, d, e) = triples.sortBy(p => (p._3, p._2, p._1))
    val query               = (c: String, l: Int) => Entity.int_?.a3.s.a2.i.a1.query.from(c).limit(l)
    for {
      _ <- Entity.int_?.s.i.insert(triples).transact
      c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
      c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
      c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
    } yield ()
  }

  "Unique first: u2-1-3" - types { implicit conn =>
    val triples             = getTriples(Nil).map(t => (t._3, t._1, t._2))
    val List(a, b, c, d, e) = triples.sortBy(p => (p._2, p._1, p._3))
    val query               = (c: String, l: Int) => Entity.int_?.a2.s.a1.i.a3.query.from(c).limit(l)
    for {
      _ <- Entity.int_?.s.i.insert(triples).transact
      c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
      c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
      c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
    } yield ()
  }

  "Unique first: u2-3-1" - types { implicit conn =>
    val triples             = getTriples(Nil).map(t => (t._3, t._1, t._2))
    val List(a, b, c, d, e) = triples.sortBy(p => (p._3, p._1, p._2))
    val query               = (c: String, l: Int) => Entity.int_?.a2.s.a3.i.a1.query.from(c).limit(l)
    for {
      _ <- Entity.int_?.s.i.insert(triples).transact
      c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
      c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
      c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
    } yield ()
  }


  "Unique middle: 1-u3-2" - types { implicit conn =>
    val triples             = getTriples(Nil).map(t => (t._1, t._3, t._2))
    val List(a, b, c, d, e) = triples.sortBy(p => (p._1, p._3, p._2))
    val query               = (c: String, l: Int) => Entity.s.a1.int_?.a3.i.a2.query.from(c).limit(l)
    for {
      _ <- Entity.s.int_?.i.insert(triples).transact
      c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
      c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
      c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
    } yield ()
  }

  "Unique middle: 2-u3-1" - types { implicit conn =>
    val triples             = getTriples(Nil).map(t => (t._1, t._3, t._2))
    val List(a, b, c, d, e) = triples.sortBy(p => (p._3, p._1, p._2))
    val query               = (c: String, l: Int) => Entity.s.a2.int_?.a3.i.a1.query.from(c).limit(l)
    for {
      _ <- Entity.s.int_?.i.insert(triples).transact
      c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
      c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
      c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
    } yield ()
  }

  "Unique middle: 1-u2-3" - types { implicit conn =>
    val triples             = getTriples(Nil).map(t => (t._1, t._3, t._2))
    val List(a, b, c, d, e) = triples.sortBy(p => (p._1, p._2, p._3))
    val query               = (c: String, l: Int) => Entity.s.a1.int_?.a2.i.a3.query.from(c).limit(l)
    for {
      _ <- Entity.s.int_?.i.insert(triples).transact
      c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
      c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
      c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
    } yield ()
  }

  "Unique middle: 3-u2-1" - types { implicit conn =>
    val triples             = getTriples(Nil).map(t => (t._1, t._3, t._2))
    val List(a, b, c, d, e) = triples.sortBy(p => (p._3, p._2, p._1))
    val query               = (c: String, l: Int) => Entity.s.a3.int_?.a2.i.a1.query.from(c).limit(l)
    for {
      _ <- Entity.s.int_?.i.insert(triples).transact
      c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
      c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
      c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
    } yield ()
  }


  "Unique last: 1-2-u3" - types { implicit conn =>
    val triples             = getTriples(Nil)
    val List(a, b, c, d, e) = triples.sortBy(p => (p._1, p._2, p._3))
    val query               = (c: String, l: Int) => Entity.s.a1.i.a2.int_?.a3.query.from(c).limit(l)
    for {
      _ <- Entity.s.i.int_?.insert(triples).transact
      c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
      c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
      c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
    } yield ()
  }

  "Unique last: 2-1-u3" - types { implicit conn =>
    val triples             = getTriples(Nil)
    val List(a, b, c, d, e) = triples.sortBy(p => (p._2, p._1, p._3))
    val query               = (c: String, l: Int) => Entity.s.a2.i.a1.int_?.a3.query.from(c).limit(l)
    for {
      _ <- Entity.s.i.int_?.insert(triples).transact
      c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
      c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
      c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
    } yield ()
  }

  "Unique last: 1-3-u2" - types { implicit conn =>
    val triples             = getTriples(Nil)
    val List(a, b, c, d, e) = triples.sortBy(p => (p._1, p._3, p._2))
    val query               = (c: String, l: Int) => Entity.s.a1.i.a3.int_?.a2.query.from(c).limit(l)
    for {
      _ <- Entity.s.i.int_?.insert(triples).transact
      c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
      c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
      c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
    } yield ()
  }

  "Unique last: 3-1-u2" - types { implicit conn =>
    val triples             = getTriples(Nil)
    val List(a, b, c, d, e) = triples.sortBy(p => (p._2, p._3, p._1))
    val query               = (c: String, l: Int) => Entity.s.a3.i.a1.int_?.a2.query.from(c).limit(l)
    for {
      _ <- Entity.s.i.int_?.insert(triples).transact
      c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
      c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
      c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
    } yield ()
  }
}