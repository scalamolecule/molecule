package molecule.db.datalog.datomic.compliance.action.update.attrOp

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.action.update.attrOp.{AttrOp_Boolean, AttrOp_String}
import molecule.db.datalog.datomic.setup.Api_datomic_async


class AttrOp_BooleanTest extends Test {
  AttrOp_Boolean(this, Api_datomic_async)
}
class AttrOp_StringTest extends Test {
  AttrOp_String(this, Api_datomic_async)
}

