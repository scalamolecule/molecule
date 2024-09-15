package molecule.coreTests.spi.validation

import molecule.base.error.{InsertError, InsertErrors, ValidationErrors}
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.dsl.Validation._
import molecule.coreTests.setup.CoreTestSuite
import utest._
import scala.language.implicitConversions

trait Enumerations extends CoreTestSuite with ApiAsync { spi: SpiAsync =>


  override lazy val tests = Tests {

    "Enum, default msg" - validation { implicit conn =>
      for {
        _ <- Enum.luckyNumber(8).save.transact
          .map(_ ==> "Unexpected success").recover {
            case ValidationErrors(errorMap) =>
              errorMap.head ==> "Enum.luckyNumber" -> Seq(
                "Value `8` is not one of the allowed values in Seq(7, 9, 13)"
              )
          }
        // Same with insert
        _ <- Enum.luckyNumber.insert(8).transact
          .map(_ ==> "Unexpected success").recover {
            case InsertErrors(Seq((_, Seq(InsertError(_, _, Seq(error), _)))), _) =>
              error ==> "Value `8` is not one of the allowed values in Seq(7, 9, 13)"
          }

        _ <- Enum.luckyNumber(7, 8).save.transact
          .map(_ ==> "Unexpected success").recover {
            case ValidationErrors(errorMap) =>
              errorMap.head ==> "Enum.luckyNumber" -> Seq(
                "Value `8` is not one of the allowed values in Seq(7, 9, 13)"
              )
          }
        _ <- Enum.luckyNumber(7).save.transact // ok
      } yield ()
    }

    "Enum, msg" - validation { implicit conn =>
      for {
        _ <- Enum.luckyNumber2(5).save.transact
          .map(_ ==> "Unexpected success").recover {
            case ValidationErrors(errorMap) =>
              errorMap.head ==> "Enum.luckyNumber2" -> Seq(
                "Lucky number can only be 7, 9 or 13"
              )
          }
        _ <- Enum.luckyNumber2(7).save.transact
      } yield ()
    }
  }
}
