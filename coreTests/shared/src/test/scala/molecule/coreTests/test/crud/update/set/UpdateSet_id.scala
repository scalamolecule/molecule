package molecule.coreTests.test.crud.update.set

import molecule.base.error._
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs.A
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateSet_id extends CoreTestSuite with ApiAsync { spi: SpiAsync =>


  override lazy val tests = Tests {

    "Update/upsert" - types { implicit conn =>
      for {
        id <- Ns.ints.insert(Set(1)).transact.map(_.id)
        _ <- Ns.ints.query.get.map(_ ==> List(Set(1)))

        _ <- Ns(id).ints(Set(2)).update.transact
        _ <- Ns.ints.query.get.map(_ ==> List(Set(2)))

        // Updating a non-asserted attribute has no effect
        _ <- Ns(id).strings(Set("a")).update.transact
        _ <- Ns.ints.strings_?.query.get.map(_ ==> List((Set(2), None)))

        // Upserting a non-asserted attribute adds the value
        _ <- Ns(id).strings(Set("a")).upsert.transact
        _ <- Ns.ints.strings_?.query.get.map(_ ==> List((Set(2), Some(Set("a")))))
      } yield ()
    }


    "Multiple entities updated" - types { implicit conn =>
      for {
        List(a, b, c) <- Ns.ints.insert(Set(1), Set(2), Set(3)).transact.map(_.ids)
        _ <- Ns.id.a1.ints.query.get.map(_ ==> List(
          (a, Set(1)),
          (b, Set(2)),
          (c, Set(3)),
        ))

        _ <- Ns(List(b, c)).ints(4).update.transact
        _ <- Ns.id.a1.ints.query.get.map(_ ==> List(
          (a, Set(1)),
          (b, Set(4)),
          (c, Set(4)),
        ))
      } yield ()
    }


    "Delete individual attribute value(s) with update" - types { implicit conn =>
      for {
        id <- Ns.ints.strings.insert(Set(1), Set("a")).transact.map(_.id)
        _ <- Ns.ints.strings.query.get.map(_ ==> List((Set(1), Set("a"))))

        // Apply empty value to delete attribute of entity (entity remains)
        _ <- Ns(id).strings().update.transact
        _ <- Ns.ints.strings_?.query.get.map(_ ==> List((Set(1), None)))
      } yield ()
    }


    "Update multiple attributes" - types { implicit conn =>
      for {
        id <- Ns.ints.strings.insert(Set(1), Set("a")).transact.map(_.id)
        _ <- Ns.ints.strings.query.get.map(_ ==> List((Set(1), Set("a"))))

        // Apply empty value to delete attribute of entity (entity remains)
        _ <- Ns(id).ints(2).strings("b").update.transact
        _ <- Ns.ints.strings.query.get.map(_ ==> List((Set(2), Set("b"))))
      } yield ()
    }


    "Referenced attributes" - refs { implicit conn =>
      for {
        id <- A.ii(Set(1)).B.ii(Set(2)).C.ii(Set(3)).save.transact.map(_.id)
        _ <- A.ii.B.ii.C.ii.query.get.map(_ ==> List((Set(1), Set(2), Set(3))))

        // A
        _ <- A(id).ii(Set(10)).update.transact
        _ <- A.ii.B.ii.C.ii.query.get.map(_ ==> List((Set(10), Set(2), Set(3))))

        // A + B
        _ <- A(id).ii(Set(11)).B.ii(Set(20)).update.transact
        _ <- A.ii.B.ii.C.ii.query.get.map(_ ==> List((Set(11), Set(20), Set(3))))

        // B
        _ <- A(id).B.ii(Set(21)).update.transact
        _ <- A.ii.B.ii.C.ii.query.get.map(_ ==> List((Set(11), Set(21), Set(3))))

        // A + B + C
        _ <- A(id).ii(Set(12)).B.ii(Set(22)).C.ii(Set(30)).update.transact
        _ <- A.ii.B.ii.C.ii.query.get.map(_ ==> List((Set(12), Set(22), Set(30))))

        // A + C
        _ <- A(id).ii(Set(13)).B.C.ii(Set(31)).update.transact
        _ <- A.ii.B.ii.C.ii.query.get.map(_ ==> List((Set(13), Set(22), Set(31))))

        // B + C
        _ <- A(id).B.ii(Set(23)).C.ii(Set(32)).update.transact
        _ <- A.ii.B.ii.C.ii.query.get.map(_ ==> List((Set(13), Set(23), Set(32))))

        // C
        _ <- A(id).B.C.ii(Set(33)).update.transact
        _ <- A.ii.B.ii.C.ii.query.get.map(_ ==> List((Set(13), Set(23), Set(33))))
      } yield ()
    }


    "Referenced attributes with backref" - refs { implicit conn =>
      for {
        id <- A.ii(Set(1)).B.ii(Set(2))._A.C.ii(Set(3)).save.transact.map(_.id)
        _ <- A.ii.B.ii._A.C.ii.query.get.map(_ ==> List((Set(1), Set(2), Set(3))))

        // Updating A.B.ii and A.C.ii
        _ <- A(id).ii(Set(10)).B.ii(Set(20))._A.C.ii(Set(30)).update.transact
        _ <- A.ii.B.ii._A.C.ii.query.get.map(_ ==> List((Set(10), Set(20), Set(30))))
      } yield ()
    }


    "Semantics" - {

      "Can't update multiple values for one card-one attribute" - types { implicit conn =>
        for {
          _ <- Ns(42).ints(Seq(Set(1), Set(2))).update.transact
            .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
              err ==> "Can only update one Set of values for Set attribute `Ns.ints`. Found: Set(1), Set(2)"
            }

          // Same as
          _ <- Ns(42).ints(Set(1), Set(2)).update.transact
            .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
              err ==> "Can only update one Set of values for Set attribute `Ns.ints`. Found: Set(1), Set(2)"
            }

          // Same as
          _ <- Ns(42).ints(1, 2).update.transact
            .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
              err ==> "Can only update one Set of values for Set attribute `Ns.ints`. Found: Set(1), Set(2)"
            }
        } yield ()
      }

      "Can't update optional values" - types { implicit conn =>
        for {
          _ <- Ns(42).ints_?(Some(Set(1))).update.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
              err ==> "Can't update optional values. Found:\n" +
                """AttrSetOptInt("Ns", "ints", Eq, Some(Seq(Set(1))), None, None, Nil, Nil, None, None, Seq(0, 22))"""
            }
        } yield ()
      }

      "Can't update card-many referenced attributes" - types { implicit conn =>
        for {
          _ <- Ns(42).i(1).Refs.i(2).update.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
              err ==> "Can't update attributes in card-many referenced namespace `Refs`"
            }
        } yield ()
      }
    }
  }
}
