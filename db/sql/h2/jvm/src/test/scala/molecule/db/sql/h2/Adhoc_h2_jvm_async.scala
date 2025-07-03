package molecule.db.sql.h2

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
//      case List(a, b) <- Entity.int.insert(1, 2).transact.map(_.ids)
//      _ <- Entity.int(3).save.transact
//      _ <- Entity.int.a1.query.get.map(_ ==> List(1, 2, 3))
//      _ <- Entity(a).int(10).update.transact
//      _ <- Entity(b).delete.transact
//      _ <- Entity.int.a1.query.get.map(_ ==> List(3, 10))


      _ <- Ref.i.Entities.*(Entity.i.string_?).insert((1, List((1, Some(string1)), (2, None)))).transact

      _ <- Ref.i(1).Entities.*(Entity.i.a1.string_?).query.get.map(_ ==> List((1, List((1, Some(string1)), (2, None)))))

    } yield ()
  }

/*
-----  2
-----  List((1,Some(a)), (2,None))
-----  List((1,Some(a)), (2,None))

 */

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

  //  "validation" - validation { implicit conn =>
  //    import molecule.db.compliance.domains.dsl.Validation._
  //
  //    for {
  //
  //      //        _ <- Type.int(1).save.transact
  //      //          .recover {
  //      //            case ValidationErrors(errorMap) =>
  //      //              errorMap.head._2.head ==>
  //      //                s"""Type.int with value `1` doesn't satisfy validation:
  //      //                   |_ > 2
  //      //                   |""".stripMargin
  //      //          }
  //
  //      //        _ <- Type.int(3).save.transact
  //      //        _ <- transact(Type.int(3).save, Type.int(5).save)
  //      //                _ <- transact(Type.int(1).save, Type.int.insert(5, 6)).recover {
  //      _ <- transact(
  //        Type.int.insert(5, 6),
  //        Type.int(1).save
  //      ).recover {
  //        case ValidationErrors(errorMap) =>
  //          errorMap.head._2.head ==>
  //            s"""Type.int with value `1` doesn't satisfy validation:
  //               |_ > 2
  //               |""".stripMargin
  //      }
  //
  //      _ <- unitOfWork {
  //        for {
  //          last <- Type.int.insert(5, 6).transact
  //          last <- Type.int(1).save.transact
  //        } yield last
  //      }.recover {
  //        case ValidationErrors(errorMap) =>
  //          errorMap.head._2.head ==>
  //            s"""Type.int with value `1` doesn't satisfy validation:
  //               |_ > 2
  //               |""".stripMargin
  //      }
  //
  //      // No data has been transacted
  //      _ <- Type.int.query.get.map(_ ==> List())
  //      //        _ <- Type.int.query.get.map(_ ==> List(3, 5))
  //
  //    } yield ()
  //  }
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
