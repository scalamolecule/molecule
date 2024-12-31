package molecule.sql.mysql.compliance.transaction.update.filter

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.action.update.filter._
import molecule.sql.mysql.setup.Api_mysql_async

class FilterOne extends MUnitSuite {
  FilterOne(this, Api_mysql_async)
}
class FilterSet extends MUnitSuite {
  FilterSet(this, Api_mysql_async)
}
class FilterSeq extends MUnitSuite {
  FilterSeq(this, Api_mysql_async)
}
class FilterMap extends MUnitSuite {
  FilterMap(this, Api_mysql_async)
}
