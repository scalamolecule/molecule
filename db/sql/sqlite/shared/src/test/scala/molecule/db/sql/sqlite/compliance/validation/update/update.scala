package molecule.db.sql.sqlite.compliance.validation.update

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.validation.update.{TypesOne, TypesOneOpt, TypesSeq, TypesSeqOpt, TypesSet, TypesSetOpt}
import molecule.db.sql
import molecule.db.sql.sqlite.setup.Api_sqlite_async

class TypesOneTest extends Test {
  TypesOne(this, Api_sqlite_async)
}
class TypesOneOptTest extends Test {
  TypesOneOpt(this, Api_sqlite_async)
}
class TypesSeqTest extends Test {
  TypesSeq(this, Api_sqlite_async)
}
class TypesSeqOptTest extends Test {
  TypesSeqOpt(this, Api_sqlite_async)
}
class TypesSetTest extends Test {
  TypesSet(this, Api_sqlite_async)
}
class TypesSetOptTest extends Test {
  TypesSetOpt(this, Api_sqlite_async)
}
