package molecule.db.datomic.test.crud.insert

import datomic.Peer
import molecule.base.util.exceptions.MoleculeException
import molecule.boilerplate.api.{Composite_02, Molecule_03}
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.db.datomic._
import molecule.db.datomic.facade.Conn_Peer
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object InsertTxMetaData extends DatomicTestSuite {


  lazy val tests = Tests {

    "Apply tx meta data to tacit attributes only" - refs { implicit conn =>
      intercept[MoleculeException](
        Ns.i.Tx(R2.i).insert(1, 2).transact
      ).message ==>
        """Please apply tx meta data to tacit attributes. Found:
          |AttrOneManInt(R2,i,V,List(),None,None,None)""".stripMargin

      intercept[MoleculeException](
        Ns.i.Tx(R2.i_?).insert(1, Some(2)).transact
      ).message ==>
        """Please apply tx meta data to tacit attributes. Found:
          |AttrOneOptInt(R2,i,V,None,None,None,None)""".stripMargin
    }

    "Basic" - refs { implicit conn =>
      Ns.i.Tx(R2.i_(7)).insert(1).transact
      Ns.i.Tx(R2.i).query.get ==> List(
        (1, 7)
      )
    }

    "Multiple attrs" - refs { implicit conn =>
      Ns.i.Tx(R2.i_(7).s_("tx").ii_(Set(8, 9))).insert(1).transact
      Ns.i.Tx(R2.i.s.ii).query.get ==> List(
        (1, 7, "tx", Set(8, 9))
      )
    }

    "Tx ref" - refs { implicit conn =>
      Ns.i.Tx(R2.i_(7).R3.i_(8)).insert(1).transact
      Ns.i.Tx(R2.i.R3.i).query.get ==> List(
        (1, 7, 8)
      )
    }

    "Tx backref" - refs { implicit conn =>
      Ns.i.Tx(R2.i_(7).R3.i_(8)._R2.s_("tx")).insert(1).transact
      Ns.i.Tx(R2.i.R3.i._R2.s).query.get ==> List(
        (1, 7, 8, "tx")
      )
    }

    "Tx composite" - refs { implicit conn =>
      Ns.i.Tx(R2.i_(7).s_("tx") + R1.i_(8)).insert(1).transact
      Ns.i.Tx(R2.i.s + R1.i).query.get ==> List(
        (1, (7, "tx"), 8)
      )
    }

    "Composite + tx" - refs { implicit conn =>
      (Ns.i + R2.i.s).Tx(R4.i_(7).s_("tx")).insert(
        (1, (2, "a"))
      ).transact

      (Ns.i + R2.i.s).Tx(R4.i.s).query.get ==> List(
        (1, (2, "a"), 7, "tx")
      )
    }

    "Composite + tx composite" - refs { implicit conn =>
      (Ns.i + R2.i.s).Tx(R3.i_(7).s_("tx") + R4.i_(8)).insert(
        (1, (2, "a"))
      ).transact

      (Ns.i + R2.i.s).Tx(R3.i.s + R4.i).query.get ==> List(
        (1, (2, "a"), (7, "tx"), 8)
      )
    }


    "Nested" - refs { implicit conn =>
      Ns.i.Rs1.*(R1.i.s).Tx(R3.s_("tx")).insert(
        (1, List((2, "a")))
      ).transact

      Ns.i.Rs1.*(R1.i.s).Tx(R3.s).query.get ==> List(
        (1, List((2, "a")), "tx")
      )
      Ns.i.Rs1.*?(R1.i.s).Tx(R3.s).query.get ==> List(
        (1, List((2, "a")), "tx")
      )
    }


    "Nested ref + tx ref" - refs { implicit conn =>
      Ns.i.Rs1.*(R1.i.R2.s).Tx(R3.s_("tx").R4.i_(7)).insert(
        (1, List((2, "a")))
      ).transact

      Ns.i.Rs1.*(R1.i.R2.s).Tx(R3.s.R4.i).query.get ==> List(
        (1, List((2, "a")), "tx", 7)
      )
      Ns.i.Rs1.*?(R1.i.R2.s).Tx(R3.s.R4.i).query.get ==> List(
        (1, List((2, "a")), "tx", 7)
      )
    }


    "Nested + tx composite" - refs { implicit conn =>
      Ns.i.Rs1.*(R1.i.s).Tx(R3.s_("tx").i_(7) + R4.i_(8)).insert(
        (1, List((2, "a")))
      ).transact

      Ns.i.Rs1.*(R1.i.s).Tx(R3.s.i + R4.i).query.get ==> List(
        (1, List((2, "a")), ("tx", 7), 8)
      )
      Ns.i.Rs1.*?(R1.i.s).Tx(R3.s.i + R4.i).query.get ==> List(
        (1, List((2, "a")), ("tx", 7), 8)
      )
    }


    "Nested ref + tx composite" - refs { implicit conn =>
      Ns.i.Rs1.*(R1.i.R2.s).Tx(R3.s_("tx").i_(7) + R4.i_(8)).insert(
        (1, List((2, "a")))
      ).transact

      Ns.i.Rs1.*(R1.i.R2.s).Tx(R3.s.i + R4.i).query.get ==> List(
        (1, List((2, "a")), ("tx", 7), 8)
      )
      Ns.i.Rs1.*?(R1.i.R2.s).Tx(R3.s.i + R4.i).query.get ==> List(
        (1, List((2, "a")), ("tx", 7), 8)
      )
    }


    "Nested composite" - refs { implicit conn =>
      Ns.i.Rs1.*(R1.i.s + R2.i).Tx(R3.s_("tx")).insert(
        (1, List(((2, "a"), 3)))
      ).transact

      Ns.i.Rs1.*(R1.i.s + R2.i).Tx(R3.s).query.get ==> List(
        (1, List(((2, "a"), 3)), "tx")
      )
      Ns.i.Rs1.*?(R1.i.s + R2.i).Tx(R3.s).query.get ==> List(
        (1, List(((2, "a"), 3)), "tx")
      )
    }


    "Nested composite + tx ref" - refs { implicit conn =>
      Ns.i.Rs1.*(R1.i.s + R2.i).Tx(R3.s_("tx").R4.i_(7)).insert(
        (1, List(((2, "a"), 3)))
      ).transact

      Ns.i.Rs1.*(R1.i.s + R2.i).Tx(R3.s.R4.i).query.get ==> List(
        (1, List(((2, "a"), 3)), "tx", 7)
      )
      Ns.i.Rs1.*?(R1.i.s + R2.i).Tx(R3.s.R4.i).query.get ==> List(
        (1, List(((2, "a"), 3)), "tx", 7)
      )
    }


    "Nested composite + tx composite" - refs { implicit conn =>
      Ns.i.Rs1.*(R1.i.s + R2.i).Tx(R3.s_("tx").i_(7) + R4.i_(8)).insert(
        (1, List(((2, "a"), 3)))
      ).transact

      Ns.i.Rs1.*(R1.i.s + R2.i).Tx(R3.s.i + R4.i).query.get ==> List(
        (1, List(((2, "a"), 3)), ("tx", 7), 8)
      )
      Ns.i.Rs1.*?(R1.i.s + R2.i).Tx(R3.s.i + R4.i).query.get ==> List(
        (1, List(((2, "a"), 3)), ("tx", 7), 8)
      )
    }


    "Nested 2 levels composite + tx ref" - refs { implicit conn =>
      Ns.i.Rs1.*(R1.i.Rs2.*(R2.i.s + R4.i)).Tx(R5.s_("tx").R6.i_(7)).insert(
        (1, List((2, List(((3, "a"), 4)))))
      ).transact

      Ns.i.Rs1.*(R1.i.Rs2.*(R2.i.s + R4.i)).Tx(R5.s.R6.i).query.get ==> List(
        (1, List((2, List(((3, "a"), 4)))), "tx", 7)
      )
      Ns.i.Rs1.*?(R1.i.Rs2.*?(R2.i.s + R4.i)).Tx(R5.s.R6.i).query.get ==> List(
        (1, List((2, List(((3, "a"), 4)))), "tx", 7)
      )
    }


    "Nested 2 levels composite + tx composite" - refs { implicit conn =>
      Ns.i.Rs1.*(R1.i.Rs2.*(R2.i.s + R4.i)).Tx(R5.s_("tx").i_(7) + R6.i_(8)).insert(
        (1, List((2, List(((3, "a"), 4)))))
      ).transact

      Ns.i.Rs1.*(R1.i.Rs2.*(R2.i.s + R4.i)).Tx(R5.s.i + R6.i).query.get ==> List(
        (1, List((2, List(((3, "a"), 4)))), ("tx", 7), 8)
      )
      Ns.i.Rs1.*?(R1.i.Rs2.*?(R2.i.s + R4.i)).Tx(R5.s.i + R6.i).query.get ==> List(
        (1, List((2, List(((3, "a"), 4)))), ("tx", 7), 8)
      )
    }


    "Composites in branch: 1 + 1" - refs { implicit conn =>
      Ns.i.Rs1.*(R1.i + R2.i.Rs3.*(R3.i)).Tx(R5.s_("tx")).insert(
        (1, List((2, (3, List(5)))))
      ).transact

      Ns.i.Rs1.*(R1.i + R2.i.Rs3.*(R3.i)).Tx(R5.s).query.get ==> List(
        (1, List((2, (3, List(5)))), "tx")
      )
      Ns.i.Rs1.*?(R1.i + R2.i.Rs3.*?(R3.i)).Tx(R5.s).query.get ==> List(
        (1, List((2, (3, List(5)))), "tx")
      )
    }

    "Composites in branch: 2 + 1" - refs { implicit conn =>
      Ns.i.Rs1.*(R1.i.s + R2.i.Rs3.*(R3.i)).Tx(R5.s_("tx")).insert(
        (1, List(((2, "a"), (3, List(5)))))
      ).transact

      Ns.i.Rs1.*(R1.i.s + R2.i.Rs3.*(R3.i)).Tx(R5.s).query.get ==> List(
        (1, List(((2, "a"), (3, List(5)))), "tx")
      )
      Ns.i.Rs1.*?(R1.i.s + R2.i.Rs3.*?(R3.i)).Tx(R5.s).query.get ==> List(
        (1, List(((2, "a"), (3, List(5)))), "tx")
      )
    }

    "Composites in branch: 2 + 2" - refs { implicit conn =>
      Ns.i.Rs1.*(R1.i.s + R2.i.s.Rs3.*(R3.i)).Tx(R5.s_("tx")).insert(
        (1, List(((2, "a"), (3, "b", List(5)))))
      ).transact

      Ns.i.Rs1.*(R1.i.s + R2.i.s.Rs3.*(R3.i)).Tx(R5.s).query.get ==> List(
        (1, List(((2, "a"), (3, "b", List(5)))), "tx")
      )
      Ns.i.Rs1.*?(R1.i.s + R2.i.s.Rs3.*?(R3.i)).Tx(R5.s).query.get ==> List(
        (1, List(((2, "a"), (3, "b", List(5)))), "tx")
      )
    }

    "Composites in branch: 2 + 1 + 2" - refs { implicit conn =>
      Ns.i.Rs1.*(R1.i.s + R4.i + R2.s.Rs3.*(R3.i)).Tx(R5.s_("tx")).insert(
        (1, List(((2, "a"), 3, ("b", List(5)))))
      ).transact

      Ns.i.Rs1.*(R1.i.s + R4.i + R2.s.Rs3.*(R3.i)).Tx(R5.s).query.get ==> List(
        (1, List(((2, "a"), 3, ("b", List(5)))), "tx")
      )
      Ns.i.Rs1.*?(R1.i.s + R4.i + R2.s.Rs3.*?(R3.i)).Tx(R5.s).query.get ==> List(
        (1, List(((2, "a"), 3, ("b", List(5)))), "tx")
      )
    }


    "Initial composite + composite in branch" - refs { implicit conn =>
      (Ns.i + R1.i.Rs2.*(R2.i.s + R3.i.Rs4.*(R4.i))).Tx(R5.s_("tx")).insert(
        (1, (2, Seq(((3, "a"), (4, List(5))))))
      ).transact

      (Ns.i + R1.i.Rs2.*(R2.i.s + R3.i.Rs4.*(R4.i))).Tx(R5.s).query.get ==> List(
        (1, (2, Seq(((3, "a"), (4, List(5))))), "tx")
      )
      (Ns.i + R1.i.Rs2.*?(R2.i.s + R3.i.Rs4.*?(R4.i))).Tx(R5.s).query.get ==> List(
        (1, (2, Seq(((3, "a"), (4, List(5))))), "tx")
      )
    }


    "Composites everywhere" - refs { implicit conn =>
      (R1.i.s + Ns.i.s.Rs1
        .*(R1.i.s + R2.i.Rs3
          .*(R3.i.s + R4.i))).Tx(R5.s_("tx").i_(7) + R6.i_(8)).insert(
        ((1, "a"), (2, "b", List(((3, "c"), (4, List(((5, "d"), 6)))))))
      ).transact

      (R1.i.s + Ns.i.s.Rs1
        .*(R1.i.s + R2.i.Rs3
          .*(R3.i.s + R4.i))).Tx(R5.s.i + R6.i).query.get ==> List(
        ((1, "a"), (2, "b", List(((3, "c"), (4, List(((5, "d"), 6)))))), ("tx", 7), 8)
      )

      (R1.i.s + Ns.i.s.Rs1
        .*?(R1.i.s + R2.i.Rs3
          .*?(R3.i.s + R4.i))).Tx(R5.s.i + R6.i).query.get ==> List(
        ((1, "a"), (2, "b", List(((3, "c"), (4, List(((5, "d"), 6)))))), ("tx", 7), 8)
      )
    }
  }
}
