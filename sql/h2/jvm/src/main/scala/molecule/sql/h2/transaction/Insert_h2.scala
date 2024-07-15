package molecule.sql.h2.transaction

import molecule.base.ast.MetaNs
import molecule.boilerplate.ast.Model.Element
import molecule.core.transaction.{InsertResolvers_, ResolveInsert}
import molecule.sql.core.transaction.SqlInsert

trait Insert_h2 extends SqlInsert { self: ResolveInsert with InsertResolvers_ =>

  doPrint = false
  //    doPrint = true






  override protected def addOptRef(
    nsMap: Map[String, MetaNs],
    tplIndex: Int,
    ns: String,
    refAttr: String,
    refNs: String,
    nestedElements: List[Element]
  ): Product => Unit = {
    if (inserts.isEmpty) {
      inserts = inserts :+ (curRefPath -> Nil)
    }

    println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%")

    val curPath       = curRefPath

    //    // Make card-one ref from current empty namespace
    //    paramIndexes += (curPath, refAttr) -> 1
    //    inserts = inserts :+ (curPath -> List(refAttr -> ""))
    //
    //
    //    // Start new ref table
    //    val refPath = curPath ++ List(refAttr, refNs)
    //    curRefPath = refPath
    //    inserts = inserts :+ (refPath -> Nil)



    //    val joinTable  = ss(ns, refAttr, refNs)
    //    val (id1, id2) = if (ns == refNs)
    //      (ss(ns, "1_id"), ss(refNs, "2_id"))
    //    else
    //      (ss(ns, "id"), ss(refNs, "id"))
    val nextLevel  = level + 1
    //    val joinPath   = curRefPath :+ joinTable
    //    val leftPath   = curRefPath
    //    val rightPath  = List(s"$nextLevel", refNs)
    //    joins = joins :+ ((joinPath, id1, id2, leftPath, rightPath))

    // Initiate new level
    level = nextLevel
    //    curRefPath = List(s"$level", refNs)
    //    colSettersMap += curRefPath -> Nil


    // Start new ref table
    val refPath = curPath ++ List(refAttr, refNs)

    rightCountsMap(refPath) = List.empty[Int]

    paramIndexes += (curPath, refAttr) -> 1

    curRefPath = refPath
    inserts = inserts :+ (refPath -> List(refAttr -> ""))
    inserts = inserts :+ (refPath -> Nil)

    // Recursively resolve nested data
    val resolveNested = getResolver(nsMap, nestedElements)


    countValueAttrs(nestedElements) match {
      case 1 =>
        (tpl: Product) => {
//          println("tpl 1: " + tpl)

          tpl.productElement(tplIndex) match {
            case Some(value) =>
              //                val array = iterable2array(iterable.asInstanceOf[M[T]])
              //                (ps: PS, _: IdsMap, _: RowIndex) => {
              //                  val conn = ps.getConnection
              //                  val arr  = conn.createArrayOf(sqlTpe, array)
              //                  ps.setArray(paramIndex, arr)
              //                }

//              println("+++++++++++++++++++++++ " + value)

            case None =>
//              println("+++++++++++++++++++++++ NONE" )
            //                (ps: PS, _: IdsMap, _: RowIndex) =>
            //                  ps.setNull(paramIndex, java.sql.Types.NULL)
          }


          val optionalSingleValue = tpl.productElement(tplIndex).asInstanceOf[Option[Any]].filter {
            case set: Set[_] if set.isEmpty => false
            case _                          => true
          }
          val length              = optionalSingleValue.fold(0)(_ => 1)
          rightCountsMap(refPath) = rightCountsMap(refPath) :+ length
          optionalSingleValue.foreach { nestedSingleValue =>
//            println("   ######  " + nestedSingleValue)
            resolveNested(Tuple1(nestedSingleValue))
          }
        }
      case _ =>
        (tpl: Product) => {
          val optionalTpl = tpl.productElement(tplIndex).asInstanceOf[Option[Product]]
          val length      = optionalTpl.fold(0)(_ => 1)
          rightCountsMap(refPath) = rightCountsMap(refPath) :+ length
          var rowIndex = 0
          optionalTpl.foreach { nestedTpl =>
            debug(s"------ $rowIndex ##################################### " + nestedTpl)
            rowIndex += 1
            resolveNested(nestedTpl)
          }
        }
    }

    //    (tpl: Product) => {
    //      println("tpl 1: " + tpl)
    //
    //      val colSetter = tpl.productElement(tplIndex) match {
    //        case Some(iterable: Iterable[_]) =>
    //          val array = iterable2array(iterable.asInstanceOf[M[T]])
    //          (ps: PS, _: IdsMap, _: RowIndex) => {
    //            val conn = ps.getConnection
    //            val arr  = conn.createArrayOf(sqlTpe, array)
    //            ps.setArray(paramIndex, arr)
    //          }
    //
    //        case None =>
    //          (ps: PS, _: IdsMap, _: RowIndex) =>
    //            ps.setNull(paramIndex, java.sql.Types.NULL)
    //      }
    //      addColSetter(curPath, colSetter)
    //    }
  }

}