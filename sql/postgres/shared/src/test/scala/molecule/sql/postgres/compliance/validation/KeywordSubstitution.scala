package molecule.sql.postgres.compliance.validation

import molecule.coreTests.spi.validation.KeywordSubstitution
import molecule.sql.postgres.setup.Test_postgres_async

object Test_KeywordSubstitution extends KeywordSubstitution with Test_postgres_async
