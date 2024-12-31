package molecule.sql.mariadb.compliance.validation.update

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.validation.update._
import molecule.sql.mariadb.setup.Api_mariadb_async

class TypesOne extends Test {
  TypesOne(this, Api_mariadb_async)
}
class TypesOneOpt extends Test {
  TypesOneOpt(this, Api_mariadb_async)
}
class TypesSeq extends Test {
  TypesSeq(this, Api_mariadb_async)
}
class TypesSeqOpt extends Test {
  TypesSeqOpt(this, Api_mariadb_async)
}
class TypesSet extends Test {
  TypesSet(this, Api_mariadb_async)
}
class TypesSetOpt extends Test {
  TypesSetOpt(this, Api_mariadb_async)
}
