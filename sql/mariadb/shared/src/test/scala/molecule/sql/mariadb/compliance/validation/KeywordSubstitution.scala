package molecule.sql.mariadb.compliance.validation

import molecule.coreTests.spi.validation.KeywordSubstitution
import molecule.sql.mariadb.setup.Test_mariadb_async

object Test_KeywordSubstitution extends KeywordSubstitution with Test_mariadb_async
