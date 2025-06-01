package molecule.db.sql.h2

import molecule.db.compliance.setup.{Test, TestUtils}
import molecule.db.core.util.Executor.*
import molecule.db.sql.h2.async.*
import molecule.db.sql.h2.setup.DbProviders_h2


class Adhoc_jvm_h2_async extends Test with DbProviders_h2 with TestUtils {

  "types" - types { implicit conn =>
    import molecule.db.compliance.domains.dsl.Types.*
    implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)


    for {
//      List(a, b) <- Entity.int.insert(1, 2).transact.map(_.ids)
//      _ <- Entity.int(3).save.transact
//      _ <- Entity.int.a1.query.get.map(_ ==> List(1, 2, 3))
//      _ <- Entity(a).int(10).update.transact
//      _ <- Entity(b).delete.transact
//      _ <- Entity.int.a1.query.get.map(_ ==> List(3, 10))


      _ <- Entity.int.insert(int1, int2, int3).transact


      //      eq = Entity.int(?).query
      //      _ <- eq(int1).get.map(_ ==> List(int1))
      //      _ <- eq(int2).get.map(_ ==> List(int2))
      //      _ <- eq(int3).get.map(_ ==> List(int3))
      //


      //      _ <- Entity.int.not(1).query.i.get.map(_ ==> List(2, 3))
      /*

========================================
QUERY:
AttrOneManInt("Entity", "int", Neq, Seq(1), None, None, Nil, Nil, None, None, List(0, 8))

[:find  ?b
 :where [?a :Entity/int ?b]
        [(!= ?b 1)]]
----------------------------------------
       */

      ne = Entity.int.not(?).query.i
      _ <- ne(int1).get.map(_ ==> List(int2, int3))
      //      _ <- ne(int2).get.map(_ ==> List(int1, int3))
      //      _ <- ne(int3).get.map(_ ==> List(int1, int2))
      //
      //      lt = Entity.int.<(?).query
      //      _ <- lt(int1).get.map(_ ==> List())
      //      _ <- lt(int2).get.map(_ ==> List(int1))
      //      _ <- lt(int3).get.map(_ ==> List(int1, int2))
      //
      //      le = Entity.int.<=(?).query
      //      _ <- le(int1).get.map(_ ==> List(int1))
      //      _ <- le(int2).get.map(_ ==> List(int1, int2))
      //      _ <- le(int3).get.map(_ ==> List(int1, int2, int3))
      //
      //      gt = Entity.int.>(?).query
      //      _ <- gt(int1).get.map(_ ==> List(int2, int3))
      //      _ <- gt(int2).get.map(_ ==> List(int3))
      //      _ <- gt(int3).get.map(_ ==> List())
      //
      //      ge = Entity.int.>=(?).query
      //      _ <- ge(int1).get.map(_ ==> List(int1, int2, int3))
      //      _ <- ge(int2).get.map(_ ==> List(int2, int3))
      //      _ <- ge(int3).get.map(_ ==> List(int3))

    } yield ()
  }


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
