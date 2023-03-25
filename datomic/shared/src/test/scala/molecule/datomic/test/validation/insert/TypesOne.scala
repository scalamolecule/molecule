package molecule.datomic.test.validation.insert

import java.net.URI
import java.util.{Date, UUID}
import molecule.base.error._
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Validation._
import molecule.datomic.async._
import molecule.datomic.setup.DatomicTestSuite
import utest._
import scala.language.implicitConversions

object TypesOne extends DatomicTestSuite {

  override lazy val tests = Tests {

    "String" - validation { implicit conn =>
      for {
        _ <- Type.string.insert("a").transact.expect {
          case InsertValidationErrors(indexedInsertErrors, _) =>
            indexedInsertErrors ==>
              Seq(
                (
                  0, // Row index
                  Seq(
                    InsertError(
                      0, // outerb tuple index
                      0, // tuple index
                      "Type.string",
                      Seq(
                        s"""Type.string with value `a` doesn't satisfy validation:
                           |  _ > "a"
                           |""".stripMargin
                      ),
                      Nil // composite/nested errors
                    )
                  )
                )
              )
        }

        // Isolate expected single InsertError with pattern matching
        _ <- Type.string.insert("a").transact.expect {
          case InsertValidationErrors(Seq((_, Seq(insertError))), _) =>
            insertError ==> InsertError(0, 0,
              "Type.string",
              Seq(
                s"""Type.string with value `a` doesn't satisfy validation:
                   |  _ > "a"
                   |""".stripMargin
              ), Nil)
        }

        // Isolate expected single error alone
        _ <- Type.string.insert("a").transact.expect {
          case InsertValidationErrors(Seq((_, Seq(InsertError(_, _, _, Seq(error), _)))), _) =>
            error ==>
              s"""Type.string with value `a` doesn't satisfy validation:
                 |  _ > "a"
                 |""".stripMargin
        }

      } yield ()
    }

    "Int" - validation { implicit conn =>
      for {
        _ <- Type.int.insert(1).transact.expect {
          case InsertValidationErrors(Seq((_, Seq(InsertError(_, _, _, Seq(error), _)))), _) =>
            error ==>
              s"""Type.int with value `1` doesn't satisfy validation:
                 |  _ > 1
                 |""".stripMargin
        }
      } yield ()
    }

    "Long" - validation { implicit conn =>
      for {
        _ <- Type.long.insert(1L).transact.expect {
          case InsertValidationErrors(Seq((_, Seq(InsertError(_, _, _, Seq(error), _)))), _) =>
            error ==>
              s"""Type.long with value `1` doesn't satisfy validation:
                 |  _ > 1L
                 |""".stripMargin
        }
      } yield ()
    }

    "Float" - validation { implicit conn =>
      for {
        _ <- Type.float.insert(1.1f).transact.expect {
          case InsertValidationErrors(Seq((_, Seq(InsertError(_, _, _, Seq(error), _)))), _) =>
            error ==>
              s"""Type.float with value `1.1` doesn't satisfy validation:
                 |  _ > 1.1f
                 |""".stripMargin
        }
      } yield ()
    }

    "Double" - validation { implicit conn =>
      for {
        _ <- Type.double.insert(1.1).transact.expect {
          case InsertValidationErrors(Seq((_, Seq(InsertError(_, _, _, Seq(error), _)))), _) =>
            error ==>
              s"""Type.double with value `1.1` doesn't satisfy validation:
                 |  _ > 1.1
                 |""".stripMargin
        }
      } yield ()
    }

    "Boolean" - validation { implicit conn =>
      for {
        _ <- Type.boolean.insert(true).transact.expect {
          case InsertValidationErrors(Seq((_, Seq(InsertError(_, _, _, Seq(error), _)))), _) =>
            error ==>
              s"""Type.boolean with value `true` doesn't satisfy validation:
                 |  _ == false
                 |""".stripMargin
        }
      } yield ()
    }

    "BigInt" - validation { implicit conn =>
      for {
        _ <- Type.bigInt.insert(BigInt(1)).transact.expect {
          case InsertValidationErrors(Seq((_, Seq(InsertError(_, _, _, Seq(error), _)))), _) =>
            error ==>
              s"""Type.bigInt with value `1` doesn't satisfy validation:
                 |  _ > BigInt(1)
                 |""".stripMargin
        }
      } yield ()
    }

    "BigDecimal" - validation { implicit conn =>
      for {
        _ <- Type.bigDecimal.insert(BigDecimal(1.1)).transact.expect {
          case InsertValidationErrors(Seq((_, Seq(InsertError(_, _, _, Seq(error), _)))), _) =>
            error ==>
              s"""Type.bigDecimal with value `1.1` doesn't satisfy validation:
                 |  _ > BigDecimal(1.1)
                 |""".stripMargin
        }
      } yield ()
    }

    "Date" - validation { implicit conn =>
      for {
        _ <- Type.date.insert(new Date(993942000000L)).transact.expect {
          case InsertValidationErrors(Seq((_, Seq(InsertError(_, _, _, Seq(error), _)))), _) =>
            error ==>
              s"""Type.date with value `$date1` doesn't satisfy validation:
                 |  _.after(new Date(993942000000L))
                 |""".stripMargin
        }
      } yield ()
    }

    "UUID" - validation { implicit conn =>
      for {
        _ <- Type.uuid.insert(UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa")).transact.expect {
          case InsertValidationErrors(Seq((_, Seq(InsertError(_, _, _, Seq(error), _)))), _) =>
            error ==>
              s"""Type.uuid with value `aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa` doesn't satisfy validation:
                 |  _.toString != "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"
                 |""".stripMargin
        }
      } yield ()
    }

    "URI" - validation { implicit conn =>
      val uri = new URI("x")
      for {
        _ <- Type.uri.insert(uri).transact.expect {
          case InsertValidationErrors(Seq((_, Seq(InsertError(_, _, _, Seq(error), _)))), _) =>
            error ==>
              s"""Type.uri with value `x` doesn't satisfy validation:
                 |  _.toString.length > 1
                 |""".stripMargin
        }
      } yield ()
    }

    "Byte" - validation { implicit conn =>
      for {
        _ <- Type.byte.insert(byte1).transact.expect {
          case InsertValidationErrors(Seq((_, Seq(InsertError(_, _, _, Seq(error), _)))), _) =>
            error ==>
              s"""Type.byte with value `$byte1` doesn't satisfy validation:
                 |  _ > $byte1
                 |""".stripMargin
        }
      } yield ()
    }

    "Short" - validation { implicit conn =>
      for {
        _ <- Type.short.insert(short1).transact.expect {
          case InsertValidationErrors(Seq((_, Seq(InsertError(_, _, _, Seq(error), _)))), _) =>
            error ==>
              s"""Type.short with value `$short1` doesn't satisfy validation:
                 |  _ > $short1
                 |""".stripMargin
        }
      } yield ()
    }

    "Char" - validation { implicit conn =>
      for {
        _ <- Type.char.insert('a').transact.expect {
          case InsertValidationErrors(Seq((_, Seq(InsertError(_, _, _, Seq(error), _)))), _) =>
            error ==>
              s"""Type.char with value `a` doesn't satisfy validation:
                 |  _ > 'a'
                 |""".stripMargin
        }
      } yield ()
    }

    "ref" - validation { implicit conn =>
      for {
        _ <- Type.ref.insert(1L).transact.expect {
          case InsertValidationErrors(Seq((_, Seq(InsertError(_, _, _, Seq(error), _)))), _) =>
            error ==>
              s"""Type.ref with value `1` doesn't satisfy validation:
                 |  _ > 1L
                 |""".stripMargin
        }
      } yield ()
    }
  }
}
