// GENERATED CODE ********************************
package molecule.db.compliance.test.bind.types

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*

case class Bind_Instant_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*


  "Mandatory" - types { implicit conn =>
    for {
      _ <- Entity.instant.insert(instant1, instant2, instant3).transact

      eq = Entity.instant(?).a1.query
      _ <- eq(instant1).get.map(_ ==> List(instant1))
      _ <- eq(instant2).get.map(_ ==> List(instant2))
      _ <- eq(instant3).get.map(_ ==> List(instant3))

      ne = Entity.instant.not(?).a1.query
      _ <- ne(instant1).get.map(_ ==> List(instant2, instant3))
      _ <- ne(instant2).get.map(_ ==> List(instant1, instant3))
      _ <- ne(instant3).get.map(_ ==> List(instant1, instant2))

      lt = Entity.instant.<(?).a1.query
      _ <- lt(instant1).get.map(_ ==> List())
      _ <- lt(instant2).get.map(_ ==> List(instant1))
      _ <- lt(instant3).get.map(_ ==> List(instant1, instant2))

      le = Entity.instant.<=(?).a1.query
      _ <- le(instant1).get.map(_ ==> List(instant1))
      _ <- le(instant2).get.map(_ ==> List(instant1, instant2))
      _ <- le(instant3).get.map(_ ==> List(instant1, instant2, instant3))

      gt = Entity.instant.>(?).a1.query
      _ <- gt(instant1).get.map(_ ==> List(instant2, instant3))
      _ <- gt(instant2).get.map(_ ==> List(instant3))
      _ <- gt(instant3).get.map(_ ==> List())

      ge = Entity.instant.>=(?).a1.query
      _ <- ge(instant1).get.map(_ ==> List(instant1, instant2, instant3))
      _ <- ge(instant2).get.map(_ ==> List(instant2, instant3))
      _ <- ge(instant3).get.map(_ ==> List(instant3))
    } yield ()
  }


  "Tacit" - types { implicit conn =>
    for {
      _ <- Entity.i.instant.insert((1, instant1), (2, instant2), (3, instant3)).transact

      eq = Entity.i.a1.instant_(?).query
      _ <- eq(instant1).get.map(_ ==> List(1))
      _ <- eq(instant2).get.map(_ ==> List(2))
      _ <- eq(instant3).get.map(_ ==> List(3))

      ne = Entity.i.a1.instant_.not(?).query
      _ <- ne(instant1).get.map(_ ==> List(2, 3))
      _ <- ne(instant2).get.map(_ ==> List(1, 3))
      _ <- ne(instant3).get.map(_ ==> List(1, 2))

      lt = Entity.i.a1.instant_.<(?).query
      _ <- lt(instant1).get.map(_ ==> List())
      _ <- lt(instant2).get.map(_ ==> List(1))
      _ <- lt(instant3).get.map(_ ==> List(1, 2))

      le = Entity.i.a1.instant_.<=(?).query
      _ <- le(instant1).get.map(_ ==> List(1))
      _ <- le(instant2).get.map(_ ==> List(1, 2))
      _ <- le(instant3).get.map(_ ==> List(1, 2, 3))

      gt = Entity.i.a1.instant_.>(?).query
      _ <- gt(instant1).get.map(_ ==> List(2, 3))
      _ <- gt(instant2).get.map(_ ==> List(3))
      _ <- gt(instant3).get.map(_ ==> List())

      ge = Entity.i.a1.instant_.>=(?).query
      _ <- ge(instant1).get.map(_ ==> List(1, 2, 3))
      _ <- ge(instant2).get.map(_ ==> List(2, 3))
      _ <- ge(instant3).get.map(_ ==> List(3))
    } yield ()
  }
}
