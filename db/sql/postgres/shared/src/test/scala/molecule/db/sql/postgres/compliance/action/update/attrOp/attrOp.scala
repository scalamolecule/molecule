package molecule.db.sql.postgres.compliance.action.update.attrOp

import molecule.core.setup.MUnit
import molecule.db.compliance.test.action.update.attrOp.{AttrOp_Boolean, AttrOp_String}
import molecule.db.sql.postgres.setup.Api_postgres_async


class AttrOp_BooleanTest extends MUnit {
  AttrOp_Boolean(this, Api_postgres_async)
}
class AttrOp_StringTest extends MUnit {
  AttrOp_String(this, Api_postgres_async)
}

