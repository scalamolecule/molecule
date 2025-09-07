package molecule.db.h2

import scala.concurrent.Future
import molecule.core.error.ModelError
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.util.Executor.*
import molecule.db.h2.async.*
import molecule.db.h2.setup.DbProviders_h2
import org.scalactic.Equality
import molecule.core.dataModel.*
import scala.Tuple.:*
import molecule.db.compliance.domains.dsl.Types.Entity


class Adhoc_h2_jvm_async extends MUnit with DbProviders_h2 with TestUtils {

  import molecule.db.compliance.domains.dsl.Refs.*

  //  "types" - types {
  //    import molecule.db.compliance.domains.dsl.Types.*
  //    given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
  //    for {
  //      List(a, b) <- Entity.int.insert(1, 2).transact.map(_.ids)
  //      _ <- Entity.int(3).save.transact
  //      _ <- Entity.int.a1.query.get.map(_ ==> List(1, 2, 3))
  //      _ <- Entity(a).int(10).update.transact
  //      _ <- Entity(b).delete.transact
  //      _ <- Entity.int.a1.query.get.map(_ ==> List(3, 10))
  //
  //    } yield ()
  //  }


  "types" - refs {
    import molecule.db.compliance.domains.dsl.Refs.*
    for {

      _ <- A.i.B.?(B.s).insert(
          (1, Some("a")),
          (2, None),
        ).transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Insertion of optional ref is not supported."
        }


    } yield ()
  }


  //  "ids, ref" - refs {
  //    for {
  //      case List(a1, a2) <- A.i.Bb.*(B.i.C.i).insert(
  //        (1, List((1, 2), (3, 4))),
  //        (2, Nil),
  //      ).i.transact.map(_.ids)
  //
  //      _ <- A.id(a1, a2).i.a1.Bb.*?(B.i.a1.C.i).query.get.map(_ ==> List(
  //        (a1, 1, List((1, 2), (3, 4))),
  //        (a2, 2, Nil),
  //      ))
  //    } yield ()
  //  }


  //  "Optional ref" - refs {
  //    for {
  //      _ <- A.i(1).B.?(B.i(2)).save.transact
  //        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
  //          err ==> "Optional ref not allowed in save molecule. Please use mandatory ref or insert instead."
  //        }
  //    } yield ()
  //  }
  //
  //
  //  "refs" - refs {
  //    import molecule.db.compliance.domains.dsl.Refs.*
  //    for {
  //      _ <- A.i(0).s("a").B.i(1).s("b").Cc.i(22)
  //        ._B.C.i(2).s("c")
  //        ._B._A.Bb.i(11)
  //        .save.transact
  //
  //      _ <- A.i.B.i.query.get.map(_ ==> List((0, 1)))
  //      _ <- A.i.B.i._A.s.query.get.map(_ ==> List((0, 1, "a")))
  //      _ <- A.i.B.i._A.Bb.i.query.get.map(_ ==> List((0, 1, 11)))
  //      _ <- A.i.B.i.C.i._B.s.query.get.map(_ ==> List((0, 1, 2, "b")))
  //      _ <- A.i.B.C.i.query.get.map(_ ==> List((0, 2)))
  //      _ <- A.i.B.C.i._B.i.query.get.map(_ ==> List((0, 2, 1)))
  //      _ <- A.i.B.i.C.i._B.Cc.i.query.get.map(_ ==> List((0, 1, 2, 22)))
  //      _ <- A.i.B.C.i._B.Cc.i.query.get.map(_ ==> List((0, 2, 22)))
  //      _ <- A.i.B.i.C.i._B.s._A.s.query.get.map(_ ==> List((0, 1, 2, "b", "a")))
  //      _ <- A.i.B.i.C.i._B._A.s.query.get.map(_ ==> List((0, 1, 2, "a")))
  //      _ <- A.i.B.C.i._B._A.s.query.get.map(_ ==> List((0, 2, "a")))
  //      _ <- A.i.B.i.C.i._B.s._A.Bb.i.query.get.map(_ ==> List((0, 1, 2, "b", 11)))
  //      _ <- A.i.B.i.C.i._B._A.Bb.i.query.get.map(_ ==> List((0, 1, 2, 11)))
  //      _ <- A.i.B.C.i._B._A.Bb.i.query.get.map(_ ==> List((0, 2, 11)))
  //      _ <- A.B.C.s._B._A.Bb.i.query.get.map(_ ==> List(("c", 11)))
  //
  //    } yield ()
  //  }


  //    "unique" - unique {
  //      import molecule.db.compliance.domains.dsl.Uniques._
  //      val triples             = getTriples.map(t => (t._3, t._1, t._2))
  //      val List(a, b, c, d, e) = triples.sortBy(p => (p._2, p._3, p._1))
  //      val query               = (c: String, l: Int) => Uniques.int.a3.s.a1.i.a2.query.from(c).limit(l)
  //      for {
  //        _ <- Uniques.int.s.i.insert(triples).transact
  //
  //      } yield ()
  //    }
  //


  //    "validation" - validation {
  //      import molecule.db.compliance.domains.dsl.Validation._
  //
  //      for {
  //
  //        _ <- MandatoryAttr.name("Bob").age(42).hobbies(Set.empty[String]).save.transact
  //          .map(_ ==> "Unexpected success").recover {
  //            case ModelError(error) =>
  //              error ==>
  //                """Missing/empty mandatory attributes:
  //                  |  MandatoryAttr.hobbies
  //                  |""".stripMargin
  //          }
  //
  //        _ <- MandatoryAttr.name.age.hobbies.insert(
  //            ("Liz", 38, Set("climbing")),
  //            ("Bob", 42, Set.empty[String]),
  //          ).transact
  //          .map(_ ==> "Unexpected success").recover {
  //            case InsertErrors(indexedInsertErrors, _) =>
  //              indexedInsertErrors ==> Seq(
  //                (
  //                  1, // Top-level row index
  //                  Seq(
  //                    InsertError(
  //                      2, // tuple index
  //                      "MandatoryAttr.hobbies",
  //                      Seq(
  //                        """Can't insert empty Set for mandatory attribute"""
  //                      ),
  //                      Seq()
  //                    )
  //                  )
  //                )
  //              )
  //          }
  //
  //        // All mandatory attributes of entity are present and valid
  //        _ <- MandatoryAttr.name("Bob").age(42).hobbies(Set("golf", "stamps")).save.transact
  //        _ <- MandatoryAttr.name.age.hobbies.insert(("Liz", 38, Set("climbing"))).transact
  //
  //      } yield ()
  //    }
  //
  //    "partitions" - partition {
  //      import molecule.db.compliance.domains.dsl.Groups._
  //      for {
  //
  //        _ <- lit_Book.title.Reviewers.name.Professions.*(gen_Profession.name)
  //          .insert("book", "Jan", List("Musician")).transact
  //
  //      } yield ()
  //    }
}
