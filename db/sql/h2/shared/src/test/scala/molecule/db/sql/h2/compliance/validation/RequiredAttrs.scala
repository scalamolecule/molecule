package molecule.db.sql.h2.compliance.validation

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.validation.*
import molecule.db.sql
import molecule.db.sql.h2.setup.Api_h2_async

class RequiredAttrsTest extends Test {
  RequiredAttrs(this, Api_h2_async)
}
