package molecule.coreTests.test.crud.insert

import molecule.base.error.ModelError
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.api.ApiAsyncImplicits
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait InsertSemantics extends CoreTestSuite with ApiAsyncImplicits { self: SpiAsync  =>

  override lazy val tests = Tests {

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
