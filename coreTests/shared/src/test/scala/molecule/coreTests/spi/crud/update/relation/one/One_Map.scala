package molecule.coreTests.spi.crud.update.relation.one

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait One_Map extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "id-filter - ref - value" - refs { implicit conn =>
      for {
        a <- A.i(1).save.transact.map(_.id)
        b <- A.i(2).B.s("b").save.transact.map(_.id)
        c <- A.i(3).B.s("c").iMap(Map(pint3, pint4)).save.transact.map(_.id)

        // Current entity with A value and ref to B value
        _ <- A.i.a1.B.iMap.query.get.map(_ ==> List(
          (3, Map(pint3, pint4))
        ))

        // Filter by A value, update existing B values
        _ <- A(a, b, c).B.iMap(Map(pint4, pint5)).update.transact

        _ <- A.i.a1.B.iMap.query.get.map(_ ==> List(
          (3, Map(pint4, pint5)) // B value updated since there was a previous value
        ))

        // Filter by A ids, upsert B values (insert if not already present)
        _ <- A(a, b, c).B.iMap(Map(pint5, pint6)).upsert.transact

        // Now three A entities with referenced B value
        _ <- A.i.a1.B.iMap.query.get.map(_ ==> List(
          (1, Map(pint5, pint6)), // relationship to B created and B value inserted
          (2, Map(pint5, pint6)), // B value inserted
          (3, Map(pint5, pint6)), // B value updated
        ))
      } yield ()
    }


    "filter - ref - value" - refs { implicit conn =>
      for {
        _ <- A.i(1).save.transact
        _ <- A.i(2).B.s("b").save.transact
        _ <- A.i(3).B.s("c").iMap(Map(pint3, pint4)).save.transact

        // Current entity with A value and ref to B value
        _ <- A.i.a1.B.iMap.query.get.map(_ ==> List(
          (3, Map(pint3, pint4))
        ))

        // Filter by A value, update existing B values
        _ <- A.i_.B.iMap(Map(pint4, pint5)).update.transact

        _ <- A.i.a1.B.iMap.query.get.map(_ ==> List(
          (3, Map(pint4, pint5)) // B value updated since there was a previous value
        ))

        // Filter by A.i values, upsert B values (insert if not already present)
        _ <- A.i_.B.iMap(Map(pint5, pint6)).upsert.transact

        // Now three A entities with referenced B value
        _ <- A.i.a1.B.iMap.query.get.map(_ ==> List(
          (1, Map(pint5, pint6)), // relationship to B created and B value inserted
          (2, Map(pint5, pint6)), // B value inserted
          (3, Map(pint5, pint6)), // B value updated
        ))
      } yield ()
    }


    "value - ref - filter" - refs { implicit conn =>
      for {
        _ <- A.iMap(Map(pint0, pint1)).save.transact // won't be updated since there's no B value
        _ <- A.s("x").B.i(1).save.transact
        _ <- A.iMap(Map(pint2, pint3)).B.i(2).save.transact
        _ <- A.iMap(Map(pint3, pint4)).B.i(3).save.transact

        // Current 2 entities with A value and ref to B value
        _ <- A.iMap.B.i.a1.query.get.map(_ ==> List(
          (Map(pint2, pint3), 2),
          (Map(pint3, pint4), 3),
        ))

        // Filter by B value, update A values
        _ <- A.iMap(Map(pint4, pint5)).B.i_.update.transact

        _ <- A.iMap.B.i.a1.query.get.map(_ ==> List(
          (Map(pint4, pint5), 2), // A value updated
          (Map(pint4, pint5), 3), // A value updated
        ))

        // Filter by B value, upsert A values (insert if not already present)
        _ <- A.iMap(Map(pint5, pint6)).B.i_.upsert.transact

        _ <- A.iMap.B.i.a1.query.get.map(_ ==> List(
          (Map(pint5, pint6), 1), // A value and relationship to B value inserted
          (Map(pint5, pint6), 2), // A value updated
          (Map(pint5, pint6), 3), // A value updated
        ))

        // Initial entity without ref to B was not updated/upserted
        _ <- A.iMap.b_().query.get.map(_ ==> List(Map(pint0, pint1)))
      } yield ()
    }


    "ref - filter/value" - refs { implicit conn =>
      for {
        // will not be updated (no update filter match)
        _ <- B.s("x").iMap(Map(pint0, pint1)).save.transact
        _ <- A.i(1).save.transact

        // will be updated
        _ <- A.i(2).B.s("b").save.transact
        _ <- A.i(3).B.s("c").iMap(Map(pint3, pint4)).save.transact

        // Current entity with A value and ref to B value
        _ <- A.i.a1.B.iMap.query.get.map(_ ==> List(
          (3, Map(pint3, pint4))
        ))

        // Filter by B value, update existing B values
        _ <- A.B.s_.iMap(Map(pint4, pint5)).update.transact

        _ <- A.i.a1.B.iMap.query.get.map(_ ==> List(
          (3, Map(pint4, pint5)), // B value updated since there was a previous value
        ))

        // Filter by B attribute, upsert B values
        _ <- A.B.s_.iMap(Map(pint5, pint6)).upsert.transact

        _ <- A.i.a1.B.iMap.query.get.map(_ ==> List(
          (2, Map(pint5, pint6)), // B value inserted
          (3, Map(pint5, pint6)), // B value updated
        ))

        _ <- B.s.a1.iMap.query.get.map(_ ==> List(
          ("b", Map(pint5, pint6)),
          ("c", Map(pint5, pint6)),
          ("x", Map(pint0, pint1)), // not updated since it isn't referenced from A
        ))
      } yield ()
    }


    "ref ref" - refs { implicit conn =>
      for {
        id <- A.iMap(Map(pint1)).B.iMap(Map(pint2)).C.iMap(Map(pint3)).save.transact.map(_.id)
        _ <- A.iMap.B.iMap.C.iMap.query.get.map(_ ==> List((Map(pint1), Map(pint2), Map(pint3))))

        // A
        _ <- A(id).iMap(Map(pint1)).update.transact
        _ <- A.iMap.B.iMap.C.iMap.query.get.map(_ ==> List((Map(pint1), Map(pint2), Map(pint3))))

        // A + B
        _ <- A(id).iMap(Map(pint2)).B.iMap(Map(pint1)).update.transact
        _ <- A.iMap.B.iMap.C.iMap.query.get.map(_ ==> List((Map(pint2), Map(pint1), Map(pint3))))

        // B
        _ <- A(id).B.iMap(Map(pint2)).update.transact
        _ <- A.iMap.B.iMap.C.iMap.query.get.map(_ ==> List((Map(pint2), Map(pint2), Map(pint3))))

        // A + B + C
        _ <- A(id).iMap(Map(pint3)).B.iMap(Map(pint3)).C.iMap(Map(pint1)).update.transact
        _ <- A.iMap.B.iMap.C.iMap.query.get.map(_ ==> List((Map(pint3), Map(pint3), Map(pint1))))

        // A + C
        _ <- A(id).iMap(Map(pint4)).B.C.iMap(Map(pint2)).update.transact
        _ <- A.iMap.B.iMap.C.iMap.query.get.map(_ ==> List((Map(pint4), Map(pint3), Map(pint2))))

        // B + C
        _ <- A(id).B.iMap(Map(pint4)).C.iMap(Map(pint3)).update.transact
        _ <- A.iMap.B.iMap.C.iMap.query.get.map(_ ==> List((Map(pint4), Map(pint4), Map(pint3))))

        // C
        _ <- A(id).B.C.iMap(Map(pint4)).update.transact
        _ <- A.iMap.B.iMap.C.iMap.query.get.map(_ ==> List((Map(pint4), Map(pint4), Map(pint4))))
      } yield ()
    }


    "backref" - refs { implicit conn =>
      for {
        id <- A.iMap(Map(pint1)).B.iMap(Map(pint2))._A.C.iMap(Map(pint3)).save.transact.map(_.id)
        _ <- A.iMap.B.iMap._A.C.iMap.query.get.map(_ ==> List((Map(pint1), Map(pint2), Map(pint3))))

        // Updating A.B.iMap and A.C.iMap
        _ <- A(id).iMap(Map(pint1)).B.iMap(Map(pint1))._A.C.iMap(Map(pint1)).update.transact
        _ <- A.iMap.B.iMap._A.C.iMap.query.get.map(_ ==> List((Map(pint1), Map(pint1), Map(pint1))))
      } yield ()
    }
  }
}