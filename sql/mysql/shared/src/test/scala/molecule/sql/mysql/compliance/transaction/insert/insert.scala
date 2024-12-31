package molecule.sql.mysql.compliance.transaction.insert

import molecule.coreTests.setup.{MUnitSuite, MUnitSuiteWithArrays}
import molecule.coreTests.spi.action.insert._
import molecule.sql.mysql.setup.Api_mysql_async

class InsertCardOne extends MUnitSuite {
  InsertCardOne(this, Api_mysql_async)
}
class InsertCardSeq extends MUnitSuiteWithArrays {
  InsertCardSeq(this, Api_mysql_async)
}
class InsertCardSet extends MUnitSuite {
  InsertCardSet(this, Api_mysql_async)
}
class InsertCardMap extends MUnitSuite {
  InsertCardMap(this, Api_mysql_async)
}
class InsertRefs extends MUnitSuite {
  InsertRefs(this, Api_mysql_async)
}
class InsertSemantics extends MUnitSuite {
  InsertSemantics(this, Api_mysql_async)
}
