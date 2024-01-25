package molecule.sql.postgres

import molecule.base.error.{ExecutionError, ModelError}
import molecule.core.util.Executor._
import molecule.sql.postgres.async._
import molecule.sql.postgres.setup.TestSuite_postgres
import utest._
import scala.collection.immutable.List
import scala.language.implicitConversions

object AdhocJVM_postgres extends TestSuite_postgres {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      val a = (1, Some(Set(int1, int2)))
      val b = (2, Some(Set(int2, int3, int4)))
      val c = (3, None)
      for {
        _ <- Ns.i.ints_?.insert(a, b, c).transact

//        // Sets without one or more values matching
//
//        // "Doesn't have this value"
//        _ <- Ns.i.a1.ints_?.hasNo(Some(int0)).query.get.map(_ ==> List(a, b))
//        _ <- Ns.i.a1.ints_?.hasNo(Some(int1)).query.get.map(_ ==> List(b))
//        _ <- Ns.i.a1.ints_?.hasNo(Some(int2)).query.get.map(_ ==> List())
//        _ <- Ns.i.a1.ints_?.hasNo(Some(int3)).query.get.map(_ ==> List(a))
//        _ <- Ns.i.a1.ints_?.hasNo(Some(int4)).query.get.map(_ ==> List(a))
//        _ <- Ns.i.a1.ints_?.hasNo(Some(int5)).query.get.map(_ ==> List(a, b))
//        // Same as
//        _ <- Ns.i.a1.ints_?.hasNo(Some(Seq(int0))).query.get.map(_ ==> List(a, b))
//        _ <- Ns.i.a1.ints_?.hasNo(Some(Seq(int1))).query.get.map(_ ==> List(b))
//        _ <- Ns.i.a1.ints_?.hasNo(Some(Seq(int2))).query.get.map(_ ==> List())
//        _ <- Ns.i.a1.ints_?.hasNo(Some(Seq(int3))).query.get.map(_ ==> List(a))
//        _ <- Ns.i.a1.ints_?.hasNo(Some(Seq(int4))).query.get.map(_ ==> List(a))
//        _ <- Ns.i.a1.ints_?.hasNo(Some(Seq(int5))).query.get.map(_ ==> List(a, b))
//
//
//        // OR semantics when multiple values
//
//        // "Not (has this OR that)"
//        _ <- Ns.i.a1.ints_?.hasNo(Some(Seq(int1, int2))).query.get.map(_ ==> List())
//        _ <- Ns.i.a1.ints_?.hasNo(Some(Seq(int1, int3))).query.get.map(_ ==> List())
//        _ <- Ns.i.a1.ints_?.hasNo(Some(Seq(int1, int4))).query.get.map(_ ==> List())
//        _ <- Ns.i.a1.ints_?.hasNo(Some(Seq(int1, int5))).query.get.map(_ ==> List(b))
//
//
//        // AND semantics when multiple values in a _Set_
//
//        // "Not (has this AND that)"
//        _ <- Ns.i.a1.ints_?.hasNo(Some(Set(int1))).query.get.map(_ ==> List(b))
//        _ <- Ns.i.a1.ints_?.hasNo(Some(Set(int1, int2))).query.get.map(_ ==> List(b))
//        _ <- Ns.i.a1.ints_?.hasNo(Some(Set(int1, int2, int3))).query.get.map(_ ==> List(a, b))
//        _ <- Ns.i.a1.ints_?.hasNo(Some(Set(int2))).query.get.map(_ ==> List())
//        _ <- Ns.i.a1.ints_?.hasNo(Some(Set(int2, int3))).query.get.map(_ ==> List(a))
//        _ <- Ns.i.a1.ints_?.hasNo(Some(Set(int2, int3, int4))).query.get.map(_ ==> List(a))
//        // Same as
//        _ <- Ns.i.a1.ints_?.hasNo(Some(Seq(Set(int1)))).query.get.map(_ ==> List(b))
//        _ <- Ns.i.a1.ints_?.hasNo(Some(Seq(Set(int1, int2)))).query.get.map(_ ==> List(b))
//        _ <- Ns.i.a1.ints_?.hasNo(Some(Seq(Set(int1, int2, int3)))).query.get.map(_ ==> List(a, b))
//        _ <- Ns.i.a1.ints_?.hasNo(Some(Seq(Set(int2)))).query.get.map(_ ==> List())
//        _ <- Ns.i.a1.ints_?.hasNo(Some(Seq(Set(int2, int3)))).query.get.map(_ ==> List(a))
//        _ <- Ns.i.a1.ints_?.hasNo(Some(Seq(Set(int2, int3, int4)))).query.get.map(_ ==> List(a))
//
//
//        // AND/OR semantics with multiple Sets
//
//        // "Not ((has this AND that) OR (has this AND that))"
//        _ <- Ns.i.a1.ints_?.hasNo(Some(Seq(Set(int1, int2), Set(int0)))).query.get.map(_ ==> List(b))
//        _ <- Ns.i.a1.ints_?.hasNo(Some(Seq(Set(int1, int2), Set(int0, int3)))).query.get.map(_ ==> List(b))
//        _ <- Ns.i.a1.ints_?.hasNo(Some(Seq(Set(int1, int2), Set(int2, int3)))).query.get.map(_ ==> List())
//        _ <- Ns.i.a1.ints_?.hasNo(Some(Seq(Set(int1, int2), Set(int2, int3, int4)))).query.get.map(_ ==> List())


        // Negating empty Seqs/Sets has no effect
        _ <- Ns.i.a1.ints.query.i.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.ints_?.hasNo(Some(Seq.empty[Int])).query.i.get.map(_ ==> List(a, b))
//        _ <- Ns.i.a1.ints_?.hasNo(Some(Set.empty[Int])).query.get.map(_ ==> List(a, b))
//        _ <- Ns.i.a1.ints_?.hasNo(Some(Seq.empty[Set[Int]])).query.get.map(_ ==> List(a, b))
//        _ <- Ns.i.a1.ints_?.hasNo(Some(Seq(Set.empty[Int]))).query.get.map(_ ==> List(a, b))
//
//
//        // Negating None returns all asserted
//        _ <- Ns.i.a1.ints_?.hasNo(Option.empty[Int]).query.get.map(_ ==> List(a, b))
//        _ <- Ns.i.a1.ints_?.hasNo(Option.empty[Seq[Int]]).query.get.map(_ ==> List(a, b))
//        _ <- Ns.i.a1.ints_?.hasNo(Option.empty[Set[Int]]).query.get.map(_ ==> List(a, b))
//        _ <- Ns.i.a1.ints_?.hasNo(Option.empty[Seq[Set[Int]]]).query.get.map(_ ==> List(a, b))







//        _ <- Ns.int.insert(1).transact
//        _ <- Ns.int.query.get.map(_ ==> List(1))

        //        _ <- rawQuery(
        //          """SELECT DISTINCT
        //            |  MEDIAN(DISTINCT Ns.int)
        //            |FROM Ns
        //            |WHERE
        //            |  Ns.int IS NOT NULL;
        //            |""".stripMargin)


      } yield ()
    }

    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {
        //            id <- A.i(1).B.i(2).C.i(3).save.transact.map(_.id)
        //            _ <- A.i.B.i.C.i.query.get.map(_ ==> List((1, 2, 3)))



        _ <- A.i.ii.insert(
          (1, Set(1, 2)),
          (2, Set(2)),
          (2, Set(7)),
          (3, Set(3)),
          (4, Set())
        ).transact


        // Mandatory ========================================

        _ <- A.i.a1.ii.query.get.map(_ ==> List(
          (1, Set(1, 2)),
          (2, Set(2, 7)), // 2 rows coalesced
          (3, Set(3)),
        ))


        _ <- A.i.a1.ii(Set(1)).query.get.map(_ ==> List(
          // Set(1, 2) != Set(1)
        ))
        _ <- A.i.a1.ii(Set(1, 2)).query.get.map(_ ==> List(
          (1, Set(1, 2)),
        ))
        _ <- A.i.a1.ii(Set.empty[Int]).query.get.map(_ ==> Nil)


        _ <- A.i.a1.ii.not(Set(1)).query.get.map(_ ==> List(
          (1, Set(1, 2)),
          (2, Set(2, 7)),
          (3, Set(3)),
        ))
        _ <- A.i.a1.ii.not(Set(1, 2)).query.get.map(_ ==> List(
          (2, Set(2, 7)),
          (3, Set(3)),
        ))
        _ <- A.i.a1.ii.not(Set.empty[Int]).query.get.map(_ ==> List(
          (1, Set(1, 2)),
          (2, Set(2, 7)),
          (3, Set(3)),
        ))


        _ <- A.i.a1.ii.has(1).query.get.map(_ ==> List(
          (1, Set(1, 2)),
        ))
        _ <- A.i.a1.ii.has(2).query.get.map(_ ==> List(
          (1, Set(1, 2)),
          (2, Set(2)),
        ))


        _ <- A.i.a1.ii.has(2, 1).query.get.map(_ ==> List(
          (1, Set(1, 2)),
          (2, Set(2)),
        ))
        _ <- A.i.a1.ii.has(2, 7).query.get.map(_ ==> List(
          (1, Set(1, 2)),
          (2, Set(2, 7)),
        ))
        _ <- A.i.a1.ii.has(2, 3).query.get.map(_ ==> List(
          (1, Set(1, 2)),
          (2, Set(2)),
          (3, Set(3)),
        ))

        _ <- A.i.a1.ii.hasNo(1).query.get.map(_ ==> List(
          (2, Set(2, 7)),
          (3, Set(3)),
        ))
        _ <- A.i.a1.ii.hasNo(2).query.get.map(_ ==> List(
          (2, Set(7)),
          (3, Set(3)),
        ))
        _ <- A.i.a1.ii.hasNo(3).query.get.map(_ ==> List(
          (1, Set(1, 2)),
          (2, Set(2, 7))
        ))


        // tacit ========================================

        _ <- A.i.a1.ii_.query.get.map(_ ==> List(1, 2, 3))

        _ <- A.i.a1.ii_(Set(1)).query.get.map(_ ==> Nil)
        _ <- A.i.a1.ii_(Set(1, 2)).query.get.map(_ ==> List(1))
        _ <- A.i.a1.ii_(Set.empty[Int]).query.get.map(_ ==> Nil)


        _ <- A.i.a1.ii_.not(Set(1)).query.get.map(_ ==> List(1, 2, 3))
        _ <- A.i.a1.ii_.not(Set(1, 2)).query.get.map(_ ==> List(2, 3))
        _ <- A.i.a1.ii_.not(Set.empty[Int]).query.get.map(_ ==> List(1, 2, 3))


        _ <- A.i.a1.ii_.has(1).query.get.map(_ ==> List(1))
        _ <- A.i.a1.ii_.has(2).query.get.map(_ ==> List(1, 2))
        _ <- A.i.a1.ii_.has(2, 7).query.get.map(_ ==> List(1, 2))
        _ <- A.i.a1.ii_.has(2, 3).query.get.map(_ ==> List(1, 2, 3))

        _ <- A.i.a1.ii_.hasNo(1).query.get.map(_ ==> List(2, 3))
        _ <- A.i.a1.ii_.hasNo(2).query.get.map(_ ==> List(2, 3))
        _ <- A.i.a1.ii_.hasNo(3).query.get.map(_ ==> List(1, 2))




        // optional ========================================

        allAssertedOptional = List(
          (1, Some(Set(1, 2))),
          (2, Some(Set(2, 7))), // 2 rows coalesced
          (3, Some(Set(3))),
        )


        // All
        _ <- A.i.a1.ii_?.query.get.map(_ ==> List(
          (1, Some(Set(1, 2))),
          (2, Some(Set(2, 7))),
          (3, Some(Set(3))),
          (4, None)
        ))



        _ <- A.i.a1.ii_?(Some(Set(1))).query.get.map(_ ==> List(
          // Set(1, 2) != Set(1)
        ))
        _ <- A.i.a1.ii_?(Some(Set(1, 2))).query.get.map(_ ==> List(
          (1, Some(Set(1, 2))),
        ))

        // None matches non-asserted values
        _ <- A.i.a1.ii_?(Option.empty[Set[Int]]).query.get.map(_ ==> List((4, None)))
        _ <- A.i.a1.ii_?(Option.empty[Seq[Set[Int]]]).query.get.map(_ ==> List((4, None)))

        // Empty Sets are ignored (use None to match non-asserted card-set attributes)
        _ <- A.i.a1.ii_?(Some(Set.empty[Int])).query.get.map(_ ==> Nil)
        _ <- A.i.a1.ii_?(Some(Seq.empty[Set[Int]])).query.get.map(_ ==> Nil)
        _ <- A.i.a1.ii_?(Some(Seq(Set.empty[Int]))).query.get.map(_ ==> Nil)






        _ <- A.i.a1.ii_?.not(Some(Set(1))).query.get.map(_ ==> List(
          (1, Some(Set(1, 2))),
          (2, Some(Set(2, 7))),
          (3, Some(Set(3))),
        ))
        _ <- A.i.a1.ii_?.not(Some(Set(1, 2))).query.get.map(_ ==> List(
          (2, Some(Set(2, 7))),
          (3, Some(Set(3))),
        ))


        // Negating None matches all asserted values
        _ <- A.i.a1.ii_?.not(Option.empty[Set[Int]]).query.get.map(_ ==> allAssertedOptional)
        _ <- A.i.a1.ii_?.not(Option.empty[Seq[Set[Int]]]).query.get.map(_ ==> allAssertedOptional)

        // Negating empty Sets match nothing
        _ <- A.i.a1.ii_?.not(Some(Set.empty[Int])).query.get.map(_ ==> allAssertedOptional)
        _ <- A.i.a1.ii_?.not(Some(Seq.empty[Set[Int]])).query.get.map(_ ==> allAssertedOptional)
        _ <- A.i.a1.ii_?.not(Some(Seq(Set.empty[Int]))).query.get.map(_ ==> allAssertedOptional)




        _ <- A.i.a1.ii_?.has(Some(1)).query.get.map(_ ==> List(
          (1, Some(Set(1, 2))),
        ))
        _ <- A.i.a1.ii_?.has(Some(2)).query.get.map(_ ==> List(
          (1, Some(Set(1, 2))),
          (2, Some(Set(2))),
        ))
        // same as
        _ <- A.i.a1.ii_?.has(Some(Seq(2))).query.get.map(_ ==> List(
          (1, Some(Set(1, 2))),
          (2, Some(Set(2))),
        ))
        // has 2 or 3
        _ <- A.i.a1.ii_?.has(Some(Seq(2, 3))).query.get.map(_ ==> List(
          (1, Some(Set(1, 2))),
          (2, Some(Set(2))),
          (3, Some(Set(3))),
        ))
        // has 2 and 3
        _ <- A.i.a1.ii_?.has(Some(Set(2, 3))).query.get.map(_ ==> List(

        ))
        // has 1 and 2
        _ <- A.i.a1.ii_?.has(Some(Set(1, 2))).query.get.map(_ ==> List(
          (1, Some(Set(1, 2))),
        ))

        // None matches non-asserted values
        _ <- A.i.a1.ii_?.has(Option.empty[Set[Int]]).query.get.map(_ ==> List((4, None)))
        _ <- A.i.a1.ii_?.has(Option.empty[Seq[Set[Int]]]).query.get.map(_ ==> List((4, None)))

        // Empty Sets match nothing
        _ <- A.i.a1.ii_?.has(Some(Set.empty[Int])).query.get.map(_ ==> Nil)
        _ <- A.i.a1.ii_?.has(Some(Seq.empty[Set[Int]])).query.get.map(_ ==> Nil)
        _ <- A.i.a1.ii_?.has(Some(Seq(Set.empty[Int]))).query.get.map(_ ==> Nil)




        _ <- A.i.a1.ii_?.hasNo(Some(1)).query.get.map(_ ==> List(
          (2, Some(Set(2, 7))),
          (3, Some(Set(3))),
        ))
        // When 1 value in set, then the same as
        _ <- A.i.a1.ii_?.hasNo(Some(Set(1))).query.get.map(_ ==> List(
          (2, Some(Set(2, 7))),
          (3, Some(Set(3))),
        ))
        _ <- A.i.a1.ii_?.hasNo(Some(2)).query.get.map(_ ==> List(
          (2, Some(Set(7))),
          (3, Some(Set(3))),
        ))
        _ <- A.i.a1.ii_?.hasNo(Some(3)).query.get.map(_ ==> List(
          (1, Some(Set(1, 2))),
          (2, Some(Set(2, 7)))
        ))
        _ <- A.i.a1.ii_?.hasNo(Some(Set(1, 2))).query.get.map(_ ==> List(
          (2, Some(Set(2, 7))),
          (3, Some(Set(3))),
        ))
        _ <- A.i.a1.ii_?.hasNo(Some(Set(1, 3))).query.get.map(_ ==> List(
          (1, Some(Set(1, 2))),
          (2, Some(Set(2, 7))),
          (3, Some(Set(3))),
        ))

        // Negating None matches all asserted values
        _ <- A.i.a1.ii_?.hasNo(Option.empty[Set[Int]]).query.get.map(_ ==> allAssertedOptional)
        _ <- A.i.a1.ii_?.hasNo(Option.empty[Seq[Set[Int]]]).query.get.map(_ ==> allAssertedOptional)

        // Negating empty Sets match nothing
        _ <- A.i.a1.ii_?.hasNo(Some(Set.empty[Int])).query.get.map(_ ==> allAssertedOptional)
        _ <- A.i.a1.ii_?.hasNo(Some(Seq.empty[Set[Int]])).query.get.map(_ ==> allAssertedOptional)
        _ <- A.i.a1.ii_?.hasNo(Some(Seq(Set.empty[Int]))).query.get.map(_ ==> allAssertedOptional)


      } yield ()
    }


    //    "unique" - unique { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Uniques._
    //      for {
    //        _ <- Uniques.i(1).save.transact
    //
    //      } yield ()
    //    }
    //

    //    "validation" - validation { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Validation._
    //
    //      for {
    //        List(r1, r2) <- RefB.i.insert(2, 3).transact.map(_.ids)
    //
    //      } yield ()
    //    }
  }
}