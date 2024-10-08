package molecule.coreTests.spi.crud.save

import molecule.base.error.ModelError
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait SaveSemantics extends CoreTestSuite with Api_async { spi: Spi_async =>


  override lazy val tests = Tests {

    "Attribute required in each namespace" - refs { implicit conn =>
      for {
        _ <- A.B.i(1).save.transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Please add at least 1 attribute to namespace A before relating to B"
          }

        _ <- A.Bb.i(1).save.transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Please add at least 1 attribute to namespace A before relating to Bb"
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
