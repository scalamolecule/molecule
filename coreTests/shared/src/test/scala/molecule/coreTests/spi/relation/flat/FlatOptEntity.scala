package molecule.coreTests.spi.relation.flat

import molecule.base.error.ModelError
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.domains.dsl.Refs._
import molecule.coreTests.setup._


case class FlatOptEntity(
  suite: Test,
  api: Api_async with Spi_async with DbProviders
) extends TestUtils {

  import api._
  import suite._

  if (database == "datomic") {

    // Optional entities not implemented for Datomic.
    // Doesn't seem viable in Datalog.

    "Optional entity only, 1 attr" - refs { implicit conn =>
      for {
        _ <- A.?(A.i).B.i.insert(List((None, 1))).transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Optional entity not implement for Datomic."
          }

        _ <- A.?(A.i).B.i.a1.query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Optional entity not implement for Datomic."
          }
      } yield ()
    }
  } else {

    "Optional entity only, 1 attr" - refs { implicit conn =>
      for {
        _ <- A.?(A.i).insert(
          Some(1),
          None
        ).transact

        _ <- A.i.query.get.map(_ ==> List(
          1
        ))
      } yield ()
    }


    "Optional entity only, multiple attrs" - refs { implicit conn =>
      for {
        _ <- A.?(A.i.s).insert(
          Some((1, "a")),
          None
        ).transact

        _ <- A.i.s.query.get.map(_ ==> List(
          (1, "a")
        ))
      } yield ()
    }


    "Basic with following ref" - refs { implicit conn =>
      for {
        _ <- A.?(A.i).B.i.insert(List(
          (None, 1),
          (Some(20), 2),
        )).transact

        _ <- A.?(A.i).B.i.a1.query.get.map(_ ==> List(
          (None, 1),
          (Some(20), 2),
        ))

        _ <- A.i.B.i.query.get.map(_ ==> List(
          (20, 2),
        ))
      } yield ()
    }


    "Tacit optional entity attrs" - refs { implicit conn =>
      for {
        _ <- A.?(A.i).B.s.insert(List(
          (None, "-"),
          (Some(1), "a"),
        )).transact

        _ <- A.?(A.i).B.s.a1.query.get.map(_ ==> List(
          (None, "-"),
          (Some(1), "a"),
        ))

        // Using only tacit attributes of the optional entity
        // always returns None when matching

        // Find B data with A.i existing
        _ <- A.?(A.i_).B.s.a1.query.get.map(_ ==> List(
          (None, "a"),
        ))

        // Find B data where A.i > 0
        _ <- A.?(A.i_.>(0)).B.s.a1.query.get.map(_ ==> List(
          (None, "a"),
        ))

        // Find B data where A.i > 1 - no match!
        _ <- A.?(A.i_.>(1)).B.s.a1.query.get.map(_ ==> Nil)

        // Find B data with no relationship from A to B
        _ <- A.?(A.b_()).B.s.a1.query.get.map(_ ==> List(
          (None, "-"),
        ))
      } yield ()
    }


    "Basic with following refs" - refs { implicit conn =>
      for {
        _ <- A.?(A.i.s).B.s.C.i.insert(
          (None, "-", 0),
          (Some((10, "x")), "a", 1),
          (Some((20, "y")), "b", 2),
        ).transact

        _ <- A.?(A.i.s).B.s.C.i.a1.query.get.map(_ ==> List(
          (None, "-", 0),
          (Some((10, "x")), "a", 1),
          (Some((20, "y")), "b", 2),
        ))
      } yield ()
    }


    "Only optional attributes in optional entity - SQL" - refs { implicit conn =>
      if (database != "datomic") {
        for {
          _ <- A.?(A.i_?).B.i.insert(List(
            (None, 1), // no relationship created
            (Some(None), 2), // relationship to empty row created
            (Some(Some(30)), 3),
          )).transact

          // 2 relationships created
          _ <- A.b(count).query.get.map(_.head ==> 2)

          // No relationship and empty row are indistinguishable
          // when all optional entity attributes (A.i_?) are optional
          _ <- A.?(A.i_?).B.i.a1.query.get.map(_ ==> List(
            (Some(None), 1), // no relationship
            (Some(None), 2), // relationship to empty ref row
            (Some(Some(30)), 3),
          ))

          // Mandatory B.i makes result more clear
          _ <- A.?(A.i).B.i.a1.query.get.map(_ ==> List(
            (None, 1),
            (None, 2),
            (Some(30), 3),
          ))
        } yield ()
      }
    }

    "Only optional attributes in optional entity - Datomic" - refs { implicit conn =>
      if (database == "datomic") {
        for {
          _ <- A.?(A.i_?).B.i.insert(List(
            (None, 1), // no relationship created
            (Some(None), 2), // no relationship created
            (Some(Some(30)), 3),
          )).transact

          // 1 relationship created
          _ <- A.b(count).query.get.map(_.head ==> 1)

          _ <- A.?(A.i_?).B.i.a1.query.get.map(_ ==> List(
            (None, 1),
            (None, 2),
            (Some(Some(30)), 3),
          ))

          // Mandatory B.i makes result more clear
          _ <- A.?(A.i).B.i.a1.query.get.map(_ ==> List(
            (None, 1),
            (None, 2),
            (Some(30), 3),
          ))
        } yield ()
      }
    }


    "Mix man/opt attributes in opt ref" - refs { implicit conn =>
      for {
        _ <- A.?(A.s.i_?).B.i.insert(List(
          (None, 1),
          (Some(("a", None)), 2),
          (Some(("b", Some(4))), 3),
        )).transact


        _ <- A.?(A.s.i_?).B.i.a1.query.get.map(_ ==> List(
          (None, 1),
          (Some(("a", None)), 2),
          (Some(("b", Some(4))), 3),
        ))

        _ <- A.?(A.s.i).B.i.a1.query.get.map(_ ==> List(
          (None, 1),
          (None, 2),
          (Some(("b", 4)), 3),
        ))


        _ <- A.s.i_?.B.i.a1.query.get.map(_ ==> List(
          ("a", None, 2),
          ("b", Some(4), 3),
        ))

        _ <- A.s.i.B.i.query.get.map(_ ==> List(
          ("b", 4, 3),
        ))
      } yield ()
    }


    "Ref inside opt ref" - refs { implicit conn =>
      for {
        _ <- C.i.insert(
          1
        ).transact

        _ <- B.i.C.i.insert(
          2, 2
        ).transact

        _ <- A.i.B.i.C.i.insert(
          3, 3, 3
        ).transact


        _ <- B.?(B.i).C.i.a1.query.get.map(_ ==> List(
          (None, 1),
          (Some(2), 2),
          (Some(3), 3),
        ))

        _ <- A.?(A.i).B.i.a1.query.get.map(_ ==> List(
          (None, 2),
          (Some(3), 3),
        ))

        _ <- A.i.B.i.C.i.query.get.map(_ ==> List(
          (3, 3, 3),
        ))
      } yield ()
    }


    "Expression inside optional nested" - refs { implicit conn =>
      if (database != "datomic") {
        // Seems not possible to add expressions inside Datomic nested pulls
        for {
          _ <- A.?(A.i).B.i.insert(
            (None, 0),
            (Some(1), 1),
            (Some(2), 2),
            (Some(3), 3),
          ).transact

          _ <- A.?(A.i).B.i.a1.query.get.map(_ ==> List(
            (None, 0),
            (Some(1), 1),
            (Some(2), 2),
            (Some(3), 3),
          ))

          _ <- A.?(A.i(1)).B.i.a1.query.get.map(_ ==> List(
            (None, 0),
            (Some(1), 1),
            (None, 2),
            (None, 3),
          ))
          _ <- A.?(A.i(1)).B.i_.query.get.map(_ ==> List(
            None,
            Some(1),
          ))

          _ <- A.?(A.i.<(2)).B.i.a1.query.get.map(_ ==> List(
            (None, 0),
            (Some(1), 1),
            (None, 2),
            (None, 3),
          ))
          _ <- A.?(A.i.<(2)).B.i_.query.get.map(_ ==> List(
            None,
            Some(1),
          ))

          _ <- A.?(A.i.<=(2)).B.i.a1.query.get.map(_ ==> List(
            (None, 0),
            (Some(1), 1),
            (Some(2), 2),
            (None, 3),
          ))
          _ <- A.?(A.i.<=(2).a1).B.i_.query.get.map(_ ==> List(
            None,
            Some(1),
            Some(2),
          ))

          _ <- A.?(A.i.>(2)).B.i.a1.query.get.map(_ ==> List(
            (None, 0),
            (None, 1),
            (None, 2),
            (Some(3), 3),
          ))
          _ <- A.?(A.i.>(2)).B.i_.query.get.map(_ ==> List(
            None,
            Some(3),
          ))

          _ <- A.?(A.i.>=(2)).B.i.a1.query.get.map(_ ==> List(
            (None, 0),
            (None, 1),
            (Some(2), 2),
            (Some(3), 3),
          ))
          _ <- A.?(A.i.>=(2)).B.i_.query.get.map(_ ==> List(
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
              err ==> "Expressions not allowed in optional entity queries (B.i)."
            }

          _ <- A.i_.B.?(B.i.<(2)).query.get
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
              err ==> "Expressions not allowed in optional entity queries (B.i)."
            }

          _ <- A.i_.B.?(B.i.<=(2)).query.get
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
              err ==> "Expressions not allowed in optional entity queries (B.i)."
            }

          _ <- A.i_.B.?(B.i.>(2)).query.get
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
              err ==> "Expressions not allowed in optional entity queries (B.i)."
            }

          _ <- A.i_.B.?(B.i.>=(2)).query.get
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
              err ==> "Expressions not allowed in optional entity queries (B.i)."
            }
        } yield ()
      }
    }


    "Opt ref with Set attr" - refs { implicit conn =>
      for {
        _ <- A.?(A.iSet).B.i.insert(
          (None, 0),
          (Some(Set(1, 2)), 1),
        ).transact

        _ <- A.?(A.iSet).B.i.a1.query.get.map(_ ==> List(
          (None, 0),
          (Some(Set(1, 2)), 1),
        ))
      } yield ()
    }

    "Opt ref with Seq attr" - refs { implicit conn =>
      for {
        _ <- A.?(A.iSeq).B.i.insert(
          (None, 0),
          (Some(Seq(1, 2, 1)), 1),
        ).transact

        _ <- A.?(A.iSeq).B.i.a1.query.get.map(_ ==> List(
          (None, 0),
          (Some(Seq(1, 2, 1)), 1),
        ))
      } yield ()
    }

    "Opt ref with Map attr" - refs { implicit conn =>
      for {
        _ <- A.?(A.iMap).B.i.insert(
          (None, 0),
          (Some(Map("a" -> 1, "b" -> 2)), 1),
        ).transact

        _ <- A.?(A.iMap).B.i.a1.query.get.map(_ ==> List(
          (None, 0),
          (Some(Map("a" -> 1, "b" -> 2)), 1),
        ))
      } yield ()
    }


    "Opt ref with sorting" - refs { implicit conn =>
      if (database != "datomic") {
        for {
          _ <- A.?(A.i).B.i.insert(List(
            (None, 1),
            (Some(1), 1),
            (Some(1), 2),
            (Some(2), 2),
          )).transact

          // Sort initial attr first
          _ <- A.?(A.i.a2).B.i.a1.query.get.map(_ ==> List(
            (None, 1),
            (Some(1), 1),
            (Some(1), 2),
            (Some(2), 2),
          ))
          _ <- A.?(A.i.d2).B.i.a1.query.get.map(_ ==> List(
            (Some(1), 1),
            (None, 1),
            (Some(2), 2),
            (Some(1), 2),
          ))
          _ <- A.?(A.i.a2).B.i.d1.query.get.map(_ ==> List(
            (Some(1), 2),
            (Some(2), 2),
            (None, 1),
            (Some(1), 1),
          ))
          _ <- A.?(A.i.d2).B.i.d1.query.get.map(_ ==> List(
            (Some(2), 2),
            (Some(1), 2),
            (Some(1), 1),
            (None, 1),
          ))

          // Sort optional attr first
          _ <- A.?(A.i.a1).B.i.a2.query.get.map(_ ==> List(
            (None, 1),
            (Some(1), 1),
            (Some(1), 2),
            (Some(2), 2),
          ))
          _ <- A.?(A.i.a1).B.i.d2.query.get.map(_ ==> List(
            (None, 1),
            (Some(1), 2),
            (Some(1), 1),
            (Some(2), 2),
          ))
          _ <- A.?(A.i.d1).B.i.a2.query.get.map(_ ==> List(
            (Some(2), 2),
            (Some(1), 1),
            (Some(1), 2),
            (None, 1),
          ))
          _ <- A.?(A.i.d1).B.i.d2.query.get.map(_ ==> List(
            (Some(2), 2),
            (Some(1), 2),
            (Some(1), 1),
            (None, 1),
          ))
        } yield ()
      }
    }


    "Limitations" - refs { implicit conn =>
      for {
        _ <- A.?(A.i.s).B.?(B.i).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Only mandatory ref allowed after optional entity."
          }

        _ <- A.?(A.i.s).Bb.*(B.i).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Only mandatory ref allowed after optional entity."
          }

        // Attributes only for optional entity
        // Caught already during molecule buildup
        _ = interceptMessage[ModelError](
          "Only attributes allowed in optional entity."
        )(A.?(A.i.s.B.i))

        _ = interceptMessage[ModelError](
          "Only attributes allowed in optional entity."
        )(A.?(A.i.s.B.?(B.i)))

        _ = interceptMessage[ModelError](
          "Only attributes allowed in optional entity."
        )(A.?(A.i.s.Bb.*(B.i)))

        _ = interceptMessage[ModelError](
          "Only attributes allowed in optional entity."
        )(A.?(A.i.s.Bb.*?(B.i)))
      } yield ()
    }
  }
}
