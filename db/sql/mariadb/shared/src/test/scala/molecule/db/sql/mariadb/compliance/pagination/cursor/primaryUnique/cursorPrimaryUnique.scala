package molecule.db.sql.mariadb.compliance.pagination.cursor.primaryUnique

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.pagination.cursor.primaryUnique.{Directions, MutationAdd, MutationDelete, Nested, TypesFilterAttr}
import molecule.db.sql
import molecule.db.sql.mariadb.setup.Api_mariadb_async

class DirectionsTest extends Test {
  Directions(this, Api_mariadb_async)
}
class MutationAddTest extends Test {
  MutationAdd(this, Api_mariadb_async)
}
class MutationDeleteTest extends Test {
  MutationDelete(this, Api_mariadb_async)
}
class NestedTest extends Test {
  Nested(this, Api_mariadb_async)
}
class TypesFilterAttrTest extends Test {
  TypesFilterAttr(this, Api_mariadb_async)
}
