package molecule.db.compliance.test.action.update.relation.one

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Refs.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*

case class One_Map_remove(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "id-filter - ref - value" - refs { implicit conn =>
    for {
      a <- A.i(1).save.transact.map(_.id)
      b <- A.i(2).B.s("b").save.transact.map(_.id)
      c <- A.i(3).B.s("c").iMap(Map(pint1, pint2)).save.transact.map(_.id)
      d <- A.i(4).B.s("c").iMap(Map(pint2, pint3)).save.transact.map(_.id)
      e <- A.i(5).B.s("c").iMap(Map(pint3, pint4)).save.transact.map(_.id)

      // Filter by A ids, update B values
      _ <- A(a, b, c, d, e).B.iMap.remove(string3, string4).update.transact

      // 2 entities left with remaining values
      _ <- A.i.a1.B.iMap.query.get.map(_ ==> List(
        (3, Map(pint1, pint2)),
        (4, Map(pint2)),
      ))
    } yield ()
  }


  "filter - ref - value" - refs { implicit conn =>
    for {
      _ <- A.i(1).save.transact
      _ <- A.i(2).B.s("b").save.transact
      _ <- A.i(3).B.s("c").iMap(Map(pint1, pint2)).save.transact
      _ <- A.i(4).B.s("c").iMap(Map(pint2, pint3)).save.transact
      _ <- A.i(5).B.s("c").iMap(Map(pint3, pint4)).save.transact

      // Filter by A ids, update B values
      _ <- A.i_.B.iMap.remove(string3, string4).update.transact

      // 2 entities left with remaining values
      _ <- A.i.a1.B.iMap.query.get.map(_ ==> List(
        (3, Map(pint1, pint2)),
        (4, Map(pint2)),
      ))
    } yield ()
  }


  "value - ref - filter" - refs { implicit conn =>
    for {
      _ <- A.iMap(Map(pint3, pint7)).save.transact

      _ <- A.s("x").B.i(2).save.transact
      _ <- A.iMap(Map(pint1, pint2)).B.i(3).save.transact
      _ <- A.iMap(Map(pint2, pint3)).B.i(4).save.transact
      _ <- A.iMap(Map(pint3, pint4)).B.i(5).save.transact

      // Filter by B attribute, update A values
      _ <- A.iMap.remove(string3, string4).B.i_.update.transact

      // 2 referencing A entities left with remaining values
      _ <- A.iMap.B.i.a1.query.get.map(_ ==> List(
        (Map(pint1, pint2), 3),
        (Map(pint2), 4),
      ))

      // Initial entity without ref was not updated (values 3 not removed)
      _ <- A.iMap.b_().query.get.map(_ ==> List(Map(pint3, pint7)))
    } yield ()
  }


  "ref - filter/value" - refs { implicit conn =>
    for {
      // will not be updated (no update filter match)
      _ <- B.s("x").iMap(Map(pint3, pint7)).save.transact
      _ <- A.i(1).save.transact

      // will be updated
      _ <- A.i(2).B.s("a").save.transact
      _ <- A.i(3).B.s("b").iMap(Map(pint1, pint2)).save.transact
      _ <- A.i(4).B.s("c").iMap(Map(pint2, pint3)).save.transact
      _ <- A.i(5).B.s("d").iMap(Map(pint3, pint4)).save.transact

      // Filter by B attribute, update B values
      _ <- A.B.s_.iMap.remove(string3, string4).update.transact

      // 2 referenced B entities left with remaining values
      _ <- A.i.a1.B.iMap.query.get.map(_ ==> List(
        (3, Map(pint1, pint2)),
        (4, Map(pint2)),
      ))

      _ <- B.s.a1.iMap.query.get.map(_ ==> List(
        ("b", Map(pint1, pint2)),
        ("c", Map(pint2)),
        ("x", Map(pint3, pint7)), // not updated since it isn't referenced from A
      ))
    } yield ()
  }
}
