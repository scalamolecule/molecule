package molecule.db.common.util

import scala.io.Source
import scala.util.Using
import molecule.db.common.marshalling.ConnProxy

trait SchemaLoader {
  def getSchema(schemaResourcePath: String): String = {
    Using(Source.fromResource(schemaResourcePath)) { source =>
      source.mkString
    }.getOrElse(throw new Exception(
      s"Couldn't find database schema file resources/$schemaResourcePath."
    ))
  }

  def getSqlInit(proxy: ConnProxy): String = {
    proxy.initSql + getSchema(proxy.metaDb.schemaResourcePath)
  }
}
