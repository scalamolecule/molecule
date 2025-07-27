package molecule.db.compliance.test.bind

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Refs.*
import molecule.db.compliance.setup.DbProviders

case class Nested(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*


  "Mandatory nested" - refs {
    for {
      _ <- A.s.Bb.*(B.i).insert(
        ("a", List(1, 2, 3)),
      ).transact

      // Mandatory nested data
      eq = A.s.Bb.*(B.i(?).a1).query
      _ <- eq(0).get.map(_ ==> List())
      _ <- eq(1).get.map(_ ==> List(("a", List(1))))
      _ <- eq(2).get.map(_ ==> List(("a", List(2))))

      ne = A.s.Bb.*(B.i.not(?).a1).query
      _ <- ne(0).get.map(_ ==> List(("a", List(1, 2, 3))))
      _ <- ne(1).get.map(_ ==> List(("a", List(2, 3))))
      _ <- ne(2).get.map(_ ==> List(("a", List(1, 3))))

      lt = A.s.Bb.*(B.i.<(?).a1).query
      _ <- lt(1).get.map(_ ==> List())
      _ <- lt(2).get.map(_ ==> List(("a", List(1))))

      le = A.s.Bb.*(B.i.<=(?).a1).query
      _ <- le(1).get.map(_ ==> List(("a", List(1))))
      _ <- le(2).get.map(_ ==> List(("a", List(1, 2))))

      gt = A.s.Bb.*(B.i.>(?).a1).query
      _ <- gt(1).get.map(_ ==> List(("a", List(2, 3))))
      _ <- gt(2).get.map(_ ==> List(("a", List(3))))

      ge = A.s.Bb.*(B.i.>=(?).a1).query
      _ <- ge(1).get.map(_ ==> List(("a", List(1, 2, 3))))
      _ <- ge(2).get.map(_ ==> List(("a", List(2, 3))))
    } yield ()
  }


  "Optional nested" - refs {
    for {
      _ <- A.s.Bb.*(B.i).insert(
        ("a", List(1, 2, 3)),
        ("b", Nil),
      ).transact

      eq = A.s.Bb.*?(B.i(?).a1).query
      _ <- eq(0).get.map(_ ==> List())
      _ <- eq(1).get.map(_ ==> List(("a", List(1))))
      _ <- eq(2).get.map(_ ==> List(("a", List(2))))

      ne = A.s.Bb.*?(B.i.not(?).a1).query
      _ <- ne(0).get.map(_ ==> List(("a", List(1, 2, 3))))
      _ <- ne(1).get.map(_ ==> List(("a", List(2, 3))))
      _ <- ne(2).get.map(_ ==> List(("a", List(1, 3))))

      lt = A.s.Bb.*?(B.i.<(?).a1).query
      _ <- lt(1).get.map(_ ==> List())
      _ <- lt(2).get.map(_ ==> List(("a", List(1))))

      le = A.s.Bb.*?(B.i.<=(?).a1).query
      _ <- le(1).get.map(_ ==> List(("a", List(1))))
      _ <- le(2).get.map(_ ==> List(("a", List(1, 2))))

      gt = A.s.Bb.*?(B.i.>(?).a1).query
      _ <- gt(1).get.map(_ ==> List(("a", List(2, 3))))
      _ <- gt(2).get.map(_ ==> List(("a", List(3))))

      ge = A.s.Bb.*?(B.i.>=(?).a1).query
      _ <- ge(1).get.map(_ ==> List(("a", List(1, 2, 3))))
      _ <- ge(2).get.map(_ ==> List(("a", List(2, 3))))
    } yield ()
  }
}
