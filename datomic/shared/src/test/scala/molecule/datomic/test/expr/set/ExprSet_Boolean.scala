// GENERATED CODE ********************************
package molecule.datomic.test.expr.set

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.datomic.setup.DatomicTestSuite
import molecule.datomic.async._
import utest._
import scala.concurrent.Future


object ExprSet_Boolean extends DatomicTestSuite {


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


      "apply" - types { implicit conn =>
        val t  = (1, Set(true))
        val f  = (2, Set(false))
        val tf = (3, Set(true, false))
        for {
          _ <- Ns.i.booleans.insert(List(t, f, tf)).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.booleans(true).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans(false).query.get.map(_ ==> List(f, tf))
          // Same as
          _ <- Ns.i.a1.booleans(Seq(true)).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans(Seq(false)).query.get.map(_ ==> List(f, tf))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.booleans(true, false).query.get.map(_ ==> List(t, f, tf))
          // Same as
          _ <- Ns.i.a1.booleans(Seq(true, false)).query.get.map(_ ==> List(t, f, tf))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.booleans(Set(true)).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans(Set(false)).query.get.map(_ ==> List(f, tf))
          _ <- Ns.i.a1.booleans(Set(true, false)).query.get.map(_ ==> List(tf))
          // Same as
          _ <- Ns.i.a1.booleans(Seq(Set(true))).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans(Seq(Set(false))).query.get.map(_ ==> List(f, tf))
          _ <- Ns.i.a1.booleans(Seq(Set(true, false))).query.get.map(_ ==> List(tf))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.booleans(Set(true, false), Set(true)).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans(Set(true, false), Set(false)).query.get.map(_ ==> List(f, tf))
          // Same as
          _ <- Ns.i.a1.booleans(Seq(Set(true, false), Set(true))).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans(Seq(Set(true, false), Set(false))).query.get.map(_ ==> List(f, tf))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.booleans(Set(true), Set.empty[Boolean]).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans(Set(false), Set.empty[Boolean]).query.get.map(_ ==> List(f, tf))
          _ <- Ns.i.a1.booleans(Set(true, false), Set.empty[Boolean]).query.get.map(_ ==> List(tf))
          _ <- Ns.i.a1.booleans(Seq.empty[Boolean]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.booleans(Set.empty[Boolean]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.booleans(Seq.empty[Set[Boolean]]).query.get.map(_ ==> List())
        } yield ()
      }


      "not" - types { implicit conn =>
        val t  = (1, Set(true))
        val f  = (2, Set(false))
        val tf = (3, Set(true, false))
        for {
          _ <- Ns.i.booleans.insert(List(t, f, tf)).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.booleans.not(true).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleans.not(false).query.get.map(_ ==> List(t))
          // Same as
          _ <- Ns.i.a1.booleans.not(Seq(true)).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleans.not(Seq(false)).query.get.map(_ ==> List(t))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.booleans.not(true, false).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.booleans.not(Seq(true, false)).query.get.map(_ ==> List())


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.booleans.not(Set(true)).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleans.not(Set(false)).query.get.map(_ ==> List(t))
          _ <- Ns.i.a1.booleans.not(Set(true, false)).query.get.map(_ ==> List(t, f))
          // Same as
          _ <- Ns.i.a1.booleans.not(Seq(Set(true))).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleans.not(Seq(Set(false))).query.get.map(_ ==> List(t))
          _ <- Ns.i.a1.booleans.not(Seq(Set(true, false))).query.get.map(_ ==> List(t, f))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.booleans.not(Set(true, false), Set(true)).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleans.not(Set(true, false), Set(false)).query.get.map(_ ==> List(t))
          // Same as
          _ <- Ns.i.a1.booleans.not(Seq(Set(true, false), Set(true))).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleans.not(Seq(Set(true, false), Set(false))).query.get.map(_ ==> List(t))

          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.booleans.not(Set(true), Set.empty[Boolean]).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleans.not(Set(false), Set.empty[Boolean]).query.get.map(_ ==> List(t))
          _ <- Ns.i.a1.booleans.not(Set(true, false), Set.empty[Boolean]).query.get.map(_ ==> List(t, f))
          _ <- Ns.i.a1.booleans.not(Seq.empty[Boolean]).query.get.map(_ ==> List(t, f, tf))
          _ <- Ns.i.a1.booleans.not(Set.empty[Boolean]).query.get.map(_ ==> List(t, f, tf))
          _ <- Ns.i.a1.booleans.not(Seq.empty[Set[Boolean]]).query.get.map(_ ==> List(t, f, tf))
          _ <- Ns.i.a1.booleans.not(Seq(Set.empty[Boolean])).query.get.map(_ ==> List(t, f, tf))
        } yield ()
      }


      "==" - types { implicit conn =>
        val t  = (1, Set(true))
        val f  = (2, Set(false))
        val tf = (3, Set(true, false))
        for {
          _ <- Ns.i.booleans.insert(List(t, f, tf)).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.booleans.==(Set(true)).query.get.map(_ ==> List(t))
          _ <- Ns.i.a1.booleans.==(Set(false)).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleans.==(Set(true, false)).query.get.map(_ ==> List(tf))
          // Same as
          _ <- Ns.i.a1.booleans.==(Seq(Set(true))).query.get.map(_ ==> List(t))
          _ <- Ns.i.a1.booleans.==(Seq(Set(false))).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleans.==(Seq(Set(true, false))).query.get.map(_ ==> List(tf))


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.booleans.==(Set(true, false), Set(true)).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans.==(Set(true, false), Set(false)).query.get.map(_ ==> List(f, tf))
          // Same as
          _ <- Ns.i.a1.booleans.==(Seq(Set(true, false), Set(true))).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans.==(Seq(Set(true, false), Set(false))).query.get.map(_ ==> List(f, tf))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.booleans.==(Set(true), Set.empty[Boolean]).query.get.map(_ ==> List(t))
          _ <- Ns.i.a1.booleans.==(Set(false), Set.empty[Boolean]).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleans.==(Set(true, false), Set.empty[Boolean]).query.get.map(_ ==> List(tf))
          _ <- Ns.i.a1.booleans.==(Set.empty[Boolean]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.booleans.==(Seq.empty[Set[Boolean]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.booleans.==(Seq(Set.empty[Boolean])).query.get.map(_ ==> List())
        } yield ()
      }


      "!=" - types { implicit conn =>
        val t  = (1, Set(true))
        val f  = (2, Set(false))
        val tf = (3, Set(true, false))
        for {
          _ <- Ns.i.booleans.insert(List(t, f, tf)).transact

          // Exact Set non-matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.booleans.!=(Set(true)).query.get.map(_ ==> List(f, tf))
          _ <- Ns.i.a1.booleans.!=(Set(false)).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans.!=(Set(true, false)).query.get.map(_ ==> List(t, f))
          // Same as
          _ <- Ns.i.a1.booleans.!=(Seq(Set(true))).query.get.map(_ ==> List(f, tf))
          _ <- Ns.i.a1.booleans.!=(Seq(Set(false))).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans.!=(Seq(Set(true, false))).query.get.map(_ ==> List(t, f))


          // AND/OR semantics with multiple Sets

          // "Not (exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.booleans.!=(Set(true, false), Set(true)).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleans.!=(Set(true, false), Set(false)).query.get.map(_ ==> List(t))
          // Same as
          _ <- Ns.i.a1.booleans.!=(Seq(Set(true, false), Set(true))).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleans.!=(Seq(Set(true, false), Set(false))).query.get.map(_ ==> List(t))


          // Empty Seq/Sets
          _ <- Ns.i.a1.booleans.!=(Seq(Set(true), Set.empty[Boolean])).query.get.map(_ ==> List(f, tf))
          _ <- Ns.i.a1.booleans.!=(Seq(Set(false), Set.empty[Boolean])).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans.!=(Seq(Set(true, false), Set.empty[Boolean])).query.get.map(_ ==> List(t, f))
          _ <- Ns.i.a1.booleans.!=(Set.empty[Boolean]).query.get.map(_ ==> List(t, f, tf))
          _ <- Ns.i.a1.booleans.!=(Seq.empty[Set[Boolean]]).query.get.map(_ ==> List(t, f, tf))
        } yield ()
      }


      "compare" - types { implicit conn =>
        val t  = (1, Set(true))
        val f  = (2, Set(false))
        val tf = (3, Set(true, false))
        for {
          _ <- Ns.i.booleans.insert(List(t, f, tf)).transact

          _ <- Ns.i.a1.booleans.<(true).query.get.map(_ ==> List(f, tf))
          _ <- Ns.i.a1.booleans.<(false).query.get.map(_ ==> List())
          _ <- Ns.i.a1.booleans.<=(true).query.get.map(_ ==> List(t, f, tf))
          _ <- Ns.i.a1.booleans.<=(false).query.get.map(_ ==> List(f, tf))
          _ <- Ns.i.a1.booleans.>(true).query.get.map(_ ==> List())
          _ <- Ns.i.a1.booleans.>(false).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans.>=(true).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans.>=(false).query.get.map(_ ==> List(t, f, tf))
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


      "apply" - types { implicit conn =>
        val (t, f, tf) = (1, 2, 3)
        for {
          _ <- Ns.i.booleans.insert(List(
            (t, Set(true)),
            (f, Set(false)),
            (tf, Set(true, false))
          )).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.booleans_(true).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans_(false).query.get.map(_ ==> List(f, tf))
          // Same as
          _ <- Ns.i.a1.booleans_(Seq(true)).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans_(Seq(false)).query.get.map(_ ==> List(f, tf))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.booleans_(true, false).query.get.map(_ ==> List(t, f, tf))
          // Same as
          _ <- Ns.i.a1.booleans_(Seq(true, false)).query.get.map(_ ==> List(t, f, tf))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.booleans_(Set(true)).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans_(Set(false)).query.get.map(_ ==> List(f, tf))
          _ <- Ns.i.a1.booleans_(Set(true, false)).query.get.map(_ ==> List(tf))
          // Same as
          _ <- Ns.i.a1.booleans_(Seq(Set(true))).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans_(Seq(Set(false))).query.get.map(_ ==> List(f, tf))
          _ <- Ns.i.a1.booleans_(Seq(Set(true, false))).query.get.map(_ ==> List(tf))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.booleans_(Set(true, false), Set(true)).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans_(Set(true, false), Set(false)).query.get.map(_ ==> List(f, tf))
          // Same as
          _ <- Ns.i.a1.booleans_(Seq(Set(true, false), Set(true))).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans_(Seq(Set(true, false), Set(false))).query.get.map(_ ==> List(f, tf))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.booleans_(Set(true), Set.empty[Boolean]).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans_(Set(false), Set.empty[Boolean]).query.get.map(_ ==> List(f, tf))
          _ <- Ns.i.a1.booleans_(Set(true, false), Set.empty[Boolean]).query.get.map(_ ==> List(tf))
          _ <- Ns.i.a1.booleans_(Seq.empty[Boolean]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.booleans_(Set.empty[Boolean]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.booleans_(Seq.empty[Set[Boolean]]).query.get.map(_ ==> List())
        } yield ()
      }


      "not" - types { implicit conn =>
        val (t, f, tf) = (1, 2, 3)
        for {
          _ <- Ns.i.booleans.insert(List(
            (t, Set(true)),
            (f, Set(false)),
            (tf, Set(true, false))
          )).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.booleans_.not(true).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleans_.not(false).query.get.map(_ ==> List(t))
          // Same as
          _ <- Ns.i.a1.booleans_.not(Seq(true)).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleans_.not(Seq(false)).query.get.map(_ ==> List(t))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.booleans_.not(true, false).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.booleans_.not(Seq(true, false)).query.get.map(_ ==> List())


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.booleans_.not(Set(true)).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleans_.not(Set(false)).query.get.map(_ ==> List(t))
          _ <- Ns.i.a1.booleans_.not(Set(true, false)).query.get.map(_ ==> List(t, f))
          // Same as
          _ <- Ns.i.a1.booleans_.not(Seq(Set(true))).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleans_.not(Seq(Set(false))).query.get.map(_ ==> List(t))
          _ <- Ns.i.a1.booleans_.not(Seq(Set(true, false))).query.get.map(_ ==> List(t, f))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.booleans_.not(Set(true, false), Set(true)).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleans_.not(Set(true, false), Set(false)).query.get.map(_ ==> List(t))
          // Same as
          _ <- Ns.i.a1.booleans_.not(Seq(Set(true, false), Set(true))).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleans_.not(Seq(Set(true, false), Set(false))).query.get.map(_ ==> List(t))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.booleans_.not(Set(true), Set.empty[Boolean]).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleans_.not(Set(false), Set.empty[Boolean]).query.get.map(_ ==> List(t))
          _ <- Ns.i.a1.booleans_.not(Set(true, false), Set.empty[Boolean]).query.get.map(_ ==> List(t, f))
          _ <- Ns.i.a1.booleans_.not(Seq.empty[Boolean]).query.get.map(_ ==> List(t, f, tf))
          _ <- Ns.i.a1.booleans_.not(Set.empty[Boolean]).query.get.map(_ ==> List(t, f, tf))
          _ <- Ns.i.a1.booleans_.not(Seq.empty[Set[Boolean]]).query.get.map(_ ==> List(t, f, tf))
          _ <- Ns.i.a1.booleans_.not(Seq(Set.empty[Boolean])).query.get.map(_ ==> List(t, f, tf))
        } yield ()
      }


      "==" - types { implicit conn =>
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
          _ <- Ns.i.a1.booleans_.==(Set(true)).query.get.map(_ ==> List(t))
          _ <- Ns.i.a1.booleans_.==(Set(false)).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleans_.==(Set(true, false)).query.get.map(_ ==> List(tf))
          // Same as
          _ <- Ns.i.a1.booleans_.==(Seq(Set(true))).query.get.map(_ ==> List(t))
          _ <- Ns.i.a1.booleans_.==(Seq(Set(false))).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleans_.==(Seq(Set(true, false))).query.get.map(_ ==> List(tf))


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.booleans_.==(Set(true, false), Set(true)).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans_.==(Set(true, false), Set(false)).query.get.map(_ ==> List(f, tf))
          // Same as
          _ <- Ns.i.a1.booleans_.==(Seq(Set(true, false), Set(true))).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans_.==(Seq(Set(true, false), Set(false))).query.get.map(_ ==> List(f, tf))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.booleans_.==(Set(true), Set.empty[Boolean]).query.get.map(_ ==> List(t))
          _ <- Ns.i.a1.booleans_.==(Set(false), Set.empty[Boolean]).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleans_.==(Set(true, false), Set.empty[Boolean]).query.get.map(_ ==> List(tf))
          _ <- Ns.i.a1.booleans_.==(Set.empty[Boolean]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.booleans_.==(Seq.empty[Set[Boolean]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.booleans_.==(Seq(Set.empty[Boolean])).query.get.map(_ ==> List())
        } yield ()
      }


      "!=" - types { implicit conn =>
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
          _ <- Ns.i.a1.booleans_.!=(Set(true)).query.get.map(_ ==> List(f, tf))
          _ <- Ns.i.a1.booleans_.!=(Set(false)).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans_.!=(Set(true, false)).query.get.map(_ ==> List(t, f))
          // Same as
          _ <- Ns.i.a1.booleans_.!=(Seq(Set(true))).query.get.map(_ ==> List(f, tf))
          _ <- Ns.i.a1.booleans_.!=(Seq(Set(false))).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans_.!=(Seq(Set(true, false))).query.get.map(_ ==> List(t, f))


          // AND/OR semantics with multiple Sets

          // "Not (exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.booleans_.!=(Set(true, false), Set(true)).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleans_.!=(Set(true, false), Set(false)).query.get.map(_ ==> List(t))
          // Same as
          _ <- Ns.i.a1.booleans_.!=(Seq(Set(true, false), Set(true))).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleans_.!=(Seq(Set(true, false), Set(false))).query.get.map(_ ==> List(t))


          // Empty Seq/Sets
          _ <- Ns.i.a1.booleans_.!=(Seq(Set(true), Set.empty[Boolean])).query.get.map(_ ==> List(f, tf))
          _ <- Ns.i.a1.booleans_.!=(Seq(Set(false), Set.empty[Boolean])).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans_.!=(Seq(Set(true, false), Set.empty[Boolean])).query.get.map(_ ==> List(t, f))
          _ <- Ns.i.a1.booleans_.!=(Set.empty[Boolean]).query.get.map(_ ==> List(t, f, tf))
          _ <- Ns.i.a1.booleans_.!=(Seq.empty[Set[Boolean]]).query.get.map(_ ==> List(t, f, tf))
        } yield ()
      }


      "compare" - types { implicit conn =>
        val (t, f, tf) = (1, 2, 3)
        for {
          _ <- Ns.i.booleans.insert(List(
            (t, Set(true)),
            (f, Set(false)),
            (tf, Set(true, false))
          )).transact

          _ <- Ns.i.a1.booleans_.<(true).query.get.map(_ ==> List(f, tf))
          _ <- Ns.i.a1.booleans_.<(false).query.get.map(_ ==> List())
          _ <- Ns.i.a1.booleans_.<=(true).query.get.map(_ ==> List(t, f, tf))
          _ <- Ns.i.a1.booleans_.<=(false).query.get.map(_ ==> List(f, tf))
          _ <- Ns.i.a1.booleans_.>(true).query.get.map(_ ==> List())
          _ <- Ns.i.a1.booleans_.>(false).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans_.>=(true).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans_.>=(false).query.get.map(_ ==> List(t, f, tf))
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

          // Bug in Datomic free version where entity api doesn't return `false` for Boolean attributes
          // - fixed later:
          // https://docs.datomic.com/on-prem/changes.html#0.9.6045
          // Works when using Datomic pro
          _ <- if (!useFree) {
            Ns.i.a1.booleans_?.query.get.map(_ ==> List(t, f, tf, x))
          } else Future.unit
        } yield ()
      }


      "apply" - types { implicit conn =>
        val t  = (1, Some(Set(true)))
        val f  = (2, Some(Set(false)))
        val tf = (3, Some(Set(true, false)))
        val x  = (4, None)
        for {
          _ <- Ns.i.booleans_?.insert(t, f, tf, x).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.booleans_?(Some(true)).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans_?(Some(false)).query.get.map(_ ==> List(f, tf))
          // Same as
          _ <- Ns.i.a1.booleans_?(Some(Seq(true))).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans_?(Some(Seq(false))).query.get.map(_ ==> List(f, tf))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.booleans_?(Some(Seq(true, false))).query.get.map(_ ==> List(t, f, tf))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.booleans_?(Some(Set(true))).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans_?(Some(Set(false))).query.get.map(_ ==> List(f, tf))
          _ <- Ns.i.a1.booleans_?(Some(Set(true, false))).query.get.map(_ ==> List(tf))
          // Same as
          _ <- Ns.i.a1.booleans_?(Some(Seq(Set(true)))).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans_?(Some(Seq(Set(false)))).query.get.map(_ ==> List(f, tf))
          _ <- Ns.i.a1.booleans_?(Some(Seq(Set(true, false)))).query.get.map(_ ==> List(tf))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.booleans_?(Some(Seq(Set(true, false), Set(true)))).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans_?(Some(Seq(Set(true, false), Set(false)))).query.get.map(_ ==> List(f, tf))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.booleans_?(Some(Seq.empty[Boolean])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.booleans_?(Some(Set.empty[Boolean])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.booleans_?(Some(Seq.empty[Set[Boolean]])).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.booleans_?(Option.empty[Boolean]).query.get.map(_ ==> List(x))
          _ <- Ns.i.a1.booleans_?(Option.empty[Seq[Boolean]]).query.get.map(_ ==> List(x))
          _ <- Ns.i.a1.booleans_?(Option.empty[Set[Boolean]]).query.get.map(_ ==> List(x))
          _ <- Ns.i.a1.booleans_?(Option.empty[Seq[Set[Boolean]]]).query.get.map(_ ==> List(x))
        } yield ()
      }


      "not" - types { implicit conn =>
        val t  = (1, Some(Set(true)))
        val f  = (2, Some(Set(false)))
        val tf = (3, Some(Set(true, false)))
        val x  = (4, None)
        for {
          _ <- Ns.i.booleans_?.insert(t, f, tf, x).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.booleans_?.not(Some(true)).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleans_?.not(Some(false)).query.get.map(_ ==> List(t))
          // Same as
          _ <- Ns.i.a1.booleans_?.not(Some(Seq(true))).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleans_?.not(Some(Seq(false))).query.get.map(_ ==> List(t))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.booleans_?.not(Some(Seq(true, false))).query.get.map(_ ==> List())


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.booleans_?.not(Some(Set(true))).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleans_?.not(Some(Set(false))).query.get.map(_ ==> List(t))
          _ <- Ns.i.a1.booleans_?.not(Some(Set(true, false))).query.get.map(_ ==> List(t, f))
          // Same as
          _ <- Ns.i.a1.booleans_?.not(Some(Seq(Set(true)))).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleans_?.not(Some(Seq(Set(false)))).query.get.map(_ ==> List(t))
          _ <- Ns.i.a1.booleans_?.not(Some(Seq(Set(true, false)))).query.get.map(_ ==> List(t, f))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.booleans_?.not(Some(Seq(Set(true, false), Set(true)))).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleans_?.not(Some(Seq(Set(true, false), Set(false)))).query.get.map(_ ==> List(t))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.booleans_?.not(Some(Seq.empty[Boolean])).query.get.map(_ ==> List(t, f, tf))
          _ <- Ns.i.a1.booleans_?.not(Some(Set.empty[Boolean])).query.get.map(_ ==> List(t, f, tf))
          _ <- Ns.i.a1.booleans_?.not(Some(Seq.empty[Set[Boolean]])).query.get.map(_ ==> List(t, f, tf))
          _ <- Ns.i.a1.booleans_?.not(Some(Seq(Set.empty[Boolean]))).query.get.map(_ ==> List(t, f, tf))


          // Negating None returns all asserted
          _ <- Ns.i.a1.booleans_?.not(Option.empty[Boolean]).query.get.map(_ ==> List(t, f, tf))
          _ <- Ns.i.a1.booleans_?.not(Option.empty[Seq[Boolean]]).query.get.map(_ ==> List(t, f, tf))
          _ <- Ns.i.a1.booleans_?.not(Option.empty[Set[Boolean]]).query.get.map(_ ==> List(t, f, tf))
          _ <- Ns.i.a1.booleans_?.not(Option.empty[Seq[Set[Boolean]]]).query.get.map(_ ==> List(t, f, tf))
        } yield ()
      }


      "==" - types { implicit conn =>
        val t  = (1, Some(Set(true)))
        val f  = (2, Some(Set(false)))
        val tf = (3, Some(Set(true, false)))
        val x  = (4, None)
        for {
          _ <- Ns.i.booleans_?.insert(t, f, tf, x).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.booleans_?.==(Some(Set(true))).query.get.map(_ ==> List(t))
          _ <- Ns.i.a1.booleans_?.==(Some(Set(false))).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleans_?.==(Some(Set(true, false))).query.get.map(_ ==> List(tf))
          // Same as
          _ <- Ns.i.a1.booleans_?.==(Some(Seq(Set(true)))).query.get.map(_ ==> List(t))
          _ <- Ns.i.a1.booleans_?.==(Some(Seq(Set(false)))).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleans_?.==(Some(Seq(Set(true, false)))).query.get.map(_ ==> List(tf))


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.booleans_?.==(Some(Seq(Set(true, false), Set(true)))).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans_?.==(Some(Seq(Set(true, false), Set(false)))).query.get.map(_ ==> List(f, tf))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.booleans_?.==(Some(Set.empty[Boolean])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.booleans_?.==(Some(Seq.empty[Set[Boolean]])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.booleans_?.==(Some(Seq(Set.empty[Boolean]))).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.booleans_?.==(Option.empty[Set[Boolean]]).query.get.map(_ ==> List(x))
          _ <- Ns.i.a1.booleans_?.==(Option.empty[Seq[Set[Boolean]]]).query.get.map(_ ==> List(x))
        } yield ()
      }


      "!=" - types { implicit conn =>
        val t  = (1, Some(Set(true)))
        val f  = (2, Some(Set(false)))
        val tf = (3, Some(Set(true, false)))
        val x  = (4, None)
        for {
          _ <- Ns.i.booleans_?.insert(t, f, tf, x).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.booleans_?.!=(Some(Set(true))).query.get.map(_ ==> List(f, tf))
          _ <- Ns.i.a1.booleans_?.!=(Some(Set(false))).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans_?.!=(Some(Set(true, false))).query.get.map(_ ==> List(t, f))
          // Same as
          _ <- Ns.i.a1.booleans_?.!=(Some(Seq(Set(true)))).query.get.map(_ ==> List(f, tf))
          _ <- Ns.i.a1.booleans_?.!=(Some(Seq(Set(false)))).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans_?.!=(Some(Seq(Set(true, false)))).query.get.map(_ ==> List(t, f))


          // AND/OR semantics with multiple Sets

          // "Not (exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.booleans_?.!=(Some(Seq(Set(true, false), Set(true)))).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleans_?.!=(Some(Seq(Set(true, false), Set(false)))).query.get.map(_ ==> List(t))


          // Empty Seq/Sets
          _ <- Ns.i.a1.booleans_?.!=(Some(Seq(Set(true), Set.empty[Boolean]))).query.get.map(_ ==> List(f, tf))
          _ <- Ns.i.a1.booleans_?.!=(Some(Seq(Set(false), Set.empty[Boolean]))).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans_?.!=(Some(Seq(Set(true, false), Set.empty[Boolean]))).query.get.map(_ ==> List(t, f))
          _ <- Ns.i.a1.booleans_?.!=(Some(Set.empty[Boolean])).query.get.map(_ ==> List(t, f, tf))
          _ <- Ns.i.a1.booleans_?.!=(Some(Seq.empty[Set[Boolean]])).query.get.map(_ ==> List(t, f, tf))


          // None matches non-asserted values
          _ <- Ns.i.a1.booleans_?.==(Option.empty[Set[Boolean]]).query.get.map(_ ==> List(x))
          _ <- Ns.i.a1.booleans_?.==(Option.empty[Seq[Set[Boolean]]]).query.get.map(_ ==> List(x))
        } yield ()
      }


      "compare" - types { implicit conn =>
        val t  = (1, Some(Set(true)))
        val f  = (2, Some(Set(false)))
        val tf = (3, Some(Set(true, false)))
        val x  = (4, None)
        for {
          _ <- Ns.i.booleans_?.insert(t, f, tf, x).transact

          _ <- Ns.i.a1.booleans_?.<(Some(true)).query.get.map(_ ==> List(f, tf))
          _ <- Ns.i.a1.booleans_?.<(Some(false)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.booleans_?.<=(Some(true)).query.get.map(_ ==> List(t, f, tf))
          _ <- Ns.i.a1.booleans_?.<=(Some(false)).query.get.map(_ ==> List(f, tf))
          _ <- Ns.i.a1.booleans_?.>(Some(true)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.booleans_?.>(Some(false)).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans_?.>=(Some(true)).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleans_?.>=(Some(false)).query.get.map(_ ==> List(t, f, tf))


          // None matches any asserted values
          _ <- Ns.i.a1.booleans_?.<(None).query.get.map(_ ==> List(t, f, tf))
          _ <- Ns.i.a1.booleans_?.>(None).query.get.map(_ ==> List(t, f, tf))
          _ <- Ns.i.a1.booleans_?.<=(None).query.get.map(_ ==> List(t, f, tf))
          _ <- Ns.i.a1.booleans_?.>=(None).query.get.map(_ ==> List(t, f, tf))
        } yield ()
      }
    }
  }
}