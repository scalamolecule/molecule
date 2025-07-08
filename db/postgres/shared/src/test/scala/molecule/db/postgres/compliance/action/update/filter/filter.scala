package molecule.db.postgres.compliance.action.update.filter

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.action.update.filter.{FilterMap, FilterOne, FilterSeq, FilterSet}
import molecule.db.postgres.setup.Api_postgres_async

class FilterOneTest extends MUnit {
  FilterOne(this, Api_postgres_async)
}
class FilterSetTest extends MUnit {
  FilterSet(this, Api_postgres_async)
}
class FilterSeqTest extends MUnit {
  FilterSeq(this, Api_postgres_async)
}
class FilterMapTest extends MUnit {
  FilterMap(this, Api_postgres_async)
}
