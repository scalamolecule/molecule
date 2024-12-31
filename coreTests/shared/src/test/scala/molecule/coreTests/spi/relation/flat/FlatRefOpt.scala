package molecule.coreTests.spi.relation.flat

import molecule.base.error.ModelError
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.domains.dsl.Refs._
import molecule.coreTests.setup._


case class FlatRefOpt(
  suite: MUnitSuite,
  api: Api_async with Spi_async with DbProviders
) extends TestUtils {

  import api._
  import suite._

  "Basic optional ref" - refs { implicit conn =>
    for {
      _ <- A.i(1).save.transact

      // Optional card-one ref (SQL left join)
      _ <- A.i.B.?(B.i).query.get.map(_ ==> List(
        (1, None),
      ))

      _ <- A.i(2).B.i(3).save.transact

      // Optional card-one ref (SQL left join)
      _ <- A.i.B.?(B.i).query.get.map(_ ==> List(
        (1, None),
        (2, Some(3)),
      ))

      // Mandatory ref
      _ <- A.i.B.i.query.get.map(_ ==> List(
        (2, 3),
      ))
    } yield ()
  }

  // same as:
  "Basic optional ref with opt ref insert" - refs { implicit conn =>
    for {
      _ <- A.i.B.?(B.i).insert(List(
        (1, None),
        (2, Some(20)),
      )).transact

      _ <- A.i.a1.B.?(B.i).query.get.map(_ ==> List(
        (1, None),
        (2, Some(20)),
      ))

      _ <- A.i.B.i.query.get.map(_ ==> List(
        (2, 20),
      ))
    } yield ()
  }


  "Only optional attributes in optional ref - SQL" - refs { implicit conn =>
    if (database != "datomic") {
      for {
        _ <- A.i.B.?(B.i_?).insert(List(
          (1, None), // no relationship created
          (2, Some(None)), // relationship to empty row created
          (3, Some(Some(30))),
        )).transact

        // 2 relationships created
        _ <- A.b(count).query.get.map(_.head ==> 2)

        // No relationship and empty row are indistinguishable
        // when all optional ref attributes (B.i_?) are optional
        _ <- A.i.a1.B.?(B.i_?).query.get.map(_ ==> List(
          (1, Some(None)), // no relationship
          (2, Some(None)), // relationship to empty ref row
          (3, Some(Some(30))),
        ))

        // Mandatory B.i makes result more clear
        _ <- A.i.a1.B.?(B.i).query.get.map(_ ==> List(
          (1, None),
          (2, None),
          (3, Some(30)),
        ))
      } yield ()
    }
  }

  "Only optional attributes in optional ref - Datomic" - refs { implicit conn =>
    if (database == "datomic") {
      for {
        _ <- A.i.B.?(B.i_?).insert(List(
          (1, None), // no relationship created
          (2, Some(None)), // no relationship created
          (3, Some(Some(30))),
        )).transact

        // 1 relationship created
        _ <- A.b(count).query.get.map(_.head ==> 1)

        _ <- A.i.a1.B.?(B.i_?).query.get.map(_ ==> List(
          (1, None),
          (2, None),
          (3, Some(Some(30))),
        ))

        // Mandatory B.i makes result more clear
        _ <- A.i.a1.B.?(B.i).query.get.map(_ ==> List(
          (1, None),
          (2, None),
          (3, Some(30)),
        ))
      } yield ()
    }
  }


  "Mix man/opt attributes in opt ref" - refs { implicit conn =>
    for {
      _ <- A.i.B.?(B.s.i_?).insert(List(
        (1, None),
        (2, Some(("a", None))),
        (3, Some(("b", Some(4)))),
      )).transact


      _ <- A.i.a1.B.?(B.s.i_?).query.get.map(_ ==> List(
        (1, None),
        (2, Some(("a", None))),
        (3, Some(("b", Some(4)))),
      ))

      _ <- A.i.a1.B.?(B.s.i).query.get.map(_ ==> List(
        (1, None),
        (2, None),
        (3, Some(("b", 4))),
      ))


      _ <- A.i.a1.B.s.i_?.query.get.map(_ ==> List(
        (2, "a", None),
        (3, "b", Some(4)),
      ))

      _ <- A.i.B.s.i.query.get.map(_ ==> List(
        (3, "b", 4),
      ))
    } yield ()
  }


  "Ref after opt ref" - refs { implicit conn =>
    for {
      _ <- A.i.B.?(B.i.s).C.i.query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Only further optional refs allowed after optional ref."
        }

      // Do this instead if C is a ref from B
      _ <- A.i.B.?(B.i.s.C.i).query.get

      // or this, if both B and C are refs from A
      _ <- A.i.C.i._A.B.?(B.i.s).query.get

      _ <- A.i.B.?(B.i.s).Cc.*(C.i).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Only further optional refs allowed after optional ref."
        }
    } yield ()
  }


  "Ref inside opt ref" - refs { implicit conn =>
    for {
      _ <- A.i.insert(
        1
      ).transact

      _ <- A.i.B.i.insert(
        2, 2
      ).transact

      _ <- A.i.B.i.C.i.insert(
        3, 3, 3
      ).transact


      _ <- A.i.a1.B.?(B.i).query.get.map(_ ==> List(
        (1, None),
        (2, Some(2)),
        (3, Some(3)),
      ))

      _ <- A.i.a1.B.?(B.i.C.i).query.get.map(_ ==> List(
        (1, None),
        (2, None),
        (3, Some((3, 3))),
      ))

      _ <- A.i.B.i.C.i.query.get.map(_ ==> List(
        (3, 3, 3),
      ))
    } yield ()
  }


  "No card-many ref inside opt ref" - refs { implicit conn =>
    for {
      _ <- A.i.B.s.i.Cc.*(C.s).insert(List(
        (1, "a", 1, Nil),
        (2, "b", 2, List("x", "y"))
      )).transact

      _ <- A.i.B.?(B.s.i.Cc.s).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Only cardinality-one refs allowed in optional ref queries (B.cc)."
        }

      // Instead, please use flat card-many ref
      _ <- A.i.B.s.i.Cc.s.query.get.map(_ ==> List(
        (2, "b", 2, "x"),
        (2, "b", 2, "y"),
      ))
      // or better, a nested query
      _ <- A.i.B.s.i.Cc.*(C.s.a1).query.get.map(_ ==> List(
        (2, "b", 2, List("x", "y")),
      ))

      _ <- A.i.B.?(B.i.s.Cc.*(C.s)).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Cardinality-many nesting not allowed inside optional ref."
        }
      _ <- A.i.B.?(B.i.s.Cc.*?(C.s)).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Cardinality-many nesting not allowed inside optional ref."
        }
    } yield ()
  }


  "Expression inside optional nested" - refs { implicit conn =>
    if (database != "datomic") {
      // Seems not possible to add expressions inside Datomic nested pulls
      for {
        _ <- A.i.B.?(B.i).insert(
          (0, None),
          (1, Some(1)),
          (2, Some(2)),
          (3, Some(3)),
        ).transact

        _ <- A.i.a1.B.?(B.i).query.get.map(_ ==> List(
          (0, None),
          (1, Some(1)),
          (2, Some(2)),
          (3, Some(3)),
        ))

        _ <- A.i.a1.B.?(B.i(1)).query.get.map(_ ==> List(
          (0, None),
          (1, Some(1)),
          (2, None),
          (3, None),
        ))
        _ <- A.i_.B.?(B.i(1)).query.get.map(_ ==> List(
          None,
          Some(1),
        ))

        _ <- A.i.a1.B.?(B.i.<(2)).query.get.map(_ ==> List(
          (0, None),
          (1, Some(1)),
          (2, None),
          (3, None),
        ))
        _ <- A.i_.B.?(B.i.<(2)).query.get.map(_ ==> List(
          None,
          Some(1),
        ))

        _ <- A.i.a1.B.?(B.i.<=(2)).query.get.map(_ ==> List(
          (0, None),
          (1, Some(1)),
          (2, Some(2)),
          (3, None),
        ))
        _ <- A.i_.B.?(B.i.<=(2).a1).query.get.map(_ ==> List(
          None,
          Some(1),
          Some(2),
        ))

        _ <- A.i.a1.B.?(B.i.>(2)).query.get.map(_ ==> List(
          (0, None),
          (1, None),
          (2, None),
          (3, Some(3)),
        ))
        _ <- A.i_.B.?(B.i.>(2)).query.get.map(_ ==> List(
          None,
          Some(3),
        ))

        _ <- A.i.a1.B.?(B.i.>=(2)).query.get.map(_ ==> List(
          (0, None),
          (1, None),
          (2, Some(2)),
          (3, Some(3)),
        ))
        _ <- A.i_.B.?(B.i.>=(2)).query.get.map(_ ==> List(
          None,
          Some(2),
          Some(3),
        ))
      } yield ()
    }
  }


  "Expression inside optional nested not in Datomic" - refs { implicit conn =>
    if (database == "datomic") {
      // Seems not possible to add expressions inside Datomic nested pulls
      for {
        _ <- A.i.B.?(B.i).insert(
          (0, None),
          (1, Some(1)),
          (2, Some(2)),
          (3, Some(3)),
        ).transact

        // Expression before optional nested ok
        _ <- A.i(1).B.?(B.i).query.get.map(_ ==> List(
          (1, Some(1)),
        ))

        // Expressions inside optional nested not allowed

        _ <- A.i_.B.?(B.i(1)).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Expressions not allowed in optional ref queries (B.i)."
          }

        _ <- A.i_.B.?(B.i.<(2)).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Expressions not allowed in optional ref queries (B.i)."
          }

        _ <- A.i_.B.?(B.i.<=(2)).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Expressions not allowed in optional ref queries (B.i)."
          }

        _ <- A.i_.B.?(B.i.>(2)).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Expressions not allowed in optional ref queries (B.i)."
          }

        _ <- A.i_.B.?(B.i.>=(2)).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Expressions not allowed in optional ref queries (B.i)."
          }
      } yield ()
    }
  }


  "Opt ref with Set attr" - refs { implicit conn =>
    for {
      _ <- A.i.B.?(B.iSet).insert(
        (0, None),
        (1, Some(Set(1, 2))),
      ).transact

      _ <- A.i.a1.B.?(B.iSet).query.get.map(_ ==> List(
        (0, None),
        (1, Some(Set(1, 2))),
      ))
    } yield ()
  }

  "Opt ref with Seq attr" - refs { implicit conn =>
    for {
      _ <- A.i.B.?(B.iSeq).insert(
        (0, None),
        (1, Some(Seq(1, 2, 1))),
      ).transact

      _ <- A.i.a1.B.?(B.iSeq).query.get.map(_ ==> List(
        (0, None),
        (1, Some(Seq(1, 2, 1))),
      ))
    } yield ()
  }

  "Opt ref with Map attr" - refs { implicit conn =>
    for {
      _ <- A.i.B.?(B.iMap).insert(
        (0, None),
        (1, Some(Map("a" -> 1, "b" -> 2))),
      ).transact

      _ <- A.i.a1.B.?(B.iMap).query.get.map(_ ==> List(
        (0, None),
        (1, Some(Map("a" -> 1, "b" -> 2))),
      ))
    } yield ()
  }


  "Opt ref with initial sorting" - refs { implicit conn =>
    for {
      _ <- A.i.B.?(B.i).insert(List(
        (1, None),
        (2, Some(2)),
      )).transact

      _ <- A.i.a1.B.?(B.i).query.get.map(_ ==> List(
        (1, None),
        (2, Some(2)),
      ))
      _ <- A.i.d1.B.?(B.i).query.get.map(_ ==> List(
        (2, Some(2)),
        (1, None),
      ))
    } yield ()
  }


  "Opt ref with sorting" - refs { implicit conn =>
    if (database != "datomic") {
      for {
        _ <- A.i.B.?(B.i).insert(List(
          (1, None),
          (1, Some(1)),
          (2, Some(1)),
          (2, Some(2)),
        )).transact

        // Sort initial attr first
        _ <- A.i.a1.B.?(B.i.a2).query.get.map(_ ==> List(
          (1, None),
          (1, Some(1)),
          (2, Some(1)),
          (2, Some(2)),
        ))
        _ <- A.i.a1.B.?(B.i.d2).query.get.map(_ ==> List(
          (1, Some(1)),
          (1, None),
          (2, Some(2)),
          (2, Some(1)),
        ))
        _ <- A.i.d1.B.?(B.i.a2).query.get.map(_ ==> List(
          (2, Some(1)),
          (2, Some(2)),
          (1, None),
          (1, Some(1)),
        ))
        _ <- A.i.d1.B.?(B.i.d2).query.get.map(_ ==> List(
          (2, Some(2)),
          (2, Some(1)),
          (1, Some(1)),
          (1, None),
        ))

        // Sort optional attr first
        _ <- A.i.a2.B.?(B.i.a1).query.get.map(_ ==> List(
          (1, None),
          (1, Some(1)),
          (2, Some(1)),
          (2, Some(2)),
        ))
        _ <- A.i.d2.B.?(B.i.a1).query.get.map(_ ==> List(
          (1, None),
          (2, Some(1)),
          (1, Some(1)),
          (2, Some(2)),
        ))
        _ <- A.i.a2.B.?(B.i.d1).query.get.map(_ ==> List(
          (2, Some(2)),
          (1, Some(1)),
          (2, Some(1)),
          (1, None),
        ))
        _ <- A.i.d2.B.?(B.i.d1).query.get.map(_ ==> List(
          (2, Some(2)),
          (2, Some(1)),
          (1, Some(1)),
          (1, None),
        ))
      } yield ()
    }
  }

  "Opt ref with sorting, 2 levels" - refs { implicit conn =>
    if (database != "datomic") {
      for {
        _ <- A.i(1).save.transact
        _ <- A.i(1).B.i(1).save.transact
        _ <- A.i(2).B.i(1).s("b").save.transact
        _ <- A.i(2).B.i(2).s("b").save.transact

        _ <- A.i(3).B.i(3).s("b").C.i(3).save.transact
        _ <- A.i(3).B.i(3).s("b").C.i(4).s("c").save.transact
        _ <- A.i(3).B.i(4).s("b").C.i(5).s("c").save.transact


        _ <- A.i.a1.B.?(B.i.a2.s.C.?(C.i.a3.s)).query.get.map(_ ==> List(
          (1, None),
          (1, None),
          (2, Some((1, "b", None))),
          (2, Some((2, "b", None))),

          (3, Some((3, "b", None))),
          (3, Some((3, "b", Some((4, "c"))))),
          (3, Some((4, "b", Some((5, "c"))))),
        ))

        _ <- A.i.d3.B.?(B.i.d2.s.C.?(C.i.d1.s)).query.get.map(_ ==> List(
          (3, Some((4, "b", Some((5, "c"))))),
          (3, Some((3, "b", Some((4, "c"))))),
          (3, Some((3, "b", None))),

          (2, Some((2, "b", None))),
          (2, Some((1, "b", None))),
          (1, None),
          (1, None),
        ))
      } yield ()
    }
  }

  "Opt ref with sorting, adjacent" - refs { implicit conn =>
    if (database != "datomic") {
      for {
        _ <- A.i(1).save.transact
        _ <- A.i(1).B.i(1).save.transact
        _ <- A.i(2).B.i(1).s("b").save.transact
        _ <- A.i(2).B.i(2).s("b").save.transact

        _ <- A.i(3).B.i(3).s("b")._A.C.i(3).save.transact
        _ <- A.i(3).B.i(3).s("b")._A.C.i(4).s("c").save.transact
        _ <- A.i(3).B.i(4).s("b")._A.C.i(5).s("c").save.transact


        _ <- A.i.a1.B.?(B.i.a2.s).C.?(C.i.a3.s).query.get.map(_ ==> List(
          (1, None, None),
          (1, None, None),
          (2, Some((1, "b")), None),
          (2, Some((2, "b")), None),

          (3, Some((3, "b")), None),
          (3, Some((3, "b")), Some((4, "c"))),
          (3, Some((4, "b")), Some((5, "c"))),
        ))

        _ <- A.i.d3.B.?(B.i.d2.s).C.?(C.i.d1.s).query.get.map(_ ==> List(
          (3, Some((4, "b")), Some((5, "c"))),
          (3, Some((3, "b")), Some((4, "c"))),
          (3, Some((3, "b")), None),

          (2, Some((2, "b")), None),
          (2, Some((1, "b")), None),
          (1, None, None),
          (1, None, None),
        ))
      } yield ()
    }
  }
}
