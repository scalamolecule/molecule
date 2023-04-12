package molecule.datomic.test.validation.update

import java.net.URI
import java.util.UUID
import molecule.base.error.ValidationErrors
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Validation._
import molecule.datomic.async._
import molecule.datomic.setup.DatomicTestSuite
import utest._
import scala.language.implicitConversions

object TypesSetOpt extends DatomicTestSuite {

  override lazy val tests = Tests {

    "String" - validation { implicit conn =>
      for {
        eid <- Type.strings_?(Some(Set("d"))).save.transact.map(_.eid)

        _ <- Type(eid).strings_?(Some(Set("a", "b", "d"))).update.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap ==>
              Map(
                "Type.strings" -> Seq(
                  s"""Type.strings with value `a` doesn't satisfy validation:
                     |  _ > "c"
                     |""".stripMargin,
                  s"""Type.strings with value `b` doesn't satisfy validation:
                     |  _ > "c"
                     |""".stripMargin
                  // (value b is ok)
                )
              )
        }

        // Focusing on error message only
        _ <- Type(eid).strings_?(Some(Set("a", "b", "d"))).update.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Type.strings with value `a` doesn't satisfy validation:
                 |  _ > "c"
                 |""".stripMargin,
              s"""Type.strings with value `b` doesn't satisfy validation:
                 |  _ > "c"
                 |""".stripMargin
              // (value b is ok)
            )
        }
      } yield ()
    }

    "Int" - validation { implicit conn =>
      for {
        eid <- Type.ints_?(Some(Set(4))).save.transact.map(_.eid)
        _ <- Type(eid).ints_?(Some(Set(1, 2, 4))).update.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Type.ints with value `1` doesn't satisfy validation:
                 |  _ > 3
                 |""".stripMargin,
              s"""Type.ints with value `2` doesn't satisfy validation:
                 |  _ > 3
                 |""".stripMargin
            )
        }
      } yield ()
    }

    "Long" - validation { implicit conn =>
      for {
        eid <- Type.longs_?(Some(Set(4L))).save.transact.map(_.eid)
        _ <- Type(eid).longs_?(Some(Set(1L, 2L, 4L))).update.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Type.longs with value `1` doesn't satisfy validation:
                 |  _ > 3L
                 |""".stripMargin,
              s"""Type.longs with value `2` doesn't satisfy validation:
                 |  _ > 3L
                 |""".stripMargin
            )
        }
      } yield ()
    }

    "Float" - validation { implicit conn =>
      for {
        eid <- Type.floats_?(Some(Set(float4))).save.transact.map(_.eid)
        _ <- Type(eid).floats_?(Some(Set(float1, float2, float4))).update.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Type.floats with value `$float1` doesn't satisfy validation:
                 |  _ > 3.3f
                 |""".stripMargin,
              s"""Type.floats with value `$float2` doesn't satisfy validation:
                 |  _ > 3.3f
                 |""".stripMargin
            )
        }
      } yield ()
    }

    "Double" - validation { implicit conn =>
      for {
        eid <- Type.double(double4).save.transact.map(_.eid)
        _ <- Type(eid).doubles_?(Some(Set(double1, double2, double4))).update.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Type.doubles with value `$double1` doesn't satisfy validation:
                 |  _ > 3.3
                 |""".stripMargin,
              s"""Type.doubles with value `$double2` doesn't satisfy validation:
                 |  _ > 3.3
                 |""".stripMargin
            )
        }
      } yield ()
    }

    "Boolean" - validation { implicit conn =>
      for {
        eid <- Type.boolean(false).save.transact.map(_.eid)
        _ <- Type(eid).booleans_?(Some(Set(true, false))).update.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Type.booleans with value `true` doesn't satisfy validation:
                 |  _ == false
                 |""".stripMargin
            )
        }
      } yield ()
    }

    "BigInt" - validation { implicit conn =>
      for {
        eid <- Type.bigInts_?(Some(Set(bigInt4))).save.transact.map(_.eid)
        _ <- Type(eid).bigInts_?(Some(Set(bigInt1, bigInt2, bigInt4))).update.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Type.bigInts with value `$bigInt1` doesn't satisfy validation:
                 |  _ > BigInt(3)
                 |""".stripMargin,
              s"""Type.bigInts with value `$bigInt2` doesn't satisfy validation:
                 |  _ > BigInt(3)
                 |""".stripMargin
            )
        }
      } yield ()
    }

    "BigDecimal" - validation { implicit conn =>
      for {
        eid <- Type.bigDecimals(bigDecimal4).save.transact.map(_.eid)
        _ <- Type(eid).bigDecimals_?(Some(Set(bigDecimal1, bigDecimal2, bigDecimal4))).update.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Type.bigDecimals with value `$bigDecimal1` doesn't satisfy validation:
                 |  _ > BigDecimal(3.3)
                 |""".stripMargin,
              s"""Type.bigDecimals with value `$bigDecimal2` doesn't satisfy validation:
                 |  _ > BigDecimal(3.3)
                 |""".stripMargin
            )
        }
      } yield ()
    }

    "Date" - validation { implicit conn =>
      for {
        eid <- Type.dates(date4).save.transact.map(_.eid)
        _ <- Type(eid).dates_?(Some(Set(date1, date2, date4))).update.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Type.dates with value `$date1` doesn't satisfy validation:
                 |  _.after(new Date(1057010400000L))
                 |""".stripMargin,
              s"""Type.dates with value `$date2` doesn't satisfy validation:
                 |  _.after(new Date(1057010400000L))
                 |""".stripMargin
            )
        }
      } yield ()
    }

    "UUID" - validation { implicit conn =>
      for {
        eid <- Type.uuids_?(Some(Set(UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-dddddddddddd")))).save.transact.map(_.eid)
        _ <- Type(eid).uuids_?(Some(Set(UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa")))).update.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Type.uuids with value `aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa` doesn't satisfy validation:
                 |  _.toString != "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"
                 |""".stripMargin
            )
        }
      } yield ()
    }

    "URI" - validation { implicit conn =>
      val uri1 = new URI("a")
      val uri2 = new URI("ab")
      val uri4 = new URI("abcd")
      for {
        eid <- Type.uris_?(Some(Set(uri4))).save.transact.map(_.eid)
        _ <- Type(eid).uris_?(Some(Set(uri1, uri2, uri4))).update.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Type.uris with value `a` doesn't satisfy validation:
                 |  _.toString.length > 3
                 |""".stripMargin,
              s"""Type.uris with value `ab` doesn't satisfy validation:
                 |  _.toString.length > 3
                 |""".stripMargin
            )
        }
      } yield ()
    }

    "Byte" - validation { implicit conn =>
      for {
        eid <- Type.bytes_?(Some(Set(byte4))).save.transact.map(_.eid)
        _ <- Type(eid).bytes_?(Some(Set(byte1, byte2, byte4))).update.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Type.bytes with value `$byte1` doesn't satisfy validation:
                 |  _ > $byte3
                 |""".stripMargin,
              s"""Type.bytes with value `$byte2` doesn't satisfy validation:
                 |  _ > $byte3
                 |""".stripMargin
            )
        }
      } yield ()
    }

    "Short" - validation { implicit conn =>
      for {
        eid <- Type.shorts_?(Some(Set(short4))).save.transact.map(_.eid)
        _ <- Type(eid).shorts_?(Some(Set(short1, short2, short4))).update.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Type.shorts with value `$short1` doesn't satisfy validation:
                 |  _ > $short3
                 |""".stripMargin,
              s"""Type.shorts with value `$short2` doesn't satisfy validation:
                 |  _ > $short3
                 |""".stripMargin
            )
        }
      } yield ()
    }

    "Char" - validation { implicit conn =>
      for {
        eid <- Type.chars_?(Some(Set('d'))).save.transact.map(_.eid)
        _ <- Type(eid).chars_?(Some(Set('a', 'b', 'd'))).update.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Type.chars with value `a` doesn't satisfy validation:
                 |  _ > 'c'
                 |""".stripMargin,
              s"""Type.chars with value `b` doesn't satisfy validation:
                 |  _ > 'c'
                 |""".stripMargin
            )
        }
      } yield ()
    }

    "ref" - validation { implicit conn =>
      for {
        eid <- Type.refs_?(Some(Set(4L))).save.transact.map(_.eid)
        _ <- Type(eid).refs_?(Some(Set(1L, 2L, 4L))).update.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              s"""Type.refs with value `1` doesn't satisfy validation:
                 |  _ > 3L
                 |""".stripMargin,
              s"""Type.refs with value `2` doesn't satisfy validation:
                 |  _ > 3L
                 |""".stripMargin
            )
        }
      } yield ()
    }
  }
}
