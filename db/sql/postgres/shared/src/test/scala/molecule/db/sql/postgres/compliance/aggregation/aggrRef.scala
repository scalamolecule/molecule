package molecule.db.sql.postgres.compliance.aggregation

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.aggregation.ref.*
import molecule.db.sql.postgres.setup.Api_postgres_async

class AggrRef_countTest extends Test {
  AggrRef_count(this, Api_postgres_async)
}
class AggrRef_distinctTest extends Test {
  AggrRef_distinct(this, Api_postgres_async)
}
class AggrRef_min_maxTest extends Test {
  AggrRef_min_max(this, Api_postgres_async)
}
class AggrRef_min_max_nTest extends Test {
  AggrRef_min_max_n(this, Api_postgres_async)
}
class AggrRef_sampleTest extends Test {
  AggrRef_sample(this, Api_postgres_async)
}
class AggrRef_sample_nTest extends Test {
  AggrRef_sample_n(this, Api_postgres_async)
}
