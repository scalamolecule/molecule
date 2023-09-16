package molecule.sql.postgres.test.validation

import molecule.coreTests.test.validation.StringValidationFns
import molecule.sql.postgres.setup.TestAsync_postgres

object StringValidationFns extends StringValidationFns with TestAsync_postgres
