package molecule.db.compliance.test.validation.insert

import java.net.URI
import java.util.UUID
import molecule.base.error.{InsertError, InsertErrors}
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Validation.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*


case class TypesOneOpt(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "Types" - validation {
    for {
      _ <- Type.string_?.insert(Some("a")).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(indexedInsertErrors, _) =>
            indexedInsertErrors ==>
              Seq(
                (
                  0, // Row index
                  Seq(
                    InsertError(
                      0, // tuple index
                      "Type.string",
                      Seq(
                        s"""Type.string with value `a` doesn't satisfy validation:
                           |_ > "b"
                           |""".stripMargin
                      ),
                      Nil // nested errors
                    )
                  )
                )
              )
        }

      // Isolate expected error
      _ <- Type.string_?.insert(Some("a")).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors.head ==>
              s"""Type.string with value `a` doesn't satisfy validation:
                 |_ > "b"
                 |""".stripMargin
        }


      _ <- Type.int_?.insert(Some(1)).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors.head ==>
              s"""Type.int with value `1` doesn't satisfy validation:
                 |_ > 2
                 |""".stripMargin
        }

      _ <- Type.long_?.insert(Some(1L)).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors.head ==>
              s"""Type.long with value `1` doesn't satisfy validation:
                 |_ > 2L
                 |""".stripMargin
        }

      _ <- Type.float_?.insert(Some(float1)).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors.head ==>
              s"""Type.float with value `$float1` doesn't satisfy validation:
                 |_ > 2.2f
                 |""".stripMargin
        }

      _ <- Type.double_?.insert(Some(double1)).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors.head ==>
              s"""Type.double with value `$double1` doesn't satisfy validation:
                 |_ > 2.2
                 |""".stripMargin
        }

      _ <- Type.boolean_?.insert(Some(true)).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors.head ==>
              s"""Type.boolean with value `true` doesn't satisfy validation:
                 |_ == false
                 |""".stripMargin
        }

      _ <- Type.bigInt_?.insert(Some(bigInt1)).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors.head ==>
              s"""Type.bigInt with value `$bigInt1` doesn't satisfy validation:
                 |_ > BigInt(2)
                 |""".stripMargin
        }

      _ <- Type.bigDecimal_?.insert(Some(bigDecimal1)).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors.head ==>
              s"""Type.bigDecimal with value `$bigDecimal1` doesn't satisfy validation:
                 |_ > BigDecimal(2.2)
                 |""".stripMargin
        }

      _ <- Type.date_?.insert(Some(date1)).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors.head ==>
              s"""Type.date with value `$date1` doesn't satisfy validation:
                 |_.after(new Date(993942000000L))
                 |""".stripMargin
        }

      _ <- Type.duration_?.insert(Some(duration1)).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors.head ==>
              s"""Type.duration with value `$duration1` doesn't satisfy validation:
                 |_.compareTo(Duration.ofMinutes(2)) > 0
                 |""".stripMargin
        }

      _ <- Type.instant_?.insert(Some(instant1)).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors.head ==>
              s"""Type.instant with value `$instant1` doesn't satisfy validation:
                 |_.compareTo(Instant.ofEpochSecond(2)) > 0
                 |""".stripMargin
        }

      _ <- Type.localDate_?.insert(Some(localDate1)).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors.head ==>
              s"""Type.localDate with value `$localDate1` doesn't satisfy validation:
                 |_.compareTo(LocalDate.of(2002, 1, 1)) > 0
                 |""".stripMargin
        }

      _ <- Type.localTime_?.insert(Some(localTime1)).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors.head ==>
              s"""Type.localTime with value `$localTime1` doesn't satisfy validation:
                 |_.compareTo(LocalTime.of(2, 2)) > 0
                 |""".stripMargin
        }

      _ <- Type.localDateTime_?.insert(Some(localDateTime1)).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors.head ==>
              s"""Type.localDateTime with value `$localDateTime1` doesn't satisfy validation:
                 |_.compareTo(LocalDateTime.of(2002, 1, 1, 1, 2)) > 0
                 |""".stripMargin
        }

      _ <- Type.offsetTime_?.insert(Some(offsetTime1)).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors.head ==>
              s"""Type.offsetTime with value `$offsetTime1` doesn't satisfy validation:
                 |_.compareTo(OffsetTime.of(2, 2, 2, 2, ZoneOffset.ofHours(2))) > 0
                 |""".stripMargin
        }

      _ <- Type.offsetDateTime_?.insert(Some(offsetDateTime1)).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors.head ==>
              s"""Type.offsetDateTime with value `$offsetDateTime1` doesn't satisfy validation:
                 |_.compareTo(OffsetDateTime.of(2002, 1, 1, 1, 1, 1, 1, ZoneOffset.ofHours(2))) > 0
                 |""".stripMargin
        }

      _ <- Type.zonedDateTime_?.insert(Some(zonedDateTime1)).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors.head ==>
              s"""Type.zonedDateTime with value `$zonedDateTime1` doesn't satisfy validation:
                 |_.compareTo(ZonedDateTime.of(2002, 1, 1, 1, 1, 1, 1, ZoneOffset.ofHours(2))) > 0
                 |""".stripMargin
        }

      _ <- Type.uuid_?.insert(Some(UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"))).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors.head ==>
              s"""Type.uuid with value `aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa` doesn't satisfy validation:
                 |_.toString != "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"
                 |""".stripMargin
        }


      uri = new URI("x")
      _ <- Type.uri_?.insert(Some(uri)).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors.head ==>
              s"""Type.uri with value `x` doesn't satisfy validation:
                 |_.toString.length > 2
                 |""".stripMargin
        }

      _ <- Type.byte_?.insert(Some(byte1)).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors.head ==>
              s"""Type.byte with value `$byte1` doesn't satisfy validation:
                 |_ > $byte2
                 |""".stripMargin
        }

      _ <- Type.short_?.insert(Some(short1)).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors.head ==>
              s"""Type.short with value `$short1` doesn't satisfy validation:
                 |_ > $short2
                 |""".stripMargin
        }

      _ <- Type.char_?.insert(Some('a')).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors.head ==>
              s"""Type.char with value `a` doesn't satisfy validation:
                 |_ > 'b'
                 |""".stripMargin
        }
    } yield ()
  }
}
