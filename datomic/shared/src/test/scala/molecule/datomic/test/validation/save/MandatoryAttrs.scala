package molecule.datomic.test.validation.save

import molecule.base.error._
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Validation._
import molecule.datomic.async._
import molecule.datomic.setup.DatomicTestSuite
import utest._
import scala.language.implicitConversions

object MandatoryAttrs extends DatomicTestSuite {


  override lazy val tests = Tests {

    "Save" - validation { implicit conn =>
      for {
        _ <- Mandatory.age(42).save.transact
          .map(_ ==> "Unexpected success").recover {
          case ModelError(error) =>
            error ==>
              """Missing/empty mandatory attributes:
                |  Mandatory.name
                |  Mandatory.hobbies
                |""".stripMargin
        }

        _ <- Mandatory.name("Bob").age(42).save.transact
          .map(_ ==> "Unexpected success").recover {
          case ModelError(error) =>
            error ==>
              """Missing/empty mandatory attributes:
                |  Mandatory.hobbies
                |""".stripMargin
        }

        // Can't save empty Set for mandatory attribute
        _ <- Mandatory.name("Bob").age(42).hobbies(Set.empty[String]).save.transact
          .map(_ ==> "Unexpected success").recover {
          case ModelError(error) =>
            error ==>
              """Missing/empty mandatory attributes:
                |  Mandatory.hobbies
                |""".stripMargin
        }

        // All mandatory attributes of namespace `Mandatory` present and valid
        _ <- Mandatory.name("Bob").age(42).hobbies(Set("golf", "stamps")).save.transact
      } yield ()
    }
  }
}
