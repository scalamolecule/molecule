// GENERATED CODE ********************************
package molecule.coreTests.spi.aggr.one.any

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrOne_String_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "distinct" - types { implicit conn =>
      for {
        _ <- Ns.i.string.insert(List(
          (1, string1),
          (2, string2),
          (2, string2),
          (2, string3),
        )).transact

        _ <- Ns.i.string.a1.query.get.map(_ ==> List(
          (1, string1),
          (2, string2), // 2 rows coalesced
          (2, string3),
        ))

        // Distinct values are returned in a Set
        _ <- Ns.i.a1.string(distinct).query.get.map(_ ==> List(
          (1, Set(string1)),
          (2, Set(string2, string3)),
        ))

        _ <- Ns.string(distinct).query.get.map(_.head ==> Set(
          string1, string2, string3
        ))
      } yield ()
    }


    "min/max" - types { implicit conn =>
      for {
        _ <- Ns.i.string.insert(
          (1, string1),
          (1, string2),
          (1, string3),
          (2, string4),
          (2, string5),
          (2, string6),
        ).transact

        _ <- Ns.string(min).query.get.map(_ ==> List(string1))
        _ <- Ns.string(min(1)).query.get.map(_ ==> List(Set(string1)))
        _ <- Ns.string(min(2)).query.get.map(_ ==> List(Set(string1, string2)))

        _ <- Ns.string(max).query.get.map(_ ==> List(string6))
        _ <- Ns.string(max(1)).query.get.map(_ ==> List(Set(string6)))
        _ <- Ns.string(max(2)).query.get.map(_ ==> List(Set(string5, string6)))

        _ <- Ns.i.a1.string(min(2)).query.get.map(_ ==> List(
          (1, Set(string1, string2)),
          (2, Set(string4, string5))
        ))

        _ <- Ns.i.a1.string(max(2)).query.get.map(_ ==> List(
          (1, Set(string2, string3)),
          (2, Set(string5, string6))
        ))

        _ <- Ns.i.a1.string(min(2)).string(max(2)).query.get.map(_ ==> List(
          (1, Set(string1, string2), Set(string2, string3)),
          (2, Set(string4, string5), Set(string5, string6))
        ))
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