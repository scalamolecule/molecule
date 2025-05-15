package molecule.db.compliance.test.action.save

import molecule.db.base.error.ModelError
import molecule.db.compliance.domains.dsl.Refs.*
import molecule.db.compliance.setup.{DbProviders, Test, TestUtils}
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*


case class SaveRefs(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "card one" - refs { implicit conn =>
    for {
      _ <- A.i(1).B.i(2).save.transact
      _ <- A.i.B.i.query.get.map(_ ==> List((1, 2)))

      _ <- A.i(1).B.i(2).C.i(3).save.transact
      _ <- A.i.B.i.C.i.query.get.map(_ ==> List((1, 2, 3)))
    } yield ()
  }


  "card many" - refs { implicit conn =>
    for {
      _ <- A.i(1).Bb.i(2).save.transact
      _ <- A.i.Bb.i.query.get.map(_ ==> List((1, 2)))

      _ <- A.i(1).Bb.i(2).Cc.i(3).save.transact
      _ <- A.i.Bb.i.Cc.i.query.get.map(_ ==> List((1, 2, 3)))
    } yield ()
  }


  "card one/many" - refs { implicit conn =>
    for {
      _ <- A.i(1).B.i(2).Cc.i(3).save.transact
      _ <- A.i.B.i.Cc.i.query.get.map(_ ==> List((1, 2, 3)))
    } yield ()
  }


  "card many/one" - refs { implicit conn =>
    for {
      _ <- A.i(1).Bb.i(2).C.i(3).save.transact
      _ <- A.i.Bb.i.C.i.query.get.map(_ ==> List((1, 2, 3)))
    } yield ()
  }


  "backref, card one" - refs { implicit conn =>
    for {
      // Can't go back from empty entities
      _ <- A.i(1).B._A.s("a").save.transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Please add attributes to entity B before going back to entity A"
        }

      _ <- A.i(1).B.i(2)._A.s("a").save.transact
      _ <- A.i.B.i._A.s.query.get.map(_ ==> List((1, 2, "a")))

      _ <- A.i(1).B.i(2)._A.s("a").C.i(3).save.transact
      _ <- A.i.B.i._A.s.C.i.query.get.map(_ ==> List((1, 2, "a", 3)))

      _ <- A.i(1).B.i(2)._A.C.i(3).save.transact
      _ <- A.i.B.i._A.C.i.query.get.map(_ ==> List((1, 2, 3)))

      _ <- A.i(1).B.i(2).C.i(3)._B.s("a").save.transact
      _ <- A.i.B.i.C.i._B.s.query.get.map(_ ==> List((1, 2, 3, "a")))

      _ <- A.i(1).B.i(2).C.i(3)._B.s("a").D.i(4).save.transact
      _ <- A.i.B.i.C.i._B.s.D.i.query.get.map(_ ==> List((1, 2, 3, "a", 4)))

      _ <- A.i(1).B.i(2).C.i(3)._B.s("a")._A.s("b").save.transact
      _ <- A.i.B.i.C.i._B.s._A.s.query.get.map(_ ==> List((1, 2, 3, "a", "b")))

      _ <- A.i(1).B.i(2).C.i(3)._B._A.s("b").save.transact
      _ <- A.i.B.i.C.i._B._A.s.query.get.map(_ ==> List((1, 2, 3, "b")))

      _ <- A.i(1).B.i(2).C.i(3)._B.s("a")._A.s("b").D.i(4).save.transact
      _ <- A.i.B.i.C.i._B.s._A.s.D.i.query.get.map(_ ==> List((1, 2, 3, "a", "b", 4)))

      // Distinguish separate relationships to same entity
      _ <- A.i(1).B.i(2)._A.B1.i(3).save.transact
      _ <- A.i.B.i._A.B1.i.query.get.map(_ ==> List((1, 2, 3)))

      _ <- A.i(1).B.i(2)._A.B1.i(3)._A.B2.i(4).save.transact
      _ <- A.i.B.i._A.B1.i._A.B2.i.query.get.map(_ ==> List((1, 2, 3, 4)))
    } yield ()
  }


  "backref, card many" - refs { implicit conn =>
    for {
      _ <- A.i(1).Bb.i(2)._A.s("a").save.transact
      _ <- A.i.Bb.i._A.s.query.get.map(_ ==> List((1, 2, "a")))

      _ <- A.i(1).Bb.i(2)._A.s("a").save.transact
      _ <- A.i.Bb.i._A.s.query.get.map(_ ==> List((1, 2, "a")))

      _ <- A.i(1).Bb.i(2)._A.s("a").C.i(3).save.transact
      _ <- A.i.Bb.i._A.s.C.i.query.get.map(_ ==> List((1, 2, "a", 3)))

      _ <- A.i(1).Bb.i(2)._A.C.i(3).save.transact
      _ <- A.i.Bb.i._A.C.i.query.get.map(_ ==> List((1, 2, 3)))

      _ <- A.i(1).Bb.i(2).Cc.i(3)._B.s("a").save.transact
      _ <- A.i.Bb.i.Cc.i._B.s.query.get.map(_ ==> List((1, 2, 3, "a")))

      _ <- A.i(1).Bb.i(2).Cc.i(3)._B.s("a").D.i(4).save.transact
      _ <- A.i.Bb.i.Cc.i._B.s.D.i.query.get.map(_ ==> List((1, 2, 3, "a", 4)))

      _ <- A.i(1).Bb.i(2).Cc.i(3)._B.D.i(4).save.transact
      _ <- A.i.Bb.i.Cc.i._B.D.i.query.get.map(_ ==> List((1, 2, 3, 4)))

      _ <- A.i(1).Bb.i(2).Cc.i(3)._B.s("a")._A.s("b").save.transact
      _ <- A.i.Bb.i.Cc.i._B.s._A.s.query.get.map(_ ==> List((1, 2, 3, "a", "b")))

      _ <- A.i(1).Bb.i(2).Cc.i(3)._B._A.s("b").save.transact
      _ <- A.i.Bb.i.Cc.i._B._A.s.query.get.map(_ ==> List((1, 2, 3, "b")))

      _ <- A.i(1).Bb.i(2).Cc.i(3)._B.s("a")._A.s("b").D.i(4).save.transact
      _ <- A.i.Bb.i.Cc.i._B.s._A.s.D.i.query.get.map(_ ==> List((1, 2, 3, "a", "b", 4)))

      // Distinguish separate relationships to same entity
      // card-many B and card-one B should be distinguished from each other
      _ <- A.i(1).Bb.i(2)._A.s("a").B.s("b").save.transact
      _ <- A.i.Bb.i._A.s.B.s.query.get.map(_ ==> List((1, 2, "a", "b")))

      _ <- A.i(0).s("a").B.i(1).s("b").Cc.i(22)._B.C.i(2).s("c")._B._A.Bb.i(11).save.transact
      _ <- A.i.s.B.i.s.Cc.i._B.C.i.s._B._A.Bb.i.query.get.map(_ ==> List(
        (0, "a", 1, "b", 22, 2, "c", 11)
      ))
    } yield ()
  }


  "self-join, one" - refs { implicit conn =>
    for {
      _ <- A.i(1).A.i(2).save.transact
      _ <- A.i.A.i.query.get.map(_ ==> List((1, 2)))

      _ <- A.i(1).A.i(2).B.i(3).save.transact
      _ <- A.i.A.i.B.i.query.get.map(_ ==> List((1, 2, 3)))

      _ <- A.i(1).B.i(2).A.i(3).save.transact
      _ <- A.i.B.i.A.i.query.get.map(_ ==> List((1, 2, 3)))

      _ <- A.i(1).B.i(2).B.i(3).save.transact
      _ <- A.i.B.i.B.i.query.get.map(_ ==> List((1, 2, 3)))
    } yield ()
  }


  "self-join, many" - refs { implicit conn =>
    for {
      _ <- A.i(1).Aa.i(2).save.transact
      _ <- A.i.Aa.i.query.get.map(_ ==> List((1, 2)))

      _ <- A.i(1).Aa.i(2).Bb.i(3).save.transact
      _ <- A.i.Aa.i.Bb.i.query.get.map(_ ==> List((1, 2, 3)))

      _ <- A.i(1).Bb.i(2).Aa.i(3).save.transact
      _ <- A.i.Bb.i.Aa.i.query.get.map(_ ==> List((1, 2, 3)))

      _ <- A.i(1).Bb.i(2).Bb.i(3).save.transact
      _ <- A.i.Bb.i.Bb.i.query.get.map(_ ==> List((1, 2, 3)))
    } yield ()
  }


  "ids, ref" - refs { implicit conn =>
    for {
      // Card one
      List(a1) <- A.i(1).B.i(2).save.transact.map(_.ids)

      _ <- A(a1).i.query.get.map(_ ==> List(1))

      // Ref ids
      List(b1) <- A(a1).b.query.get

      _ <- B(b1).i.query.get.map(_ ==> List(2))

      _ <- A.id.i.b.query.get.map(_ ==> List(
        (a1, 1, b1),
      ))


      // Card Set
      List(a1) <- A.i(1).Bb.i(2).save.transact.map(_.ids)

      _ <- A(a1).i.query.get.map(_ ==> List(1))

      // Getting head of each Set ref ids (card-set)
      List(b1) <- A(a1).bb.query.get.map(_.map(_.head))

      _ <- B(b1).i.query.get.map(_ ==> List(2))

      _ <- A.id.i.bb.query.get.map(_ ==> List(
        (a1, 1, Set(b1)),
      ))
    } yield ()
  }


  "Optional ref" - refs { implicit conn =>
    for {
      _ <- A.i(1).B.?(B.i(2)).save.transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Optional ref not allowed in save molecule. Please use mandatory ref or insert instead."
        }
    } yield ()
  }
}
