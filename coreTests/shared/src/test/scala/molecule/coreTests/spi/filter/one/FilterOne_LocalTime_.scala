// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.one

import java.time.LocalTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterOne_LocalTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - types { implicit conn =>
      val a = (1, localTime1)
      val b = (2, localTime2)
      val c = (3, localTime3)
      for {
        _ <- Ns.i.localTime.insert(List(a, b, c)).transact

        // Find all attribute values
        _ <- Ns.i.a1.localTime.query.get.map(_ ==> List(a, b, c))

        // Find value(s) matching
        _ <- Ns.i.a1.localTime(localTime0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.localTime(localTime1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.localTime(Seq(localTime0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.localTime(Seq(localTime1)).query.get.map(_ ==> List(a))
        // OR semantics for multiple args
        _ <- Ns.i.a1.localTime(localTime1, localTime2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.localTime(localTime1, localTime0).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.localTime(Seq(localTime1, localTime2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.localTime(Seq(localTime1, localTime0)).query.get.map(_ ==> List(a))
        // Empty Seq of args matches no values
        _ <- Ns.i.a1.localTime(Seq.empty[LocalTime]).query.get.map(_ ==> List())

        // Find values not matching
        _ <- Ns.i.a1.localTime.not(localTime0).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.localTime.not(localTime1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.localTime.not(localTime2).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.localTime.not(localTime3).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.localTime.not(Seq(localTime0)).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.localTime.not(Seq(localTime1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.localTime.not(Seq(localTime2)).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.localTime.not(Seq(localTime3)).query.get.map(_ ==> List(a, b))
        // OR semantics for multiple args
        _ <- Ns.i.a1.localTime.not(localTime0, localTime1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.localTime.not(localTime1, localTime2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.localTime.not(localTime2, localTime3).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.localTime.not(Seq(localTime0, localTime1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.localTime.not(Seq(localTime1, localTime2)).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.localTime.not(Seq(localTime2, localTime3)).query.get.map(_ ==> List(a))
        // Empty Seq of negation args matches all values
        _ <- Ns.i.a1.localTime.not(Seq.empty[LocalTime]).query.get.map(_ ==> List(a, b, c))

        // Find values in range
        _ <- Ns.i.a1.localTime.<(localTime2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.localTime.>(localTime2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.localTime.<=(localTime2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.localTime.>=(localTime2).query.get.map(_ ==> List(b, c))
      } yield ()
    }


    "Tacit" - types { implicit conn =>
      val (a, b, c, x) = (1, 2, 3, 4)
      for {
        _ <- Ns.i.localTime_?.insert(List(
          (a, Some(localTime1)),
          (b, Some(localTime2)),
          (c, Some(localTime3)),
          (x, None)
        )).transact

        // Match asserted attribute without returning its value
        _ <- Ns.i.a1.localTime_.query.get.map(_ ==> List(a, b, c))

        // Match non-asserted attribute (null)
        _ <- Ns.i.a1.localTime_().query.get.map(_ ==> List(x))

        // Match attribute values without returning them
        _ <- Ns.i.a1.localTime_(localTime0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.localTime_(localTime1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.localTime_(Seq(localTime0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.localTime_(Seq(localTime1)).query.get.map(_ ==> List(a))
        // OR semantics for multiple args
        _ <- Ns.i.a1.localTime_(localTime1, localTime2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.localTime_(localTime1, localTime0).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.localTime_(Seq(localTime1, localTime2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.localTime_(Seq(localTime1, localTime0)).query.get.map(_ ==> List(a))
        // Empty Seq of args matches no values
        _ <- Ns.i.a1.localTime_(Seq.empty[LocalTime]).query.get.map(_ ==> List())

        // Match non-matching values without returning them
        _ <- Ns.i.a1.localTime_.not(localTime0).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.localTime_.not(localTime1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.localTime_.not(localTime2).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.localTime_.not(localTime3).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.localTime_.not(Seq(localTime0)).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.localTime_.not(Seq(localTime1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.localTime_.not(Seq(localTime2)).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.localTime_.not(Seq(localTime3)).query.get.map(_ ==> List(a, b))
        // OR semantics for multiple args
        _ <- Ns.i.a1.localTime_.not(localTime0, localTime1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.localTime_.not(localTime1, localTime2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.localTime_.not(localTime2, localTime3).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.localTime_.not(Seq(localTime0, localTime1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.localTime_.not(Seq(localTime1, localTime2)).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.localTime_.not(Seq(localTime2, localTime3)).query.get.map(_ ==> List(a))
        // Empty Seq of negation args matches all asserted values (non-null)
        _ <- Ns.i.a1.localTime_.not(Seq.empty[LocalTime]).query.get.map(_ ==> List(a, b, c))

        // Match value ranges without returning them
        _ <- Ns.i.a1.localTime_.<(localTime2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.localTime_.>(localTime2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.localTime_.<=(localTime2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.localTime_.>=(localTime2).query.get.map(_ ==> List(b, c))
      } yield ()
    }


    "Optional" - types { implicit conn =>
      val a = (1, Some(localTime1))
      val b = (2, Some(localTime2))
      val c = (3, Some(localTime3))
      val x = (4, Option.empty[LocalTime])
      for {
        _ <- Ns.i.localTime_?.insert(List(a, b, c, x)).transact

        // Find all optional attribute values
        _ <- Ns.i.a1.localTime_?.query.get.map(_ ==> List(a, b, c, x))

        // Find optional values matching
        _ <- Ns.i.a1.localTime_?(Some(localTime0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.localTime_?(Some(localTime1)).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.localTime_?(Some(Seq(localTime0))).query.get.map(_ ==> List())
        _ <- Ns.i.a1.localTime_?(Some(Seq(localTime1))).query.get.map(_ ==> List(a))
        // OR semantics for Ses of multiple args
        _ <- Ns.i.a1.localTime_?(Some(Seq(localTime1, localTime2))).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.localTime_?(Some(Seq(localTime1, localTime0))).query.get.map(_ ==> List(a))
        // Empty Seq of args matches no values
        _ <- Ns.i.a1.localTime_?(Some(Seq.empty[LocalTime])).query.get.map(_ ==> List())
        // None matches non-asserted/null values
        _ <- Ns.i.a1.localTime_?(Option.empty[LocalTime]).query.get.map(_ ==> List(x))
        _ <- Ns.i.a1.localTime_?(Option.empty[Seq[LocalTime]]).query.get.map(_ ==> List(x))

        // Find optional values not matching
        _ <- Ns.i.a1.localTime_?.not(Some(localTime0)).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.localTime_?.not(Some(localTime1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.localTime_?.not(Some(localTime2)).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.localTime_?.not(Some(localTime3)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.localTime_?.not(Some(Seq(localTime0))).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.localTime_?.not(Some(Seq(localTime1))).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.localTime_?.not(Some(Seq(localTime2))).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.localTime_?.not(Some(Seq(localTime3))).query.get.map(_ ==> List(a, b))
        // OR semantics for multiple negation args
        _ <- Ns.i.a1.localTime_?.not(Some(Seq(localTime0, localTime1))).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.localTime_?.not(Some(Seq(localTime1, localTime2))).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.localTime_?.not(Some(Seq(localTime2, localTime3))).query.get.map(_ ==> List(a))
        // Empty Seq of negation args matches all asserted values (non-null)
        _ <- Ns.i.a1.localTime_?.not(Some(Seq.empty[LocalTime])).query.get.map(_ ==> List(a, b, c))
        // Negating None matches all asserted values (non-null)
        _ <- Ns.i.a1.localTime_?.not(Option.empty[LocalTime]).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.localTime_?.not(Option.empty[Seq[LocalTime]]).query.get.map(_ ==> List(a, b, c))

        // Find optional values in range
        _ <- Ns.i.a1.localTime_?.<(Some(localTime2)).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.localTime_?.>(Some(localTime2)).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.localTime_?.<=(Some(localTime2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.localTime_?.>=(Some(localTime2)).query.get.map(_ ==> List(b, c))
        // None can't be compared and returns empty result
        _ <- Ns.i.a1.localTime_?.<(None).query.get.map(_ ==> List())
        _ <- Ns.i.a1.localTime_?.<=(None).query.get.map(_ ==> List())
        _ <- Ns.i.a1.localTime_?.>(None).query.get.map(_ ==> List())
        _ <- Ns.i.a1.localTime_?.>=(None).query.get.map(_ ==> List())
      } yield ()
    }
  }
}
