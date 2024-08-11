package molecule.sql.h2.compliance.validation

import molecule.coreTests.spi.validation.StringValidationFns
import molecule.sql.h2.setup.TestAsync_h2

object Test_StringValidationFns extends StringValidationFns with TestAsync_h2
