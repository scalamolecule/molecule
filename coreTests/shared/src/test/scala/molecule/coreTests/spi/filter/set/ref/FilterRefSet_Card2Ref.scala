package molecule.coreTests.spi.filter.set.ref

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterRefSet_Card2Ref extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "mandatory" - refs { implicit conn =>
      for {
        _ <- A.i.Bb.iSet.insert(
          (1, Set(1, 2)),
          (2, Set(2)),
          (2, Set(7)),
          (3, Set(3)),
          (4, Set())
        ).transact

        // All
        _ <- A.i.a1.Bb.iSet.query.get.map(_ ==> List(
          (1, Set(1, 2)),
          (2, Set(2, 7)), // 2 rows coalesced
          (3, Set(3)),
        ))


        // equal/apply

        _ <- A.i.a1.Bb.iSet(Set(1)).query.get.map(_ ==> List(
          // Set(1, 2) != Set(1)
        ))
        _ <- A.i.a1.Bb.iSet(Set(1, 2)).query.get.map(_ ==> List(
          (1, Set(1, 2)),
        ))
        _ <- A.i.a1.Bb.iSet(Set.empty[Int]).query.get.map(_ ==> Nil)


        // not

        _ <- A.i.a1.Bb.iSet.not(Set(1)).query.get.map(_ ==> List(
          (1, Set(1, 2)),
          (2, Set(2, 7)),
          (3, Set(3)),
        ))
        _ <- A.i.a1.Bb.iSet.not(Set(2)).query.get.map(_ ==> List(
          (1, Set(1, 2)),
          (2, Set(7)),
          (3, Set(3)),
        ))
        _ <- A.i.a1.Bb.iSet.not(Set(1, 2)).query.get.map(_ ==> List(
          (2, Set(2, 7)),
          (3, Set(3)),
        ))
        _ <- A.i.a1.Bb.iSet.not(Set.empty[Int]).query.get.map(_ ==> List(
          (1, Set(1, 2)),
          (2, Set(2, 7)),
          (3, Set(3)),
        ))


        // has

        _ <- A.i.a1.Bb.iSet.has(1).query.get.map(_ ==> List(
          (1, Set(1, 2)),
        ))
        _ <- A.i.a1.Bb.iSet.has(2).query.get.map(_ ==> List(
          (1, Set(1, 2)),
          (2, Set(2)),
        ))

        _ <- A.i.a1.Bb.iSet.has(2, 1).query.get.map(_ ==> List(
          (1, Set(1, 2)),
          (2, Set(2)),
        ))
        _ <- A.i.a1.Bb.iSet.has(2, 7).query.get.map(_ ==> List(
          (1, Set(1, 2)),
          (2, Set(2, 7)),
        ))
        _ <- A.i.a1.Bb.iSet.has(2, 3).query.get.map(_ ==> List(
          (1, Set(1, 2)),
          (2, Set(2)),
          (3, Set(3)),
        ))


        // hasNo

        _ <- A.i.a1.Bb.iSet.hasNo(1).query.get.map(_ ==> List(
          (2, Set(2, 7)),
          (3, Set(3)),
        ))
        _ <- A.i.a1.Bb.iSet.hasNo(2).query.get.map(_ ==> List(
          (2, Set(7)),
          (3, Set(3)),
        ))
        _ <- A.i.a1.Bb.iSet.hasNo(3).query.get.map(_ ==> List(
          (1, Set(1, 2)),
          (2, Set(2, 7))
        ))
      } yield ()
    }


    "tacit" - refs { implicit conn =>
      for {
        _ <- A.i.Bb.iSet.insert(
          (1, Set(1, 2)),
          (2, Set(2)),
          (2, Set(7)),
          (3, Set(3)),
          (4, Set())
        ).transact

        // all
        _ <- A.i.a1.Bb.iSet_.query.get.map(_ ==> List(1, 2, 3))

        // equal/apply
        _ <- A.i.a1.Bb.iSet_(Set(1)).query.get.map(_ ==> Nil)
        _ <- A.i.a1.Bb.iSet_(Set(1, 2)).query.get.map(_ ==> List(1))
        _ <- A.i.a1.Bb.iSet_(Set.empty[Int]).query.get.map(_ ==> Nil)

        // not
        _ <- A.i.a1.Bb.iSet_.not(Set(1)).query.get.map(_ ==> List(1, 2, 3))
        _ <- A.i.a1.Bb.iSet_.not(Set(1, 2)).query.get.map(_ ==> List(2, 3))
        _ <- A.i.a1.Bb.iSet_.not(Set.empty[Int]).query.get.map(_ ==> List(1, 2, 3))

        // has
        _ <- A.i.a1.Bb.iSet_.has(1).query.get.map(_ ==> List(1))
        _ <- A.i.a1.Bb.iSet_.has(2).query.get.map(_ ==> List(1, 2))
        _ <- A.i.a1.Bb.iSet_.has(2, 7).query.get.map(_ ==> List(1, 2))
        _ <- A.i.a1.Bb.iSet_.has(2, 3).query.get.map(_ ==> List(1, 2, 3))

        // hasNo
        _ <- A.i.a1.Bb.iSet_.hasNo(1).query.get.map(_ ==> List(2, 3))
        _ <- A.i.a1.Bb.iSet_.hasNo(2).query.get.map(_ ==> List(2, 3))
        _ <- A.i.a1.Bb.iSet_.hasNo(3).query.get.map(_ ==> List(1, 2))

        // no value - match non-asserted attribute (null)
        // Nothing returned since there's no relationship to B
        _ <- A.i.a1.Bb.iSet_().query.get.map(_ ==> Nil)
      } yield ()
    }


    "optional" - refs { implicit conn =>
      val allAssertedOptional = List(
        (1, Some(Set(1, 2))),
        (2, Some(Set(2, 7))), // 2 rows coalesced
        (3, Some(Set(3))),
      )
      for {
        _ <- A.i.Bb.i_?.iSet.insert(
          (1, Some(1), Set(1, 2)),
          (2, Some(1), Set(2)),
          (2, Some(1), Set(7)),
          (3, Some(1), Set(3)),
          (4, Some(1), Set()), // relationship created since 1 is saved in B namespace
          (5, None, Set()) //     relationship not created
        ).transact

        // All
        _ <- A.i.a1.Bb.iSet_?.query.get.map(_ ==> List(
          (1, Some(Set(1, 2))),
          (2, Some(Set(2, 7))),
          (3, Some(Set(3))),
          (4, None), // retrieved since there's a relationship to B (but no iSet value)
          // (5, None) // not retrieved since there's no relationship to B
        ))


        // equal/apply

        _ <- A.i.a1.Bb.iSet_?(Some(Set(1))).query.get.map(_ ==> List(
          // Set(1, 2) != Set(1)
        ))
        _ <- A.i.a1.Bb.iSet_?(Some(Set(1, 2))).query.get.map(_ ==> List(
          (1, Some(Set(1, 2))),
        ))

        // None matches non-asserted values
        _ <- A.i.a1.Bb.iSet_?(Option.empty[Set[Int]]).query.get.map(_ ==> List((4, None)))
        _ <- A.i.a1.Bb.iSet_?(Option.empty[Seq[Set[Int]]]).query.get.map(_ ==> List((4, None)))

        // Empty Sets are ignored (use None to match non-asserted card-set attributes)
        _ <- A.i.a1.Bb.iSet_?(Some(Set.empty[Int])).query.get.map(_ ==> Nil)
        _ <- A.i.a1.Bb.iSet_?(Some(Seq.empty[Set[Int]])).query.get.map(_ ==> Nil)
        _ <- A.i.a1.Bb.iSet_?(Some(Seq(Set.empty[Int]))).query.get.map(_ ==> Nil)


        // not

        _ <- A.i.a1.Bb.iSet_?.not(Some(Set(1))).query.get.map(_ ==> List(
          (1, Some(Set(1, 2))),
          (2, Some(Set(2, 7))),
          (3, Some(Set(3))),
        ))
        _ <- A.i.a1.Bb.iSet_?.not(Some(Set(1, 2))).query.get.map(_ ==> List(
          (2, Some(Set(2, 7))),
          (3, Some(Set(3))),
        ))

        // Negating None matches all asserted values
        _ <- A.i.a1.Bb.iSet_?.not(Option.empty[Set[Int]]).query.get.map(_ ==> allAssertedOptional)
        _ <- A.i.a1.Bb.iSet_?.not(Option.empty[Seq[Set[Int]]]).query.get.map(_ ==> allAssertedOptional)

        // Negating empty Sets match nothing
        _ <- A.i.a1.Bb.iSet_?.not(Some(Set.empty[Int])).query.get.map(_ ==> allAssertedOptional)
        _ <- A.i.a1.Bb.iSet_?.not(Some(Seq.empty[Set[Int]])).query.get.map(_ ==> allAssertedOptional)
        _ <- A.i.a1.Bb.iSet_?.not(Some(Seq(Set.empty[Int]))).query.get.map(_ ==> allAssertedOptional)


        // has

        _ <- A.i.a1.Bb.iSet_?.has(Some(1)).query.get.map(_ ==> List(
          (1, Some(Set(1, 2))),
        ))
        _ <- A.i.a1.Bb.iSet_?.has(Some(2)).query.get.map(_ ==> List(
          (1, Some(Set(1, 2))),
          (2, Some(Set(2))),
        ))
        // same as
        _ <- A.i.a1.Bb.iSet_?.has(Some(Seq(2))).query.get.map(_ ==> List(
          (1, Some(Set(1, 2))),
          (2, Some(Set(2))),
        ))
        // has 2 or 3
        _ <- A.i.a1.Bb.iSet_?.has(Some(Seq(2, 3))).query.get.map(_ ==> List(
          (1, Some(Set(1, 2))),
          (2, Some(Set(2))),
          (3, Some(Set(3))),
        ))

        // None matches non-asserted values
        _ <- A.i.a1.Bb.iSet_?.has(Option.empty[Seq[Int]]).query.get.map(_ ==> List((4, None)))

        // Empty Sets match nothing
        _ <- A.i.a1.Bb.iSet_?.has(Some(Seq.empty[Int])).query.get.map(_ ==> Nil)


        // hasNo

        _ <- A.i.a1.Bb.iSet_?.hasNo(Some(1)).query.get.map(_ ==> List(
          (2, Some(Set(2, 7))),
          (3, Some(Set(3))),
        ))
        _ <- A.i.a1.Bb.iSet_?.hasNo(Some(2)).query.get.map(_ ==> List(
          (2, Some(Set(7))),
          (3, Some(Set(3))),
        ))
        _ <- A.i.a1.Bb.iSet_?.hasNo(Some(3)).query.get.map(_ ==> List(
          (1, Some(Set(1, 2))),
          (2, Some(Set(2, 7)))
        ))

        // Negating None matches all asserted values
        _ <- A.i.a1.Bb.iSet_?.hasNo(Option.empty[Seq[Int]]).query.get.map(_ ==> allAssertedOptional)

        // Negating empty Sets match nothing
        _ <- A.i.a1.Bb.iSet_?.hasNo(Some(Seq.empty[Int])).query.get.map(_ ==> allAssertedOptional)
      } yield ()
    }
  }
}
