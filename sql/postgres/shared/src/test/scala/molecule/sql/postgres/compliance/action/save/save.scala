package molecule.sql.postgres.compliance.action.save

import molecule.coreTests.setup.{Test, MUnitSuiteWithArrays}
import molecule.coreTests.spi.action.save._
import molecule.sql.postgres.setup.Api_postgres_async

class SaveCardOne extends Test {
  SaveCardOne(this, Api_postgres_async)
}
class SaveCardSeq extends MUnitSuiteWithArrays {
  SaveCardSeq(this, Api_postgres_async)
}
class SaveCardSet extends Test {
  SaveCardSet(this, Api_postgres_async)
}
class SaveCardMap extends Test {
  SaveCardMap(this, Api_postgres_async)
}
class SaveRefs extends Test {
  SaveRefs(this, Api_postgres_async)
}
class SaveSemantics extends Test {
  SaveSemantics(this, Api_postgres_async)
}
