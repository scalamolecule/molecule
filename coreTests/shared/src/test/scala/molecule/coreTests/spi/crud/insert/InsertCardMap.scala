package molecule.coreTests.spi.crud.insert

import java.net.URI
import java.time._
import java.util.{Date, UUID}
import molecule.base.error.ModelError
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._
import scala.concurrent.Future

trait InsertCardMap extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - types { implicit conn =>
      for {
        // No Ns.intMap inserted yet
        _ <- Ns.intMap.query.get.map(_ ==> Nil)

        // Inserting empty map of values is ignored
        // (See InsertSemantic for further details)
        _ <- Ns.intMap.insert(Map.empty[String, Int]).transact
        _ <- Ns.intMap.query.get.map(_ ==> List())

        _ <- Ns.i.stringMap.insert(1, Map(pstring1, pstring2)).transact
        _ <- Ns.i.intMap.insert(1, Map(pint1, pint2)).transact
        _ <- Ns.i.longMap.insert(1, Map(plong1, plong2)).transact
        _ <- Ns.i.floatMap.insert(1, Map(pfloat1, pfloat2)).transact
        _ <- Ns.i.doubleMap.insert(1, Map(pdouble1, pdouble2)).transact
        _ <- Ns.i.booleanMap.insert(1, Map(pboolean1, pboolean2)).transact
        _ <- Ns.i.bigIntMap.insert(1, Map(pbigInt1, pbigInt2)).transact
        _ <- Ns.i.bigDecimalMap.insert(1, Map(pbigDecimal1, pbigDecimal2)).transact
        _ <- Ns.i.dateMap.insert(1, Map(pdate1, pdate2)).transact
        _ <- Ns.i.durationMap.insert(1, Map(pduration1, pduration2)).transact
        _ <- Ns.i.instantMap.insert(1, Map(pinstant1, pinstant2)).transact
        _ <- Ns.i.localDateMap.insert(1, Map(plocalDate1, plocalDate2)).transact
        _ <- Ns.i.localTimeMap.insert(1, Map(plocalTime1, plocalTime2)).transact
        _ <- Ns.i.localDateTimeMap.insert(1, Map(plocalDateTime1, plocalDateTime2)).transact
        _ <- Ns.i.offsetTimeMap.insert(1, Map(poffsetTime1, poffsetTime2)).transact
        _ <- Ns.i.offsetDateTimeMap.insert(1, Map(poffsetDateTime1, poffsetDateTime2)).transact
        _ <- Ns.i.zonedDateTimeMap.insert(1, Map(pzonedDateTime1, pzonedDateTime2)).transact
        _ <- Ns.i.uuidMap.insert(1, Map(puuid1, puuid2)).transact
        _ <- Ns.i.uriMap.insert(1, Map(puri1, puri2)).transact
        _ <- Ns.i.byteMap.insert(1, Map(pbyte1, pbyte2)).transact
        _ <- Ns.i.shortMap.insert(1, Map(pshort1, pshort2)).transact
        _ <- Ns.i.charMap.insert(1, Map(pchar1, pchar2)).transact

        _ <- Ns.i.stringMap.query.get.map(_ ==> List((1, Map(pstring1, pstring2))))
        _ <- Ns.i.intMap.query.get.map(_ ==> List((1, Map(pint1, pint2))))
        _ <- Ns.i.longMap.query.get.map(_ ==> List((1, Map(plong1, plong2))))
        _ <- Ns.i.floatMap.query.get.map(_ ==> List((1, Map(pfloat1, pfloat2))))
        _ <- Ns.i.doubleMap.query.get.map(_ ==> List((1, Map(pdouble1, pdouble2))))
        _ <- Ns.i.booleanMap.query.get.map(_ ==> List((1, Map(pboolean1, pboolean2))))
        _ <- Ns.i.bigIntMap.query.get.map(_ ==> List((1, Map(pbigInt1, pbigInt2))))
        _ <- Ns.i.bigDecimalMap.query.get.map(_ ==> List((1, Map(pbigDecimal1, pbigDecimal2))))
        _ <- Ns.i.dateMap.query.get.map(_ ==> List((1, Map(pdate1, pdate2))))
        _ <- Ns.i.durationMap.query.get.map(_ ==> List((1, Map(pduration1, pduration2))))
        _ <- Ns.i.instantMap.query.get.map(_ ==> List((1, Map(pinstant1, pinstant2))))
        _ <- Ns.i.localDateMap.query.get.map(_ ==> List((1, Map(plocalDate1, plocalDate2))))
        _ <- Ns.i.localTimeMap.query.get.map(_ ==> List((1, Map(plocalTime1, plocalTime2))))
        _ <- Ns.i.localDateTimeMap.query.get.map(_ ==> List((1, Map(plocalDateTime1, plocalDateTime2))))
        _ <- Ns.i.offsetTimeMap.query.get.map(_ ==> List((1, Map(poffsetTime1, poffsetTime2))))
        _ <- Ns.i.offsetDateTimeMap.query.get.map(_ ==> List((1, Map(poffsetDateTime1, poffsetDateTime2))))
        _ <- Ns.i.zonedDateTimeMap.query.get.map(_ ==> List((1, Map(pzonedDateTime1, pzonedDateTime2))))
        _ <- Ns.i.uuidMap.query.get.map(_ ==> List((1, Map(puuid1, puuid2))))
        _ <- Ns.i.uriMap.query.get.map(_ ==> List((1, Map(puri1, puri2))))
        _ <- Ns.i.byteMap.query.get.map(_ ==> List((1, Map(pbyte1, pbyte2))))
        _ <- Ns.i.shortMap.query.get.map(_ ==> List((1, Map(pshort1, pshort2))))
        _ <- Ns.i.charMap.query.get.map(_ ==> List((1, Map(pchar1, pchar2))))
      } yield ()
    }


    "Optional" - types { implicit conn =>
      for {
        _ <- Ns.int.i.stringMap_?.insert(1, 1, Option.empty[Map[String, String]]).transact
        _ <- Ns.int.i.intMap_?.insert(2, 1, Option.empty[Map[String, Int]]).transact
        _ <- Ns.int.i.longMap_?.insert(3, 1, Option.empty[Map[String, Long]]).transact
        _ <- Ns.int.i.floatMap_?.insert(4, 1, Option.empty[Map[String, Float]]).transact
        _ <- Ns.int.i.doubleMap_?.insert(5, 1, Option.empty[Map[String, Double]]).transact
        _ <- Ns.int.i.booleanMap_?.insert(6, 1, Option.empty[Map[String, Boolean]]).transact
        _ <- Ns.int.i.bigIntMap_?.insert(7, 1, Option.empty[Map[String, BigInt]]).transact
        _ <- Ns.int.i.bigDecimalMap_?.insert(8, 1, Option.empty[Map[String, BigDecimal]]).transact
        _ <- Ns.int.i.dateMap_?.insert(9, 1, Option.empty[Map[String, Date]]).transact
        _ <- Ns.int.i.durationMap_?.insert(10, 1, Option.empty[Map[String, Duration]]).transact
        _ <- Ns.int.i.instantMap_?.insert(11, 1, Option.empty[Map[String, Instant]]).transact
        _ <- Ns.int.i.localDateMap_?.insert(12, 1, Option.empty[Map[String, LocalDate]]).transact
        _ <- Ns.int.i.localTimeMap_?.insert(13, 1, Option.empty[Map[String, LocalTime]]).transact
        _ <- Ns.int.i.localDateTimeMap_?.insert(14, 1, Option.empty[Map[String, LocalDateTime]]).transact
        _ <- Ns.int.i.offsetTimeMap_?.insert(15, 1, Option.empty[Map[String, OffsetTime]]).transact
        _ <- Ns.int.i.offsetDateTimeMap_?.insert(16, 1, Option.empty[Map[String, OffsetDateTime]]).transact
        _ <- Ns.int.i.zonedDateTimeMap_?.insert(17, 1, Option.empty[Map[String, ZonedDateTime]]).transact
        _ <- Ns.int.i.uuidMap_?.insert(18, 1, Option.empty[Map[String, UUID]]).transact
        _ <- Ns.int.i.uriMap_?.insert(19, 1, Option.empty[Map[String, URI]]).transact
        _ <- Ns.int.i.byteMap_?.insert(20, 1, Option.empty[Map[String, Byte]]).transact
        _ <- Ns.int.i.shortMap_?.insert(21, 1, Option.empty[Map[String, Short]]).transact
        _ <- Ns.int.i.charMap_?.insert(22, 1, Option.empty[Map[String, Char]]).transact

        _ <- Ns.int.i.stringMap_?.insert(1, 2, Some(Map.empty[String, String])).transact
        _ <- Ns.int.i.intMap_?.insert(2, 2, Some(Map.empty[String, Int])).transact
        _ <- Ns.int.i.longMap_?.insert(3, 2, Some(Map.empty[String, Long])).transact
        _ <- Ns.int.i.floatMap_?.insert(4, 2, Some(Map.empty[String, Float])).transact
        _ <- Ns.int.i.doubleMap_?.insert(5, 2, Some(Map.empty[String, Double])).transact
        _ <- Ns.int.i.booleanMap_?.insert(6, 2, Some(Map.empty[String, Boolean])).transact
        _ <- Ns.int.i.bigIntMap_?.insert(7, 2, Some(Map.empty[String, BigInt])).transact
        _ <- Ns.int.i.bigDecimalMap_?.insert(8, 2, Some(Map.empty[String, BigDecimal])).transact
        _ <- Ns.int.i.dateMap_?.insert(9, 2, Some(Map.empty[String, Date])).transact
        _ <- Ns.int.i.durationMap_?.insert(10, 2, Some(Map.empty[String, Duration])).transact
        _ <- Ns.int.i.instantMap_?.insert(11, 2, Some(Map.empty[String, Instant])).transact
        _ <- Ns.int.i.localDateMap_?.insert(12, 2, Some(Map.empty[String, LocalDate])).transact
        _ <- Ns.int.i.localTimeMap_?.insert(13, 2, Some(Map.empty[String, LocalTime])).transact
        _ <- Ns.int.i.localDateTimeMap_?.insert(14, 2, Some(Map.empty[String, LocalDateTime])).transact
        _ <- Ns.int.i.offsetTimeMap_?.insert(15, 2, Some(Map.empty[String, OffsetTime])).transact
        _ <- Ns.int.i.offsetDateTimeMap_?.insert(16, 2, Some(Map.empty[String, OffsetDateTime])).transact
        _ <- Ns.int.i.zonedDateTimeMap_?.insert(17, 2, Some(Map.empty[String, ZonedDateTime])).transact
        _ <- Ns.int.i.uuidMap_?.insert(18, 2, Some(Map.empty[String, UUID])).transact
        _ <- Ns.int.i.uriMap_?.insert(19, 2, Some(Map.empty[String, URI])).transact
        _ <- Ns.int.i.byteMap_?.insert(20, 2, Some(Map.empty[String, Byte])).transact
        _ <- Ns.int.i.shortMap_?.insert(21, 2, Some(Map.empty[String, Short])).transact
        _ <- Ns.int.i.charMap_?.insert(22, 2, Some(Map.empty[String, Char])).transact

        _ <- Ns.int.i.stringMap_?.insert(1, 3, Some(Map(pstring1, pstring2))).transact
        _ <- Ns.int.i.intMap_?.insert(2, 3, Some(Map(pint1, pint2))).transact
        _ <- Ns.int.i.longMap_?.insert(3, 3, Some(Map(plong1, plong2))).transact
        _ <- Ns.int.i.floatMap_?.insert(4, 3, Some(Map(pfloat1, pfloat2))).transact
        _ <- Ns.int.i.doubleMap_?.insert(5, 3, Some(Map(pdouble1, pdouble2))).transact
        _ <- Ns.int.i.booleanMap_?.insert(6, 3, Some(Map(pboolean1, pboolean2))).transact
        _ <- Ns.int.i.bigIntMap_?.insert(7, 3, Some(Map(pbigInt1, pbigInt2))).transact
        _ <- Ns.int.i.bigDecimalMap_?.insert(8, 3, Some(Map(pbigDecimal1, pbigDecimal2))).transact
        _ <- Ns.int.i.dateMap_?.insert(9, 3, Some(Map(pdate1, pdate2))).transact
        _ <- Ns.int.i.durationMap_?.insert(10, 3, Some(Map(pduration1, pduration2))).transact
        _ <- Ns.int.i.instantMap_?.insert(11, 3, Some(Map(pinstant1, pinstant2))).transact
        _ <- Ns.int.i.localDateMap_?.insert(12, 3, Some(Map(plocalDate1, plocalDate2))).transact
        _ <- Ns.int.i.localTimeMap_?.insert(13, 3, Some(Map(plocalTime1, plocalTime2))).transact
        _ <- Ns.int.i.localDateTimeMap_?.insert(14, 3, Some(Map(plocalDateTime1, plocalDateTime2))).transact
        _ <- Ns.int.i.offsetTimeMap_?.insert(15, 3, Some(Map(poffsetTime1, poffsetTime2))).transact
        _ <- Ns.int.i.offsetDateTimeMap_?.insert(16, 3, Some(Map(poffsetDateTime1, poffsetDateTime2))).transact
        _ <- Ns.int.i.zonedDateTimeMap_?.insert(17, 3, Some(Map(pzonedDateTime1, pzonedDateTime2))).transact
        _ <- Ns.int.i.uuidMap_?.insert(18, 3, Some(Map(puuid1, puuid2))).transact
        _ <- Ns.int.i.uriMap_?.insert(19, 3, Some(Map(puri1, puri2))).transact
        _ <- Ns.int.i.byteMap_?.insert(20, 3, Some(Map(pbyte1, pbyte2))).transact
        _ <- Ns.int.i.shortMap_?.insert(21, 3, Some(Map(pshort1, pshort2))).transact
        _ <- Ns.int.i.charMap_?.insert(22, 3, Some(Map(pchar1, pchar2))).transact

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
        // Can't insert tacit attributes
        _ <- Future(compileError("Ns.i.stringMap_.insert(1, Map(pstring1))"))
      } yield ()
    }


    "Valid keys" - types { implicit conn =>
      for {
        // Allowed characters in a key name
        _ <- Ns.intMap.insert(Map("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_0123456789" -> 1)).transact

        // No spaces
        _ <- Ns.intMap.insert(Map("foo bar" -> 1)).transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Keys of map attributes can only contain [a-zA-Z_\\-0-9] (no spaces or special characters)."
          }

        // No special characters
        _ <- Ns.intMap.insert(Map("foo:" -> 1)).transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Keys of map attributes can only contain [a-zA-Z_\\-0-9] (no spaces or special characters)."
          }
      } yield ()
    }
  }
}
