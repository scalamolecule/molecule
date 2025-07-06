package molecule.db.sql.postgres.compliance.validation

import molecule.core.setup.{MUnit, MUnit_arrays}
import molecule.db.compliance.test.validation.*
import molecule.db.sql.postgres.setup.Api_postgres_async

class AliasedAttrsTest extends MUnit {
  Aliased(this, Api_postgres_async)
}
class AllowedValuesTest extends MUnit {
  AllowedValues(this, Api_postgres_async)
}
class KeywordSubstitutionTest extends MUnit {
  KeywordSubstitution(this, Api_postgres_async)
}
class MandatoryAttrsTest extends MUnit {
  MandatoryAttrs(this, Api_postgres_async)
}
class MandatoryRefsTest extends MUnit_arrays {
  MandatoryRefs(this, Api_postgres_async)
}
class RequiredAttrsTest extends MUnit {
  RequiredAttrs(this, Api_postgres_async)
}
class StringValidationFnsTest extends MUnit {
  StringValidationFns(this, Api_postgres_async)
}
