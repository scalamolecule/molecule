package molecule.coreTests.spi.crud.update2.relations.oneOwned

import molecule.base.error.ModelError
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait OneOwned_One extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "id-filter - ref - value" - refs { implicit conn =>
      for {
        a <- A.i(1).save.transact.map(_.id)
        b <- A.i(2).OwnB.s("b").save.transact.map(_.id)
        c <- A.i(3).OwnB.s("c").i(3).save.transact.map(_.id)

        // Filter by A ids, update B values
        _ <- A(a, b, c).OwnB.i(4).update.transact

        _ <- A.i.a1.OwnB.i.query.get.map(_ ==> List(
          (1, 4), // relationship to B created + B attribute added
          (2, 4), // B attribute added
          (3, 4), // B attribute updated
        ))
      } yield ()
    }


    "filter - ref - value" - refs { implicit conn =>
      for {
        _ <- A.i(1).save.transact
        _ <- A.i(2).OwnB.s("b").save.transact
        _ <- A.i(3).OwnB.s("c").i(3).save.transact

        // Filter by A attribute, update B values
        _ <- A.i_.OwnB.i(4).update.transact

        _ <- A.i.a1.OwnB.i.query.get.map(_ ==> List(
          (1, 4), // relationship to B created + B attribute added
          (2, 4), // B attribute added
          (3, 4), // B attribute updated
        ))
      } yield ()
    }


    "value - ref - filter" - refs { implicit conn =>
      for {
        _ <- A.i(0).save.transact

        _ <- A.OwnB.i(1).save.transact
        _ <- A.i(2).OwnB.i(2).save.transact
        _ <- A.i(3).OwnB.i(3).save.transact

        // Filter by B attribute, update A values
        _ <- A.i(4).OwnB.i_.update.transact

        _ <- A.i.OwnB.i.a1.query.get.map(_ ==> List(
          (4, 1), // A attribute added
          (4, 2), // A attribute updated
          (4, 3), // A attribute updated
        ))

        // Initial entity without ref was not updated
        _ <- A.i.a1.query.get.map(_ ==> List(0, 4))
      } yield ()
    }


    "ref - filter/value" - refs { implicit conn =>
      for {
        // will not be updated (no update filter match)
        _ <- B.s("x").i.apply(0).save.transact
        _ <- A.i(1).save.transact

        // will be updated
        _ <- A.i(2).OwnB.s("b").save.transact
        _ <- A.i(3).OwnB.s("c").i(3).save.transact

        // Filter by B attribute, update B values
        _ <- A.OwnB.s_.i(4).update.transact

        _ <- A.i.a1.OwnB.i.query.get.map(_ ==> List(
          (2, 4), // B attribute added
          (3, 4), // B attribute updated
        ))

        _ <- if (database == "MongoDB") {
          // Embedded data in Mongo have no separate entity ids
          B.s.a1.i.query.get.map(_ ==> List(
            ("x", 0), // not updated since it isn't referenced from A
          ))
        } else {
          B.s.a1.i.query.get.map(_ ==> List(
            ("b", 4),
            ("c", 4),
            ("x", 0), // not updated since it isn't referenced from A
          ))
        }
      } yield ()
    }


    "ref ref" - refs { implicit conn =>
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


    "backref, own ref" - refs { implicit conn =>
      for {
        id <- A.i(1).OwnB.i(2)._A.C.i(3).save.transact.map(_.id)
        _ <- A.i.OwnB.i._A.C.i.query.get.map(_ ==> List((1, 2, 3)))

        // Updating A.OwnB.i and A.C.i
        _ <- A(id).i(10).OwnB.i(20)._A.C.i(30).update.transact
        _ <- A.i.OwnB.i._A.C.i.query.get.map(_ ==> List((10, 20, 30)))
      } yield ()
    }

    "backref, ref own" - refs { implicit conn =>
      for {
        id <- A.i(1).B.i(2)._A.OwnC.i(3).save.transact.map(_.id)
        _ <- A.i.B.i._A.OwnC.i.query.get.map(_ ==> List((1, 2, 3)))

        // Updating A.B.i and A.OwnC.i
        _ <- A(id).i(10).B.i(20)._A.OwnC.i(30).update.transact
        _ <- A.i.B.i._A.OwnC.i.query.get.map(_ ==> List((10, 20, 30)))
      } yield ()
    }

    "backref, own own" - refs { implicit conn =>
      for {
        id <- A.i(1).OwnB.i(2)._A.OwnC.i(3).save.transact.map(_.id)
        _ <- A.i.OwnB.i._A.OwnC.i.query.get.map(_ ==> List((1, 2, 3)))

        // Updating A.OwnB.i and A.OwnC.i
        _ <- A(id).i(10).OwnB.i(20)._A.OwnC.i(30).update.transact
        _ <- A.i.OwnB.i._A.OwnC.i.query.get.map(_ ==> List((10, 20, 30)))
      } yield ()
    }


    "Ownership (Mongo)" - refs { implicit conn =>
      if (database == "MongoDB") {
        for {
          id <- A.i(1).OwnB.i(2).save.transact.map(_.id)

          _ <- A(id).ownB("123456789012345678901234").update.transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
              err ==> "Can't update non-existing ids of embedded documents in MongoDB."
            }
          _ <- A.i.ownB.query.get
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
              err ==> "Can't query for non-existing ids of embedded documents in MongoDB."
            }
          _ <- A.i.ownB_.query.get
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
              err ==> "Can't query for non-existing ids of embedded documents in MongoDB."
            }
          _ <- A.i.ownB_?.query.get
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
              err ==> "Can't query for non-existing ids of embedded documents in MongoDB."
            }
        } yield ()
      }
    }


    "Delete individual owned ref value(s) with update" - refs { implicit conn =>
      // Not relevant for embedded documents without entity in Mongo
      if (database != "MongoDB") {
        for {
          id <- A.i(1).OwnB.i(7).save.transact.map(_.id)
          _ <- A.i.OwnB.i.query.get.map(_ ==> List((1, 7)))

          // Apply empty value to delete ref id of entity (entity remains)
          _ <- A(id).ownB().update.transact
          _ <- A.i.ownB_?.query.get.map(_ ==> List((1, None)))
        } yield ()
      }
    }
  }
}
