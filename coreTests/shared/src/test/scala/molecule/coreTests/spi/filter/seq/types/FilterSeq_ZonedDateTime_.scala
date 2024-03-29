// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.seq.types

import java.time.ZonedDateTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSeq_ZonedDateTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "has" - types { implicit conn =>
        val a = (1, List(zonedDateTime1, zonedDateTime2))
        val b = (2, List(zonedDateTime2, zonedDateTime3, zonedDateTime3))
        for {
          _ <- Ns.i.zonedDateTimeSeq.insert(List(a, b)).transact

          // Seqs with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.zonedDateTimeSeq.has(zonedDateTime0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq.has(zonedDateTime1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimeSeq.has(zonedDateTime2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimeSeq.has(zonedDateTime3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.zonedDateTimeSeq.has(List(zonedDateTime0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq.has(List(zonedDateTime1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimeSeq.has(List(zonedDateTime2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimeSeq.has(List(zonedDateTime3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.zonedDateTimeSeq.has(zonedDateTime1, zonedDateTime2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimeSeq.has(zonedDateTime1, zonedDateTime3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimeSeq.has(zonedDateTime2, zonedDateTime3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimeSeq.has(zonedDateTime1, zonedDateTime2, zonedDateTime3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.zonedDateTimeSeq.has(List(zonedDateTime1, zonedDateTime2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimeSeq.has(List(zonedDateTime1, zonedDateTime3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimeSeq.has(List(zonedDateTime2, zonedDateTime3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimeSeq.has(List(zonedDateTime1, zonedDateTime2, zonedDateTime3)).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.zonedDateTimeSeq.has(List.empty[ZonedDateTime]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, List(zonedDateTime1, zonedDateTime2))
        val b = (2, List(zonedDateTime2, zonedDateTime3, zonedDateTime3))
        for {
          _ <- Ns.i.zonedDateTimeSeq.insert(List(a, b)).transact

          // Seqs without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.zonedDateTimeSeq.hasNo(zonedDateTime0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimeSeq.hasNo(zonedDateTime1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.zonedDateTimeSeq.hasNo(zonedDateTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq.hasNo(zonedDateTime3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimeSeq.hasNo(zonedDateTime3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimeSeq.hasNo(zonedDateTime5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.zonedDateTimeSeq.hasNo(List(zonedDateTime0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimeSeq.hasNo(List(zonedDateTime1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.zonedDateTimeSeq.hasNo(List(zonedDateTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq.hasNo(List(zonedDateTime3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimeSeq.hasNo(List(zonedDateTime3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimeSeq.hasNo(List(zonedDateTime5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.zonedDateTimeSeq.hasNo(zonedDateTime1, zonedDateTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq.hasNo(zonedDateTime1, zonedDateTime3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq.hasNo(zonedDateTime1, zonedDateTime3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq.hasNo(zonedDateTime1, zonedDateTime5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.zonedDateTimeSeq.hasNo(List(zonedDateTime1, zonedDateTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq.hasNo(List(zonedDateTime1, zonedDateTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq.hasNo(List(zonedDateTime1, zonedDateTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq.hasNo(List(zonedDateTime1, zonedDateTime5)).query.get.map(_ ==> List(b))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.zonedDateTimeSeq.hasNo(List.empty[ZonedDateTime]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "has" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.zonedDateTimeSeq.insert(List(
            (1, List(zonedDateTime1, zonedDateTime2)),
            (2, List(zonedDateTime2, zonedDateTime3, zonedDateTime3))
          )).transact

          // Seqs with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.zonedDateTimeSeq_.has(zonedDateTime0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq_.has(zonedDateTime1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimeSeq_.has(zonedDateTime2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimeSeq_.has(zonedDateTime3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.zonedDateTimeSeq_.has(List(zonedDateTime0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq_.has(List(zonedDateTime1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimeSeq_.has(List(zonedDateTime2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimeSeq_.has(List(zonedDateTime3)).query.get.map(_ ==> List(2))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.zonedDateTimeSeq_.has(zonedDateTime1, zonedDateTime2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimeSeq_.has(zonedDateTime1, zonedDateTime3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimeSeq_.has(zonedDateTime2, zonedDateTime3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimeSeq_.has(zonedDateTime1, zonedDateTime2, zonedDateTime3).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.zonedDateTimeSeq_.has(List(zonedDateTime1, zonedDateTime2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimeSeq_.has(List(zonedDateTime1, zonedDateTime3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimeSeq_.has(List(zonedDateTime2, zonedDateTime3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimeSeq_.has(List(zonedDateTime1, zonedDateTime2, zonedDateTime3)).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.zonedDateTimeSeq_.has(List.empty[ZonedDateTime]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.zonedDateTimeSeq.insert(List(
            (1, List(zonedDateTime1, zonedDateTime2)),
            (2, List(zonedDateTime2, zonedDateTime3, zonedDateTime3))
          )).transact

          // Seqs without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.zonedDateTimeSeq_.hasNo(zonedDateTime0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimeSeq_.hasNo(zonedDateTime1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.zonedDateTimeSeq_.hasNo(zonedDateTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq_.hasNo(zonedDateTime3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimeSeq_.hasNo(zonedDateTime3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimeSeq_.hasNo(zonedDateTime5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.zonedDateTimeSeq_.hasNo(List(zonedDateTime0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimeSeq_.hasNo(List(zonedDateTime1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.zonedDateTimeSeq_.hasNo(List(zonedDateTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq_.hasNo(List(zonedDateTime3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimeSeq_.hasNo(List(zonedDateTime3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimeSeq_.hasNo(List(zonedDateTime5)).query.get.map(_ ==> List(1, 2))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.zonedDateTimeSeq_.hasNo(zonedDateTime1, zonedDateTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq_.hasNo(zonedDateTime1, zonedDateTime3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq_.hasNo(zonedDateTime1, zonedDateTime3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq_.hasNo(zonedDateTime1, zonedDateTime5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.zonedDateTimeSeq_.hasNo(List(zonedDateTime1, zonedDateTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq_.hasNo(List(zonedDateTime1, zonedDateTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq_.hasNo(List(zonedDateTime1, zonedDateTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq_.hasNo(List(zonedDateTime1, zonedDateTime5)).query.get.map(_ ==> List(2))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.zonedDateTimeSeq_.hasNo(List.empty[ZonedDateTime]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }


    "Optional" - {

      "has" - types { implicit conn =>
        val a = (1, Some(List(zonedDateTime1, zonedDateTime2)))
        val b = (2, Some(List(zonedDateTime2, zonedDateTime3, zonedDateTime3)))
        val c = (3, None)
        for {
          _ <- Ns.i.zonedDateTimeSeq_?.insert(a, b, c).transact

          // Seqs with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.zonedDateTimeSeq_?.has(Some(zonedDateTime0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq_?.has(Some(zonedDateTime1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimeSeq_?.has(Some(zonedDateTime2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimeSeq_?.has(Some(zonedDateTime3)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.zonedDateTimeSeq_?.has(Some(List(zonedDateTime0))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq_?.has(Some(List(zonedDateTime1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimeSeq_?.has(Some(List(zonedDateTime2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimeSeq_?.has(Some(List(zonedDateTime3))).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.zonedDateTimeSeq_?.has(Some(List(zonedDateTime1, zonedDateTime2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimeSeq_?.has(Some(List(zonedDateTime1, zonedDateTime3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimeSeq_?.has(Some(List(zonedDateTime2, zonedDateTime3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimeSeq_?.has(Some(List(zonedDateTime1, zonedDateTime2, zonedDateTime3))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.zonedDateTimeSeq_?.has(Some(List.empty[ZonedDateTime])).query.get.map(_ ==> List())

          // None matches non-asserted values
          _ <- Ns.i.a1.zonedDateTimeSeq_?.has(Option.empty[ZonedDateTime]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.zonedDateTimeSeq_?.has(Option.empty[List[ZonedDateTime]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Some(List(zonedDateTime1, zonedDateTime2)))
        val b = (2, Some(List(zonedDateTime2, zonedDateTime3, zonedDateTime3)))
        val c = (3, None)
        for {
          _ <- Ns.i.zonedDateTimeSeq_?.insert(a, b, c).transact

          // Seqs without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.zonedDateTimeSeq_?.hasNo(Some(zonedDateTime0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimeSeq_?.hasNo(Some(zonedDateTime1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.zonedDateTimeSeq_?.hasNo(Some(zonedDateTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq_?.hasNo(Some(zonedDateTime3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimeSeq_?.hasNo(Some(zonedDateTime3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimeSeq_?.hasNo(Some(zonedDateTime5)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.zonedDateTimeSeq_?.hasNo(Some(List(zonedDateTime0))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimeSeq_?.hasNo(Some(List(zonedDateTime1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.zonedDateTimeSeq_?.hasNo(Some(List(zonedDateTime2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq_?.hasNo(Some(List(zonedDateTime3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimeSeq_?.hasNo(Some(List(zonedDateTime3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimeSeq_?.hasNo(Some(List(zonedDateTime5))).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.zonedDateTimeSeq_?.hasNo(Some(List(zonedDateTime1, zonedDateTime2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq_?.hasNo(Some(List(zonedDateTime1, zonedDateTime3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq_?.hasNo(Some(List(zonedDateTime1, zonedDateTime3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimeSeq_?.hasNo(Some(List(zonedDateTime1, zonedDateTime5))).query.get.map(_ ==> List(b))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.zonedDateTimeSeq_?.hasNo(Some(List.empty[ZonedDateTime])).query.get.map(_ ==> List(a, b))

          // Negating None returns all asserted
          _ <- Ns.i.a1.zonedDateTimeSeq_?.hasNo(Option.empty[ZonedDateTime]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimeSeq_?.hasNo(Option.empty[List[ZonedDateTime]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}