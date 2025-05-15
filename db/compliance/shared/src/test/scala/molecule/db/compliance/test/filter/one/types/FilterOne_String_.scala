// GENERATED CODE ********************************
package molecule.db.compliance.test.filter.one.types

import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.{DbProviders, Test, TestUtils}
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*

case class FilterOne_String_(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "Mandatory" - types { implicit conn =>
    val a = (1, string1)
    val b = (2, string2)
    val c = (3, string3)
    for {
      _ <- Entity.i.string.insert(List(a, b, c)).transact

      // Find all attribute values
      _ <- Entity.i.a1.string.query.get.map(_ ==> List(a, b, c))

      // Find value(s) matching
      _ <- Entity.i.a1.string(string0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.string(string1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.string(Seq(string0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.string(Seq(string1)).query.get.map(_ ==> List(a))
      // OR semantics for multiple args ("is this or that")
      _ <- Entity.i.a1.string(string1, string2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.string(string1, string0).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.string(Seq(string1, string2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.string(Seq(string1, string0)).query.get.map(_ ==> List(a))
      // Empty Seq of args matches no values
      _ <- Entity.i.a1.string(Seq.empty[String]).query.get.map(_ ==> List())

      // Find values not matching
      _ <- Entity.i.a1.string.not(string0).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.string.not(string1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.string.not(string2).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.string.not(string3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.string.not(Seq(string0)).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.string.not(Seq(string1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.string.not(Seq(string2)).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.string.not(Seq(string3)).query.get.map(_ ==> List(a, b))
      // OR semantics for multiple args
      _ <- Entity.i.a1.string.not(string0, string1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.string.not(string1, string2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.string.not(string2, string3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.string.not(Seq(string0, string1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.string.not(Seq(string1, string2)).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.string.not(Seq(string2, string3)).query.get.map(_ ==> List(a))
      // Empty Seq of negation args matches all values
      _ <- Entity.i.a1.string.not(Seq.empty[String]).query.get.map(_ ==> List(a, b, c))

      // Find values in range
      _ <- Entity.i.a1.string.<(string2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.string.>(string2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.string.<=(string2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.string.>=(string2).query.get.map(_ ==> List(b, c))
    } yield ()
  }


  "Tacit" - types { implicit conn =>
    val (a, b, c, x) = (1, 2, 3, 4)
    for {
      _ <- Entity.i.string_?.insert(List(
        (a, Some(string1)),
        (b, Some(string2)),
        (c, Some(string3)),
        (x, None)
      )).transact

      // Match asserted attribute without returning its value
      _ <- Entity.i.a1.string_.query.get.map(_ ==> List(a, b, c))

      // Match non-asserted attribute (null)
      _ <- Entity.i.a1.string_().query.get.map(_ ==> List(x))

      // Match attribute values without returning them
      _ <- Entity.i.a1.string_(string0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.string_(string1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.string_(Seq(string0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.string_(Seq(string1)).query.get.map(_ ==> List(a))
      // OR semantics for multiple args ("is this or that")
      _ <- Entity.i.a1.string_(string1, string2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.string_(string1, string0).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.string_(Seq(string1, string2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.string_(Seq(string1, string0)).query.get.map(_ ==> List(a))
      // Empty Seq of args matches no values
      _ <- Entity.i.a1.string_(Seq.empty[String]).query.get.map(_ ==> List())

      // Match non-matching values without returning them
      _ <- Entity.i.a1.string_.not(string0).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.string_.not(string1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.string_.not(string2).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.string_.not(string3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.string_.not(Seq(string0)).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.string_.not(Seq(string1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.string_.not(Seq(string2)).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.string_.not(Seq(string3)).query.get.map(_ ==> List(a, b))
      // OR semantics for multiple args
      _ <- Entity.i.a1.string_.not(string0, string1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.string_.not(string1, string2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.string_.not(string2, string3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.string_.not(Seq(string0, string1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.string_.not(Seq(string1, string2)).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.string_.not(Seq(string2, string3)).query.get.map(_ ==> List(a))
      // Empty Seq of negation args matches all asserted values (non-null)
      _ <- Entity.i.a1.string_.not(Seq.empty[String]).query.get.map(_ ==> List(a, b, c))

      // Match value ranges without returning them
      _ <- Entity.i.a1.string_.<(string2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.string_.>(string2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.string_.<=(string2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.string_.>=(string2).query.get.map(_ ==> List(b, c))
    } yield ()
  }


  "Optional" - types { implicit conn =>
    val a = (1, Some(string1))
    val b = (2, Some(string2))
    val c = (3, Some(string3))
    val x = (4, Option.empty[String])
    for {
      _ <- Entity.i.string_?.insert(List(a, b, c, x)).transact

      // Find all optional attribute values
      _ <- Entity.i.a1.string_?.query.get.map(_ ==> List(a, b, c, x))

      // Find optional values matching
      _ <- Entity.i.a1.string_?(Some(string0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.string_?(Some(string1)).query.get.map(_ ==> List(a))

      // None matches non-asserted/null values
      _ <- Entity.i.a1.string_?(Option.empty[String]).query.get.map(_ ==> List(x))
      // Easier to apply nothing to tacit attribute
      _ <- Entity.i.a1.string_().query.get.map(_ ==> List(4))
    } yield ()
  }


  "Combinations" - types { implicit conn =>
    for {
      _ <- Entity.i.string.insert(
        (1, string1),
        (2, string2),
        (3, string3),
        (4, string4),
        (5, string5),
        (6, string6),
        (7, string7),
        (8, string8),
        (9, string9),
      ).transact

      _ <- Entity.i.a1.string_.>(string2).query.get.map(_ ==> List(3, 4, 5, 6, 7, 8, 9))
      _ <- Entity.i.a1.string_.>(string2).string_.<=(string8).query.get.map(_ ==> List(3, 4, 5, 6, 7, 8))
      _ <- Entity.i.a1.string_.>(string2).string_.<=(string8).string_.not(string4, string5).query.get.map(_ ==> List(3, 6, 7, 8))
    } yield ()
  }
}
