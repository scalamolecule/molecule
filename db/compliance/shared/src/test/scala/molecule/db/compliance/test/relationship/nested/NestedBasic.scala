package molecule.db.compliance.test.relationship.nested

import molecule.core.setup.MUnit
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Refs.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.compliance.test.relationship.Arity23


case class NestedBasic(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends Arity23 {

  import api.*
  import suite.*

  "Mandatory/optional rows" - refs {
    for {
      _ <- A.s.Bb.*(B.i).insert(
        ("a", List(1, 2)),
        ("b", Nil),
      ).transact

      // Mandatory nested data
      _ <- A.s.Bb.*(B.i.a1).query.get.map(_ ==> List(
        ("a", List(1, 2)),
      ))

      // Optional nested data
      _ <- A.s.a1.Bb.*?(B.i.a1).query.get.map(_ ==> List(
        ("a", List(1, 2)),
        ("b", Nil),
      ))
    } yield ()
  }


  "Nested: one" - refs {

    for {
      _ <- A.i.Bb.*(B.i).insert((2, List(3, 4))).transact
      _ <- A.i.Bb.*(B.i.a1).query.get.map(_ ==> List((2, List(3, 4))))
    } yield ()
  }

  "Nested: set" - refs {

    for {
      _ <- A.i.Bb.*(B.iSet).insert(List((2, List(Set(3, 4))))).transact
      _ <- A.i.Bb.*(B.iSet).query.get.map(_ ==> List((2, List(Set(3, 4)))))
    } yield ()
  }

  "Expressions in nested" - refs {
    for {
      _ <- A.i.Bb.*(B.i).insert(List((1, List(1, 2, 3)))).transact

      _ <- A.i_.Bb.*(B.i.a1).query.get.map(_ ==> List(List(1, 2, 3)))
      _ <- A.i_.Bb.*(B.i(1).a1).query.get.map(_ ==> List(List(1)))
      _ <- A.i_.Bb.*(B.i(1, 2).a1).query.get.map(_ ==> List(List(1, 2)))
      _ <- A.i_.Bb.*(B.i.not(1).a1).query.get.map(_ ==> List(List(2, 3)))
      _ <- A.i_.Bb.*(B.i.not(1, 2).a1).query.get.map(_ ==> List(List(3)))
      _ <- A.i_.Bb.*(B.i.<(2).a1).query.get.map(_ ==> List(List(1)))
      _ <- A.i_.Bb.*(B.i.<=(2).a1).query.get.map(_ ==> List(List(1, 2)))
      _ <- A.i_.Bb.*(B.i.>(2).a1).query.get.map(_ ==> List(List(3)))
      _ <- A.i_.Bb.*(B.i.>=(2).a1).query.get.map(_ ==> List(List(2, 3)))
    } yield ()
  }


  import molecule.db.compliance.domains.dsl.Types.*

  "Arity 23 nested" - types {
    for {
      _ <- Entity.i.Refs.*(ref23).insert((1, List(tpl23_1, tpl23_2))).transact
      _ <- Entity.i.Refs.*(
        Ref
          .string.a1
          .int
          .long
          .float
          .double
          .boolean
          .bigInt
          .bigDecimal
          .date
          .duration
          .instant
          .localDate
          .localTime
          .localDateTime
          .offsetTime
          .offsetDateTime
          .zonedDateTime
          .uuid
          .uri
          .byte
          .short
          .char
          .i
      ).query.get.map(_ ==> List((1, List(tpl23_1, tpl23_2))))
    } yield ()
  }
}
