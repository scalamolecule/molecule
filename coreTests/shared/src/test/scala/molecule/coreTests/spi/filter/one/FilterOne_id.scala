package molecule.coreTests.spi.filter.one

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterOne_id extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - types { implicit conn =>
      val id0 = "42"
      for {
        List(id1, id2, id3) <- Ns.i.insert(1, 2, 3).transact.map(_.ids)
        a = (1, id1)
        b = (2, id2)
        c = (3, id3)

        // Find all ids
        _ <- Ns.i.a1.id.query.get.map(_ ==> List(a, b, c))

        // Find value(s) matching
        _ <- Ns.i.a1.id(id0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.id(id1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.id(Seq(id0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.id(Seq(id1)).query.get.map(_ ==> List(a))
        // OR semantics for multiple args
        _ <- Ns.i.a1.id(id1, id2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.id(id1, id0).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.id(Seq(id1, id2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.id(Seq(id1, id0)).query.get.map(_ ==> List(a))
        // Empty Seq of args matches no ids
        _ <- Ns.i.a1.id(Seq.empty[String]).query.get.map(_ ==> List())

        // Find ids not matching
        _ <- Ns.i.a1.id.not(id0).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.id.not(id1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.id.not(id2).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.id.not(id3).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.id.not(Seq(id0)).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.id.not(Seq(id1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.id.not(Seq(id2)).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.id.not(Seq(id3)).query.get.map(_ ==> List(a, b))
        // OR semantics for multiple args
        _ <- Ns.i.a1.id.not(id0, id1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.id.not(id1, id2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.id.not(id2, id3).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.id.not(Seq(id0, id1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.id.not(Seq(id1, id2)).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.id.not(Seq(id2, id3)).query.get.map(_ ==> List(a))
        // Empty Seq of negation args matches all ids
        _ <- Ns.i.a1.id.not(Seq.empty[String]).query.get.map(_ ==> List(a, b, c))

        // Find ids in range
        _ <- Ns.i.a1.id.<(id2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.id.>(id2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.id.<=(id2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.id.>=(id2).query.get.map(_ ==> List(b, c))
      } yield ()
    }


    "Tacit" - types { implicit conn =>
      val (a, b, c) = (1, 2, 3)
      val id0       = "42"
      for {
        List(id1, id2, id3) <- Ns.i.insert(1, 2, 3).transact.map(_.ids)

        // Since all entities have an id, asking for a tacit id makes no difference
        _ <- Ns.i.a1.id_.query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.query.get.map(_ ==> List(a, b, c))

        // Match ids without returning them
        _ <- Ns.i.a1.id_(id0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.id_(id1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.id_(Seq(id0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.id_(Seq(id1)).query.get.map(_ ==> List(a))
        // OR semantics for multiple args
        _ <- Ns.i.a1.id_(id1, id2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.id_(id1, id0).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.id_(Seq(id1, id2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.id_(Seq(id1, id0)).query.get.map(_ ==> List(a))
        // Empty Seq of args matches no ids
        _ <- Ns.i.a1.id_(Seq.empty[String]).query.get.map(_ ==> List())
        _ <- Ns.i.a1.id_().query.get.map(_ ==> List())

        // Match non-matching ids without returning them
        _ <- Ns.i.a1.id_.not(id0).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.id_.not(id1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.id_.not(id2).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.id_.not(id3).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.id_.not(Seq(id0)).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.id_.not(Seq(id1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.id_.not(Seq(id2)).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.id_.not(Seq(id3)).query.get.map(_ ==> List(a, b))
        // OR semantics for multiple args
        _ <- Ns.i.a1.id_.not(id0, id1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.id_.not(id1, id2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.id_.not(id2, id3).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.id_.not(Seq(id0, id1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.id_.not(Seq(id1, id2)).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.id_.not(Seq(id2, id3)).query.get.map(_ ==> List(a))
        // Empty Seq of negation args matches all ids (non-null)
        _ <- Ns.i.a1.id_.not(Seq.empty[String]).query.get.map(_ ==> List(a, b, c))

        // Match value ranges without returning them
        _ <- Ns.i.a1.id_.<(id2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.id_.>(id2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.id_.<=(id2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.id_.>=(id2).query.get.map(_ ==> List(b, c))
      } yield ()
    }
  }
}
