package molecule.sql.mysql.compliance.validation

import molecule.coreTests.spi.validation.StringValidationFns
import molecule.sql.mysql.setup.TestAsync_mysql

object Test_StringValidationFns extends StringValidationFns with TestAsync_mysql
