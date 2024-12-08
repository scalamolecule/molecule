// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.attrOp.decimal

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AttrOpDecimal_Float_ extends CoreTestSuite with Api_async { spi: Spi_async =>

  override lazy val tests = Tests {
    implicit val tolerance = tolerantFloatEquality(toleranceFloat)

    "plus" - types { implicit conn =>
      for {
        id <- Ns.float(float1).save.transact.map(_.id)
        _ <- Ns(id).float.+(float2).update.transact
        _ <- Ns.float.query.get.map(_.head ==~ float3)
      } yield ()
    }

    "minus" - types { implicit conn =>
      for {
        id <- Ns.float(float3).save.transact.map(_.id)
        _ <- Ns(id).float.-(float2).update.transact
        _ <- Ns.float.query.get.map(_.head ==~ float1)
      } yield ()
    }

    "times" - types { implicit conn =>
      for {
        id <- Ns.float(float2).save.transact.map(_.id)
        _ <- Ns(id).float.*(float2).update.transact
        _ <- Ns.float.query.get.map(_.head ==~ float2 * float2)
      } yield ()
    }

    "divide" - types { implicit conn =>
      for {
        id <- Ns.float(float4).save.transact.map(_.id)
        _ <- Ns(id).float./(float2).update.transact
        _ <- Ns.float.query.get.map(_.head ==~ float4 / float2)
      } yield ()
    }

    "negate" - types { implicit conn =>
      for {
        ids <- Ns.float.insert(-float1, float2).transact.map(_.ids)
        _ <- Ns(ids).float.negate.update.transact
        _ <- Ns.float.d1.query.get.map(_ ==> List(float1, -float2))
      } yield ()
    }

    "absolute" - types { implicit conn =>
      for {
        ids <- Ns.float.insert(-float1, float2).transact.map(_.ids)
        _ <- Ns(ids).float.abs.update.transact
        _ <- Ns.float.a1.query.get.map(_ ==> List(float1, float2))
      } yield ()
    }

    "absolute negative" - types { implicit conn =>
      for {
        ids <- Ns.float.insert(-float1, float2).transact.map(_.ids)
        _ <- Ns(ids).float.absNeg.update.transact
        _ <- Ns.float.d1.query.get.map(_ ==> List(-float1, -float2))
      } yield ()
    }


    // ceil/floor only available for decimal numbers

    "ceil" - types { implicit conn =>
      for {
        id <- Ns.float(float3).save.transact.map(_.id)
        _ <- Ns(id).float.ceil.update.transact
        _ <- Ns.float.query.get.map(_.head ==> int4)
      } yield ()
    }

    "floor" - types { implicit conn =>
      for {
        id <- Ns.float(float3).save.transact.map(_.id)
        _ <- Ns(id).float.floor.update.transact
        _ <- Ns.float.query.get.map(_.head ==> int3)
      } yield ()
    }
  }
}
