package molecule.coreTests.compliance.validation.insert

import molecule.base.error._
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Validation._
import molecule.coreTests.setup.CoreTestSuite
import utest._
import scala.language.implicitConversions

trait Semantics extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

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
                      0, // tuple index
                      "Type.int",
                      Seq(
                        s"""Type.int with value `1` doesn't satisfy validation:
                           |  _ > 2
                           |""".stripMargin
                      ),
                      Nil // nested errors
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
                      0, // tuple index, int
                      "Type.int",
                      Seq(
                        s"""Type.int with value `1` doesn't satisfy validation:
                           |  _ > 2
                           |""".stripMargin
                      ),
                      Nil
                    ),
                    InsertError(
                      1, // tuple index, long
                      "Type.long",
                      Seq(
                        s"""Type.long with value `1` doesn't satisfy validation:
                           |  _ > 2L
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
                      0, // tuple index, int
                      "Type.int",
                      Seq(
                        s"""Type.int with value `0` doesn't satisfy validation:
                           |  _ > 2
                           |""".stripMargin
                      ),
                      Nil
                    ),
                    InsertError(
                      1, // tuple index, long
                      "Type.long",
                      Seq(
                        s"""Type.long with value `0` doesn't satisfy validation:
                           |  _ > 2L
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
                      0, // tuple index, int
                      "Type.int",
                      Seq(
                        s"""Type.int with value `1` doesn't satisfy validation:
                           |  _ > 2
                           |""".stripMargin
                      ),
                      Nil
                    ),
                    InsertError(
                      1, // tuple index, long
                      "Type.long",
                      Seq(
                        s"""Type.long with value `1` doesn't satisfy validation:
                           |  _ > 2L
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
            (1, 4L), // bad - ok
            (3, 3L), // ok  - ok
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
                      0, // tuple index, int
                      "Type.int",
                      Seq(
                        s"""Type.int with value `1` doesn't satisfy validation:
                           |  _ > 2
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
                      0, // tuple index, int
                      "Type.int",
                      Seq(
                        s"""Type.int with value `0` doesn't satisfy validation:
                           |  _ > 2
                           |""".stripMargin
                      ),
                      Nil
                    ),
                    InsertError(
                      1, // tuple index, long
                      "Type.long",
                      Seq(
                        s"""Type.long with value `1` doesn't satisfy validation:
                           |  _ > 2L
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
  }
}
