package molecule.db.compliance.test.crud.update.relation.one

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Refs.*
import molecule.db.compliance.setup.DbProviders

case class One_Seq(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "id-filter - ref - value" - refs {
    for {
      a <- A.i(1).save.transact.map(_.id)
      b <- A.i(2).B.s("b").save.transact.map(_.id)
      c <- A.i(3).B.s("c").iSeq(Seq(3, 4)).save.transact.map(_.id)

      // Current entity with A value and ref to B value
      _ <- A.i.a1.B.iSeq.query.get.map(_ ==> List(
        (3, Seq(3, 4))
      ))

      // Filter by A value, update existing B values
      _ <- A(a, b, c).B.iSeq(Seq(4, 5)).update.transact

      _ <- A.i.a1.B.iSeq.query.get.map(_ ==> List(
        (3, Seq(4, 5)) // B value updated since there was a previous value
      ))
    } yield ()
  }


  "filter - ref - value" - refs {
    for {
      _ <- A.i(1).save.transact
      _ <- A.i(2).B.s("b").save.transact
      _ <- A.i(3).B.s("c").iSeq(Seq(3, 4)).save.transact

      // Current entity with A value and ref to B value
      _ <- A.i.a1.B.iSeq.query.get.map(_ ==> List(
        (3, Seq(3, 4))
      ))

      // Filter by A value, update existing B values
      _ <- A.i_.B.iSeq(Seq(4, 5)).update.transact

      _ <- A.i.a1.B.iSeq.query.get.map(_ ==> List(
        (3, Seq(4, 5)) // B value updated since there was a previous value
      ))
    } yield ()
  }


  "value - ref - filter" - refs {
    for {
      _ <- A.iSeq(Seq(0, 1)).save.transact // won't be updated since there's no B value
      _ <- A.s("x").B.i(1).save.transact
      _ <- A.iSeq(Seq(2, 3)).B.i(2).save.transact
      _ <- A.iSeq(Seq(3, 4)).B.i(3).save.transact

      // Current 2 entities with A value and ref to B value
      _ <- A.iSeq.B.i.a1.query.get.map(_ ==> List(
        (Seq(2, 3), 2),
        (Seq(3, 4), 3),
      ))

      // Filter by B value, update A values
      _ <- A.iSeq(Seq(4, 5)).B.i_.update.transact

      _ <- A.iSeq.B.i.a1.query.get.map(_ ==> List(
        (Seq(4, 5), 2), // A value updated
        (Seq(4, 5), 3), // A value updated
      ))
    } yield ()
  }


  "ref - filter/value" - refs {
    for {
      // will not be updated (no update filter match)
      _ <- B.s("x").iSeq(Seq(0, 1)).save.transact
      _ <- A.i(1).save.transact

      // will be updated
      _ <- A.i(2).B.s("b").save.transact
      _ <- A.i(3).B.s("c").iSeq(Seq(3, 4)).save.transact

      // Current entity with A value and ref to B value
      _ <- A.i.a1.B.iSeq.query.get.map(_ ==> List(
        (3, Seq(3, 4))
      ))

      // Filter by B value, update existing B values
      _ <- A.B.s_.iSeq(Seq(4, 5)).update.transact

      _ <- A.i.a1.B.iSeq.query.get.map(_ ==> List(
        (3, Seq(4, 5)), // B value updated since there was a previous value
      ))
    } yield ()
  }


  "ref ref" - refs {
    for {
      id <- A.iSeq(List(1)).B.iSeq(List(2)).C.iSeq(List(3)).save.transact.map(_.id)
      _ <- A.iSeq.B.iSeq.C.iSeq.query.get.map(_ ==> List((List(1), List(2), List(3))))

      // A
      _ <- A(id).iSeq(List(10)).update.transact
      _ <- A.iSeq.B.iSeq.C.iSeq.query.get.map(_ ==> List((List(10), List(2), List(3))))

      // A + B
      _ <- A(id).iSeq(List(11)).B.iSeq(List(20)).update.transact
      _ <- A.iSeq.B.iSeq.C.iSeq.query.get.map(_ ==> List((List(11), List(20), List(3))))

      // B
      _ <- A(id).B.iSeq(List(21)).update.transact
      _ <- A.iSeq.B.iSeq.C.iSeq.query.get.map(_ ==> List((List(11), List(21), List(3))))

      // A + B + C
      _ <- A(id).iSeq(List(12)).B.iSeq(List(22)).C.iSeq(List(30)).update.transact
      _ <- A.iSeq.B.iSeq.C.iSeq.query.get.map(_ ==> List((List(12), List(22), List(30))))

      // A + C
      _ <- A(id).iSeq(List(13)).B.C.iSeq(List(31)).update.transact
      _ <- A.iSeq.B.iSeq.C.iSeq.query.get.map(_ ==> List((List(13), List(22), List(31))))

      // B + C
      _ <- A(id).B.iSeq(List(23)).C.iSeq(List(32)).update.transact
      _ <- A.iSeq.B.iSeq.C.iSeq.query.get.map(_ ==> List((List(13), List(23), List(32))))

      // C
      _ <- A(id).B.C.iSeq(List(33)).update.transact
      _ <- A.iSeq.B.iSeq.C.iSeq.query.get.map(_ ==> List((List(13), List(23), List(33))))
    } yield ()
  }
}
