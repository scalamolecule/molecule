// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.set.types

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
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
          _ <- Ns.i.a1.longSet_.has(long1, long2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.longSet_.has(long1, long3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.longSet_.has(long2, long3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.longSet_.has(long1, long2, long3).query.get.map(_ ==> List(1, 2))
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


    "Optional" - {

      "has" - types { implicit conn =>
        val a = (1, Some(Set(long1, long2)))
        val b = (2, Some(Set(long2, long3, long4)))
        val c = (3, None)
        for {
          _ <- Ns.i.longSet_?.insert(a, b, c).transact

          // Sets with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.longSet_?.has(Some(long0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longSet_?.has(Some(long1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longSet_?.has(Some(long2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longSet_?.has(Some(long3)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.longSet_?.has(Some(Seq(long0))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longSet_?.has(Some(Seq(long1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longSet_?.has(Some(Seq(long2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longSet_?.has(Some(Seq(long3))).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.longSet_?.has(Some(Seq(long1, long2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longSet_?.has(Some(Seq(long1, long3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longSet_?.has(Some(Seq(long2, long3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longSet_?.has(Some(Seq(long1, long2, long3))).query.get.map(_ ==> List(a, b))

          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.longSet_?.has(Some(Seq.empty[Long])).query.get.map(_ ==> List())

          // None matches non-asserted values
          _ <- Ns.i.a1.longSet_?.has(Option.empty[Long]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.longSet_?.has(Option.empty[Seq[Long]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Some(Set(long1, long2)))
        val b = (2, Some(Set(long2, long3, long4)))
        val c = (3, None)
        for {
          _ <- Ns.i.longSet_?.insert(a, b, c).transact

          // Sets without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.longSet_?.hasNo(Some(long0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longSet_?.hasNo(Some(long1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.longSet_?.hasNo(Some(long2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longSet_?.hasNo(Some(long3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longSet_?.hasNo(Some(long4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longSet_?.hasNo(Some(long5)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.longSet_?.hasNo(Some(Seq(long0))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longSet_?.hasNo(Some(Seq(long1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.longSet_?.hasNo(Some(Seq(long2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longSet_?.hasNo(Some(Seq(long3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longSet_?.hasNo(Some(Seq(long4))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longSet_?.hasNo(Some(Seq(long5))).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.longSet_?.hasNo(Some(Seq(long1, long2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longSet_?.hasNo(Some(Seq(long1, long3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longSet_?.hasNo(Some(Seq(long1, long4))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longSet_?.hasNo(Some(Seq(long1, long5))).query.get.map(_ ==> List(b))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.longSet_?.hasNo(Some(Seq.empty[Long])).query.get.map(_ ==> List(a, b))

          // Negating None returns all asserted
          _ <- Ns.i.a1.longSet_?.hasNo(Option.empty[Long]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longSet_?.hasNo(Option.empty[Seq[Long]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}