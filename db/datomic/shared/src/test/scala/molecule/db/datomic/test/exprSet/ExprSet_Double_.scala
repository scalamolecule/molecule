// GENERATED CODE ********************************
package molecule.db.datomic.test.exprSet

import molecule.coreTests.dataModels.core.types.dsl.TypesSet._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._


object ExprSet_Double_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "Mandatory" - {

      "attr" - typesSet { implicit conn =>
        val a = (1, Set(double1, double2))
        val b = (2, Set(double2, double3, double4))
        NsSet.n.doubles.insert(List(a, b)).transact

        NsSet.n.a1.doubles.query.get ==> List(a, b)
      }


      "apply" - typesSet { implicit conn =>
        val a = (1, Set(double1, double2))
        val b = (2, Set(double2, double3, double4))
        NsSet.n.doubles.insert(List(a, b)).transact

        // Sets with one or more values matching

        // "Has this value"
        NsSet.n.a1.doubles(double0).query.get ==> List()
        NsSet.n.a1.doubles(double1).query.get ==> List(a)
        NsSet.n.a1.doubles(double2).query.get ==> List(a, b)
        NsSet.n.a1.doubles(double3).query.get ==> List(b)
        // Same as
        NsSet.n.a1.doubles(Seq(double0)).query.get ==> List()
        NsSet.n.a1.doubles(Seq(double1)).query.get ==> List(a)
        NsSet.n.a1.doubles(Seq(double2)).query.get ==> List(a, b)
        NsSet.n.a1.doubles(Seq(double3)).query.get ==> List(b)


        // OR semantics when multiple values

        // "Has this OR that"
        NsSet.n.a1.doubles(double1, double2).query.get ==> List(a, b)
        NsSet.n.a1.doubles(double1, double3).query.get ==> List(a, b)
        NsSet.n.a1.doubles(double2, double3).query.get ==> List(a, b)
        NsSet.n.a1.doubles(double1, double2, double3).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.doubles(Seq(double1, double2)).query.get ==> List(a, b)
        NsSet.n.a1.doubles(Seq(double1, double3)).query.get ==> List(a, b)
        NsSet.n.a1.doubles(Seq(double2, double3)).query.get ==> List(a, b)
        NsSet.n.a1.doubles(Seq(double1, double2, double3)).query.get ==> List(a, b)


        // AND semantics when multiple values in a _Set_

        // "Has this AND that"
        NsSet.n.a1.doubles(Set(double1)).query.get ==> List(a)
        NsSet.n.a1.doubles(Set(double1, double2)).query.get ==> List(a)
        NsSet.n.a1.doubles(Set(double1, double2, double3)).query.get ==> List()
        NsSet.n.a1.doubles(Set(double2)).query.get ==> List(a, b)
        NsSet.n.a1.doubles(Set(double2, double3)).query.get ==> List(b)
        NsSet.n.a1.doubles(Set(double2, double3, double4)).query.get ==> List(b)
        // Same as
        NsSet.n.a1.doubles(Seq(Set(double1))).query.get ==> List(a)
        NsSet.n.a1.doubles(Seq(Set(double1, double2))).query.get ==> List(a)
        NsSet.n.a1.doubles(Seq(Set(double1, double2, double3))).query.get ==> List()
        NsSet.n.a1.doubles(Seq(Set(double2))).query.get ==> List(a, b)
        NsSet.n.a1.doubles(Seq(Set(double2, double3))).query.get ==> List(b)
        NsSet.n.a1.doubles(Seq(Set(double2, double3, double4))).query.get ==> List(b)


        // AND/OR semantics with multiple Sets

        // "(has this AND that) OR (has this AND that)"
        NsSet.n.a1.doubles(Set(double1, double2), Set(double0)).query.get ==> List(a)
        NsSet.n.a1.doubles(Set(double1, double2), Set(double0, double3)).query.get ==> List(a)
        NsSet.n.a1.doubles(Set(double1, double2), Set(double2, double3)).query.get ==> List(a, b)
        NsSet.n.a1.doubles(Set(double1, double2), Set(double2, double3, double4)).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.doubles(Seq(Set(double1, double2), Set(double0))).query.get ==> List(a)
        NsSet.n.a1.doubles(Seq(Set(double1, double2), Set(double0, double3))).query.get ==> List(a)
        NsSet.n.a1.doubles(Seq(Set(double1, double2), Set(double2, double3))).query.get ==> List(a, b)
        NsSet.n.a1.doubles(Seq(Set(double1, double2), Set(double2, double3, double4))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        NsSet.n.a1.doubles(Set(double1, double2), Set.empty[Double]).query.get ==> List(a)
        NsSet.n.a1.doubles(Seq.empty[Double]).query.get ==> List()
        NsSet.n.a1.doubles(Set.empty[Double]).query.get ==> List()
        NsSet.n.a1.doubles(Seq.empty[Set[Double]]).query.get ==> List()
      }


      "not" - typesSet { implicit conn =>
        val a = (1, Set(double1, double2))
        val b = (2, Set(double2, double3, double4))
        NsSet.n.doubles.insert(List(a, b)).transact

        // Sets without one or more values matching

        // "Doesn't have this value"
        NsSet.n.a1.doubles.not(double0).query.get ==> List(a, b)
        NsSet.n.a1.doubles.not(double1).query.get ==> List(b)
        NsSet.n.a1.doubles.not(double2).query.get ==> List()
        NsSet.n.a1.doubles.not(double3).query.get ==> List(a)
        NsSet.n.a1.doubles.not(double4).query.get ==> List(a)
        NsSet.n.a1.doubles.not(double5).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.doubles.not(Seq(double0)).query.get ==> List(a, b)
        NsSet.n.a1.doubles.not(Seq(double1)).query.get ==> List(b)
        NsSet.n.a1.doubles.not(Seq(double2)).query.get ==> List()
        NsSet.n.a1.doubles.not(Seq(double3)).query.get ==> List(a)
        NsSet.n.a1.doubles.not(Seq(double4)).query.get ==> List(a)
        NsSet.n.a1.doubles.not(Seq(double5)).query.get ==> List(a, b)


        // OR semantics when multiple values

        // "Not (has this OR that)"
        NsSet.n.a1.doubles.not(double1, double2).query.get ==> List()
        NsSet.n.a1.doubles.not(double1, double3).query.get ==> List()
        NsSet.n.a1.doubles.not(double1, double4).query.get ==> List()
        NsSet.n.a1.doubles.not(double1, double5).query.get ==> List(b)
        // Same as
        NsSet.n.a1.doubles.not(Seq(double1, double2)).query.get ==> List()
        NsSet.n.a1.doubles.not(Seq(double1, double3)).query.get ==> List()
        NsSet.n.a1.doubles.not(Seq(double1, double4)).query.get ==> List()
        NsSet.n.a1.doubles.not(Seq(double1, double5)).query.get ==> List(b)


        // AND semantics when multiple values in a _Set_

        // "Not (has this AND that)"
        NsSet.n.a1.doubles.not(Set(double1)).query.get ==> List(b)
        NsSet.n.a1.doubles.not(Set(double1, double2)).query.get ==> List(b)
        NsSet.n.a1.doubles.not(Set(double1, double2, double3)).query.get ==> List(a, b)
        NsSet.n.a1.doubles.not(Set(double2)).query.get ==> List()
        NsSet.n.a1.doubles.not(Set(double2, double3)).query.get ==> List(a)
        NsSet.n.a1.doubles.not(Set(double2, double3, double4)).query.get ==> List(a)
        // Same as
        NsSet.n.a1.doubles.not(Seq(Set(double1))).query.get ==> List(b)
        NsSet.n.a1.doubles.not(Seq(Set(double1, double2))).query.get ==> List(b)
        NsSet.n.a1.doubles.not(Seq(Set(double1, double2, double3))).query.get ==> List(a, b)
        NsSet.n.a1.doubles.not(Seq(Set(double2))).query.get ==> List()
        NsSet.n.a1.doubles.not(Seq(Set(double2, double3))).query.get ==> List(a)
        NsSet.n.a1.doubles.not(Seq(Set(double2, double3, double4))).query.get ==> List(a)


        // AND/OR semantics with multiple Sets

        // "Not ((has this AND that) OR (has this AND that))"
        NsSet.n.a1.doubles.not(Set(double1, double2), Set(double0)).query.get ==> List(b)
        NsSet.n.a1.doubles.not(Set(double1, double2), Set(double0, double3)).query.get ==> List(b)
        NsSet.n.a1.doubles.not(Set(double1, double2), Set(double2, double3)).query.get ==> List()
        NsSet.n.a1.doubles.not(Set(double1, double2), Set(double2, double3, double4)).query.get ==> List()
        // Same as
        NsSet.n.a1.doubles.not(Seq(Set(double1, double2), Set(double0))).query.get ==> List(b)
        NsSet.n.a1.doubles.not(Seq(Set(double1, double2), Set(double0, double3))).query.get ==> List(b)
        NsSet.n.a1.doubles.not(Seq(Set(double1, double2), Set(double2, double3))).query.get ==> List()
        NsSet.n.a1.doubles.not(Seq(Set(double1, double2), Set(double2, double3, double4))).query.get ==> List()


        // Negating empty Seqs/Sets has no effect
        NsSet.n.a1.doubles.not(Set(double1, double2), Set.empty[Double]).query.get ==> List(b)
        NsSet.n.a1.doubles.not(Seq.empty[Double]).query.get ==> List(a, b)
        NsSet.n.a1.doubles.not(Set.empty[Double]).query.get ==> List(a, b)
        NsSet.n.a1.doubles.not(Seq.empty[Set[Double]]).query.get ==> List(a, b)
        NsSet.n.a1.doubles.not(Seq(Set.empty[Double])).query.get ==> List(a, b)
      }


      "==" - typesSet { implicit conn =>
        val a = (1, Set(double1, double2))
        val b = (2, Set(double2, double3, double4))
        NsSet.n.doubles.insert(List(a, b)).transact

        // Exact Set matches

        // AND semantics
        // "Is exactly this AND that"
        NsSet.n.a1.doubles.==(Set(double1)).query.get ==> List()
        NsSet.n.a1.doubles.==(Set(double1, double2)).query.get ==> List(a) // include exact match
        NsSet.n.a1.doubles.==(Set(double1, double2, double3)).query.get ==> List()
        // Same as
        NsSet.n.a1.doubles.==(Seq(Set(double1))).query.get ==> List()
        NsSet.n.a1.doubles.==(Seq(Set(double1, double2))).query.get ==> List(a)
        NsSet.n.a1.doubles.==(Seq(Set(double1, double2, double3))).query.get ==> List()


        // AND/OR semantics with multiple Sets

        // "(exactly this AND that) OR (exactly this AND that)"
        NsSet.n.a1.doubles.==(Set(double1), Set(double2, double3)).query.get ==> List()
        NsSet.n.a1.doubles.==(Set(double1, double2), Set(double2, double3)).query.get ==> List(a)
        NsSet.n.a1.doubles.==(Set(double1, double2), Set(double2, double3, double4)).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.doubles.==(Seq(Set(double1), Set(double2, double3))).query.get ==> List()
        NsSet.n.a1.doubles.==(Seq(Set(double1, double2), Set(double2, double3))).query.get ==> List(a)
        NsSet.n.a1.doubles.==(Seq(Set(double1, double2), Set(double2, double3, double4))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        NsSet.n.a1.doubles.==(Set(double1, double2), Set.empty[Double]).query.get ==> List(a)
        NsSet.n.a1.doubles.==(Set.empty[Double], Set(double1, double2)).query.get ==> List(a)
        NsSet.n.a1.doubles.==(Set.empty[Double]).query.get ==> List()
        NsSet.n.a1.doubles.==(Seq.empty[Set[Double]]).query.get ==> List()
        NsSet.n.a1.doubles.==(Seq(Set.empty[Double])).query.get ==> List()
      }


      "!=" - typesSet { implicit conn =>
        val a = (1, Set(double1, double2))
        val b = (2, Set(double2, double3, double4))
        NsSet.n.doubles.insert(List(a, b)).transact

        // Exact Set non-matches

        // AND semantics
        // "Not (exactly this AND that)"
        NsSet.n.a1.doubles.!=(Set(double1)).query.get ==> List(a, b)
        NsSet.n.a1.doubles.!=(Set(double1, double2)).query.get ==> List(b) // exclude exact match
        NsSet.n.a1.doubles.!=(Set(double1, double2, double3)).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.doubles.!=(Seq(Set(double1))).query.get ==> List(a, b)
        NsSet.n.a1.doubles.!=(Seq(Set(double1, double2))).query.get ==> List(b)
        NsSet.n.a1.doubles.!=(Seq(Set(double1, double2, double3))).query.get ==> List(a, b)


        // AND/OR semantics with multiple Sets

        // "Not (exactly this AND that) OR (exactly this AND that)"
        NsSet.n.a1.doubles.!=(Set(double1), Set(double2, double3)).query.get ==> List(a, b)
        NsSet.n.a1.doubles.!=(Set(double1, double2), Set(double2, double3)).query.get ==> List(b)
        NsSet.n.a1.doubles.!=(Set(double1, double2), Set(double2, double3, double4)).query.get ==> List()
        // Same as
        NsSet.n.a1.doubles.!=(Seq(Set(double1), Set(double2, double3))).query.get ==> List(a, b)
        NsSet.n.a1.doubles.!=(Seq(Set(double1, double2), Set(double2, double3))).query.get ==> List(b)
        NsSet.n.a1.doubles.!=(Seq(Set(double1, double2), Set(double2, double3, double4))).query.get ==> List()


        // Empty Seq/Sets
        NsSet.n.a1.doubles.!=(Seq(Set(double1, double2), Set.empty[Double])).query.get ==> List(b)
        NsSet.n.a1.doubles.!=(Set.empty[Double]).query.get ==> List(a, b)
        NsSet.n.a1.doubles.!=(Seq.empty[Set[Double]]).query.get ==> List(a, b)
      }


      "compare" - typesSet { implicit conn =>
        val a = (1, Set(double1, double2))
        val b = (2, Set(double2, double3, double4))
        NsSet.n.doubles.insert(List(a, b)).transact

        NsSet.n.a1.doubles.<(double0).query.get ==> List()
        NsSet.n.a1.doubles.<(double1).query.get ==> List()
        NsSet.n.a1.doubles.<(double2).query.get ==> List(a)
        NsSet.n.a1.doubles.<(double3).query.get ==> List(a, b)

        NsSet.n.a1.doubles.<=(double0).query.get ==> List()
        NsSet.n.a1.doubles.<=(double1).query.get ==> List(a)
        NsSet.n.a1.doubles.<=(double2).query.get ==> List(a, b)
        NsSet.n.a1.doubles.<=(double3).query.get ==> List(a, b)

        NsSet.n.a1.doubles.>(double0).query.get ==> List(a, b)
        NsSet.n.a1.doubles.>(double1).query.get ==> List(a, b)
        NsSet.n.a1.doubles.>(double2).query.get ==> List(b)
        NsSet.n.a1.doubles.>(double3).query.get ==> List(b)

        NsSet.n.a1.doubles.>=(double0).query.get ==> List(a, b)
        NsSet.n.a1.doubles.>=(double1).query.get ==> List(a, b)
        NsSet.n.a1.doubles.>=(double2).query.get ==> List(a, b)
        NsSet.n.a1.doubles.>=(double3).query.get ==> List(b)
      }
    }


    "Tacit" - {

      "attr" - typesSet { implicit conn =>
        val (a, b) = (1, 2)
        NsSet.n.doubles.insert(List(
          (a, Set(double1, double2)),
          (b, Set(double2, double3, double4))
        )).transact

        NsSet.n.a1.doubles_.query.get ==> List(a, b)
      }


      "apply" - typesSet { implicit conn =>
        val (a, b) = (1, 2)
        NsSet.n.doubles.insert(List(
          (a, Set(double1, double2)),
          (b, Set(double2, double3, double4))
        )).transact

        // Sets with one or more values matching

        // "Has this value"
        NsSet.n.a1.doubles_(double0).query.get ==> List()
        NsSet.n.a1.doubles_(double1).query.get ==> List(a)
        NsSet.n.a1.doubles_(double2).query.get ==> List(a, b)
        NsSet.n.a1.doubles_(double3).query.get ==> List(b)
        // Same as
        NsSet.n.a1.doubles_(Seq(double0)).query.get ==> List()
        NsSet.n.a1.doubles_(Seq(double1)).query.get ==> List(a)
        NsSet.n.a1.doubles_(Seq(double2)).query.get ==> List(a, b)
        NsSet.n.a1.doubles_(Seq(double3)).query.get ==> List(b)


        // OR semantics when multiple values

        // "Has this OR that"
        NsSet.n.a1.doubles_(double1, double2).query.get ==> List(a, b)
        NsSet.n.a1.doubles_(double1, double3).query.get ==> List(a, b)
        NsSet.n.a1.doubles_(double2, double3).query.get ==> List(a, b)
        NsSet.n.a1.doubles_(double1, double2, double3).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.doubles_(Seq(double1, double2)).query.get ==> List(a, b)
        NsSet.n.a1.doubles_(Seq(double1, double3)).query.get ==> List(a, b)
        NsSet.n.a1.doubles_(Seq(double2, double3)).query.get ==> List(a, b)
        NsSet.n.a1.doubles_(Seq(double1, double2, double3)).query.get ==> List(a, b)


        // AND semantics when multiple values in a _Set_

        // "Has this AND that"
        NsSet.n.a1.doubles_(Set(double1)).query.get ==> List(a)
        NsSet.n.a1.doubles_(Set(double1, double2)).query.get ==> List(a)
        NsSet.n.a1.doubles_(Set(double1, double2, double3)).query.get ==> List()
        NsSet.n.a1.doubles_(Set(double2)).query.get ==> List(a, b)
        NsSet.n.a1.doubles_(Set(double2, double3)).query.get ==> List(b)
        NsSet.n.a1.doubles_(Set(double2, double3, double4)).query.get ==> List(b)
        // Same as
        NsSet.n.a1.doubles_(Seq(Set(double1))).query.get ==> List(a)
        NsSet.n.a1.doubles_(Seq(Set(double1, double2))).query.get ==> List(a)
        NsSet.n.a1.doubles_(Seq(Set(double1, double2, double3))).query.get ==> List()
        NsSet.n.a1.doubles_(Seq(Set(double2))).query.get ==> List(a, b)
        NsSet.n.a1.doubles_(Seq(Set(double2, double3))).query.get ==> List(b)
        NsSet.n.a1.doubles_(Seq(Set(double2, double3, double4))).query.get ==> List(b)


        // AND/OR semantics with multiple Sets

        // "(has this AND that) OR (has this AND that)"
        NsSet.n.a1.doubles_(Set(double1, double2), Set(double0)).query.get ==> List(a)
        NsSet.n.a1.doubles_(Set(double1, double2), Set(double0, double3)).query.get ==> List(a)
        NsSet.n.a1.doubles_(Set(double1, double2), Set(double2, double3)).query.get ==> List(a, b)
        NsSet.n.a1.doubles_(Set(double1, double2), Set(double2, double3, double4)).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.doubles_(Seq(Set(double1, double2), Set(double0))).query.get ==> List(a)
        NsSet.n.a1.doubles_(Seq(Set(double1, double2), Set(double0, double3))).query.get ==> List(a)
        NsSet.n.a1.doubles_(Seq(Set(double1, double2), Set(double2, double3))).query.get ==> List(a, b)
        NsSet.n.a1.doubles_(Seq(Set(double1, double2), Set(double2, double3, double4))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        NsSet.n.a1.doubles_(Set(double1, double2), Set.empty[Double]).query.get ==> List(a)
        NsSet.n.a1.doubles_(Seq.empty[Double]).query.get ==> List()
        NsSet.n.a1.doubles_(Set.empty[Double]).query.get ==> List()
        NsSet.n.a1.doubles_(Seq.empty[Set[Double]]).query.get ==> List()
      }


      "not" - typesSet { implicit conn =>
        val (a, b) = (1, 2)
        NsSet.n.doubles.insert(List(
          (a, Set(double1, double2)),
          (b, Set(double2, double3, double4))
        )).transact

        // Sets without one or more values matching

        // "Doesn't have this value"
        NsSet.n.a1.doubles_.not(double0).query.get ==> List(a, b)
        NsSet.n.a1.doubles_.not(double1).query.get ==> List(b)
        NsSet.n.a1.doubles_.not(double2).query.get ==> List()
        NsSet.n.a1.doubles_.not(double3).query.get ==> List(a)
        NsSet.n.a1.doubles_.not(double4).query.get ==> List(a)
        NsSet.n.a1.doubles_.not(double5).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.doubles_.not(Seq(double0)).query.get ==> List(a, b)
        NsSet.n.a1.doubles_.not(Seq(double1)).query.get ==> List(b)
        NsSet.n.a1.doubles_.not(Seq(double2)).query.get ==> List()
        NsSet.n.a1.doubles_.not(Seq(double3)).query.get ==> List(a)
        NsSet.n.a1.doubles_.not(Seq(double4)).query.get ==> List(a)
        NsSet.n.a1.doubles_.not(Seq(double5)).query.get ==> List(a, b)


        // OR semantics when multiple values

        // "Not (has this OR that)"
        NsSet.n.a1.doubles_.not(double1, double2).query.get ==> List()
        NsSet.n.a1.doubles_.not(double1, double3).query.get ==> List()
        NsSet.n.a1.doubles_.not(double1, double4).query.get ==> List()
        NsSet.n.a1.doubles_.not(double1, double5).query.get ==> List(b)
        // Same as
        NsSet.n.a1.doubles_.not(Seq(double1, double2)).query.get ==> List()
        NsSet.n.a1.doubles_.not(Seq(double1, double3)).query.get ==> List()
        NsSet.n.a1.doubles_.not(Seq(double1, double4)).query.get ==> List()
        NsSet.n.a1.doubles_.not(Seq(double1, double5)).query.get ==> List(b)


        // AND semantics when multiple values in a _Set_

        // "Not (has this AND that)"
        NsSet.n.a1.doubles_.not(Set(double1)).query.get ==> List(b)
        NsSet.n.a1.doubles_.not(Set(double1, double2)).query.get ==> List(b)
        NsSet.n.a1.doubles_.not(Set(double1, double2, double3)).query.get ==> List(a, b)
        NsSet.n.a1.doubles_.not(Set(double2)).query.get ==> List()
        NsSet.n.a1.doubles_.not(Set(double2, double3)).query.get ==> List(a)
        NsSet.n.a1.doubles_.not(Set(double2, double3, double4)).query.get ==> List(a)
        // Same as
        NsSet.n.a1.doubles_.not(Seq(Set(double1))).query.get ==> List(b)
        NsSet.n.a1.doubles_.not(Seq(Set(double1, double2))).query.get ==> List(b)
        NsSet.n.a1.doubles_.not(Seq(Set(double1, double2, double3))).query.get ==> List(a, b)
        NsSet.n.a1.doubles_.not(Seq(Set(double2))).query.get ==> List()
        NsSet.n.a1.doubles_.not(Seq(Set(double2, double3))).query.get ==> List(a)
        NsSet.n.a1.doubles_.not(Seq(Set(double2, double3, double4))).query.get ==> List(a)


        // AND/OR semantics with multiple Sets

        // "Not ((has this AND that) OR (has this AND that))"
        NsSet.n.a1.doubles_.not(Set(double1, double2), Set(double0)).query.get ==> List(b)
        NsSet.n.a1.doubles_.not(Set(double1, double2), Set(double0, double3)).query.get ==> List(b)
        NsSet.n.a1.doubles_.not(Set(double1, double2), Set(double2, double3)).query.get ==> List()
        NsSet.n.a1.doubles_.not(Set(double1, double2), Set(double2, double3, double4)).query.get ==> List()
        // Same as
        NsSet.n.a1.doubles_.not(Seq(Set(double1, double2), Set(double0))).query.get ==> List(b)
        NsSet.n.a1.doubles_.not(Seq(Set(double1, double2), Set(double0, double3))).query.get ==> List(b)
        NsSet.n.a1.doubles_.not(Seq(Set(double1, double2), Set(double2, double3))).query.get ==> List()
        NsSet.n.a1.doubles_.not(Seq(Set(double1, double2), Set(double2, double3, double4))).query.get ==> List()


        // Negating empty Seqs/Sets has no effect
        NsSet.n.a1.doubles_.not(Set(double1, double2), Set.empty[Double]).query.get ==> List(b)
        NsSet.n.a1.doubles_.not(Seq.empty[Double]).query.get ==> List(a, b)
        NsSet.n.a1.doubles_.not(Set.empty[Double]).query.get ==> List(a, b)
        NsSet.n.a1.doubles_.not(Seq.empty[Set[Double]]).query.get ==> List(a, b)
        NsSet.n.a1.doubles_.not(Seq(Set.empty[Double])).query.get ==> List(a, b)
      }


      "==" - typesSet { implicit conn =>
        val (a, b) = (1, 2)
        NsSet.n.doubles.insert(List(
          (a, Set(double1, double2)),
          (b, Set(double2, double3, double4))
        )).transact

        // Exact Set matches

        // AND semantics
        // "Is exactly this AND that"
        NsSet.n.a1.doubles_.==(Set(double1)).query.get ==> List()
        NsSet.n.a1.doubles_.==(Set(double1, double2)).query.get ==> List(a) // include exact match
        NsSet.n.a1.doubles_.==(Set(double1, double2, double3)).query.get ==> List()
        // Same as
        NsSet.n.a1.doubles_.==(Seq(Set(double1))).query.get ==> List()
        NsSet.n.a1.doubles_.==(Seq(Set(double1, double2))).query.get ==> List(a)
        NsSet.n.a1.doubles_.==(Seq(Set(double1, double2, double3))).query.get ==> List()


        // AND/OR semantics with multiple Sets

        // "(exactly this AND that) OR (exactly this AND that)"
        NsSet.n.a1.doubles_.==(Set(double1), Set(double2, double3)).query.get ==> List()
        NsSet.n.a1.doubles_.==(Set(double1, double2), Set(double2, double3)).query.get ==> List(a)
        NsSet.n.a1.doubles_.==(Set(double1, double2), Set(double2, double3, double4)).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.doubles_.==(Seq(Set(double1), Set(double2, double3))).query.get ==> List()
        NsSet.n.a1.doubles_.==(Seq(Set(double1, double2), Set(double2, double3))).query.get ==> List(a)
        NsSet.n.a1.doubles_.==(Seq(Set(double1, double2), Set(double2, double3, double4))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        NsSet.n.a1.doubles_.==(Set(double1, double2), Set.empty[Double]).query.get ==> List(a)
        NsSet.n.a1.doubles_.==(Set.empty[Double]).query.get ==> List()
        NsSet.n.a1.doubles_.==(Seq.empty[Set[Double]]).query.get ==> List()
        NsSet.n.a1.doubles_.==(Seq(Set.empty[Double])).query.get ==> List()
      }


      "!=" - typesSet { implicit conn =>
        val (a, b) = (1, 2)
        NsSet.n.doubles.insert(List(
          (a, Set(double1, double2)),
          (b, Set(double2, double3, double4))
        )).transact

        // Non-exact Set matches

        // AND semantics
        // "Not (exactly this AND that)"
        NsSet.n.a1.doubles_.!=(Set(double1)).query.get ==> List(a, b)
        NsSet.n.a1.doubles_.!=(Set(double1, double2)).query.get ==> List(b) // exclude exact match
        NsSet.n.a1.doubles_.!=(Set(double1, double2, double3)).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.doubles_.!=(Seq(Set(double1))).query.get ==> List(a, b)
        NsSet.n.a1.doubles_.!=(Seq(Set(double1, double2))).query.get ==> List(b)
        NsSet.n.a1.doubles_.!=(Seq(Set(double1, double2, double3))).query.get ==> List(a, b)


        // AND/OR semantics with multiple Sets

        // "Not (exactly this AND that) OR (exactly this AND that)"
        NsSet.n.a1.doubles_.!=(Set(double1), Set(double2, double3)).query.get ==> List(a, b)
        NsSet.n.a1.doubles_.!=(Set(double1, double2), Set(double2, double3)).query.get ==> List(b)
        NsSet.n.a1.doubles_.!=(Set(double1, double2), Set(double2, double3, double4)).query.get ==> List()
        // Same as
        NsSet.n.a1.doubles_.!=(Seq(Set(double1), Set(double2, double3))).query.get ==> List(a, b)
        NsSet.n.a1.doubles_.!=(Seq(Set(double1, double2), Set(double2, double3))).query.get ==> List(b)
        NsSet.n.a1.doubles_.!=(Seq(Set(double1, double2), Set(double2, double3, double4))).query.get ==> List()


        // Empty Seq/Sets
        NsSet.n.a1.doubles_.!=(Seq(Set(double1, double2), Set.empty[Double])).query.get ==> List(b)
        NsSet.n.a1.doubles_.!=(Set.empty[Double]).query.get ==> List(a, b)
        NsSet.n.a1.doubles_.!=(Seq.empty[Set[Double]]).query.get ==> List(a, b)
      }


      "compare" - typesSet { implicit conn =>
        val (a, b) = (1, 2)
        NsSet.n.doubles.insert(List(
          (a, Set(double1, double2)),
          (b, Set(double2, double3, double4))
        )).transact

        NsSet.n.a1.doubles_.<(double0).query.get ==> List()
        NsSet.n.a1.doubles_.<(double1).query.get ==> List()
        NsSet.n.a1.doubles_.<(double2).query.get ==> List(a)
        NsSet.n.a1.doubles_.<(double3).query.get ==> List(a, b)

        NsSet.n.a1.doubles_.<=(double0).query.get ==> List()
        NsSet.n.a1.doubles_.<=(double1).query.get ==> List(a)
        NsSet.n.a1.doubles_.<=(double2).query.get ==> List(a, b)
        NsSet.n.a1.doubles_.<=(double3).query.get ==> List(a, b)

        NsSet.n.a1.doubles_.>(double0).query.get ==> List(a, b)
        NsSet.n.a1.doubles_.>(double1).query.get ==> List(a, b)
        NsSet.n.a1.doubles_.>(double2).query.get ==> List(b)
        NsSet.n.a1.doubles_.>(double3).query.get ==> List(b)

        NsSet.n.a1.doubles_.>=(double0).query.get ==> List(a, b)
        NsSet.n.a1.doubles_.>=(double1).query.get ==> List(a, b)
        NsSet.n.a1.doubles_.>=(double2).query.get ==> List(a, b)
        NsSet.n.a1.doubles_.>=(double3).query.get ==> List(b)
      }
    }


    "Optional" - {

      "attr" - typesSet { implicit conn =>
        val a = (1, Some(Set(double1, double2)))
        val b = (2, Some(Set(double2, double3, double4)))
        val c = (3, None)
        NsSet.n.doubles_?.insert(a, b, c).transact

        NsSet.n.a1.doubles_?.query.get ==> List(a, b, c)
      }


      "apply" - typesSet { implicit conn =>
        val a = (1, Some(Set(double1, double2)))
        val b = (2, Some(Set(double2, double3, double4)))
        val c = (3, None)
        NsSet.n.doubles_?.insert(a, b, c).transact

        // Sets with one or more values matching

        // "Has this value"
        NsSet.n.a1.doubles_?(Some(double0)).query.get ==> List()
        NsSet.n.a1.doubles_?(Some(double1)).query.get ==> List(a)
        NsSet.n.a1.doubles_?(Some(double2)).query.get ==> List(a, b)
        NsSet.n.a1.doubles_?(Some(double3)).query.get ==> List(b)
        // Same as
        NsSet.n.a1.doubles_?(Some(Seq(double0))).query.get ==> List()
        NsSet.n.a1.doubles_?(Some(Seq(double1))).query.get ==> List(a)
        NsSet.n.a1.doubles_?(Some(Seq(double2))).query.get ==> List(a, b)
        NsSet.n.a1.doubles_?(Some(Seq(double3))).query.get ==> List(b)


        // OR semantics when multiple values

        // "Has this OR that"
        NsSet.n.a1.doubles_?(Some(Seq(double1, double2))).query.get ==> List(a, b)
        NsSet.n.a1.doubles_?(Some(Seq(double1, double3))).query.get ==> List(a, b)
        NsSet.n.a1.doubles_?(Some(Seq(double2, double3))).query.get ==> List(a, b)
        NsSet.n.a1.doubles_?(Some(Seq(double1, double2, double3))).query.get ==> List(a, b)


        // AND semantics when multiple values in a _Set_

        // "Has this AND that"
        NsSet.n.a1.doubles_?(Some(Set(double1))).query.get ==> List(a)
        NsSet.n.a1.doubles_?(Some(Set(double1, double2))).query.get ==> List(a)
        NsSet.n.a1.doubles_?(Some(Set(double1, double2, double3))).query.get ==> List()
        NsSet.n.a1.doubles_?(Some(Set(double2))).query.get ==> List(a, b)
        NsSet.n.a1.doubles_?(Some(Set(double2, double3))).query.get ==> List(b)
        NsSet.n.a1.doubles_?(Some(Set(double2, double3, double4))).query.get ==> List(b)
        // Same as
        NsSet.n.a1.doubles_?(Some(Seq(Set(double1)))).query.get ==> List(a)
        NsSet.n.a1.doubles_?(Some(Seq(Set(double1, double2)))).query.get ==> List(a)
        NsSet.n.a1.doubles_?(Some(Seq(Set(double1, double2, double3)))).query.get ==> List()
        NsSet.n.a1.doubles_?(Some(Seq(Set(double2)))).query.get ==> List(a, b)
        NsSet.n.a1.doubles_?(Some(Seq(Set(double2, double3)))).query.get ==> List(b)
        NsSet.n.a1.doubles_?(Some(Seq(Set(double2, double3, double4)))).query.get ==> List(b)


        // AND/OR semantics with multiple Sets

        // "(has this AND that) OR (has this AND that)"
        NsSet.n.a1.doubles_?(Some(Seq(Set(double1, double2), Set(double0)))).query.get ==> List(a)
        NsSet.n.a1.doubles_?(Some(Seq(Set(double1, double2), Set(double0, double3)))).query.get ==> List(a)
        NsSet.n.a1.doubles_?(Some(Seq(Set(double1, double2), Set(double2, double3)))).query.get ==> List(a, b)
        NsSet.n.a1.doubles_?(Some(Seq(Set(double1, double2), Set(double2, double3, double4)))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        NsSet.n.a1.doubles_?(Some(Seq.empty[Double])).query.get ==> List()
        NsSet.n.a1.doubles_?(Some(Set.empty[Double])).query.get ==> List()
        NsSet.n.a1.doubles_?(Some(Seq.empty[Set[Double]])).query.get ==> List()


        // None matches non-asserted values
        NsSet.n.a1.doubles_?(Option.empty[Double]).query.get ==> List(c)
        NsSet.n.a1.doubles_?(Option.empty[Seq[Double]]).query.get ==> List(c)
        NsSet.n.a1.doubles_?(Option.empty[Set[Double]]).query.get ==> List(c)
        NsSet.n.a1.doubles_?(Option.empty[Seq[Set[Double]]]).query.get ==> List(c)
      }


      "not" - typesSet { implicit conn =>
        val a = (1, Some(Set(double1, double2)))
        val b = (2, Some(Set(double2, double3, double4)))
        val c = (3, None)
        NsSet.n.doubles_?.insert(a, b, c).transact

        // Sets without one or more values matching

        // "Doesn't have this value"
        NsSet.n.a1.doubles_?.not(Some(double0)).query.get ==> List(a, b)
        NsSet.n.a1.doubles_?.not(Some(double1)).query.get ==> List(b)
        NsSet.n.a1.doubles_?.not(Some(double2)).query.get ==> List()
        NsSet.n.a1.doubles_?.not(Some(double3)).query.get ==> List(a)
        NsSet.n.a1.doubles_?.not(Some(double4)).query.get ==> List(a)
        NsSet.n.a1.doubles_?.not(Some(double5)).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.doubles_?.not(Some(Seq(double0))).query.get ==> List(a, b)
        NsSet.n.a1.doubles_?.not(Some(Seq(double1))).query.get ==> List(b)
        NsSet.n.a1.doubles_?.not(Some(Seq(double2))).query.get ==> List()
        NsSet.n.a1.doubles_?.not(Some(Seq(double3))).query.get ==> List(a)
        NsSet.n.a1.doubles_?.not(Some(Seq(double4))).query.get ==> List(a)
        NsSet.n.a1.doubles_?.not(Some(Seq(double5))).query.get ==> List(a, b)


        // OR semantics when multiple values

        // "Not (has this OR that)"
        NsSet.n.a1.doubles_?.not(Some(Seq(double1, double2))).query.get ==> List()
        NsSet.n.a1.doubles_?.not(Some(Seq(double1, double3))).query.get ==> List()
        NsSet.n.a1.doubles_?.not(Some(Seq(double1, double4))).query.get ==> List()
        NsSet.n.a1.doubles_?.not(Some(Seq(double1, double5))).query.get ==> List(b)


        // AND semantics when multiple values in a _Set_

        // "Not (has this AND that)"
        NsSet.n.a1.doubles_?.not(Some(Set(double1))).query.get ==> List(b)
        NsSet.n.a1.doubles_?.not(Some(Set(double1, double2))).query.get ==> List(b)
        NsSet.n.a1.doubles_?.not(Some(Set(double1, double2, double3))).query.get ==> List(a, b)
        NsSet.n.a1.doubles_?.not(Some(Set(double2))).query.get ==> List()
        NsSet.n.a1.doubles_?.not(Some(Set(double2, double3))).query.get ==> List(a)
        NsSet.n.a1.doubles_?.not(Some(Set(double2, double3, double4))).query.get ==> List(a)
        // Same as
        NsSet.n.a1.doubles_?.not(Some(Seq(Set(double1)))).query.get ==> List(b)
        NsSet.n.a1.doubles_?.not(Some(Seq(Set(double1, double2)))).query.get ==> List(b)
        NsSet.n.a1.doubles_?.not(Some(Seq(Set(double1, double2, double3)))).query.get ==> List(a, b)
        NsSet.n.a1.doubles_?.not(Some(Seq(Set(double2)))).query.get ==> List()
        NsSet.n.a1.doubles_?.not(Some(Seq(Set(double2, double3)))).query.get ==> List(a)
        NsSet.n.a1.doubles_?.not(Some(Seq(Set(double2, double3, double4)))).query.get ==> List(a)


        // AND/OR semantics with multiple Sets

        // "Not ((has this AND that) OR (has this AND that))"
        NsSet.n.a1.doubles_?.not(Some(Seq(Set(double1, double2), Set(double0)))).query.get ==> List(b)
        NsSet.n.a1.doubles_?.not(Some(Seq(Set(double1, double2), Set(double0, double3)))).query.get ==> List(b)
        NsSet.n.a1.doubles_?.not(Some(Seq(Set(double1, double2), Set(double2, double3)))).query.get ==> List()
        NsSet.n.a1.doubles_?.not(Some(Seq(Set(double1, double2), Set(double2, double3, double4)))).query.get ==> List()


        // Negating empty Seqs/Sets has no effect
        NsSet.n.a1.doubles_?.not(Some(Seq.empty[Double])).query.get ==> List(a, b)
        NsSet.n.a1.doubles_?.not(Some(Set.empty[Double])).query.get ==> List(a, b)
        NsSet.n.a1.doubles_?.not(Some(Seq.empty[Set[Double]])).query.get ==> List(a, b)
        NsSet.n.a1.doubles_?.not(Some(Seq(Set.empty[Double]))).query.get ==> List(a, b)


        // Negating None returns all asserted
        NsSet.n.a1.doubles_?.not(Option.empty[Double]).query.get ==> List(a, b)
        NsSet.n.a1.doubles_?.not(Option.empty[Seq[Double]]).query.get ==> List(a, b)
        NsSet.n.a1.doubles_?.not(Option.empty[Set[Double]]).query.get ==> List(a, b)
        NsSet.n.a1.doubles_?.not(Option.empty[Seq[Set[Double]]]).query.get ==> List(a, b)
      }


      "==" - typesSet { implicit conn =>
        val a = (1, Some(Set(double1, double2)))
        val b = (2, Some(Set(double2, double3, double4)))
        val c = (3, None)
        NsSet.n.doubles_?.insert(a, b, c).transact

        // Exact Set matches

        // AND semantics
        // "Is exactly this AND that"
        NsSet.n.a1.doubles_?.==(Some(Set(double1))).query.get ==> List()
        NsSet.n.a1.doubles_?.==(Some(Set(double1, double2))).query.get ==> List(a) // include exact match
        NsSet.n.a1.doubles_?.==(Some(Set(double1, double2, double3))).query.get ==> List()
        // Same as
        NsSet.n.a1.doubles_?.==(Some(Seq(Set(double1)))).query.get ==> List()
        NsSet.n.a1.doubles_?.==(Some(Seq(Set(double1, double2)))).query.get ==> List(a)
        NsSet.n.a1.doubles_?.==(Some(Seq(Set(double1, double2, double3)))).query.get ==> List()


        // AND/OR semantics with multiple Sets

        // "(exactly this AND that) OR (exactly this AND that)"
        NsSet.n.a1.doubles_?.==(Some(Seq(Set(double1), Set(double2, double3)))).query.get ==> List()
        NsSet.n.a1.doubles_?.==(Some(Seq(Set(double1, double2), Set(double2, double3)))).query.get ==> List(a)
        NsSet.n.a1.doubles_?.==(Some(Seq(Set(double1, double2), Set(double2, double3, double4)))).query.get ==> List(a, b)


        // Empty Seq/Sets match nothing
        NsSet.n.a1.doubles_?.==(Some(Set.empty[Double])).query.get ==> List()
        NsSet.n.a1.doubles_?.==(Some(Seq.empty[Set[Double]])).query.get ==> List()
        NsSet.n.a1.doubles_?.==(Some(Seq(Set.empty[Double]))).query.get ==> List()


        // None matches non-asserted values
        NsSet.n.a1.doubles_?.==(Option.empty[Set[Double]]).query.get ==> List(c)
        NsSet.n.a1.doubles_?.==(Option.empty[Seq[Set[Double]]]).query.get ==> List(c)
      }


      "!=" - typesSet { implicit conn =>
        val a = (1, Some(Set(double1, double2)))
        val b = (2, Some(Set(double2, double3, double4)))
        val c = (3, None)
        NsSet.n.doubles_?.insert(a, b, c).transact

        // Non-exact Set matches

        // AND semantics
        // "Not (exactly this AND that)"
        NsSet.n.a1.doubles_?.!=(Some(Set(double1))).query.get ==> List(a, b)
        NsSet.n.a1.doubles_?.!=(Some(Set(double1, double2))).query.get ==> List(b) // exclude exact match
        NsSet.n.a1.doubles_?.!=(Some(Set(double1, double2, double3))).query.get ==> List(a, b)
        // Same as
        NsSet.n.a1.doubles_?.!=(Some(Seq(Set(double1)))).query.get ==> List(a, b)
        NsSet.n.a1.doubles_?.!=(Some(Seq(Set(double1, double2)))).query.get ==> List(b)
        NsSet.n.a1.doubles_?.!=(Some(Seq(Set(double1, double2, double3)))).query.get ==> List(a, b)


        // AND/OR semantics with multiple Sets

        // "Not (exactly this AND that) OR (exactly this AND that)"
        NsSet.n.a1.doubles_?.!=(Some(Seq(Set(double1), Set(double2, double3)))).query.get ==> List(a, b)
        NsSet.n.a1.doubles_?.!=(Some(Seq(Set(double1, double2), Set(double2, double3)))).query.get ==> List(b)
        NsSet.n.a1.doubles_?.!=(Some(Seq(Set(double1, double2), Set(double2, double3, double4)))).query.get ==> List()


        // Empty Seq/Sets
        NsSet.n.a1.doubles_?.!=(Some(Seq(Set(double1, double2), Set.empty[Double]))).query.get ==> List(b)
        NsSet.n.a1.doubles_?.!=(Some(Set.empty[Double])).query.get ==> List(a, b)
        NsSet.n.a1.doubles_?.!=(Some(Seq.empty[Set[Double]])).query.get ==> List(a, b)


        // None matches non-asserted values
        NsSet.n.a1.doubles_?.==(Option.empty[Set[Double]]).query.get ==> List(c)
        NsSet.n.a1.doubles_?.==(Option.empty[Seq[Set[Double]]]).query.get ==> List(c)
      }


      "compare" - typesSet { implicit conn =>
        val a = (1, Some(Set(double1, double2)))
        val b = (2, Some(Set(double2, double3, double4)))
        val c = (3, None)
        NsSet.n.doubles_?.insert(a, b, c).transact

        NsSet.n.a1.doubles_?.<(Some(double0)).query.get ==> List()
        NsSet.n.a1.doubles_?.<(Some(double1)).query.get ==> List()
        NsSet.n.a1.doubles_?.<(Some(double2)).query.get ==> List(a)
        NsSet.n.a1.doubles_?.<(Some(double3)).query.get ==> List(a, b)

        NsSet.n.a1.doubles_?.<=(Some(double0)).query.get ==> List()
        NsSet.n.a1.doubles_?.<=(Some(double1)).query.get ==> List(a)
        NsSet.n.a1.doubles_?.<=(Some(double2)).query.get ==> List(a, b)
        NsSet.n.a1.doubles_?.<=(Some(double3)).query.get ==> List(a, b)

        NsSet.n.a1.doubles_?.>(Some(double0)).query.get ==> List(a, b)
        NsSet.n.a1.doubles_?.>(Some(double1)).query.get ==> List(a, b)
        NsSet.n.a1.doubles_?.>(Some(double2)).query.get ==> List(b)
        NsSet.n.a1.doubles_?.>(Some(double3)).query.get ==> List(b)

        NsSet.n.a1.doubles_?.>=(Some(double0)).query.get ==> List(a, b)
        NsSet.n.a1.doubles_?.>=(Some(double1)).query.get ==> List(a, b)
        NsSet.n.a1.doubles_?.>=(Some(double2)).query.get ==> List(a, b)
        NsSet.n.a1.doubles_?.>=(Some(double3)).query.get ==> List(b)


        // None matches any asserted values
        NsSet.n.a1.doubles_?.<(None).query.get ==> List(a, b)
        NsSet.n.a1.doubles_?.>(None).query.get ==> List(a, b)
        NsSet.n.a1.doubles_?.<=(None).query.get ==> List(a, b)
        NsSet.n.a1.doubles_?.>=(None).query.get ==> List(a, b)
      }
    }
  }
}