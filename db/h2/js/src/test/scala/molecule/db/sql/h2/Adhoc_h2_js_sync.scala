package molecule.db.sql.h2

import molecule.base.error.ModelError
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.Entity
import molecule.db.sql.h2.setup.DbProviders_h2
import molecule.db.sql.h2.sync.*


class Adhoc_h2_js_sync extends MUnit with DbProviders_h2 with TestUtils {

  "types" - types { implicit conn =>
    try {
      Entity.int.query.get ==> List(1)
    } catch {
      case ModelError(msg) =>
        msg ==> "Molecule has no synchronous api on the JS platform since RPCs are asynchronous."
    }
  }
}
