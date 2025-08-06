package molecule.db.compliance.test.validation

import molecule.core.error.{InsertError, InsertErrors, ValidationErrors}
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Validation.*
import molecule.db.compliance.setup.DbProviders


case class AllowedValues(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "Allowed values, default error" - validation {
    for {
      _ <- AllowedAttrs.luckyNumber(8).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head ==> "AllowedAttrs.luckyNumber" -> Seq(
              "Value `8` is not one of the allowed values in Seq(7, 9, 13)"
            )
        }
      // Same with insert
      _ <- AllowedAttrs.luckyNumber.insert(8).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(Seq((_, Seq(InsertError(_, _, Seq(error), _)))), _) =>
            error ==> "Value `8` is not one of the allowed values in Seq(7, 9, 13)"
        }

      _ <- AllowedAttrs.luckyNumber(7, 8).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head ==> "AllowedAttrs.luckyNumber" -> Seq(
              "Value `8` is not one of the allowed values in Seq(7, 9, 13)"
            )
        }
      _ <- AllowedAttrs.luckyNumber(7).save.transact // ok
    } yield ()
  }

  "Allowed values, custom error" - validation {
    import molecule.db.compliance.domains.dsl.Validation.*
    for {
      _ <- AllowedAttrs.luckyNumber2(5).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head ==> "AllowedAttrs.luckyNumber2" -> Seq(
              "Lucky number can only be 7, 9 or 13"
            )
        }
      _ <- AllowedAttrs.luckyNumber2(7).save.transact
    } yield ()
  }
}
