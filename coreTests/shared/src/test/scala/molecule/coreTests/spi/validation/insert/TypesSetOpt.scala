package molecule.coreTests.compliance.validation.insert

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
        _ <- Type.strings_?.insert(Some(Set("a", "b", "d"))).transact
          .map(_ ==> "Unexpected success").recover {
            case InsertErrors(indexedInsertErrors, _) =>
              indexedInsertErrors ==>
                Seq(
                  (
                    0, // Row index
                    Seq(
                      InsertError(
                        0, // tuple index
                        "Type.strings",
                        Seq(
                          s"""Type.strings with value `a` doesn't satisfy validation:
                             |  _ > "c"
                             |""".stripMargin,
                          s"""Type.strings with value `b` doesn't satisfy validation:
                             |  _ > "c"
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
        _ <- Type.strings_?.insert(Some(Set("a", "b", "d"))).transact
          .map(_ ==> "Unexpected success").recover {
            case InsertErrors(errors, _) =>
              errors.head._2.head.errors ==> Seq(
                s"""Type.strings with value `a` doesn't satisfy validation:
                   |  _ > "c"
                   |""".stripMargin,
                s"""Type.strings with value `b` doesn't satisfy validation:
                   |  _ > "c"
                   |""".stripMargin
              )
          }

      } yield ()
    }

    "Int" - validation { implicit conn =>
      for {
        _ <- Type.ints_?.insert(Some(Set(1, 2, 4))).transact
          .map(_ ==> "Unexpected success").recover {
            case InsertErrors(errors, _) =>
              errors.head._2.head.errors ==> Seq(
                s"""Type.ints with value `1` doesn't satisfy validation:
                   |  _ > 3
                   |""".stripMargin,
                s"""Type.ints with value `2` doesn't satisfy validation:
                   |  _ > 3
                   |""".stripMargin
              )
          }
      } yield ()
    }

    "Long" - validation { implicit conn =>
      for {
        _ <- Type.longs_?.insert(Some(Set(1L, 2L, 4L))).transact
          .map(_ ==> "Unexpected success").recover {
            case InsertErrors(errors, _) =>
              errors.head._2.head.errors ==> Seq(
                s"""Type.longs with value `1` doesn't satisfy validation:
                   |  _ > 3L
                   |""".stripMargin,
                s"""Type.longs with value `2` doesn't satisfy validation:
                   |  _ > 3L
                   |""".stripMargin
              )
          }
      } yield ()
    }

    "Float" - validation { implicit conn =>
      for {
        _ <- Type.floats_?.insert(Some(Set(float1, float2, float4))).transact
          .map(_ ==> "Unexpected success").recover {
            case InsertErrors(errors, _) =>
              errors.head._2.head.errors ==> Seq(
                s"""Type.floats with value `$float1` doesn't satisfy validation:
                   |  _ > 3.3f
                   |""".stripMargin,
                s"""Type.floats with value `$float2` doesn't satisfy validation:
                   |  _ > 3.3f
                   |""".stripMargin
              )
          }
      } yield ()
    }

    "Double" - validation { implicit conn =>
      for {
        _ <- Type.doubles_?.insert(Some(Set(double1, double2, double4))).transact
          .map(_ ==> "Unexpected success").recover {
            case InsertErrors(errors, _) =>
              errors.head._2.head.errors ==> Seq(
                s"""Type.doubles with value `$double1` doesn't satisfy validation:
                   |  _ > 3.3
                   |""".stripMargin,
                s"""Type.doubles with value `$double2` doesn't satisfy validation:
                   |  _ > 3.3
                   |""".stripMargin
              )
          }
      } yield ()
    }

    "Boolean" - validation { implicit conn =>
      for {
        _ <- Type.booleans_?.insert(Some(Set(true, false))).transact
          .map(_ ==> "Unexpected success").recover {
            case InsertErrors(errors, _) =>
              errors.head._2.head.errors ==> Seq(
                s"""Type.booleans with value `true` doesn't satisfy validation:
                   |  _ == false
                   |""".stripMargin
              )
          }
      } yield ()
    }

    "BigInt" - validation { implicit conn =>
      for {
        _ <- Type.bigInts_?.insert(Some(Set(bigInt1, bigInt2, bigInt4))).transact
          .map(_ ==> "Unexpected success").recover {
            case InsertErrors(errors, _) =>
              errors.head._2.head.errors ==> Seq(
                s"""Type.bigInts with value `$bigInt1` doesn't satisfy validation:
                   |  _ > BigInt(3)
                   |""".stripMargin,
                s"""Type.bigInts with value `$bigInt2` doesn't satisfy validation:
                   |  _ > BigInt(3)
                   |""".stripMargin
              )
          }
      } yield ()
    }

    "BigDecimal" - validation { implicit conn =>
      for {
        _ <- Type.bigDecimals_?.insert(Some(Set(bigDecimal1, bigDecimal2, bigDecimal4))).transact
          .map(_ ==> "Unexpected success").recover {
            case InsertErrors(errors, _) =>
              errors.head._2.head.errors ==> Seq(
                s"""Type.bigDecimals with value `$bigDecimal1` doesn't satisfy validation:
                   |  _ > BigDecimal(3.3)
                   |""".stripMargin,
                s"""Type.bigDecimals with value `$bigDecimal2` doesn't satisfy validation:
                   |  _ > BigDecimal(3.3)
                   |""".stripMargin
              )
          }
      } yield ()
    }

    "Date" - validation { implicit conn =>
      for {
        _ <- Type.dates_?.insert(Some(Set(date1, date2, date4))).transact
          .map(_ ==> "Unexpected success").recover {
            case InsertErrors(errors, _) =>
              errors.head._2.head.errors ==> Seq(
                s"""Type.dates with value `$date1` doesn't satisfy validation:
                   |  _.after(new Date(1057010400000L))
                   |""".stripMargin,
                s"""Type.dates with value `$date2` doesn't satisfy validation:
                   |  _.after(new Date(1057010400000L))
                   |""".stripMargin
              )
          }
      } yield ()
    }

    "Duration" - validation { implicit conn =>
      for {
        _ <- Type.durations_?.insert(Some(Set(duration1, duration2, duration4))).transact
          .map(_ ==> "Unexpected success").recover {
            case InsertErrors(errors, _) =>
              errors.head._2.head.errors ==> Seq(
                s"""Type.durations with value `$duration1` doesn't satisfy validation:
                   |  _.compareTo(Duration.ofMinutes(2)) > 0
                   |""".stripMargin,
                s"""Type.durations with value `$duration2` doesn't satisfy validation:
                   |  _.compareTo(Duration.ofMinutes(2)) > 0
                   |""".stripMargin
              )
          }
      } yield ()
    }

    "Instant" - validation { implicit conn =>
      for {
        _ <- Type.instants_?.insert(Some(Set(instant1, instant2, instant4))).transact
          .map(_ ==> "Unexpected success").recover {
            case InsertErrors(errors, _) =>
              errors.head._2.head.errors ==> Seq(
                s"""Type.instants with value `$instant1` doesn't satisfy validation:
                   |  _.compareTo(Instant.ofEpochSecond(2)) > 0
                   |""".stripMargin,
                s"""Type.instants with value `$instant2` doesn't satisfy validation:
                   |  _.compareTo(Instant.ofEpochSecond(2)) > 0
                   |""".stripMargin
              )
          }
      } yield ()
    }

    "LocalDate" - validation { implicit conn =>
      for {
        _ <- Type.localDates_?.insert(Some(Set(localDate1, localDate2, localDate4))).transact
          .map(_ ==> "Unexpected success").recover {
            case InsertErrors(errors, _) =>
              errors.head._2.head.errors ==> Seq(
                s"""Type.localDates with value `$localDate1` doesn't satisfy validation:
                   |  _.compareTo(LocalDate.of(2002, 1, 1)) > 0
                   |""".stripMargin,
                s"""Type.localDates with value `$localDate2` doesn't satisfy validation:
                   |  _.compareTo(LocalDate.of(2002, 1, 1)) > 0
                   |""".stripMargin
              )
          }
      } yield ()
    }

    "LocalTime" - validation { implicit conn =>
      for {
        _ <- Type.localTimes_?.insert(Some(Set(localTime1, localTime2, localTime4))).transact
          .map(_ ==> "Unexpected success").recover {
            case InsertErrors(errors, _) =>
              errors.head._2.head.errors ==> Seq(
                s"""Type.localTimes with value `$localTime1` doesn't satisfy validation:
                   |  _.compareTo(LocalTime.of(2, 2)) > 0
                   |""".stripMargin,
                s"""Type.localTimes with value `$localTime2` doesn't satisfy validation:
                   |  _.compareTo(LocalTime.of(2, 2)) > 0
                   |""".stripMargin
              )
          }
      } yield ()
    }

    "LocalDateTime" - validation { implicit conn =>
      for {
        _ <- Type.localDateTimes_?.insert(Some(Set(localDateTime1, localDateTime2, localDateTime4))).transact
          .map(_ ==> "Unexpected success").recover {
            case InsertErrors(errors, _) =>
              errors.head._2.head.errors ==> Seq(
                s"""Type.localDateTimes with value `$localDateTime1` doesn't satisfy validation:
                   |  _.compareTo(LocalDateTime.of(2002, 1, 1, 1, 2)) > 0
                   |""".stripMargin,
                s"""Type.localDateTimes with value `$localDateTime2` doesn't satisfy validation:
                   |  _.compareTo(LocalDateTime.of(2002, 1, 1, 1, 2)) > 0
                   |""".stripMargin
              )
          }
      } yield ()
    }

    "OffsetTime" - validation { implicit conn =>
      for {
        _ <- Type.offsetTimes_?.insert(Some(Set(offsetTime1, offsetTime2, offsetTime4))).transact
          .map(_ ==> "Unexpected success").recover {
            case InsertErrors(errors, _) =>
              errors.head._2.head.errors ==> Seq(
                s"""Type.offsetTimes with value `$offsetTime1` doesn't satisfy validation:
                   |  _.compareTo(OffsetTime.of(2, 2, 2, 2, ZoneOffset.ofHours(2))) > 0
                   |""".stripMargin,
                s"""Type.offsetTimes with value `$offsetTime2` doesn't satisfy validation:
                   |  _.compareTo(OffsetTime.of(2, 2, 2, 2, ZoneOffset.ofHours(2))) > 0
                   |""".stripMargin
              )
          }
      } yield ()
    }

    "OffsetDateTime" - validation { implicit conn =>
      for {
        _ <- Type.offsetDateTimes_?.insert(Some(Set(offsetDateTime1, offsetDateTime2, offsetDateTime4))).transact
          .map(_ ==> "Unexpected success").recover {
            case InsertErrors(errors, _) =>
              errors.head._2.head.errors ==> Seq(
                s"""Type.offsetDateTimes with value `$offsetDateTime1` doesn't satisfy validation:
                   |  _.compareTo(OffsetDateTime.of(2002, 1, 1, 1, 1, 1, 1, ZoneOffset.ofHours(2))) > 0
                   |""".stripMargin,
                s"""Type.offsetDateTimes with value `$offsetDateTime2` doesn't satisfy validation:
                   |  _.compareTo(OffsetDateTime.of(2002, 1, 1, 1, 1, 1, 1, ZoneOffset.ofHours(2))) > 0
                   |""".stripMargin
              )
          }
      } yield ()
    }

    "ZonedDateTime" - validation { implicit conn =>
      for {
        _ <- Type.zonedDateTimes_?.insert(Some(Set(zonedDateTime1, zonedDateTime2, zonedDateTime4))).transact
          .map(_ ==> "Unexpected success").recover {
            case InsertErrors(errors, _) =>
              errors.head._2.head.errors ==> Seq(
                s"""Type.zonedDateTimes with value `$zonedDateTime1` doesn't satisfy validation:
                   |  _.compareTo(ZonedDateTime.of(2002, 1, 1, 1, 1, 1, 1, ZoneOffset.ofHours(2))) > 0
                   |""".stripMargin,
                s"""Type.zonedDateTimes with value `$zonedDateTime2` doesn't satisfy validation:
                   |  _.compareTo(ZonedDateTime.of(2002, 1, 1, 1, 1, 1, 1, ZoneOffset.ofHours(2))) > 0
                   |""".stripMargin
              )
          }
      } yield ()
    }

    "UUID" - validation { implicit conn =>
      for {
        _ <- Type.uuids.insert(Set(
            UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"),
            UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-bbbbbbbbbbbb")
          )).transact
          .map(_ ==> "Unexpected success").recover {
            case InsertErrors(errors, _) =>
              errors.head._2.head.errors ==> Seq(
                s"""Type.uuids with value `aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa` doesn't satisfy validation:
                   |  _.toString != "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"
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
        _ <- Type.uris_?.insert(Some(Set(uri0, uri1, uri2))).transact
          .map(_ ==> "Unexpected success").recover {
            case InsertErrors(errors, _) =>
              errors.head._2.head.errors ==> Seq(
                s"""Type.uris with value `a` doesn't satisfy validation:
                   |  _.toString.length > 3
                   |""".stripMargin,
                s"""Type.uris with value `ab` doesn't satisfy validation:
                   |  _.toString.length > 3
                   |""".stripMargin
              )
          }
      } yield ()
    }

    "Byte" - validation { implicit conn =>
      for {
        _ <- Type.bytes_?.insert(Some(Set(byte1, byte2, byte4))).transact
          .map(_ ==> "Unexpected success").recover {
            case InsertErrors(errors, _) =>
              errors.head._2.head.errors ==> Seq(
                s"""Type.bytes with value `$byte1` doesn't satisfy validation:
                   |  _ > $byte3
                   |""".stripMargin,
                s"""Type.bytes with value `$byte2` doesn't satisfy validation:
                   |  _ > $byte3
                   |""".stripMargin
              )
          }
      } yield ()
    }

    "Short" - validation { implicit conn =>
      for {
        _ <- Type.shorts_?.insert(Some(Set(short1, short2, short4))).transact
          .map(_ ==> "Unexpected success").recover {
            case InsertErrors(errors, _) =>
              errors.head._2.head.errors ==> Seq(
                s"""Type.shorts with value `$short1` doesn't satisfy validation:
                   |  _ > $short3
                   |""".stripMargin,
                s"""Type.shorts with value `$short2` doesn't satisfy validation:
                   |  _ > $short3
                   |""".stripMargin
              )
          }
      } yield ()
    }

    "Char" - validation { implicit conn =>
      for {
        _ <- Type.chars_?.insert(Some(Set('a', 'b', 'd'))).transact
          .map(_ ==> "Unexpected success").recover {
            case InsertErrors(errors, _) =>
              errors.head._2.head.errors ==> Seq(
                s"""Type.chars with value `a` doesn't satisfy validation:
                   |  _ > 'c'
                   |""".stripMargin,
                s"""Type.chars with value `b` doesn't satisfy validation:
                   |  _ > 'c'
                   |""".stripMargin
              )
          }
      } yield ()
    }

    "ref" - validation { implicit conn =>
      for {
        _ <- Type.refs_?.insert(Some(Set(1L, 2L, 4L))).transact
          .map(_ ==> "Unexpected success").recover {
            case InsertErrors(errors, _) =>
              errors.head._2.head.errors ==> Seq(
                s"""Type.refs with value `1` doesn't satisfy validation:
                   |  _ > 3L
                   |""".stripMargin,
                s"""Type.refs with value `2` doesn't satisfy validation:
                   |  _ > 3L
                   |""".stripMargin
              )
          }
      } yield ()
    }
  }
}
