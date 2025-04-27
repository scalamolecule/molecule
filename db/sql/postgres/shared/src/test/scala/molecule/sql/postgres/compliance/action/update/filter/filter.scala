package molecule.sql.postgres.compliance.action.update.filter

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.action.update.filter.*
import molecule.sql.postgres.setup.Api_postgres_async

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
