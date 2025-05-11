package molecule.db.sql.postgres.compliance.action.update.filter

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.action.update.filter.{FilterMap, FilterOne, FilterSeq, FilterSet}
import molecule.db.sql
import molecule.db.sql.postgres.setup.Api_postgres_async

class FilterOneTest extends Test {
  FilterOne(this, Api_postgres_async)
}
class FilterSetTest extends Test {
  FilterSet(this, Api_postgres_async)
}
class FilterSeqTest extends Test {
  FilterSeq(this, Api_postgres_async)
}
class FilterMapTest extends Test {
  FilterMap(this, Api_postgres_async)
}
