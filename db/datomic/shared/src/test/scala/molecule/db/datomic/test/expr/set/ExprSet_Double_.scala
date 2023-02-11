// GENERATED CODE ********************************
package molecule.db.datomic.test.expr.set

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic.async._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._


object ExprSet_Double_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "Mandatory" - {

      "attr" - types { implicit conn =>
        val a = (1, Set(double1, double2))
        val b = (2, Set(double2, double3, double4))
        for {
          _ <- Ns.i.doubles.insert(List(a, b)).transact

          _ <- Ns.i.a1.doubles.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "apply" - types { implicit conn =>
        val a = (1, Set(double1, double2))
        val b = (2, Set(double2, double3, double4))
        for {
          _ <- Ns.i.doubles.insert(List(a, b)).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.doubles(double0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles(double1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles(double2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles(double3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.doubles(Seq(double0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles(Seq(double1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles(Seq(double2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles(Seq(double3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.doubles(double1, double2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles(double1, double3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles(double2, double3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles(double1, double2, double3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.doubles(Seq(double1, double2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles(Seq(double1, double3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles(Seq(double2, double3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles(Seq(double1, double2, double3)).query.get.map(_ ==> List(a, b))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.doubles(Set(double1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles(Set(double1, double2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles(Set(double1, double2, double3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles(Set(double2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles(Set(double2, double3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles(Set(double2, double3, double4)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.doubles(Seq(Set(double1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles(Seq(Set(double1, double2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles(Seq(Set(double1, double2, double3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles(Seq(Set(double2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles(Seq(Set(double2, double3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles(Seq(Set(double2, double3, double4))).query.get.map(_ ==> List(b))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.doubles(Set(double1, double2), Set(double0)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles(Set(double1, double2), Set(double0, double3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles(Set(double1, double2), Set(double2, double3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles(Set(double1, double2), Set(double2, double3, double4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.doubles(Seq(Set(double1, double2), Set(double0))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles(Seq(Set(double1, double2), Set(double0, double3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles(Seq(Set(double1, double2), Set(double2, double3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles(Seq(Set(double1, double2), Set(double2, double3, double4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.doubles(Set(double1, double2), Set.empty[Double]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles(Seq.empty[Double]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles(Set.empty[Double]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles(Seq.empty[Set[Double]]).query.get.map(_ ==> List())
        } yield ()
      }


      "not" - types { implicit conn =>
        val a = (1, Set(double1, double2))
        val b = (2, Set(double2, double3, double4))
        for {
          _ <- Ns.i.doubles.insert(List(a, b)).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.doubles.not(double0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles.not(double1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles.not(double2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles.not(double3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles.not(double4).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles.not(double5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.doubles.not(Seq(double0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles.not(Seq(double1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles.not(Seq(double2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles.not(Seq(double3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles.not(Seq(double4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles.not(Seq(double5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.doubles.not(double1, double2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles.not(double1, double3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles.not(double1, double4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles.not(double1, double5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.doubles.not(Seq(double1, double2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles.not(Seq(double1, double3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles.not(Seq(double1, double4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles.not(Seq(double1, double5)).query.get.map(_ ==> List(b))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.doubles.not(Set(double1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles.not(Set(double1, double2)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles.not(Set(double1, double2, double3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles.not(Set(double2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles.not(Set(double2, double3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles.not(Set(double2, double3, double4)).query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.doubles.not(Seq(Set(double1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles.not(Seq(Set(double1, double2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles.not(Seq(Set(double1, double2, double3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles.not(Seq(Set(double2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles.not(Seq(Set(double2, double3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles.not(Seq(Set(double2, double3, double4))).query.get.map(_ ==> List(a))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.doubles.not(Set(double1, double2), Set(double0)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles.not(Set(double1, double2), Set(double0, double3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles.not(Set(double1, double2), Set(double2, double3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles.not(Set(double1, double2), Set(double2, double3, double4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.doubles.not(Seq(Set(double1, double2), Set(double0))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles.not(Seq(Set(double1, double2), Set(double0, double3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles.not(Seq(Set(double1, double2), Set(double2, double3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles.not(Seq(Set(double1, double2), Set(double2, double3, double4))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.doubles.not(Set(double1, double2), Set.empty[Double]).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles.not(Seq.empty[Double]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles.not(Set.empty[Double]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles.not(Seq.empty[Set[Double]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles.not(Seq(Set.empty[Double])).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "==" - types { implicit conn =>
        val a = (1, Set(double1, double2))
        val b = (2, Set(double2, double3, double4))
        for {
          _ <- Ns.i.doubles.insert(List(a, b)).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.doubles.==(Set(double1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles.==(Set(double1, double2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.doubles.==(Set(double1, double2, double3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.doubles.==(Seq(Set(double1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles.==(Seq(Set(double1, double2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles.==(Seq(Set(double1, double2, double3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.doubles.==(Set(double1), Set(double2, double3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles.==(Set(double1, double2), Set(double2, double3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles.==(Set(double1, double2), Set(double2, double3, double4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.doubles.==(Seq(Set(double1), Set(double2, double3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles.==(Seq(Set(double1, double2), Set(double2, double3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles.==(Seq(Set(double1, double2), Set(double2, double3, double4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.doubles.==(Set(double1, double2), Set.empty[Double]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles.==(Set.empty[Double], Set(double1, double2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles.==(Set.empty[Double]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles.==(Seq.empty[Set[Double]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles.==(Seq(Set.empty[Double])).query.get.map(_ ==> List())
        } yield ()
      }


      "!=" - types { implicit conn =>
        val a = (1, Set(double1, double2))
        val b = (2, Set(double2, double3, double4))
        for {
          _ <- Ns.i.doubles.insert(List(a, b)).transact

          // Exact Set non-matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.doubles.!=(Set(double1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles.!=(Set(double1, double2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.doubles.!=(Set(double1, double2, double3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.doubles.!=(Seq(Set(double1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles.!=(Seq(Set(double1, double2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles.!=(Seq(Set(double1, double2, double3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "Not (exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.doubles.!=(Set(double1), Set(double2, double3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles.!=(Set(double1, double2), Set(double2, double3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles.!=(Set(double1, double2), Set(double2, double3, double4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.doubles.!=(Seq(Set(double1), Set(double2, double3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles.!=(Seq(Set(double1, double2), Set(double2, double3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles.!=(Seq(Set(double1, double2), Set(double2, double3, double4))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.doubles.!=(Seq(Set(double1, double2), Set.empty[Double])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles.!=(Set.empty[Double]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles.!=(Seq.empty[Set[Double]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "compare" - types { implicit conn =>
        val a = (1, Set(double1, double2))
        val b = (2, Set(double2, double3, double4))
        for {
          _ <- Ns.i.doubles.insert(List(a, b)).transact

          _ <- Ns.i.a1.doubles.<(double0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles.<(double1).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles.<(double2).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles.<(double3).query.get.map(_ ==> List(a, b))

          _ <- Ns.i.a1.doubles.<=(double0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles.<=(double1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles.<=(double2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles.<=(double3).query.get.map(_ ==> List(a, b))

          _ <- Ns.i.a1.doubles.>(double0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles.>(double1).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles.>(double2).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles.>(double3).query.get.map(_ ==> List(b))

          _ <- Ns.i.a1.doubles.>=(double0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles.>=(double1).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles.>=(double2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles.>=(double3).query.get.map(_ ==> List(b))
        } yield ()
      }
    }


    "Tacit" - {

      "attr" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.doubles.insert(List(
            (a, Set(double1, double2)),
            (b, Set(double2, double3, double4))
          )).transact

          _ <- Ns.i.a1.doubles_.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "apply" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.doubles.insert(List(
            (a, Set(double1, double2)),
            (b, Set(double2, double3, double4))
          )).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.doubles_(double0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_(double1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles_(double2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_(double3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.doubles_(Seq(double0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_(Seq(double1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles_(Seq(double2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_(Seq(double3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.doubles_(double1, double2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_(double1, double3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_(double2, double3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_(double1, double2, double3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.doubles_(Seq(double1, double2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_(Seq(double1, double3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_(Seq(double2, double3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_(Seq(double1, double2, double3)).query.get.map(_ ==> List(a, b))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.doubles_(Set(double1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles_(Set(double1, double2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles_(Set(double1, double2, double3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_(Set(double2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_(Set(double2, double3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles_(Set(double2, double3, double4)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.doubles_(Seq(Set(double1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles_(Seq(Set(double1, double2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles_(Seq(Set(double1, double2, double3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_(Seq(Set(double2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_(Seq(Set(double2, double3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles_(Seq(Set(double2, double3, double4))).query.get.map(_ ==> List(b))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.doubles_(Set(double1, double2), Set(double0)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles_(Set(double1, double2), Set(double0, double3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles_(Set(double1, double2), Set(double2, double3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_(Set(double1, double2), Set(double2, double3, double4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.doubles_(Seq(Set(double1, double2), Set(double0))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles_(Seq(Set(double1, double2), Set(double0, double3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles_(Seq(Set(double1, double2), Set(double2, double3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_(Seq(Set(double1, double2), Set(double2, double3, double4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.doubles_(Set(double1, double2), Set.empty[Double]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles_(Seq.empty[Double]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_(Set.empty[Double]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_(Seq.empty[Set[Double]]).query.get.map(_ ==> List())
        } yield ()
      }


      "not" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.doubles.insert(List(
            (a, Set(double1, double2)),
            (b, Set(double2, double3, double4))
          )).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.doubles_.not(double0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_.not(double1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles_.not(double2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_.not(double3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles_.not(double4).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles_.not(double5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.doubles_.not(Seq(double0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_.not(Seq(double1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles_.not(Seq(double2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_.not(Seq(double3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles_.not(Seq(double4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles_.not(Seq(double5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.doubles_.not(double1, double2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_.not(double1, double3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_.not(double1, double4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_.not(double1, double5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.doubles_.not(Seq(double1, double2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_.not(Seq(double1, double3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_.not(Seq(double1, double4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_.not(Seq(double1, double5)).query.get.map(_ ==> List(b))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.doubles_.not(Set(double1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles_.not(Set(double1, double2)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles_.not(Set(double1, double2, double3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_.not(Set(double2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_.not(Set(double2, double3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles_.not(Set(double2, double3, double4)).query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.doubles_.not(Seq(Set(double1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles_.not(Seq(Set(double1, double2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles_.not(Seq(Set(double1, double2, double3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_.not(Seq(Set(double2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_.not(Seq(Set(double2, double3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles_.not(Seq(Set(double2, double3, double4))).query.get.map(_ ==> List(a))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.doubles_.not(Set(double1, double2), Set(double0)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles_.not(Set(double1, double2), Set(double0, double3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles_.not(Set(double1, double2), Set(double2, double3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_.not(Set(double1, double2), Set(double2, double3, double4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.doubles_.not(Seq(Set(double1, double2), Set(double0))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles_.not(Seq(Set(double1, double2), Set(double0, double3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles_.not(Seq(Set(double1, double2), Set(double2, double3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_.not(Seq(Set(double1, double2), Set(double2, double3, double4))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.doubles_.not(Set(double1, double2), Set.empty[Double]).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles_.not(Seq.empty[Double]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_.not(Set.empty[Double]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_.not(Seq.empty[Set[Double]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_.not(Seq(Set.empty[Double])).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "==" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.doubles.insert(List(
            (a, Set(double1, double2)),
            (b, Set(double2, double3, double4))
          )).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.doubles_.==(Set(double1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_.==(Set(double1, double2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.doubles_.==(Set(double1, double2, double3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.doubles_.==(Seq(Set(double1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_.==(Seq(Set(double1, double2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles_.==(Seq(Set(double1, double2, double3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.doubles_.==(Set(double1), Set(double2, double3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_.==(Set(double1, double2), Set(double2, double3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles_.==(Set(double1, double2), Set(double2, double3, double4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.doubles_.==(Seq(Set(double1), Set(double2, double3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_.==(Seq(Set(double1, double2), Set(double2, double3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles_.==(Seq(Set(double1, double2), Set(double2, double3, double4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.doubles_.==(Set(double1, double2), Set.empty[Double]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles_.==(Set.empty[Double]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_.==(Seq.empty[Set[Double]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_.==(Seq(Set.empty[Double])).query.get.map(_ ==> List())
        } yield ()
      }


      "!=" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.doubles.insert(List(
            (a, Set(double1, double2)),
            (b, Set(double2, double3, double4))
          )).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.doubles_.!=(Set(double1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_.!=(Set(double1, double2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.doubles_.!=(Set(double1, double2, double3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.doubles_.!=(Seq(Set(double1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_.!=(Seq(Set(double1, double2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles_.!=(Seq(Set(double1, double2, double3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "Not (exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.doubles_.!=(Set(double1), Set(double2, double3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_.!=(Set(double1, double2), Set(double2, double3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles_.!=(Set(double1, double2), Set(double2, double3, double4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.doubles_.!=(Seq(Set(double1), Set(double2, double3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_.!=(Seq(Set(double1, double2), Set(double2, double3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles_.!=(Seq(Set(double1, double2), Set(double2, double3, double4))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.doubles_.!=(Seq(Set(double1, double2), Set.empty[Double])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles_.!=(Set.empty[Double]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_.!=(Seq.empty[Set[Double]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "compare" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.doubles.insert(List(
            (a, Set(double1, double2)),
            (b, Set(double2, double3, double4))
          )).transact

          _ <- Ns.i.a1.doubles_.<(double0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_.<(double1).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_.<(double2).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles_.<(double3).query.get.map(_ ==> List(a, b))

          _ <- Ns.i.a1.doubles_.<=(double0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_.<=(double1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles_.<=(double2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_.<=(double3).query.get.map(_ ==> List(a, b))

          _ <- Ns.i.a1.doubles_.>(double0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_.>(double1).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_.>(double2).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles_.>(double3).query.get.map(_ ==> List(b))

          _ <- Ns.i.a1.doubles_.>=(double0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_.>=(double1).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_.>=(double2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_.>=(double3).query.get.map(_ ==> List(b))
        } yield ()
      }
    }


    "Optional" - {

      "attr" - types { implicit conn =>
        val a = (1, Some(Set(double1, double2)))
        val b = (2, Some(Set(double2, double3, double4)))
        val c = (3, None)
        for {
          _ <- Ns.i.doubles_?.insert(a, b, c).transact

          _ <- Ns.i.a1.doubles_?.query.get.map(_ ==> List(a, b, c))

          // Distinct result even with redundant None's (two i = 3 with no double value)
          _ <- Ns.i.insert(3).transact
          _ <- Ns.i.>=(2).a1.doubles_?.query.get.map(_ ==> List(
            (2, Some(Set(double2, double3, double4))),
            (3, None),
          ))
        } yield ()
      }


      "apply" - types { implicit conn =>
        val a = (1, Some(Set(double1, double2)))
        val b = (2, Some(Set(double2, double3, double4)))
        val c = (3, None)
        for {
          _ <- Ns.i.doubles_?.insert(a, b, c).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.doubles_?(Some(double0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_?(Some(double1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles_?(Some(double2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_?(Some(double3)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.doubles_?(Some(Seq(double0))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_?(Some(Seq(double1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles_?(Some(Seq(double2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_?(Some(Seq(double3))).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.doubles_?(Some(Seq(double1, double2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_?(Some(Seq(double1, double3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_?(Some(Seq(double2, double3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_?(Some(Seq(double1, double2, double3))).query.get.map(_ ==> List(a, b))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.doubles_?(Some(Set(double1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles_?(Some(Set(double1, double2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles_?(Some(Set(double1, double2, double3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_?(Some(Set(double2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_?(Some(Set(double2, double3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles_?(Some(Set(double2, double3, double4))).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.doubles_?(Some(Seq(Set(double1)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles_?(Some(Seq(Set(double1, double2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles_?(Some(Seq(Set(double1, double2, double3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_?(Some(Seq(Set(double2)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_?(Some(Seq(Set(double2, double3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles_?(Some(Seq(Set(double2, double3, double4)))).query.get.map(_ ==> List(b))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.doubles_?(Some(Seq(Set(double1, double2), Set(double0)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles_?(Some(Seq(Set(double1, double2), Set(double0, double3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles_?(Some(Seq(Set(double1, double2), Set(double2, double3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_?(Some(Seq(Set(double1, double2), Set(double2, double3, double4)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.doubles_?(Some(Seq.empty[Double])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_?(Some(Set.empty[Double])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_?(Some(Seq.empty[Set[Double]])).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.doubles_?(Option.empty[Double]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.doubles_?(Option.empty[Seq[Double]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.doubles_?(Option.empty[Set[Double]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.doubles_?(Option.empty[Seq[Set[Double]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "not" - types { implicit conn =>
        val a = (1, Some(Set(double1, double2)))
        val b = (2, Some(Set(double2, double3, double4)))
        val c = (3, None)
        for {
          _ <- Ns.i.doubles_?.insert(a, b, c).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.doubles_?.not(Some(double0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_?.not(Some(double1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles_?.not(Some(double2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_?.not(Some(double3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles_?.not(Some(double4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles_?.not(Some(double5)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.doubles_?.not(Some(Seq(double0))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_?.not(Some(Seq(double1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles_?.not(Some(Seq(double2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_?.not(Some(Seq(double3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles_?.not(Some(Seq(double4))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles_?.not(Some(Seq(double5))).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.doubles_?.not(Some(Seq(double1, double2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_?.not(Some(Seq(double1, double3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_?.not(Some(Seq(double1, double4))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_?.not(Some(Seq(double1, double5))).query.get.map(_ ==> List(b))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.doubles_?.not(Some(Set(double1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles_?.not(Some(Set(double1, double2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles_?.not(Some(Set(double1, double2, double3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_?.not(Some(Set(double2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_?.not(Some(Set(double2, double3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles_?.not(Some(Set(double2, double3, double4))).query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.doubles_?.not(Some(Seq(Set(double1)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles_?.not(Some(Seq(Set(double1, double2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles_?.not(Some(Seq(Set(double1, double2, double3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_?.not(Some(Seq(Set(double2)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_?.not(Some(Seq(Set(double2, double3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles_?.not(Some(Seq(Set(double2, double3, double4)))).query.get.map(_ ==> List(a))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.doubles_?.not(Some(Seq(Set(double1, double2), Set(double0)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles_?.not(Some(Seq(Set(double1, double2), Set(double0, double3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles_?.not(Some(Seq(Set(double1, double2), Set(double2, double3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_?.not(Some(Seq(Set(double1, double2), Set(double2, double3, double4)))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.doubles_?.not(Some(Seq.empty[Double])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_?.not(Some(Set.empty[Double])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_?.not(Some(Seq.empty[Set[Double]])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_?.not(Some(Seq(Set.empty[Double]))).query.get.map(_ ==> List(a, b))


          // Negating None returns all asserted
          _ <- Ns.i.a1.doubles_?.not(Option.empty[Double]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_?.not(Option.empty[Seq[Double]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_?.not(Option.empty[Set[Double]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_?.not(Option.empty[Seq[Set[Double]]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "==" - types { implicit conn =>
        val a = (1, Some(Set(double1, double2)))
        val b = (2, Some(Set(double2, double3, double4)))
        val c = (3, None)
        for {
          _ <- Ns.i.doubles_?.insert(a, b, c).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.doubles_?.==(Some(Set(double1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_?.==(Some(Set(double1, double2))).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.doubles_?.==(Some(Set(double1, double2, double3))).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.doubles_?.==(Some(Seq(Set(double1)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_?.==(Some(Seq(Set(double1, double2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles_?.==(Some(Seq(Set(double1, double2, double3)))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.doubles_?.==(Some(Seq(Set(double1), Set(double2, double3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_?.==(Some(Seq(Set(double1, double2), Set(double2, double3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles_?.==(Some(Seq(Set(double1, double2), Set(double2, double3, double4)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.doubles_?.==(Some(Set.empty[Double])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_?.==(Some(Seq.empty[Set[Double]])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_?.==(Some(Seq(Set.empty[Double]))).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.doubles_?.==(Option.empty[Set[Double]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.doubles_?.==(Option.empty[Seq[Set[Double]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "!=" - types { implicit conn =>
        val a = (1, Some(Set(double1, double2)))
        val b = (2, Some(Set(double2, double3, double4)))
        val c = (3, None)
        for {
          _ <- Ns.i.doubles_?.insert(a, b, c).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.doubles_?.!=(Some(Set(double1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_?.!=(Some(Set(double1, double2))).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.doubles_?.!=(Some(Set(double1, double2, double3))).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.doubles_?.!=(Some(Seq(Set(double1)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_?.!=(Some(Seq(Set(double1, double2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles_?.!=(Some(Seq(Set(double1, double2, double3)))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "Not (exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.doubles_?.!=(Some(Seq(Set(double1), Set(double2, double3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_?.!=(Some(Seq(Set(double1, double2), Set(double2, double3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles_?.!=(Some(Seq(Set(double1, double2), Set(double2, double3, double4)))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.doubles_?.!=(Some(Seq(Set(double1, double2), Set.empty[Double]))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles_?.!=(Some(Set.empty[Double])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_?.!=(Some(Seq.empty[Set[Double]])).query.get.map(_ ==> List(a, b))


          // None matches non-asserted values
          _ <- Ns.i.a1.doubles_?.==(Option.empty[Set[Double]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.doubles_?.==(Option.empty[Seq[Set[Double]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "compare" - types { implicit conn =>
        val a = (1, Some(Set(double1, double2)))
        val b = (2, Some(Set(double2, double3, double4)))
        val c = (3, None)
        for {
          _ <- Ns.i.doubles_?.insert(a, b, c).transact

          _ <- Ns.i.a1.doubles_?.<(Some(double0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_?.<(Some(double1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_?.<(Some(double2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles_?.<(Some(double3)).query.get.map(_ ==> List(a, b))

          _ <- Ns.i.a1.doubles_?.<=(Some(double0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubles_?.<=(Some(double1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubles_?.<=(Some(double2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_?.<=(Some(double3)).query.get.map(_ ==> List(a, b))

          _ <- Ns.i.a1.doubles_?.>(Some(double0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_?.>(Some(double1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_?.>(Some(double2)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubles_?.>(Some(double3)).query.get.map(_ ==> List(b))

          _ <- Ns.i.a1.doubles_?.>=(Some(double0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_?.>=(Some(double1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_?.>=(Some(double2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_?.>=(Some(double3)).query.get.map(_ ==> List(b))


          // None matches any asserted values
          _ <- Ns.i.a1.doubles_?.<(None).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_?.>(None).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_?.<=(None).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubles_?.>=(None).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}