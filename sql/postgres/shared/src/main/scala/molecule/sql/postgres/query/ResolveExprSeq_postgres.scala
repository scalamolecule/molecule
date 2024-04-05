package molecule.sql.postgres.query

import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.sql.core.query.{ResolveExprSeq, SqlQueryBase}
import scala.collection.mutable.ListBuffer
import scala.reflect.ClassTag
import scala.util.Random


trait ResolveExprSeq_postgres
  extends ResolveExprSeq
    with LambdasSeq_postgres { self: SqlQueryBase =>


  // attr ----------------------------------------------------------------------

  override protected def setAttr(col: String): Unit = {
    // avoid empty arrays
    where += (("", s"ARRAY_LENGTH($col, 1) IS NOT NULL"))
  }
}