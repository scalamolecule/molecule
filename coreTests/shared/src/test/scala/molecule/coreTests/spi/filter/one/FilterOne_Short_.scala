// GENERATED CODE ********************************
package molecule.coreTests.compliance.filter.one

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterOne_Short_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - types { implicit conn =>
      val a = (1, short1)
      val b = (2, short2)
      val c = (3, short3)
      for {
        _ <- Ns.i.short.insert(List(a, b, c)).transact

        // Find all attribute values
        _ <- Ns.i.a1.short.query.get.map(_ ==> List(a, b, c))

        // Find value(s) matching
        _ <- Ns.i.a1.short(short0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.short(short1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.short(Seq(short0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.short(Seq(short1)).query.get.map(_ ==> List(a))
        // OR semantics for multiple args
        _ <- Ns.i.a1.short(short1, short2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.short(short1, short0).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.short(Seq(short1, short2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.short(Seq(short1, short0)).query.get.map(_ ==> List(a))
        // Empty Seq of args matches no values
        _ <- Ns.i.a1.short(Seq.empty[Short]).query.get.map(_ ==> List())

        // Find values not matching
        _ <- Ns.i.a1.short.not(short0).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.short.not(short1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.short.not(short2).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.short.not(short3).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.short.not(Seq(short0)).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.short.not(Seq(short1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.short.not(Seq(short2)).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.short.not(Seq(short3)).query.get.map(_ ==> List(a, b))
        // OR semantics for multiple args
        _ <- Ns.i.a1.short.not(short0, short1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.short.not(short1, short2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.short.not(short2, short3).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.short.not(Seq(short0, short1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.short.not(Seq(short1, short2)).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.short.not(Seq(short2, short3)).query.get.map(_ ==> List(a))
        // Empty Seq of negation args matches all values
        _ <- Ns.i.a1.short.not(Seq.empty[Short]).query.get.map(_ ==> List(a, b, c))

        // Find values in range
        _ <- Ns.i.a1.short.<(short2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.short.>(short2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.short.<=(short2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.short.>=(short2).query.get.map(_ ==> List(b, c))
      } yield ()
    }


    "Tacit" - types { implicit conn =>
      val (a, b, c, x) = (1, 2, 3, 4)
      for {
        _ <- Ns.i.short_?.insert(List(
          (a, Some(short1)),
          (b, Some(short2)),
          (c, Some(short3)),
          (x, None)
        )).transact

        // Match asserted attribute without returning its value
        _ <- Ns.i.a1.short_.query.get.map(_ ==> List(a, b, c))

        // Match non-asserted attribute (null)
        _ <- Ns.i.a1.short_().query.get.map(_ ==> List(x))

        // Match attribute values without returning them
        _ <- Ns.i.a1.short_(short0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.short_(short1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.short_(Seq(short0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.short_(Seq(short1)).query.get.map(_ ==> List(a))
        // OR semantics for multiple args
        _ <- Ns.i.a1.short_(short1, short2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.short_(short1, short0).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.short_(Seq(short1, short2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.short_(Seq(short1, short0)).query.get.map(_ ==> List(a))
        // Empty Seq of args matches no values
        _ <- Ns.i.a1.short_(Seq.empty[Short]).query.get.map(_ ==> List())

        // Match non-matching values without returning them
        _ <- Ns.i.a1.short_.not(short0).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.short_.not(short1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.short_.not(short2).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.short_.not(short3).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.short_.not(Seq(short0)).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.short_.not(Seq(short1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.short_.not(Seq(short2)).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.short_.not(Seq(short3)).query.get.map(_ ==> List(a, b))
        // OR semantics for multiple args
        _ <- Ns.i.a1.short_.not(short0, short1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.short_.not(short1, short2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.short_.not(short2, short3).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.short_.not(Seq(short0, short1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.short_.not(Seq(short1, short2)).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.short_.not(Seq(short2, short3)).query.get.map(_ ==> List(a))
        // Empty Seq of negation args matches all asserted values (non-null)
        _ <- Ns.i.a1.short_.not(Seq.empty[Short]).query.get.map(_ ==> List(a, b, c))

        // Match value ranges without returning them
        _ <- Ns.i.a1.short_.<(short2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.short_.>(short2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.short_.<=(short2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.short_.>=(short2).query.get.map(_ ==> List(b, c))
      } yield ()
    }


    "Optional" - types { implicit conn =>
      val a = (1, Some(short1))
      val b = (2, Some(short2))
      val c = (3, Some(short3))
      val x = (4, Option.empty[Short])
      for {
        _ <- Ns.i.short_?.insert(List(a, b, c, x)).transact

        // Find all optional attribute values
        _ <- Ns.i.a1.short_?.query.get.map(_ ==> List(a, b, c, x))

        // Find optional values matching
        _ <- Ns.i.a1.short_?(Some(short0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.short_?(Some(short1)).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.short_?(Some(Seq(short0))).query.get.map(_ ==> List())
        _ <- Ns.i.a1.short_?(Some(Seq(short1))).query.get.map(_ ==> List(a))
        // OR semantics for Ses of multiple args
        _ <- Ns.i.a1.short_?(Some(Seq(short1, short2))).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.short_?(Some(Seq(short1, short0))).query.get.map(_ ==> List(a))
        // Empty Seq of args matches no values
        _ <- Ns.i.a1.short_?(Some(Seq.empty[Short])).query.get.map(_ ==> List())
        // None matches non-asserted/null values
        _ <- Ns.i.a1.short_?(Option.empty[Short]).query.get.map(_ ==> List(x))
        _ <- Ns.i.a1.short_?(Option.empty[Seq[Short]]).query.get.map(_ ==> List(x))

        // Find optional values not matching
        _ <- Ns.i.a1.short_?.not(Some(short0)).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.short_?.not(Some(short1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.short_?.not(Some(short2)).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.short_?.not(Some(short3)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.short_?.not(Some(Seq(short0))).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.short_?.not(Some(Seq(short1))).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.short_?.not(Some(Seq(short2))).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.short_?.not(Some(Seq(short3))).query.get.map(_ ==> List(a, b))
        // OR semantics for multiple negation args
        _ <- Ns.i.a1.short_?.not(Some(Seq(short0, short1))).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.short_?.not(Some(Seq(short1, short2))).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.short_?.not(Some(Seq(short2, short3))).query.get.map(_ ==> List(a))
        // Empty Seq of negation args matches all asserted values (non-null)
        _ <- Ns.i.a1.short_?.not(Some(Seq.empty[Short])).query.get.map(_ ==> List(a, b, c))
        // Negating None matches all asserted values (non-null)
        _ <- Ns.i.a1.short_?.not(Option.empty[Short]).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.short_?.not(Option.empty[Seq[Short]]).query.get.map(_ ==> List(a, b, c))

        // Find optional values in range
        _ <- Ns.i.a1.short_?.<(Some(short2)).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.short_?.>(Some(short2)).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.short_?.<=(Some(short2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.short_?.>=(Some(short2)).query.get.map(_ ==> List(b, c))
        // None can't be compared and returns empty result
        _ <- Ns.i.a1.short_?.<(None).query.get.map(_ ==> List())
        _ <- Ns.i.a1.short_?.<=(None).query.get.map(_ ==> List())
        _ <- Ns.i.a1.short_?.>(None).query.get.map(_ ==> List())
        _ <- Ns.i.a1.short_?.>=(None).query.get.map(_ ==> List())
      } yield ()
    }
  }
}
