package molecule.coreTests.spi.action.insert

import java.net.URI
import java.time._
import java.util.{Date, UUID}
import molecule.base.error.ModelError
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.domains.dsl.Types._
import molecule.coreTests.setup._
import scala.concurrent.Future

case class InsertCardMap(
  suite: Test,
  api: Api_async with Spi_async with DbProviders
) extends TestUtils {

  import api._
  import suite._

  "Mandatory" - types { implicit conn =>
    for {
      // No Entity.intMap inserted yet
      _ <- Entity.intMap.query.get.map(_ ==> Nil)

      // Inserting empty map of values is ignored
      // (See InsertSemantic for further details)
      _ <- Entity.intMap.insert(Map.empty[String, Int]).transact
      _ <- Entity.intMap.query.get.map(_ ==> List())

      _ <- Entity.i.stringMap.insert(1, Map(pstring1, pstring2)).transact
      _ <- Entity.i.intMap.insert(1, Map(pint1, pint2)).transact
      _ <- Entity.i.longMap.insert(1, Map(plong1, plong2)).transact
      _ <- Entity.i.floatMap.insert(1, Map(pfloat1, pfloat2)).transact
      _ <- Entity.i.doubleMap.insert(1, Map(pdouble1, pdouble2)).transact
      _ <- Entity.i.booleanMap.insert(1, Map(pboolean1, pboolean2)).transact
      _ <- Entity.i.bigIntMap.insert(1, Map(pbigInt1, pbigInt2)).transact
      _ <- Entity.i.bigDecimalMap.insert(1, Map(pbigDecimal1, pbigDecimal2)).transact
      _ <- Entity.i.dateMap.insert(1, Map(pdate1, pdate2)).transact
      _ <- Entity.i.durationMap.insert(1, Map(pduration1, pduration2)).transact
      _ <- Entity.i.instantMap.insert(1, Map(pinstant1, pinstant2)).transact
      _ <- Entity.i.localDateMap.insert(1, Map(plocalDate1, plocalDate2)).transact
      _ <- Entity.i.localTimeMap.insert(1, Map(plocalTime1, plocalTime2)).transact
      _ <- Entity.i.localDateTimeMap.insert(1, Map(plocalDateTime1, plocalDateTime2)).transact
      _ <- Entity.i.offsetTimeMap.insert(1, Map(poffsetTime1, poffsetTime2)).transact
      _ <- Entity.i.offsetDateTimeMap.insert(1, Map(poffsetDateTime1, poffsetDateTime2)).transact
      _ <- Entity.i.zonedDateTimeMap.insert(1, Map(pzonedDateTime1, pzonedDateTime2)).transact
      _ <- Entity.i.uuidMap.insert(1, Map(puuid1, puuid2)).transact
      _ <- Entity.i.uriMap.insert(1, Map(puri1, puri2)).transact
      _ <- Entity.i.byteMap.insert(1, Map(pbyte1, pbyte2)).transact
      _ <- Entity.i.shortMap.insert(1, Map(pshort1, pshort2)).transact
      _ <- Entity.i.charMap.insert(1, Map(pchar1, pchar2)).transact

      _ <- Entity.i.stringMap.query.get.map(_ ==> List((1, Map(pstring1, pstring2))))
      _ <- Entity.i.intMap.query.get.map(_ ==> List((1, Map(pint1, pint2))))
      _ <- Entity.i.longMap.query.get.map(_ ==> List((1, Map(plong1, plong2))))
      _ <- Entity.i.floatMap.query.get.map(_ ==> List((1, Map(pfloat1, pfloat2))))
      _ <- Entity.i.doubleMap.query.get.map(_ ==> List((1, Map(pdouble1, pdouble2))))
      _ <- Entity.i.booleanMap.query.get.map(_ ==> List((1, Map(pboolean1, pboolean2))))
      _ <- Entity.i.bigIntMap.query.get.map(_ ==> List((1, Map(pbigInt1, pbigInt2))))
      _ <- Entity.i.bigDecimalMap.query.get.map(_ ==> List((1, Map(pbigDecimal1, pbigDecimal2))))
      _ <- Entity.i.dateMap.query.get.map(_ ==> List((1, Map(pdate1, pdate2))))
      _ <- Entity.i.durationMap.query.get.map(_ ==> List((1, Map(pduration1, pduration2))))
      _ <- Entity.i.instantMap.query.get.map(_ ==> List((1, Map(pinstant1, pinstant2))))
      _ <- Entity.i.localDateMap.query.get.map(_ ==> List((1, Map(plocalDate1, plocalDate2))))
      _ <- Entity.i.localTimeMap.query.get.map(_ ==> List((1, Map(plocalTime1, plocalTime2))))
      _ <- Entity.i.localDateTimeMap.query.get.map(_ ==> List((1, Map(plocalDateTime1, plocalDateTime2))))
      _ <- Entity.i.offsetTimeMap.query.get.map(_ ==> List((1, Map(poffsetTime1, poffsetTime2))))
      _ <- Entity.i.offsetDateTimeMap.query.get.map(_ ==> List((1, Map(poffsetDateTime1, poffsetDateTime2))))
      _ <- Entity.i.zonedDateTimeMap.query.get.map(_ ==> List((1, Map(pzonedDateTime1, pzonedDateTime2))))
      _ <- Entity.i.uuidMap.query.get.map(_ ==> List((1, Map(puuid1, puuid2))))
      _ <- Entity.i.uriMap.query.get.map(_ ==> List((1, Map(puri1, puri2))))
      _ <- Entity.i.byteMap.query.get.map(_ ==> List((1, Map(pbyte1, pbyte2))))
      _ <- Entity.i.shortMap.query.get.map(_ ==> List((1, Map(pshort1, pshort2))))
      _ <- Entity.i.charMap.query.get.map(_ ==> List((1, Map(pchar1, pchar2))))
    } yield ()
  }


  "Optional" - types { implicit conn =>
    for {
      _ <- Entity.int.i.stringMap_?.insert(1, 1, Option.empty[Map[String, String]]).transact
      _ <- Entity.int.i.intMap_?.insert(2, 1, Option.empty[Map[String, Int]]).transact
      _ <- Entity.int.i.longMap_?.insert(3, 1, Option.empty[Map[String, Long]]).transact
      _ <- Entity.int.i.floatMap_?.insert(4, 1, Option.empty[Map[String, Float]]).transact
      _ <- Entity.int.i.doubleMap_?.insert(5, 1, Option.empty[Map[String, Double]]).transact
      _ <- Entity.int.i.booleanMap_?.insert(6, 1, Option.empty[Map[String, Boolean]]).transact
      _ <- Entity.int.i.bigIntMap_?.insert(7, 1, Option.empty[Map[String, BigInt]]).transact
      _ <- Entity.int.i.bigDecimalMap_?.insert(8, 1, Option.empty[Map[String, BigDecimal]]).transact
      _ <- Entity.int.i.dateMap_?.insert(9, 1, Option.empty[Map[String, Date]]).transact
      _ <- Entity.int.i.durationMap_?.insert(10, 1, Option.empty[Map[String, Duration]]).transact
      _ <- Entity.int.i.instantMap_?.insert(11, 1, Option.empty[Map[String, Instant]]).transact
      _ <- Entity.int.i.localDateMap_?.insert(12, 1, Option.empty[Map[String, LocalDate]]).transact
      _ <- Entity.int.i.localTimeMap_?.insert(13, 1, Option.empty[Map[String, LocalTime]]).transact
      _ <- Entity.int.i.localDateTimeMap_?.insert(14, 1, Option.empty[Map[String, LocalDateTime]]).transact
      _ <- Entity.int.i.offsetTimeMap_?.insert(15, 1, Option.empty[Map[String, OffsetTime]]).transact
      _ <- Entity.int.i.offsetDateTimeMap_?.insert(16, 1, Option.empty[Map[String, OffsetDateTime]]).transact
      _ <- Entity.int.i.zonedDateTimeMap_?.insert(17, 1, Option.empty[Map[String, ZonedDateTime]]).transact
      _ <- Entity.int.i.uuidMap_?.insert(18, 1, Option.empty[Map[String, UUID]]).transact
      _ <- Entity.int.i.uriMap_?.insert(19, 1, Option.empty[Map[String, URI]]).transact
      _ <- Entity.int.i.byteMap_?.insert(20, 1, Option.empty[Map[String, Byte]]).transact
      _ <- Entity.int.i.shortMap_?.insert(21, 1, Option.empty[Map[String, Short]]).transact
      _ <- Entity.int.i.charMap_?.insert(22, 1, Option.empty[Map[String, Char]]).transact

      _ <- Entity.int.i.stringMap_?.insert(1, 2, Some(Map.empty[String, String])).transact
      _ <- Entity.int.i.intMap_?.insert(2, 2, Some(Map.empty[String, Int])).transact
      _ <- Entity.int.i.longMap_?.insert(3, 2, Some(Map.empty[String, Long])).transact
      _ <- Entity.int.i.floatMap_?.insert(4, 2, Some(Map.empty[String, Float])).transact
      _ <- Entity.int.i.doubleMap_?.insert(5, 2, Some(Map.empty[String, Double])).transact
      _ <- Entity.int.i.booleanMap_?.insert(6, 2, Some(Map.empty[String, Boolean])).transact
      _ <- Entity.int.i.bigIntMap_?.insert(7, 2, Some(Map.empty[String, BigInt])).transact
      _ <- Entity.int.i.bigDecimalMap_?.insert(8, 2, Some(Map.empty[String, BigDecimal])).transact
      _ <- Entity.int.i.dateMap_?.insert(9, 2, Some(Map.empty[String, Date])).transact
      _ <- Entity.int.i.durationMap_?.insert(10, 2, Some(Map.empty[String, Duration])).transact
      _ <- Entity.int.i.instantMap_?.insert(11, 2, Some(Map.empty[String, Instant])).transact
      _ <- Entity.int.i.localDateMap_?.insert(12, 2, Some(Map.empty[String, LocalDate])).transact
      _ <- Entity.int.i.localTimeMap_?.insert(13, 2, Some(Map.empty[String, LocalTime])).transact
      _ <- Entity.int.i.localDateTimeMap_?.insert(14, 2, Some(Map.empty[String, LocalDateTime])).transact
      _ <- Entity.int.i.offsetTimeMap_?.insert(15, 2, Some(Map.empty[String, OffsetTime])).transact
      _ <- Entity.int.i.offsetDateTimeMap_?.insert(16, 2, Some(Map.empty[String, OffsetDateTime])).transact
      _ <- Entity.int.i.zonedDateTimeMap_?.insert(17, 2, Some(Map.empty[String, ZonedDateTime])).transact
      _ <- Entity.int.i.uuidMap_?.insert(18, 2, Some(Map.empty[String, UUID])).transact
      _ <- Entity.int.i.uriMap_?.insert(19, 2, Some(Map.empty[String, URI])).transact
      _ <- Entity.int.i.byteMap_?.insert(20, 2, Some(Map.empty[String, Byte])).transact
      _ <- Entity.int.i.shortMap_?.insert(21, 2, Some(Map.empty[String, Short])).transact
      _ <- Entity.int.i.charMap_?.insert(22, 2, Some(Map.empty[String, Char])).transact

      _ <- Entity.int.i.stringMap_?.insert(1, 3, Some(Map(pstring1, pstring2))).transact
      _ <- Entity.int.i.intMap_?.insert(2, 3, Some(Map(pint1, pint2))).transact
      _ <- Entity.int.i.longMap_?.insert(3, 3, Some(Map(plong1, plong2))).transact
      _ <- Entity.int.i.floatMap_?.insert(4, 3, Some(Map(pfloat1, pfloat2))).transact
      _ <- Entity.int.i.doubleMap_?.insert(5, 3, Some(Map(pdouble1, pdouble2))).transact
      _ <- Entity.int.i.booleanMap_?.insert(6, 3, Some(Map(pboolean1, pboolean2))).transact
      _ <- Entity.int.i.bigIntMap_?.insert(7, 3, Some(Map(pbigInt1, pbigInt2))).transact
      _ <- Entity.int.i.bigDecimalMap_?.insert(8, 3, Some(Map(pbigDecimal1, pbigDecimal2))).transact
      _ <- Entity.int.i.dateMap_?.insert(9, 3, Some(Map(pdate1, pdate2))).transact
      _ <- Entity.int.i.durationMap_?.insert(10, 3, Some(Map(pduration1, pduration2))).transact
      _ <- Entity.int.i.instantMap_?.insert(11, 3, Some(Map(pinstant1, pinstant2))).transact
      _ <- Entity.int.i.localDateMap_?.insert(12, 3, Some(Map(plocalDate1, plocalDate2))).transact
      _ <- Entity.int.i.localTimeMap_?.insert(13, 3, Some(Map(plocalTime1, plocalTime2))).transact
      _ <- Entity.int.i.localDateTimeMap_?.insert(14, 3, Some(Map(plocalDateTime1, plocalDateTime2))).transact
      _ <- Entity.int.i.offsetTimeMap_?.insert(15, 3, Some(Map(poffsetTime1, poffsetTime2))).transact
      _ <- Entity.int.i.offsetDateTimeMap_?.insert(16, 3, Some(Map(poffsetDateTime1, poffsetDateTime2))).transact
      _ <- Entity.int.i.zonedDateTimeMap_?.insert(17, 3, Some(Map(pzonedDateTime1, pzonedDateTime2))).transact
      _ <- Entity.int.i.uuidMap_?.insert(18, 3, Some(Map(puuid1, puuid2))).transact
      _ <- Entity.int.i.uriMap_?.insert(19, 3, Some(Map(puri1, puri2))).transact
      _ <- Entity.int.i.byteMap_?.insert(20, 3, Some(Map(pbyte1, pbyte2))).transact
      _ <- Entity.int.i.shortMap_?.insert(21, 3, Some(Map(pshort1, pshort2))).transact
      _ <- Entity.int.i.charMap_?.insert(22, 3, Some(Map(pchar1, pchar2))).transact

      _ <- Entity.int_(1).i.a1.stringMap_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Map(pstring1, pstring2)))))
      _ <- Entity.int_(2).i.a1.intMap_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Map(pint1, pint2)))))
      _ <- Entity.int_(3).i.a1.longMap_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Map(plong1, plong2)))))
      _ <- Entity.int_(4).i.a1.floatMap_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Map(pfloat1, pfloat2)))))
      _ <- Entity.int_(5).i.a1.doubleMap_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Map(pdouble1, pdouble2)))))
      _ <- Entity.int_(6).i.a1.booleanMap_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Map(pboolean1, pboolean2)))))
      _ <- Entity.int_(7).i.a1.bigIntMap_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Map(pbigInt1, pbigInt2)))))
      _ <- Entity.int_(8).i.a1.bigDecimalMap_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Map(pbigDecimal1, pbigDecimal2)))))
      _ <- Entity.int_(9).i.a1.dateMap_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Map(pdate1, pdate2)))))
      _ <- Entity.int_(10).i.a1.durationMap_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Map(pduration1, pduration2)))))
      _ <- Entity.int_(11).i.a1.instantMap_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Map(pinstant1, pinstant2)))))
      _ <- Entity.int_(12).i.a1.localDateMap_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Map(plocalDate1, plocalDate2)))))
      _ <- Entity.int_(13).i.a1.localTimeMap_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Map(plocalTime1, plocalTime2)))))
      _ <- Entity.int_(14).i.a1.localDateTimeMap_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Map(plocalDateTime1, plocalDateTime2)))))
      _ <- Entity.int_(15).i.a1.offsetTimeMap_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Map(poffsetTime1, poffsetTime2)))))
      _ <- Entity.int_(16).i.a1.offsetDateTimeMap_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Map(poffsetDateTime1, poffsetDateTime2)))))
      _ <- Entity.int_(17).i.a1.zonedDateTimeMap_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Map(pzonedDateTime1, pzonedDateTime2)))))
      _ <- Entity.int_(18).i.a1.uuidMap_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Map(puuid1, puuid2)))))
      _ <- Entity.int_(19).i.a1.uriMap_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Map(puri1, puri2)))))
      _ <- Entity.int_(20).i.a1.byteMap_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Map(pbyte1, pbyte2)))))
      _ <- Entity.int_(21).i.a1.shortMap_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Map(pshort1, pshort2)))))
      _ <- Entity.int_(22).i.a1.charMap_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Map(pchar1, pchar2)))))
    } yield ()
  }


  "Tacit" - types { implicit conn =>
    for {
      // Can't insert tacit attributes
      _ <- Future(compileErrors("Entity.i.stringMap_.insert(1, Map(pstring1))"))
    } yield ()
  }


  "Valid keys" - types { implicit conn =>
    for {
      // Allowed characters in a key name
      _ <- Entity.intMap.insert(Map("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_0123456789" -> 1)).transact

      // No spaces
      _ <- Entity.intMap.insert(Map("foo bar" -> 1)).transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Keys of map attributes can only contain [a-zA-Z_\\-0-9] (no spaces or special characters)."
        }

      // No special characters
      _ <- Entity.intMap.insert(Map("foo:" -> 1)).transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Keys of map attributes can only contain [a-zA-Z_\\-0-9] (no spaces or special characters)."
        }
    } yield ()
  }
}
