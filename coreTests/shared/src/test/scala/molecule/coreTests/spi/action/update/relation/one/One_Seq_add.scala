package molecule.coreTests.spi.action.update.relation.one

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.domains.dsl.Refs._
import molecule.coreTests.setup._

case class One_Seq_add(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api._
  import suite._

  "id-filter - ref - value" - refs { implicit conn =>
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

      // Filter by A attribute, upsert B values
      _ <- A(a, b, c).B.iSeq.add(5, 6).upsert.transact

      _ <- A.i.a1.B.iSeq.query.get.map(_ ==> List(
        (1, Seq(5, 6)), //             relationship to B created, B values added
        (2, Seq(5, 6)), //             B attribute added
        (3, Seq(3, 4, 4, 5, 5, 6)), // B attribute updated
      ))
    } yield ()
  }


  "filter - ref - value" - refs { implicit conn =>
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

      // Filter by A attribute, upsert B values
      _ <- A.i_.B.iSeq.add(5, 6).upsert.transact

      _ <- A.i.a1.B.iSeq.query.get.map(_ ==> List(
        (1, Seq(5, 6)), //             relationship to B created, B values added
        (2, Seq(5, 6)), //             B attribute added
        (3, Seq(3, 4, 4, 5, 5, 6)), // B attribute updated
      ))
    } yield ()
  }


  "value - ref - filter" - refs { implicit conn =>
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

      // Filter by B attribute, upsert A values
      _ <- A.iSeq.add(5, 6).B.i_.upsert.transact

      _ <- A.iSeq.B.i.a1.query.get.map(_ ==> List(
        (Seq(5, 6), 1), //             A attribute inserted
        (Seq(2, 3, 4, 5, 5, 6), 2), // A attribute updated
        (Seq(3, 4, 4, 5, 5, 6), 3), // A attribute updated
      ))

      // Initial entity without ref was not updated (values 0 and 1 not changed)
      _ <- A.iSeq.b_().query.get.map(_ ==> List(Seq(0, 1)))
    } yield ()
  }


  "ref - filter/value" - refs { implicit conn =>
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

      // Filter by B attribute, upsert B values
      _ <- A.B.s_.iSeq.add(5, 6).upsert.transact

      _ <- A.i.a1.B.iSeq.query.get.map(_ ==> List(
        (1, Seq(5, 6)), //             B attribute added
        (2, Seq(2, 3, 4, 5, 5, 6)), // B attribute updated
        (3, Seq(3, 4, 4, 5, 5, 6)), // B attribute updated
      ))

      _ <- B.s.a1.iSeq.query.get.map(_ ==> List(
        ("a", Seq(5, 6)),
        ("b", Seq(2, 3, 4, 5, 5, 6)),
        ("c", Seq(3, 4, 4, 5, 5, 6)),
        ("x", Seq(0, 1)), // not updated since it isn't referenced from A
      ))
    } yield ()
  }
}
