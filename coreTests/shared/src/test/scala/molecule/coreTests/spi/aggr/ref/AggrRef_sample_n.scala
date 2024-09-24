package molecule.coreTests.spi.aggr.ref

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrRef_sample_n extends CoreTestSuite with Api_async { spi: Spi_async =>

  override lazy val tests = Tests {

    "ref" - refs { implicit conn =>
      val all = Set(1, 2, 3, 4)
      for {
        _ <- A.i.B.i.insert(List(
          (1, 1),
          (1, 2),
          (1, 3),
        )).transact

        _ <- A.B.i(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "2nd ref" - refs { implicit conn =>
      val all = Set(1, 2, 3, 4)
      for {
        _ <- A.i.B.i.C.i.insert(List(
          (1, 1, 1),
          (1, 1, 2),
          (1, 1, 3),
        )).transact

        _ <- A.B.C.i(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "multiple refs" - refs { implicit conn =>
      val all = Set(1, 2, 3, 4)
      for {
        _ <- A.i.B.i.C.i.insert(List(
          (1, 1, 1),
          (1, 2, 2),
          (1, 3, 3),
        )).transact

        _ <- A.B.i(sample(2)).C.i(sample(2)).query.get.map{res =>
          all.intersect(res.head._1).nonEmpty ==> true
          all.intersect(res.head._2).nonEmpty ==> true
        }
      } yield ()
    }


    "backref" - refs { implicit conn =>
      val all = Set(1, 2, 3, 4)
      for {
        _ <- A.i.B.i._A.C.i.insert(List(
          (1, 1, 1),
          (1, 2, 2),
          (1, 3, 3),
        )).transact

        _ <- A.B.i(sample(2))._A.C.i(sample(2)).query.get.map { res =>
          all.intersect(res.head._1).nonEmpty ==> true
          all.intersect(res.head._2).nonEmpty ==> true
        }
      } yield ()
    }
  }
}