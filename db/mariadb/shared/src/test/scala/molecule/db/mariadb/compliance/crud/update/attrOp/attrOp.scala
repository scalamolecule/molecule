package molecule.db.mariadb.compliance.crud.update.attrOp

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.crud.update.attrOp.{AttrOp_Boolean, AttrOp_String}
import molecule.db.mariadb.setup.Api_mariadb_async


class AttrOp_BooleanTest extends MUnit {
  AttrOp_Boolean(this, Api_mariadb_async)
}
class AttrOp_StringTest extends MUnit {
  AttrOp_String(this, Api_mariadb_async)
}

