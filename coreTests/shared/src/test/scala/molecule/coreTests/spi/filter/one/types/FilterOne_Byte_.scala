// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.one.types

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.domains.dsl.Types._
import molecule.coreTests.setup._

case class FilterOne_Byte_(
  suite: Test,
  api: Api_async with Spi_async with DbProviders
) extends TestUtils {

  import api._
  import suite._

  "Mandatory" - types { implicit conn =>
    val a = (1, byte1)
    val b = (2, byte2)
    val c = (3, byte3)
    for {
      _ <- Entity.i.byte.insert(List(a, b, c)).transact

      // Find all attribute values
      _ <- Entity.i.a1.byte.query.get.map(_ ==> List(a, b, c))

      // Find value(s) matching
      _ <- Entity.i.a1.byte(byte0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.byte(byte1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.byte(Seq(byte0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.byte(Seq(byte1)).query.get.map(_ ==> List(a))
      // OR semantics for multiple args ("is this or that")
      _ <- Entity.i.a1.byte(byte1, byte2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.byte(byte1, byte0).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.byte(Seq(byte1, byte2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.byte(Seq(byte1, byte0)).query.get.map(_ ==> List(a))
      // Empty Seq of args matches no values
      _ <- Entity.i.a1.byte(Seq.empty[Byte]).query.get.map(_ ==> List())

      // Find values not matching
      _ <- Entity.i.a1.byte.not(byte0).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.byte.not(byte1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.byte.not(byte2).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.byte.not(byte3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.byte.not(Seq(byte0)).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.byte.not(Seq(byte1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.byte.not(Seq(byte2)).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.byte.not(Seq(byte3)).query.get.map(_ ==> List(a, b))
      // OR semantics for multiple args
      _ <- Entity.i.a1.byte.not(byte0, byte1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.byte.not(byte1, byte2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.byte.not(byte2, byte3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.byte.not(Seq(byte0, byte1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.byte.not(Seq(byte1, byte2)).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.byte.not(Seq(byte2, byte3)).query.get.map(_ ==> List(a))
      // Empty Seq of negation args matches all values
      _ <- Entity.i.a1.byte.not(Seq.empty[Byte]).query.get.map(_ ==> List(a, b, c))

      // Find values in range
      _ <- Entity.i.a1.byte.<(byte2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.byte.>(byte2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.byte.<=(byte2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.byte.>=(byte2).query.get.map(_ ==> List(b, c))
    } yield ()
  }


  "Tacit" - types { implicit conn =>
    val (a, b, c, x) = (1, 2, 3, 4)
    for {
      _ <- Entity.i.byte_?.insert(List(
        (a, Some(byte1)),
        (b, Some(byte2)),
        (c, Some(byte3)),
        (x, None)
      )).transact

      // Match asserted attribute without returning its value
      _ <- Entity.i.a1.byte_.query.get.map(_ ==> List(a, b, c))

      // Match non-asserted attribute (null)
      _ <- Entity.i.a1.byte_().query.get.map(_ ==> List(x))

      // Match attribute values without returning them
      _ <- Entity.i.a1.byte_(byte0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.byte_(byte1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.byte_(Seq(byte0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.byte_(Seq(byte1)).query.get.map(_ ==> List(a))
      // OR semantics for multiple args ("is this or that")
      _ <- Entity.i.a1.byte_(byte1, byte2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.byte_(byte1, byte0).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.byte_(Seq(byte1, byte2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.byte_(Seq(byte1, byte0)).query.get.map(_ ==> List(a))
      // Empty Seq of args matches no values
      _ <- Entity.i.a1.byte_(Seq.empty[Byte]).query.get.map(_ ==> List())

      // Match non-matching values without returning them
      _ <- Entity.i.a1.byte_.not(byte0).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.byte_.not(byte1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.byte_.not(byte2).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.byte_.not(byte3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.byte_.not(Seq(byte0)).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.byte_.not(Seq(byte1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.byte_.not(Seq(byte2)).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.byte_.not(Seq(byte3)).query.get.map(_ ==> List(a, b))
      // OR semantics for multiple args
      _ <- Entity.i.a1.byte_.not(byte0, byte1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.byte_.not(byte1, byte2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.byte_.not(byte2, byte3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.byte_.not(Seq(byte0, byte1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.byte_.not(Seq(byte1, byte2)).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.byte_.not(Seq(byte2, byte3)).query.get.map(_ ==> List(a))
      // Empty Seq of negation args matches all asserted values (non-null)
      _ <- Entity.i.a1.byte_.not(Seq.empty[Byte]).query.get.map(_ ==> List(a, b, c))

      // Match value ranges without returning them
      _ <- Entity.i.a1.byte_.<(byte2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.byte_.>(byte2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.byte_.<=(byte2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.byte_.>=(byte2).query.get.map(_ ==> List(b, c))
    } yield ()
  }


  "Optional" - types { implicit conn =>
    val a = (1, Some(byte1))
    val b = (2, Some(byte2))
    val c = (3, Some(byte3))
    val x = (4, Option.empty[Byte])
    for {
      _ <- Entity.i.byte_?.insert(List(a, b, c, x)).transact

      // Find all optional attribute values
      _ <- Entity.i.a1.byte_?.query.get.map(_ ==> List(a, b, c, x))

      // Find optional values matching
      _ <- Entity.i.a1.byte_?(Some(byte0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.byte_?(Some(byte1)).query.get.map(_ ==> List(a))

      // None matches non-asserted/null values
      _ <- Entity.i.a1.byte_?(Option.empty[Byte]).query.get.map(_ ==> List(x))
      // Easier to apply nothing to tacit attribute
      _ <- Entity.i.a1.byte_().query.get.map(_ ==> List(4))
    } yield ()
  }


  "Combinations" - types { implicit conn =>
    for {
      _ <- Entity.i.byte.insert(
        (1, byte1),
        (2, byte2),
        (3, byte3),
        (4, byte4),
        (5, byte5),
        (6, byte6),
        (7, byte7),
        (8, byte8),
        (9, byte9),
      ).transact

      _ <- Entity.i.a1.byte_.>(byte2).query.get.map(_ ==> List(3, 4, 5, 6, 7, 8, 9))
      _ <- Entity.i.a1.byte_.>(byte2).byte_.<=(byte8).query.get.map(_ ==> List(3, 4, 5, 6, 7, 8))
      _ <- Entity.i.a1.byte_.>(byte2).byte_.<=(byte8).byte_.not(byte4, byte5).query.get.map(_ ==> List(3, 6, 7, 8))
    } yield ()
  }
}
