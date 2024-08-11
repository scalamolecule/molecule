package molecule.sql.h2.compliance.validation

import molecule.coreTests.spi.validation.KeywordSubstitution
import molecule.sql.h2.setup.TestAsync_h2

object Test_KeywordSubstitution extends KeywordSubstitution with TestAsync_h2
