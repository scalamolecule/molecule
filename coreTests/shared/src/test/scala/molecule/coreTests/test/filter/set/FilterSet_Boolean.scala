// GENERATED CODE ********************************
package molecule.coreTests.test.filter.set

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
          _ <- Ns.i.booleans.insert(List(t, f, tf)).transact

          _ <- Ns.i.a1.booleans.query.get.map(_ ==> List(t, f, tf))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val t  = (1, Set(true))
        val f  = (2, Set(false))
        val tf = (3, Set(true, false))
        for {
          _ <- Ns.i.booleans.insert(List(t, f, tf)).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.booleans(Set(true)).query.get.map(_ ==> List(t))
          _ <- Ns.i.a1.booleans(Set(false)).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleans(Set(true, false)).query.get.map(_ ==> List(tf))
          // Same as
          _ <- Ns.i.a1.booleans(Seq(Set(true))).query.get.map(_ ==> List(t))
          _ <- Ns.i.a1.booleans(Seq(Set(false))).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleans(Seq(Set(true, false))).query.get.map(_ ==> List(tf))


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.booleans(Set(true, false), Set(true)).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans(Set(true, false), Set(false)).query.get.map(_ ==> List(f, tf))
          // Same as
          _ <- Ns.i.a1.booleans(Seq(Set(true, false), Set(true))).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans(Seq(Set(true, false), Set(false))).query.get.map(_ ==> List(f, tf))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.booleans(Set(true), Set.empty[Boolean]).query.get.map(_ ==> List(t))
          _ <- Ns.i.a1.booleans(Set(false), Set.empty[Boolean]).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleans(Set(true, false), Set.empty[Boolean]).query.get.map(_ ==> List(tf))
          _ <- Ns.i.a1.booleans(Set.empty[Boolean]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.booleans(Seq.empty[Set[Boolean]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.booleans(Seq(Set.empty[Boolean])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val t  = (1, Set(true))
        val f  = (2, Set(false))
        val tf = (3, Set(true, false))
        for {
          _ <- Ns.i.booleans.insert(List(t, f, tf)).transact

          // Exact Set non-matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.booleans.not(Set(true)).query.get.map(_ ==> List(f, tf))
          _ <- Ns.i.a1.booleans.not(Set(false)).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans.not(Set(true, false)).query.get.map(_ ==> List(t, f))
          // Same as
          _ <- Ns.i.a1.booleans.not(Seq(Set(true))).query.get.map(_ ==> List(f, tf))
          _ <- Ns.i.a1.booleans.not(Seq(Set(false))).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans.not(Seq(Set(true, false))).query.get.map(_ ==> List(t, f))


          // AND/OR semantics with multiple Sets

          // "Not (exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.booleans.not(Set(true, false), Set(true)).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleans.not(Set(true, false), Set(false)).query.get.map(_ ==> List(t))
          // Same as
          _ <- Ns.i.a1.booleans.not(Seq(Set(true, false), Set(true))).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleans.not(Seq(Set(true, false), Set(false))).query.get.map(_ ==> List(t))


          // Empty Seq/Sets
          _ <- Ns.i.a1.booleans.not(Seq(Set(true), Set.empty[Boolean])).query.get.map(_ ==> List(f, tf))
          _ <- Ns.i.a1.booleans.not(Seq(Set(false), Set.empty[Boolean])).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans.not(Seq(Set(true, false), Set.empty[Boolean])).query.get.map(_ ==> List(t, f))
          _ <- Ns.i.a1.booleans.not(Set.empty[Boolean]).query.get.map(_ ==> List(t, f, tf))
          _ <- Ns.i.a1.booleans.not(Seq.empty[Set[Boolean]]).query.get.map(_ ==> List(t, f, tf))
        } yield ()
      }


      "has" - types { implicit conn =>
        val t  = (1, Set(true))
        val f  = (2, Set(false))
        val tf = (3, Set(true, false))
        for {
          _ <- Ns.i.booleans.insert(List(t, f, tf)).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.booleans.has(true).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans.has(false).query.get.map(_ ==> List(f, tf))
          // Same as
          _ <- Ns.i.a1.booleans.has(Seq(true)).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans.has(Seq(false)).query.get.map(_ ==> List(f, tf))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.booleans.has(true, false).query.get.map(_ ==> List(t, f, tf))
          // Same as
          _ <- Ns.i.a1.booleans.has(Seq(true, false)).query.get.map(_ ==> List(t, f, tf))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.booleans.has(Set(true)).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans.has(Set(false)).query.get.map(_ ==> List(f, tf))
          _ <- Ns.i.a1.booleans.has(Set(true, false)).query.get.map(_ ==> List(tf))
          // Same as
          _ <- Ns.i.a1.booleans.has(Seq(Set(true))).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans.has(Seq(Set(false))).query.get.map(_ ==> List(f, tf))
          _ <- Ns.i.a1.booleans.has(Seq(Set(true, false))).query.get.map(_ ==> List(tf))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.booleans.has(Set(true, false), Set(true)).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans.has(Set(true, false), Set(false)).query.get.map(_ ==> List(f, tf))
          // Same as
          _ <- Ns.i.a1.booleans.has(Seq(Set(true, false), Set(true))).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans.has(Seq(Set(true, false), Set(false))).query.get.map(_ ==> List(f, tf))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.booleans.has(Set(true), Set.empty[Boolean]).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans.has(Set(false), Set.empty[Boolean]).query.get.map(_ ==> List(f, tf))
          _ <- Ns.i.a1.booleans.has(Set(true, false), Set.empty[Boolean]).query.get.map(_ ==> List(tf))
          _ <- Ns.i.a1.booleans.has(Seq.empty[Boolean]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.booleans.has(Set.empty[Boolean]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.booleans.has(Seq.empty[Set[Boolean]]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val t  = (1, Set(true))
        val f  = (2, Set(false))
        val tf = (3, Set(true, false))
        for {
          _ <- Ns.i.booleans.insert(List(t, f, tf)).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.booleans.hasNo(true).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleans.hasNo(false).query.get.map(_ ==> List(t))
          // Same as
          _ <- Ns.i.a1.booleans.hasNo(Seq(true)).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleans.hasNo(Seq(false)).query.get.map(_ ==> List(t))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.booleans.hasNo(true, false).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.booleans.hasNo(Seq(true, false)).query.get.map(_ ==> List())


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.booleans.hasNo(Set(true)).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleans.hasNo(Set(false)).query.get.map(_ ==> List(t))
          _ <- Ns.i.a1.booleans.hasNo(Set(true, false)).query.get.map(_ ==> List(t, f))
          // Same as
          _ <- Ns.i.a1.booleans.hasNo(Seq(Set(true))).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleans.hasNo(Seq(Set(false))).query.get.map(_ ==> List(t))
          _ <- Ns.i.a1.booleans.hasNo(Seq(Set(true, false))).query.get.map(_ ==> List(t, f))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.booleans.hasNo(Set(true, false), Set(true)).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleans.hasNo(Set(true, false), Set(false)).query.get.map(_ ==> List(t))
          // Same as
          _ <- Ns.i.a1.booleans.hasNo(Seq(Set(true, false), Set(true))).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleans.hasNo(Seq(Set(true, false), Set(false))).query.get.map(_ ==> List(t))

          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.booleans.hasNo(Set(true), Set.empty[Boolean]).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleans.hasNo(Set(false), Set.empty[Boolean]).query.get.map(_ ==> List(t))
          _ <- Ns.i.a1.booleans.hasNo(Set(true, false), Set.empty[Boolean]).query.get.map(_ ==> List(t, f))
          _ <- Ns.i.a1.booleans.hasNo(Seq.empty[Boolean]).query.get.map(_ ==> List(t, f, tf))
          _ <- Ns.i.a1.booleans.hasNo(Set.empty[Boolean]).query.get.map(_ ==> List(t, f, tf))
          _ <- Ns.i.a1.booleans.hasNo(Seq.empty[Set[Boolean]]).query.get.map(_ ==> List(t, f, tf))
          _ <- Ns.i.a1.booleans.hasNo(Seq(Set.empty[Boolean])).query.get.map(_ ==> List(t, f, tf))
        } yield ()
      }
    }


    "Tacit" - {

      "attr" - types { implicit conn =>
        val (t, f, tf) = (1, 2, 3)
        for {
          _ <- Ns.i.booleans.insert(List(
            (t, Set(true)),
            (f, Set(false)),
            (tf, Set(true, false))
          )).transact

          _ <- Ns.i.a1.booleans_.query.get.map(_ ==> List(t, f, tf))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val (t, f, tf) = (1, 2, 3)
        for {
          _ <- Ns.i.booleans.insert(List(
            (t, Set(true)),
            (f, Set(false)),
            (tf, Set(true, false))
          )).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.booleans_(Set(true)).query.get.map(_ ==> List(t))
          _ <- Ns.i.a1.booleans_(Set(false)).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleans_(Set(true, false)).query.get.map(_ ==> List(tf))
          // Same as
          _ <- Ns.i.a1.booleans_(Seq(Set(true))).query.get.map(_ ==> List(t))
          _ <- Ns.i.a1.booleans_(Seq(Set(false))).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleans_(Seq(Set(true, false))).query.get.map(_ ==> List(tf))


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.booleans_(Set(true, false), Set(true)).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans_(Set(true, false), Set(false)).query.get.map(_ ==> List(f, tf))
          // Same as
          _ <- Ns.i.a1.booleans_(Seq(Set(true, false), Set(true))).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans_(Seq(Set(true, false), Set(false))).query.get.map(_ ==> List(f, tf))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.booleans_(Set(true), Set.empty[Boolean]).query.get.map(_ ==> List(t))
          _ <- Ns.i.a1.booleans_(Set(false), Set.empty[Boolean]).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleans_(Set(true, false), Set.empty[Boolean]).query.get.map(_ ==> List(tf))
          _ <- Ns.i.a1.booleans_(Set.empty[Boolean]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.booleans_(Seq.empty[Set[Boolean]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.booleans_(Seq(Set.empty[Boolean])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val (t, f, tf) = (1, 2, 3)
        for {
          _ <- Ns.i.booleans.insert(List(
            (t, Set(true)),
            (f, Set(false)),
            (tf, Set(true, false))
          )).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.booleans_.not(Set(true)).query.get.map(_ ==> List(f, tf))
          _ <- Ns.i.a1.booleans_.not(Set(false)).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans_.not(Set(true, false)).query.get.map(_ ==> List(t, f))
          // Same as
          _ <- Ns.i.a1.booleans_.not(Seq(Set(true))).query.get.map(_ ==> List(f, tf))
          _ <- Ns.i.a1.booleans_.not(Seq(Set(false))).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans_.not(Seq(Set(true, false))).query.get.map(_ ==> List(t, f))


          // AND/OR semantics with multiple Sets

          // "Not (exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.booleans_.not(Set(true, false), Set(true)).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleans_.not(Set(true, false), Set(false)).query.get.map(_ ==> List(t))
          // Same as
          _ <- Ns.i.a1.booleans_.not(Seq(Set(true, false), Set(true))).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleans_.not(Seq(Set(true, false), Set(false))).query.get.map(_ ==> List(t))


          // Empty Seq/Sets
          _ <- Ns.i.a1.booleans_.not(Seq(Set(true), Set.empty[Boolean])).query.get.map(_ ==> List(f, tf))
          _ <- Ns.i.a1.booleans_.not(Seq(Set(false), Set.empty[Boolean])).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans_.not(Seq(Set(true, false), Set.empty[Boolean])).query.get.map(_ ==> List(t, f))
          _ <- Ns.i.a1.booleans_.not(Set.empty[Boolean]).query.get.map(_ ==> List(t, f, tf))
          _ <- Ns.i.a1.booleans_.not(Seq.empty[Set[Boolean]]).query.get.map(_ ==> List(t, f, tf))
        } yield ()
      }


      "has" - types { implicit conn =>
        val (t, f, tf) = (1, 2, 3)
        for {
          _ <- Ns.i.booleans.insert(List(
            (t, Set(true)),
            (f, Set(false)),
            (tf, Set(true, false))
          )).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.booleans_.has(true).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans_.has(false).query.get.map(_ ==> List(f, tf))
          // Same as
          _ <- Ns.i.a1.booleans_.has(Seq(true)).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans_.has(Seq(false)).query.get.map(_ ==> List(f, tf))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.booleans_.has(true, false).query.get.map(_ ==> List(t, f, tf))
          // Same as
          _ <- Ns.i.a1.booleans_.has(Seq(true, false)).query.get.map(_ ==> List(t, f, tf))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.booleans_.has(Set(true)).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans_.has(Set(false)).query.get.map(_ ==> List(f, tf))
          _ <- Ns.i.a1.booleans_.has(Set(true, false)).query.get.map(_ ==> List(tf))
          // Same as
          _ <- Ns.i.a1.booleans_.has(Seq(Set(true))).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans_.has(Seq(Set(false))).query.get.map(_ ==> List(f, tf))
          _ <- Ns.i.a1.booleans_.has(Seq(Set(true, false))).query.get.map(_ ==> List(tf))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.booleans_.has(Set(true, false), Set(true)).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans_.has(Set(true, false), Set(false)).query.get.map(_ ==> List(f, tf))
          // Same as
          _ <- Ns.i.a1.booleans_.has(Seq(Set(true, false), Set(true))).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans_.has(Seq(Set(true, false), Set(false))).query.get.map(_ ==> List(f, tf))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.booleans_.has(Set(true), Set.empty[Boolean]).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans_.has(Set(false), Set.empty[Boolean]).query.get.map(_ ==> List(f, tf))
          _ <- Ns.i.a1.booleans_.has(Set(true, false), Set.empty[Boolean]).query.get.map(_ ==> List(tf))
          _ <- Ns.i.a1.booleans_.has(Seq.empty[Boolean]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.booleans_.has(Set.empty[Boolean]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.booleans_.has(Seq.empty[Set[Boolean]]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val (t, f, tf) = (1, 2, 3)
        for {
          _ <- Ns.i.booleans.insert(List(
            (t, Set(true)),
            (f, Set(false)),
            (tf, Set(true, false))
          )).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.booleans_.hasNo(true).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleans_.hasNo(false).query.get.map(_ ==> List(t))
          // Same as
          _ <- Ns.i.a1.booleans_.hasNo(Seq(true)).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleans_.hasNo(Seq(false)).query.get.map(_ ==> List(t))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.booleans_.hasNo(true, false).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.booleans_.hasNo(Seq(true, false)).query.get.map(_ ==> List())


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.booleans_.hasNo(Set(true)).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleans_.hasNo(Set(false)).query.get.map(_ ==> List(t))
          _ <- Ns.i.a1.booleans_.hasNo(Set(true, false)).query.get.map(_ ==> List(t, f))
          // Same as
          _ <- Ns.i.a1.booleans_.hasNo(Seq(Set(true))).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleans_.hasNo(Seq(Set(false))).query.get.map(_ ==> List(t))
          _ <- Ns.i.a1.booleans_.hasNo(Seq(Set(true, false))).query.get.map(_ ==> List(t, f))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.booleans_.hasNo(Set(true, false), Set(true)).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleans_.hasNo(Set(true, false), Set(false)).query.get.map(_ ==> List(t))
          // Same as
          _ <- Ns.i.a1.booleans_.hasNo(Seq(Set(true, false), Set(true))).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleans_.hasNo(Seq(Set(true, false), Set(false))).query.get.map(_ ==> List(t))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.booleans_.hasNo(Set(true), Set.empty[Boolean]).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleans_.hasNo(Set(false), Set.empty[Boolean]).query.get.map(_ ==> List(t))
          _ <- Ns.i.a1.booleans_.hasNo(Set(true, false), Set.empty[Boolean]).query.get.map(_ ==> List(t, f))
          _ <- Ns.i.a1.booleans_.hasNo(Seq.empty[Boolean]).query.get.map(_ ==> List(t, f, tf))
          _ <- Ns.i.a1.booleans_.hasNo(Set.empty[Boolean]).query.get.map(_ ==> List(t, f, tf))
          _ <- Ns.i.a1.booleans_.hasNo(Seq.empty[Set[Boolean]]).query.get.map(_ ==> List(t, f, tf))
          _ <- Ns.i.a1.booleans_.hasNo(Seq(Set.empty[Boolean])).query.get.map(_ ==> List(t, f, tf))
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
          _ <- Ns.i.booleans_?.insert(t, f, tf, x).transact
          _ <- Ns.i.a1.booleans_?.query.get.map(_ ==> List(t, f, tf, x))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val t  = (1, Some(Set(true)))
        val f  = (2, Some(Set(false)))
        val tf = (3, Some(Set(true, false)))
        val x  = (4, None)
        for {
          _ <- Ns.i.booleans_?.insert(t, f, tf, x).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.booleans_?(Some(Set(true))).query.get.map(_ ==> List(t))
          _ <- Ns.i.a1.booleans_?(Some(Set(false))).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleans_?(Some(Set(true, false))).query.get.map(_ ==> List(tf))
          // Same as
          _ <- Ns.i.a1.booleans_?(Some(Seq(Set(true)))).query.get.map(_ ==> List(t))
          _ <- Ns.i.a1.booleans_?(Some(Seq(Set(false)))).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleans_?(Some(Seq(Set(true, false)))).query.get.map(_ ==> List(tf))


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.booleans_?(Some(Seq(Set(true, false), Set(true)))).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans_?(Some(Seq(Set(true, false), Set(false)))).query.get.map(_ ==> List(f, tf))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.booleans_?(Some(Set.empty[Boolean])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.booleans_?(Some(Seq.empty[Set[Boolean]])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.booleans_?(Some(Seq(Set.empty[Boolean]))).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.booleans_?(Option.empty[Set[Boolean]]).query.get.map(_ ==> List(x))
          _ <- Ns.i.a1.booleans_?(Option.empty[Seq[Set[Boolean]]]).query.get.map(_ ==> List(x))
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val t  = (1, Some(Set(true)))
        val f  = (2, Some(Set(false)))
        val tf = (3, Some(Set(true, false)))
        val x  = (4, None)
        for {
          _ <- Ns.i.booleans_?.insert(t, f, tf, x).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.booleans_?.not(Some(Set(true))).query.get.map(_ ==> List(f, tf))
          _ <- Ns.i.a1.booleans_?.not(Some(Set(false))).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans_?.not(Some(Set(true, false))).query.get.map(_ ==> List(t, f))
          // Same as
          _ <- Ns.i.a1.booleans_?.not(Some(Seq(Set(true)))).query.get.map(_ ==> List(f, tf))
          _ <- Ns.i.a1.booleans_?.not(Some(Seq(Set(false)))).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans_?.not(Some(Seq(Set(true, false)))).query.get.map(_ ==> List(t, f))


          // AND/OR semantics with multiple Sets

          // "Not (exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.booleans_?.not(Some(Seq(Set(true, false), Set(true)))).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleans_?.not(Some(Seq(Set(true, false), Set(false)))).query.get.map(_ ==> List(t))


          // Empty Seq/Sets
          _ <- Ns.i.a1.booleans_?.not(Some(Seq(Set(true), Set.empty[Boolean]))).query.get.map(_ ==> List(f, tf))
          _ <- Ns.i.a1.booleans_?.not(Some(Seq(Set(false), Set.empty[Boolean]))).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans_?.not(Some(Seq(Set(true, false), Set.empty[Boolean]))).query.get.map(_ ==> List(t, f))
          _ <- Ns.i.a1.booleans_?.not(Some(Set.empty[Boolean])).query.get.map(_ ==> List(t, f, tf))
          _ <- Ns.i.a1.booleans_?.not(Some(Seq.empty[Set[Boolean]])).query.get.map(_ ==> List(t, f, tf))


          // None matches non-asserted values
          _ <- Ns.i.a1.booleans_?(Option.empty[Set[Boolean]]).query.get.map(_ ==> List(x))
          _ <- Ns.i.a1.booleans_?(Option.empty[Seq[Set[Boolean]]]).query.get.map(_ ==> List(x))
        } yield ()
      }


      "has" - types { implicit conn =>
        val t  = (1, Some(Set(true)))
        val f  = (2, Some(Set(false)))
        val tf = (3, Some(Set(true, false)))
        val x  = (4, None)
        for {
          _ <- Ns.i.booleans_?.insert(t, f, tf, x).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.booleans_?.has(Some(true)).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans_?.has(Some(false)).query.get.map(_ ==> List(f, tf))
          // Same as
          _ <- Ns.i.a1.booleans_?.has(Some(Seq(true))).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans_?.has(Some(Seq(false))).query.get.map(_ ==> List(f, tf))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.booleans_?.has(Some(Seq(true, false))).query.get.map(_ ==> List(t, f, tf))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.booleans_?.has(Some(Set(true))).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans_?.has(Some(Set(false))).query.get.map(_ ==> List(f, tf))
          _ <- Ns.i.a1.booleans_?.has(Some(Set(true, false))).query.get.map(_ ==> List(tf))
          // Same as
          _ <- Ns.i.a1.booleans_?.has(Some(Seq(Set(true)))).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans_?.has(Some(Seq(Set(false)))).query.get.map(_ ==> List(f, tf))
          _ <- Ns.i.a1.booleans_?.has(Some(Seq(Set(true, false)))).query.get.map(_ ==> List(tf))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.booleans_?.has(Some(Seq(Set(true, false), Set(true)))).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans_?.has(Some(Seq(Set(true, false), Set(false)))).query.get.map(_ ==> List(f, tf))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.booleans_?.has(Some(Seq.empty[Boolean])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.booleans_?.has(Some(Set.empty[Boolean])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.booleans_?.has(Some(Seq.empty[Set[Boolean]])).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.booleans_?.has(Option.empty[Boolean]).query.get.map(_ ==> List(x))
          _ <- Ns.i.a1.booleans_?.has(Option.empty[Seq[Boolean]]).query.get.map(_ ==> List(x))
          _ <- Ns.i.a1.booleans_?.has(Option.empty[Set[Boolean]]).query.get.map(_ ==> List(x))
          _ <- Ns.i.a1.booleans_?.has(Option.empty[Seq[Set[Boolean]]]).query.get.map(_ ==> List(x))
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val t  = (1, Some(Set(true)))
        val f  = (2, Some(Set(false)))
        val tf = (3, Some(Set(true, false)))
        val x  = (4, None)
        for {
          _ <- Ns.i.booleans_?.insert(t, f, tf, x).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.booleans_?.hasNo(Some(true)).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleans_?.hasNo(Some(false)).query.get.map(_ ==> List(t))
          // Same as
          _ <- Ns.i.a1.booleans_?.hasNo(Some(Seq(true))).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleans_?.hasNo(Some(Seq(false))).query.get.map(_ ==> List(t))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.booleans_?.hasNo(Some(Seq(true, false))).query.get.map(_ ==> List())


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.booleans_?.hasNo(Some(Set(true))).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleans_?.hasNo(Some(Set(false))).query.get.map(_ ==> List(t))
          _ <- Ns.i.a1.booleans_?.hasNo(Some(Set(true, false))).query.get.map(_ ==> List(t, f))
          // Same as
          _ <- Ns.i.a1.booleans_?.hasNo(Some(Seq(Set(true)))).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleans_?.hasNo(Some(Seq(Set(false)))).query.get.map(_ ==> List(t))
          _ <- Ns.i.a1.booleans_?.hasNo(Some(Seq(Set(true, false)))).query.get.map(_ ==> List(t, f))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.booleans_?.hasNo(Some(Seq(Set(true, false), Set(true)))).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleans_?.hasNo(Some(Seq(Set(true, false), Set(false)))).query.get.map(_ ==> List(t))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.booleans_?.hasNo(Some(Seq.empty[Boolean])).query.get.map(_ ==> List(t, f, tf))
          _ <- Ns.i.a1.booleans_?.hasNo(Some(Set.empty[Boolean])).query.get.map(_ ==> List(t, f, tf))
          _ <- Ns.i.a1.booleans_?.hasNo(Some(Seq.empty[Set[Boolean]])).query.get.map(_ ==> List(t, f, tf))
          _ <- Ns.i.a1.booleans_?.hasNo(Some(Seq(Set.empty[Boolean]))).query.get.map(_ ==> List(t, f, tf))


          // Negating None returns all asserted
          _ <- Ns.i.a1.booleans_?.hasNo(Option.empty[Boolean]).query.get.map(_ ==> List(t, f, tf))
          _ <- Ns.i.a1.booleans_?.hasNo(Option.empty[Seq[Boolean]]).query.get.map(_ ==> List(t, f, tf))
          _ <- Ns.i.a1.booleans_?.hasNo(Option.empty[Set[Boolean]]).query.get.map(_ ==> List(t, f, tf))
          _ <- Ns.i.a1.booleans_?.hasNo(Option.empty[Seq[Set[Boolean]]]).query.get.map(_ ==> List(t, f, tf))
        } yield ()
      }
    }
  }
}