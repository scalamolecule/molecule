package molecule.db.datomic.test.pagination.cursor.noUnique

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Unique._
import molecule.db.datomic.async._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._
import scala.annotation.{nowarn, tailrec}
import scala.util.Random

object TypesOptional extends DatomicTestSuite {

  @nowarn lazy val tests = Tests {

    "String" - unique { implicit conn =>
      val pairs               = List(
        (1, None),
        (1, Some(string1)),
        (2, None),
        (2, Some(string2)),
        (2, Some(string3)),
      )
      val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
      val query               = (cursor: String, limit: Int) =>
        Unique.i.a1.string_?.a2.query.from(cursor).limit(limit)
      for {
        _ <- Unique.i.string_?.insert(pairs).transact
        c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
        c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
        c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
      } yield ()
    }

    "Int" - unique { implicit conn =>
      val pairs               = List(
        (1, None),
        (1, Some(int1)),
        (2, None),
        (2, Some(int2)),
        (2, Some(int3)),
      )
      val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
      val query               = (cursor: String, limit: Int) =>
        Unique.i.a1.int_?.a2.query.from(cursor).limit(limit)
      for {
        _ <- Unique.i.int_?.insert(pairs).transact
        c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
        c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
        c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
      } yield ()
    }

    "Long" - unique { implicit conn =>
      val pairs               = List(
        (1, None),
        (1, Some(long1)),
        (2, None),
        (2, Some(long2)),
        (2, Some(long3)),
      )
      val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
      val query               = (cursor: String, limit: Int) =>
        Unique.i.a1.long_?.a2.query.from(cursor).limit(limit)
      for {
        _ <- Unique.i.long_?.insert(pairs).transact
        c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
        c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
        c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
      } yield ()
    }

    "Float" - unique { implicit conn =>
      val pairs               = List(
        (1, None),
        (1, Some(float1)),
        (2, None),
        (2, Some(float2)),
        (2, Some(float3)),
      )
      val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
      val query               = (cursor: String, limit: Int) =>
        Unique.i.a1.float_?.a2.query.from(cursor).limit(limit)
      for {
        _ <- Unique.i.float_?.insert(pairs).transact
        c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
        c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
        c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
      } yield ()
    }

    "Double" - unique { implicit conn =>
      val pairs               = List(
        (1, None),
        (1, Some(double1)),
        (2, None),
        (2, Some(double2)),
        (2, Some(double3)),
      )
      val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
      val query               = (cursor: String, limit: Int) =>
        Unique.i.a1.double_?.a2.query.from(cursor).limit(limit)
      for {
        _ <- Unique.i.double_?.insert(pairs).transact
        c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
        c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
        c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
      } yield ()
    }

    "Boolean" - unique { implicit conn =>
      // Can save only 2 unique boolean values
      val pairs      = List(
        (1, None),
        (1, Some(boolean1)),
      )
      val List(a, b) = pairs.sortBy(p => (p._1, p._2))
      val query      = (cursor: String, limit: Int) =>
        Unique.i.a1.boolean_?.a2.query.from(cursor).limit(limit)
      for {
        _ <- Unique.i.boolean_?.insert(pairs).transact
        c1 <- query("", 1).get.map { case (List(`a`), cursor, true) => cursor }
        c2 <- query(c1, 1).get.map { case (List(`b`), cursor, false) => cursor }
        _ <- query(c2, -1).get.map { case (List(`a`), _, false) => () }
      } yield ()
    }

    "BigInt" - unique { implicit conn =>
      val pairs               = List(
        (1, None),
        (1, Some(bigInt1)),
        (2, None),
        (2, Some(bigInt2)),
        (2, Some(bigInt3)),
      )
      val pairsx              = List(
        (1, bigInt1),
        (1, bigInt3),
        (1, bigInt5),
        (2, bigInt2),
        (2, bigInt4),
      )
      val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
      val query               = (cursor: String, limit: Int) =>
        Unique.i.a1.bigInt_?.a2.query.from(cursor).limit(limit)
      for {
        _ <- Unique.i.bigInt_?.insert(pairs).transact
        c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
        c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
        c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
      } yield ()
    }

    "BigDecimal" - unique { implicit conn =>
      val pairs               = List(
        (1, None),
        (1, Some(bigDecimal1)),
        (2, None),
        (2, Some(bigDecimal2)),
        (2, Some(bigDecimal3)),
      )
      val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
      val query               = (cursor: String, limit: Int) =>
        Unique.i.a1.bigDecimal_?.a2.query.from(cursor).limit(limit)
      for {
        _ <- Unique.i.bigDecimal_?.insert(pairs).transact
        c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
        c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
        c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
      } yield ()
    }

    "Date" - unique { implicit conn =>
      val pairs               = List(
        (1, None),
        (1, Some(date1)),
        (2, None),
        (2, Some(date2)),
        (2, Some(date3)),
      )
      val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
      val query               = (cursor: String, limit: Int) =>
        Unique.i.a1.date_?.a2.query.from(cursor).limit(limit)
      for {
        _ <- Unique.i.date_?.insert(pairs).transact
        c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
        c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
        c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
      } yield ()
    }

    "Uuid" - unique { implicit conn =>
      val pairs               = List(
        (1, None),
        (1, Some(uuid1)),
        (2, None),
        (2, Some(uuid2)),
        (2, Some(uuid3)),
      )
      val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
      val query               = (cursor: String, limit: Int) =>
        Unique.i.a1.uuid_?.a2.query.from(cursor).limit(limit)
      for {
        _ <- Unique.i.uuid_?.insert(pairs).transact
        c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
        c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
        c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
      } yield ()
    }

    "Uri" - unique { implicit conn =>
      val pairs               = List(
        (1, None),
        (1, Some(uri1)),
        (2, None),
        (2, Some(uri2)),
        (2, Some(uri3)),
      )
      val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
      val query               = (cursor: String, limit: Int) =>
        Unique.i.a1.uri_?.a2.query.from(cursor).limit(limit)
      for {
        _ <- Unique.i.uri_?.insert(pairs).transact
        c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
        c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
        c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
      } yield ()
    }

    "Byte" - unique { implicit conn =>
      val pairs               = List(
        (1, None),
        (1, Some(byte1)),
        (2, None),
        (2, Some(byte2)),
        (2, Some(byte3)),
      )
      val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
      val query               = (cursor: String, limit: Int) =>
        Unique.i.a1.byte_?.a2.query.from(cursor).limit(limit)
      for {
        _ <- Unique.i.byte_?.insert(pairs).transact
        c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
        c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
        c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
      } yield ()
    }

    "Short" - unique { implicit conn =>
      val pairs               = List(
        (1, None),
        (1, Some(short1)),
        (2, None),
        (2, Some(short2)),
        (2, Some(short3)),
      )
      val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
      val query               = (cursor: String, limit: Int) =>
        Unique.i.a1.short_?.a2.query.from(cursor).limit(limit)
      for {
        _ <- Unique.i.short_?.insert(pairs).transact
        c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
        c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
        c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
      } yield ()
    }

    "Char" - unique { implicit conn =>
      val pairs               = List(
        (1, None),
        (1, Some(char1)),
        (2, None),
        (2, Some(char2)),
        (2, Some(char3)),
      )
      val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
      val query               = (cursor: String, limit: Int) =>
        Unique.i.a1.char_?.a2.query.from(cursor).limit(limit)
      for {
        _ <- Unique.i.char_?.insert(pairs).transact
        c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
        c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
        c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
      } yield ()
    }
  }
}
