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


case class TypesSet(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "Types" - validation {
    for {
      id <- Tpe.stringSet(Set("d")).save.transact.map(_.id)

      _ <- Tpe(id).stringSet(Set("a", "b", "d")).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap ==>
              Map(
                "Tpe.stringSet" -> Seq(
                  s"""Tpe.stringSet with value `a` doesn't satisfy validation:
                     |_ > "c"
                     |""".stripMargin,
                  s"""Tpe.stringSet with value `b` doesn't satisfy validation:
                     |_ > "c"
                     |""".stripMargin
                  // (value d is ok)
                )
              )
        }

      // Focusing on error message only
      _ <- Tpe(id).stringSet(Set("a", "b", "d")).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Tpe.stringSet with value `a` doesn't satisfy validation:
                 |_ > "c"
                 |""".stripMargin,
              s"""Tpe.stringSet with value `b` doesn't satisfy validation:
                 |_ > "c"
                 |""".stripMargin
            )
        }

      // Value hasn't changed
      _ <- Tpe.stringSet.query.get.map(_ ==> List(Set("d")))


      id <- Tpe.intSet(Set(4)).save.transact.map(_.id)
      _ <- Tpe(id).intSet(Set(1, 2, 4)).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Tpe.intSet with value `1` doesn't satisfy validation:
                 |_ > 3
                 |""".stripMargin,
              s"""Tpe.intSet with value `2` doesn't satisfy validation:
                 |_ > 3
                 |""".stripMargin
            )
        }


      id <- Tpe.longSet(Set(4L)).save.transact.map(_.id)
      _ <- Tpe(id).longSet(Set(1L, 2L, 4L)).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Tpe.longSet with value `1` doesn't satisfy validation:
                 |_ > 3L
                 |""".stripMargin,
              s"""Tpe.longSet with value `2` doesn't satisfy validation:
                 |_ > 3L
                 |""".stripMargin
            )
        }


      id <- Tpe.floatSet(Set(float4)).save.transact.map(_.id)
      _ <- Tpe(id).floatSet(Set(float1, float2, float4)).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Tpe.floatSet with value `$float1` doesn't satisfy validation:
                 |_ > 3.3f
                 |""".stripMargin,
              s"""Tpe.floatSet with value `$float2` doesn't satisfy validation:
                 |_ > 3.3f
                 |""".stripMargin
            )
        }


      id <- Tpe.double(double4).save.transact.map(_.id)
      _ <- Tpe(id).doubleSet(Set(double1, double2, double4)).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Tpe.doubleSet with value `$double1` doesn't satisfy validation:
                 |_ > 3.3
                 |""".stripMargin,
              s"""Tpe.doubleSet with value `$double2` doesn't satisfy validation:
                 |_ > 3.3
                 |""".stripMargin
            )
        }


      id <- Tpe.boolean(false).save.transact.map(_.id)
      _ <- Tpe(id).booleanSet(Set(true, false)).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Tpe.booleanSet with value `true` doesn't satisfy validation:
                 |_ == false
                 |""".stripMargin
            )
        }


      id <- Tpe.bigIntSet(Set(bigInt4)).save.transact.map(_.id)
      _ <- Tpe(id).bigIntSet(Set(bigInt1, bigInt2, bigInt4)).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Tpe.bigIntSet with value `$bigInt1` doesn't satisfy validation:
                 |_ > BigInt(3)
                 |""".stripMargin,
              s"""Tpe.bigIntSet with value `$bigInt2` doesn't satisfy validation:
                 |_ > BigInt(3)
                 |""".stripMargin
            )
        }


      id <- Tpe.bigDecimalSet(Set(bigDecimal4)).save.transact.map(_.id)
      _ <- Tpe(id).bigDecimalSet(Set(bigDecimal1, bigDecimal2, bigDecimal4)).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Tpe.bigDecimalSet with value `$bigDecimal1` doesn't satisfy validation:
                 |_ > BigDecimal(3.3)
                 |""".stripMargin,
              s"""Tpe.bigDecimalSet with value `$bigDecimal2` doesn't satisfy validation:
                 |_ > BigDecimal(3.3)
                 |""".stripMargin
            )
        }


      id <- Tpe.dateSet(Set(date4)).save.transact.map(_.id)
      _ <- Tpe(id).dateSet(Set(date1, date2, date4)).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Tpe.dateSet with value `$date1` doesn't satisfy validation:
                 |_.after(new Date(1057010400000L))
                 |""".stripMargin,
              s"""Tpe.dateSet with value `$date2` doesn't satisfy validation:
                 |_.after(new Date(1057010400000L))
                 |""".stripMargin
            )
        }


      id <- Tpe.durationSet(Set(duration4)).save.transact.map(_.id)
      _ <- Tpe(id).durationSet(Set(duration1, duration2, duration4)).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Tpe.durationSet with value `$duration1` doesn't satisfy validation:
                 |_.compareTo(Duration.ofMinutes(2)) > 0
                 |""".stripMargin,
              s"""Tpe.durationSet with value `$duration2` doesn't satisfy validation:
                 |_.compareTo(Duration.ofMinutes(2)) > 0
                 |""".stripMargin
            )
        }


      id <- Tpe.instantSet(Set(instant4)).save.transact.map(_.id)
      _ <- Tpe(id).instantSet(Set(instant1, instant2, instant4)).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Tpe.instantSet with value `$instant1` doesn't satisfy validation:
                 |_.compareTo(Instant.ofEpochSecond(2)) > 0
                 |""".stripMargin,
              s"""Tpe.instantSet with value `$instant2` doesn't satisfy validation:
                 |_.compareTo(Instant.ofEpochSecond(2)) > 0
                 |""".stripMargin
            )
        }


      id <- Tpe.localDateSet(Set(localDate4)).save.transact.map(_.id)
      _ <- Tpe(id).localDateSet(Set(localDate1, localDate2, localDate4)).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Tpe.localDateSet with value `$localDate1` doesn't satisfy validation:
                 |_.compareTo(LocalDate.of(2002, 1, 1)) > 0
                 |""".stripMargin,
              s"""Tpe.localDateSet with value `$localDate2` doesn't satisfy validation:
                 |_.compareTo(LocalDate.of(2002, 1, 1)) > 0
                 |""".stripMargin
            )
        }


      id <- Tpe.localTimeSet(Set(localTime4)).save.transact.map(_.id)
      _ <- Tpe(id).localTimeSet(Set(localTime1, localTime2, localTime4)).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Tpe.localTimeSet with value `$localTime1` doesn't satisfy validation:
                 |_.compareTo(LocalTime.of(2, 2)) > 0
                 |""".stripMargin,
              s"""Tpe.localTimeSet with value `$localTime2` doesn't satisfy validation:
                 |_.compareTo(LocalTime.of(2, 2)) > 0
                 |""".stripMargin
            )
        }


      id <- Tpe.localDateTimeSet(Set(localDateTime4)).save.transact.map(_.id)
      _ <- Tpe(id).localDateTimeSet(Set(localDateTime1, localDateTime2, localDateTime4)).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Tpe.localDateTimeSet with value `$localDateTime1` doesn't satisfy validation:
                 |_.compareTo(LocalDateTime.of(2002, 1, 1, 1, 2)) > 0
                 |""".stripMargin,
              s"""Tpe.localDateTimeSet with value `$localDateTime2` doesn't satisfy validation:
                 |_.compareTo(LocalDateTime.of(2002, 1, 1, 1, 2)) > 0
                 |""".stripMargin
            )
        }


      id <- Tpe.offsetTimeSet(Set(offsetTime4)).save.transact.map(_.id)
      _ <- Tpe(id).offsetTimeSet(Set(offsetTime1, offsetTime2, offsetTime4)).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Tpe.offsetTimeSet with value `$offsetTime1` doesn't satisfy validation:
                 |_.compareTo(OffsetTime.of(2, 2, 2, 2, ZoneOffset.ofHours(2))) > 0
                 |""".stripMargin,
              s"""Tpe.offsetTimeSet with value `$offsetTime2` doesn't satisfy validation:
                 |_.compareTo(OffsetTime.of(2, 2, 2, 2, ZoneOffset.ofHours(2))) > 0
                 |""".stripMargin
            )
        }


      id <- Tpe.offsetDateTimeSet(Set(offsetDateTime4)).save.transact.map(_.id)
      _ <- Tpe(id).offsetDateTimeSet(Set(offsetDateTime1, offsetDateTime2, offsetDateTime4)).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Tpe.offsetDateTimeSet with value `$offsetDateTime1` doesn't satisfy validation:
                 |_.compareTo(OffsetDateTime.of(2002, 1, 1, 1, 1, 1, 1, ZoneOffset.ofHours(2))) > 0
                 |""".stripMargin,
              s"""Tpe.offsetDateTimeSet with value `$offsetDateTime2` doesn't satisfy validation:
                 |_.compareTo(OffsetDateTime.of(2002, 1, 1, 1, 1, 1, 1, ZoneOffset.ofHours(2))) > 0
                 |""".stripMargin
            )
        }


      id <- Tpe.zonedDateTimeSet(Set(zonedDateTime4)).save.transact.map(_.id)
      _ <- Tpe(id).zonedDateTimeSet(Set(zonedDateTime1, zonedDateTime2, zonedDateTime4)).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Tpe.zonedDateTimeSet with value `$zonedDateTime1` doesn't satisfy validation:
                 |_.compareTo(ZonedDateTime.of(2002, 1, 1, 1, 1, 1, 1, ZoneOffset.ofHours(2))) > 0
                 |""".stripMargin,
              s"""Tpe.zonedDateTimeSet with value `$zonedDateTime2` doesn't satisfy validation:
                 |_.compareTo(ZonedDateTime.of(2002, 1, 1, 1, 1, 1, 1, ZoneOffset.ofHours(2))) > 0
                 |""".stripMargin
            )
        }


      id <- Tpe.uuidSet(Set(UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-dddddddddddd"))).save.transact.map(_.id)
      _ <- Tpe(id).uuidSet(Set(UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"))).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Tpe.uuidSet with value `aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa` doesn't satisfy validation:
                 |_.toString != "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"
                 |""".stripMargin
            )
        }

      uri1 = new URI("a")
      uri2 = new URI("ab")
      uri4 = new URI("abcd")
      id <- Tpe.uriSet(Set(uri4)).save.transact.map(_.id)
      _ <- Tpe(id).uriSet(Set(uri1, uri2, uri4)).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Tpe.uriSet with value `a` doesn't satisfy validation:
                 |_.toString.length > 3
                 |""".stripMargin,
              s"""Tpe.uriSet with value `ab` doesn't satisfy validation:
                 |_.toString.length > 3
                 |""".stripMargin
            )
        }


      id <- Tpe.byteSet(Set(byte4)).save.transact.map(_.id)
      _ <- Tpe(id).byteSet(Set(byte1, byte2, byte4)).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Tpe.byteSet with value `$byte1` doesn't satisfy validation:
                 |_ > $byte3
                 |""".stripMargin,
              s"""Tpe.byteSet with value `$byte2` doesn't satisfy validation:
                 |_ > $byte3
                 |""".stripMargin
            )
        }


      id <- Tpe.shortSet(Set(short4)).save.transact.map(_.id)
      _ <- Tpe(id).shortSet(Set(short1, short2, short4)).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Tpe.shortSet with value `$short1` doesn't satisfy validation:
                 |_ > $short3
                 |""".stripMargin,
              s"""Tpe.shortSet with value `$short2` doesn't satisfy validation:
                 |_ > $short3
                 |""".stripMargin
            )
        }


      id <- Tpe.charSet(Set('d')).save.transact.map(_.id)
      _ <- Tpe(id).charSet(Set('a', 'b', 'd')).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Tpe.charSet with value `a` doesn't satisfy validation:
                 |_ > 'c'
                 |""".stripMargin,
              s"""Tpe.charSet with value `b` doesn't satisfy validation:
                 |_ > 'c'
                 |""".stripMargin
            )
        }
    } yield ()
  }
}
