package molecule.sql.mysql.compliance.aggregation

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.aggregation.ref.*
import molecule.sql.mysql.setup.Api_mysql_async

class AggrRef_countTest extends Test {
  AggrRef_count(this, Api_mysql_async)
}
class AggrRef_distinctTest extends Test {
  AggrRef_distinct(this, Api_mysql_async)
}
class AggrRef_min_maxTest extends Test {
  AggrRef_min_max(this, Api_mysql_async)
}
class AggrRef_min_max_nTest extends Test {
  AggrRef_min_max_n(this, Api_mysql_async)
}
class AggrRef_sampleTest extends Test {
  AggrRef_sample(this, Api_mysql_async)
}
class AggrRef_sample_nTest extends Test {
  AggrRef_sample_n(this, Api_mysql_async)
}
