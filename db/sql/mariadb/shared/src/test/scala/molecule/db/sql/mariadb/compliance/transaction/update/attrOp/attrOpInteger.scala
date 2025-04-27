package molecule.db.sql.mariadb.compliance.transaction.update.attrOp

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.action.update.attrOp.number.*
import molecule.db.sql
import molecule.db.sql.mariadb.setup.Api_mariadb_async


class AttrOpInteger_IntTest extends Test {
  AttrOpInteger_Int(this, Api_mariadb_async)
}
class AttrOpInteger_Long_Test extends Test {
  AttrOpInteger_Long_(this, Api_mariadb_async)
}
class AttrOpInteger_BigIntTest extends Test {
  AttrOpInteger_BigInt(this, Api_mariadb_async)
}
class AttrOpInteger_Byte_Test extends Test {
  AttrOpInteger_Byte_(this, Api_mariadb_async)
}
class AttrOpInteger_Short_Test extends Test {
  AttrOpInteger_Short_(this, Api_mariadb_async)
}


