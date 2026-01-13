package molecule.db.sqlite.compliance.subquery

import molecule.core.setup.{MUnit, MUnit_arrays}
import molecule.db.compliance.test.subquery.*
import molecule.db.sqlite.setup.Api_sqlite_async


class _OverviewTest extends MUnit {
  _Overview(this, Api_sqlite_async)
}
class AggregatesTest extends MUnit {
  Aggregates(this, Api_sqlite_async)
}
class ComparisonOperatorsTest extends MUnit {
  ComparisonOperators(this, Api_sqlite_async)
}
class FilteringTest extends MUnit {
  Filtering(this, Api_sqlite_async)
}
class LimitOffsetTest extends MUnit {
  LimitOffset(this, Api_sqlite_async)
}
class NestedRelationshipsTest extends MUnit {
  NestedRelationships(this, Api_sqlite_async)
}
class SelectVsJoinTest extends MUnit {
  SelectVsJoin(this, Api_sqlite_async)
}
class SemanticsTest extends MUnit {
  Semantics(this, Api_sqlite_async)
}
class SortingTest extends MUnit {
  Sorting(this, Api_sqlite_async)
}
