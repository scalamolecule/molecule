package molecule.db.compliance.test.action.insert

import java.net.URI
import java.time.*
import java.util.{Date, UUID}
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*
import scala.concurrent.Future

case class InsertCardOne(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "mandatory" - types { implicit conn =>
    for {
      _ <- Entity.string.insert(string1).transact
      _ <- Entity.int.insert(intMin, int1, intMax).transact
      _ <- Entity.long.insert(longMin, long1, longMax).transact
      _ <- Entity.float.insert(floatMin, float1, floatMax).transact
      _ <- Entity.double.insert(doubleMin, double1, doubleMax).transact
      _ <- Entity.boolean.insert(boolean1).transact
      _ <- Entity.bigInt.insert(bigIntNeg, bigInt1, bigIntPos).transact
      _ <- Entity.bigDecimal.insert(bigDecimalNeg, bigDecimal1, bigDecimalPos).transact
      _ <- Entity.date.insert(date1).transact
      _ <- Entity.duration.insert(duration1).transact
      _ <- Entity.instant.insert(instant1).transact
      _ <- Entity.localDate.insert(localDate1).transact
      _ <- Entity.localTime.insert(localTime1).transact
      _ <- Entity.localDateTime.insert(localDateTime1).transact
      _ <- Entity.offsetTime.insert(offsetTime1).transact
      _ <- Entity.offsetDateTime.insert(offsetDateTime1).transact
      _ <- Entity.zonedDateTime.insert(zonedDateTime1).transact
      _ <- Entity.uuid.insert(uuid1).transact
      _ <- Entity.uri.insert(uri1).transact
      _ <- Entity.byte.insert(byteMin, byte1, byteMax).transact
      _ <- Entity.short.insert(shortMin, short1, shortMax).transact
      _ <- Entity.char.insert(char1).transact

      _ <- Entity.string.a1.query.get.map(_ ==> List(string1))
      _ <- Entity.int.a1.query.get.map(_ ==> List(intMin, int1, intMax))
      _ <- Entity.long.a1.query.get.map(_ ==> List(longMin, long1, longMax))
      _ <- Entity.float.a1.query.get.map(_ ==> List(floatMin, float1, floatMax))
      _ <- Entity.double.a1.query.get.map(_ ==> List(doubleMin, double1, doubleMax))
      _ <- Entity.boolean.a1.query.get.map(_ ==> List(boolean1))
      _ <- Entity.bigInt.a1.query.get.map(_ ==> List(bigIntNeg, bigInt1, bigIntPos))
      _ <- Entity.bigDecimal.a1.query.get.map(_ ==> List(bigDecimalNeg, bigDecimal1, bigDecimalPos))
      _ <- Entity.date.a1.query.get.map(_ ==> List(date1))
      _ <- Entity.duration.a1.query.get.map(_ ==> List(duration1))
      _ <- Entity.instant.a1.query.get.map(_ ==> List(instant1))
      _ <- Entity.localDate.a1.query.get.map(_ ==> List(localDate1))
      _ <- Entity.localTime.a1.query.get.map(_ ==> List(localTime1))
      _ <- Entity.localDateTime.a1.query.get.map(_ ==> List(localDateTime1))
      _ <- Entity.offsetTime.a1.query.get.map(_ ==> List(offsetTime1))
      _ <- Entity.offsetDateTime.a1.query.get.map(_ ==> List(offsetDateTime1))
      _ <- Entity.zonedDateTime.a1.query.get.map(_ ==> List(zonedDateTime1))
      _ <- Entity.uuid.a1.query.get.map(_ ==> List(uuid1))
      _ <- Entity.uri.a1.query.get.map(_ ==> List(uri1))
      _ <- Entity.byte.a1.query.get.map(_ ==> List(byteMin, byte1, byteMax))
      _ <- Entity.short.a1.query.get.map(_ ==> List(shortMin, short1, shortMax))
      _ <- Entity.char.a1.query.get.map(_ ==> List(char1))

      case List(r1, r2) <- Ref.i.insert(1, 2).transact.map(_.ids)
      _ <- Entity.ref.insert(r1).transact
      _ <- Entity.ref.insert(Seq(r2)).transact
      _ <- Entity.ref.a1.query.get.map(_ ==> List(r1, r2))
    } yield ()
  }


  "Optional" - types { implicit conn =>
    for {
      _ <- Entity.i.string_?.insert((1, Some(string1))).transact
      _ <- Entity.i.int_?.insert((1, Some(int1))).transact
      _ <- Entity.i.long_?.insert((1, Some(long1))).transact
      _ <- Entity.i.float_?.insert((1, Some(float1))).transact
      _ <- Entity.i.double_?.insert((1, Some(double1))).transact
      _ <- Entity.i.boolean_?.insert((1, Some(boolean1))).transact
      _ <- Entity.i.bigInt_?.insert((1, Some(bigInt1))).transact
      _ <- Entity.i.bigDecimal_?.insert((1, Some(bigDecimal1))).transact
      _ <- Entity.i.date_?.insert((1, Some(date1))).transact
      _ <- Entity.i.duration_?.insert((1, Some(duration1))).transact
      _ <- Entity.i.instant_?.insert((1, Some(instant1))).transact
      _ <- Entity.i.localDate_?.insert((1, Some(localDate1))).transact
      _ <- Entity.i.localTime_?.insert((1, Some(localTime1))).transact
      _ <- Entity.i.localDateTime_?.insert((1, Some(localDateTime1))).transact
      _ <- Entity.i.offsetTime_?.insert((1, Some(offsetTime1))).transact
      _ <- Entity.i.offsetDateTime_?.insert((1, Some(offsetDateTime1))).transact
      _ <- Entity.i.zonedDateTime_?.insert((1, Some(zonedDateTime1))).transact
      _ <- Entity.i.uuid_?.insert((1, Some(uuid1))).transact
      _ <- Entity.i.uri_?.insert((1, Some(uri1))).transact
      _ <- Entity.i.byte_?.insert((1, Some(byte1))).transact
      _ <- Entity.i.short_?.insert((1, Some(short1))).transact
      _ <- Entity.i.char_?.insert((1, Some(char1))).transact

      _ <- Entity.i.string_?.insert((1, Option.empty[String])).transact
      _ <- Entity.i.int_?.insert((1, Option.empty[Int])).transact
      _ <- Entity.i.long_?.insert((1, Option.empty[Long])).transact
      _ <- Entity.i.float_?.insert((1, Option.empty[Float])).transact
      _ <- Entity.i.double_?.insert((1, Option.empty[Double])).transact
      _ <- Entity.i.boolean_?.insert((1, Option.empty[Boolean])).transact
      _ <- Entity.i.bigInt_?.insert((1, Option.empty[BigInt])).transact
      _ <- Entity.i.bigDecimal_?.insert((1, Option.empty[BigDecimal])).transact
      _ <- Entity.i.date_?.insert((1, Option.empty[Date])).transact
      _ <- Entity.i.duration_?.insert((1, Option.empty[Duration])).transact
      _ <- Entity.i.instant_?.insert((1, Option.empty[Instant])).transact
      _ <- Entity.i.localDate_?.insert((1, Option.empty[LocalDate])).transact
      _ <- Entity.i.localTime_?.insert((1, Option.empty[LocalTime])).transact
      _ <- Entity.i.localDateTime_?.insert((1, Option.empty[LocalDateTime])).transact
      _ <- Entity.i.offsetTime_?.insert((1, Option.empty[OffsetTime])).transact
      _ <- Entity.i.offsetDateTime_?.insert((1, Option.empty[OffsetDateTime])).transact
      _ <- Entity.i.zonedDateTime_?.insert((1, Option.empty[ZonedDateTime])).transact
      _ <- Entity.i.uuid_?.insert((1, Option.empty[UUID])).transact
      _ <- Entity.i.uri_?.insert((1, Option.empty[URI])).transact
      _ <- Entity.i.byte_?.insert((1, Option.empty[Byte])).transact
      _ <- Entity.i.short_?.insert((1, Option.empty[Short])).transact
      _ <- Entity.i.char_?.insert((1, Option.empty[Char])).transact

      _ <- Entity.i_.string_?.a1.query.get.map(_ ==> List(None, Some(string1)))
      _ <- Entity.i_.int_?.a1.query.get.map(_ ==> List(None, Some(int1)))
      _ <- Entity.i_.long_?.a1.query.get.map(_ ==> List(None, Some(long1)))
      _ <- Entity.i_.float_?.a1.query.get.map(_ ==> List(None, Some(float1)))
      _ <- Entity.i_.double_?.a1.query.get.map(_ ==> List(None, Some(double1)))
      _ <- Entity.i_.boolean_?.a1.query.get.map(_ ==> List(None, Some(boolean1)))
      _ <- Entity.i_.bigInt_?.a1.query.get.map(_ ==> List(None, Some(bigInt1)))
      _ <- Entity.i_.bigDecimal_?.a1.query.get.map(_ ==> List(None, Some(bigDecimal1)))
      _ <- Entity.i_.date_?.a1.query.get.map(_ ==> List(None, Some(date1)))
      _ <- Entity.i_.duration_?.a1.query.get.map(_ ==> List(None, Some(duration1)))
      _ <- Entity.i_.instant_?.a1.query.get.map(_ ==> List(None, Some(instant1)))
      _ <- Entity.i_.localDate_?.a1.query.get.map(_ ==> List(None, Some(localDate1)))
      _ <- Entity.i_.localTime_?.a1.query.get.map(_ ==> List(None, Some(localTime1)))
      _ <- Entity.i_.localDateTime_?.a1.query.get.map(_ ==> List(None, Some(localDateTime1)))
      _ <- Entity.i_.offsetTime_?.a1.query.get.map(_ ==> List(None, Some(offsetTime1)))
      _ <- Entity.i_.offsetDateTime_?.a1.query.get.map(_ ==> List(None, Some(offsetDateTime1)))
      _ <- Entity.i_.zonedDateTime_?.a1.query.get.map(_ ==> List(None, Some(zonedDateTime1)))
      _ <- Entity.i_.uuid_?.a1.query.get.map(_ ==> List(None, Some(uuid1)))
      _ <- Entity.i_.uri_?.a1.query.get.map(_ ==> List(None, Some(uri1)))
      _ <- Entity.i_.byte_?.a1.query.get.map(_ ==> List(None, Some(byte1)))
      _ <- Entity.i_.short_?.a1.query.get.map(_ ==> List(None, Some(short1)))
      _ <- Entity.i_.char_?.a1.query.get.map(_ ==> List(None, Some(char1)))

      r1 <- Ref.i.insert(1, 2).transact.map(_.id)
      _ <- Entity.i.ref_?.insert((1, Option.empty[Long])).transact
      _ <- Entity.i.ref_?.insert((1, Some(r1))).transact
      _ <- Entity.i_.ref_?.a1.query.get.map(_ ==> List(None, Some(r1)))
    } yield ()
  }


  "Tacit" - types { implicit conn =>
    for {
      // Can't insert tacit attributes
      _ <- Future(compileErrors("Entity.i.string_.insert(1, string1)"))
    } yield ()
  }
}
