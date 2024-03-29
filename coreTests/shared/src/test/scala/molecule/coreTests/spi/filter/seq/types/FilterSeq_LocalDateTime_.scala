// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.seq.types

import java.time.LocalDateTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSeq_LocalDateTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "has" - types { implicit conn =>
        val a = (1, List(localDateTime1, localDateTime2))
        val b = (2, List(localDateTime2, localDateTime3, localDateTime3))
        for {
          _ <- Ns.i.localDateTimeSeq.insert(List(a, b)).transact

          // Seqs with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.localDateTimeSeq.has(localDateTime0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSeq.has(localDateTime1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateTimeSeq.has(localDateTime2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateTimeSeq.has(localDateTime3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.localDateTimeSeq.has(List(localDateTime0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSeq.has(List(localDateTime1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateTimeSeq.has(List(localDateTime2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateTimeSeq.has(List(localDateTime3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.localDateTimeSeq.has(localDateTime1, localDateTime2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateTimeSeq.has(localDateTime1, localDateTime3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateTimeSeq.has(localDateTime2, localDateTime3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateTimeSeq.has(localDateTime1, localDateTime2, localDateTime3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.localDateTimeSeq.has(List(localDateTime1, localDateTime2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateTimeSeq.has(List(localDateTime1, localDateTime3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateTimeSeq.has(List(localDateTime2, localDateTime3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateTimeSeq.has(List(localDateTime1, localDateTime2, localDateTime3)).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.localDateTimeSeq.has(List.empty[LocalDateTime]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, List(localDateTime1, localDateTime2))
        val b = (2, List(localDateTime2, localDateTime3, localDateTime3))
        for {
          _ <- Ns.i.localDateTimeSeq.insert(List(a, b)).transact

          // Seqs without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.localDateTimeSeq.hasNo(localDateTime0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateTimeSeq.hasNo(localDateTime1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localDateTimeSeq.hasNo(localDateTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSeq.hasNo(localDateTime3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateTimeSeq.hasNo(localDateTime3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateTimeSeq.hasNo(localDateTime5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.localDateTimeSeq.hasNo(List(localDateTime0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateTimeSeq.hasNo(List(localDateTime1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localDateTimeSeq.hasNo(List(localDateTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSeq.hasNo(List(localDateTime3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateTimeSeq.hasNo(List(localDateTime3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateTimeSeq.hasNo(List(localDateTime5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.localDateTimeSeq.hasNo(localDateTime1, localDateTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSeq.hasNo(localDateTime1, localDateTime3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSeq.hasNo(localDateTime1, localDateTime3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSeq.hasNo(localDateTime1, localDateTime5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.localDateTimeSeq.hasNo(List(localDateTime1, localDateTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSeq.hasNo(List(localDateTime1, localDateTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSeq.hasNo(List(localDateTime1, localDateTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSeq.hasNo(List(localDateTime1, localDateTime5)).query.get.map(_ ==> List(b))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.localDateTimeSeq.hasNo(List.empty[LocalDateTime]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "has" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.localDateTimeSeq.insert(List(
            (1, List(localDateTime1, localDateTime2)),
            (2, List(localDateTime2, localDateTime3, localDateTime3))
          )).transact

          // Seqs with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.localDateTimeSeq_.has(localDateTime0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSeq_.has(localDateTime1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateTimeSeq_.has(localDateTime2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateTimeSeq_.has(localDateTime3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.localDateTimeSeq_.has(List(localDateTime0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSeq_.has(List(localDateTime1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateTimeSeq_.has(List(localDateTime2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateTimeSeq_.has(List(localDateTime3)).query.get.map(_ ==> List(2))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.localDateTimeSeq_.has(localDateTime1, localDateTime2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateTimeSeq_.has(localDateTime1, localDateTime3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateTimeSeq_.has(localDateTime2, localDateTime3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateTimeSeq_.has(localDateTime1, localDateTime2, localDateTime3).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.localDateTimeSeq_.has(List(localDateTime1, localDateTime2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateTimeSeq_.has(List(localDateTime1, localDateTime3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateTimeSeq_.has(List(localDateTime2, localDateTime3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateTimeSeq_.has(List(localDateTime1, localDateTime2, localDateTime3)).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.localDateTimeSeq_.has(List.empty[LocalDateTime]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.localDateTimeSeq.insert(List(
            (1, List(localDateTime1, localDateTime2)),
            (2, List(localDateTime2, localDateTime3, localDateTime3))
          )).transact

          // Seqs without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.localDateTimeSeq_.hasNo(localDateTime0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateTimeSeq_.hasNo(localDateTime1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateTimeSeq_.hasNo(localDateTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSeq_.hasNo(localDateTime3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateTimeSeq_.hasNo(localDateTime3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateTimeSeq_.hasNo(localDateTime5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.localDateTimeSeq_.hasNo(List(localDateTime0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateTimeSeq_.hasNo(List(localDateTime1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateTimeSeq_.hasNo(List(localDateTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSeq_.hasNo(List(localDateTime3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateTimeSeq_.hasNo(List(localDateTime3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateTimeSeq_.hasNo(List(localDateTime5)).query.get.map(_ ==> List(1, 2))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.localDateTimeSeq_.hasNo(localDateTime1, localDateTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSeq_.hasNo(localDateTime1, localDateTime3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSeq_.hasNo(localDateTime1, localDateTime3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSeq_.hasNo(localDateTime1, localDateTime5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.localDateTimeSeq_.hasNo(List(localDateTime1, localDateTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSeq_.hasNo(List(localDateTime1, localDateTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSeq_.hasNo(List(localDateTime1, localDateTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSeq_.hasNo(List(localDateTime1, localDateTime5)).query.get.map(_ ==> List(2))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.localDateTimeSeq_.hasNo(List.empty[LocalDateTime]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }


    "Optional" - {

      "has" - types { implicit conn =>
        val a = (1, Some(List(localDateTime1, localDateTime2)))
        val b = (2, Some(List(localDateTime2, localDateTime3, localDateTime3)))
        val c = (3, None)
        for {
          _ <- Ns.i.localDateTimeSeq_?.insert(a, b, c).transact

          // Seqs with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.localDateTimeSeq_?.has(Some(localDateTime0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSeq_?.has(Some(localDateTime1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateTimeSeq_?.has(Some(localDateTime2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateTimeSeq_?.has(Some(localDateTime3)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.localDateTimeSeq_?.has(Some(List(localDateTime0))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSeq_?.has(Some(List(localDateTime1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateTimeSeq_?.has(Some(List(localDateTime2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateTimeSeq_?.has(Some(List(localDateTime3))).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.localDateTimeSeq_?.has(Some(List(localDateTime1, localDateTime2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateTimeSeq_?.has(Some(List(localDateTime1, localDateTime3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateTimeSeq_?.has(Some(List(localDateTime2, localDateTime3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateTimeSeq_?.has(Some(List(localDateTime1, localDateTime2, localDateTime3))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.localDateTimeSeq_?.has(Some(List.empty[LocalDateTime])).query.get.map(_ ==> List())

          // None matches non-asserted values
          _ <- Ns.i.a1.localDateTimeSeq_?.has(Option.empty[LocalDateTime]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.localDateTimeSeq_?.has(Option.empty[List[LocalDateTime]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Some(List(localDateTime1, localDateTime2)))
        val b = (2, Some(List(localDateTime2, localDateTime3, localDateTime3)))
        val c = (3, None)
        for {
          _ <- Ns.i.localDateTimeSeq_?.insert(a, b, c).transact

          // Seqs without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.localDateTimeSeq_?.hasNo(Some(localDateTime0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateTimeSeq_?.hasNo(Some(localDateTime1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localDateTimeSeq_?.hasNo(Some(localDateTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSeq_?.hasNo(Some(localDateTime3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateTimeSeq_?.hasNo(Some(localDateTime3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateTimeSeq_?.hasNo(Some(localDateTime5)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.localDateTimeSeq_?.hasNo(Some(List(localDateTime0))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateTimeSeq_?.hasNo(Some(List(localDateTime1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localDateTimeSeq_?.hasNo(Some(List(localDateTime2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSeq_?.hasNo(Some(List(localDateTime3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateTimeSeq_?.hasNo(Some(List(localDateTime3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateTimeSeq_?.hasNo(Some(List(localDateTime5))).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.localDateTimeSeq_?.hasNo(Some(List(localDateTime1, localDateTime2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSeq_?.hasNo(Some(List(localDateTime1, localDateTime3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSeq_?.hasNo(Some(List(localDateTime1, localDateTime3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateTimeSeq_?.hasNo(Some(List(localDateTime1, localDateTime5))).query.get.map(_ ==> List(b))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.localDateTimeSeq_?.hasNo(Some(List.empty[LocalDateTime])).query.get.map(_ ==> List(a, b))

          // Negating None returns all asserted
          _ <- Ns.i.a1.localDateTimeSeq_?.hasNo(Option.empty[LocalDateTime]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateTimeSeq_?.hasNo(Option.empty[List[LocalDateTime]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}