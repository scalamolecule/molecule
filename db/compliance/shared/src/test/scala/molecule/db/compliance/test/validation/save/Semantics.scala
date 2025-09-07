package molecule.db.compliance.test.validation.save

import molecule.core.error.ValidationErrors
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Validation.*
import molecule.db.compliance.setup.DbProviders


case class Semantics(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "1 attribute" - validation {
    for {
      _ <- Tpe.int(1).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap ==>
              Map(
                "Tpe.int" -> Seq(
                  s"""Tpe.int with value `1` doesn't satisfy validation:
                     |_ > 2
                     |""".stripMargin
                )
              )
        }
    } yield ()
  }


  "2 attributes" - validation {
    for {
      _ <- Tpe.int(1).long(3L).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap ==>
              Map(
                "Tpe.int" -> Seq(
                  s"""Tpe.int with value `1` doesn't satisfy validation:
                     |_ > 2
                     |""".stripMargin
                )
              )
        }

      _ <- Tpe.int(3).long(1L).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap ==>
              Map(
                "Tpe.long" -> Seq(
                  s"""Tpe.long with value `1` doesn't satisfy validation:
                     |_ > 2L
                     |""".stripMargin
                )
              )
        }

      _ <- Tpe.int(1).long(1L).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap ==>
              Map(
                "Tpe.int" -> Seq(
                  s"""Tpe.int with value `1` doesn't satisfy validation:
                     |_ > 2
                     |""".stripMargin
                ),
                "Tpe.long" -> Seq(
                  s"""Tpe.long with value `1` doesn't satisfy validation:
                     |_ > 2L
                     |""".stripMargin
                )
              )
        }
    } yield ()
  }
}
