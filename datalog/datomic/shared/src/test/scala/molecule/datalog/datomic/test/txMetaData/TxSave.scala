package molecule.datalog.datomic.test.txMetaData

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.datalog.datomic.async._
import molecule.datalog.datomic.setup.DatomicTestSuite
import utest._

object TxSave extends DatomicTestSuite {


  override lazy val tests = Tests {

    "Mandatory tx attr" - refs { implicit conn =>
      for {
        _ <- Ns.i(1).Tx(R2.i(7)).save.transact
        _ <- Ns.i.Tx(R2.i).query.get.map(_ ==> List((1, 7)))
      } yield ()
    }

    "Tacit tx attr" - refs { implicit conn =>
      // Same effect as with mandatory tx meta attr
      // To keep parity with insert tx meta attrs
      for {
        _ <- Ns.i(1).Tx(R2.i(7)).save.transact
        _ <- Ns.i.Tx(R2.i).query.get.map(_ ==> List((1, 7)))
      } yield ()
    }

    "Optional tx attr" - refs { implicit conn =>
      for {
        _ <- Ns.i(1).Tx(R2.i(7).s_?(Some("tx"))).save.transact
        _ <- Ns.i.Tx(R2.i.s_?).query.get.map(_ ==> List(
          (1, 7, Some("tx"))
        ))

        _ <- Ns.i(2).Tx(R2.i(7).s_?(Option.empty[String])).save.transact
        _ <- Ns.i(2).Tx(R2.i.s_?).query.get.map(_ ==> List(
          (2, 7, None)
        ))
      } yield ()
    }

    "Multiple attrs" - refs { implicit conn =>
      for {
        _ <- Ns.i(1).Tx(R2.i(7).s("tx").ii(Set(8, 9))).save.transact
        _ <- Ns.i.Tx(R2.i.s.ii).query.get.map(_ ==> List(
          (1, 7, "tx", Set(8, 9))
        ))
      } yield ()
    }


    "Ref + tx meta data" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {
        _ <- Ns.i(0).R1.i(1).Tx(R2.i(2)).save.transact
        _ <- Ns.i.R1.i.Tx.apply(R2.i).query.get.map(_.head ==> (0, 1, 2))
      } yield ()
    }


    "Refs + tx meta data" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {
        _ <- Ns.i(0).R1.i(1).R2.i(2).Tx(R3.i(3)).save.transact
        _ <- Ns.i(0).R1.i(1).R2.i(2).Tx(R3.i(3)).query.get.map(_.head ==> (0, 1, 2, 3))
      } yield ()
    }


    "Ref + tx meta data composites" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {
        _ <- Ns.i(0).R1.i(1).Tx(R2.i(2) + R3.i(3)).save.transact
        _ <- Ns.i(0).R1.i(1).Tx(R2.i(2) + R3.i(3)).query.get.map(_.head ==> (0, 1, 2, 3))
      } yield ()
    }


    "Refs + tx meta data composites" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {
        _ <- Ns.i(0).R1.i(1).R2.i(2).Tx(R3.i(3) + R4.i(4)).save.transact
        _ <- Ns.i(0).R1.i(1).R2.i(2).Tx(R3.i(3) + R4.i(4)).query.get.map(_.head ==> (0, 1, 2, 3, 4))
      } yield ()
    }


    "Tx refs" - refs { implicit conn =>
      for {
        // Saving tx meta data (Ref.s) that references another namespace attribute (Ref1.int1)
        _ <- Ns.i(1).Tx(R1.s("a").R2.i(10)).save.transact

        // Tx meta data with ref attr
        _ <- Ns.i.Tx(R1.s.R2.i).query.get.map(_ ==> List(
          (1, "a", 10)
        ))

        // Tx meta data
        _ <- Ns.i.Tx(R1.s).query.get.map(_ ==> List(
          (1, "a")
        ))

        // OBS: R2.i is not asserted with tx entity, but with ref from tx entity!
        _ <- Ns.i.Tx(R2.i).query.get.map(_ ==> Nil)

        // Saving multiple tx refs
        _ <- Ns.i(2).Tx(R1.s("b").R2.i(20).R3.i(200)).save.transact

        // Getting multiple tx refs
        _ <- Ns.i.Tx(R1.s.R2.i.R3.i).query.get.map(_ ==> List(
          (2, "b", 20, 200)
        ))
        _ <- Ns.i.a1.Tx(R1.s.R2.i).query.get.map(_ ==> List(
          (1, "a", 10), // First insert matches too
          (2, "b", 20)
        ))
        _ <- Ns.i.a1.Tx(R1.s).query.get.map(_ ==> List(
          (1, "a"),
          (2, "b")
        ))
      } yield ()
    }

    "Tx backref" - refs { implicit conn =>
      for {
        _ <- Ns.i(1).Tx(R2.i(7).R3.i(8)._R2.s("tx")).save.transact
        _ <- Ns.i.Tx(R2.i.R3.i._R2.s).query.get.map(_ ==> List(
          (1, 7, 8, "tx")
        ))
      } yield ()
    }

    "Tx composite" - refs { implicit conn =>
      for {
        _ <- Ns.i(1).Tx(R2.i(7).s("tx") + R1.i(8)).save.transact
        _ <- Ns.i.Tx(R2.i.s + R1.i).query.get.map(_ ==> List(
          (1, (7, "tx"), 8)
        ))

        _ <- Ns.i(2).s("a").Tx(R2.i(7).s("tx") + R1.i(8)).save.transact
        _ <- Ns.i.s.Tx(R2.i.s + R1.i).query.get.map(_ ==> List(
          (2, "a", (7, "tx"), 8)
        ))
      } yield ()
    }


    "Composite + tx" - refs { implicit conn =>
      for {
        _ <- (Ns.i(1) + R2.i(7).s("a")).Tx(R4.i(8).s("tx")).save.transact
        _ <- (Ns.i + R2.i.s).Tx(R4.i.s).query.get.map(_ ==> List(
          (1, (7, "a"), 8, "tx")
        ))
      } yield ()
    }

    "Composite + tx composite" - refs { implicit conn =>
      for {
        _ <- (Ns.i(1) + R2.i(7).s("a")).Tx(R3.i(8).s("tx") + R4.i(9)).save.transact
        _ <- (Ns.i + R2.i.s).Tx(R3.i.s + R4.i).query.get.map(_ ==> List(
          (1, (7, "a"), (8, "tx"), 9)
        ))
      } yield ()
    }
  }
}
