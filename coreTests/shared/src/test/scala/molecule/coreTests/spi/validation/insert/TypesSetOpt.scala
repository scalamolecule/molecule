package molecule.coreTests.spi.validation.insert

import java.net.URI
import java.util.UUID
import molecule.base.error._
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

    "String" - validation { implicit conn =>
      for {
        _ <- Type.stringSet_?.insert(Some(Set("a", "b", "d"))).transact
          .map(_ ==> "Unexpected success").recover {
            case InsertErrors(indexedInsertErrors, _) =>
              indexedInsertErrors ==>
                Seq(
                  (
                    0, // Row index
                    Seq(
                      InsertError(
                        0, // tuple index
                        "Type.stringSet",
                        Seq(
                          s"""Type.stringSet with value `a` doesn't satisfy validation:
                             |_ > "c"
                             |""".stripMargin,
                          s"""Type.stringSet with value `b` doesn't satisfy validation:
                             |_ > "c"
                             |""".stripMargin
                          // (value d is ok)
                        ),
                        Nil // nested errors
                      )
                    )
                  )
                )
          }

        // Isolate expected errors
        _ <- Type.stringSet_?.insert(Some(Set("a", "b", "d"))).transact
          .map(_ ==> "Unexpected success").recover {
            case InsertErrors(errors, _) =>
              errors.head._2.head.errors ==> Seq(
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
        _ <- Type.intSet_?.insert(Some(Set(1, 2, 4))).transact
          .map(_ ==> "Unexpected success").recover {
            case InsertErrors(errors, _) =>
              errors.head._2.head.errors ==> Seq(
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
        _ <- Type.longSet_?.insert(Some(Set(1L, 2L, 4L))).transact
          .map(_ ==> "Unexpected success").recover {
            case InsertErrors(errors, _) =>
              errors.head._2.head.errors ==> Seq(
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
        _ <- Type.floatSet_?.insert(Some(Set(float1, float2, float4))).transact
          .map(_ ==> "Unexpected success").recover {
            case InsertErrors(errors, _) =>
              errors.head._2.head.errors ==> Seq(
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
        _ <- Type.doubleSet_?.insert(Some(Set(double1, double2, double4))).transact
          .map(_ ==> "Unexpected success").recover {
            case InsertErrors(errors, _) =>
              errors.head._2.head.errors ==> Seq(
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
        _ <- Type.booleanSet_?.insert(Some(Set(true, false))).transact
          .map(_ ==> "Unexpected success").recover {
            case InsertErrors(errors, _) =>
              errors.head._2.head.errors ==> Seq(
                s"""Type.booleanSet with value `true` doesn't satisfy validation:
                   |_ == false
                   |""".stripMargin
              )
          }
      } yield ()
    }

    "BigInt" - validation { implicit conn =>
      for {
        _ <- Type.bigIntSet_?.insert(Some(Set(bigInt1, bigInt2, bigInt4))).transact
          .map(_ ==> "Unexpected success").recover {
            case InsertErrors(errors, _) =>
              errors.head._2.head.errors ==> Seq(
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
        _ <- Type.bigDecimalSet_?.insert(Some(Set(bigDecimal1, bigDecimal2, bigDecimal4))).transact
          .map(_ ==> "Unexpected success").recover {
            case InsertErrors(errors, _) =>
              errors.head._2.head.errors ==> Seq(
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
        _ <- Type.dateSet_?.insert(Some(Set(date1, date2, date4))).transact
          .map(_ ==> "Unexpected success").recover {
            case InsertErrors(errors, _) =>
              errors.head._2.head.errors ==> Seq(
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
        _ <- Type.durationSet_?.insert(Some(Set(duration1, duration2, duration4))).transact
          .map(_ ==> "Unexpected success").recover {
            case InsertErrors(errors, _) =>
              errors.head._2.head.errors ==> Seq(
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
        _ <- Type.instantSet_?.insert(Some(Set(instant1, instant2, instant4))).transact
          .map(_ ==> "Unexpected success").recover {
            case InsertErrors(errors, _) =>
              errors.head._2.head.errors ==> Seq(
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
        _ <- Type.localDateSet_?.insert(Some(Set(localDate1, localDate2, localDate4))).transact
          .map(_ ==> "Unexpected success").recover {
            case InsertErrors(errors, _) =>
              errors.head._2.head.errors ==> Seq(
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
        _ <- Type.localTimeSet_?.insert(Some(Set(localTime1, localTime2, localTime4))).transact
          .map(_ ==> "Unexpected success").recover {
            case InsertErrors(errors, _) =>
              errors.head._2.head.errors ==> Seq(
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
        _ <- Type.localDateTimeSet_?.insert(Some(Set(localDateTime1, localDateTime2, localDateTime4))).transact
          .map(_ ==> "Unexpected success").recover {
            case InsertErrors(errors, _) =>
              errors.head._2.head.errors ==> Seq(
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
        _ <- Type.offsetTimeSet_?.insert(Some(Set(offsetTime1, offsetTime2, offsetTime4))).transact
          .map(_ ==> "Unexpected success").recover {
            case InsertErrors(errors, _) =>
              errors.head._2.head.errors ==> Seq(
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
        _ <- Type.offsetDateTimeSet_?.insert(Some(Set(offsetDateTime1, offsetDateTime2, offsetDateTime4))).transact
          .map(_ ==> "Unexpected success").recover {
            case InsertErrors(errors, _) =>
              errors.head._2.head.errors ==> Seq(
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
        _ <- Type.zonedDateTimeSet_?.insert(Some(Set(zonedDateTime1, zonedDateTime2, zonedDateTime4))).transact
          .map(_ ==> "Unexpected success").recover {
            case InsertErrors(errors, _) =>
              errors.head._2.head.errors ==> Seq(
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
        _ <- Type.uuidSet.insert(Set(
            UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"),
            UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-bbbbbbbbbbbb")
          )).transact
          .map(_ ==> "Unexpected success").recover {
            case InsertErrors(errors, _) =>
              errors.head._2.head.errors ==> Seq(
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
        _ <- Type.uriSet_?.insert(Some(Set(uri0, uri1, uri2))).transact
          .map(_ ==> "Unexpected success").recover {
            case InsertErrors(errors, _) =>
              errors.head._2.head.errors ==> Seq(
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
        _ <- Type.byteSet_?.insert(Some(Set(byte1, byte2, byte4))).transact
          .map(_ ==> "Unexpected success").recover {
            case InsertErrors(errors, _) =>
              errors.head._2.head.errors ==> Seq(
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
        _ <- Type.shortSet_?.insert(Some(Set(short1, short2, short4))).transact
          .map(_ ==> "Unexpected success").recover {
            case InsertErrors(errors, _) =>
              errors.head._2.head.errors ==> Seq(
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
        _ <- Type.charSet_?.insert(Some(Set('a', 'b', 'd'))).transact
          .map(_ ==> "Unexpected success").recover {
            case InsertErrors(errors, _) =>
              errors.head._2.head.errors ==> Seq(
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
