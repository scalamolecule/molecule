package molecule.db.compliance.test.relation.flat

import molecule.core.setup.MUnit
import molecule.db.compliance.domains.dsl.Refs.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.compliance.test.relation.Arity23
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*


case class FlatOptRefNested(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends Arity23 {

  import api.*
  import suite.*

  "Basic optional ref nested" - refs {
    for {
      _ <- A.i.B.?(B.s.i.C.?(C.s.i)).insert(List(
        (1, None),
        (2, Some(("b", 2, None))),
        (3, Some(("b", 3, Some(("c", 3))))),
      )).transact

      _ <- A.i.a1.B.?(B.s.i.C.?(C.s.i)).query.get.map(_ ==> List(
        (1, None),
        (2, Some(("b", 2, None))),
        (3, Some(("b", 3, Some(("c", 3)))))
      ))

      _ <- A.i.a1.B.?(B.s.i.C.s.i).query.get.map(_ ==> List(
        (1, None),
        (2, None),
        (3, Some(("b", 3, "c", 3)))
      ))

      _ <- A.i.B.s.i.C.s.i.query.get.map(_ ==> List(
        (3, "b", 3, "c", 3)
      ))
    } yield ()
  }


  "Optional ref nested with opt attrs" - refs {
    for {
      _ <- A.i(1).save.transact
      _ <- A.i(2).B.i(20).save.transact
      _ <- A.i(3).B.i(30).C.s("c").i(300).save.transact
      _ <- A.i(4).B.i(40).s("b").save.transact
      _ <- A.i(5).B.i(50).s("b").C.s("c").save.transact
      _ <- A.i(6).B.i(60).s("b").C.s("c").i(600).save.transact

      _ <- A.i.a1.B.?(B.i.s.C.?(C.s.i)).query.get.map(_ ==> List(
        (1, None),
        (2, None),
        (3, None),
        (4, Some((40, "b", None))),
        (5, Some((50, "b", None))),
        (6, Some((60, "b", Some(("c", 600))))),
      ))

      _ <- A.i.a1.B.?(B.i.s.C.?(C.s.i_?)).query.get.map(_ ==> List(
        (1, None),
        (2, None),
        (3, None),
        (4, Some((40, "b", None))),
        (5, Some((50, "b", Some(("c", None))))),
        (6, Some((60, "b", Some(("c", Some(600)))))),
      ))

      _ <- A.i.a1.B.?(B.i.s_?.C.?(C.s.i)).query.get.map(_ ==> List(
        (1, None),
        (2, Some((20, None, None))),
        (3, Some((30, None, Some(("c", 300))))),
        (4, Some((40, Some("b"), None))),
        (5, Some((50, Some("b"), None))),
        (6, Some((60, Some("b"), Some(("c", 600))))),
      ))

      _ <- A.i.a1.B.?(B.i.s_?.C.?(C.s.i_?)).query.get.map(_ ==> List(
        (1, None),
        (2, Some((20, None, None))),
        (3, Some((30, None, Some(("c", Some(300)))))),
        (4, Some((40, Some("b"), None))),
        (5, Some((50, Some("b"), Some(("c", None))))),
        (6, Some((60, Some("b"), Some(("c", Some(600)))))),
      ))
    } yield ()
  }


  "Ref (for comparison)" - refs {
    for {
      _ <- A.i(1).save.transact
      _ <- A.i(2).B.i(20).save.transact
      _ <- A.i(3).B.i(30).C.s("c").i(300).save.transact
      _ <- A.i(4).B.i(40).s("b").save.transact
      _ <- A.i(5).B.i(50).s("b").C.s("c").save.transact
      _ <- A.i(6).B.i(60).s("b").C.s("c").i(600).save.transact

      _ <- A.i.B.i.s.C.s.i.query.get.map(_ ==> List(
        (6, 60, "b", "c", 600),
      ))
      _ <- A.i.a1.B.i.s.C.s.i_?.query.get.map(_ ==> List(
        (5, 50, "b", "c", None),
        (6, 60, "b", "c", Some(600)),
      ))
      _ <- A.i.a1.B.i.s_?.C.s.i.query.get.map(_ ==> List(
        (3, 30, None, "c", 300),
        (6, 60, Some("b"), "c", 600),
      ))
      _ <- A.i.a1.B.i.s_?.C.s.i_?.query.get.map(_ ==> List(
        (3, 30, None, "c", Some(300)),
        (5, 50, Some("b"), "c", None),
        (6, 60, Some("b"), "c", Some(600)),
      ))
    } yield ()
  }
}
