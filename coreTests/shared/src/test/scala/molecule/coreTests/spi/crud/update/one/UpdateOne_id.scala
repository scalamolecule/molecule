package molecule.coreTests.spi.crud.update.one

import molecule.base.error._
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateOne_id extends CoreTestSuite with ApiAsync { spi: SpiAsync =>


  override lazy val tests = Tests {

    "Multiple entities updated" - types { implicit conn =>
      for {
        List(a, b, c) <- Ns.int.insert(1, 2, 3).transact.map(_.ids)
        _ <- Ns.id.a1.int.query.get.map(_ ==> List(
          (a, 1),
          (b, 2),
          (c, 3),
        ))

        _ <- Ns(List(b, c)).int(4).update.transact
        _ <- Ns.id.a1.int.query.get.map(_ ==> List(
          (a, 1),
          (b, 4),
          (c, 4),
        ))
      } yield ()
    }


    "Delete individual attribute value(s) with update" - types { implicit conn =>
      for {
        id <- Ns.int.string.insert(1, "a").transact.map(_.id)
        _ <- Ns.int.string.query.get.map(_ ==> List((1, "a")))

        // Apply empty value to delete attribute of entity (entity remains)
        _ <- Ns(id).string().update.transact
        _ <- Ns.int.string_?.query.get.map(_ ==> List((1, None)))
      } yield ()
    }


    "Delete individual ref value(s) with update" - refs { implicit conn =>
      for {
        refId <- B.i(7).save.transact.map(_.id)
        id <- A.i.b.insert(1, refId).transact.map(_.id)
        _ <- A.i.b.query.get.map(_ ==> List((1, refId)))

        // Apply empty value to delete ref id of entity (entity remains)
        _ <- A(id).b().update.transact
        _ <- A.i.b_?.query.get.map(_ ==> List((1, None)))
      } yield ()
    }

    "Delete individual owned ref value(s) with update" - refs { implicit conn =>
      for {
        id <- A.i(1).OwnB.i(7).save.transact.map(_.id)
        _ <- A.i.OwnB.i.query.get.map(_ ==> List((1, 7)))

        // Apply empty value to delete ref id of entity (entity remains)
        _ <- A(id).ownB().update.transact
        _ <- A.i.ownB_?.query.get.map(_ ==> List((1, None)))
      } yield ()
    }


    "Update multiple attributes" - types { implicit conn =>
      for {
        id <- Ns.int.string.insert(1, "a").transact.map(_.id)
        _ <- Ns.int.string.query.get.map(_ ==> List((1, "a")))

        _ <- Ns(id).int(2).string("b").update.transact
        _ <- Ns.int.string.query.get.map(_ ==> List((2, "b")))
      } yield ()
    }


    "ref ref" - refs { implicit conn =>
      for {
        id <- A.i(1).B.i(2).C.i(3).save.transact.map(_.id)
        _ <- A.i.B.i.C.i.query.get.map(_ ==> List((1, 2, 3)))

        // A
        _ <- A(id).i(10).update.transact
        _ <- A.i.B.i.C.i.query.get.map(_ ==> List((10, 2, 3)))

        // A + B
        _ <- A(id).i(11).B.i(20).update.transact
        _ <- A.i.B.i.C.i.query.get.map(_ ==> List((11, 20, 3)))

        // B
        _ <- A(id).B.i(21).update.transact
        _ <- A.i.B.i.C.i.query.get.map(_ ==> List((11, 21, 3)))

        // A + B + C
        _ <- A(id).i(12).B.i(22).C.i(30).update.transact
        _ <- A.i.B.i.C.i.query.get.map(_ ==> List((12, 22, 30)))

        // A + C
        _ <- A(id).i(13).B.C.i(31).update.transact
        _ <- A.i.B.i.C.i.query.get.map(_ ==> List((13, 22, 31)))

        // B + C
        _ <- A(id).B.i(23).C.i(32).update.transact
        _ <- A.i.B.i.C.i.query.get.map(_ ==> List((13, 23, 32)))

        // C
        _ <- A(id).B.C.i(33).update.transact
        _ <- A.i.B.i.C.i.query.get.map(_ ==> List((13, 23, 33)))
      } yield ()
    }

    "own ref" - refs { implicit conn =>
      for {
        id <- A.i(1).OwnB.i(2).C.i(3).save.transact.map(_.id)
        _ <- A.i.OwnB.i.C.i.query.get.map(_ ==> List((1, 2, 3)))

        // A
        _ <- A(id).i(10).update.transact
        _ <- A.i.OwnB.i.C.i.query.get.map(_ ==> List((10, 2, 3)))

        // A + B
        _ <- A(id).i(11).OwnB.i(20).update.transact
        _ <- A.i.OwnB.i.C.i.query.get.map(_ ==> List((11, 20, 3)))

        // B
        _ <- A(id).OwnB.i(21).update.transact
        _ <- A.i.OwnB.i.C.i.query.get.map(_ ==> List((11, 21, 3)))

        // A + B + C
        _ <- A(id).i(12).OwnB.i(22).C.i(30).update.transact
        _ <- A.i.OwnB.i.C.i.query.get.map(_ ==> List((12, 22, 30)))

        // A + C
        _ <- A(id).i(13).OwnB.C.i(31).update.transact
        _ <- A.i.OwnB.i.C.i.query.get.map(_ ==> List((13, 22, 31)))

        // B + C
        _ <- A(id).OwnB.i(23).C.i(32).update.transact
        _ <- A.i.OwnB.i.C.i.query.get.map(_ ==> List((13, 23, 32)))

        // C
        _ <- A(id).OwnB.C.i(33).update.transact
        _ <- A.i.OwnB.i.C.i.query.get.map(_ ==> List((13, 23, 33)))
      } yield ()
    }

    "ref own" - refs { implicit conn =>
      for {
        id <- A.i(1).B.i(2).OwnC.i(3).save.transact.map(_.id)
        _ <- A.i.B.i.OwnC.i.query.get.map(_ ==> List((1, 2, 3)))

        // A
        _ <- A(id).i(10).update.transact
        _ <- A.i.B.i.OwnC.i.query.get.map(_ ==> List((10, 2, 3)))

        // A + B
        _ <- A(id).i(11).B.i(20).update.transact
        _ <- A.i.B.i.OwnC.i.query.get.map(_ ==> List((11, 20, 3)))

        // B
        _ <- A(id).B.i(21).update.transact
        _ <- A.i.B.i.OwnC.i.query.get.map(_ ==> List((11, 21, 3)))

        // A + B + C
        _ <- A(id).i(12).B.i(22).OwnC.i(30).update.transact
        _ <- A.i.B.i.OwnC.i.query.get.map(_ ==> List((12, 22, 30)))

        // A + C
        _ <- A(id).i(13).B.OwnC.i(31).update.transact
        _ <- A.i.B.i.OwnC.i.query.get.map(_ ==> List((13, 22, 31)))

        // B + C
        _ <- A(id).B.i(23).OwnC.i(32).update.transact
        _ <- A.i.B.i.OwnC.i.query.get.map(_ ==> List((13, 23, 32)))

        // C
        _ <- A(id).B.OwnC.i(33).update.transact
        _ <- A.i.B.i.OwnC.i.query.get.map(_ ==> List((13, 23, 33)))
      } yield ()
    }

    "own own" - refs { implicit conn =>
      for {
        id <- A.i(1).OwnB.i(2).OwnC.i(3).save.transact.map(_.id)
        _ <- A.i.OwnB.i.OwnC.i.query.get.map(_ ==> List((1, 2, 3)))

        // A
        _ <- A(id).i(10).update.transact
        _ <- A.i.OwnB.i.OwnC.i.query.get.map(_ ==> List((10, 2, 3)))

        // A + B
        _ <- A(id).i(11).OwnB.i(20).update.transact
        _ <- A.i.OwnB.i.OwnC.i.query.get.map(_ ==> List((11, 20, 3)))

        // B
        _ <- A(id).OwnB.i(21).update.transact
        _ <- A.i.OwnB.i.OwnC.i.query.get.map(_ ==> List((11, 21, 3)))

        // A + B + C
        _ <- A(id).i(12).OwnB.i(22).OwnC.i(30).update.transact
        _ <- A.i.OwnB.i.OwnC.i.query.get.map(_ ==> List((12, 22, 30)))

        // A + C
        _ <- A(id).i(13).OwnB.OwnC.i(31).update.transact
        _ <- A.i.OwnB.i.OwnC.i.query.get.map(_ ==> List((13, 22, 31)))

        // B + C
        _ <- A(id).OwnB.i(23).OwnC.i(32).update.transact
        _ <- A.i.OwnB.i.OwnC.i.query.get.map(_ ==> List((13, 23, 32)))

        // C
        _ <- A(id).OwnB.OwnC.i(33).update.transact
        _ <- A.i.OwnB.i.OwnC.i.query.get.map(_ ==> List((13, 23, 33)))
      } yield ()
    }


    "Backref" - refs { implicit conn =>
      for {
        id <- A.i(1).B.i(2)._A.C.i(3).save.transact.map(_.id)
        _ <- A.i.B.i._A.C.i.query.get.map(_ ==> List((1, 2, 3)))

        // Updating A.B.i and A.C.i
        _ <- A(id).i(10).B.i(20)._A.C.i(30).update.transact
        _ <- A.i.B.i._A.C.i.query.get.map(_ ==> List((10, 20, 30)))
      } yield ()
    }

    "Backref OwnB" - refs { implicit conn =>
      for {
        id <- A.i(1).OwnB.i(2)._A.C.i(3).save.transact.map(_.id)
        _ <- A.i.OwnB.i._A.C.i.query.get.map(_ ==> List((1, 2, 3)))

        // Updating A.OwnB.i and A.C.i
        _ <- A(id).i(10).OwnB.i(20)._A.C.i(30).update.transact
        _ <- A.i.OwnB.i._A.C.i.query.get.map(_ ==> List((10, 20, 30)))
      } yield ()
    }

    "Backref OwnC" - refs { implicit conn =>
      for {
        id <- A.i(1).B.i(2)._A.OwnC.i(3).save.transact.map(_.id)
        _ <- A.i.B.i._A.OwnC.i.query.get.map(_ ==> List((1, 2, 3)))

        // Updating A.B.i and A.OwnC.i
        _ <- A(id).i(10).B.i(20)._A.OwnC.i(30).update.transact
        _ <- A.i.B.i._A.OwnC.i.query.get.map(_ ==> List((10, 20, 30)))
      } yield ()
    }

    "Backref OwnB OwnC" - refs { implicit conn =>
      for {
        id <- A.i(1).OwnB.i(2)._A.OwnC.i(3).save.transact.map(_.id)
        _ <- A.i.OwnB.i._A.OwnC.i.query.get.map(_ ==> List((1, 2, 3)))

        // Updating A.OwnB.i and A.OwnC.i
        _ <- A(id).i(10).OwnB.i(20)._A.OwnC.i(30).update.transact
        _ <- A.i.OwnB.i._A.OwnC.i.query.get.map(_ ==> List((10, 20, 30)))
      } yield ()
    }


    "Semantics" - {

      "Can't update multiple values for one card-one attribute" - types { implicit conn =>
        for {
          _ <- Ns("42").int(2, 3).update.transact
            .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
              err ==> "Can only update one value for attribute `Ns.int`. Found: 2, 3"
            }
        } yield ()
      }

      "Can't update optional values" - types { implicit conn =>
        // Depends on wether the attribute name is a reserved keyword of the tested database
        val attr = database match {
          case "Mysql" | "MariaDB" => "int_"
          case _                   => "int"
        }
        for {
          _ <- Ns("42").int_?(Some(1)).update.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
              err ==> "Can't update optional values. Found:\n" +
                s"""AttrOneOptInt("Ns", "$attr", Eq, Some(Seq(1)), None, None, Nil, Nil, None, None, Seq(0, 8))"""
            }
        } yield ()
      }

      //      "Can't update card-many referenced attributes" - types { implicit conn =>
      //        for {
      //          _ <- Ns("42").i(1).Refs.i(2).update.transact
      //            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
      //              err ==> "Can't update attributes in card-many referenced namespace `Refs`"
      //            }
      //        } yield ()
      //      }
    }
  }
}
