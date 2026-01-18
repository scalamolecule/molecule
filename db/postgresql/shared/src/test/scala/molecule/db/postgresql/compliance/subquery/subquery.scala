package molecule.db.postgresql.compliance.subquery

import molecule.core.setup.MUnit
import molecule.db.compliance.test.subquery._
import molecule.db.postgresql.setup.Api_postgresql_async


class _OverviewTest extends MUnit {
  _Overview(this, Api_postgresql_async)
}
class AggregatesTest extends MUnit {
  Aggregates(this, Api_postgresql_async)
}
class ComparisonOperatorsTest extends MUnit {
  ComparisonOperators(this, Api_postgresql_async)
}
class FilteringTest extends MUnit {
  Filtering(this, Api_postgresql_async)
}
class LimitOffsetTest extends MUnit {
  LimitOffset(this, Api_postgresql_async)
}
class NestedRelationshipsTest extends MUnit {
  NestedRelationships(this, Api_postgresql_async)
}
class SemanticsTest extends MUnit {
  Semantics(this, Api_postgresql_async)
}
class SortingTest extends MUnit {
  Sorting(this, Api_postgresql_async)
}
