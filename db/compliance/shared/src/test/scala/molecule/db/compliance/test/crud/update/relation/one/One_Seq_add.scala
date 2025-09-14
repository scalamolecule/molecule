package molecule.db.compliance.test.crud.update.relation.one

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Refs.*
import molecule.db.compliance.setup.DbProviders

case class One_Seq_add(
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

      // Filter by A attribute, update B values
      _ <- A(a, b, c).B.iSeq.add(4, 5).update.transact

      _ <- A.i.a1.B.iSeq.query.get.map(_ ==> List(
        (3, Seq(3, 4, 4, 5)) // B attribute updated, all values appended (List semantics)
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

      // Filter by A attribute, update B values
      _ <- A.i_.B.iSeq.add(4, 5).update.transact

      _ <- A.i.a1.B.iSeq.query.get.map(_ ==> List(
        (3, Seq(3, 4, 4, 5)) // B attribute updated, all values appended (List semantics)
      ))
    } yield ()
  }


  "value - ref - filter" - refs {
    for {
      _ <- A.iSeq(Seq(0, 1)).save.transact // won't be updated since there's no B value
      _ <- A.s("x").B.i(1).save.transact
      _ <- A.iSeq(Seq(2, 3)).B.i(2).save.transact
      _ <- A.iSeq(Seq(3, 4)).B.i(3).save.transact

      // Current entities with A value and ref to B value
      _ <- A.iSeq.B.i.a1.query.get.map(_ ==> List(
        (Seq(2, 3), 2),
        (Seq(3, 4), 3),
      ))

      // Filter by B attribute, update A values
      _ <- A.iSeq.add(4, 5).B.i_.update.transact

      _ <- A.iSeq.B.i.a1.query.get.map(_ ==> List(
        (Seq(2, 3, 4, 5), 2),
        (Seq(3, 4, 4, 5), 3),
      ))
    } yield ()
  }


  "ref - filter/value" - refs {
    for {
      // will not be updated (no update filter match)
      _ <- B.s("x").iSeq(Seq(0, 1)).save.transact
      _ <- A.i(0).save.transact

      // will be updated
      _ <- A.i(1).B.s("a").save.transact
      _ <- A.i(2).B.s("b").iSeq(Seq(2, 3)).save.transact
      _ <- A.i(3).B.s("c").iSeq(Seq(3, 4)).save.transact

      // Current entities with A value and ref to B value
      _ <- A.i.a1.B.iSeq.query.get.map(_ ==> List(
        (2, Seq(2, 3)),
        (3, Seq(3, 4)),
      ))

      // Filter by B attribute, update B values
      _ <- A.B.s_.iSeq.add(4, 5).update.transact

      _ <- A.i.a1.B.iSeq.query.get.map(_ ==> List(
        (2, Seq(2, 3, 4, 5)),
        (3, Seq(3, 4, 4, 5)),
      ))
    } yield ()
  }
}
