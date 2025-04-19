// GENERATED CODE ********************************
package molecule.coreTests.spi.action.update.attrOp.decimal

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor.*
import molecule.coreTests.domains.dsl.Types.*
import molecule.coreTests.setup.*
import org.scalactic.Equality

case class AttrOpDecimal_Float_(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  implicit val tolerance: Equality[Float] = tolerantFloatEquality(toleranceFloat)

  "plus" - types { implicit conn =>
    for {
      id <- Entity.float(float1).save.transact.map(_.id)
      _ <- Entity(id).float.+(float2).update.transact
      _ <- Entity.float.query.get.map(_.head ==~ float3)
    } yield ()
  }

  "minus" - types { implicit conn =>
    for {
      id <- Entity.float(float3).save.transact.map(_.id)
      _ <- Entity(id).float.-(float2).update.transact
      _ <- Entity.float.query.get.map(_.head ==~ float1)
    } yield ()
  }

  "times" - types { implicit conn =>
    for {
      id <- Entity.float(float2).save.transact.map(_.id)
      _ <- Entity(id).float.*(float2).update.transact
      _ <- Entity.float.query.get.map(_.head ==~ float2 * float2)
    } yield ()
  }

  "divide" - types { implicit conn =>
    for {
      id <- Entity.float(float4).save.transact.map(_.id)
      _ <- Entity(id).float./(float2).update.transact
      _ <- Entity.float.query.get.map(_.head ==~ float4 / float2)
    } yield ()
  }

  "negate" - types { implicit conn =>
    for {
      ids <- Entity.float.insert(-float1, float2).transact.map(_.ids)
      _ <- Entity(ids).float.negate.update.transact
      _ <- Entity.float.d1.query.get.map(_ ==> List(float1, -float2))
    } yield ()
  }

  "absolute" - types { implicit conn =>
    for {
      ids <- Entity.float.insert(-float1, float2).transact.map(_.ids)
      _ <- Entity(ids).float.abs.update.transact
      _ <- Entity.float.a1.query.get.map(_ ==> List(float1, float2))
    } yield ()
  }

  "absolute negative" - types { implicit conn =>
    for {
      ids <- Entity.float.insert(-float1, float2).transact.map(_.ids)
      _ <- Entity(ids).float.absNeg.update.transact
      _ <- Entity.float.d1.query.get.map(_ ==> List(-float1, -float2))
    } yield ()
  }


  // ceil/floor only available for decimal numbers

  "ceil" - types { implicit conn =>
    for {
      id <- Entity.float(float3).save.transact.map(_.id)
      _ <- Entity(id).float.ceil.update.transact
      _ <- Entity.float.query.get.map(_.head ==> int4)
    } yield ()
  }

  "floor" - types { implicit conn =>
    for {
      id <- Entity.float(float3).save.transact.map(_.id)
      _ <- Entity(id).float.floor.update.transact
      _ <- Entity.float.query.get.map(_.head ==> int3)
    } yield ()
  }
}
