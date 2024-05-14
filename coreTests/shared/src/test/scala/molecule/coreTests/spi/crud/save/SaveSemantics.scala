package molecule.coreTests.spi.crud.save

import molecule.base.error.ModelError
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait SaveSemantics extends CoreTestSuite with ApiAsync { spi: SpiAsync =>


  override lazy val tests = Tests {

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
