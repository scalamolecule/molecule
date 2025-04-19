package molecule.coreTests.spi.filter.one.types

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor.*
import molecule.coreTests.domains.dsl.Types.*
import molecule.coreTests.setup.*

case class FilterOne_Boolean(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "Mandatory" - types { implicit conn =>
    val a = (1, true)
    val b = (2, false)
    for {
      _ <- Entity.i.boolean.insert(List(a, b)).transact

      // Find all attribute values
      _ <- Entity.i.a1.boolean.query.get.map(_ ==> List(a, b))

      // Find value(s) matching
      _ <- Entity.i.a1.boolean(true).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.boolean(false).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.boolean(Seq(true)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.boolean(Seq(false)).query.get.map(_ ==> List(b))
      // OR semantics for multiple args (for Boolean meaning all)
      _ <- Entity.i.a1.boolean(true, false).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.boolean(Seq(true, false)).query.get.map(_ ==> List(a, b))
      // Empty Seq of args matches no values
      _ <- Entity.i.a1.boolean(Seq.empty[Boolean]).query.get.map(_ ==> List())

      // Find values not matching
      _ <- Entity.i.a1.boolean.not(true).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.boolean.not(false).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.boolean.not(Seq(true)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.boolean.not(Seq(false)).query.get.map(_ ==> List(a))
      // OR semantics for multiple args (for Boolean meaning none)
      _ <- Entity.i.a1.boolean.not(true, false).query.get.map(_ ==> List())
      _ <- Entity.i.a1.boolean.not(Seq(true, false)).query.get.map(_ ==> List())
      // Empty Seq of negation args matches all values
      _ <- Entity.i.a1.boolean.not(Seq.empty[Boolean]).query.get.map(_ ==> List(a, b))

      // Find values in range
      _ <- Entity.i.a1.boolean.<(true).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.boolean.>(true).query.get.map(_ ==> List())
      _ <- Entity.i.a1.boolean.<=(true).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.boolean.>=(true).query.get.map(_ ==> List(a))
    } yield ()
  }


  "Tacit" - types { implicit conn =>
    val (a, b, x) = (1, 2, 3)
    for {
      _ <- Entity.i.boolean_?.insert(List(
        (1, Some(true)),
        (2, Some(false)),
        (3, None)
      )).transact

      // Match asserted attribute without returning its value
      _ <- Entity.i.a1.boolean_.query.get.map(_ ==> List(a, b))

      // Match non-asserted attribute (null)
      _ <- Entity.i.a1.boolean_().query.get.map(_ ==> List(x))

      // Match attribute values without returning them
      _ <- Entity.i.a1.boolean_(true).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.boolean_(false).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.boolean_(Seq(true)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.boolean_(Seq(false)).query.get.map(_ ==> List(b))
      // OR semantics for multiple args (for Boolean meaning all)
      _ <- Entity.i.a1.boolean_(true, false).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.boolean_(Seq(true, false)).query.get.map(_ ==> List(a, b))
      // Empty Seq of args matches no values
      _ <- Entity.i.a1.boolean_(Seq.empty[Boolean]).query.get.map(_ ==> List())

      // Match non-matching values without returning them
      _ <- Entity.i.a1.boolean_.not(true).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.boolean_.not(false).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.boolean_.not(Seq(true)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.boolean_.not(Seq(false)).query.get.map(_ ==> List(a))
      // OR semantics for multiple args (for Boolean meaning none)
      _ <- Entity.i.a1.boolean_.not(true, false).query.get.map(_ ==> List())
      _ <- Entity.i.a1.boolean_.not(Seq(true, false)).query.get.map(_ ==> List())
      // Empty Seq of negation args matches all asserted values (non-null)
      _ <- Entity.i.a1.boolean_.not(Seq.empty[Boolean]).query.get.map(_ ==> List(a, b))

      // Match value ranges without returning them
      _ <- Entity.i.a1.boolean_.<(true).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.boolean_.>(true).query.get.map(_ ==> List())
      _ <- Entity.i.a1.boolean_.<=(true).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.boolean_.>=(true).query.get.map(_ ==> List(a))
    } yield ()
  }


  "Optional" - types { implicit conn =>
    val a = (1, Some(true))
    val b = (2, Some(false))
    val x = (3, Option.empty[Boolean])
    for {
      _ <- Entity.i.boolean_?.insert(List(a, b, x)).transact

      // Find all optional attribute values
      _ <- Entity.i.a1.boolean_?.query.get.map(_ ==> List(a, b, x))

      // Find optional values matching
      _ <- Entity.i.a1.boolean_?(Some(true)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.boolean_?(Some(false)).query.get.map(_ ==> List(b))

      // None matches non-asserted/null values
      _ <- Entity.i.a1.boolean_?(Option.empty[Boolean]).query.get.map(_ ==> List(x))
      // Easier to apply nothing to tacit attribute
      _ <- Entity.i.a1.boolean_().query.get.map(_ ==> List(3))
    } yield ()
  }
}