package molecule.coreTests.spi.transaction.update.relation.one

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait One_Map_add extends CoreTestSuite with Api_async { spi: Spi_async =>

  // Pairs with existing key and new value
  val pint20 = "b" -> 20
  val pint30 = "c" -> 30

  override lazy val tests = Tests {

    "id-filter - ref - value" - refs { implicit conn =>
      for {
        a <- A.i(1).save.transact.map(_.id)
        b <- A.i(2).B.s("b").save.transact.map(_.id)
        c <- A.i(3).B.s("c").iMap(Map(pint1, pint2, pint3)).save.transact.map(_.id)

        // Current entity with A value and ref to B value
        _ <- A.i.a1.B.iMap.query.get.map(_ ==> List(
          (3, Map(pint1, pint2, pint3))
        ))

        // Filter by ids, add B pairs with update
        _ <- A(a, b, c).B.iMap.add(pint20, pint3, pint4).update.transact

        _ <- A.i.a1.B.iMap.query.get.map(_ ==> List(
          (3, Map(
            pint1,
            pint20, // key unchanged, value updated
            pint3, //  pair already existed, so no change
            pint4 //   new pair added
          ))
        ))

        // Filter by ids, add B pairs with upsert
        _ <- A(a, b, c).B.iMap.add(pint30, pint4, pint5).upsert.transact

        _ <- A.i.a1.B.iMap.query.get.map(_ ==> List(
          (1, Map(pint30, pint4, pint5)), // relationship to B created, pairs added
          (2, Map(pint30, pint4, pint5)), // pairs added
          (3, Map(
            pint1,
            pint20,
            pint30, // key unchanged, value updated
            pint4, //  pair already existed, so no change
            pint5 //   new pair added
          )),
        ))
      } yield ()
    }


    "filter - ref - value" - refs { implicit conn =>
      for {
        _ <- A.i(1).save.transact
        _ <- A.i(2).B.s("b").save.transact
        _ <- A.i(3).B.s("c").iMap(Map(pint1, pint2, pint3)).save.transact

        // Current entity with A value and ref to B value
        _ <- A.i.a1.B.iMap.query.get.map(_ ==> List(
          (3, Map(pint1, pint2, pint3))
        ))

        // Filter by A attribute, add B pairs with update
        _ <- A.i_.B.iMap.add(pint20, pint3, pint4).update.transact

        _ <- A.i.a1.B.iMap.query.get.map(_ ==> List(
          (3, Map(
            pint1,
            pint20, // key unchanged, value updated
            pint3, //  pair already existed, so no change
            pint4 //   new pair added
          ))
        ))

        // Filter by A attribute, add B pairs with upsert
        _ <- A.i_.B.iMap.add(pint30, pint4, pint5).upsert.transact

        _ <- A.i.a1.B.iMap.query.get.map(_ ==> List(
          (1, Map(pint30, pint4, pint5)), // relationship to B created, pairs added
          (2, Map(pint30, pint4, pint5)), // pairs added
          (3, Map(
            pint1,
            pint20,
            pint30, // key unchanged, value updated
            pint4, //  pair already existed, so no change
            pint5 //   new pair added
          )),
        ))
      } yield ()
    }


    "value - ref - filter" - refs { implicit conn =>
      for {
        _ <- A.iMap(Map(pint0, pint1)).save.transact // won't be updated since there's no B value
        _ <- A.s("x").B.i(1).save.transact
        _ <- A.iMap(Map(pint2, pint3)).B.i(2).save.transact
        _ <- A.iMap(Map(pint3, pint4)).B.i(3).save.transact

        // Current entities with A value and ref to B value
        _ <- A.iMap.B.i.a1.query.get.map(_ ==> List(
          (Map(pint2, pint3), 2),
          (Map(pint3, pint4), 3),
        ))

        // Filter by B attribute, update A values
        _ <- A.iMap.add(pint4, pint5).B.i_.update.transact

        _ <- A.iMap.B.i.a1.query.get.map(_ ==> List(
          (Map(pint2, pint3, pint4, pint5), 2),
          (Map(pint3, pint4, pint4, pint5), 3),
        ))

        // Filter by B attribute, upsert A values
        _ <- A.iMap.add(pint5, pint6).B.i_.upsert.transact

        _ <- A.iMap.B.i.a1.query.get.map(_ ==> List(
          (Map(pint5, pint6), 1), //                             A attribute inserted
          (Map(pint2, pint3, pint4, pint5, pint5, pint6), 2), // A attribute updated
          (Map(pint3, pint4, pint4, pint5, pint5, pint6), 3), // A attribute updated
        ))

        // Initial entity without ref was not updated (values 0 and 1 not changed)
        _ <- A.iMap.b_().query.get.map(_ ==> List(Map(pint0, pint1)))
      } yield ()
    }


    "ref - filter/value" - refs { implicit conn =>
      for {
        // will not be updated (no update filter match)
        _ <- B.s("x").iMap(Map(pint0, pint1)).save.transact
        _ <- A.i(0).save.transact

        // will be updated
        _ <- A.i(1).B.s("a").save.transact
        _ <- A.i(2).B.s("b").iMap(Map(pint2, pint3)).save.transact
        _ <- A.i(3).B.s("c").iMap(Map(pint3, pint4)).save.transact

        // Current entities with A value and ref to B value
        _ <- A.i.a1.B.iMap.query.get.map(_ ==> List(
          (2, Map(pint2, pint3)),
          (3, Map(pint3, pint4)),
        ))

        // Filter by B attribute, update B values
        _ <- A.B.s_.iMap.add(pint4, pint5).update.transact

        _ <- A.i.a1.B.iMap.query.get.map(_ ==> List(
          (2, Map(pint2, pint3, pint4, pint5)),
          (3, Map(pint3, pint4, pint4, pint5)),
        ))

        // Filter by B attribute, upsert B values
        _ <- A.B.s_.iMap.add(pint5, pint6).upsert.transact

        _ <- A.i.a1.B.iMap.query.get.map(_ ==> List(
          (1, Map(pint5, pint6)), //                             B attribute added
          (2, Map(pint2, pint3, pint4, pint5, pint5, pint6)), // B attribute updated
          (3, Map(pint3, pint4, pint4, pint5, pint5, pint6)), // B attribute updated
        ))

        _ <- B.s.a1.iMap.query.get.map(_ ==> List(
          ("a", Map(pint5, pint6)),
          ("b", Map(pint2, pint3, pint4, pint5, pint5, pint6)),
          ("c", Map(pint3, pint4, pint4, pint5, pint5, pint6)),
          ("x", Map(pint0, pint1)), // not updated since it isn't referenced from A
        ))
      } yield ()
    }
  }
}
