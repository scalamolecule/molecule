package molecule.coreTests.spi.validation

import boopickle.Default._
import molecule.base.error._
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.core.util.SerializationUtils
import molecule.coreTests.async._
import molecule.coreTests.dataModels.dsl.Validation._
import molecule.coreTests.setup.CoreTestSuite
import utest._
import scala.language.implicitConversions

trait MandatoryRefs extends CoreTestSuite with ApiAsync with SerializationUtils { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Missing ref" - validation { implicit conn =>
      for {
        _ <- MandatoryRefB.i(1).save.transact
          .map(_ ==> "Unexpected success").recover {
            case ModelError(error) =>
              error ==>
                """Missing/empty mandatory references:
                  |  MandatoryRefB.refB pointing to namespace RefB
                  |""".stripMargin
          }
        // Same error for inserts
        _ <- MandatoryRefB.i.insert(1).transact
          .map(_ ==> "Unexpected success").recover {
            case ModelError(error) =>
              error ==>
                """Missing/empty mandatory references:
                  |  MandatoryRefB.refB pointing to namespace RefB
                  |""".stripMargin
          }

        // Adding ref id satisfy mandatory requirement
        refBid <- RefB.i(2).save.transact.map(_.id)
        _ <- MandatoryRefB.i(1).refB(refBid).save.transact

        // Or creating the entity and the reference in one go
        _ <- MandatoryRefB.i(1).RefB.i(2).save.transact
      } yield ()
    }


    "Missing ref, second level" - validation { implicit conn =>
      for {
        // Ref A still has unset mandatory ref to RefB
        _ <- MandatoryRefAB.i(1).RefA.i(2).save.transact
          .map(_ ==> "Unexpected success").recover {
            case ModelError(error) =>
              error ==>
                """Missing/empty mandatory references:
                  |  RefA.refB pointing to namespace RefB
                  |""".stripMargin
          }

        // Adding ref id satisfy mandatory requirement
        refBid <- RefB.i(3).save.transact.map(_.id)
        _ <- MandatoryRefAB.i(1).RefA.i(2).refB(refBid).save.transact

        // Or creating the entity and the reference in one go
        _ <- MandatoryRefAB.i(1).RefA.i(2).RefB.i(3).save.transact
      } yield ()
    }


    "Missing card-many ref" - validation { implicit conn =>
      for {
        _ <- MandatoryRefsB.i(1).save.transact
          .map(_ ==> "Unexpected success").recover {
            case ModelError(error) =>
              error ==>
                """Missing/empty mandatory references:
                  |  MandatoryRefsB.refsB pointing to namespace RefB
                  |""".stripMargin
          }

        // Adding ref id satisfy mandatory requirement
        refBid <- RefB.i(2).save.transact.map(_.id)
        _ <- MandatoryRefsB.i(1).refsB(Set(refBid)).save.transact

        // Or creating the entity and the reference in one go
        _ <- MandatoryRefsB.i(1).RefsB.i(2).save.transact
      } yield ()
    }


    "Missing card-many ref, second level" - validation { implicit conn =>
      for {
        // Ref A still has unset mandatory ref to RefB
        _ <- MandatoryRefsAB.i(1).RefsA.i(2).save.transact
          .map(_ ==> "Unexpected success").recover {
            case ModelError(error) =>
              error ==>
                """Missing/empty mandatory references:
                  |  RefA.refB pointing to namespace RefB
                  |""".stripMargin
          }

        // Adding ref id satisfy mandatory requirement
        refBid <- RefB.i(3).save.transact.map(_.id)
        _ <- MandatoryRefAB.i(1).RefA.i(2).refB(refBid).save.transact

        // Or creating the entity and the reference in one go
        _ <- MandatoryRefAB.i(1).RefA.i(2).RefB.i(3).save.transact
      } yield ()
    }


    "Update, delete ref attr" - validation { implicit conn =>
      for {
        id <- MandatoryRefB.i(1).RefB.i(2).save.transact.map(_.id)

        _ <- MandatoryRefB(id).refB().update.transact
          .map(_ ==> "Unexpected success").recover {
            case ModelError(error) =>
              error ==>
                """Can't delete mandatory attributes (or remove last values of card-many attributes):
                  |  MandatoryRefB.refB
                  |""".stripMargin
          }
      } yield ()
    }


    "Update, remove last card-many value" - validation { implicit conn =>
      for {
        List(r1, r2) <- RefB.i.insert(2, 3).transact.map(_.ids)

        id <- MandatoryRefsB.i(1).refsB(Set(r1, r2)).save.transact.map(_.ids)

        // Mandatory refs can be removed as long as some ref ids remain
        _ <- MandatoryRefsB(id).refsB.remove(r2).update.transact

        // Last mandatory ref can't be removed. This can prevent creating orphan relationships.
        _ <- MandatoryRefsB(id).refsB.remove(r1).update.transact
          .map(_ ==> "Unexpected success").recover {
            case ModelError(error) =>
              error ==>
                """Can't delete mandatory attributes (or remove last values of card-many attributes):
                  |  MandatoryRefsB.refsB
                  |""".stripMargin
          }
      } yield ()
    }


    // todo? This is easily done with Datomic. But from sql it seems that one would have to
    //  - check every known table having a reference to the ns, or
    //  - create some trigger on delete if possible? That would be much better to having the
    //    db server automatically preventing orphaning mandatory relationships.
    // So for now, this test is only implemented for Datomic.

    "Deleting mandatory ref" - validation { implicit conn =>
      if (database == "Datomic") {
        for {
          List(e1) <- MandatoryRefB.i(1).RefB.i(1).save.transact.map(_.ids)

          List(r1) <- MandatoryRefB(e1).refB.query.get

          // Can't delete r1 since MandatoryRefB.refB is referencing it and is mandatory
          _ <- RefB(r1).delete.transact
            .map(_ ==> "Unexpected success").recover {
              case ExecutionError(err) =>
                err ==>
                  s"""Can't delete entities referenced by mandatory ref attributes of other entities:
                     |  MandatoryRefB.refB: List($e1)
                     |""".stripMargin
            }

          // Let's add two other entities referencing RefB too
          List(e2, e3) <- MandatoryRefsB.i.refsB.insert(
            (4, Set(r1)),
            (5, Set(r1)),
          ).transact.map(_.ids)

          // Now 3 entities would be rendered invalid if we deleted r1
          _ <- RefB(r1).delete.transact
            .map(_ ==> "Unexpected success").recover {
              case ExecutionError(error) =>
                error ==>
                  s"""Can't delete entities referenced by mandatory ref attributes of other entities:
                     |  MandatoryRefB.refB: List($e1)
                     |  MandatoryRefsB.refsB: List($e2, $e3)
                     |""".stripMargin
            }
        } yield ()
      }
    }
  }
}
