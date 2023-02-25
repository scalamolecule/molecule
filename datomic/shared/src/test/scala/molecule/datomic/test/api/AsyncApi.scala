package molecule.datomic.test.api

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.dataModels.core.dsl.Unique.Unique
import molecule.datomic.async._
import molecule.datomic.setup.DatomicTestSuite
import utest._
import scala.annotation.nowarn


object AsyncApi extends DatomicTestSuite {

  @nowarn lazy val tests = Tests {

    "Molecule asynchronous api" - {

      "Crud actions" - types { implicit conn =>
        for {
          List(a, b) <- Ns.int.insert(1, 2).transact.map(_.eids)
          _ <- Ns.int(3).save.transact
          _ <- Ns.int.query.get.map(_ ==> List(1, 2, 3))
          _ <- Ns(a).int(10).update.transact
          _ <- Ns(b).delete.transact
          _ <- Ns.int.query.get.map(_ ==> List(3, 10))
        } yield ()
      }

      "Offset query" - types { implicit conn =>
        for {
          _ <- Ns.int.insert(1, 2, 3).transact
          _ <- Ns.int.query.get.map(_ ==> List(1, 2, 3))
          _ <- Ns.int.query.limit(2).get.map(_ ==> List(1, 2))
          _ <- Ns.int.query.offset(1).get.map(_ ==> (List(2, 3), 3, true))
          _ <- Ns.int.query.offset(1).limit(1).get.map(_ ==> (List(2), 3, true))
        } yield ()
      }

      "Cursor query" - unique { implicit conn =>
        val query = Unique.int.a1.query
        for {
          _ <- Unique.int.insert(1, 2, 3, 4, 5).transact
          c1 <- query.from("").limit(2).get.map { case (List(1, 2), c, true) => c }
          c2 <- query.from(c1).limit(2).get.map { case (List(3, 4), c, true) => c }
          c3 <- query.from(c2).limit(2).get.map { case (List(5), c, false) => c }
          c4 <- query.from(c3).limit(-2).get.map { case (List(3, 4), c, true) => c }
          _ <- query.from(c4).limit(-2).get.map { case (List(1, 2), _, false) => () }
        } yield ()
      }
    }
  }
}
