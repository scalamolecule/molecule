package molecule.db.sql.sqlite.compliance.aggregation

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.aggregation.refNum.*
import molecule.db.sql
import molecule.db.sql.sqlite.setup.Api_sqlite_async

class AggrRefNum_sumTest extends Test {
  AggrRefNum_sum(this, Api_sqlite_async)
}
class AggrRefNum_medianTest extends Test {
  AggrRefNum_median(this, Api_sqlite_async)
}
class AggrRefNum_avgTest extends Test {
  AggrRefNum_avg(this, Api_sqlite_async)
}
class AggrRefNum_varianceTest extends Test {
  AggrRefNum_variance(this, Api_sqlite_async)
}
class AggrRefNum_stddevTest extends Test {
  AggrRefNum_stddev(this, Api_sqlite_async)
}
