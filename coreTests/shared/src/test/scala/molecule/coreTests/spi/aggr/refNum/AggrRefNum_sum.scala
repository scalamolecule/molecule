package molecule.coreTests.spi.aggr.refNum

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrRefNum_sum extends CoreTestSuite with Api_async { spi: Spi_async =>

  // Sum of distinct values (Set semantics)

  override lazy val tests = Tests {

    "ref" - refs { implicit conn =>
      implicit val tolerant = tolerantIntEquality(toleranceInt)
      for {
        _ <- A.i.B.i.insert(List(
          (1, 1),
          (1, 2),
          (2, 2),
          (2, 3),
          (2, 4),
        )).transact

        // Sum of all (non-coalesced) values
        _ <- A.B.i(sum).query.get.map(_.head ==> 1 + 2 + 2 + 3 + 4)
        _ <- A.i.a1.B.i(sum).query.get.map(_ ==> List(
          (1, 1 + 2),
          (2, 2 + 3 + 4),
        ))
      } yield ()
    }


    "2nd ref" - refs { implicit conn =>
      for {
        _ <- A.i.B.i.C.i.insert(List(
          (1, 1, 1),
          (2, 2, 2),
          (2, 2, 2),
          (2, 2, 3),
        )).transact

        _ <- A.B.C.i(sum).query.get.map(_ ==> List(1 + 2 + 2 + 3))
        _ <- A.i.a1.B.i.C.i(sum).query.get.map(_ ==> List(
          (1, 1, 1),
          (2, 2, 2 + 2 + 3)
        ))
      } yield ()
    }


    "multiple refs" - refs { implicit conn =>
      for {
        _ <- A.i.B.i.C.i.insert(List(
          (1, 1, 1),
          (2, 2, 2),
          (2, 2, 2),
          (2, 2, 3),
        )).transact

        _ <- A.i.a1.B.i(sum).C.i(sum).query.get.map(_ ==> List(
          (1, 1, 1),
          (2, 2 + 2 + 2, 2 + 2 + 3),
        ))
      } yield ()
    }


    "backref" - refs { implicit conn =>
      for {
        _ <- A.i.B.i._A.C.i.insert(List(
          (1, 1, 1),
          (2, 2, 2),
          (2, 2, 2),
          (2, 2, 3),
        )).transact

        _ <- A.i.a1.B.i(sum)._A.C.i(sum).query.get.map(_ ==> List(
          (1, 1, 1),
          (2, 2 + 2 + 2, 2 + 2 + 3),
        ))
      } yield ()
    }
  }
}