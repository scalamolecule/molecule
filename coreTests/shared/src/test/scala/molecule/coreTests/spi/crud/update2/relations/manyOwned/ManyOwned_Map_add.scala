package molecule.coreTests.spi.crud.update2.relations.manyOwned

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait ManyOwned_Map_add extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "id-filter - ref - value" - refs { implicit conn =>
      for {
        List(a, b, c, d, e, f) <- A.i.a1.OwnBb.*?(B.s_?.iMap_?).insert(
          (1, List()),
          (2, List((Some("a"), None))),
          (3, List((Some("b"), None), (Some("c"), None))),
          (4, List((Some("d"), Some(Map(pint1, pint2))))),
          (5, List((Some("e"), Some(Map(pint2, pint3))), (Some("f"), Some(Map(pint3, pint4))))),
          (6, List((Some("g"), Some(Map(pint4, pint5))), (Some("h"), None))),
        ).transact.map(_.ids)

        // Filter by A ids, update B values
        _ <- A(a, b, c, d, e, f).OwnBb.iMap.add(pint4, pint5).update.transact

        _ <- A.i.a1.OwnBb.*?(B.s_?.iMap).query.get.map(_ ==> List(
          (1, List((None, Map(pint4, pint5)))), //                                                           ref + addition
          (2, List((Some("a"), Map(pint4, pint5)))), //                                                      addition in 1 ref entity
          (3, List((Some("b"), Map(pint4, pint5)), (Some("c"), Map(pint4, pint5)))), //                      addition in 2 ref entities
          (4, List((Some("d"), Map(pint1, pint2, pint4, pint5)))), //                                        update in 1 ref entity
          (5, List((Some("e"), Map(pint2, pint3, pint4, pint5)), (Some("f"), Map(pint3, pint4, pint5)))), // update in 2 ref entities
          (6, List((Some("g"), Map(pint4, pint5)), (Some("h"), Map(pint4, pint5)))), //                      update in one ref entity and addition in another
        ))
      } yield ()
    }


    "filter - ref - value" - refs { implicit conn =>
      for {
        _ <- A.i.OwnBb.*?(B.s_?.iMap_?).insert(
          (1, List()),
          (2, List((Some("a"), None))),
          (3, List((Some("b"), None), (Some("c"), None))),
          (4, List((Some("d"), Some(Map(pint1, pint2))))),
          (5, List((Some("e"), Some(Map(pint2, pint3))), (Some("f"), Some(Map(pint3, pint4))))),
          (6, List((Some("g"), Some(Map(pint4, pint5))), (Some("h"), None))),
        ).transact

        // Filter by A ids, update B values
        _ <- A.i_.OwnBb.iMap.add(pint4, pint5).update.transact

        _ <- A.i.a1.OwnBb.*?(B.s_?.iMap).query.get.map(_ ==> List(
          (1, List((None, Map(pint4, pint5)))), //                                                           ref + addition
          (2, List((Some("a"), Map(pint4, pint5)))), //                                                      addition in 1 ref entity
          (3, List((Some("b"), Map(pint4, pint5)), (Some("c"), Map(pint4, pint5)))), //                      addition in 2 ref entities
          (4, List((Some("d"), Map(pint1, pint2, pint4, pint5)))), //                                        update in 1 ref entity
          (5, List((Some("e"), Map(pint2, pint3, pint4, pint5)), (Some("f"), Map(pint3, pint4, pint5)))), // update in 2 ref entities
          (6, List((Some("g"), Map(pint4, pint5)), (Some("h"), Map(pint4, pint5)))), //                      update in one ref entity and addition in another
        ))
      } yield ()
    }


    "value - ref - filter" - refs { implicit conn =>
      for {
        _ <- A.iMap_?.OwnBb.*?(B.s).insert(
          (Some(Map(pint0, pint1)), List()),
          (None, List("a")),
          (Some(Map(pint1, pint2)), List("b", "c")),
          (Some(Map(pint2, pint3)), List("d", "e")),
        ).transact

        // Filter by B attribute, update A values
        _ <- A.iMap.add(pint3, pint4).OwnBb.s_.update.transact

        _ <- A.iMap.OwnBb.*?(B.s).query.get.map(_.sortBy(_._2.headOption.toString) ==> List(
          (Map(pint0, pint1), List()), //                       nothing updated since this A entity has no ref to B
          (Map(pint3, pint4), List("a")), //                    A attribute added
          (Map(pint1, pint2, pint3, pint4), List("b", "c")), // A attribute updated
          (Map(pint2, pint3, pint4), List("d", "e")), //        A attribute updated
        ))
      } yield ()
    }


    "ref - filter/value" - refs { implicit conn =>
      for {
        // will not be updated since entities have no A -> B relationship
        _ <- B.s("x").iMap(Map(pint0, pint1)).save.transact
        _ <- A.i(1).save.transact

        // will be updated
        _ <- A.i.OwnBb.*(B.s_?.iMap_?).insert(
          (2, List(
            (None, None), // no relationship to B created
            (None, Some(Map(pint1, pint2))),
            (Some("a"), None),
            (Some("b"), Some(Map(pint2, pint3))),
          ))
        ).transact

        // Filter by B attribute, update B values
        _ <- A.OwnBb.s_.iMap.add(pint3, pint4).update.transact

        _ <- A.i.a1.OwnBb.*?(B.s_?.a1.iMap_?).query.get.map(_ ==> List(
          (1, List()), // no change to entity without relationship to B
          (2, List(
            // (None, None),                                no relationship to B
            (None, Some(Map(pint1, pint2))), //             no change without filter match
            (Some("a"), Some(Map(pint3, pint4))), //        B attribute added
            (Some("b"), Some(Map(pint2, pint3, pint4))), // B attribute updated
          ))
        ))

        _ <- if (database == "MongoDB") {
          // Embedded documents in Mongo have no separate entity ids
          B.s_?.a1.iMap.query.get.map(_ ==> List(
            (Some("x"), Map(pint0, pint1)), // no change to entity without relationship from A
          ))
        } else {
          B.s_?.a1.iMap.query.get.map(_ ==> List(
            (None, Map(pint1, pint2)),
            (Some("a"), Map(pint3, pint4)),
            (Some("b"), Map(pint2, pint3, pint4)),
            (Some("x"), Map(pint0, pint1)), // no change to entity without relationship from A
          ))
        }
      } yield ()
    }
  }
}
