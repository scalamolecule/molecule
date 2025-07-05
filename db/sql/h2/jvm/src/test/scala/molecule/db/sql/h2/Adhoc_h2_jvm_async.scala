package molecule.db.sql.h2

import molecule.base.error.{InsertError, InsertErrors, ModelError}
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.Color.{Blue, Green, Red, White, Yellow}
import molecule.db.core.util.Executor.*
import molecule.db.sql.h2.async.*
import molecule.db.sql.h2.setup.DbProviders_h2
//import moleculeGen.molecule.db.compliance.domains.dsl.Types.Color


class Adhoc_h2_jvm_async extends MUnit with DbProviders_h2 with TestUtils {

  import molecule.db.compliance.domains.dsl.Types.*

  "types" - types { implicit conn =>
    implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)

    for {
      case List(a, b) <- Entity.int.insert(1, 2).transact.map(_.ids)
      _ <- Entity.int(3).save.transact
      _ <- Entity.int.a1.query.get.map(_ ==> List(1, 2, 3))
      _ <- Entity(a).int(10).update.transact
      _ <- Entity(b).delete.transact
      _ <- Entity.int.a1.query.get.map(_ ==> List(3, 10))

    } yield ()
  }


//  "Owned entities: Card-one" - refs { implicit conn =>
//    import molecule.db.compliance.domains.dsl.Refs.*
//
//    for {
//      e1 <- A.i.OwnB.i.insert(
//        (1, 10),
//        (2, 20)
//      ).transact.map(_.id)
//
//      // 2 entities, each with an owned sub-entity
//      _ <- A.i.a1.OwnB.i.query.get.map(_ ==> List(
//        (1, 10),
//        (2, 20)
//      ))
//
//      // 2 sub-entities
//      _ <- B.i.a1.query.get.map(_ ==> List(10, 20))
//
//      _ <- A(e1).delete.i.transact
//
//      // 1 entity with 1 owned sub-entity left
//      _ <- A.i.OwnB.i.query.get.map(_ ==> List((2, 20)))
//
//      // 2 sub-entities
//      _ <- B.i.query.get.map(_ ==> List(20))
//    } yield ()
//  }


  //    "unique" - unique { implicit conn =>
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


//    "validation" - validation { implicit conn =>
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
  //    "partitions" - partition { implicit conn =>
  //      import molecule.db.compliance.domains.dsl.Groups._
  //      for {
  //
  //        _ <- lit_Book.title.Reviewers.name.Professions.*(gen_Profession.name)
  //          .insert("book", "Jan", List("Musician")).transact
  //
  //      } yield ()
  //    }
}
