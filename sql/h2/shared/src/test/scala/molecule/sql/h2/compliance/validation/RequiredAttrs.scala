package molecule.sql.h2.compliance.validation

import molecule.coreTests.spi.validation.RequiredAttrs
import molecule.sql.h2.setup.TestAsync_h2

object Test_RequiredAttrs extends RequiredAttrs with TestAsync_h2
