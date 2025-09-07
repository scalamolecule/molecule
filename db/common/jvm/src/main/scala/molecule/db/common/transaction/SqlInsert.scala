package molecule.db.common.transaction

import java.sql.PreparedStatement as PS
import molecule.core.dataModel.*
import molecule.db.common.transaction.strategy.SqlOps
import molecule.db.common.transaction.strategy.insert.{InsertAction, InsertRoot}
import molecule.db.common.transaction.strategy.SqlAction


//trait SqlInsert extends ValueTransformers { self: ResolveInsert & InsertResolvers & SqlOps =>
trait SqlInsert extends ValueTransformers { self: ResolveInsert =>

  protected var insertAction: InsertAction = null

  protected def addOne[T](
    ent: String,
    attr: String,
    paramIndex: Int,
    tplIndex: Int,
    valueSetter: (PS, Int, T) => Unit,
    exts: List[String] = Nil
  ): (PS, Product) => Unit = {
    (ps: PS, tpl: Product) =>
      println(s"addOne: $ent.$attr, $paramIndex, $tplIndex, $tpl")
      valueSetter(ps, paramIndex, tpl.productElement(tplIndex).asInstanceOf[T])
  }

  protected def addOneOpt[T](
    ent: String,
    attr: String,
    paramIndex: Int,
    tplIndex: Int,
    valueSetter: (PS, Int, T) => Unit,
    exts: List[String] = Nil
  ): (PS, Product) => Unit = {
    (ps: PS, tpl: Product) =>
      println(s"addOneOpt: $ent.$attr, $paramIndex, $tplIndex, $tpl")
      tpl.productElement(tplIndex) match {
        case Some(scalaValue) => valueSetter(ps, paramIndex, scalaValue.asInstanceOf[T])
        case None             => ps.setNull(paramIndex, java.sql.Types.NULL)
      }
  }


  protected def addSet[T](
    ent: String,
    attr: String,
    paramIndex: Int,
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String] = Nil,
    set2array: Set[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): (PS, Product) => Unit = {
    addIterable(attr, exts(1), paramIndex, tplIndex, set2array)
  }

  private def addIterable[T, M[_] <: Iterable[?]](
    attr: String,
    sqlTpe: String,
    paramIndex: Int,
    tplIndex: Int,
    iterable2array: M[T] => Array[AnyRef],
  ): (PS, Product) => Unit = {
    (ps: PS, tpl: Product) => {
      val array = iterable2array(tpl.productElement(tplIndex).asInstanceOf[M[T]])
      if (array.nonEmpty) {
        val conn = ps.getConnection
        val arr  = conn.createArrayOf(sqlTpe, array)
        ps.setArray(paramIndex, arr)
      } else {
        ps.setNull(paramIndex, java.sql.Types.NULL)
      }
    }
  }

  protected def addSetOpt[T](
    ent: String,
    attr: String,
    paramIndex: Int,
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String] = Nil,
    set2array: Set[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): (PS, Product) => Unit = {
    addOptIterable(attr, exts(1), paramIndex, tplIndex, set2array)
  }

  private def addOptIterable[T, M[_] <: Iterable[?]](
    attr: String,
    sqlTpe: String,
    paramIndex: Int,
    tplIndex: Int,
    iterable2array: M[T] => Array[AnyRef],
  ): (PS, Product) => Unit = {
    (ps: PS, tpl: Product) => {
      tpl.productElement(tplIndex) match {
        case Some(iterable: Iterable[_]) =>
          val array = iterable2array(iterable.asInstanceOf[M[T]])
          val conn  = ps.getConnection
          val arr   = conn.createArrayOf(sqlTpe, array)
          ps.setArray(paramIndex, arr)

        case None => ps.setNull(paramIndex, java.sql.Types.NULL)
      }
    }
  }

  protected def addSeq[T](
    ent: String,
    attr: String,
    paramIndex: Int,
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String] = Nil,
    seq2array: Seq[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): (PS, Product) => Unit = {
    addIterable(attr, exts(1), paramIndex, tplIndex, seq2array)
  }

  protected def addSeqOpt[T](
    ent: String,
    attr: String,
    paramIndex: Int,
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String] = Nil,
    seq2array: Seq[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): (PS, Product) => Unit = {
    addOptIterable(attr, exts(1), paramIndex, tplIndex, seq2array)
  }

  protected def addByteArray(
    ent: String,
    attr: String,
    paramIndex: Int,
    tplIndex: Int,
  ): (PS, Product) => Unit = {
    (ps: PS, tpl: Product) => {
      tpl.productElement(tplIndex) match {
        case byteArray: Array[_] if byteArray.nonEmpty =>
          ps.setBytes(paramIndex, byteArray.asInstanceOf[Array[Byte]])

        case Some(byteArray: Array[_]) if !byteArray.isEmpty =>
          ps.setBytes(paramIndex, byteArray.asInstanceOf[Array[Byte]])

        case _ => ps.setNull(paramIndex, 0)
      }
    }
  }

  protected def addMap[T](
    ent: String,
    attr: String,
    paramIndex: Int,
    tplIndex: Int,
    transformValue: T => Any,
    value2json: (StringBuffer, T) => StringBuffer
  ): (PS, Product) => Unit = {
    (ps: PS, tpl: Product) => {
      tpl.productElement(tplIndex).asInstanceOf[Map[String, ?]] match {
        case map if map.nonEmpty =>
          ps.setBytes(
            paramIndex,
            map2jsonByteArray(map.asInstanceOf[Map[String, T]], value2json)
          )

        case _ => ps.setNull(paramIndex, java.sql.Types.NULL)
      }
    }
  }

  protected def addMapOpt[T](
    ent: String,
    attr: String,
    paramIndex: Int,
    tplIndex: Int,
    transformValue: T => Any,
    value2json: (StringBuffer, T) => StringBuffer
  ): (PS, Product) => Unit = {
    (ps: PS, tpl: Product) => {
      tpl.productElement(tplIndex) match {
        case Some(map: Map[_, _]) if map.nonEmpty =>
          ps.setBytes(
            paramIndex,
            map2jsonByteArray(map.asInstanceOf[Map[String, T]], value2json)
          )

        case _ => ps.setNull(paramIndex, java.sql.Types.NULL)
      }
    }
  }

  //  protected def addRef(
  //    ent: String,
  //    refAttr: String,
  //    ref: String,
  //    relationship: Relationship,
  //    reverseRefAttr: Option[String]
  //  ): (PS, Product) => Unit = {
  //    insertAction = relationship match {
  //      case ManyToOne => insertAction.refManyToOne(ent, refAttr, ref)
  //      case _         => insertAction.refOneToMany(ent, refAttr, ref, reverseRefAttr.get)
  //    }
  //    (_: Product) => ()
  //  }
  //
  //  protected def addBackRef(backRef: String): Product => Unit = {
  //    insertAction = insertAction.backRef
  //    (_: Product) => ()
  //  }
  //
  //  protected def addOptEntity(
  //    attrs: List[Attr]
  //  ): (PS, Product) => Unit = {
  //    //    insertAction = insertAction.optEntity(attrs.head.ent)
  //    //    val resolveOptEntityData = getResolver(attrs)
  //    //    countValueAttrs(attrs) match {
  //    //      case 1 =>
  //    //        (tpl: Product) => {
  //    //          // Optional entity can only be first
  //    //          val optionalValue = tpl.productElement(0).asInstanceOf[Option[Any]]
  //    //          optionalValue.foreach { value =>
  //    //            resolveOptEntityData(Tuple1(value))
  //    //          }
  //    //        }
  //    //      case _ =>
  //    //        (tpl: Product) => {
  //    //          val optionalTpl = tpl.productElement(0).asInstanceOf[Option[Product]]
  //    //          optionalTpl.foreach { tpl =>
  //    //            resolveOptEntityData(tpl)
  //    //          }
  //    //        }
  //    //    }
  //    ???
  //  }
  //
  //  protected def addOptRef(
  //    tplIndex: Int,
  //    ent: String,
  //    refAttr: String,
  //    ref: String,
  //    optRefElements: List[Element]
  //  ): (PS, Product) => Unit = {
  //    //    val baseAction = insertAction
  //    //
  //    //    // Cache stable insert instance
  //    //    val insertOptRef = insertAction.optRef(ent, refAttr, ref)
  //    //    insertAction = insertOptRef
  //    //
  //    //    // Recursively resolve optional data
  //    //    val resolveOptionalRefData = getResolver(optRefElements)
  //    //
  //    //    insertAction = baseAction
  //    //
  //    //    countValueAttrs(optRefElements) match {
  //    //      case 1 =>
  //    //        (tpl: Product) => {
  //    //          val optionalValue = tpl.productElement(tplIndex).asInstanceOf[Option[Any]]
  //    //          insertOptRef.setOptionalDefined(optionalValue.isDefined)
  //    //          optionalValue.foreach { value =>
  //    //            resolveOptionalRefData(Tuple1(value))
  //    //          }
  //    //        }
  //    //      case _ =>
  //    //        (tpl: Product) => {
  //    //          val optionalTpl = tpl.productElement(tplIndex).asInstanceOf[Option[Product]]
  //    //          insertOptRef.setOptionalDefined(optionalTpl.isDefined)
  //    //          optionalTpl.foreach { tpl =>
  //    //            resolveOptionalRefData(tpl)
  //    //          }
  //    //        }
  //    //    }
  //    ???
  //  }
  //
  //  protected def addNested(
  //    tplIndex: Int,
  //    ent: String,
  //    refAttr: String,
  //    ref: String,
  //    relationship: Relationship,
  //    nestedElements: List[Element],
  //    fkCol: String
  //  ): (PS, Product) => Unit = {
  //    //    val fkLink       = insertAction.nestFk(ent, refAttr, ref, fkCol)
  //    //    val nestedInsert = fkLink.nested
  //    //    insertAction = nestedInsert
  //    //
  //    //    // Recursively resolve nested data
  //    //    val resolveNested = getResolver(nestedElements)
  //    //
  //    //    countValueAttrs(nestedElements) match {
  //    //      case 1 =>
  //    //        (tpl: Product) => {
  //    //          val nestedValues = tpl.productElement(tplIndex).asInstanceOf[Seq[Any]]
  //    //          fkLink.addNestedCount(nestedValues.length)
  //    //          nestedValues.foreach { nestedSingleValue =>
  //    //            nestedInsert.nextRow()
  //    //            resolveNested(Tuple1(nestedSingleValue))
  //    //          }
  //    //        }
  //    //      case _ =>
  //    //        (tpl: Product) => {
  //    //          val nestedTpls = tpl.productElement(tplIndex).asInstanceOf[Seq[Product]]
  //    //          fkLink.addNestedCount(nestedTpls.length)
  //    //          nestedTpls.foreach { nestedTpl =>
  //    //            nestedInsert.nextRow()
  //    //            resolveNested(nestedTpl)
  //    //          }
  //    //        }
  //    //    }
  //    ???
  //  }


  // Helpers -------------------------------------------------------------------


  //  private def addOptIterable[T, M[_] <: Iterable[?]](
  //    attr: String,
  //    optRef: Option[String],
  //    sqlTpe: String,
  //    tplIndex: Int,
  //    iterable2array: M[T] => Array[AnyRef],
  //  ): Product => Unit = {
  //    optRef.fold {
  //      val stableInsert = insertAction
  //      val paramIndex   = stableInsert.setCol(attr)
  //      (tpl: Product) => {
  //        tpl.productElement(tplIndex) match {
  //          case Some(iterable: Iterable[_]) =>
  //            val array = iterable2array(iterable.asInstanceOf[M[T]])
  //            stableInsert.addColSetter((ps: PS) => {
  //              val conn = ps.getConnection
  //              val arr  = conn.createArrayOf(sqlTpe, array)
  //              ps.setArray(paramIndex, arr)
  //            })
  //
  //          case None =>
  //            stableInsert.addColSetter((ps: PS) =>
  //              ps.setNull(paramIndex, java.sql.Types.NULL))
  //        }
  //      }
  //    } { ref =>
  //      val insertRefIds = insertAction.refIds(attr, ref)
  //      (tpl: Product) => {
  //        tpl.productElement(tplIndex) match {
  //          case Some(set: Iterable[_]) =>
  //            insertRefIds.addRefIds(set.asInstanceOf[Iterable[Long]])
  //          case _                      =>
  //            insertRefIds.addRefIds(Iterable.empty[Long])
  //        }
  //      }
  //    }
  //  }
}