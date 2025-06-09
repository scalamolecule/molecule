package molecule.db.compliance.test.filterAttr.one

import molecule.base.error.ModelError
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Refs.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*

case class FilterAttrNested(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "Expressions" - refs { implicit conn =>
    for {
      _ <- A.s.i.Bb.*(B.i).insert(
        ("a", 1, List(2, 3)),
        ("b", 4, List(4, 5)),
        ("c", 7, List(5, 6)),
        ("d", 9, Nil),
      ).transact

      _ <- A.s.i(B.i_).Bb.*(B.i).query.get.map(_ ==> List(("b", 4, List(4))))

      _ <- A.s.i(B.i_).Bb.*(B.i(4)).query.get.map(_ ==> List(("b", 4, List(4))))
      _ <- A.s.i(B.i_).Bb.*(B.i.not(4)).query.get.map(_ ==> List())
      _ <- A.s.i(B.i_).Bb.*(B.i.>(4)).query.get.map(_ ==> List())
      _ <- A.s.i(B.i_).Bb.*(B.i.>=(4)).query.get.map(_ ==> List(("b", 4, List(4))))
      _ <- A.s.i(B.i_).Bb.*(B.i.<(4)).query.get.map(_ ==> List())
      _ <- A.s.i(B.i_).Bb.*(B.i.<=(4)).query.get.map(_ ==> List(("b", 4, List(4))))

      // Pointing backwards

      _ <- A.s.i.Bb.*(B.i(A.i_)).query.get.map(_ ==> List(("b", 4, List(4))))

      _ <- A.s.i(4).Bb.*(B.i(A.i_)).query.get.map(_ ==> List(("b", 4, List(4))))
      _ <- A.s.i.not(4).Bb.*(B.i(A.i_)).query.get.map(_ ==> List())
      _ <- A.s.i.>(4).Bb.*(B.i(A.i_)).query.get.map(_ ==> List())
      _ <- A.s.i.>=(4).Bb.*(B.i(A.i_)).query.get.map(_ ==> List(("b", 4, List(4))))
      _ <- A.s.i.<(4).Bb.*(B.i(A.i_)).query.get.map(_ ==> List())
      _ <- A.s.i.<=(4).Bb.*(B.i(A.i_)).query.get.map(_ ==> List(("b", 4, List(4))))
    } yield ()
  }


  "Ref" - refs { implicit conn =>
    for {
      _ <- A.s.i.Bb.*(B.i).insert(
        ("a", 1, List(2, 3)),
        ("b", 4, List(4, 5)),
        ("c", 7, List(5, 6)),
        ("d", 9, Nil),
      ).transact

      // Pointing forward

      _ <- A.s.a1.i(B.i_).Bb.*(B.i.a1).query.get.map(_ ==> List(
        ("b", 4, List(4)) // Note that only B.i values matching A.i are returned
      ))

      _ <- A.s.a1.i.not(B.i_).Bb.*(B.i.a1).query.get.map(_ ==> List(
        ("a", 1, List(2, 3)),
        ("b", 4, List(5)), // B.i == 4 omitted
        ("c", 7, List(5, 6)),
      ))

      _ <- A.s.a1.i.<(B.i_).Bb.*(B.i.a1).query.get.map(_ ==> List(
        ("a", 1, List(2, 3)),
        ("b", 4, List(5)),
      ))

      _ <- A.s.a1.i.<=(B.i_).Bb.*(B.i.a1).query.get.map(_ ==> List(
        ("a", 1, List(2, 3)),
        ("b", 4, List(4, 5)),
      ))

      _ <- A.s.a1.i.>(B.i_).Bb.*(B.i.a1).query.get.map(_ ==> List(
        ("c", 7, List(5, 6)),
      ))

      _ <- A.s.a1.i.>=(B.i_).Bb.*(B.i.a1).query.get.map(_ ==> List(
        ("b", 4, List(4)),
        ("c", 7, List(5, 6)),
      ))

      // Pointing backwards

      _ <- A.s.a1.i.Bb.*(B.i(A.i_)).query.get.map(_ ==> List(
        ("b", 4, List(4))
      ))

      _ <- A.s.a1.i.Bb.*(B.i.not(A.i_).a1).query.get.map(_ ==> List(
        ("a", 1, List(2, 3)),
        ("b", 4, List(5)),
        ("c", 7, List(5, 6)),
      ))

      _ <- A.s.a1.i.Bb.*(B.i.>(A.i_).a1).query.get.map(_ ==> List(
        ("a", 1, List(2, 3)),
        ("b", 4, List(5)),
      ))

      _ <- A.s.a1.i.Bb.*(B.i.>=(A.i_).a1).query.get.map(_ ==> List(
        ("a", 1, List(2, 3)),
        ("b", 4, List(4, 5)),
      ))

      _ <- A.s.a1.i.Bb.*(B.i.<(A.i_).a1).query.get.map(_ ==> List(
        ("c", 7, List(5, 6)),
      ))

      _ <- A.s.a1.i.Bb.*(B.i.<=(A.i_).a1).query.get.map(_ ==> List(
        ("b", 4, List(4)),
        ("c", 7, List(5, 6)),
      ))
    } yield ()
  }


  "Own" - refs { implicit conn =>
    for {
      _ <- A.s.i.Bb.*(B.i).insert(
        ("a", 1, List(2, 3)),
        ("b", 4, List(4, 5)),
        ("c", 7, List(5, 6)),
        ("d", 9, Nil),
      ).transact

      // Pointing forward

      _ <- A.s.a1.i(B.i_).Bb.*(B.i.a1).query.get.map(_ ==> List(
        ("b", 4, List(4)) // Note that only B.i values matching A.i are returned
      ))

      _ <- A.s.a1.i.not(B.i_).Bb.*(B.i.a1).query.get.map(_ ==> List(
        ("a", 1, List(2, 3)),
        ("b", 4, List(5)), // B.i == 4 omitted
        ("c", 7, List(5, 6)),
      ))

      _ <- A.s.a1.i.<(B.i_).Bb.*(B.i.a1).query.get.map(_ ==> List(
        ("a", 1, List(2, 3)),
        ("b", 4, List(5)),
      ))

      _ <- A.s.a1.i.<=(B.i_).Bb.*(B.i.a1).query.get.map(_ ==> List(
        ("a", 1, List(2, 3)),
        ("b", 4, List(4, 5)),
      ))

      _ <- A.s.a1.i.>(B.i_).Bb.*(B.i.a1).query.get.map(_ ==> List(
        ("c", 7, List(5, 6)),
      ))

      _ <- A.s.a1.i.>=(B.i_).Bb.*(B.i.a1).query.get.map(_ ==> List(
        ("b", 4, List(4)),
        ("c", 7, List(5, 6)),
      ))

      // Pointing backwards

      _ <- A.s.a1.i.Bb.*(B.i(A.i_)).query.get.map(_ ==> List(
        ("b", 4, List(4))
      ))

      _ <- A.s.a1.i.Bb.*(B.i.not(A.i_).a1).query.get.map(_ ==> List(
        ("a", 1, List(2, 3)),
        ("b", 4, List(5)),
        ("c", 7, List(5, 6)),
      ))

      _ <- A.s.a1.i.Bb.*(B.i.>(A.i_).a1).query.get.map(_ ==> List(
        ("a", 1, List(2, 3)),
        ("b", 4, List(5)),
      ))

      _ <- A.s.a1.i.Bb.*(B.i.>=(A.i_).a1).query.get.map(_ ==> List(
        ("a", 1, List(2, 3)),
        ("b", 4, List(4, 5)),
      ))

      _ <- A.s.a1.i.Bb.*(B.i.<(A.i_).a1).query.get.map(_ ==> List(
        ("c", 7, List(5, 6)),
      ))

      _ <- A.s.a1.i.Bb.*(B.i.<=(A.i_).a1).query.get.map(_ ==> List(
        ("b", 4, List(4)),
        ("c", 7, List(5, 6)),
      ))
    } yield ()
  }


  "Optional nested" - refs { implicit conn =>
    for {
      _ <- A.s.i(B.i_).Bb.*?(B.i).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Filter attributes not allowed in optional nested queries."
        }

      // Pointing backwards
      _ <- A.s.i.Bb.*?(B.i(A.i_)).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Filter attributes not allowed in optional nested queries."
        }
    } yield ()
  }
}
