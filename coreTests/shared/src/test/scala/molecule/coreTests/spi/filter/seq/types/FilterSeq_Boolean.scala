// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.seq.types

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._


trait FilterSeq_Boolean extends CoreTestSuite with ApiAsync { spi: SpiAsync =>


  override lazy val tests = Tests {

    "Mandatory" - {

      "attr" - types { implicit conn =>
        val t  = (1, List(true))
        val f  = (2, List(false))
        val tf = (3, List(true, false, false))
        for {
          _ <- Ns.i.booleanSeq.insert(List(t, f, tf)).transact

          _ <- Ns.i.a1.booleanSeq.query.get.map(_ ==> List(t, f, tf))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val t  = (1, List(true))
        val f  = (2, List(false))
        val tf = (3, List(true, false, false))
        for {
          _ <- Ns.i.booleanSeq.insert(List(t, f, tf)).transact

          // Exact List matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.booleanSeq(List(true)).query.get.map(_ ==> List(t))
          _ <- Ns.i.a1.booleanSeq(List(false)).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleanSeq(List(true, false)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.booleanSeq(List(true, false, false)).query.get.map(_ ==> List(tf))
          // Same as
          _ <- Ns.i.a1.booleanSeq(List(List(true))).query.get.map(_ ==> List(t))
          _ <- Ns.i.a1.booleanSeq(List(List(false))).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleanSeq(List(List(true, false))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.booleanSeq(List(List(true, false, false))).query.get.map(_ ==> List(tf))


          // AND/OR semantics with multiple Lists

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.booleanSeq(List(true, false), List(true)).query.get.map(_ ==> List(t))
          _ <- Ns.i.a1.booleanSeq(List(true, false, false), List(false)).query.get.map(_ ==> List(f, tf))
          // Same as
          _ <- Ns.i.a1.booleanSeq(List(List(true, false), List(true))).query.get.map(_ ==> List(t))
          _ <- Ns.i.a1.booleanSeq(List(List(true, false), List(false))).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleanSeq(List(List(true, false, false), List(false))).query.get.map(_ ==> List(f, tf))


          // Empty Seq/Lists match nothing
          _ <- Ns.i.a1.booleanSeq(List(true), List.empty[Boolean]).query.get.map(_ ==> List(t))
          _ <- Ns.i.a1.booleanSeq(List(false), List.empty[Boolean]).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleanSeq(List(true, false), List.empty[Boolean]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.booleanSeq(List(true, false, false), List.empty[Boolean]).query.get.map(_ ==> List(tf))
          _ <- Ns.i.a1.booleanSeq(List.empty[Boolean]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.booleanSeq(List.empty[List[Boolean]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.booleanSeq(List(List.empty[Boolean])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val t  = (1, List(true))
        val f  = (2, List(false))
        val tf = (3, List(true, false, false))
        for {
          _ <- Ns.i.booleanSeq.insert(List(t, f, tf)).transact

          // Exact List non-matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.booleanSeq.not(List(true)).query.get.map(_ ==> List(f, tf))
          _ <- Ns.i.a1.booleanSeq.not(List(false)).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleanSeq.not(List(true, false)).query.get.map(_ ==> List(t, f, tf))
          _ <- Ns.i.a1.booleanSeq.not(List(true, false, false)).query.get.map(_ ==> List(t, f))
          // Same as
          _ <- Ns.i.a1.booleanSeq.not(List(List(true))).query.get.map(_ ==> List(f, tf))
          _ <- Ns.i.a1.booleanSeq.not(List(List(false))).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleanSeq.not(List(List(true, false))).query.get.map(_ ==> List(t, f, tf))
          _ <- Ns.i.a1.booleanSeq.not(List(List(true, false, false))).query.get.map(_ ==> List(t, f))


          // AND/OR semantics with multiple Lists

          // "Not (exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.booleanSeq.not(List(true, false), List(true)).query.get.map(_ ==> List(f, tf))
          _ <- Ns.i.a1.booleanSeq.not(List(true, false), List(false)).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleanSeq.not(List(true, false, false), List(false)).query.get.map(_ ==> List(t))
          // Same as
          _ <- Ns.i.a1.booleanSeq.not(List(List(true, false), List(true))).query.get.map(_ ==> List(f, tf))
          _ <- Ns.i.a1.booleanSeq.not(List(List(true, false), List(false))).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleanSeq.not(List(List(true, false, false), List(false))).query.get.map(_ ==> List(t))


          // Empty Seq/Lists
          _ <- Ns.i.a1.booleanSeq.not(List(List(true), List.empty[Boolean])).query.get.map(_ ==> List(f, tf))
          _ <- Ns.i.a1.booleanSeq.not(List(List(false), List.empty[Boolean])).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleanSeq.not(List(List(true, false), List.empty[Boolean])).query.get.map(_ ==> List(t, f, tf))
          _ <- Ns.i.a1.booleanSeq.not(List(List(true, false, false), List.empty[Boolean])).query.get.map(_ ==> List(t, f))
          _ <- Ns.i.a1.booleanSeq.not(List.empty[Boolean]).query.get.map(_ ==> List(t, f, tf))
          _ <- Ns.i.a1.booleanSeq.not(List.empty[List[Boolean]]).query.get.map(_ ==> List(t, f, tf))
        } yield ()
      }


      "has" - types { implicit conn =>
        val t  = (1, List(true))
        val f  = (2, List(false))
        val tf = (3, List(true, false, false))
        for {
          _ <- Ns.i.booleanSeq.insert(List(t, f, tf)).transact

          // Lists with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.booleanSeq.has(true).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleanSeq.has(false).query.get.map(_ ==> List(f, tf))
          // Same as
          _ <- Ns.i.a1.booleanSeq.has(List(true)).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleanSeq.has(List(false)).query.get.map(_ ==> List(f, tf))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.booleanSeq.has(true, false).query.get.map(_ ==> List(t, f, tf))
          // Same as
          _ <- Ns.i.a1.booleanSeq.has(List(true, false)).query.get.map(_ ==> List(t, f, tf))


          // Empty Seq/Lists match nothing
          _ <- Ns.i.a1.booleanSeq.has(List.empty[Boolean]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val t  = (1, List(true))
        val f  = (2, List(false))
        val tf = (3, List(true, false, false))
        for {
          _ <- Ns.i.booleanSeq.insert(List(t, f, tf)).transact

          // Lists without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.booleanSeq.hasNo(true).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleanSeq.hasNo(false).query.get.map(_ ==> List(t))
          // Same as
          _ <- Ns.i.a1.booleanSeq.hasNo(List(true)).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleanSeq.hasNo(List(false)).query.get.map(_ ==> List(t))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.booleanSeq.hasNo(true, false).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.booleanSeq.hasNo(List(true, false)).query.get.map(_ ==> List())

          // Negating empty Seqs/Lists has no effect
          _ <- Ns.i.a1.booleanSeq.hasNo(List.empty[Boolean]).query.get.map(_ ==> List(t, f, tf))
        } yield ()
      }
    }


    "Tacit" - {

      "attr" - types { implicit conn =>
        val (t, f, tf) = (1, 2, 3)
        for {
          _ <- Ns.i.booleanSeq.insert(List(
            (t, List(true)),
            (f, List(false)),
            (tf, List(true, false, false))
          )).transact

          _ <- Ns.i.a1.booleanSeq_.query.get.map(_ ==> List(t, f, tf))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val (t, f, tf) = (1, 2, 3)
        for {
          _ <- Ns.i.booleanSeq.insert(List(
            (t, List(true)),
            (f, List(false)),
            (tf, List(true, false, false))
          )).transact

          // Exact List matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.booleanSeq_(List(true)).query.get.map(_ ==> List(t))
          _ <- Ns.i.a1.booleanSeq_(List(false)).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleanSeq_(List(true, false)).query.i.get.map(_ ==> List())
          _ <- Ns.i.a1.booleanSeq_(List(true, false, false)).query.i.get.map(_ ==> List(tf))
          // Same as
          _ <- Ns.i.a1.booleanSeq_(List(List(true))).query.get.map(_ ==> List(t))
          _ <- Ns.i.a1.booleanSeq_(List(List(false))).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleanSeq_(List(List(true, false))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.booleanSeq_(List(List(true, false, false))).query.get.map(_ ==> List(tf))


          // AND/OR semantics with multiple Lists

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.booleanSeq_(List(true, false, false), List(true)).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleanSeq_(List(true, false, false), List(false)).query.get.map(_ ==> List(f, tf))
          // Same as
          _ <- Ns.i.a1.booleanSeq_(List(List(true, false, false), List(true))).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleanSeq_(List(List(true, false, false), List(false))).query.get.map(_ ==> List(f, tf))


          // Empty Seq/Lists match nothing
          _ <- Ns.i.a1.booleanSeq_(List(true), List.empty[Boolean]).query.get.map(_ ==> List(t))
          _ <- Ns.i.a1.booleanSeq_(List(false), List.empty[Boolean]).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleanSeq_(List(true, false), List.empty[Boolean]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.booleanSeq_(List(true, false, false), List.empty[Boolean]).query.get.map(_ ==> List(tf))
          _ <- Ns.i.a1.booleanSeq_(List.empty[Boolean]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.booleanSeq_(List.empty[List[Boolean]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.booleanSeq_(List(List.empty[Boolean])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val (t, f, tf) = (1, 2, 3)
        for {
          _ <- Ns.i.booleanSeq.insert(List(
            (t, List(true)),
            (f, List(false)),
            (tf, List(true, false, false))
          )).transact

          // Non-exact List matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.booleanSeq_.not(List(true)).query.get.map(_ ==> List(f, tf))
          _ <- Ns.i.a1.booleanSeq_.not(List(false)).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleanSeq_.not(List(true, false)).query.get.map(_ ==> List(t, f, tf))
          _ <- Ns.i.a1.booleanSeq_.not(List(true, false, false)).query.get.map(_ ==> List(t, f))
          // Same as
          _ <- Ns.i.a1.booleanSeq_.not(List(List(true))).query.get.map(_ ==> List(f, tf))
          _ <- Ns.i.a1.booleanSeq_.not(List(List(false))).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleanSeq_.not(List(List(true, false))).query.get.map(_ ==> List(t, f, tf))
          _ <- Ns.i.a1.booleanSeq_.not(List(List(true, false, false))).query.get.map(_ ==> List(t, f))


          // AND/OR semantics with multiple Lists

          // "Not (exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.booleanSeq_.not(List(true, false, false), List(true)).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleanSeq_.not(List(true, false, false), List(false)).query.get.map(_ ==> List(t))
          // Same as
          _ <- Ns.i.a1.booleanSeq_.not(List(List(true, false, false), List(true))).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleanSeq_.not(List(List(true, false, false), List(false))).query.get.map(_ ==> List(t))


          // Empty Seq/Lists
          _ <- Ns.i.a1.booleanSeq_.not(List(List(true), List.empty[Boolean])).query.get.map(_ ==> List(f, tf))
          _ <- Ns.i.a1.booleanSeq_.not(List(List(false), List.empty[Boolean])).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleanSeq_.not(List(List(true, false), List.empty[Boolean])).query.get.map(_ ==> List(t, f, tf))
          _ <- Ns.i.a1.booleanSeq_.not(List(List(true, false, false), List.empty[Boolean])).query.get.map(_ ==> List(t, f))
          _ <- Ns.i.a1.booleanSeq_.not(List.empty[Boolean]).query.get.map(_ ==> List(t, f, tf))
          _ <- Ns.i.a1.booleanSeq_.not(List.empty[List[Boolean]]).query.get.map(_ ==> List(t, f, tf))
        } yield ()
      }


      "has" - types { implicit conn =>
        val (t, f, tf) = (1, 2, 3)
        for {
          _ <- Ns.i.booleanSeq.insert(List(
            (t, List(true)),
            (f, List(false)),
            (tf, List(true, false, false))
          )).transact

          // Lists with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.booleanSeq_.has(true).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleanSeq_.has(false).query.get.map(_ ==> List(f, tf))
          // Same as
          _ <- Ns.i.a1.booleanSeq_.has(List(true)).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleanSeq_.has(List(false)).query.get.map(_ ==> List(f, tf))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.booleanSeq_.has(true, false).query.get.map(_ ==> List(t, f, tf))
          // Same as
          _ <- Ns.i.a1.booleanSeq_.has(List(true, false)).query.get.map(_ ==> List(t, f, tf))


          // Empty Seq/Lists match nothing
          _ <- Ns.i.a1.booleanSeq_.has(List.empty[Boolean]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val (t, f, tf) = (1, 2, 3)
        for {
          _ <- Ns.i.booleanSeq.insert(List(
            (t, List(true)),
            (f, List(false)),
            (tf, List(true, false, false))
          )).transact

          // Lists without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.booleanSeq_.hasNo(true).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleanSeq_.hasNo(false).query.get.map(_ ==> List(t))
          // Same as
          _ <- Ns.i.a1.booleanSeq_.hasNo(List(true)).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleanSeq_.hasNo(List(false)).query.get.map(_ ==> List(t))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.booleanSeq_.hasNo(true, false).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.booleanSeq_.hasNo(List(true, false)).query.get.map(_ ==> List())


          // Negating empty Seqs/Lists has no effect
          _ <- Ns.i.a1.booleanSeq_.hasNo(List.empty[Boolean]).query.get.map(_ ==> List(t, f, tf))
        } yield ()
      }
    }


    "Optional" - {

      "attr" - types { implicit conn =>
        val t  = (1, Some(List(true)))
        val f  = (2, Some(List(false)))
        val tf = (3, Some(List(true, false, false)))
        val x  = (4, None)
        for {
          _ <- Ns.i.booleanSeq_?.insert(t, f, tf, x).transact
          _ <- Ns.i.a1.booleanSeq_?.query.get.map(_ ==> List(t, f, tf, x))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val t  = (1, Some(List(true)))
        val f  = (2, Some(List(false)))
        val tf = (3, Some(List(true, false, false)))
        val x  = (4, None)
        for {
          _ <- Ns.i.booleanSeq_?.insert(t, f, tf, x).transact

          // Exact List matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.booleanSeq_?(Some(List(true))).query.get.map(_ ==> List(t))
          _ <- Ns.i.a1.booleanSeq_?(Some(List(false))).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleanSeq_?(Some(List(true, false))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.booleanSeq_?(Some(List(true, false, false))).query.get.map(_ ==> List(tf))
          // Same as
          _ <- Ns.i.a1.booleanSeq_?(Some(List(List(true)))).query.get.map(_ ==> List(t))
          _ <- Ns.i.a1.booleanSeq_?(Some(List(List(false)))).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleanSeq_?(Some(List(List(true, false)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.booleanSeq_?(Some(List(List(true, false, false)))).query.get.map(_ ==> List(tf))


          // AND/OR semantics with multiple Lists

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.booleanSeq_?(Some(List(List(true, false, false), List(true)))).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleanSeq_?(Some(List(List(true, false, false), List(false)))).query.get.map(_ ==> List(f, tf))


          // Empty Seq/Lists match nothing
          _ <- Ns.i.a1.booleanSeq_?(Some(List.empty[Boolean])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.booleanSeq_?(Some(List.empty[List[Boolean]])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.booleanSeq_?(Some(List(List.empty[Boolean]))).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.booleanSeq_?(Option.empty[List[Boolean]]).query.get.map(_ ==> List(x))
          _ <- Ns.i.a1.booleanSeq_?(Option.empty[Seq[List[Boolean]]]).query.get.map(_ ==> List(x))
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val t  = (1, Some(List(true)))
        val f  = (2, Some(List(false)))
        val tf = (3, Some(List(true, false, false)))
        val x  = (4, None)
        for {
          _ <- Ns.i.booleanSeq_?.insert(t, f, tf, x).transact

          // Non-exact List matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.booleanSeq_?.not(Some(List(true))).query.get.map(_ ==> List(f, tf))
          _ <- Ns.i.a1.booleanSeq_?.not(Some(List(false))).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleanSeq_?.not(Some(List(true, false))).query.get.map(_ ==> List(t, f, tf))
          _ <- Ns.i.a1.booleanSeq_?.not(Some(List(true, false, false))).query.get.map(_ ==> List(t, f))
          // Same as
          _ <- Ns.i.a1.booleanSeq_?.not(Some(List(List(true)))).query.get.map(_ ==> List(f, tf))
          _ <- Ns.i.a1.booleanSeq_?.not(Some(List(List(false)))).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleanSeq_?.not(Some(List(List(true, false)))).query.get.map(_ ==> List(t, f, tf))
          _ <- Ns.i.a1.booleanSeq_?.not(Some(List(List(true, false, false)))).query.get.map(_ ==> List(t, f))


          // AND/OR semantics with multiple Lists

          // "Not (exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.booleanSeq_?.not(Some(List(List(true, false, false), List(true)))).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleanSeq_?.not(Some(List(List(true, false, false), List(false)))).query.get.map(_ ==> List(t))


          // Empty Seq/Lists
          _ <- Ns.i.a1.booleanSeq_?.not(Some(List(List(true), List.empty[Boolean]))).query.get.map(_ ==> List(f, tf))
          _ <- Ns.i.a1.booleanSeq_?.not(Some(List(List(false), List.empty[Boolean]))).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleanSeq_?.not(Some(List(List(true, false), List.empty[Boolean]))).query.get.map(_ ==> List(t, f, tf))
          _ <- Ns.i.a1.booleanSeq_?.not(Some(List(List(true, false, false), List.empty[Boolean]))).query.get.map(_ ==> List(t, f))
          _ <- Ns.i.a1.booleanSeq_?.not(Some(List.empty[Boolean])).query.get.map(_ ==> List(t, f, tf))
          _ <- Ns.i.a1.booleanSeq_?.not(Some(List.empty[List[Boolean]])).query.get.map(_ ==> List(t, f, tf))


          // None matches non-asserted values
          _ <- Ns.i.a1.booleanSeq_?(Option.empty[List[Boolean]]).query.get.map(_ ==> List(x))
          _ <- Ns.i.a1.booleanSeq_?(Option.empty[Seq[List[Boolean]]]).query.get.map(_ ==> List(x))
        } yield ()
      }


      "has" - types { implicit conn =>
        val t  = (1, Some(List(true)))
        val f  = (2, Some(List(false)))
        val tf = (3, Some(List(true, false, false)))
        val x  = (4, None)
        for {
          _ <- Ns.i.booleanSeq_?.insert(t, f, tf, x).transact

          // Lists with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.booleanSeq_?.has(Some(true)).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleanSeq_?.has(Some(false)).query.get.map(_ ==> List(f, tf))
          // Same as
          _ <- Ns.i.a1.booleanSeq_?.has(Some(List(true))).query.get.map(_ ==> List(t, tf))
          _ <- Ns.i.a1.booleanSeq_?.has(Some(List(false))).query.get.map(_ ==> List(f, tf))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.booleanSeq_?.has(Some(List(true, false))).query.get.map(_ ==> List(t, f, tf))


          // Empty Seq/Lists match nothing
          _ <- Ns.i.a1.booleanSeq_?.has(Some(List.empty[Boolean])).query.get.map(_ ==> List())

          // None matches non-asserted values
          _ <- Ns.i.a1.booleanSeq_?.has(Option.empty[Boolean]).query.get.map(_ ==> List(x))
          _ <- Ns.i.a1.booleanSeq_?.has(Option.empty[Seq[Boolean]]).query.get.map(_ ==> List(x))
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val t  = (1, Some(List(true)))
        val f  = (2, Some(List(false)))
        val tf = (3, Some(List(true, false, false)))
        val x  = (4, None)
        for {
          _ <- Ns.i.booleanSeq_?.insert(t, f, tf, x).transact

          // Lists without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.booleanSeq_?.hasNo(Some(true)).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleanSeq_?.hasNo(Some(false)).query.get.map(_ ==> List(t))
          // Same as
          _ <- Ns.i.a1.booleanSeq_?.hasNo(Some(List(true))).query.get.map(_ ==> List(f))
          _ <- Ns.i.a1.booleanSeq_?.hasNo(Some(List(false))).query.get.map(_ ==> List(t))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.booleanSeq_?.hasNo(Some(List(true, false))).query.get.map(_ ==> List())


          // Negating empty Seqs/Lists has no effect
          _ <- Ns.i.a1.booleanSeq_?.hasNo(Some(List.empty[Boolean])).query.get.map(_ ==> List(t, f, tf))

          // Negating None returns all asserted
          _ <- Ns.i.a1.booleanSeq_?.hasNo(Option.empty[Boolean]).query.get.map(_ ==> List(t, f, tf))
          _ <- Ns.i.a1.booleanSeq_?.hasNo(Option.empty[Seq[Boolean]]).query.get.map(_ ==> List(t, f, tf))
        } yield ()
      }
    }
  }
}