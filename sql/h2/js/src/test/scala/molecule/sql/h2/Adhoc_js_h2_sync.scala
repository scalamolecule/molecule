package molecule.sql.h2

import boopickle.Default._
import molecule.coreTests.domains.dsl.Types.Entity
import molecule.coreTests.setup.{Test, TestUtils}
import molecule.sql.h2.setup.DbProviders_h2
import molecule.sql.h2.sync._
import scala.language.implicitConversions
import molecule.base.error.ModelError

class Adhoc_js_h2_sync extends Test with DbProviders_h2 with TestUtils {

  "types" - types { implicit conn =>
    try {
      Entity.int.query.get ==> List(1)
    } catch {
      case ModelError(msg) =>
        msg ==> "Molecule has no synchronous api on the JS platform since RPCs are asynchronous."
    }
  }
}
