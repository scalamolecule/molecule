// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.seq.types

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSeq_BigInt_ extends CoreTestSuite with Api_async { spi: Spi_async =>

  val a = (1, List(bigInt1, bigInt2))
  val b = (2, List(bigInt2, bigInt3, bigInt3))

  override lazy val tests = Tests {

    "Mandatory" - {

      "has" - types { implicit conn =>
        for {
          _ <- Ns.i.bigIntSeq.insert(a, b).transact

          // Seqs with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.bigIntSeq.has(bigInt0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntSeq.has(bigInt1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigIntSeq.has(bigInt2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigIntSeq.has(bigInt3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.bigIntSeq.has(List(bigInt0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntSeq.has(List(bigInt1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigIntSeq.has(List(bigInt2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigIntSeq.has(List(bigInt3)).query.get.map(_ ==> List(b))

          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.bigIntSeq.has(bigInt0, bigInt1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigIntSeq.has(bigInt1, bigInt2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigIntSeq.has(bigInt1, bigInt3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigIntSeq.has(bigInt2, bigInt3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigIntSeq.has(bigInt1, bigInt2, bigInt3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.bigIntSeq.has(List(bigInt0, bigInt1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigIntSeq.has(List(bigInt1, bigInt2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigIntSeq.has(List(bigInt1, bigInt3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigIntSeq.has(List(bigInt2, bigInt3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigIntSeq.has(List(bigInt1, bigInt2, bigInt3)).query.get.map(_ ==> List(a, b))

          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.bigIntSeq.has(List.empty[BigInt]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i.bigIntSeq.insert(a, b).transact

          // Seqs without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.bigIntSeq.hasNo(bigInt0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigIntSeq.hasNo(bigInt1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigIntSeq.hasNo(bigInt2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntSeq.hasNo(bigInt3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigIntSeq.hasNo(bigInt3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigIntSeq.hasNo(bigInt5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.bigIntSeq.hasNo(List(bigInt0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.bigIntSeq.hasNo(List(bigInt1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.bigIntSeq.hasNo(List(bigInt2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntSeq.hasNo(List(bigInt3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigIntSeq.hasNo(List(bigInt3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.bigIntSeq.hasNo(List(bigInt5)).query.get.map(_ ==> List(a, b))

          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.bigIntSeq.hasNo(bigInt1, bigInt2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntSeq.hasNo(bigInt1, bigInt3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntSeq.hasNo(bigInt1, bigInt3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntSeq.hasNo(bigInt1, bigInt5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.bigIntSeq.hasNo(List(bigInt1, bigInt2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntSeq.hasNo(List(bigInt1, bigInt3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntSeq.hasNo(List(bigInt1, bigInt3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntSeq.hasNo(List(bigInt1, bigInt5)).query.get.map(_ ==> List(b))

          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.bigIntSeq.hasNo(List.empty[BigInt]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "has" - types { implicit conn =>
        for {
          _ <- Ns.i.bigIntSeq.insert(a, b).transact

          // Seqs with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.bigIntSeq_.has(bigInt0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntSeq_.has(bigInt1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigIntSeq_.has(bigInt2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigIntSeq_.has(bigInt3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.bigIntSeq_.has(List(bigInt0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntSeq_.has(List(bigInt1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigIntSeq_.has(List(bigInt2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigIntSeq_.has(List(bigInt3)).query.get.map(_ ==> List(2))

          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.bigIntSeq_.has(bigInt0, bigInt1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigIntSeq_.has(bigInt1, bigInt2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigIntSeq_.has(bigInt1, bigInt3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigIntSeq_.has(bigInt2, bigInt3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigIntSeq_.has(bigInt3, bigInt4).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.bigIntSeq_.has(List(bigInt0, bigInt1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigIntSeq_.has(List(bigInt1, bigInt2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigIntSeq_.has(List(bigInt1, bigInt3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigIntSeq_.has(List(bigInt2, bigInt3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigIntSeq_.has(List(bigInt3, bigInt4)).query.get.map(_ ==> List(2))

          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.bigIntSeq_.has(List.empty[BigInt]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i.bigIntSeq.insert(a, b).transact

          // Seqs without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.bigIntSeq_.hasNo(bigInt0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigIntSeq_.hasNo(bigInt1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigIntSeq_.hasNo(bigInt2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntSeq_.hasNo(bigInt3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigIntSeq_.hasNo(bigInt3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigIntSeq_.hasNo(bigInt5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.bigIntSeq_.hasNo(List(bigInt0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.bigIntSeq_.hasNo(List(bigInt1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.bigIntSeq_.hasNo(List(bigInt2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntSeq_.hasNo(List(bigInt3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigIntSeq_.hasNo(List(bigInt3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.bigIntSeq_.hasNo(List(bigInt5)).query.get.map(_ ==> List(1, 2))

          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.bigIntSeq_.hasNo(bigInt1, bigInt2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntSeq_.hasNo(bigInt1, bigInt3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntSeq_.hasNo(bigInt1, bigInt3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntSeq_.hasNo(bigInt1, bigInt5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.bigIntSeq_.hasNo(List(bigInt1, bigInt2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntSeq_.hasNo(List(bigInt1, bigInt3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntSeq_.hasNo(List(bigInt1, bigInt3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.bigIntSeq_.hasNo(List(bigInt1, bigInt5)).query.get.map(_ ==> List(2))

          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.bigIntSeq_.hasNo(List.empty[BigInt]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }

    // No filtering on optional Seq attributes
  }
}