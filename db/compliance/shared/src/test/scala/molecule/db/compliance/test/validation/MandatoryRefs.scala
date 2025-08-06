package molecule.db.compliance.test.validation

import boopickle.Default.*
import molecule.core.error.{ExecutionError, ModelError}
import molecule.core.setup.{MUnit_arrays, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Validation.*
import molecule.db.compliance.setup.DbProviders


case class MandatoryRefs(
  suite: MUnit_arrays,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "Missing ref" - validation {
    for {
      _ <- MandatoryRefB.i(1).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ModelError(error) =>
            error ==>
              """Missing/empty mandatory references:
                |  MandatoryRefB.refB pointing to entity RefB
                |""".stripMargin
        }
      // Same error for inserts
      _ <- MandatoryRefB.i.insert(1).transact
        .map(_ ==> "Unexpected success").recover {
          case ModelError(error) =>
            error ==>
              """Missing/empty mandatory references:
                |  MandatoryRefB.refB pointing to entity RefB
                |""".stripMargin
        }

      // Adding ref id satisfy mandatory requirement
      refBid <- RefB.i(2).save.transact.map(_.id)
      _ <- MandatoryRefB.i(1).refB(refBid).save.transact

      // Or creating the entity and the reference in one go
      _ <- MandatoryRefB.i(1).RefB.i(2).save.transact
    } yield ()
  }


  "Missing ref, second level" - validation {
    for {
      // Ref A still has unset mandatory ref to RefB
      _ <- MandatoryRefAB.i(1).RefA.i(2).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ModelError(error) =>
            error ==>
              """Missing/empty mandatory references:
                |  RefA.refB pointing to entity RefB
                |""".stripMargin
        }

      // Adding ref id satisfy mandatory requirement
      refBid <- RefB.i(3).save.transact.map(_.id)
      _ <- MandatoryRefAB.i(1).RefA.i(2).refB(refBid).save.transact

      // Or creating the entity and the reference in one go
      _ <- MandatoryRefAB.i(1).RefA.i(2).RefB.i(3).save.transact
    } yield ()
  }


  "Missing card-many ref" - validation {
    for {
      _ <- MandatoryRefsB.i(1).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ModelError(error) =>
            error ==>
              """Missing/empty mandatory references:
                |  MandatoryRefsB.refsB pointing to entity RefB
                |""".stripMargin
        }

      // Adding ref id satisfy mandatory requirement
      refBid <- RefB.i(2).save.transact.map(_.id)
      _ <- MandatoryRefsB.i(1).refsB(Set(refBid)).save.transact

      // Or creating the entity and the reference in one go
      _ <- MandatoryRefsB.i(1).RefsB.i(2).save.transact
    } yield ()
  }


  "Missing card-many ref, second level" - validation {
    for {
      // Ref A still has unset mandatory ref to RefB
      _ <- MandatoryRefsAB.i(1).RefsA.i(2).save.transact
        .map(_ ==> "Unexpected success").recover {
          case ModelError(error) =>
            error ==>
              """Missing/empty mandatory references:
                |  RefA.refB pointing to entity RefB
                |""".stripMargin
        }

      // Adding ref id satisfy mandatory requirement
      refBid <- RefB.i(3).save.transact.map(_.id)
      _ <- MandatoryRefAB.i(1).RefA.i(2).refB(refBid).save.transact

      // Or creating the entity and the reference in one go
      _ <- MandatoryRefAB.i(1).RefA.i(2).RefB.i(3).save.transact
    } yield ()
  }


  "Update, delete ref attr" - validation {
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


  "Update, remove last card-many value" - validation {
    for {
      case List(r1, r2) <- RefB.i.insert(2, 3).transact.map(_.ids)

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
}
