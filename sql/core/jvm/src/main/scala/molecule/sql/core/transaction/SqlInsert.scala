package molecule.sql.core.transaction

import java.sql.{PreparedStatement => PS}
import molecule.base.ast._
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.transaction.ops.InsertOps
import molecule.core.transaction.{InsertResolvers_, ResolveInsert}
import molecule.core.util.ModelUtils
import molecule.sql.core.transaction.strategy.insert.{InsertAction, InsertNs}

trait SqlInsert
  extends SqlBase_JVM
    with InsertOps
    with SqlBaseOps
    with SqlDataType_JVM
    with ModelUtils
    with MoleculeLogging { self: ResolveInsert with InsertResolvers_ =>

  protected var insert: InsertAction = null

  def getInsertAction(elements: List[Element], tpls: Seq[Product]): InsertAction = {
    insert = InsertNs(sqlConn, getInitialNs(elements))
    val stableInsert = insert
    val resolveTpl   = getResolver(elements)
    tpls.foreach { tpl =>
      stableInsert.nextRow()
      println("------------------------------- " + tpl)
      resolveTpl(tpl)
    }
    insert.initialAction
  }


  override protected def addOne[T](
    ns: String,
    attr: String,
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String] = Nil
  ): Product => Unit = {
    val paramIndex   = insert.paramIndex(attr, "?", exts(2))
    val stableInsert = insert
    println(s"    $ns.$attr          $paramIndex ")
    (tpl: Product) => {
      val scalaValue  = tpl.productElement(tplIndex).asInstanceOf[T]
      val valueSetter = transformValue(scalaValue).asInstanceOf[(PS, Int) => Unit]
      println(s"$ns.$attr($paramIndex) = $scalaValue")
      stableInsert.add {
        (ps: PS) =>
          println(s"1--  $ns.$attr($paramIndex) = $scalaValue")
          valueSetter(ps, paramIndex)
      }
    }
  }

  override protected def addOneOpt[T](
    ns: String,
    attr: String,
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String] = Nil
  ): Product => Unit = {
    val paramIndex   = insert.paramIndex(attr, "?", exts(2))
    val stableInsert = insert
    (tpl: Product) => {
      tpl.productElement(tplIndex) match {
        case Some(scalaValue) =>
          val valueSetter = transformValue(scalaValue.asInstanceOf[T])
            .asInstanceOf[(PS, Int) => Unit]
          stableInsert.add((ps: PS) => valueSetter(ps, paramIndex))

        case None =>
          stableInsert.add((ps: PS) => ps.setNull(paramIndex, java.sql.Types.NULL))
      }
    }
  }

  override protected def addSet[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String] = Nil,
    set2array: Set[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit = {
    addIterable(ns, attr, optRefNs, exts(1), tplIndex, set2array)
  }

  override protected def addSetOpt[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String] = Nil,
    set2array: Set[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit = {
    addOptIterable(ns, attr, optRefNs, exts(1), tplIndex, set2array)
  }

  override protected def addSeq[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String] = Nil,
    seq2array: Seq[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit = {
    addIterable(ns, attr, optRefNs, exts(1), tplIndex, seq2array)
  }

  override protected def addSeqOpt[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String] = Nil,
    seq2array: Seq[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit = {
    addOptIterable(ns, attr, optRefNs, exts(1), tplIndex, seq2array)
  }

  override protected def addByteArray(
    ns: String,
    attr: String,
    tplIndex: Int,
  ): Product => Unit = {
    val paramIndex   = insert.paramIndex(attr)
    val stableInsert = insert
    (tpl: Product) => {
      tpl.productElement(tplIndex) match {
        case byteArray: Array[_] if byteArray.nonEmpty =>
          stableInsert.add((ps: PS) =>
            ps.setBytes(paramIndex, byteArray.asInstanceOf[Array[Byte]]))

        case Some(byteArray: Array[_]) if !byteArray.isEmpty =>
          stableInsert.add((ps: PS) =>
            ps.setBytes(paramIndex, byteArray.asInstanceOf[Array[Byte]]))

        case _ =>
          stableInsert.add((ps: PS) => ps.setNull(paramIndex, 0))
      }
    }
  }

  override protected def addMap[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit = {
    val paramIndex   = insert.paramIndex(attr)
    val stableInsert = insert
    (tpl: Product) => {
      tpl.productElement(tplIndex).asInstanceOf[Map[String, _]] match {
        case map if map.nonEmpty =>
          stableInsert.add((ps: PS) =>
            ps.setBytes(
              paramIndex,
              map2jsonByteArray(map.asInstanceOf[Map[String, T]], value2json)
            ))

        case _ =>
          stableInsert.add((ps: PS) =>
            ps.setNull(paramIndex, java.sql.Types.NULL))
      }
    }
  }

  override protected def addMapOpt[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit = {
    val paramIndex   = insert.paramIndex(attr)
    val stableInsert = insert
    (tpl: Product) => {
      tpl.productElement(tplIndex) match {
        case Some(map: Map[_, _]) if map.nonEmpty =>
          stableInsert.add((ps: PS) =>
            ps.setBytes(
              paramIndex,
              map2jsonByteArray(map.asInstanceOf[Map[String, T]], value2json)
            ))

        case _ =>
          stableInsert.add((ps: PS) =>
            ps.setNull(paramIndex, java.sql.Types.NULL))
      }
    }
  }

  override protected def addRef(
    ns: String,
    refAttr: String,
    refNs: String,
    card: Card,
  ): Product => Unit = {
    insert = card match {
      case CardOne => insert.refOne(ns, refAttr, refNs)
      case _       => insert.refMany(ns, refAttr, refNs)
    }
    (_: Product) => ()
  }

  override protected def addBackRef(backRefNs: String): Product => Unit = {
    insert = insert.backRef
    (_: Product) => ()
  }

  override protected def addOptRef(
    tplIndex: Int,
    ns: String,
    refAttr: String,
    refNs: String,
    nestedElements: List[Element]
  ): Product => Unit = {
    ???
  }

  override protected def addNested(
    tplIndex: Int,
    ns: String,
    refAttr: String,
    refNs: String,
    nestedElements: List[Element]
  ): Product => Unit = {
    insert = insert.nest(ns, refAttr, refNs)
    val stableInsert = insert

    // Recursively resolve nested data
    val resolveNested = getResolver(nestedElements)

    countValueAttrs(nestedElements) match {
      case 1 =>
        (tpl: Product) => {
          val nestedSingleValues = tpl.productElement(tplIndex).asInstanceOf[Seq[Any]]
          stableInsert.addNestedCount(nestedSingleValues.length)
          nestedSingleValues.foreach { nestedSingleValue =>
            println("++++++++++++++++++++++++++  " + nestedSingleValue)
            stableInsert.nextNestedRow()
            resolveNested(Tuple1(nestedSingleValue))
          }
        }
      case _ =>
        (tpl: Product) => {
          val nestedTpls = tpl.productElement(tplIndex).asInstanceOf[Seq[Product]]
          stableInsert.addNestedCount(nestedTpls.length)
          nestedTpls.foreach { nestedTpl =>
            stableInsert.nextNestedRow()
            resolveNested(nestedTpl)
          }
        }
    }
  }


  // Helpers -------------------------------------------------------------------

  private def addIterable[T, M[_] <: Iterable[_]](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    sqlTpe: String,
    tplIndex: Int,
    iterable2array: M[T] => Array[AnyRef],
  ): Product => Unit = {
    val stableInsert = insert
    optRefNs.fold {
      val paramIndex = stableInsert.paramIndex(attr)
      (tpl: Product) => {
        val array = iterable2array(tpl.productElement(tplIndex).asInstanceOf[M[T]])
        //        println(s"$ns.$attr($paramIndex) = $scalaValue")
        if (array.nonEmpty) {
          stableInsert.add((ps: PS) => {
            val conn = ps.getConnection
            val arr  = conn.createArrayOf(sqlTpe, array)
            ps.setArray(paramIndex, arr)
          })
        } else {
          //          println(s"$ns.$attr tpl.productArity: " + tpl.productArity)
          //          println(s"$ns.$attr                 : " + stableInsert.cols.length)
          stableInsert.add(
            (ps: PS) => {
              //              println(s"4--  $ns.$attr($paramIndex) = $scalaValue")
              ps.setNull(paramIndex, java.sql.Types.NULL)
            }
          )
        }
      }
    } { refNs =>
      (tpl: Product) => {
        val refIds = tpl.productElement(tplIndex).asInstanceOf[Iterable[Long]]
        stableInsert.addCardManyRefAttr(ns, attr, refNs, refIds.asInstanceOf[Set[Long]])
      }
    }
  }

  private def addOptIterable[T, M[_] <: Iterable[_]](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    sqlTpe: String,
    tplIndex: Int,
    iterable2array: M[T] => Array[AnyRef],
  ): Product => Unit = {
    val stableInsert = insert
    optRefNs.fold {
      val paramIndex = stableInsert.paramIndex(attr)
      (tpl: Product) => {
        tpl.productElement(tplIndex) match {
          case Some(iterable: Iterable[_]) =>
            val array = iterable2array(iterable.asInstanceOf[M[T]])
            stableInsert.add((ps: PS) => {
              val conn = ps.getConnection
              val arr  = conn.createArrayOf(sqlTpe, array)
              ps.setArray(paramIndex, arr)
            })

          case None =>
            stableInsert.add((ps: PS) =>
              ps.setNull(paramIndex, java.sql.Types.NULL))
        }
      }
    } { refNs =>
      (tpl: Product) => {
        tpl.productElement(tplIndex) match {
          case Some(set: Iterable[_]) if set.nonEmpty =>
            stableInsert.addCardManyRefAttr(
              ns, attr, refNs, set.asInstanceOf[Set[Long]]
            )
          case _                                      => ()
        }
      }
    }
  }
}