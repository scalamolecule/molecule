// GENERATED CODE ********************************
package molecule.db.compliance.test.bind.types

import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.{DbProviders, Test, TestUtils}
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*

case class Bind_BigInt_(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*


  "Mandatory" - types { implicit conn =>
    for {
      _ <- Entity.bigInt.insert(bigInt1, bigInt2, bigInt3).transact

      eq = Entity.bigInt(?).query
      _ <- eq(bigInt1).get.map(_ ==> List(bigInt1))
      _ <- eq(bigInt2).get.map(_ ==> List(bigInt2))
      _ <- eq(bigInt3).get.map(_ ==> List(bigInt3))

      ne = Entity.bigInt.not(?).query
      _ <- ne(bigInt1).get.map(_ ==> List(bigInt2, bigInt3))
      _ <- ne(bigInt2).get.map(_ ==> List(bigInt1, bigInt3))
      _ <- ne(bigInt3).get.map(_ ==> List(bigInt1, bigInt2))

      lt = Entity.bigInt.<(?).query
      _ <- lt(bigInt1).get.map(_ ==> List())
      _ <- lt(bigInt2).get.map(_ ==> List(bigInt1))
      _ <- lt(bigInt3).get.map(_ ==> List(bigInt1, bigInt2))

      le = Entity.bigInt.<=(?).query
      _ <- le(bigInt1).get.map(_ ==> List(bigInt1))
      _ <- le(bigInt2).get.map(_ ==> List(bigInt1, bigInt2))
      _ <- le(bigInt3).get.map(_ ==> List(bigInt1, bigInt2, bigInt3))

      gt = Entity.bigInt.>(?).query
      _ <- gt(bigInt1).get.map(_ ==> List(bigInt2, bigInt3))
      _ <- gt(bigInt2).get.map(_ ==> List(bigInt3))
      _ <- gt(bigInt3).get.map(_ ==> List())

      ge = Entity.bigInt.>=(?).query
      _ <- ge(bigInt1).get.map(_ ==> List(bigInt1, bigInt2, bigInt3))
      _ <- ge(bigInt2).get.map(_ ==> List(bigInt2, bigInt3))
      _ <- ge(bigInt3).get.map(_ ==> List(bigInt3))
    } yield ()
  }


  "Tacit" - types { implicit conn =>
    for {
      _ <- Entity.i.bigInt.insert((1, bigInt1), (2, bigInt2), (3, bigInt3)).transact

      eq = Entity.i.bigInt_(?).query
      _ <- eq(bigInt1).get.map(_ ==> List(1))
      _ <- eq(bigInt2).get.map(_ ==> List(2))
      _ <- eq(bigInt3).get.map(_ ==> List(3))

      ne = Entity.i.bigInt_.not(?).query
      _ <- ne(bigInt1).get.map(_ ==> List(2, 3))
      _ <- ne(bigInt2).get.map(_ ==> List(1, 3))
      _ <- ne(bigInt3).get.map(_ ==> List(1, 2))

      lt = Entity.i.bigInt_.<(?).query
      _ <- lt(bigInt1).get.map(_ ==> List())
      _ <- lt(bigInt2).get.map(_ ==> List(1))
      _ <- lt(bigInt3).get.map(_ ==> List(1, 2))

      le = Entity.i.bigInt_.<=(?).query
      _ <- le(bigInt1).get.map(_ ==> List(1))
      _ <- le(bigInt2).get.map(_ ==> List(1, 2))
      _ <- le(bigInt3).get.map(_ ==> List(1, 2, 3))

      gt = Entity.i.bigInt_.>(?).query
      _ <- gt(bigInt1).get.map(_ ==> List(2, 3))
      _ <- gt(bigInt2).get.map(_ ==> List(3))
      _ <- gt(bigInt3).get.map(_ ==> List())

      ge = Entity.i.bigInt_.>=(?).query
      _ <- ge(bigInt1).get.map(_ ==> List(1, 2, 3))
      _ <- ge(bigInt2).get.map(_ ==> List(2, 3))
      _ <- ge(bigInt3).get.map(_ ==> List(3))
    } yield ()
  }
}
