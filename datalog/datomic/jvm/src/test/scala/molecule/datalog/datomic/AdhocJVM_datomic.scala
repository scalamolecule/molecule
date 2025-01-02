package molecule.datalog.datomic

import molecule.base.error.ModelError
import molecule.core.util.Executor._
import molecule.coreTests.setup.{Test, TestUtils}
import molecule.datalog.datomic.async._
import molecule.datalog.datomic.setup.DbProviders_datomic
import scala.language.implicitConversions


class AdhocJVM_datomic extends Test with DbProviders_datomic with TestUtils {


  "types" - types { implicit conn =>
    import molecule.coreTests.domains.dsl.Types._
    implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)

    //      println("hello".substring(-2, 1))


    for {

      //        _ <- Entity.int(3).save.transact
      //        _ <- Entity.int.query.get.map(_ ==> List(3))


      //        _ = Peer.q(
      //          """[:find  ?tx ?e ?v ?op
      //            | :where [?e :Entity/int ?v ?tx ?op]]
      //            |""".stripMargin, conn.db.asInstanceOf[Database].history()
      //        ).forEach(r => println(r))


      //        ids <- Entity.string.insert("Hello", "World").transact.map(_.ids)
      //
      //        _ <- Entity(ids).string.substring(-2, 3).update.transact
      //          .map(_ ==> "Unexpected success").recover { case ModelError(error) =>
      //            error ==> "Start index should be 0 or more"
      //          }
      //
      //        _ <- Entity(ids).string.substring(4, 3).update.transact
      //          .map(_ ==> "Unexpected success").recover { case ModelError(error) =>
      //            error ==> "Start index should be smaller than end index"
      //          }
      //
      //        // No change if start is after end
      //        _ <- Entity(ids).string.substring(10, 11).update.transact
      //        _ <- Entity.string.a1.query.get.map(_ ==> List("Hello", "World"))
      //
      //
      //        // End index after string length leaves tail as-is
      //        _ <- Entity(ids).string.substring(1, 10).update.transact
      //        _ <- Entity.string.a1.query.get.map(_ ==> List("ello", "orld"))
      //
      //        // Pick some middle part
      //        _ <- Entity(ids).string.substring(1, 3).update.transact
      //        _ <- Entity.string.a1.query.get.map(_ ==> List("ll", "rl"))

      ids <- Entity.string.insert("Hello", "World").transact.map(_.ids)

      _ <- Entity(ids).string.substring(-2, 3).update.transact
        .map(_ ==> "Unexpected success").recover { case ModelError(error) =>
          error ==> "Start index should be 0 or more"
        }

      _ <- Entity(ids).string.substring(4, 3).update.transact
        .map(_ ==> "Unexpected success").recover { case ModelError(error) =>
          error ==> "Start index should be smaller than end index"
        }

      // Pick index after end to keep rest of string
      _ <- Entity(ids).string.substring(1, 100).update.transact
      _ <- Entity.string.a1.query.get.map(_ ==> List("ello", "orld"))

      // Pick some middle part
      _ <- Entity(ids).string.substring(1, 3).update.transact
      _ <- Entity.string.a1.query.get.map(_ ==> List("ll", "rl"))

      // Empty string returned if start is after end
      // OBS: beware of saved empty string values!
      _ <- Entity(ids).string.substring(10, 11).update.transact
      _ <- Entity.string.a1.query.get.map(_ ==> List(""))


    } yield ()
  }


  //    "refs" - refs { implicit conn =>
  //      import molecule.coreTests.domains.dsl.Refs._
  //      for {
  //        _ <- A.s.i.B.?(B.i.C.?(C.i)).D.?(D.i).insert(List(
  //          ("a", 1, Some((2, Some(3))), Some(4)),
  //        )).transact
  //
  //        _ <- A.s.i.a1.B.?(B.i).D.?(D.i).query.i.get.map(_ ==> List(
  //          ("a", 1, Some(2), Some(4)),
  //        ))
  //
  //      } yield ()
  //    }


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