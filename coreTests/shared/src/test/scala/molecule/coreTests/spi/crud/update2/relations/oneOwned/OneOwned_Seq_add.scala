package molecule.coreTests.spi.crud.update2.relations.oneOwned

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._
import scala.concurrent.Future

trait OneOwned_Seq_add extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "id-filter - ref - value" - refs { implicit conn =>
      for {
        a <- A.i(1).save.transact.map(_.id)
        b <- A.i(2).OwnB.s("b").save.transact.map(_.id)
        c <- A.i(3).OwnB.s("c").iSeq(Seq(1, 2)).save.transact.map(_.id)

        // Filter by A ids, update B values
        _ <- A(a, b, c).OwnB.iSeq.add(2, 3).update.transact

        _ <- A.i.a1.OwnB.iSeq.query.get.map(_ ==> List(
          (1, Seq(2, 3)), //       relationship to B created, B attribute added
          (2, Seq(2, 3)), //       B attribute added
          (3, Seq(1, 2, 2, 3)), // B attribute updated, all values appended (List semantics)
        ))
      } yield ()
    }


    "filter - ref - value" - refs { implicit conn =>
      for {
        _ <- A.i(1).save.transact
        _ <- A.i(2).OwnB.s("b").save.transact
        _ <- A.i(3).OwnB.s("c").iSeq(Seq(1, 2)).save.transact

        // Filter by A attribute, update B values
        _ <- A.i_.OwnB.iSeq.add(2, 3).update.transact

        _ <- A.i.a1.OwnB.iSeq.query.get.map(_ ==> List(
          (1, Seq(2, 3)), //       relationship to B created, B attribute added
          (2, Seq(2, 3)), //       B attribute added
          (3, Seq(1, 2, 2, 3)), // B attribute updated
        ))
      } yield ()
    }


    "value - ref - filter" - refs { implicit conn =>
      for {
        _ <- A.iSeq(Seq(0, 1)).save.transact

        _ <- A.OwnB.i(1).save.transact
        _ <- A.iSeq(Seq(2, 3)).OwnB.i(2).save.transact
        _ <- A.iSeq(Seq(3, 4)).OwnB.i(3).save.transact

        // Filter by B attribute, update A values
        _ <- A.iSeq.add(4, 5).OwnB.i_.update.transact

        _ <- A.iSeq.OwnB.i.a1.query.get.map(_ ==> List(
          (Seq(4, 5), 1), //       A attribute added
          (Seq(2, 3, 4, 5), 2), // A attribute updated
          (Seq(3, 4, 4, 5), 3), // A attribute updated
        ))

        // Initial entity without ref was not updated (values 0 and 1 not changed)
        _ <- if (database != "MongoDB") {
          // Entity A without ref was not updated
          A.iSeq.ownB_().query.get.map(_ ==> List(Seq(0, 1)))
        } else Future.unit
      } yield ()
    }


    "ref - filter/value" - refs { implicit conn =>
      for {
        // will not be updated (no update filter match)
        _ <- B.s("x").iSeq(Seq(0, 1)).save.transact
        _ <- A.i(0).save.transact

        // will be updated
        _ <- A.i(1).OwnB.s("a").save.transact
        _ <- A.i(2).OwnB.s("b").iSeq(Seq(1, 2)).save.transact
        _ <- A.i(3).OwnB.s("c").iSeq(Seq(2, 3)).save.transact

        // Filter by B attribute, update B values
        _ <- A.OwnB.s_.iSeq.add(3, 4).update.transact

        _ <- A.i.a1.OwnB.iSeq.query.get.map(_ ==> List(
          (1, Seq(3, 4)), //       B attribute added
          (2, Seq(1, 2, 3, 4)), // B attribute updated
          (3, Seq(2, 3, 3, 4)), // B attribute updated (3 not added - already exists in Seq)
        ))

        _ <- if (database == "MongoDB") {
          // Embedded documents in Mongo have no separate entity ids
          B.s.a1.iSeq.query.get.map(_ ==> List(
            ("x", Seq(0, 1)), // not updated since it isn't referenced from A
          ))
        } else {
          B.s.a1.iSeq.query.get.map(_ ==> List(
            ("a", Seq(3, 4)),
            ("b", Seq(1, 2, 3, 4)),
            ("c", Seq(2, 3, 3, 4)),
            ("x", Seq(0, 1)), // not updated since it isn't referenced from A
          ))
        }
      } yield ()
    }
  }
}
