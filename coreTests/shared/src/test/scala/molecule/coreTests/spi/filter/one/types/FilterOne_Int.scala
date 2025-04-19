package molecule.coreTests.spi.filter.one.types

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor.*
import molecule.coreTests.domains.dsl.Types.*
import molecule.coreTests.setup.*

case class FilterOne_Int(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "Mandatory" - types { implicit conn =>
    val a = (1, int1)
    val b = (2, int2)
    val c = (3, int3)
    for {
      _ <- Entity.i.int.insert(List(a, b, c)).transact

      // Find all attribute values
      _ <- Entity.i.a1.int.query.get.map(_ ==> List(a, b, c))

      // Find value(s) matching
      _ <- Entity.i.a1.int(int0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.int(int1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.int(Seq(int0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.int(Seq(int1)).query.get.map(_ ==> List(a))
      // OR semantics for multiple args ("is this or that")
      _ <- Entity.i.a1.int(int1, int2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.int(int1, int0).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.int(Seq(int1, int2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.int(Seq(int1, int0)).query.get.map(_ ==> List(a))
      // Empty Seq of args matches no values
      _ <- Entity.i.a1.int(Seq.empty[Int]).query.get.map(_ ==> List())

      // Find values not matching
      _ <- Entity.i.a1.int.not(int0).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.int.not(int1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.int.not(int2).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.int.not(int3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.int.not(Seq(int0)).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.int.not(Seq(int1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.int.not(Seq(int2)).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.int.not(Seq(int3)).query.get.map(_ ==> List(a, b))
      // OR semantics for multiple args
      _ <- Entity.i.a1.int.not(int0, int1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.int.not(int1, int2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.int.not(int2, int3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.int.not(Seq(int0, int1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.int.not(Seq(int1, int2)).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.int.not(Seq(int2, int3)).query.get.map(_ ==> List(a))
      // Empty Seq of negation args matches all values
      _ <- Entity.i.a1.int.not(Seq.empty[Int]).query.get.map(_ ==> List(a, b, c))

      // Find values in range
      _ <- Entity.i.a1.int.<(int2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.int.>(int2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.int.<=(int2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.int.>=(int2).query.get.map(_ ==> List(b, c))
    } yield ()
  }


  "Tacit" - types { implicit conn =>
    val (a, b, c, x) = (1, 2, 3, 4)
    for {
      _ <- Entity.i.int_?.insert(List(
        (a, Some(int1)),
        (b, Some(int2)),
        (c, Some(int3)),
        (x, None)
      )).transact

      // Match asserted attribute without returning its value
      _ <- Entity.i.a1.int_.query.get.map(_ ==> List(a, b, c))

      // Match non-asserted attribute (null)
      _ <- Entity.i.a1.int_().query.get.map(_ ==> List(x))

      // Match attribute values without returning them
      _ <- Entity.i.a1.int_(int0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.int_(int1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.int_(Seq(int0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.int_(Seq(int1)).query.get.map(_ ==> List(a))
      // OR semantics for multiple args ("is this or that")
      _ <- Entity.i.a1.int_(int1, int2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.int_(int1, int0).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.int_(Seq(int1, int2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.int_(Seq(int1, int0)).query.get.map(_ ==> List(a))
      // Empty Seq of args matches no values
      _ <- Entity.i.a1.int_(Seq.empty[Int]).query.get.map(_ ==> List())

      // Match non-matching values without returning them
      _ <- Entity.i.a1.int_.not(int0).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.int_.not(int1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.int_.not(int2).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.int_.not(int3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.int_.not(Seq(int0)).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.int_.not(Seq(int1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.int_.not(Seq(int2)).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.int_.not(Seq(int3)).query.get.map(_ ==> List(a, b))
      // OR semantics for multiple args
      _ <- Entity.i.a1.int_.not(int0, int1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.int_.not(int1, int2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.int_.not(int2, int3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.int_.not(Seq(int0, int1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.int_.not(Seq(int1, int2)).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.int_.not(Seq(int2, int3)).query.get.map(_ ==> List(a))
      // Empty Seq of negation args matches all asserted values (non-null)
      _ <- Entity.i.a1.int_.not(Seq.empty[Int]).query.get.map(_ ==> List(a, b, c))

      // Match value ranges without returning them
      _ <- Entity.i.a1.int_.<(int2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.int_.>(int2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.int_.<=(int2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.int_.>=(int2).query.get.map(_ ==> List(b, c))
    } yield ()
  }


  "Optional" - types { implicit conn =>
    val a = (1, Some(int1))
    val b = (2, Some(int2))
    val c = (3, Some(int3))
    val x = (4, Option.empty[Int])
    for {
      _ <- Entity.i.int_?.insert(List(a, b, c, x)).transact

      // Find all optional attribute values
      _ <- Entity.i.a1.int_?.query.get.map(_ ==> List(a, b, c, x))

      // Find optional values matching
      _ <- Entity.i.a1.int_?(Some(int0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.int_?(Some(int1)).query.get.map(_ ==> List(a))

      // None matches non-asserted/null values
      _ <- Entity.i.a1.int_?(Option.empty[Int]).query.get.map(_ ==> List(x))
      // Easier to apply nothing to tacit attribute
      _ <- Entity.i.a1.int_().query.get.map(_ ==> List(4))
    } yield ()
  }


  "Combinations" - types { implicit conn =>
    for {
      _ <- Entity.i.int.insert(
        (1, int1),
        (2, int2),
        (3, int3),
        (4, int4),
        (5, int5),
        (6, int6),
        (7, int7),
        (8, int8),
        (9, int9),
      ).transact

      _ <- Entity.i.a1.int_.>(int2).query.get.map(_ ==> List(3, 4, 5, 6, 7, 8, 9))
      _ <- Entity.i.a1.int_.>(int2).int_.<=(int8).query.get.map(_ ==> List(3, 4, 5, 6, 7, 8))
      _ <- Entity.i.a1.int_.>(int2).int_.<=(int8).int_.not(int4, int5).query.get.map(_ ==> List(3, 6, 7, 8))
    } yield ()
  }
}
