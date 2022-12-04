package molecule.db.datomic.test.crud.save

import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object SaveTxMetaData extends DatomicTestSuite {


  lazy val tests = Tests {

    "Basic" - refs { implicit conn =>
      Ns.i(1).Tx(R2.i(7)).save.transact
      Ns.i.Tx(R2.i).query.get ==> List(
        (1, 7)
      )
    }

    "Multiple attrs" - refs { implicit conn =>
      Ns.i(1).Tx(R2.i(7).s("tx").ii(Set(8, 9))).save.transact
      Ns.i.Tx(R2.i.s.ii).query.get ==> List(
        (1, 7, "tx", Set(8, 9))
      )
    }

    "Optional attribute" - refs { implicit conn =>
      Ns.i(1).Tx(R2.i(7).s_?(Some("tx"))).save.transact
      Ns.i.Tx(R2.i.s_?).query.get ==> List(
        (1, 7, Some("tx"))
      )

      Ns.i(2).Tx(R2.i(7).s_?(Option.empty[String])).save.transact
      Ns.i(2).Tx(R2.i.s_?).query.get ==> List(
        (2, 7, None)
      )
    }


    "Tx ref" - refs { implicit conn =>
      Ns.i(1).Tx(R2.i(7).R3.i(8)).save.transact
      Ns.i.Tx(R2.i.R3.i).query.get ==> List(
        (1, 7, 8)
      )
    }

    "Tx backref" - refs { implicit conn =>
      Ns.i(1).Tx(R2.i(7).R3.i(8)._R2.s("tx")).save.transact
      Ns.i.Tx(R2.i.R3.i._R2.s).query.get ==> List(
        (1, 7, 8, "tx")
      )
    }

    "Tx composite" - refs { implicit conn =>
      Ns.i(1).Tx(R2.i(7).s("tx") + R1.i(8)).save.transact
      Ns.i.Tx(R2.i.s + R1.i).query.get ==> List(
        (1, (7, "tx"), 8)
      )

      Ns.i(2).s("a").Tx(R2.i(7).s("tx") + R1.i(8)).save.transact
      Ns.i.s.Tx(R2.i.s + R1.i).query.get ==> List(
        (2, "a", (7, "tx"), 8)
      )
    }


    "Composite + tx" - refs { implicit conn =>
      (Ns.i(1) + R2.i(7).s("a")).Tx(R4.i(8).s("tx")).save.transact
      (Ns.i + R2.i.s).Tx(R4.i.s).query.get ==> List(
        (1, (7, "a"), 8, "tx")
      )
    }

    "Composite + tx composite" - refs { implicit conn =>
      (Ns.i(1) + R2.i(7).s("a")).Tx(R3.i(8).s("tx") + R4.i(9)).save.transact
      (Ns.i + R2.i.s).Tx(R3.i.s + R4.i).query.get ==> List(
        (1, (7, "a"), (8, "tx"), 9)
      )
    }
  }
}
