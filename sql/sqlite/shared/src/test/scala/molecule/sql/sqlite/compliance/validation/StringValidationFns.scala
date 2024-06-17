package molecule.sql.sqlite.compliance.validation

import molecule.coreTests.spi.validation.StringValidationFns
import molecule.sql.sqlite.setup.TestAsync_sqlite

object StringValidationFns extends StringValidationFns with TestAsync_sqlite
