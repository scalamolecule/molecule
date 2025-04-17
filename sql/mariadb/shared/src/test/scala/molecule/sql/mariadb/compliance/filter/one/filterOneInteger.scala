package molecule.sql.mariadb.compliance.filter.one

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.filter.one.number._
import molecule.sql.mariadb.setup.Api_mariadb_async


class FilterOneInteger_IntTest extends Test {
  FilterOneInteger_Int(this, Api_mariadb_async)
}
class FilterOneInteger_Long_Test extends Test {
  FilterOneInteger_Long_(this, Api_mariadb_async)
}
class FilterOneInteger_BigInt_Test extends Test {
  FilterOneInteger_BigInt_(this, Api_mariadb_async)
}
class FilterOneInteger_Byte_Test extends Test {
  FilterOneInteger_Byte_(this, Api_mariadb_async)
}
class FilterOneInteger_Short_Test extends Test {
  FilterOneInteger_Short_(this, Api_mariadb_async)
}

