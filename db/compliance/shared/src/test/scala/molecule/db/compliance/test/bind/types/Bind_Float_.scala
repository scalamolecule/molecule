// GENERATED CODE ********************************
package molecule.db.compliance.test.bind.types

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*

case class Bind_Float_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*


  "Mandatory" - types { implicit conn =>
    for {
      _ <- Entity.float.insert(float1, float2, float3).transact

      eq = Entity.float(?).a1.query
      _ <- eq(float1).get.map(_ ==> List(float1))
      _ <- eq(float2).get.map(_ ==> List(float2))
      _ <- eq(float3).get.map(_ ==> List(float3))

      ne = Entity.float.not(?).a1.query
      _ <- ne(float1).get.map(_ ==> List(float2, float3))
      _ <- ne(float2).get.map(_ ==> List(float1, float3))
      _ <- ne(float3).get.map(_ ==> List(float1, float2))

      lt = Entity.float.<(?).a1.query
      _ <- lt(float1).get.map(_ ==> List())
      _ <- lt(float2).get.map(_ ==> List(float1))
      _ <- lt(float3).get.map(_ ==> List(float1, float2))

      le = Entity.float.<=(?).a1.query
      _ <- le(float1).get.map(_ ==> List(float1))
      _ <- le(float2).get.map(_ ==> List(float1, float2))
      _ <- le(float3).get.map(_ ==> List(float1, float2, float3))

      gt = Entity.float.>(?).a1.query
      _ <- gt(float1).get.map(_ ==> List(float2, float3))
      _ <- gt(float2).get.map(_ ==> List(float3))
      _ <- gt(float3).get.map(_ ==> List())

      ge = Entity.float.>=(?).a1.query
      _ <- ge(float1).get.map(_ ==> List(float1, float2, float3))
      _ <- ge(float2).get.map(_ ==> List(float2, float3))
      _ <- ge(float3).get.map(_ ==> List(float3))
    } yield ()
  }


  "Tacit" - types { implicit conn =>
    for {
      _ <- Entity.i.float.insert((1, float1), (2, float2), (3, float3)).transact

      eq = Entity.i.a1.float_(?).query
      _ <- eq(float1).get.map(_ ==> List(1))
      _ <- eq(float2).get.map(_ ==> List(2))
      _ <- eq(float3).get.map(_ ==> List(3))

      ne = Entity.i.a1.float_.not(?).query
      _ <- ne(float1).get.map(_ ==> List(2, 3))
      _ <- ne(float2).get.map(_ ==> List(1, 3))
      _ <- ne(float3).get.map(_ ==> List(1, 2))

      lt = Entity.i.a1.float_.<(?).query
      _ <- lt(float1).get.map(_ ==> List())
      _ <- lt(float2).get.map(_ ==> List(1))
      _ <- lt(float3).get.map(_ ==> List(1, 2))

      le = Entity.i.a1.float_.<=(?).query
      _ <- le(float1).get.map(_ ==> List(1))
      _ <- le(float2).get.map(_ ==> List(1, 2))
      _ <- le(float3).get.map(_ ==> List(1, 2, 3))

      gt = Entity.i.a1.float_.>(?).query
      _ <- gt(float1).get.map(_ ==> List(2, 3))
      _ <- gt(float2).get.map(_ ==> List(3))
      _ <- gt(float3).get.map(_ ==> List())

      ge = Entity.i.a1.float_.>=(?).query
      _ <- ge(float1).get.map(_ ==> List(1, 2, 3))
      _ <- ge(float2).get.map(_ ==> List(2, 3))
      _ <- ge(float3).get.map(_ ==> List(3))
    } yield ()
  }
}
