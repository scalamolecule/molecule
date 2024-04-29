package molecule.coreTests.spi.crud.update2.relations.oneOwned

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._
import scala.concurrent.Future

trait OneOwned_Seq_remove extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "id-filter - ref - value" - refs { implicit conn =>
      for {
        a <- A.i(1).save.transact.map(_.id)
        b <- A.i(2).OwnB.s("b").save.transact.map(_.id)
        c <- A.i(3).OwnB.s("c").iSeq(Seq(1, 2, 1)).save.transact.map(_.id)
        d <- A.i(4).OwnB.s("c").iSeq(Seq(2, 3, 2)).save.transact.map(_.id)
        e <- A.i(5).OwnB.s("c").iSeq(Seq(3, 4, 3)).save.transact.map(_.id)

        // Filter by A ids, update B values
        _ <- A(a, b, c, d, e).OwnB.iSeq.remove(3, 4).update.transact

        // 2 entities left with remaining values
        _ <- A.i.a1.OwnB.iSeq.query.get.map(_ ==> List(
          (3, Seq(1, 2, 1)),
          (4, Seq(2, 2)),
        ))
      } yield ()
    }


    "filter - ref - value" - refs { implicit conn =>
      for {
        _ <- A.i(1).save.transact.map(_.id)
        _ <- A.i(2).OwnB.s("b").save.transact.map(_.id)
        _ <- A.i(3).OwnB.s("c").iSeq(Seq(1, 2, 1)).save.transact.map(_.id)
        _ <- A.i(4).OwnB.s("c").iSeq(Seq(2, 3, 2)).save.transact.map(_.id)
        _ <- A.i(5).OwnB.s("c").iSeq(Seq(3, 4, 3)).save.transact.map(_.id)

        // Filter by A ids, update B values
        _ <- A.i_.OwnB.iSeq.remove(3, 4).update.transact

        // 2 entities left with remaining values
        _ <- A.i.a1.OwnB.iSeq.query.get.map(_ ==> List(
          (3, Seq(1, 2, 1)),
          (4, Seq(2, 2)),
        ))
      } yield ()
    }


    "value - ref - filter" - refs { implicit conn =>
      for {
        _ <- A.iSeq(Seq(3, 7)).save.transact

        _ <- A.OwnB.i(2).save.transact
        _ <- A.iSeq(Seq(1, 2, 1)).OwnB.i(3).save.transact
        _ <- A.iSeq(Seq(2, 3, 2)).OwnB.i(4).save.transact
        _ <- A.iSeq(Seq(3, 4, 3)).OwnB.i(5).save.transact

        // Filter by B attribute, update A values
        _ <- A.iSeq.remove(3, 4).OwnB.i_.update.transact

        // 2 referencing A entities left with remaining values
        _ <- A.iSeq.OwnB.i.a1.query.get.map(_ ==> List(
          (Seq(1, 2, 1), 3),
          (Seq(2, 2), 4),
        ))

        // Initial entity without ref was not updated (3 not removed)
        _ <- if (database != "MongoDB") {
          // Entity A without ref was not updated
          A.iSeq.ownB_().query.get.map(_ ==> List(Seq(3, 7)))
        } else Future.unit
      } yield ()
    }


    "ref - filter/value" - refs { implicit conn =>
      for {
        // will not be updated (no update-filter match)
        _ <- B.s("x").iSeq(Seq(3, 7)).save.transact
        _ <- A.i(1).save.transact

        // will be updated
        _ <- A.i(2).OwnB.s("a").save.transact
        _ <- A.i(3).OwnB.s("b").iSeq(Seq(1, 2, 1)).save.transact
        _ <- A.i(4).OwnB.s("c").iSeq(Seq(2, 3, 2)).save.transact
        _ <- A.i(5).OwnB.s("d").iSeq(Seq(3, 4, 3)).save.transact

        // Filter by B attribute, update B values
        _ <- A.OwnB.s_.iSeq.remove(3, 4).update.transact

        // 2 referenced B entities left with remaining values
        _ <- A.i.a1.OwnB.iSeq.query.get.map(_ ==> List(
          (3, Seq(1, 2, 1)),
          (4, Seq(2, 2)),
        ))

        _ <- if (database == "MongoDB") {
          // Embedded data in Mongo have no separate entity ids
          B.s.a1.iSeq.query.get.map(_ ==> List(
            ("x", Seq(3, 7)), // not updated since it isn't referenced from A
          ))
        } else {
          B.s.a1.iSeq.query.get.map(_ ==> List(
            ("b", Seq(1, 2, 1)),
            ("c", Seq(2, 2)),
            ("x", Seq(3, 7)), // not updated since it isn't referenced from A
          ))
        }
      } yield ()
    }
  }
}
