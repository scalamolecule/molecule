package molecule.sql.mariadb.compliance.validation

import molecule.coreTests.compliance.validation.StringValidationFns
import molecule.sql.mariadb.setup.TestAsync_mariadb

object StringValidationFns extends StringValidationFns with TestAsync_mariadb
