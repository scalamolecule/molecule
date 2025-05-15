package molecule.db.sql.mysql.compliance.aggregation

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.aggregation.refNum.*
import molecule.db.sql.mysql.setup.Api_mysql_async

class AggrRefNum_sumTest extends Test {
  AggrRefNum_sum(this, Api_mysql_async)
}
class AggrRefNum_medianTest extends Test {
  AggrRefNum_median(this, Api_mysql_async)
}
class AggrRefNum_avgTest extends Test {
  AggrRefNum_avg(this, Api_mysql_async)
}
class AggrRefNum_varianceTest extends Test {
  AggrRefNum_variance(this, Api_mysql_async)
}
class AggrRefNum_stddevTest extends Test {
  AggrRefNum_stddev(this, Api_mysql_async)
}
