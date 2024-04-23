package molecule.coreTests.spi.crud.update2.relations.many

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait Many_One extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "id-filter - ref - value" - refs { implicit conn =>
      for {
        List(a, b, c, d, e, f) <- A.i.a1.Bb.*?(B.s_?.i_?).insert(
          (1, List()),
          (2, List((Some("a"), None))),
          (3, List((Some("b"), None), (Some("c"), None))),
          (4, List((Some("d"), Some(1)))),
          (5, List((Some("e"), Some(2)), (Some("f"), Some(3)))),
          (6, List((Some("g"), Some(4)), (Some("h"), None))),
        ).transact.map(_.ids)

        // Filter by A ids, update B values
        _ <- A(a, b, c, d, e, f).Bb.i(4).update.transact

        _ <- A.i.a1.Bb.*?(B.s_?.i).query.get.map(_ ==> List(
          (1, List((None, 4))), //                       ref + addition
          (2, List((Some("a"), 4))), //                  addition in 1 ref entity
          (3, List((Some("b"), 4), (Some("c"), 4))), //  addition in 2 ref entities
          (4, List((Some("d"), 4))), //                  update in 1 ref entity
          (5, List((Some("e"), 4), (Some("f"), 4))), //  update in 2 ref entities
          (6, List((Some("g"), 4), (Some("h"), 4))), //  update in one ref entity and addition in another
        ))
      } yield ()
    }


    "filter - ref - value" - refs { implicit conn =>
      for {
        _ <- A.i.a1.Bb.*?(B.s_?.i_?).insert(
          (1, List()),
          (2, List((Some("a"), None))),
          (3, List((Some("b"), None), (Some("c"), None))),
          (4, List((Some("d"), Some(1)))),
          (5, List((Some("e"), Some(2)), (Some("f"), Some(3)))),
          (6, List((Some("g"), Some(4)), (Some("h"), None))),
        ).transact.map(_.ids)

        // Filter by A ids, update B values
        _ <- A.i_.Bb.i(4).update.transact

        _ <- A.i.a1.Bb.*?(B.s_?.i).query.get.map(_ ==> List(
          (1, List((None, 4))), //                       ref + addition
          (2, List((Some("a"), 4))), //                  addition in 1 ref entity
          (3, List((Some("b"), 4), (Some("c"), 4))), //  addition in 2 ref entities
          (4, List((Some("d"), 4))), //                  update in 1 ref entity
          (5, List((Some("e"), 4), (Some("f"), 4))), //  update in 2 ref entities
          (6, List((Some("g"), 4), (Some("h"), 4))), //  update in one ref entity and addition in another
        ))
      } yield ()
    }


    "value - ref - filter" - refs { implicit conn =>
      for {
        _ <- A.i(0).save.transact.map(_.id)
        _ <- A.Bb.*(B.s).insert(List("a", "b")).transact.map(_.id)
        _ <- A.i.Bb.*(B.s).insert((2, List("c", "d"))).transact.map(_.id)

        // Filter by B attribute, update A values
        _ <- A.i(3).Bb.s_.update.transact

        _ <- A.i.Bb.*?(B.s).query.get.map(_.sortBy(_._2.headOption.toString) ==> List(
          (0, List()), //         Nothing updated since this A entity has no ref to B
          (3, List("a", "b")), // A attribute added
          (3, List("c", "d")), // A attribute updated
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
            (Some("a"), Some(3)), // B attribute added
            (Some("b"), Some(3)), // B attribute updated
          ))
        ))

        _ <- B.s.a1.i.query.get.map(_ ==> List(
          ("a", 3),
          ("b", 3),
          ("x", 0), // No change to entity without relationship from A
        ))
      } yield ()
    }
  }
}
