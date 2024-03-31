// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.seq.types

import java.time.LocalTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSeq_LocalTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "has" - types { implicit conn =>
        val a = (1, List(localTime1, localTime2))
        val b = (2, List(localTime2, localTime3, localTime3))
        for {
          _ <- Ns.i.localTimeSeq.insert(List(a, b)).transact

          // Seqs with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.localTimeSeq.has(localTime0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSeq.has(localTime1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimeSeq.has(localTime2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimeSeq.has(localTime3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.localTimeSeq.has(List(localTime0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSeq.has(List(localTime1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimeSeq.has(List(localTime2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimeSeq.has(List(localTime3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.localTimeSeq.has(localTime1, localTime2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimeSeq.has(localTime1, localTime3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimeSeq.has(localTime2, localTime3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimeSeq.has(localTime1, localTime2, localTime3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.localTimeSeq.has(List(localTime1, localTime2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimeSeq.has(List(localTime1, localTime3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimeSeq.has(List(localTime2, localTime3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimeSeq.has(List(localTime1, localTime2, localTime3)).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.localTimeSeq.has(List.empty[LocalTime]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, List(localTime1, localTime2))
        val b = (2, List(localTime2, localTime3, localTime3))
        for {
          _ <- Ns.i.localTimeSeq.insert(List(a, b)).transact

          // Seqs without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.localTimeSeq.hasNo(localTime0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimeSeq.hasNo(localTime1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localTimeSeq.hasNo(localTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSeq.hasNo(localTime3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimeSeq.hasNo(localTime3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimeSeq.hasNo(localTime5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.localTimeSeq.hasNo(List(localTime0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localTimeSeq.hasNo(List(localTime1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localTimeSeq.hasNo(List(localTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSeq.hasNo(List(localTime3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimeSeq.hasNo(List(localTime3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localTimeSeq.hasNo(List(localTime5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.localTimeSeq.hasNo(localTime1, localTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSeq.hasNo(localTime1, localTime3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSeq.hasNo(localTime1, localTime3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSeq.hasNo(localTime1, localTime5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.localTimeSeq.hasNo(List(localTime1, localTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSeq.hasNo(List(localTime1, localTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSeq.hasNo(List(localTime1, localTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSeq.hasNo(List(localTime1, localTime5)).query.get.map(_ ==> List(b))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.localTimeSeq.hasNo(List.empty[LocalTime]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "has" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.localTimeSeq.insert(List(
            (1, List(localTime1, localTime2)),
            (2, List(localTime2, localTime3, localTime3))
          )).transact

          // Seqs with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.localTimeSeq_.has(localTime0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSeq_.has(localTime1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimeSeq_.has(localTime2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimeSeq_.has(localTime3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.localTimeSeq_.has(List(localTime0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSeq_.has(List(localTime1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimeSeq_.has(List(localTime2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimeSeq_.has(List(localTime3)).query.get.map(_ ==> List(2))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.localTimeSeq_.has(localTime1, localTime2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimeSeq_.has(localTime1, localTime3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimeSeq_.has(localTime2, localTime3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimeSeq_.has(localTime1, localTime2, localTime3).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.localTimeSeq_.has(List(localTime1, localTime2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimeSeq_.has(List(localTime1, localTime3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimeSeq_.has(List(localTime2, localTime3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimeSeq_.has(List(localTime1, localTime2, localTime3)).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.localTimeSeq_.has(List.empty[LocalTime]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.localTimeSeq.insert(List(
            (1, List(localTime1, localTime2)),
            (2, List(localTime2, localTime3, localTime3))
          )).transact

          // Seqs without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.localTimeSeq_.hasNo(localTime0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimeSeq_.hasNo(localTime1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localTimeSeq_.hasNo(localTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSeq_.hasNo(localTime3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimeSeq_.hasNo(localTime3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimeSeq_.hasNo(localTime5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.localTimeSeq_.hasNo(List(localTime0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localTimeSeq_.hasNo(List(localTime1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localTimeSeq_.hasNo(List(localTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSeq_.hasNo(List(localTime3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimeSeq_.hasNo(List(localTime3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localTimeSeq_.hasNo(List(localTime5)).query.get.map(_ ==> List(1, 2))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.localTimeSeq_.hasNo(localTime1, localTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSeq_.hasNo(localTime1, localTime3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSeq_.hasNo(localTime1, localTime3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSeq_.hasNo(localTime1, localTime5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.localTimeSeq_.hasNo(List(localTime1, localTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSeq_.hasNo(List(localTime1, localTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSeq_.hasNo(List(localTime1, localTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localTimeSeq_.hasNo(List(localTime1, localTime5)).query.get.map(_ ==> List(2))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.localTimeSeq_.hasNo(List.empty[LocalTime]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }

    // No filtering on optional Seq attributes
  }
}