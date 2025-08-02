package molecule.db.mariadb.compliance.action.update.filter

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.action.update.filter.{FilterMap, FilterOne, FilterSeq, FilterSet}
import molecule.db.mariadb.setup.Api_mariadb_async

class FilterOneTest extends MUnit {
  FilterOne(this, Api_mariadb_async)
}
class FilterSetTest extends MUnit {
  FilterSet(this, Api_mariadb_async)
}
class FilterSeqTest extends MUnit {
  FilterSeq(this, Api_mariadb_async)
}
class FilterMapTest extends MUnit {
  FilterMap(this, Api_mariadb_async)
}
