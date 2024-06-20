// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.set.types

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSet_ref extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "has" - types { implicit conn =>
        for {
          List(ref1, ref2, ref3, ref4) <- Ref.i.insert(1, 2, 3, 4).transact.map(_.ids)
          a = (1, Set(ref1, ref2))
          b = (2, Set(ref2, ref3, ref4))

          _ <- Ns.i.refs.insert(List(a, b)).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.refs.has(ref0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs.has(ref1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs.has(ref2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs.has(ref3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.refs.has(Seq(ref0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs.has(Seq(ref1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs.has(Seq(ref2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs.has(Seq(ref3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.refs.has(ref1, ref2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs.has(ref1, ref3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs.has(ref2, ref3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs.has(ref1, ref2, ref3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.refs.has(Seq(ref1, ref2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs.has(Seq(ref1, ref3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs.has(Seq(ref2, ref3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs.has(Seq(ref1, ref2, ref3)).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.refs.has(Seq.empty[String]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          List(ref0, ref1, ref2, ref3, ref4, ref5) <- Ref.i.insert(0, 1, 2, 3, 4, 5).transact.map(_.ids)
          a = (1, Set(ref1, ref2))
          b = (2, Set(ref2, ref3, ref4))

          _ <- Ns.i.refs.insert(List(a, b)).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.refs.hasNo(ref0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs.hasNo(ref1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs.hasNo(ref2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs.hasNo(ref3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs.hasNo(ref4).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs.hasNo(ref5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.refs.hasNo(Seq(ref0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.refs.hasNo(Seq(ref1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.refs.hasNo(Seq(ref2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs.hasNo(Seq(ref3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs.hasNo(Seq(ref4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.refs.hasNo(Seq(ref5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.refs.hasNo(ref1, ref2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs.hasNo(ref1, ref3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs.hasNo(ref1, ref4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs.hasNo(ref1, ref5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.refs.hasNo(Seq(ref1, ref2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs.hasNo(Seq(ref1, ref3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs.hasNo(Seq(ref1, ref4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs.hasNo(Seq(ref1, ref5)).query.get.map(_ ==> List(b))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.refs.hasNo(Seq.empty[String]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "has" - types { implicit conn =>
        for {
          List(ref0, ref1, ref2, ref3, ref4) <- Ref.i.insert(0, 1, 2, 3, 4).transact.map(_.ids)

          _ <- Ns.i(0).save.transact
          _ <- Ns.i.refs.insert(List(
            (1, Set(ref1, ref2)),
            (2, Set(ref2, ref3, ref4))
          )).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.refs_.has(ref0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_.has(ref1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.refs_.has(ref2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.refs_.has(ref3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.refs_.has(Seq(ref0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_.has(Seq(ref1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.refs_.has(Seq(ref2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.refs_.has(Seq(ref3)).query.get.map(_ ==> List(2))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.refs_.has(ref1, ref2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.refs_.has(ref1, ref3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.refs_.has(ref2, ref3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.refs_.has(ref1, ref2, ref3).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.refs_.has(Seq(ref1, ref2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.refs_.has(Seq(ref1, ref3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.refs_.has(Seq(ref2, ref3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.refs_.has(Seq(ref1, ref2, ref3)).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.refs_.has(Seq.empty[String]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          List(ref0, ref1, ref2, ref3, ref4, ref5) <- Ref.i.insert(0, 1, 2, 3, 4, 5).transact.map(_.ids)

          _ <- Ns.i(0).save.transact
          _ <- Ns.i.refs.insert(List(
            (1, Set(ref1, ref2)),
            (2, Set(ref2, ref3, ref4))
          )).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.refs_.hasNo(ref0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.refs_.hasNo(ref1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.refs_.hasNo(ref2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_.hasNo(ref3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.refs_.hasNo(ref4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.refs_.hasNo(ref5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.refs_.hasNo(Seq(ref0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.refs_.hasNo(Seq(ref1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.refs_.hasNo(Seq(ref2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_.hasNo(Seq(ref3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.refs_.hasNo(Seq(ref4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.refs_.hasNo(Seq(ref5)).query.get.map(_ ==> List(1, 2))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.refs_.hasNo(ref1, ref2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_.hasNo(ref1, ref3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_.hasNo(ref1, ref4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_.hasNo(ref1, ref5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.refs_.hasNo(Seq(ref1, ref2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_.hasNo(Seq(ref1, ref3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_.hasNo(Seq(ref1, ref4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.refs_.hasNo(Seq(ref1, ref5)).query.get.map(_ ==> List(2))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.refs_.hasNo(Seq.empty[String]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }

    // No filtering on optional Set attributes
  }
}