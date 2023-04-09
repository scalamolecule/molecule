package molecule.datomic.test.validation.insert

import java.net.URI
import java.util.{Date, UUID}
import molecule.base.error._
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Validation._
import molecule.datomic.async._
import molecule.datomic.setup.DatomicTestSuite
import molecule.datomic.test.validation.save.TypesSet.{date0, date1, date2}
import utest._
import scala.language.implicitConversions

object TypesSet extends DatomicTestSuite {

  override lazy val tests = Tests {

    "String" - validation { implicit conn =>
      for {
        _ <- Type.strings.insert(Set("a", "b", "d")).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(indexedInsertErrors, _) =>
            indexedInsertErrors ==>
              Seq(
                (
                  0, // Row index
                  Seq(
                    InsertError(
                      0, // outer tuple index
                      0, // tuple index
                      "Type.strings",
                      Seq(
                        s"""Type.strings with value `a` doesn't satisfy validation:
                           |  _ > "c"
                           |""".stripMargin,
                        s"""Type.strings with value `b` doesn't satisfy validation:
                           |  _ > "c"
                           |""".stripMargin
                        // (value d is ok)
                      ),
                      Nil // composite/nested errors
                    )
                  )
                )
              )
        }

        // Isolate expected errors
        _ <- Type.strings.insert(Set("a", "b", "d")).transact
          .map(_ ==> "Unexpected success").recover {

          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
              s"""Type.strings with value `a` doesn't satisfy validation:
                 |  _ > "c"
                 |""".stripMargin,
              s"""Type.strings with value `b` doesn't satisfy validation:
                 |  _ > "c"
                 |""".stripMargin
            )
        }

      } yield ()
    }

    "Int" - validation { implicit conn =>
      for {
        _ <- Type.ints.insert(Set(1, 2, 4)).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
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
        _ <- Type.longs.insert(Set(1L, 2L, 4L)).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
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
        _ <- Type.floats.insert(Set(float1, float2, float4)).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
              s"""Type.floats with value `1.1` doesn't satisfy validation:
                 |  _ > 3.3f
                 |""".stripMargin,
              s"""Type.floats with value `2.2` doesn't satisfy validation:
                 |  _ > 3.3f
                 |""".stripMargin
            )
        }
      } yield ()
    }

    "Double" - validation { implicit conn =>
      for {
        _ <- Type.doubles.insert(Set(double1, double2, double4)).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
              s"""Type.doubles with value `1.1` doesn't satisfy validation:
                 |  _ > 3.3
                 |""".stripMargin,
              s"""Type.doubles with value `2.2` doesn't satisfy validation:
                 |  _ > 3.3
                 |""".stripMargin
            )
        }
      } yield ()
    }

    "Boolean" - validation { implicit conn =>
      for {
        _ <- Type.booleans.insert(Set(true, false)).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
              s"""Type.booleans with value `true` doesn't satisfy validation:
                 |  _ == false
                 |""".stripMargin
            )
        }
      } yield ()
    }

    "BigInt" - validation { implicit conn =>
      for {
        _ <- Type.bigInts.insert(Set(bigInt1, bigInt2, bigInt4)).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
              s"""Type.bigInts with value `1` doesn't satisfy validation:
                 |  _ > BigInt(3)
                 |""".stripMargin,
              s"""Type.bigInts with value `2` doesn't satisfy validation:
                 |  _ > BigInt(3)
                 |""".stripMargin
            )
        }
      } yield ()
    }

    "BigDecimal" - validation { implicit conn =>
      for {
        _ <- Type.bigDecimals.insert(Set(bigDecimal1, bigDecimal2, bigDecimal4)).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
              s"""Type.bigDecimals with value `1.1` doesn't satisfy validation:
                 |  _ > BigDecimal(3.3)
                 |""".stripMargin,
              s"""Type.bigDecimals with value `2.2` doesn't satisfy validation:
                 |  _ > BigDecimal(3.3)
                 |""".stripMargin
            )
        }
      } yield ()
    }

    "Date" - validation { implicit conn =>
      for {
        _ <- Type.dates.insert(Set(date1, date2, date4)).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
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
        _ <- Type.uuids.insert(Set(
          UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"),
          UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-bbbbbbbbbbbb")
        )).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
              s"""Type.uuids with value `aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa` doesn't satisfy validation:
                 |  _.toString != "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"
                 |""".stripMargin
            )
        }
      } yield ()
    }

    "URI" - validation { implicit conn =>
      val uri0 = new URI("a")
      val uri1 = new URI("ab")
      val uri2 = new URI("abcd")
      for {
        _ <- Type.uris.insert(Set(uri0, uri1, uri2)).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
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
        _ <- Type.bytes.insert(Set(byte1, byte2, byte4)).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
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
        _ <- Type.shorts.insert(Set(short1, short2, short4)).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
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
        _ <- Type.chars.insert(Set('a', 'b', 'd')).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
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
        _ <- Type.refs.insert(Set(1L, 2L, 4L)).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
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
