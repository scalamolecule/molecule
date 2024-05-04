package molecule.coreTests.spi.crud.update2.relations.many

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait Many_Seq_remove extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "id-filter - ref - value" - refs { implicit conn =>
      for {
        List(a, b, c, d, e, f) <- A.i.a1.Bb.*?(B.s_?.iSeq_?).insert(
          (1, List()),
          (2, List((Some("a"), None))),
          (3, List((Some("b"), None), (Some("c"), None))),
          (4, List((Some("d"), Some(Seq(1, 2, 1))))),
          (5, List((Some("e"), Some(Seq(2, 3, 2))), (Some("f"), Some(Seq(3, 4, 4))))),
          (6, List((Some("g"), Some(Seq(4, 5, 4))), (Some("h"), None))),
        ).transact.map(_.ids)

        // Filter by A ids, update B values
        _ <- A(a, b, c, d, e, f).Bb.iSeq.remove(4, 5).update.transact

        _ <- A.i.a1.Bb.*?(B.s_?.iSeq_?).query.get.map(_ ==> List(
          (1, List()),
          (2, List((Some("a"), None))),
          (3, List((Some("b"), None), (Some("c"), None))),
          (4, List((Some("d"), Some(Seq(1, 2, 1))))),
          (5, List((Some("e"), Some(Seq(2, 3, 2))), (Some("f"), Some(Seq(3))))), // update of last nested entity
          (6, List((Some("g"), None), (Some("h"), None))), //                       update of first nested entity
        ))
      } yield ()
    }


    "filter - ref - value" - refs { implicit conn =>
      for {
        _ <- A.i.Bb.*?(B.s_?.iSeq_?).insert(
          (1, List()),
          (2, List((Some("a"), None))),
          (3, List((Some("b"), None), (Some("c"), None))),
          (4, List((Some("d"), Some(Seq(1, 2, 1))))),
          (5, List((Some("e"), Some(Seq(2, 3, 2))), (Some("f"), Some(Seq(3, 4, 4))))),
          (6, List((Some("g"), Some(Seq(4, 5, 4))), (Some("h"), None))),
        ).transact

        // Filter by A ids, update B values
        _ <- A.i_.Bb.iSeq.remove(4, 5).update.transact

        _ <- A.i.a1.Bb.*?(B.s_?.iSeq_?).query.get.map(_ ==> List(
          (1, List()),
          (2, List((Some("a"), None))),
          (3, List((Some("b"), None), (Some("c"), None))),
          (4, List((Some("d"), Some(Seq(1, 2, 1))))),
          (5, List((Some("e"), Some(Seq(2, 3, 2))), (Some("f"), Some(Seq(3))))), // update of last nested entity
          (6, List((Some("g"), None), (Some("h"), None))), //                       update of first nested entity
        ))
      } yield ()
    }


    "value - ref - filter" - refs { implicit conn =>
      for {
        _ <- A.iSeq.Bb.*?(B.s).insert(
          (Seq(1, 2, 3), List()),
          (Seq(1, 2, 1), List("a")),
          (Seq(2, 3, 2), List("b", "c")),
          (Seq(3, 4, 3), List("d", "e")),
        ).transact.map(_.ids)

        // Filter by B attribute, update A values
        _ <- A.iSeq.remove(3, 4).Bb.s_.update.transact

        // 2 A entities updated
        _ <- A.iSeq_?.Bb.*(B.s.a1).query.get.map(_.sortBy(_._2.headOption.toString) ==> List(
          (Some(Seq(1, 2, 1)), List("a")),
          (Some(Seq(2, 2)), List("b", "c")), // 1 value removed
          (None, List("d", "e")), //            all 3 values removed (refs to B still exist)
        ))

        // Initial entity without ref to B was not updated (3 not removed)
        _ <- A.iSeq.bb_().query.get.map(_ ==> List(Seq(1, 2, 3)))
      } yield ()
    }


    "ref - filter/value" - refs { implicit conn =>
      for {
        // Will not be updated since entity has no A -> B relationship
        _ <- B.s("x").iSeq(Seq(1, 2, 3)).save.transact

        _ <- A.i.Bb.*?(B.s_?.iSeq_?).insert(
          (0, List()),
          (1, List(
            (None, None), // no B entity created
            (None, Some(Seq(0, 1, 0))),
            (Some("a"), None),
            (Some("b"), Some(Seq(1, 2, 1))),
            (Some("c"), Some(Seq(2, 3, 2))),
            (Some("d"), Some(Seq(3, 4, 3))),
          ))
        ).transact

        // Filter by B attribute, update B values
        _ <- A.Bb.s_.iSeq.remove(3, 4).update.transact

        _ <- A.i.a1.Bb.*?(B.s_?.a1.iSeq_?).query.get.map(_ ==> List(
          (0, List()), //                       no B entities to update
          (1, List(
            // (None, None),                    no B entity created
            (None, Some(Seq(0, 1, 0))), //      no change without filter match
            (Some("a"), None), //               no values to remove
            (Some("b"), Some(Seq(1, 2, 1))), // no matching values removed
            (Some("c"), Some(Seq(2, 2))), //    1 value removed
            (Some("d"), None), //               all 3 values removed
          ))
        ))

        _ <- B.s_?.a1.iSeq.query.get.map(_ ==> List(
          (None, Seq(0, 1, 0)),
          (Some("b"), Seq(1, 2, 1)),
          (Some("c"), Seq(2, 2)),
          (Some("x"), Seq(1, 2, 3)), // not update without relationship from A
        ))
      } yield ()
    }
  }
}
