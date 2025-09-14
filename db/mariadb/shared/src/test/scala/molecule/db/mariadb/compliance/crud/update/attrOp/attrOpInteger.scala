package molecule.db.mariadb.compliance.crud.update.attrOp

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.crud.update.attrOp.number.*
import molecule.db.mariadb.setup.Api_mariadb_async


class AttrOpInteger_IntTest extends MUnit {
  AttrOpInteger_Int(this, Api_mariadb_async)
}
class AttrOpInteger_Long_Test extends MUnit {
  AttrOpInteger_Long_(this, Api_mariadb_async)
}
class AttrOpInteger_BigIntTest extends MUnit {
  AttrOpInteger_BigInt(this, Api_mariadb_async)
}
class AttrOpInteger_Byte_Test extends MUnit {
  AttrOpInteger_Byte_(this, Api_mariadb_async)
}
class AttrOpInteger_Short_Test extends MUnit {
  AttrOpInteger_Short_(this, Api_mariadb_async)
}


