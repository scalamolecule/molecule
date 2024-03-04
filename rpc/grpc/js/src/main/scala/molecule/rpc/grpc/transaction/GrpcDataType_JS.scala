package molecule.rpc.grpc.transaction

import molecule.core.spi.DataType

trait GrpcDataType_JS extends DataType {

  type Data = String // dummy not used on JS side since we serialize with boopickle
}
