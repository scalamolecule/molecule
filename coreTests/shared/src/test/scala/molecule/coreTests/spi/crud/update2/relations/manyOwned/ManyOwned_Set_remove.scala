package molecule.coreTests.spi.crud.update2.relations.manyOwned

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait ManyOwned_Set_remove extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "id-filter - ref - value" - refs { implicit conn =>
      for {
        List(a, b, c, d, e, f) <- A.i.a1.OwnBb.*?(B.s_?.iSet_?).insert(
          (1, List()),
          (2, List((Some("a"), None))),
          (3, List((Some("b"), None), (Some("c"), None))),
          (4, List((Some("d"), Some(Set(1, 2))))),
          (5, List((Some("e"), Some(Set(2, 3))), (Some("f"), Some(Set(3, 4))))),
          (6, List((Some("g"), Some(Set(4, 5))), (Some("h"), None))),
        ).transact.map(_.ids)

        // Filter by A ids, update B values
        _ <- A(a, b, c, d, e, f).OwnBb.iSet.remove(4, 5).update.transact

        // 3 nested entities updated
        _ <- A.i.a1.OwnBb.*?(B.s_?.iSet_?).query.get.map(_ ==> List(
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
        _ <- A.i.a1.OwnBb.*?(B.s_?.iSet_?).insert(
          (1, List()),
          (2, List((Some("a"), None))),
          (3, List((Some("b"), None), (Some("c"), None))),
          (4, List((Some("d"), Some(Set(1, 2))))),
          (5, List((Some("e"), Some(Set(2, 3))), (Some("f"), Some(Set(3, 4))))),
          (6, List((Some("g"), Some(Set(4, 5))), (Some("h"), None))),
        ).transact

        // Filter by A ids, update B values
        _ <- A.i_.OwnBb.iSet.remove(4, 5).update.transact

        _ <- A.i.a1.OwnBb.*?(B.s_?.iSet_?).query.get.map(_ ==> List(
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
        _ <- A.iSet.OwnBb.*?(B.s).insert(
          (Set(1, 3), List()),
          (Set(1, 2), List("a")),
          (Set(2, 3), List("b", "c")),
          (Set(3, 4), List("d", "e")),
        ).transact.map(_.ids)

        // Filter by B attribute, update A values
        _ <- A.iSet.remove(3, 4).OwnBb.s_.update.transact

        // 2 A entities updated
        _ <- A.iSet_?.OwnBb.*(B.s.a1).query.get.map(_.sortBy(_._2.headOption.toString) ==> List(
          (Some(Set(1, 2)), List("a")),
          (Some(Set(2)), List("b", "c")), // 1 value removed
          (None, List("d", "e")), //         both values removed (refs to B still exist)
        ))

        // Initial entity without ref to B was not updated (3 not removed)
        _ <- A.iSet.ownBb_().query.get.map(_ ==> List(Set(1, 3)))
      } yield ()
    }


    "ref - filter/value" - refs { implicit conn =>
      for {
        // Will not be updated since entity has no A -> B relationship
        _ <- B.s("x").iSet(Set(1, 3)).save.transact

        _ <- A.i.OwnBb.*?(B.s_?.iSet_?).insert(
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
        _ <- A.OwnBb.s_.iSet.remove(3, 4).update.transact

        _ <- A.i.a1.OwnBb.*?(B.s_?.a1.iSet_?).query.get.map(_ ==> List(
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
