// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.seq.types

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSeq_String_ extends CoreTestSuite with Api_async { spi: Spi_async =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "has" - types { implicit conn =>
        val a = (1, List(string1, string2))
        val b = (2, List(string2, string3, string3))
        for {
          _ <- Ns.i.stringSeq.insert(List(a, b)).transact

          // Seqs with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.stringSeq.has(string0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSeq.has(string1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.stringSeq.has(string2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.stringSeq.has(string3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.stringSeq.has(List(string0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSeq.has(List(string1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.stringSeq.has(List(string2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.stringSeq.has(List(string3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.stringSeq.has(string1, string2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.stringSeq.has(string1, string3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.stringSeq.has(string2, string3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.stringSeq.has(string1, string2, string3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.stringSeq.has(List(string1, string2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.stringSeq.has(List(string1, string3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.stringSeq.has(List(string2, string3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.stringSeq.has(List(string1, string2, string3)).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.stringSeq.has(List.empty[String]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, List(string1, string2))
        val b = (2, List(string2, string3, string3))
        for {
          _ <- Ns.i.stringSeq.insert(List(a, b)).transact

          // Seqs without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.stringSeq.hasNo(string0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.stringSeq.hasNo(string1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.stringSeq.hasNo(string2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSeq.hasNo(string3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.stringSeq.hasNo(string3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.stringSeq.hasNo(string5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.stringSeq.hasNo(List(string0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.stringSeq.hasNo(List(string1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.stringSeq.hasNo(List(string2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSeq.hasNo(List(string3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.stringSeq.hasNo(List(string3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.stringSeq.hasNo(List(string5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.stringSeq.hasNo(string1, string2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSeq.hasNo(string1, string3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSeq.hasNo(string1, string3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSeq.hasNo(string1, string5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.stringSeq.hasNo(List(string1, string2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSeq.hasNo(List(string1, string3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSeq.hasNo(List(string1, string3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSeq.hasNo(List(string1, string5)).query.get.map(_ ==> List(b))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.stringSeq.hasNo(List.empty[String]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "has" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.stringSeq.insert(List(
            (1, List(string1, string2)),
            (2, List(string2, string3, string3))
          )).transact

          // Seqs with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.stringSeq_.has(string0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSeq_.has(string1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.stringSeq_.has(string2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.stringSeq_.has(string3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.stringSeq_.has(List(string0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSeq_.has(List(string1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.stringSeq_.has(List(string2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.stringSeq_.has(List(string3)).query.get.map(_ ==> List(2))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.stringSeq_.has(string1, string2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.stringSeq_.has(string1, string3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.stringSeq_.has(string2, string3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.stringSeq_.has(string1, string2, string3).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.stringSeq_.has(List(string1, string2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.stringSeq_.has(List(string1, string3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.stringSeq_.has(List(string2, string3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.stringSeq_.has(List(string1, string2, string3)).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.stringSeq_.has(List.empty[String]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.stringSeq.insert(List(
            (1, List(string1, string2)),
            (2, List(string2, string3, string3))
          )).transact

          // Seqs without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.stringSeq_.hasNo(string0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.stringSeq_.hasNo(string1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.stringSeq_.hasNo(string2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSeq_.hasNo(string3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.stringSeq_.hasNo(string3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.stringSeq_.hasNo(string5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.stringSeq_.hasNo(List(string0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.stringSeq_.hasNo(List(string1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.stringSeq_.hasNo(List(string2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSeq_.hasNo(List(string3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.stringSeq_.hasNo(List(string3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.stringSeq_.hasNo(List(string5)).query.get.map(_ ==> List(1, 2))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.stringSeq_.hasNo(string1, string2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSeq_.hasNo(string1, string3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSeq_.hasNo(string1, string3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSeq_.hasNo(string1, string5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.stringSeq_.hasNo(List(string1, string2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSeq_.hasNo(List(string1, string3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSeq_.hasNo(List(string1, string3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSeq_.hasNo(List(string1, string5)).query.get.map(_ ==> List(2))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.stringSeq_.hasNo(List.empty[String]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }

    // No filtering on optional Seq attributes
  }
}