package molecule.sql.postgres.compliance.action.update.filter

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.action.update.filter._
import molecule.sql.postgres.setup.Api_postgres_async

class FilterOne extends Test {
  FilterOne(this, Api_postgres_async)
}
class FilterSet extends Test {
  FilterSet(this, Api_postgres_async)
}
class FilterSeq extends Test {
  FilterSeq(this, Api_postgres_async)
}
class FilterMap extends Test {
  FilterMap(this, Api_postgres_async)
}
