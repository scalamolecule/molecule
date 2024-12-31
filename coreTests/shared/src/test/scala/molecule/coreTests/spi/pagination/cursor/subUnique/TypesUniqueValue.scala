package molecule.coreTests.spi.pagination.cursor.subUnique

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.domains.dsl.Uniques._
import molecule.coreTests.setup._
import scala.annotation.nowarn
import scala.util.Random

@nowarn
case class TypesUniqueValue(
  suite: Test,
  api: Api_async with Spi_async with DbProviders
) extends TestUtils {

  import api._
  import suite._

  "String" - unique { implicit conn =>
    val pairs               = List(
      (Random.nextInt(3) + 1, string1),
      (Random.nextInt(3) + 1, string2),
      (Random.nextInt(3) + 1, string3),
      (Random.nextInt(3) + 1, string4),
      (Random.nextInt(3) + 1, string5),
    )
    val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
    val query               = (cursor: String, limit: Int) => Uniques.i.a1.string.a2.query.from(cursor).limit(limit)
    for {
      _ <- Uniques.i.string.insert(pairs).transact
      c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
      c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
      c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
    } yield ()
  }

  "Int" - unique { implicit conn =>
    val pairs               = List(
      (Random.nextInt(3) + 1, int1),
      (Random.nextInt(3) + 1, int2),
      (Random.nextInt(3) + 1, int3),
      (Random.nextInt(3) + 1, int4),
      (Random.nextInt(3) + 1, int5),
    )
    val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
    val query               = (cursor: String, limit: Int) => Uniques.i.a1.int.a2.query.from(cursor).limit(limit)
    for {
      _ <- Uniques.i.int.insert(pairs).transact
      c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
      c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
      c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
    } yield ()
  }

  "Long" - unique { implicit conn =>
    val pairs               = List(
      (Random.nextInt(3) + 1, long1),
      (Random.nextInt(3) + 1, long2),
      (Random.nextInt(3) + 1, long3),
      (Random.nextInt(3) + 1, long4),
      (Random.nextInt(3) + 1, long5),
    )
    val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
    val query               = (cursor: String, limit: Int) => Uniques.i.a1.long.a2.query.from(cursor).limit(limit)
    for {
      _ <- Uniques.i.long.insert(pairs).transact
      c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
      c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
      c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
    } yield ()
  }

  "Float" - unique { implicit conn =>
    val pairs               = List(
      (Random.nextInt(3) + 1, float1),
      (Random.nextInt(3) + 1, float2),
      (Random.nextInt(3) + 1, float3),
      (Random.nextInt(3) + 1, float4),
      (Random.nextInt(3) + 1, float5),
    )
    val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
    val query               = (cursor: String, limit: Int) => Uniques.i.a1.float.a2.query.from(cursor).limit(limit)
    for {
      _ <- Uniques.i.float.insert(pairs).transact
      c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
      c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
      c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
    } yield ()
  }

  "Double" - unique { implicit conn =>
    val pairs               = List(
      (Random.nextInt(3) + 1, double1),
      (Random.nextInt(3) + 1, double2),
      (Random.nextInt(3) + 1, double3),
      (Random.nextInt(3) + 1, double4),
      (Random.nextInt(3) + 1, double5),
    )
    val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
    val query               = (cursor: String, limit: Int) => Uniques.i.a1.double.a2.query.from(cursor).limit(limit)
    for {
      _ <- Uniques.i.double.insert(pairs).transact
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
      (Random.nextInt(3) + 1, boolean1),
      (Random.nextInt(3) + 1, boolean2),
    )
    val List(a, b) = pairs.sortBy(p => (p._1, p._2))
    val query      = (cursor: String, limit: Int) => Uniques.i.a1.boolean.a2.query.from(cursor).limit(limit)
    for {
      _ <- Uniques.i.boolean.insert(pairs).transact
      c1 <- query("", 1).get.map { case (List(`a`), cursor, true) => cursor }
      c2 <- query(c1, 1).get.map { case (List(`b`), cursor, false) => cursor }
      _ <- query(c2, -1).get.map { case (List(`a`), _, false) => () }
    } yield ()
  }

  "BigInt" - unique { implicit conn =>
    val pairs               = List(
      (Random.nextInt(3) + 1, bigInt1),
      (Random.nextInt(3) + 1, bigInt2),
      (Random.nextInt(3) + 1, bigInt3),
      (Random.nextInt(3) + 1, bigInt4),
      (Random.nextInt(3) + 1, bigInt5),
    )
    val pairsx              = List(
      (1, bigInt1),
      (1, bigInt3),
      (1, bigInt5),
      (2, bigInt2),
      (2, bigInt4),
    )
    val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
    val query               = (cursor: String, limit: Int) => Uniques.i.a1.bigInt.a2.query.from(cursor).limit(limit)
    for {
      _ <- Uniques.i.bigInt.insert(pairs).transact
      c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
      c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
      c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
    } yield ()
  }

  "BigDecimal" - unique { implicit conn =>
    val pairs               = List(
      (Random.nextInt(3) + 1, bigDecimal1),
      (Random.nextInt(3) + 1, bigDecimal2),
      (Random.nextInt(3) + 1, bigDecimal3),
      (Random.nextInt(3) + 1, bigDecimal4),
      (Random.nextInt(3) + 1, bigDecimal5),
    )
    val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
    val query               = (cursor: String, limit: Int) => Uniques.i.a1.bigDecimal.a2.query.from(cursor).limit(limit)
    for {
      _ <- Uniques.i.bigDecimal.insert(pairs).transact
      c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
      c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
      c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
    } yield ()
  }

  "Date" - unique { implicit conn =>
    val pairs               = List(
      (Random.nextInt(3) + 1, date1),
      (Random.nextInt(3) + 1, date2),
      (Random.nextInt(3) + 1, date3),
      (Random.nextInt(3) + 1, date4),
      (Random.nextInt(3) + 1, date5),
    )
    val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
    val query               = (cursor: String, limit: Int) => Uniques.i.a1.date.a2.query.from(cursor).limit(limit)
    for {
      _ <- Uniques.i.date.insert(pairs).transact
      c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
      c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
      c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
    } yield ()
  }

  "Duration" - unique { implicit conn =>
    val pairs               = List(
      (Random.nextInt(3) + 1, duration1),
      (Random.nextInt(3) + 1, duration2),
      (Random.nextInt(3) + 1, duration3),
      (Random.nextInt(3) + 1, duration4),
      (Random.nextInt(3) + 1, duration5),
    )
    val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
    val query               = (cursor: String, limit: Int) => Uniques.i.a1.duration.a2.query.from(cursor).limit(limit)
    for {
      _ <- Uniques.i.duration.insert(pairs).transact
      c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
      c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
      c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
    } yield ()
  }

  "Instant" - unique { implicit conn =>
    val pairs               = List(
      (Random.nextInt(3) + 1, instant1),
      (Random.nextInt(3) + 1, instant2),
      (Random.nextInt(3) + 1, instant3),
      (Random.nextInt(3) + 1, instant4),
      (Random.nextInt(3) + 1, instant5),
    )
    val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
    val query               = (cursor: String, limit: Int) => Uniques.i.a1.instant.a2.query.from(cursor).limit(limit)
    for {
      _ <- Uniques.i.instant.insert(pairs).transact
      c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
      c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
      c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
    } yield ()
  }

  "LocalDate" - unique { implicit conn =>
    val pairs               = List(
      (Random.nextInt(3) + 1, localDate1),
      (Random.nextInt(3) + 1, localDate2),
      (Random.nextInt(3) + 1, localDate3),
      (Random.nextInt(3) + 1, localDate4),
      (Random.nextInt(3) + 1, localDate5),
    )
    val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
    val query               = (cursor: String, limit: Int) => Uniques.i.a1.localDate.a2.query.from(cursor).limit(limit)
    for {
      _ <- Uniques.i.localDate.insert(pairs).transact
      c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
      c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
      c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
    } yield ()
  }

  "LocalTime" - unique { implicit conn =>
    val pairs               = List(
      (Random.nextInt(3) + 1, localTime1),
      (Random.nextInt(3) + 1, localTime2),
      (Random.nextInt(3) + 1, localTime3),
      (Random.nextInt(3) + 1, localTime4),
      (Random.nextInt(3) + 1, localTime5),
    )
    val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
    val query               = (cursor: String, limit: Int) => Uniques.i.a1.localTime.a2.query.from(cursor).limit(limit)
    for {
      _ <- Uniques.i.localTime.insert(pairs).transact
      c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
      c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
      c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
    } yield ()
  }

  "LocalDateTime" - unique { implicit conn =>
    val pairs               = List(
      (Random.nextInt(3) + 1, localDateTime1),
      (Random.nextInt(3) + 1, localDateTime2),
      (Random.nextInt(3) + 1, localDateTime3),
      (Random.nextInt(3) + 1, localDateTime4),
      (Random.nextInt(3) + 1, localDateTime5),
    )
    val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
    val query               = (cursor: String, limit: Int) => Uniques.i.a1.localDateTime.a2.query.from(cursor).limit(limit)
    for {
      _ <- Uniques.i.localDateTime.insert(pairs).transact
      c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
      c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
      c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
    } yield ()
  }

  "OffsetTime" - unique { implicit conn =>
    val pairs               = List(
      (Random.nextInt(3) + 1, offsetTime1),
      (Random.nextInt(3) + 1, offsetTime2),
      (Random.nextInt(3) + 1, offsetTime3),
      (Random.nextInt(3) + 1, offsetTime4),
      (Random.nextInt(3) + 1, offsetTime5),
    )
    val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
    val query               = (cursor: String, limit: Int) => Uniques.i.a1.offsetTime.a2.query.from(cursor).limit(limit)
    for {
      _ <- Uniques.i.offsetTime.insert(pairs).transact
      c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
      c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
      c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
    } yield ()
  }

  "OffsetDateTime" - unique { implicit conn =>
    val pairs               = List(
      (Random.nextInt(3) + 1, offsetDateTime1),
      (Random.nextInt(3) + 1, offsetDateTime2),
      (Random.nextInt(3) + 1, offsetDateTime3),
      (Random.nextInt(3) + 1, offsetDateTime4),
      (Random.nextInt(3) + 1, offsetDateTime5),
    )
    val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
    val query               = (cursor: String, limit: Int) => Uniques.i.a1.offsetDateTime.a2.query.from(cursor).limit(limit)
    for {
      _ <- Uniques.i.offsetDateTime.insert(pairs).transact
      c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
      c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
      c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
    } yield ()
  }

  "ZonedDateTime" - unique { implicit conn =>
    val pairs               = List(
      (Random.nextInt(3) + 1, zonedDateTime1),
      (Random.nextInt(3) + 1, zonedDateTime2),
      (Random.nextInt(3) + 1, zonedDateTime3),
      (Random.nextInt(3) + 1, zonedDateTime4),
      (Random.nextInt(3) + 1, zonedDateTime5),
    )
    val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
    val query               = (cursor: String, limit: Int) => Uniques.i.a1.zonedDateTime.a2.query.from(cursor).limit(limit)
    for {
      _ <- Uniques.i.zonedDateTime.insert(pairs).transact
      c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
      c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
      c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
    } yield ()
  }

  "Uuid" - unique { implicit conn =>
    val pairs               = List(
      (Random.nextInt(3) + 1, uuid1),
      (Random.nextInt(3) + 1, uuid2),
      (Random.nextInt(3) + 1, uuid3),
      (Random.nextInt(3) + 1, uuid4),
      (Random.nextInt(3) + 1, uuid5),
    )
    val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
    val query               = (cursor: String, limit: Int) => Uniques.i.a1.uuid.a2.query.from(cursor).limit(limit)
    for {
      _ <- Uniques.i.uuid.insert(pairs).transact
      c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
      c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
      c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
    } yield ()
  }

  "Uri" - unique { implicit conn =>
    val pairs               = List(
      (Random.nextInt(3) + 1, uri1),
      (Random.nextInt(3) + 1, uri2),
      (Random.nextInt(3) + 1, uri3),
      (Random.nextInt(3) + 1, uri4),
      (Random.nextInt(3) + 1, uri5),
    )
    val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
    val query               = (cursor: String, limit: Int) => Uniques.i.a1.uri.a2.query.from(cursor).limit(limit)
    for {
      _ <- Uniques.i.uri.insert(pairs).transact
      c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
      c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
      c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
    } yield ()
  }

  "Byte" - unique { implicit conn =>
    val pairs               = List(
      (Random.nextInt(3) + 1, byte1),
      (Random.nextInt(3) + 1, byte2),
      (Random.nextInt(3) + 1, byte3),
      (Random.nextInt(3) + 1, byte4),
      (Random.nextInt(3) + 1, byte5),
    )
    val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
    val query               = (cursor: String, limit: Int) => Uniques.i.a1.byte.a2.query.from(cursor).limit(limit)
    for {
      _ <- Uniques.i.byte.insert(pairs).transact
      c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
      c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
      c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
    } yield ()
  }

  "Short" - unique { implicit conn =>
    val pairs               = List(
      (Random.nextInt(3) + 1, short1),
      (Random.nextInt(3) + 1, short2),
      (Random.nextInt(3) + 1, short3),
      (Random.nextInt(3) + 1, short4),
      (Random.nextInt(3) + 1, short5),
    )
    val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
    val query               = (cursor: String, limit: Int) => Uniques.i.a1.short.a2.query.from(cursor).limit(limit)
    for {
      _ <- Uniques.i.short.insert(pairs).transact
      c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
      c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
      c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
    } yield ()
  }

  "Char" - unique { implicit conn =>
    val pairs               = List(
      (Random.nextInt(3) + 1, char1),
      (Random.nextInt(3) + 1, char2),
      (Random.nextInt(3) + 1, char3),
      (Random.nextInt(3) + 1, char4),
      (Random.nextInt(3) + 1, char5),
    )
    val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
    val query               = (cursor: String, limit: Int) => Uniques.i.a1.char.a2.query.from(cursor).limit(limit)
    for {
      _ <- Uniques.i.char.insert(pairs).transact
      c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
      c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
      c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
      _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }
    } yield ()
  }
}
