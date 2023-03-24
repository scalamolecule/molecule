package molecule.datomic.test.crud.insert

import molecule.base.util.exceptions.ExecutionError
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.datomic.setup.DatomicTestSuite
import molecule.datomic.async._
import utest._

object InsertTxMetaData extends DatomicTestSuite {


  lazy val tests = Tests {

    "Apply tx meta data to tacit attributes only" - refs { implicit conn =>
      for {
        _ <- Ns.i.Tx(R2.i).insert(1, 2).transact.expect { case ExecutionError(err, _) =>
          err ==>
            """Missing applied value for attribute:
              |AttrOneManInt("R2", "i", V, Seq(), None, Nil, None, None)""".stripMargin
        }

        _ <- Ns.i.Tx(R2.i_?).insert(1, Some(2)).transact.expect { case ExecutionError(err, _) =>
          err ==>
            """Missing applied value for attribute:
              |AttrOneOptInt("R2", "i", V, None, None, Nil, None, None)""".stripMargin
        }
      } yield ()
    }

    "Basic" - refs { implicit conn =>
      for {
        _ <- Ns.i.Tx(R2.i_(7)).insert(1).transact
        _ <- Ns.i.Tx(R2.i).query.get.map(_ ==> List(
          (1, 7)
        ))
      } yield ()
    }

    "Multiple attrs" - refs { implicit conn =>
      for {
        _ <- Ns.i.Tx(R2.i_(7).s_("tx").ii_(Set(8, 9))).insert(1).transact
        _ <- Ns.i.Tx(R2.i.s.ii).query.get.map(_ ==> List(
          (1, 7, "tx", Set(8, 9))
        ))
      } yield ()
    }

    "Tx ref" - refs { implicit conn =>
      for {
        _ <- Ns.i.Tx(R2.i_(7).R3.i_(8)).insert(1).transact
        _ <- Ns.i.Tx(R2.i.R3.i).query.get.map(_ ==> List(
          (1, 7, 8)
        ))
      } yield ()
    }

    "Tx backref" - refs { implicit conn =>
      for {
        _ <- Ns.i.Tx(R2.i_(7).R3.i_(8)._R2.s_("tx")).insert(1).transact
        _ <- Ns.i.Tx(R2.i.R3.i._R2.s).query.get.map(_ ==> List(
          (1, 7, 8, "tx")
        ))
      } yield ()
    }

    "Tx composite" - refs { implicit conn =>
      for {
        _ <- Ns.i.Tx(R2.i_(7).s_("tx") + R1.i_(8)).insert(1).transact
        _ <- Ns.i.Tx(R2.i.s + R1.i).query.get.map(_ ==> List(
          (1, (7, "tx"), 8)
        ))
      } yield ()
    }

    "Composite + tx" - refs { implicit conn =>
      for {
        _ <- (Ns.i + R2.i.s).Tx(R4.i_(7).s_("tx")).insert(
          (1, (2, "a"))
        ).transact

        _ <- (Ns.i + R2.i.s).Tx(R4.i.s).query.get.map(_ ==> List(
          (1, (2, "a"), 7, "tx")
        ))
      } yield ()
    }

    "Composite + tx composite" - refs { implicit conn =>
      for {
        _ <- (Ns.i + R2.i.s).Tx(R3.i_(7).s_("tx") + R4.i_(8)).insert(
          (1, (2, "a"))
        ).transact

        _ <- (Ns.i + R2.i.s).Tx(R3.i.s + R4.i).query.get.map(_ ==> List(
          (1, (2, "a"), (7, "tx"), 8)
        ))
      } yield ()
    }


    "Nested" - refs { implicit conn =>
      for {
        _ <- Ns.i.Rs1.*(R1.i.s).Tx(R3.s_("tx")).insert(
          (1, List((2, "a")))
        ).transact

        _ <- Ns.i.Rs1.*(R1.i.s).Tx(R3.s).query.get.map(_ ==> List(
          (1, List((2, "a")), "tx")
        ))
        _ <- Ns.i.Rs1.*?(R1.i.s).Tx(R3.s).query.get.map(_ ==> List(
          (1, List((2, "a")), "tx")
        ))
      } yield ()
    }


    "Nested ref + tx ref" - refs { implicit conn =>
      for {
        _ <- Ns.i.Rs1.*(R1.i.R2.s).Tx(R3.s_("tx").R4.i_(7)).insert(
          (1, List((2, "a")))
        ).transact

        _ <- Ns.i.Rs1.*(R1.i.R2.s).Tx(R3.s.R4.i).query.get.map(_ ==> List(
          (1, List((2, "a")), "tx", 7)
        ))
        _ <- Ns.i.Rs1.*?(R1.i.R2.s).Tx(R3.s.R4.i).query.get.map(_ ==> List(
          (1, List((2, "a")), "tx", 7)
        ))
      } yield ()
    }


    "Nested + tx composite" - refs { implicit conn =>
      for {
        _ <- Ns.i.Rs1.*(R1.i.s).Tx(R3.s_("tx").i_(7) + R4.i_(8)).insert(
          (1, List((2, "a")))
        ).transact

        _ <- Ns.i.Rs1.*(R1.i.s).Tx(R3.s.i + R4.i).query.get.map(_ ==> List(
          (1, List((2, "a")), ("tx", 7), 8)
        ))
        _ <- Ns.i.Rs1.*?(R1.i.s).Tx(R3.s.i + R4.i).query.get.map(_ ==> List(
          (1, List((2, "a")), ("tx", 7), 8)
        ))
      } yield ()
    }


    "Nested ref + tx composite" - refs { implicit conn =>
      for {
        _ <- Ns.i.Rs1.*(R1.i.R2.s).Tx(R3.s_("tx").i_(7) + R4.i_(8)).insert(
          (1, List((2, "a")))
        ).transact

        _ <- Ns.i.Rs1.*(R1.i.R2.s).Tx(R3.s.i + R4.i).query.get.map(_ ==> List(
          (1, List((2, "a")), ("tx", 7), 8)
        ))
        _ <- Ns.i.Rs1.*?(R1.i.R2.s).Tx(R3.s.i + R4.i).query.get.map(_ ==> List(
          (1, List((2, "a")), ("tx", 7), 8)
        ))
      } yield ()
    }


    "Nested composite" - refs { implicit conn =>
      for {
        _ <- Ns.i.Rs1.*(R1.i.s + R2.i).Tx(R3.s_("tx")).insert(
          (1, List(((2, "a"), 3)))
        ).transact

        _ <- Ns.i.Rs1.*(R1.i.s + R2.i).Tx(R3.s).query.get.map(_ ==> List(
          (1, List(((2, "a"), 3)), "tx")
        ))
        _ <- Ns.i.Rs1.*?(R1.i.s + R2.i).Tx(R3.s).query.get.map(_ ==> List(
          (1, List(((2, "a"), 3)), "tx")
        ))
      } yield ()
    }


    "Nested composite + tx ref" - refs { implicit conn =>
      for {
        _ <- Ns.i.Rs1.*(R1.i.s + R2.i).Tx(R3.s_("tx").R4.i_(7)).insert(
          (1, List(((2, "a"), 3)))
        ).transact

        _ <- Ns.i.Rs1.*(R1.i.s + R2.i).Tx(R3.s.R4.i).query.get.map(_ ==> List(
          (1, List(((2, "a"), 3)), "tx", 7)
        ))
        _ <- Ns.i.Rs1.*?(R1.i.s + R2.i).Tx(R3.s.R4.i).query.get.map(_ ==> List(
          (1, List(((2, "a"), 3)), "tx", 7)
        ))
      } yield ()
    }


    "Nested composite + tx composite" - refs { implicit conn =>
      for {
        _ <- Ns.i.Rs1.*(R1.i.s + R2.i).Tx(R3.s_("tx").i_(7) + R4.i_(8)).insert(
          (1, List(((2, "a"), 3)))
        ).transact

        _ <- Ns.i.Rs1.*(R1.i.s + R2.i).Tx(R3.s.i + R4.i).query.get.map(_ ==> List(
          (1, List(((2, "a"), 3)), ("tx", 7), 8)
        ))
        _ <- Ns.i.Rs1.*?(R1.i.s + R2.i).Tx(R3.s.i + R4.i).query.get.map(_ ==> List(
          (1, List(((2, "a"), 3)), ("tx", 7), 8)
        ))
      } yield ()
    }


    "Nested 2 levels composite + tx ref" - refs { implicit conn =>
      for {
        _ <- Ns.i.Rs1.*(R1.i.Rs2.*(R2.i.s + R4.i)).Tx(R5.s_("tx").R6.i_(7)).insert(
          (1, List((2, List(((3, "a"), 4)))))
        ).transact

        _ <- Ns.i.Rs1.*(R1.i.Rs2.*(R2.i.s + R4.i)).Tx(R5.s.R6.i).query.get.map(_ ==> List(
          (1, List((2, List(((3, "a"), 4)))), "tx", 7)
        ))
        _ <- Ns.i.Rs1.*?(R1.i.Rs2.*?(R2.i.s + R4.i)).Tx(R5.s.R6.i).query.get.map(_ ==> List(
          (1, List((2, List(((3, "a"), 4)))), "tx", 7)
        ))
      } yield ()
    }


    "Nested 2 levels composite + tx composite" - refs { implicit conn =>
      for {
        _ <- Ns.i.Rs1.*(R1.i.Rs2.*(R2.i.s + R4.i)).Tx(R5.s_("tx").i_(7) + R6.i_(8)).insert(
          (1, List((2, List(((3, "a"), 4)))))
        ).transact

        _ <- Ns.i.Rs1.*(R1.i.Rs2.*(R2.i.s + R4.i)).Tx(R5.s.i + R6.i).query.get.map(_ ==> List(
          (1, List((2, List(((3, "a"), 4)))), ("tx", 7), 8)
        ))
        _ <- Ns.i.Rs1.*?(R1.i.Rs2.*?(R2.i.s + R4.i)).Tx(R5.s.i + R6.i).query.get.map(_ ==> List(
          (1, List((2, List(((3, "a"), 4)))), ("tx", 7), 8)
        ))
      } yield ()
    }


    "Composites in branch: 1 + 1" - refs { implicit conn =>
      for {
        _ <- Ns.i.Rs1.*(R1.i + R2.i.Rs3.*(R3.i)).Tx(R5.s_("tx")).insert(
          (1, List((2, (3, List(5)))))
        ).transact

        _ <- Ns.i.Rs1.*(R1.i + R2.i.Rs3.*(R3.i)).Tx(R5.s).query.get.map(_ ==> List(
          (1, List((2, (3, List(5)))), "tx")
        ))
        _ <- Ns.i.Rs1.*?(R1.i + R2.i.Rs3.*?(R3.i)).Tx(R5.s).query.get.map(_ ==> List(
          (1, List((2, (3, List(5)))), "tx")
        ))
      } yield ()
    }

    "Composites in branch: 2 + 1" - refs { implicit conn =>
      for {
        _ <- Ns.i.Rs1.*(R1.i.s + R2.i.Rs3.*(R3.i)).Tx(R5.s_("tx")).insert(
          (1, List(((2, "a"), (3, List(5)))))
        ).transact

        _ <- Ns.i.Rs1.*(R1.i.s + R2.i.Rs3.*(R3.i)).Tx(R5.s).query.get.map(_ ==> List(
          (1, List(((2, "a"), (3, List(5)))), "tx")
        ))
        _ <- Ns.i.Rs1.*?(R1.i.s + R2.i.Rs3.*?(R3.i)).Tx(R5.s).query.get.map(_ ==> List(
          (1, List(((2, "a"), (3, List(5)))), "tx")
        ))
      } yield ()
    }

    "Composites in branch: 2 + 2" - refs { implicit conn =>
      for {
        _ <- Ns.i.Rs1.*(R1.i.s + R2.i.s.Rs3.*(R3.i)).Tx(R5.s_("tx")).insert(
          (1, List(((2, "a"), (3, "b", List(5)))))
        ).transact

        _ <- Ns.i.Rs1.*(R1.i.s + R2.i.s.Rs3.*(R3.i)).Tx(R5.s).query.get.map(_ ==> List(
          (1, List(((2, "a"), (3, "b", List(5)))), "tx")
        ))
        _ <- Ns.i.Rs1.*?(R1.i.s + R2.i.s.Rs3.*?(R3.i)).Tx(R5.s).query.get.map(_ ==> List(
          (1, List(((2, "a"), (3, "b", List(5)))), "tx")
        ))
      } yield ()
    }

    "Composites in branch: 2 + 1 + 2" - refs { implicit conn =>
      for {
        _ <- Ns.i.Rs1.*(R1.i.s + R4.i + R2.s.Rs3.*(R3.i)).Tx(R5.s_("tx")).insert(
          (1, List(((2, "a"), 3, ("b", List(5)))))
        ).transact

        _ <- Ns.i.Rs1.*(R1.i.s + R4.i + R2.s.Rs3.*(R3.i)).Tx(R5.s).query.get.map(_ ==> List(
          (1, List(((2, "a"), 3, ("b", List(5)))), "tx")
        ))
        _ <- Ns.i.Rs1.*?(R1.i.s + R4.i + R2.s.Rs3.*?(R3.i)).Tx(R5.s).query.get.map(_ ==> List(
          (1, List(((2, "a"), 3, ("b", List(5)))), "tx")
        ))
      } yield ()
    }


    "Initial composite + composite in branch" - refs { implicit conn =>
      for {
        _ <- (Ns.i + R1.i.Rs2.*(R2.i.s + R3.i.Rs4.*(R4.i))).Tx(R5.s_("tx")).insert(
          (1, (2, Seq(((3, "a"), (4, List(5))))))
        ).transact

        _ <- (Ns.i + R1.i.Rs2.*(R2.i.s + R3.i.Rs4.*(R4.i))).Tx(R5.s).query.get.map(_ ==> List(
          (1, (2, Seq(((3, "a"), (4, List(5))))), "tx")
        ))
        _ <- (Ns.i + R1.i.Rs2.*?(R2.i.s + R3.i.Rs4.*?(R4.i))).Tx(R5.s).query.get.map(_ ==> List(
          (1, (2, Seq(((3, "a"), (4, List(5))))), "tx")
        ))
      } yield ()
    }


    "Composites everywhere" - refs { implicit conn =>
      for {
        _ <- (R1.i.s + Ns.i.s.Rs1
          .*(R1.i.s + R2.i.Rs3
            .*(R3.i.s + R4.i))).Tx(R5.s_("tx").i_(7) + R6.i_(8)).insert(
          ((1, "a"), (2, "b", List(((3, "c"), (4, List(((5, "d"), 6)))))))
        ).transact

        _ <- (R1.i.s + Ns.i.s.Rs1
          .*(R1.i.s + R2.i.Rs3
            .*(R3.i.s + R4.i))).Tx(R5.s.i + R6.i).query.get.map(_ ==> List(
          ((1, "a"), (2, "b", List(((3, "c"), (4, List(((5, "d"), 6)))))), ("tx", 7), 8)
        ))

        _ <- (R1.i.s + Ns.i.s.Rs1
          .*?(R1.i.s + R2.i.Rs3
            .*?(R3.i.s + R4.i))).Tx(R5.s.i + R6.i).query.get.map(_ ==> List(
          ((1, "a"), (2, "b", List(((3, "c"), (4, List(((5, "d"), 6)))))), ("tx", 7), 8)
        ))
      } yield ()
    }
  }
}
