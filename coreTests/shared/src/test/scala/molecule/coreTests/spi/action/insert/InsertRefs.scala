package molecule.coreTests.spi.action.insert

import molecule.base.error.ModelError
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor.*
import molecule.coreTests.domains.dsl.Refs.*
import molecule.coreTests.setup.*
//import scala.language.implicitConversions


case class InsertRefs(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "card one" - refs { implicit conn =>
    for {
      _ <- A.i.B.s.insert(
        (1, "a"),
        (2, "b"),
      ).transact

      _ <- A.i.a1.B.s.query.get.map(_ ==> List(
        (1, "a"),
        (2, "b"),
      ))

      _ <- A.i.B.i.insert(1, 2).transact
      _ <- A.i.B.i.query.get.map(_ ==> List((1, 2)))

      _ <- A.i.B.i.C.i.insert(1, 2, 3).transact
      _ <- A.i.B.i.C.i.query.get.map(_ ==> List((1, 2, 3)))
    } yield ()
  }


  "card many" - refs { implicit conn =>
    for {
      _ <- A.i.Bb.i.insert(1, 2).transact
      _ <- A.i.Bb.i.query.get.map(_ ==> List((1, 2)))

      _ <- A.i.Bb.i.Cc.i.insert(1, 2, 3).transact
      _ <- A.i.Bb.i.Cc.i.query.get.map(_ ==> List((1, 2, 3)))
    } yield ()
  }


  "card one/many" - refs { implicit conn =>
    for {
      _ <- A.i.B.i.Cc.i.insert(1, 2, 3).transact
      _ <- A.i.B.i.Cc.i.query.get.map(_ ==> List((1, 2, 3)))
    } yield ()
  }


  "card many/one" - refs { implicit conn =>
    for {
      _ <- A.i.Bb.i.C.i.insert(1, 2, 3).transact
      _ <- A.i.Bb.i.C.i.query.get.map(_ ==> List((1, 2, 3)))
    } yield ()
  }


  "backref, card one" - refs { implicit conn =>
    for {
      // Can't go back from empty entities
      _ <- A.i.B._A.s.insert(1, "a").transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Please add attributes to entity B before going back to entity A"
        }

      _ <- A.i.B.i._A.s.insert(1, 2, "a").transact
      _ <- A.i.B.i._A.s.query.get.map(_ ==> List((1, 2, "a")))

      _ <- A.i.B.i._A.s.C.i.insert(1, 2, "a", 3).transact
      _ <- A.i.B.i._A.s.C.i.query.get.map(_ ==> List((1, 2, "a", 3)))

      _ <- A.i.B.i._A.C.i.insert(1, 2, 3).transact
      _ <- A.i.B.i._A.C.i.query.get.map(_ ==> List((1, 2, 3)))

      _ <- A.i.B.i.C.i._B.s.insert(1, 2, 3, "a").transact
      _ <- A.i.B.i.C.i._B.s.query.get.map(_ ==> List((1, 2, 3, "a")))

      _ <- A.i.B.i.C.i._B.s.D.i.insert(1, 2, 3, "a", 4).transact
      _ <- A.i.B.i.C.i._B.s.D.i.query.get.map(_ ==> List((1, 2, 3, "a", 4)))

      _ <- A.i.B.i.C.i._B.s._A.s.insert(1, 2, 3, "a", "b").transact
      _ <- A.i.B.i.C.i._B.s._A.s.query.get.map(_ ==> List((1, 2, 3, "a", "b")))

      _ <- A.i.B.i.C.i._B._A.s.insert(1, 2, 3, "b").transact
      _ <- A.i.B.i.C.i._B._A.s.query.get.map(_ ==> List((1, 2, 3, "b")))

      _ <- A.i.B.i.C.i._B.s._A.s.D.i.insert(1, 2, 3, "a", "b", 4).transact
      _ <- A.i.B.i.C.i._B.s._A.s.D.i.query.get.map(_ ==> List((1, 2, 3, "a", "b", 4)))

      // Distinguish separate relationships to same entity
      _ <- A.i.B.i._A.B1.i.insert(1, 2, 3).transact
      _ <- A.i.B.i._A.B1.i.query.get.map(_ ==> List((1, 2, 3)))

      _ <- A.i.B.i._A.B1.i._A.B2.i.insert(1, 2, 3, 4).transact
      _ <- A.i.B.i._A.B1.i._A.B2.i.query.get.map(_ ==> List((1, 2, 3, 4)))
    } yield ()
  }


  "backref, card many" - refs { implicit conn =>
    for {
      _ <- A.i.Bb.i._A.s.insert(1, 2, "a").transact
      _ <- A.i.Bb.i._A.s.query.get.map(_ ==> List((1, 2, "a")))

      _ <- A.i.Bb.i._A.s.insert(1, 2, "a").transact
      _ <- A.i.Bb.i._A.s.query.get.map(_ ==> List((1, 2, "a")))

      _ <- A.i.Bb.i._A.s.C.i.insert(1, 2, "a", 3).transact
      _ <- A.i.Bb.i._A.s.C.i.query.get.map(_ ==> List((1, 2, "a", 3)))

      _ <- A.i.Bb.i._A.C.i.insert(1, 2, 3).transact
      _ <- A.i.Bb.i._A.C.i.query.get.map(_ ==> List((1, 2, 3)))

      _ <- A.i.Bb.i.Cc.i._B.s.insert(1, 2, 3, "a").transact
      _ <- A.i.Bb.i.Cc.i._B.s.query.get.map(_ ==> List((1, 2, 3, "a")))

      _ <- A.i.Bb.i.Cc.i._B.s.D.i.insert(1, 2, 3, "a", 4).transact
      _ <- A.i.Bb.i.Cc.i._B.s.D.i.query.get.map(_ ==> List((1, 2, 3, "a", 4)))

      _ <- A.i.Bb.i.Cc.i._B.s._A.s.insert(1, 2, 3, "a", "b").transact
      _ <- A.i.Bb.i.Cc.i._B.s._A.s.query.get.map(_ ==> List((1, 2, 3, "a", "b")))

      _ <- A.i.Bb.i.Cc.i._B._A.s.insert(1, 2, 3, "b").transact
      _ <- A.i.Bb.i.Cc.i._B._A.s.query.get.map(_ ==> List((1, 2, 3, "b")))

      _ <- A.i.Bb.i.Cc.i._B.s._A.s.D.i.insert(1, 2, 3, "a", "b", 4).transact
      _ <- A.i.Bb.i.Cc.i._B.s._A.s.D.i.query.get.map(_ ==> List((1, 2, 3, "a", "b", 4)))

      // Distinguish separate relationships to same entity
      // card-many B and card-one B should be distinguished from each other
      _ <- A.i.Bb.i._A.s.B.s.insert(1, 2, "a", "b").transact
      _ <- A.i.Bb.i._A.s.B.s.query.get.map(_ ==> List((1, 2, "a", "b")))

      _ <- A.i.s.B.i.s.Cc.i._B.C.i.s._B._A.Bb.i.insert(
        (0, "a", 1, "b", 22, 2, "c", 11),
        (1, "a", 1, "b", 22, 2, "c", 11),
      ).transact

      _ <- A.i.a1.s.B.i.s.Cc.i._B.C.i.s._B._A.Bb.i.query.get.map(_ ==> List(
        (0, "a", 1, "b", 22, 2, "c", 11),
        (1, "a", 1, "b", 22, 2, "c", 11),
      ))
    } yield ()
  }


  "self-join, one" - refs { implicit conn =>
    for {
      _ <- A.i.A.i.insert(1, 2).transact
      _ <- A.i.A.i.query.get.map(_ ==> List((1, 2)))

      _ <- A.i.A.i.B.i.insert(1, 2, 3).transact
      _ <- A.i.A.i.B.i.query.get.map(_ ==> List((1, 2, 3)))

      _ <- A.i.B.i.A.i.insert(1, 2, 3).transact
      _ <- A.i.B.i.A.i.query.get.map(_ ==> List((1, 2, 3)))

      _ <- A.i.B.i.B.i.insert(1, 2, 3).transact
      _ <- A.i.B.i.B.i.query.get.map(_ ==> List((1, 2, 3)))
    } yield ()
  }


  "self-join, many" - refs { implicit conn =>
    for {
      _ <- A.i.Aa.i.insert(1, 2).transact
      _ <- A.i.Aa.i.query.get.map(_ ==> List((1, 2)))

      _ <- A.i.Aa.i.Bb.i.insert(1, 2, 3).transact
      _ <- A.i.Aa.i.Bb.i.query.get.map(_ ==> List((1, 2, 3)))

      _ <- A.i.Bb.i.Aa.i.insert(1, 2, 3).transact
      _ <- A.i.Bb.i.Aa.i.query.get.map(_ ==> List((1, 2, 3)))

      _ <- A.i.Bb.i.Bb.i.insert(1, 2, 3).transact
      _ <- A.i.Bb.i.Bb.i.query.get.map(_ ==> List((1, 2, 3)))
    } yield ()
  }


  "ids, ref, card-one" - refs { implicit conn =>
    for {
      // Ids of A entities returned
      List(a1, a2) <- A.i.B.i.insert(
        (1, 2),
        (3, 4),
      ).transact.map(_.ids)

      _ <- A(a1).i.query.get.map(_ ==> List(1))
      _ <- A(a2).i.query.get.map(_ ==> List(3))

      // Ref ids
      List(b1, b2) <- A(a1, a2).b.query.get

      _ <- B(b1).i.query.get.map(_ ==> List(2))
      _ <- B(b2).i.query.get.map(_ ==> List(4))

      _ <- A.id.i.a1.b.query.get.map(_ ==> List(
        (a1, 1, b1),
        (a2, 3, b2),
      ))
    } yield ()
  }

  "ids, ref, card-set" - refs { implicit conn =>
    for {
      // Ids of A entities returned
      List(a1, a2) <- A.i.Bb.i.insert(
        (1, 2),
        (3, 4),
      ).transact.map(_.ids)

      _ <- A(a1).i.query.get.map(_ ==> List(1))
      _ <- A(a2).i.query.get.map(_ ==> List(3))

      // Getting head of each Set ref ids (card-set)
      List(b1) <- A(a1).bb.query.get.map(_.map(_.head))
      List(b2) <- A(a2).bb.query.get.map(_.map(_.head))

      _ <- B(b1).i.query.get.map(_ ==> List(2))
      _ <- B(b2).i.query.get.map(_ ==> List(4))

      _ <- A.id.i.a1.bb.query.get.map(_ ==> List(
        (a1, 1, Set(b1)),
        (a2, 3, Set(b2)),
      ))
    } yield ()
  }


  "ids, backref" - refs { implicit conn =>
    import molecule.coreTests.domains.dsl.Refs.*
    for {
      // ref - ref
      List(a1, a2) <- A.i.B.i._A.C.i.insert(
        (1, 2, 3),
        (4, 5, 6),
      ).transact.map(_.ids)

      _ <- A.id(a1, a2).i.a1.B.i._A.C.i.query.get.map(_ ==> List(
        (a1, 1, 2, 3),
        (a2, 4, 5, 6),
      ))

      // ref - own
      List(a1, a2) <- A.i.B.i._A.OwnC.i.insert(
        (1, 2, 3),
        (4, 5, 6),
      ).transact.map(_.ids)

      _ <- A.id.i.a1.B.i._A.OwnC.i.query.get.map(_ ==> List(
        (a1, 1, 2, 3),
        (a2, 4, 5, 6),
      ))

      // own - ref
      List(a1, a2) <- A.i.OwnB.i._A.C.i.insert(
        (1, 2, 3),
        (4, 5, 6),
      ).transact.map(_.ids)

      _ <- A.id.i.a1.OwnB.i._A.C.i.query.get.map(_ ==> List(
        (a1, 1, 2, 3),
        (a2, 4, 5, 6),
      ))

      // own - own
      List(a1, a2) <- A.i.OwnB.i._A.OwnC.i.insert(
        (1, 2, 3),
        (4, 5, 6),
      ).transact.map(_.ids)

      _ <- A.id.i.a1.OwnB.i._A.OwnC.i.query.get.map(_ ==> List(
        (a1, 1, 2, 3),
        (a2, 4, 5, 6),
      ))
    } yield ()
  }

  "ids, nested" - refs { implicit conn =>
    import molecule.coreTests.domains.dsl.Refs.*
    for {
      // 2 A entity ids returned (no Bb ref ids)
      List(a1, a2) <- A.i.Bb.*(B.i).insert(
        (1, List(1, 2)),
        (2, Nil),
      ).transact.map(_.ids)

      _ <- A.id.i.a1.Bb.*?(B.i.a1).query.get.map(_ ==> List(
        (a1, 1, List(1, 2)),
        (a2, 2, Nil),
      ))
    } yield ()
  }

  "ids, nested + ref" - refs { implicit conn =>
    import molecule.coreTests.domains.dsl.Refs.*
    for {
      // Ids of A entities returned
      List(a1, a2) <- A.i.Bb.*(B.i.C.i).insert(
        (1, List((1, 2), (3, 4))),
        (2, Nil),
      ).transact.map(_.ids)

      _ <- A.id(a1, a2).i.a1.Bb.*?(B.i.a1.C.i).query.get.map(_ ==> List(
        (a1, 1, List((1, 2), (3, 4))),
        (a2, 2, Nil),
      ))
    } yield ()
  }

  "ids, ref + nested + ref " - refs { implicit conn =>
    import molecule.coreTests.domains.dsl.Refs.*
    for {
      // Ids of A entities returned
      List(a1, a2) <- A.i.B.i.Cc.*(C.i.D.i).insert(
        (1, 10, List((1, 2), (3, 4))),
        (2, 20, Nil),
      ).transact.map(_.ids)

      _ <- A.id(a1, a2).i.a1.B.i.Cc.*?(C.i.a1.D.i).query.get.map(_ ==> List(
        (a1, 1, 10, List((1, 2), (3, 4))),
        (a2, 2, 20, Nil),
      ))
    } yield ()
  }

  "ids, self-join + nested + ref " - refs { implicit conn =>
    import molecule.coreTests.domains.dsl.Refs.*
    for {
      // Ids of A entities returned
      List(a1, a2) <- A.i.A.i.Cc.*(C.i.D.i).insert(
        (1, 10, List((1, 2), (3, 4))),
        (2, 20, Nil),
      ).transact.map(_.ids)

      _ <- A.id(a1, a2).i.a1.A.i.Cc.*?(C.i.a1.D.i).query.get.map(_ ==> List(
        (a1, 1, 10, List((1, 2), (3, 4))),
        (a2, 2, 20, Nil),
      ))
    } yield ()
  }

  "Optional ref (left join)" - refs { implicit conn =>
    for {
      _ <- A.i.B.?(B.s).insert(
        (1, Some("a")),
        (2, None),
      ).transact

      _ <- A.i.a1.B.?(B.s).query.get.map(_ ==> List(
        (1, Some("a")),
        (2, None),
      ))
    } yield ()
  }

  "Optional ref 2 (left join)" - refs { implicit conn =>
    for {
      _ <- A.i.B.?(B.i.s).insert(
        (1, Some((10, "a"))),
        (2, None),
      ).transact

      _ <- A.i.a1.B.?(B.i.s).query.get.map(_ ==> List(
        (1, Some((10, "a"))),
        (2, None),
      ))
    } yield ()
  }


  if (database != "datomic") {
    "Optional entity (right join)" - refs { implicit conn =>
      for {
        _ <- A.?(A.i).B.s.insert(
          (Some(1), "a"),
          (None, "b"),
        ).transact

        _ <- A.?(A.i).B.s.a1.query.get.map(_ ==> List(
          (Some(1), "a"),
          (None, "b"),
        ))
      } yield ()
    }

    "Optional entity 2 (right join)" - refs { implicit conn =>
      for {
        _ <- A.?(A.i.s).B.s.insert(
          (Some((1, "x")), "a"),
          (None, "b"),
        ).transact

        _ <- A.?(A.i.s).B.s.a1.query.get.map(_ ==> List(
          (Some((1, "x")), "a"),
          (None, "b"),
        ))
      } yield ()
    }
  }
}
