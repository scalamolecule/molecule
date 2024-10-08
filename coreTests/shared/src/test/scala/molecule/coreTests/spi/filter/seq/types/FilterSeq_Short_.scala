// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.seq.types

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSeq_Short_ extends CoreTestSuite with Api_async { spi: Spi_async =>

  val a = (1, List(short1, short2))
  val b = (2, List(short2, short3, short3))

  override lazy val tests = Tests {

    "Mandatory" - {

      "has" - types { implicit conn =>
        for {
          _ <- Ns.i.shortSeq.insert(a, b).transact

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
          _ <- Ns.i.a1.shortSeq.has(short0, short1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shortSeq.has(short1, short2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortSeq.has(short1, short3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortSeq.has(short2, short3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortSeq.has(short1, short2, short3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.shortSeq.has(List(short0, short1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.shortSeq.has(List(short1, short2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortSeq.has(List(short1, short3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortSeq.has(List(short2, short3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.shortSeq.has(List(short1, short2, short3)).query.get.map(_ ==> List(a, b))

          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.shortSeq.has(List.empty[Short]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i.shortSeq.insert(a, b).transact

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
          _ <- Ns.i.shortSeq.insert(a, b).transact

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
          _ <- Ns.i.a1.shortSeq_.has(short0, short1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.shortSeq_.has(short1, short2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.shortSeq_.has(short1, short3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.shortSeq_.has(short2, short3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.shortSeq_.has(short3, short4).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.shortSeq_.has(List(short0, short1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.shortSeq_.has(List(short1, short2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.shortSeq_.has(List(short1, short3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.shortSeq_.has(List(short2, short3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.shortSeq_.has(List(short3, short4)).query.get.map(_ ==> List(2))

          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.shortSeq_.has(List.empty[Short]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i.shortSeq.insert(a, b).transact

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

    // No filtering on optional Seq attributes
  }
}