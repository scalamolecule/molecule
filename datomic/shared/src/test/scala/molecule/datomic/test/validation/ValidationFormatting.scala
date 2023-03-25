package molecule.datomic.test.validation

import molecule.base.error.ValidationErrors
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Validation._
import molecule.datomic.async._
import molecule.datomic.setup.DatomicTestSuite
import utest._
import scala.language.implicitConversions

object ValidationFormatting extends DatomicTestSuite {

  // These test are mainly to confirm that formatting of validation tests
  // and messages in data definition file are transferred correctly to
  // boilerplate code and works as intended.

  override lazy val tests = Tests {

    "Test, default msg" - validation { implicit conn =>
      for {
        _ <- Format.noErrorMsg(1).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap.head ==>
              "Format.noErrorMsg" -> Seq(
                // Default error message is used when no custom error message is defined
                """Format.noErrorMsg with value `1` doesn't satisfy validation:
                  |  _ > 2
                  |""".stripMargin
              )
        }
        
        // 1 has correctly not been saved
        _ <- Format.noErrorMsg.query.get.map(_ ==> Nil)

        // Valid value can be saved
        _ <- Format.noErrorMsg(3).save.transact
        _ <- Format.noErrorMsg.query.get.map(_ ==> List(3))
      } yield ()
    }


    "Test, msg" - validation { implicit conn =>
      for {
        _ <- Format.errorMsg(1).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap.head ==>
              "Format.errorMsg" -> Seq("One-line error msg")
        }
      } yield ()
    }


    "Test, msg with value" - validation { implicit conn =>
      for {
        _ <- Format.errorMsgWithValue(1).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap.head ==>
              "Format.errorMsgWithValue" -> Seq("One-line error msg. Found 1")
        }
      } yield ()
    }


    "Test, msg with quoted value" - validation { implicit conn =>
      for {
        _ <- Format.errorMsgWithValueQuoted("hi").save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap.head ==>
              "Format.errorMsgWithValueQuoted" -> Seq("""Expected hello. Found "hi".""")
        }
      } yield ()
    }

    "Test, msg with quoted value 2" - validation { implicit conn =>
      for {
        _ <- Format.errorMsgWithValueQuoted2("hi").save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap.head ==>
              "Format.errorMsgWithValueQuoted2" -> Seq("""Expected hello. Found "hi".""")
        }
      } yield ()
    }


    "Test, multi-line msg" - validation { implicit conn =>
      for {
        _ <- Format.multilineErrorMsg(1).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap.head ==>
              "Format.multilineErrorMsg" -> Seq(
                """Long error explanation
                  |with multiple lines""".stripMargin
              )
        }
      } yield ()
    }


    "Test, multi-line msg with value" - validation { implicit conn =>
      for {
        _ <- Format.multilineMsgWithValue(1).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap.head ==>
              "Format.multilineMsgWithValue" -> Seq(
                """Validation failed:
                  |Input value 1 is not bigger than 2.""".stripMargin
              )
        }
      } yield ()
    }

    "Test, multi-line msg with value 2" - validation { implicit conn =>
      for {
        _ <- Format.multilineMsgWithValue2(1).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap.head ==>
              "Format.multilineMsgWithValue2" -> Seq(
                """Validation failed:
                  |Input value "1" is not bigger than 2.""".stripMargin
              )
        }
      } yield ()
    }


    "Multi-line test, default msg" - validation { implicit conn =>
      for {
        _ <- Format.multiLine(1).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap.head ==>
              "Format.multiLine" -> Seq(
                """Format.multiLine with value `1` doesn't satisfy validation:
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
        _ <- Format.multiLine2(1).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap.head ==>
              "Format.multiLine2" -> Seq("One-line error msg")
        }
      } yield ()
    }


    "Multi-line test, multi-line msg" - validation { implicit conn =>
      for {
        _ <- Format.multiLine3(1).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap.head ==>
              "Format.multiLine3" -> Seq(
                """Long error explanation
                  |with multiple lines""".stripMargin
              )
        }
      } yield ()
    }


    "Single test line with logic" - validation { implicit conn =>
      for {
        _ <- Format.logic(1).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap.head ==>
              "Format.logic" -> Seq("Value must be an odd number between 3 and 9 but not 7")
        }
      } yield ()
    }


    "Multiple validations" - validation { implicit conn =>
      for {
        // Fail validation 1
        _ <- Format.multipleErrors(1).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap.head ==>
              "Format.multipleErrors" -> Seq("Number must be bigger than 2. Found: 1")
        }
        // Fail validation 2
        _ <- Format.multipleErrors(11).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap.head ==>
              "Format.multipleErrors" -> Seq("Number must be smaller than 10. Found: 11")
        }
        // Fail validation 3
        _ <- Format.multipleErrors(7).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap.head ==>
              "Format.multipleErrors" -> Seq("Number must not be count of allowed numbers. Found: 7")
        }
        // Fail validation 4
        _ <- Format.multipleErrors(4).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap.head ==>
              "Format.multipleErrors" -> Seq(
                """Number must
                  |be odd. Found: 4""".stripMargin
              )
        }

        // Multiple errorMap at once - fail validation 1 + 4
        _ <- Format.multipleErrors(0).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap.head ==>
              "Format.multipleErrors" -> Seq(
                "Number must be bigger than 2. Found: 0",
                """Number must
                  |be odd. Found: 0""".stripMargin
              )
        }
      } yield ()
    }

    "Multiple validations, multiple values" - validation { implicit conn =>
      for {
        // Only 5 is ok
        _ <- Format.multipleErrors(0, 5, 11).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap.head ==>
              "Format.multipleErrors" -> Seq(
                // 0
                "Number must be bigger than 2. Found: 0",
                """Number must
                  |be odd. Found: 0""".stripMargin,

                // 11
                "Number must be smaller than 10. Found: 11"
              )
        }
      } yield ()
    }


    "Multiple attribute validations" - validation { implicit conn =>
      for {
        _ <- Format.errorMsg(1).multipleErrors(0).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            // All errorMap in Map with attribute name keys
            errorMap ==> Map(
              "Format.errorMsg" -> Seq(
                "One-line error msg"
              ),
              "Format.multipleErrors" -> Seq(
                "Number must be bigger than 2. Found: 0",
                """Number must
                  |be odd. Found: 0""".stripMargin
              )
            )

            // Errors by attribute name

            errorMap("Format.errorMsg") ==> Seq(
              "One-line error msg"
            )

            errorMap("Format.multipleErrors") ==> Seq(
              "Number must be bigger than 2. Found: 0",
              """Number must
                |be odd. Found: 0""".stripMargin
            )
        }
      } yield ()
    }
  }
}
