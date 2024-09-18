// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.seq.types

import java.time.LocalDate
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSeq_LocalDate_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "has" - types { implicit conn =>
        val a = (1, List(localDate1, localDate2))
        val b = (2, List(localDate2, localDate3, localDate3))
        for {
          _ <- Ns.i.localDateSeq.insert(List(a, b)).transact

          // Seqs with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.localDateSeq.has(localDate0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSeq.has(localDate1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateSeq.has(localDate2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateSeq.has(localDate3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.localDateSeq.has(List(localDate0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSeq.has(List(localDate1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateSeq.has(List(localDate2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateSeq.has(List(localDate3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.localDateSeq.has(localDate1, localDate2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateSeq.has(localDate1, localDate3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateSeq.has(localDate2, localDate3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateSeq.has(localDate1, localDate2, localDate3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.localDateSeq.has(List(localDate1, localDate2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateSeq.has(List(localDate1, localDate3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateSeq.has(List(localDate2, localDate3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateSeq.has(List(localDate1, localDate2, localDate3)).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.localDateSeq.has(List.empty[LocalDate]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, List(localDate1, localDate2))
        val b = (2, List(localDate2, localDate3, localDate3))
        for {
          _ <- Ns.i.localDateSeq.insert(List(a, b)).transact

          // Seqs without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.localDateSeq.hasNo(localDate0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateSeq.hasNo(localDate1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localDateSeq.hasNo(localDate2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSeq.hasNo(localDate3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateSeq.hasNo(localDate3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateSeq.hasNo(localDate5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.localDateSeq.hasNo(List(localDate0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateSeq.hasNo(List(localDate1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localDateSeq.hasNo(List(localDate2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSeq.hasNo(List(localDate3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateSeq.hasNo(List(localDate3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateSeq.hasNo(List(localDate5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.localDateSeq.hasNo(localDate1, localDate2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSeq.hasNo(localDate1, localDate3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSeq.hasNo(localDate1, localDate3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSeq.hasNo(localDate1, localDate5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.localDateSeq.hasNo(List(localDate1, localDate2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSeq.hasNo(List(localDate1, localDate3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSeq.hasNo(List(localDate1, localDate3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSeq.hasNo(List(localDate1, localDate5)).query.get.map(_ ==> List(b))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.localDateSeq.hasNo(List.empty[LocalDate]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "has" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.localDateSeq.insert(List(
            (1, List(localDate1, localDate2)),
            (2, List(localDate2, localDate3, localDate3))
          )).transact

          // Seqs with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.localDateSeq_.has(localDate0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSeq_.has(localDate1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateSeq_.has(localDate2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateSeq_.has(localDate3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.localDateSeq_.has(List(localDate0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSeq_.has(List(localDate1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateSeq_.has(List(localDate2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateSeq_.has(List(localDate3)).query.get.map(_ ==> List(2))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.localDateSeq_.has(localDate1, localDate2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateSeq_.has(localDate1, localDate3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateSeq_.has(localDate2, localDate3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateSeq_.has(localDate1, localDate2, localDate3).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.localDateSeq_.has(List(localDate1, localDate2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateSeq_.has(List(localDate1, localDate3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateSeq_.has(List(localDate2, localDate3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateSeq_.has(List(localDate1, localDate2, localDate3)).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.localDateSeq_.has(List.empty[LocalDate]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.localDateSeq.insert(List(
            (1, List(localDate1, localDate2)),
            (2, List(localDate2, localDate3, localDate3))
          )).transact

          // Seqs without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.localDateSeq_.hasNo(localDate0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateSeq_.hasNo(localDate1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateSeq_.hasNo(localDate2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSeq_.hasNo(localDate3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateSeq_.hasNo(localDate3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateSeq_.hasNo(localDate5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.localDateSeq_.hasNo(List(localDate0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateSeq_.hasNo(List(localDate1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateSeq_.hasNo(List(localDate2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSeq_.hasNo(List(localDate3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateSeq_.hasNo(List(localDate3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateSeq_.hasNo(List(localDate5)).query.get.map(_ ==> List(1, 2))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.localDateSeq_.hasNo(localDate1, localDate2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSeq_.hasNo(localDate1, localDate3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSeq_.hasNo(localDate1, localDate3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSeq_.hasNo(localDate1, localDate5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.localDateSeq_.hasNo(List(localDate1, localDate2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSeq_.hasNo(List(localDate1, localDate3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSeq_.hasNo(List(localDate1, localDate3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSeq_.hasNo(List(localDate1, localDate5)).query.get.map(_ ==> List(2))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.localDateSeq_.hasNo(List.empty[LocalDate]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }

    // No filtering on optional Seq attributes
  }
}