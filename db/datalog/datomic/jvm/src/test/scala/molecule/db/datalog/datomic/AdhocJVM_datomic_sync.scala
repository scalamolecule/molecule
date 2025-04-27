package molecule.db.datalog.datomic

import cats.effect.unsafe.implicits.global as ioRuntime
import molecule.coreTests.domains.dsl.Types.*
import molecule.coreTests.setup.{Test, TestUtils}
import molecule.db.datalog
import molecule.db.datalog.datomic.setup.DbProviders_datomic
import molecule.db.datalog.datomic.sync.*


class AdhocJVM_datomic_sync extends Test with DbProviders_datomic with TestUtils {

  "commit" - refs { implicit conn =>
    import molecule.coreTests.domains.dsl.Refs.*

    A.i.insert(1, 2, 3).transact
    A.i.query.stream.toList.sorted ==> List(1, 2, 3)
  }
}
