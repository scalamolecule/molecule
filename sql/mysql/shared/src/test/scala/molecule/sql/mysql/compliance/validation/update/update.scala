package molecule.sql.mysql.compliance.validation.update

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.validation.update._
import molecule.sql.mysql.setup.Api_mysql_async

class TypesOne extends Test {
  TypesOne(this, Api_mysql_async)
}
class TypesOneOpt extends Test {
  TypesOneOpt(this, Api_mysql_async)
}
class TypesSeq extends Test {
  TypesSeq(this, Api_mysql_async)
}
class TypesSeqOpt extends Test {
  TypesSeqOpt(this, Api_mysql_async)
}
class TypesSet extends Test {
  TypesSet(this, Api_mysql_async)
}
class TypesSetOpt extends Test {
  TypesSetOpt(this, Api_mysql_async)
}
