// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.seq.types

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSeq_Long_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "has" - types { implicit conn =>
        val a = (1, List(long1, long2))
        val b = (2, List(long2, long3, long3))
        for {
          _ <- Ns.i.longSeq.insert(List(a, b)).transact

          // Seqs with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.longSeq.has(long0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longSeq.has(long1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longSeq.has(long2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longSeq.has(long3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.longSeq.has(List(long0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longSeq.has(List(long1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longSeq.has(List(long2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longSeq.has(List(long3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.longSeq.has(long1, long2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longSeq.has(long1, long3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longSeq.has(long2, long3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longSeq.has(long1, long2, long3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.longSeq.has(List(long1, long2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longSeq.has(List(long1, long3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longSeq.has(List(long2, long3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longSeq.has(List(long1, long2, long3)).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.longSeq.has(List.empty[Long]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, List(long1, long2))
        val b = (2, List(long2, long3, long3))
        for {
          _ <- Ns.i.longSeq.insert(List(a, b)).transact

          // Seqs without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.longSeq.hasNo(long0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longSeq.hasNo(long1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.longSeq.hasNo(long2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longSeq.hasNo(long3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longSeq.hasNo(long3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longSeq.hasNo(long5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.longSeq.hasNo(List(long0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longSeq.hasNo(List(long1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.longSeq.hasNo(List(long2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longSeq.hasNo(List(long3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longSeq.hasNo(List(long3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longSeq.hasNo(List(long5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.longSeq.hasNo(long1, long2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longSeq.hasNo(long1, long3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longSeq.hasNo(long1, long3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longSeq.hasNo(long1, long5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.longSeq.hasNo(List(long1, long2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longSeq.hasNo(List(long1, long3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longSeq.hasNo(List(long1, long3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longSeq.hasNo(List(long1, long5)).query.get.map(_ ==> List(b))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.longSeq.hasNo(List.empty[Long]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "has" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.longSeq.insert(List(
            (1, List(long1, long2)),
            (2, List(long2, long3, long3))
          )).transact

          // Seqs with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.longSeq_.has(long0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longSeq_.has(long1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.longSeq_.has(long2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.longSeq_.has(long3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.longSeq_.has(List(long0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longSeq_.has(List(long1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.longSeq_.has(List(long2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.longSeq_.has(List(long3)).query.get.map(_ ==> List(2))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.longSeq_.has(long1, long2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.longSeq_.has(long1, long3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.longSeq_.has(long2, long3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.longSeq_.has(long1, long2, long3).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.longSeq_.has(List(long1, long2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.longSeq_.has(List(long1, long3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.longSeq_.has(List(long2, long3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.longSeq_.has(List(long1, long2, long3)).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.longSeq_.has(List.empty[Long]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.longSeq.insert(List(
            (1, List(long1, long2)),
            (2, List(long2, long3, long3))
          )).transact

          // Seqs without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.longSeq_.hasNo(long0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.longSeq_.hasNo(long1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.longSeq_.hasNo(long2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longSeq_.hasNo(long3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.longSeq_.hasNo(long3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.longSeq_.hasNo(long5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.longSeq_.hasNo(List(long0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.longSeq_.hasNo(List(long1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.longSeq_.hasNo(List(long2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longSeq_.hasNo(List(long3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.longSeq_.hasNo(List(long3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.longSeq_.hasNo(List(long5)).query.get.map(_ ==> List(1, 2))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.longSeq_.hasNo(long1, long2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longSeq_.hasNo(long1, long3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longSeq_.hasNo(long1, long3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longSeq_.hasNo(long1, long5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.longSeq_.hasNo(List(long1, long2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longSeq_.hasNo(List(long1, long3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longSeq_.hasNo(List(long1, long3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longSeq_.hasNo(List(long1, long5)).query.get.map(_ ==> List(2))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.longSeq_.hasNo(List.empty[Long]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }

    // No filtering on optional Seq attributes
  }
}