package molecule.db.mariadb.compliance.types

import molecule.core.setup.MUnit
import molecule.db.compliance.test.types.*
import molecule.db.mariadb.setup.Api_mariadb_sync

class Map_Test extends MUnit {
  Map_(this, Api_mariadb_sync)
}
class One_Test extends MUnit {
  One_(this, Api_mariadb_sync)
}
class One_Boolean_Test extends MUnit {
  One_Boolean(this, Api_mariadb_sync)
}
class One_Decimal_Test extends MUnit {
  One_Decimal(this, Api_mariadb_sync)
}
class One_Enum_Test extends MUnit {
  One_Enum(this, Api_mariadb_sync)
}
class One_Integer_Test extends MUnit {
  One_Integer(this, Api_mariadb_sync)
}
class One_String_Test extends MUnit {
  One_String(this, Api_mariadb_sync)
}
class Seq_Test extends MUnit {
  Seq_(this, Api_mariadb_sync)
}
class Set_Test extends MUnit {
  Set_(this, Api_mariadb_sync)
}
