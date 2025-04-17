// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.one.types

import java.time.OffsetTime
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.domains.dsl.Types._
import molecule.coreTests.setup._

case class FilterOne_OffsetTime_(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api._
  import suite._

  "Mandatory" - types { implicit conn =>
    val a = (1, offsetTime1)
    val b = (2, offsetTime2)
    val c = (3, offsetTime3)
    for {
      _ <- Entity.i.offsetTime.insert(List(a, b, c)).transact

      // Find all attribute values
      _ <- Entity.i.a1.offsetTime.query.get.map(_ ==> List(a, b, c))

      // Find value(s) matching
      _ <- Entity.i.a1.offsetTime(offsetTime0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetTime(offsetTime1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.offsetTime(Seq(offsetTime0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetTime(Seq(offsetTime1)).query.get.map(_ ==> List(a))
      // OR semantics for multiple args ("is this or that")
      _ <- Entity.i.a1.offsetTime(offsetTime1, offsetTime2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.offsetTime(offsetTime1, offsetTime0).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.offsetTime(Seq(offsetTime1, offsetTime2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.offsetTime(Seq(offsetTime1, offsetTime0)).query.get.map(_ ==> List(a))
      // Empty Seq of args matches no values
      _ <- Entity.i.a1.offsetTime(Seq.empty[OffsetTime]).query.get.map(_ ==> List())

      // Find values not matching
      _ <- Entity.i.a1.offsetTime.not(offsetTime0).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.offsetTime.not(offsetTime1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.offsetTime.not(offsetTime2).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.offsetTime.not(offsetTime3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.offsetTime.not(Seq(offsetTime0)).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.offsetTime.not(Seq(offsetTime1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.offsetTime.not(Seq(offsetTime2)).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.offsetTime.not(Seq(offsetTime3)).query.get.map(_ ==> List(a, b))
      // OR semantics for multiple args
      _ <- Entity.i.a1.offsetTime.not(offsetTime0, offsetTime1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.offsetTime.not(offsetTime1, offsetTime2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.offsetTime.not(offsetTime2, offsetTime3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.offsetTime.not(Seq(offsetTime0, offsetTime1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.offsetTime.not(Seq(offsetTime1, offsetTime2)).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.offsetTime.not(Seq(offsetTime2, offsetTime3)).query.get.map(_ ==> List(a))
      // Empty Seq of negation args matches all values
      _ <- Entity.i.a1.offsetTime.not(Seq.empty[OffsetTime]).query.get.map(_ ==> List(a, b, c))

      // Find values in range
      _ <- Entity.i.a1.offsetTime.<(offsetTime2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.offsetTime.>(offsetTime2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.offsetTime.<=(offsetTime2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.offsetTime.>=(offsetTime2).query.get.map(_ ==> List(b, c))
    } yield ()
  }


  "Tacit" - types { implicit conn =>
    val (a, b, c, x) = (1, 2, 3, 4)
    for {
      _ <- Entity.i.offsetTime_?.insert(List(
        (a, Some(offsetTime1)),
        (b, Some(offsetTime2)),
        (c, Some(offsetTime3)),
        (x, None)
      )).transact

      // Match asserted attribute without returning its value
      _ <- Entity.i.a1.offsetTime_.query.get.map(_ ==> List(a, b, c))

      // Match non-asserted attribute (null)
      _ <- Entity.i.a1.offsetTime_().query.get.map(_ ==> List(x))

      // Match attribute values without returning them
      _ <- Entity.i.a1.offsetTime_(offsetTime0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetTime_(offsetTime1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.offsetTime_(Seq(offsetTime0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetTime_(Seq(offsetTime1)).query.get.map(_ ==> List(a))
      // OR semantics for multiple args ("is this or that")
      _ <- Entity.i.a1.offsetTime_(offsetTime1, offsetTime2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.offsetTime_(offsetTime1, offsetTime0).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.offsetTime_(Seq(offsetTime1, offsetTime2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.offsetTime_(Seq(offsetTime1, offsetTime0)).query.get.map(_ ==> List(a))
      // Empty Seq of args matches no values
      _ <- Entity.i.a1.offsetTime_(Seq.empty[OffsetTime]).query.get.map(_ ==> List())

      // Match non-matching values without returning them
      _ <- Entity.i.a1.offsetTime_.not(offsetTime0).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.offsetTime_.not(offsetTime1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.offsetTime_.not(offsetTime2).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.offsetTime_.not(offsetTime3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.offsetTime_.not(Seq(offsetTime0)).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.offsetTime_.not(Seq(offsetTime1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.offsetTime_.not(Seq(offsetTime2)).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.offsetTime_.not(Seq(offsetTime3)).query.get.map(_ ==> List(a, b))
      // OR semantics for multiple args
      _ <- Entity.i.a1.offsetTime_.not(offsetTime0, offsetTime1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.offsetTime_.not(offsetTime1, offsetTime2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.offsetTime_.not(offsetTime2, offsetTime3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.offsetTime_.not(Seq(offsetTime0, offsetTime1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.offsetTime_.not(Seq(offsetTime1, offsetTime2)).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.offsetTime_.not(Seq(offsetTime2, offsetTime3)).query.get.map(_ ==> List(a))
      // Empty Seq of negation args matches all asserted values (non-null)
      _ <- Entity.i.a1.offsetTime_.not(Seq.empty[OffsetTime]).query.get.map(_ ==> List(a, b, c))

      // Match value ranges without returning them
      _ <- Entity.i.a1.offsetTime_.<(offsetTime2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.offsetTime_.>(offsetTime2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.offsetTime_.<=(offsetTime2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.offsetTime_.>=(offsetTime2).query.get.map(_ ==> List(b, c))
    } yield ()
  }


  "Optional" - types { implicit conn =>
    val a = (1, Some(offsetTime1))
    val b = (2, Some(offsetTime2))
    val c = (3, Some(offsetTime3))
    val x = (4, Option.empty[OffsetTime])
    for {
      _ <- Entity.i.offsetTime_?.insert(List(a, b, c, x)).transact

      // Find all optional attribute values
      _ <- Entity.i.a1.offsetTime_?.query.get.map(_ ==> List(a, b, c, x))

      // Find optional values matching
      _ <- Entity.i.a1.offsetTime_?(Some(offsetTime0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetTime_?(Some(offsetTime1)).query.get.map(_ ==> List(a))

      // None matches non-asserted/null values
      _ <- Entity.i.a1.offsetTime_?(Option.empty[OffsetTime]).query.get.map(_ ==> List(x))
      // Easier to apply nothing to tacit attribute
      _ <- Entity.i.a1.offsetTime_().query.get.map(_ ==> List(4))
    } yield ()
  }


  "Combinations" - types { implicit conn =>
    for {
      _ <- Entity.i.offsetTime.insert(
        (1, offsetTime1),
        (2, offsetTime2),
        (3, offsetTime3),
        (4, offsetTime4),
        (5, offsetTime5),
        (6, offsetTime6),
        (7, offsetTime7),
        (8, offsetTime8),
        (9, offsetTime9),
      ).transact

      _ <- Entity.i.a1.offsetTime_.>(offsetTime2).query.get.map(_ ==> List(3, 4, 5, 6, 7, 8, 9))
      _ <- Entity.i.a1.offsetTime_.>(offsetTime2).offsetTime_.<=(offsetTime8).query.get.map(_ ==> List(3, 4, 5, 6, 7, 8))
      _ <- Entity.i.a1.offsetTime_.>(offsetTime2).offsetTime_.<=(offsetTime8).offsetTime_.not(offsetTime4, offsetTime5).query.get.map(_ ==> List(3, 6, 7, 8))
    } yield ()
  }
}
