package molecule.db.sql.h2.compliance.validation.update

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.validation.update.*
import molecule.db.sql.h2.setup.Api_h2_async

class TypesOneTest extends Test {
  TypesOne(this, Api_h2_async)
}
class TypesOneOptTest extends Test {
  TypesOneOpt(this, Api_h2_async)
}
class TypesSeqTest extends Test {
  TypesSeq(this, Api_h2_async)
}
class TypesSeqOptTest extends Test {
  TypesSeqOpt(this, Api_h2_async)
}
class TypesSetTest extends Test {
  TypesSet(this, Api_h2_async)
}
class TypesSetOptTest extends Test {
  TypesSetOpt(this, Api_h2_async)
}
