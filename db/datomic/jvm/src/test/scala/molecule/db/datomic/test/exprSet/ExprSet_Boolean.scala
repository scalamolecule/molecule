// GENERATED CODE ********************************
package molecule.db.datomic.test.exprSet

import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._


object ExprSet_Boolean extends DatomicTestSuite {


  lazy val tests = Tests {

    "Mandatory" - {

      "attr" - types { implicit conn =>
        val t  = (1, Set(true))
        val f  = (2, Set(false))
        val tf = (3, Set(true, false))
        Ns.i.booleans.insert(List(t, f, tf)).transact

        Ns.i.a1.booleans.query.get ==> List(t, f, tf)
      }


      "apply" - types { implicit conn =>
        val t  = (1, Set(true))
        val f  = (2, Set(false))
        val tf = (3, Set(true, false))
        Ns.i.booleans.insert(List(t, f, tf)).transact

        // Sets with one or more values matching

        // "Has this value"
        Ns.i.a1.booleans(true).query.get ==> List(t, tf)
        Ns.i.a1.booleans(false).query.get ==> List(f, tf)
        // Same as
        Ns.i.a1.booleans(Seq(true)).query.get ==> List(t, tf)
        Ns.i.a1.booleans(Seq(false)).query.get ==> List(f, tf)


        // OR semantics when multiple values

        // "Has this OR that"
        Ns.i.a1.booleans(true, false).query.get ==> List(t, f, tf)
        // Same as
        Ns.i.a1.booleans(Seq(true, false)).query.get ==> List(t, f, tf)


        // AND semantics when multiple values in a _Set_

        // "Has this AND that"
        Ns.i.a1.booleans(Set(true)).query.get ==> List(t, tf)
        Ns.i.a1.booleans(Set(false)).query.get ==> List(f, tf)
        Ns.i.a1.booleans(Set(true, false)).query.get ==> List(tf)
        // Same as
        Ns.i.a1.booleans(Seq(Set(true))).query.get ==> List(t, tf)
        Ns.i.a1.booleans(Seq(Set(false))).query.get ==> List(f, tf)
        Ns.i.a1.booleans(Seq(Set(true, false))).query.get ==> List(tf)


        // AND/OR semantics with multiple Sets

        // "(has this AND that) OR (has this AND that)"
        Ns.i.a1.booleans(Set(true, false), Set(true)).query.get ==> List(t, tf)
        Ns.i.a1.booleans(Set(true, false), Set(false)).query.get ==> List(f, tf)
        // Same as
        Ns.i.a1.booleans(Seq(Set(true, false), Set(true))).query.get ==> List(t, tf)
        Ns.i.a1.booleans(Seq(Set(true, false), Set(false))).query.get ==> List(f, tf)


        // Empty Seq/Sets match nothing
        Ns.i.a1.booleans(Set(true), Set.empty[Boolean]).query.get ==> List(t, tf)
        Ns.i.a1.booleans(Set(false), Set.empty[Boolean]).query.get ==> List(f, tf)
        Ns.i.a1.booleans(Set(true, false), Set.empty[Boolean]).query.get ==> List(tf)
        Ns.i.a1.booleans(Seq.empty[Boolean]).query.get ==> List()
        Ns.i.a1.booleans(Set.empty[Boolean]).query.get ==> List()
        Ns.i.a1.booleans(Seq.empty[Set[Boolean]]).query.get ==> List()
      }


      "not" - types { implicit conn =>
        val t  = (1, Set(true))
        val f  = (2, Set(false))
        val tf = (3, Set(true, false))
        Ns.i.booleans.insert(List(t, f, tf)).transact

        // Sets without one or more values matching

        // "Doesn't have this value"
        Ns.i.a1.booleans.not(true).query.get ==> List(f)
        Ns.i.a1.booleans.not(false).query.get ==> List(t)
        // Same as
        Ns.i.a1.booleans.not(Seq(true)).query.get ==> List(f)
        Ns.i.a1.booleans.not(Seq(false)).query.get ==> List(t)


        // OR semantics when multiple values

        // "Not (has this OR that)"
        Ns.i.a1.booleans.not(true, false).query.get ==> List()
        // Same as
        Ns.i.a1.booleans.not(Seq(true, false)).query.get ==> List()


        // AND semantics when multiple values in a _Set_

        // "Not (has this AND that)"
        Ns.i.a1.booleans.not(Set(true)).query.get ==> List(f)
        Ns.i.a1.booleans.not(Set(false)).query.get ==> List(t)
        Ns.i.a1.booleans.not(Set(true, false)).query.get ==> List(t, f)
        // Same as
        Ns.i.a1.booleans.not(Seq(Set(true))).query.get ==> List(f)
        Ns.i.a1.booleans.not(Seq(Set(false))).query.get ==> List(t)
        Ns.i.a1.booleans.not(Seq(Set(true, false))).query.get ==> List(t, f)


        // AND/OR semantics with multiple Sets

        // "Not ((has this AND that) OR (has this AND that))"
        Ns.i.a1.booleans.not(Set(true, false), Set(true)).query.get ==> List(f)
        Ns.i.a1.booleans.not(Set(true, false), Set(false)).query.get ==> List(t)
        // Same as
        Ns.i.a1.booleans.not(Seq(Set(true, false), Set(true))).query.get ==> List(f)
        Ns.i.a1.booleans.not(Seq(Set(true, false), Set(false))).query.get ==> List(t)

        // Negating empty Seqs/Sets has no effect
        Ns.i.a1.booleans.not(Set(true), Set.empty[Boolean]).query.get ==> List(f)
        Ns.i.a1.booleans.not(Set(false), Set.empty[Boolean]).query.get ==> List(t)
        Ns.i.a1.booleans.not(Set(true, false), Set.empty[Boolean]).query.get ==> List(t, f)
        Ns.i.a1.booleans.not(Seq.empty[Boolean]).query.get ==> List(t, f, tf)
        Ns.i.a1.booleans.not(Set.empty[Boolean]).query.get ==> List(t, f, tf)
        Ns.i.a1.booleans.not(Seq.empty[Set[Boolean]]).query.get ==> List(t, f, tf)
        Ns.i.a1.booleans.not(Seq(Set.empty[Boolean])).query.get ==> List(t, f, tf)
      }


      "==" - types { implicit conn =>
        val t  = (1, Set(true))
        val f  = (2, Set(false))
        val tf = (3, Set(true, false))
        Ns.i.booleans.insert(List(t, f, tf)).transact

        // Exact Set matches

        // AND semantics
        // "Is exactly this AND that"
        Ns.i.a1.booleans.==(Set(true)).query.get ==> List(t)
        Ns.i.a1.booleans.==(Set(false)).query.get ==> List(f)
        Ns.i.a1.booleans.==(Set(true, false)).query.get ==> List(tf)
        // Same as
        Ns.i.a1.booleans.==(Seq(Set(true))).query.get ==> List(t)
        Ns.i.a1.booleans.==(Seq(Set(false))).query.get ==> List(f)
        Ns.i.a1.booleans.==(Seq(Set(true, false))).query.get ==> List(tf)


        // AND/OR semantics with multiple Sets

        // "(exactly this AND that) OR (exactly this AND that)"
        Ns.i.a1.booleans.==(Set(true, false), Set(true)).query.get ==> List(t, tf)
        Ns.i.a1.booleans.==(Set(true, false), Set(false)).query.get ==> List(f, tf)
        // Same as
        Ns.i.a1.booleans.==(Seq(Set(true, false), Set(true))).query.get ==> List(t, tf)
        Ns.i.a1.booleans.==(Seq(Set(true, false), Set(false))).query.get ==> List(f, tf)


        // Empty Seq/Sets match nothing
        Ns.i.a1.booleans.==(Set(true), Set.empty[Boolean]).query.get ==> List(t)
        Ns.i.a1.booleans.==(Set(false), Set.empty[Boolean]).query.get ==> List(f)
        Ns.i.a1.booleans.==(Set(true, false), Set.empty[Boolean]).query.get ==> List(tf)
        Ns.i.a1.booleans.==(Set.empty[Boolean]).query.get ==> List()
        Ns.i.a1.booleans.==(Seq.empty[Set[Boolean]]).query.get ==> List()
        Ns.i.a1.booleans.==(Seq(Set.empty[Boolean])).query.get ==> List()
      }


      "!=" - types { implicit conn =>
        val t  = (1, Set(true))
        val f  = (2, Set(false))
        val tf = (3, Set(true, false))
        Ns.i.booleans.insert(List(t, f, tf)).transact

        // Exact Set non-matches

        // AND semantics
        // "Not (exactly this AND that)"
        Ns.i.a1.booleans.!=(Set(true)).query.get ==> List(f, tf)
        Ns.i.a1.booleans.!=(Set(false)).query.get ==> List(t, tf)
        Ns.i.a1.booleans.!=(Set(true, false)).query.get ==> List(t, f)
        // Same as
        Ns.i.a1.booleans.!=(Seq(Set(true))).query.get ==> List(f, tf)
        Ns.i.a1.booleans.!=(Seq(Set(false))).query.get ==> List(t, tf)
        Ns.i.a1.booleans.!=(Seq(Set(true, false))).query.get ==> List(t, f)


        // AND/OR semantics with multiple Sets

        // "Not (exactly this AND that) OR (exactly this AND that)"
        Ns.i.a1.booleans.!=(Set(true, false), Set(true)).query.get ==> List(f)
        Ns.i.a1.booleans.!=(Set(true, false), Set(false)).query.get ==> List(t)
        // Same as
        Ns.i.a1.booleans.!=(Seq(Set(true, false), Set(true))).query.get ==> List(f)
        Ns.i.a1.booleans.!=(Seq(Set(true, false), Set(false))).query.get ==> List(t)


        // Empty Seq/Sets
        Ns.i.a1.booleans.!=(Seq(Set(true), Set.empty[Boolean])).query.get ==> List(f, tf)
        Ns.i.a1.booleans.!=(Seq(Set(false), Set.empty[Boolean])).query.get ==> List(t, tf)
        Ns.i.a1.booleans.!=(Seq(Set(true, false), Set.empty[Boolean])).query.get ==> List(t, f)
        Ns.i.a1.booleans.!=(Set.empty[Boolean]).query.get ==> List(t, f, tf)
        Ns.i.a1.booleans.!=(Seq.empty[Set[Boolean]]).query.get ==> List(t, f, tf)
      }


      "compare" - types { implicit conn =>
        val t  = (1, Set(true))
        val f  = (2, Set(false))
        val tf = (3, Set(true, false))
        Ns.i.booleans.insert(List(t, f, tf)).transact

        Ns.i.a1.booleans.<(true).query.get ==> List(f, tf)
        Ns.i.a1.booleans.<(false).query.get ==> List()
        Ns.i.a1.booleans.<=(true).query.get ==> List(t, f, tf)
        Ns.i.a1.booleans.<=(false).query.get ==> List(f, tf)
        Ns.i.a1.booleans.>(true).query.get ==> List()
        Ns.i.a1.booleans.>(false).query.get ==> List(t, tf)
        Ns.i.a1.booleans.>=(true).query.get ==> List(t, tf)
        Ns.i.a1.booleans.>=(false).query.get ==> List(t, f, tf)
      }
    }


    "Tacit" - {

      "attr" - types { implicit conn =>
        val (t, f, tf) = (1, 2, 3)
        Ns.i.booleans.insert(List(
          (t, Set(true)),
          (f, Set(false)),
          (tf, Set(true, false))
        )).transact

        Ns.i.a1.booleans_.query.get ==> List(t, f, tf)
      }


      "apply" - types { implicit conn =>
        val (t, f, tf) = (1, 2, 3)
        Ns.i.booleans.insert(List(
          (t, Set(true)),
          (f, Set(false)),
          (tf, Set(true, false))
        )).transact

        // Sets with one or more values matching

        // "Has this value"
        Ns.i.a1.booleans_(true).query.get ==> List(t, tf)
        Ns.i.a1.booleans_(false).query.get ==> List(f, tf)
        // Same as
        Ns.i.a1.booleans_(Seq(true)).query.get ==> List(t, tf)
        Ns.i.a1.booleans_(Seq(false)).query.get ==> List(f, tf)


        // OR semantics when multiple values

        // "Has this OR that"
        Ns.i.a1.booleans_(true, false).query.get ==> List(t, f, tf)
        // Same as
        Ns.i.a1.booleans_(Seq(true, false)).query.get ==> List(t, f, tf)


        // AND semantics when multiple values in a _Set_

        // "Has this AND that"
        Ns.i.a1.booleans_(Set(true)).query.get ==> List(t, tf)
        Ns.i.a1.booleans_(Set(false)).query.get ==> List(f, tf)
        Ns.i.a1.booleans_(Set(true, false)).query.get ==> List(tf)
        // Same as
        Ns.i.a1.booleans_(Seq(Set(true))).query.get ==> List(t, tf)
        Ns.i.a1.booleans_(Seq(Set(false))).query.get ==> List(f, tf)
        Ns.i.a1.booleans_(Seq(Set(true, false))).query.get ==> List(tf)


        // AND/OR semantics with multiple Sets

        // "(has this AND that) OR (has this AND that)"
        Ns.i.a1.booleans_(Set(true, false), Set(true)).query.get ==> List(t, tf)
        Ns.i.a1.booleans_(Set(true, false), Set(false)).query.get ==> List(f, tf)
        // Same as
        Ns.i.a1.booleans_(Seq(Set(true, false), Set(true))).query.get ==> List(t, tf)
        Ns.i.a1.booleans_(Seq(Set(true, false), Set(false))).query.get ==> List(f, tf)


        // Empty Seq/Sets match nothing
        Ns.i.a1.booleans_(Set(true), Set.empty[Boolean]).query.get ==> List(t, tf)
        Ns.i.a1.booleans_(Set(false), Set.empty[Boolean]).query.get ==> List(f, tf)
        Ns.i.a1.booleans_(Set(true, false), Set.empty[Boolean]).query.get ==> List(tf)
        Ns.i.a1.booleans_(Seq.empty[Boolean]).query.get ==> List()
        Ns.i.a1.booleans_(Set.empty[Boolean]).query.get ==> List()
        Ns.i.a1.booleans_(Seq.empty[Set[Boolean]]).query.get ==> List()
      }


      "not" - types { implicit conn =>
        val (t, f, tf) = (1, 2, 3)
        Ns.i.booleans.insert(List(
          (t, Set(true)),
          (f, Set(false)),
          (tf, Set(true, false))
        )).transact

        // Sets without one or more values matching

        // "Doesn't have this value"
        Ns.i.a1.booleans_.not(true).query.get ==> List(f)
        Ns.i.a1.booleans_.not(false).query.get ==> List(t)
        // Same as
        Ns.i.a1.booleans_.not(Seq(true)).query.get ==> List(f)
        Ns.i.a1.booleans_.not(Seq(false)).query.get ==> List(t)


        // OR semantics when multiple values

        // "Not (has this OR that)"
        Ns.i.a1.booleans_.not(true, false).query.get ==> List()
        // Same as
        Ns.i.a1.booleans_.not(Seq(true, false)).query.get ==> List()


        // AND semantics when multiple values in a _Set_

        // "Not (has this AND that)"
        Ns.i.a1.booleans_.not(Set(true)).query.get ==> List(f)
        Ns.i.a1.booleans_.not(Set(false)).query.get ==> List(t)
        Ns.i.a1.booleans_.not(Set(true, false)).query.get ==> List(t, f)
        // Same as
        Ns.i.a1.booleans_.not(Seq(Set(true))).query.get ==> List(f)
        Ns.i.a1.booleans_.not(Seq(Set(false))).query.get ==> List(t)
        Ns.i.a1.booleans_.not(Seq(Set(true, false))).query.get ==> List(t, f)


        // AND/OR semantics with multiple Sets

        // "Not ((has this AND that) OR (has this AND that))"
        Ns.i.a1.booleans_.not(Set(true, false), Set(true)).query.get ==> List(f)
        Ns.i.a1.booleans_.not(Set(true, false), Set(false)).query.get ==> List(t)
        // Same as
        Ns.i.a1.booleans_.not(Seq(Set(true, false), Set(true))).query.get ==> List(f)
        Ns.i.a1.booleans_.not(Seq(Set(true, false), Set(false))).query.get ==> List(t)


        // Negating empty Seqs/Sets has no effect
        Ns.i.a1.booleans_.not(Set(true), Set.empty[Boolean]).query.get ==> List(f)
        Ns.i.a1.booleans_.not(Set(false), Set.empty[Boolean]).query.get ==> List(t)
        Ns.i.a1.booleans_.not(Set(true, false), Set.empty[Boolean]).query.get ==> List(t, f)
        Ns.i.a1.booleans_.not(Seq.empty[Boolean]).query.get ==> List(t, f, tf)
        Ns.i.a1.booleans_.not(Set.empty[Boolean]).query.get ==> List(t, f, tf)
        Ns.i.a1.booleans_.not(Seq.empty[Set[Boolean]]).query.get ==> List(t, f, tf)
        Ns.i.a1.booleans_.not(Seq(Set.empty[Boolean])).query.get ==> List(t, f, tf)
      }


      "==" - types { implicit conn =>
        val (t, f, tf) = (1, 2, 3)
        Ns.i.booleans.insert(List(
          (t, Set(true)),
          (f, Set(false)),
          (tf, Set(true, false))
        )).transact

        // Exact Set matches

        // AND semantics
        // "Is exactly this AND that"
        Ns.i.a1.booleans_.==(Set(true)).query.get ==> List(t)
        Ns.i.a1.booleans_.==(Set(false)).query.get ==> List(f)
        Ns.i.a1.booleans_.==(Set(true, false)).query.get ==> List(tf)
        // Same as
        Ns.i.a1.booleans_.==(Seq(Set(true))).query.get ==> List(t)
        Ns.i.a1.booleans_.==(Seq(Set(false))).query.get ==> List(f)
        Ns.i.a1.booleans_.==(Seq(Set(true, false))).query.get ==> List(tf)


        // AND/OR semantics with multiple Sets

        // "(exactly this AND that) OR (exactly this AND that)"
        Ns.i.a1.booleans_.==(Set(true, false), Set(true)).query.get ==> List(t, tf)
        Ns.i.a1.booleans_.==(Set(true, false), Set(false)).query.get ==> List(f, tf)
        // Same as
        Ns.i.a1.booleans_.==(Seq(Set(true, false), Set(true))).query.get ==> List(t, tf)
        Ns.i.a1.booleans_.==(Seq(Set(true, false), Set(false))).query.get ==> List(f, tf)


        // Empty Seq/Sets match nothing
        Ns.i.a1.booleans_.==(Set(true), Set.empty[Boolean]).query.get ==> List(t)
        Ns.i.a1.booleans_.==(Set(false), Set.empty[Boolean]).query.get ==> List(f)
        Ns.i.a1.booleans_.==(Set(true, false), Set.empty[Boolean]).query.get ==> List(tf)
        Ns.i.a1.booleans_.==(Set.empty[Boolean]).query.get ==> List()
        Ns.i.a1.booleans_.==(Seq.empty[Set[Boolean]]).query.get ==> List()
        Ns.i.a1.booleans_.==(Seq(Set.empty[Boolean])).query.get ==> List()
      }


      "!=" - types { implicit conn =>
        val (t, f, tf) = (1, 2, 3)
        Ns.i.booleans.insert(List(
          (t, Set(true)),
          (f, Set(false)),
          (tf, Set(true, false))
        )).transact

        // Non-exact Set matches

        // AND semantics
        // "Not (exactly this AND that)"
        Ns.i.a1.booleans_.!=(Set(true)).query.get ==> List(f, tf)
        Ns.i.a1.booleans_.!=(Set(false)).query.get ==> List(t, tf)
        Ns.i.a1.booleans_.!=(Set(true, false)).query.get ==> List(t, f)
        // Same as
        Ns.i.a1.booleans_.!=(Seq(Set(true))).query.get ==> List(f, tf)
        Ns.i.a1.booleans_.!=(Seq(Set(false))).query.get ==> List(t, tf)
        Ns.i.a1.booleans_.!=(Seq(Set(true, false))).query.get ==> List(t, f)


        // AND/OR semantics with multiple Sets

        // "Not (exactly this AND that) OR (exactly this AND that)"
        Ns.i.a1.booleans_.!=(Set(true, false), Set(true)).query.get ==> List(f)
        Ns.i.a1.booleans_.!=(Set(true, false), Set(false)).query.get ==> List(t)
        // Same as
        Ns.i.a1.booleans_.!=(Seq(Set(true, false), Set(true))).query.get ==> List(f)
        Ns.i.a1.booleans_.!=(Seq(Set(true, false), Set(false))).query.get ==> List(t)


        // Empty Seq/Sets
        Ns.i.a1.booleans_.!=(Seq(Set(true), Set.empty[Boolean])).query.get ==> List(f, tf)
        Ns.i.a1.booleans_.!=(Seq(Set(false), Set.empty[Boolean])).query.get ==> List(t, tf)
        Ns.i.a1.booleans_.!=(Seq(Set(true, false), Set.empty[Boolean])).query.get ==> List(t, f)
        Ns.i.a1.booleans_.!=(Set.empty[Boolean]).query.get ==> List(t, f, tf)
        Ns.i.a1.booleans_.!=(Seq.empty[Set[Boolean]]).query.get ==> List(t, f, tf)
      }


      "compare" - types { implicit conn =>
        val (t, f, tf) = (1, 2, 3)
        Ns.i.booleans.insert(List(
          (t, Set(true)),
          (f, Set(false)),
          (tf, Set(true, false))
        )).transact

        Ns.i.a1.booleans_.<(true).query.get ==> List(f, tf)
        Ns.i.a1.booleans_.<(false).query.get ==> List()
        Ns.i.a1.booleans_.<=(true).query.get ==> List(t, f, tf)
        Ns.i.a1.booleans_.<=(false).query.get ==> List(f, tf)
        Ns.i.a1.booleans_.>(true).query.get ==> List()
        Ns.i.a1.booleans_.>(false).query.get ==> List(t, tf)
        Ns.i.a1.booleans_.>=(true).query.get ==> List(t, tf)
        Ns.i.a1.booleans_.>=(false).query.get ==> List(t, f, tf)
      }
    }


    "Optional" - {

      "attr" - types { implicit conn =>
        val t  = (1, Some(Set(true)))
        val f  = (2, Some(Set(false)))
        val tf = (3, Some(Set(true, false)))
        val x  = (4, None)
        Ns.i.booleans_?.insert(t, f, tf, x).transact

        // Bug in Datomic free version where entity api doesn't return `false` for Boolean attributes
        // - fixed later:
        // https://docs.datomic.com/on-prem/changes.html#0.9.6045
        // Works when using Datomic pro
        if (!useFree) {
          Ns.i.a1.booleans_?.query.get ==> List(t, f, tf, x)
        }
      }


      "apply" - types { implicit conn =>
        val t  = (1, Some(Set(true)))
        val f  = (2, Some(Set(false)))
        val tf = (3, Some(Set(true, false)))
        val x  = (4, None)
        Ns.i.booleans_?.insert(t, f, tf, x).transact

        // Sets with one or more values matching

        // "Has this value"
        Ns.i.a1.booleans_?(Some(true)).query.get ==> List(t, tf)
        Ns.i.a1.booleans_?(Some(false)).query.get ==> List(f, tf)
        // Same as
        Ns.i.a1.booleans_?(Some(Seq(true))).query.get ==> List(t, tf)
        Ns.i.a1.booleans_?(Some(Seq(false))).query.get ==> List(f, tf)


        // OR semantics when multiple values

        // "Has this OR that"
        Ns.i.a1.booleans_?(Some(Seq(true, false))).query.get ==> List(t, f, tf)


        // AND semantics when multiple values in a _Set_

        // "Has this AND that"
        Ns.i.a1.booleans_?(Some(Set(true))).query.get ==> List(t, tf)
        Ns.i.a1.booleans_?(Some(Set(false))).query.get ==> List(f, tf)
        Ns.i.a1.booleans_?(Some(Set(true, false))).query.get ==> List(tf)
        // Same as
        Ns.i.a1.booleans_?(Some(Seq(Set(true)))).query.get ==> List(t, tf)
        Ns.i.a1.booleans_?(Some(Seq(Set(false)))).query.get ==> List(f, tf)
        Ns.i.a1.booleans_?(Some(Seq(Set(true, false)))).query.get ==> List(tf)


        // AND/OR semantics with multiple Sets

        // "(has this AND that) OR (has this AND that)"
        Ns.i.a1.booleans_?(Some(Seq(Set(true, false), Set(true)))).query.get ==> List(t, tf)
        Ns.i.a1.booleans_?(Some(Seq(Set(true, false), Set(false)))).query.get ==> List(f, tf)


        // Empty Seq/Sets match nothing
        Ns.i.a1.booleans_?(Some(Seq.empty[Boolean])).query.get ==> List()
        Ns.i.a1.booleans_?(Some(Set.empty[Boolean])).query.get ==> List()
        Ns.i.a1.booleans_?(Some(Seq.empty[Set[Boolean]])).query.get ==> List()


        // None matches non-asserted values
        Ns.i.a1.booleans_?(Option.empty[Boolean]).query.get ==> List(x)
        Ns.i.a1.booleans_?(Option.empty[Seq[Boolean]]).query.get ==> List(x)
        Ns.i.a1.booleans_?(Option.empty[Set[Boolean]]).query.get ==> List(x)
        Ns.i.a1.booleans_?(Option.empty[Seq[Set[Boolean]]]).query.get ==> List(x)
      }


      "not" - types { implicit conn =>
        val t  = (1, Some(Set(true)))
        val f  = (2, Some(Set(false)))
        val tf = (3, Some(Set(true, false)))
        val x  = (4, None)
        Ns.i.booleans_?.insert(t, f, tf, x).transact

        // Sets without one or more values matching

        // "Doesn't have this value"
        Ns.i.a1.booleans_?.not(Some(true)).query.get ==> List(f)
        Ns.i.a1.booleans_?.not(Some(false)).query.get ==> List(t)
        // Same as
        Ns.i.a1.booleans_?.not(Some(Seq(true))).query.get ==> List(f)
        Ns.i.a1.booleans_?.not(Some(Seq(false))).query.get ==> List(t)


        // OR semantics when multiple values

        // "Not (has this OR that)"
        Ns.i.a1.booleans_?.not(Some(Seq(true, false))).query.get ==> List()


        // AND semantics when multiple values in a _Set_

        // "Not (has this AND that)"
        Ns.i.a1.booleans_?.not(Some(Set(true))).query.get ==> List(f)
        Ns.i.a1.booleans_?.not(Some(Set(false))).query.get ==> List(t)
        Ns.i.a1.booleans_?.not(Some(Set(true, false))).query.get ==> List(t, f)
        // Same as
        Ns.i.a1.booleans_?.not(Some(Seq(Set(true)))).query.get ==> List(f)
        Ns.i.a1.booleans_?.not(Some(Seq(Set(false)))).query.get ==> List(t)
        Ns.i.a1.booleans_?.not(Some(Seq(Set(true, false)))).query.get ==> List(t, f)


        // AND/OR semantics with multiple Sets

        // "Not ((has this AND that) OR (has this AND that))"
        Ns.i.a1.booleans_?.not(Some(Seq(Set(true, false), Set(true)))).query.get ==> List(f)
        Ns.i.a1.booleans_?.not(Some(Seq(Set(true, false), Set(false)))).query.get ==> List(t)


        // Negating empty Seqs/Sets has no effect
        Ns.i.a1.booleans_?.not(Some(Seq.empty[Boolean])).query.get ==> List(t, f, tf)
        Ns.i.a1.booleans_?.not(Some(Set.empty[Boolean])).query.get ==> List(t, f, tf)
        Ns.i.a1.booleans_?.not(Some(Seq.empty[Set[Boolean]])).query.get ==> List(t, f, tf)
        Ns.i.a1.booleans_?.not(Some(Seq(Set.empty[Boolean]))).query.get ==> List(t, f, tf)


        // Negating None returns all asserted
        Ns.i.a1.booleans_?.not(Option.empty[Boolean]).query.get ==> List(t, f, tf)
        Ns.i.a1.booleans_?.not(Option.empty[Seq[Boolean]]).query.get ==> List(t, f, tf)
        Ns.i.a1.booleans_?.not(Option.empty[Set[Boolean]]).query.get ==> List(t, f, tf)
        Ns.i.a1.booleans_?.not(Option.empty[Seq[Set[Boolean]]]).query.get ==> List(t, f, tf)
      }


      "==" - types { implicit conn =>
        val t  = (1, Some(Set(true)))
        val f  = (2, Some(Set(false)))
        val tf = (3, Some(Set(true, false)))
        val x  = (4, None)
        Ns.i.booleans_?.insert(t, f, tf, x).transact

        // Exact Set matches

        // AND semantics
        // "Is exactly this AND that"
        Ns.i.a1.booleans_?.==(Some(Set(true))).query.get ==> List(t)
        Ns.i.a1.booleans_?.==(Some(Set(false))).query.get ==> List(f)
        Ns.i.a1.booleans_?.==(Some(Set(true, false))).query.get ==> List(tf)
        // Same as
        Ns.i.a1.booleans_?.==(Some(Seq(Set(true)))).query.get ==> List(t)
        Ns.i.a1.booleans_?.==(Some(Seq(Set(false)))).query.get ==> List(f)
        Ns.i.a1.booleans_?.==(Some(Seq(Set(true, false)))).query.get ==> List(tf)


        // AND/OR semantics with multiple Sets

        // "(exactly this AND that) OR (exactly this AND that)"
        Ns.i.a1.booleans_?.==(Some(Seq(Set(true, false), Set(true)))).query.get ==> List(t, tf)
        Ns.i.a1.booleans_?.==(Some(Seq(Set(true, false), Set(false)))).query.get ==> List(f, tf)


        // Empty Seq/Sets match nothing
        Ns.i.a1.booleans_?.==(Some(Set.empty[Boolean])).query.get ==> List()
        Ns.i.a1.booleans_?.==(Some(Seq.empty[Set[Boolean]])).query.get ==> List()
        Ns.i.a1.booleans_?.==(Some(Seq(Set.empty[Boolean]))).query.get ==> List()


        // None matches non-asserted values
        Ns.i.a1.booleans_?.==(Option.empty[Set[Boolean]]).query.get ==> List(x)
        Ns.i.a1.booleans_?.==(Option.empty[Seq[Set[Boolean]]]).query.get ==> List(x)
      }


      "!=" - types { implicit conn =>
        val t  = (1, Some(Set(true)))
        val f  = (2, Some(Set(false)))
        val tf = (3, Some(Set(true, false)))
        val x  = (4, None)
        Ns.i.booleans_?.insert(t, f, tf, x).transact

        // Non-exact Set matches

        // AND semantics
        // "Not (exactly this AND that)"
        Ns.i.a1.booleans_?.!=(Some(Set(true))).query.get ==> List(f, tf)
        Ns.i.a1.booleans_?.!=(Some(Set(false))).query.get ==> List(t, tf)
        Ns.i.a1.booleans_?.!=(Some(Set(true, false))).query.get ==> List(t, f)
        // Same as
        Ns.i.a1.booleans_?.!=(Some(Seq(Set(true)))).query.get ==> List(f, tf)
        Ns.i.a1.booleans_?.!=(Some(Seq(Set(false)))).query.get ==> List(t, tf)
        Ns.i.a1.booleans_?.!=(Some(Seq(Set(true, false)))).query.get ==> List(t, f)


        // AND/OR semantics with multiple Sets

        // "Not (exactly this AND that) OR (exactly this AND that)"
        Ns.i.a1.booleans_?.!=(Some(Seq(Set(true, false), Set(true)))).query.get ==> List(f)
        Ns.i.a1.booleans_?.!=(Some(Seq(Set(true, false), Set(false)))).query.get ==> List(t)


        // Empty Seq/Sets
        Ns.i.a1.booleans_?.!=(Some(Seq(Set(true), Set.empty[Boolean]))).query.get ==> List(f, tf)
        Ns.i.a1.booleans_?.!=(Some(Seq(Set(false), Set.empty[Boolean]))).query.get ==> List(t, tf)
        Ns.i.a1.booleans_?.!=(Some(Seq(Set(true, false), Set.empty[Boolean]))).query.get ==> List(t, f)
        Ns.i.a1.booleans_?.!=(Some(Set.empty[Boolean])).query.get ==> List(t, f, tf)
        Ns.i.a1.booleans_?.!=(Some(Seq.empty[Set[Boolean]])).query.get ==> List(t, f, tf)


        // None matches non-asserted values
        Ns.i.a1.booleans_?.==(Option.empty[Set[Boolean]]).query.get ==> List(x)
        Ns.i.a1.booleans_?.==(Option.empty[Seq[Set[Boolean]]]).query.get ==> List(x)
      }


      "compare" - types { implicit conn =>
        val t  = (1, Some(Set(true)))
        val f  = (2, Some(Set(false)))
        val tf = (3, Some(Set(true, false)))
        val x  = (4, None)
        Ns.i.booleans_?.insert(t, f, tf, x).transact

        Ns.i.a1.booleans_?.<(Some(true)).query.get ==> List(f, tf)
        Ns.i.a1.booleans_?.<(Some(false)).query.get ==> List()
        Ns.i.a1.booleans_?.<=(Some(true)).query.get ==> List(t, f, tf)
        Ns.i.a1.booleans_?.<=(Some(false)).query.get ==> List(f, tf)
        Ns.i.a1.booleans_?.>(Some(true)).query.get ==> List()
        Ns.i.a1.booleans_?.>(Some(false)).query.get ==> List(t, tf)
        Ns.i.a1.booleans_?.>=(Some(true)).query.get ==> List(t, tf)
        Ns.i.a1.booleans_?.>=(Some(false)).query.get ==> List(t, f, tf)


        // None matches any asserted values
        Ns.i.a1.booleans_?.<(None).query.get ==> List(t, f, tf)
        Ns.i.a1.booleans_?.>(None).query.get ==> List(t, f, tf)
        Ns.i.a1.booleans_?.<=(None).query.get ==> List(t, f, tf)
        Ns.i.a1.booleans_?.>=(None).query.get ==> List(t, f, tf)
      }
    }
  }
}