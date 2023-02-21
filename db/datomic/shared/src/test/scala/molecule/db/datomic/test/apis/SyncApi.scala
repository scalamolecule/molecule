package molecule.db.datomic.test.apis

import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.dataModels.core.dsl.Unique.Unique
import molecule.db.datomic.setup.DatomicTestSuite
import molecule.db.datomic.sync._
import utest._
import scala.annotation.nowarn


object SyncApi extends DatomicTestSuite {

  // (Allow pattern matching cursor results without warnings)
  @nowarn lazy val tests = Tests {

    "Molecule synchronous api" - {

      "Sync actions" - typesImpl { implicit conn =>
        val List(a, b) = Ns.int.insert(1, 2).transact.eids
        Ns.int(3).save.transact
        Ns.int.query.get ==> List(1, 2, 3)
        Ns(a).int(10).update.transact
        Ns(b).delete.transact
        Ns.int.query.get ==> List(3, 10)
      }

      "Offset query" - typesImpl { implicit conn =>
        Ns.int.insert(1, 2, 3).transact
        Ns.int.query.get ==> List(1, 2, 3)
        Ns.int.query.limit(2).get ==> List(1, 2)
        Ns.int.query.offset(1).get ==> (List(2, 3), 3, true)
        Ns.int.query.offset(1).limit(1).get ==> (List(2), 3, true)
      }

      "Cursor query" - uniqueImpl { implicit conn =>
        val query = Unique.int.a1.query
        Unique.int.insert(1, 2, 3, 4, 5).transact
        val c1 = query.from("").limit(2).get match {
          case (List(1, 2), c, true) => c
        }
        val c2 = query.from(c1).limit(2).get match {
          case (List(3, 4), c, true) => c
        }
        val c3 = query.from(c2).limit(2).get match {
          case (List(5), c, false) => c
        }
        val c4 = query.from(c3).limit(-2).get match {
          case (List(3, 4), c, true) => c
        }
        query.from(c4).limit(-2).get match {
          case (List(1, 2), _, false) => ()
        }
      }
    }
  }
}
