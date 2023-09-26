package molecule.sql.mysql.test.validation

import molecule.coreTests.test.validation.StringValidationFns
import molecule.sql.mysql.setup.TestAsync_mysql

object StringValidationFns extends StringValidationFns with TestAsync_mysql
