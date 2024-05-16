package molecule.coreTests.spi.crud.update2.relation.many

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait Many_Map_remove extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "id-filter - ref - value" - refs { implicit conn =>
      for {
        List(a, b, c, d, e, f) <- A.i.Bb.*?(B.s_?.iMap_?).insert(
          (1, List()),
          (2, List((Some("a"), None))),
          (3, List((Some("b"), None), (Some("c"), None))),
          (4, List((Some("d"), Some(Map(pint1, pint2))))),
          (5, List((Some("e"), Some(Map(pint2, pint3))), (Some("f"), Some(Map(pint3, pint4))))),
          (6, List((Some("g"), Some(Map(pint4, pint5))), (Some("h"), None))),
        ).transact.map(_.ids)

        // Filter by A ids, update B values
        _ <- A(a, b, c, d, e, f).Bb.iMap.remove(string4, string5).update.transact

        // 3 nested entities updated
        _ <- A.i.a1.Bb.*?(B.s_?.iMap_?).query.get.map(_ ==> List(
          (1, List()),
          (2, List((Some("a"), None))),
          (3, List((Some("b"), None), (Some("c"), None))),
          (4, List((Some("d"), Some(Map(pint1, pint2))))),
          (5, List((Some("e"), Some(Map(pint2, pint3))), (Some("f"), Some(Map(pint3))))), // update of last nested entity
          (6, List((Some("g"), None), (Some("h"), None))), //                                update of first nested entity
        ))
      } yield ()
    }


    "filter - ref - value" - refs { implicit conn =>
      for {
        _ <- A.i.Bb.*?(B.s_?.iMap_?).insert(
          (1, List()),
          (2, List((Some("a"), None))),
          (3, List((Some("b"), None), (Some("c"), None))),
          (4, List((Some("d"), Some(Map(pint1, pint2))))),
          (5, List((Some("e"), Some(Map(pint2, pint3))), (Some("f"), Some(Map(pint3, pint4))))),
          (6, List((Some("g"), Some(Map(pint4, pint5))), (Some("h"), None))),
        ).transact

        // `upsert` has same semantics as `update` with `remove` since we don't insert data
        // Filter by A ids, update B values
        _ <- A.i_.Bb.iMap.remove(string4, string5).upsert.transact

        _ <- A.i.a1.Bb.*?(B.s_?.iMap_?).query.get.map(_ ==> List(
          (1, List()),
          (2, List((Some("a"), None))),
          (3, List((Some("b"), None), (Some("c"), None))),
          (4, List((Some("d"), Some(Map(pint1, pint2))))),
          (5, List((Some("e"), Some(Map(pint2, pint3))), (Some("f"), Some(Map(pint3))))), // update of last nested entity
          (6, List((Some("g"), None), (Some("h"), None))), //                                update of first nested entity
        ))
      } yield ()
    }


    "value - ref - filter" - refs { implicit conn =>
      for {
        _ <- A.iMap.Bb.*?(B.s).insert(
          (Map(pint1, pint3), List()),
          (Map(pint1, pint2), List("a")),
          (Map(pint2, pint3), List("b", "c")),
          (Map(pint3, pint4), List("d", "e")),
        ).transact.map(_.ids)

        // Filter by B attribute, update A values
        _ <- A.iMap.remove(string3, string4).Bb.s_.update.transact

        // 2 A entities updated
        _ <- A.iMap_?.Bb.*(B.s.a1).query.get.map(_.sortBy(_._2.headOption.toString) ==> List(
          (Some(Map(pint1, pint2)), List("a")),
          (Some(Map(pint2)), List("b", "c")), // 1 value removed
          (None, List("d", "e")), //             both values removed (refs to B still exist)
        ))

        // Initial entity without ref to B was not updated (3 not removed)
        _ <- A.iMap.bb_().query.get.map(_ ==> List(Map(pint1, pint3)))
      } yield ()
    }


    "ref - filter/value" - refs { implicit conn =>
      for {
        // Will not be updated since entity has no A -> B relationship
        _ <- B.s("x").iMap(Map(pint1, pint3)).save.transact

        _ <- A.i.Bb.*?(B.s_?.iMap_?).insert(
          (0, List()),
          (1, List(
            (None, None), // no B entity created
            (None, Some(Map(pint0, pint1))),
            (Some("a"), None),
            (Some("b"), Some(Map(pint1, pint2))),
            (Some("c"), Some(Map(pint2, pint3))),
            (Some("d"), Some(Map(pint3, pint4))),
          ))
        ).transact

        // Filter by B attribute, update B values
        _ <- A.Bb.s_.iMap.remove(string3, string4).update.transact

        _ <- A.i.a1.Bb.*?(B.s_?.a1.iMap_?).query.get.map(_ ==> List(
          (0, List()), //                            no B entities to update
          (1, List(
            // (None, None),                         no B entity created
            (None, Some(Map(pint0, pint1))), //      no change without filter match
            (Some("a"), None), //                    no values to remove
            (Some("b"), Some(Map(pint1, pint2))), // no matching values removed
            (Some("c"), Some(Map(pint2))), //        1 value removed
            (Some("d"), None), //                    all 3 values removed
          ))
        ))

        _ <- B.s_?.a1.iMap.query.get.map(_ ==> List(
          (None, Map(pint0, pint1)),
          (Some("b"), Map(pint1, pint2)),
          (Some("c"), Map(pint2)),
          (Some("x"), Map(pint1, pint3)), // not update without relationship from A
        ))
      } yield ()
    }
  }
}
