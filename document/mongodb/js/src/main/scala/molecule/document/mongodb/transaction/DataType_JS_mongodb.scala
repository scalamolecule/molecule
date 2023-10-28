package molecule.document.mongodb.transaction

import molecule.core.spi.DataType

trait DataType_JS_mongodb extends DataType {

  type Data = String // dummy not used since we serialize with boopickle
}
