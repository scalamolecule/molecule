package molecule.db.compliance.test.action.update.relation.many

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Refs.*
import molecule.db.compliance.setup.DbProviders

case class Many_One(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "id-filter - ref - value" - refs {
    for {
      case List(a, b, c, d, e, f) <- A.i.Bb.*?(B.s_?.i_?).insert(
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
    } yield ()
  }


  "filter - ref - value" - refs {
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
    } yield ()
  }


  "value - ref - filter" - refs {
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
    } yield ()
  }


  "ref - filter/value" - refs {
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
    } yield ()
  }


  "ref ref" - refs {
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
}