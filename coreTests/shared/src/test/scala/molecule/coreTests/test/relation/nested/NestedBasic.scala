package molecule.coreTests.test.relation.nested

import molecule.coreTests.api.ApiAsyncImplicits
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.async._
import molecule.coreTests.setup.CoreTestSuite
import molecule.core.spi.SpiAsync
import utest._


trait NestedBasic extends CoreTestSuite with ApiAsyncImplicits { self: SpiAsync  =>


  override lazy val tests = Tests {

    "Mandatory/optional rows" - refs { implicit conn =>
      for {
        _ <- A.i.Bb.*(B.i).insert(
          (1, List(10, 11)),
          (2, Nil),
        ).transact

        // Mandatory nested data
        _ <- A.i.Bb.*(B.i.a1).query.get.map(_ ==> List(
          (1, List(10, 11)),
        ))

        // Optional nested data
        _ <- A.i.Bb.*?(B.i.a1).query.get.map(_ ==> List(
          (1, List(10, 11)),
          (2, Nil),
        ))
      } yield ()
    }
  }
}
