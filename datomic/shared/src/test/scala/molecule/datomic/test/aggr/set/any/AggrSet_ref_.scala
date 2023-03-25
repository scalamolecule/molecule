// GENERATED CODE ********************************
package molecule.datomic.test.aggr.set.any

import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.datomic.async._
import utest._
import molecule.core.util.Executor._
import molecule.datomic.setup.DatomicTestSuite

object AggrSet_ref_ extends DatomicTestSuite {


  override lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.refs.insert(List(
          (1, Set(ref1, ref2)),
          (2, Set(ref2, ref3)),
          (2, Set(ref3, ref4)),
          (2, Set(ref3, ref4)),
        )).transact

        // Non-aggregated card-many Set of attribute values coalesce
        _ <- Ns.i.a1.refs.query.get.map(_ ==> List(
          (1, Set(ref1, ref2)),
          (2, Set(ref2, ref3, ref4)), // 3 rows coalesced
        ))

        // Use `distinct` keyword to retrieve unique Sets of values
        _ <- Ns.i.a1.refs(distinct).query.get.map(_ ==> List(
          (1, Set(Set(ref1, ref2))),
          (2, Set(
            Set(ref2, ref3),
            Set(ref3, ref4) // 2 rows coalesced
          ))
        ))

        _ <- Ns.refs(distinct).query.get.map(_ ==> List(
          Set(
            Set(ref1, ref2),
            Set(ref2, ref3),
            Set(ref3, ref4),
          )
        ))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.i.refs.insert(List(
          (1, Set(ref1, ref2)),
          (2, Set(ref2, ref3)),
          (2, Set(ref3, ref4)),
          (2, Set(ref3, ref4)),
        )).transact

        _ <- Ns.refs(min).query.get.map(_ ==> List(Set(ref1)))
        _ <- Ns.refs(min(1)).query.get.map(_ ==> List(Set(ref1)))
        _ <- Ns.refs(min(2)).query.get.map(_ ==> List(Set(ref1, ref2)))

        _ <- Ns.i.refs(min).query.get.map(_ ==> List(
          (1, Set(ref1)),
          (2, Set(ref2)),
        ))
        // Same as
        _ <- Ns.i.refs(min(1)).query.get.map(_ ==> List(
          (1, Set(ref1)),
          (2, Set(ref2)),
        ))

        _ <- Ns.i.refs(min(2)).query.get.map(_ ==> List(
          (1, Set(ref1, ref2)),
          (2, Set(ref2, ref3)),
        ))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.i.refs.insert(List(
          (1, Set(ref1, ref2)),
          (2, Set(ref2, ref3)),
          (2, Set(ref3, ref4)),
          (2, Set(ref3, ref4)),
        )).transact

        _ <- Ns.refs(max).query.get.map(_ ==> List(Set(ref4)))
        _ <- Ns.refs(max(1)).query.get.map(_ ==> List(Set(ref4)))
        _ <- Ns.refs(max(2)).query.get.map(_ ==> List(Set(ref3, ref4)))

        _ <- Ns.i.refs(max).query.get.map(_ ==> List(
          (1, Set(ref2)),
          (2, Set(ref4)),
        ))
        // Same as
        _ <- Ns.i.refs(max(1)).query.get.map(_ ==> List(
          (1, Set(ref2)),
          (2, Set(ref4)),
        ))

        _ <- Ns.i.refs(max(2)).query.get.map(_ ==> List(
          (1, Set(ref1, ref2)),
          (2, Set(ref3, ref4)),
        ))
      } yield ()
    }


    "rand" - types { implicit conn =>
      for {
        _ <- Ns.i.refs.insert(List(
          (1, Set(ref1, ref2)),
          (2, Set(ref2, ref3)),
          (2, Set(ref3, ref4)),
          (2, Set(ref3, ref4)),
        )).transact
        all = Set(ref1, ref2, ref3, ref4)
        _ <- Ns.refs(rand).query.get.map(res => all.contains(res.head.head) ==> true)
        _ <- Ns.refs(rand(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.refs(rand(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.i.refs.insert(List(
          (1, Set(ref1, ref2)),
          (2, Set(ref2, ref3)),
          (2, Set(ref3, ref4)),
          (2, Set(ref3, ref4)),
        )).transact
        all = Set(ref1, ref2, ref3, ref4)
        _ <- Ns.refs(sample).query.get.map(res => all.contains(res.head.head) ==> true)
        _ <- Ns.refs(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.refs(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "count, countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.refs.insert(List(
          (1, Set(ref1, ref2)),
          (2, Set(ref2, ref3)),
          (2, Set(ref3, ref4)),
          (2, Set(ref3, ref4)),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.refs(count).query.get.map(_ ==> List(8))
        _ <- Ns.refs(countDistinct).query.get.map(_ ==> List(4))

        _ <- Ns.i.a1.refs(count).query.get.map(_ ==> List(
          (1, 2),
          (2, 6)
        ))
        _ <- Ns.i.a1.refs(countDistinct).query.get.map(_ ==> List(
          (1, 2),
          (2, 3)
        ))
      } yield ()
    }
  }
}