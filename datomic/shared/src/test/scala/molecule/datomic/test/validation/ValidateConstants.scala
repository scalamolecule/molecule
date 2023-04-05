package molecule.datomic.test.validation

import molecule.base.error._
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Validation._
import molecule.datomic.async._
import molecule.datomic.setup.DatomicTestSuite
import utest._
import scala.language.implicitConversions

object ValidateConstants extends DatomicTestSuite {

  // These test are mainly to confirm that formatting of validation tests
  // and messages in data definition file are transferred correctly to
  // boilerplate code and works as intended.

  override lazy val tests = Tests {

    "Default msg" - validation { implicit conn =>
      for {
        _ <- FormatConstants.noErrorMsg(1).save.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap, _) =>
            errorMap.head ==>
              "FormatConstants.noErrorMsg" -> Seq(
                // Default error message is used when no custom error message is defined
                """FormatConstants.noErrorMsg with value `1` doesn't satisfy validation:
                  |  _ > 2
                  |""".stripMargin
              )
        }

        // 1 has correctly not been saved
        _ <- FormatConstants.noErrorMsg.query.get.map(_ ==> Nil)

        // Valid value can be saved
        _ <- FormatConstants.noErrorMsg(3).save.transact
        _ <- FormatConstants.noErrorMsg.query.get.map(_ ==> List(3))
      } yield ()
    }


    "Msg" - validation { implicit conn =>
      for {
        _ <- FormatConstants.errorMsg(1).save.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap, _) =>
            errorMap.head ==>
              "FormatConstants.errorMsg" -> Seq(
                "One-line error msg"
              )
        }
      } yield ()
    }


    "Msg with value" - validation { implicit conn =>
      for {
        _ <- FormatConstants.errorMsgWithValue(1).save.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap, _) =>
            errorMap.head ==>
              "FormatConstants.errorMsgWithValue" -> Seq(
                "One-line error msg. Found 1"
              )
        }
      } yield ()
    }


    "Msg with quoted value" - validation { implicit conn =>
      for {
        _ <- FormatConstants.errorMsgWithValueQuoted("hi").save.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap, _) =>
            errorMap.head ==>
              "FormatConstants.errorMsgWithValueQuoted" -> Seq(
                """Expected hello. Found "hi"."""
              )
        }
      } yield ()
    }

    "Msg with quoted value 2" - validation { implicit conn =>
      for {
        _ <- FormatConstants.errorMsgWithValueQuoted2("hi").save.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap, _) =>
            errorMap.head ==>
              "FormatConstants.errorMsgWithValueQuoted2" -> Seq(
                """Expected hello. Found "hi"."""
              )
        }
      } yield ()
    }


    "Multi-line msg" - validation { implicit conn =>
      for {
        _ <- FormatConstants.multilineErrorMsg(1).save.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap, _) =>
            errorMap.head ==>
              "FormatConstants.multilineErrorMsg" -> Seq(
                """Long error explanation
                  |with multiple lines""".stripMargin
              )
        }
      } yield ()
    }


    "Multi-line msg with value" - validation { implicit conn =>
      for {
        _ <- FormatConstants.multilineMsgWithValue(1).save.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap, _) =>
            errorMap.head ==>
              "FormatConstants.multilineMsgWithValue" -> Seq(
                """Validation failed:
                  |Input value 1 is not bigger than 2.""".stripMargin
              )
        }
      } yield ()
    }

    "Multi-line msg with value 2" - validation { implicit conn =>
      for {
        _ <- FormatConstants.multilineMsgWithValue2(1).save.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap, _) =>
            errorMap.head ==>
              "FormatConstants.multilineMsgWithValue2" -> Seq(
                """Validation failed:
                  |Input value "1" is not bigger than 2.""".stripMargin
              )
        }
      } yield ()
    }


    "Multi-line test, default msg" - validation { implicit conn =>
      for {
        _ <- FormatConstants.multiLine(1).save.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap, _) =>
            errorMap.head ==>
              "FormatConstants.multiLine" -> Seq(
                """FormatConstants.multiLine with value `1` doesn't satisfy validation:
                  |  { v =>
                  |    val data   = 22
                  |    val result = data % 10
                  |    v > result
                  |  }
                  |""".stripMargin
              )
        }
      } yield ()
    }


    "Multi-line test, msg" - validation { implicit conn =>
      for {
        _ <- FormatConstants.multiLine2(1).save.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap, _) =>
            errorMap.head ==>
              "FormatConstants.multiLine2" -> Seq("One-line error msg")
        }
      } yield ()
    }


    "Multi-line test, multi-line msg" - validation { implicit conn =>
      for {
        _ <- FormatConstants.multiLine3(1).save.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap, _) =>
            errorMap.head ==>
              "FormatConstants.multiLine3" -> Seq(
                """Long error explanation
                  |with multiple lines""".stripMargin
              )
        }
      } yield ()
    }


    "Single test line with logic" - validation { implicit conn =>
      for {
        _ <- FormatConstants.logic(1).save.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap, _) =>
            errorMap.head ==>
              "FormatConstants.logic" -> Seq(
                "Value must be an odd number between 3 and 9 but not 7"
              )
        }
      } yield ()
    }


    "Multiple validations" - validation { implicit conn =>
      for {
        _ <- FormatConstants.multipleErrors(1).save.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap, _) =>
            errorMap.head ==>
              "FormatConstants.multipleErrors" -> Seq(
                "Test 1: Number must be bigger than 2. Found: 1"
              )
        }

        _ <- FormatConstants.multipleErrors(11).save.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap, _) =>
            errorMap.head ==>
              "FormatConstants.multipleErrors" -> Seq(
                "Test 2: Number must be smaller than 10. Found: 11"
              )
        }

        _ <- FormatConstants.multipleErrors(7).save.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap, _) =>
            errorMap.head ==>
              "FormatConstants.multipleErrors" -> Seq(
                "Test 3: Number must not be 7"
              )
        }

        _ <- FormatConstants.multipleErrors(4).save.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap, _) =>
            errorMap.head ==>
              "FormatConstants.multipleErrors" -> Seq(
                """Test 4: Number must
                  |be odd. Found: 4""".stripMargin
              )
        }

        _ <- FormatConstants.multipleErrors(0).save.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap, _) =>
            errorMap.head ==>
              "FormatConstants.multipleErrors" -> Seq(
                "Test 1: Number must be bigger than 2. Found: 0",
                """Test 4: Number must
                  |be odd. Found: 0""".stripMargin
              )
        }
      } yield ()
    }


    "Multiple validations, multiple values" - validation { implicit conn =>
      for {
        // Multiple values for an attribute cannot be stored with `save`.
        // But since validation of constants happen already on applying the values
        // (creating the molecule model), the errors will propagate first:

        // 0 and 11 are not valid
        _ <- FormatConstants.multipleErrors(0, 5, 11).save.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap, _) =>
            errorMap.head ==>
              "FormatConstants.multipleErrors" -> Seq(
                // 0
                "Test 1: Number must be bigger than 2. Found: 0",
                """Test 4: Number must
                  |be odd. Found: 0""".stripMargin,

                // 11
                "Test 2: Number must be smaller than 10. Found: 11"
              )
        }

        // If all values are valid, we'll get the runtime ExecutionError message for not being able to save multiple values.
        _ <- FormatConstants.multipleErrors(3, 5, 9).save.transact
          .map(_ ==> "Unexpected success").recover {
          case ExecutionError(error, _) =>
            error ==> "Can only save one value for attribute `FormatConstants.multipleErrors`. Found: 3, 5, 9"
        }
      } yield ()
    }


    "Multiple attribute validations" - validation { implicit conn =>
      for {
        _ <- FormatConstants.errorMsg(1).multipleErrors(0).save.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap, _) =>
            errorMap ==> Map(
              "FormatConstants.errorMsg" -> Seq(
                "One-line error msg"
              ),
              "FormatConstants.multipleErrors" -> Seq(
                "Test 1: Number must be bigger than 2. Found: 0",
                """Test 4: Number must
                  |be odd. Found: 0""".stripMargin
              )
            )

            // Errors by attribute name

            errorMap("FormatConstants.errorMsg") ==> Seq(
              "One-line error msg"
            )

            errorMap("FormatConstants.multipleErrors") ==> Seq(
              "Test 1: Number must be bigger than 2. Found: 0",
              """Test 4: Number must
                |be odd. Found: 0""".stripMargin
            )
        }
      } yield ()
    }


    // todo: move to insert test
    "3 rows, mixed errors with multi-errors" - validation { implicit conn =>
      for {
        _ <- FormatConstants.errorMsg.multipleErrors.insert(
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
                    0, // Composite tuple index
                    0, // tuple index
                    "FormatConstants.errorMsg",
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
                    0, // Composite tuple index
                    0, // tuple index
                    "FormatConstants.errorMsg",
                    Seq(
                      """One-line error msg"""
                    ),
                    Seq()
                  ),
                  InsertError(
                    0, // Composite tuple index
                    1, // tuple index
                    "FormatConstants.multipleErrors",

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
}
