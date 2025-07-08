package molecule.db.sql.sqlite.compliance.validation.update

import molecule.core.setup.MUnit
import molecule.db.compliance.test.validation.update.*
import molecule.db.sql.sqlite.setup.Api_sqlite_async

class TypesOneTest extends MUnit {
  TypesOne(this, Api_sqlite_async)
}
class TypesOneOptTest extends MUnit {
  TypesOneOpt(this, Api_sqlite_async)
}
class TypesSeqTest extends MUnit {
  TypesSeq(this, Api_sqlite_async)
}
class TypesSeqOptTest extends MUnit {
  TypesSeqOpt(this, Api_sqlite_async)
}
class TypesSetTest extends MUnit {
  TypesSet(this, Api_sqlite_async)
}
class TypesSetOptTest extends MUnit {
  TypesSetOpt(this, Api_sqlite_async)
}
