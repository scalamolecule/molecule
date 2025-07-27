// GENERATED CODE ********************************
package molecule.db.compliance.test.bind.types

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*

case class Bind_BigDecimal_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*


  "Mandatory" - types {
    for {
      _ <- Entity.bigDecimal.insert(bigDecimal1, bigDecimal2, bigDecimal3).transact

      eq = Entity.bigDecimal(?).a1.query
      _ <- eq(bigDecimal1).get.map(_ ==> List(bigDecimal1))
      _ <- eq(bigDecimal2).get.map(_ ==> List(bigDecimal2))
      _ <- eq(bigDecimal3).get.map(_ ==> List(bigDecimal3))

      ne = Entity.bigDecimal.not(?).a1.query
      _ <- ne(bigDecimal1).get.map(_ ==> List(bigDecimal2, bigDecimal3))
      _ <- ne(bigDecimal2).get.map(_ ==> List(bigDecimal1, bigDecimal3))
      _ <- ne(bigDecimal3).get.map(_ ==> List(bigDecimal1, bigDecimal2))

      lt = Entity.bigDecimal.<(?).a1.query
      _ <- lt(bigDecimal1).get.map(_ ==> List())
      _ <- lt(bigDecimal2).get.map(_ ==> List(bigDecimal1))
      _ <- lt(bigDecimal3).get.map(_ ==> List(bigDecimal1, bigDecimal2))

      le = Entity.bigDecimal.<=(?).a1.query
      _ <- le(bigDecimal1).get.map(_ ==> List(bigDecimal1))
      _ <- le(bigDecimal2).get.map(_ ==> List(bigDecimal1, bigDecimal2))
      _ <- le(bigDecimal3).get.map(_ ==> List(bigDecimal1, bigDecimal2, bigDecimal3))

      gt = Entity.bigDecimal.>(?).a1.query
      _ <- gt(bigDecimal1).get.map(_ ==> List(bigDecimal2, bigDecimal3))
      _ <- gt(bigDecimal2).get.map(_ ==> List(bigDecimal3))
      _ <- gt(bigDecimal3).get.map(_ ==> List())

      ge = Entity.bigDecimal.>=(?).a1.query
      _ <- ge(bigDecimal1).get.map(_ ==> List(bigDecimal1, bigDecimal2, bigDecimal3))
      _ <- ge(bigDecimal2).get.map(_ ==> List(bigDecimal2, bigDecimal3))
      _ <- ge(bigDecimal3).get.map(_ ==> List(bigDecimal3))
    } yield ()
  }


  "Tacit" - types {
    for {
      _ <- Entity.i.bigDecimal.insert((1, bigDecimal1), (2, bigDecimal2), (3, bigDecimal3)).transact

      eq = Entity.i.a1.bigDecimal_(?).query
      _ <- eq(bigDecimal1).get.map(_ ==> List(1))
      _ <- eq(bigDecimal2).get.map(_ ==> List(2))
      _ <- eq(bigDecimal3).get.map(_ ==> List(3))

      ne = Entity.i.a1.bigDecimal_.not(?).query
      _ <- ne(bigDecimal1).get.map(_ ==> List(2, 3))
      _ <- ne(bigDecimal2).get.map(_ ==> List(1, 3))
      _ <- ne(bigDecimal3).get.map(_ ==> List(1, 2))

      lt = Entity.i.a1.bigDecimal_.<(?).query
      _ <- lt(bigDecimal1).get.map(_ ==> List())
      _ <- lt(bigDecimal2).get.map(_ ==> List(1))
      _ <- lt(bigDecimal3).get.map(_ ==> List(1, 2))

      le = Entity.i.a1.bigDecimal_.<=(?).query
      _ <- le(bigDecimal1).get.map(_ ==> List(1))
      _ <- le(bigDecimal2).get.map(_ ==> List(1, 2))
      _ <- le(bigDecimal3).get.map(_ ==> List(1, 2, 3))

      gt = Entity.i.a1.bigDecimal_.>(?).query
      _ <- gt(bigDecimal1).get.map(_ ==> List(2, 3))
      _ <- gt(bigDecimal2).get.map(_ ==> List(3))
      _ <- gt(bigDecimal3).get.map(_ ==> List())

      ge = Entity.i.a1.bigDecimal_.>=(?).query
      _ <- ge(bigDecimal1).get.map(_ ==> List(1, 2, 3))
      _ <- ge(bigDecimal2).get.map(_ ==> List(2, 3))
      _ <- ge(bigDecimal3).get.map(_ ==> List(3))
    } yield ()
  }
}
