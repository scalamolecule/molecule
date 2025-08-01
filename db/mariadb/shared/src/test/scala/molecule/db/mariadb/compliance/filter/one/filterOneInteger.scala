package molecule.db.mariadb.compliance.filter.one

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.filter.one.number.*
import molecule.db.mariadb.setup.Api_mariadb_async


class FilterOneInteger_IntTest extends MUnit {
  FilterOneInteger_Int(this, Api_mariadb_async)
}
class FilterOneInteger_Long_Test extends MUnit {
  FilterOneInteger_Long_(this, Api_mariadb_async)
}
class FilterOneInteger_BigInt_Test extends MUnit {
  FilterOneInteger_BigInt_(this, Api_mariadb_async)
}
class FilterOneInteger_Byte_Test extends MUnit {
  FilterOneInteger_Byte_(this, Api_mariadb_async)
}
class FilterOneInteger_Short_Test extends MUnit {
  FilterOneInteger_Short_(this, Api_mariadb_async)
}

