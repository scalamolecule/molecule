package molecule.document.mongodb.query

import com.mongodb.client.model.Filters
import molecule.base.ast._
import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import org.bson.{BsonArray, BsonDocument, BsonInt32, BsonString}
import scala.collection.mutable.ListBuffer


trait ResolveRef { self: MongoQueryBase =>

  protected def resolveRef(ref: Ref): Unit = {
    val Ref(_, refAttr, refNs, card, owner, _) = ref
    if (isNestedOpt && card == CardSet) {
      throw ModelError(
        "Only cardinality-one refs allowed in optional nested queries. Found: " + ref
      )
    }

    if (owner) {
      b.refPath = b.refPath ++ List(refAttr, refNs)
      b.pathDot = b.pathDot + refAttr + "."
      b.pathUnderscore = b.pathUnderscore + refAttr + "_"
      b.addFields(b.refPath) = Nil
    } else {
      //      // Referenced entities aggregate in a clean new branch
      //      val prevPath = if (branches.isEmpty) Nil else branches.last._1
      //
      //      println("Q  " + prevPath)
      //      println("   " + topRefPath)
      //
      //      val newPath  = prevPath ++ List(refAttr, refNs)
      //      val branchMap = branches.toMap
      //      b = branchMap.getOrElse(newPath, {
      //        val newBranch = new Branch
      //        branches += (prevPath ++ List(refAttr, refNs)) -> newBranch
      //        newBranch
      //      })


      val branchMap = branches.toMap
      val newPath   = topRefPath ++ List(refAttr, refNs)
      b = branchMap.getOrElse(newPath, {
        val newBranch = new Branch
        branches += newPath -> newBranch
        newBranch
      })
    }

    // Know if we get arrays (ref) or documents (embedded)
    b.refOwnerships(b.refPath) = owner

    val refDoc = new BsonDocument()
    projections(topRefPath) = projections(topRefPath).append(refAttr, refDoc)

    topRefPath = topRefPath ++ List(refAttr, refNs)
    projections(topRefPath) = refDoc
    //    topRefPath = topRefPath :+ refAttr

    //    println("  -1- projections2: " + projections2.toList.sortBy(_._1.length))

    // Continue from ref namespace
    val nss     = casts.last
    val curPath = nss.last
    nss += ((
      ListBuffer.empty[String].addAll(curPath._1.toList :+ refAttr),
      ListBuffer.empty[(String, Boolean, BsonDocument => Any)]
    ))
    //    printCasts("REF " + refAttr)
  }

  //  protected def resolveRefOLD(ref: Ref): Unit = {
  //    val Ref(_, refAttr, refNs, card, owner, _) = ref
  //    if (isNestedOpt && card == CardSet) {
  //      throw ModelError(
  //        "Only cardinality-one refs allowed in optional nested queries. Found: " + ref
  //      )
  //    }
  //
  //    if (!owner) {
  //      val lookup = new BsonDocument()
  //        .append("from", new BsonString(refNs))
  //        .append("localField", new BsonString(refAttr))
  //        .append("foreignField", new BsonString("_id"))
  //        .append("as", new BsonString(refAttr))
  //
  //      if (refPath.isEmpty) {
  //        lookups(List(refAttr)) = lookup
  //      } else {
  //        val parentLookup = lookups(refPath)
  //        val pipeline     = new BsonArray()
  //        pipeline.add(new BsonDocument().append("$lookup", lookup))
  //        parentLookup.append("pipeline", pipeline)
  //        lookups(refPath :+ refAttr) = lookup
  //      }
  //
  //      val refDoc = new BsonDocument()
  //      projections2(refPath) = projections2(refPath).append(refAttr, refDoc)
  //      projections2(refPath :+ refAttr) = refDoc
  //      //    println("  -1- projections2: " + projections2.toList.sortBy(_._1.length))
  //    }
  //
  //    refPath = refPath :+ refAttr
  //    pathDot = pathDot + refAttr + "."
  //    pathUnderscore = pathUnderscore + refAttr + "_"
  //    addFields(refPath) = Nil
  //    refOwnerships(refPath) = owner
  //
  //    // Continue from ref namespace
  //    val nss     = casts.last
  //    val curPath = nss.last
  //    nss += ((
  //      ListBuffer.empty[String].addAll(curPath._1.toList :+ refAttr),
  //      ListBuffer.empty[(String, Boolean, BsonDocument => Any)]
  //    ))
  //    //    printCasts("REF " + refAttr)
  //  }

  final protected def resolveBackRef(bRef: BackRef, prev: Element): Unit = {
    if (isNested || isNestedOpt) {
      val BackRef(backRef, _, _) = bRef
      prev match {
        case a: Attr => throw ModelError(
          s"Expected ref after backref _$backRef. " +
            s"Please add attribute ${a.ns}.${a.attr} to initial namespace ${a.ns} " +
            s"instead of after backref _$backRef."
        )
        case _       => ()
      }
    }

    // Back to previous namespace

    b.refPath = b.refPath.dropRight(2)
    b.pathDot = {
      val nss = b.pathDot.init.split('.').init
      if (nss.isEmpty) "" else nss.mkString(".") + "."
    }
    b.pathUnderscore = {
      val nss = b.pathUnderscore.init.split('_').init
      if (nss.isEmpty) "" else nss.mkString("_") + "_"
    }

    // go back to previous ref branch if it exists
    branches.toMap.get(b.refPath).foreach(branch => b = branch)

    topRefPath = topRefPath.drop(2)

    val nss     = casts.last
    val curPath = nss.last
    nss += ((
      ListBuffer.empty[String].addAll(curPath._1.toList.init),
      ListBuffer.empty[(String, Boolean, BsonDocument => Any)]
    ))
    //    printCasts("BACKREF")
  }

  protected def resolveNestedRef(ref: Ref, nestedElements: List[Element]): Unit = {
    level += 1
    isNested = true
    if (isNestedOpt) {
      noMixedNestedModes
    }
    validateRefNs(ref, nestedElements)
    val Ref(_, refAttr, _, _, _, _) = ref

    // No empty nested arrays when asking for mandatory nested data
    b.matches.add(Filters.ne(refAttr, new BsonArray()))

    b.refPath = b.refPath :+ refAttr
    b.pathDot = b.pathDot + refAttr + "."
    b.pathUnderscore = b.pathUnderscore + refAttr + "_"

    b.addFields(b.refPath) = Nil

    // Initiate nested namespace
    casts += ListBuffer((
      ListBuffer.empty[String].addAll(casts.last.last._1.toList :+ refAttr),
      ListBuffer.empty[(String, Boolean, BsonDocument => Any)]
    ))
    //    printCasts("NEST " + refAttr)
  }

  protected def resolveNestedOptRef(ref: Ref, nestedElements: List[Element]): Unit = {
    level += 1
    isNestedOpt = true
    if (isNested) {
      noMixedNestedModes
    }
    if (expectedFilterAttrs.nonEmpty) {
      throw ModelError("Filter attributes not allowed in optional nested queries.")
    }
    validateRefNs(ref, nestedElements)
    val Ref(_, refAttr, _, _, _, _) = ref

    b.refPath = b.refPath :+ refAttr
    b.pathDot = b.pathDot + refAttr + "."
    b.pathUnderscore = b.pathUnderscore + refAttr + "_"

    b.addFields(b.refPath) = Nil

    // Initiate (optional) nested namespace
    casts += ListBuffer(
      (
        ListBuffer.empty[String].addAll(casts.last.last._1.toList :+ refAttr),
        ListBuffer.empty[(String, Boolean, BsonDocument => Any)]
      )
    )
    //    printCasts("OPT NEST " + refAttr)
  }

  final private def validateRefNs(ref: Ref, nestedElements: List[Element]): Unit = {
    val refName  = ref.refAttr.capitalize
    val nestedNs = nestedElements.head match {
      case a: Attr => a.ns
      case r: Ref  => r.ns
      case other   => unexpectedElement(other)
    }
    if (ref.refNs != nestedNs) {
      throw ModelError(s"`$refName` can only nest to `${ref.refNs}`. Found: `$nestedNs`")
    }
  }
}
