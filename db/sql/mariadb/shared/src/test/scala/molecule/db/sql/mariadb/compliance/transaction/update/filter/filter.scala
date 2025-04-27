package molecule.db.sql.mariadb.compliance.transaction.update.filter

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.action.update.filter.*
import molecule.db.sql
import molecule.db.sql.mariadb.setup.Api_mariadb_async

class FilterOneTest extends Test {
  FilterOne(this, Api_mariadb_async)
}
class FilterSetTest extends Test {
  FilterSet(this, Api_mariadb_async)
}
class FilterSeqTest extends Test {
  FilterSeq(this, Api_mariadb_async)
}
class FilterMapTest extends Test {
  FilterMap(this, Api_mariadb_async)
}
