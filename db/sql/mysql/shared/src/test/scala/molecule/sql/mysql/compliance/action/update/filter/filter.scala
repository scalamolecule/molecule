package molecule.sql.mysql.compliance.action.update.filter

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.action.update.filter.*
import molecule.sql.mysql.setup.Api_mysql_async

class FilterOneTest extends Test {
  FilterOne(this, Api_mysql_async)
}
class FilterSetTest extends Test {
  FilterSet(this, Api_mysql_async)
}
class FilterSeqTest extends Test {
  FilterSeq(this, Api_mysql_async)
}
class FilterMapTest extends Test {
  FilterMap(this, Api_mysql_async)
}
