package molecule.db.sql.postgres.compliance.validation

import molecule.core.setup.MUnit
import molecule.db.compliance.test.validation.KeywordSubstitution
import molecule.db.sql.postgres.setup.Api_postgres_async

class KeywordSubstitutionTest extends MUnit {
  KeywordSubstitution(this, Api_postgres_async)
}
