package molecule.coreTests.spi.pagination.cursor.primaryUnique

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._
import scala.annotation.nowarn

trait TypesFilterAttr extends CoreTestSuite with Api_async { spi: Spi_async =>

  @nowarn lazy val tests = Tests {

    "String" - types { implicit conn =>
      val pairs               = List(
        (1, string1),
        (2, string1),
        (3, string1),
        (4, string2),
        (5, string2),
      )
      val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
      val query               = (cursor: String, limit: Int) => Ns.u.a1.string.a2.query.from(cursor).limit(limit)
      for {
        _ <- Ns.u.string.insert(pairs).transact
        c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
        c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
        c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
      } yield ()
    }

    "Int" - types { implicit conn =>
      val pairs               = List(
        (1, int1),
        (2, int1),
        (3, int1),
        (4, int2),
        (5, int2),
      )
      val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
      val query               = (cursor: String, limit: Int) => Ns.u.a1.int.a2.query.from(cursor).limit(limit)
      for {
        _ <- Ns.u.int.insert(pairs).transact
        c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
        c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
        c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
      } yield ()
    }

    "Long" - types { implicit conn =>
      val pairs               = List(
        (1, long1),
        (2, long1),
        (3, long1),
        (4, long2),
        (5, long2),
      )
      val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
      val query               = (cursor: String, limit: Int) => Ns.u.a1.long.a2.query.from(cursor).limit(limit)
      for {
        _ <- Ns.u.long.insert(pairs).transact
        c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
        c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
        c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
      } yield ()
    }

    "Float" - types { implicit conn =>
      val pairs               = List(
        (1, float1),
        (2, float1),
        (3, float1),
        (4, float2),
        (5, float2),
      )
      val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
      val query               = (cursor: String, limit: Int) => Ns.u.a1.float.a2.query.from(cursor).limit(limit)
      for {
        _ <- Ns.u.float.insert(pairs).transact
        c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
        c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
        c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
      } yield ()
    }

    "Double" - types { implicit conn =>
      val pairs               = List(
        (1, double1),
        (2, double1),
        (3, double1),
        (4, double2),
        (5, double2),
      )
      val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
      val query               = (cursor: String, limit: Int) => Ns.u.a1.double.a2.query.from(cursor).limit(limit)
      for {
        _ <- Ns.u.double.insert(pairs).transact
        c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
        c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
        c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
      } yield ()
    }

    "Boolean" - types { implicit conn =>
      // Can save only 2 ns boolean values
      val pairs      = List(
        (1, boolean1),
        (2, boolean2),
      )
      val List(a, b) = pairs.sortBy(p => (p._1, p._2))
      val query      = (cursor: String, limit: Int) => Ns.u.a1.boolean.a2.query.from(cursor).limit(limit)
      for {
        _ <- Ns.u.boolean.insert(pairs).transact
        c1 <- query("", 1).get.map { case (List(`a`), cursor, true) => cursor }
        c2 <- query(c1, 1).get.map { case (List(`b`), cursor, false) => cursor }
        _ <- query(c2, -1).get.map { case (List(`a`), _, false) => () }
      } yield ()
    }

    "BigInt" - types { implicit conn =>
      val pairs               = List(
        (1, bigInt1),
        (2, bigInt1),
        (3, bigInt1),
        (4, bigInt2),
        (5, bigInt2),
      )
      val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
      val query               = (cursor: String, limit: Int) => Ns.u.a1.bigInt.a2.query.from(cursor).limit(limit)
      for {
        _ <- Ns.u.bigInt.insert(pairs).transact
        c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
        c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
        c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
      } yield ()
    }

    "BigDecimal" - types { implicit conn =>
      val pairs               = List(
        (1, bigDecimal1),
        (2, bigDecimal1),
        (3, bigDecimal1),
        (4, bigDecimal2),
        (5, bigDecimal2),
      )
      val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
      val query               = (cursor: String, limit: Int) => Ns.u.a1.bigDecimal.a2.query.from(cursor).limit(limit)
      for {
        _ <- Ns.u.bigDecimal.insert(pairs).transact
        c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
        c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
        c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
      } yield ()
    }

    "Date" - types { implicit conn =>
      val pairs               = List(
        (1, date1),
        (2, date1),
        (3, date1),
        (4, date2),
        (5, date2),
      )
      val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
      val query               = (cursor: String, limit: Int) => Ns.u.a1.date.a2.query.from(cursor).limit(limit)
      for {
        _ <- Ns.u.date.insert(pairs).transact
        c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
        c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
        c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
      } yield ()
    }

    "Duration" - types { implicit conn =>
      val pairs               = List(
        (1, duration1),
        (2, duration1),
        (3, duration1),
        (4, duration2),
        (5, duration2),
      )
      val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
      val query               = (cursor: String, limit: Int) => Ns.u.a1.duration.a2.query.from(cursor).limit(limit)
      for {
        _ <- Ns.u.duration.insert(pairs).transact
        c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
        c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
        c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
      } yield ()
    }

    "Instant" - types { implicit conn =>
      val pairs               = List(
        (1, instant1),
        (2, instant1),
        (3, instant1),
        (4, instant2),
        (5, instant2),
      )
      val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
      val query               = (cursor: String, limit: Int) => Ns.u.a1.instant.a2.query.from(cursor).limit(limit)
      for {
        _ <- Ns.u.instant.insert(pairs).transact
        c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
        c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
        c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
      } yield ()
    }

    "LocalDate" - types { implicit conn =>
      val pairs               = List(
        (1, localDate1),
        (2, localDate1),
        (3, localDate1),
        (4, localDate2),
        (5, localDate2),
      )
      val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
      val query               = (cursor: String, limit: Int) => Ns.u.a1.localDate.a2.query.from(cursor).limit(limit)
      for {
        _ <- Ns.u.localDate.insert(pairs).transact
        c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
        c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
        c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
      } yield ()
    }

    "LocalTime" - types { implicit conn =>
      val pairs               = List(
        (1, localTime1),
        (2, localTime1),
        (3, localTime1),
        (4, localTime2),
        (5, localTime2),
      )
      val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
      val query               = (cursor: String, limit: Int) => Ns.u.a1.localTime.a2.query.from(cursor).limit(limit)
      for {
        _ <- Ns.u.localTime.insert(pairs).transact
        c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
        c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
        c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
      } yield ()
    }

    "LocalDateTime" - types { implicit conn =>
      val pairs               = List(
        (1, localDateTime1),
        (2, localDateTime1),
        (3, localDateTime1),
        (4, localDateTime2),
        (5, localDateTime2),
      )
      val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
      val query               = (cursor: String, limit: Int) => Ns.u.a1.localDateTime.a2.query.from(cursor).limit(limit)
      for {
        _ <- Ns.u.localDateTime.insert(pairs).transact
        c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
        c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
        c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
      } yield ()
    }

    "OffsetTime" - types { implicit conn =>
      val pairs               = List(
        (1, offsetTime1),
        (2, offsetTime1),
        (3, offsetTime1),
        (4, offsetTime2),
        (5, offsetTime2),
      )
      val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
      val query               = (cursor: String, limit: Int) => Ns.u.a1.offsetTime.a2.query.from(cursor).limit(limit)
      for {
        _ <- Ns.u.offsetTime.insert(pairs).transact
        c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
        c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
        c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
      } yield ()
    }

    "OffsetDateTime" - types { implicit conn =>
      val pairs               = List(
        (1, offsetDateTime1),
        (2, offsetDateTime1),
        (3, offsetDateTime1),
        (4, offsetDateTime2),
        (5, offsetDateTime2),
      )
      val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
      val query               = (cursor: String, limit: Int) => Ns.u.a1.offsetDateTime.a2.query.from(cursor).limit(limit)
      for {
        _ <- Ns.u.offsetDateTime.insert(pairs).transact
        c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
        c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
        c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
      } yield ()
    }

    "ZonedDateTime" - types { implicit conn =>
      val pairs               = List(
        (1, zonedDateTime1),
        (2, zonedDateTime1),
        (3, zonedDateTime1),
        (4, zonedDateTime2),
        (5, zonedDateTime2),
      )
      val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
      val query               = (cursor: String, limit: Int) => Ns.u.a1.zonedDateTime.a2.query.from(cursor).limit(limit)
      for {
        _ <- Ns.u.zonedDateTime.insert(pairs).transact
        c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
        c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
        c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
      } yield ()
    }

    "Uuid" - types { implicit conn =>
      val pairs               = List(
        (1, uuid1),
        (2, uuid1),
        (3, uuid1),
        (4, uuid2),
        (5, uuid2),
      )
      val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
      val query               = (cursor: String, limit: Int) => Ns.u.a1.uuid.a2.query.from(cursor).limit(limit)
      for {
        _ <- Ns.u.uuid.insert(pairs).transact
        c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
        c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
        c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
      } yield ()
    }

    "Uri" - types { implicit conn =>
      val pairs               = List(
        (1, uri1),
        (2, uri1),
        (3, uri1),
        (4, uri2),
        (5, uri2),
      )
      val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
      val query               = (cursor: String, limit: Int) => Ns.u.a1.uri.a2.query.from(cursor).limit(limit)
      for {
        _ <- Ns.u.uri.insert(pairs).transact
        c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
        c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
        c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
      } yield ()
    }

    "Byte" - types { implicit conn =>
      val pairs               = List(
        (1, byte1),
        (2, byte1),
        (3, byte1),
        (4, byte2),
        (5, byte2),
      )
      val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
      val query               = (cursor: String, limit: Int) => Ns.u.a1.byte.a2.query.from(cursor).limit(limit)
      for {
        _ <- Ns.u.byte.insert(pairs).transact
        c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
        c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
        c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
      } yield ()
    }

    "Short" - types { implicit conn =>
      val pairs               = List(
        (1, short1),
        (2, short1),
        (3, short1),
        (4, short2),
        (5, short2),
      )
      val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
      val query               = (cursor: String, limit: Int) => Ns.u.a1.short.a2.query.from(cursor).limit(limit)
      for {
        _ <- Ns.u.short.insert(pairs).transact
        c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
        c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
        c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
      } yield ()
    }

    "Char" - types { implicit conn =>
      val pairs               = List(
        (1, char1),
        (2, char1),
        (3, char1),
        (4, char2),
        (5, char2),
      )
      val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
      val query               = (cursor: String, limit: Int) => Ns.u.a1.char.a2.query.from(cursor).limit(limit)
      for {
        _ <- Ns.u.char.insert(pairs).transact
        c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
        c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
        c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
      } yield ()
    }
  }
}