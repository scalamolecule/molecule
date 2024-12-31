package molecule.coreTests.spi.action.update.relation.many

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.domains.dsl.Refs._
import molecule.coreTests.setup._

case class Many_One(
  suite: MUnitSuite,
  api: Api_async with Spi_async with DbProviders
) extends TestUtils {

  import api._
  import suite._

  "id-filter - ref - value" - refs { implicit conn =>
    for {
      List(a, b, c, d, e, f) <- A.i.Bb.*?(B.s_?.i_?).insert(
        (1, List()),
        (2, List((Some("a"), None))),
        (3, List((Some("b"), None), (Some("c"), None))),
        (4, List((Some("d"), Some(1)))),
        (5, List((Some("e"), Some(2)), (Some("f"), Some(3)))),
        (6, List((Some("g"), Some(4)), (Some("h"), None))),
      ).transact.map(_.ids)

      // Filter by A ids, update B values
      _ <- A(a, b, c, d, e, f).Bb.i(4).update.transact

      _ <- A.i.a1.Bb.*?(B.s_?.a1.i).query.get.map(_ ==> List(
        (1, List()), //                                no B.i value
        (2, List()), //                                no B.i value
        (3, List()), //                                no B.i value
        (4, List((Some("d"), 4))), //                  update in 1 ref entity
        (5, List((Some("e"), 4), (Some("f"), 4))), //  update in 2 ref entities
        (6, List((Some("g"), 4))), //                  already had same value
      ))

      // Filter by A ids, upsert B values
      _ <- A(a, b, c, d, e, f).Bb.i(5).upsert.transact

      _ <- A.i.a1.Bb.*?(B.s_?.a1.i).query.get.map(_ ==> List(
        (1, List((None, 5))), //                       ref + insert
        (2, List((Some("a"), 5))), //                  addition in 1 ref entity
        (3, List((Some("b"), 5), (Some("c"), 5))), //  addition in 2 ref entities
        (4, List((Some("d"), 5))), //                  update in 1 ref entity
        (5, List((Some("e"), 5), (Some("f"), 5))), //  update in 2 ref entities
        (6, List((Some("g"), 5), (Some("h"), 5))), //  update in one ref entity and insertion in another
      ))
    } yield ()
  }


  "filter - ref - value" - refs { implicit conn =>
    for {
      _ <- A.i.Bb.*?(B.s_?.i_?).insert(
        (1, List()),
        (2, List((Some("a"), None))),
        (3, List((Some("b"), None), (Some("c"), None))),
        (4, List((Some("d"), Some(1)))),
        (5, List((Some("e"), Some(2)), (Some("f"), Some(3)))),
        (6, List((Some("g"), Some(4)), (Some("h"), None))),
      ).transact

      // Filter by A ids, update B values
      _ <- A.i_.Bb.i(4).update.transact

      _ <- A.i.a1.Bb.*?(B.s_?.a1.i).query.get.map(_ ==> List(
        (1, List()), //                               no B.i value
        (2, List()), //                               no B.i value
        (3, List()), //                               no B.i value
        (4, List((Some("d"), 4))), //                 update in 1 ref entity
        (5, List((Some("e"), 4), (Some("f"), 4))), // update in 2 ref entities
        (6, List((Some("g"), 4))), //                 already had same value
      ))

      // Filter by A ids, upsert B values
      _ <- A.i_.Bb.i(5).upsert.transact

      _ <- A.i.a1.Bb.*?(B.s_?.a1.i).query.get.map(_ ==> List(
        (1, List((None, 5))), //                      ref + insert
        (2, List((Some("a"), 5))), //                 addition in 1 ref entity
        (3, List((Some("b"), 5), (Some("c"), 5))), // addition in 2 ref entities
        (4, List((Some("d"), 5))), //                 update in 1 ref entity
        (5, List((Some("e"), 5), (Some("f"), 5))), // update in 2 ref entities
        (6, List((Some("g"), 5), (Some("h"), 5))), // update in one ref entity and insertion in another
      ))
    } yield ()
  }


  "value - ref - filter" - refs { implicit conn =>
    for {
      _ <- A.i(0).save.transact.map(_.id)
      _ <- A.s.Bb.*(B.s).insert(("x", List("a", "b"))).transact.map(_.id)
      _ <- A.i.Bb.*(B.s).insert((2, List("c", "d"))).transact.map(_.id)

      // Filter by B attribute, update A values
      _ <- A.i(3).Bb.s_.update.transact

      _ <- A.i.Bb.*?(B.s.a1).query.get.map(_.sortBy(_._2.headOption.toString) ==> List(
        (0, List()), //         Nothing updated since this A entity has no ref to B
        (3, List("c", "d")), // A attribute updated
      ))

      // Filter by B attribute, upsert A values
      _ <- A.i(4).Bb.s_.upsert.transact

      _ <- A.i.Bb.*?(B.s.a1).query.get.map(_.sortBy(_._2.headOption.toString) ==> List(
        (0, List()), //         Nothing updated since this A entity has no ref to B
        (4, List("a", "b")), // A attribute inserted
        (4, List("c", "d")), // A attribute updated
      ))
    } yield ()
  }


  "ref - filter/value" - refs { implicit conn =>
    for {
      // will not be updated since entities have no A -> B relationship
      _ <- B.s("x").i(0).save.transact
      _ <- A.i(1).save.transact

      // will be updated
      _ <- A.i.Bb.*(B.s_?.i_?).insert(
        (2, List(
          (None, None), // (no relationship to B created)
          (None, Some(1)),
          (Some("a"), None),
          (Some("b"), Some(2)),
        ))
      ).transact

      // Filter by B attribute, update B values
      _ <- A.Bb.s_.i(3).update.transact

      _ <- A.i.a1.Bb.*?(B.s_?.a1.i_?).query.get.map(_ ==> List(
        (1, List()), // No change to entity without relationship to B
        (2, List(
          // (None, None),         (no relationship to B)
          (None, Some(1)), //      No change without filter match
          (Some("a"), None), //    No change without filter match
          (Some("b"), Some(3)), // B attribute updated
        ))
      ))

      // Filter by B attribute, upsert B values
      _ <- A.Bb.s_.i(4).upsert.transact

      _ <- A.i.a1.Bb.*?(B.s_?.a1.i_?).query.get.map(_ ==> List(
        (1, List()), // No change to entity without relationship to B
        (2, List(
          // (None, None),         (no relationship to B)
          (None, Some(1)), //      No change without filter match
          (Some("a"), Some(4)), // B attribute inserted
          (Some("b"), Some(4)), // B attribute updated
        ))
      ))

      _ <- B.s.a1.i.query.get.map(_ ==> List(
        ("a", 4),
        ("b", 4),
        ("x", 0), // No change to entity without relationship from A
      ))
    } yield ()
  }


  "ref ref" - refs { implicit conn =>
    for {
      id <- A.i(1).Bb.i(2).Cc.i(3).save.transact.map(_.id)
      _ <- A.i.Bb.i.Cc.i.query.get.map(_ ==> List((1, 2, 3)))

      // A
      _ <- A(id).i(10).update.transact
      _ <- A.i.Bb.i.Cc.i.query.get.map(_ ==> List((10, 2, 3)))

      // A + B
      _ <- A(id).i(11).Bb.i(20).update.transact
      _ <- A.i.Bb.i.Cc.i.query.get.map(_ ==> List((11, 20, 3)))

      // B
      _ <- A(id).Bb.i(21).update.transact
      _ <- A.i.Bb.i.Cc.i.query.get.map(_ ==> List((11, 21, 3)))

      // A + B + C
      _ <- A(id).i(12).Bb.i(22).Cc.i(30).update.transact
      _ <- A.i.Bb.i.Cc.i.query.get.map(_ ==> List((12, 22, 30)))

      // A + C
      _ <- A(id).i(13).Bb.Cc.i(31).update.transact
      _ <- A.i.Bb.i.Cc.i.query.get.map(_ ==> List((13, 22, 31)))

      // B + C
      _ <- A(id).Bb.i(23).Cc.i(32).update.transact
      _ <- A.i.Bb.i.Cc.i.query.get.map(_ ==> List((13, 23, 32)))

      // C
      _ <- A(id).Bb.Cc.i(33).update.transact
      _ <- A.i.Bb.i.Cc.i.query.get.map(_ ==> List((13, 23, 33)))
    } yield ()
  }


  "backref" - refs { implicit conn =>
    for {
      id <- A.i(1).Bb.i(2)._A.Cc.i(3).save.transact.map(_.id)
      _ <- A.i.Bb.i._A.Cc.i.query.get.map(_ ==> List((1, 2, 3)))

      // Updating A.Bb.i and A.Cc.i
      _ <- A(id).i(10).Bb.i(20)._A.Cc.i(30).update.transact
      _ <- A.i.Bb.i._A.Cc.i.query.get.map(_ ==> List((10, 20, 30)))
    } yield ()
  }
}
