package molecule.coreTests.spi.validation.save

import molecule.base.error._
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.domains.dsl.Validation._
import molecule.coreTests.setup._
import scala.language.implicitConversions

case class FormatConstants(
  suite: MUnitSuite,
  api: Api_async with Spi_async with DbProviders
) extends TestUtils {

  // These test are mainly to confirm that formatting of validation tests
  // and messages in data definition file are transferred correctly to
  // boilerplate code and works as intended.

  import api._
  import suite._

  "Default msg" - validation { implicit conn =>
    for {
      _ <- Constants.noErrorMsg(1).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap ==>
              Map(
                "Constants.noErrorMsg" -> Seq(
                  // Default error message is used when no custom error message is defined
                  """Constants.noErrorMsg with value `1` doesn't satisfy validation:
                    |_ > 2
                    |""".stripMargin
                )
              )
        }

      // Same test
      // To shorten the following tests, we isolate the error messages
      _ <- Constants.noErrorMsg(1).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              // Default error message is used when no custom error message is defined
              """Constants.noErrorMsg with value `1` doesn't satisfy validation:
                |_ > 2
                |""".stripMargin
            )
        }

      // 1 has correctly not been saved
      _ <- Constants.noErrorMsg.query.get.map(_ ==> Nil)

      // Valid value can be saved
      _ <- Constants.noErrorMsg(3).save.transact
      _ <- Constants.noErrorMsg.query.get.map(_ ==> List(3))
    } yield ()
  }


  "Msg" - validation { implicit conn =>
    for {
      _ <- Constants.errorMsg(1).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              "One-line error msg"
            )
        }
    } yield ()
  }


  "Msg with value" - validation { implicit conn =>
    for {
      _ <- Constants.errorMsgWithValue(1).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              "One-line error msg. Found 1"
            )
        }
    } yield ()
  }


  "Msg with quoted value" - validation { implicit conn =>
    for {
      _ <- Constants.errorMsgWithValueQuoted("hi").save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              """Expected hello. Found "hi"."""
            )
        }
    } yield ()
  }

  "Msg with quoted value 2" - validation { implicit conn =>
    for {
      _ <- Constants.errorMsgWithValueQuoted2("hi").save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              """Expected hello. Found "hi"."""
            )
        }
    } yield ()
  }


  "Multi-line msg" - validation { implicit conn =>
    for {
      _ <- Constants.multilineErrorMsg(1).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              """Long error explanation
                |with multiple lines""".stripMargin
            )
        }
    } yield ()
  }


  "Multi-line msg with value" - validation { implicit conn =>
    for {
      _ <- Constants.multilineMsgWithValue(1).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              """Validation failed:
                |Input value 1 is not bigger than 2.""".stripMargin
            )
        }
    } yield ()
  }

  "Multi-line msg with value 2" - validation { implicit conn =>
    for {
      _ <- Constants.multilineMsgWithValue2(1).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              """Validation failed:
                |Input value "1" is not bigger than 2.""".stripMargin
            )
        }
    } yield ()
  }


  "Multi-line test, default msg" - validation { implicit conn =>
    for {
      _ <- Constants.multiLine(1).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
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
      _ <- Constants.multiLine2(1).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head ==>
              "Constants.multiLine2" -> Seq(
                "One-line error msg"
              )
        }
    } yield ()
  }


  "Multi-line test, multi-line msg" - validation { implicit conn =>
    for {
      _ <- Constants.multiLine3(1).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              """Long error explanation
                |with multiple lines""".stripMargin
            )
        }
    } yield ()
  }


  "Single test line with logic" - validation { implicit conn =>
    for {
      _ <- Constants.logic(1).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              "Value must be an odd number between 3 and 9 but not 7"
            )
        }
    } yield ()
  }


  "Multiple validations" - validation { implicit conn =>
    for {
      _ <- Constants.multipleErrors(1).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              "Test 1: Number must be bigger than 2. Found: 1"
            )
        }

      _ <- Constants.multipleErrors(11).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              "Test 2: Number must be smaller than 10. Found: 11"
            )
        }

      _ <- Constants.multipleErrors(7).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              "Test 3: Number must not be 7"
            )
        }

      _ <- Constants.multipleErrors(4).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head ==>
              "Constants.multipleErrors" -> Seq(
                """Test 4: Number must
                  |be odd. Found: 4""".stripMargin
              )
        }

      _ <- Constants.multipleErrors(0).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
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
      _ <- Constants.multipleErrors(0, 5, 11).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              // 0
              "Test 1: Number must be bigger than 2. Found: 0",
              """Test 4: Number must
                |be odd. Found: 0""".stripMargin,

              // 11
              "Test 2: Number must be smaller than 10. Found: 11"
            )
        }

      // If all values are valid, we'll get the runtime ExecutionError message for not being able to save multiple values.
      _ <- Constants.multipleErrors(3, 5, 9).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ExecutionError(err) =>
            err ==> "Can only save one value for attribute `Constants.multipleErrors`. Found: 3, 5, 9"
        }
    } yield ()
  }


  "Multiple attribute validations" - validation { implicit conn =>
    for {
      _ <- Constants.errorMsg(1).multipleErrors(0).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap ==> Map(
              "Constants.errorMsg" -> Seq(
                "One-line error msg"
              ),
              "Constants.multipleErrors" -> Seq(
                "Test 1: Number must be bigger than 2. Found: 0",
                """Test 4: Number must
                  |be odd. Found: 0""".stripMargin
              )
            )

            // Errors by attribute name

            errorMap("Constants.errorMsg") ==> Seq(
              "One-line error msg"
            )

            errorMap("Constants.multipleErrors") ==> Seq(
              "Test 1: Number must be bigger than 2. Found: 0",
              """Test 4: Number must
                |be odd. Found: 0""".stripMargin
            )
        }
    } yield ()
  }
}
