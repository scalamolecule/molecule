package molecule.sql.mariadb.test.validation

import molecule.coreTests.test.validation.StringValidationFns
import molecule.sql.mariadb.setup.TestAsync_mariadb

object StringValidationFns extends StringValidationFns with TestAsync_mariadb
