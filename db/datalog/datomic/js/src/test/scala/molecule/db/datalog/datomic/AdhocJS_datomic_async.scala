package molecule.db.datalog.datomic

import boopickle.Default.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.{Test, TestUtils}
import molecule.db.core.util.Executor.*
import molecule.db.datalog.datomic.async.*
import molecule.db.datalog.datomic.setup.DbProviders_datomic

class AdhocJS_datomic_async extends Test with DbProviders_datomic with TestUtils {

  "types" - types { implicit conn =>
    for {

      List(a, b) <- Entity.int.insert(1, 2).transact.map(_.ids)
      _ <- Entity.int(3).save.transact
      _ <- Entity.int.a1.query.get.map(_ ==> List(1, 2, 3))
      _ <- Entity(a).int(10).update.transact
      _ <- Entity(b).delete.transact
      _ <- Entity.int.a1.query.get.map(_ ==> List(3, 10))

    } yield ()
  }


  //    "refs" - refs { implicit conn =>
  //      import molecule.db.compliance.domains.dsl.Refs._
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
