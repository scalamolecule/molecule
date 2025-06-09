package molecule.db.compliance.test.validation.save

import molecule.base.error.{ExecutionError, ModelError, ValidationErrors}
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Validation.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*

case class FormatVariables(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  // As with FormatConstants, these test mainly check the formatting in
  // generated boilerplate code and error outputs. Here we substitute the
  // constants used earlier with attribute values in validation expressions.

  import api.*
  import suite.*

  "Default msg" - validation { implicit conn =>
    for {
      _ <- Variables.noErrorMsg(1).int(2).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap ==>
              Map(
                "Variables.noErrorMsg" -> Seq(
                  // Default error message is used when no custom error message is defined
                  """Variables.noErrorMsg with value `1` doesn't satisfy validation:
                    |_ > int
                    |""".stripMargin
                )
              )
        }

      // Same test
      // To shorten the following tests, we isolate the error messages
      _ <- Variables.noErrorMsg(1).int(2).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              // Default error message is used when no custom error message is defined
              """Variables.noErrorMsg with value `1` doesn't satisfy validation:
                |_ > int
                |""".stripMargin
            )
        }

      // 1 has correctly not been saved
      _ <- Variables.noErrorMsg.query.get.map(_ ==> Nil)

      // Valid value can be saved
      _ <- Variables.noErrorMsg(3).int(2).save.transact
      _ <- Variables.noErrorMsg.query.get.map(_ ==> List(3))
    } yield ()
  }


  "Msg" - validation { implicit conn =>
    for {
      _ <- Variables.errorMsg(1).int1(2).save.transact
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
      _ <- Variables.errorMsgWithValue(1).int2(2).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              "One-line error msg. Found 1"
            )
        }
    } yield ()
  }


  "Multi-line msg with value 2" - validation { implicit conn =>
    for {
      _ <- Variables.multilineMsg(1).int3(2).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              """Validation failed:
                |Input value `1` is not bigger than `int3` value `2`.""".stripMargin
            )
        }
    } yield ()
  }


  "Multi-line test, default msg" - validation { implicit conn =>
    for {
      _ <- Variables.multiLine(1).int4(10).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
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
      _ <- Variables.multiLine2(1).int5(10).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              "One-line error msg"
            )
        }
    } yield ()
  }


  "Multi-line test, multi-line msg" - validation { implicit conn =>
    for {
      _ <- Variables.multiLine3(1).int6(10).save.transact
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
      _ <- Variables.logic(1).int7(7).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              "Value must be an odd number between 3 and 9 but not `int7` value `7`"
            )
        }
    } yield ()
  }


  "Validation attributes only mandatory" - validation { implicit conn =>
    for {
      _ <- Variables.errorMsg_(1).int1(2).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ModelError(error) =>
            error ==> "Required attributes have to be mandatory. Found tacit attribute Variables.errorMsg"
        }
      _ <- Variables.errorMsg_?(Some(1)).int1(2).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ModelError(error) =>
            error ==> "Required attributes have to be mandatory. Found optional attribute Variables.errorMsg"
        }

      _ <- Variables.errorMsg(1).int1_(2).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ModelError(error) =>
            error ==> "Required attributes have to be mandatory. Found tacit attribute Variables.int1"
        }
      _ <- Variables.errorMsg(1).int1_?(Some(2)).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ModelError(error) =>
            error ==> "Required attributes have to be mandatory. Found optional attribute Variables.int1"
        }

      // Check Set cardinality attribute intSet
      _ <- Variables.multipleErrors(1).int8(3).str("hello").intSet_(Set(10)).strs(Set("hi", "there")).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ModelError(error) =>
            error ==> "Required attributes have to be mandatory. Found tacit attribute Variables.intSet"
        }
      _ <- Variables.multipleErrors(1).int8(3).str("hello").intSet_?(Some(Set(10))).strs(Set("hi", "there")).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ModelError(error) =>
            error ==> "Required attributes have to be mandatory. Found optional attribute Variables.intSet"
        }
    } yield ()
  }


  "Multiple validations, missing value attrs" - validation { implicit conn =>
    for {
      // Valid values passing all 5 tests
      _ <- Variables
        .multipleErrors(5)
        .int8(3)
        .str("hello")
        .intSet(Set(10))
        .strs(Set("hi", "there")).save.transact

      // Missing value attributes needed for tests
      _ <- Variables.multipleErrors(7).int8(3).str("hello").intSet(Set(10)).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ModelError(error) =>
            error ==>
              """Attribute `Variables.multipleErrors` is missing some attributes needed for its validations:
                |  Needs  : int8, intSet, str, strs
                |  Found  : int8, intSet, str
                |  Missing: strs
                |""".stripMargin
        }

      _ <- Variables.multipleErrors(7).int8(3).str("hello").save.transact
        .map(_ ==> "Unexpected success").recover {
          case ModelError(error) =>
            error ==>
              """Attribute `Variables.multipleErrors` is missing some attributes needed for its validations:
                |  Needs  : int8, intSet, str, strs
                |  Found  : int8, str
                |  Missing: intSet, strs
                |""".stripMargin
        }

      _ <- Variables.multipleErrors(7).int8(3).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ModelError(error) =>
            error ==>
              """Attribute `Variables.multipleErrors` is missing some attributes needed for its validations:
                |  Needs  : int8, intSet, str, strs
                |  Found  : int8
                |  Missing: intSet, str, strs
                |""".stripMargin
        }

      _ <- Variables.multipleErrors(7).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ModelError(error) =>
            error ==>
              """Attribute `Variables.multipleErrors` is missing some attributes needed for its validations:
                |  Needs  : int8, intSet, str, strs
                |  Found  :
                |  Missing: int8, intSet, str, strs
                |""".stripMargin
        }
    } yield ()
  }


  "Value attributes are tied to validation attributes using them" - validation { implicit conn =>
    for {
      // multipleErrors requires int8, intSet, strs and str for its validation
      // Like a quintuple of coherent data
      _ <- Variables.int8(3).str("hello").save.transact
        .map(_ ==> "Unexpected success").recover {
          case ModelError(error) =>
            error ==>
              """Missing/empty required attributes:
                |  Required: int8, intSet, strs, str, multipleErrors
                |  Present : int8, str
                |  Missing : intSet, strs, multipleErrors
                |""".stripMargin
        }
    } yield ()
  }


  "Multiple validations" - validation { implicit conn =>
    for {
      _ <- Variables.multipleErrors(3).int8(3).str("hello").intSet(Set(10)).strs(Set("hi", "there")).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              "Test 1: Number must be bigger than 4. Found: 3",
              "Test 2: Number must be bigger than `int8` value `3`. Found: 3"
            )
        }

      _ <- Variables.multipleErrors(11).int8(3).str("hello").intSet(Set(10)).strs(Set("hi", "there")).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              "Test 3: Number must be smaller than `str` value `hello` length `5` * 2. Found: 11"
            )
        }

      _ <- Variables.multipleErrors(7).int8(3).str("hello").intSet(Set(10)).strs(Set("hi", "there")).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              "Test 4: Number must not be `intSet` head value `10` minus 3. Found: 7"
            )
        }

      _ <- Variables.multipleErrors(4).int8(3).str("hello").intSet(Set(7)).strs(Set("hi", "there")).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2 ==> Seq(
              "Test 1: Number must be bigger than 4. Found: 4",
              "Test 4: Number must not be `intSet` head value `7` minus 3. Found: 4",
              """Test 5: Number must
                |be odd. Found: 4""".stripMargin
            )
        }
    } yield ()
  }


  "Multiple values for save-validation" - validation { implicit conn =>
    for {
      _ <- Variables.multipleErrors(0, 3, 11).int8(3).str("hello").intSet(Set(10)).strs(Set("hi", "there")).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ExecutionError(err) =>
            err ==> "Please use `insert` to store multiple values for attribute Variables.multipleErrors"
        }
    } yield ()
  }
}
