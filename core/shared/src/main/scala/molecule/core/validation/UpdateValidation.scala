//package molecule.core.validation
//
//import molecule.base.error._
//import molecule.boilerplate.ast.Model._
//import molecule.core.api.Connection
//import molecule.core.validation.insert.{InsertValidationExtraction, InsertValidationResolvers_}
//
//object UpdateValidation {
//
//  def validate(
//    conn: Connection,
//    elements: List[Element],
//    tpls: Seq[Product]
//  ): Seq[(Int, Seq[InsertError])] = {
//    val (nsMap, attrMap)               = (conn.proxy.nsMap, conn.proxy.attrMap)
//
////    val conn                              = conn0.asInstanceOf[DatomicConn_JVM]
////    val proxy                             = conn.proxy
//    val db                                = conn.peerConn.db()
//    val getCurSetValues: Attr => Set[Any] = (attr: Attr) => {
//      val a = s":${attr.ns}/${attr.attr}"
//      try {
//        val curValues = Peer.q(s"[:find ?vs :where [_ $a ?vs]]", db)
//        if (curValues.isEmpty) {
//          throw ExecutionError(s"While checking to avoid removing the last values of mandatory " +
//            s"attribute ${attr.ns}.${attr.attr} the current Set of values couldn't be found.")
//        }
//        val vs = ListBuffer.empty[Any]
//        curValues.forEach(row => vs.addOne(row.get(0)))
//        vs.toSet
//      } catch {
//        case e: MoleculeError => throw e
//        case t: Throwable     => throw ExecutionError(
//          s"Unexpected error trying to find current values of mandatory attribute ${attr.name}")
//      }
//    }
//    ModelValidation(
//      nsMap,
//      attrMap,
//      false,
//      Some(getCurSetValues)
//    ).check(elements)
//  }
//}
