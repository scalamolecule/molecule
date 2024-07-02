package molecule.coreTests.spi.validation.update

import java.net.URI
import java.util.UUID
import molecule.base.error.ValidationErrors
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Validation._
import molecule.coreTests.setup.CoreTestSuite
import utest._
import scala.language.implicitConversions

trait TypesSetOpt extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Types" - validation { implicit conn =>
      for {
        id <- Type.stringSet_?(Some(Set("d"))).save.transact.map(_.id)

        _ <- Type(id).stringSet_?(Some(Set("a", "b", "d"))).update.transact
          .map(_ ==> "Unexpected success").recover {
            case ValidationErrors(errorMap) =>
              errorMap ==>
                Map(
                  "Type.stringSet" -> Seq(
                    s"""Type.stringSet with value `a` doesn't satisfy validation:
                       |_ > "c"
                       |""".stripMargin,
                    s"""Type.stringSet with value `b` doesn't satisfy validation:
                       |_ > "c"
                       |""".stripMargin
                    // (value b is ok)
                  )
                )
          }

        // Focusing on error message only
        _ <- Type(id).stringSet_?(Some(Set("a", "b", "d"))).update.transact
          .map(_ ==> "Unexpected success").recover {
            case ValidationErrors(errorMap) =>
              errorMap.head._2 ==> Seq(
                s"""Type.stringSet with value `a` doesn't satisfy validation:
                   |_ > "c"
                   |""".stripMargin,
                s"""Type.stringSet with value `b` doesn't satisfy validation:
                   |_ > "c"
                   |""".stripMargin
                // (value b is ok)
              )
          }


        id <- Type.intSet_?(Some(Set(4))).save.transact.map(_.id)
        _ <- Type(id).intSet_?(Some(Set(1, 2, 4))).update.transact
          .map(_ ==> "Unexpected success").recover {
            case ValidationErrors(errorMap) =>
              errorMap.head._2 ==> Seq(
                s"""Type.intSet with value `1` doesn't satisfy validation:
                   |_ > 3
                   |""".stripMargin,
                s"""Type.intSet with value `2` doesn't satisfy validation:
                   |_ > 3
                   |""".stripMargin
              )
          }


        id <- Type.longSet_?(Some(Set(4L))).save.transact.map(_.id)
        _ <- Type(id).longSet_?(Some(Set(1L, 2L, 4L))).update.transact
          .map(_ ==> "Unexpected success").recover {
            case ValidationErrors(errorMap) =>
              errorMap.head._2 ==> Seq(
                s"""Type.longSet with value `1` doesn't satisfy validation:
                   |_ > 3L
                   |""".stripMargin,
                s"""Type.longSet with value `2` doesn't satisfy validation:
                   |_ > 3L
                   |""".stripMargin
              )
          }


        id <- Type.floatSet_?(Some(Set(float4))).save.transact.map(_.id)
        _ <- Type(id).floatSet_?(Some(Set(float1, float2, float4))).update.transact
          .map(_ ==> "Unexpected success").recover {
            case ValidationErrors(errorMap) =>
              errorMap.head._2 ==> Seq(
                s"""Type.floatSet with value `$float1` doesn't satisfy validation:
                   |_ > 3.3f
                   |""".stripMargin,
                s"""Type.floatSet with value `$float2` doesn't satisfy validation:
                   |_ > 3.3f
                   |""".stripMargin
              )
          }


        id <- Type.double(double4).save.transact.map(_.id)
        _ <- Type(id).doubleSet_?(Some(Set(double1, double2, double4))).update.transact
          .map(_ ==> "Unexpected success").recover {
            case ValidationErrors(errorMap) =>
              errorMap.head._2 ==> Seq(
                s"""Type.doubleSet with value `$double1` doesn't satisfy validation:
                   |_ > 3.3
                   |""".stripMargin,
                s"""Type.doubleSet with value `$double2` doesn't satisfy validation:
                   |_ > 3.3
                   |""".stripMargin
              )
          }


        id <- Type.boolean(false).save.transact.map(_.id)
        _ <- Type(id).booleanSet_?(Some(Set(true, false))).update.transact
          .map(_ ==> "Unexpected success").recover {
            case ValidationErrors(errorMap) =>
              errorMap.head._2 ==> Seq(
                s"""Type.booleanSet with value `true` doesn't satisfy validation:
                   |_ == false
                   |""".stripMargin
              )
          }


        id <- Type.bigIntSet_?(Some(Set(bigInt4))).save.transact.map(_.id)
        _ <- Type(id).bigIntSet_?(Some(Set(bigInt1, bigInt2, bigInt4))).update.transact
          .map(_ ==> "Unexpected success").recover {
            case ValidationErrors(errorMap) =>
              errorMap.head._2 ==> Seq(
                s"""Type.bigIntSet with value `$bigInt1` doesn't satisfy validation:
                   |_ > BigInt(3)
                   |""".stripMargin,
                s"""Type.bigIntSet with value `$bigInt2` doesn't satisfy validation:
                   |_ > BigInt(3)
                   |""".stripMargin
              )
          }


        id <- Type.bigDecimalSet_?(Some(Set(bigDecimal4))).save.transact.map(_.id)
        _ <- Type(id).bigDecimalSet_?(Some(Set(bigDecimal1, bigDecimal2, bigDecimal4))).update.transact
          .map(_ ==> "Unexpected success").recover {
            case ValidationErrors(errorMap) =>
              errorMap.head._2 ==> Seq(
                s"""Type.bigDecimalSet with value `$bigDecimal1` doesn't satisfy validation:
                   |_ > BigDecimal(3.3)
                   |""".stripMargin,
                s"""Type.bigDecimalSet with value `$bigDecimal2` doesn't satisfy validation:
                   |_ > BigDecimal(3.3)
                   |""".stripMargin
              )
          }


        id <- Type.dateSet_?(Some(Set(date4))).save.transact.map(_.id)
        _ <- Type(id).dateSet_?(Some(Set(date1, date2, date4))).update.transact
          .map(_ ==> "Unexpected success").recover {
            case ValidationErrors(errorMap) =>
              errorMap.head._2 ==> Seq(
                s"""Type.dateSet with value `$date1` doesn't satisfy validation:
                   |_.after(new Date(1057010400000L))
                   |""".stripMargin,
                s"""Type.dateSet with value `$date2` doesn't satisfy validation:
                   |_.after(new Date(1057010400000L))
                   |""".stripMargin
              )
          }


        id <- Type.durationSet_?(Some(Set(duration4))).save.transact.map(_.id)
        _ <- Type(id).durationSet_?(Some(Set(duration1, duration2, duration4))).update.transact
          .map(_ ==> "Unexpected success").recover {
            case ValidationErrors(errorMap) =>
              errorMap.head._2 ==> Seq(
                s"""Type.durationSet with value `$duration1` doesn't satisfy validation:
                   |_.compareTo(Duration.ofMinutes(2)) > 0
                   |""".stripMargin,
                s"""Type.durationSet with value `$duration2` doesn't satisfy validation:
                   |_.compareTo(Duration.ofMinutes(2)) > 0
                   |""".stripMargin
              )
          }


        id <- Type.instantSet_?(Some(Set(instant4))).save.transact.map(_.id)
        _ <- Type(id).instantSet_?(Some(Set(instant1, instant2, instant4))).update.transact
          .map(_ ==> "Unexpected success").recover {
            case ValidationErrors(errorMap) =>
              errorMap.head._2 ==> Seq(
                s"""Type.instantSet with value `$instant1` doesn't satisfy validation:
                   |_.compareTo(Instant.ofEpochSecond(2)) > 0
                   |""".stripMargin,
                s"""Type.instantSet with value `$instant2` doesn't satisfy validation:
                   |_.compareTo(Instant.ofEpochSecond(2)) > 0
                   |""".stripMargin
              )
          }


        id <- Type.localDateSet_?(Some(Set(localDate4))).save.transact.map(_.id)
        _ <- Type(id).localDateSet_?(Some(Set(localDate1, localDate2, localDate4))).update.transact
          .map(_ ==> "Unexpected success").recover {
            case ValidationErrors(errorMap) =>
              errorMap.head._2 ==> Seq(
                s"""Type.localDateSet with value `$localDate1` doesn't satisfy validation:
                   |_.compareTo(LocalDate.of(2002, 1, 1)) > 0
                   |""".stripMargin,
                s"""Type.localDateSet with value `$localDate2` doesn't satisfy validation:
                   |_.compareTo(LocalDate.of(2002, 1, 1)) > 0
                   |""".stripMargin
              )
          }


        id <- Type.localTimeSet_?(Some(Set(localTime4))).save.transact.map(_.id)
        _ <- Type(id).localTimeSet_?(Some(Set(localTime1, localTime2, localTime4))).update.transact
          .map(_ ==> "Unexpected success").recover {
            case ValidationErrors(errorMap) =>
              errorMap.head._2 ==> Seq(
                s"""Type.localTimeSet with value `$localTime1` doesn't satisfy validation:
                   |_.compareTo(LocalTime.of(2, 2)) > 0
                   |""".stripMargin,
                s"""Type.localTimeSet with value `$localTime2` doesn't satisfy validation:
                   |_.compareTo(LocalTime.of(2, 2)) > 0
                   |""".stripMargin
              )
          }


        id <- Type.localDateTimeSet_?(Some(Set(localDateTime4))).save.transact.map(_.id)
        _ <- Type(id).localDateTimeSet_?(Some(Set(localDateTime1, localDateTime2, localDateTime4))).update.transact
          .map(_ ==> "Unexpected success").recover {
            case ValidationErrors(errorMap) =>
              errorMap.head._2 ==> Seq(
                s"""Type.localDateTimeSet with value `$localDateTime1` doesn't satisfy validation:
                   |_.compareTo(LocalDateTime.of(2002, 1, 1, 1, 2)) > 0
                   |""".stripMargin,
                s"""Type.localDateTimeSet with value `$localDateTime2` doesn't satisfy validation:
                   |_.compareTo(LocalDateTime.of(2002, 1, 1, 1, 2)) > 0
                   |""".stripMargin
              )
          }


        id <- Type.offsetTimeSet_?(Some(Set(offsetTime4))).save.transact.map(_.id)
        _ <- Type(id).offsetTimeSet_?(Some(Set(offsetTime1, offsetTime2, offsetTime4))).update.transact
          .map(_ ==> "Unexpected success").recover {
            case ValidationErrors(errorMap) =>
              errorMap.head._2 ==> Seq(
                s"""Type.offsetTimeSet with value `$offsetTime1` doesn't satisfy validation:
                   |_.compareTo(OffsetTime.of(2, 2, 2, 2, ZoneOffset.ofHours(2))) > 0
                   |""".stripMargin,
                s"""Type.offsetTimeSet with value `$offsetTime2` doesn't satisfy validation:
                   |_.compareTo(OffsetTime.of(2, 2, 2, 2, ZoneOffset.ofHours(2))) > 0
                   |""".stripMargin
              )
          }


        id <- Type.offsetDateTimeSet_?(Some(Set(offsetDateTime4))).save.transact.map(_.id)
        _ <- Type(id).offsetDateTimeSet_?(Some(Set(offsetDateTime1, offsetDateTime2, offsetDateTime4))).update.transact
          .map(_ ==> "Unexpected success").recover {
            case ValidationErrors(errorMap) =>
              errorMap.head._2 ==> Seq(
                s"""Type.offsetDateTimeSet with value `$offsetDateTime1` doesn't satisfy validation:
                   |_.compareTo(OffsetDateTime.of(2002, 1, 1, 1, 1, 1, 1, ZoneOffset.ofHours(2))) > 0
                   |""".stripMargin,
                s"""Type.offsetDateTimeSet with value `$offsetDateTime2` doesn't satisfy validation:
                   |_.compareTo(OffsetDateTime.of(2002, 1, 1, 1, 1, 1, 1, ZoneOffset.ofHours(2))) > 0
                   |""".stripMargin
              )
          }


        id <- Type.zonedDateTimeSet_?(Some(Set(zonedDateTime4))).save.transact.map(_.id)
        _ <- Type(id).zonedDateTimeSet_?(Some(Set(zonedDateTime1, zonedDateTime2, zonedDateTime4))).update.transact
          .map(_ ==> "Unexpected success").recover {
            case ValidationErrors(errorMap) =>
              errorMap.head._2 ==> Seq(
                s"""Type.zonedDateTimeSet with value `$zonedDateTime1` doesn't satisfy validation:
                   |_.compareTo(ZonedDateTime.of(2002, 1, 1, 1, 1, 1, 1, ZoneOffset.ofHours(2))) > 0
                   |""".stripMargin,
                s"""Type.zonedDateTimeSet with value `$zonedDateTime2` doesn't satisfy validation:
                   |_.compareTo(ZonedDateTime.of(2002, 1, 1, 1, 1, 1, 1, ZoneOffset.ofHours(2))) > 0
                   |""".stripMargin
              )
          }


        id <- Type.uuidSet_?(Some(Set(UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-dddddddddddd")))).save.transact.map(_.id)
        _ <- Type(id).uuidSet_?(Some(Set(UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa")))).update.transact
          .map(_ ==> "Unexpected success").recover {
            case ValidationErrors(errorMap) =>
              errorMap.head._2 ==> Seq(
                s"""Type.uuidSet with value `aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa` doesn't satisfy validation:
                   |_.toString != "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"
                   |""".stripMargin
              )
          }


        uri1 = new URI("a")
        uri2 = new URI("ab")
        uri4 = new URI("abcd")
        id <- Type.uriSet_?(Some(Set(uri4))).save.transact.map(_.id)
        _ <- Type(id).uriSet_?(Some(Set(uri1, uri2, uri4))).update.transact
          .map(_ ==> "Unexpected success").recover {
            case ValidationErrors(errorMap) =>
              errorMap.head._2 ==> Seq(
                s"""Type.uriSet with value `a` doesn't satisfy validation:
                   |_.toString.length > 3
                   |""".stripMargin,
                s"""Type.uriSet with value `ab` doesn't satisfy validation:
                   |_.toString.length > 3
                   |""".stripMargin
              )
          }


        id <- Type.byteSet_?(Some(Set(byte4))).save.transact.map(_.id)
        _ <- Type(id).byteSet_?(Some(Set(byte1, byte2, byte4))).update.transact
          .map(_ ==> "Unexpected success").recover {
            case ValidationErrors(errorMap) =>
              errorMap.head._2 ==> Seq(
                s"""Type.byteSet with value `$byte1` doesn't satisfy validation:
                   |_ > $byte3
                   |""".stripMargin,
                s"""Type.byteSet with value `$byte2` doesn't satisfy validation:
                   |_ > $byte3
                   |""".stripMargin
              )
          }


        id <- Type.shortSet_?(Some(Set(short4))).save.transact.map(_.id)
        _ <- Type(id).shortSet_?(Some(Set(short1, short2, short4))).update.transact
          .map(_ ==> "Unexpected success").recover {
            case ValidationErrors(errorMap) =>
              errorMap.head._2 ==> Seq(
                s"""Type.shortSet with value `$short1` doesn't satisfy validation:
                   |_ > $short3
                   |""".stripMargin,
                s"""Type.shortSet with value `$short2` doesn't satisfy validation:
                   |_ > $short3
                   |""".stripMargin
              )
          }

        id <- Type.charSet_?(Some(Set('d'))).save.transact.map(_.id)
        _ <- Type(id).charSet_?(Some(Set('a', 'b', 'd'))).update.transact
          .map(_ ==> "Unexpected success").recover {
            case ValidationErrors(errorMap) =>
              errorMap.head._2 ==> Seq(
                s"""Type.charSet with value `a` doesn't satisfy validation:
                   |_ > 'c'
                   |""".stripMargin,
                s"""Type.charSet with value `b` doesn't satisfy validation:
                   |_ > 'c'
                   |""".stripMargin
              )
          }
      } yield ()
    }
  }
}
