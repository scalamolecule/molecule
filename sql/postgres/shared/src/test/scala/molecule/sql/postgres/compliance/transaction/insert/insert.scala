package molecule.sql.postgres.compliance.transaction.insert

import molecule.coreTests.setup.{MUnitSuite, MUnitSuiteWithArrays}
import molecule.coreTests.spi.action.insert._
import molecule.sql.postgres.setup.Api_postgres_async

class InsertCardOne extends MUnitSuite {
  InsertCardOne(this, Api_postgres_async)
}
class InsertCardSeq extends MUnitSuiteWithArrays {
  InsertCardSeq(this, Api_postgres_async)
}
class InsertCardSet extends MUnitSuite {
  InsertCardSet(this, Api_postgres_async)
}
class InsertCardMap extends MUnitSuite {
  InsertCardMap(this, Api_postgres_async)
}
class InsertRefs extends MUnitSuite {
  InsertRefs(this, Api_postgres_async)
}
class InsertSemantics extends MUnitSuite {
  InsertSemantics(this, Api_postgres_async)
}
