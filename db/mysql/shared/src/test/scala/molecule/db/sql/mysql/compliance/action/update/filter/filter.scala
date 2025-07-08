package molecule.db.sql.mysql.compliance.action.update.filter

import molecule.core.setup.MUnit
import molecule.db.compliance.test.action.update.filter.{FilterMap, FilterOne, FilterSeq, FilterSet}
import molecule.db.sql.mysql.setup.Api_mysql_async

class FilterOneTest extends MUnit {
  FilterOne(this, Api_mysql_async)
}
class FilterSetTest extends MUnit {
  FilterSet(this, Api_mysql_async)
}
class FilterSeqTest extends MUnit {
  FilterSeq(this, Api_mysql_async)
}
class FilterMapTest extends MUnit {
  FilterMap(this, Api_mysql_async)
}
