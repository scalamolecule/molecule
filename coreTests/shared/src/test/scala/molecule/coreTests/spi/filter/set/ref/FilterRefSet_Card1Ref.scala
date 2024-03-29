package molecule.coreTests.spi.filter.set.ref

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterRefSet_Card1Ref extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "mandatory" - refs { implicit conn =>
      for {
        _ <- A.i.B.iSet.insert(
          (1, Set(1, 2)),
          (2, Set(2)),
          (2, Set(7)),
          (3, Set(3)),
          (4, Set())
        ).transact

        // has

        _ <- A.i.a1.B.iSet.has(1).query.get.map(_ ==> List(
          (1, Set(1, 2)),
        ))
        _ <- A.i.a1.B.iSet.has(2).query.get.map(_ ==> List(
          (1, Set(1, 2)),
          (2, Set(2)),
        ))

        _ <- A.i.a1.B.iSet.has(2, 1).query.get.map(_ ==> List(
          (1, Set(1, 2)),
          (2, Set(2)),
        ))
        _ <- A.i.a1.B.iSet.has(2, 7).query.get.map(_ ==> List(
          (1, Set(1, 2)),
          (2, Set(2, 7)),
        ))
        _ <- A.i.a1.B.iSet.has(2, 3).query.get.map(_ ==> List(
          (1, Set(1, 2)),
          (2, Set(2)),
          (3, Set(3)),
        ))


        // hasNo

        _ <- A.i.a1.B.iSet.hasNo(1).query.get.map(_ ==> List(
          (2, Set(2, 7)),
          (3, Set(3)),
        ))
        _ <- A.i.a1.B.iSet.hasNo(2).query.get.map(_ ==> List(
          (2, Set(7)),
          (3, Set(3)),
        ))
        _ <- A.i.a1.B.iSet.hasNo(3).query.get.map(_ ==> List(
          (1, Set(1, 2)),
          (2, Set(2, 7))
        ))
      } yield ()
    }


    "tacit" - refs { implicit conn =>
      for {
        _ <- A.i.B.iSet.insert(
          (1, Set(1, 2)),
          (2, Set(2)),
          (2, Set(7)),
          (3, Set(3)),
          (4, Set())
        ).transact

        // has
        _ <- A.i.a1.B.iSet_.has(1).query.get.map(_ ==> List(1))
        _ <- A.i.a1.B.iSet_.has(2).query.get.map(_ ==> List(1, 2))
        _ <- A.i.a1.B.iSet_.has(2, 7).query.get.map(_ ==> List(1, 2))
        _ <- A.i.a1.B.iSet_.has(2, 3).query.get.map(_ ==> List(1, 2, 3))

        // hasNo
        _ <- A.i.a1.B.iSet_.hasNo(1).query.get.map(_ ==> List(2, 3))
        _ <- A.i.a1.B.iSet_.hasNo(2).query.get.map(_ ==> List(2, 3))
        _ <- A.i.a1.B.iSet_.hasNo(3).query.get.map(_ ==> List(1, 2))

        // no value - match non-asserted attribute (null)
        // Nothing returned since there's no relationship to B
        _ <- A.i.a1.B.iSet_().query.i.get.map(_ ==> Nil)
      } yield ()
    }


    "optional" - refs { implicit conn =>
      val allAssertedOptional = List(
        (1, Some(Set(1, 2))),
        (2, Some(Set(2, 7))), // 2 rows coalesced
        (3, Some(Set(3))),
      )
      for {
        _ <- A.i.B.i_?.iSet.insert(
          (1, Some(1), Set(1, 2)),
          (2, Some(1), Set(2)),
          (2, Some(1), Set(7)),
          (3, Some(1), Set(3)),
          (4, Some(1), Set()), // relationship created since 1 is saved in B namespace
          (5, None, Set()) //     relationship not created
        ).transact

        // has

        _ <- A.i.a1.B.iSet_?.has(Some(1)).query.get.map(_ ==> List(
          (1, Some(Set(1, 2))),
        ))
        _ <- A.i.a1.B.iSet_?.has(Some(2)).query.get.map(_ ==> List(
          (1, Some(Set(1, 2))),
          (2, Some(Set(2))),
        ))
        // same as
        _ <- A.i.a1.B.iSet_?.has(Some(Seq(2))).query.get.map(_ ==> List(
          (1, Some(Set(1, 2))),
          (2, Some(Set(2))),
        ))
        // has 2 or 3
        _ <- A.i.a1.B.iSet_?.has(Some(Seq(2, 3))).query.get.map(_ ==> List(
          (1, Some(Set(1, 2))),
          (2, Some(Set(2))),
          (3, Some(Set(3))),
        ))
        // None matches non-asserted values
        _ <- A.i.a1.B.iSet_?.has(Option.empty[Seq[Int]]).query.get.map(_ ==> List((4, None)))

        // Empty Sets match nothing
        _ <- A.i.a1.B.iSet_?.has(Some(Seq.empty[Int])).query.get.map(_ ==> Nil)


        // hasNo

        _ <- A.i.a1.B.iSet_?.hasNo(Some(1)).query.get.map(_ ==> List(
          (2, Some(Set(2, 7))),
          (3, Some(Set(3))),
        ))
        _ <- A.i.a1.B.iSet_?.hasNo(Some(2)).query.get.map(_ ==> List(
          (2, Some(Set(7))),
          (3, Some(Set(3))),
        ))
        _ <- A.i.a1.B.iSet_?.hasNo(Some(3)).query.get.map(_ ==> List(
          (1, Some(Set(1, 2))),
          (2, Some(Set(2, 7)))
        ))

        // Negating None matches all asserted values
        _ <- A.i.a1.B.iSet_?.hasNo(Option.empty[Seq[Int]]).query.get.map(_ ==> allAssertedOptional)

        // Negating empty Sets match nothing
        _ <- A.i.a1.B.iSet_?.hasNo(Some(Seq.empty[Int])).query.get.map(_ ==> allAssertedOptional)
      } yield ()
    }
  }
}
