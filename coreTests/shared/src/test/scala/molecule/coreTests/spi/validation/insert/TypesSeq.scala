package molecule.coreTests.spi.validation.insert

import java.net.URI
import java.util.UUID
import molecule.base.error.*
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor.*
import molecule.coreTests.domains.dsl.Validation.*
import molecule.coreTests.setup.*
//import scala.language.implicitConversions

case class TypesSeq(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "Types" - validation { implicit conn =>
    for {
      _ <- Type.stringSeq.insert(Seq("a", "b", "d")).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(indexedInsertErrors, _) =>
            indexedInsertErrors ==>
              Seq(
                (
                  0, // Row index
                  Seq(
                    InsertError(
                      0, // tuple index
                      "Type.stringSeq",
                      Seq(
                        s"""Type.stringSeq with value `a` doesn't satisfy validation:
                           |_ > "c"
                           |""".stripMargin,
                        s"""Type.stringSeq with value `b` doesn't satisfy validation:
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
      _ <- Type.stringSeq.insert(Seq("a", "b", "d")).transact
        .map(_ ==> "Unexpected success").recover {

          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
              s"""Type.stringSeq with value `a` doesn't satisfy validation:
                 |_ > "c"
                 |""".stripMargin,
              s"""Type.stringSeq with value `b` doesn't satisfy validation:
                 |_ > "c"
                 |""".stripMargin
            )
        }


      _ <- Type.intSeq.insert(Seq(1, 2, 4)).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
              s"""Type.intSeq with value `1` doesn't satisfy validation:
                 |_ > 3
                 |""".stripMargin,
              s"""Type.intSeq with value `2` doesn't satisfy validation:
                 |_ > 3
                 |""".stripMargin
            )
        }

      _ <- Type.longSeq.insert(Seq(1L, 2L, 4L)).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
              s"""Type.longSeq with value `1` doesn't satisfy validation:
                 |_ > 3L
                 |""".stripMargin,
              s"""Type.longSeq with value `2` doesn't satisfy validation:
                 |_ > 3L
                 |""".stripMargin
            )
        }

      _ <- Type.floatSeq.insert(Seq(float1, float2, float4)).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
              s"""Type.floatSeq with value `$float1` doesn't satisfy validation:
                 |_ > 3.3f
                 |""".stripMargin,
              s"""Type.floatSeq with value `$float2` doesn't satisfy validation:
                 |_ > 3.3f
                 |""".stripMargin
            )
        }

      _ <- Type.doubleSeq.insert(Seq(double1, double2, double4)).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
              s"""Type.doubleSeq with value `$double1` doesn't satisfy validation:
                 |_ > 3.3
                 |""".stripMargin,
              s"""Type.doubleSeq with value `$double2` doesn't satisfy validation:
                 |_ > 3.3
                 |""".stripMargin
            )
        }

      _ <- Type.booleanSeq.insert(Seq(true, false)).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
              s"""Type.booleanSeq with value `true` doesn't satisfy validation:
                 |_ == false
                 |""".stripMargin
            )
        }

      _ <- Type.bigIntSeq.insert(Seq(bigInt1, bigInt2, bigInt4)).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
              s"""Type.bigIntSeq with value `$bigInt1` doesn't satisfy validation:
                 |_ > BigInt(3)
                 |""".stripMargin,
              s"""Type.bigIntSeq with value `$bigInt2` doesn't satisfy validation:
                 |_ > BigInt(3)
                 |""".stripMargin
            )
        }

      _ <- Type.bigDecimalSeq.insert(Seq(bigDecimal1, bigDecimal2, bigDecimal4)).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
              s"""Type.bigDecimalSeq with value `$bigDecimal1` doesn't satisfy validation:
                 |_ > BigDecimal(3.3)
                 |""".stripMargin,
              s"""Type.bigDecimalSeq with value `$bigDecimal2` doesn't satisfy validation:
                 |_ > BigDecimal(3.3)
                 |""".stripMargin
            )
        }

      _ <- Type.dateSeq.insert(Seq(date1, date2, date4)).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
              s"""Type.dateSeq with value `$date1` doesn't satisfy validation:
                 |_.after(new Date(1057010400000L))
                 |""".stripMargin,
              s"""Type.dateSeq with value `$date2` doesn't satisfy validation:
                 |_.after(new Date(1057010400000L))
                 |""".stripMargin
            )
        }

      _ <- Type.durationSeq.insert(Seq(duration1, duration2, duration4)).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
              s"""Type.durationSeq with value `$duration1` doesn't satisfy validation:
                 |_.compareTo(Duration.ofMinutes(2)) > 0
                 |""".stripMargin,
              s"""Type.durationSeq with value `$duration2` doesn't satisfy validation:
                 |_.compareTo(Duration.ofMinutes(2)) > 0
                 |""".stripMargin
            )
        }

      _ <- Type.instantSeq.insert(Seq(instant1, instant2, instant4)).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
              s"""Type.instantSeq with value `$instant1` doesn't satisfy validation:
                 |_.compareTo(Instant.ofEpochSecond(2)) > 0
                 |""".stripMargin,
              s"""Type.instantSeq with value `$instant2` doesn't satisfy validation:
                 |_.compareTo(Instant.ofEpochSecond(2)) > 0
                 |""".stripMargin
            )
        }

      _ <- Type.localDateSeq.insert(Seq(localDate1, localDate2, localDate4)).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
              s"""Type.localDateSeq with value `$localDate1` doesn't satisfy validation:
                 |_.compareTo(LocalDate.of(2002, 1, 1)) > 0
                 |""".stripMargin,
              s"""Type.localDateSeq with value `$localDate2` doesn't satisfy validation:
                 |_.compareTo(LocalDate.of(2002, 1, 1)) > 0
                 |""".stripMargin
            )
        }

      _ <- Type.localTimeSeq.insert(Seq(localTime1, localTime2, localTime4)).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
              s"""Type.localTimeSeq with value `$localTime1` doesn't satisfy validation:
                 |_.compareTo(LocalTime.of(2, 2)) > 0
                 |""".stripMargin,
              s"""Type.localTimeSeq with value `$localTime2` doesn't satisfy validation:
                 |_.compareTo(LocalTime.of(2, 2)) > 0
                 |""".stripMargin
            )
        }

      _ <- Type.localDateTimeSeq.insert(Seq(localDateTime1, localDateTime2, localDateTime4)).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
              s"""Type.localDateTimeSeq with value `$localDateTime1` doesn't satisfy validation:
                 |_.compareTo(LocalDateTime.of(2002, 1, 1, 1, 2)) > 0
                 |""".stripMargin,
              s"""Type.localDateTimeSeq with value `$localDateTime2` doesn't satisfy validation:
                 |_.compareTo(LocalDateTime.of(2002, 1, 1, 1, 2)) > 0
                 |""".stripMargin
            )
        }

      _ <- Type.offsetTimeSeq.insert(Seq(offsetTime1, offsetTime2, offsetTime4)).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
              s"""Type.offsetTimeSeq with value `$offsetTime1` doesn't satisfy validation:
                 |_.compareTo(OffsetTime.of(2, 2, 2, 2, ZoneOffset.ofHours(2))) > 0
                 |""".stripMargin,
              s"""Type.offsetTimeSeq with value `$offsetTime2` doesn't satisfy validation:
                 |_.compareTo(OffsetTime.of(2, 2, 2, 2, ZoneOffset.ofHours(2))) > 0
                 |""".stripMargin
            )
        }

      _ <- Type.offsetDateTimeSeq.insert(Seq(offsetDateTime1, offsetDateTime2, offsetDateTime4)).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
              s"""Type.offsetDateTimeSeq with value `$offsetDateTime1` doesn't satisfy validation:
                 |_.compareTo(OffsetDateTime.of(2002, 1, 1, 1, 1, 1, 1, ZoneOffset.ofHours(2))) > 0
                 |""".stripMargin,
              s"""Type.offsetDateTimeSeq with value `$offsetDateTime2` doesn't satisfy validation:
                 |_.compareTo(OffsetDateTime.of(2002, 1, 1, 1, 1, 1, 1, ZoneOffset.ofHours(2))) > 0
                 |""".stripMargin
            )
        }

      _ <- Type.zonedDateTimeSeq.insert(Seq(zonedDateTime1, zonedDateTime2, zonedDateTime4)).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
              s"""Type.zonedDateTimeSeq with value `$zonedDateTime1` doesn't satisfy validation:
                 |_.compareTo(ZonedDateTime.of(2002, 1, 1, 1, 1, 1, 1, ZoneOffset.ofHours(2))) > 0
                 |""".stripMargin,
              s"""Type.zonedDateTimeSeq with value `$zonedDateTime2` doesn't satisfy validation:
                 |_.compareTo(ZonedDateTime.of(2002, 1, 1, 1, 1, 1, 1, ZoneOffset.ofHours(2))) > 0
                 |""".stripMargin
            )
        }

      _ <- Type.uuidSeq.insert(Seq(
          UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"),
          UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-bbbbbbbbbbbb")
        )).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
              s"""Type.uuidSeq with value `aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa` doesn't satisfy validation:
                 |_.toString != "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"
                 |""".stripMargin
            )
        }


      uri0 = new URI("a")
      uri1 = new URI("ab")
      uri2 = new URI("abcd")
      _ <- Type.uriSeq.insert(Seq(uri0, uri1, uri2)).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
              s"""Type.uriSeq with value `a` doesn't satisfy validation:
                 |_.toString.length > 3
                 |""".stripMargin,
              s"""Type.uriSeq with value `ab` doesn't satisfy validation:
                 |_.toString.length > 3
                 |""".stripMargin
            )
        }

      _ <- Type.shortSeq.insert(Seq(short1, short2, short4)).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
              s"""Type.shortSeq with value `$short1` doesn't satisfy validation:
                 |_ > $short3
                 |""".stripMargin,
              s"""Type.shortSeq with value `$short2` doesn't satisfy validation:
                 |_ > $short3
                 |""".stripMargin
            )
        }

      _ <- Type.charSeq.insert(Seq('a', 'b', 'd')).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
              s"""Type.charSeq with value `a` doesn't satisfy validation:
                 |_ > 'c'
                 |""".stripMargin,
              s"""Type.charSeq with value `b` doesn't satisfy validation:
                 |_ > 'c'
                 |""".stripMargin
            )
        }
    } yield ()
  }
}
