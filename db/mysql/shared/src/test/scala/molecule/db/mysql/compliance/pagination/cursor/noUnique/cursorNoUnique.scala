package molecule.db.mysql.compliance.pagination.cursor.noUnique

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.pagination.cursor.noUnique.*
import molecule.db.mysql.setup.Api_mysql_async

class AttrOrderMandatoryTest extends MUnit {
  AttrOrderMandatory(this, Api_mysql_async)
}
class AttrOrderOptionalTest extends MUnit {
  AttrOrderOptional(this, Api_mysql_async)
}
class DirectionsMandatoryTest extends MUnit {
  DirectionsMandatory(this, Api_mysql_async)
}
class DirectionsOptionalTest extends MUnit {
  DirectionsOptional(this, Api_mysql_async)
}
class MutationAddTest extends MUnit {
  MutationAdd(this, Api_mysql_async)
}
class MutationDeleteTest extends MUnit {
  MutationDelete(this, Api_mysql_async)
}
class NestedTest extends MUnit {
  Nested(this, Api_mysql_async)
}
class OptNestedTest extends MUnit {
  OptNested(this, Api_mysql_async)
}
class TypesOptionalTest extends MUnit {
  TypesOptional(this, Api_mysql_async)
}
