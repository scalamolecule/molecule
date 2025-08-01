// GENERATED CODE ********************************
package molecule.db.compliance.test.filter.one.types

import java.time.Duration
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*

case class FilterOne_Duration_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "Mandatory" - types {
    val a = (1, duration1)
    val b = (2, duration2)
    val c = (3, duration3)
    for {
      _ <- Entity.i.duration.insert(List(a, b, c)).transact

      // Find all attribute values
      _ <- Entity.i.a1.duration.query.get.map(_ ==> List(a, b, c))

      // Find value(s) matching
      _ <- Entity.i.a1.duration(duration0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.duration(duration1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.duration(Seq(duration0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.duration(Seq(duration1)).query.get.map(_ ==> List(a))
      // OR semantics for multiple args ("is this or that")
      _ <- Entity.i.a1.duration(duration1, duration2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.duration(duration1, duration0).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.duration(Seq(duration1, duration2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.duration(Seq(duration1, duration0)).query.get.map(_ ==> List(a))
      // Empty Seq of args matches no values
      _ <- Entity.i.a1.duration(Seq.empty[Duration]).query.get.map(_ ==> List())

      // Find values not matching
      _ <- Entity.i.a1.duration.not(duration0).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.duration.not(duration1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.duration.not(duration2).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.duration.not(duration3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.duration.not(Seq(duration0)).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.duration.not(Seq(duration1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.duration.not(Seq(duration2)).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.duration.not(Seq(duration3)).query.get.map(_ ==> List(a, b))
      // OR semantics for multiple args
      _ <- Entity.i.a1.duration.not(duration0, duration1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.duration.not(duration1, duration2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.duration.not(duration2, duration3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.duration.not(Seq(duration0, duration1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.duration.not(Seq(duration1, duration2)).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.duration.not(Seq(duration2, duration3)).query.get.map(_ ==> List(a))
      // Empty Seq of negation args matches all values
      _ <- Entity.i.a1.duration.not(Seq.empty[Duration]).query.get.map(_ ==> List(a, b, c))

      // Find values in range
      _ <- Entity.i.a1.duration.<(duration2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.duration.>(duration2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.duration.<=(duration2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.duration.>=(duration2).query.get.map(_ ==> List(b, c))
    } yield ()
  }


  "Tacit" - types {
    val (a, b, c, x) = (1, 2, 3, 4)
    for {
      _ <- Entity.i.duration_?.insert(List(
        (a, Some(duration1)),
        (b, Some(duration2)),
        (c, Some(duration3)),
        (x, None)
      )).transact

      // Match asserted attribute without returning its value
      _ <- Entity.i.a1.duration_.query.get.map(_ ==> List(a, b, c))

      // Match non-asserted attribute (null)
      _ <- Entity.i.a1.duration_().query.get.map(_ ==> List(x))

      // Match attribute values without returning them
      _ <- Entity.i.a1.duration_(duration0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.duration_(duration1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.duration_(Seq(duration0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.duration_(Seq(duration1)).query.get.map(_ ==> List(a))
      // OR semantics for multiple args ("is this or that")
      _ <- Entity.i.a1.duration_(duration1, duration2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.duration_(duration1, duration0).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.duration_(Seq(duration1, duration2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.duration_(Seq(duration1, duration0)).query.get.map(_ ==> List(a))
      // Empty Seq of args matches no values
      _ <- Entity.i.a1.duration_(Seq.empty[Duration]).query.get.map(_ ==> List())

      // Match non-matching values without returning them
      _ <- Entity.i.a1.duration_.not(duration0).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.duration_.not(duration1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.duration_.not(duration2).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.duration_.not(duration3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.duration_.not(Seq(duration0)).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.duration_.not(Seq(duration1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.duration_.not(Seq(duration2)).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.duration_.not(Seq(duration3)).query.get.map(_ ==> List(a, b))
      // OR semantics for multiple args
      _ <- Entity.i.a1.duration_.not(duration0, duration1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.duration_.not(duration1, duration2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.duration_.not(duration2, duration3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.duration_.not(Seq(duration0, duration1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.duration_.not(Seq(duration1, duration2)).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.duration_.not(Seq(duration2, duration3)).query.get.map(_ ==> List(a))
      // Empty Seq of negation args matches all asserted values (non-null)
      _ <- Entity.i.a1.duration_.not(Seq.empty[Duration]).query.get.map(_ ==> List(a, b, c))

      // Match value ranges without returning them
      _ <- Entity.i.a1.duration_.<(duration2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.duration_.>(duration2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.duration_.<=(duration2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.duration_.>=(duration2).query.get.map(_ ==> List(b, c))
    } yield ()
  }


  "Optional" - types {
    val a = (1, Some(duration1))
    val b = (2, Some(duration2))
    val c = (3, Some(duration3))
    val x = (4, Option.empty[Duration])
    for {
      _ <- Entity.i.duration_?.insert(List(a, b, c, x)).transact

      // Find all optional attribute values
      _ <- Entity.i.a1.duration_?.query.get.map(_ ==> List(a, b, c, x))

      // Find optional values matching
      _ <- Entity.i.a1.duration_?(Some(duration0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.duration_?(Some(duration1)).query.get.map(_ ==> List(a))

      // None matches non-asserted/null values
      _ <- Entity.i.a1.duration_?(Option.empty[Duration]).query.get.map(_ ==> List(x))
      // Easier to apply nothing to tacit attribute
      _ <- Entity.i.a1.duration_().query.get.map(_ ==> List(4))
    } yield ()
  }


  "Combinations" - types {
    for {
      _ <- Entity.i.duration.insert(
        (1, duration1),
        (2, duration2),
        (3, duration3),
        (4, duration4),
        (5, duration5),
        (6, duration6),
        (7, duration7),
        (8, duration8),
        (9, duration9),
      ).transact

      _ <- Entity.i.a1.duration_.>(duration2).query.get.map(_ ==> List(3, 4, 5, 6, 7, 8, 9))
      _ <- Entity.i.a1.duration_.>(duration2).duration_.<=(duration8).query.get.map(_ ==> List(3, 4, 5, 6, 7, 8))
      _ <- Entity.i.a1.duration_.>(duration2).duration_.<=(duration8).duration_.not(duration4, duration5).query.get.map(_ ==> List(3, 6, 7, 8))
    } yield ()
  }
}
