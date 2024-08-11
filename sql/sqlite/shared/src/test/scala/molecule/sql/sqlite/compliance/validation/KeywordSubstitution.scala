package molecule.sql.sqlite.compliance.validation

import molecule.coreTests.spi.validation.KeywordSubstitution
import molecule.sql.sqlite.setup.TestAsync_sqlite

object Test_KeywordSubstitution extends KeywordSubstitution with TestAsync_sqlite
