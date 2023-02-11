// GENERATED CODE ********************************
package molecule.db.datomic.test.aggr.one.any

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic.async._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrOne_ref_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.ref.insert(List(
          (1, ref1),
          (2, ref2),
          (2, ref2),
          (2, ref3),
        )).transact

        _ <- Ns.i.a1.ref.query.get.map(_.sortBy(_._2) ==> List(
          (1, ref1),
          (2, ref2), // 2 rows coalesced
          (2, ref3),
        ))

        // Distinct values are returned in a List
        _ <- Ns.i.a1.ref(distinct).query.get.map(_ ==> List(
          (1, Set(ref1)),
          (2, Set(ref2, ref3)),
        ))

        _ <- Ns.ref(distinct).query.get.map(_.head ==> Set(
          ref1, ref2, ref3
        ))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.ref.insert(List(ref1, ref2, ref3)).transact
        _ <- Ns.ref(min).query.get.map(_ ==> List(ref1))
        _ <- Ns.ref(min(1)).query.get.map(_ ==> List(Set(ref1)))
        _ <- Ns.ref(min(2)).query.get.map(_ ==> List(Set(ref1, ref2)))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.ref.insert(List(ref1, ref2, ref3)).transact
        _ <- Ns.ref(max).query.get.map(_ ==> List(ref3))
        _ <- Ns.ref(max(1)).query.get.map(_ ==> List(Set(ref3)))
        _ <- Ns.ref(max(2)).query.get.map(_ ==> List(Set(ref3, ref2)))
      } yield ()
    }


    "rand" - types { implicit conn =>
      for {
        _ <- Ns.ref.insert(List(ref1, ref2, ref3)).transact
        all = Set(ref1, ref2, ref3, ref4)
        _ <- Ns.ref(rand).query.get.map(res => all.contains(res.head) ==> true)
        _ <- Ns.ref(rand(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.ref(rand(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.ref.insert(List(ref1, ref2, ref3)).transact
        all = Set(ref1, ref2, ref3, ref4)
        _ <- Ns.ref(sample).query.get.map(res => all.contains(res.head) ==> true)
        _ <- Ns.ref(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.ref(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "count, countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.ref.insert(List(
          (1, ref1),
          (2, ref2),
          (2, ref2),
          (2, ref3),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.ref(count).query.get.map(_ ==> List(4))
        _ <- Ns.ref(countDistinct).query.get.map(_ ==> List(3))

        _ <- Ns.i.a1.ref(count).query.get.map(_ ==> List(
          (1, 1),
          (2, 3)
        ))
        _ <- Ns.i.a1.ref(countDistinct).query.get.map(_ ==> List(
          (1, 1),
          (2, 2)
        ))
      } yield ()
    }
  }
}