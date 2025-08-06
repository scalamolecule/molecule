// GENERATED CODE ********************************
package molecule.db.compliance.test.filter.one.types

import java.time.LocalDateTime
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders

case class FilterOne_LocalDateTime_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "Mandatory" - types {
    val a = (1, localDateTime1)
    val b = (2, localDateTime2)
    val c = (3, localDateTime3)
    for {
      _ <- Entity.i.localDateTime.insert(List(a, b, c)).transact

      // Find all attribute values
      _ <- Entity.i.a1.localDateTime.query.get.map(_ ==> List(a, b, c))

      // Find value(s) matching
      _ <- Entity.i.a1.localDateTime(localDateTime0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateTime(localDateTime1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localDateTime(Seq(localDateTime0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateTime(Seq(localDateTime1)).query.get.map(_ ==> List(a))
      // OR semantics for multiple args ("is this or that")
      _ <- Entity.i.a1.localDateTime(localDateTime1, localDateTime2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localDateTime(localDateTime1, localDateTime0).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localDateTime(Seq(localDateTime1, localDateTime2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localDateTime(Seq(localDateTime1, localDateTime0)).query.get.map(_ ==> List(a))
      // Empty Seq of args matches no values
      _ <- Entity.i.a1.localDateTime(Seq.empty[LocalDateTime]).query.get.map(_ ==> List())

      // Find values not matching
      _ <- Entity.i.a1.localDateTime.not(localDateTime0).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.localDateTime.not(localDateTime1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.localDateTime.not(localDateTime2).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.localDateTime.not(localDateTime3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localDateTime.not(Seq(localDateTime0)).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.localDateTime.not(Seq(localDateTime1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.localDateTime.not(Seq(localDateTime2)).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.localDateTime.not(Seq(localDateTime3)).query.get.map(_ ==> List(a, b))
      // OR semantics for multiple args
      _ <- Entity.i.a1.localDateTime.not(localDateTime0, localDateTime1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.localDateTime.not(localDateTime1, localDateTime2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.localDateTime.not(localDateTime2, localDateTime3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localDateTime.not(Seq(localDateTime0, localDateTime1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.localDateTime.not(Seq(localDateTime1, localDateTime2)).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.localDateTime.not(Seq(localDateTime2, localDateTime3)).query.get.map(_ ==> List(a))
      // Empty Seq of negation args matches all values
      _ <- Entity.i.a1.localDateTime.not(Seq.empty[LocalDateTime]).query.get.map(_ ==> List(a, b, c))

      // Find values in range
      _ <- Entity.i.a1.localDateTime.<(localDateTime2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localDateTime.>(localDateTime2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.localDateTime.<=(localDateTime2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localDateTime.>=(localDateTime2).query.get.map(_ ==> List(b, c))
    } yield ()
  }


  "Tacit" - types {
    val (a, b, c, x) = (1, 2, 3, 4)
    for {
      _ <- Entity.i.localDateTime_?.insert(List(
        (a, Some(localDateTime1)),
        (b, Some(localDateTime2)),
        (c, Some(localDateTime3)),
        (x, None)
      )).transact

      // Match asserted attribute without returning its value
      _ <- Entity.i.a1.localDateTime_.query.get.map(_ ==> List(a, b, c))

      // Match non-asserted attribute (null)
      _ <- Entity.i.a1.localDateTime_().query.get.map(_ ==> List(x))

      // Match attribute values without returning them
      _ <- Entity.i.a1.localDateTime_(localDateTime0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateTime_(localDateTime1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localDateTime_(Seq(localDateTime0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateTime_(Seq(localDateTime1)).query.get.map(_ ==> List(a))
      // OR semantics for multiple args ("is this or that")
      _ <- Entity.i.a1.localDateTime_(localDateTime1, localDateTime2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localDateTime_(localDateTime1, localDateTime0).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localDateTime_(Seq(localDateTime1, localDateTime2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localDateTime_(Seq(localDateTime1, localDateTime0)).query.get.map(_ ==> List(a))
      // Empty Seq of args matches no values
      _ <- Entity.i.a1.localDateTime_(Seq.empty[LocalDateTime]).query.get.map(_ ==> List())

      // Match non-matching values without returning them
      _ <- Entity.i.a1.localDateTime_.not(localDateTime0).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.localDateTime_.not(localDateTime1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.localDateTime_.not(localDateTime2).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.localDateTime_.not(localDateTime3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localDateTime_.not(Seq(localDateTime0)).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.localDateTime_.not(Seq(localDateTime1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.localDateTime_.not(Seq(localDateTime2)).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.localDateTime_.not(Seq(localDateTime3)).query.get.map(_ ==> List(a, b))
      // OR semantics for multiple args
      _ <- Entity.i.a1.localDateTime_.not(localDateTime0, localDateTime1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.localDateTime_.not(localDateTime1, localDateTime2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.localDateTime_.not(localDateTime2, localDateTime3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localDateTime_.not(Seq(localDateTime0, localDateTime1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.localDateTime_.not(Seq(localDateTime1, localDateTime2)).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.localDateTime_.not(Seq(localDateTime2, localDateTime3)).query.get.map(_ ==> List(a))
      // Empty Seq of negation args matches all asserted values (non-null)
      _ <- Entity.i.a1.localDateTime_.not(Seq.empty[LocalDateTime]).query.get.map(_ ==> List(a, b, c))

      // Match value ranges without returning them
      _ <- Entity.i.a1.localDateTime_.<(localDateTime2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localDateTime_.>(localDateTime2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.localDateTime_.<=(localDateTime2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localDateTime_.>=(localDateTime2).query.get.map(_ ==> List(b, c))
    } yield ()
  }


  "Optional" - types {
    val a = (1, Some(localDateTime1))
    val b = (2, Some(localDateTime2))
    val c = (3, Some(localDateTime3))
    val x = (4, Option.empty[LocalDateTime])
    for {
      _ <- Entity.i.localDateTime_?.insert(List(a, b, c, x)).transact

      // Find all optional attribute values
      _ <- Entity.i.a1.localDateTime_?.query.get.map(_ ==> List(a, b, c, x))

      // Find optional values matching
      _ <- Entity.i.a1.localDateTime_?(Some(localDateTime0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateTime_?(Some(localDateTime1)).query.get.map(_ ==> List(a))

      // None matches non-asserted/null values
      _ <- Entity.i.a1.localDateTime_?(Option.empty[LocalDateTime]).query.get.map(_ ==> List(x))
      // Easier to apply nothing to tacit attribute
      _ <- Entity.i.a1.localDateTime_().query.get.map(_ ==> List(4))
    } yield ()
  }


  "Combinations" - types {
    for {
      _ <- Entity.i.localDateTime.insert(
        (1, localDateTime1),
        (2, localDateTime2),
        (3, localDateTime3),
        (4, localDateTime4),
        (5, localDateTime5),
        (6, localDateTime6),
        (7, localDateTime7),
        (8, localDateTime8),
        (9, localDateTime9),
      ).transact

      _ <- Entity.i.a1.localDateTime_.>(localDateTime2).query.get.map(_ ==> List(3, 4, 5, 6, 7, 8, 9))
      _ <- Entity.i.a1.localDateTime_.>(localDateTime2).localDateTime_.<=(localDateTime8).query.get.map(_ ==> List(3, 4, 5, 6, 7, 8))
      _ <- Entity.i.a1.localDateTime_.>(localDateTime2).localDateTime_.<=(localDateTime8).localDateTime_.not(localDateTime4, localDateTime5).query.get.map(_ ==> List(3, 6, 7, 8))
    } yield ()
  }
}
