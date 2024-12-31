package molecule.sql.postgres.compliance.validation

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.validation.StringValidationFns
import molecule.sql.postgres.setup.Api_postgres_async

class StringValidationFns extends MUnitSuite {
  StringValidationFns(this, Api_postgres_async)
}
