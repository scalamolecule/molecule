package molecule.rpc.openapi.transaction

import molecule.db.common.spi.DataType

trait OpenapiDataType_JS extends DataType {

  type Data = String // dummy not used on JS side since we serialize with boopickle
}
