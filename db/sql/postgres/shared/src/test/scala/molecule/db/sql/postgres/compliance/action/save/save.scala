package molecule.db.sql.postgres.compliance.action.save

import molecule.db.compliance.setup.{MUnitSuiteWithArrays, Test}
import molecule.db.compliance.test.action.save.*
import molecule.db.sql.postgres.setup.Api_postgres_async

class SaveCardOneTest extends Test {
  SaveCardOne(this, Api_postgres_async)
}
class SaveCardSeqTest extends MUnitSuiteWithArrays {
  SaveCardSeq(this, Api_postgres_async)
}
class SaveCardSetTest extends Test {
  SaveCardSet(this, Api_postgres_async)
}
class SaveCardMapTest extends Test {
  SaveCardMap(this, Api_postgres_async)
}
class SaveRefsTest extends Test {
  SaveRefs(this, Api_postgres_async)
}
class SaveSemanticsTest extends Test {
  SaveSemantics(this, Api_postgres_async)
}
