package molecule.db.mysql.compliance.validation

import molecule.core.setup.{MUnit, MUnit_arrays}
import molecule.db
import molecule.db.compliance.test.validation.*
import molecule.db.mysql.setup.Api_mysql_async

class AliasedAttrsTest extends MUnit {
  Aliased(this, Api_mysql_async)
}
class AllowedValuesTest extends MUnit {
  AllowedValues(this, Api_mysql_async)
}
class EnumsTest extends MUnit {
  Enums(this, Api_mysql_async)
}
class KeywordSubstitutionTest extends MUnit {
  KeywordSubstitution(this, Api_mysql_async)
}
class MandatoryAttrsTest extends MUnit {
  MandatoryAttrs(this, Api_mysql_async)
}
class MandatoryRefsTest extends MUnit_arrays {
  MandatoryRefs(this, Api_mysql_async)
}
class RequiredAttrsTest extends MUnit {
  RequiredAttrs(this, Api_mysql_async)
}
class StringValidationFnsTest extends MUnit {
  StringValidationFns(this, Api_mysql_async)
}
