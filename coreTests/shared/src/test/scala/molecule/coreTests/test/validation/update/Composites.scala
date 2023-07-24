package molecule.coreTests.test.validation.update

import molecule.base.error.ValidationErrors
import molecule.coreTests.api.ApiAsyncImplicits
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Validation._
import molecule.coreTests.async._
import molecule.coreTests.setup.CoreTestSuite
import molecule.core.spi.SpiAsync
import utest._
import scala.language.implicitConversions

trait Composites extends CoreTestSuite with ApiAsyncImplicits { self: SpiAsync  =>

  override lazy val tests = Tests {

    "1 + 1" - validation { implicit conn =>
      for {
        id <- (Type.int(3) + Enum.luckyNumber(7)).save.transact.map(_.id)

        // Composite sub groups share the same entity id

        // bad, ok
        _ <- (Type(id).int(1) + Enum.luckyNumber(9)).update.transact
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

        // ok, bad
        _ <- (Type(id).int(3) + Enum.luckyNumber(0)).update.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap ==>
              Map(
                "Enum.luckyNumber" -> Seq(
                  "Value `0` is not one of the allowed values in Seq(7, 9, 13)"
                )
              )
        }

        // bad, bad
        _ <- (Type(id).int(1) + Enum.luckyNumber(0)).update.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap ==>
              Map(
                "Type.int" -> Seq(
                  s"""Type.int with value `1` doesn't satisfy validation:
                     |  _ > 2
                     |""".stripMargin
                ),
                "Enum.luckyNumber" -> Seq(
                  "Value `0` is not one of the allowed values in Seq(7, 9, 13)"
                )
              )
        }
      } yield ()
    }


    // Same validation mechanism for updating more complex composite models.
  }
}
