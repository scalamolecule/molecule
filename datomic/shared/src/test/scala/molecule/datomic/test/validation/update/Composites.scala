package molecule.datomic.test.validation.update

import molecule.base.error.ValidationErrors
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Validation._
import molecule.datomic.async._
import molecule.datomic.setup.DatomicTestSuite
import utest._
import scala.language.implicitConversions

object Composites extends DatomicTestSuite {

  override lazy val tests = Tests {

    "1 + 1" - validation { implicit conn =>
      for {
        eid <- (Type.int(2) + Allowed.luckyNumber(7)).save.transact.map(_.eids.head)

        // Composite sub groups share the same entity id

        // bad, ok
        _ <- (Type(eid).int(1) + Allowed.luckyNumber(9)).update.transact.expect {
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

        // ok, bad
        _ <- (Type(eid).int(2) + Allowed.luckyNumber(0)).update.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap ==>
              Map(
                "Allowed.luckyNumber" -> Seq(
                  "Value `0` is not one of the allowed values in Seq(7, 9, 13)"
                )
              )
        }

        // bad, bad
        _ <- (Type(eid).int(1) + Allowed.luckyNumber(0)).update.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap ==>
              Map(
                "Type.int" -> Seq(
                  s"""Type.int with value `1` doesn't satisfy validation:
                     |  _ > 1
                     |""".stripMargin
                ),
                "Allowed.luckyNumber" -> Seq(
                  "Value `0` is not one of the allowed values in Seq(7, 9, 13)"
                )
              )
        }
      } yield ()
    }


    // Same validation mechanism for updating more complex composite models.
  }
}
