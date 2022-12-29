package molecule.db.datomic.test.crud.save

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object SaveTxMetaData extends DatomicTestSuite {


  lazy val tests = Tests {

    "Basic" - refs { implicit conn =>
      for {
        _ <- Ns.i(1).Tx(R2.i(7)).save.transact
        _ <- Ns.i.Tx(R2.i).query.get.map(_ ==> List(
          (1, 7)
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

    "Optional attribute" - refs { implicit conn =>
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


    "Tx ref" - refs { implicit conn =>
      for {
        _ <- Ns.i(1).Tx(R2.i(7).R3.i(8)).save.transact
        _ <- Ns.i.Tx(R2.i.R3.i).query.get.map(_ ==> List(
          (1, 7, 8)
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
