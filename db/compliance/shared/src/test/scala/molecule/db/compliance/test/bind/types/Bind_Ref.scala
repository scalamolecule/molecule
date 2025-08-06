package molecule.db.compliance.test.bind.types

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders

case class Bind_Ref(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*


  "Mandatory" - types {
    for {
      case List(ref1, ref2, ref3) <- Ref.i.insert(1, 2, 3).transact.map(_.ids)
      a = (1, ref1)
      b = (2, ref2)
      c = (3, ref3)

      _ <- Entity.i.ref.insert(a, b, c).transact

      eq = Entity.ref(?).query
      _ <- eq(ref1).get.map(_ ==> List(ref1))
      _ <- eq(ref2).get.map(_ ==> List(ref2))
      _ <- eq(ref3).get.map(_ ==> List(ref3))

      ne = Entity.ref.not(?).a1.query
      _ <- ne(ref1).get.map(_ ==> List(ref2, ref3))
      _ <- ne(ref2).get.map(_ ==> List(ref1, ref3))
      _ <- ne(ref3).get.map(_ ==> List(ref1, ref2))

      lt = Entity.ref.<(?).a1.query
      _ <- lt(ref1).get.map(_ ==> List())
      _ <- lt(ref2).get.map(_ ==> List(ref1))
      _ <- lt(ref3).get.map(_ ==> List(ref1, ref2))

      le = Entity.ref.<=(?).a1.query
      _ <- le(ref1).get.map(_ ==> List(ref1))
      _ <- le(ref2).get.map(_ ==> List(ref1, ref2))
      _ <- le(ref3).get.map(_ ==> List(ref1, ref2, ref3))

      gt = Entity.ref.>(?).a1.query
      _ <- gt(ref1).get.map(_ ==> List(ref2, ref3))
      _ <- gt(ref2).get.map(_ ==> List(ref3))
      _ <- gt(ref3).get.map(_ ==> List())

      ge = Entity.ref.>=(?).a1.query
      _ <- ge(ref1).get.map(_ ==> List(ref1, ref2, ref3))
      _ <- ge(ref2).get.map(_ ==> List(ref2, ref3))
      _ <- ge(ref3).get.map(_ ==> List(ref3))
    } yield ()
  }


  "Tacit" - types {
    for {
      case List(ref1, ref2, ref3) <- Ref.i.insert(1, 2, 3).transact.map(_.ids)
      a = (1, ref1)
      b = (2, ref2)
      c = (3, ref3)

      _ <- Entity.i.ref.insert(a, b, c).transact

      eq = Entity.i.ref_(?).query
      _ <- eq(ref1).get.map(_ ==> List(1))
      _ <- eq(ref2).get.map(_ ==> List(2))
      _ <- eq(ref3).get.map(_ ==> List(3))

      ne = Entity.i.a1.ref_.not(?).query
      _ <- ne(ref1).get.map(_ ==> List(2, 3))
      _ <- ne(ref2).get.map(_ ==> List(1, 3))
      _ <- ne(ref3).get.map(_ ==> List(1, 2))

      lt = Entity.i.a1.ref_.<(?).query
      _ <- lt(ref1).get.map(_ ==> List())
      _ <- lt(ref2).get.map(_ ==> List(1))
      _ <- lt(ref3).get.map(_ ==> List(1, 2))

      le = Entity.i.a1.ref_.<=(?).query
      _ <- le(ref1).get.map(_ ==> List(1))
      _ <- le(ref2).get.map(_ ==> List(1, 2))
      _ <- le(ref3).get.map(_ ==> List(1, 2, 3))

      gt = Entity.i.a1.ref_.>(?).query
      _ <- gt(ref1).get.map(_ ==> List(2, 3))
      _ <- gt(ref2).get.map(_ ==> List(3))
      _ <- gt(ref3).get.map(_ ==> List())

      ge = Entity.i.a1.ref_.>=(?).query
      _ <- ge(ref1).get.map(_ ==> List(1, 2, 3))
      _ <- ge(ref2).get.map(_ ==> List(2, 3))
      _ <- ge(ref3).get.map(_ ==> List(3))
    } yield ()
  }
}
