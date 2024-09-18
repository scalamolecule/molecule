// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.one.types

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterOne_String_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - types { implicit conn =>
      val a = (1, string1)
      val b = (2, string2)
      val c = (3, string3)
      for {
        _ <- Ns.i.string.insert(List(a, b, c)).transact

        // Find all attribute values
        _ <- Ns.i.a1.string.query.get.map(_ ==> List(a, b, c))

        // Find value(s) matching
        _ <- Ns.i.a1.string(string0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.string(string1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.string(Seq(string0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.string(Seq(string1)).query.get.map(_ ==> List(a))
        // OR semantics for multiple args ("is this or that")
        _ <- Ns.i.a1.string(string1, string2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.string(string1, string0).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.string(Seq(string1, string2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.string(Seq(string1, string0)).query.get.map(_ ==> List(a))
        // Empty Seq of args matches no values
        _ <- Ns.i.a1.string(Seq.empty[String]).query.get.map(_ ==> List())

        // Find values not matching
        _ <- Ns.i.a1.string.not(string0).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.string.not(string1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.string.not(string2).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.string.not(string3).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.string.not(Seq(string0)).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.string.not(Seq(string1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.string.not(Seq(string2)).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.string.not(Seq(string3)).query.get.map(_ ==> List(a, b))
        // OR semantics for multiple args
        _ <- Ns.i.a1.string.not(string0, string1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.string.not(string1, string2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.string.not(string2, string3).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.string.not(Seq(string0, string1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.string.not(Seq(string1, string2)).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.string.not(Seq(string2, string3)).query.get.map(_ ==> List(a))
        // Empty Seq of negation args matches all values
        _ <- Ns.i.a1.string.not(Seq.empty[String]).query.get.map(_ ==> List(a, b, c))

        // Find values in range
        _ <- Ns.i.a1.string.<(string2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.string.>(string2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.string.<=(string2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.string.>=(string2).query.get.map(_ ==> List(b, c))
      } yield ()
    }


    "Tacit" - types { implicit conn =>
      val (a, b, c, x) = (1, 2, 3, 4)
      for {
        _ <- Ns.i.string_?.insert(List(
          (a, Some(string1)),
          (b, Some(string2)),
          (c, Some(string3)),
          (x, None)
        )).transact

        // Match asserted attribute without returning its value
        _ <- Ns.i.a1.string_.query.get.map(_ ==> List(a, b, c))

        // Match non-asserted attribute (null)
        _ <- Ns.i.a1.string_().query.get.map(_ ==> List(x))

        // Match attribute values without returning them
        _ <- Ns.i.a1.string_(string0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.string_(string1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.string_(Seq(string0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.string_(Seq(string1)).query.get.map(_ ==> List(a))
        // OR semantics for multiple args ("is this or that")
        _ <- Ns.i.a1.string_(string1, string2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.string_(string1, string0).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.string_(Seq(string1, string2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.string_(Seq(string1, string0)).query.get.map(_ ==> List(a))
        // Empty Seq of args matches no values
        _ <- Ns.i.a1.string_(Seq.empty[String]).query.get.map(_ ==> List())

        // Match non-matching values without returning them
        _ <- Ns.i.a1.string_.not(string0).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.string_.not(string1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.string_.not(string2).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.string_.not(string3).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.string_.not(Seq(string0)).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.string_.not(Seq(string1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.string_.not(Seq(string2)).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.string_.not(Seq(string3)).query.get.map(_ ==> List(a, b))
        // OR semantics for multiple args
        _ <- Ns.i.a1.string_.not(string0, string1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.string_.not(string1, string2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.string_.not(string2, string3).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.string_.not(Seq(string0, string1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.string_.not(Seq(string1, string2)).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.string_.not(Seq(string2, string3)).query.get.map(_ ==> List(a))
        // Empty Seq of negation args matches all asserted values (non-null)
        _ <- Ns.i.a1.string_.not(Seq.empty[String]).query.get.map(_ ==> List(a, b, c))

        // Match value ranges without returning them
        _ <- Ns.i.a1.string_.<(string2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.string_.>(string2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.string_.<=(string2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.string_.>=(string2).query.get.map(_ ==> List(b, c))
      } yield ()
    }


    "Optional" - types { implicit conn =>
      val a = (1, Some(string1))
      val b = (2, Some(string2))
      val c = (3, Some(string3))
      val x = (4, Option.empty[String])
      for {
        _ <- Ns.i.string_?.insert(List(a, b, c, x)).transact

        // Find all optional attribute values
        _ <- Ns.i.a1.string_?.query.get.map(_ ==> List(a, b, c, x))

        // Find optional values matching
        _ <- Ns.i.a1.string_?(Some(string0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.string_?(Some(string1)).query.get.map(_ ==> List(a))

        // None matches non-asserted/null values
        _ <- Ns.i.a1.string_?(Option.empty[String]).query.get.map(_ ==> List(x))
        // Easier to apply nothing to tacit attribute
        _ <- Ns.i.a1.string_().query.get.map(_ ==> List(4))
      } yield ()
    }
  }
}
