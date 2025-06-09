package boilerplate.db.sql

import boilerplate.Util

abstract class DbSqlBase(fileName: String, dir: String)
  extends Util(
    fileName,
    dir,
    "db/sql/core/shared/src/main/scala/molecule/db/sql/core"
  )
