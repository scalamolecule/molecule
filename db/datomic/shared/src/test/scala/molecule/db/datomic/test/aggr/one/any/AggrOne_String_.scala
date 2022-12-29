// GENERATED CODE ********************************
package molecule.db.datomic.test.aggr.one.any

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrOne_String_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.string.insert(List(
          (1, string1),
          (2, string2),
          (2, string2),
          (2, string3),
        )).transact

        _ <- Ns.i.a1.string.query.get.map(_.sortBy(_._2) ==> List(
          (1, string1),
          (2, string2), // 2 rows coalesced
          (2, string3),
        ))

        // Distinct values are returned in a List
        _ <- Ns.i.a1.string(distinct).query.get.map(_ ==> List(
          (1, Set(string1)),
          (2, Set(string2, string3)),
        ))

        _ <- Ns.string(distinct).query.get.map(_.head ==> Set(
          string1, string2, string3
        ))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.string.insert(List(string1, string2, string3)).transact
        _ <- Ns.string(min).query.get.map(_ ==> List(string1))
        _ <- Ns.string(min(1)).query.get.map(_ ==> List(Set(string1)))
        _ <- Ns.string(min(2)).query.get.map(_ ==> List(Set(string1, string2)))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.string.insert(List(string1, string2, string3)).transact
        _ <- Ns.string(max).query.get.map(_ ==> List(string3))
        _ <- Ns.string(max(1)).query.get.map(_ ==> List(Set(string3)))
        _ <- Ns.string(max(2)).query.get.map(_ ==> List(Set(string3, string2)))
      } yield ()
    }


    "rand" - types { implicit conn =>
      for {
        _ <- Ns.string.insert(List(string1, string2, string3)).transact
        all = Set(string1, string2, string3, string4)
        _ <- Ns.string(rand).query.get.map(res => all.contains(res.head) ==> true)
        _ <- Ns.string(rand(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.string(rand(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.string.insert(List(string1, string2, string3)).transact
        all = Set(string1, string2, string3, string4)
        _ <- Ns.string(sample).query.get.map(res => all.contains(res.head) ==> true)
        _ <- Ns.string(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.string(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "count, countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.string.insert(List(
          (1, string1),
          (2, string2),
          (2, string2),
          (2, string3),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.string(count).query.get.map(_ ==> List(4))
        _ <- Ns.string(countDistinct).query.get.map(_ ==> List(3))

        _ <- Ns.i.a1.string(count).query.get.map(_ ==> List(
          (1, 1),
          (2, 3)
        ))
        _ <- Ns.i.a1.string(countDistinct).query.get.map(_ ==> List(
          (1, 1),
          (2, 2)
        ))
      } yield ()
    }
  }
}