package molecule.db.sql.postgres.compliance.validation

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.validation.KeywordSubstitution
import molecule.db.sql
import molecule.db.sql.postgres.setup.Api_postgres_async

class KeywordSubstitutionTest extends Test {
  KeywordSubstitution(this, Api_postgres_async)
}
