package molecule.sql.mysql.compliance.validation.update

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.validation.update.*
import molecule.sql.mysql.setup.Api_mysql_async

class TypesOneTest extends Test {
  TypesOne(this, Api_mysql_async)
}
class TypesOneOptTest extends Test {
  TypesOneOpt(this, Api_mysql_async)
}
class TypesSeqTest extends Test {
  TypesSeq(this, Api_mysql_async)
}
class TypesSeqOptTest extends Test {
  TypesSeqOpt(this, Api_mysql_async)
}
class TypesSetTest extends Test {
  TypesSet(this, Api_mysql_async)
}
class TypesSetOptTest extends Test {
  TypesSetOpt(this, Api_mysql_async)
}
