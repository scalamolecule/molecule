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

        _ <- rawQuery(
          """SELECT DISTINCT
            |  Ns.i,
            |  ARRAY_AGG(Ns.ints)
            |FROM Ns
            |WHERE
            |  Ns.i IS NOT NULL
            |GROUP BY Ns.i;
            |""".stripMargin, true)

        _ <- rawQuery(
          """SELECT DISTINCT
            |  Ns.i,
            |  ARRAY_AGG(Ns.ints)
            |FROM Ns
            |WHERE
            |  Ns.i    IS NOT NULL AND
            |  Ns.ints IS NOT NULL
            |GROUP BY Ns.i
            |HAVING COUNT(*) > 0;
            |""".stripMargin, true)

        _ <- Ns.i.ints.query.i.get.map(_.toSet ==> Set( // (since we can't sort by Sets)
          (1, Set(2)),
          (2, Set(3)),
        ))

        _ <- Ns.i.ints_?.query.i.get.map(_.toSet ==> Set( // (since we can't sort by Sets)
          (1, None),
          (1, Some(Set(2))),
          (2, Some(Set(3))),
        ))


      } yield ()
    }

    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {

        _ <- A.i.B.ii.insert((1, Set.empty[Int])).transact

        // A.i was inserted
        _ <- A.i.query.get.map(_ ==> List(1))

        // Relationship to B was not created since no value of B was present
        _ <- A.i_.b.query.get.map(_.size ==> 0)
        _ <- A.i.B.ii_?.query.get.map(_ ==> Nil)
        _ <- A.i.B.ii.query.get.map(_ ==> Nil)

        //        _ <- rawQuery(
        //          """SELECT DISTINCT
        //            |  A.id,
        //            |  A.i,
        //            |  B.i,
        //            |  ARRAY_AGG(C.ii)
        //            |FROM A
        //            |  LEFT JOIN A_bb_B ON A.id        = A_bb_B.A_id
        //            |  LEFT JOIN B      ON A_bb_B.B_id = B.id
        //            |  LEFT JOIN C      ON B.c         = C.id
        //            |WHERE
        //            |  A.i IS NOT NULL
        //            |GROUP BY A.i, B.i
        //            |HAVING COUNT(*) > 0;
        //            |""".stripMargin, true)
        //
        //
        //        _ <- rawQuery(
        //          """SELECT DISTINCT
        //            |  A.id,
        //            |  ARRAY_AGG(C.ii)
        //            |FROM A
        //            |  LEFT JOIN A_bb_B ON A.id        = A_bb_B.A_id
        //            |  LEFT JOIN B      ON A_bb_B.B_id = B.id
        //            |  LEFT JOIN C      ON B.c         = C.id
        //            |HAVING COUNT(*) > 0
        //            |""".stripMargin, true)

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
