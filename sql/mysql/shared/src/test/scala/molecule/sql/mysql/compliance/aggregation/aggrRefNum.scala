package molecule.sql.mysql.compliance.aggregation

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.aggregation.refNum._
import molecule.sql.mysql.setup.Api_mysql_async

class AggrRefNum_sum extends Test {
  AggrRefNum_sum(this, Api_mysql_async)
}
class AggrRefNum_median extends Test {
  AggrRefNum_median(this, Api_mysql_async)
}
class AggrRefNum_avg extends Test {
  AggrRefNum_avg(this, Api_mysql_async)
}
class AggrRefNum_variance extends Test {
  AggrRefNum_variance(this, Api_mysql_async)
}
class AggrRefNum_stddev extends Test {
  AggrRefNum_stddev(this, Api_mysql_async)
}
