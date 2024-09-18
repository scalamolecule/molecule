package molecule.coreTests.spi.aggr.ref

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrRef_distinct extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "ref" - refs { implicit conn =>
      for {
        _ <- A.i.B.i.insert(List(
          (1, 1),
          (2, 2),
          (2, 2),
          (2, 3),
        )).transact

        _ <- A.i.B.i.a1.query.get.map(_ ==> List(
          (1, 1),
          (2, 2), // 2 rows coalesced
          (2, 3),
        ))

        // Distinct values are returned in a Set
        _ <- A.i.a1.B.i(distinct).query.get.map(_ ==> List(
          (1, Set(1)),
          (2, Set(2, 3)),
        ))

        _ <- A.B.i(distinct).query.get.map(_.head ==> Set(1, 2, 3))
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

        _ <- A.i.B.i.C.i.a1.query.get.map(_ ==> List(
          (1, 1, 1),
          (2, 2, 2), // 2 rows coalesced
          (2, 2, 3),
        ))

        // Distinct values are returned in a Set
        _ <- A.i.a1.B.i.C.i(distinct).query.get.map(_ ==> List(
          (1, 1, Set(1)),
          (2, 2, Set(2, 3)),
        ))

        _ <- A.B.C.i(distinct).query.get.map(_.head ==> Set(1, 2, 3))
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

        _ <- A.i.B.i.C.i.a1.query.get.map(_ ==> List(
          (1, 1, 1),
          (2, 2, 2), // 2 rows coalesced
          (2, 2, 3),
        ))

        // Distinct values are returned in a Set
        _ <- A.i.a1.B.i(distinct).C.i(distinct).query.get.map(_ ==> List(
          (1, Set(1), Set(1)),
          (2, Set(2), Set(2, 3)),
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

        _ <- A.i.B.i._A.C.i.a1.query.get.map(_ ==> List(
          (1, 1, 1),
          (2, 2, 2), // 2 rows coalesced
          (2, 2, 3),
        ))

        // Distinct values are returned in a Set
        _ <- A.i.a1.B.i(distinct)._A.C.i(distinct).query.get.map(_ ==> List(
          (1, Set(1), Set(1)),
          (2, Set(2), Set(2, 3)),
        ))
      } yield ()
    }
  }
}