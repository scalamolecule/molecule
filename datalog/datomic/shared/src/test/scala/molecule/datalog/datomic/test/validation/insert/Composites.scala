package molecule.datalog.datomic.test.validation.insert

import molecule.base.error._
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Validation._
import molecule.datalog.datomic.async._
import molecule.datalog.datomic.setup.DatomicTestSuite
import utest._
import scala.language.implicitConversions

object Composites extends DatomicTestSuite {

  override lazy val tests = Tests {

    "1 + 1" - validation { implicit conn =>
      for {
        _ <- (Type.int + Enum.luckyNumber).insert(
          (1, 7) // bad, ok
        ).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors ==> Seq(
              (
                0, // row index
                Seq(
                  InsertError(
                    0, // outer tuple index
                    0, // tuple index of first composite
                    "Type.int",
                    Seq(
                      s"""Type.int with value `1` doesn't satisfy validation:
                         |  _ > 2
                         |""".stripMargin
                    ),
                    Nil // composite/nested errors
                  )
                )
              )
            )
        }

        _ <- (Type.int + Enum.luckyNumber).insert(
          (3, 0) // ok, bad
        ).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors ==> Seq(
              (
                0, // row index
                Seq(
                  InsertError(
                    1, // outer tuple index
                    0, // tuple index of second composite
                    "Enum.luckyNumber",
                    Seq("Value `0` is not one of the allowed values in Seq(7, 9, 13)"),
                    Nil // composite/nested errors
                  )
                )
              )
            )
        }

        _ <- (Type.int + Enum.luckyNumber).insert(
          (1, 0) // bad, bad
        ).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors ==> Seq(
              (
                0, // row index
                Seq(
                  InsertError(
                    0, // outer tuple index
                    0, // tuple index of first composite
                    "Type.int",
                    Seq(
                      s"""Type.int with value `1` doesn't satisfy validation:
                         |  _ > 2
                         |""".stripMargin
                    ),
                    Nil // composite/nested errors
                  ),
                  InsertError(
                    1, // outer tuple index
                    0, // tuple index of second composite
                    "Enum.luckyNumber",
                    Seq("Value `0` is not one of the allowed values in Seq(7, 9, 13)"),
                    Nil // composite/nested errors
                  )
                )
              )
            )
        }
      } yield ()
    }


    "2 + 1" - validation { implicit conn =>
      for {
        _ <- (Type.int.string + Enum.luckyNumber).insert(
          ((1, "c"), 7) // (bad, ok), ok
        ).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors ==> Seq(
              (
                0, // row index
                Seq(
                  InsertError(
                    0, // outer tuple index
                    0, // tuple index of first composite
                    "Type.int",
                    Seq(
                      s"""Type.int with value `1` doesn't satisfy validation:
                         |  _ > 2
                         |""".stripMargin
                    ),
                    Nil // composite/nested errors
                  )
                )
              )
            )
        }

        _ <- (Type.int.string + Enum.luckyNumber).insert(
          ((3, "a"), 7) // (ok, bad), ok
        ).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors ==> Seq(
              (
                0, // row index
                Seq(
                  InsertError(
                    0, // outer tuple index
                    1, // tuple index of first composite
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

        _ <- (Type.int.string + Enum.luckyNumber).insert(
          ((3, "c"), 0) // (ok, ok), bad
        ).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors ==> Seq(
              (
                0, // row index
                Seq(
                  InsertError(
                    1, // outer tuple index
                    0, // tuple index of second composite
                    "Enum.luckyNumber",
                    Seq("Value `0` is not one of the allowed values in Seq(7, 9, 13)"),
                    Nil // composite/nested errors
                  )
                )
              )
            )
        }

        _ <- (Type.int.string + Enum.luckyNumber).insert(
          ((1, "a"), 7) // (bad, bad), ok
        ).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors ==> Seq(
              (
                0, // row index
                Seq(
                  InsertError(
                    0, // outer tuple index
                    0, // tuple index of first composite
                    "Type.int",
                    Seq(
                      s"""Type.int with value `1` doesn't satisfy validation:
                         |  _ > 2
                         |""".stripMargin
                    ),
                    Nil // composite/nested errors
                  ),
                  InsertError(
                    0, // outer tuple index
                    1, // tuple index of first composite
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

        _ <- (Type.int.string + Enum.luckyNumber).insert(
          ((1, "c"), 0) // (bad, ok), bad
        ).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors ==> Seq(
              (
                0, // row index
                Seq(
                  InsertError(
                    0, // outer tuple index
                    0, // tuple index of first composite
                    "Type.int",
                    Seq(
                      s"""Type.int with value `1` doesn't satisfy validation:
                         |  _ > 2
                         |""".stripMargin
                    ),
                    Nil // composite/nested errors
                  ),
                  InsertError(
                    1, // outer tuple index
                    0, // tuple index of second composite
                    "Enum.luckyNumber",
                    Seq("Value `0` is not one of the allowed values in Seq(7, 9, 13)"),
                    Nil // composite/nested errors
                  )
                )
              )
            )
        }

        _ <- (Type.int.string + Enum.luckyNumber).insert(
          ((3, "a"), 0) // (ok, bad), bad
        ).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors ==> Seq(
              (
                0, // row index
                Seq(
                  InsertError(
                    0, // outer tuple index
                    1, // tuple index of first composite
                    "Type.string",
                    Seq(
                      s"""Type.string with value `a` doesn't satisfy validation:
                         |  _ > "b"
                         |""".stripMargin
                    ),
                    Nil // composite/nested errors
                  ),
                  InsertError(
                    1, // outer tuple index
                    0, // tuple index of second composite
                    "Enum.luckyNumber",
                    Seq("Value `0` is not one of the allowed values in Seq(7, 9, 13)"),
                    Nil // composite/nested errors
                  )
                )
              )
            )
        }

        _ <- (Type.int.string + Enum.luckyNumber).insert(
          ((1, "a"), 0) // (bad, bad), bad
        ).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors ==> Seq(
              (
                0, // row index
                Seq(
                  InsertError(
                    0, // outer tuple index
                    0, // tuple index of first composite
                    "Type.int",
                    Seq(
                      s"""Type.int with value `1` doesn't satisfy validation:
                         |  _ > 2
                         |""".stripMargin
                    ),
                    Nil // composite/nested errors
                  ),
                  InsertError(
                    0, // outer tuple index
                    1, // tuple index of first composite
                    "Type.string",
                    Seq(
                      s"""Type.string with value `a` doesn't satisfy validation:
                         |  _ > "b"
                         |""".stripMargin
                    ),
                    Nil // composite/nested errors
                  ),
                  InsertError(
                    1, // outer tuple index
                    0, // tuple index of second composite
                    "Enum.luckyNumber",
                    Seq("Value `0` is not one of the allowed values in Seq(7, 9, 13)"),
                    Nil // composite/nested errors
                  )
                )
              )
            )
        }
      } yield ()
    }


    "1 + 2" - validation { implicit conn =>
      for {
        _ <- (Enum.luckyNumber + Type.int.string).insert(
          (7, (1, "c")) // ok, (bad, ok)
        ).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors ==> Seq(
              (
                0, // row index
                Seq(
                  InsertError(
                    1, // outer tuple index
                    0, // tuple index of first composite
                    "Type.int",
                    Seq(
                      s"""Type.int with value `1` doesn't satisfy validation:
                         |  _ > 2
                         |""".stripMargin
                    ),
                    Nil // composite/nested errors
                  )
                )
              )
            )
        }

        _ <- (Enum.luckyNumber + Type.int.string).insert(
          (7, (3, "a")) // ok, (ok, bad)
        ).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors ==> Seq(
              (
                0, // row index
                Seq(
                  InsertError(
                    1, // outer tuple index
                    1, // tuple index of first composite
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

        _ <- (Enum.luckyNumber + Type.int.string).insert(
          (0, (3, "c")) // bad, (ok, ok)
        ).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors ==> Seq(
              (
                0, // row index
                Seq(
                  InsertError(
                    0, // outer tuple index
                    0, // tuple index of second composite
                    "Enum.luckyNumber",
                    Seq("Value `0` is not one of the allowed values in Seq(7, 9, 13)"),
                    Nil // composite/nested errors
                  )
                )
              )
            )
        }

        _ <- (Enum.luckyNumber + Type.int.string).insert(
          (7, (1, "a")) // ok, (bad, bad)
        ).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors ==> Seq(
              (
                0, // row index
                Seq(
                  InsertError(
                    1, // outer tuple index
                    0, // tuple index of first composite
                    "Type.int",
                    Seq(
                      s"""Type.int with value `1` doesn't satisfy validation:
                         |  _ > 2
                         |""".stripMargin
                    ),
                    Nil // composite/nested errors
                  ),
                  InsertError(
                    1, // outer tuple index
                    1, // tuple index of first composite
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

        _ <- (Enum.luckyNumber + Type.int.string).insert(
          (0, (1, "c")) // bad, (bad, ok)
        ).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors ==> Seq(
              (
                0, // row index
                Seq(
                  InsertError(
                    0, // outer tuple index
                    0, // tuple index of second composite
                    "Enum.luckyNumber",
                    Seq("Value `0` is not one of the allowed values in Seq(7, 9, 13)"),
                    Nil // composite/nested errors
                  ),
                  InsertError(
                    1, // outer tuple index
                    0, // tuple index of first composite
                    "Type.int",
                    Seq(
                      s"""Type.int with value `1` doesn't satisfy validation:
                         |  _ > 2
                         |""".stripMargin
                    ),
                    Nil // composite/nested errors
                  )
                )
              )
            )
        }

        _ <- (Enum.luckyNumber + Type.int.string).insert(
          (0, (3, "a")) // bad, (ok, bad)
        ).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors ==> Seq(
              (
                0, // row index
                Seq(
                  InsertError(
                    0, // outer tuple index
                    0, // tuple index of second composite
                    "Enum.luckyNumber",
                    Seq("Value `0` is not one of the allowed values in Seq(7, 9, 13)"),
                    Nil // composite/nested errors
                  ),
                  InsertError(
                    1, // outer tuple index
                    1, // tuple index of first composite
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

        _ <- (Enum.luckyNumber + Type.int.string).insert(
          (0, (1, "a")) // bad, (bad, bad)
        ).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors ==> Seq(
              (
                0, // row index
                Seq(
                  InsertError(
                    0, // outer tuple index
                    0, // tuple index of second composite
                    "Enum.luckyNumber",
                    Seq("Value `0` is not one of the allowed values in Seq(7, 9, 13)"),
                    Nil // composite/nested errors
                  ),
                  InsertError(
                    1, // outer tuple index
                    0, // tuple index of first composite
                    "Type.int",
                    Seq(
                      s"""Type.int with value `1` doesn't satisfy validation:
                         |  _ > 2
                         |""".stripMargin
                    ),
                    Nil // composite/nested errors
                  ),
                  InsertError(
                    1, // outer tuple index
                    1, // tuple index of first composite
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
      } yield ()
    }


    "2 + 2" - validation { implicit conn =>
      for {
        _ <- (Type.int.string + Enum.luckyNumber.luckyNumber2).insert(
          ((1, "c"), (7, 9)) // (bad, ok), (ok, ok)
        ).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors ==> Seq(
              (
                0, // row index
                Seq(
                  InsertError(
                    0, // outer tuple index
                    0, // tuple index of first composite
                    "Type.int",
                    Seq(
                      s"""Type.int with value `1` doesn't satisfy validation:
                         |  _ > 2
                         |""".stripMargin
                    ),
                    Nil // composite/nested errors
                  )
                )
              )
            )
        }

        _ <- (Type.int.string + Enum.luckyNumber.luckyNumber2).insert(
          ((3, "a"), (7, 9)) // (ok, bad), (ok, ok)
        ).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors ==> Seq(
              (
                0, // row index
                Seq(
                  InsertError(
                    0, // outer tuple index
                    1, // tuple index of first composite
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

        _ <- (Type.int.string + Enum.luckyNumber.luckyNumber2).insert(
          ((3, "c"), (0, 9)) // (ok, ok), (bad, ok)
        ).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors ==> Seq(
              (
                0, // row index
                Seq(
                  InsertError(
                    1, // outer tuple index
                    0, // tuple index of second composite
                    "Enum.luckyNumber",
                    Seq("Value `0` is not one of the allowed values in Seq(7, 9, 13)"),
                    Nil // composite/nested errors
                  )
                )
              )
            )
        }

        _ <- (Type.int.string + Enum.luckyNumber.luckyNumber2).insert(
          ((3, "c"), (7, 0)) // (ok, ok), (ok, bad)
        ).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors ==> Seq(
              (
                0, // row index
                Seq(
                  InsertError(
                    1, // outer tuple index
                    1, // tuple index of second composite
                    "Enum.luckyNumber2",
                    Seq("Lucky number can only be 7, 9 or 13"),
                    Nil // composite/nested errors
                  )
                )
              )
            )
        }

        // Jackpot
        _ <- (Type.int.string + Enum.luckyNumber.luckyNumber2).insert(
          ((1, "a"), (0, 2)) // (bad, bad), (bad, bad)
        ).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors ==> Seq(
              (
                0, // row index
                Seq(
                  InsertError(
                    0, // outer tuple index
                    0, // tuple index of first composite
                    "Type.int",
                    Seq(
                      s"""Type.int with value `1` doesn't satisfy validation:
                         |  _ > 2
                         |""".stripMargin
                    ),
                    Nil // composite/nested errors
                  ),
                  InsertError(
                    0, // outer tuple index
                    1, // tuple index of first composite
                    "Type.string",
                    Seq(
                      s"""Type.string with value `a` doesn't satisfy validation:
                         |  _ > "b"
                         |""".stripMargin
                    ),
                    Nil // composite/nested errors
                  ),
                  InsertError(
                    1, // outer tuple index
                    0, // tuple index of second composite
                    "Enum.luckyNumber",
                    Seq("Value `0` is not one of the allowed values in Seq(7, 9, 13)"),
                    Nil // composite/nested errors
                  ),
                  InsertError(
                    1, // outer tuple index
                    1, // tuple index of second composite
                    "Enum.luckyNumber2",
                    Seq("Lucky number can only be 7, 9 or 13"),
                    Nil // composite/nested errors
                  )
                )
              )
            )
        }
      } yield ()
    }


    "Multiple rows" - validation { implicit conn =>
      for {
        _ <- (Type.int.string + Enum.luckyNumber.luckyNumber2).insert(
          ((3, "a"), (7, 9)), // (ok , bad), (ok , ok)
          ((4, "c"), (9, 9)), // (ok , ok ), (ok , ok)
          ((1, "c"), (13, 0)) // (bad, ok ), (ok, bad)
        ).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors ==> Seq(
              (
                0, // row index of first row
                Seq(
                  InsertError(
                    0, // outer tuple index
                    1, // tuple index of first composite
                    "Type.string",
                    Seq(
                      s"""Type.string with value `a` doesn't satisfy validation:
                         |  _ > "b"
                         |""".stripMargin
                    ),
                    Nil // composite/nested errors
                  )
                )
              ),

              // Second row is ok

              (
                2, // row index of third row
                Seq(
                  InsertError(
                    0, // outer tuple index
                    0, // tuple index of first composite
                    "Type.int",
                    Seq(
                      s"""Type.int with value `1` doesn't satisfy validation:
                         |  _ > 2
                         |""".stripMargin
                    ),
                    Nil // composite/nested errors
                  ),
                  InsertError(
                    1, // outer tuple index
                    1, // tuple index of second composite
                    "Enum.luckyNumber2",
                    Seq("Lucky number can only be 7, 9 or 13"),
                    Nil // composite/nested errors
                  )
                )
              )
            )
        }
      }
      yield ()
    }
  }
}
