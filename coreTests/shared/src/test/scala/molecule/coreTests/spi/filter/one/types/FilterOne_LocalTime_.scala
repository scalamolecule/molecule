// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.one.types

import java.time.LocalTime
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.domains.dsl.Types._
import molecule.coreTests.setup._

case class FilterOne_LocalTime_(
  suite: MUnitSuite,
  api: Api_async with Spi_async with DbProviders
) extends TestUtils {

  import api._
  import suite._

  "Mandatory" - types { implicit conn =>
    val a = (1, localTime1)
    val b = (2, localTime2)
    val c = (3, localTime3)
    for {
      _ <- Entity.i.localTime.insert(List(a, b, c)).transact

      // Find all attribute values
      _ <- Entity.i.a1.localTime.query.get.map(_ ==> List(a, b, c))

      // Find value(s) matching
      _ <- Entity.i.a1.localTime(localTime0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localTime(localTime1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localTime(Seq(localTime0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localTime(Seq(localTime1)).query.get.map(_ ==> List(a))
      // OR semantics for multiple args ("is this or that")
      _ <- Entity.i.a1.localTime(localTime1, localTime2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localTime(localTime1, localTime0).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localTime(Seq(localTime1, localTime2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localTime(Seq(localTime1, localTime0)).query.get.map(_ ==> List(a))
      // Empty Seq of args matches no values
      _ <- Entity.i.a1.localTime(Seq.empty[LocalTime]).query.get.map(_ ==> List())

      // Find values not matching
      _ <- Entity.i.a1.localTime.not(localTime0).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.localTime.not(localTime1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.localTime.not(localTime2).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.localTime.not(localTime3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localTime.not(Seq(localTime0)).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.localTime.not(Seq(localTime1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.localTime.not(Seq(localTime2)).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.localTime.not(Seq(localTime3)).query.get.map(_ ==> List(a, b))
      // OR semantics for multiple args
      _ <- Entity.i.a1.localTime.not(localTime0, localTime1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.localTime.not(localTime1, localTime2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.localTime.not(localTime2, localTime3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localTime.not(Seq(localTime0, localTime1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.localTime.not(Seq(localTime1, localTime2)).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.localTime.not(Seq(localTime2, localTime3)).query.get.map(_ ==> List(a))
      // Empty Seq of negation args matches all values
      _ <- Entity.i.a1.localTime.not(Seq.empty[LocalTime]).query.get.map(_ ==> List(a, b, c))

      // Find values in range
      _ <- Entity.i.a1.localTime.<(localTime2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localTime.>(localTime2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.localTime.<=(localTime2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localTime.>=(localTime2).query.get.map(_ ==> List(b, c))
    } yield ()
  }


  "Tacit" - types { implicit conn =>
    val (a, b, c, x) = (1, 2, 3, 4)
    for {
      _ <- Entity.i.localTime_?.insert(List(
        (a, Some(localTime1)),
        (b, Some(localTime2)),
        (c, Some(localTime3)),
        (x, None)
      )).transact

      // Match asserted attribute without returning its value
      _ <- Entity.i.a1.localTime_.query.get.map(_ ==> List(a, b, c))

      // Match non-asserted attribute (null)
      _ <- Entity.i.a1.localTime_().query.get.map(_ ==> List(x))

      // Match attribute values without returning them
      _ <- Entity.i.a1.localTime_(localTime0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localTime_(localTime1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localTime_(Seq(localTime0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localTime_(Seq(localTime1)).query.get.map(_ ==> List(a))
      // OR semantics for multiple args ("is this or that")
      _ <- Entity.i.a1.localTime_(localTime1, localTime2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localTime_(localTime1, localTime0).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localTime_(Seq(localTime1, localTime2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localTime_(Seq(localTime1, localTime0)).query.get.map(_ ==> List(a))
      // Empty Seq of args matches no values
      _ <- Entity.i.a1.localTime_(Seq.empty[LocalTime]).query.get.map(_ ==> List())

      // Match non-matching values without returning them
      _ <- Entity.i.a1.localTime_.not(localTime0).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.localTime_.not(localTime1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.localTime_.not(localTime2).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.localTime_.not(localTime3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localTime_.not(Seq(localTime0)).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.localTime_.not(Seq(localTime1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.localTime_.not(Seq(localTime2)).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.localTime_.not(Seq(localTime3)).query.get.map(_ ==> List(a, b))
      // OR semantics for multiple args
      _ <- Entity.i.a1.localTime_.not(localTime0, localTime1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.localTime_.not(localTime1, localTime2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.localTime_.not(localTime2, localTime3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localTime_.not(Seq(localTime0, localTime1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.localTime_.not(Seq(localTime1, localTime2)).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.localTime_.not(Seq(localTime2, localTime3)).query.get.map(_ ==> List(a))
      // Empty Seq of negation args matches all asserted values (non-null)
      _ <- Entity.i.a1.localTime_.not(Seq.empty[LocalTime]).query.get.map(_ ==> List(a, b, c))

      // Match value ranges without returning them
      _ <- Entity.i.a1.localTime_.<(localTime2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localTime_.>(localTime2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.localTime_.<=(localTime2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localTime_.>=(localTime2).query.get.map(_ ==> List(b, c))
    } yield ()
  }


  "Optional" - types { implicit conn =>
    val a = (1, Some(localTime1))
    val b = (2, Some(localTime2))
    val c = (3, Some(localTime3))
    val x = (4, Option.empty[LocalTime])
    for {
      _ <- Entity.i.localTime_?.insert(List(a, b, c, x)).transact

      // Find all optional attribute values
      _ <- Entity.i.a1.localTime_?.query.get.map(_ ==> List(a, b, c, x))

      // Find optional values matching
      _ <- Entity.i.a1.localTime_?(Some(localTime0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localTime_?(Some(localTime1)).query.get.map(_ ==> List(a))

      // None matches non-asserted/null values
      _ <- Entity.i.a1.localTime_?(Option.empty[LocalTime]).query.get.map(_ ==> List(x))
      // Easier to apply nothing to tacit attribute
      _ <- Entity.i.a1.localTime_().query.get.map(_ ==> List(4))
    } yield ()
  }


  "Combinations" - types { implicit conn =>
    for {
      _ <- Entity.i.localTime.insert(
        (1, localTime1),
        (2, localTime2),
        (3, localTime3),
        (4, localTime4),
        (5, localTime5),
        (6, localTime6),
        (7, localTime7),
        (8, localTime8),
        (9, localTime9),
      ).transact

      _ <- Entity.i.a1.localTime_.>(localTime2).query.get.map(_ ==> List(3, 4, 5, 6, 7, 8, 9))
      _ <- Entity.i.a1.localTime_.>(localTime2).localTime_.<=(localTime8).query.get.map(_ ==> List(3, 4, 5, 6, 7, 8))
      _ <- Entity.i.a1.localTime_.>(localTime2).localTime_.<=(localTime8).localTime_.not(localTime4, localTime5).query.get.map(_ ==> List(3, 6, 7, 8))
    } yield ()
  }
}
