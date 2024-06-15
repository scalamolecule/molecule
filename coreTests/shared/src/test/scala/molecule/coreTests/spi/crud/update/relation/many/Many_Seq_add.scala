package molecule.coreTests.spi.crud.update.relation.many

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait Many_Seq_add extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "id-filter - ref - value" - refs { implicit conn =>
      for {
        List(a, b, c, d, e, f) <- A.i.a1.Bb.*?(B.s_?.iSeq_?).insert(
          (1, List()),
          (2, List((Some("a"), None))),
          (3, List((Some("b"), None), (Some("c"), None))),
          (4, List((Some("d"), Some(Seq(1, 2))))),
          (5, List((Some("e"), Some(Seq(2, 3))), (Some("f"), Some(Seq(3, 4))))),
          (6, List((Some("g"), Some(Seq(4, 5))), (Some("h"), None))),
        ).transact.map(_.ids)

        // Filter by A ids, update B values
        _ <- A(a, b, c, d, e, f).Bb.iSeq.add(4, 5).update.transact

        _ <- A.i.a1.Bb.*?(B.s_?.a1.iSeq).query.get.map(_ ==> List(
          (1, List()), //                                                           no B value to update
          (2, List()), //                                                           no B value to update
          (3, List()), //                                                           no B value to update
          (4, List((Some("d"), Seq(1, 2, 4, 5)))), //                               update in 1 ref entity
          (5, List((Some("e"), Seq(2, 3, 4, 5)), (Some("f"), Seq(3, 4, 4, 5)))), // update in 2 ref entities
          (6, List((Some("g"), Seq(4, 5, 4, 5)))), //                               update, but already has same values
        ))

        // Filter by A ids, upsert B values
        _ <- A(a, b, c, d, e, f).Bb.iSeq.add(5, 6).upsert.transact

        _ <- A.i.a1.Bb.*?(B.s_?.a1.iSeq).query.get.map(_ ==> List(
          (1, List((None, Seq(5, 6)))), //                                                      ref + insertion
          (2, List((Some("a"), Seq(5, 6)))), //                                                 insertion in 1 ref entity
          (3, List((Some("b"), Seq(5, 6)), (Some("c"), Seq(5, 6)))), //                         insertion in 2 ref entities
          (4, List((Some("d"), Seq(1, 2, 4, 5, 5, 6)))), //                                     update in 1 ref entity
          (5, List((Some("e"), Seq(2, 3, 4, 5, 5, 6)), (Some("f"), Seq(3, 4, 4, 5, 5, 6)))), // update in 2 ref entities
          (6, List((Some("g"), Seq(4, 5, 4, 5, 5, 6)), (Some("h"), Seq(5, 6)))), //             update in one ref entity and insertion in another
        ))
      } yield ()
    }


    "filter - ref - value" - refs { implicit conn =>
      for {
        _ <- A.i.a1.Bb.*?(B.s_?.iSeq_?).insert(
          (1, List()),
          (2, List((Some("a"), None))),
          (3, List((Some("b"), None), (Some("c"), None))),
          (4, List((Some("d"), Some(Seq(1, 2))))),
          (5, List((Some("e"), Some(Seq(2, 3))), (Some("f"), Some(Seq(3, 4))))),
          (6, List((Some("g"), Some(Seq(4, 5))), (Some("h"), None))),
        ).transact.map(_.ids)

        // Filter by A ids, update B values
        _ <- A.i_.Bb.iSeq.add(4, 5).update.transact

        _ <- A.i.a1.Bb.*?(B.s_?.a1.iSeq).query.get.map(_ ==> List(
          (1, List()), //                                                           no B value to update
          (2, List()), //                                                           no B value to update
          (3, List()), //                                                           no B value to update
          (4, List((Some("d"), Seq(1, 2, 4, 5)))), //                               update in 1 ref entity
          (5, List((Some("e"), Seq(2, 3, 4, 5)), (Some("f"), Seq(3, 4, 4, 5)))), // update in 2 ref entities
          (6, List((Some("g"), Seq(4, 5, 4, 5)))), //                               update, but already has same values
        ))

        // Filter by A ids, upsert B values
        _ <- A.i_.Bb.iSeq.add(5, 6).upsert.transact

        _ <- A.i.a1.Bb.*?(B.s_?.a1.iSeq).query.get.map(_ ==> List(
          (1, List((None, Seq(5, 6)))), //                                                      ref + insertion
          (2, List((Some("a"), Seq(5, 6)))), //                                                 insertion in 1 ref entity
          (3, List((Some("b"), Seq(5, 6)), (Some("c"), Seq(5, 6)))), //                         insertion in 2 ref entities
          (4, List((Some("d"), Seq(1, 2, 4, 5, 5, 6)))), //                                     update in 1 ref entity
          (5, List((Some("e"), Seq(2, 3, 4, 5, 5, 6)), (Some("f"), Seq(3, 4, 4, 5, 5, 6)))), // update in 2 ref entities
          (6, List((Some("g"), Seq(4, 5, 4, 5, 5, 6)), (Some("h"), Seq(5, 6)))), //             update in one ref entity and insertion in another
        ))
      } yield ()
    }


    "value - ref - filter" - refs { implicit conn =>
      for {
        _ <- A.iSeq_?.Bb.*?(B.s).insert(
          (Some(Seq(0, 1)), List()),
          (None, List("a")),
          (Some(Seq(1, 2)), List("b", "c")),
          (Some(Seq(2, 3)), List("d", "e")),
        ).transact

        // Filter by B attribute, update A values
        _ <- A.iSeq.add(3, 4).Bb.s_.update.transact

        _ <- A.iSeq.Bb.*?(B.s.a1).query.get.map(_.sortBy(_._2.headOption.toString) ==> List(
          (Seq(0, 1), List()), //               nothing updated since this A entity has no ref to B
          // (<none>, List("a")), //            no A attribute to update
          (Seq(1, 2, 3, 4), List("b", "c")), // A attribute updated
          (Seq(2, 3, 3, 4), List("d", "e")), // A attribute updated
        ))

        // Filter by B attribute, update A values
        _ <- A.iSeq.add(4, 5).Bb.s_.upsert.transact

        _ <- A.iSeq.Bb.*?(B.s.a1).query.get.map(_.sortBy(_._2.headOption.toString) ==> List(
          (Seq(0, 1), List()), //                     nothing updated since this A entity has no ref to B
          (Seq(4, 5), List("a")), //                  A attribute inserted
          (Seq(1, 2, 3, 4, 4, 5), List("b", "c")), // A attribute updated
          (Seq(2, 3, 3, 4, 4, 5), List("d", "e")), // A attribute updated
        ))
      } yield ()
    }


    "ref - filter/value" - refs { implicit conn =>
      for {
        // will not be updated since entities have no A -> B relationship
        _ <- B.s("x").iSeq(Seq(0, 1)).save.transact
        _ <- A.i(1).save.transact

        // will be updated
        _ <- A.i.Bb.*(B.s_?.iSeq_?).insert(
          (2, List(
            (None, None), // no relationship to B created
            (None, Some(Seq(1, 2))),
            (Some("a"), None),
            (Some("b"), Some(Seq(2, 3))),
          ))
        ).transact

        // Filter by B attribute, update B values
        _ <- A.Bb.s_.iSeq.add(3, 4).update.transact

        _ <- A.i.a1.Bb.*?(B.s_?.a1.iSeq_?).query.get.map(_ ==> List(
          (1, List()), //                          no change to entity without relationship to B
          (2, List(
            // (None, None),                       no relationship to B
            (None, Some(Seq(1, 2))), //            no change without filter match
            (Some("a"), None), //                  no B attribute to update
            (Some("b"), Some(Seq(2, 3, 3, 4))), // B attribute updated
          ))
        ))

        // Filter by B attribute, upsert B values
        _ <- A.Bb.s_.iSeq.add(4, 5).upsert.transact

        _ <- A.i.a1.Bb.*?(B.s_?.a1.iSeq_?).query.get.map(_ ==> List(
          (1, List()), //                                no change to entity without relationship to B
          (2, List(
            // (None, None),                             no relationship to B
            (None, Some(Seq(1, 2))), //                  no change without filter match
            (Some("a"), Some(Seq(4, 5))), //             B attribute added
            (Some("b"), Some(Seq(2, 3, 3, 4, 4, 5))), // B attribute updated
          ))
        ))

        _ <- B.s_?.a1.iSeq.query.get.map(_ ==> List(
          (None, Seq(1, 2)),
          (Some("a"), Seq(4, 5)),
          (Some("b"), Seq(2, 3, 3, 4, 4, 5)),
          (Some("x"), Seq(0, 1)), // no change to entity without relationship from A
        ))
      } yield ()
    }
  }
}
