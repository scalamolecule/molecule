package molecule.datomic.test.validation.update

import molecule.base.error._
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Validation._
import molecule.datomic.async._
import molecule.datomic.setup.DatomicTestSuite
import utest._
import scala.language.implicitConversions

object MandatoryAttrs extends DatomicTestSuite {


  override lazy val tests = Tests {

    "Update" - validation { implicit conn =>
      for {
        eid <- Mandatory.name("Bob").age(42).hobbies(Set("golf", "stamps")).save.transact.map(_.eids.head)

        _ <- Mandatory(eid).name().update.transact
          .map(_ ==> "Unexpected success").recover {
          case ModelError(error) =>
            error ==>
              """Can't delete mandatory attributes (or remove last values of card-many attributes):
                |  Mandatory.name
                |""".stripMargin
        }

        _ <- Mandatory(eid).hobbies().update.transact
          .map(_ ==> "Unexpected success").recover {
          case ModelError(error) =>
            error ==>
              """Can't delete mandatory attributes (or remove last values of card-many attributes):
                |  Mandatory.hobbies
                |""".stripMargin
        }

        _ <- Mandatory(eid).name().hobbies().update.transact
          .map(_ ==> "Unexpected success").recover {
          case ModelError(error) =>
            error ==>
              """Can't delete mandatory attributes (or remove last values of card-many attributes):
                |  Mandatory.name
                |  Mandatory.hobbies
                |""".stripMargin
        }

        // We can remove a value from a Set as long as it's not the last value
        _ <- Mandatory(eid).hobbies.remove("stamps").update.transact

        // Can't remove the last value of a mandatory attribute Set of values
        _ <- Mandatory(eid).hobbies.remove("golf").update.transact
          .map(_ ==> "Unexpected success").recover {
          case ModelError(error) =>
            error ==>
              """Can't delete mandatory attributes (or remove last values of card-many attributes):
                |  Mandatory.hobbies
                |""".stripMargin
        }
      } yield ()
    }
  }
}
