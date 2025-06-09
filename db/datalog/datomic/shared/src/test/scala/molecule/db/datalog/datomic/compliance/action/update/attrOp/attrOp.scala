package molecule.db.datalog.datomic.compliance.action.update.attrOp

import molecule.core.setup.MUnit
import molecule.db.compliance.test.action.update.attrOp.{AttrOp_Boolean, AttrOp_String}
import molecule.db.datalog.datomic.setup.Api_datomic_async


class AttrOp_BooleanTest extends MUnit {
  AttrOp_Boolean(this, Api_datomic_async)
}
class AttrOp_StringTest extends MUnit {
  AttrOp_String(this, Api_datomic_async)
}

