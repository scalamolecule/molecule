package molecule.db.datalog.datomic.compliance.validation.update

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.validation.update.*
import molecule.db.datalog
import molecule.db.datalog.datomic.setup.Api_datomic_async

class TypesOneTest extends Test {
  TypesOne(this, Api_datomic_async)
}
class TypesOneOptTest extends Test {
  TypesOneOpt(this, Api_datomic_async)
}
class TypesSeqTest extends Test {
  TypesSeq(this, Api_datomic_async)
}
class TypesSeqOptTest extends Test {
  TypesSeqOpt(this, Api_datomic_async)
}
class TypesSetTest extends Test {
  TypesSet(this, Api_datomic_async)
}
class TypesSetOptTest extends Test {
  TypesSetOpt(this, Api_datomic_async)
}
