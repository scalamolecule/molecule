package molecule.sql.mysql.compliance.validation

import molecule.coreTests.spi.validation.MandatoryAttrs
import molecule.sql.mysql.setup.TestAsync_mysql

object Test_MandatoryAttrs extends MandatoryAttrs with TestAsync_mysql
