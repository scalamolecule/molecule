package molecule.coreTests.spi.aggr.one

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrInRefs extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "ref" - refs { implicit conn =>
      for {
        _ <- A.i.B.i.insert(
          (1, 1),
          (1, 2),
          (1, 3),
          (2, 4),
          (2, 5),
          (2, 6),
        ).transact


        _ <- A.B.i(min).query.get.map(_ ==> List(1))
        _ <- A.B.i(max).query.get.map(_ ==> List(6))
        _ <- A.B.i(min).i(max).query.get.map(_ ==> List((1, 6)))

        _ <- A.i.a1.B.i(min).query.get.map(_ ==> List(
          (1, 1),
          (2, 4)
        ))

        _ <- A.i.a1.B.i(max).query.get.map(_ ==> List(
          (1, 3),
          (2, 6)
        ))

        _ <- A.i.a1.B.i(min).i(max).query.get.map(_ ==> List(
          (1, 1, 3),
          (2, 4, 6)
        ))
      } yield ()
    }


    "2nd ref" - refs { implicit conn =>
      for {
        _ <- A.i.B.i.C.i.insert(
          (1, 1, 1),
          (1, 1, 2),
          (1, 1, 3),
          (2, 2, 4),
          (2, 2, 5),
          (2, 2, 6),
        ).transact

        _ <- A.i.a1.B.i.C.i(min).query.get.map(_ ==> List(
          (1, 1, 1),
          (2, 2, 4),
        ))
        _ <- A.i.a1.B.i.C.i(max).query.get.map(_ ==> List(
          (1, 1, 3),
          (2, 2, 6),
        ))

        _ <- A.B.C.i(min).query.get.map(_ ==> List(1))
        _ <- A.B.C.i(max).query.get.map(_ ==> List(6))
        _ <- A.B.C.i(min).i(max).query.get.map(_ ==> List((1, 6)))

        _ <- A.i.a1.B.i.C.i(min).query.get.map(_ ==> List(
          (1, 1, 1),
          (2, 2, 4)
        ))

        _ <- A.i.a1.B.i.C.i(max).query.get.map(_ ==> List(
          (1, 1, 3),
          (2, 2, 6)
        ))

        _ <- A.i.a1.B.i.C.i(min).i(max).query.get.map(_ ==> List(
          (1, 1, 1, 3),
          (2, 2, 4, 6)
        ))
      } yield ()
    }


    "multiple refs" - refs { implicit conn =>
      for {
        _ <- A.i.B.i.C.i.insert(
          (10, 1, 1),
          (10, 1, 2),
          (10, 2, 3),
          (10, 2, 4),
          (20, 3, 5),
          (20, 3, 6),
          (20, 4, 7),
          (20, 4, 8),
        ).transact

        _ <- A.i.a1.B.i(min).C.i(min).query.get.map(_ ==> List(
          (10, 1, 1),
          (20, 3, 5),
        ))
        _ <- A.i.a1.B.i(min).C.i(max).query.get.map(_ ==> List(
          (10, 1, 4),
          (20, 3, 8),
        ))
        _ <- A.i.a1.B.i(max).C.i(min).query.get.map(_ ==> List(
          (10, 2, 1),
          (20, 4, 5),
        ))
        _ <- A.i.a1.B.i(max).C.i(max).query.get.map(_ ==> List(
          (10, 2, 4),
          (20, 4, 8),
        ))
      } yield ()
    }


    "backref" - refs { implicit conn =>
      for {
        _ <- A.i.B.i._A.C.i.insert(
          (10, 1, 1),
          (10, 1, 2),
          (10, 2, 3),
          (10, 2, 4),
          (20, 3, 5),
          (20, 3, 6),
          (20, 4, 7),
          (20, 4, 8),
        ).transact

        _ <- A.i.a1.B.i(min)._A.C.i(min).query.get.map(_ ==> List(
          (10, 1, 1),
          (20, 3, 5),
        ))
        _ <- A.i.a1.B.i(min)._A.C.i(max).query.get.map(_ ==> List(
          (10, 1, 4),
          (20, 3, 8),
        ))
        _ <- A.i.a1.B.i(max)._A.C.i(min).query.get.map(_ ==> List(
          (10, 2, 1),
          (20, 4, 5),
        ))
        _ <- A.i.a1.B.i(max)._A.C.i(max).query.get.map(_ ==> List(
          (10, 2, 4),
          (20, 4, 8),
        ))
      } yield ()
    }
  }
}