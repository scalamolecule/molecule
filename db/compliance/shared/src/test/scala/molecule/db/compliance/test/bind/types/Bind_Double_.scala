// GENERATED CODE ********************************
package molecule.db.compliance.test.bind.types

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*

case class Bind_Double_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*


  "Mandatory" - types { implicit conn =>
    for {
      _ <- Entity.double.insert(double1, double2, double3).transact

      eq = Entity.double(?).a1.query
      _ <- eq(double1).get.map(_ ==> List(double1))
      _ <- eq(double2).get.map(_ ==> List(double2))
      _ <- eq(double3).get.map(_ ==> List(double3))

      ne = Entity.double.not(?).a1.query
      _ <- ne(double1).get.map(_ ==> List(double2, double3))
      _ <- ne(double2).get.map(_ ==> List(double1, double3))
      _ <- ne(double3).get.map(_ ==> List(double1, double2))

      lt = Entity.double.<(?).a1.query
      _ <- lt(double1).get.map(_ ==> List())
      _ <- lt(double2).get.map(_ ==> List(double1))
      _ <- lt(double3).get.map(_ ==> List(double1, double2))

      le = Entity.double.<=(?).a1.query
      _ <- le(double1).get.map(_ ==> List(double1))
      _ <- le(double2).get.map(_ ==> List(double1, double2))
      _ <- le(double3).get.map(_ ==> List(double1, double2, double3))

      gt = Entity.double.>(?).a1.query
      _ <- gt(double1).get.map(_ ==> List(double2, double3))
      _ <- gt(double2).get.map(_ ==> List(double3))
      _ <- gt(double3).get.map(_ ==> List())

      ge = Entity.double.>=(?).a1.query
      _ <- ge(double1).get.map(_ ==> List(double1, double2, double3))
      _ <- ge(double2).get.map(_ ==> List(double2, double3))
      _ <- ge(double3).get.map(_ ==> List(double3))
    } yield ()
  }


  "Tacit" - types { implicit conn =>
    for {
      _ <- Entity.i.double.insert((1, double1), (2, double2), (3, double3)).transact

      eq = Entity.i.a1.double_(?).query
      _ <- eq(double1).get.map(_ ==> List(1))
      _ <- eq(double2).get.map(_ ==> List(2))
      _ <- eq(double3).get.map(_ ==> List(3))

      ne = Entity.i.a1.double_.not(?).query
      _ <- ne(double1).get.map(_ ==> List(2, 3))
      _ <- ne(double2).get.map(_ ==> List(1, 3))
      _ <- ne(double3).get.map(_ ==> List(1, 2))

      lt = Entity.i.a1.double_.<(?).query
      _ <- lt(double1).get.map(_ ==> List())
      _ <- lt(double2).get.map(_ ==> List(1))
      _ <- lt(double3).get.map(_ ==> List(1, 2))

      le = Entity.i.a1.double_.<=(?).query
      _ <- le(double1).get.map(_ ==> List(1))
      _ <- le(double2).get.map(_ ==> List(1, 2))
      _ <- le(double3).get.map(_ ==> List(1, 2, 3))

      gt = Entity.i.a1.double_.>(?).query
      _ <- gt(double1).get.map(_ ==> List(2, 3))
      _ <- gt(double2).get.map(_ ==> List(3))
      _ <- gt(double3).get.map(_ ==> List())

      ge = Entity.i.a1.double_.>=(?).query
      _ <- ge(double1).get.map(_ ==> List(1, 2, 3))
      _ <- ge(double2).get.map(_ ==> List(2, 3))
      _ <- ge(double3).get.map(_ ==> List(3))
    } yield ()
  }
}
