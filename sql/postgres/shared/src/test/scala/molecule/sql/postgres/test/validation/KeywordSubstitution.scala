package molecule.sql.postgres.test.validation

import molecule.coreTests.test.validation.KeywordSubstitution
import molecule.sql.postgres.setup.TestAsync_postgres

object KeywordSubstitution extends KeywordSubstitution with TestAsync_postgres
