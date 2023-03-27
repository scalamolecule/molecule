package molecule.datomic.test.crud.save

import molecule.base.error.ModelError
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.datomic.setup.DatomicTestSuite
import molecule.datomic.async._
import utest._

object SaveSemantics extends DatomicTestSuite {


  override lazy val tests = Tests {

    "Can't mix save/insert" - refs { implicit conn =>
      for {
        _ <- (Ns.i + R2.i).save.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Missing applied value for attribute:\n" +
            """AttrOneManInt("Ns", "i", V, Seq(), None, Nil, None, None)"""
        }

        _ <- (Ns.i(1) + R2.i).save.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Missing applied value for attribute:\n" +
            """AttrOneManInt("R2", "i", V, Seq(), None, Nil, None, None)"""
        }
      } yield ()
    }


    "Duplicate attributes not allowed, flat" - {

      "Same ns" - refs { implicit conn =>
        for {
          _ <- Ns.i(1).i(2).save.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't transact duplicate attribute `Ns.i`."
          }
        } yield ()
      }

      "After backref" - refs { implicit conn =>
        for {
          _ <- Ns.i(1).R1.i(2)._Ns.i(3).save.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't transact duplicate attribute `Ns.i`."
          }

          _ <- Ns.i(1).R1.i(2).R2.i(3)._R1.i(4).save.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't transact duplicate attribute `R1.i`."
          }

          _ <- Ns.i(1).R1.i(2).R2.i(3)._R1._Ns.i(4).save.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
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
          _ <- (R2.i(1) + Ns.i(2).i(3)).save.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't transact duplicate attribute `Ns.i`."
          }

          // After backref
          _ <- (R2.i(1) + Ns.i(2).R1.i(3)._Ns.i(4)).save.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't transact duplicate attribute `Ns.i`."
          }

          _ <- (R2.i(1) + Ns.i(2).R1.i(3).R2.i(4)._R1.i(5)).save.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't transact duplicate attribute `R1.i`."
          }

          _ <- (R2.i(1) + Ns.i(2).R1.i(3).R2.i(4)._R1._Ns.i(5)).save.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't transact duplicate attribute `Ns.i`."
          }
        } yield ()
      }

      "Across sub tuples, top level" - refs { implicit conn =>
        for {
          _ <- (Ns.i(1) + Ns.i(2)).save.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't transact duplicate attribute `Ns.i`."
          }
        } yield ()
      }

      "Across sub tuples, ref" - refs { implicit conn =>
        for {
          // On different levels is ok
          _ <- (Ns.i(1).R1.i(2) + R1.i(3)).save.transact

          // Can't reference same ns twice
          _ <- (Ns.i(1).R1.i(2) + Ns.s("a").R1.s("b")).save.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't transact duplicate attribute `Ns.r1`."
          }
        } yield ()
      }

      "Across sub tuples, after backref" - refs { implicit conn =>
        for {
          _ <- (Ns.s("a") + Ns.i(1).R1.i(2)._Ns.s("b")).save.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't transact duplicate attribute `Ns.s`."
          }

          _ <- (Ns.s("a") + Ns.i(1).R1.i(2).R2.i(3)._R1._Ns.s("b")).save.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't transact duplicate attribute `Ns.s`."
          }
        } yield ()
      }
    }


    "Nested data can only be inserted, not saved" - refs { implicit conn =>
      for {
        _ <- Ns.i(0).Rs1.*(R1.i(1)).save.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Nested data structure not allowed in save molecule. " +
            "Please use insert instead."
        }

        _ <- Ns.i(0).Rs1.*?(R1.i(1)).save.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Optional nested data structure not allowed in save molecule. " +
            "Please use insert instead."
        }

        // ok
        _ <- Ns.i.Rs1.*(R1.i).insert(0, List(1)).transact
        _ <- Ns.i.Rs1.*?(R1.i).insert(0, List(1)).transact
      } yield ()
    }
  }
}
