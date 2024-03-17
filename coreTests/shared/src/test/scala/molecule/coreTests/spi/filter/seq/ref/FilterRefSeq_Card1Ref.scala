package molecule.coreTests.spi.filter.seq.ref

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterRefSeq_Card1Ref extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "mandatory" - refs { implicit conn =>
      for {
        _ <- A.i.B.iSeq.insert(
          (1, List(1, 2, 2)),
          (2, List(2)),
          (2, List(7)),
          (3, List(3)),
          (4, List())
        ).transact

        // All
        _ <- A.i.a1.B.iSeq.query.get.map(_ ==> List(
          (1, List(1, 2, 2)),
          (2, List(2)),
          (2, List(7)),
          (3, List(3)),
        ))


        // equal/apply

        _ <- A.i.a1.B.iSeq(List(1)).query.get.map(_ ==> Nil)
        _ <- A.i.a1.B.iSeq(List(1, 2)).query.get.map(_ ==> Nil)
        _ <- A.i.a1.B.iSeq(List(1, 2, 2)).query.get.map(_ ==> List(
          (1, List(1, 2, 2)),
        ))
        _ <- A.i.a1.B.iSeq(List.empty[Int]).query.get.map(_ ==> Nil)


        // not

        _ <- A.i.a1.B.iSeq.not(List(1)).query.get.map(_ ==> List(
          (1, List(1, 2, 2)),
          (2, List(2)),
          (2, List(7)),
          (3, List(3)),
        ))
        _ <- A.i.a1.B.iSeq.not(List(2)).query.get.map(_ ==> List(
          (1, List(1, 2, 2)),
          (2, List(7)),
          (3, List(3)),
        ))
        _ <- A.i.a1.B.iSeq.not(List(1, 2)).query.get.map(_ ==> List(
          (1, List(1, 2, 2)),
          (2, List(2)),
          (2, List(7)),
          (3, List(3)),
        ))
        _ <- A.i.a1.B.iSeq.not(List(1, 2, 2)).query.get.map(_ ==> List(
          (2, List(2)),
          (2, List(7)),
          (3, List(3)),
        ))
        _ <- A.i.a1.B.iSeq.not(List.empty[Int]).query.get.map(_ ==> List(
          (1, List(1, 2, 2)),
          (2, List(2)),
          (2, List(7)),
          (3, List(3)),
        ))


        // has

        _ <- A.i.a1.B.iSeq.has(1).query.get.map(_ ==> List(
          (1, List(1, 2, 2)),
        ))
        _ <- A.i.a1.B.iSeq.has(2).query.get.map(_ ==> List(
          (1, List(1, 2, 2)),
          (2, List(2)),
        ))

        _ <- A.i.a1.B.iSeq.has(2, 1).query.get.map(_ ==> List(
          (1, List(1, 2, 2)),
          (2, List(2)),
        ))
        _ <- A.i.a1.B.iSeq.has(2, 7).query.get.map(_ ==> List(
          (1, List(1, 2, 2)),
          (2, List(2)),
          (2, List(7)),
        ))
        _ <- A.i.a1.B.iSeq.has(2, 3).query.get.map(_ ==> List(
          (1, List(1, 2, 2)),
          (2, List(2)),
          (3, List(3)),
        ))


        // hasNo

        _ <- A.i.a1.B.iSeq.hasNo(1).query.get.map(_ ==> List(
          (2, List(2)),
          (2, List(7)),
          (3, List(3)),
        ))
        _ <- A.i.a1.B.iSeq.hasNo(2).query.get.map(_ ==> List(
          (2, List(7)),
          (3, List(3)),
        ))
        _ <- A.i.a1.B.iSeq.hasNo(3).query.get.map(_ ==> List(
          (1, List(1, 2, 2)),
          (2, List(2)),
          (2, List(7)),
        ))
      } yield ()
    }


    "tacit" - refs { implicit conn =>
      for {
        _ <- A.i.B.iSeq.insert(
          (1, List(1, 2, 2)),
          (2, List(2)),
          (2, List(7)),
          (3, List(3)),
          (4, List())
        ).transact

        // all
        _ <- A.i.a1.B.iSeq_.query.get.map(_ ==> List(1, 2, 2, 3))

        // equal/apply
        _ <- A.i.a1.B.iSeq_(List(1)).query.get.map(_ ==> Nil)
        _ <- A.i.a1.B.iSeq_(List(1, 2)).query.get.map(_ ==> Nil)
        _ <- A.i.a1.B.iSeq_(List(1, 2, 2)).query.get.map(_ ==> List(1))
        _ <- A.i.a1.B.iSeq_(List.empty[Int]).query.get.map(_ ==> Nil)

        // not
        _ <- A.i.a1.B.iSeq_.not(List(1)).query.get.map(_ ==> List(1, 2, 2, 3))
        _ <- A.i.a1.B.iSeq_.not(List(1, 2)).query.get.map(_ ==> List(1, 2, 2, 3))
        _ <- A.i.a1.B.iSeq_.not(List(1, 2, 2)).query.get.map(_ ==> List(2, 2, 3))
        _ <- A.i.a1.B.iSeq_.not(List.empty[Int]).query.get.map(_ ==> List(1, 2, 2, 3))

        // has
        _ <- A.i.a1.B.iSeq_.has(1).query.get.map(_ ==> List(1))
        _ <- A.i.a1.B.iSeq_.has(2).query.get.map(_ ==> List(1, 2))
        _ <- A.i.a1.B.iSeq_.has(2, 7).query.get.map(_ ==> List(1, 2, 2))
        _ <- A.i.a1.B.iSeq_.has(2, 3).query.get.map(_ ==> List(1, 2, 3))

        // hasNo
        _ <- A.i.a1.B.iSeq_.hasNo(1).query.get.map(_ ==> List(2, 2, 3))
        _ <- A.i.a1.B.iSeq_.hasNo(2).query.get.map(_ ==> List(2, 3))
        _ <- A.i.a1.B.iSeq_.hasNo(3).query.get.map(_ ==> List(1, 2, 2))

        // no value - match non-asserted attribute (null)
        _ <- A.i.a1.B.iSeq_().query.get.map(_ ==> List(4))
      } yield ()
    }


    "optional" - refs { implicit conn =>
      val allAssertedOptional = List(
        (1, Some(List(1, 2, 2))),
        (2, Some(List(2))),
        (2, Some(List(7))),
        (3, Some(List(3))),
      )
      for {
        _ <- A.i.B.i_?.iSeq.insert(
          (1, Some(1), List(1, 2, 2)),
          (2, Some(1), List(2)),
          (2, Some(1), List(7)),
          (3, Some(1), List(3)),
          (4, Some(1), List()), // relationship created since 1 is saved in B namespace
          (5, None, List()) //     relationship not created
        ).transact

        // All
        _ <- A.i.a1.B.iSeq_?.query.get.map(_ ==> List(
          (1, Some(List(1, 2, 2))),
          (2, Some(List(2))),
          (2, Some(List(7))),
          (3, Some(List(3))),
          (4, None), // retrieved since there's a relationship to B (but no iSeq value)
          // (5, None) // not retrieved since there's no relationship to B
        ))


        // equal/apply

        _ <- A.i.a1.B.iSeq_?(Some(List(1))).query.get.map(_ ==> Nil)
        _ <- A.i.a1.B.iSeq_?(Some(List(1, 2))).query.get.map(_ ==> Nil)
        _ <- A.i.a1.B.iSeq_?(Some(List(1, 2, 2))).query.get.map(_ ==> List(
          (1, Some(List(1, 2, 2))),
        ))

        // None matches non-asserted values
        _ <- A.i.a1.B.iSeq_?(Option.empty[List[Int]]).query.get.map(_ ==> List((4, None)))
        _ <- A.i.a1.B.iSeq_?(Option.empty[List[List[Int]]]).query.get.map(_ ==> List((4, None)))

        // Empty Lists are ignored (use None to match non-asserted card-set attributes)
        _ <- A.i.a1.B.iSeq_?(Some(List.empty[Int])).query.get.map(_ ==> Nil)
        _ <- A.i.a1.B.iSeq_?(Some(List.empty[List[Int]])).query.get.map(_ ==> Nil)
        _ <- A.i.a1.B.iSeq_?(Some(List(List.empty[Int]))).query.get.map(_ ==> Nil)


        // not

        _ <- A.i.a1.B.iSeq_?.not(Some(List(1))).query.get.map(_ ==> List(
          (1, Some(List(1, 2, 2))),
          (2, Some(List(2))),
          (2, Some(List(7))),
          (3, Some(List(3))),
        ))
        _ <- A.i.a1.B.iSeq_?.not(Some(List(2))).query.get.map(_ ==> List(
          (1, Some(List(1, 2, 2))),
          (2, Some(List(7))),
          (3, Some(List(3))),
        ))
        _ <- A.i.a1.B.iSeq_?.not(Some(List(1, 2, 2))).query.get.map(_ ==> List(
          (2, Some(List(2))),
          (2, Some(List(7))),
          (3, Some(List(3))),
        ))

        // Negating None matches all asserted values
        _ <- A.i.a1.B.iSeq_?.not(Option.empty[List[Int]]).query.get.map(_ ==> allAssertedOptional)
        _ <- A.i.a1.B.iSeq_?.not(Option.empty[List[List[Int]]]).query.get.map(_ ==> allAssertedOptional)

        // Negating empty Lists match nothing
        _ <- A.i.a1.B.iSeq_?.not(Some(List.empty[Int])).query.get.map(_ ==> allAssertedOptional)
        _ <- A.i.a1.B.iSeq_?.not(Some(List.empty[List[Int]])).query.get.map(_ ==> allAssertedOptional)
        _ <- A.i.a1.B.iSeq_?.not(Some(List(List.empty[Int]))).query.get.map(_ ==> allAssertedOptional)


        // has

        _ <- A.i.a1.B.iSeq_?.has(Some(1)).query.get.map(_ ==> List(
          (1, Some(List(1, 2, 2))),
        ))
        _ <- A.i.a1.B.iSeq_?.has(Some(2)).query.get.map(_ ==> List(
          (1, Some(List(1, 2, 2))),
          (2, Some(List(2))),
        ))
        // same as
        _ <- A.i.a1.B.iSeq_?.has(Some(List(2))).query.get.map(_ ==> List(
          (1, Some(List(1, 2, 2))),
          (2, Some(List(2))),
        ))
        // has 2 or 3
        _ <- A.i.a1.B.iSeq_?.has(Some(List(2, 3))).query.get.map(_ ==> List(
          (1, Some(List(1, 2, 2))),
          (2, Some(List(2))),
          (3, Some(List(3))),
        ))

        // None matches non-asserted values
        _ <- A.i.a1.B.iSeq_?.has(Option.empty[List[Int]]).query.get.map(_ ==> List((4, None)))

        // Empty Lists match nothing
        _ <- A.i.a1.B.iSeq_?.has(Some(List.empty[Int])).query.get.map(_ ==> Nil)


        // hasNo

        _ <- A.i.a1.B.iSeq_?.hasNo(Some(1)).query.get.map(_ ==> List(
          (2, Some(List(2))),
          (2, Some(List(7))),
          (3, Some(List(3))),
        ))
        _ <- A.i.a1.B.iSeq_?.hasNo(Some(2)).query.get.map(_ ==> List(
          (2, Some(List(7))),
          (3, Some(List(3))),
        ))
        _ <- A.i.a1.B.iSeq_?.hasNo(Some(3)).query.get.map(_ ==> List(
          (1, Some(List(1, 2, 2))),
          (2, Some(List(2))),
          (2, Some(List(7))),
        ))

        // Negating None matches all asserted values
        _ <- A.i.a1.B.iSeq_?.hasNo(Option.empty[List[Int]]).query.get.map(_ ==> allAssertedOptional)

        // Negating empty Lists match nothing
        _ <- A.i.a1.B.iSeq_?.hasNo(Some(List.empty[Int])).query.get.map(_ ==> allAssertedOptional)
      } yield ()
    }
  }
}
