package molecule.db.datalog.datomic.compliance.validation

import molecule.core.setup.{MUnit, MUnit_arrays}
import molecule.db.compliance.test.validation.*
import molecule.db.datalog.datomic.setup.Api_datomic_async

class AliasedAttrsTest extends MUnit {
  Aliased(this, Api_datomic_async)
}
class AllowedValuesTest extends MUnit {
  AllowedValues(this, Api_datomic_async)
}
class KeywordSubstitutionTest extends MUnit {
  KeywordSubstitution(this, Api_datomic_async)
}
class MandatoryAttrsTest extends MUnit {
  MandatoryAttrs(this, Api_datomic_async)
}
class MandatoryRefsTest extends MUnit_arrays {
  MandatoryRefs(this, Api_datomic_async)
}
class RequiredAttrsTest extends MUnit {
  RequiredAttrs(this, Api_datomic_async)
}
class StringValidationFnsTest extends MUnit {
  StringValidationFns(this, Api_datomic_async)
}
