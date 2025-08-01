package molecule.db.compliance.test.pagination.subUnique

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Uniques.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import scala.annotation.nowarn


@nowarn
case class AttrOrder(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  def getTriples: List[(String, Int, Int)] = (1 to 5).toList.map { int =>
    val s = ('a' + scala.util.Random.nextInt(3)).toChar.toString // "a" or "b"
    val i = scala.util.Random.nextInt(3) + 1 // 1 or 2
    (s, i, int)
  }

  import api.*
  import suite.*

  "Unique first: u3-1-2" - unique {
    val triples             = getTriples.map(t => (t._3, t._1, t._2))
    val List(a, b, c, d, e) = triples.sortBy(p => (p._2, p._3, p._1))
    val query               = (c: String, l: Int) => Uniques.int.a3.s.a1.i.a2.query.from(c).limit(l)
    for {
      _ <- Uniques.int.s.i.insert(triples).transact
      c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
      c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
      c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
    } yield ()
  }

  "Unique first: u3-2-1" - unique {
    val triples             = getTriples.map(t => (t._3, t._1, t._2))
    val List(a, b, c, d, e) = triples.sortBy(p => (p._3, p._2, p._1))
    val query               = (c: String, l: Int) => Uniques.int.a3.s.a2.i.a1.query.from(c).limit(l)
    for {
      _ <- Uniques.int.s.i.insert(triples).transact
      c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
      c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
      c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
    } yield ()
  }

  "Unique first: u2-1-3" - unique {
    val triples             = getTriples.map(t => (t._3, t._1, t._2))
    val List(a, b, c, d, e) = triples.sortBy(p => (p._2, p._1, p._3))
    val query               = (c: String, l: Int) => Uniques.int.a2.s.a1.i.a3.query.from(c).limit(l)
    for {
      _ <- Uniques.int.s.i.insert(triples).transact
      c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
      c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
      c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
    } yield ()
  }

  "Unique first: u2-3-1" - unique {
    val triples             = getTriples.map(t => (t._3, t._1, t._2))
    val List(a, b, c, d, e) = triples.sortBy(p => (p._3, p._1, p._2))
    val query               = (c: String, l: Int) => Uniques.int.a2.s.a3.i.a1.query.from(c).limit(l)
    for {
      _ <- Uniques.int.s.i.insert(triples).transact
      c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
      c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
      c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
    } yield ()
  }


  "Unique middle: 1-u3-2" - unique {
    val triples             = getTriples.map(t => (t._1, t._3, t._2))
    val List(a, b, c, d, e) = triples.sortBy(p => (p._1, p._3, p._2))
    val query               = (c: String, l: Int) => Uniques.s.a1.int.a3.i.a2.query.from(c).limit(l)
    for {
      _ <- Uniques.s.int.i.insert(triples).transact
      c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
      c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
      c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
    } yield ()
  }

  "Unique middle: 2-u3-1" - unique {
    val triples             = getTriples.map(t => (t._1, t._3, t._2))
    val List(a, b, c, d, e) = triples.sortBy(p => (p._3, p._1, p._2))
    val query               = (c: String, l: Int) => Uniques.s.a2.int.a3.i.a1.query.from(c).limit(l)
    for {
      _ <- Uniques.s.int.i.insert(triples).transact
      c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
      c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
      c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
    } yield ()
  }

  "Unique middle: 1-u2-3" - unique {
    val triples             = getTriples.map(t => (t._1, t._3, t._2))
    val List(a, b, c, d, e) = triples.sortBy(p => (p._1, p._2, p._3))
    val query               = (c: String, l: Int) => Uniques.s.a1.int.a2.i.a3.query.from(c).limit(l)
    for {
      _ <- Uniques.s.int.i.insert(triples).transact
      c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
      c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
      c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
    } yield ()
  }

  "Unique middle: 3-u2-1" - unique {
    val triples             = getTriples.map(t => (t._1, t._3, t._2))
    val List(a, b, c, d, e) = triples.sortBy(p => (p._3, p._2, p._1))
    val query               = (c: String, l: Int) => Uniques.s.a3.int.a2.i.a1.query.from(c).limit(l)
    for {
      _ <- Uniques.s.int.i.insert(triples).transact
      c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
      c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
      c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
    } yield ()
  }


  "Unique last: 1-2-u3" - unique {
    val triples             = getTriples
    val List(a, b, c, d, e) = triples.sortBy(p => (p._1, p._2, p._3))
    val query               = (c: String, l: Int) => Uniques.s.a1.i.a2.int.a3.query.from(c).limit(l)
    for {
      _ <- Uniques.s.i.int.insert(triples).transact
      c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
      c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
      c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
    } yield ()
  }

  "Unique last: 2-1-u3" - unique {
    val triples             = getTriples
    val List(a, b, c, d, e) = triples.sortBy(p => (p._2, p._1, p._3))
    val query               = (c: String, l: Int) => Uniques.s.a2.i.a1.int.a3.query.from(c).limit(l)
    for {
      _ <- Uniques.s.i.int.insert(triples).transact
      c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
      c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
      c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
    } yield ()
  }

  "Unique last: 1-3-u2" - unique {
    val triples             = getTriples
    val List(a, b, c, d, e) = triples.sortBy(p => (p._1, p._3, p._2))
    val query               = (c: String, l: Int) => Uniques.s.a1.i.a3.int.a2.query.from(c).limit(l)
    for {
      _ <- Uniques.s.i.int.insert(triples).transact
      c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
      c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
      c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
    } yield ()
  }

  "Unique last: 3-1-u2" - unique {
    val triples             = getTriples
    val List(a, b, c, d, e) = triples.sortBy(p => (p._2, p._3, p._1))
    val query               = (c: String, l: Int) => Uniques.s.a3.i.a1.int.a2.query.from(c).limit(l)
    for {
      _ <- Uniques.s.i.int.insert(triples).transact
      c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
      c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
      c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
    } yield ()
  }
}