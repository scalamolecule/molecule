package molecule.sql.sqlite.compliance.validation

import molecule.coreTests.spi.validation.MandatoryRefs
import molecule.sql.sqlite.setup.TestAsync_sqlite

object Test_MandatoryRefs extends MandatoryRefs with TestAsync_sqlite
