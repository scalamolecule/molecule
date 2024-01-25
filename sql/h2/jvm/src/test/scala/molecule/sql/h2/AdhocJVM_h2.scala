package molecule.sql.h2

import molecule.base.error.ModelError
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types.Ns
import molecule.sql.h2.async._
import molecule.sql.h2.setup.TestSuite_h2
import utest._
import scala.collection.immutable.List
import scala.language.implicitConversions

object AdhocJVM_h2 extends TestSuite_h2 {

  def getTriples: List[(String, Int, Int)] = (1 to 5).toList.map { int =>
    val s = ('a' + scala.util.Random.nextInt(3)).toChar.toString // "a" or "b"
    val i = scala.util.Random.nextInt(3) + 1 // 1 or 2
    (s, i, int)
  }

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      for {


        _ <- Ns.i.ints_?.insert(List(
          (0, None),
          (1, Some(Set(int1, int2))),
          (2, Some(Set(int2, int3, int4))),
        )).transact

        // Match non-asserted attribute (null)
        _ <- Ns.i.a1.ints_().query.i.get.map(_ ==> List(0))

        //        _ <- Ns.int.insert(1).i.transact
        //        _ <- Ns.int.query.i.get.map(_ ==> List(1))

        //        _ <- Ns.s.i.Refs.*(Ref.int).insert(
        //          ("a", 1, List(2, 3)),
        //          ("b", 4, List(4, 5)),
        //          ("c", 7, List(5, 6)),
        //          ("d", 9, Nil),
        //        ).transact
        //
        //        _ <- Ns.s.i(Ref.int_).Refs.*(Ref.int).query.i.get.map(_ ==> List(("b", 4, List(4))))

      } yield ()
    }

    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {

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




        //        _ <- rawQuery(
        //          """SELECT DISTINCT
        //            |  A.i,
        //            |  ARRAY_AGG(A.ii)
        //            |FROM A
        //            |WHERE
        //            |  A.i  IS NOT NULL AND
        //            |  A.ii IS NOT NULL
        //            |GROUP BY A.i
        //            |HAVING COUNT(*) > 0
        //            |ORDER BY A.i;
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
