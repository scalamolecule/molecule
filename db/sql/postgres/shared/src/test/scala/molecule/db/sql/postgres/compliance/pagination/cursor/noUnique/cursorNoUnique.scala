package molecule.db.sql.postgres.compliance.pagination.cursor.noUnique

import molecule.core.setup.MUnit
import molecule.db.compliance.test.pagination.cursor.noUnique.*
import molecule.db.sql.postgres.setup.Api_postgres_async

class AttrOrderMandatoryTest extends MUnit {
  AttrOrderMandatory(this, Api_postgres_async)
}
class AttrOrderOptionalTest extends MUnit {
  AttrOrderOptional(this, Api_postgres_async)
}
class DirectionsMandatoryTest extends MUnit {
  DirectionsMandatory(this, Api_postgres_async)
}
class DirectionsOptionalTest extends MUnit {
  DirectionsOptional(this, Api_postgres_async)
}
class MutationAddTest extends MUnit {
  MutationAdd(this, Api_postgres_async)
}
class MutationDeleteTest extends MUnit {
  MutationDelete(this, Api_postgres_async)
}
class NestedTest extends MUnit {
  Nested(this, Api_postgres_async)
}
class OptNestedTest extends MUnit {
  OptNested(this, Api_postgres_async)
}
class TypesOptionalTest extends MUnit {
  TypesOptional(this, Api_postgres_async)
}
