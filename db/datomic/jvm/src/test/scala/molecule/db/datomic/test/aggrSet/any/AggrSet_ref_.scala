// GENERATED CODE ********************************
package molecule.db.datomic.test.aggrSet.any


import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AggrSet_ref_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      Ns.i.refs.insert(List(
        (1, Set(ref1, ref2)),
        (2, Set(ref2, ref3)),
        (2, Set(ref3, ref4)),
        (2, Set(ref3, ref4)),
      )).transact

      // Non-aggregated card-many Set of attribute values coalesce
      Ns.i.a1.refs.query.get ==> List(
        (1, Set(ref1, ref2)),
        (2, Set(ref2, ref3, ref4)), // 3 rows coalesced
      )

      // Use `distinct` keyword to retrieve unique Sets of values
      Ns.i.a1.refs(distinct).query.get ==> List(
        (1, Set(Set(ref1, ref2))),
        (2, Set(
          Set(ref2, ref3),
          Set(ref3, ref4) // 2 rows coalesced
        ))
      )

      Ns.refs(distinct).query.get ==> List(
        Set(
          Set(ref1, ref2),
          Set(ref2, ref3),
          Set(ref3, ref4),
        )
      )
    }


    "min" - types { implicit conn =>
      Ns.i.refs.insert(List(
        (1, Set(ref1, ref2)),
        (2, Set(ref2, ref3)),
        (2, Set(ref3, ref4)),
        (2, Set(ref3, ref4)),
      )).transact

      Ns.refs(min).query.get ==> List(Set(ref1))
      Ns.refs(min(1)).query.get ==> List(Set(ref1))
      Ns.refs(min(2)).query.get ==> List(Set(ref1, ref2))

      Ns.i.refs(min).query.get ==> List(
        (1, Set(ref1)),
        (2, Set(ref2)),
      )
      // Same as
      Ns.i.refs(min(1)).query.get ==> List(
        (1, Set(ref1)),
        (2, Set(ref2)),
      )

      Ns.i.refs(min(2)).query.get ==> List(
        (1, Set(ref1, ref2)),
        (2, Set(ref2, ref3)),
      )
    }


    "max" - types { implicit futConn =>
      Ns.i.refs.insert(List(
        (1, Set(ref1, ref2)),
        (2, Set(ref2, ref3)),
        (2, Set(ref3, ref4)),
        (2, Set(ref3, ref4)),
      )).transact

      Ns.refs(max).query.get ==> List(Set(ref4))
      Ns.refs(max(1)).query.get ==> List(Set(ref4))
      Ns.refs(max(2)).query.get ==> List(Set(ref3, ref4))

      Ns.i.refs(max).query.get ==> List(
        (1, Set(ref2)),
        (2, Set(ref4)),
      )
      // Same as
      Ns.i.refs(max(1)).query.get ==> List(
        (1, Set(ref2)),
        (2, Set(ref4)),
      )

      Ns.i.refs(max(2)).query.get ==> List(
        (1, Set(ref1, ref2)),
        (2, Set(ref3, ref4)),
      )
    }


    "rand" - types { implicit conn =>
      Ns.i.refs.insert(List(
        (1, Set(ref1, ref2)),
        (2, Set(ref2, ref3)),
        (2, Set(ref3, ref4)),
        (2, Set(ref3, ref4)),
      )).transact
      val all = Set(ref1, ref2, ref3, ref4)
      all.contains(Ns.refs(rand).query.get.head.head) ==> true
      all.intersect(Ns.refs(rand(1)).query.get.head).nonEmpty ==> true
      all.intersect(Ns.refs(rand(2)).query.get.head).nonEmpty ==> true
    }


    "sample" - types { implicit futConn =>
      Ns.i.refs.insert(List(
        (1, Set(ref1, ref2)),
        (2, Set(ref2, ref3)),
        (2, Set(ref3, ref4)),
        (2, Set(ref3, ref4)),
      )).transact
      val all = Set(ref1, ref2, ref3, ref4)
      all.contains(Ns.refs(sample).query.get.head.head) ==> true
      all.intersect(Ns.refs(sample(1)).query.get.head).nonEmpty ==> true
      all.intersect(Ns.refs(sample(2)).query.get.head).nonEmpty ==> true
    }


    "count, countDistinct" - types { implicit conn =>
      Ns.i.refs.insert(List(
        (1, Set(ref1, ref2)),
        (2, Set(ref2, ref3)),
        (2, Set(ref3, ref4)),
        (2, Set(ref3, ref4)),
      )).transact

      Ns.i(count).query.get ==> List(4)
      Ns.i(countDistinct).query.get ==> List(2)

      Ns.refs(count).query.get ==> List(8)
      Ns.refs(countDistinct).query.get ==> List(4)

      Ns.i.a1.refs(count).query.get ==> List(
        (1, 2),
        (2, 6)
      )
      Ns.i.a1.refs(countDistinct).query.get ==> List(
        (1, 2),
        (2, 3)
      )
    }
  }
}