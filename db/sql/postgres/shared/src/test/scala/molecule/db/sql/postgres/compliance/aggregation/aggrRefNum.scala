package molecule.db.sql.postgres.compliance.aggregation

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.aggregation.refNum.*
import molecule.db.sql
import molecule.db.sql.postgres.setup.Api_postgres_async

class AggrRefNum_sumTest extends Test {
  AggrRefNum_sum(this, Api_postgres_async)
}
class AggrRefNum_medianTest extends Test {
  AggrRefNum_median(this, Api_postgres_async)
}
class AggrRefNum_avgTest extends Test {
  AggrRefNum_avg(this, Api_postgres_async)
}
class AggrRefNum_varianceTest extends Test {
  AggrRefNum_variance(this, Api_postgres_async)
}
class AggrRefNum_stddevTest extends Test {
  AggrRefNum_stddev(this, Api_postgres_async)
}
