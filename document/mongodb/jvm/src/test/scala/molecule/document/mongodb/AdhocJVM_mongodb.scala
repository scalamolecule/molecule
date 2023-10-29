package molecule.document.mongodb

import java.time.LocalTime
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types.Ns
import molecule.document.mongodb.async._
import molecule.document.mongodb.setup.TestSuite_mongodb
import utest._
import scala.collection.immutable.Seq
import scala.language.implicitConversions


object AdhocJVM_mongodb extends TestSuite_mongodb {


  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      val (a, b, c) = (1, 2, 3)
      val id0 = "a23456789012345678901234"
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

    //    "refs" - refs { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Refs._
    //      for {
    //        id <- A.i(1).B.i(2).C.i(3).save.transact.map(_.id)
    //        _ <- A.i.B.i.C.i.query.get.map(_ ==> List((1, 2, 3)))
    //
    //
    //      } yield ()
    //    }
    //
    //
    //    "unique" - unique { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Uniques._
    //      for {
    //        _ <- Uniques.i(1).save.transact
    //
    //      } yield ()
    //    }
    //
    //
    //    "validation" - validation { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Validation._
    //
    //      for {
    //        List(r1, r2) <- RefB.i.insert(2, 3).transact.map(_.ids)
    //
    //      } yield ()
    //    }
  }
}
