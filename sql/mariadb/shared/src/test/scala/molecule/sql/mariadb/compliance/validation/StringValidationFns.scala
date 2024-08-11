package molecule.sql.mariadb.compliance.validation

import molecule.coreTests.spi.validation.StringValidationFns
import molecule.sql.mariadb.setup.TestAsync_mariadb

object Test_StringValidationFns extends StringValidationFns with TestAsync_mariadb
