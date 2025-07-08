package molecule.db.sql.mariadb.compliance.validation.update

import molecule.core.setup.MUnit
import molecule.db.compliance.test.validation.update.*
import molecule.db.sql.mariadb.setup.Api_mariadb_async

class TypesOneTest extends MUnit {
  TypesOne(this, Api_mariadb_async)
}
class TypesOneOptTest extends MUnit {
  TypesOneOpt(this, Api_mariadb_async)
}
class TypesSeqTest extends MUnit {
  TypesSeq(this, Api_mariadb_async)
}
class TypesSeqOptTest extends MUnit {
  TypesSeqOpt(this, Api_mariadb_async)
}
class TypesSetTest extends MUnit {
  TypesSet(this, Api_mariadb_async)
}
class TypesSetOptTest extends MUnit {
  TypesSetOpt(this, Api_mariadb_async)
}
