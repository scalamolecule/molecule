package molecule.db.compliance.test.validation.insert

import java.net.URI
import java.util.UUID
import molecule.core.error.{InsertError, InsertErrors}
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Validation.*
import molecule.db.compliance.setup.DbProviders


case class TypesSeq(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "Types" - validation {
    for {
      _ <- Tpe.stringSeq.insert(Seq("a", "b", "d")).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(indexedInsertErrors, _) =>
            indexedInsertErrors ==>
              Seq(
                (
                  0, // Row index
                  Seq(
                    InsertError(
                      0, // tuple index
                      "Tpe.stringSeq",
                      Seq(
                        s"""Tpe.stringSeq with value `a` doesn't satisfy validation:
                           |_ > "c"
                           |""".stripMargin,
                        s"""Tpe.stringSeq with value `b` doesn't satisfy validation:
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
      _ <- Tpe.stringSeq.insert(Seq("a", "b", "d")).transact
        .map(_ ==> "Unexpected success").recover {

          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
              s"""Tpe.stringSeq with value `a` doesn't satisfy validation:
                 |_ > "c"
                 |""".stripMargin,
              s"""Tpe.stringSeq with value `b` doesn't satisfy validation:
                 |_ > "c"
                 |""".stripMargin
            )
        }


      _ <- Tpe.intSeq.insert(Seq(1, 2, 4)).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
              s"""Tpe.intSeq with value `1` doesn't satisfy validation:
                 |_ > 3
                 |""".stripMargin,
              s"""Tpe.intSeq with value `2` doesn't satisfy validation:
                 |_ > 3
                 |""".stripMargin
            )
        }

      _ <- Tpe.longSeq.insert(Seq(1L, 2L, 4L)).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
              s"""Tpe.longSeq with value `1` doesn't satisfy validation:
                 |_ > 3L
                 |""".stripMargin,
              s"""Tpe.longSeq with value `2` doesn't satisfy validation:
                 |_ > 3L
                 |""".stripMargin
            )
        }

      _ <- Tpe.floatSeq.insert(Seq(float1, float2, float4)).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
              s"""Tpe.floatSeq with value `$float1` doesn't satisfy validation:
                 |_ > 3.3f
                 |""".stripMargin,
              s"""Tpe.floatSeq with value `$float2` doesn't satisfy validation:
                 |_ > 3.3f
                 |""".stripMargin
            )
        }

      _ <- Tpe.doubleSeq.insert(Seq(double1, double2, double4)).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
              s"""Tpe.doubleSeq with value `$double1` doesn't satisfy validation:
                 |_ > 3.3
                 |""".stripMargin,
              s"""Tpe.doubleSeq with value `$double2` doesn't satisfy validation:
                 |_ > 3.3
                 |""".stripMargin
            )
        }

      _ <- Tpe.booleanSeq.insert(Seq(true, false)).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
              s"""Tpe.booleanSeq with value `true` doesn't satisfy validation:
                 |_ == false
                 |""".stripMargin
            )
        }

      _ <- Tpe.bigIntSeq.insert(Seq(bigInt1, bigInt2, bigInt4)).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
              s"""Tpe.bigIntSeq with value `$bigInt1` doesn't satisfy validation:
                 |_ > BigInt(3)
                 |""".stripMargin,
              s"""Tpe.bigIntSeq with value `$bigInt2` doesn't satisfy validation:
                 |_ > BigInt(3)
                 |""".stripMargin
            )
        }

      _ <- Tpe.bigDecimalSeq.insert(Seq(bigDecimal1, bigDecimal2, bigDecimal4)).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
              s"""Tpe.bigDecimalSeq with value `$bigDecimal1` doesn't satisfy validation:
                 |_ > BigDecimal(3.3)
                 |""".stripMargin,
              s"""Tpe.bigDecimalSeq with value `$bigDecimal2` doesn't satisfy validation:
                 |_ > BigDecimal(3.3)
                 |""".stripMargin
            )
        }

      _ <- Tpe.dateSeq.insert(Seq(date1, date2, date4)).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
              s"""Tpe.dateSeq with value `$date1` doesn't satisfy validation:
                 |_.after(new Date(1057010400000L))
                 |""".stripMargin,
              s"""Tpe.dateSeq with value `$date2` doesn't satisfy validation:
                 |_.after(new Date(1057010400000L))
                 |""".stripMargin
            )
        }

      _ <- Tpe.durationSeq.insert(Seq(duration1, duration2, duration4)).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
              s"""Tpe.durationSeq with value `$duration1` doesn't satisfy validation:
                 |_.compareTo(Duration.ofMinutes(2)) > 0
                 |""".stripMargin,
              s"""Tpe.durationSeq with value `$duration2` doesn't satisfy validation:
                 |_.compareTo(Duration.ofMinutes(2)) > 0
                 |""".stripMargin
            )
        }

      _ <- Tpe.instantSeq.insert(Seq(instant1, instant2, instant4)).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
              s"""Tpe.instantSeq with value `$instant1` doesn't satisfy validation:
                 |_.compareTo(Instant.ofEpochSecond(2)) > 0
                 |""".stripMargin,
              s"""Tpe.instantSeq with value `$instant2` doesn't satisfy validation:
                 |_.compareTo(Instant.ofEpochSecond(2)) > 0
                 |""".stripMargin
            )
        }

      _ <- Tpe.localDateSeq.insert(Seq(localDate1, localDate2, localDate4)).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
              s"""Tpe.localDateSeq with value `$localDate1` doesn't satisfy validation:
                 |_.compareTo(LocalDate.of(2002, 1, 1)) > 0
                 |""".stripMargin,
              s"""Tpe.localDateSeq with value `$localDate2` doesn't satisfy validation:
                 |_.compareTo(LocalDate.of(2002, 1, 1)) > 0
                 |""".stripMargin
            )
        }

      _ <- Tpe.localTimeSeq.insert(Seq(localTime1, localTime2, localTime4)).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
              s"""Tpe.localTimeSeq with value `$localTime1` doesn't satisfy validation:
                 |_.compareTo(LocalTime.of(2, 2)) > 0
                 |""".stripMargin,
              s"""Tpe.localTimeSeq with value `$localTime2` doesn't satisfy validation:
                 |_.compareTo(LocalTime.of(2, 2)) > 0
                 |""".stripMargin
            )
        }

      _ <- Tpe.localDateTimeSeq.insert(Seq(localDateTime1, localDateTime2, localDateTime4)).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
              s"""Tpe.localDateTimeSeq with value `$localDateTime1` doesn't satisfy validation:
                 |_.compareTo(LocalDateTime.of(2002, 1, 1, 1, 2)) > 0
                 |""".stripMargin,
              s"""Tpe.localDateTimeSeq with value `$localDateTime2` doesn't satisfy validation:
                 |_.compareTo(LocalDateTime.of(2002, 1, 1, 1, 2)) > 0
                 |""".stripMargin
            )
        }

      _ <- Tpe.offsetTimeSeq.insert(Seq(offsetTime1, offsetTime2, offsetTime4)).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
              s"""Tpe.offsetTimeSeq with value `$offsetTime1` doesn't satisfy validation:
                 |_.compareTo(OffsetTime.of(2, 2, 2, 2, ZoneOffset.ofHours(2))) > 0
                 |""".stripMargin,
              s"""Tpe.offsetTimeSeq with value `$offsetTime2` doesn't satisfy validation:
                 |_.compareTo(OffsetTime.of(2, 2, 2, 2, ZoneOffset.ofHours(2))) > 0
                 |""".stripMargin
            )
        }

      _ <- Tpe.offsetDateTimeSeq.insert(Seq(offsetDateTime1, offsetDateTime2, offsetDateTime4)).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
              s"""Tpe.offsetDateTimeSeq with value `$offsetDateTime1` doesn't satisfy validation:
                 |_.compareTo(OffsetDateTime.of(2002, 1, 1, 1, 1, 1, 1, ZoneOffset.ofHours(2))) > 0
                 |""".stripMargin,
              s"""Tpe.offsetDateTimeSeq with value `$offsetDateTime2` doesn't satisfy validation:
                 |_.compareTo(OffsetDateTime.of(2002, 1, 1, 1, 1, 1, 1, ZoneOffset.ofHours(2))) > 0
                 |""".stripMargin
            )
        }

      _ <- Tpe.zonedDateTimeSeq.insert(Seq(zonedDateTime1, zonedDateTime2, zonedDateTime4)).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
              s"""Tpe.zonedDateTimeSeq with value `$zonedDateTime1` doesn't satisfy validation:
                 |_.compareTo(ZonedDateTime.of(2002, 1, 1, 1, 1, 1, 1, ZoneOffset.ofHours(2))) > 0
                 |""".stripMargin,
              s"""Tpe.zonedDateTimeSeq with value `$zonedDateTime2` doesn't satisfy validation:
                 |_.compareTo(ZonedDateTime.of(2002, 1, 1, 1, 1, 1, 1, ZoneOffset.ofHours(2))) > 0
                 |""".stripMargin
            )
        }

      _ <- Tpe.uuidSeq.insert(Seq(
          UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"),
          UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-bbbbbbbbbbbb")
        )).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
              s"""Tpe.uuidSeq with value `aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa` doesn't satisfy validation:
                 |_.toString != "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"
                 |""".stripMargin
            )
        }


      uri0 = new URI("a")
      uri1 = new URI("ab")
      uri2 = new URI("abcd")
      _ <- Tpe.uriSeq.insert(Seq(uri0, uri1, uri2)).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
              s"""Tpe.uriSeq with value `a` doesn't satisfy validation:
                 |_.toString.length > 3
                 |""".stripMargin,
              s"""Tpe.uriSeq with value `ab` doesn't satisfy validation:
                 |_.toString.length > 3
                 |""".stripMargin
            )
        }

      _ <- Tpe.shortSeq.insert(Seq(short1, short2, short4)).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
              s"""Tpe.shortSeq with value `$short1` doesn't satisfy validation:
                 |_ > $short3
                 |""".stripMargin,
              s"""Tpe.shortSeq with value `$short2` doesn't satisfy validation:
                 |_ > $short3
                 |""".stripMargin
            )
        }

      _ <- Tpe.charSeq.insert(Seq('a', 'b', 'd')).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
              s"""Tpe.charSeq with value `a` doesn't satisfy validation:
                 |_ > 'c'
                 |""".stripMargin,
              s"""Tpe.charSeq with value `b` doesn't satisfy validation:
                 |_ > 'c'
                 |""".stripMargin
            )
        }
    } yield ()
  }
}
