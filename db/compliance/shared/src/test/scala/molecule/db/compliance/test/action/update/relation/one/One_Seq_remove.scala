package molecule.db.compliance.test.action.update.relation.one

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Refs.*
import molecule.db.compliance.setup.DbProviders

case class One_Seq_remove(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "id-filter - ref - value" - refs {
    for {
      a <- A.i(1).save.transact.map(_.id)
      b <- A.i(2).B.s("b").save.transact.map(_.id)
      c <- A.i(3).B.s("c").iSeq(Seq(1, 2, 1)).save.transact.map(_.id)
      d <- A.i(4).B.s("c").iSeq(Seq(2, 3, 2)).save.transact.map(_.id)
      e <- A.i(5).B.s("c").iSeq(Seq(3, 4, 3)).save.transact.map(_.id)

      // Filter by A ids, update B values
      _ <- A(a, b, c, d, e).B.iSeq.remove(3, 4).update.transact

      // 2 entities left with remaining values
      _ <- A.i.a1.B.iSeq.query.get.map(_ ==> List(
        (3, Seq(1, 2, 1)),
        (4, Seq(2, 2)),
      ))
    } yield ()
  }


  "filter - ref - value" - refs {
    for {
      _ <- A.i(1).save.transact
      _ <- A.i(2).B.s("b").save.transact
      _ <- A.i(3).B.s("c").iSeq(Seq(1, 2, 1)).save.transact
      _ <- A.i(4).B.s("c").iSeq(Seq(2, 3, 2)).save.transact
      _ <- A.i(5).B.s("c").iSeq(Seq(3, 4, 3)).save.transact

      // Filter by A ids, update B values
      _ <- A.i_.B.iSeq.remove(3, 4).update.transact

      // 2 entities left with remaining values
      _ <- A.i.a1.B.iSeq.query.get.map(_ ==> List(
        (3, Seq(1, 2, 1)),
        (4, Seq(2, 2)),
      ))
    } yield ()
  }


  "value - ref - filter" - refs {
    for {
      _ <- A.iSeq(Seq(3, 7)).save.transact

      _ <- A.s("x").B.i(2).save.transact
      _ <- A.iSeq(Seq(1, 2, 1)).B.i(3).save.transact
      _ <- A.iSeq(Seq(2, 3, 2)).B.i(4).save.transact
      _ <- A.iSeq(Seq(3, 4, 3)).B.i(5).save.transact

      // Filter by B attribute, update A values
      _ <- A.iSeq.remove(3, 4).B.i_.update.transact

      // 2 referencing A entities left with remaining values
      _ <- A.iSeq.B.i.a1.query.get.map(_ ==> List(
        (Seq(1, 2, 1), 3),
        (Seq(2, 2), 4),
      ))

      // Initial entity without ref was not updated (3 not removed)
      _ <- A.iSeq.b_().query.get.map(_ ==> List(Seq(3, 7)))
    } yield ()
  }


  "ref - filter/value" - refs {
    for {
      // will not be updated (no update-filter match)
      _ <- B.s("x").iSeq(Seq(3, 7)).save.transact
      _ <- A.i(1).save.transact

      // will be updated
      _ <- A.i(2).B.s("a").save.transact
      _ <- A.i(3).B.s("b").iSeq(Seq(1, 2, 1)).save.transact
      _ <- A.i(4).B.s("c").iSeq(Seq(2, 3, 2)).save.transact
      _ <- A.i(5).B.s("d").iSeq(Seq(3, 4, 3)).save.transact

      // Filter by B attribute, update B values
      _ <- A.B.s_.iSeq.remove(3, 4).update.transact

      // 2 referenced B entities left with remaining values
      _ <- A.i.a1.B.iSeq.query.get.map(_ ==> List(
        (3, Seq(1, 2, 1)),
        (4, Seq(2, 2)),
      ))

      _ <- B.s.a1.iSeq.query.get.map(_ ==> List(
        ("b", Seq(1, 2, 1)),
        ("c", Seq(2, 2)),
        ("x", Seq(3, 7)), // not updated since it isn't referenced from A
      ))
    } yield ()
  }
}
