package molecule.datalog.datomic.test.crud.save

import molecule.base.error.ModelError
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.datalog.datomic.async._
import molecule.datalog.datomic.setup.DatomicTestSuite
import utest._

object SaveSemantics extends DatomicTestSuite {


  override lazy val tests = Tests {

    "Save requires applied values" - refs { implicit conn =>
      for {
        _ <- (A.i + C.i).save.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Missing applied value for attribute A.i"
        }

        _ <- (A.i(1) + C.i).save.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Missing applied value for attribute C.i"
        }
      } yield ()
    }


    "Duplicate attributes not allowed, flat" - {

      "Same ns" - refs { implicit conn =>
        for {
          _ <- A.i(1).i(2).save.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't transact duplicate attribute A.i"
          }
        } yield ()
      }

      "After backref" - refs { implicit conn =>
        for {
          _ <- A.i(1).B.i(2)._A.i(3).save.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't transact duplicate attribute A.i"
          }

          _ <- A.i(1).B.i(2).C.i(3)._B.i(4).save.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't transact duplicate attribute B.i"
          }

          _ <- A.i(1).B.i(2).C.i(3)._B._A.i(4).save.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't transact duplicate attribute A.i"
          }
        } yield ()
      }
    }


    "Duplicate attributes not allowed, composite" - {

      "Within sub tuple" - refs { implicit conn =>
        for {
          // Each sub tuple has same semantics as flat molecule

          // Same ns
          _ <- (C.i(1) + A.i(2).i(3)).save.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't transact duplicate attribute A.i"
          }

          // After backref
          _ <- (C.i(1) + A.i(2).B.i(3)._A.i(4)).save.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't transact duplicate attribute A.i"
          }

          _ <- (C.i(1) + A.i(2).B.i(3).C.i(4)._B.i(5)).save.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't transact duplicate attribute B.i"
          }

          _ <- (C.i(1) + A.i(2).B.i(3).C.i(4)._B._A.i(5)).save.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't transact duplicate attribute A.i"
          }
        } yield ()
      }

      "Across sub tuples, top level" - refs { implicit conn =>
        for {
          _ <- (A.i(1) + A.i(2)).save.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't transact duplicate attribute A.i"
          }
        } yield ()
      }

      "Across sub tuples, ref" - refs { implicit conn =>
        for {
          // On different levels is ok
          _ <- (A.i(1).B.i(2) + B.i(3)).save.transact

          // Can't reference same ns twice
          _ <- (A.i(1).B.i(2) + A.s("a").B.s("b")).save.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't transact duplicate attribute A.b"
          }
        } yield ()
      }

      "Across sub tuples, after backref" - refs { implicit conn =>
        for {
          _ <- (A.s("a") + A.i(1).B.i(2)._A.s("b")).save.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't transact duplicate attribute A.s"
          }

          _ <- (A.s("a") + A.i(1).B.i(2).C.i(3)._B._A.s("b")).save.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't transact duplicate attribute A.s"
          }
        } yield ()
      }
    }


    "Nested data can only be inserted, not saved" - refs { implicit conn =>
      for {
        _ <- A.i(0).Bb.*(B.i(1)).save.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Nested data structure not allowed in save molecule. " +
            "Please use insert instead."
        }

        _ <- A.i(0).Bb.*?(B.i(1)).save.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Optional nested data structure not allowed in save molecule. " +
            "Please use insert instead."
        }

        // ok
        _ <- A.i.Bb.*(B.i).insert(0, List(1)).transact
        _ <- A.i.Bb.*?(B.i).insert(0, List(1)).transact
      } yield ()
    }
  }
}
