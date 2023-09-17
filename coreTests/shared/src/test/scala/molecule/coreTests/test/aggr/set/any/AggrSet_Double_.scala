// GENERATED CODE ********************************
package molecule.coreTests.test.aggr.set.any

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSet_Double_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.doubles.insert(List(
          (1, Set(double1, double2)),
          (2, Set(double2, double3)),
          (2, Set(double3, double4)),
          (2, Set(double3, double4)),
        )).transact

        // Non-aggregated card-many Set of attribute values coalesce
        _ <- Ns.i.a1.doubles.query.get.map(_ ==> List(
          (1, Set(double1, double2)),
          (2, Set(double2, double3, double4)), // 3 rows coalesced
        ))

        // Use `distinct` keyword to retrieve unique Sets of Sets
        _ <- Ns.i.a1.doubles(distinct).query.get.map(_ ==> List(
          (1, Set(Set(double1, double2))),
          (2, Set(
            Set(double2, double3),
            Set(double3, double4) // 2 rows coalesced
          ))
        ))

        _ <- Ns.doubles(distinct).query.get.map(_ ==> List(
          Set(
            Set(double1, double2),
            Set(double2, double3),
            Set(double3, double4),
          )
        ))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.i.doubles.insert(List(
          (1, Set(double1, double2)),
          (2, Set(double2, double3)),
          (2, Set(double3, double4)),
          (2, Set(double3, double4)),
        )).transact

        _ <- Ns.doubles(min).query.get.map(_ ==> List(Set(double1)))
        _ <- Ns.doubles(min(1)).query.get.map(_ ==> List(Set(double1)))
        _ <- Ns.doubles(min(2)).query.get.map(_ ==> List(Set(double1, double2)))

        _ <- Ns.i.a1.doubles(min).query.get.map(_ ==> List(
          (1, Set(double1)),
          (2, Set(double2)),
        ))
        // Same as
        _ <- Ns.i.a1.doubles(min(1)).query.get.map(_ ==> List(
          (1, Set(double1)),
          (2, Set(double2)),
        ))

        _ <- Ns.i.a1.doubles(min(2)).query.get.map(_ ==> List(
          (1, Set(double1, double2)),
          (2, Set(double2, double3)),
        ))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.i.doubles.insert(List(
          (1, Set(double1, double2)),
          (2, Set(double2, double3)),
          (2, Set(double3, double4)),
          (2, Set(double3, double4)),
        )).transact

        _ <- Ns.doubles(max).query.get.map(_ ==> List(Set(double4)))
        _ <- Ns.doubles(max(1)).query.get.map(_ ==> List(Set(double4)))
        _ <- Ns.doubles(max(2)).query.get.map(_ ==> List(Set(double3, double4)))

        _ <- Ns.i.a1.doubles(max).query.get.map(_ ==> List(
          (1, Set(double2)),
          (2, Set(double4)),
        ))
        // Same as
        _ <- Ns.i.a1.doubles(max(1)).query.get.map(_ ==> List(
          (1, Set(double2)),
          (2, Set(double4)),
        ))

        _ <- Ns.i.a1.doubles(max(2)).query.get.map(_ ==> List(
          (1, Set(double1, double2)),
          (2, Set(double3, double4)),
        ))
      } yield ()
    }


    "rand" - types { implicit conn =>
      for {
        _ <- Ns.i.doubles.insert(List(
          (1, Set(double1, double2)),
          (2, Set(double2, double3)),
          (2, Set(double3, double4)),
          (2, Set(double3, double4)),
        )).transact
        all = Set(double1, double2, double3, double4)
        _ <- Ns.doubles(rand).query.get.map(res => all.contains(res.head.head) ==> true)
        _ <- Ns.doubles(rand(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.doubles(rand(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.i.doubles.insert(List(
          (1, Set(double1, double2)),
          (2, Set(double2, double3)),
          (2, Set(double3, double4)),
          (2, Set(double3, double4)),
        )).transact
        all = Set(double1, double2, double3, double4)
        _ <- Ns.doubles(sample).query.get.map(res => all.contains(res.head.head) ==> true)
        _ <- Ns.doubles(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.doubles(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "count countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.doubles.insert(List(
          (1, Set(double1, double2)),
          (2, Set(double2, double3)),
          (2, Set(double3, double4)),
          (2, Set(double3, double4)),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.doubles(count).query.get.map(_ ==> List(8))
        _ <- Ns.doubles(countDistinct).query.get.map(_ ==> List(4))

        _ <- Ns.i.a1.doubles(count).query.get.map(_ ==> List(
          (1, 2),
          (2, 6)
        ))
        _ <- Ns.i.a1.doubles(countDistinct).query.get.map(_ ==> List(
          (1, 2),
          (2, 3)
        ))
      } yield ()
    }
  }
}