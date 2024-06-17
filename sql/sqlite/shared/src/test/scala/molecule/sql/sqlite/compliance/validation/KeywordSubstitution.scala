package molecule.sql.sqlite.compliance.validation

import molecule.coreTests.spi.validation.KeywordSubstitution
import molecule.sql.sqlite.setup.TestAsync_sqlite

object KeywordSubstitution extends KeywordSubstitution with TestAsync_sqlite
