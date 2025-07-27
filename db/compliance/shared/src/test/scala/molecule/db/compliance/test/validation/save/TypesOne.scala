package molecule.db.compliance.test.validation.save

import java.net.URI
import java.util.UUID
import molecule.base.error.ValidationErrors
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Validation.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*


case class TypesOne(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "Types" - validation {
    for {
      _ <- Type.string("a").save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap ==>
              Map(
                "Type.string" -> Seq(
                  s"""Type.string with value `a` doesn't satisfy validation:
                     |_ > "b"
                     |""".stripMargin
                )
              )
        }

      // Focusing only on the first (and only) error message
      // (See ValidationFormatting tests for multi-error validations)
      _ <- Type.string("a").save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Type.string with value `a` doesn't satisfy validation:
                 |_ > "b"
                 |""".stripMargin
        }

      _ <- Type.int(1).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Type.int with value `1` doesn't satisfy validation:
                 |_ > 2
                 |""".stripMargin
        }

      _ <- Type.long(1L).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Type.long with value `1` doesn't satisfy validation:
                 |_ > 2L
                 |""".stripMargin
        }

      _ <- Type.float(float1).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Type.float with value `$float1` doesn't satisfy validation:
                 |_ > 2.2f
                 |""".stripMargin
        }

      _ <- Type.double(double1).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Type.double with value `$double1` doesn't satisfy validation:
                 |_ > 2.2
                 |""".stripMargin
        }

      _ <- Type.boolean(true).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Type.boolean with value `true` doesn't satisfy validation:
                 |_ == false
                 |""".stripMargin
        }

      _ <- Type.bigInt(bigInt1).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Type.bigInt with value `$bigInt1` doesn't satisfy validation:
                 |_ > BigInt(2)
                 |""".stripMargin
        }


      _ <- Type.bigDecimal(bigDecimal1).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Type.bigDecimal with value `$bigDecimal1` doesn't satisfy validation:
                 |_ > BigDecimal(2.2)
                 |""".stripMargin
        }

      _ <- Type.date(date1).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Type.date with value `$date1` doesn't satisfy validation:
                 |_.after(new Date(993942000000L))
                 |""".stripMargin
        }

      _ <- Type.duration(duration1).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Type.duration with value `$duration1` doesn't satisfy validation:
                 |_.compareTo(Duration.ofMinutes(2)) > 0
                 |""".stripMargin
        }

      _ <- Type.instant(instant1).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Type.instant with value `$instant1` doesn't satisfy validation:
                 |_.compareTo(Instant.ofEpochSecond(2)) > 0
                 |""".stripMargin
        }

      _ <- Type.localDate(localDate1).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Type.localDate with value `$localDate1` doesn't satisfy validation:
                 |_.compareTo(LocalDate.of(2002, 1, 1)) > 0
                 |""".stripMargin
        }

      _ <- Type.localTime(localTime1).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Type.localTime with value `$localTime1` doesn't satisfy validation:
                 |_.compareTo(LocalTime.of(2, 2)) > 0
                 |""".stripMargin
        }

      _ <- Type.localDateTime(localDateTime1).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Type.localDateTime with value `$localDateTime1` doesn't satisfy validation:
                 |_.compareTo(LocalDateTime.of(2002, 1, 1, 1, 2)) > 0
                 |""".stripMargin
        }

      _ <- Type.offsetTime(offsetTime1).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Type.offsetTime with value `$offsetTime1` doesn't satisfy validation:
                 |_.compareTo(OffsetTime.of(2, 2, 2, 2, ZoneOffset.ofHours(2))) > 0
                 |""".stripMargin
        }

      _ <- Type.offsetDateTime(offsetDateTime1).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Type.offsetDateTime with value `$offsetDateTime1` doesn't satisfy validation:
                 |_.compareTo(OffsetDateTime.of(2002, 1, 1, 1, 1, 1, 1, ZoneOffset.ofHours(2))) > 0
                 |""".stripMargin
        }

      _ <- Type.zonedDateTime(zonedDateTime1).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Type.zonedDateTime with value `$zonedDateTime1` doesn't satisfy validation:
                 |_.compareTo(ZonedDateTime.of(2002, 1, 1, 1, 1, 1, 1, ZoneOffset.ofHours(2))) > 0
                 |""".stripMargin
        }

      _ <- Type.uuid(UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa")).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Type.uuid with value `aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa` doesn't satisfy validation:
                 |_.toString != "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"
                 |""".stripMargin
        }

      uri = new URI("x")
      _ <- Type.uri(uri).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Type.uri with value `x` doesn't satisfy validation:
                 |_.toString.length > 2
                 |""".stripMargin
        }

      _ <- Type.byte(byte1).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Type.byte with value `$byte1` doesn't satisfy validation:
                 |_ > $byte2
                 |""".stripMargin
        }

      _ <- Type.short(short1).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Type.short with value `$short1` doesn't satisfy validation:
                 |_ > $short2
                 |""".stripMargin
        }

      _ <- Type.char('a').save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Type.char with value `a` doesn't satisfy validation:
                 |_ > 'b'
                 |""".stripMargin
        }
    } yield ()
  }
}
