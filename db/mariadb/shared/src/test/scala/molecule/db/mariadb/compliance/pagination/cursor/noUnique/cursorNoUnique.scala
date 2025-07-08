package molecule.db.mariadb.compliance.pagination.cursor.noUnique

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.pagination.cursor.noUnique.*
import molecule.db.mariadb.setup.Api_mariadb_async

class AttrOrderMandatoryTest extends MUnit {
  AttrOrderMandatory(this, Api_mariadb_async)
}
class AttrOrderOptionalTest extends MUnit {
  AttrOrderOptional(this, Api_mariadb_async)
}
class DirectionsMandatoryTest extends MUnit {
  DirectionsMandatory(this, Api_mariadb_async)
}
class DirectionsOptionalTest extends MUnit {
  DirectionsOptional(this, Api_mariadb_async)
}
class MutationAddTest extends MUnit {
  MutationAdd(this, Api_mariadb_async)
}
class MutationDeleteTest extends MUnit {
  MutationDelete(this, Api_mariadb_async)
}
class NestedTest extends MUnit {
  Nested(this, Api_mariadb_async)
}
class OptNestedTest extends MUnit {
  OptNested(this, Api_mariadb_async)
}
class TypesOptionalTest extends MUnit {
  TypesOptional(this, Api_mariadb_async)
}
