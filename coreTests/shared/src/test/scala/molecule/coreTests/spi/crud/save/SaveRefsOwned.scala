package molecule.coreTests.spi.crud.save

import molecule.base.error.ModelError
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._
import scala.language.implicitConversions


trait SaveRefsOwned extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "card one" - refs { implicit conn =>
      for {
        _ <- A.OwnB.i(1).save.transact
        _ <- A.OwnB.i.query.get.map(_ ==> List(1))

        _ <- A.i(1).OwnB.i(2).save.transact
        _ <- A.i.OwnB.i.query.get.map(_ ==> List((1, 2)))


        _ <- A.OwnB.C.i(1).save.transact
        _ <- A.OwnB.C.i.query.get.map(_ ==> List(1))

        _ <- A.OwnB.i(1).C.i(2).save.transact
        _ <- A.OwnB.i.C.i.query.get.map(_ ==> List((1, 2)))

        _ <- A.i(1).OwnB.C.i(2).save.transact
        _ <- A.i.OwnB.C.i.query.get.map(_ ==> List((1, 2)))

        _ <- A.i(1).OwnB.i(2).C.i(3).save.transact
        _ <- A.i.OwnB.i.C.i.query.get.map(_ ==> List((1, 2, 3)))
      } yield ()
    }


    "card many" - refs { implicit conn =>
      for {
        _ <- A.OwnBb.i(1).save.transact
        _ <- A.OwnBb.i.query.get.map(_ ==> List(1))

        _ <- A.i(1).OwnBb.i(2).save.transact
        _ <- A.i.OwnBb.i.query.get.map(_ ==> List((1, 2)))


        _ <- A.OwnBb.Cc.i(1).save.transact
        _ <- A.OwnBb.Cc.i.query.get.map(_ ==> List(1))

        _ <- A.OwnBb.i(1).Cc.i(2).save.transact
        _ <- A.OwnBb.i.Cc.i.query.get.map(_ ==> List((1, 2)))

        _ <- A.i(1).OwnBb.Cc.i(2).save.transact
        _ <- A.i.OwnBb.Cc.i.query.get.map(_ ==> List((1, 2)))

        _ <- A.i(1).OwnBb.i(2).Cc.i(3).save.transact
        _ <- A.i.OwnBb.i.Cc.i.query.get.map(_ ==> List((1, 2, 3)))
      } yield ()
    }


    "card one/many" - refs { implicit conn =>
      for {
        _ <- A.OwnB.Cc.i(1).save.transact
        _ <- A.OwnB.Cc.i.query.get.map(_ ==> List(1))

        _ <- A.i(1).OwnB.Cc.i(2).save.transact
        _ <- A.i.OwnB.Cc.i.query.get.map(_ ==> List((1, 2)))

        _ <- A.OwnB.i(1).Cc.i(2).save.transact
        _ <- A.OwnB.i.Cc.i.query.get.map(_ ==> List((1, 2)))

        _ <- A.i(1).OwnB.i(2).Cc.i(3).save.transact
        _ <- A.i.OwnB.i.Cc.i.query.get.map(_ ==> List((1, 2, 3)))
      } yield ()
    }


    "card many/one" - refs { implicit conn =>
      for {
        _ <- A.OwnBb.C.i(1).save.transact
        _ <- A.OwnBb.C.i.query.get.map(_ ==> List(1))

        _ <- A.OwnBb.i(1).C.i(2).save.transact
        _ <- A.OwnBb.i.C.i.query.get.map(_ ==> List((1, 2)))

        _ <- A.i(1).OwnBb.C.i(2).save.transact
        _ <- A.i.OwnBb.C.i.query.get.map(_ ==> List((1, 2)))

        _ <- A.i(1).OwnBb.i(2).C.i(3).save.transact
        _ <- A.i.OwnBb.i.C.i.query.get.map(_ ==> List((1, 2, 3)))
      } yield ()
    }


    "backref, card one" - refs { implicit conn =>
      for {
        // Can't go back from empty namespaces
        _ <- A.i(1).OwnB._A.s("a").save.transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Please add attributes to namespace B before going back to namespace A"
          }

        _ <- A.i(1).OwnB.i(2)._A.s("a").save.transact
        _ <- A.i.OwnB.i._A.s.query.get.map(_ ==> List((1, 2, "a")))

        _ <- A.OwnB.i(2)._A.s("a").save.transact
        _ <- A.OwnB.i._A.s.query.get.map(_ ==> List((2, "a")))


        _ <- A.i(1).OwnB.i(2)._A.s("a").C.i(3).save.transact
        _ <- A.i.OwnB.i._A.s.C.i.query.get.map(_ ==> List((1, 2, "a", 3)))

        _ <- A.i(1).OwnB.i(2)._A.C.i(3).save.transact
        _ <- A.i.OwnB.i._A.C.i.query.get.map(_ ==> List((1, 2, 3)))

        _ <- A.OwnB.i(2)._A.s("a").C.i(3).save.transact
        _ <- A.OwnB.i._A.s.C.i.query.get.map(_ ==> List((2, "a", 3)))

        _ <- A.OwnB.i(2)._A.C.i(3).save.transact
        _ <- A.OwnB.i._A.C.i.query.get.map(_ ==> List((2, 3)))


        _ <- A.i(1).OwnB.i(2).C.i(3)._B.s("a").save.transact
        _ <- A.i.OwnB.i.C.i._B.s.query.get.map(_ ==> List((1, 2, 3, "a")))

        _ <- A.OwnB.i(2).C.i(3)._B.s("a").save.transact
        _ <- A.OwnB.i.C.i._B.s.query.get.map(_ ==> List((2, 3, "a")))

        _ <- A.OwnB.C.i(3)._B.s("a").save.transact
        _ <- A.OwnB.C.i._B.s.query.get.map(_ ==> List((3, "a")))


        _ <- A.i(1).OwnB.i(2).C.i(3)._B.s("a").D.i(4).save.transact
        _ <- A.i.OwnB.i.C.i._B.s.D.i.query.get.map(_ ==> List((1, 2, 3, "a", 4)))

        _ <- A.i(1).OwnB.i(2).C.i(3)._B.D.i(4).save.transact
        _ <- A.i.OwnB.i.C.i._B.D.i.query.get.map(_ ==> List((1, 2, 3, 4)))

        _ <- A.i(1).OwnB.C.i(3)._B.D.i(4).save.transact
        _ <- A.i.OwnB.C.i._B.D.i.query.get.map(_ ==> List((1, 3, 4)))

        _ <- A.OwnB.C.i(3)._B.D.i(4).save.transact
        _ <- A.OwnB.C.i._B.D.i.query.get.map(_ ==> List((3, 4)))


        _ <- A.i(1).OwnB.i(2).C.i(3)._B.s("a")._A.s("b").save.transact
        _ <- A.i.OwnB.i.C.i._B.s._A.s.query.get.map(_ ==> List((1, 2, 3, "a", "b")))

        _ <- A.i(1).OwnB.i(2).C.i(3)._B._A.s("b").save.transact
        _ <- A.i.OwnB.i.C.i._B._A.s.query.get.map(_ ==> List((1, 2, 3, "b")))

        _ <- A.i(1).OwnB.C.i(3)._B._A.s("b").save.transact
        _ <- A.i.OwnB.C.i._B._A.s.query.get.map(_ ==> List((1, 3, "b")))

        _ <- A.OwnB.C.i(3)._B._A.s("b").save.transact
        _ <- A.OwnB.C.i._B._A.s.query.get.map(_ ==> List((3, "b")))


        _ <- A.i(1).OwnB.i(2).C.i(3)._B.s("a")._A.s("b").D.i(4).save.transact
        _ <- A.i.OwnB.i.C.i._B.s._A.s.D.i.query.get.map(_ ==> List((1, 2, 3, "a", "b", 4)))


        // Distinguish separate relationships to same namespace
        _ <- A.i(1).OwnB.i(2)._A.B1.i(3).save.transact
        _ <- A.i.OwnB.i._A.B1.i.query.get.map(_ ==> List((1, 2, 3)))

        _ <- A.i(1).OwnB.i(2)._A.B1.i(3)._A.B2.i(4).save.transact
        _ <- A.i.OwnB.i._A.B1.i._A.B2.i.query.get.map(_ ==> List((1, 2, 3, 4)))
      } yield ()
    }


    "backref, card many" - refs { implicit conn =>
      for {
        _ <- A.i(1).OwnBb.i(2)._A.s("a").save.transact
        _ <- A.i.OwnBb.i._A.s.query.get.map(_ ==> List((1, 2, "a")))

        _ <- A.OwnB.i(2)._A.s("a").save.transact
        _ <- A.OwnB.i._A.s.query.get.map(_ ==> List((2, "a")))

        _ <- A.i(1).OwnBb.i(2)._A.s("a").save.transact
        _ <- A.i.OwnBb.i._A.s.query.get.map(_ ==> List((1, 2, "a")))

        _ <- A.OwnBb.i(2)._A.s("a").save.transact
        _ <- A.OwnBb.i._A.s.query.get.map(_ ==> List((2, "a")))


        _ <- A.i(1).OwnBb.i(2)._A.s("a").C.i(3).save.transact
        _ <- A.i.OwnBb.i._A.s.C.i.query.get.map(_ ==> List((1, 2, "a", 3)))

        _ <- A.i(1).OwnBb.i(2)._A.C.i(3).save.transact
        _ <- A.i.OwnBb.i._A.C.i.query.get.map(_ ==> List((1, 2, 3)))

        _ <- A.OwnBb.i(2)._A.s("a").C.i(3).save.transact
        _ <- A.OwnBb.i._A.s.C.i.query.get.map(_ ==> List((2, "a", 3)))

        _ <- A.OwnBb.i(2)._A.C.i(3).save.transact
        _ <- A.OwnBb.i._A.C.i.query.get.map(_ ==> List((2, 3)))


        _ <- A.i(1).OwnBb.i(2).Cc.i(3)._B.s("a").save.transact
        _ <- A.i.OwnBb.i.Cc.i._B.s.query.get.map(_ ==> List((1, 2, 3, "a")))

        _ <- A.OwnBb.i(2).Cc.i(3)._B.s("a").save.transact
        _ <- A.OwnBb.i.Cc.i._B.s.query.get.map(_ ==> List((2, 3, "a")))

        _ <- A.OwnBb.Cc.i(3)._B.s("a").save.transact
        _ <- A.OwnBb.Cc.i._B.s.query.get.map(_ ==> List((3, "a")))


        _ <- A.i(1).OwnBb.i(2).Cc.i(3)._B.s("a").D.i(4).save.transact
        _ <- A.i.OwnBb.i.Cc.i._B.s.D.i.query.get.map(_ ==> List((1, 2, 3, "a", 4)))

        _ <- A.i(1).OwnBb.i(2).Cc.i(3)._B.D.i(4).save.transact
        _ <- A.i.OwnBb.i.Cc.i._B.D.i.query.get.map(_ ==> List((1, 2, 3, 4)))

        _ <- A.i(1).OwnBb.Cc.i(3)._B.D.i(4).save.transact
        _ <- A.i.OwnBb.Cc.i._B.D.i.query.get.map(_ ==> List((1, 3, 4)))

        _ <- A.OwnBb.Cc.i(3)._B.D.i(4).save.transact
        _ <- A.OwnBb.Cc.i._B.D.i.query.get.map(_ ==> List((3, 4)))


        _ <- A.i(1).OwnBb.i(2).Cc.i(3)._B.s("a")._A.s("b").save.transact
        _ <- A.i.OwnBb.i.Cc.i._B.s._A.s.query.get.map(_ ==> List((1, 2, 3, "a", "b")))

        _ <- A.i(1).OwnBb.i(2).Cc.i(3)._B._A.s("b").save.transact
        _ <- A.i.OwnBb.i.Cc.i._B._A.s.query.get.map(_ ==> List((1, 2, 3, "b")))

        _ <- A.i(1).OwnBb.Cc.i(3)._B._A.s("b").save.transact
        _ <- A.i.OwnBb.Cc.i._B._A.s.query.get.map(_ ==> List((1, 3, "b")))

        _ <- A.OwnBb.Cc.i(3)._B._A.s("b").save.transact
        _ <- A.OwnBb.Cc.i._B._A.s.query.get.map(_ ==> List((3, "b")))


        _ <- A.i(1).OwnBb.i(2).Cc.i(3)._B.s("a")._A.s("b").D.i(4).save.transact
        _ <- A.i.OwnBb.i.Cc.i._B.s._A.s.D.i.query.get.map(_ ==> List((1, 2, 3, "a", "b", 4)))

        // Distinguish separate relationships to same namespace
        // card-many B and card-one B should be distinguished from each other
        _ <- A.i(1).OwnBb.i(2)._A.s("a").OwnB.s("b").save.transact
        _ <- A.i.OwnBb.i._A.s.OwnB.s.query.get.map(_ ==> List((1, 2, "a", "b")))

        _ <- A.i(0).s("a").OwnB.i(1).s("b").Cc.i(22)._B.C.i(2).s("c")._B._A.OwnBb.i(11).save.transact
        _ <- A.i.s.OwnB.i.s.Cc.i._B.C.i.s._B._A.OwnBb.i.query.get.map(_ ==> List(
          (0, "a", 1, "b", 22, 2, "c", 11)
        ))
      } yield ()
    }


    "self-join, one" - refs { implicit conn =>
      for {
        _ <- A.i(1).A.i(2).save.transact
        _ <- A.i.A.i.query.get.map(_ ==> List((1, 2)))

        _ <- A.i(1).A.i(2).OwnB.i(3).save.transact
        _ <- A.i.A.i.OwnB.i.query.get.map(_ ==> List((1, 2, 3)))

        _ <- A.i(1).OwnB.i(2).A.i(3).save.transact
        _ <- A.i.OwnB.i.A.i.query.get.map(_ ==> List((1, 2, 3)))

        _ <- A.i(1).OwnB.i(2).B.i(3).save.transact
        _ <- A.i.OwnB.i.B.i.query.get.map(_ ==> List((1, 2, 3)))
      } yield ()
    }


    "self-join, many" - refs { implicit conn =>
      for {
        _ <- A.i(1).Aa.i(2).save.transact
        _ <- A.i.Aa.i.query.get.map(_ ==> List((1, 2)))

        _ <- A.i(1).Aa.i(2).OwnBb.i(3).save.transact
        _ <- A.i.Aa.i.OwnBb.i.query.get.map(_ ==> List((1, 2, 3)))

        _ <- A.i(1).OwnBb.i(2).Aa.i(3).save.transact
        _ <- A.i.OwnBb.i.Aa.i.query.get.map(_ ==> List((1, 2, 3)))

        _ <- A.i(1).OwnBb.i(2).Bb.i(3).save.transact
        _ <- A.i.OwnBb.i.Bb.i.query.get.map(_ ==> List((1, 2, 3)))
      } yield ()
    }


    "ids, owned ref" - refs { implicit conn =>
      if (database == "MongoDB") {
        // Can't query for non-existing ids of embedded documents in MongoDB
        for {
          // Card one
          List(a1) <- A.i(1).OwnB.i(2).save.transact.map(_.ids)

          _ <- A(a1).i.query.get.map(_ ==> List(1))

          // Can't query for non-existing ownB id
          // List(b1) <- A(a1).ownB.query.get

          _ <- A.id.i.OwnB.i.query.get.map(_ ==> List(
            (a1, 1, 2),
          ))


          // Card Set
          List(a1) <- A.i(1).OwnBb.i(2).save.transact.map(_.ids)

          _ <- A(a1).i.query.get.map(_ ==> List(1))

          // Can't query for non-existing ownBb id
          // b1 <- A(a1).ownBb.query.get.map(_.head.head)

          _ <- A.id.i.OwnBb.i.query.get.map(_ ==> List(
            (a1, 1, 2),
          ))
        } yield ()

      } else {

        // Other databases
        for {
          // Card one
          List(a1) <- A.i(1).OwnB.i(2).save.transact.map(_.ids)

          _ <- A(a1).i.query.get.map(_ ==> List(1))

          List(b1) <- A(a1).ownB.query.get

          _ <- A.id.i.ownB.query.get.map(_ ==> List(
            (a1, 1, b1),
          ))


          // Card Set
          List(a1) <- A.i(1).OwnBb.i(2).save.transact.map(_.ids)

          _ <- A(a1).i.query.get.map(_ ==> List(1))

          b1 <- A(a1).ownBb.query.get.map(_.head.head) // first row, first id in Set of ids (card-set)

          _ <- B(b1).i.query.get.map(_ ==> List(2))

          _ <- A.id.i.ownBb.query.get.map(_ ==> List(
            (a1, 1, Set(b1)),
          ))
        } yield ()
      }
    }
  }
}
