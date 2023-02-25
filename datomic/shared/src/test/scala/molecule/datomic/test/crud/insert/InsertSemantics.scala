package molecule.datomic.test.crud.insert

import molecule.base.util.exceptions.MoleculeError
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.datomic.setup.DatomicTestSuite
import molecule.datomic.async._
import utest._

object InsertSemantics extends DatomicTestSuite {


  lazy val tests = Tests {

    "Can't mix save/insert" - refs { implicit conn =>
      for {
        _ <- (Ns.i(1) + R2.i(2)).insert(1, 2).transact
          .map(_ ==> "Unexpected success").recover { case MoleculeError(err, _) =>
          err ==> "Can't insert attributes with an applied value. Found:\n" +
            """AttrOneManInt("Ns", "i", Appl, Seq(1), None, None, None)"""
        }

        _ <- (Ns.i + R2.i(2)).insert(1, 2).transact
          .map(_ ==> "Unexpected success").recover { case MoleculeError(err, _) =>
          err ==> "Can't insert attributes with an applied value. Found:\n" +
            """AttrOneManInt("R2", "i", Appl, Seq(2), None, None, None)"""
        }
      } yield ()
    }


    "Duplicate attributes not allowed, flat" - {

      "Same ns" - refs { implicit conn =>
        for {
          _ <- Ns.i.i.insert(1, 2).transact
            .map(_ ==> "Unexpected success").recover { case MoleculeError(err, _) =>
            err ==> "Can't transact duplicate attribute `Ns.i`."
          }
        } yield ()
      }

      "After backref" - refs { implicit conn =>
        for {
          _ <- Ns.i.R1.i._Ns.i.insert(1, 2, 3).transact
            .map(_ ==> "Unexpected success").recover { case MoleculeError(err, _) =>
            err ==> "Can't transact duplicate attribute `Ns.i`."
          }

          _ <- Ns.i.R1.i.R2.i._R1.i.insert(1, 2, 3, 4).transact
            .map(_ ==> "Unexpected success").recover { case MoleculeError(err, _) =>
            err ==> "Can't transact duplicate attribute `R1.i`."
          }

          _ <- Ns.i.R1.i.R2.i._R1._Ns.i.insert(1, 2, 3, 4).transact
            .map(_ ==> "Unexpected success").recover { case MoleculeError(err, _) =>
            err ==> "Can't transact duplicate attribute `Ns.i`."
          }
        } yield ()
      }
    }


    "Duplicate attributes not allowed, composite" - {

      "Within sub tuple" - refs { implicit conn =>
        for {
          // Each sub tuple has same semantics as flat molecule

          // Same ns
          _ <- (R2.i + Ns.i.i).insert(0, (1, 2)).transact
            .map(_ ==> "Unexpected success").recover { case MoleculeError(err, _) =>
            err ==> "Can't transact duplicate attribute `Ns.i`."
          }

          // After backref

          _ <- (R2.i + Ns.i.R1.i._Ns.i).insert(0, (1, 2, 3)).transact
            .map(_ ==> "Unexpected success").recover { case MoleculeError(err, _) =>
            err ==> "Can't transact duplicate attribute `Ns.i`."
          }

          _ <- (R2.i + Ns.i.R1.i.R2.i._R1.i).insert(0, (1, 2, 3, 4)).transact
            .map(_ ==> "Unexpected success").recover { case MoleculeError(err, _) =>
            err ==> "Can't transact duplicate attribute `R1.i`."
          }
          //
          _ <- (R2.i + Ns.i.R1.i.R2.i._R1._Ns.i).insert(0, (1, 2, 3, 4)).transact
            .map(_ ==> "Unexpected success").recover { case MoleculeError(err, _) =>
            err ==> "Can't transact duplicate attribute `Ns.i`."
          }
        } yield ()
      }

      "Across sub tuples, top level" - refs { implicit conn =>
        for {
          _ <- (Ns.i + Ns.i).insert(1, 2).transact
            .map(_ ==> "Unexpected success").recover { case MoleculeError(err, _) =>
            err ==> "Can't transact duplicate attribute `Ns.i`."
          }
        } yield ()
      }

      "Across sub tuples, ref" - refs { implicit conn =>
        for {
          // On different levels is ok
          _ <- (Ns.i.R1.i + R1.i).insert((1, 2), 3).transact

          // Can't reference same ns twice
          _ <- (Ns.i.R1.i + Ns.s.R1.s).insert((1, 2), ("a", "b")).transact
            .map(_ ==> "Unexpected success").recover { case MoleculeError(err, _) =>
            err ==> "Can't transact duplicate attribute `Ns.r1`."
          }
        } yield ()
      }

      "Across sub tuples, after backref" - refs { implicit conn =>
        for {
          _ <- (Ns.s + Ns.i.R1.i._Ns.s).insert("a", (1, 2, "b")).transact
            .map(_ ==> "Unexpected success").recover { case MoleculeError(err, _) =>
            err ==> "Can't transact duplicate attribute `Ns.s`."
          }

          _ <- (Ns.s + Ns.i.R1.i.R2.i._R1._Ns.s).insert("a", (1, 2, 3, "b")).transact
            .map(_ ==> "Unexpected success").recover { case MoleculeError(err, _) =>
            err ==> "Can't transact duplicate attribute `Ns.s`."
          }
        } yield ()
      }
    }

    "Duplicate attributes not allowed, nested" - {

      "Same ns" - refs { implicit conn =>
        for {
          _ <- Ns.i.Rs1.*(R1.i.i).insert(1, List((2, 3))).transact
            .map(_ ==> "Unexpected success").recover { case MoleculeError(err, _) =>
            err ==> "Can't transact duplicate attribute `R1.i`."
          }

          _ <- Ns.i.Rs1.*?(R1.i.i).insert(1, List((2, 3))).transact
            .map(_ ==> "Unexpected success").recover { case MoleculeError(err, _) =>
            err ==> "Can't transact duplicate attribute `R1.i`."
          }
        } yield ()
      }

      "Backref in nested" - refs { implicit conn =>
        for {
          _ <- Ns.i.Rs1.*(R1.i.R2.i._R1.i).insert(1, List((2, 3, 4))).transact
            .map(_ ==> "Unexpected success").recover { case MoleculeError(err, _) =>
            err ==> "Can't transact duplicate attribute `R1.i`."
          }

          _ <- Ns.i.Rs1.*?(R1.i.R2.i._R1.i).insert(1, List((2, 3, 4))).transact
            .map(_ ==> "Unexpected success").recover { case MoleculeError(err, _) =>
            err ==> "Can't transact duplicate attribute `R1.i`."
          }
        } yield ()
      }
    }


    "Backref in nested" - refs { implicit conn =>
      for {
        _ <- Ns.i.Rs1.*(R1.i._Ns.i).insert(1, List((2, 3))).transact
          .map(_ ==> "Unexpected success").recover { case MoleculeError(err, _) =>
          err ==> "Can't use backref from here."
        }

        _ <- Ns.i.Rs1.*?(R1.i._Ns.i).insert(1, List((2, 3))).transact
          .map(_ ==> "Unexpected success").recover { case MoleculeError(err, _) =>
          err ==> "Can't use backref from here."
        }

        // ok
        _ <- Ns.i.Rs1.*(R1.i.R2.i._R1.s).insert(1, List((2, 3, "a"))).transact
        _ <- Ns.i.Rs1.*?(R1.i.R2.i._R1.s).insert(1, List((2, 3, "a"))).transact
      } yield ()
    }
  }
}
