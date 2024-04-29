package molecule.coreTests.spi.crud.update2.relations.oneOwned

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._
import scala.concurrent.Future

trait OneOwned_Map_add extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "id-filter - ref - value" - refs { implicit conn =>
      for {
        a <- A.i(1).save.transact.map(_.id)
        b <- A.i(2).OwnB.s("b").save.transact.map(_.id)
        c <- A.i(3).OwnB.s("c").iMap(Map(pint1, pint2)).save.transact.map(_.id)

        // Filter by A ids, update B values
        _ <- A(a, b, c).OwnB.iMap.add(pint2, pint3).update.transact

        _ <- A.i.a1.OwnB.iMap.query.get.map(_ ==> List(
          (1, Map(pint2, pint3)), //        relationship to B created, B attribute added
          (2, Map(pint2, pint3)), //        B attribute added
          (3, Map(pint1, pint2, pint3)), // B attribute updated (2 not added - already exists in Map)
        ))
      } yield ()
    }


    "filter - ref - value" - refs { implicit conn =>
      for {
        _ <- A.i(1).save.transact
        _ <- A.i(2).OwnB.s("b").save.transact
        _ <- A.i(3).OwnB.s("c").iMap(Map(pint1, pint2)).save.transact

        // Filter by A attribute, update B values
        _ <- A.i_.OwnB.iMap.add(pint2, pint3).update.transact

        _ <- A.i.a1.OwnB.iMap.query.get.map(_ ==> List(
          (1, Map(pint2, pint3)), //        relationship to B created, B attribute added
          (2, Map(pint2, pint3)), //        B attribute added
          (3, Map(pint1, pint2, pint3)), // B attribute updated (2 not added - already exists in Map)
        ))
      } yield ()
    }


    "value - ref - filter" - refs { implicit conn =>
      for {
        _ <- A.iMap(Map(pint0, pint1)).save.transact

        _ <- A.OwnB.i(1).save.transact
        _ <- A.iMap(Map(pint2, pint3)).OwnB.i(2).save.transact
        _ <- A.iMap(Map(pint3, pint4)).OwnB.i(3).save.transact

        // Filter by B attribute, update A values
        _ <- A.iMap.add(pint4, pint5).OwnB.i_.update.transact

        _ <- A.iMap.OwnB.i.a1.query.get.map(_ ==> List(
          (Map(pint4, pint5), 1), //               A attribute added
          (Map(pint2, pint3, pint4, pint5), 2), // A attribute updated
          (Map(pint3, pint4, pint5), 3), //        A attribute updated (4 not added - already exists in Map)
        ))

        // Initial entity without ref was not updated (values 0 and 1 not changed)
        _ <- if (database != "MongoDB") {
          // Entity A without ref was not updated
          A.iMap.ownB_().query.get.map(_ ==> List(Map(pint0, pint1)))
        } else Future.unit
      } yield ()
    }


    "ref - filter/value" - refs { implicit conn =>
      for {
        // will not be updated (no update filter match)
        _ <- B.s("x").iMap(Map(pint0, pint1)).save.transact
        _ <- A.i(0).save.transact

        // will be updated
        _ <- A.i(1).OwnB.s("a").save.transact
        _ <- A.i(2).OwnB.s("b").iMap(Map(pint1, pint2)).save.transact
        _ <- A.i(3).OwnB.s("c").iMap(Map(pint2, pint3)).save.transact

        // Filter by B attribute, update B values
        _ <- A.OwnB.s_.iMap.add(pint3, pint4).update.transact

        _ <- A.i.a1.OwnB.iMap.query.get.map(_ ==> List(
          (1, Map(pint3, pint4)), //               B attribute added
          (2, Map(pint1, pint2, pint3, pint4)), // B attribute updated
          (3, Map(pint2, pint3, pint4)), //        B attribute updated (3 not added - already exists in Map)
        ))

        _ <- if (database == "MongoDB") {
          // Embedded data in Mongo have no separate entity ids
          B.s.a1.iMap.query.get.map(_ ==> List(
            ("x", Map(pint0, pint1)), // not updated since it isn't referenced from A
          ))
        } else {
          B.s.a1.iMap.query.get.map(_ ==> List(
            ("a", Map(pint3, pint4)),
            ("b", Map(pint1, pint2, pint3, pint4)),
            ("c", Map(pint2, pint3, pint4)),
            ("x", Map(pint0, pint1)), // not updated since it isn't referenced from A
          ))
        }
      } yield ()
    }
  }
}
