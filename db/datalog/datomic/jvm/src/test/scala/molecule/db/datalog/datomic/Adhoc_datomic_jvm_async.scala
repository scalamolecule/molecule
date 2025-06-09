package molecule.db.datalog.datomic

import molecule.db.compliance.domains.dsl.Types.Entity
import molecule.db.compliance.setup.{Test, TestUtils}
import molecule.db.core.ast.{AttrOneManInt, AttrOneManString, DataModel, V}
import molecule.db.core.util.Executor.*
import molecule.db.datalog.datomic.async.*
import molecule.db.datalog.datomic.setup.DbProviders_datomic


class Adhoc_datomic_jvm_async extends Test with DbProviders_datomic with TestUtils {

    "types" - types { implicit conn =>
      import molecule.db.compliance.domains.dsl.Types._
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Entity.int(3).save.transact
        _ <- Entity.int.query.get.map(_ ==> List(3))


      } yield ()
    }


  //    "unique" - unique { implicit conn =>
  //      import molecule.db.compliance.domains.dsl.Types._
  //      for {
  //        _ <- Entity.int.s.i.insert(triples).transact
  //
  //      } yield ()
  //    }
  //
  //
  //        "validation" - validation { implicit conn =>
  //          import molecule.db.compliance.domains.dsl.Validation._
  //          for {
  //
  //            id <- MandatoryRefB.i(1).RefB.i(2).save.transact.map(_.id)
  //
  //            _ <- MandatoryRefB(id).refB().update.i.transact
  //              .map(_ ==> "Unexpected success").recover {
  //                case ModelError(error) =>
  //                  error ==>
  //                    """Can't delete mandatory attributes (or remove last values of card-many attributes):
  //                      |  MandatoryRefB.refB
  //                      |""".stripMargin
  //              }
  //
  //          } yield ()
  //        }

  //
  //
  //    "validation" - validation { implicit conn =>
  //      import molecule.db.compliance.domains.dsl.Validation._
  //      for {
  //        List(r1, r2) <- RefB.i.insert(2, 3).transact.map(_.ids)
  //
  //        id <- MandatoryRefsB.i(1).refsB(Set(r1, r2)).save.transact.map(_.ids)
  //
  //        // Mandatory refs can be removed as long as some ref ids remain
  //        _ <- MandatoryRefsB(id).refsB.remove(r2).update.transact
  //
  //        // Last mandatory ref can't be removed. This can prevent creating orphan relationships.
  //        _ <- MandatoryRefsB(id).refsB.remove(r1).update.transact
  //          .map(_ ==> "Unexpected success").recover {
  //            case ModelError(error) =>
  //              error ==>
  //                """Can't delete mandatory attributes (or remove last values of card-many attributes):
  //                  |  MandatoryRefsB.refsB
  //                  |""".stripMargin
  //          }
  //      } yield ()
  //    }
}