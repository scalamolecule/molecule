package molecule.coreTests.spi.crud.update2.relations.oneOwned

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._
import scala.concurrent.Future

trait OneOwned_Map_remove extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "id-filter - ref - value" - refs { implicit conn =>
      for {
        a <- A.i(1).save.transact.map(_.id)
        b <- A.i(2).OwnB.s("b").save.transact.map(_.id)
        c <- A.i(3).OwnB.s("c").iMap(Map(pint1, pint2)).save.transact.map(_.id)
        d <- A.i(4).OwnB.s("c").iMap(Map(pint2, pint3)).save.transact.map(_.id)
        e <- A.i(5).OwnB.s("c").iMap(Map(pint3, pint4)).save.transact.map(_.id)

        // Filter by A ids, update B values
        _ <- A(a, b, c, d, e).OwnB.iMap.remove(string3, string4).update.transact

        // 2 entities left with remaining values
        _ <- A.i.a1.OwnB.iMap.query.get.map(_ ==> List(
          (3, Map(pint1, pint2)),
          (4, Map(pint2)),
        ))
      } yield ()
    }


    "filter - ref - value" - refs { implicit conn =>
      for {
        _ <- A.i(1).save.transact.map(_.id)
        _ <- A.i(2).OwnB.s("b").save.transact.map(_.id)
        _ <- A.i(3).OwnB.s("c").iMap(Map(pint1, pint2)).save.transact.map(_.id)
        _ <- A.i(4).OwnB.s("c").iMap(Map(pint2, pint3)).save.transact.map(_.id)
        _ <- A.i(5).OwnB.s("c").iMap(Map(pint3, pint4)).save.transact.map(_.id)

        // Filter by A ids, update B values
        _ <- A.i_.OwnB.iMap.remove(string3, string4).update.transact

        // 2 entities left with remaining values
        _ <- A.i.a1.OwnB.iMap.query.get.map(_ ==> List(
          (3, Map(pint1, pint2)),
          (4, Map(pint2)),
        ))
      } yield ()
    }


    "value - ref - filter" - refs { implicit conn =>
      for {
        _ <- A.iMap(Map(pint3, pint7)).save.transact

        _ <- A.OwnB.i(2).save.transact
        _ <- A.iMap(Map(pint1, pint2)).OwnB.i(3).save.transact
        _ <- A.iMap(Map(pint2, pint3)).OwnB.i(4).save.transact
        _ <- A.iMap(Map(pint3, pint4)).OwnB.i(5).save.transact

        // Filter by B attribute, update A values
        _ <- A.iMap.remove(string3, string4).OwnB.i_.update.transact

        // 2 referencing A entities left with remaining values
        _ <- A.iMap.OwnB.i.a1.query.get.map(_ ==> List(
          (Map(pint1, pint2), 3),
          (Map(pint2), 4),
        ))

        // Initial entity without ref was not updated (values 3 not removed)
        _ <- if (database != "MongoDB") {
          // Entity A without ref was not updated
          A.iMap.ownB_().query.get.map(_ ==> List(Map(pint3, pint7)))
        } else Future.unit
      } yield ()
    }


    "ref - filter/value" - refs { implicit conn =>
      for {
        // will not be updated (no update filter match)
        _ <- B.s("x").iMap(Map(pint3, pint7)).save.transact
        _ <- A.i(1).save.transact

        // will be updated
        _ <- A.i(2).OwnB.s("a").save.transact
        _ <- A.i(3).OwnB.s("b").iMap(Map(pint1, pint2)).save.transact
        _ <- A.i(4).OwnB.s("c").iMap(Map(pint2, pint3)).save.transact
        _ <- A.i(5).OwnB.s("d").iMap(Map(pint3, pint4)).save.transact

        // Filter by B attribute, update B values
        _ <- A.OwnB.s_.iMap.remove(string3, string4).update.transact

        // 2 referenced B entities left with remaining values
        _ <- A.i.a1.OwnB.iMap.query.get.map(_ ==> List(
          (3, Map(pint1, pint2)),
          (4, Map(pint2)),
        ))

        _ <- if (database == "MongoDB") {
          // Embedded documents in Mongo have no separate entity ids
          B.s.a1.iMap.query.get.map(_ ==> List(
            ("x", Map(pint3, pint7)), // not updated since it isn't referenced from A
          ))
        } else {
          B.s.a1.iMap.query.get.map(_ ==> List(
            ("b", Map(pint1, pint2)),
            ("c", Map(pint2)),
            ("x", Map(pint3, pint7)), // not updated since it isn't referenced from A
          ))
        }
      } yield ()
    }
  }
}
