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


case class TypesSeqOpt(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "Types" - validation {
    for {
      id <- Type.stringSeq_?(Some(Seq("d"))).save.transact.map(_.id)

      _ <- Type(id).stringSeq_?(Some(Seq("a", "b", "d"))).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap ==>
              Map(
                "Type.stringSeq" -> Seq(
                  s"""Type.stringSeq with value `a` doesn't satisfy validation:
                     |_ > "c"
                     |""".stripMargin,
                  s"""Type.stringSeq with value `b` doesn't satisfy validation:
                     |_ > "c"
                     |""".stripMargin
                  // (value b is ok)
                )
              )
        }

      // Focusing on error message only
      _ <- Type(id).stringSeq_?(Some(Seq("a", "b", "d"))).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Type.stringSeq with value `a` doesn't satisfy validation:
                 |_ > "c"
                 |""".stripMargin,
              s"""Type.stringSeq with value `b` doesn't satisfy validation:
                 |_ > "c"
                 |""".stripMargin
              // (value b is ok)
            )
        }


      id <- Type.intSeq_?(Some(Seq(4))).save.transact.map(_.id)
      _ <- Type(id).intSeq_?(Some(Seq(1, 2, 4))).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Type.intSeq with value `1` doesn't satisfy validation:
                 |_ > 3
                 |""".stripMargin,
              s"""Type.intSeq with value `2` doesn't satisfy validation:
                 |_ > 3
                 |""".stripMargin
            )
        }


      id <- Type.longSeq_?(Some(Seq(4L))).save.transact.map(_.id)
      _ <- Type(id).longSeq_?(Some(Seq(1L, 2L, 4L))).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Type.longSeq with value `1` doesn't satisfy validation:
                 |_ > 3L
                 |""".stripMargin,
              s"""Type.longSeq with value `2` doesn't satisfy validation:
                 |_ > 3L
                 |""".stripMargin
            )
        }


      id <- Type.floatSeq_?(Some(Seq(float4))).save.transact.map(_.id)
      _ <- Type(id).floatSeq_?(Some(Seq(float1, float2, float4))).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Type.floatSeq with value `$float1` doesn't satisfy validation:
                 |_ > 3.3f
                 |""".stripMargin,
              s"""Type.floatSeq with value `$float2` doesn't satisfy validation:
                 |_ > 3.3f
                 |""".stripMargin
            )
        }


      id <- Type.double(double4).save.transact.map(_.id)
      _ <- Type(id).doubleSeq_?(Some(Seq(double1, double2, double4))).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Type.doubleSeq with value `$double1` doesn't satisfy validation:
                 |_ > 3.3
                 |""".stripMargin,
              s"""Type.doubleSeq with value `$double2` doesn't satisfy validation:
                 |_ > 3.3
                 |""".stripMargin
            )
        }


      id <- Type.boolean(false).save.transact.map(_.id)
      _ <- Type(id).booleanSeq_?(Some(Seq(true, false))).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Type.booleanSeq with value `true` doesn't satisfy validation:
                 |_ == false
                 |""".stripMargin
            )
        }


      id <- Type.bigIntSeq_?(Some(Seq(bigInt4))).save.transact.map(_.id)
      _ <- Type(id).bigIntSeq_?(Some(Seq(bigInt1, bigInt2, bigInt4))).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Type.bigIntSeq with value `$bigInt1` doesn't satisfy validation:
                 |_ > BigInt(3)
                 |""".stripMargin,
              s"""Type.bigIntSeq with value `$bigInt2` doesn't satisfy validation:
                 |_ > BigInt(3)
                 |""".stripMargin
            )
        }


      id <- Type.bigDecimalSeq_?(Some(Seq(bigDecimal4))).save.transact.map(_.id)
      _ <- Type(id).bigDecimalSeq_?(Some(Seq(bigDecimal1, bigDecimal2, bigDecimal4))).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Type.bigDecimalSeq with value `$bigDecimal1` doesn't satisfy validation:
                 |_ > BigDecimal(3.3)
                 |""".stripMargin,
              s"""Type.bigDecimalSeq with value `$bigDecimal2` doesn't satisfy validation:
                 |_ > BigDecimal(3.3)
                 |""".stripMargin
            )
        }


      id <- Type.dateSeq_?(Some(Seq(date4))).save.transact.map(_.id)
      _ <- Type(id).dateSeq_?(Some(Seq(date1, date2, date4))).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Type.dateSeq with value `$date1` doesn't satisfy validation:
                 |_.after(new Date(1057010400000L))
                 |""".stripMargin,
              s"""Type.dateSeq with value `$date2` doesn't satisfy validation:
                 |_.after(new Date(1057010400000L))
                 |""".stripMargin
            )
        }


      id <- Type.durationSeq_?(Some(Seq(duration4))).save.transact.map(_.id)
      _ <- Type(id).durationSeq_?(Some(Seq(duration1, duration2, duration4))).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Type.durationSeq with value `$duration1` doesn't satisfy validation:
                 |_.compareTo(Duration.ofMinutes(2)) > 0
                 |""".stripMargin,
              s"""Type.durationSeq with value `$duration2` doesn't satisfy validation:
                 |_.compareTo(Duration.ofMinutes(2)) > 0
                 |""".stripMargin
            )
        }


      id <- Type.instantSeq_?(Some(Seq(instant4))).save.transact.map(_.id)
      _ <- Type(id).instantSeq_?(Some(Seq(instant1, instant2, instant4))).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Type.instantSeq with value `$instant1` doesn't satisfy validation:
                 |_.compareTo(Instant.ofEpochSecond(2)) > 0
                 |""".stripMargin,
              s"""Type.instantSeq with value `$instant2` doesn't satisfy validation:
                 |_.compareTo(Instant.ofEpochSecond(2)) > 0
                 |""".stripMargin
            )
        }


      id <- Type.localDateSeq_?(Some(Seq(localDate4))).save.transact.map(_.id)
      _ <- Type(id).localDateSeq_?(Some(Seq(localDate1, localDate2, localDate4))).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Type.localDateSeq with value `$localDate1` doesn't satisfy validation:
                 |_.compareTo(LocalDate.of(2002, 1, 1)) > 0
                 |""".stripMargin,
              s"""Type.localDateSeq with value `$localDate2` doesn't satisfy validation:
                 |_.compareTo(LocalDate.of(2002, 1, 1)) > 0
                 |""".stripMargin
            )
        }


      id <- Type.localTimeSeq_?(Some(Seq(localTime4))).save.transact.map(_.id)
      _ <- Type(id).localTimeSeq_?(Some(Seq(localTime1, localTime2, localTime4))).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Type.localTimeSeq with value `$localTime1` doesn't satisfy validation:
                 |_.compareTo(LocalTime.of(2, 2)) > 0
                 |""".stripMargin,
              s"""Type.localTimeSeq with value `$localTime2` doesn't satisfy validation:
                 |_.compareTo(LocalTime.of(2, 2)) > 0
                 |""".stripMargin
            )
        }


      id <- Type.localDateTimeSeq_?(Some(Seq(localDateTime4))).save.transact.map(_.id)
      _ <- Type(id).localDateTimeSeq_?(Some(Seq(localDateTime1, localDateTime2, localDateTime4))).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Type.localDateTimeSeq with value `$localDateTime1` doesn't satisfy validation:
                 |_.compareTo(LocalDateTime.of(2002, 1, 1, 1, 2)) > 0
                 |""".stripMargin,
              s"""Type.localDateTimeSeq with value `$localDateTime2` doesn't satisfy validation:
                 |_.compareTo(LocalDateTime.of(2002, 1, 1, 1, 2)) > 0
                 |""".stripMargin
            )
        }


      id <- Type.offsetTimeSeq_?(Some(Seq(offsetTime4))).save.transact.map(_.id)
      _ <- Type(id).offsetTimeSeq_?(Some(Seq(offsetTime1, offsetTime2, offsetTime4))).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Type.offsetTimeSeq with value `$offsetTime1` doesn't satisfy validation:
                 |_.compareTo(OffsetTime.of(2, 2, 2, 2, ZoneOffset.ofHours(2))) > 0
                 |""".stripMargin,
              s"""Type.offsetTimeSeq with value `$offsetTime2` doesn't satisfy validation:
                 |_.compareTo(OffsetTime.of(2, 2, 2, 2, ZoneOffset.ofHours(2))) > 0
                 |""".stripMargin
            )
        }


      id <- Type.offsetDateTimeSeq_?(Some(Seq(offsetDateTime4))).save.transact.map(_.id)
      _ <- Type(id).offsetDateTimeSeq_?(Some(Seq(offsetDateTime1, offsetDateTime2, offsetDateTime4))).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Type.offsetDateTimeSeq with value `$offsetDateTime1` doesn't satisfy validation:
                 |_.compareTo(OffsetDateTime.of(2002, 1, 1, 1, 1, 1, 1, ZoneOffset.ofHours(2))) > 0
                 |""".stripMargin,
              s"""Type.offsetDateTimeSeq with value `$offsetDateTime2` doesn't satisfy validation:
                 |_.compareTo(OffsetDateTime.of(2002, 1, 1, 1, 1, 1, 1, ZoneOffset.ofHours(2))) > 0
                 |""".stripMargin
            )
        }


      id <- Type.zonedDateTimeSeq_?(Some(Seq(zonedDateTime4))).save.transact.map(_.id)
      _ <- Type(id).zonedDateTimeSeq_?(Some(Seq(zonedDateTime1, zonedDateTime2, zonedDateTime4))).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Type.zonedDateTimeSeq with value `$zonedDateTime1` doesn't satisfy validation:
                 |_.compareTo(ZonedDateTime.of(2002, 1, 1, 1, 1, 1, 1, ZoneOffset.ofHours(2))) > 0
                 |""".stripMargin,
              s"""Type.zonedDateTimeSeq with value `$zonedDateTime2` doesn't satisfy validation:
                 |_.compareTo(ZonedDateTime.of(2002, 1, 1, 1, 1, 1, 1, ZoneOffset.ofHours(2))) > 0
                 |""".stripMargin
            )
        }


      id <- Type.uuidSeq_?(Some(Seq(UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-dddddddddddd")))).save.transact.map(_.id)
      _ <- Type(id).uuidSeq_?(Some(Seq(UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa")))).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Type.uuidSeq with value `aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa` doesn't satisfy validation:
                 |_.toString != "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"
                 |""".stripMargin
            )
        }

      uri1 = new URI("a")
      uri2 = new URI("ab")
      uri4 = new URI("abcd")
      id <- Type.uriSeq_?(Some(Seq(uri4))).save.transact.map(_.id)
      _ <- Type(id).uriSeq_?(Some(Seq(uri1, uri2, uri4))).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Type.uriSeq with value `a` doesn't satisfy validation:
                 |_.toString.length > 3
                 |""".stripMargin,
              s"""Type.uriSeq with value `ab` doesn't satisfy validation:
                 |_.toString.length > 3
                 |""".stripMargin
            )
        }


      id <- Type.shortSeq_?(Some(Seq(short4))).save.transact.map(_.id)
      _ <- Type(id).shortSeq_?(Some(Seq(short1, short2, short4))).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Type.shortSeq with value `$short1` doesn't satisfy validation:
                 |_ > $short3
                 |""".stripMargin,
              s"""Type.shortSeq with value `$short2` doesn't satisfy validation:
                 |_ > $short3
                 |""".stripMargin
            )
        }


      id <- Type.charSeq_?(Some(Seq('d'))).save.transact.map(_.id)
      _ <- Type(id).charSeq_?(Some(Seq('a', 'b', 'd'))).update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
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
