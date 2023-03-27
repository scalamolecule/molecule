package molecule.datomic.test.validation.insert

import molecule.base.error._
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Validation._
import molecule.datomic.async._
import molecule.datomic.setup.DatomicTestSuite
import utest._
import scala.language.implicitConversions

object MandatoryAttrs extends DatomicTestSuite {


  override lazy val tests = Tests {

    "Insert" - validation { implicit conn =>
      for {
        // Modelling errors:

        _ <- Mandatory.age.insert(42).transact
          .map(_ ==> "Unexpected success").recover {
          case ModelError(error) =>
            error ==>
              """Missing/empty mandatory attributes:
                |  Mandatory.name
                |  Mandatory.hobbies
                |""".stripMargin
        }

        _ <- Mandatory.name.age.insert("Bob", 42).transact
          .map(_ ==> "Unexpected success").recover {
          case ModelError(error) =>
            error ==>
              """Missing/empty mandatory attributes:
                |  Mandatory.hobbies
                |""".stripMargin
        }

        _ <- Mandatory.name.age.insert("Bob", 42).transact
          .map(_ ==> "Unexpected success").recover {
          case ModelError(error) =>
            error ==>
              """Missing/empty mandatory attributes:
                |  Mandatory.hobbies
                |""".stripMargin
        }

        // Runtime data error:

        // Can't save empty Set for mandatory attribute
        _ <- Mandatory.name.age.hobbies.insert("Bob", 42, Set.empty[String]).transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(indexedInsertErrors, _) =>
            indexedInsertErrors ==> Seq(
              (
                0, // Top-level row index
                Seq(
                  InsertError(
                    0, // Composite tuple index
                    2, // tuple index
                    "Mandatory.hobbies",
                    Seq(
                      """Can't insert empty Set for mandatory attribute"""
                    ),
                    Seq()
                  )
                )
              )
            )
        }

        // All mandatory attributes of namespace `Mandatory` present
        _ <- Mandatory.name.age.hobbies.insert("Bob", 42, Set("golf", "stamps")).transact
      } yield ()
    }
  }
}
