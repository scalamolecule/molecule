package molecule.sql.h2

import molecule.base.error.ModelError
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types.Ns
import molecule.sql.h2.async._
import molecule.sql.h2.setup.TestSuite_h2
import utest._
import scala.collection.immutable.List
import scala.concurrent.Future
import scala.language.implicitConversions

object AdhocJVM_h2 extends TestSuite_h2 {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {

        List(a, b, c) <- Ns.i.ints_?.insert(
          (1, None),
          (1, Some(Set(2))),
          (2, Some(Set(3))),
        ).transact.map(_.ids)


        // Update all entities where non-unique attribute i is 1
        _ <- Ns.i_(1).ints(Set(4)).update.transact

        // Only matching entities with previous values updated
        _ <- Ns.id.a1.i.ints_?.query.get.map(_ ==> List(
          (a, 1, None), // not updated since there were no previous value
          (b, 1, Some(Set(4))), // 2 updated to 4
          (c, 2, Some(Set(3))),
        ))

        // Upsert all entities where non-unique attribute i is 1
        _ <- Ns.i_(1).ints(Set(5)).upsert.transact

        // All matching entities updated
        _ <- Ns.id.a1.i.ints_?.query.get.map(_ ==> List(
          (a, 1, Some(Set(5))), // 5 inserted
          (b, 1, Some(Set(5))), // 4 updated to 5
          (c, 2, Some(Set(3))),
        ))


      } yield ()
    }

    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      val a = (1, Set(1, 2), Set(1, 2, 3), 3)
      val b = (2, Set(2, 3), Set(2, 3), 3)
      val c = (2, Set(4), Set(4), 4)

      val d = (2, Set(4), Set(3), 4)


      for {


//        List(_, a2, a3) <- A.i.ii.B.ii.i.insert(a, b, c).transact.map(_.ids)

//        _ <- rawQuery(
//          """SELECT DISTINCT
//            |  A.i,
//            |  ARRAY_AGG(B.ii)
//            |FROM A
//            |  INNER JOIN B ON A.b = B.id
//            |WHERE
//            |  A.ii = B.ii AND
//            |  A.i  IS NOT NULL AND
//            |  A.ii IS NOT NULL AND
//            |  B.ii IS NOT NULL
//            |GROUP BY A.i
//            |HAVING COUNT(*) > 0;
//            |  """.stripMargin
//        ).map(println(_))
//
//        _ = println("---------------")
//
//        _ <- rawQuery(
//          """SELECT DISTINCT
//            |  A.i,
//            |  B.ii
//            |FROM A
//            |  INNER JOIN B ON A.b = B.id
//            |WHERE
//            |  B.ii = A.ii AND
//            |  A.i  IS NOT NULL AND
//            |  A.ii IS NOT NULL AND
//            |  B.ii IS NOT NULL;
//            |  """.stripMargin
//        ).map(println(_))


        _ <- A.i.Bb.*(B.i.C.i._B.C1.s.D.i).insert(0, List((1, 2, "a", 3))).transact
        /*
SELECT DISTINCT
  A.id,
  B.i,
  C.i,
  C_c1.s,
  D.i
FROM A
  INNER JOIN A_bb_B         ON A.id = A_bb_B.A_id
  INNER JOIN B              ON A_bb_B.B_id = B.id
  INNER JOIN C              ON B.c = C.id
  INNER JOIN C      AS C_c1 ON B.c1 = C_c1.id
  INNER JOIN D              ON C_c1.d = D.id
WHERE
  A.i    IS NOT NULL AND
  B.i    IS NOT NULL AND
  C.i    IS NOT NULL AND
  C_c1.s IS NOT NULL AND
  D.i    IS NOT NULL;
         */
        _ <- A.i_.Bb.*(B.i.C.i._B.C1.s.D.i).query.get.map(_ ==> List(List((1, 2, "a", 3))))
//        _ <- A.i_.Bb.*?(B.i.C.i._B.C1.s.D.i).query.get.map(_ ==> List(List((1, 2, "a", 3))))
//
//        _ <- A.i_.Bb.*(B.i.C.i._B.C1.D.i).query.get.map(_ ==> List(List((1, 2, 3))))
//        _ <- A.i_.Bb.*?(B.i.C.i._B.C1.D.i).query.get.map(_ ==> List(List((1, 2, 3))))
//
//        _ <- A.i.Bb.*(B.i.C.i._B.C1.s.D.i.Ee.*(E.i.F.i)).insert(1, List((1, 2, "a", 3, List((4, 5))))).transact
//        _ <- A.i_(1).Bb.*(B.i.C.i._B.C1.s.D.i.Ee.*(E.i.F.i)).query.get.map(_ ==> List(List((1, 2, "a", 3, List((4, 5))))))
//        _ <- A.i_(1).Bb.*?(B.i.C.i._B.C1.s.D.i.Ee.*?(E.i.F.i)).query.get.map(_ ==> List(List((1, 2, "a", 3, List((4, 5))))))
//
//        _ <- A.i.Bb.*(B.i.C.i.Dd.*(D.i.E.i._D.E1.s.F.i)).insert(2, List((1, 2, List((3, 4, "a", 5))))).transact
//        _ <- A.i_(2).Bb.*(B.i.C.i.Dd.*(D.i.E.i._D.E1.s.F.i)).query.get.map(_ ==> List(List((1, 2, List((3, 4, "a", 5))))))
//        _ <- A.i_(2).Bb.*?(B.i.C.i.Dd.*?(D.i.E.i._D.E1.s.F.i)).query.get.map(_ ==> List(List((1, 2, List((3, 4, "a", 5))))))

      } yield ()
    }


    //    "unique" - unique { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Uniques._
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
    //      import molecule.coreTests.dataModels.core.dsl.Validation._
    //      for {
    //        List(r1, r2) <- RefB.i.insert(2, 3).transact.map(_.ids)
    //
    //
    //      } yield ()
    //    }
  }
}
