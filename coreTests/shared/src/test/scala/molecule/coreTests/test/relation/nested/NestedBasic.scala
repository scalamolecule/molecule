package molecule.coreTests.test.relation.nested

import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.api.ApiAsyncImplicits
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._


trait NestedBasic extends CoreTestSuite with ApiAsyncImplicits { self: SpiAsync  =>

  override lazy val tests = Tests {

    "Mandatory/optional rows" - refs { implicit conn =>
      for {
        _ <- A.s.Bb.*(B.i).insert(
          ("a", List(1, 2)),
          ("b", Nil),
        ).transact

        // Mandatory nested data
        _ <- A.s.Bb.*(B.i.a1).query.get.map(_ ==> List(
          ("a", List(1, 2)),
        ))

        // Optional nested data
        _ <- A.s.a1.Bb.*?(B.i.a1).query.get.map(_ ==> List(
          ("a", List(1, 2)),
          ("b", Nil),
        ))
      } yield ()
    }
  }
}
