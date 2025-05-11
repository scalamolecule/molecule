package codegen

import molecule.db.base.util.CodeGenTemplate

abstract class SpiTestGenBase(fileName: String, dir: String)
  extends CodeGenTemplate(
    fileName,
    dir,
    "coreTests/shared/src/test/scala/molecule/coreTests/spi"
  )

