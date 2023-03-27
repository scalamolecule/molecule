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

object Semantics extends DatomicTestSuite {

  override lazy val tests = Tests {

    "1 row, 1 error" - validation { implicit conn =>
      for {
        _ <- Type.int.insert(1).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors ==> Seq(
              (
                0, // row index
                Seq(
                  InsertError(
                    0, // outer tuple index
                    0, // tuple index
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
      } yield ()
    }


    "1 row, 2 errors" - validation { implicit conn =>
      for {
        _ <- Type.int.long.insert((1, 1L)).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors ==> Seq(
              (
                0, // row index
                Seq(
                  InsertError(
                    0, // outer tuple index
                    0, // tuple index, int
                    "Type.int",
                    Seq(
                      s"""Type.int with value `1` doesn't satisfy validation:
                         |  _ > 1
                         |""".stripMargin
                    ),
                    Nil
                  ),
                  InsertError(
                    0, // outer tuple index
                    1, // tuple index, long
                    "Type.long",
                    Seq(
                      s"""Type.long with value `1` doesn't satisfy validation:
                         |  _ > 1L
                         |""".stripMargin
                    ),
                    Nil
                  )
                )
              )
            )
        }
      } yield ()
    }


    "2 rows, 2 errors" - validation { implicit conn =>
      for {
        _ <- Type.int.long.insert(
          (0, 0L),
          (1, 1L),
        ).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors ==> Seq(
              (
                0, // first row
                Seq(
                  InsertError(
                    0, // outer tuple index
                    0, // tuple index, int
                    "Type.int",
                    Seq(
                      s"""Type.int with value `0` doesn't satisfy validation:
                         |  _ > 1
                         |""".stripMargin
                    ),
                    Nil
                  ),
                  InsertError(
                    0, // outer tuple index
                    1, // tuple index, long
                    "Type.long",
                    Seq(
                      s"""Type.long with value `0` doesn't satisfy validation:
                         |  _ > 1L
                         |""".stripMargin
                    ),
                    Nil
                  )
                )
              ),
              (
                1, // second row
                Seq(
                  InsertError(
                    0, // outer tuple index
                    0, // tuple index, int
                    "Type.int",
                    Seq(
                      s"""Type.int with value `1` doesn't satisfy validation:
                         |  _ > 1
                         |""".stripMargin
                    ),
                    Nil
                  ),
                  InsertError(
                    0, // outer tuple index
                    1, // tuple index, long
                    "Type.long",
                    Seq(
                      s"""Type.long with value `1` doesn't satisfy validation:
                         |  _ > 1L
                         |""".stripMargin
                    ),
                    Nil
                  )
                )
              )
            )
        }
      } yield ()
    }


    "3 rows, mixed errors" - validation { implicit conn =>
      for {
        _ <- Type.int.long.insert(
          (1, 3L), // bad - ok
          (2, 2L), // ok  - ok
          (0, 1L), // bad  - bad
        ).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors ==> Seq(
              // first row: 1 error
              (
                0,
                Seq(
                  InsertError(
                    0, // outer tuple index
                    0, // tuple index, int
                    "Type.int",
                    Seq(
                      s"""Type.int with value `1` doesn't satisfy validation:
                         |  _ > 1
                         |""".stripMargin
                    ),
                    Nil
                  )
                )
              ),

              // second row has no errors

              // third row: 2 errors
              (
                2,
                Seq(
                  InsertError(
                    0, // outer tuple index
                    0, // tuple index, int
                    "Type.int",
                    Seq(
                      s"""Type.int with value `0` doesn't satisfy validation:
                         |  _ > 1
                         |""".stripMargin
                    ),
                    Nil
                  ),
                  InsertError(
                    0, // outer tuple index
                    1, // tuple index, long
                    "Type.long",
                    Seq(
                      s"""Type.long with value `1` doesn't satisfy validation:
                         |  _ > 1L
                         |""".stripMargin
                    ),
                    Nil
                  )
                )
              )
            )
        }
      } yield ()
    }


    "3 rows, mixed errors with multi-errors" - validation { implicit conn =>
      for {
        _ <- Format.errorMsg.multipleErrors.insert(
          (1, 3), // bad  - ok
          (3, 5), // ok   - ok
          (0, 0), // bad  - bad
        ).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors ==> Seq(
              // first row: 1 error
              (
                0,
                Seq(
                  InsertError(
                    0, // outer tuple index
                    0, // tuple index, errorMsg
                    "Format.errorMsg",
                    Seq(
                      "One-line error msg"
                    ),
                    Nil
                  )
                )
              ),

              // second row has no errors

              // third row: 2 errors
              (
                2,
                Seq(
                  InsertError(
                    0, // outer tuple index
                    0, // tuple index, errorMsg
                    "Format.errorMsg",
                    Seq(
                      "One-line error msg"
                    ),
                    Nil
                  ),
                  InsertError(
                    0, // outer tuple index
                    1, // tuple index, multipleErrors
                    "Format.multipleErrors",

                    // 2 errors for this attribute
                    Seq(
                      "Number must be bigger than 2. Found: 0",
                      """Number must
                        |be odd. Found: 0""".stripMargin
                    ),
                    Nil
                  )
                )
              )
            )
        }
      } yield ()
    }

  }
}
