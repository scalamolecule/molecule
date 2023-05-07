package molecule.datalog.datomic.test.validation.save

import molecule.base.error._
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Validation._
import molecule.datalog.datomic.async._
import molecule.datalog.datomic.setup.DatomicTestSuite
import utest._
import scala.language.implicitConversions

object Semantics extends DatomicTestSuite {

  override lazy val tests = Tests {

    "1 attribute" - validation { implicit conn =>
      for {
        _ <- Type.int(1).save.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap ==>
              Map(
                "Type.int" -> Seq(
                  s"""Type.int with value `1` doesn't satisfy validation:
                     |  _ > 2
                     |""".stripMargin
                )
              )
        }
      } yield ()
    }


    "2 attributes" - validation { implicit conn =>
      for {
        _ <- Type.int(1).long(3L).save.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap ==>
              Map(
                "Type.int" -> Seq(
                  s"""Type.int with value `1` doesn't satisfy validation:
                     |  _ > 2
                     |""".stripMargin
                )
              )
        }

        _ <- Type.int(3).long(1L).save.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap ==>
              Map(
                "Type.long" -> Seq(
                  s"""Type.long with value `1` doesn't satisfy validation:
                     |  _ > 2L
                     |""".stripMargin
                )
              )
        }

        _ <- Type.int(1).long(1L).save.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap ==>
              Map(
                "Type.int" -> Seq(
                  s"""Type.int with value `1` doesn't satisfy validation:
                     |  _ > 2
                     |""".stripMargin
                ),
                "Type.long" -> Seq(
                  s"""Type.long with value `1` doesn't satisfy validation:
                     |  _ > 2L
                     |""".stripMargin
                )
              )
        }
      } yield ()
    }
  }
}