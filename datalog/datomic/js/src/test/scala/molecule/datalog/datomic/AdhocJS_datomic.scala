package molecule.datalog.datomic

import boopickle.Default._
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.datalog.datomic.async._
import molecule.datalog.datomic.setup.TestSuite_datomic
import utest._


object AdhocJS_datomic extends TestSuite_datomic {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      for {

        List(a, b) <- Ns.int.insert(1, 2).transact.map(_.ids)
        _ <- Ns.int(3).save.transact
        _ <- Ns.int.a1.query.get.map(_ ==> List(1, 2, 3))
        _ <- Ns(a).int(10).update.transact
        _ <- Ns(b).delete.transact
        _ <- Ns.int.a1.query.get.map(_ ==> List(3, 10))

      } yield ()
    }


    //    "refs" - refs { implicit conn =>
    //      import molecule.coreTests.dataModels.dsl.Refs._
    //      for {
    //
    ////        _ <- A.i.B.?(B.i).insert(List(
    ////          (1, None),
    ////          (2, Some(20)),
    ////        )).transact
    ////
    ////        _ <- A.i.a1.B.?(B.i).query.get.map(_ ==> List(
    ////          (1, None),
    ////          (2, Some(20)),
    ////        ))
    ////
    ////        _ <- A.i.B.i.query.get.map(_ ==> List(
    ////          (2, 20),
    ////        ))
    //
    //        _ <- A.i
    //          .B.?(B.i.s)
    //          .C.?(C.s.i).insert(List(
    //            (1, None, None),
    //            (2, Some((20, "b")), None),
    //            (3, None, Some(("c", 300))),
    //            (4, Some((40, "b")), Some(("c", 400))),
    //          )).transact
    //
    //        _ <- A.i.a1
    //          .B.?(B.i.s)
    //          .C.?(C.s.i).query.get.map(_ ==> List(
    //            (1, None, None),
    //            (2, Some((20, "b")), None),
    //            (3, None, Some(("c", 300))),
    //            (4, Some((40, "b")), Some(("c", 400))),
    //          ))
    //
    //      } yield ()
    //        }


    //    "validation" - validation { implicit conn =>
    //      import molecule.coreTests.dataModels.dsl.Validation._
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
}
