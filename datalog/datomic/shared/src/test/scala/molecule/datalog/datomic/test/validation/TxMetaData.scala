package molecule.datalog.datomic.test.validation

import molecule.base.error._
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Validation._
import molecule.datalog.datomic.async._
import molecule.datalog.datomic.setup.DatomicTestSuite
import utest._
import scala.language.implicitConversions

object TxMetaData extends DatomicTestSuite {

  override lazy val tests = Tests {

    "Save" - validation { implicit conn =>
      for {
        // Main data invalid
        _ <- Type.int(0).Tx(Enum.luckyNumber_(7)).save.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap ==>
              Map(
                "Type.int" -> Seq(
                  s"""Type.int with value `0` doesn't satisfy validation:
                     |  _ > 2
                     |""".stripMargin
                )
              )
        }

        // Tx meta data invalid
        _ <- Type.int(3).Tx(Enum.luckyNumber_(0)).save.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap ==>
              Map(
                "Enum.luckyNumber" -> Seq(
                  "Value `0` is not one of the allowed values in Seq(7, 9, 13)"
                )
              )
        }

        // Main data and tx meta data invalid
        _ <- Type.int(0).Tx(Enum.luckyNumber_(0)).save.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap ==>
              Map(
                "Type.int" -> Seq(
                  s"""Type.int with value `0` doesn't satisfy validation:
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


    "Insert" - validation { implicit conn =>
      for {
        // Main data invalid
        _ <- Type.int.Tx(Enum.luckyNumber_(7)).insert(
          10,
          -1
        ).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(indexedInsertErrors, _) =>
            indexedInsertErrors ==> Seq(
              (
                1, // Top-level row index
                Seq(
                  InsertError(
                    0, // Outer tuple
                    0, // tuple index
                    "Type.int",
                    Seq(
                      s"""Type.int with value `-1` doesn't satisfy validation:
                         |  _ > 2
                         |""".stripMargin
                    ),
                    Seq()
                  )
                )
              )
            )
        }

        // Tx meta data invalid
        _ <- Type.int.Tx(Enum.luckyNumber_(0)).insert(
          10,
          11
        ).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(indexedInsertErrors, _) =>
            indexedInsertErrors ==> Seq(
              (
                -1, // Appended tx meta data errors row
                Seq(
                  InsertError(
                    0, // Composite tuple index
                    0, // tuple index
                    "Enum.luckyNumber",
                    Seq(
                      """Value `0` is not one of the allowed values in Seq(7, 9, 13)"""
                    ),
                    Seq()
                  )
                )
              )
            )
        }


        // Main data and tx meta data invalid
        _ <- Type.int.Tx(Enum.luckyNumber_(0)).insert(
          10,
          -1
        ).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(indexedInsertErrors, _) =>
            indexedInsertErrors ==> Seq(
              (
                1, // Top-level row index
                Seq(
                  InsertError(
                    0, // Composite tuple index
                    0, // tuple index
                    "Type.int",
                    Seq(
                      s"""Type.int with value `-1` doesn't satisfy validation:
                         |  _ > 2
                         |""".stripMargin
                    ),
                    Seq()
                  )
                )
              ),
              (
                -1, // Appended tx meta data errors row
                Seq(
                  InsertError(
                    0, // Composite tuple index
                    0, // tuple index
                    "Enum.luckyNumber",
                    Seq(
                      """Value `0` is not one of the allowed values in Seq(7, 9, 13)"""
                    ),
                    Seq()
                  )
                )
              )
            )
        }
      } yield ()
    }


    "Update" - validation { implicit conn =>
      for {
        // Save valid tx meta data and get transaction entity id
        tx <- Type.int(3).Tx(Enum.luckyNumber_(7)).save.transact.map(_.tx)

        // Update transaction meta data with invalid value
        // (like updating any other values)
        _ <- Enum(tx).luckyNumber(2).update.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap ==>
              Map(
                "Enum.luckyNumber" -> Seq(
                  "Value `2` is not one of the allowed values in Seq(7, 9, 13)"
                )
              )
        }
      } yield ()
    }
  }
}
