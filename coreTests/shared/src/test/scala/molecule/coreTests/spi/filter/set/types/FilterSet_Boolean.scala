// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.set.types

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._


trait FilterSet_Boolean extends CoreTestSuite with ApiAsync { spi: SpiAsync =>


  override lazy val tests = Tests {

    "Mandatory" - {

      "attr" - types { implicit conn =>
        val t  = (1, Set(true))
        val f  = (2, Set(false))
        val tf = (3, Set(true, false))
        for {
          _ <- Ns.i.booleanSet.insert(List(t, f, tf)).transact

          _ <- Ns.i.a1.booleanSet.query.get.map(_ ==> List(t, f, tf))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val t  = (1, Set(true))
        val f  = (2, Set(false))
        val tf = (3, Set(true, false))
        for {
          _ <- Ns.i.booleanSet.insert(List(t, f, tf)).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.booleanSet(Set(true)).query.get.map(_ ==> List(t))
          _ <- Ns.i.a1.booleanSet(Set(false)).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleanSet(Set(true, false)).query.get.map(_ ==> List(tf))
          // Same as
          _ <- Ns.i.a1.booleanSet(Seq(Set(true))).query.get.map(_ ==> List(t))
          _ <- Ns.i.a1.booleanSet(Seq(Set(false))).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleanSet(Seq(Set(true, false))).query.get.map(_ ==> List(tf))


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.booleanSet(Set(true, false), Set(true)).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleanSet(Set(true, false), Set(false)).query.get.map(_ ==> List(f, tf))
          // Same as
          _ <- Ns.i.a1.booleanSet(Seq(Set(true, false), Set(true))).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleanSet(Seq(Set(true, false), Set(false))).query.get.map(_ ==> List(f, tf))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.booleanSet(Set(true), Set.empty[Boolean]).query.get.map(_ ==> List(t))
          _ <- Ns.i.a1.booleanSet(Set(false), Set.empty[Boolean]).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleanSet(Set(true, false), Set.empty[Boolean]).query.get.map(_ ==> List(tf))
          _ <- Ns.i.a1.booleanSet(Set.empty[Boolean]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.booleanSet(Seq.empty[Set[Boolean]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.booleanSet(Seq(Set.empty[Boolean])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val t  = (1, Set(true))
        val f  = (2, Set(false))
        val tf = (3, Set(true, false))
        for {
          _ <- Ns.i.booleanSet.insert(List(t, f, tf)).transact

          // Exact Set non-matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.booleanSet.not(Set(true)).query.get.map(_ ==> List(f, tf))
          _ <- Ns.i.a1.booleanSet.not(Set(false)).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleanSet.not(Set(true, false)).query.get.map(_ ==> List(t, f))
          // Same as
          _ <- Ns.i.a1.booleanSet.not(Seq(Set(true))).query.get.map(_ ==> List(f, tf))
          _ <- Ns.i.a1.booleanSet.not(Seq(Set(false))).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleanSet.not(Seq(Set(true, false))).query.get.map(_ ==> List(t, f))


          // AND/OR semantics with multiple Sets

          // "Not (exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.booleanSet.not(Set(true, false), Set(true)).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleanSet.not(Set(true, false), Set(false)).query.get.map(_ ==> List(t))
          // Same as
          _ <- Ns.i.a1.booleanSet.not(Seq(Set(true, false), Set(true))).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleanSet.not(Seq(Set(true, false), Set(false))).query.get.map(_ ==> List(t))


          // Empty Seq/Sets
          _ <- Ns.i.a1.booleanSet.not(Seq(Set(true), Set.empty[Boolean])).query.get.map(_ ==> List(f, tf))
          _ <- Ns.i.a1.booleanSet.not(Seq(Set(false), Set.empty[Boolean])).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleanSet.not(Seq(Set(true, false), Set.empty[Boolean])).query.get.map(_ ==> List(t, f))
          _ <- Ns.i.a1.booleanSet.not(Set.empty[Boolean]).query.get.map(_ ==> List(t, f, tf))
          _ <- Ns.i.a1.booleanSet.not(Seq.empty[Set[Boolean]]).query.get.map(_ ==> List(t, f, tf))
        } yield ()
      }


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

          // "Not (has this OR that)"
          _ <- Ns.i.a1.booleanSet.hasNo(true, false).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.booleanSet.hasNo(Seq(true, false)).query.get.map(_ ==> List())

          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.booleanSet.hasNo(Seq.empty[Boolean]).query.get.map(_ ==> List(t, f, tf))
        } yield ()
      }
    }


    "Tacit" - {

      "attr" - types { implicit conn =>
        val (t, f, tf) = (1, 2, 3)
        for {
          _ <- Ns.i.booleanSet.insert(List(
            (t, Set(true)),
            (f, Set(false)),
            (tf, Set(true, false))
          )).transact

          _ <- Ns.i.a1.booleanSet_.query.get.map(_ ==> List(t, f, tf))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val (t, f, tf) = (1, 2, 3)
        for {
          _ <- Ns.i.booleanSet.insert(List(
            (t, Set(true)),
            (f, Set(false)),
            (tf, Set(true, false))
          )).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.booleanSet_(Set(true)).query.get.map(_ ==> List(t))
          _ <- Ns.i.a1.booleanSet_(Set(false)).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleanSet_(Set(true, false)).query.get.map(_ ==> List(tf))
          // Same as
          _ <- Ns.i.a1.booleanSet_(Seq(Set(true))).query.get.map(_ ==> List(t))
          _ <- Ns.i.a1.booleanSet_(Seq(Set(false))).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleanSet_(Seq(Set(true, false))).query.get.map(_ ==> List(tf))


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.booleanSet_(Set(true, false), Set(true)).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleanSet_(Set(true, false), Set(false)).query.get.map(_ ==> List(f, tf))
          // Same as
          _ <- Ns.i.a1.booleanSet_(Seq(Set(true, false), Set(true))).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleanSet_(Seq(Set(true, false), Set(false))).query.get.map(_ ==> List(f, tf))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.booleanSet_(Set(true), Set.empty[Boolean]).query.get.map(_ ==> List(t))
          _ <- Ns.i.a1.booleanSet_(Set(false), Set.empty[Boolean]).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleanSet_(Set(true, false), Set.empty[Boolean]).query.get.map(_ ==> List(tf))
          _ <- Ns.i.a1.booleanSet_(Set.empty[Boolean]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.booleanSet_(Seq.empty[Set[Boolean]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.booleanSet_(Seq(Set.empty[Boolean])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val (t, f, tf) = (1, 2, 3)
        for {
          _ <- Ns.i.booleanSet.insert(List(
            (t, Set(true)),
            (f, Set(false)),
            (tf, Set(true, false))
          )).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.booleanSet_.not(Set(true)).query.get.map(_ ==> List(f, tf))
          _ <- Ns.i.a1.booleanSet_.not(Set(false)).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleanSet_.not(Set(true, false)).query.get.map(_ ==> List(t, f))
          // Same as
          _ <- Ns.i.a1.booleanSet_.not(Seq(Set(true))).query.get.map(_ ==> List(f, tf))
          _ <- Ns.i.a1.booleanSet_.not(Seq(Set(false))).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleanSet_.not(Seq(Set(true, false))).query.get.map(_ ==> List(t, f))


          // AND/OR semantics with multiple Sets

          // "Not (exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.booleanSet_.not(Set(true, false), Set(true)).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleanSet_.not(Set(true, false), Set(false)).query.get.map(_ ==> List(t))
          // Same as
          _ <- Ns.i.a1.booleanSet_.not(Seq(Set(true, false), Set(true))).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleanSet_.not(Seq(Set(true, false), Set(false))).query.get.map(_ ==> List(t))


          // Empty Seq/Sets
          _ <- Ns.i.a1.booleanSet_.not(Seq(Set(true), Set.empty[Boolean])).query.get.map(_ ==> List(f, tf))
          _ <- Ns.i.a1.booleanSet_.not(Seq(Set(false), Set.empty[Boolean])).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleanSet_.not(Seq(Set(true, false), Set.empty[Boolean])).query.get.map(_ ==> List(t, f))
          _ <- Ns.i.a1.booleanSet_.not(Set.empty[Boolean]).query.get.map(_ ==> List(t, f, tf))
          _ <- Ns.i.a1.booleanSet_.not(Seq.empty[Set[Boolean]]).query.get.map(_ ==> List(t, f, tf))
        } yield ()
      }


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

          // "Not (has this OR that)"
          _ <- Ns.i.a1.booleanSet_.hasNo(true, false).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.booleanSet_.hasNo(Seq(true, false)).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.booleanSet_.hasNo(Seq.empty[Boolean]).query.get.map(_ ==> List(t, f, tf))
        } yield ()
      }
    }


    "Optional" - {

      "attr" - types { implicit conn =>
        val t  = (1, Some(Set(true)))
        val f  = (2, Some(Set(false)))
        val tf = (3, Some(Set(true, false)))
        val x  = (4, None)
        for {
          _ <- Ns.i.booleanSet_?.insert(t, f, tf, x).transact
          _ <- Ns.i.a1.booleanSet_?.query.get.map(_ ==> List(t, f, tf, x))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val t  = (1, Some(Set(true)))
        val f  = (2, Some(Set(false)))
        val tf = (3, Some(Set(true, false)))
        val x  = (4, None)
        for {
          _ <- Ns.i.booleanSet_?.insert(t, f, tf, x).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.booleanSet_?(Some(Set(true))).query.get.map(_ ==> List(t))
          _ <- Ns.i.a1.booleanSet_?(Some(Set(false))).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleanSet_?(Some(Set(true, false))).query.get.map(_ ==> List(tf))
          // Same as
          _ <- Ns.i.a1.booleanSet_?(Some(Seq(Set(true)))).query.get.map(_ ==> List(t))
          _ <- Ns.i.a1.booleanSet_?(Some(Seq(Set(false)))).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleanSet_?(Some(Seq(Set(true, false)))).query.get.map(_ ==> List(tf))


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.booleanSet_?(Some(Seq(Set(true, false), Set(true)))).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleanSet_?(Some(Seq(Set(true, false), Set(false)))).query.get.map(_ ==> List(f, tf))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.booleanSet_?(Some(Set.empty[Boolean])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.booleanSet_?(Some(Seq.empty[Set[Boolean]])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.booleanSet_?(Some(Seq(Set.empty[Boolean]))).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.booleanSet_?(Option.empty[Set[Boolean]]).query.get.map(_ ==> List(x))
          _ <- Ns.i.a1.booleanSet_?(Option.empty[Seq[Set[Boolean]]]).query.get.map(_ ==> List(x))
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val t  = (1, Some(Set(true)))
        val f  = (2, Some(Set(false)))
        val tf = (3, Some(Set(true, false)))
        val x  = (4, None)
        for {
          _ <- Ns.i.booleanSet_?.insert(t, f, tf, x).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.booleanSet_?.not(Some(Set(true))).query.get.map(_ ==> List(f, tf))
          _ <- Ns.i.a1.booleanSet_?.not(Some(Set(false))).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleanSet_?.not(Some(Set(true, false))).query.get.map(_ ==> List(t, f))
          // Same as
          _ <- Ns.i.a1.booleanSet_?.not(Some(Seq(Set(true)))).query.get.map(_ ==> List(f, tf))
          _ <- Ns.i.a1.booleanSet_?.not(Some(Seq(Set(false)))).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleanSet_?.not(Some(Seq(Set(true, false)))).query.get.map(_ ==> List(t, f))


          // AND/OR semantics with multiple Sets

          // "Not (exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.booleanSet_?.not(Some(Seq(Set(true, false), Set(true)))).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleanSet_?.not(Some(Seq(Set(true, false), Set(false)))).query.get.map(_ ==> List(t))


          // Empty Seq/Sets
          _ <- Ns.i.a1.booleanSet_?.not(Some(Seq(Set(true), Set.empty[Boolean]))).query.get.map(_ ==> List(f, tf))
          _ <- Ns.i.a1.booleanSet_?.not(Some(Seq(Set(false), Set.empty[Boolean]))).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleanSet_?.not(Some(Seq(Set(true, false), Set.empty[Boolean]))).query.get.map(_ ==> List(t, f))
          _ <- Ns.i.a1.booleanSet_?.not(Some(Set.empty[Boolean])).query.get.map(_ ==> List(t, f, tf))
          _ <- Ns.i.a1.booleanSet_?.not(Some(Seq.empty[Set[Boolean]])).query.get.map(_ ==> List(t, f, tf))


          // None matches non-asserted values
          _ <- Ns.i.a1.booleanSet_?(Option.empty[Set[Boolean]]).query.get.map(_ ==> List(x))
          _ <- Ns.i.a1.booleanSet_?(Option.empty[Seq[Set[Boolean]]]).query.get.map(_ ==> List(x))
        } yield ()
      }


      "has" - types { implicit conn =>
        val t  = (1, Some(Set(true)))
        val f  = (2, Some(Set(false)))
        val tf = (3, Some(Set(true, false)))
        val x  = (4, None)
        for {
          _ <- Ns.i.booleanSet_?.insert(t, f, tf, x).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.booleanSet_?.has(Some(true)).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleanSet_?.has(Some(false)).query.get.map(_ ==> List(f, tf))
          // Same as
          _ <- Ns.i.a1.booleanSet_?.has(Some(Seq(true))).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleanSet_?.has(Some(Seq(false))).query.get.map(_ ==> List(f, tf))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.booleanSet_?.has(Some(Seq(true, false))).query.get.map(_ ==> List(t, f, tf))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.booleanSet_?.has(Some(Seq.empty[Boolean])).query.get.map(_ ==> List())

          // None matches non-asserted values
          _ <- Ns.i.a1.booleanSet_?.has(Option.empty[Boolean]).query.get.map(_ ==> List(x))
          _ <- Ns.i.a1.booleanSet_?.has(Option.empty[Seq[Boolean]]).query.get.map(_ ==> List(x))
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val t  = (1, Some(Set(true)))
        val f  = (2, Some(Set(false)))
        val tf = (3, Some(Set(true, false)))
        val x  = (4, None)
        for {
          _ <- Ns.i.booleanSet_?.insert(t, f, tf, x).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.booleanSet_?.hasNo(Some(true)).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleanSet_?.hasNo(Some(false)).query.get.map(_ ==> List(t))
          // Same as
          _ <- Ns.i.a1.booleanSet_?.hasNo(Some(Seq(true))).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleanSet_?.hasNo(Some(Seq(false))).query.get.map(_ ==> List(t))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.booleanSet_?.hasNo(Some(Seq(true, false))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.booleanSet_?.hasNo(Some(Seq.empty[Boolean])).query.get.map(_ ==> List(t, f, tf))

          // Negating None returns all asserted
          _ <- Ns.i.a1.booleanSet_?.hasNo(Option.empty[Boolean]).query.get.map(_ ==> List(t, f, tf))
          _ <- Ns.i.a1.booleanSet_?.hasNo(Option.empty[Seq[Boolean]]).query.get.map(_ ==> List(t, f, tf))
        } yield ()
      }
    }
  }
}