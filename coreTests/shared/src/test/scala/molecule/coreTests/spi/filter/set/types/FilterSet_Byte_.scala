// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.set.types

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.domains.dsl.Types._
import molecule.coreTests.setup._

case class FilterSet_Byte_(
  suite: Test,
  api: Api_async with Spi_async with DbProviders
) extends TestUtils {

  val a = (1, Set(byte1, byte2))
  val b = (2, Set(byte2, byte3, byte4))

  import api._
  import suite._


  "Mandatory: has" - types { implicit conn =>
    for {
      _ <- Entity.i.byteSet.insert(a, b).transact

      // Sets with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.byteSet.has(byte0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.byteSet.has(byte1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.byteSet.has(byte2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.byteSet.has(byte3).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.byteSet.has(Seq(byte0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.byteSet.has(Seq(byte1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.byteSet.has(Seq(byte2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.byteSet.has(Seq(byte3)).query.get.map(_ ==> List(b))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.byteSet.has(byte1, byte2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.byteSet.has(byte1, byte3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.byteSet.has(byte2, byte3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.byteSet.has(byte1, byte2, byte3).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.byteSet.has(Seq(byte1, byte2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.byteSet.has(Seq(byte1, byte3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.byteSet.has(Seq(byte2, byte3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.byteSet.has(Seq(byte1, byte2, byte3)).query.get.map(_ ==> List(a, b))


      // Empty Seq/Sets match nothing
      _ <- Entity.i.a1.byteSet.has(Seq.empty[Byte]).query.get.map(_ ==> List())
    } yield ()
  }


  "Mandatory: hasNo" - types { implicit conn =>
    for {
      _ <- Entity.i.byteSet.insert(a, b).transact

      // Sets without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.byteSet.hasNo(byte0).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.byteSet.hasNo(byte1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.byteSet.hasNo(byte2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.byteSet.hasNo(byte3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.byteSet.hasNo(byte4).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.byteSet.hasNo(byte5).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.byteSet.hasNo(Seq(byte0)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.byteSet.hasNo(Seq(byte1)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.byteSet.hasNo(Seq(byte2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.byteSet.hasNo(Seq(byte3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.byteSet.hasNo(Seq(byte4)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.byteSet.hasNo(Seq(byte5)).query.get.map(_ ==> List(a, b))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.byteSet.hasNo(byte1, byte2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.byteSet.hasNo(byte1, byte3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.byteSet.hasNo(byte1, byte4).query.get.map(_ ==> List())
      _ <- Entity.i.a1.byteSet.hasNo(byte1, byte5).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.byteSet.hasNo(Seq(byte1, byte2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.byteSet.hasNo(Seq(byte1, byte3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.byteSet.hasNo(Seq(byte1, byte4)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.byteSet.hasNo(Seq(byte1, byte5)).query.get.map(_ ==> List(b))

      // Negating empty Seqs/Sets has no effect
      _ <- Entity.i.a1.byteSet.hasNo(Seq.empty[Byte]).query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Tacit: has" - types { implicit conn =>
    for {
      _ <- Entity.i.byteSet.insert(a, b).transact

      // Sets with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.byteSet_.has(byte0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.byteSet_.has(byte1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.byteSet_.has(byte2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.byteSet_.has(byte3).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.byteSet_.has(Seq(byte0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.byteSet_.has(Seq(byte1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.byteSet_.has(Seq(byte2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.byteSet_.has(Seq(byte3)).query.get.map(_ ==> List(2))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.byteSet_.has(byte0, byte1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.byteSet_.has(byte1, byte2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.byteSet_.has(byte1, byte3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.byteSet_.has(byte2, byte3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.byteSet_.has(byte3, byte4).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.byteSet_.has(Seq(byte0, byte1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.byteSet_.has(Seq(byte1, byte2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.byteSet_.has(Seq(byte1, byte3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.byteSet_.has(Seq(byte2, byte3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.byteSet_.has(Seq(byte3, byte4)).query.get.map(_ ==> List(2))

      // Empty Seq/Sets match nothing
      _ <- Entity.i.a1.byteSet_.has(Seq.empty[Byte]).query.get.map(_ ==> List())
    } yield ()
  }


  "Tacit: hasNo" - types { implicit conn =>
    for {
      _ <- Entity.i.byteSet.insert(a, b).transact

      // Sets without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.byteSet_.hasNo(byte0).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.byteSet_.hasNo(byte1).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.byteSet_.hasNo(byte2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.byteSet_.hasNo(byte3).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.byteSet_.hasNo(byte4).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.byteSet_.hasNo(byte5).query.get.map(_ ==> List(1, 2))
      // Same as
      _ <- Entity.i.a1.byteSet_.hasNo(Seq(byte0)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.byteSet_.hasNo(Seq(byte1)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.byteSet_.hasNo(Seq(byte2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.byteSet_.hasNo(Seq(byte3)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.byteSet_.hasNo(Seq(byte4)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.byteSet_.hasNo(Seq(byte5)).query.get.map(_ ==> List(1, 2))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.byteSet_.hasNo(byte1, byte2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.byteSet_.hasNo(byte1, byte3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.byteSet_.hasNo(byte1, byte4).query.get.map(_ ==> List())
      _ <- Entity.i.a1.byteSet_.hasNo(byte1, byte5).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.byteSet_.hasNo(Seq(byte1, byte2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.byteSet_.hasNo(Seq(byte1, byte3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.byteSet_.hasNo(Seq(byte1, byte4)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.byteSet_.hasNo(Seq(byte1, byte5)).query.get.map(_ ==> List(2))

      // Negating empty Seqs/Sets has no effect
      _ <- Entity.i.a1.byteSet_.hasNo(Seq.empty[Byte]).query.get.map(_ ==> List(1, 2))
    } yield ()
  }

  // No filtering on optional Set attributes
}