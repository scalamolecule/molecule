// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.one

import java.util.Date
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterOne_Date_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - types { implicit conn =>
      val a = (1, date1)
      val b = (2, date2)
      val c = (3, date3)
      for {
        _ <- Ns.i.date.insert(List(a, b, c)).transact

        // Find all attribute values
        _ <- Ns.i.a1.date.query.get.map(_ ==> List(a, b, c))

        // Find value(s) matching
        _ <- Ns.i.a1.date(date0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.date(date1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.date(Seq(date0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.date(Seq(date1)).query.get.map(_ ==> List(a))
        // OR semantics for multiple args
        _ <- Ns.i.a1.date(date1, date2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.date(date1, date0).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.date(Seq(date1, date2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.date(Seq(date1, date0)).query.get.map(_ ==> List(a))
        // Empty Seq of args matches no values
        _ <- Ns.i.a1.date(Seq.empty[Date]).query.get.map(_ ==> List())

        // Find values not matching
        _ <- Ns.i.a1.date.not(date0).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.date.not(date1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.date.not(date2).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.date.not(date3).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.date.not(Seq(date0)).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.date.not(Seq(date1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.date.not(Seq(date2)).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.date.not(Seq(date3)).query.get.map(_ ==> List(a, b))
        // OR semantics for multiple args
        _ <- Ns.i.a1.date.not(date0, date1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.date.not(date1, date2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.date.not(date2, date3).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.date.not(Seq(date0, date1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.date.not(Seq(date1, date2)).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.date.not(Seq(date2, date3)).query.get.map(_ ==> List(a))
        // Empty Seq of negation args matches all values
        _ <- Ns.i.a1.date.not(Seq.empty[Date]).query.get.map(_ ==> List(a, b, c))

        // Find values in range
        _ <- Ns.i.a1.date.<(date2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.date.>(date2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.date.<=(date2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.date.>=(date2).query.get.map(_ ==> List(b, c))
      } yield ()
    }


    "Tacit" - types { implicit conn =>
      val (a, b, c, x) = (1, 2, 3, 4)
      for {
        _ <- Ns.i.date_?.insert(List(
          (a, Some(date1)),
          (b, Some(date2)),
          (c, Some(date3)),
          (x, None)
        )).transact

        // Match asserted attribute without returning its value
        _ <- Ns.i.a1.date_.query.get.map(_ ==> List(a, b, c))

        // Match non-asserted attribute (null)
        _ <- Ns.i.a1.date_().query.get.map(_ ==> List(x))

        // Match attribute values without returning them
        _ <- Ns.i.a1.date_(date0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.date_(date1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.date_(Seq(date0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.date_(Seq(date1)).query.get.map(_ ==> List(a))
        // OR semantics for multiple args
        _ <- Ns.i.a1.date_(date1, date2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.date_(date1, date0).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.date_(Seq(date1, date2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.date_(Seq(date1, date0)).query.get.map(_ ==> List(a))
        // Empty Seq of args matches no values
        _ <- Ns.i.a1.date_(Seq.empty[Date]).query.get.map(_ ==> List())

        // Match non-matching values without returning them
        _ <- Ns.i.a1.date_.not(date0).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.date_.not(date1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.date_.not(date2).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.date_.not(date3).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.date_.not(Seq(date0)).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.date_.not(Seq(date1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.date_.not(Seq(date2)).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.date_.not(Seq(date3)).query.get.map(_ ==> List(a, b))
        // OR semantics for multiple args
        _ <- Ns.i.a1.date_.not(date0, date1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.date_.not(date1, date2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.date_.not(date2, date3).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.date_.not(Seq(date0, date1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.date_.not(Seq(date1, date2)).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.date_.not(Seq(date2, date3)).query.get.map(_ ==> List(a))
        // Empty Seq of negation args matches all asserted values (non-null)
        _ <- Ns.i.a1.date_.not(Seq.empty[Date]).query.get.map(_ ==> List(a, b, c))

        // Match value ranges without returning them
        _ <- Ns.i.a1.date_.<(date2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.date_.>(date2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.date_.<=(date2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.date_.>=(date2).query.get.map(_ ==> List(b, c))
      } yield ()
    }


    "Optional" - types { implicit conn =>
      val a = (1, Some(date1))
      val b = (2, Some(date2))
      val c = (3, Some(date3))
      val x = (4, Option.empty[Date])
      for {
        _ <- Ns.i.date_?.insert(List(a, b, c, x)).transact

        // Find all optional attribute values
        _ <- Ns.i.a1.date_?.query.get.map(_ ==> List(a, b, c, x))

        // Find optional values matching
        _ <- Ns.i.a1.date_?(Some(date0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.date_?(Some(date1)).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.date_?(Some(Seq(date0))).query.get.map(_ ==> List())
        _ <- Ns.i.a1.date_?(Some(Seq(date1))).query.get.map(_ ==> List(a))
        // OR semantics for Ses of multiple args
        _ <- Ns.i.a1.date_?(Some(Seq(date1, date2))).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.date_?(Some(Seq(date1, date0))).query.get.map(_ ==> List(a))
        // Empty Seq of args matches no values
        _ <- Ns.i.a1.date_?(Some(Seq.empty[Date])).query.get.map(_ ==> List())
        // None matches non-asserted/null values
        _ <- Ns.i.a1.date_?(Option.empty[Date]).query.get.map(_ ==> List(x))
        _ <- Ns.i.a1.date_?(Option.empty[Seq[Date]]).query.get.map(_ ==> List(x))

        // Find optional values not matching
        _ <- Ns.i.a1.date_?.not(Some(date0)).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.date_?.not(Some(date1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.date_?.not(Some(date2)).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.date_?.not(Some(date3)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.date_?.not(Some(Seq(date0))).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.date_?.not(Some(Seq(date1))).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.date_?.not(Some(Seq(date2))).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.date_?.not(Some(Seq(date3))).query.get.map(_ ==> List(a, b))
        // OR semantics for multiple negation args
        _ <- Ns.i.a1.date_?.not(Some(Seq(date0, date1))).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.date_?.not(Some(Seq(date1, date2))).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.date_?.not(Some(Seq(date2, date3))).query.get.map(_ ==> List(a))
        // Empty Seq of negation args matches all asserted values (non-null)
        _ <- Ns.i.a1.date_?.not(Some(Seq.empty[Date])).query.get.map(_ ==> List(a, b, c))
        // Negating None matches all asserted values (non-null)
        _ <- Ns.i.a1.date_?.not(Option.empty[Date]).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.date_?.not(Option.empty[Seq[Date]]).query.get.map(_ ==> List(a, b, c))

        // Find optional values in range
        _ <- Ns.i.a1.date_?.<(Some(date2)).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.date_?.>(Some(date2)).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.date_?.<=(Some(date2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.date_?.>=(Some(date2)).query.get.map(_ ==> List(b, c))
        // None can't be compared and returns empty result
        _ <- Ns.i.a1.date_?.<(None).query.get.map(_ ==> List())
        _ <- Ns.i.a1.date_?.<=(None).query.get.map(_ ==> List())
        _ <- Ns.i.a1.date_?.>(None).query.get.map(_ ==> List())
        _ <- Ns.i.a1.date_?.>=(None).query.get.map(_ ==> List())
      } yield ()
    }
  }
}
