package molecule.coreTests.spi.crud.update.set

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

    "Semantics update/upsert" - types { implicit conn =>
      for {
        id <- Ns.intSet.insert(Set(1)).transact.map(_.id)
        _ <- Ns.intSet.query.get.map(_ ==> List(Set(1)))

        _ <- Ns(id).intSet(Set(2)).update.transact
        _ <- Ns.intSet.query.get.map(_ ==> List(Set(2)))

        // Updating a non-asserted attribute has no effect
        _ <- Ns(id).stringSet(Set("a")).update.transact
        _ <- Ns.intSet.stringSet_?.query.get.map(_ ==> List((Set(2), None)))

        // Upserting a non-asserted attribute adds the value
        _ <- Ns(id).stringSet(Set("a")).upsert.transact
        _ <- Ns.intSet.stringSet_?.query.get.map(_ ==> List((Set(2), Some(Set("a")))))

        // All attributes have to be previously asserted to be updated.
        // `int` is previously asserted, `boolean` is not. So this entity is not updated
        _ <- Ns(id).intSet(Set(3)).booleanSet(Set(true)).update.transact
        _ <- Ns.intSet.booleanSet_?.query.get.map(_ ==> List((Set(2), None)))

        // Upsert sets all values regardless of previous assertions
        _ <- Ns(id).intSet(Set(3)).booleanSet(Set(true)).upsert.transact
        _ <- Ns.intSet.booleanSet_?.query.get.map(_ ==> List((Set(3), Some(Set(true)))))
      } yield ()
    }


    "Ownership (Mongo)" - refs { implicit conn =>
      if (database == "MongoDB") {
        for {
          id <- A.i(1).OwnBb.i(2).save.transact.map(_.id)

          _ <- A(id).ownBb(Set("123456789012345678901234")).update.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
              err ==> "Can't update non-existing ids of embedded documents in MongoDB."
            }
          _ <- A.i.ownBb_?.query.get
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
              err ==> "Can't query for non-existing set of ids of embedded documents in MongoDB."
            }
        } yield ()
      }
    }


    "Multiple entities updated" - types { implicit conn =>
      for {
        List(a, b, c) <- Ns.intSet.insert(Set(1), Set(2), Set(3)).transact.map(_.ids)
        _ <- Ns.id.a1.intSet.query.get.map(_ ==> List(
          (a, Set(1)),
          (b, Set(2)),
          (c, Set(3)),
        ))

        _ <- Ns(List(b, c)).intSet(Set(4)).update.transact
        _ <- Ns.id.a1.intSet.query.get.map(_ ==> List(
          (a, Set(1)),
          (b, Set(4)),
          (c, Set(4)),
        ))
      } yield ()
    }


    "Delete set" - types { implicit conn =>
      for {
        id <- Ns.intSet.stringSet.insert(Set(1), Set("a")).transact.map(_.id)
        _ <- Ns.intSet.stringSet.query.get.map(_ ==> List((Set(1), Set("a"))))

        // Apply empty value to delete attribute of entity (entity remains)
        _ <- Ns(id).stringSet.apply().update.transact
        _ <- Ns.intSet.stringSet_?.query.get.map(_ ==> List((Set(1), None)))
      } yield ()
    }


    "Update set" - types { implicit conn =>
      for {
        id <- Ns.intSet.stringSet.insert(Set(1), Set("a")).transact.map(_.id)
        _ <- Ns.intSet.stringSet.query.get.map(_ ==> List((Set(1), Set("a"))))

        // Apply new value(s) to replace old value(s)
        _ <- Ns(id).intSet.apply(Set(2)).stringSet.apply(Set("b", "c")).update.transact
        _ <- Ns.intSet.stringSet.query.get.map(_ ==> List((Set(2), Set("b", "c"))))
      } yield ()
    }


    "Ref" - refs { implicit conn =>
      for {
        id <- A.iSet(Set(1)).B.iSet(Set(2)).C.iSet(Set(3)).save.transact.map(_.id)
        _ <- A.iSet.B.iSet.C.iSet.query.get.map(_ ==> List((Set(1), Set(2), Set(3))))

        // A
        _ <- A(id).iSet(Set(10)).update.transact
        _ <- A.iSet.B.iSet.C.iSet.query.get.map(_ ==> List((Set(10), Set(2), Set(3))))

        // A + B
        _ <- A(id).iSet(Set(11)).B.iSet(Set(20)).update.transact
        _ <- A.iSet.B.iSet.C.iSet.query.get.map(_ ==> List((Set(11), Set(20), Set(3))))

        // B
        _ <- A(id).B.iSet(Set(21)).update.transact
        _ <- A.iSet.B.iSet.C.iSet.query.get.map(_ ==> List((Set(11), Set(21), Set(3))))

        // A + B + C
        _ <- A(id).iSet(Set(12)).B.iSet(Set(22)).C.iSet(Set(30)).update.transact
        _ <- A.iSet.B.iSet.C.iSet.query.get.map(_ ==> List((Set(12), Set(22), Set(30))))

        // A + C
        _ <- A(id).iSet(Set(13)).B.C.iSet(Set(31)).update.transact
        _ <- A.iSet.B.iSet.C.iSet.query.get.map(_ ==> List((Set(13), Set(22), Set(31))))

        // B + C
        _ <- A(id).B.iSet(Set(23)).C.iSet(Set(32)).update.transact
        _ <- A.iSet.B.iSet.C.iSet.query.get.map(_ ==> List((Set(13), Set(23), Set(32))))

        // C
        _ <- A(id).B.C.iSet(Set(33)).update.transact
        _ <- A.iSet.B.iSet.C.iSet.query.get.map(_ ==> List((Set(13), Set(23), Set(33))))
      } yield ()
    }

    "OwnB" - refs { implicit conn =>
      for {
        id <- A.iSet(Set(1)).OwnB.iSet(Set(2)).C.iSet(Set(3)).save.transact.map(_.id)
        _ <- A.iSet.OwnB.iSet.C.iSet.query.get.map(_ ==> List((Set(1), Set(2), Set(3))))

        // A
        _ <- A(id).iSet(Set(10)).update.transact
        _ <- A.iSet.OwnB.iSet.C.iSet.query.get.map(_ ==> List((Set(10), Set(2), Set(3))))

        // A + B
        _ <- A(id).iSet(Set(11)).OwnB.iSet(Set(20)).update.transact
        _ <- A.iSet.OwnB.iSet.C.iSet.query.get.map(_ ==> List((Set(11), Set(20), Set(3))))

        // B
        _ <- A(id).OwnB.iSet(Set(21)).update.transact
        _ <- A.iSet.OwnB.iSet.C.iSet.query.get.map(_ ==> List((Set(11), Set(21), Set(3))))

        // A + B + C
        _ <- A(id).iSet(Set(12)).OwnB.iSet(Set(22)).C.iSet(Set(30)).update.transact
        _ <- A.iSet.OwnB.iSet.C.iSet.query.get.map(_ ==> List((Set(12), Set(22), Set(30))))

        // A + C
        _ <- A(id).iSet(Set(13)).OwnB.C.iSet(Set(31)).update.transact
        _ <- A.iSet.OwnB.iSet.C.iSet.query.get.map(_ ==> List((Set(13), Set(22), Set(31))))

        // B + C
        _ <- A(id).OwnB.iSet(Set(23)).C.iSet(Set(32)).update.transact
        _ <- A.iSet.OwnB.iSet.C.iSet.query.get.map(_ ==> List((Set(13), Set(23), Set(32))))

        // C
        _ <- A(id).OwnB.C.iSet(Set(33)).update.transact
        _ <- A.iSet.OwnB.iSet.C.iSet.query.get.map(_ ==> List((Set(13), Set(23), Set(33))))
      } yield ()
    }

    "OwnC" - refs { implicit conn =>
      for {
        id <- A.iSet(Set(1)).B.iSet(Set(2)).OwnC.iSet(Set(3)).save.transact.map(_.id)
        _ <- A.iSet.B.iSet.OwnC.iSet.query.get.map(_ ==> List((Set(1), Set(2), Set(3))))

        // A
        _ <- A(id).iSet(Set(10)).update.transact
        _ <- A.iSet.B.iSet.OwnC.iSet.query.get.map(_ ==> List((Set(10), Set(2), Set(3))))

        // A + B
        _ <- A(id).iSet(Set(11)).B.iSet(Set(20)).update.transact
        _ <- A.iSet.B.iSet.OwnC.iSet.query.get.map(_ ==> List((Set(11), Set(20), Set(3))))

        // B
        _ <- A(id).B.iSet(Set(21)).update.transact
        _ <- A.iSet.B.iSet.OwnC.iSet.query.get.map(_ ==> List((Set(11), Set(21), Set(3))))

        // A + B + C
        _ <- A(id).iSet(Set(12)).B.iSet(Set(22)).OwnC.iSet(Set(30)).update.transact
        _ <- A.iSet.B.iSet.OwnC.iSet.query.get.map(_ ==> List((Set(12), Set(22), Set(30))))

        // A + C
        _ <- A(id).iSet(Set(13)).B.OwnC.iSet(Set(31)).update.transact
        _ <- A.iSet.B.iSet.OwnC.iSet.query.get.map(_ ==> List((Set(13), Set(22), Set(31))))

        // B + C
        _ <- A(id).B.iSet(Set(23)).OwnC.iSet(Set(32)).update.transact
        _ <- A.iSet.B.iSet.OwnC.iSet.query.get.map(_ ==> List((Set(13), Set(23), Set(32))))

        // C
        _ <- A(id).B.OwnC.iSet(Set(33)).update.transact
        _ <- A.iSet.B.iSet.OwnC.iSet.query.get.map(_ ==> List((Set(13), Set(23), Set(33))))
      } yield ()
    }

    "OwnB OwnC" - refs { implicit conn =>
      for {
        id <- A.iSet(Set(1)).OwnB.iSet(Set(2)).OwnC.iSet(Set(3)).save.transact.map(_.id)
        _ <- A.iSet.OwnB.iSet.OwnC.iSet.query.get.map(_ ==> List((Set(1), Set(2), Set(3))))

        // A
        _ <- A(id).iSet(Set(10)).update.transact
        _ <- A.iSet.OwnB.iSet.OwnC.iSet.query.get.map(_ ==> List((Set(10), Set(2), Set(3))))

        // A + B
        _ <- A(id).iSet(Set(11)).OwnB.iSet(Set(20)).update.transact
        _ <- A.iSet.OwnB.iSet.OwnC.iSet.query.get.map(_ ==> List((Set(11), Set(20), Set(3))))

        // B
        _ <- A(id).OwnB.iSet(Set(21)).update.transact
        _ <- A.iSet.OwnB.iSet.OwnC.iSet.query.get.map(_ ==> List((Set(11), Set(21), Set(3))))

        // A + B + C
        _ <- A(id).iSet(Set(12)).OwnB.iSet(Set(22)).OwnC.iSet(Set(30)).update.transact
        _ <- A.iSet.OwnB.iSet.OwnC.iSet.query.get.map(_ ==> List((Set(12), Set(22), Set(30))))

        // A + C
        _ <- A(id).iSet(Set(13)).OwnB.OwnC.iSet(Set(31)).update.transact
        _ <- A.iSet.OwnB.iSet.OwnC.iSet.query.get.map(_ ==> List((Set(13), Set(22), Set(31))))

        // B + C
        _ <- A(id).OwnB.iSet(Set(23)).OwnC.iSet(Set(32)).update.transact
        _ <- A.iSet.OwnB.iSet.OwnC.iSet.query.get.map(_ ==> List((Set(13), Set(23), Set(32))))

        // C
        _ <- A(id).OwnB.OwnC.iSet(Set(33)).update.transact
        _ <- A.iSet.OwnB.iSet.OwnC.iSet.query.get.map(_ ==> List((Set(13), Set(23), Set(33))))
      } yield ()
    }


    "Referenced attributes with backref" - refs { implicit conn =>
      for {
        id <- A.iSet(Set(1)).B.iSet(Set(2))._A.C.iSet(Set(3)).save.transact.map(_.id)
        _ <- A.iSet.B.iSet._A.C.iSet.query.get.map(_ ==> List((Set(1), Set(2), Set(3))))

        // Updating A.B.iSet and A.C.iSet
        _ <- A(id).iSet(Set(10)).B.iSet(Set(20))._A.C.iSet(Set(30)).update.transact
        _ <- A.iSet.B.iSet._A.C.iSet.query.get.map(_ ==> List((Set(10), Set(20), Set(30))))
      } yield ()
    }


    "Semantics" - {

      "Can't update multiple values for one card-one attribute" - types { implicit conn =>
        for {
          _ <- Ns("42").intSet(Seq(Set(1), Set(2))).update.transact
            .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
              err ==> "Can only update one Set of values for Set attribute `Ns.intSet`. Found: Set(1), Set(2)"
            }

          // Same as
          _ <- Ns("42").intSet(Set(1), Set(2)).update.transact
            .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
              err ==> "Can only update one Set of values for Set attribute `Ns.intSet`. Found: Set(1), Set(2)"
            }
        } yield ()
      }

      "Can't update optional values" - types { implicit conn =>
        for {
          _ <- Ns("42").intSet_?(Some(Set(1))).update.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
              err ==> "Can't update optional values. Found:\n" +
                """AttrSetOptInt("Ns", "intSet", Eq, Some(Seq(Set(1))), None, None, Nil, Nil, None, None, Seq(0, 31))"""
            }
        } yield ()
      }

      "Can't update card-many referenced attributes" - types { implicit conn =>
        for {
          _ <- Ns("42").i(1).Refs.i(2).update.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
              err ==> "Can't update attributes in card-many referenced namespace `Refs`"
            }
        } yield ()
      }
    }
  }
}
