// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.seq.types

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSeq_Short_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "has" - types { implicit conn =>
        val a = (1, List(short1, short2))
        val b = (2, List(short2, short3, short3))
        for {
          _ <- Ns.i.shortSeq.insert(List(a, b)).transact

          // Seqs with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.shortSeq.has(short0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq.has(short1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shortSeq.has(short2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortSeq.has(short3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.shortSeq.has(List(short0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq.has(List(short1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shortSeq.has(List(short2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortSeq.has(List(short3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.shortSeq.has(short1, short2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortSeq.has(short1, short3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortSeq.has(short2, short3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortSeq.has(short1, short2, short3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.shortSeq.has(List(short1, short2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortSeq.has(List(short1, short3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortSeq.has(List(short2, short3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortSeq.has(List(short1, short2, short3)).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.shortSeq.has(List.empty[Short]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, List(short1, short2))
        val b = (2, List(short2, short3, short3))
        for {
          _ <- Ns.i.shortSeq.insert(List(a, b)).transact

          // Seqs without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.shortSeq.hasNo(short0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortSeq.hasNo(short1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shortSeq.hasNo(short2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq.hasNo(short3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shortSeq.hasNo(short3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shortSeq.hasNo(short5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.shortSeq.hasNo(List(short0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortSeq.hasNo(List(short1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shortSeq.hasNo(List(short2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq.hasNo(List(short3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shortSeq.hasNo(List(short3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shortSeq.hasNo(List(short5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.shortSeq.hasNo(short1, short2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq.hasNo(short1, short3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq.hasNo(short1, short3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq.hasNo(short1, short5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.shortSeq.hasNo(List(short1, short2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq.hasNo(List(short1, short3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq.hasNo(List(short1, short3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq.hasNo(List(short1, short5)).query.get.map(_ ==> List(b))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.shortSeq.hasNo(List.empty[Short]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "has" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.shortSeq.insert(List(
            (1, List(short1, short2)),
            (2, List(short2, short3, short3))
          )).transact

          // Seqs with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.shortSeq_.has(short0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq_.has(short1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.shortSeq_.has(short2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.shortSeq_.has(short3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.shortSeq_.has(List(short0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq_.has(List(short1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.shortSeq_.has(List(short2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.shortSeq_.has(List(short3)).query.get.map(_ ==> List(2))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.shortSeq_.has(short1, short2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.shortSeq_.has(short1, short3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.shortSeq_.has(short2, short3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.shortSeq_.has(short1, short2, short3).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.shortSeq_.has(List(short1, short2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.shortSeq_.has(List(short1, short3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.shortSeq_.has(List(short2, short3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.shortSeq_.has(List(short1, short2, short3)).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.shortSeq_.has(List.empty[Short]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.shortSeq.insert(List(
            (1, List(short1, short2)),
            (2, List(short2, short3, short3))
          )).transact

          // Seqs without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.shortSeq_.hasNo(short0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.shortSeq_.hasNo(short1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.shortSeq_.hasNo(short2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq_.hasNo(short3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.shortSeq_.hasNo(short3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.shortSeq_.hasNo(short5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.shortSeq_.hasNo(List(short0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.shortSeq_.hasNo(List(short1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.shortSeq_.hasNo(List(short2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq_.hasNo(List(short3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.shortSeq_.hasNo(List(short3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.shortSeq_.hasNo(List(short5)).query.get.map(_ ==> List(1, 2))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.shortSeq_.hasNo(short1, short2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq_.hasNo(short1, short3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq_.hasNo(short1, short3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq_.hasNo(short1, short5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.shortSeq_.hasNo(List(short1, short2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq_.hasNo(List(short1, short3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq_.hasNo(List(short1, short3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq_.hasNo(List(short1, short5)).query.get.map(_ ==> List(2))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.shortSeq_.hasNo(List.empty[Short]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }


    "Optional" - {

      "has" - types { implicit conn =>
        val a = (1, Some(List(short1, short2)))
        val b = (2, Some(List(short2, short3, short3)))
        val c = (3, None)
        for {
          _ <- Ns.i.shortSeq_?.insert(a, b, c).transact

          // Seqs with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.shortSeq_?.has(Some(short0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq_?.has(Some(short1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shortSeq_?.has(Some(short2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortSeq_?.has(Some(short3)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.shortSeq_?.has(Some(List(short0))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq_?.has(Some(List(short1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shortSeq_?.has(Some(List(short2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortSeq_?.has(Some(List(short3))).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.shortSeq_?.has(Some(List(short1, short2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortSeq_?.has(Some(List(short1, short3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortSeq_?.has(Some(List(short2, short3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortSeq_?.has(Some(List(short1, short2, short3))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.shortSeq_?.has(Some(List.empty[Short])).query.get.map(_ ==> List())

          // None matches non-asserted values
          _ <- Ns.i.a1.shortSeq_?.has(Option.empty[Short]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.shortSeq_?.has(Option.empty[List[Short]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Some(List(short1, short2)))
        val b = (2, Some(List(short2, short3, short3)))
        val c = (3, None)
        for {
          _ <- Ns.i.shortSeq_?.insert(a, b, c).transact

          // Seqs without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.shortSeq_?.hasNo(Some(short0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortSeq_?.hasNo(Some(short1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shortSeq_?.hasNo(Some(short2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq_?.hasNo(Some(short3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shortSeq_?.hasNo(Some(short3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shortSeq_?.hasNo(Some(short5)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.shortSeq_?.hasNo(Some(List(short0))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortSeq_?.hasNo(Some(List(short1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.shortSeq_?.hasNo(Some(List(short2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq_?.hasNo(Some(List(short3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shortSeq_?.hasNo(Some(List(short3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shortSeq_?.hasNo(Some(List(short5))).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.shortSeq_?.hasNo(Some(List(short1, short2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq_?.hasNo(Some(List(short1, short3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq_?.hasNo(Some(List(short1, short3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.shortSeq_?.hasNo(Some(List(short1, short5))).query.get.map(_ ==> List(b))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.shortSeq_?.hasNo(Some(List.empty[Short])).query.get.map(_ ==> List(a, b))

          // Negating None returns all asserted
          _ <- Ns.i.a1.shortSeq_?.hasNo(Option.empty[Short]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortSeq_?.hasNo(Option.empty[List[Short]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}