package molecule.datomic.test.validation.save

import molecule.base.error._
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Validation._
import molecule.datomic.async._
import molecule.datomic.setup.DatomicTestSuite
import utest._
import scala.language.implicitConversions

object Semantics extends DatomicTestSuite {

  override lazy val tests = Tests {

    "1 attribute" - validation { implicit conn =>
      for {
        _ <- Type.int(1).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap ==>
              Map(
                "Type.int" -> Seq(
                  s"""Type.int with value `1` doesn't satisfy validation:
                     |  _ > 1
                     |""".stripMargin
                )
              )
        }
      } yield ()
    }


    "2 attributes" - validation { implicit conn =>
      for {
        _ <- Type.int(1).long(3L).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap ==>
              Map(
                "Type.int" -> Seq(
                  s"""Type.int with value `1` doesn't satisfy validation:
                     |  _ > 1
                     |""".stripMargin
                )
              )
        }

        _ <- Type.int(3).long(1L).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap ==>
              Map(
                "Type.long" -> Seq(
                  s"""Type.long with value `1` doesn't satisfy validation:
                     |  _ > 1L
                     |""".stripMargin
                )
              )
        }

        _ <- Type.int(1).long(1L).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap ==>
              Map(
                "Type.int" -> Seq(
                  s"""Type.int with value `1` doesn't satisfy validation:
                     |  _ > 1
                     |""".stripMargin
                ),
                "Type.long" -> Seq(
                  s"""Type.long with value `1` doesn't satisfy validation:
                     |  _ > 1L
                     |""".stripMargin
                )
              )
        }
      } yield ()
    }


    "2 attributes, multi-error" - validation { implicit conn =>
      for {
        _ <- Format.errorMsg(1).multipleErrors(0).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap ==>
              Map(
                "Format.errorMsg" -> Seq(
                  "One-line error msg"
                ),

                "Format.multipleErrors" -> Seq(
                  "Number must be bigger than 2. Found: 0",
                  """Number must
                    |be odd. Found: 0""".stripMargin
                )
              )
        }
      } yield ()
    }
  }
}