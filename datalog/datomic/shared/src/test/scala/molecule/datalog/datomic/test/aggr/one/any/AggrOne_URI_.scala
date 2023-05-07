// GENERATED CODE ********************************
package molecule.datalog.datomic.test.aggr.one.any

import java.net.URI
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.datalog.datomic.async._
import molecule.datalog.datomic.setup.DatomicTestSuite
import utest._

object AggrOne_URI_ extends DatomicTestSuite {


  override lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.uri.insert(List(
          (1, uri1),
          (2, uri2),
          (2, uri2),
          (2, uri3),
        )).transact

        _ <- Ns.i.a1.uri.query.get.map(_.sortBy(_._2) ==> List(
          (1, uri1),
          (2, uri2), // 2 rows coalesced
          (2, uri3),
        ))

        // Distinct values are returned in a List
        _ <- Ns.i.a1.uri(distinct).query.get.map(_ ==> List(
          (1, Set(uri1)),
          (2, Set(uri2, uri3)),
        ))

        _ <- Ns.uri(distinct).query.get.map(_.head ==> Set(
          uri1, uri2, uri3
        ))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.uri.insert(List(uri1, uri2, uri3)).transact
        _ <- Ns.uri(min).query.get.map(_ ==> List(uri1))
        _ <- Ns.uri(min(1)).query.get.map(_ ==> List(Set(uri1)))
        _ <- Ns.uri(min(2)).query.get.map(_ ==> List(Set(uri1, uri2)))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.uri.insert(List(uri1, uri2, uri3)).transact
        _ <- Ns.uri(max).query.get.map(_ ==> List(uri3))
        _ <- Ns.uri(max(1)).query.get.map(_ ==> List(Set(uri3)))
        _ <- Ns.uri(max(2)).query.get.map(_ ==> List(Set(uri3, uri2)))
      } yield ()
    }


    "rand" - types { implicit conn =>
      for {
        _ <- Ns.uri.insert(List(uri1, uri2, uri3)).transact
        all = Set(uri1, uri2, uri3, uri4)
        _ <- Ns.uri(rand).query.get.map(res => all.contains(res.head) ==> true)
        _ <- Ns.uri(rand(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.uri(rand(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.uri.insert(List(uri1, uri2, uri3)).transact
        all = Set(uri1, uri2, uri3, uri4)
        _ <- Ns.uri(sample).query.get.map(res => all.contains(res.head) ==> true)
        _ <- Ns.uri(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.uri(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "count, countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.uri.insert(List(
          (1, uri1),
          (2, uri2),
          (2, uri2),
          (2, uri3),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.uri(count).query.get.map(_ ==> List(4))
        _ <- Ns.uri(countDistinct).query.get.map(_ ==> List(3))

        _ <- Ns.i.a1.uri(count).query.get.map(_ ==> List(
          (1, 1),
          (2, 3)
        ))
        _ <- Ns.i.a1.uri(countDistinct).query.get.map(_ ==> List(
          (1, 1),
          (2, 2)
        ))
      } yield ()
    }
  }
}