package molecule.db.mysql.compliance.aggregation

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.aggregation.refNum.*
import molecule.db.mysql.setup.Api_mysql_async

class AggrRefNum_sumTest extends MUnit {
  AggrRefNum_sum(this, Api_mysql_async)
}
class AggrRefNum_medianTest extends MUnit {
  AggrRefNum_median(this, Api_mysql_async)
}
class AggrRefNum_avgTest extends MUnit {
  AggrRefNum_avg(this, Api_mysql_async)
}
class AggrRefNum_varianceTest extends MUnit {
  AggrRefNum_variance(this, Api_mysql_async)
}
class AggrRefNum_stddevTest extends MUnit {
  AggrRefNum_stddev(this, Api_mysql_async)
}
