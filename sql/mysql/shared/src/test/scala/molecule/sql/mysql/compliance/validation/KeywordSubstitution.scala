package molecule.sql.mysql.compliance.validation

import molecule.coreTests.spi.validation.KeywordSubstitution
import molecule.sql.mysql.setup.TestAsync_mysql

object Test_KeywordSubstitution extends KeywordSubstitution with TestAsync_mysql
