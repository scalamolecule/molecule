package molecule.sql.postgres.query

import molecule.sql.core.query.{LambdasSet, SqlQueryBase}

trait LambdasSet_postgres extends LambdasSet { self: SqlQueryBase =>

  override protected lazy val j2Float: Any => Float = (v: Any) => v.toString.toFloat
  override protected lazy val j2Byte : Any => Byte  = (v: Any) => v.asInstanceOf[Short].toByte
  override protected lazy val j2Short: Any => Short = (v: Any) => v.asInstanceOf[Short]
}
