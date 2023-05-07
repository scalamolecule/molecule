// GENERATED CODE ********************************
package molecule.datalog.datomic.test.aggr.set.any

import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.datalog.datomic.async._
import utest._
import molecule.core.util.Executor._
import molecule.datalog.datomic.setup.DatomicTestSuite

object AggrSet_BigInt_ extends DatomicTestSuite {


  override lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.bigInts.insert(List(
          (1, Set(bigInt1, bigInt2)),
          (2, Set(bigInt2, bigInt3)),
          (2, Set(bigInt3, bigInt4)),
          (2, Set(bigInt3, bigInt4)),
        )).transact

        // Non-aggregated card-many Set of attribute values coalesce
        _ <- Ns.i.a1.bigInts.query.get.map(_ ==> List(
          (1, Set(bigInt1, bigInt2)),
          (2, Set(bigInt2, bigInt3, bigInt4)), // 3 rows coalesced
        ))

        // Use `distinct` keyword to retrieve unique Sets of values
        _ <- Ns.i.a1.bigInts(distinct).query.get.map(_ ==> List(
          (1, Set(Set(bigInt1, bigInt2))),
          (2, Set(
            Set(bigInt2, bigInt3),
            Set(bigInt3, bigInt4) // 2 rows coalesced
          ))
        ))

        _ <- Ns.bigInts(distinct).query.get.map(_ ==> List(
          Set(
            Set(bigInt1, bigInt2),
            Set(bigInt2, bigInt3),
            Set(bigInt3, bigInt4),
          )
        ))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.i.bigInts.insert(List(
          (1, Set(bigInt1, bigInt2)),
          (2, Set(bigInt2, bigInt3)),
          (2, Set(bigInt3, bigInt4)),
          (2, Set(bigInt3, bigInt4)),
        )).transact

        _ <- Ns.bigInts(min).query.get.map(_ ==> List(Set(bigInt1)))
        _ <- Ns.bigInts(min(1)).query.get.map(_ ==> List(Set(bigInt1)))
        _ <- Ns.bigInts(min(2)).query.get.map(_ ==> List(Set(bigInt1, bigInt2)))

        _ <- Ns.i.bigInts(min).query.get.map(_ ==> List(
          (1, Set(bigInt1)),
          (2, Set(bigInt2)),
        ))
        // Same as
        _ <- Ns.i.bigInts(min(1)).query.get.map(_ ==> List(
          (1, Set(bigInt1)),
          (2, Set(bigInt2)),
        ))

        _ <- Ns.i.bigInts(min(2)).query.get.map(_ ==> List(
          (1, Set(bigInt1, bigInt2)),
          (2, Set(bigInt2, bigInt3)),
        ))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.i.bigInts.insert(List(
          (1, Set(bigInt1, bigInt2)),
          (2, Set(bigInt2, bigInt3)),
          (2, Set(bigInt3, bigInt4)),
          (2, Set(bigInt3, bigInt4)),
        )).transact

        _ <- Ns.bigInts(max).query.get.map(_ ==> List(Set(bigInt4)))
        _ <- Ns.bigInts(max(1)).query.get.map(_ ==> List(Set(bigInt4)))
        _ <- Ns.bigInts(max(2)).query.get.map(_ ==> List(Set(bigInt3, bigInt4)))

        _ <- Ns.i.bigInts(max).query.get.map(_ ==> List(
          (1, Set(bigInt2)),
          (2, Set(bigInt4)),
        ))
        // Same as
        _ <- Ns.i.bigInts(max(1)).query.get.map(_ ==> List(
          (1, Set(bigInt2)),
          (2, Set(bigInt4)),
        ))

        _ <- Ns.i.bigInts(max(2)).query.get.map(_ ==> List(
          (1, Set(bigInt1, bigInt2)),
          (2, Set(bigInt3, bigInt4)),
        ))
      } yield ()
    }


    "rand" - types { implicit conn =>
      for {
        _ <- Ns.i.bigInts.insert(List(
          (1, Set(bigInt1, bigInt2)),
          (2, Set(bigInt2, bigInt3)),
          (2, Set(bigInt3, bigInt4)),
          (2, Set(bigInt3, bigInt4)),
        )).transact
        all = Set(bigInt1, bigInt2, bigInt3, bigInt4)
        _ <- Ns.bigInts(rand).query.get.map(res => all.contains(res.head.head) ==> true)
        _ <- Ns.bigInts(rand(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.bigInts(rand(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.i.bigInts.insert(List(
          (1, Set(bigInt1, bigInt2)),
          (2, Set(bigInt2, bigInt3)),
          (2, Set(bigInt3, bigInt4)),
          (2, Set(bigInt3, bigInt4)),
        )).transact
        all = Set(bigInt1, bigInt2, bigInt3, bigInt4)
        _ <- Ns.bigInts(sample).query.get.map(res => all.contains(res.head.head) ==> true)
        _ <- Ns.bigInts(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.bigInts(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "count, countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.bigInts.insert(List(
          (1, Set(bigInt1, bigInt2)),
          (2, Set(bigInt2, bigInt3)),
          (2, Set(bigInt3, bigInt4)),
          (2, Set(bigInt3, bigInt4)),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.bigInts(count).query.get.map(_ ==> List(8))
        _ <- Ns.bigInts(countDistinct).query.get.map(_ ==> List(4))

        _ <- Ns.i.a1.bigInts(count).query.get.map(_ ==> List(
          (1, 2),
          (2, 6)
        ))
        _ <- Ns.i.a1.bigInts(countDistinct).query.get.map(_ ==> List(
          (1, 2),
          (2, 3)
        ))
      } yield ()
    }
  }
}