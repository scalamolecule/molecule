package molecule.coreTests.spi.relation.nested

import molecule.base.error.ModelError
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._


trait NestedSemantics extends CoreTestSuite with ApiAsync { spi: SpiAsync =>


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
            err ==> "Can't mix mandatory/optional nested queries."
          }

        _ <- A.i.Bb.*?(B.i.Cc.*(C.i)).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't mix mandatory/optional nested queries."
          }
      } yield ()
    }
  }
}
