package molecule.db.h2.compliance.action.update.attrOp

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.action.update.attrOp.{AttrOp_Boolean, AttrOp_String}
import molecule.db.h2.setup.Api_h2_async


class AttrOp_BooleanTest extends MUnit {
  AttrOp_Boolean(this, Api_h2_async)
}
class AttrOp_StringTest extends MUnit {
  AttrOp_String(this, Api_h2_async)
}

