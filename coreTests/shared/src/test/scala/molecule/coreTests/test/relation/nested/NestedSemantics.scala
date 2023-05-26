package molecule.coreTests.test.relation.nested

import molecule.base.error.ModelError
import molecule.coreTests.api.ApiAsyncImplicits
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.async._
import molecule.coreTests.setup.CoreTestSuite
import molecule.core.spi.SpiAsync
import utest._


trait NestedSemantics extends CoreTestSuite with ApiAsyncImplicits { self: SpiAsync  =>


  override lazy val tests = Tests {

    "Nested namespace must match" - refs { implicit conn =>
      for {
        _ <- A.i.Bb.*(E.i).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "`Bb` can only nest to `B`. Found: `E`"
        }

        _ <- A.i.Bb.*?(E.i).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "`Bb` can only nest to `B`. Found: `E`"
        }
      } yield ()
    }


    "Mixing *?/* not allowed" - refs { implicit conn =>
      for {
        _ <- A.i.Bb.*(B.i.Cc.*?(C.i)).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Can't mix mandatory/optional nested data structures."
        }

        _ <- A.i.Bb.*?(B.i.Cc.*(C.i)).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Can't mix mandatory/optional nested data structures."
        }
      } yield ()
    }
  }
}
