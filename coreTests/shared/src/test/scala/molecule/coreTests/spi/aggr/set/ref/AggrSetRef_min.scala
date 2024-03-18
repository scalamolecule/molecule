package molecule.coreTests.spi.aggr.set.ref

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSetRef_min extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "1st ref" - refs { implicit conn =>
      for {
        _ <- A.i.B.iSet.insert(List(
          (1, Set(1, 2)),
          (2, Set(2)),
          (2, Set(3, 4)),
          (2, Set(3, 4)),
        )).transact

        _ <- A.B.iSet(min).query.get.map(_ ==> List(1))

        _ <- A.i.B.iSet(min).a1.query.get.map(_ ==> List(
          (1, 1),
          (2, 2),
        ))
        _ <- A.i.B.iSet(min).d1.query.get.map(_ ==> List(
          (2, 2),
          (1, 1),
        ))


        _ <- A.B.iSet(min(1)).query.get.map(_ ==> List(Set(1)))
        _ <- A.B.iSet(min(2)).query.get.map(_ ==> List(Set(1, 2)))
        _ <- A.B.iSet(min(3)).query.get.map(_ ==> List(Set(1, 2, 3)))

        _ <- A.i.a1.B.iSet(min(1)).query.get.map(_ ==> List(
          (1, Set(1)),
          (2, Set(2)),
        ))
        _ <- A.i.a1.B.iSet(min(2)).query.get.map(_ ==> List(
          (1, Set(1, 2)),
          (2, Set(2, 3)),
        ))
        _ <- A.i.a1.B.iSet(min(3)).query.get.map(_ ==> List(
          (1, Set(1, 2)),
          (2, Set(2, 3, 4)),
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

        _ <- A.B.C.iSet(min).query.get.map(_ ==> List(1))

        _ <- A.i.B.i.C.iSet(min).a1.query.get.map(_ ==> List(
          (1, 1, 1),
          (2, 2, 2),
        ))
        _ <- A.i.B.i.C.iSet(min).d1.query.get.map(_ ==> List(
          (2, 2, 2),
          (1, 1, 1),
        ))


        _ <- A.B.C.iSet(min(1)).query.get.map(_ ==> List(Set(1)))
        _ <- A.B.C.iSet(min(2)).query.get.map(_ ==> List(Set(1, 2)))
        _ <- A.B.C.iSet(min(3)).query.get.map(_ ==> List(Set(1, 2, 3)))

        _ <- A.i.a1.B.i.C.iSet(min(1)).query.get.map(_ ==> List(
          (1, 1, Set(1)),
          (2, 2, Set(2)),
        ))
        _ <- A.i.a1.B.i.C.iSet(min(2)).query.get.map(_ ==> List(
          (1, 1, Set(1, 2)),
          (2, 2, Set(2, 3)),
        ))
        _ <- A.i.a1.B.i.C.iSet(min(3)).query.get.map(_ ==> List(
          (1, 1, Set(1, 2)),
          (2, 2, Set(2, 3, 4)),
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

        _ <- A.i.B.i._A.C.iSet(min).a1.query.get.map(_ ==> List(
          (1, 1, 1),
          (2, 2, 2),
        ))
        _ <- A.i.B.i._A.C.iSet(min).d1.query.get.map(_ ==> List(
          (2, 2, 2),
          (1, 1, 1),
        ))


        _ <- A.i.a1.B.i._A.C.iSet(min(1)).query.get.map(_ ==> List(
          (1, 1, Set(1)),
          (2, 2, Set(2)),
        ))
        _ <- A.i.a1.B.i._A.C.iSet(min(2)).query.get.map(_ ==> List(
          (1, 1, Set(1, 2)),
          (2, 2, Set(2, 3)),
        ))
        _ <- A.i.a1.B.i._A.C.iSet(min(3)).query.get.map(_ ==> List(
          (1, 1, Set(1, 2)),
          (2, 2, Set(2, 3, 4)),
        ))
      } yield ()
    }
  }
}