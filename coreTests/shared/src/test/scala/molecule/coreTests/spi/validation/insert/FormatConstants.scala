package molecule.coreTests.spi.validation.insert

import molecule.base.error._
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.domains.dsl.Validation._
import molecule.coreTests.setup._
import scala.language.implicitConversions

case class FormatConstants(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  // These test are mainly to confirm that formatting of validation tests
  // and messages in data definition file are transferred correctly to
  // boilerplate code and works as intended.

  import api._
  import suite._

  "Default msg" - validation { implicit conn =>
    for {
      _ <- Constants.noErrorMsg.insert(1).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors ==> Seq(
              (
                0, // row index
                Seq(
                  InsertError(
                    0, // tuple index
                    "Constants.noErrorMsg",
                    Seq(
                      s"""Constants.noErrorMsg with value `1` doesn't satisfy validation:
                         |_ > 2
                         |""".stripMargin
                    ),
                    Nil // nested errors
                  )
                )
              )
            )
        }

      // Same test
      // To shorten the following tests, we isolate the error messages
      _ <- Constants.noErrorMsg.insert(1).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
              s"""Constants.noErrorMsg with value `1` doesn't satisfy validation:
                 |_ > 2
                 |""".stripMargin
            )
        }

      // 1 has correctly not been saved
      _ <- Constants.noErrorMsg.query.get.map(_ ==> Nil)

      // Valid value can be saved
      _ <- Constants.noErrorMsg.insert(3).transact
      _ <- Constants.noErrorMsg.query.get.map(_ ==> List(3))
    } yield ()
  }


  "Msg" - validation { implicit conn =>
    for {
      _ <- Constants.errorMsg.insert(1).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
              "One-line error msg"
            )
        }
    } yield ()
  }


  "Msg with value" - validation { implicit conn =>
    for {
      _ <- Constants.errorMsgWithValue.insert(1).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
              "One-line error msg. Found 1"
            )
        }
    } yield ()
  }


  "Msg with quoted value" - validation { implicit conn =>
    for {
      _ <- Constants.errorMsgWithValueQuoted.insert("hi").transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
              """Expected hello. Found "hi"."""
            )
        }
    } yield ()
  }

  "Msg with quoted value 2" - validation { implicit conn =>
    for {
      _ <- Constants.errorMsgWithValueQuoted2.insert("hi").transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
              """Expected hello. Found "hi"."""
            )
        }
    } yield ()
  }


  "Multi-line msg" - validation { implicit conn =>
    for {
      _ <- Constants.multilineErrorMsg.insert(1).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
              """Long error explanation
                |with multiple lines""".stripMargin
            )
        }
    } yield ()
  }


  "Multi-line msg with value" - validation { implicit conn =>
    for {
      _ <- Constants.multilineMsgWithValue.insert(1).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
              """Validation failed:
                |Input value 1 is not bigger than 2.""".stripMargin
            )
        }
    } yield ()
  }

  "Multi-line msg with value 2" - validation { implicit conn =>
    for {
      _ <- Constants.multilineMsgWithValue2.insert(1).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
              """Validation failed:
                |Input value "1" is not bigger than 2.""".stripMargin
            )
        }
    } yield ()
  }


  "Multi-line test, default msg" - validation { implicit conn =>
    for {
      _ <- Constants.multiLine.insert(1).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
              """Constants.multiLine with value `1` doesn't satisfy validation:
                |v =>
                |  val data   = 22
                |  val result = data % 10
                |  v > result
                |""".stripMargin
            )
        }
    } yield ()
  }


  "Multi-line test, msg" - validation { implicit conn =>
    for {
      _ <- Constants.multiLine2.insert(1).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
              "One-line error msg"
            )
        }
    } yield ()
  }


  "Multi-line test, multi-line msg" - validation { implicit conn =>
    for {
      _ <- Constants.multiLine3.insert(1).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
              """Long error explanation
                |with multiple lines""".stripMargin
            )
        }
    } yield ()
  }


  "Single test line with logic" - validation { implicit conn =>
    for {
      _ <- Constants.logic.insert(1).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
              "Value must be an odd number between 3 and 9 but not 7"
            )
        }
    } yield ()
  }


  "Multiple validations" - validation { implicit conn =>
    for {
      _ <- Constants.multipleErrors.insert(1).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
              "Test 1: Number must be bigger than 2. Found: 1"
            )
        }

      _ <- Constants.multipleErrors.insert(11).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
              "Test 2: Number must be smaller than 10. Found: 11"
            )
        }

      _ <- Constants.multipleErrors.insert(7).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
              "Test 3: Number must not be 7"
            )
        }

      _ <- Constants.multipleErrors.insert(4).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
              """Test 4: Number must
                |be odd. Found: 4""".stripMargin
            )
        }

      _ <- Constants.multipleErrors.insert(0).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors ==> Seq(
              "Test 1: Number must be bigger than 2. Found: 0",
              """Test 4: Number must
                |be odd. Found: 0""".stripMargin
            )
        }
    } yield ()
  }


  "Multiple rows" - validation { implicit conn =>
    for {
      // 0 and 11 are not valid
      _ <- Constants.multipleErrors.insert(
          0,
          5,
          11
        ).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors ==> Seq(
              (
                0, // Top-level row index
                Seq(
                  InsertError(
                    0, // tuple index
                    "Constants.multipleErrors",
                    Seq(
                      """Test 1: Number must be bigger than 2. Found: 0""",
                      s"""Test 4: Number must
                         |be odd. Found: 0""".stripMargin
                    ),
                    Seq()
                  )
                )
              ),

              // Second row (row index 1) with value 5 is ok

              (
                2, // Top-level row index
                Seq(
                  InsertError(
                    0, // tuple index
                    "Constants.multipleErrors",
                    Seq(
                      """Test 2: Number must be smaller than 10. Found: 11"""
                    ),
                    Seq()
                  )
                )
              )
            )
        }

      // No rows have been inserted
      _ <- Constants.multipleErrors.query.get.map(_ ==> Nil)
    } yield ()
  }


  "Multiple attribute validations" - validation { implicit conn =>
    for {
      _ <- Constants.errorMsg.multipleErrors.insert(1, 0).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2 ==> Seq(
              InsertError(
                0, // tuple index
                "Constants.errorMsg",
                Seq(
                  """One-line error msg"""
                ),
                Seq()
              ),
              InsertError(
                1, // tuple index
                "Constants.multipleErrors",
                Seq(
                  """Test 1: Number must be bigger than 2. Found: 0""",
                  s"""Test 4: Number must
                     |be odd. Found: 0""".stripMargin
                ),
                Seq()
              )
            )
        }
    } yield ()
  }


  "3 rows, mixed errors with multi-errors" - validation { implicit conn =>
    for {
      _ <- Constants.errorMsg.multipleErrors.insert(
          (1, 3), // bad  - ok
          (3, 5), // ok   - ok
          (0, 0), // bad  - bad
        ).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors ==> Seq(
              // first row: 1 error
              (
                0, // Top-level row index
                Seq(
                  InsertError(
                    0, // tuple index
                    "Constants.errorMsg",
                    Seq(
                      """One-line error msg"""
                    ),
                    Seq()
                  )
                )
              ),

              // second row has no errors

              // third row: 2 errors
              (
                2, // Top-level row index
                Seq(
                  InsertError(
                    0, // tuple index
                    "Constants.errorMsg",
                    Seq(
                      """One-line error msg"""
                    ),
                    Seq()
                  ),
                  InsertError(
                    1, // tuple index
                    "Constants.multipleErrors",

                    // 2 errors for this attribute
                    Seq(
                      """Test 1: Number must be bigger than 2. Found: 0""",
                      s"""Test 4: Number must
                         |be odd. Found: 0""".stripMargin
                    ),
                    Seq()
                  )
                )
              )
            )
        }
    } yield ()
  }
}
