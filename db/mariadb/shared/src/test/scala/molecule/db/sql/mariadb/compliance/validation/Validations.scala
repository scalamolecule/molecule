package molecule.db.sql.mariadb.compliance.validation

import molecule.core.setup.{MUnit, MUnit_arrays}
import molecule.db.compliance.test.validation.*
import molecule.db.sql.mariadb.setup.Api_mariadb_async

class AliasedAttrsTest extends MUnit {
  Aliased(this, Api_mariadb_async)
}
class AllowedValuesTest extends MUnit {
  AllowedValues(this, Api_mariadb_async)
}
class EnumsTest extends MUnit {
  Enums(this, Api_mariadb_async)
}
class KeywordSubstitutionTest extends MUnit {
  KeywordSubstitution(this, Api_mariadb_async)
}
class MandatoryAttrsTest extends MUnit {
  MandatoryAttrs(this, Api_mariadb_async)
}
class MandatoryRefsTest extends MUnit_arrays {
  MandatoryRefs(this, Api_mariadb_async)
}
class RequiredAttrsTest extends MUnit {
  RequiredAttrs(this, Api_mariadb_async)
}
class StringValidationFnsTest extends MUnit {
  StringValidationFns(this, Api_mariadb_async)
}
