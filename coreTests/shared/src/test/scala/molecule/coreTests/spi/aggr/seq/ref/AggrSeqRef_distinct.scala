package molecule.coreTests.spi.aggr.seq.ref

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSeqRef_distinct extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "1st ref" - refs { implicit conn =>
      for {
        _ <- A.i.B.iSet.insert(List(
          (1, Set(1, 2)),
          (2, Set(2)),
          (2, Set(3, 4)),
          (2, Set(3, 4)),
        )).transact

        // Matching values coalesced into one Set

        _ <- A.i.a1.B.iSet.query.get.map(_ ==> List(
          (1, Set(1, 2)),
          (2, Set(2, 3, 4)), // 3 rows coalesced
        ))

        _ <- A.i.a1.B.iSet(distinct).query.get.map(_ ==> List(
          (1, Set(Set(1, 2))),
          (2, Set(
            Set(2),
            Set(3, 4) // 2 rows coalesced
          ))
        ))

        _ <- A.B.iSet(distinct).query.get.map(_ ==> List(
          Set(
            Set(1, 2),
            Set(2),
            Set(3, 4),
          )
        ))
      } yield ()
    }


    "2nd ref" - refs { implicit conn =>
      for {
        _ <- A.i.B.i.C.iSet.insert(List(
          (1, 1, Set(1, 2)),
          (2, 2, Set(2)),
          (2, 2, Set(3, 4)),
          (2, 2, Set(3, 4)),
        )).transact

        _ <- A.i.a1.B.i.C.iSet(distinct).query.get.map(_ ==> List(
          (1, 1, Set(Set(1, 2))),
          (2, 2, Set(
            Set(2),
            Set(3, 4) // 2 rows coalesced
          ))
        ))

        _ <- A.B.C.iSet(distinct).query.get.map(_ ==> List(
          Set(
            Set(1, 2),
            Set(2),
            Set(3, 4),
          )
        ))
      } yield ()
    }


    "backref" - refs { implicit conn =>
      for {
        _ <- A.i.B.i._A.C.iSet.insert(List(
          (1, 1, Set(1, 2)),
          (2, 2, Set(2)),
          (2, 2, Set(3, 4)),
          (2, 2, Set(3, 4)),
        )).transact

        _ <- A.i.a1.B.i._A.C.iSet(distinct).query.get.map(_ ==> List(
          (1, 1, Set(Set(1, 2))),
          (2, 2, Set(Set(2), Set(3, 4))),
        ))
      } yield ()
    }
  }
}