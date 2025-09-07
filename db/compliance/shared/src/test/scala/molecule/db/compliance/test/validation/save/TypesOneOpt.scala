package molecule.db.compliance.test.validation.save

import java.net.URI
import java.util.UUID
import molecule.core.error.ValidationErrors
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Validation.*
import molecule.db.compliance.setup.DbProviders


case class TypesOneOpt(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "Types" - validation {
    for {
      _ <- Tpe.string_?(Some("a")).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap ==>
              Map(
                "Tpe.string" -> Seq(
                  s"""Tpe.string with value `a` doesn't satisfy validation:
                     |_ > "b"
                     |""".stripMargin
                )
              )
        }
      // Focusing only on the first (and only) error message
      // (See ValidationFormatting tests for multi-error validations)
      _ <- Tpe.string_?(Some("a")).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Tpe.string with value `a` doesn't satisfy validation:
                 |_ > "b"
                 |""".stripMargin
        }

      _ <- Tpe.int_?(Some(1)).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Tpe.int with value `1` doesn't satisfy validation:
                 |_ > 2
                 |""".stripMargin
        }

      _ <- Tpe.long_?(Some(1L)).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Tpe.long with value `1` doesn't satisfy validation:
                 |_ > 2L
                 |""".stripMargin
        }

      // Using float0 to avoid rounding discrepancy on scala.js
      _ <- Tpe.float_?(Some(float1)).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Tpe.float with value `$float1` doesn't satisfy validation:
                 |_ > 2.2f
                 |""".stripMargin
        }

      _ <- Tpe.double_?(Some(double1)).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Tpe.double with value `$double1` doesn't satisfy validation:
                 |_ > 2.2
                 |""".stripMargin
        }

      _ <- Tpe.boolean_?(Some(true)).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Tpe.boolean with value `true` doesn't satisfy validation:
                 |_ == false
                 |""".stripMargin
        }

      _ <- Tpe.bigInt_?(Some(bigInt1)).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Tpe.bigInt with value `$bigInt1` doesn't satisfy validation:
                 |_ > BigInt(2)
                 |""".stripMargin
        }

      _ <- Tpe.bigDecimal_?(Some(bigDecimal1)).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Tpe.bigDecimal with value `$bigDecimal1` doesn't satisfy validation:
                 |_ > BigDecimal(2.2)
                 |""".stripMargin
        }

      _ <- Tpe.date_?(Some(date1)).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Tpe.date with value `$date1` doesn't satisfy validation:
                 |_.after(new Date(993942000000L))
                 |""".stripMargin
        }

      _ <- Tpe.duration_?(Some(duration1)).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Tpe.duration with value `$duration1` doesn't satisfy validation:
                 |_.compareTo(Duration.ofMinutes(2)) > 0
                 |""".stripMargin
        }

      _ <- Tpe.instant_?(Some(instant1)).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Tpe.instant with value `$instant1` doesn't satisfy validation:
                 |_.compareTo(Instant.ofEpochSecond(2)) > 0
                 |""".stripMargin
        }

      _ <- Tpe.localDate_?(Some(localDate1)).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Tpe.localDate with value `$localDate1` doesn't satisfy validation:
                 |_.compareTo(LocalDate.of(2002, 1, 1)) > 0
                 |""".stripMargin
        }

      _ <- Tpe.localTime_?(Some(localTime1)).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Tpe.localTime with value `$localTime1` doesn't satisfy validation:
                 |_.compareTo(LocalTime.of(2, 2)) > 0
                 |""".stripMargin
        }

      _ <- Tpe.localDateTime_?(Some(localDateTime1)).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Tpe.localDateTime with value `$localDateTime1` doesn't satisfy validation:
                 |_.compareTo(LocalDateTime.of(2002, 1, 1, 1, 2)) > 0
                 |""".stripMargin
        }

      _ <- Tpe.offsetTime_?(Some(offsetTime1)).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Tpe.offsetTime with value `$offsetTime1` doesn't satisfy validation:
                 |_.compareTo(OffsetTime.of(2, 2, 2, 2, ZoneOffset.ofHours(2))) > 0
                 |""".stripMargin
        }

      _ <- Tpe.offsetDateTime_?(Some(offsetDateTime1)).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Tpe.offsetDateTime with value `$offsetDateTime1` doesn't satisfy validation:
                 |_.compareTo(OffsetDateTime.of(2002, 1, 1, 1, 1, 1, 1, ZoneOffset.ofHours(2))) > 0
                 |""".stripMargin
        }

      _ <- Tpe.zonedDateTime_?(Some(zonedDateTime1)).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Tpe.zonedDateTime with value `$zonedDateTime1` doesn't satisfy validation:
                 |_.compareTo(ZonedDateTime.of(2002, 1, 1, 1, 1, 1, 1, ZoneOffset.ofHours(2))) > 0
                 |""".stripMargin
        }

      _ <- Tpe.uuid_?(Some(UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"))).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Tpe.uuid with value `aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa` doesn't satisfy validation:
                 |_.toString != "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"
                 |""".stripMargin
        }

      uri = new URI("x")
      _ <- Tpe.uri_?(Some(uri)).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Tpe.uri with value `x` doesn't satisfy validation:
                 |_.toString.length > 2
                 |""".stripMargin
        }

      _ <- Tpe.byte_?(Some(byte1)).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Tpe.byte with value `$byte1` doesn't satisfy validation:
                 |_ > $byte2
                 |""".stripMargin
        }

      _ <- Tpe.short_?(Some(short1)).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Tpe.short with value `$short1` doesn't satisfy validation:
                 |_ > $short2
                 |""".stripMargin
        }

      _ <- Tpe.char_?(Some('a')).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Tpe.char with value `a` doesn't satisfy validation:
                 |_ > 'b'
                 |""".stripMargin
        }
    } yield ()
  }
}
