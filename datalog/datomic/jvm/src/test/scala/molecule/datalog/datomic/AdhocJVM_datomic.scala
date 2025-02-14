package molecule.datalog.datomic

import molecule.base.error.ModelError
import molecule.core.util.Executor._
import molecule.coreTests.domains.schema.TypesSchema_datomic
import molecule.coreTests.setup.{Test, TestUtils}
import molecule.datalog.datomic.async._
import molecule.datalog.datomic.facade.DatomicPeer
import molecule.datalog.datomic.setup.DbProviders_datomic
import scala.language.implicitConversions


class AdhocJVM_datomic extends Test with DbProviders_datomic with TestUtils {


  DatomicPeer.recreateDb(TypesSchema_datomic)

  "types" - types { implicit conn =>
    import molecule.coreTests.domains.dsl.Types._
    implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
    for {

      _ <- Entity.int(3).save.transact
      _ <- Entity.int.query.get.map(_ ==> List(3))

      //        _ = Peer.q(
      //          """[:find  ?tx ?e ?v ?op
      //            | :where [?e :Entity/int ?v ?tx ?op]]
      //            |""".stripMargin, conn.db.asInstanceOf[Database].history()
      //        ).forEach(r => println(r))
    } yield ()
  }


  "refs" - refs { implicit conn =>
    import molecule.coreTests.domains.dsl.Refs._
    for {

//      _ <- A.?(A.i).insert(Some(1)).transact
//        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
//          err ==> "Optional entity not implement for Datomic."
//        }
//
//      _ <- A.i.query.get.map(_ ==> List(1))
//        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
//          err ==> "Optional entity not implement for Datomic."
//        }


      _ <- A.?(A.i).B.i.insert(List((None, 1))).transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Optional entity not implement for Datomic."
        }

      _ <- A.?(A.i).B.i.a1.query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Optional entity not implement for Datomic."
        }

      //          _ <- A.i.query.i.get.map(_ ==> List(
      //            1
      //          ))

    } yield ()
  }


  //    "unique" - unique { implicit conn =>
  //      import molecule.coreTests.domains.dsl.Types._
  //      for {
  //        _ <- Entity.int.s.i.insert(triples).transact
  //
  //      } yield ()
  //    }
  //
  //
  //        "validation" - validation { implicit conn =>
  //          import molecule.coreTests.domains.dsl.Validation._
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
  //      import molecule.coreTests.domains.dsl.Validation._
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