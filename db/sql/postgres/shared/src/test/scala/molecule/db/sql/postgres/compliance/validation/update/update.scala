package molecule.db.sql.postgres.compliance.validation.update

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.validation.update.{TypesOne, TypesOneOpt, TypesSeq, TypesSeqOpt, TypesSet, TypesSetOpt}
import molecule.db.sql
import molecule.db.sql.postgres.setup.Api_postgres_async

class TypesOneTest extends Test {
  TypesOne(this, Api_postgres_async)
}
class TypesOneOptTest extends Test {
  TypesOneOpt(this, Api_postgres_async)
}
class TypesSeqTest extends Test {
  TypesSeq(this, Api_postgres_async)
}
class TypesSeqOptTest extends Test {
  TypesSeqOpt(this, Api_postgres_async)
}
class TypesSetTest extends Test {
  TypesSet(this, Api_postgres_async)
}
class TypesSetOptTest extends Test {
  TypesSetOpt(this, Api_postgres_async)
}
