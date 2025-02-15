package molecule.coreTests.spi.validation

import molecule.base.error._
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.domains.dsl.Validation._
import molecule.coreTests.setup._
import scala.language.implicitConversions

case class MandatoryAttrs(
  suite: Test,
  api: Api_async with Spi_async with DbProviders
) extends TestUtils {

  import api._
  import suite._

  "Missing attributes" - validation { implicit conn =>
    for {
      _ <- MandatoryAttr.age(42).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ModelError(error) =>
            error ==>
              """Missing/empty mandatory attributes:
                |  MandatoryAttr.name
                |  MandatoryAttr.hobbies
                |""".stripMargin
        }
      // Same error for inserts
      _ <- MandatoryAttr.age.insert(42).transact
        .map(_ ==> "Unexpected success").recover {
          case ModelError(error) =>
            error ==>
              """Missing/empty mandatory attributes:
                |  MandatoryAttr.name
                |  MandatoryAttr.hobbies
                |""".stripMargin
        }

      _ <- MandatoryAttr.name("Bob").age(42).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ModelError(error) =>
            error ==>
              """Missing/empty mandatory attributes:
                |  MandatoryAttr.hobbies
                |""".stripMargin
        }
      _ <- MandatoryAttr.name.age.insert("Bob", 42).transact
        .map(_ ==> "Unexpected success").recover {
          case ModelError(error) =>
            error ==>
              """Missing/empty mandatory attributes:
                |  MandatoryAttr.hobbies
                |""".stripMargin
        }

      // All mandatory attributes of entity are present and valid
      _ <- MandatoryAttr.name("Bob").age(42).hobbies(Set("golf", "stamps")).save.transact
      _ <- MandatoryAttr.name.age.hobbies.insert("Liz", 38, Set("climbing")).transact
    } yield ()
  }


  "No Set values" - validation { implicit conn =>
    for {
      _ <- MandatoryAttr.name("Bob").age(42).hobbies(Set.empty[String]).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ModelError(error) =>
            error ==>
              """Missing/empty mandatory attributes:
                |  MandatoryAttr.hobbies
                |""".stripMargin
        }

      _ <- MandatoryAttr.name.age.hobbies.insert(
          ("Liz", 38, Set("climbing")),
          ("Bob", 42, Set.empty[String]),
        ).transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(indexedInsertErrors, _) =>
            indexedInsertErrors ==> Seq(
              (
                1, // Top-level row index
                Seq(
                  InsertError(
                    2, // tuple index
                    "MandatoryAttr.hobbies",
                    Seq(
                      """Can't insert empty Set for mandatory attribute"""
                    ),
                    Seq()
                  )
                )
              )
            )
        }

      // All mandatory attributes of entity are present and valid
      _ <- MandatoryAttr.name("Bob").age(42).hobbies(Set("golf", "stamps")).save.transact
      _ <- MandatoryAttr.name.age.hobbies.insert("Liz", 38, Set("climbing")).transact
    } yield ()
  }


  "Update, delete attr" - validation { implicit conn =>
    for {
      id <- MandatoryAttr.name("Bob").age(42).hobbies(Set("golf", "stamps")).save.transact.map(_.id)

      _ <- MandatoryAttr(id).name().update.transact
        .map(_ ==> "Unexpected success").recover {
          case ModelError(error) =>
            error ==>
              """Can't delete mandatory attributes (or remove last values of card-many attributes):
                |  MandatoryAttr.name
                |""".stripMargin
        }

      _ <- MandatoryAttr(id).hobbies().update.transact
        .map(_ ==> "Unexpected success").recover {
          case ModelError(error) =>
            error ==>
              """Can't delete mandatory attributes (or remove last values of card-many attributes):
                |  MandatoryAttr.hobbies
                |""".stripMargin
        }

      _ <- MandatoryAttr(id).name().hobbies().update.transact
        .map(_ ==> "Unexpected success").recover {
          case ModelError(error) =>
            error ==>
              """Can't delete mandatory attributes (or remove last values of card-many attributes):
                |  MandatoryAttr.name
                |  MandatoryAttr.hobbies
                |""".stripMargin
        }
    } yield ()
  }


  "Update, remove last card-many value" - validation { implicit conn =>
    for {
      id <- MandatoryAttr.name("Bob").age(42).hobbies(Set("golf", "stamps")).save.transact.map(_.id)

      // We can remove a value from a Set as long as it's not the last value
      _ <- MandatoryAttr(id).hobbies.remove("stamps").update.transact

      // Can't remove the last value of a mandatory attribute Set of values
      _ <- MandatoryAttr(id).hobbies.remove("golf").update.transact
        .map(_ ==> "Unexpected success").recover {
          case ModelError(error) =>
            error ==>
              """Can't delete mandatory attributes (or remove last values of card-many attributes):
                |  MandatoryAttr.hobbies
                |""".stripMargin
        }
    } yield ()
  }
}
