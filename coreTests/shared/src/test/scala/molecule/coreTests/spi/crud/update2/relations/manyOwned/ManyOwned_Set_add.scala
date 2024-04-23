package molecule.coreTests.spi.crud.update2.relations.manyOwned

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait ManyOwned_Set_add extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

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
        _ <- A(a, b, c, d, e, f).OwnBb.iSet.add(4, 5).update.transact

        _ <- A.i.a1.OwnBb.*?(B.s_?.iSet).query.get.map(_ ==> List(
          (1, List((None, Set(4, 5)))), //                                       ref + addition
          (2, List((Some("a"), Set(4, 5)))), //                                  addition in 1 ref entity
          (3, List((Some("b"), Set(4, 5)), (Some("c"), Set(4, 5)))), //          addition in 2 ref entities
          (4, List((Some("d"), Set(1, 2, 4, 5)))), //                            update in 1 ref entity
          (5, List((Some("e"), Set(2, 3, 4, 5)), (Some("f"), Set(3, 4, 5)))), // update in 2 ref entities
          (6, List((Some("g"), Set(4, 5)), (Some("h"), Set(4, 5)))), //          update in one ref entity and addition in another
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
        _ <- A.i_.OwnBb.iSet.add(4, 5).update.transact

        _ <- A.i.a1.OwnBb.*?(B.s_?.iSet).query.get.map(_ ==> List(
          (1, List((None, Set(4, 5)))), //                                       ref + addition
          (2, List((Some("a"), Set(4, 5)))), //                                  addition in 1 ref entity
          (3, List((Some("b"), Set(4, 5)), (Some("c"), Set(4, 5)))), //          addition in 2 ref entities
          (4, List((Some("d"), Set(1, 2, 4, 5)))), //                            update in 1 ref entity
          (5, List((Some("e"), Set(2, 3, 4, 5)), (Some("f"), Set(3, 4, 5)))), // update in 2 ref entities
          (6, List((Some("g"), Set(4, 5)), (Some("h"), Set(4, 5)))), //          update in one ref entity and addition in another
        ))
      } yield ()
    }


    "value - ref - filter" - refs { implicit conn =>
      for {
        _ <- A.iSet_?.OwnBb.*?(B.s).insert(
          (Some(Set(0, 1)), List()),
          (None, List("a")),
          (Some(Set(1, 2)), List("b", "c")),
          (Some(Set(2, 3)), List("d", "e")),
        ).transact

        // Filter by B attribute, update A values
        _ <- A.iSet.add(3, 4).OwnBb.s_.update.transact

        _ <- A.iSet.OwnBb.*?(B.s).query.get.map(_.sortBy(_._2.headOption.toString) ==> List(
          (Set(0, 1), List()), //               nothing updated since this A entity has no ref to B
          (Set(3, 4), List("a")), //            A attribute added
          (Set(1, 2, 3, 4), List("b", "c")), // A attribute updated
          (Set(2, 3, 4), List("d", "e")), //    A attribute updated
        ))
      } yield ()
    }


    "ref - filter/value" - refs { implicit conn =>
      for {
        // will not be updated since entities have no A -> B relationship
        _ <- B.s("x").iSet(Set(0, 1)).save.transact
        _ <- A.i(1).save.transact

        // will be updated
        _ <- A.i.OwnBb.*(B.s_?.iSet_?).insert(
          (2, List(
            (None, None), // no relationship to B created
            (None, Some(Set(1, 2))),
            (Some("a"), None),
            (Some("b"), Some(Set(2, 3))),
          ))
        ).transact

        // Filter by B attribute, update B values
        _ <- A.OwnBb.s_.iSet.add(3, 4).update.transact

        _ <- A.i.a1.OwnBb.*?(B.s_?.a1.iSet_?).query.get.map(_ ==> List(
          (1, List()), // no change to entity without relationship to B
          (2, List(
            // (None, None),                    no relationship to B
            (None, Some(Set(1, 2))), //         no change without filter match
            (Some("a"), Some(Set(3, 4))), //    B attribute added
            (Some("b"), Some(Set(2, 3, 4))), // B attribute updated
          ))
        ))

        _ <- B.s_?.a1.iSet.query.get.map(_ ==> List(
          (None, Set(1, 2)),
          (Some("a"), Set(3, 4)),
          (Some("b"), Set(2, 3, 4)),
          (Some("x"), Set(0, 1)), // no change to entity without relationship from A
        ))
      } yield ()
    }
  }
}
