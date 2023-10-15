package molecule.coreTests.compliance.crud.save

import java.net.URI
import java.time._
import java.util.{Date, UUID}
import molecule.base.error._
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait SaveCardOne extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - types { implicit conn =>
      for {
        // Can't save multiple values (use insert for that)
        _ <- Ns.i(1, 2).save.transact
          .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
            err ==> "Can only save one value for attribute `Ns.i`. Found: 1, 2"
          }

        _ <- Ns.i(Seq(1, 2)).save.transact
          .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
            err ==> "Can only save one value for attribute `Ns.i`. Found: 1, 2"
          }

        // Saving empty list of values is ignored
        _ <- Ns.i(Seq.empty[Int]).save.transact
        _ <- Ns.i.query.get.map(_ ==> List())

        _ <- Ns.string(string1).save.transact
        _ <- Ns.int(int1).save.transact
        _ <- Ns.long(long1).save.transact
        _ <- Ns.float(float1).save.transact
        _ <- Ns.double(double1).save.transact
        _ <- Ns.boolean(boolean1).save.transact
        _ <- Ns.bigInt(bigInt1).save.transact
        _ <- Ns.bigDecimal(bigDecimal1).save.transact
        _ <- Ns.date(date1).save.transact
        _ <- Ns.duration(duration1).save.transact
        _ <- Ns.instant(instant1).save.transact
        _ <- Ns.localDate(localDate1).save.transact
        _ <- Ns.localTime(localTime1).save.transact
        _ <- Ns.localDateTime(localDateTime1).save.transact
        _ <- Ns.offsetTime(offsetTime1).save.transact
        _ <- Ns.offsetDateTime(offsetDateTime1).save.transact
        _ <- Ns.zonedDateTime(zonedDateTime1).save.transact
        _ <- Ns.uuid(uuid1).save.transact
        _ <- Ns.uri(uri1).save.transact
        _ <- Ns.byte(byte1).save.transact
        _ <- Ns.short(short1).save.transact
        _ <- Ns.char(char1).save.transact

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
        _ <- Ns.ref(r1).save.transact
        _ <- Ns.ref(Seq(r2)).save.transact
        _ <- Ns.ref.a1.query.get.map(_ ==> List(r1, r2))
      } yield ()
    }


    "Optional" - types { implicit conn =>
      for {
        // Can't save multiple values (use insert for that)
        _ <- Ns.i_?(Some(Seq(1, 2))).save.transact
          .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
            err ==> "Can only save one value for optional attribute `Ns.i`. Found: 1, 2"
          }

        // Empty option of values saves nothing
        _ <- Ns.i_?(Option.empty[Seq[Int]]).save.transact
        _ <- Ns.i.query.get.map(_ ==> List())

        _ <- Ns.i(1).string_?(Some(string1)).save.transact
        _ <- Ns.i(1).int_?(Some(int1)).save.transact
        _ <- Ns.i(1).long_?(Some(long1)).save.transact
        _ <- Ns.i(1).float_?(Some(float1)).save.transact
        _ <- Ns.i(1).double_?(Some(double1)).save.transact
        _ <- Ns.i(1).boolean_?(Some(boolean1)).save.transact
        _ <- Ns.i(1).bigInt_?(Some(bigInt1)).save.transact
        _ <- Ns.i(1).bigDecimal_?(Some(bigDecimal1)).save.transact
        _ <- Ns.i(1).date_?(Some(date1)).save.transact
        _ <- Ns.i(1).duration_?(Some(duration1)).save.transact
        _ <- Ns.i(1).instant_?(Some(instant1)).save.transact
        _ <- Ns.i(1).localDate_?(Some(localDate1)).save.transact
        _ <- Ns.i(1).localTime_?(Some(localTime1)).save.transact
        _ <- Ns.i(1).localDateTime_?(Some(localDateTime1)).save.transact
        _ <- Ns.i(1).offsetTime_?(Some(offsetTime1)).save.transact
        _ <- Ns.i(1).offsetDateTime_?(Some(offsetDateTime1)).save.transact
        _ <- Ns.i(1).zonedDateTime_?(Some(zonedDateTime1)).save.transact
        _ <- Ns.i(1).uuid_?(Some(uuid1)).save.transact
        _ <- Ns.i(1).uri_?(Some(uri1)).save.transact
        _ <- Ns.i(1).byte_?(Some(byte1)).save.transact
        _ <- Ns.i(1).short_?(Some(short1)).save.transact
        _ <- Ns.i(1).char_?(Some(char1)).save.transact

        _ <- Ns.i(1).string_?(Option.empty[String]).save.transact
        _ <- Ns.i(1).int_?(Option.empty[Int]).save.transact
        _ <- Ns.i(1).long_?(Option.empty[Long]).save.transact
        _ <- Ns.i(1).float_?(Option.empty[Float]).save.transact
        _ <- Ns.i(1).double_?(Option.empty[Double]).save.transact
        _ <- Ns.i(1).boolean_?(Option.empty[Boolean]).save.transact
        _ <- Ns.i(1).bigInt_?(Option.empty[BigInt]).save.transact
        _ <- Ns.i(1).bigDecimal_?(Option.empty[BigDecimal]).save.transact
        _ <- Ns.i(1).date_?(Option.empty[Date]).save.transact
        _ <- Ns.i(1).duration_?(Option.empty[Duration]).save.transact
        _ <- Ns.i(1).instant_?(Option.empty[Instant]).save.transact
        _ <- Ns.i(1).localDate_?(Option.empty[LocalDate]).save.transact
        _ <- Ns.i(1).localTime_?(Option.empty[LocalTime]).save.transact
        _ <- Ns.i(1).localDateTime_?(Option.empty[LocalDateTime]).save.transact
        _ <- Ns.i(1).offsetTime_?(Option.empty[OffsetTime]).save.transact
        _ <- Ns.i(1).offsetDateTime_?(Option.empty[OffsetDateTime]).save.transact
        _ <- Ns.i(1).zonedDateTime_?(Option.empty[ZonedDateTime]).save.transact
        _ <- Ns.i(1).uuid_?(Option.empty[UUID]).save.transact
        _ <- Ns.i(1).uri_?(Option.empty[URI]).save.transact
        _ <- Ns.i(1).byte_?(Option.empty[Byte]).save.transact
        _ <- Ns.i(1).short_?(Option.empty[Short]).save.transact
        _ <- Ns.i(1).char_?(Option.empty[Char]).save.transact

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

        List(r1, r2) <- Ref.i.insert(1, 2).transact.map(_.ids)
        _ <- Ns.i(1).ref_?(Option.empty[Long]).save.transact
        _ <- Ns.i(1).ref_?(Some(r1)).save.transact
        _ <- Ns.i(1).ref_?(Some(Seq(r2))).save.transact
        _ <- Ns.i_.ref_?.a1.query.get.map(_ ==> List(None, Some(r1), Some(r2)))
      } yield ()
    }


    "Tacit" - types { implicit conn =>
      for {
        _ <- Ns.i(1).string_(string1).save.transact
        _ <- Ns.i(1).int_(int1).save.transact
        _ <- Ns.i(1).long_(long1).save.transact
        _ <- Ns.i(1).float_(float1).save.transact
        _ <- Ns.i(1).double_(double1).save.transact
        _ <- Ns.i(1).boolean_(boolean1).save.transact
        _ <- Ns.i(1).bigInt_(bigInt1).save.transact
        _ <- Ns.i(1).bigDecimal_(bigDecimal1).save.transact
        _ <- Ns.i(1).date_(date1).save.transact
        _ <- Ns.i(1).duration_(duration1).save.transact
        _ <- Ns.i(1).instant_(instant1).save.transact
        _ <- Ns.i(1).localDate_(localDate1).save.transact
        _ <- Ns.i(1).localTime_(localTime1).save.transact
        _ <- Ns.i(1).localDateTime_(localDateTime1).save.transact
        _ <- Ns.i(1).offsetTime_(offsetTime1).save.transact
        _ <- Ns.i(1).offsetDateTime_(offsetDateTime1).save.transact
        _ <- Ns.i(1).zonedDateTime_(zonedDateTime1).save.transact
        _ <- Ns.i(1).uuid_(uuid1).save.transact
        _ <- Ns.i(1).uri_(uri1).save.transact
        _ <- Ns.i(1).byte_(byte1).save.transact
        _ <- Ns.i(1).short_(short1).save.transact
        _ <- Ns.i(1).char_(char1).save.transact

        _ <- Ns.i.string.query.get.map(_.head ==> (1, string1))
        _ <- Ns.i.int.query.get.map(_.head ==> (1, int1))
        _ <- Ns.i.long.query.get.map(_.head ==> (1, long1))
        _ <- Ns.i.float.query.get.map(_.head ==> (1, float1))
        _ <- Ns.i.double.query.get.map(_.head ==> (1, double1))
        _ <- Ns.i.boolean.query.get.map(_.head ==> (1, boolean1))
        _ <- Ns.i.bigInt.query.get.map(_.head ==> (1, bigInt1))
        _ <- Ns.i.bigDecimal.query.get.map(_.head ==> (1, bigDecimal1))
        _ <- Ns.i.date.query.get.map(_.head ==> (1, date1))
        _ <- Ns.i.duration.query.get.map(_.head ==> (1, duration1))
        _ <- Ns.i.instant.query.get.map(_.head ==> (1, instant1))
        _ <- Ns.i.localDate.query.get.map(_.head ==> (1, localDate1))
        _ <- Ns.i.localTime.query.get.map(_.head ==> (1, localTime1))
        _ <- Ns.i.localDateTime.query.get.map(_.head ==> (1, localDateTime1))
        _ <- Ns.i.offsetTime.query.get.map(_.head ==> (1, offsetTime1))
        _ <- Ns.i.offsetDateTime.query.get.map(_.head ==> (1, offsetDateTime1))
        _ <- Ns.i.zonedDateTime.query.get.map(_.head ==> (1, zonedDateTime1))
        _ <- Ns.i.uuid.query.get.map(_.head ==> (1, uuid1))
        _ <- Ns.i.uri.query.get.map(_.head ==> (1, uri1))
        _ <- Ns.i.byte.query.get.map(_.head ==> (1, byte1))
        _ <- Ns.i.short.query.get.map(_.head ==> (1, short1))
        _ <- Ns.i.char.query.get.map(_.head ==> (1, char1))

        r1 <- Ref.i(1).save.transact.map(_.id)
        _ <- Ns.i(1).ref_(r1).save.transact
        _ <- Ns.i.ref.query.get.map(_ ==> List((1, r1)))
      } yield ()
    }
  }
}
