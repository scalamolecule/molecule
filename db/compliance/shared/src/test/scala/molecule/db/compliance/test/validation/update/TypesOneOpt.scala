package molecule.db.compliance.test.validation.update

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
      id <- Tpe.string("c").save.transact.map(_.id)

      _ <- Tpe(id).string_?(Some("a")).update.transact
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

      // Focusing on error message only
      _ <- Tpe(id).string_?(Some("a")).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Tpe.string with value `a` doesn't satisfy validation:
                 |_ > "b"
                 |""".stripMargin
        }

      // Value hasn't changed
      _ <- Tpe.string.query.get.map(_ ==> List("c"))


      id <- Tpe.int(3).save.transact.map(_.id)
      _ <- Tpe(id).int_?(Some(1)).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Tpe.int with value `1` doesn't satisfy validation:
                 |_ > 2
                 |""".stripMargin
        }


      id <- Tpe.long(3L).save.transact.map(_.id)
      _ <- Tpe(id).long_?(Some(1L)).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Tpe.long with value `1` doesn't satisfy validation:
                 |_ > 2L
                 |""".stripMargin
        }


      id <- Tpe.float(float3).save.transact.map(_.id)
      _ <- Tpe(id).float_?(Some(float1)).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Tpe.float with value `$float1` doesn't satisfy validation:
                 |_ > 2.2f
                 |""".stripMargin
        }


      id <- Tpe.double(double3).save.transact.map(_.id)
      _ <- Tpe(id).double_?(Some(double1)).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Tpe.double with value `$double1` doesn't satisfy validation:
                 |_ > 2.2
                 |""".stripMargin
        }


      id <- Tpe.boolean(false).save.transact.map(_.id)
      _ <- Tpe(id).boolean_?(Some(true)).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Tpe.boolean with value `true` doesn't satisfy validation:
                 |_ == false
                 |""".stripMargin
        }


      id <- Tpe.bigInt(bigInt3).save.transact.map(_.id)
      _ <- Tpe(id).bigInt_?(Some(bigInt1)).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Tpe.bigInt with value `$bigInt1` doesn't satisfy validation:
                 |_ > BigInt(2)
                 |""".stripMargin
        }


      id <- Tpe.bigDecimal(bigDecimal3).save.transact.map(_.id)
      _ <- Tpe(id).bigDecimal_?(Some(bigDecimal1)).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Tpe.bigDecimal with value `$bigDecimal1` doesn't satisfy validation:
                 |_ > BigDecimal(2.2)
                 |""".stripMargin
        }


      id <- Tpe.date(date3).save.transact.map(_.id)
      _ <- Tpe(id).date_?(Some(date1)).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Tpe.date with value `$date1` doesn't satisfy validation:
                 |_.after(new Date(993942000000L))
                 |""".stripMargin
        }


      id <- Tpe.duration(duration3).save.transact.map(_.id)
      _ <- Tpe(id).duration_?(Some(duration1)).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Tpe.duration with value `$duration1` doesn't satisfy validation:
                 |_.compareTo(Duration.ofMinutes(2)) > 0
                 |""".stripMargin
        }


      id <- Tpe.instant(instant3).save.transact.map(_.id)
      _ <- Tpe(id).instant_?(Some(instant1)).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Tpe.instant with value `$instant1` doesn't satisfy validation:
                 |_.compareTo(Instant.ofEpochSecond(2)) > 0
                 |""".stripMargin
        }


      id <- Tpe.localDate(localDate3).save.transact.map(_.id)
      _ <- Tpe(id).localDate_?(Some(localDate1)).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Tpe.localDate with value `$localDate1` doesn't satisfy validation:
                 |_.compareTo(LocalDate.of(2002, 1, 1)) > 0
                 |""".stripMargin
        }


      id <- Tpe.localTime(localTime3).save.transact.map(_.id)
      _ <- Tpe(id).localTime_?(Some(localTime1)).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Tpe.localTime with value `$localTime1` doesn't satisfy validation:
                 |_.compareTo(LocalTime.of(2, 2)) > 0
                 |""".stripMargin
        }


      id <- Tpe.localDateTime(localDateTime3).save.transact.map(_.id)
      _ <- Tpe(id).localDateTime_?(Some(localDateTime1)).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Tpe.localDateTime with value `$localDateTime1` doesn't satisfy validation:
                 |_.compareTo(LocalDateTime.of(2002, 1, 1, 1, 2)) > 0
                 |""".stripMargin
        }


      id <- Tpe.offsetTime(offsetTime3).save.transact.map(_.id)
      _ <- Tpe(id).offsetTime_?(Some(offsetTime1)).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Tpe.offsetTime with value `$offsetTime1` doesn't satisfy validation:
                 |_.compareTo(OffsetTime.of(2, 2, 2, 2, ZoneOffset.ofHours(2))) > 0
                 |""".stripMargin
        }


      id <- Tpe.offsetDateTime(offsetDateTime3).save.transact.map(_.id)
      _ <- Tpe(id).offsetDateTime_?(Some(offsetDateTime1)).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Tpe.offsetDateTime with value `$offsetDateTime1` doesn't satisfy validation:
                 |_.compareTo(OffsetDateTime.of(2002, 1, 1, 1, 1, 1, 1, ZoneOffset.ofHours(2))) > 0
                 |""".stripMargin
        }


      id <- Tpe.zonedDateTime(zonedDateTime3).save.transact.map(_.id)
      _ <- Tpe(id).zonedDateTime_?(Some(zonedDateTime1)).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Tpe.zonedDateTime with value `$zonedDateTime1` doesn't satisfy validation:
                 |_.compareTo(ZonedDateTime.of(2002, 1, 1, 1, 1, 1, 1, ZoneOffset.ofHours(2))) > 0
                 |""".stripMargin
        }


      id <- Tpe.uuid(UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-cccccccccccc")).save.transact.map(_.id)
      _ <- Tpe(id).uuid_?(Some(UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"))).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Tpe.uuid with value `aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa` doesn't satisfy validation:
                 |_.toString != "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"
                 |""".stripMargin
        }


      uri1 = new URI("a")
      uri4 = new URI("abcd")
      id <- Tpe.uri(uri4).save.transact.map(_.id)
      _ <- Tpe(id).uri_?(Some(uri1)).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Tpe.uri with value `a` doesn't satisfy validation:
                 |_.toString.length > 2
                 |""".stripMargin
        }


      id <- Tpe.byte(byte3).save.transact.map(_.id)
      _ <- Tpe(id).byte_?(Some(byte1)).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Tpe.byte with value `$byte1` doesn't satisfy validation:
                 |_ > $byte2
                 |""".stripMargin
        }


      id <- Tpe.short(short3).save.transact.map(_.id)
      _ <- Tpe(id).short_?(Some(short1)).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Tpe.short with value `$short1` doesn't satisfy validation:
                 |_ > $short2
                 |""".stripMargin
        }


      id <- Tpe.char('c').save.transact.map(_.id)
      _ <- Tpe(id).char_?(Some('a')).update.transact
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
