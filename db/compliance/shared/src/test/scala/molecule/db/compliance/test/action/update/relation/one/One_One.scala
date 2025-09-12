package molecule.db.compliance.test.action.update.relation.one

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Refs.*
import molecule.db.compliance.setup.DbProviders

case class One_One(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "id - ref - value" - refs {
    for {
      a <- A.i(1).save.transact.map(_.id)
      b <- A.i(2).B.s("b").save.transact.map(_.id)
      c <- A.i(3).B.s("c").i(3).save.transact.map(_.id)

      // Current entity with A value and ref to B value
      _ <- A.i.a1.B.i.query.get.map(_ ==> List(
        (3, 3)
      ))

      // Filter by A ids, update existing B values
      _ <- A(a, b, c).B.i(4).update.transact

      _ <- A.i.a1.B.i.query.get.map(_ ==> List(
        (3, 4) // B value updated since there was a previous value
      ))
    } yield ()
  }


  "filter - ref - value" - refs {
    for {
      _ <- A.i(1).save.transact
      _ <- A.i(2).B.s("b").save.transact
      _ <- A.i(3).B.s("c").i(3).save.transact

      // Current entity with A value and ref to B value
      _ <- A.i.a1.B.i.query.get.map(_ ==> List(
        (3, 3)
      ))

      // Filter by A value, update existing B values
      _ <- A.i_.B.i(4).update.transact

      _ <- A.i.a1.B.i.query.get.map(_ ==> List(
        (3, 4) // B value updated since there was a previous value
      ))
    } yield ()
  }


  "value - ref - filter" - refs {
    for {
      _ <- A.i(0).save.transact // won't be updated since there's no B value
      _ <- A.s("x").B.i(1).save.transact
      _ <- A.i(2).B.i(2).save.transact
      _ <- A.i(3).B.i(3).save.transact

      // Current 2 entities with A value and ref to B value
      _ <- A.i.B.i.a1.query.get.map(_ ==> List(
        (2, 2),
        (3, 3),
      ))

      // Filter by B value, update A values
      _ <- A.i(4).B.i_.update.transact

      _ <- A.i.B.i.a1.query.get.map(_ ==> List(
        (4, 2), // A value updated
        (4, 3), // A value updated
      ))
    } yield ()
  }


  "ref - filter/value" - refs {
    for {
      // will not be updated (no update filter match)
      _ <- B.s("x").i(0).save.transact
      _ <- A.i(1).save.transact

      // will be updated
      _ <- A.i(2).B.s("b").save.transact
      _ <- A.i(3).B.s("c").i(3).save.transact

      // Current entity with A value and ref to B value
      _ <- A.i.a1.B.i.query.get.map(_ ==> List(
        (3, 3)
      ))

      // Filter by B value, update existing B values
      _ <- A.B.s_.i(4).update.transact

      _ <- A.i.a1.B.i.query.get.map(_ ==> List(
        (3, 4), // B value updated since there was a previous value
      ))
    } yield ()
  }


  "value - ref - value/filter" - refs {
    for {
      _ <- A.i(1).save.transact
      _ <- A.s("x").B.i(2).save.transact
      _ <- A.i(3).B.s("a").save.transact
      _ <- A.i(4).B.s("b").i(4).save.transact
      _ <- A.i(5).B.s("c").i(5).save.transact

      _ <- A.i.B.s.i.a1.query.get.map(_ ==> List(
        (4, "b", 4),
        (5, "c", 5),
      ))

      _ <- A.i(6).B.s("x").i_.update.transact

      _ <- A.i.B.s.i.a1.query.get.map(_ ==> List(
        (6, "x", 4), // A and B values updated
        (6, "x", 5), // A and B values updated
      ))
    } yield ()
  }


  "value/filter - ref - value" - refs {
    for {
      _ <- A.i(1).s("a").save.transact
      _ <- A.i(2).B.s("b").save.transact
      _ <- A.i(3).B.i(3).save.transact
      _ <- A.i(4).s("d").B.i(4).save.transact

      _ <- A.i.s.a1.B.i.query.get.map(_ ==> List(
        (4, "d", 4),
      ))

      _ <- A.i_.s("x").B.i(5).update.transact

      _ <- A.i.a1.s.B.i.query.get.map(_ ==> List(
        (4, "x", 5), // A and B values updated
      ))
    } yield ()
  }


  "All ids known" - refs {
    for {
      _ <- A.i(1).B.i(1).C.s("c").save.transact // no matching C.i
      _ <- A.i(2).B.i(2).C.i(2).save.transact

      // 1 filter attribute (C.i)
      _ <- A.i(3).B.i(3).C.i_.update.transact
      _ <- A.i.B.i.C.i.query.get.map(_ ==> List(
        (3, 3, 2)
      ))

      // 2 filter attributes (B.i and C.i)
      _ <- A.i(4).B.i_.C.i_.update.transact
      _ <- A.i.B.i.C.i.query.get.map(_ ==> List(
        (4, 3, 2)
      ))

      // 2 filter attributes (A.i and C.i)
      _ <- A.i_.B.i(5).C.i_.update.transact
      _ <- A.i.B.i.C.i.query.get.map(_ ==> List(
        (4, 5, 2)
      ))
    } yield ()
  }


  "1 ref unknown (C), 1 filter (B.i)" - refs {
    for {
      _ <- A.s("a").B.s("b").save.transact // no B.i filter match
      _ <- A.i(1).B.s("b").save.transact // no B.i filter match
      _ <- A.s("a").B.i(2).save.transact
      _ <- A.i(3).B.i(3).save.transact

      _ <- A.i(4).B.s("b").C.i(4).save.transact // no B.i filter match
      _ <- A.s("a").B.i(5).C.s("c").save.transact
      _ <- A.s("a").B.i(6).C.i(6).save.transact
      _ <- A.i(7).B.i(7).C.s("c").save.transact
      _ <- A.i(8).B.i(8).C.i(8).save.transact

      // Not filtering on C attribute makes ref to C unknown

      // Only entities having B.i value will have existing A.i and C.i values updated
      _ <- A.i(9).B.i_.C.i(9).update.transact
      _ <- A.i.B.i.C.i.query.get.map(_ ==> List(
        (9, 8, 9) // A.i and C.i updated
      ))
    } yield ()
  }


  "1 ref unknown (C), 2 filters (A.i and B.i)" - refs {
    for {
      _ <- A.s("a").B.s("b").save.transact // no A.i or B.i filter match
      _ <- A.s("a").B.i(1).save.transact // no A.i filter match
      _ <- A.i(2).B.s("b").save.transact // no B.i filter match
      _ <- A.i(3).B.i(3).save.transact

      _ <- A.s("a").B.i(4).C.i(4).save.transact // no A.i filter match
      _ <- A.i(5).B.s("b").C.i(5).save.transact // no B.i filter match
      _ <- A.i(6).B.i(6).C.s("c").save.transact
      _ <- A.i(7).B.i(7).C.i(7).save.transact

      // Not filtering on C attribute makes ref to C unknown

      // Only entities having A.i and B.i values will have existing C.i value updated
      _ <- A.i_.B.i_.C.i(8).update.transact
      _ <- A.i.B.i.C.i.query.get.map(_ ==> List(
        (7, 7, 8) // C.i updated
      ))
    } yield ()
  }


  "2 refs unknown (B and C), 1 filter (A.i)" - refs {
    for {
      _ <- A.s("a").save.transact // no A.i filter match
      _ <- A.i(1).save.transact

      _ <- A.s("a").B.s("b").save.transact // no A.i filter match
      _ <- A.s("a").B.i(2).save.transact // no A.i filter match
      _ <- A.i(3).B.s("b").save.transact
      _ <- A.i(4).B.i(4).save.transact

      _ <- A.s("a").B.i(5).C.s("c").save.transact // no A.i filter match
      _ <- A.s("a").B.i(6).C.i(6).save.transact // no A.i filter match
      _ <- A.i(7).B.s("b").C.s("c").save.transact
      _ <- A.i(8).B.s("b").C.i(8).save.transact
      _ <- A.i(9).B.i(9).C.s("c").save.transact
      _ <- A.i(10).B.i(10).C.i(10).save.transact

      // Not filtering on C attribute makes ref to C unknown

      // Only entities having A.i value will have existing B.i and C.i values updated
      _ <- A.i_.B.i(11).C.i(11).update.transact
      _ <- A.i.B.i.C.i.query.get.map(_ ==> List(
        (10, 11, 11) // B.i and C.i updated
      ))
    } yield ()
  }


  "ref ref" - refs {
    for {
      id <- A.i(1).B.i(2).C.i(3).save.transact.map(_.id)
      _ <- A.i.B.i.C.i.query.get.map(_ ==> List((1, 2, 3)))

      // A
      _ <- A(id).i(10).update.transact
      _ <- A.i.B.i.C.i.query.get.map(_ ==> List((10, 2, 3)))

      // A + B
      _ <- A(id).i(11).B.i(20).update.transact
      _ <- A.i.B.i.C.i.query.get.map(_ ==> List((11, 20, 3)))

      // B
      _ <- A(id).B.i(21).update.transact
      _ <- A.i.B.i.C.i.query.get.map(_ ==> List((11, 21, 3)))

      // A + B + C
      _ <- A(id).i(12).B.i(22).C.i(30).update.transact
      _ <- A.i.B.i.C.i.query.get.map(_ ==> List((12, 22, 30)))

      // A + C
      _ <- A(id).i(13).B.C.i(31).update.transact
      _ <- A.i.B.i.C.i.query.get.map(_ ==> List((13, 22, 31)))

      // B + C
      _ <- A(id).B.i(23).C.i(32).update.transact
      _ <- A.i.B.i.C.i.query.get.map(_ ==> List((13, 23, 32)))

      // C
      _ <- A(id).B.C.i(33).update.transact
      _ <- A.i.B.i.C.i.query.get.map(_ ==> List((13, 23, 33)))
    } yield ()
  }


  "Delete individual ref value(s) with update" - refs {
    for {
      refId <- B.i(7).save.transact.map(_.id)
      id <- A.i.b.insert((1, refId)).transact.map(_.id)
      _ <- A.i.b.query.get.map(_ ==> List((1, refId)))

      // Apply empty value to delete ref id of entity (entity remains)
      _ <- A(id).b().update.transact
      _ <- A.i.b_?.query.get.map(_ ==> List((1, None)))
    } yield ()
  }
}
