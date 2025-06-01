// GENERATED CODE ********************************
package molecule.db.compliance.test.bind.types

import java.util.UUID
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.{DbProviders, Test, TestUtils}
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*

case class Bind_UUID_(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*


  "Mandatory" - types { implicit conn =>
    for {
      _ <- Entity.uuid.insert(uuid1, uuid2, uuid3).transact

      eq = Entity.uuid(?).query
      _ <- eq(uuid1).get.map(_ ==> List(uuid1))
      _ <- eq(uuid2).get.map(_ ==> List(uuid2))
      _ <- eq(uuid3).get.map(_ ==> List(uuid3))

      ne = Entity.uuid.not(?).query
      _ <- ne(uuid1).get.map(_ ==> List(uuid2, uuid3))
      _ <- ne(uuid2).get.map(_ ==> List(uuid1, uuid3))
      _ <- ne(uuid3).get.map(_ ==> List(uuid1, uuid2))

      lt = Entity.uuid.<(?).query
      _ <- lt(uuid1).get.map(_ ==> List())
      _ <- lt(uuid2).get.map(_ ==> List(uuid1))
      _ <- lt(uuid3).get.map(_ ==> List(uuid1, uuid2))

      le = Entity.uuid.<=(?).query
      _ <- le(uuid1).get.map(_ ==> List(uuid1))
      _ <- le(uuid2).get.map(_ ==> List(uuid1, uuid2))
      _ <- le(uuid3).get.map(_ ==> List(uuid1, uuid2, uuid3))

      gt = Entity.uuid.>(?).query
      _ <- gt(uuid1).get.map(_ ==> List(uuid2, uuid3))
      _ <- gt(uuid2).get.map(_ ==> List(uuid3))
      _ <- gt(uuid3).get.map(_ ==> List())

      ge = Entity.uuid.>=(?).query
      _ <- ge(uuid1).get.map(_ ==> List(uuid1, uuid2, uuid3))
      _ <- ge(uuid2).get.map(_ ==> List(uuid2, uuid3))
      _ <- ge(uuid3).get.map(_ ==> List(uuid3))
    } yield ()
  }


  "Tacit" - types { implicit conn =>
    for {
      _ <- Entity.i.uuid.insert((1, uuid1), (2, uuid2), (3, uuid3)).transact

      eq = Entity.i.uuid_(?).query
      _ <- eq(uuid1).get.map(_ ==> List(1))
      _ <- eq(uuid2).get.map(_ ==> List(2))
      _ <- eq(uuid3).get.map(_ ==> List(3))

      ne = Entity.i.uuid_.not(?).query
      _ <- ne(uuid1).get.map(_ ==> List(2, 3))
      _ <- ne(uuid2).get.map(_ ==> List(1, 3))
      _ <- ne(uuid3).get.map(_ ==> List(1, 2))

      lt = Entity.i.uuid_.<(?).query
      _ <- lt(uuid1).get.map(_ ==> List())
      _ <- lt(uuid2).get.map(_ ==> List(1))
      _ <- lt(uuid3).get.map(_ ==> List(1, 2))

      le = Entity.i.uuid_.<=(?).query
      _ <- le(uuid1).get.map(_ ==> List(1))
      _ <- le(uuid2).get.map(_ ==> List(1, 2))
      _ <- le(uuid3).get.map(_ ==> List(1, 2, 3))

      gt = Entity.i.uuid_.>(?).query
      _ <- gt(uuid1).get.map(_ ==> List(2, 3))
      _ <- gt(uuid2).get.map(_ ==> List(3))
      _ <- gt(uuid3).get.map(_ ==> List())

      ge = Entity.i.uuid_.>=(?).query
      _ <- ge(uuid1).get.map(_ ==> List(1, 2, 3))
      _ <- ge(uuid2).get.map(_ ==> List(2, 3))
      _ <- ge(uuid3).get.map(_ ==> List(3))
    } yield ()
  }
}
