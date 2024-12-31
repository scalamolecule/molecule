// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.one.types

import java.time.ZonedDateTime
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.domains.dsl.Types._
import molecule.coreTests.setup._

case class FilterOne_ZonedDateTime_(
  suite: Test,
  api: Api_async with Spi_async with DbProviders
) extends TestUtils {

  import api._
  import suite._

  "Mandatory" - types { implicit conn =>
    val a = (1, zonedDateTime1)
    val b = (2, zonedDateTime2)
    val c = (3, zonedDateTime3)
    for {
      _ <- Entity.i.zonedDateTime.insert(List(a, b, c)).transact

      // Find all attribute values
      _ <- Entity.i.a1.zonedDateTime.query.get.map(_ ==> List(a, b, c))

      // Find value(s) matching
      _ <- Entity.i.a1.zonedDateTime(zonedDateTime0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.zonedDateTime(zonedDateTime1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.zonedDateTime(Seq(zonedDateTime0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.zonedDateTime(Seq(zonedDateTime1)).query.get.map(_ ==> List(a))
      // OR semantics for multiple args ("is this or that")
      _ <- Entity.i.a1.zonedDateTime(zonedDateTime1, zonedDateTime2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.zonedDateTime(zonedDateTime1, zonedDateTime0).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.zonedDateTime(Seq(zonedDateTime1, zonedDateTime2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.zonedDateTime(Seq(zonedDateTime1, zonedDateTime0)).query.get.map(_ ==> List(a))
      // Empty Seq of args matches no values
      _ <- Entity.i.a1.zonedDateTime(Seq.empty[ZonedDateTime]).query.get.map(_ ==> List())

      // Find values not matching
      _ <- Entity.i.a1.zonedDateTime.not(zonedDateTime0).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.zonedDateTime.not(zonedDateTime1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.zonedDateTime.not(zonedDateTime2).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.zonedDateTime.not(zonedDateTime3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.zonedDateTime.not(Seq(zonedDateTime0)).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.zonedDateTime.not(Seq(zonedDateTime1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.zonedDateTime.not(Seq(zonedDateTime2)).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.zonedDateTime.not(Seq(zonedDateTime3)).query.get.map(_ ==> List(a, b))
      // OR semantics for multiple args
      _ <- Entity.i.a1.zonedDateTime.not(zonedDateTime0, zonedDateTime1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.zonedDateTime.not(zonedDateTime1, zonedDateTime2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.zonedDateTime.not(zonedDateTime2, zonedDateTime3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.zonedDateTime.not(Seq(zonedDateTime0, zonedDateTime1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.zonedDateTime.not(Seq(zonedDateTime1, zonedDateTime2)).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.zonedDateTime.not(Seq(zonedDateTime2, zonedDateTime3)).query.get.map(_ ==> List(a))
      // Empty Seq of negation args matches all values
      _ <- Entity.i.a1.zonedDateTime.not(Seq.empty[ZonedDateTime]).query.get.map(_ ==> List(a, b, c))

      // Find values in range
      _ <- Entity.i.a1.zonedDateTime.<(zonedDateTime2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.zonedDateTime.>(zonedDateTime2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.zonedDateTime.<=(zonedDateTime2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.zonedDateTime.>=(zonedDateTime2).query.get.map(_ ==> List(b, c))
    } yield ()
  }


  "Tacit" - types { implicit conn =>
    val (a, b, c, x) = (1, 2, 3, 4)
    for {
      _ <- Entity.i.zonedDateTime_?.insert(List(
        (a, Some(zonedDateTime1)),
        (b, Some(zonedDateTime2)),
        (c, Some(zonedDateTime3)),
        (x, None)
      )).transact

      // Match asserted attribute without returning its value
      _ <- Entity.i.a1.zonedDateTime_.query.get.map(_ ==> List(a, b, c))

      // Match non-asserted attribute (null)
      _ <- Entity.i.a1.zonedDateTime_().query.get.map(_ ==> List(x))

      // Match attribute values without returning them
      _ <- Entity.i.a1.zonedDateTime_(zonedDateTime0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.zonedDateTime_(zonedDateTime1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.zonedDateTime_(Seq(zonedDateTime0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.zonedDateTime_(Seq(zonedDateTime1)).query.get.map(_ ==> List(a))
      // OR semantics for multiple args ("is this or that")
      _ <- Entity.i.a1.zonedDateTime_(zonedDateTime1, zonedDateTime2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.zonedDateTime_(zonedDateTime1, zonedDateTime0).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.zonedDateTime_(Seq(zonedDateTime1, zonedDateTime2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.zonedDateTime_(Seq(zonedDateTime1, zonedDateTime0)).query.get.map(_ ==> List(a))
      // Empty Seq of args matches no values
      _ <- Entity.i.a1.zonedDateTime_(Seq.empty[ZonedDateTime]).query.get.map(_ ==> List())

      // Match non-matching values without returning them
      _ <- Entity.i.a1.zonedDateTime_.not(zonedDateTime0).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.zonedDateTime_.not(zonedDateTime1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.zonedDateTime_.not(zonedDateTime2).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.zonedDateTime_.not(zonedDateTime3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.zonedDateTime_.not(Seq(zonedDateTime0)).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.zonedDateTime_.not(Seq(zonedDateTime1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.zonedDateTime_.not(Seq(zonedDateTime2)).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.zonedDateTime_.not(Seq(zonedDateTime3)).query.get.map(_ ==> List(a, b))
      // OR semantics for multiple args
      _ <- Entity.i.a1.zonedDateTime_.not(zonedDateTime0, zonedDateTime1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.zonedDateTime_.not(zonedDateTime1, zonedDateTime2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.zonedDateTime_.not(zonedDateTime2, zonedDateTime3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.zonedDateTime_.not(Seq(zonedDateTime0, zonedDateTime1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.zonedDateTime_.not(Seq(zonedDateTime1, zonedDateTime2)).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.zonedDateTime_.not(Seq(zonedDateTime2, zonedDateTime3)).query.get.map(_ ==> List(a))
      // Empty Seq of negation args matches all asserted values (non-null)
      _ <- Entity.i.a1.zonedDateTime_.not(Seq.empty[ZonedDateTime]).query.get.map(_ ==> List(a, b, c))

      // Match value ranges without returning them
      _ <- Entity.i.a1.zonedDateTime_.<(zonedDateTime2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.zonedDateTime_.>(zonedDateTime2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.zonedDateTime_.<=(zonedDateTime2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.zonedDateTime_.>=(zonedDateTime2).query.get.map(_ ==> List(b, c))
    } yield ()
  }


  "Optional" - types { implicit conn =>
    val a = (1, Some(zonedDateTime1))
    val b = (2, Some(zonedDateTime2))
    val c = (3, Some(zonedDateTime3))
    val x = (4, Option.empty[ZonedDateTime])
    for {
      _ <- Entity.i.zonedDateTime_?.insert(List(a, b, c, x)).transact

      // Find all optional attribute values
      _ <- Entity.i.a1.zonedDateTime_?.query.get.map(_ ==> List(a, b, c, x))

      // Find optional values matching
      _ <- Entity.i.a1.zonedDateTime_?(Some(zonedDateTime0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.zonedDateTime_?(Some(zonedDateTime1)).query.get.map(_ ==> List(a))

      // None matches non-asserted/null values
      _ <- Entity.i.a1.zonedDateTime_?(Option.empty[ZonedDateTime]).query.get.map(_ ==> List(x))
      // Easier to apply nothing to tacit attribute
      _ <- Entity.i.a1.zonedDateTime_().query.get.map(_ ==> List(4))
    } yield ()
  }


  "Combinations" - types { implicit conn =>
    for {
      _ <- Entity.i.zonedDateTime.insert(
        (1, zonedDateTime1),
        (2, zonedDateTime2),
        (3, zonedDateTime3),
        (4, zonedDateTime4),
        (5, zonedDateTime5),
        (6, zonedDateTime6),
        (7, zonedDateTime7),
        (8, zonedDateTime8),
        (9, zonedDateTime9),
      ).transact

      _ <- Entity.i.a1.zonedDateTime_.>(zonedDateTime2).query.get.map(_ ==> List(3, 4, 5, 6, 7, 8, 9))
      _ <- Entity.i.a1.zonedDateTime_.>(zonedDateTime2).zonedDateTime_.<=(zonedDateTime8).query.get.map(_ ==> List(3, 4, 5, 6, 7, 8))
      _ <- Entity.i.a1.zonedDateTime_.>(zonedDateTime2).zonedDateTime_.<=(zonedDateTime8).zonedDateTime_.not(zonedDateTime4, zonedDateTime5).query.get.map(_ ==> List(3, 6, 7, 8))
    } yield ()
  }
}
