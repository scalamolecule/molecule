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
        _ <- Ns.i.strings.insert(List(
          (1, Set(string1, string2)),
          (2, Set(string2, string3)),
          (2, Set(string3, string4)),
          (2, Set(string3, string4)),
        )).transact

        // Non-aggregated card-many Set of attribute values coalesce
        _ <- Ns.i.a1.strings.query.get.map(_ ==> List(
          (1, Set(string1, string2)),
          (2, Set(string2, string3, string4)), // 3 rows coalesced
        ))

        // Use `distinct` keyword to retrieve unique Sets of Sets
        _ <- Ns.i.a1.strings(distinct).query.get.map(_ ==> List(
          (1, Set(Set(string1, string2))),
          (2, Set(
            Set(string2, string3),
            Set(string3, string4) // 2 rows coalesced
          ))
        ))

        _ <- Ns.strings(distinct).query.get.map(_ ==> List(
          Set(
            Set(string1, string2),
            Set(string2, string3),
            Set(string3, string4),
          )
        ))
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        _ <- Ns.i.strings.insert(List(
          (1, Set(string1, string2)),
          (2, Set(string2, string3)),
          (2, Set(string3, string4)),
          (2, Set(string3, string4)),
        )).transact

        // Matching values coalesced stringo one Set

        _ <- Ns.strings(min).query.get.map(_ ==> List(Set(string1)))
        _ <- Ns.strings(min(1)).query.get.map(_ ==> List(Set(string1)))
        _ <- Ns.strings(min(2)).query.get.map(_ ==> List(Set(string1, string2)))
        _ <- Ns.strings(min(3)).query.get.map(_ ==> List(Set(string1, string2, string3)))

        _ <- Ns.i.a1.strings(min).query.get.map(_ ==> List(
          (1, Set(string1)),
          (2, Set(string2)),
        ))
        // Same as
        _ <- Ns.i.a1.strings(min(1)).query.get.map(_ ==> List(
          (1, Set(string1)),
          (2, Set(string2)),
        ))

        _ <- Ns.i.a1.strings(min(2)).query.get.map(_ ==> List(
          (1, Set(string1, string2)),
          (2, Set(string2, string3)),
        ))

        _ <- Ns.i.a1.strings(min(3)).query.get.map(_ ==> List(
          (1, Set(string1, string2)),
          (2, Set(string2, string3, string4)),
        ))
      } yield ()
    }


    "max" - types { implicit futConn =>
      for {
        _ <- Ns.i.strings.insert(List(
          (1, Set(string1, string2)),
          (2, Set(string2, string3)),
          (2, Set(string3, string4)),
          (2, Set(string3, string4)),
        )).transact

        // Matching values coalesced stringo one Set

        _ <- Ns.strings(max).query.get.map(_ ==> List(Set(string4)))
        _ <- Ns.strings(max(1)).query.get.map(_ ==> List(Set(string4)))
        _ <- Ns.strings(max(2)).query.get.map(_ ==> List(Set(string3, string4)))
        _ <- Ns.strings(max(3)).query.get.map(_ ==> List(Set(string2, string3, string4)))

        _ <- Ns.i.a1.strings(max).query.get.map(_ ==> List(
          (1, Set(string2)),
          (2, Set(string4)),
        ))
        // Same as
        _ <- Ns.i.a1.strings(max(1)).query.get.map(_ ==> List(
          (1, Set(string2)),
          (2, Set(string4)),
        ))

        _ <- Ns.i.a1.strings(max(2)).query.get.map(_ ==> List(
          (1, Set(string1, string2)),
          (2, Set(string3, string4)),
        ))

        _ <- Ns.i.a1.strings(max(3)).query.get.map(_ ==> List(
          (1, Set(string1, string2)),
          (2, Set(string2, string3, string4)),
        ))
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        _ <- Ns.i.strings.insert(List(
          (1, Set(string1, string2)),
          (2, Set(string2, string3)),
          (2, Set(string3, string4)),
          (2, Set(string3, string4)),
        )).transact
        all = Set(string1, string2, string3, string4)
        _ <- Ns.strings(sample).query.get.map(res => all.contains(res.head.head) ==> true)
        _ <- Ns.strings(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
        _ <- Ns.strings(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      } yield ()
    }


    "count countDistinct" - types { implicit conn =>
      for {
        _ <- Ns.i.strings.insert(List(
          (1, Set(string1, string2)),
          (2, Set(string2, string3)),
          (2, Set(string3, string4)),
          (2, Set(string3, string4)),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.strings(count).query.get.map(_ ==> List(8))
        _ <- Ns.strings(countDistinct).query.get.map(_ ==> List(4))

        _ <- Ns.i.a1.strings(count).query.get.map(_ ==> List(
          (1, 2),
          (2, 6)
        ))
        _ <- Ns.i.a1.strings(countDistinct).query.get.map(_ ==> List(
          (1, 2),
          (2, 3)
        ))
      } yield ()
    }
  }
}