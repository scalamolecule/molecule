package molecule.coreTests.spi.validation.save

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

trait TypesSet extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "String" - validation { implicit conn =>
      for {
        _ <- Type.stringSet(Set("a", "b", "d")).save.transact
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
                    // (value d is ok)
                  )
                )
          }
        // Focusing only on the first (and only) error message
        // (See ValidationFormatting tests for multi-error validations)
        _ <- Type.stringSet(Set("a", "b", "d")).save.transact
          .map(_ ==> "Unexpected success").recover {
            case ValidationErrors(errorMap) =>
              errorMap.head._2 ==> Seq(
                s"""Type.stringSet with value `a` doesn't satisfy validation:
                   |_ > "c"
                   |""".stripMargin,
                s"""Type.stringSet with value `b` doesn't satisfy validation:
                   |_ > "c"
                   |""".stripMargin
              )
          }
      } yield ()
    }

    "Int" - validation { implicit conn =>
      for {
        _ <- Type.intSet(Set(1, 2, 4)).save.transact
          .map(_ ==> "Unexpected success").recover {
            case ValidationErrors(errorMap) =>
              errorMap.head._2.sorted ==> Seq(
                s"""Type.intSet with value `1` doesn't satisfy validation:
                   |_ > 3
                   |""".stripMargin,
                s"""Type.intSet with value `2` doesn't satisfy validation:
                   |_ > 3
                   |""".stripMargin
              )
          }
      } yield ()
    }

    "Long" - validation { implicit conn =>
      for {
        _ <- Type.longSet(Set(1L, 2L, 4L)).save.transact
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
      } yield ()
    }

    "Float" - validation { implicit conn =>
      for {
        _ <- Type.floatSet(Set(float1, float2, float4)).save.transact
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
      } yield ()
    }

    "Double" - validation { implicit conn =>
      for {
        _ <- Type.doubleSet(Set(double1, double2, double4)).save.transact
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
      } yield ()
    }

    "Boolean" - validation { implicit conn =>
      for {
        _ <- Type.booleanSet(Set(true, false)).save.transact
          .map(_ ==> "Unexpected success").recover {
            case ValidationErrors(errorMap) =>
              errorMap.head._2 ==> Seq(
                s"""Type.booleanSet with value `true` doesn't satisfy validation:
                   |_ == false
                   |""".stripMargin
              )
          }
      } yield ()
    }

    "BigInt" - validation { implicit conn =>
      for {
        _ <- Type.bigIntSet(Set(bigInt1, bigInt2, bigInt4)).save.transact
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
      } yield ()
    }

    "BigDecimal" - validation { implicit conn =>
      for {
        _ <- Type.bigDecimalSet(Set(bigDecimal1, bigDecimal2, bigDecimal4)).save.transact
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
      } yield ()
    }

    "Date" - validation { implicit conn =>
      for {
        _ <- Type.dateSet(Set(date1, date2, date4)).save.transact
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
      } yield ()
    }

    "Duration" - validation { implicit conn =>
      for {
        _ <- Type.durationSet(Set(duration1, duration2, duration4)).save.transact
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
      } yield ()
    }

    "Instant" - validation { implicit conn =>
      for {
        _ <- Type.instantSet(Set(instant1, instant2, instant4)).save.transact
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
      } yield ()
    }

    "LocalDate" - validation { implicit conn =>
      for {
        _ <- Type.localDateSet(Set(localDate1, localDate2, localDate4)).save.transact
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
      } yield ()
    }

    "LocalTime" - validation { implicit conn =>
      for {
        _ <- Type.localTimeSet(Set(localTime1, localTime2, localTime4)).save.transact
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
      } yield ()
    }

    "LocalDateTime" - validation { implicit conn =>
      for {
        _ <- Type.localDateTimeSet(Set(localDateTime1, localDateTime2, localDateTime4)).save.transact
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
      } yield ()
    }

    "OffsetTime" - validation { implicit conn =>
      for {
        _ <- Type.offsetTimeSet(Set(offsetTime1, offsetTime2, offsetTime4)).save.transact
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
      } yield ()
    }

    "OffsetDateTime" - validation { implicit conn =>
      for {
        _ <- Type.offsetDateTimeSet(Set(offsetDateTime1, offsetDateTime2, offsetDateTime4)).save.transact
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
      } yield ()
    }

    "ZonedDateTime" - validation { implicit conn =>
      for {
        _ <- Type.zonedDateTimeSet(Set(zonedDateTime1, zonedDateTime2, zonedDateTime4)).save.transact
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
      } yield ()
    }

    "UUID" - validation { implicit conn =>
      for {
        _ <- Type.uuidSet(Set(
            UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"),
            UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-bbbbbbbbbbbb")
          )).save.transact
          .map(_ ==> "Unexpected success").recover {
            case ValidationErrors(errorMap) =>
              errorMap.head._2 ==> Seq(
                s"""Type.uuidSet with value `aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa` doesn't satisfy validation:
                   |_.toString != "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"
                   |""".stripMargin
              )
          }
      } yield ()
    }

    "URI" - validation { implicit conn =>
      val uri0 = new URI("a")
      val uri1 = new URI("ab")
      val uri2 = new URI("abcd")
      for {
        _ <- Type.uriSet(Set(uri0, uri1, uri2)).save.transact
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
      } yield ()
    }

    "Byte" - validation { implicit conn =>
      for {
        _ <- Type.byteSet(Set(byte1, byte2, byte4)).save.transact
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
      } yield ()
    }

    "Short" - validation { implicit conn =>
      for {
        _ <- Type.shortSet(Set(short1, short2, short4)).save.transact
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
      } yield ()
    }

    "Char" - validation { implicit conn =>
      for {
        _ <- Type.charSet(Set('a', 'b', 'd')).save.transact
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
