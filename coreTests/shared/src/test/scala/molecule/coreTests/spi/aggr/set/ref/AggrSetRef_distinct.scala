package molecule.coreTests.spi.aggr.set.ref

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.dataModels.core.dsl.Types.Ns
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSetRef_distinct extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "ref" - refs { implicit conn =>
      for {
        _ <- A.i.B.ii.insert(List(
          (1, Set(1, 2)),
          (2, Set(2, 3)),
          (2, Set(3, 4)),
          (2, Set(3, 4)),
        )).transact

        // Matching values coalesced into one Set

        _ <- A.i.a1.B.ii.query.get.map(_ ==> List(
          (1, Set(1, 2)),
          (2, Set(2, 3, 4)), // 3 rows coalesced
        ))

        _ <- A.i.a1.B.ii(distinct).query.get.map(_ ==> List(
          (1, Set(Set(1, 2))),
          (2, Set(
            Set(2, 3),
            Set(3, 4) // 2 rows coalesced
          ))
        ))

        _ <- A.B.ii(distinct).query.get.map(_ ==> List(
          Set(
            Set(1, 2),
            Set(2, 3),
            Set(3, 4),
          )
        ))
      } yield ()
    }


    "2nd ref" - refs { implicit conn =>
      for {
        _ <- A.i.B.i.C.ii.insert(List(
          (1, 1, Set(1, 2)),
          (2, 2, Set(2, 3)),
          (2, 2, Set(3, 4)),
          (2, 2, Set(3, 4)),
        )).transact

        _ <- A.i.a1.B.i.C.ii(distinct).query.get.map(_ ==> List(
          (1, 1, Set(Set(1, 2))),
          (2, 2, Set(
            Set(2, 3),
            Set(3, 4) // 2 rows coalesced
          ))
        ))

        _ <- A.B.C.ii(distinct).query.get.map(_ ==> List(
          Set(
            Set(1, 2),
            Set(2, 3),
            Set(3, 4),
          )
        ))
      } yield ()
    }


    "multiple refs" - refs { implicit conn =>
      for {
        _ <- A.i.B.ii.C.ii.insert(List(
          (1, Set(1, 2), Set(1, 2)),
          (2, Set(2, 3), Set(2, 3)),
          (2, Set(3, 4), Set(3, 4)),
          (2, Set(3, 4), Set(3, 4)),
        )).transact

        _ <- A.i.a1.B.ii(distinct).C.ii(distinct).query.get.map(_ ==> List(
          (1,
            Set(Set(1, 2)),
            Set(Set(1, 2))),
          (2,
            Set(Set(2, 3), Set(3, 4)),
            Set(Set(2, 3), Set(3, 4))),
        ))
      } yield ()
    }


    "backref" - refs { implicit conn =>
      for {
        _ <- A.i.B.ii._A.C.ii.insert(List(
          (1, Set(1, 2), Set(1, 2)),
          (2, Set(2, 3), Set(2, 3)),
          (2, Set(3, 4), Set(3, 4)),
          (2, Set(3, 4), Set(3, 4)),
        )).transact

        _ <- A.i.a1.B.ii(distinct)._A.C.ii(distinct).query.get.map(_ ==> List(
          (1,
            Set(Set(1, 2)),
            Set(Set(1, 2))),
          (2,
            Set(Set(2, 3), Set(3, 4)),
            Set(Set(2, 3), Set(3, 4))),
        ))
      } yield ()
    }
  }
}