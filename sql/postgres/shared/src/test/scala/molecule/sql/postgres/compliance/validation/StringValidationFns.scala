package molecule.sql.postgres.compliance.validation

import molecule.coreTests.compliance.validation.StringValidationFns
import molecule.sql.postgres.setup.TestAsync_postgres

object StringValidationFns extends StringValidationFns with TestAsync_postgres
