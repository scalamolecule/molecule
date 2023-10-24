package molecule.coreTests.spi.crud.insert

import java.net.URI
import java.time._
import java.util.{Date, UUID}
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._
import scala.concurrent.Future

trait InsertCardOne extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "mandatory" - types { implicit conn =>
      for {
        _ <- Ns.string.insert(string1).transact
        _ <- Ns.int.insert(int1).transact
        _ <- Ns.long.insert(long1).transact
        _ <- Ns.float.insert(float1).transact
        _ <- Ns.double.insert(double1).transact
        _ <- Ns.boolean.insert(boolean1).transact
        _ <- Ns.bigInt.insert(bigInt1).transact
        _ <- Ns.bigDecimal.insert(bigDecimal1).transact
        _ <- Ns.date.insert(date1).transact
        _ <- Ns.duration.insert(duration1).transact
        _ <- Ns.instant.insert(instant1).transact
        _ <- Ns.localDate.insert(localDate1).transact
        _ <- Ns.localTime.insert(localTime1).transact
        _ <- Ns.localDateTime.insert(localDateTime1).transact
        _ <- Ns.offsetTime.insert(offsetTime1).transact
        _ <- Ns.offsetDateTime.insert(offsetDateTime1).transact
        _ <- Ns.zonedDateTime.insert(zonedDateTime1).transact
        _ <- Ns.uuid.insert(uuid1).transact
        _ <- Ns.uri.insert(uri1).transact
        _ <- Ns.byte.insert(byte1).transact
        _ <- Ns.short.insert(short1).transact
        _ <- Ns.char.insert(char1).transact

        _ <- Ns.string.a1.query.get.map(_ ==> List(string1))
        _ <- Ns.int.a1.query.get.map(_ ==> List(int1))
        _ <- Ns.long.a1.query.get.map(_ ==> List(long1))
        _ <- Ns.float.a1.query.get.map(_ ==> List(float1))
        _ <- Ns.double.a1.query.get.map(_ ==> List(double1))
        _ <- Ns.boolean.a1.query.get.map(_ ==> List(boolean1))
        _ <- Ns.bigInt.a1.query.get.map(_ ==> List(bigInt1))
        _ <- Ns.bigDecimal.a1.query.get.map(_ ==> List(bigDecimal1))
        _ <- Ns.date.a1.query.get.map(_ ==> List(date1))
        _ <- Ns.duration.a1.query.get.map(_ ==> List(duration1))
        _ <- Ns.instant.a1.query.get.map(_ ==> List(instant1))
        _ <- Ns.localDate.a1.query.get.map(_ ==> List(localDate1))
        _ <- Ns.localTime.a1.query.get.map(_ ==> List(localTime1))
        _ <- Ns.localDateTime.a1.query.get.map(_ ==> List(localDateTime1))
        _ <- Ns.offsetTime.a1.query.get.map(_ ==> List(offsetTime1))
        _ <- Ns.offsetDateTime.a1.query.get.map(_ ==> List(offsetDateTime1))
        _ <- Ns.zonedDateTime.a1.query.get.map(_ ==> List(zonedDateTime1))
        _ <- Ns.uuid.a1.query.get.map(_ ==> List(uuid1))
        _ <- Ns.uri.a1.query.get.map(_ ==> List(uri1))
        _ <- Ns.byte.a1.query.get.map(_ ==> List(byte1))
        _ <- Ns.short.a1.query.get.map(_ ==> List(short1))
        _ <- Ns.char.a1.query.get.map(_ ==> List(char1))

        List(r1, r2) <- Ref.i.insert(1, 2).transact.map(_.ids)
        _ <- Ns.ref.insert(r1).transact
        _ <- Ns.ref.insert(Seq(r2)).transact
        _ <- Ns.ref.a1.query.get.map(_ ==> List(r1, r2))
      } yield ()
    }


    "Optional" - types { implicit conn =>
      for {
        _ <- Ns.i.string_?.insert(1, Some(string1)).transact
        _ <- Ns.i.int_?.insert(1, Some(int1)).transact
        _ <- Ns.i.long_?.insert(1, Some(long1)).transact
        _ <- Ns.i.float_?.insert(1, Some(float1)).transact
        _ <- Ns.i.double_?.insert(1, Some(double1)).transact
        _ <- Ns.i.boolean_?.insert(1, Some(boolean1)).transact
        _ <- Ns.i.bigInt_?.insert(1, Some(bigInt1)).transact
        _ <- Ns.i.bigDecimal_?.insert(1, Some(bigDecimal1)).transact
        _ <- Ns.i.date_?.insert(1, Some(date1)).transact
        _ <- Ns.i.duration_?.insert(1, Some(duration1)).transact
        _ <- Ns.i.instant_?.insert(1, Some(instant1)).transact
        _ <- Ns.i.localDate_?.insert(1, Some(localDate1)).transact
        _ <- Ns.i.localTime_?.insert(1, Some(localTime1)).transact
        _ <- Ns.i.localDateTime_?.insert(1, Some(localDateTime1)).transact
        _ <- Ns.i.offsetTime_?.insert(1, Some(offsetTime1)).transact
        _ <- Ns.i.offsetDateTime_?.insert(1, Some(offsetDateTime1)).transact
        _ <- Ns.i.zonedDateTime_?.insert(1, Some(zonedDateTime1)).transact
        _ <- Ns.i.uuid_?.insert(1, Some(uuid1)).transact
        _ <- Ns.i.uri_?.insert(1, Some(uri1)).transact
        _ <- Ns.i.byte_?.insert(1, Some(byte1)).transact
        _ <- Ns.i.short_?.insert(1, Some(short1)).transact
        _ <- Ns.i.char_?.insert(1, Some(char1)).transact

        _ <- Ns.i.string_?.insert(1, Option.empty[String]).transact
        _ <- Ns.i.int_?.insert(1, Option.empty[Int]).transact
        _ <- Ns.i.long_?.insert(1, Option.empty[Long]).transact
        _ <- Ns.i.float_?.insert(1, Option.empty[Float]).transact
        _ <- Ns.i.double_?.insert(1, Option.empty[Double]).transact
        _ <- Ns.i.boolean_?.insert(1, Option.empty[Boolean]).transact
        _ <- Ns.i.bigInt_?.insert(1, Option.empty[BigInt]).transact
        _ <- Ns.i.bigDecimal_?.insert(1, Option.empty[BigDecimal]).transact
        _ <- Ns.i.date_?.insert(1, Option.empty[Date]).transact
        _ <- Ns.i.duration_?.insert(1, Option.empty[Duration]).transact
        _ <- Ns.i.instant_?.insert(1, Option.empty[Instant]).transact
        _ <- Ns.i.localDate_?.insert(1, Option.empty[LocalDate]).transact
        _ <- Ns.i.localTime_?.insert(1, Option.empty[LocalTime]).transact
        _ <- Ns.i.localDateTime_?.insert(1, Option.empty[LocalDateTime]).transact
        _ <- Ns.i.offsetTime_?.insert(1, Option.empty[OffsetTime]).transact
        _ <- Ns.i.offsetDateTime_?.insert(1, Option.empty[OffsetDateTime]).transact
        _ <- Ns.i.zonedDateTime_?.insert(1, Option.empty[ZonedDateTime]).transact
        _ <- Ns.i.uuid_?.insert(1, Option.empty[UUID]).transact
        _ <- Ns.i.uri_?.insert(1, Option.empty[URI]).transact
        _ <- Ns.i.byte_?.insert(1, Option.empty[Byte]).transact
        _ <- Ns.i.short_?.insert(1, Option.empty[Short]).transact
        _ <- Ns.i.char_?.insert(1, Option.empty[Char]).transact

        _ <- Ns.i_.string_?.a1.query.get.map(_ ==> List(None, Some(string1)))
        _ <- Ns.i_.int_?.a1.query.get.map(_ ==> List(None, Some(int1)))
        _ <- Ns.i_.long_?.a1.query.get.map(_ ==> List(None, Some(long1)))
        _ <- Ns.i_.float_?.a1.query.get.map(_ ==> List(None, Some(float1)))
        _ <- Ns.i_.double_?.a1.query.get.map(_ ==> List(None, Some(double1)))
        _ <- Ns.i_.boolean_?.a1.query.get.map(_ ==> List(None, Some(boolean1)))
        _ <- Ns.i_.bigInt_?.a1.query.get.map(_ ==> List(None, Some(bigInt1)))
        _ <- Ns.i_.bigDecimal_?.a1.query.get.map(_ ==> List(None, Some(bigDecimal1)))
        _ <- Ns.i_.date_?.a1.query.get.map(_ ==> List(None, Some(date1)))
        _ <- Ns.i_.duration_?.a1.query.get.map(_ ==> List(None, Some(duration1)))
        _ <- Ns.i_.instant_?.a1.query.get.map(_ ==> List(None, Some(instant1)))
        _ <- Ns.i_.localDate_?.a1.query.get.map(_ ==> List(None, Some(localDate1)))
        _ <- Ns.i_.localTime_?.a1.query.get.map(_ ==> List(None, Some(localTime1)))
        _ <- Ns.i_.localDateTime_?.a1.query.get.map(_ ==> List(None, Some(localDateTime1)))
        _ <- Ns.i_.offsetTime_?.a1.query.get.map(_ ==> List(None, Some(offsetTime1)))
        _ <- Ns.i_.offsetDateTime_?.a1.query.get.map(_ ==> List(None, Some(offsetDateTime1)))
        _ <- Ns.i_.zonedDateTime_?.a1.query.get.map(_ ==> List(None, Some(zonedDateTime1)))
        _ <- Ns.i_.uuid_?.a1.query.get.map(_ ==> List(None, Some(uuid1)))
        _ <- Ns.i_.uri_?.a1.query.get.map(_ ==> List(None, Some(uri1)))
        _ <- Ns.i_.byte_?.a1.query.get.map(_ ==> List(None, Some(byte1)))
        _ <- Ns.i_.short_?.a1.query.get.map(_ ==> List(None, Some(short1)))
        _ <- Ns.i_.char_?.a1.query.get.map(_ ==> List(None, Some(char1)))

        r1 <- Ref.i.insert(1, 2).transact.map(_.id)
        _ <- Ns.i.ref_?.insert(1, Option.empty[String]).transact
        _ <- Ns.i.ref_?.insert(1, Some(r1)).transact
        _ <- Ns.i_.ref_?.a1.query.get.map(_ ==> List(None, Some(r1)))
      } yield ()
    }


    "Tacit" - types { implicit conn =>
      for {
        // Can't insert tacit attributes
        _ <- Future(compileError("Ns.i.strings_.insert(1, Set(string1))"))
      } yield ()
    }
  }
}
