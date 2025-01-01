package molecule.sql.mysql.compliance.action.update.filter

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.action.update.filter._
import molecule.sql.mysql.setup.Api_mysql_async

class FilterOne extends Test {
  FilterOne(this, Api_mysql_async)
}
class FilterSet extends Test {
  FilterSet(this, Api_mysql_async)
}
class FilterSeq extends Test {
  FilterSeq(this, Api_mysql_async)
}
class FilterMap extends Test {
  FilterMap(this, Api_mysql_async)
}
