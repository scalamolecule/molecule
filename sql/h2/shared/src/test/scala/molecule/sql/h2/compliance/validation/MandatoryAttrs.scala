package molecule.sql.h2.compliance.validation

import molecule.coreTests.spi.validation.MandatoryAttrs
import molecule.sql.h2.setup.TestAsync_h2

object Test_MandatoryAttrs extends MandatoryAttrs with TestAsync_h2
