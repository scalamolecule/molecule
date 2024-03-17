// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.set.any

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrSet_String_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.stringSet.insert(List(
          (1, Set(string1, string2)),
          (2, Set(string2)),
          (2, Set(string3, string4)),
          (2, Set(string3, string4)),
        )).transact

        // Non-aggregated card-many Set of attribute values coalesce
        _ <- Ns.i.a1.stringSet.query.get.map(_ ==> List(
          (1, Set(string1, string2)),
          (2, Set(string2, string3, string4)), // 3 rows coalesced
        ))

        // Use `distinct` keyword to retrieve unique Sets of Sets
        _ <- Ns.i.a1.stringSet(distinct).query.get.map(_ ==> List(
          (1, Set(Set(string1, string2))),
          (2, Set(
            Set(string2),
            Set(string3, string4) // 2 rows coalesced
          ))
        ))

        _ <- Ns.stringSet(distinct).query.get.map(_ ==> List(
          Set(
            Set(string1, string2),
            Set(string2),
            Set(string3, string4),
          )
        ))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.i.stringSet.insert(List(
          (1, Set(string1, string2)),
          (2, Set(string2)),
          (2, Set(string3, string4)),
          (2, Set(string3, string4)),
        )).transact

        // Matching values coalesced stringo one Set

        _ <- Ns.stringSet(min).query.get.map(_ ==> List(Set(string1)))
        _ <- Ns.stringSet(min(1)).query.get.map(_ ==> List(Set(string1)))
        _ <- Ns.stringSet(min(2)).query.get.map(_ ==> List(Set(string1, string2)))
        _ <- Ns.stringSet(min(3)).query.get.map(_ ==> List(Set(string1, string2, string3)))

        _ <- Ns.i.a1.stringSet(min).query.get.map(_ ==> List(
          (1, Set(string1)),
          (2, Set(string2)),
        ))
        // Same as
        _ <- Ns.i.a1.stringSet(min(1)).query.get.map(_ ==> List(
          (1, Set(string1)),
          (2, Set(string2)),
        ))

        _ <- Ns.i.a1.stringSet(min(2)).query.get.map(_ ==> List(
          (1, Set(string1, string2)),
          (2, Set(string2, string3)),
        ))

        _ <- Ns.i.a1.stringSet(min(3)).query.get.map(_ ==> List(
          (1, Set(string1, string2)),
          (2, Set(string2, string3, string4)),
        ))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.i.stringSet.insert(List(
          (1, Set(string1, string2)),
          (2, Set(string2)),
          (2, Set(string3, string4)),
          (2, Set(string3, string4)),
        )).transact

        // Matching values coalesced stringo one Set

        _ <- Ns.stringSet(max).query.get.map(_ ==> List(Set(string4)))
        _ <- Ns.stringSet(max(1)).query.get.map(_ ==> List(Set(string4)))
        _ <- Ns.stringSet(max(2)).query.get.map(_ ==> List(Set(string3, string4)))
        _ <- Ns.stringSet(max(3)).query.get.map(_ ==> List(Set(string2, string3, string4)))

        _ <- Ns.i.a1.stringSet(max).query.get.map(_ ==> List(
          (1, Set(string2)),
          (2, Set(string4)),
        ))
        // Same as
        _ <- Ns.i.a1.stringSet(max(1)).query.get.map(_ ==> List(
          (1, Set(string2)),
          (2, Set(string4)),
        ))

        _ <- Ns.i.a1.stringSet(max(2)).query.get.map(_ ==> List(
          (1, Set(string1, string2)),
          (2, Set(string3, string4)),
        ))

        _ <- Ns.i.a1.stringSet(max(3)).query.get.map(_ ==> List(
          (1, Set(string1, string2)),
          (2, Set(string2, string3, string4)),
        ))
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.i.stringSet.insert(List(
          (1, Set(string1, string2)),
          (2, Set(string2)),
          (2, Set(string3, string4)),
          (2, Set(string3, string4)),
        )).transact
        all = Set(string1, string2, string3, string4)
        _ <- Ns.stringSet(sample).query.get.map(res => all.contains(res.head.head) ==> true)
        _ <- Ns.stringSet(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.stringSet(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "count countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.stringSet.insert(List(
          (1, Set(string1, string2)),
          (2, Set(string2)),
          (2, Set(string3, string4)),
          (2, Set(string3, string4)),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.stringSet(count).query.get.map(_ ==> List(7))
        _ <- Ns.stringSet(countDistinct).query.get.map(_ ==> List(4))

        _ <- Ns.i.a1.stringSet(count).query.get.map(_ ==> List(
          (1, 2),
          (2, 5)
        ))
        _ <- Ns.i.a1.stringSet(countDistinct).query.get.map(_ ==> List(
          (1, 2),
          (2, 3)
        ))
      } yield ()
    }
  }
}