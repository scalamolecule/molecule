package molecule.coreTests.spi.crud.insert

import molecule.base.error.ModelError
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._
import scala.language.implicitConversions


trait InsertRefs extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

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

        _ <- A.B.i.insert(1).transact
        _ <- A.B.i.query.get.map(_ ==> List(1))

        _ <- A.i.B.i.insert(1, 2).transact
        _ <- A.i.B.i.query.get.map(_ ==> List((1, 2)))


        _ <- A.B.C.i.insert(1).transact
        _ <- A.B.C.i.query.get.map(_ ==> List(1))

        _ <- A.B.i.C.i.insert(1, 2).transact
        _ <- A.B.i.C.i.query.get.map(_ ==> List((1, 2)))

        _ <- A.i.B.C.i.insert(1, 2).transact
        _ <- A.i.B.C.i.query.get.map(_ ==> List((1, 2)))

        _ <- A.i.B.i.C.i.insert(1, 2, 3).transact
        _ <- A.i.B.i.C.i.query.get.map(_ ==> List((1, 2, 3)))
      } yield ()
    }


    "card many" - refs { implicit conn =>
      for {
        _ <- A.Bb.i.insert(1).transact
        _ <- A.Bb.i.query.get.map(_ ==> List(1))

        _ <- A.i.Bb.i.insert(1, 2).transact
        _ <- A.i.Bb.i.query.get.map(_ ==> List((1, 2)))


        _ <- A.Bb.Cc.i.insert(1).transact
        _ <- A.Bb.Cc.i.query.get.map(_ ==> List(1))

        _ <- A.Bb.i.Cc.i.insert(1, 2).transact
        _ <- A.Bb.i.Cc.i.query.get.map(_ ==> List((1, 2)))

        _ <- A.i.Bb.Cc.i.insert(1, 2).transact
        _ <- A.i.Bb.Cc.i.query.get.map(_ ==> List((1, 2)))

        _ <- A.i.Bb.i.Cc.i.insert(1, 2, 3).transact
        _ <- A.i.Bb.i.Cc.i.query.get.map(_ ==> List((1, 2, 3)))
      } yield ()
    }


    "card one/many" - refs { implicit conn =>
      for {
        _ <- A.B.Cc.i.insert(1).transact
        _ <- A.B.Cc.i.query.get.map(_ ==> List(1))

        _ <- A.i.B.Cc.i.insert(1, 2).transact
        _ <- A.i.B.Cc.i.query.get.map(_ ==> List((1, 2)))

        _ <- A.B.i.Cc.i.insert(1, 2).transact
        _ <- A.B.i.Cc.i.query.get.map(_ ==> List((1, 2)))

        _ <- A.i.B.i.Cc.i.insert(1, 2, 3).transact
        _ <- A.i.B.i.Cc.i.query.get.map(_ ==> List((1, 2, 3)))
      } yield ()
    }


    "card many/one" - refs { implicit conn =>
      for {
        _ <- A.Bb.C.i.insert(1).transact
        _ <- A.Bb.C.i.query.get.map(_ ==> List(1))

        _ <- A.Bb.i.C.i.insert(1, 2).transact
        _ <- A.Bb.i.C.i.query.get.map(_ ==> List((1, 2)))

        _ <- A.i.Bb.C.i.insert(1, 2).transact
        _ <- A.i.Bb.C.i.query.get.map(_ ==> List((1, 2)))

        _ <- A.i.Bb.i.C.i.insert(1, 2, 3).transact
        _ <- A.i.Bb.i.C.i.query.get.map(_ ==> List((1, 2, 3)))
      } yield ()
    }


    "backref, card one" - refs { implicit conn =>
      for {
        // Can't go back from empty namespaces
        _ <- A.i.B._A.s.insert(1, "a").transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Please add attributes to namespace B before going back to namespace A"
          }

        _ <- A.i.B.i._A.s.insert(1, 2, "a").transact
        _ <- A.i.B.i._A.s.query.get.map(_ ==> List((1, 2, "a")))

        _ <- A.B.i._A.s.insert(2, "a").transact
        _ <- A.B.i._A.s.query.get.map(_ ==> List((2, "a")))


        _ <- A.i.B.i._A.s.C.i.insert(1, 2, "a", 3).transact
        _ <- A.i.B.i._A.s.C.i.query.get.map(_ ==> List((1, 2, "a", 3)))

        _ <- A.i.B.i._A.C.i.insert(1, 2, 3).transact
        _ <- A.i.B.i._A.C.i.query.get.map(_ ==> List((1, 2, 3)))

        _ <- A.B.i._A.s.C.i.insert(2, "a", 3).transact
        _ <- A.B.i._A.s.C.i.query.get.map(_ ==> List((2, "a", 3)))

        _ <- A.B.i._A.C.i.insert(2, 3).transact
        _ <- A.B.i._A.C.i.query.get.map(_ ==> List((2, 3)))


        _ <- A.i.B.i.C.i._B.s.insert(1, 2, 3, "a").transact
        _ <- A.i.B.i.C.i._B.s.query.get.map(_ ==> List((1, 2, 3, "a")))

        _ <- A.B.i.C.i._B.s.insert(2, 3, "a").transact
        _ <- A.B.i.C.i._B.s.query.get.map(_ ==> List((2, 3, "a")))

        _ <- A.B.C.i._B.s.insert(3, "a").transact
        _ <- A.B.C.i._B.s.query.get.map(_ ==> List((3, "a")))


        _ <- A.i.B.i.C.i._B.s.D.i.insert(1, 2, 3, "a", 4).transact
        _ <- A.i.B.i.C.i._B.s.D.i.query.get.map(_ ==> List((1, 2, 3, "a", 4)))

        _ <- A.i.B.i.C.i._B.D.i.insert(1, 2, 3, 4).transact
        _ <- A.i.B.i.C.i._B.D.i.query.get.map(_ ==> List((1, 2, 3, 4)))

        _ <- A.i.B.C.i._B.D.i.insert(1, 3, 4).transact
        _ <- A.i.B.C.i._B.D.i.query.get.map(_ ==> List((1, 3, 4)))

        _ <- A.B.C.i._B.D.i.insert(3, 4).transact
        _ <- A.B.C.i._B.D.i.query.get.map(_ ==> List((3, 4)))


        _ <- A.i.B.i.C.i._B.s._A.s.insert(1, 2, 3, "a", "b").transact
        _ <- A.i.B.i.C.i._B.s._A.s.query.get.map(_ ==> List((1, 2, 3, "a", "b")))

        _ <- A.i.B.i.C.i._B._A.s.insert(1, 2, 3, "b").transact
        _ <- A.i.B.i.C.i._B._A.s.query.get.map(_ ==> List((1, 2, 3, "b")))

        _ <- A.i.B.C.i._B._A.s.insert(1, 3, "b").transact
        _ <- A.i.B.C.i._B._A.s.query.get.map(_ ==> List((1, 3, "b")))

        _ <- A.B.C.i._B._A.s.insert(3, "b").transact
        _ <- A.B.C.i._B._A.s.query.get.map(_ ==> List((3, "b")))


        _ <- A.i.B.i.C.i._B.s._A.s.D.i.insert(1, 2, 3, "a", "b", 4).transact
        _ <- A.i.B.i.C.i._B.s._A.s.D.i.query.get.map(_ ==> List((1, 2, 3, "a", "b", 4)))


        // Distinguish separate relationships to same namespace
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

        _ <- A.B.i._A.s.insert(2, "a").transact
        _ <- A.B.i._A.s.query.get.map(_ ==> List((2, "a")))

        _ <- A.i.Bb.i._A.s.insert(1, 2, "a").transact
        _ <- A.i.Bb.i._A.s.query.get.map(_ ==> List((1, 2, "a")))

        _ <- A.Bb.i._A.s.insert(2, "a").transact
        _ <- A.Bb.i._A.s.query.get.map(_ ==> List((2, "a")))


        _ <- A.i.Bb.i._A.s.C.i.insert(1, 2, "a", 3).transact
        _ <- A.i.Bb.i._A.s.C.i.query.get.map(_ ==> List((1, 2, "a", 3)))

        _ <- A.i.Bb.i._A.C.i.insert(1, 2, 3).transact
        _ <- A.i.Bb.i._A.C.i.query.get.map(_ ==> List((1, 2, 3)))

        _ <- A.Bb.i._A.s.C.i.insert(2, "a", 3).transact
        _ <- A.Bb.i._A.s.C.i.query.get.map(_ ==> List((2, "a", 3)))

        _ <- A.Bb.i._A.C.i.insert(2, 3).transact
        _ <- A.Bb.i._A.C.i.query.get.map(_ ==> List((2, 3)))


        _ <- A.i.Bb.i.Cc.i._B.s.insert(1, 2, 3, "a").transact
        _ <- A.i.Bb.i.Cc.i._B.s.query.get.map(_ ==> List((1, 2, 3, "a")))

        _ <- A.Bb.i.Cc.i._B.s.insert(2, 3, "a").transact
        _ <- A.Bb.i.Cc.i._B.s.query.get.map(_ ==> List((2, 3, "a")))

        _ <- A.Bb.Cc.i._B.s.insert(3, "a").transact
        _ <- A.Bb.Cc.i._B.s.query.get.map(_ ==> List((3, "a")))


        _ <- A.i.Bb.i.Cc.i._B.s.D.i.insert(1, 2, 3, "a", 4).transact
        _ <- A.i.Bb.i.Cc.i._B.s.D.i.query.get.map(_ ==> List((1, 2, 3, "a", 4)))

        _ <- A.i.Bb.i.Cc.i._B.D.i.insert(1, 2, 3, 4).transact
        _ <- A.i.Bb.i.Cc.i._B.D.i.query.get.map(_ ==> List((1, 2, 3, 4)))

        _ <- A.i.Bb.Cc.i._B.D.i.insert(1, 3, 4).transact
        _ <- A.i.Bb.Cc.i._B.D.i.query.get.map(_ ==> List((1, 3, 4)))

        _ <- A.Bb.Cc.i._B.D.i.insert(3, 4).transact
        _ <- A.Bb.Cc.i._B.D.i.query.get.map(_ ==> List((3, 4)))


        _ <- A.i.Bb.i.Cc.i._B.s._A.s.insert(1, 2, 3, "a", "b").transact
        _ <- A.i.Bb.i.Cc.i._B.s._A.s.query.get.map(_ ==> List((1, 2, 3, "a", "b")))

        _ <- A.i.Bb.i.Cc.i._B._A.s.insert(1, 2, 3, "b").transact
        _ <- A.i.Bb.i.Cc.i._B._A.s.query.get.map(_ ==> List((1, 2, 3, "b")))

        _ <- A.i.Bb.Cc.i._B._A.s.insert(1, 3, "b").transact
        _ <- A.i.Bb.Cc.i._B._A.s.query.get.map(_ ==> List((1, 3, "b")))

        _ <- A.Bb.Cc.i._B._A.s.insert(3, "b").transact
        _ <- A.Bb.Cc.i._B._A.s.query.get.map(_ ==> List((3, "b")))


        _ <- A.i.Bb.i.Cc.i._B.s._A.s.D.i.insert(1, 2, 3, "a", "b", 4).transact
        _ <- A.i.Bb.i.Cc.i._B.s._A.s.D.i.query.get.map(_ ==> List((1, 2, 3, "a", "b", 4)))

        // Distinguish separate relationships to same namespace
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


    "ids, ref" - refs { implicit conn =>
      for {
        // Card one
        List(a1, b1, a2, b2) <- A.i.B.i.insert(
          (1, 2),
          (3, 4),
        ).transact.map(_.ids)

        _ <- A(a1).i.query.get.map(_ ==> List(1))
        _ <- B(b1).i.query.get.map(_ ==> List(2))

        _ <- A(a2).i.query.get.map(_ ==> List(3))
        _ <- B(b2).i.query.get.map(_ ==> List(4))

        _ <- A.id.i.a1.b.query.get.map(_ ==> List(
          (a1, 1, b1),
          (a2, 3, b2),
        ))

        // Card Set
        List(a1, b1, a2, b2) <- A.i.Bb.i.insert(
          (1, 2),
          (3, 4),
        ).transact.map(_.ids)

        _ <- A(a1).i.query.get.map(_ ==> List(1))
        _ <- B(b1).i.query.get.map(_ ==> List(2))

        _ <- A(a2).i.query.get.map(_ ==> List(3))
        _ <- B(b2).i.query.get.map(_ ==> List(4))

        _ <- A.id.i.a1.bb.query.get.map(_ ==> List(
          (a1, 1, Set(b1)),
          (a2, 3, Set(b2)),
        ))
      } yield ()
    }


    "ids, owned ref" - refs { implicit conn =>
      if (database == "MongoDB") {
        // Can't query for non-existing ids of embedded documents in MongoDB
        for {
          // Card one
          List(a1, a2) <- A.i.OwnB.i.insert(
            (1, 2),
            (3, 4),
          ).transact.map(_.ids)

          _ <- A(a1).i.query.get.map(_ ==> List(1))
          _ <- A(a2).i.query.get.map(_ ==> List(3))

          // Can't query for non-existing ownB id
          //_ <- A.id.i.ownB.query

          _ <- A.id.i.a1.OwnB.i.query.get.map(_ ==> List(
            (a1, 1, 2),
            (a2, 3, 4),
          ))

          // Card Set
          List(a1, a2) <- A.i.OwnBb.i.insert(
            (1, 2),
            (3, 4),
          ).transact.map(_.ids)

          _ <- A(a1).i.query.get.map(_ ==> List(1))
          _ <- A(a2).i.query.get.map(_ ==> List(3))

          // Can't query for non-existing ownBb id
          //_ <- A.id.i.ownBb.query

          _ <- A.id.i.a1.OwnBb.i.query.get.map(_ ==> List(
            (a1, 1, 2),
            (a2, 3, 4),
          ))
        } yield ()

      } else {

        // Other databases
        for {
          // Card one
          List(a1, b1, a2, b2) <- A.i.OwnB.i.insert(
            (1, 2),
            (3, 4),
          ).transact.map(_.ids)

          _ <- A(a1).i.query.get.map(_ ==> List(1))
          _ <- B(b1).i.query.get.map(_ ==> List(2))

          _ <- A(a2).i.query.get.map(_ ==> List(3))
          _ <- B(b2).i.query.get.map(_ ==> List(4))

          _ <- A.id.i.a1.ownB.query.get.map(_ ==> List(
            (a1, 1, b1),
            (a2, 3, b2),
          ))

          // Card Set
          List(a1, b1, a2, b2) <- A.i.OwnBb.i.insert(
            (1, 2),
            (3, 4),
          ).transact.map(_.ids)

          _ <- A(a1).i.query.get.map(_ ==> List(1))
          _ <- B(b1).i.query.get.map(_ ==> List(2))

          _ <- A(a2).i.query.get.map(_ ==> List(3))
          _ <- B(b2).i.query.get.map(_ ==> List(4))

          _ <- A.id.i.a1.ownBb.query.get.map(_ ==> List(
            (a1, 1, Set(b1)),
            (a2, 3, Set(b2)),
          ))
        } yield ()
      }
    }

    "ids, backref" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      if (database == "MongoDB") {
        for {
          // ref - ref
          List(a1, b1, c1, a2, b2, c2) <- A.i.B.i._A.C.i.insert(
            (1, 2, 3),
            (4, 5, 6),
          ).i.transact.map(_.ids)

          _ <- A.id.i.a1.B.id.i._A.C.id.i.query.get.map(_ ==> List(
            (a1, 1, b1, 2, c1, 3),
            (a2, 4, b2, 5, c2, 6),
          ))

          // ref - own
          List(a1, b1, a2, b2) <- A.i.B.i._A.OwnC.i.insert(
            (1, 2, 3),
            (4, 5, 6),
          ).i.transact.map(_.ids)

          _ <- A.id.i.a1.B.id.i._A.OwnC.i.query.get.map(_ ==> List(
            (a1, 1, b1, 2, 3),
            (a2, 4, b2, 5, 6),
          ))

          // own - ref
          List(a1, c1, a2, c2) <- A.i.OwnB.i._A.C.i.insert(
            (1, 2, 3),
            (4, 5, 6),
          ).i.transact.map(_.ids)

          _ <- A.id.i.a1.OwnB.i._A.C.id.i.query.get.map(_ ==> List(
            (a1, 1, 2, c1, 3),
            (a2, 4, 5, c2, 6),
          ))

          // own - own
          List(a1, a2) <- A.i.OwnB.i._A.OwnC.i.insert(
            (1, 2, 3),
            (4, 5, 6),
          ).i.transact.map(_.ids)

          _ <- A.id.i.a1.OwnB.i._A.OwnC.i.query.get.map(_ ==> List(
            (a1, 1, 2, 3),
            (a2, 4, 5, 6),
          ))
        } yield ()

      } else {
        for {
        // ref - ref
          List(a1, b1, c1, a2, b2, c2) <- A.i.B.i._A.C.i.insert(
            (1, 2, 3),
            (4, 5, 6),
          ).i.transact.map(_.ids)

          _ <- A.id.i.a1.B.id.i._A.C.id.i.query.get.map(_ ==> List(
            (a1, 1, b1, 2, c1, 3),
            (a2, 4, b2, 5, c2, 6),
          ))

          // ref - own
          List(a1, b1, c1, a2, b2, c2) <- A.i.B.i._A.OwnC.i.insert(
            (1, 2, 3),
            (4, 5, 6),
          ).i.transact.map(_.ids)

          _ <- A.id.i.a1.B.id.i._A.OwnC.id.i.query.get.map(_ ==> List(
            (a1, 1, b1, 2, c1, 3),
            (a2, 4, b2, 5, c2, 6),
          ))

          // own - ref
          List(a1, b1, c1, a2, b2, c2) <- A.i.OwnB.i._A.C.i.insert(
            (1, 2, 3),
            (4, 5, 6),
          ).i.transact.map(_.ids)

          _ <- A.id.i.a1.OwnB.id.i._A.C.id.i.query.get.map(_ ==> List(
            (a1, 1, b1, 2, c1, 3),
            (a2, 4, b2, 5, c2, 6),
          ))

          // own - own
          List(a1, b1, c1, a2, b2, c2) <- A.i.OwnB.i._A.OwnC.i.insert(
            (1, 2, 3),
            (4, 5, 6),
          ).i.transact.map(_.ids)

          _ <- A.id.i.a1.OwnB.id.i._A.OwnC.id.i.query.get.map(_ ==> List(
            (a1, 1, b1, 2, c1, 3),
            (a2, 4, b2, 5, c2, 6),
          ))
        } yield ()
      }
    }

    "ids, nested" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {
        // 2 A entity ids returned (no Bb ref ids)
        List(a1, a2) <- A.i.Bb.*(B.i).insert(
          (1, List(1, 2)),
          (2, Nil),
        ).i.transact.map(_.ids)

        _ <- A.id.i.a1.Bb.*?(B.i.a1).query.get.map(_ ==> List(
          (a1, 1, List(1, 2)),
          (a2, 2, Nil),
        ))
      } yield ()
    }

    "ids, nested + ref" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {
        // 2 A entity ids returned (no BB or nested C refs)
        List(a1, a2) <- A.i.Bb.*(B.i.C.i).insert(
          (1, List((1, 2), (3, 4))),
          (2, Nil),
        ).i.transact.map(_.ids)

        _ <- A.id.i.a1.Bb.*?(B.i.a1.C.i).query.get.map(_ ==> List(
          (a1, 1, List((1, 2), (3, 4))),
          (a2, 2, Nil),
        ))
      } yield ()
    }

    "ids, ref + nested + ref " - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {
        // 2 A and 2 B entity ids returned (no BB or nested C refs)
        List(a1, b1, a2, b2) <- A.i.B.i.Cc.*(C.i.D.i).insert(
          (1, 10, List((1, 2), (3, 4))),
          (2, 20, Nil),
        ).transact.map(_.ids)

        _ <- A.id.i.a1.B.id.i.Cc.*?(C.i.a1.D.i).query.get.map(_ ==> List(
          (a1, 1, b1, 10, List((1, 2), (3, 4))),
          (a2, 2, b2, 20, Nil),
        ))
      } yield ()
    }

    "ids, self-join + nested + ref " - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {
        // 2 A and 2 A self-join entity ids returned (no BB or nested C refs)
        List(a1, a_1, a2, a_2) <- A.i.A.i.Cc.*(C.i.D.i).insert(
          (1, 10, List((1, 2), (3, 4))),
          (2, 20, Nil),
        ).transact.map(_.ids)

        _ <- A.id.i.a1.A.id.i.Cc.*?(C.i.a1.D.i).query.get.map(_ ==> List(
          (a1, 1, a_1, 10, List((1, 2), (3, 4))),
          (a2, 2, a_2, 20, Nil),
        ))
      } yield ()
    }
  }
}
