package molecule.sql.mysql.compliance.validation

import molecule.coreTests.spi.validation.StringValidationFns
import molecule.sql.mysql.setup.Test_mysql_async

object Test_StringValidationFns extends StringValidationFns with Test_mysql_async
