// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.seq.types

import java.time.OffsetDateTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSeq_OffsetDateTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "has" - types { implicit conn =>
        val a = (1, List(offsetDateTime1, offsetDateTime2))
        val b = (2, List(offsetDateTime2, offsetDateTime3, offsetDateTime3))
        for {
          _ <- Ns.i.offsetDateTimeSeq.insert(List(a, b)).transact

          // Seqs with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.offsetDateTimeSeq.has(offsetDateTime0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSeq.has(offsetDateTime1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetDateTimeSeq.has(offsetDateTime2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSeq.has(offsetDateTime3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.offsetDateTimeSeq.has(List(offsetDateTime0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSeq.has(List(offsetDateTime1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetDateTimeSeq.has(List(offsetDateTime2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSeq.has(List(offsetDateTime3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.offsetDateTimeSeq.has(offsetDateTime1, offsetDateTime2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSeq.has(offsetDateTime1, offsetDateTime3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSeq.has(offsetDateTime2, offsetDateTime3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSeq.has(offsetDateTime1, offsetDateTime2, offsetDateTime3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.offsetDateTimeSeq.has(List(offsetDateTime1, offsetDateTime2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSeq.has(List(offsetDateTime1, offsetDateTime3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSeq.has(List(offsetDateTime2, offsetDateTime3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSeq.has(List(offsetDateTime1, offsetDateTime2, offsetDateTime3)).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.offsetDateTimeSeq.has(List.empty[OffsetDateTime]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, List(offsetDateTime1, offsetDateTime2))
        val b = (2, List(offsetDateTime2, offsetDateTime3, offsetDateTime3))
        for {
          _ <- Ns.i.offsetDateTimeSeq.insert(List(a, b)).transact

          // Seqs without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.offsetDateTimeSeq.hasNo(offsetDateTime0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSeq.hasNo(offsetDateTime1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetDateTimeSeq.hasNo(offsetDateTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSeq.hasNo(offsetDateTime3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetDateTimeSeq.hasNo(offsetDateTime3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetDateTimeSeq.hasNo(offsetDateTime5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.offsetDateTimeSeq.hasNo(List(offsetDateTime0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.offsetDateTimeSeq.hasNo(List(offsetDateTime1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.offsetDateTimeSeq.hasNo(List(offsetDateTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSeq.hasNo(List(offsetDateTime3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetDateTimeSeq.hasNo(List(offsetDateTime3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.offsetDateTimeSeq.hasNo(List(offsetDateTime5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.offsetDateTimeSeq.hasNo(offsetDateTime1, offsetDateTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSeq.hasNo(offsetDateTime1, offsetDateTime3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSeq.hasNo(offsetDateTime1, offsetDateTime3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSeq.hasNo(offsetDateTime1, offsetDateTime5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.offsetDateTimeSeq.hasNo(List(offsetDateTime1, offsetDateTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSeq.hasNo(List(offsetDateTime1, offsetDateTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSeq.hasNo(List(offsetDateTime1, offsetDateTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSeq.hasNo(List(offsetDateTime1, offsetDateTime5)).query.get.map(_ ==> List(b))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.offsetDateTimeSeq.hasNo(List.empty[OffsetDateTime]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "has" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.offsetDateTimeSeq.insert(List(
            (1, List(offsetDateTime1, offsetDateTime2)),
            (2, List(offsetDateTime2, offsetDateTime3, offsetDateTime3))
          )).transact

          // Seqs with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.offsetDateTimeSeq_.has(offsetDateTime0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSeq_.has(offsetDateTime1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetDateTimeSeq_.has(offsetDateTime2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetDateTimeSeq_.has(offsetDateTime3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.offsetDateTimeSeq_.has(List(offsetDateTime0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSeq_.has(List(offsetDateTime1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetDateTimeSeq_.has(List(offsetDateTime2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetDateTimeSeq_.has(List(offsetDateTime3)).query.get.map(_ ==> List(2))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.offsetDateTimeSeq_.has(offsetDateTime1, offsetDateTime2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetDateTimeSeq_.has(offsetDateTime1, offsetDateTime3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetDateTimeSeq_.has(offsetDateTime2, offsetDateTime3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetDateTimeSeq_.has(offsetDateTime1, offsetDateTime2, offsetDateTime3).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.offsetDateTimeSeq_.has(List(offsetDateTime1, offsetDateTime2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetDateTimeSeq_.has(List(offsetDateTime1, offsetDateTime3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetDateTimeSeq_.has(List(offsetDateTime2, offsetDateTime3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetDateTimeSeq_.has(List(offsetDateTime1, offsetDateTime2, offsetDateTime3)).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.offsetDateTimeSeq_.has(List.empty[OffsetDateTime]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.offsetDateTimeSeq.insert(List(
            (1, List(offsetDateTime1, offsetDateTime2)),
            (2, List(offsetDateTime2, offsetDateTime3, offsetDateTime3))
          )).transact

          // Seqs without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.offsetDateTimeSeq_.hasNo(offsetDateTime0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetDateTimeSeq_.hasNo(offsetDateTime1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetDateTimeSeq_.hasNo(offsetDateTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSeq_.hasNo(offsetDateTime3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetDateTimeSeq_.hasNo(offsetDateTime3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetDateTimeSeq_.hasNo(offsetDateTime5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.offsetDateTimeSeq_.hasNo(List(offsetDateTime0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.offsetDateTimeSeq_.hasNo(List(offsetDateTime1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.offsetDateTimeSeq_.hasNo(List(offsetDateTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSeq_.hasNo(List(offsetDateTime3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetDateTimeSeq_.hasNo(List(offsetDateTime3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.offsetDateTimeSeq_.hasNo(List(offsetDateTime5)).query.get.map(_ ==> List(1, 2))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.offsetDateTimeSeq_.hasNo(offsetDateTime1, offsetDateTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSeq_.hasNo(offsetDateTime1, offsetDateTime3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSeq_.hasNo(offsetDateTime1, offsetDateTime3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSeq_.hasNo(offsetDateTime1, offsetDateTime5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.offsetDateTimeSeq_.hasNo(List(offsetDateTime1, offsetDateTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSeq_.hasNo(List(offsetDateTime1, offsetDateTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSeq_.hasNo(List(offsetDateTime1, offsetDateTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.offsetDateTimeSeq_.hasNo(List(offsetDateTime1, offsetDateTime5)).query.get.map(_ ==> List(2))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.offsetDateTimeSeq_.hasNo(List.empty[OffsetDateTime]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }

    // No filtering on optional Seq attributes
  }
}