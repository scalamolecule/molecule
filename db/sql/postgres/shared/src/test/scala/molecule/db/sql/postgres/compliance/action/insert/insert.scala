package molecule.db.sql.postgres.compliance.action.insert

import molecule.coreTests.setup.{MUnitSuiteWithArrays, Test}
import molecule.coreTests.spi.action.insert.*
import molecule.db.sql
import molecule.db.sql.postgres.setup.Api_postgres_async

class InsertCardOneTest extends Test {
  InsertCardOne(this, Api_postgres_async)
}
class InsertCardSeqTest extends MUnitSuiteWithArrays {
  InsertCardSeq(this, Api_postgres_async)
}
class InsertCardSetTest extends Test {
  InsertCardSet(this, Api_postgres_async)
}
class InsertCardMapTest extends Test {
  InsertCardMap(this, Api_postgres_async)
}
class InsertRefsTest extends Test {
  InsertRefs(this, Api_postgres_async)
}
class InsertSemanticsTest extends Test {
  InsertSemantics(this, Api_postgres_async)
}
