package molecule.coreTests.spi.crud.insert

import molecule.base.error.ModelError
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._
import scala.concurrent.Future
import scala.language.implicitConversions


trait InsertRefsOwned extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "card one" - refs { implicit conn =>
      for {
        _ <- A.i.OwnB.s.insert(
          (1, "a"),
          (2, "b"),
        ).transact

        _ <- A.i.a1.OwnB.s.query.get.map(_ ==> List(
          (1, "a"),
          (2, "b"),
        ))

        _ <- A.OwnB.i.insert(1).transact
        _ <- A.OwnB.i.query.get.map(_ ==> List(1))

        _ <- A.i.OwnB.i.insert(1, 2).transact
        _ <- A.i.OwnB.i.query.get.map(_ ==> List((1, 2)))


        _ <- A.OwnB.C.i.insert(1).transact
        _ <- A.OwnB.C.i.query.get.map(_ ==> List(1))

        _ <- A.OwnB.i.C.i.insert(1, 2).transact
        _ <- A.OwnB.i.C.i.query.get.map(_ ==> List((1, 2)))

        _ <- A.i.OwnB.C.i.insert(1, 2).transact
        _ <- A.i.OwnB.C.i.query.get.map(_ ==> List((1, 2)))

        _ <- A.i.OwnB.i.C.i.insert(1, 2, 3).transact
        _ <- A.i.OwnB.i.C.i.query.get.map(_ ==> List((1, 2, 3)))
      } yield ()
    }


    "card many" - refs { implicit conn =>
      for {
        _ <- A.OwnBb.i.insert(1).transact
        _ <- A.OwnBb.i.query.get.map(_ ==> List(1))

        _ <- A.i.OwnBb.i.insert(1, 2).transact
        _ <- A.i.OwnBb.i.query.get.map(_ ==> List((1, 2)))


        _ <- A.OwnBb.Cc.i.insert(1).transact
        _ <- A.OwnBb.Cc.i.query.get.map(_ ==> List(1))

        _ <- A.OwnBb.i.Cc.i.insert(1, 2).transact
        _ <- A.OwnBb.i.Cc.i.query.get.map(_ ==> List((1, 2)))

        _ <- A.i.OwnBb.Cc.i.insert(1, 2).transact
        _ <- A.i.OwnBb.Cc.i.query.get.map(_ ==> List((1, 2)))

        _ <- A.i.OwnBb.i.Cc.i.insert(1, 2, 3).transact
        _ <- A.i.OwnBb.i.Cc.i.query.get.map(_ ==> List((1, 2, 3)))
      } yield ()
    }


    "card one/many" - refs { implicit conn =>
      for {
        _ <- A.OwnB.Cc.i.insert(1).transact
        _ <- A.OwnB.Cc.i.query.get.map(_ ==> List(1))

        _ <- A.i.OwnB.Cc.i.insert(1, 2).transact
        _ <- A.i.OwnB.Cc.i.query.get.map(_ ==> List((1, 2)))

        _ <- A.OwnB.i.Cc.i.insert(1, 2).transact
        _ <- A.OwnB.i.Cc.i.query.get.map(_ ==> List((1, 2)))

        _ <- A.i.OwnB.i.Cc.i.insert(1, 2, 3).transact
        _ <- A.i.OwnB.i.Cc.i.query.get.map(_ ==> List((1, 2, 3)))
      } yield ()
    }


    "card many/one" - refs { implicit conn =>
      for {
        _ <- A.OwnBb.C.i.insert(1).transact
        _ <- A.OwnBb.C.i.query.get.map(_ ==> List(1))

        _ <- A.OwnBb.i.C.i.insert(1, 2).transact
        _ <- A.OwnBb.i.C.i.query.get.map(_ ==> List((1, 2)))

        _ <- A.i.OwnBb.C.i.insert(1, 2).transact
        _ <- A.i.OwnBb.C.i.query.get.map(_ ==> List((1, 2)))

        _ <- A.i.OwnBb.i.C.i.insert(1, 2, 3).transact
        _ <- A.i.OwnBb.i.C.i.query.get.map(_ ==> List((1, 2, 3)))
      } yield ()
    }


    "backref, card one" - refs { implicit conn =>
      for {
        // Can't go back from empty namespaces
        _ <- A.i.OwnB._A.s.insert(1, "a").transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Please add attributes to namespace B before going back to namespace A"
          }

        _ <- A.i.OwnB.i._A.s.insert(1, 2, "a").transact
        _ <- A.i.OwnB.i._A.s.query.get.map(_ ==> List((1, 2, "a")))

        _ <- A.OwnB.i._A.s.insert(2, "a").transact
        _ <- A.OwnB.i._A.s.query.get.map(_ ==> List((2, "a")))


        _ <- A.i.OwnB.i._A.s.C.i.insert(1, 2, "a", 3).transact
        _ <- A.i.OwnB.i._A.s.C.i.query.get.map(_ ==> List((1, 2, "a", 3)))

        _ <- A.i.OwnB.i._A.C.i.insert(1, 2, 3).transact
        _ <- A.i.OwnB.i._A.C.i.query.get.map(_ ==> List((1, 2, 3)))

        _ <- A.OwnB.i._A.s.C.i.insert(2, "a", 3).transact
        _ <- A.OwnB.i._A.s.C.i.query.get.map(_ ==> List((2, "a", 3)))

        _ <- A.OwnB.i._A.C.i.insert(2, 3).transact
        _ <- A.OwnB.i._A.C.i.query.get.map(_ ==> List((2, 3)))


        _ <- A.i.OwnB.i.C.i._B.s.insert(1, 2, 3, "a").transact
        _ <- A.i.OwnB.i.C.i._B.s.query.get.map(_ ==> List((1, 2, 3, "a")))

        _ <- A.OwnB.i.C.i._B.s.insert(2, 3, "a").transact
        _ <- A.OwnB.i.C.i._B.s.query.get.map(_ ==> List((2, 3, "a")))

        _ <- A.OwnB.C.i._B.s.insert(3, "a").transact
        _ <- A.OwnB.C.i._B.s.query.get.map(_ ==> List((3, "a")))


        _ <- A.i.OwnB.i.C.i._B.s.D.i.insert(1, 2, 3, "a", 4).transact
        _ <- A.i.OwnB.i.C.i._B.s.D.i.query.get.map(_ ==> List((1, 2, 3, "a", 4)))

        _ <- A.i.OwnB.i.C.i._B.D.i.insert(1, 2, 3, 4).transact
        _ <- A.i.OwnB.i.C.i._B.D.i.query.get.map(_ ==> List((1, 2, 3, 4)))

        _ <- A.i.OwnB.C.i._B.D.i.insert(1, 3, 4).transact
        _ <- A.i.OwnB.C.i._B.D.i.query.get.map(_ ==> List((1, 3, 4)))

        _ <- A.OwnB.C.i._B.D.i.insert(3, 4).transact
        _ <- A.OwnB.C.i._B.D.i.query.get.map(_ ==> List((3, 4)))


        _ <- A.i.OwnB.i.C.i._B.s._A.s.insert(1, 2, 3, "a", "b").transact
        _ <- A.i.OwnB.i.C.i._B.s._A.s.query.get.map(_ ==> List((1, 2, 3, "a", "b")))

        _ <- A.i.OwnB.i.C.i._B._A.s.insert(1, 2, 3, "b").transact
        _ <- A.i.OwnB.i.C.i._B._A.s.query.get.map(_ ==> List((1, 2, 3, "b")))

        _ <- A.i.OwnB.C.i._B._A.s.insert(1, 3, "b").transact
        _ <- A.i.OwnB.C.i._B._A.s.query.get.map(_ ==> List((1, 3, "b")))

        _ <- A.OwnB.C.i._B._A.s.insert(3, "b").transact
        _ <- A.OwnB.C.i._B._A.s.query.get.map(_ ==> List((3, "b")))


        _ <- A.i.OwnB.i.C.i._B.s._A.s.D.i.insert(1, 2, 3, "a", "b", 4).transact
        _ <- A.i.OwnB.i.C.i._B.s._A.s.D.i.query.get.map(_ ==> List((1, 2, 3, "a", "b", 4)))


        // Distinguish separate relationships to same namespace
        _ <- A.i.OwnB.i._A.B1.i.insert(1, 2, 3).transact
        _ <- A.i.OwnB.i._A.B1.i.query.get.map(_ ==> List((1, 2, 3)))

        _ <- A.i.OwnB.i._A.B1.i._A.B2.i.insert(1, 2, 3, 4).transact
        _ <- A.i.OwnB.i._A.B1.i._A.B2.i.query.get.map(_ ==> List((1, 2, 3, 4)))
      } yield ()
    }


    "backref, card many" - refs { implicit conn =>
      for {
        _ <- A.i.OwnBb.i._A.s.insert(1, 2, "a").transact
        _ <- A.i.OwnBb.i._A.s.query.get.map(_ ==> List((1, 2, "a")))

        _ <- A.OwnB.i._A.s.insert(2, "a").transact
        _ <- A.OwnB.i._A.s.query.get.map(_ ==> List((2, "a")))

        _ <- A.i.OwnBb.i._A.s.insert(1, 2, "a").transact
        _ <- A.i.OwnBb.i._A.s.query.get.map(_ ==> List((1, 2, "a")))

        _ <- A.OwnBb.i._A.s.insert(2, "a").transact
        _ <- A.OwnBb.i._A.s.query.get.map(_ ==> List((2, "a")))


        _ <- A.i.OwnBb.i._A.s.C.i.insert(1, 2, "a", 3).transact
        _ <- A.i.OwnBb.i._A.s.C.i.query.get.map(_ ==> List((1, 2, "a", 3)))

        _ <- A.i.OwnBb.i._A.C.i.insert(1, 2, 3).transact
        _ <- A.i.OwnBb.i._A.C.i.query.get.map(_ ==> List((1, 2, 3)))

        _ <- A.OwnBb.i._A.s.C.i.insert(2, "a", 3).transact
        _ <- A.OwnBb.i._A.s.C.i.query.get.map(_ ==> List((2, "a", 3)))

        _ <- A.OwnBb.i._A.C.i.insert(2, 3).transact
        _ <- A.OwnBb.i._A.C.i.query.get.map(_ ==> List((2, 3)))


        _ <- A.i.OwnBb.i.Cc.i._B.s.insert(1, 2, 3, "a").transact
        _ <- A.i.OwnBb.i.Cc.i._B.s.query.get.map(_ ==> List((1, 2, 3, "a")))

        _ <- A.OwnBb.i.Cc.i._B.s.insert(2, 3, "a").transact
        _ <- A.OwnBb.i.Cc.i._B.s.query.get.map(_ ==> List((2, 3, "a")))

        _ <- A.OwnBb.Cc.i._B.s.insert(3, "a").transact
        _ <- A.OwnBb.Cc.i._B.s.query.get.map(_ ==> List((3, "a")))


        _ <- A.i.OwnBb.i.Cc.i._B.s.D.i.insert(1, 2, 3, "a", 4).transact
        _ <- A.i.OwnBb.i.Cc.i._B.s.D.i.query.get.map(_ ==> List((1, 2, 3, "a", 4)))

        _ <- A.i.OwnBb.i.Cc.i._B.D.i.insert(1, 2, 3, 4).transact
        _ <- A.i.OwnBb.i.Cc.i._B.D.i.query.get.map(_ ==> List((1, 2, 3, 4)))

        _ <- A.i.OwnBb.Cc.i._B.D.i.insert(1, 3, 4).transact
        _ <- A.i.OwnBb.Cc.i._B.D.i.query.get.map(_ ==> List((1, 3, 4)))

        _ <- A.OwnBb.Cc.i._B.D.i.insert(3, 4).transact
        _ <- A.OwnBb.Cc.i._B.D.i.query.get.map(_ ==> List((3, 4)))


        _ <- A.i.OwnBb.i.Cc.i._B.s._A.s.insert(1, 2, 3, "a", "b").transact
        _ <- A.i.OwnBb.i.Cc.i._B.s._A.s.query.get.map(_ ==> List((1, 2, 3, "a", "b")))

        _ <- A.i.OwnBb.i.Cc.i._B._A.s.insert(1, 2, 3, "b").transact
        _ <- A.i.OwnBb.i.Cc.i._B._A.s.query.get.map(_ ==> List((1, 2, 3, "b")))

        _ <- A.i.OwnBb.Cc.i._B._A.s.insert(1, 3, "b").transact
        _ <- A.i.OwnBb.Cc.i._B._A.s.query.get.map(_ ==> List((1, 3, "b")))

        _ <- A.OwnBb.Cc.i._B._A.s.insert(3, "b").transact
        _ <- A.OwnBb.Cc.i._B._A.s.query.get.map(_ ==> List((3, "b")))


        _ <- A.i.OwnBb.i.Cc.i._B.s._A.s.D.i.insert(1, 2, 3, "a", "b", 4).transact
        _ <- A.i.OwnBb.i.Cc.i._B.s._A.s.D.i.query.get.map(_ ==> List((1, 2, 3, "a", "b", 4)))

        // Distinguish separate relationships to same namespace
        // card-many B and card-one B should be distinguished from each other
        _ <- A.i.OwnBb.i._A.s.OwnB.s.insert(1, 2, "a", "b").transact
        _ <- A.i.OwnBb.i._A.s.OwnB.s.query.get.map(_ ==> List((1, 2, "a", "b")))

        _ <- A.i.s.OwnB.i.s.Cc.i._B.C.i.s._B._A.OwnBb.i.insert(
          (0, "a", 1, "b", 22, 2, "c", 11),
          (1, "a", 1, "b", 22, 2, "c", 11),
        ).transact

        _ <- A.i.a1.s.OwnB.i.s.Cc.i._B.C.i.s._B._A.OwnBb.i.query.get.map(_ ==> List(
          (0, "a", 1, "b", 22, 2, "c", 11),
          (1, "a", 1, "b", 22, 2, "c", 11),
        ))
      } yield ()
    }


    "self-join, one" - refs { implicit conn =>
      for {
        _ <- A.i.A.i.insert(1, 2).transact
        _ <- A.i.A.i.query.get.map(_ ==> List((1, 2)))

        _ <- A.i.A.i.OwnB.i.insert(1, 2, 3).transact
        _ <- A.i.A.i.OwnB.i.query.get.map(_ ==> List((1, 2, 3)))

        _ <- A.i.OwnB.i.A.i.insert(1, 2, 3).transact
        _ <- A.i.OwnB.i.A.i.query.get.map(_ ==> List((1, 2, 3)))

        _ <- A.i.OwnB.i.B.i.insert(1, 2, 3).transact
        _ <- A.i.OwnB.i.B.i.query.get.map(_ ==> List((1, 2, 3)))
      } yield ()
    }


    "self-join, many" - refs { implicit conn =>
      for {
        _ <- A.i.Aa.i.insert(1, 2).transact
        _ <- A.i.Aa.i.query.get.map(_ ==> List((1, 2)))

        _ <- A.i.Aa.i.OwnBb.i.insert(1, 2, 3).transact
        _ <- A.i.Aa.i.OwnBb.i.query.get.map(_ ==> List((1, 2, 3)))

        _ <- A.i.OwnBb.i.Aa.i.insert(1, 2, 3).transact
        _ <- A.i.OwnBb.i.Aa.i.query.get.map(_ ==> List((1, 2, 3)))

        _ <- A.i.OwnBb.i.Bb.i.insert(1, 2, 3).transact
        _ <- A.i.OwnBb.i.Bb.i.query.get.map(_ ==> List((1, 2, 3)))
      } yield ()
    }


    "ids, ref, card-one" - refs { implicit conn =>
      for {
        // Ids of first namespace entities returned
        List(a1, a2) <- A.i.OwnB.i.insert(
          (1, 2),
          (3, 4),
        ).transact.map(_.ids)

        _ <- A(a1).i.query.get.map(_ ==> List(1))
        _ <- A(a2).i.query.get.map(_ ==> List(3))

        // Ref ids
        _ <- if (database == "MongoDB") {
          // Ids not added to embedded documents in MongoDB
          Future.unit
        } else {
          for {
            List(b1, b2) <- A(a1, a2).ownB.query.get

            _ <- B(b1).i.query.get.map(_ ==> List(2))
            _ <- B(b2).i.query.get.map(_ ==> List(4))

            _ <- A.id.i.a1.ownB.query.get.map(_ ==> List(
              (a1, 1, b1),
              (a2, 3, b2),
            ))
          } yield ()
        }
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

          // Can't query for non-existing ownB id in MongoDB
          //_ <- A.id.i.ownB.query

          _ <- A.id(a1, a2).i.a1.OwnB.i.query.get.map(_ ==> List(
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

          // Can't query for non-existing ownBb id in MongoDB
          //_ <- A.id.i.ownBb.query

          _ <- A.id(a1, a2).i.a1.OwnBb.i.query.get.map(_ ==> List(
            (a1, 1, 2),
            (a2, 3, 4),
          ))
        } yield ()

      } else {

        // Other databases
        for {
          //          // Card one
          //          List(a1, b1, a2, b2) <- A.i.OwnB.i.insert(
          //            (1, 2),
          //            (3, 4),
          //          ).transact.map(_.ids)
          //
          //          _ <- A(a1).i.query.get.map(_ ==> List(1))
          //          _ <- B(b1).i.query.get.map(_ ==> List(2))
          //
          //          _ <- A(a2).i.query.get.map(_ ==> List(3))
          //          _ <- B(b2).i.query.get.map(_ ==> List(4))
          //
          //          _ <- A.id.i.a1.ownB.query.get.map(_ ==> List(
          //            (a1, 1, b1),
          //            (a2, 3, b2),
          //          ))
          //
          //          // Card Set
          //          List(a1, b1, a2, b2) <- A.i.OwnBb.i.insert(
          //            (1, 2),
          //            (3, 4),
          //          ).transact.map(_.ids)
          //
          //          _ <- A(a1).i.query.get.map(_ ==> List(1))
          //          _ <- B(b1).i.query.get.map(_ ==> List(2))
          //
          //          _ <- A(a2).i.query.get.map(_ ==> List(3))
          //          _ <- B(b2).i.query.get.map(_ ==> List(4))
          //
          //          _ <- A.id.i.a1.ownBb.query.get.map(_ ==> List(
          //            (a1, 1, Set(b1)),
          //            (a2, 3, Set(b2)),
          //          ))


          // Card one

          // Ids of first namespace entities returned
          List(a1, a2) <- A.i.OwnB.i.insert(
            (1, 2),
            (3, 4),
          ).transact.map(_.ids)

          _ <- A(a1).i.query.get.map(_ ==> List(1))
          _ <- A(a2).i.query.get.map(_ ==> List(3))

          // Ref ids
          List(b1, b2) <- A(a1, a2).ownB.query.get

          _ <- B(b1).i.query.get.map(_ ==> List(2))
          _ <- B(b2).i.query.get.map(_ ==> List(4))

          _ <- A.id.i.a1.ownB.query.get.map(_ ==> List(
            (a1, 1, b1),
            (a2, 3, b2),
          ))


          // Card Set

          // Ids of first namespace entities returned
          List(a1, a2) <- A.i.OwnBb.i.insert(
            (1, 2),
            (3, 4),
          ).transact.map(_.ids)

          _ <- A(a1).i.query.get.map(_ ==> List(1))
          _ <- A(a2).i.query.get.map(_ ==> List(3))

          _ <- if (database == "MongoDB") {
            // Ids not added to embedded documents in MongoDB
            Future.unit
          } else {
            for {
              // Getting head of each Set ref ids (card-set)
              List(b1) <- A(a1).ownBb.query.get.map(_.map(_.head))
              List(b2) <- A(a2).ownBb.query.get.map(_.map(_.head))

              _ <- B(b1).i.query.get.map(_ ==> List(2))
              _ <- B(b2).i.query.get.map(_ ==> List(4))

              _ <- A.id.i.a1.ownBb.query.get.map(_ ==> List(
                (a1, 1, Set(b1)),
                (a2, 3, Set(b2)),
              ))
            } yield ()
          }
        } yield ()
      }
    }

    "ids, backref" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {
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
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {
        // 2 A entity ids returned (no Bb ref ids)
        List(a1, a2) <- A.i.OwnBb.*(B.i).insert(
          (1, List(1, 2)),
          (2, Nil),
        ).transact.map(_.ids)

        _ <- A.id.i.a1.OwnBb.*?(B.i.a1).query.get.map(_ ==> List(
          (a1, 1, List(1, 2)),
          (a2, 2, Nil),
        ))
      } yield ()
    }

    "ids, nested + ref" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {
        // Ids of first namespace entities returned
        List(a1, a2) <- A.i.OwnBb.*(B.i.C.i).insert(
          (1, List((1, 2), (3, 4))),
          (2, Nil),
        ).transact.map(_.ids)

        _ <- A.id(a1, a2).i.a1.OwnBb.*?(B.i.a1.C.i).query.get.map(_ ==> List(
          (a1, 1, List((1, 2), (3, 4))),
          (a2, 2, Nil),
        ))
      } yield ()
    }

    "ids, ref + nested + ref " - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {
        // Ids of first namespace entities returned
        List(a1, a2) <- A.i.OwnB.i.Cc.*(C.i.D.i).insert(
          (1, 10, List((1, 2), (3, 4))),
          (2, 20, Nil),
        ).transact.map(_.ids)

        _ <- A.id(a1, a2).i.a1.OwnB.i.Cc.*?(C.i.a1.D.i).query.get.map(_ ==> List(
          (a1, 1, 10, List((1, 2), (3, 4))),
          (a2, 2, 20, Nil),
        ))
      } yield ()
    }

    "ids, self-join + nested + ref " - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {
        // Ids of first namespace entities returned
        List(a1, a2) <- A.i.OwnA.i.Cc.*(C.i.D.i).insert(
          (1, 10, List((1, 2), (3, 4))),
          (2, 20, Nil),
        ).transact.map(_.ids)

        _ <- A.id(a1, a2).i.a1.OwnA.i.Cc.*?(C.i.a1.D.i).query.get.map(_ ==> List(
          (a1, 1, 10, List((1, 2), (3, 4))),
          (a2, 2, 20, Nil),
        ))
      } yield ()
    }
  }
}
