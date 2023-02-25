// GENERATED CODE ********************************
package molecule.datomic.test.expr.set

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.datomic.setup.DatomicTestSuite
import molecule.datomic.async._
import utest._


object ExprSet_Long_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "Mandatory" - {

      "attr" - types { implicit conn =>
        val a = (1, Set(long1, long2))
        val b = (2, Set(long2, long3, long4))
        for {
          _ <- Ns.i.longs.insert(List(a, b)).transact

          _ <- Ns.i.a1.longs.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "apply" - types { implicit conn =>
        val a = (1, Set(long1, long2))
        val b = (2, Set(long2, long3, long4))
        for {
          _ <- Ns.i.longs.insert(List(a, b)).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.longs(long0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs(long1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs(long2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs(long3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.longs(Seq(long0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs(Seq(long1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs(Seq(long2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs(Seq(long3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.longs(long1, long2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs(long1, long3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs(long2, long3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs(long1, long2, long3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.longs(Seq(long1, long2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs(Seq(long1, long3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs(Seq(long2, long3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs(Seq(long1, long2, long3)).query.get.map(_ ==> List(a, b))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.longs(Set(long1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs(Set(long1, long2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs(Set(long1, long2, long3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs(Set(long2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs(Set(long2, long3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.longs(Set(long2, long3, long4)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.longs(Seq(Set(long1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs(Seq(Set(long1, long2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs(Seq(Set(long1, long2, long3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs(Seq(Set(long2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs(Seq(Set(long2, long3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.longs(Seq(Set(long2, long3, long4))).query.get.map(_ ==> List(b))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.longs(Set(long1, long2), Set(long0)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs(Set(long1, long2), Set(long0, long3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs(Set(long1, long2), Set(long2, long3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs(Set(long1, long2), Set(long2, long3, long4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.longs(Seq(Set(long1, long2), Set(long0))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs(Seq(Set(long1, long2), Set(long0, long3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs(Seq(Set(long1, long2), Set(long2, long3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs(Seq(Set(long1, long2), Set(long2, long3, long4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.longs(Set(long1, long2), Set.empty[Long]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs(Seq.empty[Long]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs(Set.empty[Long]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs(Seq.empty[Set[Long]]).query.get.map(_ ==> List())
        } yield ()
      }


      "not" - types { implicit conn =>
        val a = (1, Set(long1, long2))
        val b = (2, Set(long2, long3, long4))
        for {
          _ <- Ns.i.longs.insert(List(a, b)).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.longs.not(long0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs.not(long1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.longs.not(long2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs.not(long3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs.not(long4).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs.not(long5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.longs.not(Seq(long0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs.not(Seq(long1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.longs.not(Seq(long2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs.not(Seq(long3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs.not(Seq(long4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs.not(Seq(long5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.longs.not(long1, long2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs.not(long1, long3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs.not(long1, long4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs.not(long1, long5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.longs.not(Seq(long1, long2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs.not(Seq(long1, long3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs.not(Seq(long1, long4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs.not(Seq(long1, long5)).query.get.map(_ ==> List(b))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.longs.not(Set(long1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.longs.not(Set(long1, long2)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.longs.not(Set(long1, long2, long3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs.not(Set(long2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs.not(Set(long2, long3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs.not(Set(long2, long3, long4)).query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.longs.not(Seq(Set(long1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.longs.not(Seq(Set(long1, long2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.longs.not(Seq(Set(long1, long2, long3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs.not(Seq(Set(long2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs.not(Seq(Set(long2, long3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs.not(Seq(Set(long2, long3, long4))).query.get.map(_ ==> List(a))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.longs.not(Set(long1, long2), Set(long0)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.longs.not(Set(long1, long2), Set(long0, long3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.longs.not(Set(long1, long2), Set(long2, long3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs.not(Set(long1, long2), Set(long2, long3, long4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.longs.not(Seq(Set(long1, long2), Set(long0))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.longs.not(Seq(Set(long1, long2), Set(long0, long3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.longs.not(Seq(Set(long1, long2), Set(long2, long3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs.not(Seq(Set(long1, long2), Set(long2, long3, long4))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.longs.not(Set(long1, long2), Set.empty[Long]).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.longs.not(Seq.empty[Long]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs.not(Set.empty[Long]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs.not(Seq.empty[Set[Long]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs.not(Seq(Set.empty[Long])).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "==" - types { implicit conn =>
        val a = (1, Set(long1, long2))
        val b = (2, Set(long2, long3, long4))
        for {
          _ <- Ns.i.longs.insert(List(a, b)).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.longs.==(Set(long1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs.==(Set(long1, long2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.longs.==(Set(long1, long2, long3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.longs.==(Seq(Set(long1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs.==(Seq(Set(long1, long2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs.==(Seq(Set(long1, long2, long3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.longs.==(Set(long1), Set(long2, long3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs.==(Set(long1, long2), Set(long2, long3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs.==(Set(long1, long2), Set(long2, long3, long4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.longs.==(Seq(Set(long1), Set(long2, long3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs.==(Seq(Set(long1, long2), Set(long2, long3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs.==(Seq(Set(long1, long2), Set(long2, long3, long4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.longs.==(Set(long1, long2), Set.empty[Long]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs.==(Set.empty[Long], Set(long1, long2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs.==(Set.empty[Long]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs.==(Seq.empty[Set[Long]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs.==(Seq(Set.empty[Long])).query.get.map(_ ==> List())
        } yield ()
      }


      "!=" - types { implicit conn =>
        val a = (1, Set(long1, long2))
        val b = (2, Set(long2, long3, long4))
        for {
          _ <- Ns.i.longs.insert(List(a, b)).transact

          // Exact Set non-matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.longs.!=(Set(long1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs.!=(Set(long1, long2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.longs.!=(Set(long1, long2, long3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.longs.!=(Seq(Set(long1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs.!=(Seq(Set(long1, long2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.longs.!=(Seq(Set(long1, long2, long3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "Not (exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.longs.!=(Set(long1), Set(long2, long3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs.!=(Set(long1, long2), Set(long2, long3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.longs.!=(Set(long1, long2), Set(long2, long3, long4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.longs.!=(Seq(Set(long1), Set(long2, long3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs.!=(Seq(Set(long1, long2), Set(long2, long3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.longs.!=(Seq(Set(long1, long2), Set(long2, long3, long4))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.longs.!=(Seq(Set(long1, long2), Set.empty[Long])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.longs.!=(Set.empty[Long]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs.!=(Seq.empty[Set[Long]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "compare" - types { implicit conn =>
        val a = (1, Set(long1, long2))
        val b = (2, Set(long2, long3, long4))
        for {
          _ <- Ns.i.longs.insert(List(a, b)).transact

          _ <- Ns.i.a1.longs.<(long0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs.<(long1).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs.<(long2).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs.<(long3).query.get.map(_ ==> List(a, b))

          _ <- Ns.i.a1.longs.<=(long0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs.<=(long1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs.<=(long2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs.<=(long3).query.get.map(_ ==> List(a, b))

          _ <- Ns.i.a1.longs.>(long0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs.>(long1).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs.>(long2).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.longs.>(long3).query.get.map(_ ==> List(b))

          _ <- Ns.i.a1.longs.>=(long0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs.>=(long1).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs.>=(long2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs.>=(long3).query.get.map(_ ==> List(b))
        } yield ()
      }
    }


    "Tacit" - {

      "attr" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.longs.insert(List(
            (a, Set(long1, long2)),
            (b, Set(long2, long3, long4))
          )).transact

          _ <- Ns.i.a1.longs_.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "apply" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.longs.insert(List(
            (a, Set(long1, long2)),
            (b, Set(long2, long3, long4))
          )).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.longs_(long0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_(long1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs_(long2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_(long3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.longs_(Seq(long0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_(Seq(long1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs_(Seq(long2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_(Seq(long3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.longs_(long1, long2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_(long1, long3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_(long2, long3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_(long1, long2, long3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.longs_(Seq(long1, long2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_(Seq(long1, long3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_(Seq(long2, long3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_(Seq(long1, long2, long3)).query.get.map(_ ==> List(a, b))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.longs_(Set(long1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs_(Set(long1, long2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs_(Set(long1, long2, long3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_(Set(long2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_(Set(long2, long3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.longs_(Set(long2, long3, long4)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.longs_(Seq(Set(long1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs_(Seq(Set(long1, long2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs_(Seq(Set(long1, long2, long3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_(Seq(Set(long2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_(Seq(Set(long2, long3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.longs_(Seq(Set(long2, long3, long4))).query.get.map(_ ==> List(b))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.longs_(Set(long1, long2), Set(long0)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs_(Set(long1, long2), Set(long0, long3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs_(Set(long1, long2), Set(long2, long3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_(Set(long1, long2), Set(long2, long3, long4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.longs_(Seq(Set(long1, long2), Set(long0))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs_(Seq(Set(long1, long2), Set(long0, long3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs_(Seq(Set(long1, long2), Set(long2, long3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_(Seq(Set(long1, long2), Set(long2, long3, long4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.longs_(Set(long1, long2), Set.empty[Long]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs_(Seq.empty[Long]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_(Set.empty[Long]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_(Seq.empty[Set[Long]]).query.get.map(_ ==> List())
        } yield ()
      }


      "not" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.longs.insert(List(
            (a, Set(long1, long2)),
            (b, Set(long2, long3, long4))
          )).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.longs_.not(long0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_.not(long1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.longs_.not(long2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_.not(long3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs_.not(long4).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs_.not(long5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.longs_.not(Seq(long0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_.not(Seq(long1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.longs_.not(Seq(long2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_.not(Seq(long3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs_.not(Seq(long4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs_.not(Seq(long5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.longs_.not(long1, long2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_.not(long1, long3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_.not(long1, long4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_.not(long1, long5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.longs_.not(Seq(long1, long2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_.not(Seq(long1, long3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_.not(Seq(long1, long4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_.not(Seq(long1, long5)).query.get.map(_ ==> List(b))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.longs_.not(Set(long1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.longs_.not(Set(long1, long2)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.longs_.not(Set(long1, long2, long3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_.not(Set(long2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_.not(Set(long2, long3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs_.not(Set(long2, long3, long4)).query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.longs_.not(Seq(Set(long1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.longs_.not(Seq(Set(long1, long2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.longs_.not(Seq(Set(long1, long2, long3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_.not(Seq(Set(long2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_.not(Seq(Set(long2, long3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs_.not(Seq(Set(long2, long3, long4))).query.get.map(_ ==> List(a))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.longs_.not(Set(long1, long2), Set(long0)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.longs_.not(Set(long1, long2), Set(long0, long3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.longs_.not(Set(long1, long2), Set(long2, long3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_.not(Set(long1, long2), Set(long2, long3, long4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.longs_.not(Seq(Set(long1, long2), Set(long0))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.longs_.not(Seq(Set(long1, long2), Set(long0, long3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.longs_.not(Seq(Set(long1, long2), Set(long2, long3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_.not(Seq(Set(long1, long2), Set(long2, long3, long4))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.longs_.not(Set(long1, long2), Set.empty[Long]).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.longs_.not(Seq.empty[Long]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_.not(Set.empty[Long]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_.not(Seq.empty[Set[Long]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_.not(Seq(Set.empty[Long])).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "==" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.longs.insert(List(
            (a, Set(long1, long2)),
            (b, Set(long2, long3, long4))
          )).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.longs_.==(Set(long1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_.==(Set(long1, long2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.longs_.==(Set(long1, long2, long3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.longs_.==(Seq(Set(long1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_.==(Seq(Set(long1, long2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs_.==(Seq(Set(long1, long2, long3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.longs_.==(Set(long1), Set(long2, long3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_.==(Set(long1, long2), Set(long2, long3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs_.==(Set(long1, long2), Set(long2, long3, long4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.longs_.==(Seq(Set(long1), Set(long2, long3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_.==(Seq(Set(long1, long2), Set(long2, long3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs_.==(Seq(Set(long1, long2), Set(long2, long3, long4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.longs_.==(Set(long1, long2), Set.empty[Long]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs_.==(Set.empty[Long]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_.==(Seq.empty[Set[Long]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_.==(Seq(Set.empty[Long])).query.get.map(_ ==> List())
        } yield ()
      }


      "!=" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.longs.insert(List(
            (a, Set(long1, long2)),
            (b, Set(long2, long3, long4))
          )).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.longs_.!=(Set(long1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_.!=(Set(long1, long2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.longs_.!=(Set(long1, long2, long3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.longs_.!=(Seq(Set(long1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_.!=(Seq(Set(long1, long2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.longs_.!=(Seq(Set(long1, long2, long3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "Not (exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.longs_.!=(Set(long1), Set(long2, long3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_.!=(Set(long1, long2), Set(long2, long3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.longs_.!=(Set(long1, long2), Set(long2, long3, long4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.longs_.!=(Seq(Set(long1), Set(long2, long3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_.!=(Seq(Set(long1, long2), Set(long2, long3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.longs_.!=(Seq(Set(long1, long2), Set(long2, long3, long4))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.longs_.!=(Seq(Set(long1, long2), Set.empty[Long])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.longs_.!=(Set.empty[Long]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_.!=(Seq.empty[Set[Long]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "compare" - types { implicit conn =>
        val (a, b) = (1, 2)
        for {
          _ <- Ns.i.longs.insert(List(
            (a, Set(long1, long2)),
            (b, Set(long2, long3, long4))
          )).transact

          _ <- Ns.i.a1.longs_.<(long0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_.<(long1).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_.<(long2).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs_.<(long3).query.get.map(_ ==> List(a, b))

          _ <- Ns.i.a1.longs_.<=(long0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_.<=(long1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs_.<=(long2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_.<=(long3).query.get.map(_ ==> List(a, b))

          _ <- Ns.i.a1.longs_.>(long0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_.>(long1).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_.>(long2).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.longs_.>(long3).query.get.map(_ ==> List(b))

          _ <- Ns.i.a1.longs_.>=(long0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_.>=(long1).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_.>=(long2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_.>=(long3).query.get.map(_ ==> List(b))
        } yield ()
      }
    }


    "Optional" - {

      "attr" - types { implicit conn =>
        val a = (1, Some(Set(long1, long2)))
        val b = (2, Some(Set(long2, long3, long4)))
        val c = (3, None)
        for {
          _ <- Ns.i.longs_?.insert(a, b, c).transact

          _ <- Ns.i.a1.longs_?.query.get.map(_ ==> List(a, b, c))

          // Distinct result even with redundant None's (two i = 3 with no long value)
          _ <- Ns.i.insert(3).transact
          _ <- Ns.i.>=(2).a1.longs_?.query.get.map(_ ==> List(
            (2, Some(Set(long2, long3, long4))),
            (3, None),
          ))
        } yield ()
      }


      "apply" - types { implicit conn =>
        val a = (1, Some(Set(long1, long2)))
        val b = (2, Some(Set(long2, long3, long4)))
        val c = (3, None)
        for {
          _ <- Ns.i.longs_?.insert(a, b, c).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.longs_?(Some(long0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_?(Some(long1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs_?(Some(long2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_?(Some(long3)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.longs_?(Some(Seq(long0))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_?(Some(Seq(long1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs_?(Some(Seq(long2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_?(Some(Seq(long3))).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.longs_?(Some(Seq(long1, long2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_?(Some(Seq(long1, long3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_?(Some(Seq(long2, long3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_?(Some(Seq(long1, long2, long3))).query.get.map(_ ==> List(a, b))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.longs_?(Some(Set(long1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs_?(Some(Set(long1, long2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs_?(Some(Set(long1, long2, long3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_?(Some(Set(long2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_?(Some(Set(long2, long3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.longs_?(Some(Set(long2, long3, long4))).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.longs_?(Some(Seq(Set(long1)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs_?(Some(Seq(Set(long1, long2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs_?(Some(Seq(Set(long1, long2, long3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_?(Some(Seq(Set(long2)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_?(Some(Seq(Set(long2, long3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.longs_?(Some(Seq(Set(long2, long3, long4)))).query.get.map(_ ==> List(b))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.longs_?(Some(Seq(Set(long1, long2), Set(long0)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs_?(Some(Seq(Set(long1, long2), Set(long0, long3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs_?(Some(Seq(Set(long1, long2), Set(long2, long3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_?(Some(Seq(Set(long1, long2), Set(long2, long3, long4)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.longs_?(Some(Seq.empty[Long])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_?(Some(Set.empty[Long])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_?(Some(Seq.empty[Set[Long]])).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.longs_?(Option.empty[Long]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.longs_?(Option.empty[Seq[Long]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.longs_?(Option.empty[Set[Long]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.longs_?(Option.empty[Seq[Set[Long]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "not" - types { implicit conn =>
        val a = (1, Some(Set(long1, long2)))
        val b = (2, Some(Set(long2, long3, long4)))
        val c = (3, None)
        for {
          _ <- Ns.i.longs_?.insert(a, b, c).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.longs_?.not(Some(long0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_?.not(Some(long1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.longs_?.not(Some(long2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_?.not(Some(long3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs_?.not(Some(long4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs_?.not(Some(long5)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.longs_?.not(Some(Seq(long0))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_?.not(Some(Seq(long1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.longs_?.not(Some(Seq(long2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_?.not(Some(Seq(long3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs_?.not(Some(Seq(long4))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs_?.not(Some(Seq(long5))).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.longs_?.not(Some(Seq(long1, long2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_?.not(Some(Seq(long1, long3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_?.not(Some(Seq(long1, long4))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_?.not(Some(Seq(long1, long5))).query.get.map(_ ==> List(b))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.longs_?.not(Some(Set(long1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.longs_?.not(Some(Set(long1, long2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.longs_?.not(Some(Set(long1, long2, long3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_?.not(Some(Set(long2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_?.not(Some(Set(long2, long3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs_?.not(Some(Set(long2, long3, long4))).query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.longs_?.not(Some(Seq(Set(long1)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.longs_?.not(Some(Seq(Set(long1, long2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.longs_?.not(Some(Seq(Set(long1, long2, long3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_?.not(Some(Seq(Set(long2)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_?.not(Some(Seq(Set(long2, long3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs_?.not(Some(Seq(Set(long2, long3, long4)))).query.get.map(_ ==> List(a))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.longs_?.not(Some(Seq(Set(long1, long2), Set(long0)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.longs_?.not(Some(Seq(Set(long1, long2), Set(long0, long3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.longs_?.not(Some(Seq(Set(long1, long2), Set(long2, long3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_?.not(Some(Seq(Set(long1, long2), Set(long2, long3, long4)))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.longs_?.not(Some(Seq.empty[Long])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_?.not(Some(Set.empty[Long])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_?.not(Some(Seq.empty[Set[Long]])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_?.not(Some(Seq(Set.empty[Long]))).query.get.map(_ ==> List(a, b))


          // Negating None returns all asserted
          _ <- Ns.i.a1.longs_?.not(Option.empty[Long]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_?.not(Option.empty[Seq[Long]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_?.not(Option.empty[Set[Long]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_?.not(Option.empty[Seq[Set[Long]]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "==" - types { implicit conn =>
        val a = (1, Some(Set(long1, long2)))
        val b = (2, Some(Set(long2, long3, long4)))
        val c = (3, None)
        for {
          _ <- Ns.i.longs_?.insert(a, b, c).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.longs_?.==(Some(Set(long1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_?.==(Some(Set(long1, long2))).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.longs_?.==(Some(Set(long1, long2, long3))).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.longs_?.==(Some(Seq(Set(long1)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_?.==(Some(Seq(Set(long1, long2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs_?.==(Some(Seq(Set(long1, long2, long3)))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.longs_?.==(Some(Seq(Set(long1), Set(long2, long3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_?.==(Some(Seq(Set(long1, long2), Set(long2, long3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs_?.==(Some(Seq(Set(long1, long2), Set(long2, long3, long4)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.longs_?.==(Some(Set.empty[Long])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_?.==(Some(Seq.empty[Set[Long]])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_?.==(Some(Seq(Set.empty[Long]))).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.longs_?.==(Option.empty[Set[Long]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.longs_?.==(Option.empty[Seq[Set[Long]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "!=" - types { implicit conn =>
        val a = (1, Some(Set(long1, long2)))
        val b = (2, Some(Set(long2, long3, long4)))
        val c = (3, None)
        for {
          _ <- Ns.i.longs_?.insert(a, b, c).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.longs_?.!=(Some(Set(long1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_?.!=(Some(Set(long1, long2))).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.longs_?.!=(Some(Set(long1, long2, long3))).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.longs_?.!=(Some(Seq(Set(long1)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_?.!=(Some(Seq(Set(long1, long2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.longs_?.!=(Some(Seq(Set(long1, long2, long3)))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "Not (exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.longs_?.!=(Some(Seq(Set(long1), Set(long2, long3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_?.!=(Some(Seq(Set(long1, long2), Set(long2, long3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.longs_?.!=(Some(Seq(Set(long1, long2), Set(long2, long3, long4)))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.longs_?.!=(Some(Seq(Set(long1, long2), Set.empty[Long]))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.longs_?.!=(Some(Set.empty[Long])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_?.!=(Some(Seq.empty[Set[Long]])).query.get.map(_ ==> List(a, b))


          // None matches non-asserted values
          _ <- Ns.i.a1.longs_?.==(Option.empty[Set[Long]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.longs_?.==(Option.empty[Seq[Set[Long]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "compare" - types { implicit conn =>
        val a = (1, Some(Set(long1, long2)))
        val b = (2, Some(Set(long2, long3, long4)))
        val c = (3, None)
        for {
          _ <- Ns.i.longs_?.insert(a, b, c).transact

          _ <- Ns.i.a1.longs_?.<(Some(long0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_?.<(Some(long1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_?.<(Some(long2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs_?.<(Some(long3)).query.get.map(_ ==> List(a, b))

          _ <- Ns.i.a1.longs_?.<=(Some(long0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_?.<=(Some(long1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs_?.<=(Some(long2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_?.<=(Some(long3)).query.get.map(_ ==> List(a, b))

          _ <- Ns.i.a1.longs_?.>(Some(long0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_?.>(Some(long1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_?.>(Some(long2)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.longs_?.>(Some(long3)).query.get.map(_ ==> List(b))

          _ <- Ns.i.a1.longs_?.>=(Some(long0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_?.>=(Some(long1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_?.>=(Some(long2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_?.>=(Some(long3)).query.get.map(_ ==> List(b))


          // None matches any asserted values
          _ <- Ns.i.a1.longs_?.<(None).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_?.>(None).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_?.<=(None).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_?.>=(None).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}