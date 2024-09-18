// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.set.types

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSet_BigDecimal_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "has" - types { implicit conn =>
        val a = (1, Set(bigDecimal1, bigDecimal2))
        val b = (2, Set(bigDecimal2, bigDecimal3, bigDecimal4))
        for {
          _ <- Ns.i.bigDecimalSet.insert(List(a, b)).transact

          // Sets with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.bigDecimalSet.has(bigDecimal0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSet.has(bigDecimal1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimalSet.has(bigDecimal2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimalSet.has(bigDecimal3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.bigDecimalSet.has(Seq(bigDecimal0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSet.has(Seq(bigDecimal1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimalSet.has(Seq(bigDecimal2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimalSet.has(Seq(bigDecimal3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.bigDecimalSet.has(bigDecimal1, bigDecimal2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimalSet.has(bigDecimal1, bigDecimal3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimalSet.has(bigDecimal2, bigDecimal3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimalSet.has(bigDecimal1, bigDecimal2, bigDecimal3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.bigDecimalSet.has(Seq(bigDecimal1, bigDecimal2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimalSet.has(Seq(bigDecimal1, bigDecimal3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimalSet.has(Seq(bigDecimal2, bigDecimal3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimalSet.has(Seq(bigDecimal1, bigDecimal2, bigDecimal3)).query.get.map(_ ==> List(a, b))

          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.bigDecimalSet.has(Seq.empty[BigDecimal]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Set(bigDecimal1, bigDecimal2))
        val b = (2, Set(bigDecimal2, bigDecimal3, bigDecimal4))
        for {
          _ <- Ns.i.bigDecimalSet.insert(List(a, b)).transact

          // Sets without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.bigDecimalSet.hasNo(bigDecimal0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimalSet.hasNo(bigDecimal1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimalSet.hasNo(bigDecimal2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSet.hasNo(bigDecimal3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimalSet.hasNo(bigDecimal4).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimalSet.hasNo(bigDecimal5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.bigDecimalSet.hasNo(Seq(bigDecimal0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimalSet.hasNo(Seq(bigDecimal1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimalSet.hasNo(Seq(bigDecimal2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSet.hasNo(Seq(bigDecimal3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimalSet.hasNo(Seq(bigDecimal4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimalSet.hasNo(Seq(bigDecimal5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.bigDecimalSet.hasNo(bigDecimal1, bigDecimal2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSet.hasNo(bigDecimal1, bigDecimal3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSet.hasNo(bigDecimal1, bigDecimal4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSet.hasNo(bigDecimal1, bigDecimal5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.bigDecimalSet.hasNo(Seq(bigDecimal1, bigDecimal2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSet.hasNo(Seq(bigDecimal1, bigDecimal3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSet.hasNo(Seq(bigDecimal1, bigDecimal4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSet.hasNo(Seq(bigDecimal1, bigDecimal5)).query.get.map(_ ==> List(b))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.bigDecimalSet.hasNo(Seq.empty[BigDecimal]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "has" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.bigDecimalSet.insert(List(
            (1, Set(bigDecimal1, bigDecimal2)),
            (2, Set(bigDecimal2, bigDecimal3, bigDecimal4))
          )).transact

          // Sets with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.bigDecimalSet_.has(bigDecimal0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSet_.has(bigDecimal1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigDecimalSet_.has(bigDecimal2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigDecimalSet_.has(bigDecimal3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.bigDecimalSet_.has(Seq(bigDecimal0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSet_.has(Seq(bigDecimal1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigDecimalSet_.has(Seq(bigDecimal2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigDecimalSet_.has(Seq(bigDecimal3)).query.get.map(_ ==> List(2))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.bigDecimalSet_.has(bigDecimal0, bigDecimal1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigDecimalSet_.has(bigDecimal1, bigDecimal2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigDecimalSet_.has(bigDecimal1, bigDecimal3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigDecimalSet_.has(bigDecimal2, bigDecimal3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigDecimalSet_.has(bigDecimal3, bigDecimal4).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.bigDecimalSet_.has(Seq(bigDecimal1, bigDecimal2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigDecimalSet_.has(Seq(bigDecimal1, bigDecimal3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigDecimalSet_.has(Seq(bigDecimal2, bigDecimal3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigDecimalSet_.has(Seq(bigDecimal1, bigDecimal2, bigDecimal3)).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.bigDecimalSet_.has(Seq.empty[BigDecimal]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.bigDecimalSet.insert(List(
            (1, Set(bigDecimal1, bigDecimal2)),
            (2, Set(bigDecimal2, bigDecimal3, bigDecimal4))
          )).transact

          // Sets without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.bigDecimalSet_.hasNo(bigDecimal0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigDecimalSet_.hasNo(bigDecimal1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigDecimalSet_.hasNo(bigDecimal2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSet_.hasNo(bigDecimal3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigDecimalSet_.hasNo(bigDecimal4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigDecimalSet_.hasNo(bigDecimal5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.bigDecimalSet_.hasNo(Seq(bigDecimal0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigDecimalSet_.hasNo(Seq(bigDecimal1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigDecimalSet_.hasNo(Seq(bigDecimal2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSet_.hasNo(Seq(bigDecimal3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigDecimalSet_.hasNo(Seq(bigDecimal4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigDecimalSet_.hasNo(Seq(bigDecimal5)).query.get.map(_ ==> List(1, 2))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.bigDecimalSet_.hasNo(bigDecimal1, bigDecimal2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSet_.hasNo(bigDecimal1, bigDecimal3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSet_.hasNo(bigDecimal1, bigDecimal4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSet_.hasNo(bigDecimal1, bigDecimal5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.bigDecimalSet_.hasNo(Seq(bigDecimal1, bigDecimal2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSet_.hasNo(Seq(bigDecimal1, bigDecimal3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSet_.hasNo(Seq(bigDecimal1, bigDecimal4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSet_.hasNo(Seq(bigDecimal1, bigDecimal5)).query.get.map(_ ==> List(2))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.bigDecimalSet_.hasNo(Seq.empty[BigDecimal]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }

    // No filtering on optional Set attributes
  }
}