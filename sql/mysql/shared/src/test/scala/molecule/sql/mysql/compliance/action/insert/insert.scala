package molecule.sql.mysql.compliance.action.insert

import molecule.coreTests.setup.{Test, MUnitSuiteWithArrays}
import molecule.coreTests.spi.action.insert._
import molecule.sql.mysql.setup.Api_mysql_async

class InsertCardOne extends Test {
  InsertCardOne(this, Api_mysql_async)
}
class InsertCardSeq extends MUnitSuiteWithArrays {
  InsertCardSeq(this, Api_mysql_async)
}
class InsertCardSet extends Test {
  InsertCardSet(this, Api_mysql_async)
}
class InsertCardMap extends Test {
  InsertCardMap(this, Api_mysql_async)
}
class InsertRefs extends Test {
  InsertRefs(this, Api_mysql_async)
}
class InsertSemantics extends Test {
  InsertSemantics(this, Api_mysql_async)
}
