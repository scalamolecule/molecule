package molecule.db.datomic.test.relation.flat

import molecule.base.util.exceptions.MoleculeError
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._
import molecule.core.util.Executor._


object FlatRefs extends DatomicTestSuite {


  lazy val tests = Tests {

    "ref" - refs { implicit conn =>
      for {
      _ <- Ns.i.R1.i.insert(1, 2).transact
      _ <- Ns.i.R1.i.query.get.map(_ ==> List((1, 2)))
    } yield ()
    }


    "backref" - refs { implicit conn =>
      for {
      _ <- Ns.i.R1.i._Ns.Rs1.i.insert(1, 2, 3).transact
      _ <- Ns.i.R1.i._Ns.Rs1.i.query.get.map(_ ==> List((1, 2, 3)))
    } yield ()
    }


    "Adjacent ref without attribute" - refs { implicit conn =>
      for {
      _ <- Ns.i.R1.i.R2.i.insert(1, 2, 3).transact
      _ <- Ns.i.R1.R2.i.query.get.map(_ ==> List((1, 3)))
    } yield ()
    }


    "complex" - refs { implicit conn =>
      for {
      _ <- Ns.i(0).s("a")
        .R1.i(1).s("b")
        .Rs2.i(22)._R1
        .R2.i(2).s("c")._R1._Ns
        .Rs1.i(11)
        .save.transact

      _ <- Ns.i.R1.i.query.get.map(_ ==> List((0, 1)))
      _ <- Ns.i.R1.i._Ns.s.query.get.map(_ ==> List((0, 1, "a")))
      _ <- Ns.i.R1.i._Ns.Rs1.i.query.get.map(_ ==> List((0, 1, 11)))
      _ <- Ns.i.R1.i.R2.i._R1.s.query.get.map(_ ==> List((0, 1, 2, "b")))
      _ <- Ns.i.R1.R2.i._R1.i.query.get.map(_ ==> List((0, 2, 1)))
      _ <- Ns.i.R1.i.R2.i._R1.Rs2.i.query.get.map(_ ==> List((0, 1, 2, 22)))
      _ <- Ns.i.R1.R2.i._R1.Rs2.i.query.get.map(_ ==> List((0, 2, 22)))
      _ <- Ns.i.R1.i.R2.i._R1.s._Ns.s.query.get.map(_ ==> List((0, 1, 2, "b", "a")))
      _ <- Ns.i.R1.i.R2.i._R1._Ns.s.query.get.map(_ ==> List((0, 1, 2, "a")))
      _ <- Ns.i.R1.R2.i._R1._Ns.s.query.get.map(_ ==> List((0, 2, "a")))
      _ <- Ns.i.R1.i.R2.i._R1.s._Ns.Rs1.i.query.get.map(_ ==> List((0, 1, 2, "b", 11)))
      _ <- Ns.i.R1.i.R2.i._R1._Ns.Rs1.i.query.get.map(_ ==> List((0, 1, 2, 11)))
      _ <- Ns.i.R1.R2.i._R1._Ns.Rs1.i.query.get.map(_ ==> List((0, 2, 11)))
      _ <- Ns.R1.R2.s._R1._Ns.Rs1.i.query.get.map(_ ==> List(("c", 11)))
    } yield ()
    }


    "ref attributes" - refs { implicit conn =>
      for {
      // Card one ref attr
      List(_, e1) <- Ns.i.R1.i.insert(1, 2).transact.map(_.eids)
      _ <- Ns.i.r1.query.get.map(_ ==> List((1, e1)))

      // Card many ref attr (returned as Set)
      List(_, e2) <- Ns.i.Rs1.i.insert(1, 2).transact.map(_.eids)
      _ <- Ns.i.rs1.query.get.map(_ ==> List((1, Set(e2))))
    } yield ()
    }


    "multiple card-many refs" - refs { implicit conn =>
      for {
      // Two entities to be referenced
      List(b1, b2) <- R1.i.insert(1, 2).transact.map(_.eids)

      // Reference Set of entities
      _ <- Ns.i(0).rs1(Set(b1, b2)).save.transact

      // Saving individual ref ids (not in a Set) is not allowed
      _ <- Ns.i(0).rs1(b1, b2).save.transact
        .map(_ ==> "Unexpected success").recover { case MoleculeError(err, _) =>
        err ==> "Can only save one Set of values for Set attribute `Ns.rs1`. " +
          s"Found: Set($b1), Set($b2)"
      }

      // Referencing namespace attributes repeat for each referenced entity
      _ <- Ns.i.Rs1.i.query.get.map(_ ==> List(
        (0, 1),
        (0, 2), // 0 is repeated
      ))

      // Card many ref attributes return Set of ref ids
      _ <- Ns.i.rs1.query.get.map(_ ==> List((0, Set(b1, b2))))
    } yield()
  }
}
}
