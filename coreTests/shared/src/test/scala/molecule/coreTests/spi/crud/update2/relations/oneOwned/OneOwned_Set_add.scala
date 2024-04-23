package molecule.coreTests.spi.crud.update2.relations.oneOwned

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait OneOwned_Set_add extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "id-filter - ref - value" - refs { implicit conn =>
      for {
        a <- A.i(1).save.transact.map(_.id)
        b <- A.i(2).OwnB.s("b").save.transact.map(_.id)
        c <- A.i(3).OwnB.s("c").iSet(Set(1, 2)).save.transact.map(_.id)

        // Filter by A ids, update B values
        _ <- A(a, b, c).OwnB.iSet.add(2, 3).update.transact

        _ <- A.i.a1.OwnB.iSet.query.get.map(_ ==> List(
          (1, Set(2, 3)), //    relationship to B created, B attribute added
          (2, Set(2, 3)), //    B attribute added
          (3, Set(1, 2, 3)), // B attribute updated (2 not added - already exists in Set)
        ))
      } yield ()
    }


    "filter - ref - value" - refs { implicit conn =>
      for {
        _ <- A.i(1).save.transact
        _ <- A.i(2).OwnB.s("b").save.transact
        _ <- A.i(3).OwnB.s("c").iSet(Set(1, 2)).save.transact

        // Filter by A attribute, update B values
        _ <- A.i_.OwnB.iSet.add(2, 3).update.transact

        _ <- A.i.a1.OwnB.iSet.query.get.map(_ ==> List(
          (1, Set(2, 3)), //    relationship to B created, B attribute added
          (2, Set(2, 3)), //    B attribute added
          (3, Set(1, 2, 3)), // B attribute updated (2 not added - already exists in Set)
        ))
      } yield ()
    }


    "value - ref - filter" - refs { implicit conn =>
      for {
        _ <- A.iSet(Set(0, 1)).save.transact

        _ <- A.OwnB.i(1).save.transact
        _ <- A.iSet(Set(2, 3)).OwnB.i(2).save.transact
        _ <- A.iSet(Set(3, 4)).OwnB.i(3).save.transact

        // Filter by B attribute, update A values
        _ <- A.iSet.add(4, 5).OwnB.i_.update.transact

        _ <- A.iSet.OwnB.i.a1.query.get.map(_ ==> List(
          (Set(4, 5), 1), //       A attribute added
          (Set(2, 3, 4, 5), 2), // A attribute updated
          (Set(3, 4, 5), 3), //    A attribute updated (4 not added - already exists in Set)
        ))

        // Initial entity without ref was not updated (values 0 and 1 not changed)
        _ <- A.iSet.ownB_().query.get.map(_ ==> List(Set(0, 1)))
      } yield ()
    }


    "ref - filter/value" - refs { implicit conn =>
      for {
        // will not be updated (no update filter match)
        _ <- B.s("x").iSet(Set(0, 1)).save.transact
        _ <- A.i(0).save.transact

        // will be updated
        _ <- A.i(1).OwnB.s("a").save.transact
        _ <- A.i(2).OwnB.s("b").iSet(Set(1, 2)).save.transact
        _ <- A.i(3).OwnB.s("c").iSet(Set(2, 3)).save.transact

        // Filter by B attribute, update B values
        _ <- A.OwnB.s_.iSet.add(3, 4).update.transact

        _ <- A.i.a1.OwnB.iSet.query.get.map(_ ==> List(
          (1, Set(3, 4)), //       B attribute added
          (2, Set(1, 2, 3, 4)), // B attribute updated
          (3, Set(2, 3, 4)), //    B attribute updated (3 not added - already exists in Set)
        ))

        _ <- B.s.a1.iSet.query.get.map(_ ==> List(
          ("a", Set(3, 4)),
          ("b", Set(1, 2, 3, 4)),
          ("c", Set(2, 3, 4)),
          ("x", Set(0, 1)), // not updated since it isn't referenced from A
        ))
      } yield ()
    }
  }
}
