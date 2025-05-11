package molecule.db.compliance.test.action.insert

import molecule.db.compliance.setup.*
import molecule.db.base.error.ModelError
import molecule.db.compliance.setup.{DbProviders, Test, TestUtils}
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.domains.dsl.Refs.*

case class InsertSemantics(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "Attribute required for each entity" - refs { implicit conn =>
    for {
      _ <- A.B.i.insert(1).transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Please add at least 1 attribute to entity A before relating to B"
        }

      _ <- A.Bb.i.insert(1).transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Please add at least 1 attribute to entity A before relating to Bb"
        }

      _ <- A.Bb.*(B.i).insert(List(1)).transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Please add at least 1 attribute to entity A before relating to Bb"
        }
    } yield ()
  }


  "Empty Sets: Alone" - refs { implicit conn =>
    for {
      _ <- A.iSet.insert(Set.empty[Int]).transact

      // A.iSet was not inserted
      _ <- A.iSet.query.get.map(_ ==> Nil)
    } yield ()
  }

  "Empty Sets: With other attribute" - refs { implicit conn =>
    for {
      _ <- A.i.iSet.insert((1, Set.empty[Int])).transact

      // A.i was inserted
      _ <- A.i.query.get.map(_ ==> List(1))

      // A.iSet was not inserted
      _ <- A.i.iSet_?.query.get.map(_ ==> List((1, None)))
      _ <- A.i.iSet.query.get.map(_ ==> Nil)
    } yield ()
  }

  "Empty Sets: Optional with other attribute" - refs { implicit conn =>
    for {
      List(a, b, c, d) <- A.i.iSet_?.insert(
        (1, None),
        (1, Some(Set(2))),
        (1, Some(Set(3))),
        (2, Some(Set(4, 5))),
      ).transact.map(_.ids)

      _ <- A.i.a1.iSet_?.query.get.map(_ ==> List(
        // (1, None), // coalesced with Set(2) and Set(3)
        (1, Some(Set(2, 3))), // coalesced Set(2) and Set(3)
        (2, Some(Set(4, 5))),
      ))

      _ <- A.i.a1.iSet.query.get.map(_ ==> List(
        (1, Set(2, 3)),
        (2, Set(4, 5)),
      ))

      _ <- A.id.a1.i.iSet_?.query.get.map(_ ==> List(
        (a, 1, None),
        (b, 1, Some(Set(2))),
        (c, 1, Some(Set(3))),
        (d, 2, Some(Set(4, 5))),
      ))
    } yield ()
  }

  "Empty Sets: Alone after ref" - refs { implicit conn =>
    for {
      _ <- A.i.B.iSet.insert((1, Set.empty[Int])).transact

      // A.i was inserted
      _ <- A.i.query.get.map(_ ==> List(1))

      // B.ii was not inserted
      _ <- A.i.B.iSet_?.query.get.map(_ ==> List((1, None)))

      // Datomic/SQL differs in whether a relationship is created
      // when inserting empty collection
      _ <- if (database == "datomic") {
        // No relationship to B
        A.i.b_.query.get.map(_ ==> Nil)
      } else {
        // Relationship to empty row in B
        A.i.b_.query.get.map(_ ==> List(1))
      }
    } yield ()
  }

  "Empty Sets: With other attribute after ref" - refs { implicit conn =>
    for {
      _ <- A.i.B.i.iSet.insert((1, 2, Set.empty[Int])).transact

      // Relationship to B was created
      _ <- A.i.B.i.query.get.map(_ ==> List((1, 2)))

      // But B.iSet was not inserted
      _ <- A.i.B.i.iSet_?.query.get.map(_ ==> List((1, 2, None)))
      _ <- A.i.B.i.iSet.query.get.map(_ ==> Nil)
    } yield ()
  }

  "Empty Sets: Nested alone" - refs { implicit conn =>
    for {
      _ <- A.i.Bb.*(B.iSet).insert((1, List(Set.empty[Int]))).transact

      // A.i was inserted
      _ <- A.i.query.get.map(_ ==> List(1))

      _ <- A.i.Bb.*?(B.iSet).query.get.map(_ ==> List((1, Nil)))
      _ <- A.i.Bb.*(B.iSet).query.get.map(_ ==> Nil)

      // B.iSet was not inserted
      _ <- A.i.Bb.iSet_?.query.get.map(_ ==> List((1, None)))
      _ <- A.i.Bb.iSet.query.get.map(_ ==> Nil)

      // Datomic/SQL differs in whether a relationship is created
      // when inserting empty collection
      _ <- if (database == "datomic") {
        // No relationship to B
        A.i.bb_.query.get.map(_ ==> Nil)
      } else {
        // Relationship to empty row in B
        A.i.bb_.query.get.map(_ ==> List(1))
      }
    } yield ()
  }

  "Empty Sets: Nested with other attribute" - refs { implicit conn =>
    for {
      _ <- A.i.Bb.*(B.i.iSet).insert((1, List((2, Set.empty[Int])))).transact

      // A.i was inserted
      _ <- A.i.query.get.map(_ ==> List(1))

      _ <- A.i.Bb.*?(B.i.iSet).query.get.map(_ ==> List((1, Nil)))
      _ <- A.i.Bb.*(B.i.iSet).query.get.map(_ ==> Nil)

      // No optional B.iSet value
      _ <- A.i.Bb.i.iSet_?.query.get.map(_ ==> List((1, 2, None)))
      _ <- A.i.Bb.i.iSet.query.get.map(_ ==> Nil)
    } yield ()
  }


  "Optional Nested" - refs { implicit conn =>
    for {
      _ <- A.i.Bb.*(B.i).insert(
        (1, List(1, 2)),
        (2, Nil),
      ).transact.map(_.ids)

      // Same as using optional nested notation
      _ <- A.i.Bb.*?(B.i).insert(
        (3, List(3, 4)),
        (4, Nil),
      ).transact.map(_.ids)

      _ <- A.i.a1.Bb.*?(B.i.a1).query.get.map(_ ==> List(
        (1, List(1, 2)),
        (2, Nil),
        (3, List(3, 4)),
        (4, Nil),
      ))
    } yield ()
  }


  "Duplicate attributes not allowed, flat: Same entity" - refs { implicit conn =>
    for {
      _ <- A.i.i.insert(1, 2).transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Can't transact duplicate attribute A.i"
        }
    } yield ()
  }

  "Duplicate attributes not allowed, flat: After backref" - refs { implicit conn =>
    for {
      _ <- A.i.B.i._A.i.insert(1, 2, 3).transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Can't transact duplicate attribute A.i"
        }

      _ <- A.i.B.i.C.i._B.i.insert(1, 2, 3, 4).transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Can't transact duplicate attribute B.i"
        }

      _ <- A.i.B.i.C.i._B._A.i.insert(1, 2, 3, 4).transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Can't transact duplicate attribute A.i"
        }
    } yield ()
  }


  "Duplicate attributes not allowed, nested: Same entity" - refs { implicit conn =>
    for {
      _ <- A.i.Bb.*(B.i.i).insert(1, List((2, 3))).transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Can't transact duplicate attribute B.i"
        }

      _ <- A.i.Bb.*?(B.i.i).insert(1, List((2, 3))).transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Can't transact duplicate attribute B.i"
        }
    } yield ()
  }

  "Duplicate attributes not allowed, nested: Backref in nested" - refs { implicit conn =>
    for {
      _ <- A.i.Bb.*(B.i.C.i._B.i).insert(1, List((2, 3, 4))).transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Can't transact duplicate attribute B.i"
        }

      _ <- A.i.Bb.*?(B.i.C.i._B.i).insert(1, List((2, 3, 4))).transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Can't transact duplicate attribute B.i"
        }
    } yield ()
  }


  "Backref in nested" - refs { implicit conn =>
    for {
      _ <- A.i.Bb.*(B.i._A.i).insert(1, List((2, 3))).transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Can't use backref entity _A from here"
        }

      _ <- A.i.Bb.*?(B.i._A.i).insert(1, List((2, 3))).transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Can't use backref entity _A from here"
        }

      // ok
      _ <- A.i.Bb.*(B.i.C.i._B.s).insert(1, List((2, 3, "a"))).transact
      _ <- A.i.Bb.*?(B.i.C.i._B.s).insert(1, List((2, 3, "a"))).transact
    } yield ()
  }
}
