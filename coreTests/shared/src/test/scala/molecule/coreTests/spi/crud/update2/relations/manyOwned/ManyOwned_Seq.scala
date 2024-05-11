package molecule.coreTests.spi.crud.update2.relations.manyOwned

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait ManyOwned_Seq extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "id-filter - ref - value" - refs { implicit conn =>
      for {
        List(a, b, c, d, e, f) <- A.i.a1.OwnBb.*?(B.s_?.iSeq_?).insert(
          (1, List()),
          (2, List((Some("a"), None))),
          (3, List((Some("b"), None), (Some("c"), None))),
          (4, List((Some("d"), Some(Seq(1, 2))))),
          (5, List((Some("e"), Some(Seq(2, 3))), (Some("f"), Some(Seq(3, 4))))),
          (6, List((Some("g"), Some(Seq(4, 5))), (Some("h"), None))),
        ).transact.map(_.ids)

        // Filter by A ids, update B values
        _ <- A(a, b, c, d, e, f).OwnBb.iSeq(Seq(4, 5)).update.transact

        _ <- A.i.a1.OwnBb.*?(B.s_?.iSeq).query.get.map(_ ==> List(
          (1, List((None, Seq(4, 5)))), //                              ref + addition
          (2, List((Some("a"), Seq(4, 5)))), //                         addition in 1 ref entity
          (3, List((Some("b"), Seq(4, 5)), (Some("c"), Seq(4, 5)))), // addition in 2 ref entities
          (4, List((Some("d"), Seq(4, 5)))), //                         update in 1 ref entity
          (5, List((Some("e"), Seq(4, 5)), (Some("f"), Seq(4, 5)))), // update in 2 ref entities
          (6, List((Some("g"), Seq(4, 5)), (Some("h"), Seq(4, 5)))), // update in one ref entity and addition in another
        ))
      } yield ()
    }


    "filter - ref - value" - refs { implicit conn =>
      for {
        _ <- A.i.OwnBb.*?(B.s_?.iSeq_?).insert(
          (1, List()),
          (2, List((Some("a"), None))),
          (3, List((Some("b"), None), (Some("c"), None))),
          (4, List((Some("d"), Some(Seq(1, 2))))),
          (5, List((Some("e"), Some(Seq(2, 3))), (Some("f"), Some(Seq(3, 4))))),
          (6, List((Some("g"), Some(Seq(4, 5))), (Some("h"), None))),
        ).transact

        // Filter by A ids, update B values
        _ <- A.i_.OwnBb.iSeq(Seq(4, 5)).update.transact

        _ <- A.i.a1.OwnBb.*?(B.s_?.iSeq).query.get.map(_ ==> List(
          (1, List((None, Seq(4, 5)))), //                              ref + addition
          (2, List((Some("a"), Seq(4, 5)))), //                         addition in 1 ref entity
          (3, List((Some("b"), Seq(4, 5)), (Some("c"), Seq(4, 5)))), // addition in 2 ref entities
          (4, List((Some("d"), Seq(4, 5)))), //                         update in 1 ref entity
          (5, List((Some("e"), Seq(4, 5)), (Some("f"), Seq(4, 5)))), // update in 2 ref entities
          (6, List((Some("g"), Seq(4, 5)), (Some("h"), Seq(4, 5)))), // update in one ref entity and addition in another
        ))
      } yield ()
    }


    "value - ref - filter" - refs { implicit conn =>
      for {
        _ <- A.iSeq_?.OwnBb.*?(B.s).insert(
          (Some(Seq(0, 1)), List()),
          (None, List("a")),
          (Some(Seq(1, 2)), List("b", "c")),
          (Some(Seq(2, 3)), List("d", "e")),
        ).transact

        // Filter by B attribute, update A values
        _ <- A.iSeq(Seq(3, 4)).OwnBb.s_.update.transact

        _ <- A.iSeq.OwnBb.*?(B.s).query.get.map(_.sortBy(_._2.headOption.toString) ==> List(
          (Seq(0, 1), List()), //         nothing updated since this A entity has no ref to B
          (Seq(3, 4), List("a")), //      A attribute added
          (Seq(3, 4), List("b", "c")), // A attribute updated
          (Seq(3, 4), List("d", "e")), // A attribute updated
        ))
      } yield ()
    }


    "ref - filter/value" - refs { implicit conn =>
      for {
        // will not be updated since entities have no A -> B relationship
        _ <- B.s("x").iSeq(Seq(0, 1)).save.transact
        _ <- A.i(1).save.transact

        // will be updated
        _ <- A.i.OwnBb.*(B.s_?.iSeq_?).insert(
          (2, List(
            (None, None), // no relationship to B created
            (None, Some(Seq(1, 2))),
            (Some("a"), None),
            (Some("b"), Some(Seq(2, 3))),
          ))
        ).transact

        // Filter by B attribute, update B values
        _ <- A.OwnBb.s_.iSeq(Seq(3, 4)).update.transact

        _ <- A.i.a1.OwnBb.*?(B.s_?.a1.iSeq_?).query.get.map(_ ==> List(
          (1, List()), // no change to entity without relationship to B
          (2, List(
            // (None, None),                 no relationship to B
            (None, Some(Seq(1, 2))), //      no change without filter match
            (Some("a"), Some(Seq(3, 4))), // B attribute added
            (Some("b"), Some(Seq(3, 4))), // B attribute updated
          ))
        ))

        _ <- if (database == "MongoDB") {
          // Embedded documents in Mongo have no separate entity ids
          B.s_?.a1.iSeq.query.get.map(_ ==> List(
            (Some("x"), Seq(0, 1)), // no change to entity without relationship from A
          ))
        } else {
          B.s_?.a1.iSeq.query.get.map(_ ==> List(
            (None, Seq(1, 2)),
            (Some("a"), Seq(3, 4)),
            (Some("b"), Seq(3, 4)),
            (Some("x"), Seq(0, 1)), // no change to entity without relationship from A
          ))
        }
      } yield ()
    }


    "own ref" - refs { implicit conn =>
      if (database != "MongoDB") {
        //
        // Mixing cardinality-many updates with further nested
        // related/embedded documents not implemented for Mongodb.
        for {
          id <- A.iSeq(List(1)).OwnBb.iSeq(List(2)).Cc.iSeq(List(3)).save.transact.map(_.id)
          _ <- A.iSeq.OwnBb.iSeq.Cc.iSeq.query.get.map(_ ==> List((List(1), List(2), List(3))))

          // A
          _ <- A(id).iSeq(List(10)).update.transact
          _ <- A.iSeq.OwnBb.iSeq.Cc.iSeq.query.get.map(_ ==> List((List(10), List(2), List(3))))

          // A + B
          _ <- A(id).iSeq(List(11)).OwnBb.iSeq(List(20)).update.transact
          _ <- A.iSeq.OwnBb.iSeq.Cc.iSeq.query.get.map(_ ==> List((List(11), List(20), List(3))))

          // B
          _ <- A(id).OwnBb.iSeq(List(21)).update.transact
          _ <- A.iSeq.OwnBb.iSeq.Cc.iSeq.query.get.map(_ ==> List((List(11), List(21), List(3))))

          // A + B + C
          _ <- A(id).iSeq(List(12)).OwnBb.iSeq(List(22)).Cc.iSeq(List(30)).update.transact
          _ <- A.iSeq.OwnBb.iSeq.Cc.iSeq.query.get.map(_ ==> List((List(12), List(22), List(30))))

          // A + C
          _ <- A(id).iSeq(List(13)).OwnBb.Cc.iSeq(List(31)).update.transact
          _ <- A.iSeq.OwnBb.iSeq.Cc.iSeq.query.get.map(_ ==> List((List(13), List(22), List(31))))

          // B + C
          _ <- A(id).OwnBb.iSeq(List(23)).Cc.iSeq(List(32)).update.transact
          _ <- A.iSeq.OwnBb.iSeq.Cc.iSeq.query.get.map(_ ==> List((List(13), List(23), List(32))))

          // C
          _ <- A(id).OwnBb.Cc.iSeq(List(33)).update.transact
          _ <- A.iSeq.OwnBb.iSeq.Cc.iSeq.query.get.map(_ ==> List((List(13), List(23), List(33))))
        } yield ()
      }
    }


    "ref own" - refs { implicit conn =>
      if (database != "MongoDB") {
        //
        // Mixing cardinality-many updates with further nested
        // related/embedded documents not implemented for Mongodb.
        for {
          id <- A.iSeq(List(1)).Bb.iSeq(List(2)).OwnCc.iSeq(List(3)).save.transact.map(_.id)
          _ <- A.iSeq.Bb.iSeq.OwnCc.iSeq.query.get.map(_ ==> List((List(1), List(2), List(3))))

          // A
          _ <- A(id).iSeq(List(10)).update.transact
          _ <- A.iSeq.Bb.iSeq.OwnCc.iSeq.query.get.map(_ ==> List((List(10), List(2), List(3))))

          // A + B
          _ <- A(id).iSeq(List(11)).Bb.iSeq(List(20)).update.transact
          _ <- A.iSeq.Bb.iSeq.OwnCc.iSeq.query.get.map(_ ==> List((List(11), List(20), List(3))))

          // B
          _ <- A(id).Bb.iSeq(List(21)).update.transact
          _ <- A.iSeq.Bb.iSeq.OwnCc.iSeq.query.get.map(_ ==> List((List(11), List(21), List(3))))

          // A + B + C
          _ <- A(id).iSeq(List(12)).Bb.iSeq(List(22)).OwnCc.iSeq(List(30)).update.transact
          _ <- A.iSeq.Bb.iSeq.OwnCc.iSeq.query.get.map(_ ==> List((List(12), List(22), List(30))))

          // A + C
          _ <- A(id).iSeq(List(13)).Bb.OwnCc.iSeq(List(31)).update.transact
          _ <- A.iSeq.Bb.iSeq.OwnCc.iSeq.query.get.map(_ ==> List((List(13), List(22), List(31))))

          // B + C
          _ <- A(id).Bb.iSeq(List(23)).OwnCc.iSeq(List(32)).update.transact
          _ <- A.iSeq.Bb.iSeq.OwnCc.iSeq.query.get.map(_ ==> List((List(13), List(23), List(32))))

          // C
          _ <- A(id).Bb.OwnCc.iSeq(List(33)).update.transact
          _ <- A.iSeq.Bb.iSeq.OwnCc.iSeq.query.get.map(_ ==> List((List(13), List(23), List(33))))
        } yield ()
      }
    }


    "own own" - refs { implicit conn =>
      if (database != "MongoDB") {
        //
        // Mixing cardinality-many updates with further nested
        // related/embedded documents not implemented for Mongodb.
        for {
          id <- A.iSeq(List(1)).OwnBb.iSeq(List(2)).OwnCc.iSeq(List(3)).save.transact.map(_.id)
          _ <- A.iSeq.OwnBb.iSeq.OwnCc.iSeq.query.get.map(_ ==> List((List(1), List(2), List(3))))

          // A
          _ <- A(id).iSeq(List(10)).update.transact
          _ <- A.iSeq.OwnBb.iSeq.OwnCc.iSeq.query.get.map(_ ==> List((List(10), List(2), List(3))))

          // A + B
          _ <- A(id).iSeq(List(11)).OwnBb.iSeq(List(20)).update.transact
          _ <- A.iSeq.OwnBb.iSeq.OwnCc.iSeq.query.get.map(_ ==> List((List(11), List(20), List(3))))

          // B
          _ <- A(id).OwnBb.iSeq(List(21)).update.transact
          _ <- A.iSeq.OwnBb.iSeq.OwnCc.iSeq.query.get.map(_ ==> List((List(11), List(21), List(3))))

          // A + B + C
          _ <- A(id).iSeq(List(12)).OwnBb.iSeq(List(22)).OwnCc.iSeq(List(30)).update.transact
          _ <- A.iSeq.OwnBb.iSeq.OwnCc.iSeq.query.get.map(_ ==> List((List(12), List(22), List(30))))

          // A + C
          _ <- A(id).iSeq(List(13)).OwnBb.OwnCc.iSeq(List(31)).update.transact
          _ <- A.iSeq.OwnBb.iSeq.OwnCc.iSeq.query.get.map(_ ==> List((List(13), List(22), List(31))))

          // B + C
          _ <- A(id).OwnBb.iSeq(List(23)).OwnCc.iSeq(List(32)).update.transact
          _ <- A.iSeq.OwnBb.iSeq.OwnCc.iSeq.query.get.map(_ ==> List((List(13), List(23), List(32))))

          // C
          _ <- A(id).OwnBb.OwnCc.iSeq(List(33)).update.transact
          _ <- A.iSeq.OwnBb.iSeq.OwnCc.iSeq.query.get.map(_ ==> List((List(13), List(23), List(33))))
        } yield ()
      }
    }


    "backref" - refs { implicit conn =>
      for {
        id <- A.iSeq(List(1)).OwnBb.iSeq(List(2))._A.Cc.iSeq(List(3)).save.transact.map(_.id)
        _ <- A.iSeq.OwnBb.iSeq._A.Cc.iSeq.query.get.map(_ ==> List((List(1), List(2), List(3))))

        // Updating A.OwnBb.iSeq and A.Cc.iSeq
        _ <- A(id).iSeq(List(10)).OwnBb.iSeq(List(20))._A.Cc.iSeq(List(30)).update.transact
        _ <- A.iSeq.OwnBb.iSeq._A.Cc.iSeq.query.get.map(_ ==> List((List(10), List(20), List(30))))
      } yield ()
    }
  }
}
