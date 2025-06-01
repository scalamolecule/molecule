// GENERATED CODE ********************************
package molecule.db.compliance.test.bind.types

import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.{DbProviders, Test, TestUtils}
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*

case class Bind_Long_(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*


  "Mandatory" - types { implicit conn =>
    for {
      _ <- Entity.long.insert(long1, long2, long3).transact

      eq = Entity.long(?).query
      _ <- eq(long1).get.map(_ ==> List(long1))
      _ <- eq(long2).get.map(_ ==> List(long2))
      _ <- eq(long3).get.map(_ ==> List(long3))

      ne = Entity.long.not(?).query
      _ <- ne(long1).get.map(_ ==> List(long2, long3))
      _ <- ne(long2).get.map(_ ==> List(long1, long3))
      _ <- ne(long3).get.map(_ ==> List(long1, long2))

      lt = Entity.long.<(?).query
      _ <- lt(long1).get.map(_ ==> List())
      _ <- lt(long2).get.map(_ ==> List(long1))
      _ <- lt(long3).get.map(_ ==> List(long1, long2))

      le = Entity.long.<=(?).query
      _ <- le(long1).get.map(_ ==> List(long1))
      _ <- le(long2).get.map(_ ==> List(long1, long2))
      _ <- le(long3).get.map(_ ==> List(long1, long2, long3))

      gt = Entity.long.>(?).query
      _ <- gt(long1).get.map(_ ==> List(long2, long3))
      _ <- gt(long2).get.map(_ ==> List(long3))
      _ <- gt(long3).get.map(_ ==> List())

      ge = Entity.long.>=(?).query
      _ <- ge(long1).get.map(_ ==> List(long1, long2, long3))
      _ <- ge(long2).get.map(_ ==> List(long2, long3))
      _ <- ge(long3).get.map(_ ==> List(long3))
    } yield ()
  }


  "Tacit" - types { implicit conn =>
    for {
      _ <- Entity.i.long.insert((1, long1), (2, long2), (3, long3)).transact

      eq = Entity.i.long_(?).query
      _ <- eq(long1).get.map(_ ==> List(1))
      _ <- eq(long2).get.map(_ ==> List(2))
      _ <- eq(long3).get.map(_ ==> List(3))

      ne = Entity.i.long_.not(?).query
      _ <- ne(long1).get.map(_ ==> List(2, 3))
      _ <- ne(long2).get.map(_ ==> List(1, 3))
      _ <- ne(long3).get.map(_ ==> List(1, 2))

      lt = Entity.i.long_.<(?).query
      _ <- lt(long1).get.map(_ ==> List())
      _ <- lt(long2).get.map(_ ==> List(1))
      _ <- lt(long3).get.map(_ ==> List(1, 2))

      le = Entity.i.long_.<=(?).query
      _ <- le(long1).get.map(_ ==> List(1))
      _ <- le(long2).get.map(_ ==> List(1, 2))
      _ <- le(long3).get.map(_ ==> List(1, 2, 3))

      gt = Entity.i.long_.>(?).query
      _ <- gt(long1).get.map(_ ==> List(2, 3))
      _ <- gt(long2).get.map(_ ==> List(3))
      _ <- gt(long3).get.map(_ ==> List())

      ge = Entity.i.long_.>=(?).query
      _ <- ge(long1).get.map(_ ==> List(1, 2, 3))
      _ <- ge(long2).get.map(_ ==> List(2, 3))
      _ <- ge(long3).get.map(_ ==> List(3))
    } yield ()
  }
}
