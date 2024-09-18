// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.set.types

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSet_Long_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "has" - types { implicit conn =>
        val a = (1, Set(long1, long2))
        val b = (2, Set(long2, long3, long4))
        for {
          _ <- Ns.i.longSet.insert(List(a, b)).transact

          // Sets with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.longSet.has(long0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longSet.has(long1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longSet.has(long2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longSet.has(long3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.longSet.has(Seq(long0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longSet.has(Seq(long1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longSet.has(Seq(long2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longSet.has(Seq(long3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.longSet.has(long1, long2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longSet.has(long1, long3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longSet.has(long2, long3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longSet.has(long1, long2, long3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.longSet.has(Seq(long1, long2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longSet.has(Seq(long1, long3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longSet.has(Seq(long2, long3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longSet.has(Seq(long1, long2, long3)).query.get.map(_ ==> List(a, b))

          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.longSet.has(Seq.empty[Long]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Set(long1, long2))
        val b = (2, Set(long2, long3, long4))
        for {
          _ <- Ns.i.longSet.insert(List(a, b)).transact

          // Sets without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.longSet.hasNo(long0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longSet.hasNo(long1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.longSet.hasNo(long2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longSet.hasNo(long3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longSet.hasNo(long4).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longSet.hasNo(long5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.longSet.hasNo(Seq(long0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longSet.hasNo(Seq(long1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.longSet.hasNo(Seq(long2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longSet.hasNo(Seq(long3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longSet.hasNo(Seq(long4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longSet.hasNo(Seq(long5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.longSet.hasNo(long1, long2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longSet.hasNo(long1, long3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longSet.hasNo(long1, long4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longSet.hasNo(long1, long5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.longSet.hasNo(Seq(long1, long2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longSet.hasNo(Seq(long1, long3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longSet.hasNo(Seq(long1, long4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longSet.hasNo(Seq(long1, long5)).query.get.map(_ ==> List(b))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.longSet.hasNo(Seq.empty[Long]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "has" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.longSet.insert(List(
            (1, Set(long1, long2)),
            (2, Set(long2, long3, long4))
          )).transact

          // Sets with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.longSet_.has(long0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longSet_.has(long1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.longSet_.has(long2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.longSet_.has(long3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.longSet_.has(Seq(long0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longSet_.has(Seq(long1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.longSet_.has(Seq(long2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.longSet_.has(Seq(long3)).query.get.map(_ ==> List(2))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.longSet_.has(long0, long1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.longSet_.has(long1, long2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.longSet_.has(long1, long3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.longSet_.has(long2, long3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.longSet_.has(long3, long4).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.longSet_.has(Seq(long1, long2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.longSet_.has(Seq(long1, long3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.longSet_.has(Seq(long2, long3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.longSet_.has(Seq(long1, long2, long3)).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.longSet_.has(Seq.empty[Long]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.longSet.insert(List(
            (1, Set(long1, long2)),
            (2, Set(long2, long3, long4))
          )).transact

          // Sets without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.longSet_.hasNo(long0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.longSet_.hasNo(long1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.longSet_.hasNo(long2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longSet_.hasNo(long3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.longSet_.hasNo(long4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.longSet_.hasNo(long5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.longSet_.hasNo(Seq(long0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.longSet_.hasNo(Seq(long1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.longSet_.hasNo(Seq(long2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longSet_.hasNo(Seq(long3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.longSet_.hasNo(Seq(long4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.longSet_.hasNo(Seq(long5)).query.get.map(_ ==> List(1, 2))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.longSet_.hasNo(long1, long2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longSet_.hasNo(long1, long3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longSet_.hasNo(long1, long4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longSet_.hasNo(long1, long5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.longSet_.hasNo(Seq(long1, long2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longSet_.hasNo(Seq(long1, long3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longSet_.hasNo(Seq(long1, long4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longSet_.hasNo(Seq(long1, long5)).query.get.map(_ ==> List(2))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.longSet_.hasNo(Seq.empty[Long]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }

    // No filtering on optional Set attributes
  }
}