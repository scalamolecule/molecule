package molecule.db.compliance.test.action.update.relation.many

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Refs.*
import molecule.db.compliance.setup.DbProviders

case class Many_Map_add(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "id-filter - ref - value" - refs {
    for {
      case List(a, b, c, d, e, f) <- A.i.a1.Bb.*?(B.s_?.iMap_?).insert(
        (1, List()),
        (2, List((Some("a"), None))),
        (3, List((Some("b"), None), (Some("c"), None))),
        (4, List((Some("d"), Some(Map(pint1, pint2))))),
        (5, List((Some("e"), Some(Map(pint2, pint3))), (Some("f"), Some(Map(pint3, pint4))))),
        (6, List((Some("g"), Some(Map(pint4, pint5))), (Some("h"), None))),
      ).transact.map(_.ids)

      // Filter by A ids, update B values
      _ <- A(a, b, c, d, e, f).Bb.iMap.add(pint4, pint5).update.transact

      _ <- A.i.a1.Bb.*?(B.s_?.a1.iMap).query.get.map(_ ==> List(
        (1, List()), //                                   no B value to update
        (2, List()), //                                   no B value to update
        (3, List()), //                                   no B value to update
        (4, List( //                                      update in 1 ref entity
          (Some("d"), Map(pint1, pint2, pint4, pint5)))),
        (5, List( //                                      update in 2 ref entities
          (Some("e"), Map(pint2, pint3, pint4, pint5)),
          (Some("f"), Map(pint3, pint4, pint4, pint5)))),
        (6, List( //                                      update one ref entity
          (Some("g"), Map(pint4, pint5, pint4, pint5)))),
      ))
    } yield ()
  }


  "filter - ref - value" - refs {
    for {
      _ <- A.i.a1.Bb.*?(B.s_?.iMap_?).insert(
        (1, List()),
        (2, List((Some("a"), None))),
        (3, List((Some("b"), None), (Some("c"), None))),
        (4, List((Some("d"), Some(Map(pint1, pint2))))),
        (5, List((Some("e"), Some(Map(pint2, pint3))), (Some("f"), Some(Map(pint3, pint4))))),
        (6, List((Some("g"), Some(Map(pint4, pint5))), (Some("h"), None))),
      ).transact.map(_.ids)

      // Filter by A ids, update B values
      _ <- A.i_.Bb.iMap.add(pint4, pint5).update.transact

      _ <- A.i.a1.Bb.*?(B.s_?.a1.iMap).query.get.map(_ ==> List(
        (1, List()), //                                   no B value to update
        (2, List()), //                                   no B value to update
        (3, List()), //                                   no B value to update
        (4, List( //                                      update in 1 ref entity
          (Some("d"), Map(pint1, pint2, pint4, pint5)))),
        (5, List( //                                      update in 2 ref entities
          (Some("e"), Map(pint2, pint3, pint4, pint5)),
          (Some("f"), Map(pint3, pint4, pint4, pint5)))),
        (6, List( //                                      update one ref entity
          (Some("g"), Map(pint4, pint5, pint4, pint5)))),
      ))
    } yield ()
  }


  "value - ref - filter" - refs {
    for {
      _ <- A.iMap_?.Bb.*?(B.s).insert(
        (Some(Map(pint0, pint1)), List()),
        (None, List("a")),
        (Some(Map(pint1, pint2)), List("b", "c")),
        (Some(Map(pint2, pint3)), List("d", "e")),
      ).transact

      // Filter by B attribute, update A values
      _ <- A.iMap.add(pint3, pint4).Bb.s_.update.transact

      _ <- A.iMap.Bb.*?(B.s.a1).query.get.map(_.sortBy(_._2.headOption.toString) ==> List(
        (Map(pint0, pint1), List()), //                       nothing updated since this A entity has no ref to B
        // (<none>, List("a")), //                            no A attribute to update
        (Map(pint1, pint2, pint3, pint4), List("b", "c")), // A attribute updated
        (Map(pint2, pint3, pint3, pint4), List("d", "e")), // A attribute updated
      ))
    } yield ()
  }


  "ref - filter/value" - refs {
    for {
      // will not be updated since entities have no A -> B relationship
      _ <- B.s("x").iMap(Map(pint0, pint1)).save.transact
      _ <- A.i(1).save.transact

      // will be updated
      _ <- A.i.Bb.*(B.s_?.iMap_?).insert(
        (2, List(
          (None, None), // no relationship to B created
          (None, Some(Map(pint1, pint2))),
          (Some("a"), None),
          (Some("b"), Some(Map(pint2, pint3))),
        ))
      ).transact

      // Filter by B attribute, update B values
      _ <- A.Bb.s_.iMap.add(pint3, pint4).update.transact

      _ <- A.i.a1.Bb.*?(B.s_?.a1.iMap_?).query.get.map(_ ==> List(
        (1, List()), //                                          no change to entity without relationship to B
        (2, List(
          // (None, None),                                       no relationship to B
          (None, Some(Map(pint1, pint2))), //                    no change without filter match
          (Some("a"), None), //                                  no B attribute to update
          (Some("b"), Some(Map(pint2, pint3, pint3, pint4))), // B attribute updated
        ))
      ))
    } yield ()
  }
}
