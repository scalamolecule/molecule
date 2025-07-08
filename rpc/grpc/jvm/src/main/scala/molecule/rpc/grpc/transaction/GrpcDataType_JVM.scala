package molecule.rpc.grpc.transaction

import java.util.{List => jList}
import molecule.db.common.spi.DataType

trait GrpcDataType_JVM extends DataType {

  type Data = String
}
