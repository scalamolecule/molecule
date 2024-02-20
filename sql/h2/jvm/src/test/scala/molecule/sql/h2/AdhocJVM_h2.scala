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
      for {

        //        _ <- A.s.i.B.i._A.OwnB.i.insert(
        //          ("a", 1, 1, 0),
        //          ("b", 1, 0, 1),
        //          ("c", 0, 1, 1),
        //        ).transact
        //
        //        // Unambiguous when pointing backwards from branches
        //
        //        _ <- A.s.i_
        //          .B.i_(A.i_)._A // point back to A from A.B branch
        //          .OwnB.i_
        //          .query.i.get.map(_ ==> List("a"))
        /*
SELECT DISTINCT
  A.s
FROM A
  INNER JOIN B           ON A.b = B.id
  INNER JOIN B AS B_ownB ON A.ownB = B_ownB.id
WHERE
  B.i = A.i AND
  A.s IS NOT NULL AND
  A.i IS NOT NULL AND
  B.i IS NOT NULL;
         */

        //        _ <- A.s.i_
        //          .B.i_._A
        //          .OwnB.i_(A.i_) // point back to A from A.OwnB branch
        //          .query.get.map(_ ==> List("b"))



        _ <- A.s.i
          .B.i._A
          .OwnB.i
          .insert(
            ("a", 1, 1, 0),
            ("b", 1, 0, 1),
          ).transact

//        _ <- rawQuery(
//          """SELECT DISTINCT
//            |  A.s
//            |FROM A
//            |  INNER JOIN B           ON A.b    = B.id
//            |  INNER JOIN B AS B_ownB ON A.ownB = B_ownB.id
//            |WHERE
//            |  A.i      = B_ownB.i AND
//            |  A.s      IS NOT NULL AND
//            |  A.i      IS NOT NULL AND
//            |  B.i      IS NOT NULL AND
//            |  B_ownB.i IS NOT NULL;
//            |""".stripMargin, true)

        //        _ <- rawQuery(
        //          """SELECT DISTINCT
        //            |  A.s
        //            |FROM A
        //            |  INNER JOIN B AS B_b ON A.b    = B_b.id
        //            |  INNER JOIN B        ON A.ownB = B.id
        //            |WHERE
        //            |  A.i      = B_b.i AND
        //            |  A.s      IS NOT NULL AND
        //            |  A.i      IS NOT NULL AND
        //            |  B.i      IS NOT NULL AND
        //            |  B.i IS NOT NULL;
        //            |""".stripMargin, true)



        _ <- A.s.i_(A.OwnB.i_)
          .B.i_._A
          .OwnB.i_
          .query.i.get.map(_ ==> List("b"))

        //        _ <- A.s.i_(A.OwnB.i_)
        //          .OwnB.i_._A
        //          .B.i_
        //          .query.i.get.map(_ ==> List("b"))
        //
        //
        //        _ <- A.s.i_(A.B.i_)
        //          .B.i_._A
        //          .OwnB.i_
        //          .query.i.get.map(_ ==> List("a"))

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
