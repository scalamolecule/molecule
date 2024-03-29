// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.seq.types

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSeq_Float_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "has" - types { implicit conn =>
        val a = (1, List(float1, float2))
        val b = (2, List(float2, float3, float3))
        for {
          _ <- Ns.i.floatSeq.insert(List(a, b)).transact

          // Seqs with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.floatSeq.has(float0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq.has(float1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floatSeq.has(float2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSeq.has(float3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.floatSeq.has(List(float0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq.has(List(float1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floatSeq.has(List(float2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSeq.has(List(float3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.floatSeq.has(float1, float2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSeq.has(float1, float3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSeq.has(float2, float3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSeq.has(float1, float2, float3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.floatSeq.has(List(float1, float2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSeq.has(List(float1, float3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSeq.has(List(float2, float3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSeq.has(List(float1, float2, float3)).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.floatSeq.has(List.empty[Float]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, List(float1, float2))
        val b = (2, List(float2, float3, float3))
        for {
          _ <- Ns.i.floatSeq.insert(List(a, b)).transact

          // Seqs without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.floatSeq.hasNo(float0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSeq.hasNo(float1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floatSeq.hasNo(float2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq.hasNo(float3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floatSeq.hasNo(float3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floatSeq.hasNo(float5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.floatSeq.hasNo(List(float0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSeq.hasNo(List(float1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floatSeq.hasNo(List(float2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq.hasNo(List(float3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floatSeq.hasNo(List(float3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floatSeq.hasNo(List(float5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.floatSeq.hasNo(float1, float2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq.hasNo(float1, float3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq.hasNo(float1, float3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq.hasNo(float1, float5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.floatSeq.hasNo(List(float1, float2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq.hasNo(List(float1, float3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq.hasNo(List(float1, float3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq.hasNo(List(float1, float5)).query.get.map(_ ==> List(b))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.floatSeq.hasNo(List.empty[Float]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "has" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.floatSeq.insert(List(
            (1, List(float1, float2)),
            (2, List(float2, float3, float3))
          )).transact

          // Seqs with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.floatSeq_.has(float0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq_.has(float1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.floatSeq_.has(float2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.floatSeq_.has(float3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.floatSeq_.has(List(float0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq_.has(List(float1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.floatSeq_.has(List(float2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.floatSeq_.has(List(float3)).query.get.map(_ ==> List(2))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.floatSeq_.has(float1, float2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.floatSeq_.has(float1, float3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.floatSeq_.has(float2, float3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.floatSeq_.has(float1, float2, float3).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.floatSeq_.has(List(float1, float2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.floatSeq_.has(List(float1, float3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.floatSeq_.has(List(float2, float3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.floatSeq_.has(List(float1, float2, float3)).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.floatSeq_.has(List.empty[Float]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.floatSeq.insert(List(
            (1, List(float1, float2)),
            (2, List(float2, float3, float3))
          )).transact

          // Seqs without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.floatSeq_.hasNo(float0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.floatSeq_.hasNo(float1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.floatSeq_.hasNo(float2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq_.hasNo(float3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.floatSeq_.hasNo(float3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.floatSeq_.hasNo(float5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.floatSeq_.hasNo(List(float0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.floatSeq_.hasNo(List(float1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.floatSeq_.hasNo(List(float2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq_.hasNo(List(float3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.floatSeq_.hasNo(List(float3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.floatSeq_.hasNo(List(float5)).query.get.map(_ ==> List(1, 2))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.floatSeq_.hasNo(float1, float2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq_.hasNo(float1, float3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq_.hasNo(float1, float3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq_.hasNo(float1, float5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.floatSeq_.hasNo(List(float1, float2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq_.hasNo(List(float1, float3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq_.hasNo(List(float1, float3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq_.hasNo(List(float1, float5)).query.get.map(_ ==> List(2))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.floatSeq_.hasNo(List.empty[Float]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }


    "Optional" - {

      "has" - types { implicit conn =>
        val a = (1, Some(List(float1, float2)))
        val b = (2, Some(List(float2, float3, float3)))
        val c = (3, None)
        for {
          _ <- Ns.i.floatSeq_?.insert(a, b, c).transact

          // Seqs with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.floatSeq_?.has(Some(float0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq_?.has(Some(float1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floatSeq_?.has(Some(float2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSeq_?.has(Some(float3)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.floatSeq_?.has(Some(List(float0))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq_?.has(Some(List(float1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floatSeq_?.has(Some(List(float2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSeq_?.has(Some(List(float3))).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.floatSeq_?.has(Some(List(float1, float2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSeq_?.has(Some(List(float1, float3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSeq_?.has(Some(List(float2, float3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSeq_?.has(Some(List(float1, float2, float3))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.floatSeq_?.has(Some(List.empty[Float])).query.get.map(_ ==> List())

          // None matches non-asserted values
          _ <- Ns.i.a1.floatSeq_?.has(Option.empty[Float]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.floatSeq_?.has(Option.empty[List[Float]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Some(List(float1, float2)))
        val b = (2, Some(List(float2, float3, float3)))
        val c = (3, None)
        for {
          _ <- Ns.i.floatSeq_?.insert(a, b, c).transact

          // Seqs without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.floatSeq_?.hasNo(Some(float0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSeq_?.hasNo(Some(float1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floatSeq_?.hasNo(Some(float2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq_?.hasNo(Some(float3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floatSeq_?.hasNo(Some(float3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floatSeq_?.hasNo(Some(float5)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.floatSeq_?.hasNo(Some(List(float0))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSeq_?.hasNo(Some(List(float1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.floatSeq_?.hasNo(Some(List(float2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq_?.hasNo(Some(List(float3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floatSeq_?.hasNo(Some(List(float3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.floatSeq_?.hasNo(Some(List(float5))).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.floatSeq_?.hasNo(Some(List(float1, float2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq_?.hasNo(Some(List(float1, float3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq_?.hasNo(Some(List(float1, float3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.floatSeq_?.hasNo(Some(List(float1, float5))).query.get.map(_ ==> List(b))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.floatSeq_?.hasNo(Some(List.empty[Float])).query.get.map(_ ==> List(a, b))

          // Negating None returns all asserted
          _ <- Ns.i.a1.floatSeq_?.hasNo(Option.empty[Float]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.floatSeq_?.hasNo(Option.empty[List[Float]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}