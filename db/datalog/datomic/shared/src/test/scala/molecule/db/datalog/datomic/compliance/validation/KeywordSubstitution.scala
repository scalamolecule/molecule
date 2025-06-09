package molecule.db.datalog.datomic.compliance.validation

import molecule.core.setup.MUnit
import molecule.db.compliance.test.validation.KeywordSubstitution
import molecule.db.datalog.datomic.setup.Api_datomic_async

class KeywordSubstitutionTest extends MUnit {
  KeywordSubstitution(this, Api_datomic_async)
}
