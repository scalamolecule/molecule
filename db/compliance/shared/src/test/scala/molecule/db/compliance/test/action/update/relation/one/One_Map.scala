package molecule.db.compliance.test.action.update.relation.one

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Refs.*
import molecule.db.compliance.setup.DbProviders

case class One_Map(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "id-filter - ref - value" - refs {
    for {
      a <- A.i(1).save.transact.map(_.id)
      b <- A.i(2).B.s("b").save.transact.map(_.id)
      c <- A.i(3).B.s("c").iMap(Map(pint3, pint4)).save.transact.map(_.id)

      // Current entity with A value and ref to B value
      _ <- A.i.a1.B.iMap.query.get.map(_ ==> List(
        (3, Map(pint3, pint4))
      ))

      // Filter by A value, update existing B values
      _ <- A(a, b, c).B.iMap(Map(pint4, pint5)).update.transact

      _ <- A.i.a1.B.iMap.query.get.map(_ ==> List(
        (3, Map(pint4, pint5)) // B value updated since there was a previous value
      ))
    } yield ()
  }


  "filter - ref - value" - refs {
    for {
      _ <- A.i(1).save.transact
      _ <- A.i(2).B.s("b").save.transact
      _ <- A.i(3).B.s("c").iMap(Map(pint3, pint4)).save.transact

      // Current entity with A value and ref to B value
      _ <- A.i.a1.B.iMap.query.get.map(_ ==> List(
        (3, Map(pint3, pint4))
      ))

      // Filter by A value, update existing B values
      _ <- A.i_.B.iMap(Map(pint4, pint5)).update.transact

      _ <- A.i.a1.B.iMap.query.get.map(_ ==> List(
        (3, Map(pint4, pint5)) // B value updated since there was a previous value
      ))
    } yield ()
  }


  "value - ref - filter" - refs {
    for {
      _ <- A.iMap(Map(pint0, pint1)).save.transact // won't be updated since there's no B value
      _ <- A.s("x").B.i(1).save.transact
      _ <- A.iMap(Map(pint2, pint3)).B.i(2).save.transact
      _ <- A.iMap(Map(pint3, pint4)).B.i(3).save.transact

      // Current 2 entities with A value and ref to B value
      _ <- A.iMap.B.i.a1.query.get.map(_ ==> List(
        (Map(pint2, pint3), 2),
        (Map(pint3, pint4), 3),
      ))

      // Filter by B value, update A values
      _ <- A.iMap(Map(pint4, pint5)).B.i_.update.transact

      _ <- A.iMap.B.i.a1.query.get.map(_ ==> List(
        (Map(pint4, pint5), 2), // A value updated
        (Map(pint4, pint5), 3), // A value updated
      ))
    } yield ()
  }


  "ref - filter/value" - refs {
    for {
      // will not be updated (no update filter match)
      _ <- B.s("x").iMap(Map(pint0, pint1)).save.transact
      _ <- A.i(1).save.transact

      // will be updated
      _ <- A.i(2).B.s("b").save.transact
      _ <- A.i(3).B.s("c").iMap(Map(pint3, pint4)).save.transact

      // Current entity with A value and ref to B value
      _ <- A.i.a1.B.iMap.query.get.map(_ ==> List(
        (3, Map(pint3, pint4))
      ))

      // Filter by B value, update existing B values
      _ <- A.B.s_.iMap(Map(pint4, pint5)).update.transact

      _ <- A.i.a1.B.iMap.query.get.map(_ ==> List(
        (3, Map(pint4, pint5)), // B value updated since there was a previous value
      ))
    } yield ()
  }


  "ref ref" - refs {
    for {
      id <- A.iMap(Map(pint1)).B.iMap(Map(pint2)).C.iMap(Map(pint3)).save.transact.map(_.id)
      _ <- A.iMap.B.iMap.C.iMap.query.get.map(_ ==> List((Map(pint1), Map(pint2), Map(pint3))))

      // A
      _ <- A(id).iMap(Map(pint1)).update.transact
      _ <- A.iMap.B.iMap.C.iMap.query.get.map(_ ==> List((Map(pint1), Map(pint2), Map(pint3))))

      // A + B
      _ <- A(id).iMap(Map(pint2)).B.iMap(Map(pint1)).update.transact
      _ <- A.iMap.B.iMap.C.iMap.query.get.map(_ ==> List((Map(pint2), Map(pint1), Map(pint3))))

      // B
      _ <- A(id).B.iMap(Map(pint2)).update.transact
      _ <- A.iMap.B.iMap.C.iMap.query.get.map(_ ==> List((Map(pint2), Map(pint2), Map(pint3))))

      // A + B + C
      _ <- A(id).iMap(Map(pint3)).B.iMap(Map(pint3)).C.iMap(Map(pint1)).update.transact
      _ <- A.iMap.B.iMap.C.iMap.query.get.map(_ ==> List((Map(pint3), Map(pint3), Map(pint1))))

      // A + C
      _ <- A(id).iMap(Map(pint4)).B.C.iMap(Map(pint2)).update.transact
      _ <- A.iMap.B.iMap.C.iMap.query.get.map(_ ==> List((Map(pint4), Map(pint3), Map(pint2))))

      // B + C
      _ <- A(id).B.iMap(Map(pint4)).C.iMap(Map(pint3)).update.transact
      _ <- A.iMap.B.iMap.C.iMap.query.get.map(_ ==> List((Map(pint4), Map(pint4), Map(pint3))))

      // C
      _ <- A(id).B.C.iMap(Map(pint4)).update.transact
      _ <- A.iMap.B.iMap.C.iMap.query.get.map(_ ==> List((Map(pint4), Map(pint4), Map(pint4))))
    } yield ()
  }
}