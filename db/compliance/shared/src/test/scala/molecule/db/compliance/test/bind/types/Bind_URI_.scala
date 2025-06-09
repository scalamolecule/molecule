// GENERATED CODE ********************************
package molecule.db.compliance.test.bind.types

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*

case class Bind_URI_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*


  "Mandatory" - types { implicit conn =>
    for {
      _ <- Entity.uri.insert(uri1, uri2, uri3).transact

      eq = Entity.uri(?).a1.query
      _ <- eq(uri1).get.map(_ ==> List(uri1))
      _ <- eq(uri2).get.map(_ ==> List(uri2))
      _ <- eq(uri3).get.map(_ ==> List(uri3))

      ne = Entity.uri.not(?).a1.query
      _ <- ne(uri1).get.map(_ ==> List(uri2, uri3))
      _ <- ne(uri2).get.map(_ ==> List(uri1, uri3))
      _ <- ne(uri3).get.map(_ ==> List(uri1, uri2))

      lt = Entity.uri.<(?).a1.query
      _ <- lt(uri1).get.map(_ ==> List())
      _ <- lt(uri2).get.map(_ ==> List(uri1))
      _ <- lt(uri3).get.map(_ ==> List(uri1, uri2))

      le = Entity.uri.<=(?).a1.query
      _ <- le(uri1).get.map(_ ==> List(uri1))
      _ <- le(uri2).get.map(_ ==> List(uri1, uri2))
      _ <- le(uri3).get.map(_ ==> List(uri1, uri2, uri3))

      gt = Entity.uri.>(?).a1.query
      _ <- gt(uri1).get.map(_ ==> List(uri2, uri3))
      _ <- gt(uri2).get.map(_ ==> List(uri3))
      _ <- gt(uri3).get.map(_ ==> List())

      ge = Entity.uri.>=(?).a1.query
      _ <- ge(uri1).get.map(_ ==> List(uri1, uri2, uri3))
      _ <- ge(uri2).get.map(_ ==> List(uri2, uri3))
      _ <- ge(uri3).get.map(_ ==> List(uri3))
    } yield ()
  }


  "Tacit" - types { implicit conn =>
    for {
      _ <- Entity.i.uri.insert((1, uri1), (2, uri2), (3, uri3)).transact

      eq = Entity.i.a1.uri_(?).query
      _ <- eq(uri1).get.map(_ ==> List(1))
      _ <- eq(uri2).get.map(_ ==> List(2))
      _ <- eq(uri3).get.map(_ ==> List(3))

      ne = Entity.i.a1.uri_.not(?).query
      _ <- ne(uri1).get.map(_ ==> List(2, 3))
      _ <- ne(uri2).get.map(_ ==> List(1, 3))
      _ <- ne(uri3).get.map(_ ==> List(1, 2))

      lt = Entity.i.a1.uri_.<(?).query
      _ <- lt(uri1).get.map(_ ==> List())
      _ <- lt(uri2).get.map(_ ==> List(1))
      _ <- lt(uri3).get.map(_ ==> List(1, 2))

      le = Entity.i.a1.uri_.<=(?).query
      _ <- le(uri1).get.map(_ ==> List(1))
      _ <- le(uri2).get.map(_ ==> List(1, 2))
      _ <- le(uri3).get.map(_ ==> List(1, 2, 3))

      gt = Entity.i.a1.uri_.>(?).query
      _ <- gt(uri1).get.map(_ ==> List(2, 3))
      _ <- gt(uri2).get.map(_ ==> List(3))
      _ <- gt(uri3).get.map(_ ==> List())

      ge = Entity.i.a1.uri_.>=(?).query
      _ <- ge(uri1).get.map(_ ==> List(1, 2, 3))
      _ <- ge(uri2).get.map(_ ==> List(2, 3))
      _ <- ge(uri3).get.map(_ ==> List(3))
    } yield ()
  }
}
