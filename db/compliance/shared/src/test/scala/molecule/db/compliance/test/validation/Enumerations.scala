package molecule.db.compliance.test.validation

import molecule.base.error.{InsertError, InsertErrors, ValidationErrors}
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Validation.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*


case class Enumerations(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

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
    import molecule.db.compliance.domains.dsl.Validation.*
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
