// GENERATED CODE ********************************
package molecule.db.compliance.test.bind.types

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders

case class Bind_BigInt_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*


  "Mandatory" - types {
    for {
      _ <- Entity.bigInt.insert(bigInt1, bigInt2, bigInt3).transact

      eq = Entity.bigInt(?).a1.query
      _ <- eq(bigInt1).get.map(_ ==> List(bigInt1))
      _ <- eq(bigInt2).get.map(_ ==> List(bigInt2))
      _ <- eq(bigInt3).get.map(_ ==> List(bigInt3))

      ne = Entity.bigInt.not(?).a1.query
      _ <- ne(bigInt1).get.map(_ ==> List(bigInt2, bigInt3))
      _ <- ne(bigInt2).get.map(_ ==> List(bigInt1, bigInt3))
      _ <- ne(bigInt3).get.map(_ ==> List(bigInt1, bigInt2))

      lt = Entity.bigInt.<(?).a1.query
      _ <- lt(bigInt1).get.map(_ ==> List())
      _ <- lt(bigInt2).get.map(_ ==> List(bigInt1))
      _ <- lt(bigInt3).get.map(_ ==> List(bigInt1, bigInt2))

      le = Entity.bigInt.<=(?).a1.query
      _ <- le(bigInt1).get.map(_ ==> List(bigInt1))
      _ <- le(bigInt2).get.map(_ ==> List(bigInt1, bigInt2))
      _ <- le(bigInt3).get.map(_ ==> List(bigInt1, bigInt2, bigInt3))

      gt = Entity.bigInt.>(?).a1.query
      _ <- gt(bigInt1).get.map(_ ==> List(bigInt2, bigInt3))
      _ <- gt(bigInt2).get.map(_ ==> List(bigInt3))
      _ <- gt(bigInt3).get.map(_ ==> List())

      ge = Entity.bigInt.>=(?).a1.query
      _ <- ge(bigInt1).get.map(_ ==> List(bigInt1, bigInt2, bigInt3))
      _ <- ge(bigInt2).get.map(_ ==> List(bigInt2, bigInt3))
      _ <- ge(bigInt3).get.map(_ ==> List(bigInt3))
    } yield ()
  }


  "Tacit" - types {
    for {
      _ <- Entity.i.bigInt.insert((1, bigInt1), (2, bigInt2), (3, bigInt3)).transact

      eq = Entity.i.a1.bigInt_(?).query
      _ <- eq(bigInt1).get.map(_ ==> List(1))
      _ <- eq(bigInt2).get.map(_ ==> List(2))
      _ <- eq(bigInt3).get.map(_ ==> List(3))

      ne = Entity.i.a1.bigInt_.not(?).query
      _ <- ne(bigInt1).get.map(_ ==> List(2, 3))
      _ <- ne(bigInt2).get.map(_ ==> List(1, 3))
      _ <- ne(bigInt3).get.map(_ ==> List(1, 2))

      lt = Entity.i.a1.bigInt_.<(?).query
      _ <- lt(bigInt1).get.map(_ ==> List())
      _ <- lt(bigInt2).get.map(_ ==> List(1))
      _ <- lt(bigInt3).get.map(_ ==> List(1, 2))

      le = Entity.i.a1.bigInt_.<=(?).query
      _ <- le(bigInt1).get.map(_ ==> List(1))
      _ <- le(bigInt2).get.map(_ ==> List(1, 2))
      _ <- le(bigInt3).get.map(_ ==> List(1, 2, 3))

      gt = Entity.i.a1.bigInt_.>(?).query
      _ <- gt(bigInt1).get.map(_ ==> List(2, 3))
      _ <- gt(bigInt2).get.map(_ ==> List(3))
      _ <- gt(bigInt3).get.map(_ ==> List())

      ge = Entity.i.a1.bigInt_.>=(?).query
      _ <- ge(bigInt1).get.map(_ ==> List(1, 2, 3))
      _ <- ge(bigInt2).get.map(_ ==> List(2, 3))
      _ <- ge(bigInt3).get.map(_ ==> List(3))
    } yield ()
  }
}
