package molecule.datomic.test.validation.insert

import molecule.base.error._
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Validation._
import molecule.datomic.async._
import molecule.datomic.setup.DatomicTestSuite
import utest._
import scala.language.implicitConversions

object Composites extends DatomicTestSuite {

  override lazy val tests = Tests {

    "1 + 1" - validation { implicit conn =>
      for {
        _ <- (Type.int + Allowed.luckyNumber).insert(
          (1, 7) // bad, ok
        ).transact.expect {
          case InsertValidationErrors(errors, _) =>
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
                         |  _ > 1
                         |""".stripMargin
                    ),
                    Nil // composite/nested errors
                  )
                )
              )
            )
        }

        _ <- (Type.int + Allowed.luckyNumber).insert(
          (2, 0) // ok, bad
        ).transact.expect {
          case InsertValidationErrors(errors, _) =>
            errors ==> Seq(
              (
                0, // row index
                Seq(
                  InsertError(
                    1, // outer tuple index
                    0, // tuple index of second composite
                    "Allowed.luckyNumber",
                    Seq("Value `0` is not one of the allowed values in Seq(7, 9, 13)"),
                    Nil // composite/nested errors
                  )
                )
              )
            )
        }

        _ <- (Type.int + Allowed.luckyNumber).insert(
          (1, 0) // bad, bad
        ).transact.expect {
          case InsertValidationErrors(errors, _) =>
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
                         |  _ > 1
                         |""".stripMargin
                    ),
                    Nil // composite/nested errors
                  ),
                  InsertError(
                    1, // outer tuple index
                    0, // tuple index of second composite
                    "Allowed.luckyNumber",
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
        _ <- (Type.int.string + Allowed.luckyNumber).insert(
          ((1, "b"), 7) // (bad, ok), ok
        ).transact.expect {
          case InsertValidationErrors(errors, _) =>
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
                         |  _ > 1
                         |""".stripMargin
                    ),
                    Nil // composite/nested errors
                  )
                )
              )
            )
        }

        _ <- (Type.int.string + Allowed.luckyNumber).insert(
          ((2, "a"), 7) // (ok, bad), ok
        ).transact.expect {
          case InsertValidationErrors(errors, _) =>
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
                         |  _ > "a"
                         |""".stripMargin
                    ),
                    Nil // composite/nested errors
                  )
                )
              )
            )
        }

        _ <- (Type.int.string + Allowed.luckyNumber).insert(
          ((2, "b"), 0) // (ok, ok), bad
        ).transact.expect {
          case InsertValidationErrors(errors, _) =>
            errors ==> Seq(
              (
                0, // row index
                Seq(
                  InsertError(
                    1, // outer tuple index
                    0, // tuple index of second composite
                    "Allowed.luckyNumber",
                    Seq("Value `0` is not one of the allowed values in Seq(7, 9, 13)"),
                    Nil // composite/nested errors
                  )
                )
              )
            )
        }

        _ <- (Type.int.string + Allowed.luckyNumber).insert(
          ((1, "a"), 7) // (bad, bad), ok
        ).transact.expect {
          case InsertValidationErrors(errors, _) =>
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
                         |  _ > 1
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
                         |  _ > "a"
                         |""".stripMargin
                    ),
                    Nil // composite/nested errors
                  )
                )
              )
            )
        }

        _ <- (Type.int.string + Allowed.luckyNumber).insert(
          ((1, "b"), 0) // (bad, ok), bad
        ).transact.expect {
          case InsertValidationErrors(errors, _) =>
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
                         |  _ > 1
                         |""".stripMargin
                    ),
                    Nil // composite/nested errors
                  ),
                  InsertError(
                    1, // outer tuple index
                    0, // tuple index of second composite
                    "Allowed.luckyNumber",
                    Seq("Value `0` is not one of the allowed values in Seq(7, 9, 13)"),
                    Nil // composite/nested errors
                  )
                )
              )
            )
        }

        _ <- (Type.int.string + Allowed.luckyNumber).insert(
          ((2, "a"), 0) // (ok, bad), bad
        ).transact.expect {
          case InsertValidationErrors(errors, _) =>
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
                         |  _ > "a"
                         |""".stripMargin
                    ),
                    Nil // composite/nested errors
                  ),
                  InsertError(
                    1, // outer tuple index
                    0, // tuple index of second composite
                    "Allowed.luckyNumber",
                    Seq("Value `0` is not one of the allowed values in Seq(7, 9, 13)"),
                    Nil // composite/nested errors
                  )
                )
              )
            )
        }

        _ <- (Type.int.string + Allowed.luckyNumber).insert(
          ((1, "a"), 0) // (bad, bad), bad
        ).transact.expect {
          case InsertValidationErrors(errors, _) =>
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
                         |  _ > 1
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
                         |  _ > "a"
                         |""".stripMargin
                    ),
                    Nil // composite/nested errors
                  ),
                  InsertError(
                    1, // outer tuple index
                    0, // tuple index of second composite
                    "Allowed.luckyNumber",
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
        _ <- (Allowed.luckyNumber + Type.int.string).insert(
          (7, (1, "b")) // ok, (bad, ok)
        ).transact.expect {
          case InsertValidationErrors(errors, _) =>
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
                         |  _ > 1
                         |""".stripMargin
                    ),
                    Nil // composite/nested errors
                  )
                )
              )
            )
        }

        _ <- (Allowed.luckyNumber + Type.int.string).insert(
          (7, (2, "a")) // ok, (ok, bad)
        ).transact.expect {
          case InsertValidationErrors(errors, _) =>
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
                         |  _ > "a"
                         |""".stripMargin
                    ),
                    Nil // composite/nested errors
                  )
                )
              )
            )
        }

        _ <- (Allowed.luckyNumber + Type.int.string).insert(
          (0, (2, "b")) // bad, (ok, ok)
        ).transact.expect {
          case InsertValidationErrors(errors, _) =>
            errors ==> Seq(
              (
                0, // row index
                Seq(
                  InsertError(
                    0, // outer tuple index
                    0, // tuple index of second composite
                    "Allowed.luckyNumber",
                    Seq("Value `0` is not one of the allowed values in Seq(7, 9, 13)"),
                    Nil // composite/nested errors
                  )
                )
              )
            )
        }

        _ <- (Allowed.luckyNumber + Type.int.string).insert(
          (7, (1, "a")) // ok, (bad, bad)
        ).transact.expect {
          case InsertValidationErrors(errors, _) =>
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
                         |  _ > 1
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
                         |  _ > "a"
                         |""".stripMargin
                    ),
                    Nil // composite/nested errors
                  )
                )
              )
            )
        }

        _ <- (Allowed.luckyNumber + Type.int.string).insert(
          (0, (1, "b")) // bad, (bad, ok)
        ).transact.expect {
          case InsertValidationErrors(errors, _) =>
            errors ==> Seq(
              (
                0, // row index
                Seq(
                  InsertError(
                    0, // outer tuple index
                    0, // tuple index of second composite
                    "Allowed.luckyNumber",
                    Seq("Value `0` is not one of the allowed values in Seq(7, 9, 13)"),
                    Nil // composite/nested errors
                  ),
                  InsertError(
                    1, // outer tuple index
                    0, // tuple index of first composite
                    "Type.int",
                    Seq(
                      s"""Type.int with value `1` doesn't satisfy validation:
                         |  _ > 1
                         |""".stripMargin
                    ),
                    Nil // composite/nested errors
                  )
                )
              )
            )
        }

        _ <- (Allowed.luckyNumber + Type.int.string).insert(
          (0, (2, "a")) // bad, (ok, bad)
        ).transact.expect {
          case InsertValidationErrors(errors, _) =>
            errors ==> Seq(
              (
                0, // row index
                Seq(
                  InsertError(
                    0, // outer tuple index
                    0, // tuple index of second composite
                    "Allowed.luckyNumber",
                    Seq("Value `0` is not one of the allowed values in Seq(7, 9, 13)"),
                    Nil // composite/nested errors
                  ),
                  InsertError(
                    1, // outer tuple index
                    1, // tuple index of first composite
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

        _ <- (Allowed.luckyNumber + Type.int.string).insert(
          (0, (1, "a")) // bad, (bad, bad)
        ).transact.expect {
          case InsertValidationErrors(errors, _) =>
            errors ==> Seq(
              (
                0, // row index
                Seq(
                  InsertError(
                    0, // outer tuple index
                    0, // tuple index of second composite
                    "Allowed.luckyNumber",
                    Seq("Value `0` is not one of the allowed values in Seq(7, 9, 13)"),
                    Nil // composite/nested errors
                  ),
                  InsertError(
                    1, // outer tuple index
                    0, // tuple index of first composite
                    "Type.int",
                    Seq(
                      s"""Type.int with value `1` doesn't satisfy validation:
                         |  _ > 1
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
                         |  _ > "a"
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
        _ <- (Type.int.string + Allowed.luckyNumber.luckyNumber2).insert(
          ((1, "b"), (7, 9)) // (bad, ok), (ok, ok)
        ).transact.expect {
          case InsertValidationErrors(errors, _) =>
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
                         |  _ > 1
                         |""".stripMargin
                    ),
                    Nil // composite/nested errors
                  )
                )
              )
            )
        }

        _ <- (Type.int.string + Allowed.luckyNumber.luckyNumber2).insert(
          ((2, "a"), (7, 9)) // (ok, bad), (ok, ok)
        ).transact.expect {
          case InsertValidationErrors(errors, _) =>
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
                         |  _ > "a"
                         |""".stripMargin
                    ),
                    Nil // composite/nested errors
                  )
                )
              )
            )
        }

        _ <- (Type.int.string + Allowed.luckyNumber.luckyNumber2).insert(
          ((2, "b"), (0, 9)) // (ok, ok), (bad, ok)
        ).transact.expect {
          case InsertValidationErrors(errors, _) =>
            errors ==> Seq(
              (
                0, // row index
                Seq(
                  InsertError(
                    1, // outer tuple index
                    0, // tuple index of second composite
                    "Allowed.luckyNumber",
                    Seq("Value `0` is not one of the allowed values in Seq(7, 9, 13)"),
                    Nil // composite/nested errors
                  )
                )
              )
            )
        }

        _ <- (Type.int.string + Allowed.luckyNumber.luckyNumber2).insert(
          ((2, "b"), (7, 0)) // (ok, ok), (ok, bad)
        ).transact.expect {
          case InsertValidationErrors(errors, _) =>
            errors ==> Seq(
              (
                0, // row index
                Seq(
                  InsertError(
                    1, // outer tuple index
                    1, // tuple index of second composite
                    "Allowed.luckyNumber2",
                    Seq("Lucky number can only be 7, 9 or 13"),
                    Nil // composite/nested errors
                  )
                )
              )
            )
        }

        // Jackpot
        _ <- (Type.int.string + Allowed.luckyNumber.luckyNumber2).insert(
          ((1, "a"), (0, 2)) // (bad, bad), (bad, bad)
        ).transact.expect {
          case InsertValidationErrors(errors, _) =>
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
                         |  _ > 1
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
                         |  _ > "a"
                         |""".stripMargin
                    ),
                    Nil // composite/nested errors
                  ),
                  InsertError(
                    1, // outer tuple index
                    0, // tuple index of second composite
                    "Allowed.luckyNumber",
                    Seq("Value `0` is not one of the allowed values in Seq(7, 9, 13)"),
                    Nil // composite/nested errors
                  ),
                  InsertError(
                    1, // outer tuple index
                    1, // tuple index of second composite
                    "Allowed.luckyNumber2",
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
        _ <- (Type.int.string + Allowed.luckyNumber.luckyNumber2).insert(
          ((2, "a"), (7, 9)), // (ok , bad), (ok , ok)
          ((3, "b"), (9, 9)), // (ok , ok ), (ok , ok)
          ((1, "c"), (13, 0)) // (bad, ok ), (ok, bad)
        ).transact.expect {
          case InsertValidationErrors(errors, _) =>
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
                         |  _ > "a"
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
                         |  _ > 1
                         |""".stripMargin
                    ),
                    Nil // composite/nested errors
                  ),
                  InsertError(
                    1, // outer tuple index
                    1, // tuple index of second composite
                    "Allowed.luckyNumber2",
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
