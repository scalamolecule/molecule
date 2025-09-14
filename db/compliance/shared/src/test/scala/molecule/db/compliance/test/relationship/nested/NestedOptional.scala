package molecule.db.compliance.test.relationship.nested

import molecule.core.setup.MUnit
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Refs.*
import molecule.db.compliance.domains.dsl.Types.{Entity, Ref}
import molecule.db.compliance.setup.DbProviders
import molecule.db.compliance.test.relationship.Arity23


case class NestedOptional(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends Arity23 {

  import api.*
  import suite.*

  "Mandatory attr, nested row" - refs {
    for {
      _ <- A.s.Bb.*(B.i).insert(
        ("a", List(1)),
      ).transact

      _ <- A.s.a1.Bb.*?(B.i).query.get.map(_ ==> List(
        ("a", List(1)),
      ))
    } yield ()
  }


  "Mandatory attr, no nested row" - refs {
    for {
      _ <- A.s.Bb.*(B.i).insert(
        ("b", Nil),
      ).transact

      _ <- A.s.a1.Bb.*?(B.i).query.get.map(_ ==> List(
        ("b", Nil),
      ))
    } yield ()
  }


  "Mandatory attr, 1 value" - refs {
    for {
      _ <- A.s.Bb.*(B.i).insert(
        ("a", List(1)),
        ("b", Nil),
      ).transact

      _ <- A.s.a1.Bb.*?(B.i).query.get.map(_ ==> List(
        ("a", List(1)),
        ("b", Nil),
      ))
      _ <- A.s.d1.Bb.*?(B.i).query.get.map(_ ==> List(
        ("b", Nil),
        ("a", List(1)),
      ))
    } yield ()
  }

  "Mandatory attr, 2 values" - refs {
    for {
      _ <- A.s.Bb.*(B.i).insert(
        ("a", List(1, 2)),
        ("b", Nil),
      ).transact

      _ <- A.s.a1.Bb.*?(B.i.a1).query.get.map(_ ==> List(
        ("a", List(1, 2)),
        ("b", Nil),
      ))
      _ <- A.s.d1.Bb.*?(B.i.a1).query.get.map(_ ==> List(
        ("b", Nil),
        ("a", List(1, 2)),
      ))
    } yield ()
  }


  "Optional attr, 1 value" - refs {
    for {
      _ <- A.s.Bb.*(B.i_?).insert(
        ("a", List(Some(1))),
        ("b", List(None)),
        ("c", Nil),
      ).transact

      _ <- A.s.a1.Bb.*?(B.i_?).query.get.map(_ ==> List(
        ("a", List(Some(1))),
        ("b", Nil),
        ("c", Nil),
      ))
      _ <- A.s.d1.Bb.*?(B.i_?).query.get.map(_ ==> List(
        ("c", Nil),
        ("b", Nil),
        ("a", List(Some(1))),
      ))
    } yield ()
  }

  "Optional attr, 2 values" - refs {
    for {
      _ <- A.s.Bb.*(B.i_?).insert(
        ("a", List(Some(1), Some(2))),
        ("b", List(Some(1), None)),
        ("c", List(None, Some(2))),
        ("d", List(None, None)),
        ("e", Nil),
        ("f", Nil),
      ).transact

      _ <- A.s.a1.Bb.*?(B.i_?.a1).query.get.map(_ ==> List(
        ("a", List(Some(1), Some(2))),
        ("b", List(Some(1))),
        ("c", List(Some(2))),
        ("d", Nil),
        ("e", Nil),
        ("f", Nil),
      ))
      _ <- A.s.d1.Bb.*?(B.i_?.a1).query.get.map(_ ==> List(
        ("f", Nil),
        ("e", Nil),
        ("d", Nil),
        ("c", List(Some(2))),
        ("b", List(Some(1))),
        ("a", List(Some(1), Some(2))),
      ))
    } yield ()
  }


  "Man/man attr" - refs {
    for {
      _ <- A.s.Bb.*(B.i.s).insert(
        ("a", List((1, "x"), (2, "y"))),
        ("b", List((3, "z"))),
        ("c", Nil),
      ).transact

      _ <- A.s.a1.Bb.*?(B.i.a1.s).query.get.map(_ ==> List(
        ("a", List((1, "x"), (2, "y"))),
        ("b", List((3, "z"))),
        ("c", Nil),
      ))
      _ <- A.s.d1.Bb.*?(B.i.a1.s).query.get.map(_ ==> List(
        ("c", Nil),
        ("b", List((3, "z"))),
        ("a", List((1, "x"), (2, "y"))),
      ))
    } yield ()
  }


  "Man/opt attr" - refs {
    for {
      _ <- A.s.Bb.*(B.i.s_?).insert(
        ("a", List((1, Some("x")), (2, Some("y")))),
        ("b", List((1, Some("x")), (2, None))),
        ("c", List((1, None), (2, Some("y")))),
        ("d", List((1, None), (2, None))),
        ("e", Nil),
      ).transact

      _ <- A.s.a1.Bb.*?(B.i.a1.s_?).query.get.map(_ ==> List(
        ("a", List((1, Some("x")), (2, Some("y")))),
        ("b", List((1, Some("x")), (2, None))),
        ("c", List((1, None), (2, Some("y")))),
        ("d", List((1, None), (2, None))),
        ("e", Nil),
      ))
      _ <- A.s.d1.Bb.*?(B.i.a1.s_?).query.get.map(_ ==> List(
        ("e", Nil),
        ("d", List((1, None), (2, None))),
        ("c", List((1, None), (2, Some("y")))),
        ("b", List((1, Some("x")), (2, None))),
        ("a", List((1, Some("x")), (2, Some("y")))),
      ))
    } yield ()
  }


  "Opt/man attr" - refs {
    for {
      _ <- A.s.Bb.*(B.i_?.s).insert(
        ("a", List((Some(1), "x"), (Some(2), "y"))),
        ("b", List((Some(1), "x"), (None, "y"))),
        ("c", List((None, "x"), (Some(2), "y"))),
        ("d", List((None, "x"), (None, "y"))),
        ("e", Nil),
      ).transact

      _ <- A.s.a1.Bb.*?(B.i_?.s.a1).query.get.map(_ ==> List(
        ("a", List((Some(1), "x"), (Some(2), "y"))),
        ("b", List((Some(1), "x"), (None, "y"))),
        ("c", List((None, "x"), (Some(2), "y"))),
        ("d", List((None, "x"), (None, "y"))),
        ("e", Nil),
      ))
      _ <- A.s.d1.Bb.*?(B.i_?.s.a1).query.get.map(_ ==> List(
        ("e", Nil),
        ("d", List((None, "x"), (None, "y"))),
        ("c", List((None, "x"), (Some(2), "y"))),
        ("b", List((Some(1), "x"), (None, "y"))),
        ("a", List((Some(1), "x"), (Some(2), "y"))),
      ))
    } yield ()
  }


  "Opt/opt attr" - refs {
    for {
      _ <- A.s.Bb.*(B.i_?.s_?).insert(
        ("0", List((Some(1), Some("x")), (Some(2), Some("y")))),

        ("1a", List((None, Some("x")), (Some(2), Some("y")))),
        ("1b", List((Some(1), None), (Some(2), Some("y")))),
        ("1c", List((Some(1), Some("x")), (None, Some("y")))),
        ("1d", List((Some(1), Some("x")), (Some(2), None))),

        ("2a", List((None, None), (Some(2), Some("y")))),
        ("2b", List((None, Some("x")), (None, Some("y")))),
        ("2c", List((None, Some("x")), (Some(2), None))),
        ("2d", List((Some(1), None), (None, Some("y")))),
        ("2e", List((Some(1), None), (Some(2), None))),
        ("2f", List((Some(1), Some("x")), (None, None))),

        ("3a", List((None, None), (None, Some("y")))),
        ("3b", List((None, None), (Some(2), None))),
        ("3c", List((None, Some("x")), (None, None))),
        ("3d", List((Some(1), None), (None, None))),

        ("4", List((None, None), (None, None))),

        ("a", Nil),
      ).transact

      _ <- A.s.a1.Bb.*?(B.i_?.a1.s_?.a2).query.get.map(_ ==> List(
        ("0", List((Some(1), Some("x")), (Some(2), Some("y")))),

        ("1a", List((None, Some("x")), (Some(2), Some("y")))),
        ("1b", List((Some(1), None), (Some(2), Some("y")))),
        ("1c", List((None, Some("y")), (Some(1), Some("x")))), // None sorted first
        ("1d", List((Some(1), Some("x")), (Some(2), None))),

        ("2a", List((Some(2), Some("y")))), // (None, None) not included
        ("2b", List((None, Some("x")), (None, Some("y")))),
        ("2c", List((None, Some("x")), (Some(2), None))),
        ("2d", List((None, Some("y")), (Some(1), None))), // None sorted first
        ("2e", List((Some(1), None), (Some(2), None))),
        ("2f", List((Some(1), Some("x")))), // (None, None) not included

        ("3a", List((None, Some("y")))),
        ("3b", List((Some(2), None))),
        ("3c", List((None, Some("x")))),
        ("3d", List((Some(1), None))),

        ("4", Nil), // List((None, None), (None, None)) collapsing to Nil

        ("a", Nil),
      ))
    } yield ()
  }


  "2 levels" - refs {
    for {
      _ <- A.s.Bb.*(B.i_?.s.Cc.*(C.i.s_?)).insert(
        ("A", List(
          (Some(1), "a1", List((10, Some("x")), (11, None))),
          (None, "a2", List((20, None), (21, Some("y"))))
        )),
        ("B", Nil),
      ).transact

      _ <- A.s.a1.Bb.*?(B.i_?.a1.s.Cc.*?(C.i.a1.s_?)).query.get.map(_ ==> List(
        ("A", List(
          (None, "a2", List(
            (20, None),
            (21, Some("y")),
          )),
          (Some(1), "a1", List(
            (10, Some("x")),
            (11, None),
          ))
        )),
        ("B", Nil),
      ))

      _ <- A.s.d1.Bb.*?(B.i_?.d1.s.Cc.*?(C.i.d1.s_?)).query.get.map(_ ==> List(
        ("B", Nil),
        ("A", List(
          (Some(1), "a1", List(
            (11, None),
            (10, Some("x")),
          )),
          (None, "a2", List(
            (21, Some("y")),
            (20, None),
          )),
        )),
      ))
    } yield ()
  }

  "Expression inside optional nested" - refs {
    // In SQL, expressions in optional nested queries are ok
    for {
      _ <- A.i.Bb.*(B.i).insert(List((1, List(1, 2, 3)))).transact

      _ <- A.i(1).Bb.*?(B.i.a1).query.get.map(_ ==> List((1, List(1, 2, 3))))
      _ <- A.i_.Bb.*?(B.i(1)).query.get.map(_ ==> List(List(1)))
      _ <- A.i_.Bb.*?(B.i.<(2)).query.get.map(_ ==> List(List(1)))
      _ <- A.i_.Bb.*?(B.i.<=(2).a1).query.get.map(_ ==> List(List(1, 2)))
      _ <- A.i_.Bb.*?(B.i.>(2)).query.get.map(_ ==> List(List(3)))
      _ <- A.i_.Bb.*?(B.i.>=(2).a1).query.get.map(_ ==> List(List(2, 3)))
    } yield ()
  }


  "Arity 23 optional nested" - types {
    for {
      _ <- Entity.i.Refs.*?(ref23).insert(
        (1, List(tpl23_1, tpl23_2)),
        (2, Nil),
      ).transact

      _ <- Entity.i.a1.Refs.*?(
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
      ).query.get.map(_ ==> List(
        (1, List(tpl23_1, tpl23_2)),
        (2, Nil)
      ))
    } yield ()
  }
}
