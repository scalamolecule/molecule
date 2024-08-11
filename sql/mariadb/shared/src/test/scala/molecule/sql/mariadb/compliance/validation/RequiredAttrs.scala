package molecule.sql.mariadb.compliance.validation

import molecule.coreTests.spi.validation.RequiredAttrs
import molecule.sql.mariadb.setup.TestAsync_mariadb

object Test_RequiredAttrs extends RequiredAttrs with TestAsync_mariadb
