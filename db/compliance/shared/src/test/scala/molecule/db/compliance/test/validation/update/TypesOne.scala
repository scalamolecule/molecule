package molecule.db.compliance.test.validation.update

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
      id <- Type.string("c").save.transact.map(_.id)

      _ <- Type(id).string("a").update.transact
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

      // Focusing on error message only
      _ <- Type(id).string("a").update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Type.string with value `a` doesn't satisfy validation:
                 |_ > "b"
                 |""".stripMargin
        }

      // Value hasn't changed
      _ <- Type.string.query.get.map(_ ==> List("c"))


      id <- Type.int(3).save.transact.map(_.id)
      _ <- Type(id).int(1).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Type.int with value `1` doesn't satisfy validation:
                 |_ > 2
                 |""".stripMargin
        }


      id <- Type.long(3L).save.transact.map(_.id)
      _ <- Type(id).long(1L).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Type.long with value `1` doesn't satisfy validation:
                 |_ > 2L
                 |""".stripMargin
        }


      id <- Type.float(float3).save.transact.map(_.id)
      _ <- Type(id).float(float1).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Type.float with value `$float1` doesn't satisfy validation:
                 |_ > 2.2f
                 |""".stripMargin
        }


      id <- Type.double(double3).save.transact.map(_.id)
      _ <- Type(id).double(double1).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Type.double with value `$double1` doesn't satisfy validation:
                 |_ > 2.2
                 |""".stripMargin
        }


      id <- Type.boolean(false).save.transact.map(_.id)
      _ <- Type(id).boolean(true).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Type.boolean with value `true` doesn't satisfy validation:
                 |_ == false
                 |""".stripMargin
        }


      id <- Type.bigInt(bigInt3).save.transact.map(_.id)
      _ <- Type(id).bigInt(bigInt1).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Type.bigInt with value `$bigInt1` doesn't satisfy validation:
                 |_ > BigInt(2)
                 |""".stripMargin
        }


      id <- Type.bigDecimal(bigDecimal3).save.transact.map(_.id)
      _ <- Type(id).bigDecimal(bigDecimal1).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Type.bigDecimal with value `$bigDecimal1` doesn't satisfy validation:
                 |_ > BigDecimal(2.2)
                 |""".stripMargin
        }


      id <- Type.date(date3).save.transact.map(_.id)
      _ <- Type(id).date(date1).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Type.date with value `$date1` doesn't satisfy validation:
                 |_.after(new Date(993942000000L))
                 |""".stripMargin
        }


      id <- Type.duration(duration3).save.transact.map(_.id)
      _ <- Type(id).duration(duration1).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Type.duration with value `$duration1` doesn't satisfy validation:
                 |_.compareTo(Duration.ofMinutes(2)) > 0
                 |""".stripMargin
        }


      id <- Type.instant(instant3).save.transact.map(_.id)
      _ <- Type(id).instant(instant1).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Type.instant with value `$instant1` doesn't satisfy validation:
                 |_.compareTo(Instant.ofEpochSecond(2)) > 0
                 |""".stripMargin
        }


      id <- Type.localDate(localDate3).save.transact.map(_.id)
      _ <- Type(id).localDate(localDate1).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Type.localDate with value `$localDate1` doesn't satisfy validation:
                 |_.compareTo(LocalDate.of(2002, 1, 1)) > 0
                 |""".stripMargin
        }


      id <- Type.localTime(localTime3).save.transact.map(_.id)
      _ <- Type(id).localTime(localTime1).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Type.localTime with value `$localTime1` doesn't satisfy validation:
                 |_.compareTo(LocalTime.of(2, 2)) > 0
                 |""".stripMargin
        }


      id <- Type.localDateTime(localDateTime3).save.transact.map(_.id)
      _ <- Type(id).localDateTime(localDateTime1).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Type.localDateTime with value `$localDateTime1` doesn't satisfy validation:
                 |_.compareTo(LocalDateTime.of(2002, 1, 1, 1, 2)) > 0
                 |""".stripMargin
        }


      id <- Type.offsetTime(offsetTime3).save.transact.map(_.id)
      _ <- Type(id).offsetTime(offsetTime1).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Type.offsetTime with value `$offsetTime1` doesn't satisfy validation:
                 |_.compareTo(OffsetTime.of(2, 2, 2, 2, ZoneOffset.ofHours(2))) > 0
                 |""".stripMargin
        }


      id <- Type.offsetDateTime(offsetDateTime3).save.transact.map(_.id)
      _ <- Type(id).offsetDateTime(offsetDateTime1).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Type.offsetDateTime with value `$offsetDateTime1` doesn't satisfy validation:
                 |_.compareTo(OffsetDateTime.of(2002, 1, 1, 1, 1, 1, 1, ZoneOffset.ofHours(2))) > 0
                 |""".stripMargin
        }


      id <- Type.zonedDateTime(zonedDateTime3).save.transact.map(_.id)
      _ <- Type(id).zonedDateTime(zonedDateTime1).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Type.zonedDateTime with value `$zonedDateTime1` doesn't satisfy validation:
                 |_.compareTo(ZonedDateTime.of(2002, 1, 1, 1, 1, 1, 1, ZoneOffset.ofHours(2))) > 0
                 |""".stripMargin
        }


      id <- Type.uuid(UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-cccccccccccc")).save.transact.map(_.id)
      _ <- Type(id).uuid(UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa")).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Type.uuid with value `aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa` doesn't satisfy validation:
                 |_.toString != "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"
                 |""".stripMargin
        }


      uri1 = new URI("a")
      uri4 = new URI("abcd")
      id <- Type.uri(uri4).save.transact.map(_.id)
      _ <- Type(id).uri(uri1).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Type.uri with value `a` doesn't satisfy validation:
                 |_.toString.length > 2
                 |""".stripMargin
        }


      id <- Type.byte(byte3).save.transact.map(_.id)
      _ <- Type(id).byte(byte1).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Type.byte with value `$byte1` doesn't satisfy validation:
                 |_ > $byte2
                 |""".stripMargin
        }


      id <- Type.short(short3).save.transact.map(_.id)
      _ <- Type(id).short(short1).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Type.short with value `$short1` doesn't satisfy validation:
                 |_ > $short2
                 |""".stripMargin
        }


      id <- Type.char('c').save.transact.map(_.id)
      _ <- Type(id).char('a').update.transact
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
