package molecule.sql.postgres.compliance.transaction.update.filter

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.action.update.filter._
import molecule.sql.postgres.setup.Api_postgres_async

class FilterOne extends MUnitSuite {
  FilterOne(this, Api_postgres_async)
}
class FilterSet extends MUnitSuite {
  FilterSet(this, Api_postgres_async)
}
class FilterSeq extends MUnitSuite {
  FilterSeq(this, Api_postgres_async)
}
class FilterMap extends MUnitSuite {
  FilterMap(this, Api_postgres_async)
}
