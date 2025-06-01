package molecule.db.compliance.test.bind.types

import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.{DbProviders, Test, TestUtils}
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*

case class Bind_Int(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*


  "Mandatory" - types { implicit conn =>
    for {
      _ <- Entity.int.insert(int1, int2, int3).transact

      eq = Entity.int(?).a1.query
      _ <- eq(int1).get.map(_ ==> List(int1))
      _ <- eq(int2).get.map(_ ==> List(int2))
      _ <- eq(int3).get.map(_ ==> List(int3))

      ne = Entity.int.not(?).a1.query
      _ <- ne(int1).get.map(_ ==> List(int2, int3))
      _ <- ne(int2).get.map(_ ==> List(int1, int3))
      _ <- ne(int3).get.map(_ ==> List(int1, int2))

      lt = Entity.int.<(?).a1.query
      _ <- lt(int1).get.map(_ ==> List())
      _ <- lt(int2).get.map(_ ==> List(int1))
      _ <- lt(int3).get.map(_ ==> List(int1, int2))

      le = Entity.int.<=(?).a1.query
      _ <- le(int1).get.map(_ ==> List(int1))
      _ <- le(int2).get.map(_ ==> List(int1, int2))
      _ <- le(int3).get.map(_ ==> List(int1, int2, int3))

      gt = Entity.int.>(?).a1.query
      _ <- gt(int1).get.map(_ ==> List(int2, int3))
      _ <- gt(int2).get.map(_ ==> List(int3))
      _ <- gt(int3).get.map(_ ==> List())

      ge = Entity.int.>=(?).a1.query
      _ <- ge(int1).get.map(_ ==> List(int1, int2, int3))
      _ <- ge(int2).get.map(_ ==> List(int2, int3))
      _ <- ge(int3).get.map(_ ==> List(int3))
    } yield ()
  }


  "Tacit" - types { implicit conn =>
    for {
      _ <- Entity.i.int.insert((1, int1), (2, int2), (3, int3)).transact

      eq = Entity.i.a1.int_(?).query
      _ <- eq(int1).get.map(_ ==> List(1))
      _ <- eq(int2).get.map(_ ==> List(2))
      _ <- eq(int3).get.map(_ ==> List(3))

      ne = Entity.i.a1.int_.not(?).query
      _ <- ne(int1).get.map(_ ==> List(2, 3))
      _ <- ne(int2).get.map(_ ==> List(1, 3))
      _ <- ne(int3).get.map(_ ==> List(1, 2))

      lt = Entity.i.a1.int_.<(?).query
      _ <- lt(int1).get.map(_ ==> List())
      _ <- lt(int2).get.map(_ ==> List(1))
      _ <- lt(int3).get.map(_ ==> List(1, 2))

      le = Entity.i.a1.int_.<=(?).query
      _ <- le(int1).get.map(_ ==> List(1))
      _ <- le(int2).get.map(_ ==> List(1, 2))
      _ <- le(int3).get.map(_ ==> List(1, 2, 3))

      gt = Entity.i.a1.int_.>(?).query
      _ <- gt(int1).get.map(_ ==> List(2, 3))
      _ <- gt(int2).get.map(_ ==> List(3))
      _ <- gt(int3).get.map(_ ==> List())

      ge = Entity.i.a1.int_.>=(?).query
      _ <- ge(int1).get.map(_ ==> List(1, 2, 3))
      _ <- ge(int2).get.map(_ ==> List(2, 3))
      _ <- ge(int3).get.map(_ ==> List(3))
    } yield ()
  }
}
