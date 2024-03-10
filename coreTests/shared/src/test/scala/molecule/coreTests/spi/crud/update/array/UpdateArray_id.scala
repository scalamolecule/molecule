package molecule.coreTests.spi.crud.update.array

import molecule.base.error._
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs.A
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import molecule.coreTests.util.Array2List
import utest._

trait UpdateArray_id extends CoreTestSuite with Array2List with ApiAsync { spi: SpiAsync =>


  override lazy val tests = Tests {

    "Semantics update/upsert" - types { implicit conn =>
      for {
        id <- Ns.intArray.insert(Array(1)).transact.map(_.id)
        _ <- Ns.intArray.query.get.map(_ ==> List(Array(1)))

        _ <- Ns(id).intArray(Array(2)).update.transact
        _ <- Ns.intArray.query.get.map(_ ==> List(Array(2)))

        // Updating a non-asserted attribute has no effect
        _ <- Ns(id).stringArray(Array("a")).update.transact
        _ <- Ns.intArray.stringArray_?.query.get.map(_ ==> List((Array(2), None)))

        // Upserting a non-asserted attribute adds the value
        _ <- Ns(id).stringArray(Array("a")).upsert.transact
        _ <- Ns.intArray.stringArray_?.query.get.map(_ ==> List((Array(2), Some(Array("a")))))

        // All attributes have to be previously asserted to be updated.
        // `int` is previously asserted, `boolean` is not. So this entity is not updated
        _ <- Ns(id).intArray(Array(3)).booleanArray(Array(true)).update.transact
        _ <- Ns.intArray.booleanArray_?.query.get.map(_ ==> List((Array(2), None)))

        // Upsert sets all values regardless of previous assertions
        _ <- Ns(id).intArray(Array(3)).booleanArray(Array(true)).upsert.transact
        _ <- Ns.intArray.booleanArray_?.query.get.map(_ ==> List((Array(3), Some(Array(true)))))
      } yield ()
    }


    "Ownership (Mongo)" - refs { implicit conn =>
      if (database == "MongoDB") {
        for {
          id <- A.i(1).OwnBb.i(2).save.transact.map(_.id)

          _ <- A(id).ownBb("123456789012345678901234").update.transact
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
        List(a, b, c) <- Ns.intArray.insert(Array(1), Array(2), Array(3)).transact.map(_.ids)
        _ <- Ns.id.a1.intArray.query.get.map(_ ==> List(
          (a, Array(1)),
          (b, Array(2)),
          (c, Array(3)),
        ))

        _ <- Ns(List(b, c)).intArray(4).update.transact
        _ <- Ns.id.a1.intArray.query.get.map(_ ==> List(
          (a, Array(1)),
          (b, Array(4)),
          (c, Array(4)),
        ))
      } yield ()
    }


    "Delete individual attribute value(s) with update" - types { implicit conn =>
      for {
        id <- Ns.intArray.stringArray.insert(Array(1), Array("a")).transact.map(_.id)
        _ <- Ns.intArray.stringArray.query.get.map(_ ==> List((Array(1), Array("a"))))

        // Apply empty value to delete attribute of entity (entity remains)
        _ <- Ns(id).stringArray().update.transact
        _ <- Ns.intArray.stringArray_?.query.get.map(_ ==> List((Array(1), None)))
      } yield ()
    }


    "Update multiple attributes" - types { implicit conn =>
      for {
        id <- Ns.intArray.stringArray.insert(Array(1), Array("a")).transact.map(_.id)
        _ <- Ns.intArray.stringArray.query.get.map(_ ==> List((Array(1), Array("a"))))

        // Apply empty value to delete attribute of entity (entity remains)
        _ <- Ns(id).intArray(2).stringArray("b").update.transact
        _ <- Ns.intArray.stringArray.query.get.map(_ ==> List((Array(2), Array("b"))))
      } yield ()
    }


    "Ref" - refs { implicit conn =>
      for {
        id <- A.ia(Array(1)).B.ia(Array(2)).C.ia(Array(3)).save.transact.map(_.id)
        _ <- A.ia.B.ia.C.ia.query.get.map(_ ==> List((Array(1), Array(2), Array(3))))

        // A
        _ <- A(id).ia(Array(10)).update.transact
        _ <- A.ia.B.ia.C.ia.query.get.map(_ ==> List((Array(10), Array(2), Array(3))))

        // A + B
        _ <- A(id).ia(Array(11)).B.ia(Array(20)).update.transact
        _ <- A.ia.B.ia.C.ia.query.get.map(_ ==> List((Array(11), Array(20), Array(3))))

        // B
        _ <- A(id).B.ia(Array(21)).update.transact
        _ <- A.ia.B.ia.C.ia.query.get.map(_ ==> List((Array(11), Array(21), Array(3))))

        // A + B + C
        _ <- A(id).ia(Array(12)).B.ia(Array(22)).C.ia(Array(30)).update.transact
        _ <- A.ia.B.ia.C.ia.query.get.map(_ ==> List((Array(12), Array(22), Array(30))))

        // A + C
        _ <- A(id).ia(Array(13)).B.C.ia(Array(31)).update.transact
        _ <- A.ia.B.ia.C.ia.query.get.map(_ ==> List((Array(13), Array(22), Array(31))))

        // B + C
        _ <- A(id).B.ia(Array(23)).C.ia(Array(32)).update.transact
        _ <- A.ia.B.ia.C.ia.query.get.map(_ ==> List((Array(13), Array(23), Array(32))))

        // C
        _ <- A(id).B.C.ia(Array(33)).update.transact
        _ <- A.ia.B.ia.C.ia.query.get.map(_ ==> List((Array(13), Array(23), Array(33))))
      } yield ()
    }

    "OwnB" - refs { implicit conn =>
      for {
        id <- A.ia(Array(1)).B.ia(Array(2)).C.ia(Array(3)).save.transact.map(_.id)
        _ <- A.ia.B.ia.C.ia.query.get.map(_ ==> List((Array(1), Array(2), Array(3))))

        // A
        _ <- A(id).ia(Array(10)).update.transact
        _ <- A.ia.B.ia.C.ia.query.get.map(_ ==> List((Array(10), Array(2), Array(3))))

        // A + B
        _ <- A(id).ia(Array(11)).B.ia(Array(20)).update.transact
        _ <- A.ia.B.ia.C.ia.query.get.map(_ ==> List((Array(11), Array(20), Array(3))))

        // B
        _ <- A(id).B.ia(Array(21)).update.transact
        _ <- A.ia.B.ia.C.ia.query.get.map(_ ==> List((Array(11), Array(21), Array(3))))

        // A + B + C
        _ <- A(id).ia(Array(12)).B.ia(Array(22)).C.ia(Array(30)).update.transact
        _ <- A.ia.B.ia.C.ia.query.get.map(_ ==> List((Array(12), Array(22), Array(30))))

        // A + C
        _ <- A(id).ia(Array(13)).B.C.ia(Array(31)).update.transact
        _ <- A.ia.B.ia.C.ia.query.get.map(_ ==> List((Array(13), Array(22), Array(31))))

        // B + C
        _ <- A(id).B.ia(Array(23)).C.ia(Array(32)).update.transact
        _ <- A.ia.B.ia.C.ia.query.get.map(_ ==> List((Array(13), Array(23), Array(32))))

        // C
        _ <- A(id).B.C.ia(Array(33)).update.transact
        _ <- A.ia.B.ia.C.ia.query.get.map(_ ==> List((Array(13), Array(23), Array(33))))
      } yield ()
    }

    "OwnC" - refs { implicit conn =>
      for {
        id <- A.ia(Array(1)).B.ia(Array(2)).C.ia(Array(3)).save.transact.map(_.id)
        _ <- A.ia.B.ia.C.ia.query.get.map(_ ==> List((Array(1), Array(2), Array(3))))

        // A
        _ <- A(id).ia(Array(10)).update.transact
        _ <- A.ia.B.ia.C.ia.query.get.map(_ ==> List((Array(10), Array(2), Array(3))))

        // A + B
        _ <- A(id).ia(Array(11)).B.ia(Array(20)).update.transact
        _ <- A.ia.B.ia.C.ia.query.get.map(_ ==> List((Array(11), Array(20), Array(3))))

        // B
        _ <- A(id).B.ia(Array(21)).update.transact
        _ <- A.ia.B.ia.C.ia.query.get.map(_ ==> List((Array(11), Array(21), Array(3))))

        // A + B + C
        _ <- A(id).ia(Array(12)).B.ia(Array(22)).C.ia(Array(30)).update.transact
        _ <- A.ia.B.ia.C.ia.query.get.map(_ ==> List((Array(12), Array(22), Array(30))))

        // A + C
        _ <- A(id).ia(Array(13)).B.C.ia(Array(31)).update.transact
        _ <- A.ia.B.ia.C.ia.query.get.map(_ ==> List((Array(13), Array(22), Array(31))))

        // B + C
        _ <- A(id).B.ia(Array(23)).C.ia(Array(32)).update.transact
        _ <- A.ia.B.ia.C.ia.query.get.map(_ ==> List((Array(13), Array(23), Array(32))))

        // C
        _ <- A(id).B.C.ia(Array(33)).update.transact
        _ <- A.ia.B.ia.C.ia.query.get.map(_ ==> List((Array(13), Array(23), Array(33))))
      } yield ()
    }

    "OwnB OwnC" - refs { implicit conn =>
      for {
        id <- A.ia(Array(1)).B.ia(Array(2)).C.ia(Array(3)).save.transact.map(_.id)
        _ <- A.ia.B.ia.C.ia.query.get.map(_ ==> List((Array(1), Array(2), Array(3))))

        // A
        _ <- A(id).ia(Array(10)).update.transact
        _ <- A.ia.B.ia.C.ia.query.get.map(_ ==> List((Array(10), Array(2), Array(3))))

        // A + B
        _ <- A(id).ia(Array(11)).B.ia(Array(20)).update.transact
        _ <- A.ia.B.ia.C.ia.query.get.map(_ ==> List((Array(11), Array(20), Array(3))))

        // B
        _ <- A(id).B.ia(Array(21)).update.transact
        _ <- A.ia.B.ia.C.ia.query.get.map(_ ==> List((Array(11), Array(21), Array(3))))

        // A + B + C
        _ <- A(id).ia(Array(12)).B.ia(Array(22)).C.ia(Array(30)).update.transact
        _ <- A.ia.B.ia.C.ia.query.get.map(_ ==> List((Array(12), Array(22), Array(30))))

        // A + C
        _ <- A(id).ia(Array(13)).B.C.ia(Array(31)).update.transact
        _ <- A.ia.B.ia.C.ia.query.get.map(_ ==> List((Array(13), Array(22), Array(31))))

        // B + C
        _ <- A(id).B.ia(Array(23)).C.ia(Array(32)).update.transact
        _ <- A.ia.B.ia.C.ia.query.get.map(_ ==> List((Array(13), Array(23), Array(32))))

        // C
        _ <- A(id).B.C.ia(Array(33)).update.transact
        _ <- A.ia.B.ia.C.ia.query.get.map(_ ==> List((Array(13), Array(23), Array(33))))
      } yield ()
    }


    "Referenced attributes with backref" - refs { implicit conn =>
      for {
        id <- A.ia(Array(1)).B.ia(Array(2))._A.C.ia(Array(3)).save.transact.map(_.id)
        _ <- A.ia.B.ia._A.C.ia.query.get.map(_ ==> List((Array(1), Array(2), Array(3))))

        // Updating A.B.ia and A.C.ia
        _ <- A(id).ia(Array(10)).B.ia(Array(20))._A.C.ia(Array(30)).update.transact
        _ <- A.ia.B.ia._A.C.ia.query.get.map(_ ==> List((Array(10), Array(20), Array(30))))
      } yield ()
    }


    "Semantics" - {

      "Can't update multiple values for one card-one attribute" - types { implicit conn =>
        for {
          _ <- Ns("42").intArray(Seq(Array(1), Array(2))).update.transact
            .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
              err ==> "Can only update one Array of values for Array attribute `Ns.intArray`."
            }

          // Same as
          _ <- Ns("42").intArray(Array(1), Array(2)).update.transact
            .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
              err ==> "Can only update one Array of values for Array attribute `Ns.intArray`."
            }

          // Same as
          _ <- Ns("42").intArray(1, 2).update.transact
            .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
              err ==> "Can only update one Array of values for Array attribute `Ns.intArray`."
            }
        } yield ()
      }

      "Can't update optional values" - types { implicit conn =>
        for {
          _ <- Ns("42").intArray_?(Some(Array(1))).update.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
              err ==> "Can't update optional values. Found:\n" +
                """AttrArrOptInt("Ns", "intArray", Eq, Some(Seq(Array(1))), None, None, Nil, Nil, None, None, Seq(0, 53))"""
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
