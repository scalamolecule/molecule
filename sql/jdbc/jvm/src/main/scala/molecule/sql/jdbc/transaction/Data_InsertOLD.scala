//package molecule.sql.jdbc.transaction
//
//import java.net.URI
//import java.sql.{PreparedStatement => PS}
//import java.util.{Date, UUID}
//import molecule.base.ast.SchemaAST._
//import molecule.boilerplate.ast.Model._
//import molecule.boilerplate.util.MoleculeLogging
//import molecule.core.transaction.ops.InsertOps
//import molecule.core.transaction.{InsertExtraction, InsertResolvers_}
//import molecule.core.util.ModelUtils
//
//trait Data_InsertOLD
//  extends JdbcTxBase_JVM
//    with InsertOps
//    with JdbcDataType_JVM
//    with ModelUtils
//    with MoleculeLogging { self: InsertExtraction with InsertResolvers_ =>
//
//
//
//  private var firstRow   = true
//  private var insertIndex = 0
//
//  def getData(
//    nsMap: Map[String, MetaNs],
//    elements: List[Element],
//    tpls: Seq[Product],
//  ): Data = {
//    val (mainElements, _) = separateTxElements(elements)
//    val tpl2data          = getResolver(nsMap, mainElements)
//
//    // Loop rows of tuples
//    tpls.foreach { tpl =>
//      tpl2data(tpl)
//      addBatchSetters()
//      firstRow = false
//    }
//    (getInsertStmts, batchSetters)
//  }
//
//
//  private def getInsertStmts: List[String] = {
//    val valueInserts = columnLists.zipWithIndex.map { case (columns, i) =>
//      s"""INSERT INTO ${tables(i)} (
//         |  ${columns.mkString(",\n  ")}
//         |) VALUES (${columns.map(_ => "?").mkString(", ")})""".stripMargin
//    }.toList
////    valueInserts ++ joinInserts
//    joinInserts ++ valueInserts
//  }
//
//  private def addBatchSetters(): Unit = {
//    val valueSettersForRow = colSetterLists.map(colSetterList =>
//      (ps: PS, idLists: Array[List[Long]]) => {
//        // Set all column values for this insert/batch
//        colSetterList.foreach { colSetter =>
//          colSetter.apply(ps, idLists)
//        }
//        ps.addBatch()
//      }
//    )
//    // (added backwards since we run batches backwards and want rows to be inserted in order given)
//    batchSetters = joinSetters ++ valueSettersForRow ++ batchSetters
////    batchSetters = batchSetters ++ valueSettersForRow ++ joinSetters
////    batchSetters = batchSetters ++ joinSetters ++ valueSettersForRow
//
//    // Collect new column setters for next row
//    colSetterLists = Array(Array.empty[ValueSetter])
//
//  }
//
//  override protected def addV[T](
//    ns: String,
//    attr: String,
//    tplIndex: Int,
//    handleScalaValue: T => Any,
//  ): Product => Unit = {
//    if (firstRow) {
//      tables(insertIndex) = ns
//      // Add column/attr to current stmt
//      columnLists(insertIndex) = columnLists(insertIndex) :+ attr
//    }
//    val curInsertIndex  = insertIndex
//    val parameterIndex = columnLists(curInsertIndex).length
//    (tpl: Product) => {
//      //      println(s"tplArity: " + tplIndex + "  -  " + attr)
//      val scalaValue             = tpl.productElement(tplIndex).asInstanceOf[T]
//      val valueSetter            = handleScalaValue(scalaValue).asInstanceOf[(PS, Int) => Unit]
//      val colSetter: ValueSetter = (ps: PS, _) => {
//        valueSetter(ps, parameterIndex)
//        println(s"$ns.$attr value $tplIndex $parameterIndex: " + scalaValue)
//      }
//      colSetterLists(curInsertIndex) = colSetterLists(curInsertIndex) :+ colSetter
//    }
//  }
//
//  override protected def addRef(ns: String, refAttr: String, refNs: String, card: Card): Product => Unit = {
//    if (firstRow) {
//      if (card == CardOne) {
//        // Add ref attribute to current stmt
//        columnLists(insertIndex) = columnLists(insertIndex) :+ refAttr
//      }
//
//      // Start new stmt
//      tables = tables :+ refNs
//      columnLists = columnLists :+ Array.empty[String]
//    }
//
//    val curInsertIndex  = insertIndex
//    val nextInsertIndex  = curInsertIndex + 1
//    val parameterIndex = columnLists(curInsertIndex).length
//
//
//    val valueSetter = if (card == CardOne) {
//      (_: Product) => {
//        val colSetter: ValueSetter = (ps: PS, idLists: Array[List[Long]]) => {
//          //          if (idLists(curBatchIndex).nonEmpty)
//          println(idLists.toList)
//          println(s"$ns.$refAttr value $nextInsertIndex $parameterIndex: " + idLists(nextInsertIndex).head)
//          ps.setLong(parameterIndex, idLists(nextInsertIndex).head)
//        }
//        colSetterLists(curInsertIndex) = colSetterLists(curInsertIndex) :+ colSetter
//      }
//
//    } else {
//      val joinInsert =
//        s"""INSERT INTO ${ns}_${refAttr}_$refNs (
//           |  ${ns}_1_id,
//           |  ${refNs}_2_id
//           |) VALUES (?, ?)""".stripMargin
//      joinInserts = joinInserts :+ joinInsert
//
//      val joinSetter: ValueSetter = (ps: PS, idLists: Array[List[Long]]) => {
//        println("curInsertIndex: " + curInsertIndex)
//        val id1 = idLists(curInsertIndex + 1).head
//        val id2 = idLists(curInsertIndex).head
//        println("id1: " + id1)
//        println("id2: " + id2)
//        ps.setLong(1, idLists(curInsertIndex + 1).head)
//        ps.setLong(2, idLists(curInsertIndex).head)
//        ps.addBatch()
//      }
//      joinSetters = joinSetters :+ joinSetter
//      // No values set here, only in join table
//      (_: Product) => ()
//    }
//
//    // New stmt/insert after adding this ref
//    insertIndex += 1
//    colSetterLists = colSetterLists :+ Array.empty[ValueSetter]
//
//    valueSetter
//  }
//
//  override protected lazy val valueString     = (v: String) => (ps: PS, n: Int) => ps.setString(n, v)
//  override protected lazy val valueInt        = (v: Int) => (ps: PS, n: Int) => ps.setInt(n, v)
//  override protected lazy val valueLong       = (v: Long) => (ps: PS, n: Int) => ps.setLong(n, v)
//  override protected lazy val valueFloat      = (v: Float) => (ps: PS, n: Int) => ps.setFloat(n, v)
//  override protected lazy val valueDouble     = (v: Double) => (ps: PS, n: Int) => ps.setDouble(n, v)
//  override protected lazy val valueBoolean    = (v: Boolean) => (ps: PS, n: Int) => ps.setBoolean(n, v)
//  override protected lazy val valueBigInt     = (v: BigInt) => (ps: PS, n: Int) => ps.setBigDecimal(n, BigDecimal(v).bigDecimal)
//  override protected lazy val valueBigDecimal = (v: BigDecimal) => (ps: PS, n: Int) => ps.setBigDecimal(n, v.bigDecimal)
//  override protected lazy val valueDate       = (v: Date) => (ps: PS, n: Int) => ps.setDate(n, new java.sql.Date(v.getTime))
//  override protected lazy val valueUUID       = (v: UUID) => (ps: PS, n: Int) => ps.setString(n, v.toString)
//  override protected lazy val valueURI        = (v: URI) => (ps: PS, n: Int) => ps.setString(n, v.toString)
//  override protected lazy val valueByte       = (v: Byte) => (ps: PS, n: Int) => ps.setByte(n, v)
//  override protected lazy val valueShort      = (v: Short) => (ps: PS, n: Int) => ps.setShort(n, v)
//  override protected lazy val valueChar       = (v: Char) => (ps: PS, n: Int) => ps.setString(n, v.toString)
//
//  override protected def addComposite(
//    nsMap: Map[String, MetaNs],
//    outerTpl: Int,
//    tplIndex: Int,
//    compositeElements: List[Element]
//  ): Product => Unit = {
//    hasComposites = true
//    val composite2stmts = getResolver(nsMap, compositeElements, outerTpl)
//    // Start from initial entity id for each composite sub group
//    countValueAttrs(compositeElements) match {
//      case 1 => (tpl: Product) =>
//        e = e0
//        composite2stmts(Tuple1(tpl.productElement(tplIndex)))
//      case _ => (tpl: Product) =>
//        e = e0
//        composite2stmts(tpl.productElement(tplIndex).asInstanceOf[Product])
//    }
//  }
//
//  override protected def addNested(
//    nsMap: Map[String, MetaNs],
//    tplIndex: Int,
//    ns: String,
//    refAttr: String,
//    refNs: String,
//    nestedElements: List[Element]
//  ): Product => Unit = {
//    // Recursively resolve nested data
//    val nested2stmts = getResolver(nsMap, nestedElements)
//    countValueAttrs(nestedElements) match {
//      case 1 => // Nested arity-1 values
//        (tpl: Product) => {
//          val values       = tpl.productElement(tplIndex).asInstanceOf[Seq[Any]]
//          val nestedBaseId = e
//          values.foreach { value =>
//            e = nestedBaseId
//            val nestedTpl = Tuple1(value)
//            addRef(ns, refAttr, refNs, CardOne)(nestedTpl)
//            e0 = e
//            nested2stmts(nestedTpl)
//          }
//        }
//      case _ =>
//        (tpl: Product) => {
//          val nestedTpls   = tpl.productElement(tplIndex).asInstanceOf[Seq[Product]]
//          val nestedBaseId = e
//          nestedTpls.foreach { nestedTpl =>
//            e = nestedBaseId
//            addRef(ns, refAttr, refNs, CardOne)(nestedTpl)
//            e0 = e
//            nested2stmts(nestedTpl)
//          }
//        }
//    }
//  }
//
//
//  override protected def addOptV[T](
//    ns: String,
//    attr: String,
//    tplIndex: Int,
//    handleScalaValue: T => Any,
//  ): Product => Unit = {
//    val a = kw(ns, attr)
//    backRefs = backRefs + (ns -> e)
//    (tpl: Product) =>
//      tpl.productElement(tplIndex) match {
//        case Some(value) => appendStmt(add, e, a,
//          handleScalaValue(value.asInstanceOf[T]).asInstanceOf[AnyRef])
//        case _           => () // no statement to insert
//      }
//  }
//
//  override protected def addSet[T](
//    ns: String,
//    attr: String,
//    tplIndex: Int,
//    handleScalaValue: T => Any,
//  ): Product => Unit = {
//    val a = kw(ns, attr)
//    backRefs = backRefs + (ns -> e)
//    (tpl: Product) =>
//      tpl.productElement(tplIndex).asInstanceOf[Set[_]].foreach { value =>
//        appendStmt(add, e, a, handleScalaValue(value.asInstanceOf[T]).asInstanceOf[AnyRef])
//      }
//  }
//
//  override protected def addOptSet[T](
//    ns: String,
//    attr: String,
//    tplIndex: Int,
//    handleScalaValue: T => Any,
//  ): Product => Unit = {
//    val a = kw(ns, attr)
//    backRefs = backRefs + (ns -> e)
//    (tpl: Product) =>
//      tpl.productElement(tplIndex) match {
//        case Some(set: Set[_]) =>
//          set.foreach(value =>
//            appendStmt(add, e, a, handleScalaValue(value.asInstanceOf[T]).asInstanceOf[AnyRef])
//          )
//        case None              => () // no statement to insert
//      }
//  }
//
//
//  override protected def addBackRef(backRefNs: String): Product => Unit = {
//    //    batchIndex -= 1
//    (_: Product) => {
//
//      //      e = backRefs(backRefNs)
//
//    }
//  }
//}