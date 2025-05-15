package molecule.db.sql.h2.compliance.action.update.attrOp

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.action.update.attrOp.number.*
import molecule.db.sql.h2.setup.Api_h2_async


class AttrOpInteger_IntTest extends Test {
  AttrOpInteger_Int(this, Api_h2_async)
}
class AttrOpInteger_Long_Test extends Test {
  AttrOpInteger_Long_(this, Api_h2_async)
}
class AttrOpInteger_BigIntTest extends Test {
  AttrOpInteger_BigInt(this, Api_h2_async)
}
class AttrOpInteger_Byte_Test extends Test {
  AttrOpInteger_Byte_(this, Api_h2_async)
}
class AttrOpInteger_Short_Test extends Test {
  AttrOpInteger_Short_(this, Api_h2_async)
}


