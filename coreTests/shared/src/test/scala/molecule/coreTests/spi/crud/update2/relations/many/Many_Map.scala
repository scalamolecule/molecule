package molecule.coreTests.spi.crud.update2.relations.many

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait Many_Map extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "id-filter - ref - value" - refs { implicit conn =>
      for {
        List(a, b, c, d, e, f) <- A.i.a1.Bb.*?(B.s_?.iMap_?).insert(
          (1, List()),
          (2, List((Some("a"), None))),
          (3, List((Some("b"), None), (Some("c"), None))),
          (4, List((Some("d"), Some(Map(pint1, pint2))))),
          (5, List((Some("e"), Some(Map(pint2, pint3))), (Some("f"), Some(Map(pint3, pint4))))),
          (6, List((Some("g"), Some(Map(pint4, pint5))), (Some("h"), None))),
        ).transact.map(_.ids)

        // Filter by A ids, update B values
        _ <- A(a, b, c, d, e, f).Bb.iMap(Map(pint4, pint5)).update.transact

        _ <- A.i.a1.Bb.*?(B.s_?.iMap).query.get.map(_ ==> List(
          (1, List((None, Map(pint4, pint5)))), //                                      ref + addition
          (2, List((Some("a"), Map(pint4, pint5)))), //                                 addition in 1 ref entity
          (3, List((Some("b"), Map(pint4, pint5)), (Some("c"), Map(pint4, pint5)))), // addition in 2 ref entities
          (4, List((Some("d"), Map(pint4, pint5)))), //                                 update in 1 ref entity
          (5, List((Some("e"), Map(pint4, pint5)), (Some("f"), Map(pint4, pint5)))), // update in 2 ref entities
          (6, List((Some("g"), Map(pint4, pint5)), (Some("h"), Map(pint4, pint5)))), // update in one ref entity and addition in another
        ))
      } yield ()
    }


    "filter - ref - value" - refs { implicit conn =>
      for {
        _ <- A.i.a1.Bb.*?(B.s_?.iMap_?).insert(
          (1, List()),
          (2, List((Some("a"), None))),
          (3, List((Some("b"), None), (Some("c"), None))),
          (4, List((Some("d"), Some(Map(pint1, pint2))))),
          (5, List((Some("e"), Some(Map(pint2, pint3))), (Some("f"), Some(Map(pint3, pint4))))),
          (6, List((Some("g"), Some(Map(pint4, pint5))), (Some("h"), None))),
        ).transact

        // Filter by A ids, update B values
        _ <- A.i_.Bb.iMap(Map(pint4, pint5)).update.transact

        _ <- A.i.a1.Bb.*?(B.s_?.iMap).query.get.map(_ ==> List(
          (1, List((None, Map(pint4, pint5)))), //                                      ref + addition
          (2, List((Some("a"), Map(pint4, pint5)))), //                                 addition in 1 ref entity
          (3, List((Some("b"), Map(pint4, pint5)), (Some("c"), Map(pint4, pint5)))), // addition in 2 ref entities
          (4, List((Some("d"), Map(pint4, pint5)))), //                                 update in 1 ref entity
          (5, List((Some("e"), Map(pint4, pint5)), (Some("f"), Map(pint4, pint5)))), // update in 2 ref entities
          (6, List((Some("g"), Map(pint4, pint5)), (Some("h"), Map(pint4, pint5)))), // update in one ref entity and addition in another
        ))
      } yield ()
    }


    "value - ref - filter" - refs { implicit conn =>
      for {
        _ <- A.iMap_?.Bb.*?(B.s).insert(
          (Some(Map(pint0, pint1)), List()),
          (None, List("a")),
          (Some(Map(pint1, pint2)), List("b", "c")),
          (Some(Map(pint2, pint3)), List("d", "e")),
        ).transact

        // Filter by B attribute, update A values
        _ <- A.iMap(Map(pint3, pint4)).Bb.s_.update.transact

        _ <- A.iMap.Bb.*?(B.s).query.get.map(_.sortBy(_._2.headOption.toString) ==> List(
          (Map(pint0, pint1), List()), //         nothing updated since this A entity has no ref to B
          (Map(pint3, pint4), List("a")), //      A attribute added
          (Map(pint3, pint4), List("b", "c")), // A attribute updated
          (Map(pint3, pint4), List("d", "e")), // A attribute updated
        ))
      } yield ()
    }


    "ref - filter/value" - refs { implicit conn =>
      for {
        // will not be updated since entities have no A -> B relationship
        _ <- B.s("x").iMap(Map(pint0, pint1)).save.transact
        _ <- A.i(1).save.transact

        // will be updated
        _ <- A.i.Bb.*(B.s_?.iMap_?).insert(
          (2, List(
            (None, None), // no relationship to B created
            (None, Some(Map(pint1, pint2))),
            (Some("a"), None),
            (Some("b"), Some(Map(pint2, pint3))),
          ))
        ).transact

        // Filter by B attribute, update B values
        _ <- A.Bb.s_.iMap(Map(pint3, pint4)).update.transact

        _ <- A.i.a1.Bb.*?(B.s_?.a1.iMap_?).query.get.map(_ ==> List(
          (1, List()), // no change to entity without relationship to B
          (2, List(
            // (None, None),                         no relationship to B
            (None, Some(Map(pint1, pint2))), //      no change without filter match
            (Some("a"), Some(Map(pint3, pint4))), // B attribute added
            (Some("b"), Some(Map(pint3, pint4))), // B attribute updated
          ))
        ))

        _ <- B.s_?.a1.iMap.query.get.map(_ ==> List(
          (None, Map(pint1, pint2)),
          (Some("a"), Map(pint3, pint4)),
          (Some("b"), Map(pint3, pint4)),
          (Some("x"), Map(pint0, pint1)), // no change to entity without relationship from A
        ))
      } yield ()
    }


    "ref ref" - refs { implicit conn =>
      for {
        id <- A.iMap(Map(pint1)).Bb.iMap(Map(pint2)).Cc.iMap(Map(pint3)).save.transact.map(_.id)
        _ <- A.iMap.Bb.iMap.Cc.iMap.query.get.map(_ ==> List((Map(pint1), Map(pint2), Map(pint3))))

        // A
        _ <- A(id).iMap(Map(pint1)).update.transact
        _ <- A.iMap.Bb.iMap.Cc.iMap.query.get.map(_ ==> List((Map(pint1), Map(pint2), Map(pint3))))

        // A + B
        _ <- A(id).iMap(Map(pint2)).Bb.iMap(Map(pint1)).update.transact
        _ <- A.iMap.Bb.iMap.Cc.iMap.query.get.map(_ ==> List((Map(pint2), Map(pint1), Map(pint3))))

        // B
        _ <- A(id).Bb.iMap(Map(pint2)).update.transact
        _ <- A.iMap.Bb.iMap.Cc.iMap.query.get.map(_ ==> List((Map(pint2), Map(pint2), Map(pint3))))

        // A + B + C
        _ <- A(id).iMap(Map(pint3)).Bb.iMap(Map(pint3)).Cc.iMap(Map(pint1)).update.transact
        _ <- A.iMap.Bb.iMap.Cc.iMap.query.get.map(_ ==> List((Map(pint3), Map(pint3), Map(pint1))))

        // A + C
        _ <- A(id).iMap(Map(pint4)).Bb.Cc.iMap(Map(pint2)).update.transact
        _ <- A.iMap.Bb.iMap.Cc.iMap.query.get.map(_ ==> List((Map(pint4), Map(pint3), Map(pint2))))

        // B + C
        _ <- A(id).Bb.iMap(Map(pint4)).Cc.iMap(Map(pint3)).update.transact
        _ <- A.iMap.Bb.iMap.Cc.iMap.query.get.map(_ ==> List((Map(pint4), Map(pint4), Map(pint3))))

        // C
        _ <- A(id).Bb.Cc.iMap(Map(pint4)).update.transact
        _ <- A.iMap.Bb.iMap.Cc.iMap.query.get.map(_ ==> List((Map(pint4), Map(pint4), Map(pint4))))
      } yield ()
    }


    "backref" - refs { implicit conn =>
      for {
        id <- A.iMap(Map(pint1)).Bb.iMap(Map(pint2))._A.Cc.iMap(Map(pint3)).save.transact.map(_.id)
        _ <- A.iMap.Bb.iMap._A.Cc.iMap.query.get.map(_ ==> List((Map(pint1), Map(pint2), Map(pint3))))

        // Updating A.Bb.iMap and A.Cc.iMap
        _ <- A(id).iMap(Map(pint1)).Bb.iMap(Map(pint1))._A.Cc.iMap(Map(pint1)).update.transact
        _ <- A.iMap.Bb.iMap._A.Cc.iMap.query.get.map(_ ==> List((Map(pint1), Map(pint1), Map(pint1))))
      } yield ()
    }
  }
}
