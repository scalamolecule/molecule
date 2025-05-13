package boilerplate.db.core

import boilerplate.CodeGenTemplate

abstract class CoreGenBase(fileName: String, dir: String)
  extends CodeGenTemplate(
    fileName,
    dir,
    "db/core/shared/src/main/scala/molecule/db/core"
  )
