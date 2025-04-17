package molecule.coreTests.spi.filter.set.types

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.domains.dsl.Types._
import molecule.coreTests.setup._


case class FilterSet_Boolean(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api._
  import suite._

  "Mandatory: has" - types { implicit conn =>
    val t  = (1, Set(true))
    val f  = (2, Set(false))
    val tf = (3, Set(true, false))
    for {
      _ <- Entity.i.booleanSet.insert(List(t, f, tf)).transact

      // Sets with one or more values matching

      // "Has this value"
      _ <- Entity.i.a1.booleanSet.has(true).query.get.map(_ ==> List(t, tf))
      _ <- Entity.i.a1.booleanSet.has(false).query.get.map(_ ==> List(f, tf))
      // Same as
      _ <- Entity.i.a1.booleanSet.has(Seq(true)).query.get.map(_ ==> List(t, tf))
      _ <- Entity.i.a1.booleanSet.has(Seq(false)).query.get.map(_ ==> List(f, tf))


      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.booleanSet.has(true, false).query.get.map(_ ==> List(t, f, tf))
      // Same as
      _ <- Entity.i.a1.booleanSet.has(Seq(true, false)).query.get.map(_ ==> List(t, f, tf))


      // Empty Seq/Sets match nothing
      _ <- Entity.i.a1.booleanSet.has(Seq.empty[Boolean]).query.get.map(_ ==> List())
    } yield ()
  }


  "Mandatory: hasNo" - types { implicit conn =>
    val t  = (1, Set(true))
    val f  = (2, Set(false))
    val tf = (3, Set(true, false))
    for {
      _ <- Entity.i.booleanSet.insert(List(t, f, tf)).transact

      // Sets without one or more values matching

      // "Doesn't have this value"
      _ <- Entity.i.a1.booleanSet.hasNo(true).query.get.map(_ ==> List(f))
      _ <- Entity.i.a1.booleanSet.hasNo(false).query.get.map(_ ==> List(t))
      // Same as
      _ <- Entity.i.a1.booleanSet.hasNo(Seq(true)).query.get.map(_ ==> List(f))
      _ <- Entity.i.a1.booleanSet.hasNo(Seq(false)).query.get.map(_ ==> List(t))


      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.booleanSet.hasNo(true, false).query.get.map(_ ==> List())
      // Same as
      _ <- Entity.i.a1.booleanSet.hasNo(Seq(true, false)).query.get.map(_ ==> List())

      // Negating empty Seqs/Sets has no effect
      _ <- Entity.i.a1.booleanSet.hasNo(Seq.empty[Boolean]).query.get.map(_ ==> List(t, f, tf))
    } yield ()
  }


  "Tacit: has" - types { implicit conn =>
    val (t, f, tf) = (1, 2, 3)
    for {
      _ <- Entity.i.booleanSet.insert(List(
        (t, Set(true)),
        (f, Set(false)),
        (tf, Set(true, false))
      )).transact

      // Sets with one or more values matching

      // "Has this value"
      _ <- Entity.i.a1.booleanSet_.has(true).query.get.map(_ ==> List(t, tf))
      _ <- Entity.i.a1.booleanSet_.has(false).query.get.map(_ ==> List(f, tf))
      // Same as
      _ <- Entity.i.a1.booleanSet_.has(Seq(true)).query.get.map(_ ==> List(t, tf))
      _ <- Entity.i.a1.booleanSet_.has(Seq(false)).query.get.map(_ ==> List(f, tf))


      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.booleanSet_.has(true, false).query.get.map(_ ==> List(t, f, tf))
      // Same as
      _ <- Entity.i.a1.booleanSet_.has(Seq(true, false)).query.get.map(_ ==> List(t, f, tf))


      // Empty Seq/Sets match nothing
      _ <- Entity.i.a1.booleanSet_.has(Seq.empty[Boolean]).query.get.map(_ ==> List())
    } yield ()
  }


  "Tacit: hasNo" - types { implicit conn =>
    val (t, f, tf) = (1, 2, 3)
    for {
      _ <- Entity.i.booleanSet.insert(List(
        (t, Set(true)),
        (f, Set(false)),
        (tf, Set(true, false))
      )).transact

      // Sets without one or more values matching

      // "Doesn't have this value"
      _ <- Entity.i.a1.booleanSet_.hasNo(true).query.get.map(_ ==> List(f))
      _ <- Entity.i.a1.booleanSet_.hasNo(false).query.get.map(_ ==> List(t))
      // Same as
      _ <- Entity.i.a1.booleanSet_.hasNo(Seq(true)).query.get.map(_ ==> List(f))
      _ <- Entity.i.a1.booleanSet_.hasNo(Seq(false)).query.get.map(_ ==> List(t))


      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.booleanSet_.hasNo(true, false).query.get.map(_ ==> List())
      // Same as
      _ <- Entity.i.a1.booleanSet_.hasNo(Seq(true, false)).query.get.map(_ ==> List())


      // Negating empty Seqs/Sets has no effect
      _ <- Entity.i.a1.booleanSet_.hasNo(Seq.empty[Boolean]).query.get.map(_ ==> List(t, f, tf))
    } yield ()
  }

  // No filtering on optional Set attributes
}