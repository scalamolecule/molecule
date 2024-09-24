// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.seq.types

import java.time.OffsetTime
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSeq_OffsetTime_ extends CoreTestSuite with Api_async { spi: Spi_async =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "has" - types { implicit conn =>
        val a = (1, List(offsetTime1, offsetTime2))
        val b = (2, List(offsetTime2, offsetTime3, offsetTime3))
        for {
          _ <- Ns.i.offsetTimeSeq.insert(List(a, b)).transact

          // Seqs with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.offsetTimeSeq.has(offsetTime0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSeq.has(offsetTime1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimeSeq.has(offsetTime2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeSeq.has(offsetTime3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.offsetTimeSeq.has(List(offsetTime0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSeq.has(List(offsetTime1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimeSeq.has(List(offsetTime2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeSeq.has(List(offsetTime3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.offsetTimeSeq.has(offsetTime1, offsetTime2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeSeq.has(offsetTime1, offsetTime3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeSeq.has(offsetTime2, offsetTime3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeSeq.has(offsetTime1, offsetTime2, offsetTime3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.offsetTimeSeq.has(List(offsetTime1, offsetTime2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeSeq.has(List(offsetTime1, offsetTime3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeSeq.has(List(offsetTime2, offsetTime3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeSeq.has(List(offsetTime1, offsetTime2, offsetTime3)).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.offsetTimeSeq.has(List.empty[OffsetTime]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, List(offsetTime1, offsetTime2))
        val b = (2, List(offsetTime2, offsetTime3, offsetTime3))
        for {
          _ <- Ns.i.offsetTimeSeq.insert(List(a, b)).transact

          // Seqs without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.offsetTimeSeq.hasNo(offsetTime0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeSeq.hasNo(offsetTime1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetTimeSeq.hasNo(offsetTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSeq.hasNo(offsetTime3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimeSeq.hasNo(offsetTime3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimeSeq.hasNo(offsetTime5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.offsetTimeSeq.hasNo(List(offsetTime0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetTimeSeq.hasNo(List(offsetTime1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetTimeSeq.hasNo(List(offsetTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSeq.hasNo(List(offsetTime3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimeSeq.hasNo(List(offsetTime3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetTimeSeq.hasNo(List(offsetTime5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.offsetTimeSeq.hasNo(offsetTime1, offsetTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSeq.hasNo(offsetTime1, offsetTime3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSeq.hasNo(offsetTime1, offsetTime3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSeq.hasNo(offsetTime1, offsetTime5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.offsetTimeSeq.hasNo(List(offsetTime1, offsetTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSeq.hasNo(List(offsetTime1, offsetTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSeq.hasNo(List(offsetTime1, offsetTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSeq.hasNo(List(offsetTime1, offsetTime5)).query.get.map(_ ==> List(b))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.offsetTimeSeq.hasNo(List.empty[OffsetTime]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "has" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.offsetTimeSeq.insert(List(
            (1, List(offsetTime1, offsetTime2)),
            (2, List(offsetTime2, offsetTime3, offsetTime3))
          )).transact

          // Seqs with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.offsetTimeSeq_.has(offsetTime0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSeq_.has(offsetTime1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimeSeq_.has(offsetTime2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimeSeq_.has(offsetTime3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.offsetTimeSeq_.has(List(offsetTime0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSeq_.has(List(offsetTime1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimeSeq_.has(List(offsetTime2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimeSeq_.has(List(offsetTime3)).query.get.map(_ ==> List(2))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.offsetTimeSeq_.has(offsetTime1, offsetTime2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimeSeq_.has(offsetTime1, offsetTime3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimeSeq_.has(offsetTime2, offsetTime3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimeSeq_.has(offsetTime1, offsetTime2, offsetTime3).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.offsetTimeSeq_.has(List(offsetTime1, offsetTime2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimeSeq_.has(List(offsetTime1, offsetTime3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimeSeq_.has(List(offsetTime2, offsetTime3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimeSeq_.has(List(offsetTime1, offsetTime2, offsetTime3)).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.offsetTimeSeq_.has(List.empty[OffsetTime]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.offsetTimeSeq.insert(List(
            (1, List(offsetTime1, offsetTime2)),
            (2, List(offsetTime2, offsetTime3, offsetTime3))
          )).transact

          // Seqs without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.offsetTimeSeq_.hasNo(offsetTime0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimeSeq_.hasNo(offsetTime1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetTimeSeq_.hasNo(offsetTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSeq_.hasNo(offsetTime3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimeSeq_.hasNo(offsetTime3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimeSeq_.hasNo(offsetTime5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.offsetTimeSeq_.hasNo(List(offsetTime0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetTimeSeq_.hasNo(List(offsetTime1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetTimeSeq_.hasNo(List(offsetTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSeq_.hasNo(List(offsetTime3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimeSeq_.hasNo(List(offsetTime3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetTimeSeq_.hasNo(List(offsetTime5)).query.get.map(_ ==> List(1, 2))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.offsetTimeSeq_.hasNo(offsetTime1, offsetTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSeq_.hasNo(offsetTime1, offsetTime3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSeq_.hasNo(offsetTime1, offsetTime3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSeq_.hasNo(offsetTime1, offsetTime5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.offsetTimeSeq_.hasNo(List(offsetTime1, offsetTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSeq_.hasNo(List(offsetTime1, offsetTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSeq_.hasNo(List(offsetTime1, offsetTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetTimeSeq_.hasNo(List(offsetTime1, offsetTime5)).query.get.map(_ ==> List(2))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.offsetTimeSeq_.hasNo(List.empty[OffsetTime]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }

    // No filtering on optional Seq attributes
  }
}