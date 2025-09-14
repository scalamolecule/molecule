package molecule.db.compliance.test.crud.update.relation.many

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Refs.*
import molecule.db.compliance.setup.DbProviders

case class Many_Seq(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "id-filter - ref - value" - refs {
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
      _ <- A(a, b, c, d, e, f).Bb.iSeq(Seq(4, 5)).update.transact

      _ <- A.i.a1.Bb.*?(B.s_?.a1.iSeq).query.get.map(_ ==> List(
        (1, List()), //                                               no B.i value
        (2, List()), //                                               no B.i value
        (3, List()), //                                               no B.i value
        (4, List((Some("d"), Seq(4, 5)))), //                         update in 1 ref entity
        (5, List((Some("e"), Seq(4, 5)), (Some("f"), Seq(4, 5)))), // update in 2 ref entities
        (6, List((Some("g"), Seq(4, 5)))), //                         already had same value
      ))
    } yield ()
  }


  "filter - ref - value" - refs {
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
      _ <- A.i_.Bb.iSeq(Seq(4, 5)).update.transact

      _ <- A.i.a1.Bb.*?(B.s_?.a1.iSeq).query.get.map(_ ==> List(
        (1, List()), //                                               no B.i value
        (2, List()), //                                               no B.i value
        (3, List()), //                                               no B.i value
        (4, List((Some("d"), Seq(4, 5)))), //                         update in 1 ref entity
        (5, List((Some("e"), Seq(4, 5)), (Some("f"), Seq(4, 5)))), // update in 2 ref entities
        (6, List((Some("g"), Seq(4, 5)))), //                         already had same value
      ))
    } yield ()
  }


  "value - ref - filter" - refs {
    for {
      _ <- A.iSeq_?.Bb.*?(B.s).insert(
        (Some(Seq(0, 1)), List()),
        (None, List("a")),
        (Some(Seq(1, 2)), List("b", "c")),
        (Some(Seq(2, 3)), List("d", "e")),
      ).transact

      // Filter by B attribute, update A values
      _ <- A.iSeq(Seq(3, 4)).Bb.s_.update.transact


      _ <- A.iSeq.Bb.*?(B.s.a1).query.get.map(_.sortBy(_._2.headOption.toString) ==> List(
        (Seq(0, 1), List()), //         nothing updated since this A entity has no ref to B
        // (<none>, List("a")), //      no A attribute to update
        (Seq(3, 4), List("b", "c")), // A attribute updated
        (Seq(3, 4), List("d", "e")), // A attribute updated
      ))
    } yield ()
  }


  "ref - filter/value" - refs {
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
      _ <- A.Bb.s_.iSeq(Seq(3, 4)).update.transact

      _ <- A.i.a1.Bb.*?(B.s_?.a1.iSeq_?).query.get.map(_ ==> List(
        (1, List()), //                    no change to entity without relationship to B
        (2, List(
          // (None, None),                 no relationship to B
          (None, Some(Seq(1, 2))), //      no change without filter match
          (Some("a"), None), //            no value to update
          (Some("b"), Some(Seq(3, 4))), // B attribute updated
        ))
      ))
    } yield ()
  }


  "ref ref" - refs {
    for {
      id <- A.iSeq(List(1)).Bb.iSeq(List(2)).Cc.iSeq(List(3)).save.transact.map(_.id)
      _ <- A.iSeq.Bb.iSeq.Cc.iSeq.query.get.map(_ ==> List((List(1), List(2), List(3))))

      // A
      _ <- A(id).iSeq(List(10)).update.transact
      _ <- A.iSeq.Bb.iSeq.Cc.iSeq.query.get.map(_ ==> List((List(10), List(2), List(3))))

      // A + B
      _ <- A(id).iSeq(List(11)).Bb.iSeq(List(20)).update.transact
      _ <- A.iSeq.Bb.iSeq.Cc.iSeq.query.get.map(_ ==> List((List(11), List(20), List(3))))

      // B
      _ <- A(id).Bb.iSeq(List(21)).update.transact
      _ <- A.iSeq.Bb.iSeq.Cc.iSeq.query.get.map(_ ==> List((List(11), List(21), List(3))))

      // A + B + C
      _ <- A(id).iSeq(List(12)).Bb.iSeq(List(22)).Cc.iSeq(List(30)).update.transact
      _ <- A.iSeq.Bb.iSeq.Cc.iSeq.query.get.map(_ ==> List((List(12), List(22), List(30))))

      // A + C
      _ <- A(id).iSeq(List(13)).Bb.Cc.iSeq(List(31)).update.transact
      _ <- A.iSeq.Bb.iSeq.Cc.iSeq.query.get.map(_ ==> List((List(13), List(22), List(31))))

      // B + C
      _ <- A(id).Bb.iSeq(List(23)).Cc.iSeq(List(32)).update.transact
      _ <- A.iSeq.Bb.iSeq.Cc.iSeq.query.get.map(_ ==> List((List(13), List(23), List(32))))

      // C
      _ <- A(id).Bb.Cc.iSeq(List(33)).update.transact
      _ <- A.iSeq.Bb.iSeq.Cc.iSeq.query.get.map(_ ==> List((List(13), List(23), List(33))))
    } yield ()
  }
}
