package molecule.db.datalog.datomic

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.datalog.datomic.setup.DbProviders_datomic
import molecule.db.datalog.datomic.sync.*


class Adhoc_datomic_jvm_sync extends MUnit with DbProviders_datomic with TestUtils {

  "commit" - refs { implicit conn =>
    import molecule.db.compliance.domains.dsl.Refs.*

    A.i.insert(1, 2, 3).transact
    A.i.query.stream.toList.sorted ==> List(1, 2, 3)
  }
}
