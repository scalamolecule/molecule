package molecule.db.postgresql.compliance.validation.update

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.validation.update.*
import molecule.db.postgresql.setup.Api_postgresql_async

class TypesOneTest extends MUnit {
  TypesOne(this, Api_postgresql_async)
}
class TypesOneOptTest extends MUnit {
  TypesOneOpt(this, Api_postgresql_async)
}
class TypesSeqTest extends MUnit {
  TypesSeq(this, Api_postgresql_async)
}
class TypesSeqOptTest extends MUnit {
  TypesSeqOpt(this, Api_postgresql_async)
}
class TypesSetTest extends MUnit {
  TypesSet(this, Api_postgresql_async)
}
class TypesSetOptTest extends MUnit {
  TypesSetOpt(this, Api_postgresql_async)
}
