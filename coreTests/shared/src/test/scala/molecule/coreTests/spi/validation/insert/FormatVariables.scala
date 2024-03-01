package molecule.coreTests.spi.validation.insert

import molecule.base.error._
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Validation._
import molecule.coreTests.setup.CoreTestSuite
import utest._
import scala.language.implicitConversions

trait FormatVariables extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  // As with FormatConstants, these test mainly check the formatting in
  // generated boilerplate code and error outputs. Here we substitute the
  // constants used earlier with attribute values in validation expressions.

  override lazy val tests = Tests {

    "Default msg" - validation { implicit conn =>
      for {
        _ <- Variables.noErrorMsg.int.insert(1, 2).transact
          .map(_ ==> "Unexpected success").recover {
            case InsertErrors(errors, _) =>
              errors ==> Seq(
                (
                  0, // row index
                  Seq(
                    InsertError(
                      0, // tuple index
                      "Variables.noErrorMsg",
                      Seq(
                        // Default error message is used when no custom error message is defined
                        s"""Variables.noErrorMsg with value `1` doesn't satisfy validation:
                           |_ > int
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
        _ <- Variables.noErrorMsg.int.insert(1, 2).transact
          .map(_ ==> "Unexpected success").recover {
            case InsertErrors(errors, _) =>
              errors.head._2.head.errors ==> Seq(
                // Default error message is used when no custom error message is defined
                """Variables.noErrorMsg with value `1` doesn't satisfy validation:
                  |_ > int
                  |""".stripMargin
              )
          }

        // 1 has correctly not been saved
        _ <- Variables.noErrorMsg.query.get.map(_ ==> Nil)

        // Valid value can be saved
        _ <- Variables.noErrorMsg.int.insert(3, 2).transact
        _ <- Variables.noErrorMsg.query.get.map(_ ==> List(3))
      } yield ()
    }


    "Msg" - validation { implicit conn =>
      for {
        _ <- Variables.errorMsg.int1.insert(1, 2).transact
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
        _ <- Variables.errorMsgWithValue.int2.insert(1, 2).transact
          .map(_ ==> "Unexpected success").recover {
            case InsertErrors(errors, _) =>
              errors.head._2.head.errors ==> Seq(
                "One-line error msg. Found 1"
              )
          }
      } yield ()
    }


    "Multi-line msg with value 2" - validation { implicit conn =>
      for {
        _ <- Variables.multilineMsg.int3.insert(1, 2).transact
          .map(_ ==> "Unexpected success").recover {
            case InsertErrors(errors, _) =>
              errors.head._2.head.errors ==> Seq(
                """Validation failed:
                  |Input value `1` is not bigger than `int3` value `2`.""".stripMargin
              )
          }
      } yield ()
    }


    "Multi-line test, default msg" - validation { implicit conn =>
      for {
        _ <- Variables.multiLine.int4.insert(1, 10).transact
          .map(_ ==> "Unexpected success").recover {
            case InsertErrors(errors, _) =>
              errors.head._2.head.errors ==> Seq(
                """Variables.multiLine with value `1` doesn't satisfy validation:
                  |v =>
                  |  val data   = 22
                  |  val result = data % int4
                  |  v > result
                  |""".stripMargin
              )
          }
      } yield ()
    }


    "Multi-line test, msg" - validation { implicit conn =>
      for {
        _ <- Variables.multiLine2.int5.insert(1, 10).transact
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
        _ <- Variables.multiLine3.int6.insert(1, 10).transact
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
        _ <- Variables.logic.int7.insert(1, 7).transact
          .map(_ ==> "Unexpected success").recover {
            case InsertErrors(errors, _) =>
              errors.head._2.head.errors ==> Seq(
                "Value must be an odd number between 3 and 9 but not `int7` value `7`"
              )
          }
      } yield ()
    }


    "Multiple validations, missing value attrs" - validation { implicit conn =>
      for {
        // Valid values passing all 5 tests
        _ <- Variables.multipleErrors.int8.str.ints.strs.insert(5, 3, "hello", Set(10), Set("hi", "there")).transact

        // Missing value attributes needed for tests
        _ <- Variables.multipleErrors.int8.str.ints.insert(7, 3, "hello", Set(10)).transact
          .map(_ ==> "Unexpected success").recover {
            case ModelError(error) =>
              error ==>
                """Attribute `Variables.multipleErrors` is missing some attributes needed for its validations:
                  |  Needs  : int8, ints, str, strs
                  |  Found  : int8, ints, str
                  |  Missing: strs
                  |""".stripMargin
          }

        _ <- Variables.multipleErrors.int8.str.insert(7, 3, "hello").transact
          .map(_ ==> "Unexpected success").recover {
            case ModelError(error) =>
              error ==>
                """Attribute `Variables.multipleErrors` is missing some attributes needed for its validations:
                  |  Needs  : int8, ints, str, strs
                  |  Found  : int8, str
                  |  Missing: ints, strs
                  |""".stripMargin
          }

        _ <- Variables.multipleErrors.int8.insert(7, 3).transact
          .map(_ ==> "Unexpected success").recover {
            case ModelError(error) =>
              error ==>
                """Attribute `Variables.multipleErrors` is missing some attributes needed for its validations:
                  |  Needs  : int8, ints, str, strs
                  |  Found  : int8
                  |  Missing: ints, str, strs
                  |""".stripMargin
          }

        _ <- Variables.multipleErrors.insert(7).transact
          .map(_ ==> "Unexpected success").recover {
            case ModelError(error) =>
              error ==>
                """Attribute `Variables.multipleErrors` is missing some attributes needed for its validations:
                  |  Needs  : int8, ints, str, strs
                  |  Found  :
                  |  Missing: int8, ints, str, strs
                  |""".stripMargin
          }
      } yield ()
    }


    "Multiple validations" - validation { implicit conn =>
      for {
        _ <- Variables.multipleErrors.int8.str.ints.strs.insert(3, 3, "hello", Set(10), Set("hi", "there")).transact
          .map(_ ==> "Unexpected success").recover {
            case InsertErrors(errors, _) =>
              errors.head._2.head.errors ==> Seq(
                "Test 1: Number must be bigger than 4. Found: 3",
                "Test 2: Number must be bigger than `int8` value `3`. Found: 3"
              )
          }

        _ <- Variables.multipleErrors.int8.str.ints.strs.insert(11, 3, "hello", Set(10), Set("hi", "there")).transact
          .map(_ ==> "Unexpected success").recover {
            case InsertErrors(errors, _) =>
              errors.head._2.head.errors ==> Seq(
                "Test 3: Number must be smaller than `str` value `hello` length `5` * 2. Found: 11"
              )
          }

        _ <- Variables.multipleErrors.int8.str.ints.strs.insert(7, 3, "hello", Set(10), Set("hi", "there")).transact
          .map(_ ==> "Unexpected success").recover {
            case InsertErrors(errors, _) =>
              errors.head._2.head.errors ==> Seq(
                "Test 4: Number must not be `ints` head value `10` minus 3. Found: 7"
              )
          }

        _ <- Variables.multipleErrors.int8.str.ints.strs.insert(4, 3, "hello", Set(7), Set("hi", "there")).transact
          .map(_ ==> "Unexpected success").recover {
            case InsertErrors(errors, _) =>
              errors.head._2.head.errors ==> Seq(
                "Test 1: Number must be bigger than 4. Found: 4",
                "Test 4: Number must not be `ints` head value `7` minus 3. Found: 4",
                """Test 5: Number must
                  |be odd. Found: 4""".stripMargin
              )
          }
      } yield ()
    }
  }
}
