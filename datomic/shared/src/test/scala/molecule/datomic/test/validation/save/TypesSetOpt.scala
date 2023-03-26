package molecule.datomic.test.validation.save

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
        _ <- Type.strings_?(Some(Set("-", "a", "b"))).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap ==>
              Map(
                "Type.strings" -> Seq(
                  s"""Type.strings with value `-` doesn't satisfy validation:
                     |  _ > "a"
                     |""".stripMargin,
                  s"""Type.strings with value `a` doesn't satisfy validation:
                     |  _ > "a"
                     |""".stripMargin
                  // (value b is ok)
                )
              )
        }
        // Focusing only on the first (and only) error message
        // (See ValidationFormatting tests for multi-error validations)
        _ <- Type.strings_?(Some(Set("-", "a", "a"))).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap.head._2 ==> Seq(
              s"""Type.strings with value `-` doesn't satisfy validation:
                 |  _ > "a"
                 |""".stripMargin,
              s"""Type.strings with value `a` doesn't satisfy validation:
                 |  _ > "a"
                 |""".stripMargin
            )
        }
      } yield ()
    }

    "Int" - validation { implicit conn =>
      for {
        _ <- Type.ints_?(Some(Set(0, 1, 2))).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap.head._2.sorted ==> Seq(
              s"""Type.ints with value `0` doesn't satisfy validation:
                 |  _ > 1
                 |""".stripMargin,
              s"""Type.ints with value `1` doesn't satisfy validation:
                 |  _ > 1
                 |""".stripMargin
            )
        }
      } yield ()
    }

    "Long" - validation { implicit conn =>
      for {
        _ <- Type.longs_?(Some(Set(0L, 1L, 2L))).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap.head._2 ==> Seq(
              s"""Type.longs with value `0` doesn't satisfy validation:
                 |  _ > 1L
                 |""".stripMargin,
              s"""Type.longs with value `1` doesn't satisfy validation:
                 |  _ > 1L
                 |""".stripMargin
            )
        }
      } yield ()
    }

    "Float" - validation { implicit conn =>
      for {
        _ <- Type.floats_?(Some(Set(float0, float1, float2))).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap.head._2 ==> Seq(
              s"""Type.floats with value `0.0` doesn't satisfy validation:
                 |  _ > 1.1f
                 |""".stripMargin,
              s"""Type.floats with value `1.1` doesn't satisfy validation:
                 |  _ > 1.1f
                 |""".stripMargin
            )
        }
      } yield ()
    }

    "Double" - validation { implicit conn =>
      for {
        _ <- Type.doubles_?(Some(Set(double0, double1, double2))).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap.head._2 ==> Seq(
              s"""Type.doubles with value `0.0` doesn't satisfy validation:
                 |  _ > 1.1
                 |""".stripMargin,
              s"""Type.doubles with value `1.1` doesn't satisfy validation:
                 |  _ > 1.1
                 |""".stripMargin
            )
        }
      } yield ()
    }

    "Boolean" - validation { implicit conn =>
      for {
        _ <- Type.booleans_?(Some(Set(true, false))).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
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
        _ <- Type.bigInts_?(Some(Set(bigInt0, bigInt1, bigInt2))).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap.head._2 ==> Seq(
              s"""Type.bigInts with value `0` doesn't satisfy validation:
                 |  _ > BigInt(1)
                 |""".stripMargin,
              s"""Type.bigInts with value `1` doesn't satisfy validation:
                 |  _ > BigInt(1)
                 |""".stripMargin
            )
        }
      } yield ()
    }

    "BigDecimal" - validation { implicit conn =>
      for {
        _ <- Type.bigDecimals_?(Some(Set(bigDecimal0, bigDecimal1, bigDecimal2))).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap.head._2 ==> Seq(
              s"""Type.bigDecimals with value `0.0` doesn't satisfy validation:
                 |  _ > BigDecimal(1.1)
                 |""".stripMargin,
              s"""Type.bigDecimals with value `1.1` doesn't satisfy validation:
                 |  _ > BigDecimal(1.1)
                 |""".stripMargin
            )
        }
      } yield ()
    }

    "Date" - validation { implicit conn =>
      for {
        _ <- Type.dates_?(Some(Set(date0, date1, date2))).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap.head._2 ==> Seq(
              s"""Type.dates with value `$date0` doesn't satisfy validation:
                 |  _.after(new Date(993942000000L))
                 |""".stripMargin,
              s"""Type.dates with value `$date1` doesn't satisfy validation:
                 |  _.after(new Date(993942000000L))
                 |""".stripMargin
            )
        }
      } yield ()
    }

    "UUID" - validation { implicit conn =>
      for {
        _ <- Type.uuids(Set(
          UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"),
          UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-bbbbbbbbbbbb"))).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap.head._2 ==> Seq(
              s"""Type.uuids with value `aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa` doesn't satisfy validation:
                 |  _.toString != "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"
                 |""".stripMargin
            )
        }
      } yield ()
    }

    "URI" - validation { implicit conn =>
      val uri0 = new URI("x")
      val uri1 = new URI("y")
      val uri2 = new URI("xy")
      for {
        _ <- Type.uris_?(Some(Set(uri0, uri1, uri2))).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap.head._2 ==> Seq(
              s"""Type.uris with value `x` doesn't satisfy validation:
                 |  _.toString.length > 1
                 |""".stripMargin,
              s"""Type.uris with value `y` doesn't satisfy validation:
                 |  _.toString.length > 1
                 |""".stripMargin
            )
        }
      } yield ()
    }

    "Byte" - validation { implicit conn =>
      for {
        _ <- Type.bytes_?(Some(Set(byte0, byte1, byte2))).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap.head._2 ==> Seq(
              s"""Type.bytes with value `$byte0` doesn't satisfy validation:
                 |  _ > $byte1
                 |""".stripMargin,
              s"""Type.bytes with value `$byte1` doesn't satisfy validation:
                 |  _ > $byte1
                 |""".stripMargin
            )
        }
      } yield ()
    }

    "Short" - validation { implicit conn =>
      for {
        _ <- Type.shorts_?(Some(Set(short0, short1, short2))).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap.head._2 ==> Seq(
              s"""Type.shorts with value `$short0` doesn't satisfy validation:
                 |  _ > $short1
                 |""".stripMargin,
              s"""Type.shorts with value `$short1` doesn't satisfy validation:
                 |  _ > $short1
                 |""".stripMargin
            )
        }
      } yield ()
    }

    "Char" - validation { implicit conn =>
      for {
        _ <- Type.chars_?(Some(Set('-', 'a', 'b'))).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap.head._2 ==> Seq(
              s"""Type.chars with value `-` doesn't satisfy validation:
                 |  _ > 'a'
                 |""".stripMargin,
              s"""Type.chars with value `a` doesn't satisfy validation:
                 |  _ > 'a'
                 |""".stripMargin
            )
        }
      } yield ()
    }

    "ref" - validation { implicit conn =>
      for {
        _ <- Type.refs_?(Some(Set(0L, 1L, 2L))).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap.head._2 ==> Seq(
              s"""Type.refs with value `0` doesn't satisfy validation:
                 |  _ > 1L
                 |""".stripMargin,
              s"""Type.refs with value `1` doesn't satisfy validation:
                 |  _ > 1L
                 |""".stripMargin
            )
        }
      } yield ()
    }
  }
}
