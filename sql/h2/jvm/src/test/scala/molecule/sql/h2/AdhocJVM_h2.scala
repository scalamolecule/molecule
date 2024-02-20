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

//        //        _ <- A.i.Bb.*(B.i.C.ii).insert(
//        _ <- A.i.Bb.*(B.i.ii).insert(
//          //          (0, Nil),
//          //          (1, List(
//          //            (1, Set.empty[Int])
//          //          )),
//          (2, List(
//            (1, Set.empty[Int]),
//            (2, Set(1)),
//            //            (3, Set(1, 2)),
//          )),
//        ).i.transact
//
//        //        _ <- A.i.Bb.*?(B.i.C.ii).query.i.get.map(_ ==> List(
//        _ <- A.i.Bb.*?(B.i.ii).query.i.get.map(_ ==> List(
//          //          (0, Nil),
//          //          (1, Nil),
//          (2, List(
//            (2, Set(1)),
//            //            (3, Set(1, 2)),
//          )),
//        ))



        //        _ <- A.i.Bb.*(B.i.C.ii).insert(
        _ <- A.i.Bb.*(B.ii.i).insert(
          //          (0, Nil),
          //          (1, List(
          //            (1, Set.empty[Int])
          //          )),
          (2, List(
            (Set.empty[Int], 3),
            (Set(5), 4),
            //            (3, Set(1, 2)),
          )),
        ).i.transact

        //        _ <- A.i.Bb.*?(B.i.C.ii).query.i.get.map(_ ==> List(
        _ <- A.i.Bb.*?(B.ii.i).query.i.get.map(_ ==> List(
          //          (0, Nil),
          //          (1, Nil),
          (2, List(
            (Set(5), 4),
            //            (3, Set(1, 2)),
          )),
        ))

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
