// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.set.types

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._


trait FilterSet_Boolean extends CoreTestSuite with Api_async { spi: Spi_async =>


  override lazy val tests = Tests {

    "Mandatory" - {

      "has" - types { implicit conn =>
        val t  = (1, Set(true))
        val f  = (2, Set(false))
        val tf = (3, Set(true, false))
        for {
          _ <- Ns.i.booleanSet.insert(List(t, f, tf)).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.booleanSet.has(true).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleanSet.has(false).query.get.map(_ ==> List(f, tf))
          // Same as
          _ <- Ns.i.a1.booleanSet.has(Seq(true)).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleanSet.has(Seq(false)).query.get.map(_ ==> List(f, tf))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.booleanSet.has(true, false).query.get.map(_ ==> List(t, f, tf))
          // Same as
          _ <- Ns.i.a1.booleanSet.has(Seq(true, false)).query.get.map(_ ==> List(t, f, tf))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.booleanSet.has(Seq.empty[Boolean]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val t  = (1, Set(true))
        val f  = (2, Set(false))
        val tf = (3, Set(true, false))
        for {
          _ <- Ns.i.booleanSet.insert(List(t, f, tf)).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.booleanSet.hasNo(true).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleanSet.hasNo(false).query.get.map(_ ==> List(t))
          // Same as
          _ <- Ns.i.a1.booleanSet.hasNo(Seq(true)).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleanSet.hasNo(Seq(false)).query.get.map(_ ==> List(t))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.booleanSet.hasNo(true, false).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.booleanSet.hasNo(Seq(true, false)).query.get.map(_ ==> List())

          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.booleanSet.hasNo(Seq.empty[Boolean]).query.get.map(_ ==> List(t, f, tf))
        } yield ()
      }
    }


    "Tacit" - {

      "has" - types { implicit conn =>
        val (t, f, tf) = (1, 2, 3)
        for {
          _ <- Ns.i.booleanSet.insert(List(
            (t, Set(true)),
            (f, Set(false)),
            (tf, Set(true, false))
          )).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.booleanSet_.has(true).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleanSet_.has(false).query.get.map(_ ==> List(f, tf))
          // Same as
          _ <- Ns.i.a1.booleanSet_.has(Seq(true)).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleanSet_.has(Seq(false)).query.get.map(_ ==> List(f, tf))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.booleanSet_.has(true, false).query.get.map(_ ==> List(t, f, tf))
          // Same as
          _ <- Ns.i.a1.booleanSet_.has(Seq(true, false)).query.get.map(_ ==> List(t, f, tf))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.booleanSet_.has(Seq.empty[Boolean]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val (t, f, tf) = (1, 2, 3)
        for {
          _ <- Ns.i.booleanSet.insert(List(
            (t, Set(true)),
            (f, Set(false)),
            (tf, Set(true, false))
          )).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.booleanSet_.hasNo(true).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleanSet_.hasNo(false).query.get.map(_ ==> List(t))
          // Same as
          _ <- Ns.i.a1.booleanSet_.hasNo(Seq(true)).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleanSet_.hasNo(Seq(false)).query.get.map(_ ==> List(t))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.booleanSet_.hasNo(true, false).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.booleanSet_.hasNo(Seq(true, false)).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.booleanSet_.hasNo(Seq.empty[Boolean]).query.get.map(_ ==> List(t, f, tf))
        } yield ()
      }
    }

    // No filtering on optional Set attributes
  }
}