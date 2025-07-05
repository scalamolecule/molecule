package molecule.db.datalog.datomic

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.core.util.Executor.*
import molecule.db.datalog.datomic.async.*
import molecule.db.datalog.datomic.setup.DbProviders_datomic


class Adhoc_datomic_jvm_async extends MUnit with DbProviders_datomic with TestUtils {

//  "types" - types { implicit conn =>
//    import molecule.db.compliance.domains.dsl.Types.*
//    implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
//    for {
//      _ <- Entity.int(3).save.transact
//      _ <- Entity.int.query.get.map(_ ==> List(3))
//
//
//    } yield ()
//  }

  "Basic adjacent optional refs" - refs { implicit conn =>
    import molecule.db.compliance.domains.dsl.Refs.*
    for {
      _ <- A.i
        .B.?(B.i.s)
        .C.?(C.s.i).insert(List(
          (1, None, None),
          (2, Some((20, "b")), None),
          (3, None, Some(("c", 300))),
          (4, Some((40, "b")), Some(("c", 400))),
        )).transact

      _ <- A.i.a1
        .B.?(B.i.s)
        .C.?(C.s.i).query.get.map(_ ==> List(
          (1, None, None),
          (2, Some((20, "b")), None),
          (3, None, Some(("c", 300))),
          (4, Some((40, "b")), Some(("c", 400))),
        ))
    } yield ()
  }

//  "Nested 2 levels" - segments { implicit conn =>
//    import molecule.db.compliance.domains.dsl.Segments.*
//
//    for {
//      _ <- lit_Book.title.Reviewers.name.Professions.*(gen_Profession.name)
//        .insert(("book", "Jan", List("Musician"))).i.transact
//
////      _ <- lit_Book.title.Reviewers.name.Professions.*(gen_Profession.name)
////        .query.i.get.map(_ ==> List(("book", "Jan", List("Musician"))))
////
////      // Same as
////      _ <- lit_Book.title.Reviewers.Professions.*(gen_Profession.name)
////        .query.get.map(_ ==> List(("book", List("Musician"))))
//    } yield ()
//  }


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
  //        case List(r1, r2) <- RefB.i.insert(2, 3).transact.map(_.ids)
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