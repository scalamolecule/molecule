package molecule.db.compliance.test.crud.update.relation.many

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Refs.*
import molecule.db.compliance.setup.DbProviders

case class Many_Set_add(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "id-filter - ref - value" - refs {
    for {
      List(a, b, c, d, e, f) <- A.i.a1.Bb.*?(B.s_?.iSet_?).insert(
        (1, List()),
        (2, List((Some("a"), None))),
        (3, List((Some("b"), None), (Some("c"), None))),
        (4, List((Some("d"), Some(Set(1, 2))))),
        (5, List((Some("e"), Some(Set(2, 3))), (Some("f"), Some(Set(3, 4))))),
        (6, List((Some("g"), Some(Set(4, 5))), (Some("h"), None))),
      ).transact.map(_.ids)

      // Filter by A ids, update B values
      _ <- A(a, b, c, d, e, f).Bb.iSet.add(4, 5).update.transact

      _ <- A.i.a1.Bb.*?(B.s_?.iSet).query.get.map(_ ==> List(
        (1, List()), //                                                        no B value to update
        (2, List()), //                                                        no B value to update
        (3, List()), //                                                        no B value to update
        (4, List((Some("d"), Set(1, 2, 4, 5)))), //                            update in 1 ref entity
        (5, List((Some("e"), Set(2, 3, 4, 5)), (Some("f"), Set(3, 4, 5)))), // update in 2 ref entities
        (6, List((Some("g"), Set(4, 5)))), //                                  update, but already has same values
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
      _ <- A.i_.Bb.iSet.add(4, 5).update.transact

      _ <- A.i.a1.Bb.*?(B.s_?.iSet).query.get.map(_ ==> List(
        (1, List()), //                                                        no B value to update
        (2, List()), //                                                        no B value to update
        (3, List()), //                                                        no B value to update
        (4, List((Some("d"), Set(1, 2, 4, 5)))), //                            update in 1 ref entity
        (5, List((Some("e"), Set(2, 3, 4, 5)), (Some("f"), Set(3, 4, 5)))), // update in 2 ref entities
        (6, List((Some("g"), Set(4, 5)))), //                                  update, but already has same values
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
      _ <- A.iSet.add(3, 4).Bb.s_.update.transact

      _ <- A.iSet.Bb.*?(B.s.a1).query.get.map(_.sortBy(_._2.headOption.toString) ==> List(
        (Set(0, 1), List()), //               nothing updated since this A entity has no ref to B
        // (<none>, List("a")), //            no A attribute to update
        (Set(1, 2, 3, 4), List("b", "c")), // A attribute updated
        (Set(2, 3, 4), List("d", "e")), //    A attribute updated
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
      _ <- A.Bb.s_.iSet.add(3, 4).update.transact

      _ <- A.i.a1.Bb.*?(B.s_?.a1.iSet_?).query.get.map(_ ==> List(
        (1, List()), // no change to entity without relationship to B
        (2, List(
          // (None, None),                    no relationship to B
          (None, Some(Set(1, 2))), //         no change without filter match
          (Some("a"), None), //               no B attribute to update
          (Some("b"), Some(Set(2, 3, 4))), // B attribute updated
        ))
      ))
    } yield ()
  }
}
