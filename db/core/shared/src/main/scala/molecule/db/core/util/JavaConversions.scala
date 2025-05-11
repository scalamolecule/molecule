package molecule.db.core.util

import scala.collection.convert.{AsJavaExtensions, AsScalaExtensions}

/** Base class for Datomic connection facade.
  *
  */
trait JavaConversions extends AsJavaExtensions with AsScalaExtensions
