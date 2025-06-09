package molecule.db.sql.h2.compliance.validation.update

import molecule.core.setup.MUnit
import molecule.db.compliance.test.validation.update.*
import molecule.db.sql.h2.setup.Api_h2_async

class TypesOneTest extends MUnit {
  TypesOne(this, Api_h2_async)
}
class TypesOneOptTest extends MUnit {
  TypesOneOpt(this, Api_h2_async)
}
class TypesSeqTest extends MUnit {
  TypesSeq(this, Api_h2_async)
}
class TypesSeqOptTest extends MUnit {
  TypesSeqOpt(this, Api_h2_async)
}
class TypesSetTest extends MUnit {
  TypesSet(this, Api_h2_async)
}
class TypesSetOptTest extends MUnit {
  TypesSetOpt(this, Api_h2_async)
}
