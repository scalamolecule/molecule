package molecule.datomic.test.validation

import molecule.base.error._
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Validation._
import molecule.datomic.async._
import molecule.datomic.setup.DatomicTestSuite
import utest._
import scala.language.implicitConversions

object ValidateVariables extends DatomicTestSuite {

  // As with ValidationFormatting, these test mainly check the formatting in
  // generated boilerplate code and error outputs. Here we substitute the
  // constants used earlier with attribute values in validation expressions.

  override lazy val tests = Tests {

    "Default msg" - validation { implicit conn =>
      for {
        _ <- FormatVariables.noErrorMsg(1).int(2).save.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap, _) =>
            errorMap.head ==>
              "FormatVariables.noErrorMsg" -> Seq(
                // Default error message is used when no custom error message is defined
                """FormatVariables.noErrorMsg with value `1` doesn't satisfy validation:
                  |  _ > int
                  |""".stripMargin
              )
        }

        // 1 has correctly not been saved
        _ <- FormatVariables.noErrorMsg.query.get.map(_ ==> Nil)

        // Valid value can be saved
        _ <- FormatVariables.noErrorMsg(3).int(2).save.transact
        _ <- FormatVariables.noErrorMsg.query.get.map(_ ==> List(3))
      } yield ()
    }


    "Msg" - validation { implicit conn =>
      for {
        _ <- FormatVariables.errorMsg(1).int1(2).save.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap, _) =>
            errorMap.head ==>
              "FormatVariables.errorMsg" -> Seq(
                "One-line error msg"
              )
        }
      } yield ()
    }


    "Msg with value" - validation { implicit conn =>
      for {
        _ <- FormatVariables.errorMsgWithValue(1).int2(2).save.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap, _) =>
            errorMap.head ==>
              "FormatVariables.errorMsgWithValue" -> Seq(
                "One-line error msg. Found 1"
              )
        }
      } yield ()
    }


    "Multi-line msg with value 2" - validation { implicit conn =>
      for {
        _ <- FormatVariables.multilineMsg(1).int3(2).save.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap, _) =>
            errorMap.head ==>
              "FormatVariables.multilineMsg" -> Seq(
                """Validation failed:
                  |Input value `1` is not bigger than `int3` value `2`.""".stripMargin
              )
        }
      } yield ()
    }


    "Multi-line test, default msg" - validation { implicit conn =>
      for {
        _ <- FormatVariables.multiLine(1).int4(10).save.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap, _) =>
            errorMap.head ==>
              "FormatVariables.multiLine" -> Seq(
                """FormatVariables.multiLine with value `1` doesn't satisfy validation:
                  |  { v =>
                  |    val data   = 22
                  |    val result = data % int4
                  |    v > result
                  |  }
                  |""".stripMargin
              )
        }
      } yield ()
    }


    "Multi-line test, msg" - validation { implicit conn =>
      for {
        _ <- FormatVariables.multiLine2(1).int5(10).save.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap, _) =>
            errorMap.head ==>
              "FormatVariables.multiLine2" -> Seq(
                "One-line error msg"
              )
        }
      } yield ()
    }


    "Multi-line test, multi-line msg" - validation { implicit conn =>
      for {
        _ <- FormatVariables.multiLine3(1).int6(10).save.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap, _) =>
            errorMap.head ==>
              "FormatVariables.multiLine3" -> Seq(
                """Long error explanation
                  |with multiple lines""".stripMargin
              )
        }
      } yield ()
    }


    "Single test line with logic" - validation { implicit conn =>
      for {
        _ <- FormatVariables.logic(1).int7(7).save.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap, _) =>
            errorMap.head ==>
              "FormatVariables.logic" -> Seq(
                "Value must be an odd number between 3 and 9 but not `int7` value `7`"
              )
        }
      } yield ()
    }


    "Multiple validations, missing value attrs" - validation { implicit conn =>
      for {
        // Valid values passing all 5 tests
        _ <- FormatVariables
          .multipleErrors(5)
          .int8(3)
          .str("hello")
          .ints(Set(10))
          .strs(Set("hi", "there")).save.transact

        // Missing value attributes needed for tests
        _ <- FormatVariables.multipleErrors(7).int8(3).str("hello").ints(Set(10)).save.transact
          .map(_ ==> "Unexpected success").recover {
          case ModelError(error) =>
            error ==>
              """Attribute `FormatVariables.multipleErrors` is missing some attributes needed for its validations:
                |  Needs  : int8, ints, str, strs
                |  Found  : int8, ints, str
                |  Missing: strs
                |""".stripMargin
        }

        _ <- FormatVariables.multipleErrors(7).int8(3).str("hello").save.transact
          .map(_ ==> "Unexpected success").recover {
          case ModelError(error) =>
            error ==>
              """Attribute `FormatVariables.multipleErrors` is missing some attributes needed for its validations:
                |  Needs  : int8, ints, str, strs
                |  Found  : int8, str
                |  Missing: ints, strs
                |""".stripMargin
        }

        _ <- FormatVariables.multipleErrors(7).int8(3).save.transact
          .map(_ ==> "Unexpected success").recover {
          case ModelError(error) =>
            error ==>
              """Attribute `FormatVariables.multipleErrors` is missing some attributes needed for its validations:
                |  Needs  : int8, ints, str, strs
                |  Found  : int8
                |  Missing: ints, str, strs
                |""".stripMargin
        }

        _ <- FormatVariables.multipleErrors(7).save.transact
          .map(_ ==> "Unexpected success").recover {
          case ModelError(error) =>
            error ==>
              """Attribute `FormatVariables.multipleErrors` is missing some attributes needed for its validations:
                |  Needs  : int8, ints, str, strs
                |  Found  :
                |  Missing: int8, ints, str, strs
                |""".stripMargin
        }
      } yield ()
    }


    "Multiple validations" - validation { implicit conn =>
      for {
        _ <- FormatVariables.multipleErrors(3).int8(3).str("hello").ints(Set(10)).strs(Set("hi", "there")).save.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap, _) =>
            errorMap.head ==>
              "FormatVariables.multipleErrors" -> Seq(
                "Test 1: Number must be bigger than 4. Found: 3",
                "Test 2: Number must be bigger than `int8` value `3`. Found: 3"
              )
        }

        _ <- FormatVariables.multipleErrors(11).int8(3).str("hello").ints(Set(10)).strs(Set("hi", "there")).save.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap, _) =>
            errorMap.head ==>
              "FormatVariables.multipleErrors" -> Seq(
                "Test 3: Number must be smaller than `str` value `hello` length `5` * 2. Found: 11"
              )
        }

        _ <- FormatVariables.multipleErrors(7).int8(3).str("hello").ints(Set(10)).strs(Set("hi", "there")).save.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap, _) =>
            errorMap.head ==>
              "FormatVariables.multipleErrors" -> Seq(
                "Test 4: Number must not be `ints` head value `10` minus 3. Found: 7"
              )
        }

        _ <- FormatVariables.multipleErrors(4).int8(3).str("hello").ints(Set(7)).strs(Set("hi", "there")).save.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap, _) =>
            errorMap.head ==>
              "FormatVariables.multipleErrors" -> Seq(
                "Test 1: Number must be bigger than 4. Found: 4",
                "Test 4: Number must not be `ints` head value `7` minus 3. Found: 4",
                """Test 5: Number must
                  |be odd. Found: 4""".stripMargin
              )
        }
      } yield ()
    }


    "Multiple values for save-validation" - validation { implicit conn =>
      for {
        _ <- FormatVariables.multipleErrors(0, 3, 11).int8(3).str("hello").ints(Set(10)).strs(Set("hi", "there")).save.transact
          .map(_ ==> "Unexpected success").recover {
          case ExecutionError(error, _) =>
            error ==> "Please use `insert` to store multiple values for attribute FormatVariables.multipleErrors."
        }
      } yield ()
    }
  }
}
