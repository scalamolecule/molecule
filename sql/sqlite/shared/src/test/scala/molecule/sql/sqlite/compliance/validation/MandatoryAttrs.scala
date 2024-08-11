package molecule.sql.sqlite.compliance.validation

import molecule.coreTests.spi.validation.MandatoryAttrs
import molecule.sql.sqlite.setup.TestAsync_sqlite

object Test_MandatoryAttrs extends MandatoryAttrs with TestAsync_sqlite
