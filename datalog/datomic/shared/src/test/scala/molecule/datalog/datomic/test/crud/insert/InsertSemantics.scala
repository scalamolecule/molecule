package molecule.datalog.datomic.test.crud.insert

import molecule.base.error.ModelError
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.datalog.datomic.async._
import molecule.datalog.datomic.setup.DatomicTestSuite
import utest._

object InsertSemantics extends DatomicTestSuite {

  override lazy val tests = Tests {

    "Insert not allowed to apply values to attributes" - refs { implicit conn =>
      for {
        _ <- (A.i(1) + C.i(2)).insert(1, 2).transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Can't insert attributes with an applied value. Found:\n" +
            """AttrOneManInt("A", "i", Eq, Seq(1), None, None, Nil, Nil, None, None)"""
        }

        _ <- (A.i + C.i(2)).insert(1, 2).transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Can't insert attributes with an applied value. Found:\n" +
            """AttrOneManInt("C", "i", Eq, Seq(2), None, None, Nil, Nil, None, None)"""
        }
      } yield ()
    }


    "Duplicate attributes not allowed, flat" - {

      "Same ns" - refs { implicit conn =>
        for {
          _ <- A.i.i.insert(1, 2).transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't transact duplicate attribute A.i"
          }
        } yield ()
      }

      "After backref" - refs { implicit conn =>
        for {
          _ <- A.i.B.i._A.i.insert(1, 2, 3).transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't transact duplicate attribute A.i"
          }

          _ <- A.i.B.i.C.i._B.i.insert(1, 2, 3, 4).transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't transact duplicate attribute B.i"
          }

          _ <- A.i.B.i.C.i._B._A.i.insert(1, 2, 3, 4).transact
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
          _ <- (C.i + A.i.i).insert(0, (1, 2)).transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't transact duplicate attribute A.i"
          }

          // After backref

          _ <- (C.i + A.i.B.i._A.i).insert(0, (1, 2, 3)).transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't transact duplicate attribute A.i"
          }

          _ <- (C.i + A.i.B.i.C.i._B.i).insert(0, (1, 2, 3, 4)).transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't transact duplicate attribute B.i"
          }
          //
          _ <- (C.i + A.i.B.i.C.i._B._A.i).insert(0, (1, 2, 3, 4)).transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't transact duplicate attribute A.i"
          }
        } yield ()
      }

      "Across sub tuples, top level" - refs { implicit conn =>
        for {
          _ <- (A.i + A.i).insert(1, 2).transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't transact duplicate attribute A.i"
          }
        } yield ()
      }

      "Across sub tuples, ref" - refs { implicit conn =>
        for {
          // On different levels is ok
          _ <- (A.i.B.i + B.i).insert((1, 2), 3).transact

          // Can't reference same ns twice
          _ <- (A.i.B.i + A.s.B.s).insert((1, 2), ("a", "b")).transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't transact duplicate attribute A.b"
          }
        } yield ()
      }

      "Across sub tuples, after backref" - refs { implicit conn =>
        for {
          _ <- (A.s + A.i.B.i._A.s).insert("a", (1, 2, "b")).transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't transact duplicate attribute A.s"
          }

          _ <- (A.s + A.i.B.i.C.i._B._A.s).insert("a", (1, 2, 3, "b")).transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't transact duplicate attribute A.s"
          }
        } yield ()
      }
    }

    "Duplicate attributes not allowed, nested" - {

      "Same ns" - refs { implicit conn =>
        for {
          _ <- A.i.Bb.*(B.i.i).insert(1, List((2, 3))).transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't transact duplicate attribute B.i"
          }

          _ <- A.i.Bb.*?(B.i.i).insert(1, List((2, 3))).transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't transact duplicate attribute B.i"
          }
        } yield ()
      }

      "Backref in nested" - refs { implicit conn =>
        for {
          _ <- A.i.Bb.*(B.i.C.i._B.i).insert(1, List((2, 3, 4))).transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't transact duplicate attribute B.i"
          }

          _ <- A.i.Bb.*?(B.i.C.i._B.i).insert(1, List((2, 3, 4))).transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't transact duplicate attribute B.i"
          }
        } yield ()
      }
    }


    "Backref in nested" - refs { implicit conn =>
      for {
        _ <- A.i.Bb.*(B.i._A.i).insert(1, List((2, 3))).transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Can't use backref namespace _A from here"
        }

        _ <- A.i.Bb.*?(B.i._A.i).insert(1, List((2, 3))).transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Can't use backref namespace _A from here"
        }

        // ok
        _ <- A.i.Bb.*(B.i.C.i._B.s).insert(1, List((2, 3, "a"))).transact
        _ <- A.i.Bb.*?(B.i.C.i._B.s).insert(1, List((2, 3, "a"))).transact
      } yield ()
    }
  }
}
