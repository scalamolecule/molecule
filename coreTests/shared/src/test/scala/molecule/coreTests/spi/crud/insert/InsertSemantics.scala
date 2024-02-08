package molecule.coreTests.spi.crud.insert

import molecule.base.error.ModelError
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait InsertSemantics extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Empty Sets" - {

      "Alone" - refs { implicit conn =>
        for {
          _ <- A.ii.insert(Set.empty[Int]).transact

          // A.ii was not inserted
          _ <- A.ii.query.get.map(_ ==> Nil)
        } yield ()
      }

      "With other attribute" - refs { implicit conn =>
        for {
          _ <- A.i.ii.insert((1, Set.empty[Int])).transact

          // A.i was inserted
          _ <- A.i.query.get.map(_ ==> List(1))

          // A.ii was not inserted
          _ <- A.i.ii_?.query.get.map(_ ==> List((1, None)))
          _ <- A.i.ii.query.get.map(_ ==> Nil)
        } yield ()
      }

      "Alone after ref" - refs { implicit conn =>
        for {
          _ <- A.i.B.ii.insert((1, Set.empty[Int])).transact

          // A.i was inserted
          _ <- A.i.query.get.map(_ ==> List(1))

          // Relationship to B was not created since no value of B was present
          _ <- A.i_.b.query.get.map(_.size ==> 0)
          _ <- A.i.B.ii_?.query.get.map(_ ==> Nil)
          _ <- A.i.B.ii.query.get.map(_ ==> Nil)
        } yield ()
      }

      "With other attribute after ref" - refs { implicit conn =>
        for {
          _ <- A.i.B.i.ii.insert((1, 2, Set.empty[Int])).transact

          // Relationship to B was created
          _ <- A.i.B.i.query.get.map(_ ==> List((1, 2)))

          // But B.ii was not inserted
          _ <- A.i.B.i.ii_?.query.get.map(_ ==> List((1, 2, None)))
          _ <- A.i.B.i.ii.query.get.map(_ ==> Nil)
        } yield ()
      }

      "Nested alone" - refs { implicit conn =>
        for {
          _ <- A.i.Bb.*(B.ii).insert((1, List(Set.empty[Int]))).transact

          // A.i was inserted
          _ <- A.i.query.get.map(_ ==> List(1))

          // But relationships to B were not created since no value of B was present
          _ <- A.i_.bb.query.get.map(_.size ==> 0)

          _ <- A.i.Bb.*?(B.ii).query.get.map(_ ==> List((1, Nil)))
          _ <- A.i.Bb.*(B.ii).query.get.map(_ ==> Nil)

          // No optional B.ii value since there's no relationship
          _ <- A.i.Bb.ii_?.query.get.map(_ ==> Nil)
          _ <- A.i.Bb.ii.query.get.map(_ ==> Nil)
        } yield ()
      }

      "Nested with other attribute" - refs { implicit conn =>
        for {
          _ <- A.i.B.i.ii.insert((1, 2, Set.empty[Int])).transact

          // Relationship to B was created
          _ <- A.i.B.i.query.get.map(_ ==> List((1, 2)))

          // But B.ii was not inserted
          _ <- A.i.B.i.ii_?.query.get.map(_ ==> List((1, 2, None)))
          _ <- A.i.B.i.ii.query.get.map(_ ==> Nil)
        } yield ()
      }
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
