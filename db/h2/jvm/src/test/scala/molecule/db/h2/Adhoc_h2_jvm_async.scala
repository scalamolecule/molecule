package molecule.db.h2

import scala.concurrent.Future
import molecule.core.error.ModelError
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.util.Executor.*
import molecule.db.h2.async.*
import molecule.db.h2.setup.DbProviders_h2
import org.scalactic.Equality


class Adhoc_h2_jvm_async extends MUnit with DbProviders_h2 with TestUtils {

  import molecule.db.compliance.domains.dsl.Types.*

  "types" - types {
    given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
    for {
      List(a, b) <- Entity.int.insert(1, 2).transact.map(_.ids)
      _ <- Entity.int(3).save.transact
      _ <- Entity.int.a1.query.get.map(_ ==> List(1, 2, 3))
      _ <- Entity(a).int(10).update.transact
      _ <- Entity(b).delete.transact
      _ <- Entity.int.a1.query.get.map(_ ==> List(3, 10))


    } yield ()
  }


//    "refs" - refs {
//      import molecule.db.compliance.domains.dsl.Refs.*
//
//      for {
//        _ <- G.i(1).H.i(2).save.transact
//
////        _ <- G.i.query.get.map(_ ==> List(1))
////        _ <- G.i.h.query.get.map(_ ==> List((1, 1)))
////        _ <- H.i.query.get.map(_ ==> List(2))
//
//        _ <- G.i.H.i.query.get.map(_ ==> List((1, 2)))
//
////        _ <- G.i(1).H.i(2).C.i(3).save.transact
////        _ <- G.i.H.i.C.i.query.get.map(_ ==> List((1, 2, 3)))
//
//
//      } yield ()
//    }



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
