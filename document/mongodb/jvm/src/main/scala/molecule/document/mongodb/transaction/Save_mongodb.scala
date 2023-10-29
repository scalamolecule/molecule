package molecule.document.mongodb.transaction

import java.util
import molecule.base.ast.Card
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.transaction.ResolveSave
import molecule.core.transaction.ops.SaveOps
import org.bson._

trait Save_mongodb
  extends Base_JVM_mongodb
    with SaveOps
    with MoleculeLogging { self: ResolveSave =>


  def getData(elements: List[Element]): Data = {
    initialNs = getInitialNs(elements)
    resolve(elements)
    val saveDocs = new util.ArrayList[BsonDocument]()
    saveDocs.add(saveDoc)
    (initialNs, saveDocs)
  }


  override protected def addOne[T](
    ns: String,
    attr: String,
    optBsonValue: Option[T],
    handleValue: T => Any,
    exts: List[String] = Nil
  ): Unit = {
    optBsonValue.fold {
      saveDoc.append(attr, new BsonNull())
    } { bsonValue =>
      saveDoc.append(attr, bsonValue.asInstanceOf[BsonValue])
    }
  }

  override protected def addSet[T](
    ns: String,
    attr: String,
    optSet: Option[Set[Any]],
    transformValue: T => Any,
    set2array: Set[Any] => Array[AnyRef],
    refNs: Option[String],
    exts: List[String] = Nil,
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    refNs.fold {
      optSet.fold {
        saveDoc.append(attr, new BsonNull())
      } {
        case set if set.nonEmpty =>
          val array: util.ArrayList[BsonValue] = new util.ArrayList[BsonValue]()
          set.map(bsonValue => array.add(bsonValue.asInstanceOf[BsonValue]))
          saveDoc.append(attr, new BsonArray(array))

        case _ => saveDoc.append(attr, new BsonNull())
      }
    } { refNs =>
      //      val curPath = if (paramIndexes.nonEmpty) curRefPath else List(ns)
      //      if (optSet.isEmpty || optSet.get.isEmpty) {
      //        addColSetter(curPath, (_: PS, _: IdsMap, _: RowIndex) => ())
      //      } else {
      //        val set       = optSet.get
      //        val refAttr   = attr
      //        val joinTable = ss(ns, refAttr, refNs)
      //        val joinPath  = curPath :+ joinTable
      //
      //        // join table with single row (treated as normal insert since there's only 1 join per row)
      //        val (id1, id2) = if (ns == refNs)
      //          (ss(ns, "1_id"), ss(refNs, "2_id"))
      //        else
      //          (ss(ns, "id"), ss(refNs, "id"))
      //        // When insertion order is reversed, this join table will be set after left and right has been inserted
      //        inserts = (joinPath, List((id1, ""), (id2, ""))) +: inserts
      //
      //        if (paramIndexes.isEmpty) {
      //          // If current namespace has no attributes, then add an empty row with
      //          // default null values (only to be referenced as the left side of the join table)
      //          val emptyRowSetter: Setter = (ps: PS, _: IdsMap, _: RowIndex) => ps.addBatch()
      //          addColSetter(curPath, emptyRowSetter)
      //          inserts = inserts :+ (curRefPath, List())
      //        }
      //
      //        // Join table setter
      //        val refIds2            = set.iterator.asInstanceOf[Iterator[Long]]
      //        val joinSetter: Setter = (ps: PS, idsMap: IdsMap, rowIndex: RowIndex) => {
      //          val refId1 = idsMap(curPath)(rowIndex)
      //          while (refIds2.hasNext) {
      //            val refId2 = refIds2.next()
      //            ps.setLong(1, refId1)
      //            ps.setLong(2, refId2)
      //            if (refIds2.hasNext)
      //              ps.addBatch()
      //          }
      //        }
      //        addColSetter(joinPath, joinSetter)
      //      }
      ???
    }
  }


  override protected def addRef(ns: String, refAttr: String, refNs: String, card: Card): Unit = {
    //    postResolvers = postResolvers :+ getRefResolver[Unit](ns, refAttr, refNs, card)
    ???
  }

  override protected def addBackRef(backRefNs: String): Unit = {
    //    curRefPath = curRefPath.dropRight(2)
    ???
  }

  override protected def handleRefNs(refNs: String): Unit = ()

}