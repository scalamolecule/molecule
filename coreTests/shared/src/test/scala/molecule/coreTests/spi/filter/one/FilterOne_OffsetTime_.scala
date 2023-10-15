// GENERATED CODE ********************************
package molecule.coreTests.compliance.filter.one

import java.time.OffsetTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterOne_OffsetTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - types { implicit conn =>
      val a = (1, offsetTime1)
      val b = (2, offsetTime2)
      val c = (3, offsetTime3)
      for {
        _ <- Ns.i.offsetTime.insert(List(a, b, c)).transact

        // Find all attribute values
        _ <- Ns.i.a1.offsetTime.query.get.map(_ ==> List(a, b, c))

        // Find value(s) matching
        _ <- Ns.i.a1.offsetTime(offsetTime0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.offsetTime(offsetTime1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.offsetTime(Seq(offsetTime0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.offsetTime(Seq(offsetTime1)).query.get.map(_ ==> List(a))
        // OR semantics for multiple args
        _ <- Ns.i.a1.offsetTime(offsetTime1, offsetTime2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.offsetTime(offsetTime1, offsetTime0).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.offsetTime(Seq(offsetTime1, offsetTime2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.offsetTime(Seq(offsetTime1, offsetTime0)).query.get.map(_ ==> List(a))
        // Empty Seq of args matches no values
        _ <- Ns.i.a1.offsetTime(Seq.empty[OffsetTime]).query.get.map(_ ==> List())

        // Find values not matching
        _ <- Ns.i.a1.offsetTime.not(offsetTime0).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.offsetTime.not(offsetTime1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.offsetTime.not(offsetTime2).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.offsetTime.not(offsetTime3).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.offsetTime.not(Seq(offsetTime0)).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.offsetTime.not(Seq(offsetTime1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.offsetTime.not(Seq(offsetTime2)).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.offsetTime.not(Seq(offsetTime3)).query.get.map(_ ==> List(a, b))
        // OR semantics for multiple args
        _ <- Ns.i.a1.offsetTime.not(offsetTime0, offsetTime1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.offsetTime.not(offsetTime1, offsetTime2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.offsetTime.not(offsetTime2, offsetTime3).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.offsetTime.not(Seq(offsetTime0, offsetTime1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.offsetTime.not(Seq(offsetTime1, offsetTime2)).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.offsetTime.not(Seq(offsetTime2, offsetTime3)).query.get.map(_ ==> List(a))
        // Empty Seq of negation args matches all values
        _ <- Ns.i.a1.offsetTime.not(Seq.empty[OffsetTime]).query.get.map(_ ==> List(a, b, c))

        // Find values in range
        _ <- Ns.i.a1.offsetTime.<(offsetTime2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.offsetTime.>(offsetTime2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.offsetTime.<=(offsetTime2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.offsetTime.>=(offsetTime2).query.get.map(_ ==> List(b, c))
      } yield ()
    }


    "Tacit" - types { implicit conn =>
      val (a, b, c, x) = (1, 2, 3, 4)
      for {
        _ <- Ns.i.offsetTime_?.insert(List(
          (a, Some(offsetTime1)),
          (b, Some(offsetTime2)),
          (c, Some(offsetTime3)),
          (x, None)
        )).transact

        // Match asserted attribute without returning its value
        _ <- Ns.i.a1.offsetTime_.query.get.map(_ ==> List(a, b, c))

        // Match non-asserted attribute (null)
        _ <- Ns.i.a1.offsetTime_().query.get.map(_ ==> List(x))

        // Match attribute values without returning them
        _ <- Ns.i.a1.offsetTime_(offsetTime0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.offsetTime_(offsetTime1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.offsetTime_(Seq(offsetTime0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.offsetTime_(Seq(offsetTime1)).query.get.map(_ ==> List(a))
        // OR semantics for multiple args
        _ <- Ns.i.a1.offsetTime_(offsetTime1, offsetTime2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.offsetTime_(offsetTime1, offsetTime0).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.offsetTime_(Seq(offsetTime1, offsetTime2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.offsetTime_(Seq(offsetTime1, offsetTime0)).query.get.map(_ ==> List(a))
        // Empty Seq of args matches no values
        _ <- Ns.i.a1.offsetTime_(Seq.empty[OffsetTime]).query.get.map(_ ==> List())

        // Match non-matching values without returning them
        _ <- Ns.i.a1.offsetTime_.not(offsetTime0).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.offsetTime_.not(offsetTime1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.offsetTime_.not(offsetTime2).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.offsetTime_.not(offsetTime3).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.offsetTime_.not(Seq(offsetTime0)).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.offsetTime_.not(Seq(offsetTime1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.offsetTime_.not(Seq(offsetTime2)).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.offsetTime_.not(Seq(offsetTime3)).query.get.map(_ ==> List(a, b))
        // OR semantics for multiple args
        _ <- Ns.i.a1.offsetTime_.not(offsetTime0, offsetTime1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.offsetTime_.not(offsetTime1, offsetTime2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.offsetTime_.not(offsetTime2, offsetTime3).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.offsetTime_.not(Seq(offsetTime0, offsetTime1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.offsetTime_.not(Seq(offsetTime1, offsetTime2)).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.offsetTime_.not(Seq(offsetTime2, offsetTime3)).query.get.map(_ ==> List(a))
        // Empty Seq of negation args matches all asserted values (non-null)
        _ <- Ns.i.a1.offsetTime_.not(Seq.empty[OffsetTime]).query.get.map(_ ==> List(a, b, c))

        // Match value ranges without returning them
        _ <- Ns.i.a1.offsetTime_.<(offsetTime2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.offsetTime_.>(offsetTime2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.offsetTime_.<=(offsetTime2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.offsetTime_.>=(offsetTime2).query.get.map(_ ==> List(b, c))
      } yield ()
    }


    "Optional" - types { implicit conn =>
      val a = (1, Some(offsetTime1))
      val b = (2, Some(offsetTime2))
      val c = (3, Some(offsetTime3))
      val x = (4, Option.empty[OffsetTime])
      for {
        _ <- Ns.i.offsetTime_?.insert(List(a, b, c, x)).transact

        // Find all optional attribute values
        _ <- Ns.i.a1.offsetTime_?.query.get.map(_ ==> List(a, b, c, x))

        // Find optional values matching
        _ <- Ns.i.a1.offsetTime_?(Some(offsetTime0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.offsetTime_?(Some(offsetTime1)).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.offsetTime_?(Some(Seq(offsetTime0))).query.get.map(_ ==> List())
        _ <- Ns.i.a1.offsetTime_?(Some(Seq(offsetTime1))).query.get.map(_ ==> List(a))
        // OR semantics for Ses of multiple args
        _ <- Ns.i.a1.offsetTime_?(Some(Seq(offsetTime1, offsetTime2))).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.offsetTime_?(Some(Seq(offsetTime1, offsetTime0))).query.get.map(_ ==> List(a))
        // Empty Seq of args matches no values
        _ <- Ns.i.a1.offsetTime_?(Some(Seq.empty[OffsetTime])).query.get.map(_ ==> List())
        // None matches non-asserted/null values
        _ <- Ns.i.a1.offsetTime_?(Option.empty[OffsetTime]).query.get.map(_ ==> List(x))
        _ <- Ns.i.a1.offsetTime_?(Option.empty[Seq[OffsetTime]]).query.get.map(_ ==> List(x))

        // Find optional values not matching
        _ <- Ns.i.a1.offsetTime_?.not(Some(offsetTime0)).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.offsetTime_?.not(Some(offsetTime1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.offsetTime_?.not(Some(offsetTime2)).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.offsetTime_?.not(Some(offsetTime3)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.offsetTime_?.not(Some(Seq(offsetTime0))).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.offsetTime_?.not(Some(Seq(offsetTime1))).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.offsetTime_?.not(Some(Seq(offsetTime2))).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.offsetTime_?.not(Some(Seq(offsetTime3))).query.get.map(_ ==> List(a, b))
        // OR semantics for multiple negation args
        _ <- Ns.i.a1.offsetTime_?.not(Some(Seq(offsetTime0, offsetTime1))).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.offsetTime_?.not(Some(Seq(offsetTime1, offsetTime2))).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.offsetTime_?.not(Some(Seq(offsetTime2, offsetTime3))).query.get.map(_ ==> List(a))
        // Empty Seq of negation args matches all asserted values (non-null)
        _ <- Ns.i.a1.offsetTime_?.not(Some(Seq.empty[OffsetTime])).query.get.map(_ ==> List(a, b, c))
        // Negating None matches all asserted values (non-null)
        _ <- Ns.i.a1.offsetTime_?.not(Option.empty[OffsetTime]).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.offsetTime_?.not(Option.empty[Seq[OffsetTime]]).query.get.map(_ ==> List(a, b, c))

        // Find optional values in range
        _ <- Ns.i.a1.offsetTime_?.<(Some(offsetTime2)).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.offsetTime_?.>(Some(offsetTime2)).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.offsetTime_?.<=(Some(offsetTime2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.offsetTime_?.>=(Some(offsetTime2)).query.get.map(_ ==> List(b, c))
        // None can't be compared and returns empty result
        _ <- Ns.i.a1.offsetTime_?.<(None).query.get.map(_ ==> List())
        _ <- Ns.i.a1.offsetTime_?.<=(None).query.get.map(_ ==> List())
        _ <- Ns.i.a1.offsetTime_?.>(None).query.get.map(_ ==> List())
        _ <- Ns.i.a1.offsetTime_?.>=(None).query.get.map(_ ==> List())
      } yield ()
    }
  }
}
