package molecule.db.h2.compliance.subquery

import molecule.core.setup.{MUnit, MUnit_arrays}
import molecule.db.compliance.test.subquery.*
import molecule.db.h2.setup.Api_h2_async


class _OverviewTest extends MUnit {
  _Overview(this, Api_h2_async)
}
class AggregatesTest extends MUnit {
  Aggregates(this, Api_h2_async)
}
class ComparisonOperatorsTest extends MUnit {
  ComparisonOperators(this, Api_h2_async)
}
class FilteringTest extends MUnit {
  Filtering(this, Api_h2_async)
}
class NestedRelationshipsTest extends MUnit {
  NestedRelationships(this, Api_h2_async)
}
class SemanticsTest extends MUnit {
  Semantics(this, Api_h2_async)
}
class SortingTest extends MUnit {
  Sorting(this, Api_h2_async)
}
