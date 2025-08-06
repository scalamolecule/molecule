// GENERATED CODE ********************************
package molecule.db.compliance.test.bind.types

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders

case class Bind_Duration_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*


  "Mandatory" - types {
    for {
      _ <- Entity.duration.insert(duration1, duration2, duration3).transact

      eq = Entity.duration(?).a1.query
      _ <- eq(duration1).get.map(_ ==> List(duration1))
      _ <- eq(duration2).get.map(_ ==> List(duration2))
      _ <- eq(duration3).get.map(_ ==> List(duration3))

      ne = Entity.duration.not(?).a1.query
      _ <- ne(duration1).get.map(_ ==> List(duration2, duration3))
      _ <- ne(duration2).get.map(_ ==> List(duration1, duration3))
      _ <- ne(duration3).get.map(_ ==> List(duration1, duration2))

      lt = Entity.duration.<(?).a1.query
      _ <- lt(duration1).get.map(_ ==> List())
      _ <- lt(duration2).get.map(_ ==> List(duration1))
      _ <- lt(duration3).get.map(_ ==> List(duration1, duration2))

      le = Entity.duration.<=(?).a1.query
      _ <- le(duration1).get.map(_ ==> List(duration1))
      _ <- le(duration2).get.map(_ ==> List(duration1, duration2))
      _ <- le(duration3).get.map(_ ==> List(duration1, duration2, duration3))

      gt = Entity.duration.>(?).a1.query
      _ <- gt(duration1).get.map(_ ==> List(duration2, duration3))
      _ <- gt(duration2).get.map(_ ==> List(duration3))
      _ <- gt(duration3).get.map(_ ==> List())

      ge = Entity.duration.>=(?).a1.query
      _ <- ge(duration1).get.map(_ ==> List(duration1, duration2, duration3))
      _ <- ge(duration2).get.map(_ ==> List(duration2, duration3))
      _ <- ge(duration3).get.map(_ ==> List(duration3))
    } yield ()
  }


  "Tacit" - types {
    for {
      _ <- Entity.i.duration.insert((1, duration1), (2, duration2), (3, duration3)).transact

      eq = Entity.i.a1.duration_(?).query
      _ <- eq(duration1).get.map(_ ==> List(1))
      _ <- eq(duration2).get.map(_ ==> List(2))
      _ <- eq(duration3).get.map(_ ==> List(3))

      ne = Entity.i.a1.duration_.not(?).query
      _ <- ne(duration1).get.map(_ ==> List(2, 3))
      _ <- ne(duration2).get.map(_ ==> List(1, 3))
      _ <- ne(duration3).get.map(_ ==> List(1, 2))

      lt = Entity.i.a1.duration_.<(?).query
      _ <- lt(duration1).get.map(_ ==> List())
      _ <- lt(duration2).get.map(_ ==> List(1))
      _ <- lt(duration3).get.map(_ ==> List(1, 2))

      le = Entity.i.a1.duration_.<=(?).query
      _ <- le(duration1).get.map(_ ==> List(1))
      _ <- le(duration2).get.map(_ ==> List(1, 2))
      _ <- le(duration3).get.map(_ ==> List(1, 2, 3))

      gt = Entity.i.a1.duration_.>(?).query
      _ <- gt(duration1).get.map(_ ==> List(2, 3))
      _ <- gt(duration2).get.map(_ ==> List(3))
      _ <- gt(duration3).get.map(_ ==> List())

      ge = Entity.i.a1.duration_.>=(?).query
      _ <- ge(duration1).get.map(_ ==> List(1, 2, 3))
      _ <- ge(duration2).get.map(_ ==> List(2, 3))
      _ <- ge(duration3).get.map(_ ==> List(3))
    } yield ()
  }
}
