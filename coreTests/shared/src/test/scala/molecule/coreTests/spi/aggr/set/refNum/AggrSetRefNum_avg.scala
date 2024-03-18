package molecule.coreTests.spi.aggr.set.refNum

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.dataModels.core.dsl.Types.Ns
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSetRefNum_avg extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  // Average of unique values (Set semantics)

  override lazy val tests = Tests {
    val all  = 1 + 2 + 2 + 3 + 4 + 3 + 4
    val all2 = 2 + 3 + 4 + 3 + 4

    "1st ref" - refs { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- A.i.B.iSet.insert(List(
          (1, Set(1, 2)),
          (2, Set(2)),
          (2, Set(3, 4)),
          (2, Set(3, 4)),
        )).transact

        _ <- A.B.iSet(avg).query.get.map(_.head ==~ all.toDouble / 7.0)

        _ <- A.i.B.iSet(avg).a1.query.get.map(_ ==> List(
          (1, (1 + 2).toDouble / 2.0),
          (2, all2.toDouble / 5.0),
        ))
        _ <- A.i.B.iSet(avg).d1.query.get.map(_ ==> List(
          (2, all2.toDouble / 5.0),
          (1, (1 + 2).toDouble / 2.0),
        ))
      } yield ()
    }


    "2nd ref" - refs { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- A.i.B.i.C.iSet.insert(List(
          (1, 1, Set(1, 2)),
          (2, 2, Set(2)),
          (2, 2, Set(3, 4)),
          (2, 2, Set(3, 4)),
        )).transact

        _ <- A.B.C.iSet(avg).query.get.map(_.head ==~ all.toDouble / 7.0)

        _ <- A.i.B.i.C.iSet(avg).a1.query.get.map(_ ==> List(
          (1, 1, (1 + 2).toDouble / 2.0),
          (2, 2, all2.toDouble / 5.0),
        ))
        _ <- A.i.B.i.C.iSet(avg).d1.query.get.map(_ ==> List(
          (2, 2, all2.toDouble / 5.0),
          (1, 1, (1 + 2).toDouble / 2.0),
        ))
      } yield ()
    }


    "backref" - refs { implicit conn =>
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- A.i.B.i._A.C.iSet.insert(List(
          (1, 1, Set(1, 2)),
          (2, 2, Set(2)),
          (2, 2, Set(3, 4)),
          (2, 2, Set(3, 4)),
        )).transact

        _ <- A.i.B.i._A.C.iSet(avg).a1.query.get.map(_ ==> List(
          (1, 1, (1 + 2).toDouble / 2.0),
          (2, 2, all2.toDouble / 5.0),
        ))
        _ <- A.i.B.i._A.C.iSet(avg).d1.query.get.map(_ ==> List(
          (2, 2, all2.toDouble / 5.0),
          (1, 1, (1 + 2).toDouble / 2.0),
        ))
      } yield ()
    }
  }
}