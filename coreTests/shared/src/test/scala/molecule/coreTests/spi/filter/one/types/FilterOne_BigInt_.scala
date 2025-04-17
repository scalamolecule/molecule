// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.one.types

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.domains.dsl.Types._
import molecule.coreTests.setup._

case class FilterOne_BigInt_(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api._
  import suite._

  "Mandatory" - types { implicit conn =>
    val a = (1, bigInt1)
    val b = (2, bigInt2)
    val c = (3, bigInt3)
    for {
      _ <- Entity.i.bigInt.insert(List(a, b, c)).transact

      // Find all attribute values
      _ <- Entity.i.a1.bigInt.query.get.map(_ ==> List(a, b, c))

      // Find value(s) matching
      _ <- Entity.i.a1.bigInt(bigInt0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigInt(bigInt1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigInt(Seq(bigInt0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigInt(Seq(bigInt1)).query.get.map(_ ==> List(a))
      // OR semantics for multiple args ("is this or that")
      _ <- Entity.i.a1.bigInt(bigInt1, bigInt2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigInt(bigInt1, bigInt0).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigInt(Seq(bigInt1, bigInt2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigInt(Seq(bigInt1, bigInt0)).query.get.map(_ ==> List(a))
      // Empty Seq of args matches no values
      _ <- Entity.i.a1.bigInt(Seq.empty[BigInt]).query.get.map(_ ==> List())

      // Find values not matching
      _ <- Entity.i.a1.bigInt.not(bigInt0).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.bigInt.not(bigInt1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.bigInt.not(bigInt2).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.bigInt.not(bigInt3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigInt.not(Seq(bigInt0)).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.bigInt.not(Seq(bigInt1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.bigInt.not(Seq(bigInt2)).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.bigInt.not(Seq(bigInt3)).query.get.map(_ ==> List(a, b))
      // OR semantics for multiple args
      _ <- Entity.i.a1.bigInt.not(bigInt0, bigInt1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.bigInt.not(bigInt1, bigInt2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.bigInt.not(bigInt2, bigInt3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigInt.not(Seq(bigInt0, bigInt1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.bigInt.not(Seq(bigInt1, bigInt2)).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.bigInt.not(Seq(bigInt2, bigInt3)).query.get.map(_ ==> List(a))
      // Empty Seq of negation args matches all values
      _ <- Entity.i.a1.bigInt.not(Seq.empty[BigInt]).query.get.map(_ ==> List(a, b, c))

      // Find values in range
      _ <- Entity.i.a1.bigInt.<(bigInt2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigInt.>(bigInt2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.bigInt.<=(bigInt2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigInt.>=(bigInt2).query.get.map(_ ==> List(b, c))
    } yield ()
  }


  "Tacit" - types { implicit conn =>
    val (a, b, c, x) = (1, 2, 3, 4)
    for {
      _ <- Entity.i.bigInt_?.insert(List(
        (a, Some(bigInt1)),
        (b, Some(bigInt2)),
        (c, Some(bigInt3)),
        (x, None)
      )).transact

      // Match asserted attribute without returning its value
      _ <- Entity.i.a1.bigInt_.query.get.map(_ ==> List(a, b, c))

      // Match non-asserted attribute (null)
      _ <- Entity.i.a1.bigInt_().query.get.map(_ ==> List(x))

      // Match attribute values without returning them
      _ <- Entity.i.a1.bigInt_(bigInt0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigInt_(bigInt1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigInt_(Seq(bigInt0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigInt_(Seq(bigInt1)).query.get.map(_ ==> List(a))
      // OR semantics for multiple args ("is this or that")
      _ <- Entity.i.a1.bigInt_(bigInt1, bigInt2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigInt_(bigInt1, bigInt0).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigInt_(Seq(bigInt1, bigInt2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigInt_(Seq(bigInt1, bigInt0)).query.get.map(_ ==> List(a))
      // Empty Seq of args matches no values
      _ <- Entity.i.a1.bigInt_(Seq.empty[BigInt]).query.get.map(_ ==> List())

      // Match non-matching values without returning them
      _ <- Entity.i.a1.bigInt_.not(bigInt0).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.bigInt_.not(bigInt1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.bigInt_.not(bigInt2).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.bigInt_.not(bigInt3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigInt_.not(Seq(bigInt0)).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.bigInt_.not(Seq(bigInt1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.bigInt_.not(Seq(bigInt2)).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.bigInt_.not(Seq(bigInt3)).query.get.map(_ ==> List(a, b))
      // OR semantics for multiple args
      _ <- Entity.i.a1.bigInt_.not(bigInt0, bigInt1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.bigInt_.not(bigInt1, bigInt2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.bigInt_.not(bigInt2, bigInt3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigInt_.not(Seq(bigInt0, bigInt1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.bigInt_.not(Seq(bigInt1, bigInt2)).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.bigInt_.not(Seq(bigInt2, bigInt3)).query.get.map(_ ==> List(a))
      // Empty Seq of negation args matches all asserted values (non-null)
      _ <- Entity.i.a1.bigInt_.not(Seq.empty[BigInt]).query.get.map(_ ==> List(a, b, c))

      // Match value ranges without returning them
      _ <- Entity.i.a1.bigInt_.<(bigInt2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigInt_.>(bigInt2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.bigInt_.<=(bigInt2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigInt_.>=(bigInt2).query.get.map(_ ==> List(b, c))
    } yield ()
  }


  "Optional" - types { implicit conn =>
    val a = (1, Some(bigInt1))
    val b = (2, Some(bigInt2))
    val c = (3, Some(bigInt3))
    val x = (4, Option.empty[BigInt])
    for {
      _ <- Entity.i.bigInt_?.insert(List(a, b, c, x)).transact

      // Find all optional attribute values
      _ <- Entity.i.a1.bigInt_?.query.get.map(_ ==> List(a, b, c, x))

      // Find optional values matching
      _ <- Entity.i.a1.bigInt_?(Some(bigInt0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigInt_?(Some(bigInt1)).query.get.map(_ ==> List(a))

      // None matches non-asserted/null values
      _ <- Entity.i.a1.bigInt_?(Option.empty[BigInt]).query.get.map(_ ==> List(x))
      // Easier to apply nothing to tacit attribute
      _ <- Entity.i.a1.bigInt_().query.get.map(_ ==> List(4))
    } yield ()
  }


  "Combinations" - types { implicit conn =>
    for {
      _ <- Entity.i.bigInt.insert(
        (1, bigInt1),
        (2, bigInt2),
        (3, bigInt3),
        (4, bigInt4),
        (5, bigInt5),
        (6, bigInt6),
        (7, bigInt7),
        (8, bigInt8),
        (9, bigInt9),
      ).transact

      _ <- Entity.i.a1.bigInt_.>(bigInt2).query.get.map(_ ==> List(3, 4, 5, 6, 7, 8, 9))
      _ <- Entity.i.a1.bigInt_.>(bigInt2).bigInt_.<=(bigInt8).query.get.map(_ ==> List(3, 4, 5, 6, 7, 8))
      _ <- Entity.i.a1.bigInt_.>(bigInt2).bigInt_.<=(bigInt8).bigInt_.not(bigInt4, bigInt5).query.get.map(_ ==> List(3, 6, 7, 8))
    } yield ()
  }
}
