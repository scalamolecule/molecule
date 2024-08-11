package molecule.sql.mysql.compliance.validation

import molecule.coreTests.spi.validation.RequiredAttrs
import molecule.sql.mysql.setup.TestAsync_mysql

object Test_RequiredAttrs extends RequiredAttrs with TestAsync_mysql
