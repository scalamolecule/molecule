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
        _ <- A.i(1).Tx(C.i(7)).save.transact
        _ <- A.i.Tx(C.i).query.get.map(_ ==> List((1, 7)))
      } yield ()
    }

    "Tacit tx attr" - refs { implicit conn =>
      // Same effect as with mandatory tx meta attr
      // To keep parity with insert tx meta attrs
      for {
        _ <- A.i(1).Tx(C.i(7)).save.transact
        _ <- A.i.Tx(C.i).query.get.map(_ ==> List((1, 7)))
      } yield ()
    }

    "Optional tx attr" - refs { implicit conn =>
      for {
        _ <- A.i(1).Tx(C.i(7).s_?(Some("tx"))).save.transact
        _ <- A.i.Tx(C.i.s_?).query.get.map(_ ==> List(
          (1, 7, Some("tx"))
        ))

        _ <- A.i(2).Tx(C.i(7).s_?(Option.empty[String])).save.transact
        _ <- A.i(2).Tx(C.i.s_?).query.get.map(_ ==> List(
          (2, 7, None)
        ))
      } yield ()
    }

    "Multiple attrs" - refs { implicit conn =>
      for {
        _ <- A.i(1).Tx(C.i(7).s("tx").ii(Set(8, 9))).save.transact
        _ <- A.i.Tx(C.i.s.ii).query.get.map(_ ==> List(
          (1, 7, "tx", Set(8, 9))
        ))
      } yield ()
    }


    "Ref + tx meta data" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {
        _ <- A.i(0).B.i(1).Tx(C.i(2)).save.transact
        _ <- A.i.B.i.Tx.apply(C.i).query.get.map(_.head ==> (0, 1, 2))
      } yield ()
    }


    "Refs + tx meta data" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {
        _ <- A.i(0).B.i(1).C.i(2).Tx(D.i(3)).save.transact
        _ <- A.i(0).B.i(1).C.i(2).Tx(D.i(3)).query.get.map(_.head ==> (0, 1, 2, 3))
      } yield ()
    }


    "Ref + tx meta data composites" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {
        _ <- A.i(0).B.i(1).Tx(C.i(2) + D.i(3)).save.transact
        _ <- A.i(0).B.i(1).Tx(C.i(2) + D.i(3)).query.get.map(_.head ==> (0, 1, 2, 3))
      } yield ()
    }


    "Refs + tx meta data composites" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {
        _ <- A.i(0).B.i(1).C.i(2).Tx(D.i(3) + E.i(4)).save.transact
        _ <- A.i(0).B.i(1).C.i(2).Tx(D.i(3) + E.i(4)).query.get.map(_.head ==> (0, 1, 2, 3, 4))
      } yield ()
    }


    "Tx refs" - refs { implicit conn =>
      for {
        // Saving tx meta data (Ref.s) that references another namespace attribute (Ref1.int1)
        _ <- A.i(1).Tx(B.s("a").C.i(10)).save.transact

        // Tx meta data with ref attr
        _ <- A.i.Tx(B.s.C.i).query.get.map(_ ==> List(
          (1, "a", 10)
        ))

        // Tx meta data
        _ <- A.i.Tx(B.s).query.get.map(_ ==> List(
          (1, "a")
        ))

        // OBS: C.i is not asserted with tx entity, but with ref from tx entity!
        _ <- A.i.Tx(C.i).query.get.map(_ ==> Nil)

        // Saving multiple tx refs
        _ <- A.i(2).Tx(B.s("b").C.i(20).D.i(200)).save.transact

        // Getting multiple tx refs
        _ <- A.i.Tx(B.s.C.i.D.i).query.get.map(_ ==> List(
          (2, "b", 20, 200)
        ))
        _ <- A.i.a1.Tx(B.s.C.i).query.get.map(_ ==> List(
          (1, "a", 10), // First insert matches too
          (2, "b", 20)
        ))
        _ <- A.i.a1.Tx(B.s).query.get.map(_ ==> List(
          (1, "a"),
          (2, "b")
        ))
      } yield ()
    }

    "Tx backref" - refs { implicit conn =>
      for {
        _ <- A.i(1).Tx(C.i(7).D.i(8)._C.s("tx")).save.transact
        _ <- A.i.Tx(C.i.D.i._C.s).query.get.map(_ ==> List(
          (1, 7, 8, "tx")
        ))
      } yield ()
    }

    "Tx composite" - refs { implicit conn =>
      for {
        _ <- A.i(1).Tx(C.i(7).s("tx") + B.i(8)).save.transact
        _ <- A.i.Tx(C.i.s + B.i).query.get.map(_ ==> List(
          (1, (7, "tx"), 8)
        ))

        _ <- A.i(2).s("a").Tx(C.i(7).s("tx") + B.i(8)).save.transact
        _ <- A.i.s.Tx(C.i.s + B.i).query.get.map(_ ==> List(
          (2, "a", (7, "tx"), 8)
        ))
      } yield ()
    }


    "Composite + tx" - refs { implicit conn =>
      for {
        _ <- (A.i(1) + C.i(7).s("a")).Tx(E.i(8).s("tx")).save.transact
        _ <- (A.i + C.i.s).Tx(E.i.s).query.get.map(_ ==> List(
          (1, (7, "a"), 8, "tx")
        ))
      } yield ()
    }

    "Composite + tx composite" - refs { implicit conn =>
      for {
        _ <- (A.i(1) + C.i(7).s("a")).Tx(D.i(8).s("tx") + E.i(9)).save.transact
        _ <- (A.i + C.i.s).Tx(D.i.s + E.i).query.get.map(_ ==> List(
          (1, (7, "a"), (8, "tx"), 9)
        ))
      } yield ()
    }
  }
}
