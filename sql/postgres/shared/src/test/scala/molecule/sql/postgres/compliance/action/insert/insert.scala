package molecule.sql.postgres.compliance.action.insert

import molecule.coreTests.setup.{MUnitSuiteWithArrays, Test}
import molecule.coreTests.spi.action.insert._
import molecule.sql.postgres.setup.Api_postgres_async

class InsertCardOne extends Test {
  InsertCardOne(this, Api_postgres_async)
}
class InsertCardSeq extends MUnitSuiteWithArrays {
  InsertCardSeq(this, Api_postgres_async)
}
class InsertCardSet extends Test {
  InsertCardSet(this, Api_postgres_async)
}
class InsertCardMap extends Test {
  InsertCardMap(this, Api_postgres_async)
}
class InsertRefs extends Test {
  InsertRefs(this, Api_postgres_async)
}
class InsertSemantics extends Test {
  InsertSemantics(this, Api_postgres_async)
}
