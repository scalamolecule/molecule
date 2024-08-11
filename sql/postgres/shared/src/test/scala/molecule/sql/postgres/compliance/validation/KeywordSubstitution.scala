package molecule.sql.postgres.compliance.validation

import molecule.coreTests.spi.validation.KeywordSubstitution
import molecule.sql.postgres.setup.TestAsync_postgres

object Test_KeywordSubstitution extends KeywordSubstitution with TestAsync_postgres
