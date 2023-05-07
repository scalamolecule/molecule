package molecule.datalog.datomic.test.validation.insert

import java.net.URI
import java.util.{Date, UUID}
import molecule.base.error._
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Validation._
import molecule.datalog.datomic.async._
import molecule.datalog.datomic.setup.DatomicTestSuite
import utest._
import scala.language.implicitConversions

object TypesOne extends DatomicTestSuite {

  override lazy val tests = Tests {

    "String" - validation { implicit conn =>
      for {
        _ <- Type.string.insert("a").transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(indexedInsertErrors, _) =>
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
                           |  _ > "b"
                           |""".stripMargin
                      ),
                      Nil // composite/nested errors
                    )
                  )
                )
              )
        }

        // Isolate expected error
        _ <- Type.string.insert("a").transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors.head ==>
              s"""Type.string with value `a` doesn't satisfy validation:
                 |  _ > "b"
                 |""".stripMargin
        }

      } yield ()
    }

    "Int" - validation { implicit conn =>
      for {
        _ <- Type.int.insert(1).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors.head ==>
              s"""Type.int with value `1` doesn't satisfy validation:
                 |  _ > 2
                 |""".stripMargin
        }
      } yield ()
    }

    "Long" - validation { implicit conn =>
      for {
        _ <- Type.long.insert(1L).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors.head ==>
              s"""Type.long with value `1` doesn't satisfy validation:
                 |  _ > 2L
                 |""".stripMargin
        }
      } yield ()
    }

    "Float" - validation { implicit conn =>
      for {
        _ <- Type.float.insert(float1).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors.head ==>
              s"""Type.float with value `$float1` doesn't satisfy validation:
                 |  _ > 2.2f
                 |""".stripMargin
        }
      } yield ()
    }

    "Double" - validation { implicit conn =>
      for {
        _ <- Type.double.insert(double1).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors.head ==>
              s"""Type.double with value `$double1` doesn't satisfy validation:
                 |  _ > 2.2
                 |""".stripMargin
        }
      } yield ()
    }

    "Boolean" - validation { implicit conn =>
      for {
        _ <- Type.boolean.insert(true).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors.head ==>
              s"""Type.boolean with value `true` doesn't satisfy validation:
                 |  _ == false
                 |""".stripMargin
        }
      } yield ()
    }

    "BigInt" - validation { implicit conn =>
      for {
        _ <- Type.bigInt.insert(bigInt1).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors.head ==>
              s"""Type.bigInt with value `$bigInt1` doesn't satisfy validation:
                 |  _ > BigInt(2)
                 |""".stripMargin
        }
      } yield ()
    }

    "BigDecimal" - validation { implicit conn =>
      for {
        _ <- Type.bigDecimal.insert(bigDecimal1).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors.head ==>
              s"""Type.bigDecimal with value `$bigDecimal1` doesn't satisfy validation:
                 |  _ > BigDecimal(2.2)
                 |""".stripMargin
        }
      } yield ()
    }

    "Date" - validation { implicit conn =>
      for {
        _ <- Type.date.insert(date1).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors.head ==>
              s"""Type.date with value `$date1` doesn't satisfy validation:
                 |  _.after(new Date(993942000000L))
                 |""".stripMargin
        }
      } yield ()
    }

    "UUID" - validation { implicit conn =>
      for {
        _ <- Type.uuid.insert(UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa")).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors.head ==>
              s"""Type.uuid with value `aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa` doesn't satisfy validation:
                 |  _.toString != "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"
                 |""".stripMargin
        }
      } yield ()
    }

    "URI" - validation { implicit conn =>
      val uri = new URI("x")
      for {
        _ <- Type.uri.insert(uri).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors.head ==>
              s"""Type.uri with value `x` doesn't satisfy validation:
                 |  _.toString.length > 2
                 |""".stripMargin
        }
      } yield ()
    }

    "Byte" - validation { implicit conn =>
      for {
        _ <- Type.byte.insert(byte1).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors.head ==>
              s"""Type.byte with value `$byte1` doesn't satisfy validation:
                 |  _ > $byte2
                 |""".stripMargin
        }
      } yield ()
    }

    "Short" - validation { implicit conn =>
      for {
        _ <- Type.short.insert(short1).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors.head ==>
              s"""Type.short with value `$short1` doesn't satisfy validation:
                 |  _ > $short2
                 |""".stripMargin
        }
      } yield ()
    }

    "Char" - validation { implicit conn =>
      for {
        _ <- Type.char.insert('a').transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors.head ==>
              s"""Type.char with value `a` doesn't satisfy validation:
                 |  _ > 'b'
                 |""".stripMargin
        }
      } yield ()
    }

    "ref" - validation { implicit conn =>
      for {
        _ <- Type.ref.insert(1L).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors.head ==>
              s"""Type.ref with value `1` doesn't satisfy validation:
                 |  _ > 2L
                 |""".stripMargin
        }
      } yield ()
    }
  }
}