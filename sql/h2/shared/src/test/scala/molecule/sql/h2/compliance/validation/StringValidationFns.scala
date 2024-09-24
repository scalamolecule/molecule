package molecule.sql.h2.compliance.validation

import molecule.coreTests.spi.validation.StringValidationFns
import molecule.sql.h2.setup.Test_h2_async

object Test_StringValidationFns extends StringValidationFns with Test_h2_async
