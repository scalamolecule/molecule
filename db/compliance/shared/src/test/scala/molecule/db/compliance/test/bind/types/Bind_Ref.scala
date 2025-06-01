package molecule.db.compliance.test.bind.types

import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.{DbProviders, Test, TestUtils}
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*

case class Bind_Ref(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*


  "Mandatory" - types { implicit conn =>
    for {
      List(ref1, ref2, ref3) <- Ref.i.insert(1, 2, 3).transact.map(_.ids)
      a = (1, ref1)
      b = (2, ref2)
      c = (3, ref3)

      _ <- Entity.i.ref.insert(a, b, c).transact

      eq = Entity.ref(?).query
      _ <- eq(ref1).get.map(_ ==> List(ref1))
      _ <- eq(ref2).get.map(_ ==> List(ref2))
      _ <- eq(ref3).get.map(_ ==> List(ref3))

      ne = Entity.ref.not(?).query
      _ <- ne(ref1).get.map(_ ==> List(ref2, ref3))
      _ <- ne(ref2).get.map(_ ==> List(ref1, ref3))
      _ <- ne(ref3).get.map(_ ==> List(ref1, ref2))

      lt = Entity.ref.<(?).query
      _ <- lt(ref1).get.map(_ ==> List())
      _ <- lt(ref2).get.map(_ ==> List(ref1))
      _ <- lt(ref3).get.map(_ ==> List(ref1, ref2))

      le = Entity.ref.<=(?).query
      _ <- le(ref1).get.map(_ ==> List(ref1))
      _ <- le(ref2).get.map(_ ==> List(ref1, ref2))
      _ <- le(ref3).get.map(_ ==> List(ref1, ref2, ref3))

      gt = Entity.ref.>(?).query
      _ <- gt(ref1).get.map(_ ==> List(ref2, ref3))
      _ <- gt(ref2).get.map(_ ==> List(ref3))
      _ <- gt(ref3).get.map(_ ==> List())

      ge = Entity.ref.>=(?).query
      _ <- ge(ref1).get.map(_ ==> List(ref1, ref2, ref3))
      _ <- ge(ref2).get.map(_ ==> List(ref2, ref3))
      _ <- ge(ref3).get.map(_ ==> List(ref3))
    } yield ()
  }


  "Tacit" - types { implicit conn =>
    for {
      List(ref1, ref2, ref3) <- Ref.i.insert(1, 2, 3).transact.map(_.ids)
      a = (1, ref1)
      b = (2, ref2)
      c = (3, ref3)

      _ <- Entity.i.ref.insert(a, b, c).transact

      eq = Entity.i.ref_(?).query
      _ <- eq(ref1).get.map(_ ==> List(1))
      _ <- eq(ref2).get.map(_ ==> List(2))
      _ <- eq(ref3).get.map(_ ==> List(3))

      ne = Entity.i.ref_.not(?).query
      _ <- ne(ref1).get.map(_ ==> List(2, 3))
      _ <- ne(ref2).get.map(_ ==> List(1, 3))
      _ <- ne(ref3).get.map(_ ==> List(1, 2))

      lt = Entity.i.ref_.<(?).query
      _ <- lt(ref1).get.map(_ ==> List())
      _ <- lt(ref2).get.map(_ ==> List(1))
      _ <- lt(ref3).get.map(_ ==> List(1, 2))

      le = Entity.i.ref_.<=(?).query
      _ <- le(ref1).get.map(_ ==> List(1))
      _ <- le(ref2).get.map(_ ==> List(1, 2))
      _ <- le(ref3).get.map(_ ==> List(1, 2, 3))

      gt = Entity.i.ref_.>(?).query
      _ <- gt(ref1).get.map(_ ==> List(2, 3))
      _ <- gt(ref2).get.map(_ ==> List(3))
      _ <- gt(ref3).get.map(_ ==> List())

      ge = Entity.i.ref_.>=(?).query
      _ <- ge(ref1).get.map(_ ==> List(1, 2, 3))
      _ <- ge(ref2).get.map(_ ==> List(2, 3))
      _ <- ge(ref3).get.map(_ ==> List(3))
    } yield ()
  }
}
