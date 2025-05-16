package molecule.db.datalog.datomic

import molecule.db.compliance.domains.dsl.Types.Entity
import molecule.db.compliance.setup.{Test, TestUtils}
import molecule.db.core.util.Executor.*
import molecule.db.datalog.datomic.async.*
import molecule.db.datalog.datomic.setup.DbProviders_datomic


class AdhocJVM_datomic_async extends Test with DbProviders_datomic with TestUtils {

  //  DatomicPeer.recreateDb(TypesSchema_datomic)

  //  "types" - types { implicit conn =>
  //    import molecule.db.compliance.domains.dsl.Types._
  //    implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
  //    for {
  //
  //      _ <- Entity.int(3).save.transact
  //      _ <- Entity.int.query.get.map(_ ==> List(3))
  //
  //      //        _ = Peer.q(
  //      //          """[:find  ?tx ?e ?v ?op
  //      //            | :where [?e :Entity/int ?v ?tx ?op]]
  //      //            |""".stripMargin, conn.db.asInstanceOf[Database].history()
  //      //        ).forEach(r => println(r))
  //    } yield ()
  //  }

  "Mutations call back" - types { implicit conn =>
    var intermediaryResults = List.empty[List[Int]]
    for {
      // Initial data
      _ <- Entity.i(1).save.transact

      // Start subscription and define a callback function
      _ <- Entity.i.query.subscribe { updatedResult =>
        // Do something with updated result
        intermediaryResults = intermediaryResults :+ updatedResult.sorted
      }

      // When calling from ScalaJS, calls are asynchronous, and we need to wait
      // a bit for the subscription websocket to be ready to serve callbacks.
      _ <- delay(1000)

      // Mutations to be monitored by subscription
      id <- Entity.i(2).save.transact.map(_.id)
      _ <- Entity.i.a1.query.get.map(_ ==> List(1, 2))

      _ <- Entity.i.insert(3, 4).transact
      _ <- Entity.i.a1.query.get.map(_ ==> List(1, 2, 3, 4))

      _ <- Entity(id).i(20).update.transact
      _ <- Entity.i.a1.query.get.map(_ ==> List(1, 3, 4, 20))

      _ <- Entity(id).delete.transact
      _ <- Entity.i.a1.query.get.map(_ ==> List(1, 3, 4))

      // Mutations with no callback-involved attributes don't call back
      _ <- Entity.string("foo").save.transact
      _ <- delay(1000)

      // Callback produced all intermediary results correctly
      _ = intermediaryResults ==> List(
        List(1, 2), //        query result after 2 was saved
        List(1, 2, 3, 4), //  query result after 3 and 4 were inserted
        List(1, 3, 4, 20), // query result after 2 was updated to 20
        List(1, 3, 4), //     query result after 20 was deleted
      )
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