package molecule.db.sql.postgres.compliance.action.update.attrOp

import molecule.core.setup.MUnit
import molecule.db.compliance.test.action.update.attrOp.number.*
import molecule.db.sql.postgres.setup.Api_postgres_async


class AttrOpInteger_IntTest extends MUnit {
  AttrOpInteger_Int(this, Api_postgres_async)
}
class AttrOpInteger_Long_Test extends MUnit {
  AttrOpInteger_Long_(this, Api_postgres_async)
}
class AttrOpInteger_BigIntTest extends MUnit {
  AttrOpInteger_BigInt(this, Api_postgres_async)
}
class AttrOpInteger_Byte_Test extends MUnit {
  AttrOpInteger_Byte_(this, Api_postgres_async)
}
class AttrOpInteger_Short_Test extends MUnit {
  AttrOpInteger_Short_(this, Api_postgres_async)
}

