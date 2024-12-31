package molecule.sql.postgres.compliance.validation

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.validation.RequiredAttrs
import molecule.sql.postgres.setup.Api_postgres_async

class RequiredAttrs extends MUnitSuite {
  RequiredAttrs(this, Api_postgres_async)
}
