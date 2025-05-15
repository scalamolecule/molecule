package molecule.db.sql.mysql.compliance.action.update.filter

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.action.update.filter.{FilterMap, FilterOne, FilterSeq, FilterSet}
import molecule.db.sql.mysql.setup.Api_mysql_async

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
