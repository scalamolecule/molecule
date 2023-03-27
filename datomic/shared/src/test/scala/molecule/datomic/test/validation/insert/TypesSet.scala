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
        _ <- Type.strings.insert(Set("-", "a", "b")).transact
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
                        s"""Type.strings with value `-` doesn't satisfy validation:
                           |  _ > "a"
                           |""".stripMargin,
                        s"""Type.strings with value `a` doesn't satisfy validation:
                           |  _ > "a"
                           |""".stripMargin
                        // (value b is ok)
                      ),
                      Nil // composite/nested errors
                    )
                  )
                )
              )
        }

        // Isolate expected single InsertError with pattern matching
        _ <- Type.strings.insert(Set("-", "a", "b")).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(Seq((_, Seq(insertError))), _) =>
            insertError ==> InsertError(0, 0,
              "Type.strings",
              Seq(
                s"""Type.strings with value `-` doesn't satisfy validation:
                   |  _ > "a"
                   |""".stripMargin,
                s"""Type.strings with value `a` doesn't satisfy validation:
                   |  _ > "a"
                   |""".stripMargin
              ), Nil)
        }

        // Isolate expected errors
        _ <- Type.strings.insert(Set("-", "a", "b")).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(Seq((_, Seq(InsertError(_, _, _, errors, _)))), _) =>
            errors ==> Seq(
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
        _ <- Type.ints.insert(Set(0, 1, 2)).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(Seq((_, Seq(InsertError(_, _, _, errors, _)))), _) =>
            errors ==> Seq(
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
        _ <- Type.longs.insert(Set(0L, 1L, 2L)).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(Seq((_, Seq(InsertError(_, _, _, errors, _)))), _) =>
            errors ==> Seq(
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
        _ <- Type.floats.insert(Set(float0, float1, float2)).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(Seq((_, Seq(InsertError(_, _, _, errors, _)))), _) =>
            errors ==> Seq(
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
        _ <- Type.doubles.insert(Set(double0, double1, double2)).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(Seq((_, Seq(InsertError(_, _, _, errors, _)))), _) =>
            errors ==> Seq(
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
        _ <- Type.booleans.insert(Set(true, false)).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(Seq((_, Seq(InsertError(_, _, _, errors, _)))), _) =>
            errors ==> Seq(
              s"""Type.booleans with value `true` doesn't satisfy validation:
                 |  _ == false
                 |""".stripMargin
            )
        }
      } yield ()
    }

    "BigInt" - validation { implicit conn =>
      for {
        _ <- Type.bigInts.insert(Set(bigInt0, bigInt1, bigInt2)).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(Seq((_, Seq(InsertError(_, _, _, errors, _)))), _) =>
            errors ==> Seq(
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
        _ <- Type.bigDecimals.insert(Set(bigDecimal0, bigDecimal1, bigDecimal2)).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(Seq((_, Seq(InsertError(_, _, _, errors, _)))), _) =>
            errors ==> Seq(
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
        _ <- Type.dates.insert(Set(date0, date1, date2)).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(Seq((_, Seq(InsertError(_, _, _, errors, _)))), _) =>
            errors ==> Seq(
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
        _ <- Type.uuids.insert(Set(
          UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"),
          UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-bbbbbbbbbbbb")
        )).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(Seq((_, Seq(InsertError(_, _, _, errors, _)))), _) =>
            errors ==> Seq(
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
        _ <- Type.uris.insert(Set(uri0, uri1, uri2)).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(Seq((_, Seq(InsertError(_, _, _, errors, _)))), _) =>
            errors ==> Seq(
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
        _ <- Type.bytes.insert(Set(byte0, byte1, byte2)).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(Seq((_, Seq(InsertError(_, _, _, errors, _)))), _) =>
            errors ==> Seq(
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
        _ <- Type.shorts.insert(Set(short0, short1, short2)).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(Seq((_, Seq(InsertError(_, _, _, errors, _)))), _) =>
            errors ==> Seq(
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
        _ <- Type.chars.insert(Set('-', 'a', 'b')).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(Seq((_, Seq(InsertError(_, _, _, errors, _)))), _) =>
            errors ==> Seq(
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
        _ <- Type.refs.insert(Set(0L, 1L, 2L)).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(Seq((_, Seq(InsertError(_, _, _, errors, _)))), _) =>
            errors ==> Seq(
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
