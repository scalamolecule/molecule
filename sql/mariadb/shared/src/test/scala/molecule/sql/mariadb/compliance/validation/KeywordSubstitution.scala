package molecule.sql.mariadb.compliance.validation

import molecule.coreTests.spi.validation.KeywordSubstitution
import molecule.sql.mariadb.setup.TestAsync_mariadb

object Test_KeywordSubstitution extends KeywordSubstitution with TestAsync_mariadb
