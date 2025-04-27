package molecule.db.datalog.datomic.compliance.action.update.attrOp

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.action.update.attrOp.*
import molecule.db.datalog
import molecule.db.datalog.datomic.setup.Api_datomic_async


class AttrOp_BooleanTest extends Test {
  AttrOp_Boolean(this, Api_datomic_async)
}
class AttrOp_StringTest extends Test {
  AttrOp_String(this, Api_datomic_async)
}

