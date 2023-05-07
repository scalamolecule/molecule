// GENERATED CODE ********************************
package molecule.datalog.datomic.test.aggr.one.any

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.datalog.datomic.async._
import molecule.datalog.datomic.setup.DatomicTestSuite
import utest._

object AggrOne_BigInt_ extends DatomicTestSuite {


  override lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.bigInt.insert(List(
          (1, bigInt1),
          (2, bigInt2),
          (2, bigInt2),
          (2, bigInt3),
        )).transact

        _ <- Ns.i.a1.bigInt.query.get.map(_.sortBy(_._2) ==> List(
          (1, bigInt1),
          (2, bigInt2), // 2 rows coalesced
          (2, bigInt3),
        ))

        // Distinct values are returned in a List
        _ <- Ns.i.a1.bigInt(distinct).query.get.map(_ ==> List(
          (1, Set(bigInt1)),
          (2, Set(bigInt2, bigInt3)),
        ))

        _ <- Ns.bigInt(distinct).query.get.map(_.head ==> Set(
          bigInt1, bigInt2, bigInt3
        ))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.bigInt.insert(List(bigInt1, bigInt2, bigInt3)).transact
        _ <- Ns.bigInt(min).query.get.map(_ ==> List(bigInt1))
        _ <- Ns.bigInt(min(1)).query.get.map(_ ==> List(Set(bigInt1)))
        _ <- Ns.bigInt(min(2)).query.get.map(_ ==> List(Set(bigInt1, bigInt2)))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.bigInt.insert(List(bigInt1, bigInt2, bigInt3)).transact
        _ <- Ns.bigInt(max).query.get.map(_ ==> List(bigInt3))
        _ <- Ns.bigInt(max(1)).query.get.map(_ ==> List(Set(bigInt3)))
        _ <- Ns.bigInt(max(2)).query.get.map(_ ==> List(Set(bigInt3, bigInt2)))
      } yield ()
    }


    "rand" - types { implicit conn =>
      for {
        _ <- Ns.bigInt.insert(List(bigInt1, bigInt2, bigInt3)).transact
        all = Set(bigInt1, bigInt2, bigInt3, bigInt4)
        _ <- Ns.bigInt(rand).query.get.map(res => all.contains(res.head) ==> true)
        _ <- Ns.bigInt(rand(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.bigInt(rand(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.bigInt.insert(List(bigInt1, bigInt2, bigInt3)).transact
        all = Set(bigInt1, bigInt2, bigInt3, bigInt4)
        _ <- Ns.bigInt(sample).query.get.map(res => all.contains(res.head) ==> true)
        _ <- Ns.bigInt(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.bigInt(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "count, countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.bigInt.insert(List(
          (1, bigInt1),
          (2, bigInt2),
          (2, bigInt2),
          (2, bigInt3),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.bigInt(count).query.get.map(_ ==> List(4))
        _ <- Ns.bigInt(countDistinct).query.get.map(_ ==> List(3))

        _ <- Ns.i.a1.bigInt(count).query.get.map(_ ==> List(
          (1, 1),
          (2, 3)
        ))
        _ <- Ns.i.a1.bigInt(countDistinct).query.get.map(_ ==> List(
          (1, 1),
          (2, 2)
        ))
      } yield ()
    }
  }
}