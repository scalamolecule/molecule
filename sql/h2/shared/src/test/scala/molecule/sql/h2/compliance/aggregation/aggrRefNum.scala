package molecule.sql.h2.compliance.aggregation

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.aggregation.refNum._
import molecule.sql.h2.setup.Api_h2_async

class AggrRefNum_sum extends MUnitSuite {
  AggrRefNum_sum(this, Api_h2_async)
}
class AggrRefNum_median extends MUnitSuite {
  AggrRefNum_median(this, Api_h2_async)
}
class AggrRefNum_avg extends MUnitSuite {
  AggrRefNum_avg(this, Api_h2_async)
}
class AggrRefNum_variance extends MUnitSuite {
  AggrRefNum_variance(this, Api_h2_async)
}
class AggrRefNum_stddev extends MUnitSuite {
  AggrRefNum_stddev(this, Api_h2_async)
}
