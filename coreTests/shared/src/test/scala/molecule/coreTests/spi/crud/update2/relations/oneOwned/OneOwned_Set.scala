package molecule.coreTests.spi.crud.update2.relations.oneOwned

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait OneOwned_Set extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "id-filter - ref - value" - refs { implicit conn =>
      for {
        a <- A.i(1).save.transact.map(_.id)
        b <- A.i(2).OwnB.s("b").save.transact.map(_.id)
        c <- A.i(3).OwnB.s("c").iSet(Set(1, 2)).save.transact.map(_.id)

        // Filter by A ids, update B values
        _ <- A(a, b, c).OwnB.iSet(Set(2, 3)).update.transact

        _ <- A.i.a1.OwnB.iSet.query.get.map(_ ==> List(
          (1, Set(2, 3)), // relationship to B created, B attribute added
          (2, Set(2, 3)), // B attribute added
          (3, Set(2, 3)), // B attribute updated
        ))
      } yield ()
    }


    "filter - ref - value" - refs { implicit conn =>
      for {
        _ <- A.i(1).save.transact
        _ <- A.i(2).OwnB.s("b").save.transact
        _ <- A.i(3).OwnB.s("c").iSet(Set(1, 2)).save.transact

        // Filter by A attribute, update B values
        _ <- A.i_.OwnB.iSet(Set(2, 3)).update.transact

        _ <- A.i.a1.OwnB.iSet.query.get.map(_ ==> List(
          (1, Set(2, 3)), // relationship to B created, B attribute added
          (2, Set(2, 3)), // B attribute added
          (3, Set(2, 3)), // B attribute updated
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
        _ <- A.iSet(Set(4, 5)).OwnB.i_.update.transact

        _ <- A.iSet.OwnB.i.a1.query.get.map(_ ==> List(
          (Set(4, 5), 1), // A attribute added
          (Set(4, 5), 2), // A attribute updated
          (Set(4, 5), 3), // A attribute updated
        ))

        // Entity A without ref was not updated
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

        // Filter by B attribute, update B values
        _ <- A.OwnB.s_.iSet(Set(2, 3)).update.transact

        _ <- A.i.a1.OwnB.iSet.query.get.map(_ ==> List(
          (1, Set(2, 3)), // B attribute added
          (2, Set(2, 3)), // B attribute updated
        ))

        _ <- B.s.a1.iSet.query.get.map(_ ==> List(
          ("a", Set(2, 3)),
          ("b", Set(2, 3)),
          ("x", Set(0, 1)), // not updated since it isn't referenced from A
        ))
      } yield ()
    }


    "ref ref" - refs { implicit conn =>
      for {
        id <- A.iSet(Set(1)).OwnB.iSet(Set(2)).C.iSet(Set(3)).save.transact.map(_.id)
        _ <- A.iSet.OwnB.iSet.C.iSet.query.get.map(_ ==> List((Set(1), Set(2), Set(3))))

        // A
        _ <- A(id).iSet(Set(10)).update.transact
        _ <- A.iSet.OwnB.iSet.C.iSet.query.get.map(_ ==> List((Set(10), Set(2), Set(3))))

        // A + B
        _ <- A(id).iSet(Set(11)).OwnB.iSet(Set(20)).update.transact
        _ <- A.iSet.OwnB.iSet.C.iSet.query.get.map(_ ==> List((Set(11), Set(20), Set(3))))

        // B
        _ <- A(id).OwnB.iSet(Set(21)).update.transact
        _ <- A.iSet.OwnB.iSet.C.iSet.query.get.map(_ ==> List((Set(11), Set(21), Set(3))))

        // A + B + C
        _ <- A(id).iSet(Set(12)).OwnB.iSet(Set(22)).C.iSet(Set(30)).update.transact
        _ <- A.iSet.OwnB.iSet.C.iSet.query.get.map(_ ==> List((Set(12), Set(22), Set(30))))

        // A + C
        _ <- A(id).iSet(Set(13)).OwnB.C.iSet(Set(31)).update.transact
        _ <- A.iSet.OwnB.iSet.C.iSet.query.get.map(_ ==> List((Set(13), Set(22), Set(31))))

        // B + C
        _ <- A(id).OwnB.iSet(Set(23)).C.iSet(Set(32)).update.transact
        _ <- A.iSet.OwnB.iSet.C.iSet.query.get.map(_ ==> List((Set(13), Set(23), Set(32))))

        // C
        _ <- A(id).OwnB.C.iSet(Set(33)).update.transact
        _ <- A.iSet.OwnB.iSet.C.iSet.query.get.map(_ ==> List((Set(13), Set(23), Set(33))))
      } yield ()
    }


    "ref own" - refs { implicit conn =>
      for {
        id <- A.iSet(Set(1)).B.iSet(Set(2)).OwnC.iSet(Set(3)).save.transact.map(_.id)
        _ <- A.iSet.B.iSet.OwnC.iSet.query.get.map(_ ==> List((Set(1), Set(2), Set(3))))

        // A
        _ <- A(id).iSet(Set(10)).update.transact
        _ <- A.iSet.B.iSet.OwnC.iSet.query.get.map(_ ==> List((Set(10), Set(2), Set(3))))

        // A + B
        _ <- A(id).iSet(Set(11)).B.iSet(Set(20)).update.transact
        _ <- A.iSet.B.iSet.OwnC.iSet.query.get.map(_ ==> List((Set(11), Set(20), Set(3))))

        // B
        _ <- A(id).B.iSet(Set(21)).update.transact
        _ <- A.iSet.B.iSet.OwnC.iSet.query.get.map(_ ==> List((Set(11), Set(21), Set(3))))

        // A + B + C
        _ <- A(id).iSet(Set(12)).B.iSet(Set(22)).OwnC.iSet(Set(30)).update.transact
        _ <- A.iSet.B.iSet.OwnC.iSet.query.get.map(_ ==> List((Set(12), Set(22), Set(30))))

        // A + C
        _ <- A(id).iSet(Set(13)).B.OwnC.iSet(Set(31)).update.transact
        _ <- A.iSet.B.iSet.OwnC.iSet.query.get.map(_ ==> List((Set(13), Set(22), Set(31))))

        // B + C
        _ <- A(id).B.iSet(Set(23)).OwnC.iSet(Set(32)).update.transact
        _ <- A.iSet.B.iSet.OwnC.iSet.query.get.map(_ ==> List((Set(13), Set(23), Set(32))))

        // C
        _ <- A(id).B.OwnC.iSet(Set(33)).update.transact
        _ <- A.iSet.B.iSet.OwnC.iSet.query.get.map(_ ==> List((Set(13), Set(23), Set(33))))
      } yield ()
    }


    "own own" - refs { implicit conn =>
      for {
        id <- A.iSet(Set(1)).OwnB.iSet(Set(2)).OwnC.iSet(Set(3)).save.transact.map(_.id)
        _ <- A.iSet.OwnB.iSet.OwnC.iSet.query.get.map(_ ==> List((Set(1), Set(2), Set(3))))

        // A
        _ <- A(id).iSet(Set(10)).update.transact
        _ <- A.iSet.OwnB.iSet.OwnC.iSet.query.get.map(_ ==> List((Set(10), Set(2), Set(3))))

        // A + B
        _ <- A(id).iSet(Set(11)).OwnB.iSet(Set(20)).update.transact
        _ <- A.iSet.OwnB.iSet.OwnC.iSet.query.get.map(_ ==> List((Set(11), Set(20), Set(3))))

        // B
        _ <- A(id).OwnB.iSet(Set(21)).update.transact
        _ <- A.iSet.OwnB.iSet.OwnC.iSet.query.get.map(_ ==> List((Set(11), Set(21), Set(3))))

        // A + B + C
        _ <- A(id).iSet(Set(12)).OwnB.iSet(Set(22)).OwnC.iSet(Set(30)).update.transact
        _ <- A.iSet.OwnB.iSet.OwnC.iSet.query.get.map(_ ==> List((Set(12), Set(22), Set(30))))

        // A + C
        _ <- A(id).iSet(Set(13)).OwnB.OwnC.iSet(Set(31)).update.transact
        _ <- A.iSet.OwnB.iSet.OwnC.iSet.query.get.map(_ ==> List((Set(13), Set(22), Set(31))))

        // B + C
        _ <- A(id).OwnB.iSet(Set(23)).OwnC.iSet(Set(32)).update.transact
        _ <- A.iSet.OwnB.iSet.OwnC.iSet.query.get.map(_ ==> List((Set(13), Set(23), Set(32))))

        // C
        _ <- A(id).OwnB.OwnC.iSet(Set(33)).update.transact
        _ <- A.iSet.OwnB.iSet.OwnC.iSet.query.get.map(_ ==> List((Set(13), Set(23), Set(33))))
      } yield ()
    }


    "backref" - refs { implicit conn =>
      for {
        id <- A.iSet(Set(1)).OwnB.iSet(Set(2))._A.C.iSet(Set(3)).save.transact.map(_.id)
        _ <- A.iSet.OwnB.iSet._A.C.iSet.query.get.map(_ ==> List((Set(1), Set(2), Set(3))))

        // Updating A.OwnB.iSet and A.C.iSet
        _ <- A(id).iSet(Set(10)).OwnB.iSet(Set(20))._A.C.iSet(Set(30)).update.transact
        _ <- A.iSet.OwnB.iSet._A.C.iSet.query.get.map(_ ==> List((Set(10), Set(20), Set(30))))
      } yield ()
    }
  }
}
