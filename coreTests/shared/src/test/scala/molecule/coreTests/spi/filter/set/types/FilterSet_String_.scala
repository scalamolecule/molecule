// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.set.types

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSet_String_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "has" - types { implicit conn =>
        val a = (1, Set(string1, string2))
        val b = (2, Set(string2, string3, string4))
        for {
          _ <- Ns.i.stringSet.insert(List(a, b)).transact

          // Sets with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.stringSet.has(string0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSet.has(string1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.stringSet.has(string2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.stringSet.has(string3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.stringSet.has(Seq(string0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSet.has(Seq(string1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.stringSet.has(Seq(string2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.stringSet.has(Seq(string3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.stringSet.has(string1, string2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.stringSet.has(string1, string3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.stringSet.has(string2, string3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.stringSet.has(string1, string2, string3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.stringSet.has(Seq(string1, string2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.stringSet.has(Seq(string1, string3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.stringSet.has(Seq(string2, string3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.stringSet.has(Seq(string1, string2, string3)).query.get.map(_ ==> List(a, b))

          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.stringSet.has(Seq.empty[String]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Set(string1, string2))
        val b = (2, Set(string2, string3, string4))
        for {
          _ <- Ns.i.stringSet.insert(List(a, b)).transact

          // Sets without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.stringSet.hasNo(string0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.stringSet.hasNo(string1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.stringSet.hasNo(string2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSet.hasNo(string3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.stringSet.hasNo(string4).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.stringSet.hasNo(string5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.stringSet.hasNo(Seq(string0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.stringSet.hasNo(Seq(string1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.stringSet.hasNo(Seq(string2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSet.hasNo(Seq(string3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.stringSet.hasNo(Seq(string4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.stringSet.hasNo(Seq(string5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.stringSet.hasNo(string1, string2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSet.hasNo(string1, string3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSet.hasNo(string1, string4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSet.hasNo(string1, string5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.stringSet.hasNo(Seq(string1, string2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSet.hasNo(Seq(string1, string3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSet.hasNo(Seq(string1, string4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSet.hasNo(Seq(string1, string5)).query.get.map(_ ==> List(b))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.stringSet.hasNo(Seq.empty[String]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "has" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.stringSet.insert(List(
            (1, Set(string1, string2)),
            (2, Set(string2, string3, string4))
          )).transact

          // Sets with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.stringSet_.has(string0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSet_.has(string1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.stringSet_.has(string2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.stringSet_.has(string3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.stringSet_.has(Seq(string0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSet_.has(Seq(string1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.stringSet_.has(Seq(string2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.stringSet_.has(Seq(string3)).query.get.map(_ ==> List(2))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.stringSet_.has(string1, string2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.stringSet_.has(string1, string3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.stringSet_.has(string2, string3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.stringSet_.has(string1, string2, string3).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.stringSet_.has(Seq(string1, string2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.stringSet_.has(Seq(string1, string3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.stringSet_.has(Seq(string2, string3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.stringSet_.has(Seq(string1, string2, string3)).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.stringSet_.has(Seq.empty[String]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.stringSet.insert(List(
            (1, Set(string1, string2)),
            (2, Set(string2, string3, string4))
          )).transact

          // Sets without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.stringSet_.hasNo(string0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.stringSet_.hasNo(string1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.stringSet_.hasNo(string2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSet_.hasNo(string3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.stringSet_.hasNo(string4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.stringSet_.hasNo(string5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.stringSet_.hasNo(Seq(string0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.stringSet_.hasNo(Seq(string1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.stringSet_.hasNo(Seq(string2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSet_.hasNo(Seq(string3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.stringSet_.hasNo(Seq(string4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.stringSet_.hasNo(Seq(string5)).query.get.map(_ ==> List(1, 2))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.stringSet_.hasNo(string1, string2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSet_.hasNo(string1, string3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSet_.hasNo(string1, string4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSet_.hasNo(string1, string5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.stringSet_.hasNo(Seq(string1, string2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSet_.hasNo(Seq(string1, string3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSet_.hasNo(Seq(string1, string4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSet_.hasNo(Seq(string1, string5)).query.get.map(_ ==> List(2))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.stringSet_.hasNo(Seq.empty[String]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }


    "Optional" - {

      "has" - types { implicit conn =>
        val a = (1, Some(Set(string1, string2)))
        val b = (2, Some(Set(string2, string3, string4)))
        val c = (3, None)
        for {
          _ <- Ns.i.stringSet_?.insert(a, b, c).transact

          // Sets with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.stringSet_?.has(Some(string0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSet_?.has(Some(string1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.stringSet_?.has(Some(string2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.stringSet_?.has(Some(string3)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.stringSet_?.has(Some(Seq(string0))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSet_?.has(Some(Seq(string1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.stringSet_?.has(Some(Seq(string2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.stringSet_?.has(Some(Seq(string3))).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.stringSet_?.has(Some(Seq(string1, string2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.stringSet_?.has(Some(Seq(string1, string3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.stringSet_?.has(Some(Seq(string2, string3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.stringSet_?.has(Some(Seq(string1, string2, string3))).query.get.map(_ ==> List(a, b))

          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.stringSet_?.has(Some(Seq.empty[String])).query.get.map(_ ==> List())

          // None matches non-asserted values
          _ <- Ns.i.a1.stringSet_?.has(Option.empty[String]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.stringSet_?.has(Option.empty[Seq[String]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Some(Set(string1, string2)))
        val b = (2, Some(Set(string2, string3, string4)))
        val c = (3, None)
        for {
          _ <- Ns.i.stringSet_?.insert(a, b, c).transact

          // Sets without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.stringSet_?.hasNo(Some(string0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.stringSet_?.hasNo(Some(string1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.stringSet_?.hasNo(Some(string2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSet_?.hasNo(Some(string3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.stringSet_?.hasNo(Some(string4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.stringSet_?.hasNo(Some(string5)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.stringSet_?.hasNo(Some(Seq(string0))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.stringSet_?.hasNo(Some(Seq(string1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.stringSet_?.hasNo(Some(Seq(string2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSet_?.hasNo(Some(Seq(string3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.stringSet_?.hasNo(Some(Seq(string4))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.stringSet_?.hasNo(Some(Seq(string5))).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.stringSet_?.hasNo(Some(Seq(string1, string2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSet_?.hasNo(Some(Seq(string1, string3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSet_?.hasNo(Some(Seq(string1, string4))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.stringSet_?.hasNo(Some(Seq(string1, string5))).query.get.map(_ ==> List(b))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.stringSet_?.hasNo(Some(Seq.empty[String])).query.get.map(_ ==> List(a, b))

          // Negating None returns all asserted
          _ <- Ns.i.a1.stringSet_?.hasNo(Option.empty[String]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.stringSet_?.hasNo(Option.empty[Seq[String]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}