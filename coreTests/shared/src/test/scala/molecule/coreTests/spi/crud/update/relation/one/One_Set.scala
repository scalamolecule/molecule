package molecule.coreTests.spi.crud.update.relation.one

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait One_Set extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "id-filter - ref - value" - refs { implicit conn =>
      for {
        a <- A.i(1).save.transact.map(_.id)
        b <- A.i(2).B.s("b").save.transact.map(_.id)
        c <- A.i(3).B.s("c").iSet(Set(3, 4)).save.transact.map(_.id)

        // Current entity with A value and ref to B value
        _ <- A.i.a1.B.iSet.query.get.map(_ ==> List(
          (3, Set(3, 4))
        ))

        // Filter by A value, update existing B values
        _ <- A(a, b, c).B.iSet(Set(4, 5)).update.transact

        _ <- A.i.a1.B.iSet.query.get.map(_ ==> List(
          (3, Set(4, 5)) // B value updated since there was a previous value
        ))

        // Filter by A ids, upsert B values (insert if not already present)
        _ <- A(a, b, c).B.iSet(Set(5, 6)).upsert.transact

        // Now three A entities with referenced B value
        _ <- A.i.a1.B.iSet.query.get.map(_ ==> List(
          (1, Set(5, 6)), // relationship to B created and B value inserted
          (2, Set(5, 6)), // B value inserted
          (3, Set(5, 6)), // B value updated
        ))
      } yield ()
    }


    "filter - ref - value" - refs { implicit conn =>
      for {
        _ <- A.i(1).save.transact
        _ <- A.i(2).B.s("b").save.transact
        _ <- A.i(3).B.s("c").iSet(Set(3, 4)).save.transact

        // Current entity with A value and ref to B value
        _ <- A.i.a1.B.iSet.query.get.map(_ ==> List(
          (3, Set(3, 4))
        ))

        // Filter by A value, update existing B values
        _ <- A.i_.B.iSet(Set(4, 5)).update.transact

        _ <- A.i.a1.B.iSet.query.get.map(_ ==> List(
          (3, Set(4, 5)) // B value updated since there was a previous value
        ))

        // Filter by A ids, upsert B values (insert if not already present)
        _ <- A.i_.B.iSet(Set(5, 6)).upsert.transact

        // Now three A entities with referenced B value
        _ <- A.i.a1.B.iSet.query.get.map(_ ==> List(
          (1, Set(5, 6)), // relationship to B created and B value inserted
          (2, Set(5, 6)), // B value inserted
          (3, Set(5, 6)), // B value updated
        ))
      } yield ()
    }


    "value - ref - filter" - refs { implicit conn =>
      for {
        _ <- A.iSet(Set(0, 1)).save.transact // won't be updated since there's no B value
        _ <- A.B.i(1).save.transact
        _ <- A.iSet(Set(2, 3)).B.i(2).save.transact
        _ <- A.iSet(Set(3, 4)).B.i(3).save.transact

        // Current 2 entities with A value and ref to B value
        _ <- A.iSet.B.i.a1.query.get.map(_ ==> List(
          (Set(2, 3), 2),
          (Set(3, 4), 3),
        ))

        // Filter by B value, update A values
        _ <- A.iSet(Set(4, 5)).B.i_.update.transact

        _ <- A.iSet.B.i.a1.query.get.map(_ ==> List(
          (Set(4, 5), 2), // A value updated
          (Set(4, 5), 3), // A value updated
        ))

        // Filter by B value, upsert A values (insert if not already present)
        _ <- A.iSet(Set(5, 6)).B.i_.upsert.transact

        _ <- A.iSet.B.i.a1.query.get.map(_ ==> List(
          (Set(5, 6), 1), // A value and relationship to B value inserted
          (Set(5, 6), 2), // A value updated
          (Set(5, 6), 3), // A value updated
        ))

        // Initial entity without ref to B was not updated/upserted
        _ <- A.iSet.b_().query.get.map(_ ==> List(Set(0, 1)))
      } yield ()
    }


    "ref - filter/value" - refs { implicit conn =>
      for {
        // will not be updated (no update filter match)
        _ <- B.s("x").iSet(Set(0, 1)).save.transact
        _ <- A.i(1).save.transact

        // will be updated
        _ <- A.i(2).B.s("b").save.transact
        _ <- A.i(3).B.s("c").iSet(Set(3, 4)).save.transact

        // Current entity with A value and ref to B value
        _ <- A.i.a1.B.iSet.query.get.map(_ ==> List(
          (3, Set(3, 4))
        ))

        // Filter by B value, update existing B values
        _ <- A.B.s_.iSet(Set(4, 5)).update.transact

        _ <- A.i.a1.B.iSet.query.get.map(_ ==> List(
          (3, Set(4, 5)), // B value updated since there was a previous value
        ))

        // Filter by B attribute, upsert B values
        _ <- A.B.s_.iSet(Set(5, 6)).upsert.transact

        _ <- A.i.a1.B.iSet.query.get.map(_ ==> List(
          (2, Set(5, 6)), // B value inserted
          (3, Set(5, 6)), // B value updated
        ))

        _ <- B.s.a1.iSet.query.get.map(_ ==> List(
          ("b", Set(5, 6)),
          ("c", Set(5, 6)),
          ("x", Set(0, 1)), // not updated since it isn't referenced from A
        ))
      } yield ()
    }


    "ref ref" - refs { implicit conn =>
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


    "backref" - refs { implicit conn =>
      for {
        id <- A.iSet(Set(1)).B.iSet(Set(2))._A.C.iSet(Set(3)).save.transact.map(_.id)
        _ <- A.iSet.B.iSet._A.C.iSet.query.get.map(_ ==> List((Set(1), Set(2), Set(3))))

        // Updating A.B.iSet and A.C.iSet
        _ <- A(id).iSet(Set(10)).B.iSet(Set(20))._A.C.iSet(Set(30)).update.transact
        _ <- A.iSet.B.iSet._A.C.iSet.query.get.map(_ ==> List((Set(10), Set(20), Set(30))))
      } yield ()
    }
  }
}
