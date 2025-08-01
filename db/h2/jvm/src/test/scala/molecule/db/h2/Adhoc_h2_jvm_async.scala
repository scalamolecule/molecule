package molecule.db.h2

import scala.concurrent.Future
import molecule.base.error.ModelError
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.util.Executor.*
import molecule.db.h2.async.*
import molecule.db.h2.setup.DbProviders_h2
import org.scalactic.Equality


class Adhoc_h2_jvm_async extends MUnit with DbProviders_h2 with TestUtils {

  import molecule.db.compliance.domains.dsl.Types.*

  "types" - types {
//    given Equality[Double] = tolerantDoubleEquality(toleranceDouble)
    for {
//      List(a, b) <- Entity.int.insert(1, 2).transact.map(_.ids)
//      _ <- Entity.int(3).save.transact
//      _ <- Entity.int.a1.query.get.map(_ ==> List(1, 2, 3))
//      _ <- Entity(a).int(10).update.transact
//      _ <- Entity(b).delete.transact
//      _ <- Entity.int.a1.query.get.map(_ ==> List(3, 10))

      _ <- Entity.i.int.insert(
        (1, int1),
        (1, int2),
        (2, int2)).transact
      _ <- Entity.i.long.insert((1, long1), (1, long2), (2, long2)).transact
      _ <- Entity.i.float.insert((1, float1), (1, float2), (2, float2)).transact
      _ <- Entity.i.double.insert((1, double1), (1, double2), (2, double2)).transact
      _ <- Entity.i.bigInt.insert((1, bigInt1), (1, bigInt2), (2, bigInt2)).transact
      _ <- Entity.i.bigDecimal.insert((1, bigDecimal1), (1, bigDecimal2), (2, bigDecimal2)).transact
      _ <- Entity.i.byte.insert((1, byte1), (1, byte2), (2, byte2)).transact
      _ <- Entity.i.short.insert((1, short1), (1, short2), (2, short2)).transact

      /*
SELECT DISTINCT
  Entity.i,
  ROUND(SUM(Entity.int_), 6) Entity_int__round(sum
FROM Entity
WHERE
  Entity.i IS NOT NULL
GROUP BY Entity.i
ORDER BY Entity_int__round(sum;

SELECT DISTINCT
  Entity.i,
  SUM(Entity.int_) Entity_int__sum
FROM Entity
WHERE
  Entity.i IS NOT NULL
GROUP BY Entity.i
ORDER BY Entity_int__sum;

       */
      //      _ <- Entity.i.int(sum).a1.query.i.get.map(_ ==> List((2, 2), (1, 3)))
      //      _ <- Entity.i.long(sum).a1.query.get.map(_ ==> List((2, 2), (1, 3)))
      //      _ <- Entity.i.float(sum).a1.query.get.map(_ ==> List((2, 2), (1, 3)))
      _ <- Entity.i.double(sum).a1.query.i.get.map(_ ==> List((2, 2), (1, 3)))
      //      _ <- Entity.i.bigInt(sum).a1.query.get.map(_ ==> List((2, 2), (1, 3)))
      //      _ <- Entity.i.bigDecimal(sum).a1.query.get.map(_ ==> List((2, 2), (1, 3)))
      //      _ <- Entity.i.byte(sum).a1.query.get.map(_ ==> List((2, 2), (1, 3)))
      //      _ <- Entity.i.short(sum).a1.query.get.map(_ ==> List((2, 2), (1, 3)))
      //
      //      _ <- Entity.i.int(sum).d1.query.get.map(_ ==> List((1, 3), (2, 2)))
      //      _ <- Entity.i.long(sum).d1.query.get.map(_ ==> List((1, 3), (2, 2)))
      ////      _ <- Entity.i.float(sum).d1.query.get.map(_ ==> List((1, 3), (2, 2)))
      //      _ <- Entity.i.double(sum).d1.query.get.map(_ ==> List((1, 3), (2, 2)))
      //      _ <- Entity.i.bigInt(sum).d1.query.get.map(_ ==> List((1, 3), (2, 2)))
      //      _ <- Entity.i.bigDecimal(sum).d1.query.get.map(_ ==> List((1, 3), (2, 2)))
      //      _ <- Entity.i.byte(sum).d1.query.get.map(_ ==> List((1, 3), (2, 2)))
      //      _ <- Entity.i.short(sum).d1.query.get.map(_ ==> List((1, 3), (2, 2)))
    } yield ()
  }


  //  "refs" - refs {
  //    import molecule.db.compliance.domains.dsl.Refs.*
  //
  //    for {
  //      _ <- A.?(A.i).B.i.insert(List(
  //        (None, 1),
  //        (Some(20), 2),
  //      )).transact
  //
  //      _ <- A.?(A.i).B.i.a1.query.i.get.map(_ ==> List(
  //        (None, 1),
  //        (Some(20), 2),
  //      ))
  //
  ////      _ <- A.i.B.i.query.get.map(_ ==> List(
  ////        (20, 2),
  ////      ))
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
