package molecule.coreTests.spi.crud.insert

import molecule.base.error.ModelError
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait InsertSemantics extends CoreTestSuite with Api_async { spi: Spi_async =>

  override lazy val tests = Tests {

    "Attribute required in each namespace" - refs { implicit conn =>
      for {
        _ <- A.B.i.insert(1).transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Please add at least 1 attribute to namespace A before relating to B"
          }

        _ <- A.Bb.i.insert(1).transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Please add at least 1 attribute to namespace A before relating to Bb"
          }

        _ <- A.Bb.*(B.i).insert(List(1)).transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Please add at least 1 attribute to namespace A before relating to Bb"
          }
      } yield ()
    }


    "Empty Sets" - {

      "Alone" - refs { implicit conn =>
        for {
          _ <- A.iSet.insert(Set.empty[Int]).transact

          // A.iSet was not inserted
          _ <- A.iSet.query.get.map(_ ==> Nil)
        } yield ()
      }

      "With other attribute" - refs { implicit conn =>
        for {
          _ <- A.i.iSet.insert((1, Set.empty[Int])).transact

          // A.i was inserted
          _ <- A.i.query.get.map(_ ==> List(1))

          // A.iSet was not inserted
          _ <- A.i.iSet_?.query.get.map(_ ==> List((1, None)))
          _ <- A.i.iSet.query.get.map(_ ==> Nil)
        } yield ()
      }

      "Optional with other attribute" - refs { implicit conn =>
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

      "Alone after ref" - refs { implicit conn =>
        for {
          _ <- A.i.B.iSet.insert((1, Set.empty[Int])).transact

          // A.i was inserted
          _ <- A.i.query.get.map(_ ==> List(1))

          // B.ii was not inserted
          _ <- A.i.B.iSet_?.query.get.map(_ ==> List((1, None)))

          // Datomic/SQL differs in whether a relationship is created
          // when inserting empty collection
          _ <- if (database == "Datomic") {
            // No relationship to B
            A.i.b_.query.get.map(_ ==> Nil)
          } else {
            // Relationship to empty row in B
            A.i.b_.query.get.map(_ ==> List(1))
          }
        } yield ()
      }

      "With other attribute after ref" - refs { implicit conn =>
        for {
          _ <- A.i.B.i.iSet.insert((1, 2, Set.empty[Int])).transact

          // Relationship to B was created
          _ <- A.i.B.i.query.get.map(_ ==> List((1, 2)))

          // But B.iSet was not inserted
          _ <- A.i.B.i.iSet_?.query.get.map(_ ==> List((1, 2, None)))
          _ <- A.i.B.i.iSet.query.get.map(_ ==> Nil)
        } yield ()
      }

      "Nested alone" - refs { implicit conn =>
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
          _ <- if (database == "Datomic") {
            // No relationship to B
            A.i.bb_.query.get.map(_ ==> Nil)
          } else {
            // Relationship to empty row in B
            A.i.bb_.query.get.map(_ ==> List(1))
          }
        } yield ()
      }

      "Nested with other attribute" - refs { implicit conn =>
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


    "Duplicate attributes not allowed, flat" - {

      "Same ns" - refs { implicit conn =>
        for {
          _ <- A.i.i.insert(1, 2).transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
              err ==> "Can't transact duplicate attribute A.i"
            }
        } yield ()
      }

      "After backref" - refs { implicit conn =>
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
    }


    "Duplicate attributes not allowed, nested" - {

      "Same ns" - refs { implicit conn =>
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

      "Backref in nested" - refs { implicit conn =>
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
    }


    "Backref in nested" - refs { implicit conn =>
      for {
        _ <- A.i.Bb.*(B.i._A.i).insert(1, List((2, 3))).transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't use backref namespace _A from here"
          }

        _ <- A.i.Bb.*?(B.i._A.i).insert(1, List((2, 3))).transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't use backref namespace _A from here"
          }

        // ok
        _ <- A.i.Bb.*(B.i.C.i._B.s).insert(1, List((2, 3, "a"))).transact
        _ <- A.i.Bb.*?(B.i.C.i._B.s).insert(1, List((2, 3, "a"))).transact
      } yield ()
    }
  }
}
