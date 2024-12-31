package molecule.sql.postgres.compliance.transaction.insert

import molecule.coreTests.setup.{Test, MUnitSuiteWithArrays}
import molecule.coreTests.spi.transaction.insert._
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
