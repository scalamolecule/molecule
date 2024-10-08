// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.seq.types

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSeq_Char_ extends CoreTestSuite with Api_async { spi: Spi_async =>

  val a = (1, List(char1, char2))
  val b = (2, List(char2, char3, char3))

  override lazy val tests = Tests {

    "Mandatory" - {

      "has" - types { implicit conn =>
        for {
          _ <- Ns.i.charSeq.insert(a, b).transact

          // Seqs with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.charSeq.has(char0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSeq.has(char1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.charSeq.has(char2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSeq.has(char3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.charSeq.has(List(char0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSeq.has(List(char1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.charSeq.has(List(char2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSeq.has(List(char3)).query.get.map(_ ==> List(b))

          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.charSeq.has(char0, char1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.charSeq.has(char1, char2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSeq.has(char1, char3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSeq.has(char2, char3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSeq.has(char1, char2, char3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.charSeq.has(List(char0, char1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.charSeq.has(List(char1, char2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSeq.has(List(char1, char3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSeq.has(List(char2, char3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSeq.has(List(char1, char2, char3)).query.get.map(_ ==> List(a, b))

          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.charSeq.has(List.empty[Char]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i.charSeq.insert(a, b).transact

          // Seqs without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.charSeq.hasNo(char0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSeq.hasNo(char1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.charSeq.hasNo(char2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSeq.hasNo(char3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.charSeq.hasNo(char3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.charSeq.hasNo(char5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.charSeq.hasNo(List(char0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.charSeq.hasNo(List(char1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.charSeq.hasNo(List(char2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSeq.hasNo(List(char3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.charSeq.hasNo(List(char3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.charSeq.hasNo(List(char5)).query.get.map(_ ==> List(a, b))

          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.charSeq.hasNo(char1, char2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSeq.hasNo(char1, char3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSeq.hasNo(char1, char3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSeq.hasNo(char1, char5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.charSeq.hasNo(List(char1, char2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSeq.hasNo(List(char1, char3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSeq.hasNo(List(char1, char3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSeq.hasNo(List(char1, char5)).query.get.map(_ ==> List(b))

          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.charSeq.hasNo(List.empty[Char]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "has" - types { implicit conn =>
        for {
          _ <- Ns.i.charSeq.insert(a, b).transact

          // Seqs with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.charSeq_.has(char0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSeq_.has(char1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.charSeq_.has(char2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charSeq_.has(char3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.charSeq_.has(List(char0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSeq_.has(List(char1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.charSeq_.has(List(char2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charSeq_.has(List(char3)).query.get.map(_ ==> List(2))

          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.charSeq_.has(char0, char1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.charSeq_.has(char1, char2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charSeq_.has(char1, char3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charSeq_.has(char2, char3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charSeq_.has(char3, char4).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.charSeq_.has(List(char0, char1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.charSeq_.has(List(char1, char2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charSeq_.has(List(char1, char3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charSeq_.has(List(char2, char3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charSeq_.has(List(char3, char4)).query.get.map(_ ==> List(2))

          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.charSeq_.has(List.empty[Char]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i.charSeq.insert(a, b).transact

          // Seqs without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.charSeq_.hasNo(char0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charSeq_.hasNo(char1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.charSeq_.hasNo(char2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSeq_.hasNo(char3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.charSeq_.hasNo(char3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.charSeq_.hasNo(char5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.charSeq_.hasNo(List(char0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.charSeq_.hasNo(List(char1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.charSeq_.hasNo(List(char2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSeq_.hasNo(List(char3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.charSeq_.hasNo(List(char3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.charSeq_.hasNo(List(char5)).query.get.map(_ ==> List(1, 2))

          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.charSeq_.hasNo(char1, char2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSeq_.hasNo(char1, char3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSeq_.hasNo(char1, char3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSeq_.hasNo(char1, char5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.charSeq_.hasNo(List(char1, char2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSeq_.hasNo(List(char1, char3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSeq_.hasNo(List(char1, char3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.charSeq_.hasNo(List(char1, char5)).query.get.map(_ ==> List(2))

          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.charSeq_.hasNo(List.empty[Char]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }

    // No filtering on optional Seq attributes
  }
}