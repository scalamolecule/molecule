package molecule.coreTests.spi.crud.update.seq

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

trait UpdateSeq_id extends CoreTestSuite with ApiAsync { spi: SpiAsync =>


  override lazy val tests = Tests {

    "Semantics update/upsert" - types { implicit conn =>
      for {
        id <- Ns.intSeq.insert(List(1)).transact.map(_.id)
        _ <- Ns.intSeq.query.get.map(_ ==> List(List(1)))

        _ <- Ns(id).intSeq(List(2)).update.transact
        _ <- Ns.intSeq.query.get.map(_ ==> List(List(2)))

        // Updating a non-asserted attribute has no effect
        _ <- Ns(id).stringSeq(List("a")).update.transact
        _ <- Ns.intSeq.stringSeq_?.query.get.map(_ ==> List((List(2), None)))

        // Upserting a non-asserted attribute adds the value
        _ <- Ns(id).stringSeq(List("a")).upsert.transact
        _ <- Ns.intSeq.stringSeq_?.query.get.map(_ ==> List((List(2), Some(List("a")))))

        // All attributes have to be previously asserted to be updated.
        // `int` is previously asserted, `boolean` is not. So this entity is not updated
        _ <- Ns(id).intSeq(List(3)).booleanSeq(List(true)).update.transact
        _ <- Ns.intSeq.booleanSeq_?.query.get.map(_ ==> List((List(2), None)))

        // Upsert sets all values regardless of previous assertions
        _ <- Ns(id).intSeq(List(3)).booleanSeq(List(true)).upsert.transact
        _ <- Ns.intSeq.booleanSeq_?.query.get.map(_ ==> List((List(3), Some(List(true)))))
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
        List(a, b, c) <- Ns.intSeq.insert(List(1), List(2), List(3)).transact.map(_.ids)
        _ <- Ns.id.a1.intSeq.query.get.map(_ ==> List(
          (a, List(1)),
          (b, List(2)),
          (c, List(3)),
        ))

        _ <- Ns(List(b, c)).intSeq(Seq(4)).update.transact
        _ <- Ns.id.a1.intSeq.query.get.map(_ ==> List(
          (a, List(1)),
          (b, List(4)),
          (c, List(4)),
        ))
      } yield ()
    }


    "Delete individual attribute value(s) with update" - types { implicit conn =>
      for {
        id <- Ns.intSeq.stringSeq.insert(List(1), List("a")).transact.map(_.id)
        _ <- Ns.intSeq.stringSeq.query.get.map(_ ==> List((List(1), List("a"))))

        // Apply empty value to delete attribute of entity (entity remains)
        _ <- Ns(id).stringSeq().update.transact
        _ <- Ns.intSeq.stringSeq_?.query.get.map(_ ==> List((List(1), None)))
      } yield ()
    }


    "Update multiple attributes" - types { implicit conn =>
      for {
        id <- Ns.intSeq.stringSeq.insert(List(1), List("a")).transact.map(_.id)
        _ <- Ns.intSeq.stringSeq.query.get.map(_ ==> List((List(1), List("a"))))

        // Apply empty value to delete attribute of entity (entity remains)
        _ <- Ns(id).intSeq(Seq(2)).stringSeq(Seq("b", "c")).update.transact
        _ <- Ns.intSeq.stringSeq.query.get.map(_ ==> List((List(2), List("b", "c"))))
      } yield ()
    }


    "Ref" - refs { implicit conn =>
      for {
        id <- A.iSeq(List(1)).B.iSeq(List(2)).C.iSeq(List(3)).save.transact.map(_.id)
        _ <- A.iSeq.B.iSeq.C.iSeq.query.get.map(_ ==> List((List(1), List(2), List(3))))

        // A
        _ <- A(id).iSeq(List(10)).update.transact
        _ <- A.iSeq.B.iSeq.C.iSeq.query.get.map(_ ==> List((List(10), List(2), List(3))))

        // A + B
        _ <- A(id).iSeq(List(11)).B.iSeq(List(20)).update.transact
        _ <- A.iSeq.B.iSeq.C.iSeq.query.get.map(_ ==> List((List(11), List(20), List(3))))

        // B
        _ <- A(id).B.iSeq(List(21)).update.transact
        _ <- A.iSeq.B.iSeq.C.iSeq.query.get.map(_ ==> List((List(11), List(21), List(3))))

        // A + B + C
        _ <- A(id).iSeq(List(12)).B.iSeq(List(22)).C.iSeq(List(30)).update.transact
        _ <- A.iSeq.B.iSeq.C.iSeq.query.get.map(_ ==> List((List(12), List(22), List(30))))

        // A + C
        _ <- A(id).iSeq(List(13)).B.C.iSeq(List(31)).update.transact
        _ <- A.iSeq.B.iSeq.C.iSeq.query.get.map(_ ==> List((List(13), List(22), List(31))))

        // B + C
        _ <- A(id).B.iSeq(List(23)).C.iSeq(List(32)).update.transact
        _ <- A.iSeq.B.iSeq.C.iSeq.query.get.map(_ ==> List((List(13), List(23), List(32))))

        // C
        _ <- A(id).B.C.iSeq(List(33)).update.transact
        _ <- A.iSeq.B.iSeq.C.iSeq.query.get.map(_ ==> List((List(13), List(23), List(33))))
      } yield ()
    }

    "OwnB" - refs { implicit conn =>
      for {
        id <- A.iSeq(List(1)).B.iSeq(List(2)).C.iSeq(List(3)).save.transact.map(_.id)
        _ <- A.iSeq.B.iSeq.C.iSeq.query.get.map(_ ==> List((List(1), List(2), List(3))))

        // A
        _ <- A(id).iSeq(List(10)).update.transact
        _ <- A.iSeq.B.iSeq.C.iSeq.query.get.map(_ ==> List((List(10), List(2), List(3))))

        // A + B
        _ <- A(id).iSeq(List(11)).B.iSeq(List(20)).update.transact
        _ <- A.iSeq.B.iSeq.C.iSeq.query.get.map(_ ==> List((List(11), List(20), List(3))))

        // B
        _ <- A(id).B.iSeq(List(21)).update.transact
        _ <- A.iSeq.B.iSeq.C.iSeq.query.get.map(_ ==> List((List(11), List(21), List(3))))

        // A + B + C
        _ <- A(id).iSeq(List(12)).B.iSeq(List(22)).C.iSeq(List(30)).update.transact
        _ <- A.iSeq.B.iSeq.C.iSeq.query.get.map(_ ==> List((List(12), List(22), List(30))))

        // A + C
        _ <- A(id).iSeq(List(13)).B.C.iSeq(List(31)).update.transact
        _ <- A.iSeq.B.iSeq.C.iSeq.query.get.map(_ ==> List((List(13), List(22), List(31))))

        // B + C
        _ <- A(id).B.iSeq(List(23)).C.iSeq(List(32)).update.transact
        _ <- A.iSeq.B.iSeq.C.iSeq.query.get.map(_ ==> List((List(13), List(23), List(32))))

        // C
        _ <- A(id).B.C.iSeq(List(33)).update.transact
        _ <- A.iSeq.B.iSeq.C.iSeq.query.get.map(_ ==> List((List(13), List(23), List(33))))
      } yield ()
    }

    "OwnC" - refs { implicit conn =>
      for {
        id <- A.iSeq(List(1)).B.iSeq(List(2)).C.iSeq(List(3)).save.transact.map(_.id)
        _ <- A.iSeq.B.iSeq.C.iSeq.query.get.map(_ ==> List((List(1), List(2), List(3))))

        // A
        _ <- A(id).iSeq(List(10)).update.transact
        _ <- A.iSeq.B.iSeq.C.iSeq.query.get.map(_ ==> List((List(10), List(2), List(3))))

        // A + B
        _ <- A(id).iSeq(List(11)).B.iSeq(List(20)).update.transact
        _ <- A.iSeq.B.iSeq.C.iSeq.query.get.map(_ ==> List((List(11), List(20), List(3))))

        // B
        _ <- A(id).B.iSeq(List(21)).update.transact
        _ <- A.iSeq.B.iSeq.C.iSeq.query.get.map(_ ==> List((List(11), List(21), List(3))))

        // A + B + C
        _ <- A(id).iSeq(List(12)).B.iSeq(List(22)).C.iSeq(List(30)).update.transact
        _ <- A.iSeq.B.iSeq.C.iSeq.query.get.map(_ ==> List((List(12), List(22), List(30))))

        // A + C
        _ <- A(id).iSeq(List(13)).B.C.iSeq(List(31)).update.transact
        _ <- A.iSeq.B.iSeq.C.iSeq.query.get.map(_ ==> List((List(13), List(22), List(31))))

        // B + C
        _ <- A(id).B.iSeq(List(23)).C.iSeq(List(32)).update.transact
        _ <- A.iSeq.B.iSeq.C.iSeq.query.get.map(_ ==> List((List(13), List(23), List(32))))

        // C
        _ <- A(id).B.C.iSeq(List(33)).update.transact
        _ <- A.iSeq.B.iSeq.C.iSeq.query.get.map(_ ==> List((List(13), List(23), List(33))))
      } yield ()
    }

    "OwnB OwnC" - refs { implicit conn =>
      for {
        id <- A.iSeq(List(1)).B.iSeq(List(2)).C.iSeq(List(3)).save.transact.map(_.id)
        _ <- A.iSeq.B.iSeq.C.iSeq.query.get.map(_ ==> List((List(1), List(2), List(3))))

        // A
        _ <- A(id).iSeq(List(10)).update.transact
        _ <- A.iSeq.B.iSeq.C.iSeq.query.get.map(_ ==> List((List(10), List(2), List(3))))

        // A + B
        _ <- A(id).iSeq(List(11)).B.iSeq(List(20)).update.transact
        _ <- A.iSeq.B.iSeq.C.iSeq.query.get.map(_ ==> List((List(11), List(20), List(3))))

        // B
        _ <- A(id).B.iSeq(List(21)).update.transact
        _ <- A.iSeq.B.iSeq.C.iSeq.query.get.map(_ ==> List((List(11), List(21), List(3))))

        // A + B + C
        _ <- A(id).iSeq(List(12)).B.iSeq(List(22)).C.iSeq(List(30)).update.transact
        _ <- A.iSeq.B.iSeq.C.iSeq.query.get.map(_ ==> List((List(12), List(22), List(30))))

        // A + C
        _ <- A(id).iSeq(List(13)).B.C.iSeq(List(31)).update.transact
        _ <- A.iSeq.B.iSeq.C.iSeq.query.get.map(_ ==> List((List(13), List(22), List(31))))

        // B + C
        _ <- A(id).B.iSeq(List(23)).C.iSeq(List(32)).update.transact
        _ <- A.iSeq.B.iSeq.C.iSeq.query.get.map(_ ==> List((List(13), List(23), List(32))))

        // C
        _ <- A(id).B.C.iSeq(List(33)).update.transact
        _ <- A.iSeq.B.iSeq.C.iSeq.query.get.map(_ ==> List((List(13), List(23), List(33))))
      } yield ()
    }


    "Referenced attributes with backref" - refs { implicit conn =>
      for {
        id <- A.iSeq(List(1)).B.iSeq(List(2))._A.C.iSeq(List(3)).save.transact.map(_.id)
        _ <- A.iSeq.B.iSeq._A.C.iSeq.query.get.map(_ ==> List((List(1), List(2), List(3))))

        // Updating A.B.iSeq and A.C.iSeq
        _ <- A(id).iSeq(List(10)).B.iSeq(List(20))._A.C.iSeq(List(30)).update.transact
        _ <- A.iSeq.B.iSeq._A.C.iSeq.query.get.map(_ ==> List((List(10), List(20), List(30))))
      } yield ()
    }


    "Semantics" - {

      "Can't update multiple values for one card-one attribute" - types { implicit conn =>
        for {
          _ <- Ns("42").intSeq(Seq(Seq(1), Seq(2))).update.transact
            .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
              err ==> "Can only update one Seq of values for Seq attribute `Ns.intSeq`."
            }

          // Same as
          _ <- Ns("42").intSeq(Seq(1), Seq(2)).update.transact
            .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
              err ==> "Can only update one Seq of values for Seq attribute `Ns.intSeq`."
            }

          // Same as
          _ <- Ns("42").intSeq(Seq(1), Seq(2)).update.transact
            .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
              err ==> "Can only update one Seq of values for Seq attribute `Ns.intSeq`."
            }
        } yield ()
      }

      "Can't update optional values" - types { implicit conn =>
        for {
          _ <- Ns("42").intSeq_?(Some(List(1))).update.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
              err ==> "Can't update optional values. Found:\n" +
                """AttrSeqOptInt("Ns", "intSeq", Eq, Some(Seq(Seq(1))), None, None, Nil, Nil, None, None, Seq(0, 53))"""
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
