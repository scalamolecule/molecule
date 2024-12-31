// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.one.types

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.domains.dsl.Types._
import molecule.coreTests.setup._

case class FilterOne_Long_(
  suite: Test,
  api: Api_async with Spi_async with DbProviders
) extends TestUtils {

  import api._
  import suite._

  "Mandatory" - types { implicit conn =>
    val a = (1, long1)
    val b = (2, long2)
    val c = (3, long3)
    for {
      _ <- Entity.i.long.insert(List(a, b, c)).transact

      // Find all attribute values
      _ <- Entity.i.a1.long.query.get.map(_ ==> List(a, b, c))

      // Find value(s) matching
      _ <- Entity.i.a1.long(long0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.long(long1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.long(Seq(long0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.long(Seq(long1)).query.get.map(_ ==> List(a))
      // OR semantics for multiple args ("is this or that")
      _ <- Entity.i.a1.long(long1, long2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.long(long1, long0).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.long(Seq(long1, long2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.long(Seq(long1, long0)).query.get.map(_ ==> List(a))
      // Empty Seq of args matches no values
      _ <- Entity.i.a1.long(Seq.empty[Long]).query.get.map(_ ==> List())

      // Find values not matching
      _ <- Entity.i.a1.long.not(long0).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.long.not(long1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.long.not(long2).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.long.not(long3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.long.not(Seq(long0)).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.long.not(Seq(long1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.long.not(Seq(long2)).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.long.not(Seq(long3)).query.get.map(_ ==> List(a, b))
      // OR semantics for multiple args
      _ <- Entity.i.a1.long.not(long0, long1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.long.not(long1, long2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.long.not(long2, long3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.long.not(Seq(long0, long1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.long.not(Seq(long1, long2)).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.long.not(Seq(long2, long3)).query.get.map(_ ==> List(a))
      // Empty Seq of negation args matches all values
      _ <- Entity.i.a1.long.not(Seq.empty[Long]).query.get.map(_ ==> List(a, b, c))

      // Find values in range
      _ <- Entity.i.a1.long.<(long2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.long.>(long2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.long.<=(long2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.long.>=(long2).query.get.map(_ ==> List(b, c))
    } yield ()
  }


  "Tacit" - types { implicit conn =>
    val (a, b, c, x) = (1, 2, 3, 4)
    for {
      _ <- Entity.i.long_?.insert(List(
        (a, Some(long1)),
        (b, Some(long2)),
        (c, Some(long3)),
        (x, None)
      )).transact

      // Match asserted attribute without returning its value
      _ <- Entity.i.a1.long_.query.get.map(_ ==> List(a, b, c))

      // Match non-asserted attribute (null)
      _ <- Entity.i.a1.long_().query.get.map(_ ==> List(x))

      // Match attribute values without returning them
      _ <- Entity.i.a1.long_(long0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.long_(long1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.long_(Seq(long0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.long_(Seq(long1)).query.get.map(_ ==> List(a))
      // OR semantics for multiple args ("is this or that")
      _ <- Entity.i.a1.long_(long1, long2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.long_(long1, long0).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.long_(Seq(long1, long2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.long_(Seq(long1, long0)).query.get.map(_ ==> List(a))
      // Empty Seq of args matches no values
      _ <- Entity.i.a1.long_(Seq.empty[Long]).query.get.map(_ ==> List())

      // Match non-matching values without returning them
      _ <- Entity.i.a1.long_.not(long0).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.long_.not(long1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.long_.not(long2).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.long_.not(long3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.long_.not(Seq(long0)).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.long_.not(Seq(long1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.long_.not(Seq(long2)).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.long_.not(Seq(long3)).query.get.map(_ ==> List(a, b))
      // OR semantics for multiple args
      _ <- Entity.i.a1.long_.not(long0, long1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.long_.not(long1, long2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.long_.not(long2, long3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.long_.not(Seq(long0, long1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.long_.not(Seq(long1, long2)).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.long_.not(Seq(long2, long3)).query.get.map(_ ==> List(a))
      // Empty Seq of negation args matches all asserted values (non-null)
      _ <- Entity.i.a1.long_.not(Seq.empty[Long]).query.get.map(_ ==> List(a, b, c))

      // Match value ranges without returning them
      _ <- Entity.i.a1.long_.<(long2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.long_.>(long2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.long_.<=(long2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.long_.>=(long2).query.get.map(_ ==> List(b, c))
    } yield ()
  }


  "Optional" - types { implicit conn =>
    val a = (1, Some(long1))
    val b = (2, Some(long2))
    val c = (3, Some(long3))
    val x = (4, Option.empty[Long])
    for {
      _ <- Entity.i.long_?.insert(List(a, b, c, x)).transact

      // Find all optional attribute values
      _ <- Entity.i.a1.long_?.query.get.map(_ ==> List(a, b, c, x))

      // Find optional values matching
      _ <- Entity.i.a1.long_?(Some(long0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.long_?(Some(long1)).query.get.map(_ ==> List(a))

      // None matches non-asserted/null values
      _ <- Entity.i.a1.long_?(Option.empty[Long]).query.get.map(_ ==> List(x))
      // Easier to apply nothing to tacit attribute
      _ <- Entity.i.a1.long_().query.get.map(_ ==> List(4))
    } yield ()
  }


  "Combinations" - types { implicit conn =>
    for {
      _ <- Entity.i.long.insert(
        (1, long1),
        (2, long2),
        (3, long3),
        (4, long4),
        (5, long5),
        (6, long6),
        (7, long7),
        (8, long8),
        (9, long9),
      ).transact

      _ <- Entity.i.a1.long_.>(long2).query.get.map(_ ==> List(3, 4, 5, 6, 7, 8, 9))
      _ <- Entity.i.a1.long_.>(long2).long_.<=(long8).query.get.map(_ ==> List(3, 4, 5, 6, 7, 8))
      _ <- Entity.i.a1.long_.>(long2).long_.<=(long8).long_.not(long4, long5).query.get.map(_ ==> List(3, 6, 7, 8))
    } yield ()
  }
}
