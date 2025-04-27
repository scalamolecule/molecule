package molecule.sql.mariadb.compliance.transaction.save

import molecule.coreTests.setup.{MUnitSuiteWithArrays, Test}
import molecule.coreTests.spi.action.save.*
import molecule.sql.mariadb.setup.Api_mariadb_async

class SaveCardOneTest extends Test {
  SaveCardOne(this, Api_mariadb_async)
}
class SaveCardSeqTest extends MUnitSuiteWithArrays {
  SaveCardSeq(this, Api_mariadb_async)
}
class SaveCardSetTest extends Test {
  SaveCardSet(this, Api_mariadb_async)
}
class SaveCardMapTest extends Test {
  SaveCardMap(this, Api_mariadb_async)
}
class SaveRefsTest extends Test {
  SaveRefs(this, Api_mariadb_async)
}
class SaveSemanticsTest extends Test {
  SaveSemantics(this, Api_mariadb_async)
}
