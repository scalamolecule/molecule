package molecule.db.datomic.test.crud.save

import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object SaveTxMetaData extends DatomicTestSuite {


  lazy val tests = Tests {

    "Basic" - refs { implicit conn =>
      Ns.i(1).Tx(R2.i(2)).save.transact
      Ns.i.Tx(R2.i).query.get ==> List(
        (1, 2)
      )
    }

    "Multiple attrs" - refs { implicit conn =>
      Ns.i(1).Tx(R2.i(2).s("a").ii(Set(3, 4))).save.transact
      Ns.i.Tx(R2.i.s.ii).query.get ==> List(
        (1, 2, "a", Set(3,4))
      )
    }

    "Optional attribute" - refs { implicit conn =>
      Ns.i(1).Tx(R2.i(2).s_?(Some("a"))).save.transact
      Ns.i.Tx(R2.i.s_?).query.get ==> List(
        (1, 2, Some("a"))
      )

      Ns.i(2).Tx(R2.i(3).s_?(Option.empty[String])).save.transact
      Ns.i(2).Tx(R2.i.s_?).query.get ==> List(
        (2, 3, None)
      )
    }


    "Tx ref" - refs { implicit conn =>
      Ns.i(1).Tx(R2.i(2).R3.i(3)).save.transact
      Ns.i.Tx(R2.i.R3.i).query.get ==> List(
        (1, 2, 3)
      )
    }

    "Tx backref" - refs { implicit conn =>
      Ns.i(1).Tx(R2.i(2).R3.i(3)._R2.s("a")).save.transact
      Ns.i.Tx(R2.i.R3.i._R2.s).query.get ==> List(
        (1, 2, 3, "a")
      )
    }

    "Tx composite" - refs { implicit conn =>
      Ns.i(1).Tx(R2.i(2).s("a") + R1.i(3)).save.transact
      Ns.i.Tx(R2.i.s + R1.i).query.get ==> List(
        (1, (2, "a"), 3)
      )
    }


    "Composite + tx" - refs { implicit conn =>
      (Ns.i(1) + R2.i(2).s("a")).Tx(R4.i(3).s("b")).save.transact
      (Ns.i + R2.i.s).Tx(R4.i.s).query.get ==> List(
        (1, (2, "a"), 3, "b")
      )
    }

    "Composite + tx composite" - refs { implicit conn =>
      (Ns.i(1) + R2.i(2).s("a")).Tx(R3.i(3).s("b") + R4.i(4)).save.transact
      (Ns.i + R2.i.s).Tx(R3.i.s + R4.i).query.get ==> List(
        (1, (2, "a"), (3, "b"), 4)
      )
    }
  }
}
