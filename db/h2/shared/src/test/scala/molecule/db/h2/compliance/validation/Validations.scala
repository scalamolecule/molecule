package molecule.db.h2.compliance.validation

import molecule.core.setup.{MUnit, MUnit_arrays}
import molecule.db
import molecule.db.compliance.test.validation.*
import molecule.db.h2.setup.Api_h2_async

class AliasedAttrsTest extends MUnit {
  Aliased(this, Api_h2_async)
}
class AllowedValuesTest extends MUnit {
  AllowedValues(this, Api_h2_async)
}
class EnumsTest extends MUnit {
  Enums(this, Api_h2_async)
}
class KeywordSubstitutionTest extends MUnit {
  KeywordSubstitution(this, Api_h2_async)
}
class MandatoryAttrsTest extends MUnit {
  MandatoryAttrs(this, Api_h2_async)
}
class MandatoryRefsTest extends MUnit_arrays {
  MandatoryRefs(this, Api_h2_async)
}
class RequiredAttrsTest extends MUnit {
  RequiredAttrs(this, Api_h2_async)
}
class StringValidationFnsTest extends MUnit {
  StringValidationFns(this, Api_h2_async)
}
