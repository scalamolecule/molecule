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


case class TypesSeqOpt(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "Types" - validation {
    for {
      id <- Tpe.stringSeq_?(Some(Seq("d"))).save.transact.map(_.id)

      _ <- Tpe(id).stringSeq_?(Some(Seq("a", "b", "d"))).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap ==>
              Map(
                "Tpe.stringSeq" -> Seq(
                  s"""Tpe.stringSeq with value `a` doesn't satisfy validation:
                     |_ > "c"
                     |""".stripMargin,
                  s"""Tpe.stringSeq with value `b` doesn't satisfy validation:
                     |_ > "c"
                     |""".stripMargin
                  // (value b is ok)
                )
              )
        }

      // Focusing on error message only
      _ <- Tpe(id).stringSeq_?(Some(Seq("a", "b", "d"))).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Tpe.stringSeq with value `a` doesn't satisfy validation:
                 |_ > "c"
                 |""".stripMargin,
              s"""Tpe.stringSeq with value `b` doesn't satisfy validation:
                 |_ > "c"
                 |""".stripMargin
              // (value b is ok)
            )
        }


      id <- Tpe.intSeq_?(Some(Seq(4))).save.transact.map(_.id)
      _ <- Tpe(id).intSeq_?(Some(Seq(1, 2, 4))).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Tpe.intSeq with value `1` doesn't satisfy validation:
                 |_ > 3
                 |""".stripMargin,
              s"""Tpe.intSeq with value `2` doesn't satisfy validation:
                 |_ > 3
                 |""".stripMargin
            )
        }


      id <- Tpe.longSeq_?(Some(Seq(4L))).save.transact.map(_.id)
      _ <- Tpe(id).longSeq_?(Some(Seq(1L, 2L, 4L))).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Tpe.longSeq with value `1` doesn't satisfy validation:
                 |_ > 3L
                 |""".stripMargin,
              s"""Tpe.longSeq with value `2` doesn't satisfy validation:
                 |_ > 3L
                 |""".stripMargin
            )
        }


      id <- Tpe.floatSeq_?(Some(Seq(float4))).save.transact.map(_.id)
      _ <- Tpe(id).floatSeq_?(Some(Seq(float1, float2, float4))).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Tpe.floatSeq with value `$float1` doesn't satisfy validation:
                 |_ > 3.3f
                 |""".stripMargin,
              s"""Tpe.floatSeq with value `$float2` doesn't satisfy validation:
                 |_ > 3.3f
                 |""".stripMargin
            )
        }


      id <- Tpe.double(double4).save.transact.map(_.id)
      _ <- Tpe(id).doubleSeq_?(Some(Seq(double1, double2, double4))).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Tpe.doubleSeq with value `$double1` doesn't satisfy validation:
                 |_ > 3.3
                 |""".stripMargin,
              s"""Tpe.doubleSeq with value `$double2` doesn't satisfy validation:
                 |_ > 3.3
                 |""".stripMargin
            )
        }


      id <- Tpe.boolean(false).save.transact.map(_.id)
      _ <- Tpe(id).booleanSeq_?(Some(Seq(true, false))).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Tpe.booleanSeq with value `true` doesn't satisfy validation:
                 |_ == false
                 |""".stripMargin
            )
        }


      id <- Tpe.bigIntSeq_?(Some(Seq(bigInt4))).save.transact.map(_.id)
      _ <- Tpe(id).bigIntSeq_?(Some(Seq(bigInt1, bigInt2, bigInt4))).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Tpe.bigIntSeq with value `$bigInt1` doesn't satisfy validation:
                 |_ > BigInt(3)
                 |""".stripMargin,
              s"""Tpe.bigIntSeq with value `$bigInt2` doesn't satisfy validation:
                 |_ > BigInt(3)
                 |""".stripMargin
            )
        }


      id <- Tpe.bigDecimalSeq_?(Some(Seq(bigDecimal4))).save.transact.map(_.id)
      _ <- Tpe(id).bigDecimalSeq_?(Some(Seq(bigDecimal1, bigDecimal2, bigDecimal4))).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Tpe.bigDecimalSeq with value `$bigDecimal1` doesn't satisfy validation:
                 |_ > BigDecimal(3.3)
                 |""".stripMargin,
              s"""Tpe.bigDecimalSeq with value `$bigDecimal2` doesn't satisfy validation:
                 |_ > BigDecimal(3.3)
                 |""".stripMargin
            )
        }


      id <- Tpe.dateSeq_?(Some(Seq(date4))).save.transact.map(_.id)
      _ <- Tpe(id).dateSeq_?(Some(Seq(date1, date2, date4))).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Tpe.dateSeq with value `$date1` doesn't satisfy validation:
                 |_.after(new Date(1057010400000L))
                 |""".stripMargin,
              s"""Tpe.dateSeq with value `$date2` doesn't satisfy validation:
                 |_.after(new Date(1057010400000L))
                 |""".stripMargin
            )
        }


      id <- Tpe.durationSeq_?(Some(Seq(duration4))).save.transact.map(_.id)
      _ <- Tpe(id).durationSeq_?(Some(Seq(duration1, duration2, duration4))).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Tpe.durationSeq with value `$duration1` doesn't satisfy validation:
                 |_.compareTo(Duration.ofMinutes(2)) > 0
                 |""".stripMargin,
              s"""Tpe.durationSeq with value `$duration2` doesn't satisfy validation:
                 |_.compareTo(Duration.ofMinutes(2)) > 0
                 |""".stripMargin
            )
        }


      id <- Tpe.instantSeq_?(Some(Seq(instant4))).save.transact.map(_.id)
      _ <- Tpe(id).instantSeq_?(Some(Seq(instant1, instant2, instant4))).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Tpe.instantSeq with value `$instant1` doesn't satisfy validation:
                 |_.compareTo(Instant.ofEpochSecond(2)) > 0
                 |""".stripMargin,
              s"""Tpe.instantSeq with value `$instant2` doesn't satisfy validation:
                 |_.compareTo(Instant.ofEpochSecond(2)) > 0
                 |""".stripMargin
            )
        }


      id <- Tpe.localDateSeq_?(Some(Seq(localDate4))).save.transact.map(_.id)
      _ <- Tpe(id).localDateSeq_?(Some(Seq(localDate1, localDate2, localDate4))).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Tpe.localDateSeq with value `$localDate1` doesn't satisfy validation:
                 |_.compareTo(LocalDate.of(2002, 1, 1)) > 0
                 |""".stripMargin,
              s"""Tpe.localDateSeq with value `$localDate2` doesn't satisfy validation:
                 |_.compareTo(LocalDate.of(2002, 1, 1)) > 0
                 |""".stripMargin
            )
        }


      id <- Tpe.localTimeSeq_?(Some(Seq(localTime4))).save.transact.map(_.id)
      _ <- Tpe(id).localTimeSeq_?(Some(Seq(localTime1, localTime2, localTime4))).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Tpe.localTimeSeq with value `$localTime1` doesn't satisfy validation:
                 |_.compareTo(LocalTime.of(2, 2)) > 0
                 |""".stripMargin,
              s"""Tpe.localTimeSeq with value `$localTime2` doesn't satisfy validation:
                 |_.compareTo(LocalTime.of(2, 2)) > 0
                 |""".stripMargin
            )
        }


      id <- Tpe.localDateTimeSeq_?(Some(Seq(localDateTime4))).save.transact.map(_.id)
      _ <- Tpe(id).localDateTimeSeq_?(Some(Seq(localDateTime1, localDateTime2, localDateTime4))).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Tpe.localDateTimeSeq with value `$localDateTime1` doesn't satisfy validation:
                 |_.compareTo(LocalDateTime.of(2002, 1, 1, 1, 2)) > 0
                 |""".stripMargin,
              s"""Tpe.localDateTimeSeq with value `$localDateTime2` doesn't satisfy validation:
                 |_.compareTo(LocalDateTime.of(2002, 1, 1, 1, 2)) > 0
                 |""".stripMargin
            )
        }


      id <- Tpe.offsetTimeSeq_?(Some(Seq(offsetTime4))).save.transact.map(_.id)
      _ <- Tpe(id).offsetTimeSeq_?(Some(Seq(offsetTime1, offsetTime2, offsetTime4))).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Tpe.offsetTimeSeq with value `$offsetTime1` doesn't satisfy validation:
                 |_.compareTo(OffsetTime.of(2, 2, 2, 2, ZoneOffset.ofHours(2))) > 0
                 |""".stripMargin,
              s"""Tpe.offsetTimeSeq with value `$offsetTime2` doesn't satisfy validation:
                 |_.compareTo(OffsetTime.of(2, 2, 2, 2, ZoneOffset.ofHours(2))) > 0
                 |""".stripMargin
            )
        }


      id <- Tpe.offsetDateTimeSeq_?(Some(Seq(offsetDateTime4))).save.transact.map(_.id)
      _ <- Tpe(id).offsetDateTimeSeq_?(Some(Seq(offsetDateTime1, offsetDateTime2, offsetDateTime4))).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Tpe.offsetDateTimeSeq with value `$offsetDateTime1` doesn't satisfy validation:
                 |_.compareTo(OffsetDateTime.of(2002, 1, 1, 1, 1, 1, 1, ZoneOffset.ofHours(2))) > 0
                 |""".stripMargin,
              s"""Tpe.offsetDateTimeSeq with value `$offsetDateTime2` doesn't satisfy validation:
                 |_.compareTo(OffsetDateTime.of(2002, 1, 1, 1, 1, 1, 1, ZoneOffset.ofHours(2))) > 0
                 |""".stripMargin
            )
        }


      id <- Tpe.zonedDateTimeSeq_?(Some(Seq(zonedDateTime4))).save.transact.map(_.id)
      _ <- Tpe(id).zonedDateTimeSeq_?(Some(Seq(zonedDateTime1, zonedDateTime2, zonedDateTime4))).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Tpe.zonedDateTimeSeq with value `$zonedDateTime1` doesn't satisfy validation:
                 |_.compareTo(ZonedDateTime.of(2002, 1, 1, 1, 1, 1, 1, ZoneOffset.ofHours(2))) > 0
                 |""".stripMargin,
              s"""Tpe.zonedDateTimeSeq with value `$zonedDateTime2` doesn't satisfy validation:
                 |_.compareTo(ZonedDateTime.of(2002, 1, 1, 1, 1, 1, 1, ZoneOffset.ofHours(2))) > 0
                 |""".stripMargin
            )
        }


      id <- Tpe.uuidSeq_?(Some(Seq(UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-dddddddddddd")))).save.transact.map(_.id)
      _ <- Tpe(id).uuidSeq_?(Some(Seq(UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa")))).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Tpe.uuidSeq with value `aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa` doesn't satisfy validation:
                 |_.toString != "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"
                 |""".stripMargin
            )
        }

      uri1 = new URI("a")
      uri2 = new URI("ab")
      uri4 = new URI("abcd")
      id <- Tpe.uriSeq_?(Some(Seq(uri4))).save.transact.map(_.id)
      _ <- Tpe(id).uriSeq_?(Some(Seq(uri1, uri2, uri4))).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Tpe.uriSeq with value `a` doesn't satisfy validation:
                 |_.toString.length > 3
                 |""".stripMargin,
              s"""Tpe.uriSeq with value `ab` doesn't satisfy validation:
                 |_.toString.length > 3
                 |""".stripMargin
            )
        }


      id <- Tpe.shortSeq_?(Some(Seq(short4))).save.transact.map(_.id)
      _ <- Tpe(id).shortSeq_?(Some(Seq(short1, short2, short4))).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Tpe.shortSeq with value `$short1` doesn't satisfy validation:
                 |_ > $short3
                 |""".stripMargin,
              s"""Tpe.shortSeq with value `$short2` doesn't satisfy validation:
                 |_ > $short3
                 |""".stripMargin
            )
        }


      id <- Tpe.charSeq_?(Some(Seq('d'))).save.transact.map(_.id)
      _ <- Tpe(id).charSeq_?(Some(Seq('a', 'b', 'd'))).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
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
