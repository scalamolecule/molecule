package molecule.db.datomic.test.crud.insert

import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import molecule.db.datomic.test.crud.save.SaveTxMetaData.refs
import utest._

object InsertTxMetaData extends DatomicTestSuite {


  lazy val tests = Tests {

    "Basic" - refs { implicit conn =>
      Ns.i.Tx(R2.i).insert(1, 2).transact
      Ns.i.Tx(R2.i).query.get ==> List(
        (1, 2)
      )
    }

    "Multiple attrs" - refs { implicit conn =>
      Ns.i.Tx(R2.i.s.ii).insert(1, 2, "a", Set(3, 4)).transact
      Ns.i.Tx(R2.i.s.ii).query.get ==> List(
        (1, 2, "a", Set(3, 4))
      )
    }

    "Optional attribute" - refs { implicit conn =>
      Ns.i.Tx(R2.i.s_?).insert(1, 2, Some("a")).transact
      Ns.i.Tx(R2.i.s_?).query.get ==> List(
        (1, 2, Some("a"))
      )

      Ns.i.Tx(R2.i.s_?).insert(2, 3, None).transact
      Ns.i(2).Tx(R2.i.s_?).query.get ==> List(
        (2, 3, None)
      )
    }


    "Tx ref" - refs { implicit conn =>
      Ns.i.Tx(R2.i.R3.i).insert(1, 2, 3).transact
      Ns.i.Tx(R2.i.R3.i).query.get ==> List(
        (1, 2, 3)
      )
    }

    "Tx backref" - refs { implicit conn =>
      Ns.i.Tx(R2.i.R3.i._R2.s).insert(1, 2, 3, "a").transact
      Ns.i.Tx(R2.i.R3.i._R2.s).query.get ==> List(
        (1, 2, 3, "a")
      )
    }

    "Tx composite" - refs { implicit conn =>
      Ns.i.Tx(R2.i.s + R1.i).insert(1, (2, "a"), 3).transact
      Ns.i.Tx(R2.i.s + R1.i).query.get ==> List(
        (1, (2, "a"), 3)
      )
    }


    "Composite + tx" - refs { implicit conn =>
      (Ns.i + R2.i.s).Tx(R4.i.s).insert(1, (2, "a"), 3, "b").transact
      (Ns.i + R2.i.s).Tx(R4.i.s).query.get ==> List(
        (1, (2, "a"), 3, "b")
      )
    }

    "Composite + tx composite" - refs { implicit conn =>
      (Ns.i + R2.i.s).Tx(R3.i.s + R4.i).insert(1, (2, "a"), (3, "b"), 4).transact
      (Ns.i + R2.i.s).Tx(R3.i.s + R4.i).query.get ==> List(
        (1, (2, "a"), (3, "b"), 4)
      )
    }
  }
}
