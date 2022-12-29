// GENERATED CODE ********************************
package molecule.db.datomic.test.aggr.set.any

import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._
import molecule.core.util.Executor._

object AggrSet_Long_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.longs.insert(List(
          (1, Set(long1, long2)),
          (2, Set(long2, long3)),
          (2, Set(long3, long4)),
          (2, Set(long3, long4)),
        )).transact

        // Non-aggregated card-many Set of attribute values coalesce
        _ <- Ns.i.a1.longs.query.get.map(_ ==> List(
          (1, Set(long1, long2)),
          (2, Set(long2, long3, long4)), // 3 rows coalesced
        ))

        // Use `distinct` keyword to retrieve unique Sets of values
        _ <- Ns.i.a1.longs(distinct).query.get.map(_ ==> List(
          (1, Set(Set(long1, long2))),
          (2, Set(
            Set(long2, long3),
            Set(long3, long4) // 2 rows coalesced
          ))
        ))

        _ <- Ns.longs(distinct).query.get.map(_ ==> List(
          Set(
            Set(long1, long2),
            Set(long2, long3),
            Set(long3, long4),
          )
        ))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.i.longs.insert(List(
          (1, Set(long1, long2)),
          (2, Set(long2, long3)),
          (2, Set(long3, long4)),
          (2, Set(long3, long4)),
        )).transact

        _ <- Ns.longs(min).query.get.map(_ ==> List(Set(long1)))
        _ <- Ns.longs(min(1)).query.get.map(_ ==> List(Set(long1)))
        _ <- Ns.longs(min(2)).query.get.map(_ ==> List(Set(long1, long2)))

        _ <- Ns.i.longs(min).query.get.map(_ ==> List(
          (1, Set(long1)),
          (2, Set(long2)),
        ))
        // Same as
        _ <- Ns.i.longs(min(1)).query.get.map(_ ==> List(
          (1, Set(long1)),
          (2, Set(long2)),
        ))

        _ <- Ns.i.longs(min(2)).query.get.map(_ ==> List(
          (1, Set(long1, long2)),
          (2, Set(long2, long3)),
        ))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.i.longs.insert(List(
          (1, Set(long1, long2)),
          (2, Set(long2, long3)),
          (2, Set(long3, long4)),
          (2, Set(long3, long4)),
        )).transact

        _ <- Ns.longs(max).query.get.map(_ ==> List(Set(long4)))
        _ <- Ns.longs(max(1)).query.get.map(_ ==> List(Set(long4)))
        _ <- Ns.longs(max(2)).query.get.map(_ ==> List(Set(long3, long4)))

        _ <- Ns.i.longs(max).query.get.map(_ ==> List(
          (1, Set(long2)),
          (2, Set(long4)),
        ))
        // Same as
        _ <- Ns.i.longs(max(1)).query.get.map(_ ==> List(
          (1, Set(long2)),
          (2, Set(long4)),
        ))

        _ <- Ns.i.longs(max(2)).query.get.map(_ ==> List(
          (1, Set(long1, long2)),
          (2, Set(long3, long4)),
        ))
      } yield ()
    }


    "rand" - types { implicit conn =>
      for {
        _ <- Ns.i.longs.insert(List(
          (1, Set(long1, long2)),
          (2, Set(long2, long3)),
          (2, Set(long3, long4)),
          (2, Set(long3, long4)),
        )).transact
        all = Set(long1, long2, long3, long4)
        _ <- Ns.longs(rand).query.get.map(res => all.contains(res.head.head) ==> true)
        _ <- Ns.longs(rand(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.longs(rand(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.i.longs.insert(List(
          (1, Set(long1, long2)),
          (2, Set(long2, long3)),
          (2, Set(long3, long4)),
          (2, Set(long3, long4)),
        )).transact
        all = Set(long1, long2, long3, long4)
        _ <- Ns.longs(sample).query.get.map(res => all.contains(res.head.head) ==> true)
        _ <- Ns.longs(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.longs(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "count, countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.longs.insert(List(
          (1, Set(long1, long2)),
          (2, Set(long2, long3)),
          (2, Set(long3, long4)),
          (2, Set(long3, long4)),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.longs(count).query.get.map(_ ==> List(8))
        _ <- Ns.longs(countDistinct).query.get.map(_ ==> List(4))

        _ <- Ns.i.a1.longs(count).query.get.map(_ ==> List(
          (1, 2),
          (2, 6)
        ))
        _ <- Ns.i.a1.longs(countDistinct).query.get.map(_ ==> List(
          (1, 2),
          (2, 3)
        ))
      } yield ()
    }
  }
}