package molecule.db.compliance.test.action.update.relation.many

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Refs.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*

case class Many_Set(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "id-filter - ref - value" - refs {
    for {
      case List(a, b, c, d, e, f) <- A.i.a1.Bb.*?(B.s_?.iSet_?).insert(
        (1, List()),
        (2, List((Some("a"), None))),
        (3, List((Some("b"), None), (Some("c"), None))),
        (4, List((Some("d"), Some(Set(1, 2))))),
        (5, List((Some("e"), Some(Set(2, 3))), (Some("f"), Some(Set(3, 4))))),
        (6, List((Some("g"), Some(Set(4, 5))), (Some("h"), None))),
      ).transact.map(_.ids)

      // Filter by A ids, update B values
      _ <- A(a, b, c, d, e, f).Bb.iSet(Set(4, 5)).update.transact

      _ <- A.i.a1.Bb.*?(B.s_?.iSet).query.get.map(_ ==> List(
        (1, List()), //                                               no B.i value
        (2, List()), //                                               no B.i value
        (3, List()), //                                               no B.i value
        (4, List((Some("d"), Set(4, 5)))), //                         update in 1 ref entity
        (5, List((Some("e"), Set(4, 5)), (Some("f"), Set(4, 5)))), // update in 2 ref entities
        (6, List((Some("g"), Set(4, 5)))), //                         already had same value
      ))

      // Filter by A ids, upsert B values
      _ <- A(a, b, c, d, e, f).Bb.iSet(Set(5, 6)).upsert.transact

      _ <- A.i.a1.Bb.*?(B.s_?.iSet).query.get.map(_ ==> List(
        (1, List((None, Set(5, 6)))), //                              ref + addition
        (2, List((Some("a"), Set(5, 6)))), //                         addition in 1 ref entity
        (3, List((Some("b"), Set(5, 6)), (Some("c"), Set(5, 6)))), // addition in 2 ref entities
        (4, List((Some("d"), Set(5, 6)))), //                         update in 1 ref entity
        (5, List((Some("e"), Set(5, 6)), (Some("f"), Set(5, 6)))), // update in 2 ref entities
        (6, List((Some("g"), Set(5, 6)), (Some("h"), Set(5, 6)))), // update in one ref entity and addition in another
      ))
    } yield ()
  }


  "filter - ref - value" - refs {
    for {
      _ <- A.i.a1.Bb.*?(B.s_?.iSet_?).insert(
        (1, List()),
        (2, List((Some("a"), None))),
        (3, List((Some("b"), None), (Some("c"), None))),
        (4, List((Some("d"), Some(Set(1, 2))))),
        (5, List((Some("e"), Some(Set(2, 3))), (Some("f"), Some(Set(3, 4))))),
        (6, List((Some("g"), Some(Set(4, 5))), (Some("h"), None))),
      ).transact.map(_.ids)

      // Filter by A ids, update B values
      _ <- A.i_.Bb.iSet(Set(4, 5)).update.transact

      _ <- A.i.a1.Bb.*?(B.s_?.iSet).query.get.map(_ ==> List(
        (1, List()), //                                               no B.i value
        (2, List()), //                                               no B.i value
        (3, List()), //                                               no B.i value
        (4, List((Some("d"), Set(4, 5)))), //                         update in 1 ref entity
        (5, List((Some("e"), Set(4, 5)), (Some("f"), Set(4, 5)))), // update in 2 ref entities
        (6, List((Some("g"), Set(4, 5)))), //                         already had same value
      ))

      // Filter by A ids, upsert B values
      _ <- A.i_.Bb.iSet(Set(5, 6)).upsert.transact

      _ <- A.i.a1.Bb.*?(B.s_?.iSet).query.get.map(_ ==> List(
        (1, List((None, Set(5, 6)))), //                              ref + addition
        (2, List((Some("a"), Set(5, 6)))), //                         addition in 1 ref entity
        (3, List((Some("b"), Set(5, 6)), (Some("c"), Set(5, 6)))), // addition in 2 ref entities
        (4, List((Some("d"), Set(5, 6)))), //                         update in 1 ref entity
        (5, List((Some("e"), Set(5, 6)), (Some("f"), Set(5, 6)))), // update in 2 ref entities
        (6, List((Some("g"), Set(5, 6)), (Some("h"), Set(5, 6)))), // update in one ref entity and addition in another
      ))
    } yield ()
  }


  "value - ref - filter" - refs {
    for {
      _ <- A.iSet_?.Bb.*?(B.s).insert(
        (Some(Set(0, 1)), List()),
        (None, List("a")),
        (Some(Set(1, 2)), List("b", "c")),
        (Some(Set(2, 3)), List("d", "e")),
      ).transact

      // Filter by B attribute, update A values
      _ <- A.iSet(Set(3, 4)).Bb.s_.update.transact


      _ <- A.iSet.Bb.*?(B.s.a1).query.get.map(_.sortBy(_._2.headOption.toString) ==> List(
        (Set(0, 1), List()), //         nothing updated since this A entity has no ref to B
        // (<none>, List("a")), //      no A attribute to update
        (Set(3, 4), List("b", "c")), // A attribute updated
        (Set(3, 4), List("d", "e")), // A attribute updated
      ))

      // Filter by B attribute, update A values
      _ <- A.iSet(Set(4, 5)).Bb.s_.upsert.transact

      _ <- A.iSet.Bb.*?(B.s.a1).query.get.map(_.sortBy(_._2.headOption.toString) ==> List(
        (Set(0, 1), List()), //         nothing updated since this A entity has no ref to B
        (Set(4, 5), List("a")), //      A attribute inserted
        (Set(4, 5), List("b", "c")), // A attribute updated
        (Set(4, 5), List("d", "e")), // A attribute updated
      ))
    } yield ()
  }


  "ref - filter/value" - refs {
    for {
      // will not be updated since entities have no A -> B relationship
      _ <- B.s("x").iSet(Set(0, 1)).save.transact
      _ <- A.i(1).save.transact

      // will be updated
      _ <- A.i.Bb.*(B.s_?.iSet_?).insert(
        (2, List(
          (None, None), // no relationship to B created
          (None, Some(Set(1, 2))),
          (Some("a"), None),
          (Some("b"), Some(Set(2, 3))),
        ))
      ).transact

      // Filter by B attribute, update B values
      _ <- A.Bb.s_.iSet(Set(3, 4)).update.transact

      _ <- A.i.a1.Bb.*?(B.s_?.a1.iSet_?).query.get.map(_ ==> List(
        (1, List()), //                    no change to entity without relationship to B
        (2, List(
          // (None, None),                 no relationship to B
          (None, Some(Set(1, 2))), //      no change without filter match
          (Some("a"), None), //            no value to update
          (Some("b"), Some(Set(3, 4))), // B attribute updated
        ))
      ))

      // Filter by B attribute, update B values
      _ <- A.Bb.s_.iSet(Set(4, 5)).upsert.transact

      _ <- A.i.a1.Bb.*?(B.s_?.a1.iSet_?).query.get.map(_ ==> List(
        (1, List()), //                    no change to entity without relationship to B
        (2, List(
          // (None, None),                 no relationship to B
          (None, Some(Set(1, 2))), //      no change without filter match
          (Some("a"), Some(Set(4, 5))), // B attribute inserted
          (Some("b"), Some(Set(4, 5))), // B attribute updated
        ))
      ))

      _ <- B.s_?.a1.iSet.query.get.map(_ ==> List(
        (None, Set(1, 2)),
        (Some("a"), Set(4, 5)),
        (Some("b"), Set(4, 5)),
        (Some("x"), Set(0, 1)), // no change to entity without relationship from A
      ))
    } yield ()
  }


  "ref ref" - refs {
    for {
      id <- A.iSet(Set(1)).Bb.iSet(Set(2)).Cc.iSet(Set(3)).save.transact.map(_.id)
      _ <- A.iSet.Bb.iSet.Cc.iSet.query.get.map(_ ==> List((Set(1), Set(2), Set(3))))

      // A
      _ <- A(id).iSet(Set(10)).update.transact
      _ <- A.iSet.Bb.iSet.Cc.iSet.query.get.map(_ ==> List((Set(10), Set(2), Set(3))))

      // A + B
      _ <- A(id).iSet(Set(11)).Bb.iSet(Set(20)).update.transact
      _ <- A.iSet.Bb.iSet.Cc.iSet.query.get.map(_ ==> List((Set(11), Set(20), Set(3))))

      // B
      _ <- A(id).Bb.iSet(Set(21)).update.transact
      _ <- A.iSet.Bb.iSet.Cc.iSet.query.get.map(_ ==> List((Set(11), Set(21), Set(3))))

      // A + B + C
      _ <- A(id).iSet(Set(12)).Bb.iSet(Set(22)).Cc.iSet(Set(30)).update.transact
      _ <- A.iSet.Bb.iSet.Cc.iSet.query.get.map(_ ==> List((Set(12), Set(22), Set(30))))

      // A + C
      _ <- A(id).iSet(Set(13)).Bb.Cc.iSet(Set(31)).update.transact
      _ <- A.iSet.Bb.iSet.Cc.iSet.query.get.map(_ ==> List((Set(13), Set(22), Set(31))))

      // B + C
      _ <- A(id).Bb.iSet(Set(23)).Cc.iSet(Set(32)).update.transact
      _ <- A.iSet.Bb.iSet.Cc.iSet.query.get.map(_ ==> List((Set(13), Set(23), Set(32))))

      // C
      _ <- A(id).Bb.Cc.iSet(Set(33)).update.transact
      _ <- A.iSet.Bb.iSet.Cc.iSet.query.get.map(_ ==> List((Set(13), Set(23), Set(33))))
    } yield ()
  }


  "backref" - refs {
    for {
      id <- A.iSet(Set(1)).Bb.iSet(Set(2))._A.Cc.iSet(Set(3)).save.transact.map(_.id)
      _ <- A.iSet.Bb.iSet._A.Cc.iSet.query.get.map(_ ==> List((Set(1), Set(2), Set(3))))

      // Updating A.Bb.iSet and A.Cc.iSet
      _ <- A(id).iSet(Set(10)).Bb.iSet(Set(20))._A.Cc.iSet(Set(30)).update.transact
      _ <- A.iSet.Bb.iSet._A.Cc.iSet.query.get.map(_ ==> List((Set(10), Set(20), Set(30))))
    } yield ()
  }
}
