// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.one.types

import java.time.LocalDate
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterOne_LocalDate_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - types { implicit conn =>
      val a = (1, localDate1)
      val b = (2, localDate2)
      val c = (3, localDate3)
      for {
        _ <- Ns.i.localDate.insert(List(a, b, c)).transact

        // Find all attribute values
        _ <- Ns.i.a1.localDate.query.get.map(_ ==> List(a, b, c))

        // Find value(s) matching
        _ <- Ns.i.a1.localDate(localDate0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.localDate(localDate1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.localDate(Seq(localDate0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.localDate(Seq(localDate1)).query.get.map(_ ==> List(a))
        // OR semantics for multiple args ("is this or that")
        _ <- Ns.i.a1.localDate(localDate1, localDate2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.localDate(localDate1, localDate0).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.localDate(Seq(localDate1, localDate2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.localDate(Seq(localDate1, localDate0)).query.get.map(_ ==> List(a))
        // Empty Seq of args matches no values
        _ <- Ns.i.a1.localDate(Seq.empty[LocalDate]).query.get.map(_ ==> List())

        // Find values not matching
        _ <- Ns.i.a1.localDate.not(localDate0).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.localDate.not(localDate1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.localDate.not(localDate2).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.localDate.not(localDate3).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.localDate.not(Seq(localDate0)).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.localDate.not(Seq(localDate1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.localDate.not(Seq(localDate2)).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.localDate.not(Seq(localDate3)).query.get.map(_ ==> List(a, b))
        // OR semantics for multiple args
        _ <- Ns.i.a1.localDate.not(localDate0, localDate1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.localDate.not(localDate1, localDate2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.localDate.not(localDate2, localDate3).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.localDate.not(Seq(localDate0, localDate1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.localDate.not(Seq(localDate1, localDate2)).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.localDate.not(Seq(localDate2, localDate3)).query.get.map(_ ==> List(a))
        // Empty Seq of negation args matches all values
        _ <- Ns.i.a1.localDate.not(Seq.empty[LocalDate]).query.get.map(_ ==> List(a, b, c))

        // Find values in range
        _ <- Ns.i.a1.localDate.<(localDate2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.localDate.>(localDate2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.localDate.<=(localDate2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.localDate.>=(localDate2).query.get.map(_ ==> List(b, c))
      } yield ()
    }


    "Tacit" - types { implicit conn =>
      val (a, b, c, x) = (1, 2, 3, 4)
      for {
        _ <- Ns.i.localDate_?.insert(List(
          (a, Some(localDate1)),
          (b, Some(localDate2)),
          (c, Some(localDate3)),
          (x, None)
        )).transact

        // Match asserted attribute without returning its value
        _ <- Ns.i.a1.localDate_.query.get.map(_ ==> List(a, b, c))

        // Match non-asserted attribute (null)
        _ <- Ns.i.a1.localDate_().query.get.map(_ ==> List(x))

        // Match attribute values without returning them
        _ <- Ns.i.a1.localDate_(localDate0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.localDate_(localDate1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.localDate_(Seq(localDate0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.localDate_(Seq(localDate1)).query.get.map(_ ==> List(a))
        // OR semantics for multiple args ("is this or that")
        _ <- Ns.i.a1.localDate_(localDate1, localDate2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.localDate_(localDate1, localDate0).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.localDate_(Seq(localDate1, localDate2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.localDate_(Seq(localDate1, localDate0)).query.get.map(_ ==> List(a))
        // Empty Seq of args matches no values
        _ <- Ns.i.a1.localDate_(Seq.empty[LocalDate]).query.get.map(_ ==> List())

        // Match non-matching values without returning them
        _ <- Ns.i.a1.localDate_.not(localDate0).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.localDate_.not(localDate1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.localDate_.not(localDate2).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.localDate_.not(localDate3).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.localDate_.not(Seq(localDate0)).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.localDate_.not(Seq(localDate1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.localDate_.not(Seq(localDate2)).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.localDate_.not(Seq(localDate3)).query.get.map(_ ==> List(a, b))
        // OR semantics for multiple args
        _ <- Ns.i.a1.localDate_.not(localDate0, localDate1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.localDate_.not(localDate1, localDate2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.localDate_.not(localDate2, localDate3).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.localDate_.not(Seq(localDate0, localDate1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.localDate_.not(Seq(localDate1, localDate2)).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.localDate_.not(Seq(localDate2, localDate3)).query.get.map(_ ==> List(a))
        // Empty Seq of negation args matches all asserted values (non-null)
        _ <- Ns.i.a1.localDate_.not(Seq.empty[LocalDate]).query.get.map(_ ==> List(a, b, c))

        // Match value ranges without returning them
        _ <- Ns.i.a1.localDate_.<(localDate2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.localDate_.>(localDate2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.localDate_.<=(localDate2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.localDate_.>=(localDate2).query.get.map(_ ==> List(b, c))
      } yield ()
    }


    "Optional" - types { implicit conn =>
      val a = (1, Some(localDate1))
      val b = (2, Some(localDate2))
      val c = (3, Some(localDate3))
      val x = (4, Option.empty[LocalDate])
      for {
        _ <- Ns.i.localDate_?.insert(List(a, b, c, x)).transact

        // Find all optional attribute values
        _ <- Ns.i.a1.localDate_?.query.get.map(_ ==> List(a, b, c, x))

        // Find optional values matching
        _ <- Ns.i.a1.localDate_?(Some(localDate0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.localDate_?(Some(localDate1)).query.get.map(_ ==> List(a))

        // None matches non-asserted/null values
        _ <- Ns.i.a1.localDate_?(Option.empty[LocalDate]).query.get.map(_ ==> List(x))
        // Easier to apply nothing to tacit attribute
        _ <- Ns.i.a1.localDate_().query.get.map(_ ==> List(4))
      } yield ()
    }
  }
}
