package molecule.db.compliance.test.filter.one

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*

case class FilterOne_id(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "Mandatory" - types { implicit conn =>
    val id0 = 42L
    for {
      List(id1, id2, id3) <- Entity.i.insert(1, 2, 3).transact.map(_.ids)
      a = (1, id1)
      b = (2, id2)
      c = (3, id3)

      // Find all ids
      _ <- Entity.i.a1.id.query.get.map(_ ==> List(a, b, c))

      // Find value(s) matching
      _ <- Entity.i.a1.id(id0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.id(id1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.id(Seq(id0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.id(Seq(id1)).query.get.map(_ ==> List(a))
      // OR semantics for multiple args
      _ <- Entity.i.a1.id(id1, id2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.id(id1, id0).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.id(Seq(id1, id2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.id(Seq(id1, id0)).query.get.map(_ ==> List(a))
      // Empty Seq of args matches no ids
      _ <- Entity.i.a1.id(Seq.empty[Long]).query.get.map(_ ==> List())

      // Find ids not matching
      _ <- Entity.i.a1.id.not(id0).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.id.not(id1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.id.not(id2).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.id.not(id3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.id.not(Seq(id0)).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.id.not(Seq(id1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.id.not(Seq(id2)).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.id.not(Seq(id3)).query.get.map(_ ==> List(a, b))
      // OR semantics for multiple args
      _ <- Entity.i.a1.id.not(id0, id1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.id.not(id1, id2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.id.not(id2, id3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.id.not(Seq(id0, id1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.id.not(Seq(id1, id2)).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.id.not(Seq(id2, id3)).query.get.map(_ ==> List(a))
      // Empty Seq of negation args matches all ids
      _ <- Entity.i.a1.id.not(Seq.empty[Long]).query.get.map(_ ==> List(a, b, c))

      // Find ids in range
      _ <- Entity.i.a1.id.<(id2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.id.>(id2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.id.<=(id2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.id.>=(id2).query.get.map(_ ==> List(b, c))
    } yield ()
  }


  "Tacit" - types { implicit conn =>
    val (a, b, c) = (1, 2, 3)
    val id0       = 42L
    for {
      List(id1, id2, id3) <- Entity.i.insert(1, 2, 3).transact.map(_.ids)

      // Since all entities have an id, asking for a tacit id makes no difference
      _ <- Entity.i.a1.id_.query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.query.get.map(_ ==> List(a, b, c))

      // Match ids without returning them
      _ <- Entity.i.a1.id_(id0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.id_(id1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.id_(Seq(id0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.id_(Seq(id1)).query.get.map(_ ==> List(a))
      // OR semantics for multiple args
      _ <- Entity.i.a1.id_(id1, id2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.id_(id1, id0).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.id_(Seq(id1, id2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.id_(Seq(id1, id0)).query.get.map(_ ==> List(a))
      // Empty Seq of args matches no ids
      _ <- Entity.i.a1.id_(Seq.empty[Long]).query.get.map(_ ==> List())
      _ <- Entity.i.a1.id_().query.get.map(_ ==> List())

      // Match non-matching ids without returning them
      _ <- Entity.i.a1.id_.not(id0).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.id_.not(id1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.id_.not(id2).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.id_.not(id3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.id_.not(Seq(id0)).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.id_.not(Seq(id1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.id_.not(Seq(id2)).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.id_.not(Seq(id3)).query.get.map(_ ==> List(a, b))
      // OR semantics for multiple args
      _ <- Entity.i.a1.id_.not(id0, id1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.id_.not(id1, id2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.id_.not(id2, id3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.id_.not(Seq(id0, id1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.id_.not(Seq(id1, id2)).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.id_.not(Seq(id2, id3)).query.get.map(_ ==> List(a))
      // Empty Seq of negation args matches all ids (non-null)
      _ <- Entity.i.a1.id_.not(Seq.empty[Long]).query.get.map(_ ==> List(a, b, c))

      // Match value ranges without returning them
      _ <- Entity.i.a1.id_.<(id2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.id_.>(id2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.id_.<=(id2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.id_.>=(id2).query.get.map(_ ==> List(b, c))
    } yield ()
  }
}
