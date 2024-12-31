package molecule.coreTests.spi.filter.seq.types

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.domains.dsl.Types._
import molecule.coreTests.setup._


case class FilterSeq_Boolean(
  suite: MUnitSuite,
  api: Api_async with Spi_async with DbProviders
) extends TestUtils {

  import api._
  import suite._

  "Mandatory: has" - types { implicit conn =>
    val t  = (1, List(true))
    val f  = (2, List(false))
    val tf = (3, List(true, false, false))
    for {
      _ <- Entity.i.booleanSeq.insert(List(t, f, tf)).transact

      // Lists with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.booleanSeq.has(true).query.get.map(_ ==> List(t, tf))
      _ <- Entity.i.a1.booleanSeq.has(false).query.get.map(_ ==> List(f, tf))
      // Same as
      _ <- Entity.i.a1.booleanSeq.has(List(true)).query.get.map(_ ==> List(t, tf))
      _ <- Entity.i.a1.booleanSeq.has(List(false)).query.get.map(_ ==> List(f, tf))


      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.booleanSeq.has(true, false).query.get.map(_ ==> List(t, f, tf))
      // Same as
      _ <- Entity.i.a1.booleanSeq.has(List(true, false)).query.get.map(_ ==> List(t, f, tf))


      // Empty Seq/Lists match nothing
      _ <- Entity.i.a1.booleanSeq.has(List.empty[Boolean]).query.get.map(_ ==> List())
    } yield ()
  }


  "Mandatory: hasNo" - types { implicit conn =>
    val t  = (1, List(true))
    val f  = (2, List(false))
    val tf = (3, List(true, false, false))
    for {
      _ <- Entity.i.booleanSeq.insert(List(t, f, tf)).transact

      // Lists without one or more values matching

      // "Doesn't have this value"
      _ <- Entity.i.a1.booleanSeq.hasNo(true).query.get.map(_ ==> List(f))
      _ <- Entity.i.a1.booleanSeq.hasNo(false).query.get.map(_ ==> List(t))
      // Same as
      _ <- Entity.i.a1.booleanSeq.hasNo(List(true)).query.get.map(_ ==> List(f))
      _ <- Entity.i.a1.booleanSeq.hasNo(List(false)).query.get.map(_ ==> List(t))


      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.booleanSeq.hasNo(true, false).query.get.map(_ ==> List())
      // Same as
      _ <- Entity.i.a1.booleanSeq.hasNo(List(true, false)).query.get.map(_ ==> List())

      // Negating empty Seqs/Lists has no effect
      _ <- Entity.i.a1.booleanSeq.hasNo(List.empty[Boolean]).query.get.map(_ ==> List(t, f, tf))
    } yield ()
  }


  "Tacit: has" - types { implicit conn =>
    val (t, f, tf) = (1, 2, 3)
    for {
      _ <- Entity.i.booleanSeq.insert(List(
        (t, List(true)),
        (f, List(false)),
        (tf, List(true, false, false))
      )).transact

      // Lists with one or more values matching

      // "Has this value"
      _ <- Entity.i.a1.booleanSeq_.has(true).query.get.map(_ ==> List(t, tf))
      _ <- Entity.i.a1.booleanSeq_.has(false).query.get.map(_ ==> List(f, tf))
      // Same as
      _ <- Entity.i.a1.booleanSeq_.has(List(true)).query.get.map(_ ==> List(t, tf))
      _ <- Entity.i.a1.booleanSeq_.has(List(false)).query.get.map(_ ==> List(f, tf))


      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.booleanSeq_.has(true, false).query.get.map(_ ==> List(t, f, tf))
      // Same as
      _ <- Entity.i.a1.booleanSeq_.has(List(true, false)).query.get.map(_ ==> List(t, f, tf))


      // Empty Seq/Lists match nothing
      _ <- Entity.i.a1.booleanSeq_.has(List.empty[Boolean]).query.get.map(_ ==> List())
    } yield ()
  }


  "Tacit: hasNo" - types { implicit conn =>
    val (t, f, tf) = (1, 2, 3)
    for {
      _ <- Entity.i.booleanSeq.insert(List(
        (t, List(true)),
        (f, List(false)),
        (tf, List(true, false, false))
      )).transact

      // Lists without one or more values matching

      // "Doesn't have this value"
      _ <- Entity.i.a1.booleanSeq_.hasNo(true).query.get.map(_ ==> List(f))
      _ <- Entity.i.a1.booleanSeq_.hasNo(false).query.get.map(_ ==> List(t))
      // Same as
      _ <- Entity.i.a1.booleanSeq_.hasNo(List(true)).query.get.map(_ ==> List(f))
      _ <- Entity.i.a1.booleanSeq_.hasNo(List(false)).query.get.map(_ ==> List(t))


      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.booleanSeq_.hasNo(true, false).query.get.map(_ ==> List())
      // Same as
      _ <- Entity.i.a1.booleanSeq_.hasNo(List(true, false)).query.get.map(_ ==> List())


      // Negating empty Seqs/Lists has no effect
      _ <- Entity.i.a1.booleanSeq_.hasNo(List.empty[Boolean]).query.get.map(_ ==> List(t, f, tf))
    } yield ()
  }

  // No filtering on optional Seq attributes
}