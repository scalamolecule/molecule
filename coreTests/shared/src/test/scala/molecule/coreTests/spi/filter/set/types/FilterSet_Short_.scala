// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.set.types

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSet_Short_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "has" - types { implicit conn =>
        val a = (1, Set(short1, short2))
        val b = (2, Set(short2, short3, short4))
        for {
          _ <- Ns.i.shortSet.insert(List(a, b)).transact

          // Sets with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.shortSet.has(short0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSet.has(short1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shortSet.has(short2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortSet.has(short3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.shortSet.has(Seq(short0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSet.has(Seq(short1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shortSet.has(Seq(short2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortSet.has(Seq(short3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.shortSet.has(short1, short2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortSet.has(short1, short3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortSet.has(short2, short3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortSet.has(short1, short2, short3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.shortSet.has(Seq(short1, short2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortSet.has(Seq(short1, short3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortSet.has(Seq(short2, short3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortSet.has(Seq(short1, short2, short3)).query.get.map(_ ==> List(a, b))

          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.shortSet.has(Seq.empty[Short]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Set(short1, short2))
        val b = (2, Set(short2, short3, short4))
        for {
          _ <- Ns.i.shortSet.insert(List(a, b)).transact

          // Sets without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.shortSet.hasNo(short0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortSet.hasNo(short1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shortSet.hasNo(short2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSet.hasNo(short3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shortSet.hasNo(short4).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shortSet.hasNo(short5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.shortSet.hasNo(Seq(short0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortSet.hasNo(Seq(short1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shortSet.hasNo(Seq(short2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSet.hasNo(Seq(short3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shortSet.hasNo(Seq(short4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shortSet.hasNo(Seq(short5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.shortSet.hasNo(short1, short2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSet.hasNo(short1, short3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSet.hasNo(short1, short4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSet.hasNo(short1, short5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.shortSet.hasNo(Seq(short1, short2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSet.hasNo(Seq(short1, short3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSet.hasNo(Seq(short1, short4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSet.hasNo(Seq(short1, short5)).query.get.map(_ ==> List(b))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.shortSet.hasNo(Seq.empty[Short]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "has" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.shortSet.insert(List(
            (1, Set(short1, short2)),
            (2, Set(short2, short3, short4))
          )).transact

          // Sets with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.shortSet_.has(short0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSet_.has(short1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.shortSet_.has(short2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.shortSet_.has(short3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.shortSet_.has(Seq(short0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSet_.has(Seq(short1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.shortSet_.has(Seq(short2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.shortSet_.has(Seq(short3)).query.get.map(_ ==> List(2))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.shortSet_.has(short0, short1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.shortSet_.has(short1, short2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.shortSet_.has(short1, short3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.shortSet_.has(short2, short3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.shortSet_.has(short3, short4).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.shortSet_.has(Seq(short1, short2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.shortSet_.has(Seq(short1, short3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.shortSet_.has(Seq(short2, short3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.shortSet_.has(Seq(short1, short2, short3)).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.shortSet_.has(Seq.empty[Short]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.shortSet.insert(List(
            (1, Set(short1, short2)),
            (2, Set(short2, short3, short4))
          )).transact

          // Sets without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.shortSet_.hasNo(short0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.shortSet_.hasNo(short1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.shortSet_.hasNo(short2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSet_.hasNo(short3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.shortSet_.hasNo(short4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.shortSet_.hasNo(short5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.shortSet_.hasNo(Seq(short0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.shortSet_.hasNo(Seq(short1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.shortSet_.hasNo(Seq(short2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSet_.hasNo(Seq(short3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.shortSet_.hasNo(Seq(short4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.shortSet_.hasNo(Seq(short5)).query.get.map(_ ==> List(1, 2))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.shortSet_.hasNo(short1, short2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSet_.hasNo(short1, short3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSet_.hasNo(short1, short4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSet_.hasNo(short1, short5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.shortSet_.hasNo(Seq(short1, short2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSet_.hasNo(Seq(short1, short3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSet_.hasNo(Seq(short1, short4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSet_.hasNo(Seq(short1, short5)).query.get.map(_ ==> List(2))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.shortSet_.hasNo(Seq.empty[Short]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }

    // No filtering on optional Set attributes
  }
}