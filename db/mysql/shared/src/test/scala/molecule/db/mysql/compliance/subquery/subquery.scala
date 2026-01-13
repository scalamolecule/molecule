package molecule.db.mysql.compliance.subquery

import molecule.core.setup.{MUnit, MUnit_arrays}
import molecule.db.compliance.test.subquery.*
import molecule.db.mysql.setup.Api_mysql_async


class _OverviewTest extends MUnit {
  _Overview(this, Api_mysql_async)
}
class AggregatesTest extends MUnit {
  Aggregates(this, Api_mysql_async)
}
class ComparisonOperatorsTest extends MUnit {
  ComparisonOperators(this, Api_mysql_async)
}
class FilteringTest extends MUnit {
  Filtering(this, Api_mysql_async)
}
class LimitOffsetTest extends MUnit {
  LimitOffset(this, Api_mysql_async)
}
class NestedRelationshipsTest extends MUnit {
  NestedRelationships(this, Api_mysql_async)
}
class SelectVsJoinTest extends MUnit {
  SelectVsJoin(this, Api_mysql_async)
}
class SemanticsTest extends MUnit {
  Semantics(this, Api_mysql_async)
}
class SortingTest extends MUnit {
  Sorting(this, Api_mysql_async)
}
