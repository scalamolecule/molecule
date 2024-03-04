package molecule.graphql.client.transaction

import molecule.core.spi.DataType

trait GraphqlDataType_JS extends DataType {

  type Data = String // dummy not used on JS side since we serialize with boopickle
}
