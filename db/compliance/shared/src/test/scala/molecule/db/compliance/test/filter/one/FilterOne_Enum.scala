package molecule.db.compliance.test.filter.one

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*

case class FilterOne_Enum(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  import Color.*

  "Mandatory" - types { implicit conn =>
    val a = (1, Red.toString)
    val b = (2, Green.toString)
    val c = (3, Blue.toString)
    for {
      //      _ <- Entity.color.insert.apply("Red").transact
      _ <- Entity.i.color.insert.apply(List(a, b, c)).transact

      // Find all attribute values
      _ <- Entity.i.a1.color.query.get.map(_ ==> List(a, b, c))

      // Find value(s) matching
      _ <- Entity.i.a1.color(Yellow).query.get.map(_ ==> List())
      _ <- Entity.i.a1.color(Red).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.color(Seq(Yellow)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.color(Seq(Red)).query.get.map(_ ==> List(a))
      // OR semantics for multiple args ("is this or that")
      _ <- Entity.i.a1.color(Red, Green).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.color(Red, Yellow).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.color(Seq(Red, Green)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.color(Seq(Red, Yellow)).query.get.map(_ ==> List(a))

      // Empty Seq of args matches no values
      _ <- Entity.i.a1.color(Seq.empty[Color]).query.get.map(_ ==> List())

      // Find values not matching
      _ <- Entity.i.a1.color.not(Yellow).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.color.not(Red).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.color.not(Green).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.color.not(Blue).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.color.not(Seq(Yellow)).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.color.not(Seq(Red)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.color.not(Seq(Green)).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.color.not(Seq(Blue)).query.get.map(_ ==> List(a, b))
      // OR semantics for multiple args
      _ <- Entity.i.a1.color.not(Yellow, Red).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.color.not(Red, Green).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.color.not(Green, Blue).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.color.not(Seq(Yellow, Red)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.color.not(Seq(Red, Green)).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.color.not(Seq(Green, Blue)).query.get.map(_ ==> List(a))

      // Empty Seq of negation args matches all values
      _ <- Entity.i.a1.color.not(Seq.empty[Color]).query.get.map(_ ==> List(a, b, c))
    } yield ()
  }


  "Tacit" - types { implicit conn =>
    val (a, b, c, x) = (1, 2, 3, 4)
    for {
      _ <- Entity.i.color_?.insert(List(
        (a, Some(Red.toString)),
        (b, Some(Green.toString)),
        (c, Some(Blue.toString)),
        (x, None)
      )).transact

      // Match asserted attribute without returning its value
      _ <- Entity.i.a1.color_.query.get.map(_ ==> List(a, b, c))

      // Match non-asserted attribute (null)
      _ <- Entity.i.a1.color_().query.get.map(_ ==> List(x))

      // Match attribute values without returning them
      _ <- Entity.i.a1.color_(Yellow).query.get.map(_ ==> List())
      _ <- Entity.i.a1.color_(Red).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.color_(Seq(Yellow)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.color_(Seq(Red)).query.get.map(_ ==> List(a))
      // OR semantics for multiple args ("is this or that")
      _ <- Entity.i.a1.color_(Red, Green).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.color_(Red, Yellow).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.color_(Seq(Red, Green)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.color_(Seq(Red, Yellow)).query.get.map(_ ==> List(a))
      // Empty Seq of args matches no values
      _ <- Entity.i.a1.color_(Seq.empty[Color]).query.get.map(_ ==> List())

      // Match non-matching values without returning them
      _ <- Entity.i.a1.color_.not(Yellow).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.color_.not(Red).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.color_.not(Green).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.color_.not(Blue).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.color_.not(Seq(Yellow)).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.color_.not(Seq(Red)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.color_.not(Seq(Green)).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.color_.not(Seq(Blue)).query.get.map(_ ==> List(a, b))
      // OR semantics for multiple args
      _ <- Entity.i.a1.color_.not(Yellow, Red).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.color_.not(Red, Green).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.color_.not(Green, Blue).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.color_.not(Seq(Yellow, Red)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.color_.not(Seq(Red, Green)).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.color_.not(Seq(Green, Blue)).query.get.map(_ ==> List(a))
      // Empty Seq of negation args matches all asserted values (non-null)
      _ <- Entity.i.a1.color_.not(Seq.empty[Color]).query.get.map(_ ==> List(a, b, c))
    } yield ()
  }


  "Optional" - types { implicit conn =>
    val a = (1, Some(Red.toString))
    val b = (2, Some(Green.toString))
    val c = (3, Some(Blue.toString))
    val x = (4, Option.empty[String])
    for {
      _ <- Entity.i.color_?.insert(List(a, b, c, x)).transact

      // Find all optional attribute values
      _ <- Entity.i.a1.color_?.query.get.map(_ ==> List(a, b, c, x))

      // Find optional values matching
      _ <- Entity.i.a1.color_?(Some(Yellow)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.color_?(Some(Red)).query.get.map(_ ==> List(a))

      // None matches non-asserted/null values
      _ <- Entity.i.a1.color_?(Option.empty[Color]).query.get.map(_ ==> List(x))
      // Easier to apply nothing to tacit attribute
      _ <- Entity.i.a1.color_().query.get.map(_ ==> List(4))
    } yield ()
  }
}
