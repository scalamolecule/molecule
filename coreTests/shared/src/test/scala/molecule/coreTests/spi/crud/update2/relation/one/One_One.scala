package molecule.coreTests.spi.crud.update2.relation.one

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait One_One extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "id-filter - ref - value" - refs { implicit conn =>
      for {
        a <- A.i(1).save.transact.map(_.id)
        b <- A.i(2).B.s("b").save.transact.map(_.id)
        c <- A.i(3).B.s("c").i(3).save.transact.map(_.id)

        // Filter by A ids, update B values
        _ <- A(a, b, c).B.i(4).update.transact

        _ <- A.i.a1.B.i.query.get.map(_ ==> List(
          (1, 4), // relationship to B created + B attribute added
          (2, 4), // B attribute added
          (3, 4), // B attribute updated
        ))
      } yield ()
    }


    "filter - ref - value" - refs { implicit conn =>
      for {
        _ <- A.i(1).save.transact
        _ <- A.i(2).B.s("b").save.transact
        _ <- A.i(3).B.s("c").i(3).save.transact

        // Filter by A attribute, update B values
        _ <- A.i_.B.i(4).update.transact

        _ <- A.i.a1.B.i.query.get.map(_ ==> List(
          (1, 4), // relationship to B created + B attribute added
          (2, 4), // B attribute added
          (3, 4), // B attribute updated
        ))
      } yield ()
    }


    "value - ref - filter" - refs { implicit conn =>
      for {
        _ <- A.i(0).save.transact

        _ <- A.B.i(1).save.transact
        _ <- A.i(2).B.i(2).save.transact
        _ <- A.i(3).B.i(3).save.transact

        // Filter by B attribute, update A values
        _ <- A.i(4).B.i_.update.transact

        _ <- A.i.B.i.a1.query.get.map(_ ==> List(
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
        _ <- B.s("x").i(0).save.transact
        _ <- A.i(1).save.transact

        // will be updated
        _ <- A.i(2).B.s("b").save.transact
        _ <- A.i(3).B.s("c").i(3).save.transact

        // Filter by B attribute, update B values
        _ <- A.B.s_.i(4).update.transact

        _ <- A.i.a1.B.i.query.get.map(_ ==> List(
          (2, 4), // B attribute added
          (3, 4), // B attribute updated
        ))

        _ <- B.s.a1.i.query.get.map(_ ==> List(
          ("b", 4),
          ("c", 4),
          ("x", 0), // not updated since it isn't referenced from A
        ))
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


    "backref" - refs { implicit conn =>
      for {
        id <- A.i(1).B.i(2)._A.C.i(3).save.transact.map(_.id)
        _ <- A.i.B.i._A.C.i.query.get.map(_ ==> List((1, 2, 3)))

        // Updating A.B.i and A.C.i
        _ <- A(id).i(10).B.i(20)._A.C.i(30).update.transact
        _ <- A.i.B.i._A.C.i.query.get.map(_ ==> List((10, 20, 30)))
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
  }
}
