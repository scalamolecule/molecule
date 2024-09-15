// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.seq.types

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSeq_BigDecimal_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "has" - types { implicit conn =>
        val a = (1, List(bigDecimal1, bigDecimal2))
        val b = (2, List(bigDecimal2, bigDecimal3, bigDecimal3))
        for {
          _ <- Ns.i.bigDecimalSeq.insert(List(a, b)).transact

          // Seqs with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.bigDecimalSeq.has(bigDecimal0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSeq.has(bigDecimal1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimalSeq.has(bigDecimal2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimalSeq.has(bigDecimal3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.bigDecimalSeq.has(List(bigDecimal0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSeq.has(List(bigDecimal1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimalSeq.has(List(bigDecimal2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimalSeq.has(List(bigDecimal3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.bigDecimalSeq.has(bigDecimal1, bigDecimal2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimalSeq.has(bigDecimal1, bigDecimal3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimalSeq.has(bigDecimal2, bigDecimal3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimalSeq.has(bigDecimal1, bigDecimal2, bigDecimal3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.bigDecimalSeq.has(List(bigDecimal1, bigDecimal2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimalSeq.has(List(bigDecimal1, bigDecimal3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimalSeq.has(List(bigDecimal2, bigDecimal3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimalSeq.has(List(bigDecimal1, bigDecimal2, bigDecimal3)).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.bigDecimalSeq.has(List.empty[BigDecimal]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, List(bigDecimal1, bigDecimal2))
        val b = (2, List(bigDecimal2, bigDecimal3, bigDecimal3))
        for {
          _ <- Ns.i.bigDecimalSeq.insert(List(a, b)).transact

          // Seqs without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.bigDecimalSeq.hasNo(bigDecimal0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimalSeq.hasNo(bigDecimal1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimalSeq.hasNo(bigDecimal2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSeq.hasNo(bigDecimal3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimalSeq.hasNo(bigDecimal3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimalSeq.hasNo(bigDecimal5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.bigDecimalSeq.hasNo(List(bigDecimal0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigDecimalSeq.hasNo(List(bigDecimal1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigDecimalSeq.hasNo(List(bigDecimal2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSeq.hasNo(List(bigDecimal3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimalSeq.hasNo(List(bigDecimal3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigDecimalSeq.hasNo(List(bigDecimal5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.bigDecimalSeq.hasNo(bigDecimal1, bigDecimal2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSeq.hasNo(bigDecimal1, bigDecimal3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSeq.hasNo(bigDecimal1, bigDecimal3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSeq.hasNo(bigDecimal1, bigDecimal5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.bigDecimalSeq.hasNo(List(bigDecimal1, bigDecimal2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSeq.hasNo(List(bigDecimal1, bigDecimal3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSeq.hasNo(List(bigDecimal1, bigDecimal3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSeq.hasNo(List(bigDecimal1, bigDecimal5)).query.get.map(_ ==> List(b))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.bigDecimalSeq.hasNo(List.empty[BigDecimal]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "has" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.bigDecimalSeq.insert(List(
            (1, List(bigDecimal1, bigDecimal2)),
            (2, List(bigDecimal2, bigDecimal3, bigDecimal3))
          )).transact

          // Seqs with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.bigDecimalSeq_.has(bigDecimal0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSeq_.has(bigDecimal1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigDecimalSeq_.has(bigDecimal2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigDecimalSeq_.has(bigDecimal3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.bigDecimalSeq_.has(List(bigDecimal0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSeq_.has(List(bigDecimal1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigDecimalSeq_.has(List(bigDecimal2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigDecimalSeq_.has(List(bigDecimal3)).query.get.map(_ ==> List(2))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.bigDecimalSeq_.has(bigDecimal1, bigDecimal2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigDecimalSeq_.has(bigDecimal1, bigDecimal3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigDecimalSeq_.has(bigDecimal2, bigDecimal3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigDecimalSeq_.has(bigDecimal1, bigDecimal2, bigDecimal3).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.bigDecimalSeq_.has(List(bigDecimal1, bigDecimal2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigDecimalSeq_.has(List(bigDecimal1, bigDecimal3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigDecimalSeq_.has(List(bigDecimal2, bigDecimal3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigDecimalSeq_.has(List(bigDecimal1, bigDecimal2, bigDecimal3)).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.bigDecimalSeq_.has(List.empty[BigDecimal]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.bigDecimalSeq.insert(List(
            (1, List(bigDecimal1, bigDecimal2)),
            (2, List(bigDecimal2, bigDecimal3, bigDecimal3))
          )).transact

          // Seqs without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.bigDecimalSeq_.hasNo(bigDecimal0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigDecimalSeq_.hasNo(bigDecimal1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigDecimalSeq_.hasNo(bigDecimal2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSeq_.hasNo(bigDecimal3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigDecimalSeq_.hasNo(bigDecimal3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigDecimalSeq_.hasNo(bigDecimal5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.bigDecimalSeq_.hasNo(List(bigDecimal0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigDecimalSeq_.hasNo(List(bigDecimal1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigDecimalSeq_.hasNo(List(bigDecimal2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSeq_.hasNo(List(bigDecimal3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigDecimalSeq_.hasNo(List(bigDecimal3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigDecimalSeq_.hasNo(List(bigDecimal5)).query.get.map(_ ==> List(1, 2))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.bigDecimalSeq_.hasNo(bigDecimal1, bigDecimal2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSeq_.hasNo(bigDecimal1, bigDecimal3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSeq_.hasNo(bigDecimal1, bigDecimal3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSeq_.hasNo(bigDecimal1, bigDecimal5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.bigDecimalSeq_.hasNo(List(bigDecimal1, bigDecimal2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSeq_.hasNo(List(bigDecimal1, bigDecimal3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSeq_.hasNo(List(bigDecimal1, bigDecimal3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigDecimalSeq_.hasNo(List(bigDecimal1, bigDecimal5)).query.get.map(_ ==> List(2))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.bigDecimalSeq_.hasNo(List.empty[BigDecimal]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }

    // No filtering on optional Seq attributes
  }
}