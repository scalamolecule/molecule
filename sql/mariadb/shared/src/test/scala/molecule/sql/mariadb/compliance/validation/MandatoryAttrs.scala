package molecule.sql.mariadb.compliance.validation

import molecule.coreTests.spi.validation.MandatoryAttrs
import molecule.sql.mariadb.setup.TestAsync_mariadb

object Test_MandatoryAttrs extends MandatoryAttrs with TestAsync_mariadb
