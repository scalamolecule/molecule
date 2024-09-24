package molecule.coreTests.spi.crud.update.relation.many

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait Many_Set_remove extends CoreTestSuite with Api_async { spi: Spi_async =>

  override lazy val tests = Tests {

    "id-filter - ref - value" - refs { implicit conn =>
      for {
        List(a, b, c, d, e, f) <- A.i.a1.Bb.*?(B.s_?.iSet_?).insert(
          (1, List()),
          (2, List((Some("a"), None))),
          (3, List((Some("b"), None), (Some("c"), None))),
          (4, List((Some("d"), Some(Set(1, 2))))),
          (5, List((Some("e"), Some(Set(2, 3))), (Some("f"), Some(Set(3, 4))))),
          (6, List((Some("g"), Some(Set(4, 5))), (Some("h"), None))),
        ).transact.map(_.ids)

        // Filter by A ids, update B values
        _ <- A(a, b, c, d, e, f).Bb.iSet.remove(4, 5).update.transact

        // 3 nested entities updated
        _ <- A.i.a1.Bb.*?(B.s_?.iSet_?).query.get.map(_ ==> List(
          (1, List()),
          (2, List((Some("a"), None))),
          (3, List((Some("b"), None), (Some("c"), None))),
          (4, List((Some("d"), Some(Set(1, 2))))),
          (5, List((Some("e"), Some(Set(2, 3))), (Some("f"), Some(Set(3))))), // update of last nested entity
          (6, List((Some("g"), None), (Some("h"), None))), //                    update of first nested entity
        ))
      } yield ()
    }


    "filter - ref - value" - refs { implicit conn =>
      for {
        _ <- A.i.Bb.*?(B.s_?.iSet_?).insert(
          (1, List()),
          (2, List((Some("a"), None))),
          (3, List((Some("b"), None), (Some("c"), None))),
          (4, List((Some("d"), Some(Set(1, 2))))),
          (5, List((Some("e"), Some(Set(2, 3))), (Some("f"), Some(Set(3, 4))))),
          (6, List((Some("g"), Some(Set(4, 5))), (Some("h"), None))),
        ).transact

        // `upsert` has same semantics as `update` with `remove` since we don't insert data
        // Filter by A ids, update B values
        _ <- A.i_.Bb.iSet.remove(4, 5).upsert.transact

        _ <- A.i.a1.Bb.*?(B.s_?.iSet_?).query.get.map(_ ==> List(
          (1, List()),
          (2, List((Some("a"), None))),
          (3, List((Some("b"), None), (Some("c"), None))),
          (4, List((Some("d"), Some(Set(1, 2))))),
          (5, List((Some("e"), Some(Set(2, 3))), (Some("f"), Some(Set(3))))), // update of last nested entity
          (6, List((Some("g"), None), (Some("h"), None))), //                    update of first nested entity
        ))
      } yield ()
    }


    "value - ref - filter" - refs { implicit conn =>
      for {
        _ <- A.iSet.Bb.*?(B.s).insert(
          (Set(1, 3), List()),
          (Set(1, 2), List("a")),
          (Set(2, 3), List("b", "c")),
          (Set(3, 4), List("d", "e")),
        ).transact.map(_.ids)

        // Filter by B attribute, update A values
        _ <- A.iSet.remove(3, 4).Bb.s_.update.transact

        // 2 A entities updated
        _ <- A.iSet_?.Bb.*(B.s.a1).query.get.map(_.sortBy(_._2.headOption.toString) ==> List(
          (Some(Set(1, 2)), List("a")),
          (Some(Set(2)), List("b", "c")), // 1 value removed
          (None, List("d", "e")), //         both values removed (refs to B still exist)
        ))

        // Initial entity without ref to B was not updated (3 not removed)
        _ <- A.iSet.bb_().query.get.map(_ ==> List(Set(1, 3)))
      } yield ()
    }


    "ref - filter/value" - refs { implicit conn =>
      for {
        // Will not be updated since entity has no A -> B relationship
        _ <- B.s("x").iSet(Set(1, 3)).save.transact

        _ <- A.i.Bb.*?(B.s_?.iSet_?).insert(
          (0, List()),
          (1, List(
            (None, None), // no B entity created
            (None, Some(Set(0, 1))),
            (Some("a"), None),
            (Some("b"), Some(Set(1, 2))),
            (Some("c"), Some(Set(2, 3))),
            (Some("d"), Some(Set(3, 4))),
          ))
        ).transact

        // Filter by B attribute, update B values
        _ <- A.Bb.s_.iSet.remove(3, 4).update.transact

        _ <- A.i.a1.Bb.*?(B.s_?.a1.iSet_?).query.get.map(_ ==> List(
          (0, List()), //                    no B entities to update
          (1, List(
            // (None, None),                 no B entity created
            (None, Some(Set(0, 1))), //      no change without filter match
            (Some("a"), None), //            no values to remove
            (Some("b"), Some(Set(1, 2))), // no matching values removed
            (Some("c"), Some(Set(2))), //    1 value removed
            (Some("d"), None), //            all 3 values removed
          ))
        ))

        _ <- B.s_?.a1.iSet.query.get.map(_ ==> List(
          (None, Set(0, 1)),
          (Some("b"), Set(1, 2)),
          (Some("c"), Set(2)),
          (Some("x"), Set(1, 3)), // not update without relationship from A
        ))
      } yield ()
    }
  }
}
