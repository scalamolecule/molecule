package molecule.coreTests.spi.crud.save

import java.net.URI
import java.time._
import java.util.{Date, UUID}
import molecule.base.error.ModelError
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._
import scala.concurrent.Future

trait SaveCardMap extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "mandatory" - types { implicit conn =>
      for {
        // Empty values are ignored
        _ <- Ns.intMap.query.get.map(_ ==> List())
        _ <- Ns.intMap(Map.empty[String, Int]).save.transact
        _ <- Ns.intMap.query.get.map(_ ==> List())

        _ <- Ns.stringMap(Map(pstring1, pstring2)).save.transact
        _ <- Ns.intMap(Map(pint1, pint2)).save.transact
        _ <- Ns.longMap(Map(plong1, plong2)).save.transact
        _ <- Ns.floatMap(Map(pfloat1, pfloat2)).save.transact
        _ <- Ns.doubleMap(Map(pdouble1, pdouble2)).save.transact
        _ <- Ns.booleanMap(Map(pboolean1, pboolean2)).save.transact
        _ <- Ns.bigIntMap(Map(pbigInt1, pbigInt2)).save.transact
        _ <- Ns.bigDecimalMap(Map(pbigDecimal1, pbigDecimal2)).save.transact
        _ <- Ns.dateMap(Map(pdate1, pdate2)).save.transact
        _ <- Ns.durationMap(Map(pduration1, pduration2)).save.transact
        _ <- Ns.instantMap(Map(pinstant1, pinstant2)).save.transact
        _ <- Ns.localDateMap(Map(plocalDate1, plocalDate2)).save.transact
        _ <- Ns.localTimeMap(Map(plocalTime1, plocalTime2)).save.transact
        _ <- Ns.localDateTimeMap(Map(plocalDateTime1, plocalDateTime2)).save.transact
        _ <- Ns.offsetTimeMap(Map(poffsetTime1, poffsetTime2)).save.transact
        _ <- Ns.offsetDateTimeMap(Map(poffsetDateTime1, poffsetDateTime2)).save.transact
        _ <- Ns.zonedDateTimeMap(Map(pzonedDateTime1, pzonedDateTime2)).save.transact
        _ <- Ns.uuidMap(Map(puuid1, puuid2)).save.transact
        _ <- Ns.uriMap(Map(puri1, puri2)).save.transact
        _ <- Ns.byteMap(Map(pbyte1, pbyte2)).save.transact
        _ <- Ns.shortMap(Map(pshort1, pshort2)).save.transact
        _ <- Ns.charMap(Map(pchar1, pchar2)).save.transact

        _ <- Ns.stringMap.query.get.map(_.head ==> Map(pstring1, pstring2))
        _ <- Ns.intMap.query.get.map(_.head ==> Map(pint1, pint2))
        _ <- Ns.longMap.query.get.map(_.head ==> Map(plong1, plong2))
        _ <- Ns.floatMap.query.get.map(_.head ==> Map(pfloat1, pfloat2))
        _ <- Ns.doubleMap.query.get.map(_.head ==> Map(pdouble1, pdouble2))
        _ <- Ns.booleanMap.query.get.map(_.head ==> Map(pboolean1, pboolean2))
        _ <- Ns.bigIntMap.query.get.map(_.head ==> Map(pbigInt1, pbigInt2))
        _ <- Ns.bigDecimalMap.query.get.map(_.head ==> Map(pbigDecimal1, pbigDecimal2))
        _ <- Ns.dateMap.query.get.map(_.head ==> Map(pdate1, pdate2))
        _ <- Ns.durationMap.query.get.map(_.head ==> Map(pduration1, pduration2))
        _ <- Ns.instantMap.query.get.map(_.head ==> Map(pinstant1, pinstant2))
        _ <- Ns.localDateMap.query.get.map(_.head ==> Map(plocalDate1, plocalDate2))
        _ <- Ns.localTimeMap.query.get.map(_.head ==> Map(plocalTime1, plocalTime2))
        _ <- Ns.localDateTimeMap.query.get.map(_.head ==> Map(plocalDateTime1, plocalDateTime2))
        _ <- Ns.offsetTimeMap.query.get.map(_.head ==> Map(poffsetTime1, poffsetTime2))
        _ <- Ns.offsetDateTimeMap.query.get.map(_.head ==> Map(poffsetDateTime1, poffsetDateTime2))
        _ <- Ns.zonedDateTimeMap.query.get.map(_.head ==> Map(pzonedDateTime1, pzonedDateTime2))
        _ <- Ns.uuidMap.query.get.map(_.head ==> Map(puuid1, puuid2))
        _ <- Ns.uriMap.query.get.map(_.head ==> Map(puri1, puri2))
        _ <- Ns.byteMap.query.get.map(_.head ==> Map(pbyte1, pbyte2))
        _ <- Ns.shortMap.query.get.map(_.head ==> Map(pshort1, pshort2))
        _ <- Ns.charMap.query.get.map(_.head ==> Map(pchar1, pchar2))
      } yield ()
    }


    "optional" - types { implicit conn =>
      for {
        // Empty option of Array of values saves nothing
        _ <- Ns.intMap_?.apply(Option.empty[Map[String, Int]]).save.transact
        _ <- Ns.intMap.query.get.map(_ ==> List())

        _ <- Ns.int(1).i(1).stringMap_?(Option.empty[Map[String, String]]).save.transact
        _ <- Ns.int(2).i(1).intMap_?(Option.empty[Map[String, Int]]).save.transact
        _ <- Ns.int(3).i(1).longMap_?(Option.empty[Map[String, Long]]).save.transact
        _ <- Ns.int(4).i(1).floatMap_?(Option.empty[Map[String, Float]]).save.transact
        _ <- Ns.int(5).i(1).doubleMap_?(Option.empty[Map[String, Double]]).save.transact
        _ <- Ns.int(6).i(1).booleanMap_?(Option.empty[Map[String, Boolean]]).save.transact
        _ <- Ns.int(7).i(1).bigIntMap_?(Option.empty[Map[String, BigInt]]).save.transact
        _ <- Ns.int(8).i(1).bigDecimalMap_?(Option.empty[Map[String, BigDecimal]]).save.transact
        _ <- Ns.int(9).i(1).dateMap_?(Option.empty[Map[String, Date]]).save.transact
        _ <- Ns.int(10).i(1).durationMap_?(Option.empty[Map[String, Duration]]).save.transact
        _ <- Ns.int(11).i(1).instantMap_?(Option.empty[Map[String, Instant]]).save.transact
        _ <- Ns.int(12).i(1).localDateMap_?(Option.empty[Map[String, LocalDate]]).save.transact
        _ <- Ns.int(13).i(1).localTimeMap_?(Option.empty[Map[String, LocalTime]]).save.transact
        _ <- Ns.int(14).i(1).localDateTimeMap_?(Option.empty[Map[String, LocalDateTime]]).save.transact
        _ <- Ns.int(15).i(1).offsetTimeMap_?(Option.empty[Map[String, OffsetTime]]).save.transact
        _ <- Ns.int(16).i(1).offsetDateTimeMap_?(Option.empty[Map[String, OffsetDateTime]]).save.transact
        _ <- Ns.int(17).i(1).zonedDateTimeMap_?(Option.empty[Map[String, ZonedDateTime]]).save.transact
        _ <- Ns.int(18).i(1).uuidMap_?(Option.empty[Map[String, UUID]]).save.transact
        _ <- Ns.int(19).i(1).uriMap_?(Option.empty[Map[String, URI]]).save.transact
        _ <- Ns.int(20).i(1).byteMap_?(Option.empty[Map[String, Byte]]).save.transact
        _ <- Ns.int(21).i(1).shortMap_?(Option.empty[Map[String, Short]]).save.transact
        _ <- Ns.int(22).i(1).charMap_?(Option.empty[Map[String, Char]]).save.transact

        _ <- Ns.int(1).i(2).stringMap_?(Some(Map.empty[String, String])).save.transact
        _ <- Ns.int(2).i(2).intMap_?(Some(Map.empty[String, Int])).save.transact
        _ <- Ns.int(3).i(2).longMap_?(Some(Map.empty[String, Long])).save.transact
        _ <- Ns.int(4).i(2).floatMap_?(Some(Map.empty[String, Float])).save.transact
        _ <- Ns.int(5).i(2).doubleMap_?(Some(Map.empty[String, Double])).save.transact
        _ <- Ns.int(6).i(2).booleanMap_?(Some(Map.empty[String, Boolean])).save.transact
        _ <- Ns.int(7).i(2).bigIntMap_?(Some(Map.empty[String, BigInt])).save.transact
        _ <- Ns.int(8).i(2).bigDecimalMap_?(Some(Map.empty[String, BigDecimal])).save.transact
        _ <- Ns.int(9).i(2).dateMap_?(Some(Map.empty[String, Date])).save.transact
        _ <- Ns.int(10).i(2).durationMap_?(Some(Map.empty[String, Duration])).save.transact
        _ <- Ns.int(11).i(2).instantMap_?(Some(Map.empty[String, Instant])).save.transact
        _ <- Ns.int(12).i(2).localDateMap_?(Some(Map.empty[String, LocalDate])).save.transact
        _ <- Ns.int(13).i(2).localTimeMap_?(Some(Map.empty[String, LocalTime])).save.transact
        _ <- Ns.int(14).i(2).localDateTimeMap_?(Some(Map.empty[String, LocalDateTime])).save.transact
        _ <- Ns.int(15).i(2).offsetTimeMap_?(Some(Map.empty[String, OffsetTime])).save.transact
        _ <- Ns.int(16).i(2).offsetDateTimeMap_?(Some(Map.empty[String, OffsetDateTime])).save.transact
        _ <- Ns.int(17).i(2).zonedDateTimeMap_?(Some(Map.empty[String, ZonedDateTime])).save.transact
        _ <- Ns.int(18).i(2).uuidMap_?(Some(Map.empty[String, UUID])).save.transact
        _ <- Ns.int(19).i(2).uriMap_?(Some(Map.empty[String, URI])).save.transact
        _ <- Ns.int(20).i(2).byteMap_?(Some(Map.empty[String, Byte])).save.transact
        _ <- Ns.int(21).i(2).shortMap_?(Some(Map.empty[String, Short])).save.transact
        _ <- Ns.int(22).i(2).charMap_?(Some(Map.empty[String, Char])).save.transact

        _ <- Ns.int(1).i(3).stringMap_?(Some(Map(pstring1, pstring2))).save.transact
        _ <- Ns.int(2).i(3).intMap_?(Some(Map(pint1, pint2))).save.transact
        _ <- Ns.int(3).i(3).longMap_?(Some(Map(plong1, plong2))).save.transact
        _ <- Ns.int(4).i(3).floatMap_?(Some(Map(pfloat1, pfloat2))).save.transact
        _ <- Ns.int(5).i(3).doubleMap_?(Some(Map(pdouble1, pdouble2))).save.transact
        _ <- Ns.int(6).i(3).booleanMap_?(Some(Map(pboolean1, pboolean2))).save.transact
        _ <- Ns.int(7).i(3).bigIntMap_?(Some(Map(pbigInt1, pbigInt2))).save.transact
        _ <- Ns.int(8).i(3).bigDecimalMap_?(Some(Map(pbigDecimal1, pbigDecimal2))).save.transact
        _ <- Ns.int(9).i(3).dateMap_?(Some(Map(pdate1, pdate2))).save.transact
        _ <- Ns.int(10).i(3).durationMap_?(Some(Map(pduration1, pduration2))).save.transact
        _ <- Ns.int(11).i(3).instantMap_?(Some(Map(pinstant1, pinstant2))).save.transact
        _ <- Ns.int(12).i(3).localDateMap_?(Some(Map(plocalDate1, plocalDate2))).save.transact
        _ <- Ns.int(13).i(3).localTimeMap_?(Some(Map(plocalTime1, plocalTime2))).save.transact
        _ <- Ns.int(14).i(3).localDateTimeMap_?(Some(Map(plocalDateTime1, plocalDateTime2))).save.transact
        _ <- Ns.int(15).i(3).offsetTimeMap_?(Some(Map(poffsetTime1, poffsetTime2))).save.transact
        _ <- Ns.int(16).i(3).offsetDateTimeMap_?(Some(Map(poffsetDateTime1, poffsetDateTime2))).save.transact
        _ <- Ns.int(17).i(3).zonedDateTimeMap_?(Some(Map(pzonedDateTime1, pzonedDateTime2))).save.transact
        _ <- Ns.int(18).i(3).uuidMap_?(Some(Map(puuid1, puuid2))).save.transact
        _ <- Ns.int(19).i(3).uriMap_?(Some(Map(puri1, puri2))).save.transact
        _ <- Ns.int(20).i(3).byteMap_?(Some(Map(pbyte1, pbyte2))).save.transact
        _ <- Ns.int(21).i(3).shortMap_?(Some(Map(pshort1, pshort2))).save.transact
        _ <- Ns.int(22).i(3).charMap_?(Some(Map(pchar1, pchar2))).save.transact

        _ <- Ns.int_(1).i.a1.stringMap_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Map(pstring1, pstring2)))))
        _ <- Ns.int_(2).i.a1.intMap_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Map(pint1, pint2)))))
        _ <- Ns.int_(3).i.a1.longMap_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Map(plong1, plong2)))))
        _ <- Ns.int_(4).i.a1.floatMap_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Map(pfloat1, pfloat2)))))
        _ <- Ns.int_(5).i.a1.doubleMap_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Map(pdouble1, pdouble2)))))
        _ <- Ns.int_(6).i.a1.booleanMap_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Map(pboolean1, pboolean2)))))
        _ <- Ns.int_(7).i.a1.bigIntMap_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Map(pbigInt1, pbigInt2)))))
        _ <- Ns.int_(8).i.a1.bigDecimalMap_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Map(pbigDecimal1, pbigDecimal2)))))
        _ <- Ns.int_(9).i.a1.dateMap_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Map(pdate1, pdate2)))))
        _ <- Ns.int_(10).i.a1.durationMap_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Map(pduration1, pduration2)))))
        _ <- Ns.int_(11).i.a1.instantMap_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Map(pinstant1, pinstant2)))))
        _ <- Ns.int_(12).i.a1.localDateMap_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Map(plocalDate1, plocalDate2)))))
        _ <- Ns.int_(13).i.a1.localTimeMap_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Map(plocalTime1, plocalTime2)))))
        _ <- Ns.int_(14).i.a1.localDateTimeMap_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Map(plocalDateTime1, plocalDateTime2)))))
        _ <- Ns.int_(15).i.a1.offsetTimeMap_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Map(poffsetTime1, poffsetTime2)))))
        _ <- Ns.int_(16).i.a1.offsetDateTimeMap_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Map(poffsetDateTime1, poffsetDateTime2)))))
        _ <- Ns.int_(17).i.a1.zonedDateTimeMap_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Map(pzonedDateTime1, pzonedDateTime2)))))
        _ <- Ns.int_(18).i.a1.uuidMap_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Map(puuid1, puuid2)))))
        _ <- Ns.int_(19).i.a1.uriMap_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Map(puri1, puri2)))))
        _ <- Ns.int_(20).i.a1.byteMap_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Map(pbyte1, pbyte2)))))
        _ <- Ns.int_(21).i.a1.shortMap_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Map(pshort1, pshort2)))))
        _ <- Ns.int_(22).i.a1.charMap_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Map(pchar1, pchar2)))))
      } yield ()
    }


    "Tacit" - types { implicit conn =>
      for {
        // Can't save map with tacit map attribute
        _ <- Future(compileError("Ns.i(1).stringMap_(Map(pstring1, pstring2)).save"))
      } yield ()
    }


    "Valid keys" - types { implicit conn =>
      for {
        // Allowed characters in a key name
        _ <- Ns.intMap(Map("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_0123456789" -> 1)).save.transact

        // No spaces
        _ <- Ns.intMap(Map("foo bar" -> 1)).save.transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Keys of map attributes can only contain [a-zA-Z_0-9] (no spaces or special characters)."
          }

        // No special characters
        _ <- Ns.intMap(Map("foo:" -> 1)).save.transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Keys of map attributes can only contain [a-zA-Z_0-9] (no spaces or special characters)."
          }
      } yield ()
    }
  }
}
