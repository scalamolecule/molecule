package molecule.coreTests.spi.crud.update2.relations.oneOwned

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._
import scala.concurrent.Future

trait OneOwned_Seq extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "id-filter - ref - value" - refs { implicit conn =>
      for {
        a <- A.i(1).save.transact.map(_.id)
        b <- A.i(2).OwnB.s("b").save.transact.map(_.id)
        c <- A.i(3).OwnB.s("c").iSeq(Seq(1, 2)).save.transact.map(_.id)

        // Filter by A ids, update B values
        _ <- A(a, b, c).OwnB.iSeq(Seq(2, 3)).update.transact

        _ <- A.i.a1.OwnB.iSeq.query.get.map(_ ==> List(
          (1, Seq(2, 3)), // relationship to B created, B attribute added
          (2, Seq(2, 3)), // B attribute added
          (3, Seq(2, 3)), // B attribute updated
        ))
      } yield ()
    }


    "filter - ref - value" - refs { implicit conn =>
      for {
        _ <- A.i(1).save.transact
        _ <- A.i(2).OwnB.s("b").save.transact
        _ <- A.i(3).OwnB.s("c").iSeq(Seq(1, 2)).save.transact

        // Filter by A attribute, update B values
        _ <- A.i_.OwnB.iSeq(Seq(2, 3)).update.transact

        _ <- A.i.a1.OwnB.iSeq.query.get.map(_ ==> List(
          (1, Seq(2, 3)), // relationship to B created, B attribute added
          (2, Seq(2, 3)), // B attribute added
          (3, Seq(2, 3)), // B attribute updated
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
        _ <- A.iSeq(Seq(4, 5)).OwnB.i_.update.transact

        _ <- A.iSeq.OwnB.i.a1.query.get.map(_ ==> List(
          (Seq(4, 5), 1), // A attribute added
          (Seq(4, 5), 2), // A attribute updated
          (Seq(4, 5), 3), // A attribute updated
        ))

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

        // Filter by B attribute, update B values
        _ <- A.OwnB.s_.iSeq(Seq(2, 3)).update.transact

        _ <- A.i.a1.OwnB.iSeq.query.get.map(_ ==> List(
          (1, Seq(2, 3)), // B attribute added
          (2, Seq(2, 3)), // B attribute updated
        ))

        _ <- if (database == "MongoDB") {
          // Embedded data in Mongo have no separate entity ids
          B.s.a1.iSeq.query.get.map(_ ==> List(
            ("x", Seq(0, 1)), // not updated since it isn't referenced from A
          ))
        } else {
          B.s.a1.iSeq.query.get.map(_ ==> List(
            ("a", Seq(2, 3)),
            ("b", Seq(2, 3)),
            ("x", Seq(0, 1)), // not updated since it isn't referenced from A
          ))
        }
      } yield ()
    }


    "ref ref" - refs { implicit conn =>
      for {
        id <- A.iSeq(List(1)).OwnB.iSeq(List(2)).C.iSeq(List(3)).save.transact.map(_.id)
        _ <- A.iSeq.OwnB.iSeq.C.iSeq.query.get.map(_ ==> List((List(1), List(2), List(3))))

        // A
        _ <- A(id).iSeq(List(10)).update.transact
        _ <- A.iSeq.OwnB.iSeq.C.iSeq.query.get.map(_ ==> List((List(10), List(2), List(3))))

        // A + B
        _ <- A(id).iSeq(List(11)).OwnB.iSeq(List(20)).update.transact
        _ <- A.iSeq.OwnB.iSeq.C.iSeq.query.get.map(_ ==> List((List(11), List(20), List(3))))

        // B
        _ <- A(id).OwnB.iSeq(List(21)).update.transact
        _ <- A.iSeq.OwnB.iSeq.C.iSeq.query.get.map(_ ==> List((List(11), List(21), List(3))))

        // A + B + C
        _ <- A(id).iSeq(List(12)).OwnB.iSeq(List(22)).C.iSeq(List(30)).update.transact
        _ <- A.iSeq.OwnB.iSeq.C.iSeq.query.get.map(_ ==> List((List(12), List(22), List(30))))

        // A + C
        _ <- A(id).iSeq(List(13)).OwnB.C.iSeq(List(31)).update.transact
        _ <- A.iSeq.OwnB.iSeq.C.iSeq.query.get.map(_ ==> List((List(13), List(22), List(31))))

        // B + C
        _ <- A(id).OwnB.iSeq(List(23)).C.iSeq(List(32)).update.transact
        _ <- A.iSeq.OwnB.iSeq.C.iSeq.query.get.map(_ ==> List((List(13), List(23), List(32))))

        // C
        _ <- A(id).OwnB.C.iSeq(List(33)).update.transact
        _ <- A.iSeq.OwnB.iSeq.C.iSeq.query.get.map(_ ==> List((List(13), List(23), List(33))))
      } yield ()
    }


    "ref own" - refs { implicit conn =>
      for {
        id <- A.iSeq(List(1)).B.iSeq(List(2)).OwnC.iSeq(List(3)).save.transact.map(_.id)
        _ <- A.iSeq.B.iSeq.OwnC.iSeq.query.get.map(_ ==> List((List(1), List(2), List(3))))

        // A
        _ <- A(id).iSeq(List(10)).update.transact
        _ <- A.iSeq.B.iSeq.OwnC.iSeq.query.get.map(_ ==> List((List(10), List(2), List(3))))

        // A + B
        _ <- A(id).iSeq(List(11)).B.iSeq(List(20)).update.transact
        _ <- A.iSeq.B.iSeq.OwnC.iSeq.query.get.map(_ ==> List((List(11), List(20), List(3))))

        // B
        _ <- A(id).B.iSeq(List(21)).update.transact
        _ <- A.iSeq.B.iSeq.OwnC.iSeq.query.get.map(_ ==> List((List(11), List(21), List(3))))

        // A + B + C
        _ <- A(id).iSeq(List(12)).B.iSeq(List(22)).OwnC.iSeq(List(30)).update.transact
        _ <- A.iSeq.B.iSeq.OwnC.iSeq.query.get.map(_ ==> List((List(12), List(22), List(30))))

        // A + C
        _ <- A(id).iSeq(List(13)).B.OwnC.iSeq(List(31)).update.transact
        _ <- A.iSeq.B.iSeq.OwnC.iSeq.query.get.map(_ ==> List((List(13), List(22), List(31))))

        // B + C
        _ <- A(id).B.iSeq(List(23)).OwnC.iSeq(List(32)).update.transact
        _ <- A.iSeq.B.iSeq.OwnC.iSeq.query.get.map(_ ==> List((List(13), List(23), List(32))))

        // C
        _ <- A(id).B.OwnC.iSeq(List(33)).update.transact
        _ <- A.iSeq.B.iSeq.OwnC.iSeq.query.get.map(_ ==> List((List(13), List(23), List(33))))
      } yield ()
    }


    "own own" - refs { implicit conn =>
      for {
        id <- A.iSeq(List(1)).OwnB.iSeq(List(2)).OwnC.iSeq(List(3)).save.transact.map(_.id)
        _ <- A.iSeq.OwnB.iSeq.OwnC.iSeq.query.get.map(_ ==> List((List(1), List(2), List(3))))

        // A
        _ <- A(id).iSeq(List(10)).update.transact
        _ <- A.iSeq.OwnB.iSeq.OwnC.iSeq.query.get.map(_ ==> List((List(10), List(2), List(3))))

        // A + B
        _ <- A(id).iSeq(List(11)).OwnB.iSeq(List(20)).update.transact
        _ <- A.iSeq.OwnB.iSeq.OwnC.iSeq.query.get.map(_ ==> List((List(11), List(20), List(3))))

        // B
        _ <- A(id).OwnB.iSeq(List(21)).update.transact
        _ <- A.iSeq.OwnB.iSeq.OwnC.iSeq.query.get.map(_ ==> List((List(11), List(21), List(3))))

        // A + B + C
        _ <- A(id).iSeq(List(12)).OwnB.iSeq(List(22)).OwnC.iSeq(List(30)).update.transact
        _ <- A.iSeq.OwnB.iSeq.OwnC.iSeq.query.get.map(_ ==> List((List(12), List(22), List(30))))

        // A + C
        _ <- A(id).iSeq(List(13)).OwnB.OwnC.iSeq(List(31)).update.transact
        _ <- A.iSeq.OwnB.iSeq.OwnC.iSeq.query.get.map(_ ==> List((List(13), List(22), List(31))))

        // B + C
        _ <- A(id).OwnB.iSeq(List(23)).OwnC.iSeq(List(32)).update.transact
        _ <- A.iSeq.OwnB.iSeq.OwnC.iSeq.query.get.map(_ ==> List((List(13), List(23), List(32))))

        // C
        _ <- A(id).OwnB.OwnC.iSeq(List(33)).update.transact
        _ <- A.iSeq.OwnB.iSeq.OwnC.iSeq.query.get.map(_ ==> List((List(13), List(23), List(33))))
      } yield ()
    }


    "backref" - refs { implicit conn =>
      for {
        id <- A.iSeq(List(1)).OwnB.iSeq(List(2))._A.C.iSeq(List(3)).save.transact.map(_.id)
        _ <- A.iSeq.OwnB.iSeq._A.C.iSeq.query.get.map(_ ==> List((List(1), List(2), List(3))))

        // Updating A.OwnB.iSeq and A.C.iSeq
        _ <- A(id).iSeq(List(10)).OwnB.iSeq(List(20))._A.C.iSeq(List(30)).update.transact
        _ <- A.iSeq.OwnB.iSeq._A.C.iSeq.query.get.map(_ ==> List((List(10), List(20), List(30))))
      } yield ()
    }


    "no synthetic orphans" - refs { implicit conn =>
      for {
        a <- A.iSeq(Seq(1, 2)).save.transact.map(_.id)
        _ <- A(a).iSeq(Seq(3, 4)).update.transact

        _ <- {
          database match {
            case "Datomic" =>
              for {
                _ <- rawQuery(s"[:find (count ?e) :where [_ :A/iSeq ?e]]").map(_.head ==> List(2))
                _ <- rawQuery(s"[:find (count ?e) :where [?e :A.iSeq/i_ ?i]]").map(_.head ==> List(2))
                _ <- rawQuery(s"[:find (count ?e) :where [?e :A.iSeq/v_ ?v]]").map(_.head ==> List(2))
              } yield ()

            case "MongoDB" =>
              // Mongo implementation doesn't need synthetic entities
              Future.unit

            case other => throw new Exception(s"Missing $other test implementation.")
          }
        }
      } yield ()
    }
  }
}
