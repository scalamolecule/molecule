package molecule.sql.mysql.compliance.validation

import molecule.coreTests.spi.validation.MandatoryRefs
import molecule.sql.mysql.setup.TestAsync_mysql

object Test_MandatoryRefs extends MandatoryRefs with TestAsync_mysql
