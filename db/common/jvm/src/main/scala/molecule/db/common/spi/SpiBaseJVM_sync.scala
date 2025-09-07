package molecule.db.common.spi

import java.sql.{ResultSet, ResultSetMetaData, Statement, Types, PreparedStatement as PS}
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.concurrent.Future
import scala.util.control.NonFatal
import geny.Generator
import molecule.core.dataModel.{DataModel, Element}
import molecule.core.error.{InsertError, InsertErrors, ModelError, ValidationErrors}
import molecule.core.util.BaseHelpers
import molecule.db.common.action.*
import molecule.db.common.facade.JdbcConn_JVM
import molecule.db.common.javaSql.{PrepStmtImpl, ResultSetInterface as RS}
import molecule.db.common.marshalling.ConnProxy
import molecule.db.common.query.casting.strategy.{CastOptEntity, CastOptRefs, CastTuple}
import molecule.db.common.query.{Model2SqlQuery, SqlQueryBase, SqlQueryResolveCursor, SqlQueryResolveOffset}
import molecule.db.common.spi.{Conn, Renderer, Spi_sync, TxReport}
import molecule.db.common.transaction.plan.InsertEngine
import molecule.db.common.transaction.plan.render.PlanRenderer
import molecule.db.common.transaction.strategy.SqlAction
import molecule.db.common.transaction.strategy.delete.DeleteAction
import molecule.db.common.transaction.strategy.insert.InsertAction
import molecule.db.common.transaction.strategy.save.SaveAction
import molecule.db.common.transaction.strategy.update.UpdateAction
import molecule.db.common.transaction.*
import molecule.db.common.util.Executor.*
import molecule.db.common.util.FutureUtils
import molecule.db.common.validation.TxModelValidation
import molecule.db.common.validation.insert.InsertValidation

trait SpiBaseJVM_sync
  extends Spi_sync
    with CachedConnection
    with SpiHelpers
    with SqlUpdateSetValidator
    with Renderer
    with FutureUtils
    with BaseHelpers {


  // Query --------------------------------------------------------

  override def query_get[Tpl](query: Query[Tpl])(using conn0: Conn): List[Tpl] = {
    val conn = conn0.asInstanceOf[JdbcConn_JVM]
    if (query.printInspect)
      query_inspect(query)
    val cleanElements  = keywordsSuffixed(query.dataModel.elements, conn.proxy)
    val cleanDataModel = query.dataModel.copy(elements = cleanElements)
    val m2q            = getModel2SqlQuery(cleanElements)
    m2q.bindValues.addAll(query.bindValues)
    SqlQueryResolveOffset[Tpl](cleanDataModel, query.optLimit, None, m2q)
      .getListFromOffset_sync(using conn)._1
  }

  override def query_inspect[Tpl](q: Query[Tpl])(using conn: Conn): String = {
    inspectQuery("QUERY", q.dataModel, q.optLimit, None, conn.proxy)
  }


  override def queryOffset_get[Tpl](query: QueryOffset[Tpl])
                                   (using conn0: Conn): (List[Tpl], Int, Boolean) = {
    val conn = conn0.asInstanceOf[JdbcConn_JVM]
    if (query.printInspect)
      queryOffset_inspect(query)
    val cleanElements = keywordsSuffixed(query.dataModel.elements, conn.proxy)
    val queryClean    = query.copy(dataModel = query.dataModel.copy(elements = cleanElements))
    val m2q           = getModel2SqlQuery(cleanElements)
    m2q.bindValues.addAll(query.bindValues)
    SqlQueryResolveOffset[Tpl](queryClean.dataModel, queryClean.optLimit, Some(queryClean.offset), m2q)
      .getListFromOffset_sync(using conn)
  }

  override def queryOffset_inspect[Tpl](q: QueryOffset[Tpl])(using conn: Conn): String = {
    inspectQuery("QUERY (offset)", q.dataModel, q.optLimit, Some(q.offset), conn.proxy)
  }

  override def queryCursor_get[Tpl](query: QueryCursor[Tpl])
                                   (using conn0: Conn): (List[Tpl], String, Boolean) = {
    val conn = conn0.asInstanceOf[JdbcConn_JVM]
    if (query.printInspect)
      queryCursor_inspect(query)
    val cleanElements  = keywordsSuffixed(query.dataModel.elements, conn.proxy)
    val cleanDataModel = query.dataModel.copy(elements = cleanElements)
    val m2q            = getModel2SqlQuery(cleanElements)
    m2q.bindValues.addAll(query.bindValues)
    SqlQueryResolveCursor[Tpl](cleanDataModel, query.optLimit, Some(query.cursor), m2q)
      .getListFromCursor_sync(using conn)
  }

  override def queryCursor_inspect[Tpl](q: QueryCursor[Tpl])(using conn: Conn): String = {
    inspectQuery("QUERY (cursor)", q.dataModel, q.optLimit, None, conn.proxy)
  }


  protected def inspectQuery(
    label: String,
    dataModel: DataModel,
    optLimit: Option[Int],
    optOffset: Option[Int],
    proxy: ConnProxy
  ): String = {
    tryInspect("query", dataModel) {
      val elementsClean = keywordsSuffixed(dataModel.elements, proxy)
      val query         = getModel2SqlQuery(elementsClean)
        .getSqlQuery(Nil, optLimit, optOffset, Some(proxy))
      renderInspection(label, dataModel, query)
    }
  }


  // Simple geny Generator stream implementation.
  // Plugs in nicely with the Lihaoyi ecosystem.
  // See https://github.com/com-lihaoyi/geny
  override def query_stream[Tpl](
    q: Query[Tpl], chunkSize: Int
  )(using conn0: Conn): Generator[Tpl] = new Generator[Tpl] {
    // callback function
    def generate(handleTuple: Tpl => Generator.Action): Generator.Action = {
      if (q.printInspect)
        query_inspect(q)
      val (rs, rs2row)             = getResultSetAndRowResolver(q, conn0)
      var action: Generator.Action = Generator.Continue
      while (rs.next() && action == Generator.Continue) {
        action = handleTuple(rs2row(rs).asInstanceOf[Tpl])
      }
      action
    }
  }


  override def query_subscribe[Tpl](query: Query[Tpl], callback: List[Tpl] => Unit)
                                   (using conn0: Conn): Unit = {
    val conn           = conn0.asInstanceOf[JdbcConn_JVM]
    val elements       = keywordsSuffixed(query.dataModel.elements, conn.proxy)
    val cleanDataModel = query.dataModel.copy(elements = elements)
    conn.addCallback(query.dataModel, () =>
      callback {
        SqlQueryResolveOffset(cleanDataModel, query.optLimit, None, getModel2SqlQuery(elements))
          .getListFromOffset_sync(using conn)._1
      }
    )
  }

  override def query_unsubscribe[Tpl](query: Query[Tpl])(using conn: Conn): Unit = {
    conn.removeCallback(query.dataModel)
  }


  // Save --------------------------------------------------------

  override def save_transact(save: Save)(using conn0: Conn): TxReport = {
    val conn = conn0.asInstanceOf[JdbcConn_JVM]
    if (save.printInspect)
      save_inspect(save)
    val cleanElements  = keywordsSuffixed(save.dataModel.elements, conn.proxy)
    val cleanDataModel = save.dataModel.copy(elements = cleanElements)
    val saveClean      = save.copy(dataModel = cleanDataModel)
    val errors         = save_validate(save)
    if (errors.isEmpty) {
      val txReport = conn.transact_sync(save_getAction(saveClean, conn))
      await(conn.callback(cleanDataModel))
      txReport
    } else {
      throw ValidationErrors(errors)
    }
  }

  override def save_inspect(save: Save)(using conn0: Conn): String = {
    val conn = conn0.asInstanceOf[JdbcConn_JVM]
    tryInspect("save", save.dataModel) {
      val cleanElements  = keywordsSuffixed(save.dataModel.elements, conn.proxy)
      val cleanDataModel = save.dataModel.copy(elements = cleanElements)
      val saveClean      = save.copy(dataModel = cleanDataModel)
      renderInspectTx("SAVE", save.dataModel, save_getAction(saveClean, conn))
    }
  }

  // Implement for each sql database
  def save_getAction(save: Save, conn: JdbcConn_JVM): SaveAction


  override def save_validate(save: Save)(using conn: Conn): Map[String, Seq[String]] = {
    if (save.doValidate) {
      TxModelValidation(conn.proxy.metaDb, "save").validate(save.dataModel.elements)
    } else {
      Map.empty[String, Seq[String]]
    }
  }


  // Insert --------------------------------------------------------

  override def insert_transact(insert: Insert)(using conn0: Conn): TxReport = {
    val conn = conn0.asInstanceOf[JdbcConn_JVM]
    if (insert.printInspect)
      insert_inspect(insert)
    val cleanElements  = keywordsSuffixed(insert.dataModel.elements, conn.proxy)
    val cleanDataModel = insert.dataModel.copy(elements = cleanElements)
    val insertClean    = insert.copy(dataModel = cleanDataModel)
    val errors         = insert_validate(insert) // validate original elements against meta model

    if (errors.nonEmpty)
      throw InsertErrors(errors)

    if (insertClean.tpls.isEmpty)
      return TxReport(Nil)

    val firstEntity      = getInitialEntity(cleanElements)
    val firstRefPath     = List(firstEntity)
    val firstTableInsert = TableInsert(firstRefPath)
    val firstPartition   = Partition(Nil, 0, Tpl)
    val sqlOps           = new ResolveInsert with SqlInsert {}

    // Can we only know this after all entities in a partition have been resolved?
    // Avoid cyclic dependencies?
    val partitions = sqlOps.resolve(cleanElements, 1, 0, List(firstPartition), firstTableInsert)

    // Partitioned tuple data
    val dataPartitions: Iterator[DataPartition] = tuplePartitions(insert.tpls, partitions)

    // Cache generated ids for each table insertion
    // Table is identified by ref path so that foreign keys know from where to pick parent ids
    val idss = mutable.Map.empty[List[String], List[Long]]

    partitions.foreach { case p@Partition(tableInserts, tupleIndex, dataKind) =>
      val dataPartition = dataPartitions.next()
      val tpls          = dataPartition.tuples

      sortTableInserts(tableInserts).foreach { tableInsert =>
        //        println(tableInsert)
        val ps = conn.sqlConn.prepareStatement(tableInsert.sql, Statement.RETURN_GENERATED_KEYS)
        if (tableInsert.foreignKeys.isEmpty) {
          tpls.foreach { tpl =>
            tableInsert.colSetters.foreach(_(ps, tpl))
            ps.addBatch()
          }
        } else {
          val firstParamIndex        = tableInsert.colSetters.length + 1
          // RefPaths present in this partition (used to detect intra-partition dependencies)
          val samePartitionRefPaths  = tableInserts.map(_.refPath).toSet

          // Build one setter per FK column. For each FK, use an iterator of parent IDs that
          // aligns with the number of current rows. If the referenced table is in the SAME
          // partition, we have a 1:1 mapping with current tuples (no replication).
          // If the referenced table is from a PREVIOUS partition, replicate each parent ID
          // according to how many children it has (derived from childCounts of the parent level).
          val foreignKeySetters = tableInsert.foreignKeys.zipWithIndex.map { case ((refAttr, refPath), i) =>
            val paramIndex = firstParamIndex + i
            val parentIds  = idss(refPath)
            val fks =
              if (samePartitionRefPaths.contains(refPath)) {
                // Intra-partition dependency: IDs already align 1:1 with current rows
                parentIds
              } else {
                // Cross-partition dependency: replicate per childCounts from parent level
                dataPartition.childCounts.zip(parentIds).flatMap {
                  case (count, id) => List.fill(count)(id)
                }
              }

            println(s"******* parentIds: $parentIds, childCounts: ${dataPartition.childCounts}, fks: $fks")

            val fkIterator = fks.iterator
            (ps: PS) =>
              val fk = fkIterator.next()
              // Debug aid: shows which parent IDs feed this FK column
              println(s"refAttr: $refAttr, refPath: $refPath, paramIndex: $paramIndex, foreignKeys: $parentIds, fk: $fk")
              ps.setLong(paramIndex, fk)
          }

          tpls.foreach { tpl =>
            tableInsert.colSetters.foreach(_(ps, tpl))
            foreignKeySetters.foreach(_(ps))
            ps.addBatch()
          }
        }
        ps.executeBatch()

        // Get generated ids
        val resultSet = ps.getGeneratedKeys
        val ids       = ListBuffer.empty[Long]
        while (resultSet.next()) {
          ids += resultSet.getLong(1)
        }
        ps.close()
        idss += tableInsert.refPath -> ids.toList
      }
    }
    val txReport = TxReport(idss(firstRefPath))
    await(conn.callback(cleanDataModel))
    txReport
  }

  private case class DataPartition(tuples: Seq[Product], childCounts: Vector[Int]) {
    override def toString: String = {
      s"""........................................
         |DataPartition(
         |  tuples     : $tuples,
         |  childCounts: $childCounts
         |)
         |""".stripMargin
    }
  }

  private def tuplePartitions(topLevel: Seq[Product], partitions: Seq[Partition]) = new Iterator[DataPartition] {
    var curTuples      = topLevel
    var childCounts    = Vector.empty[Int]
    var partitionIndex = 0

    def hasNext: Boolean = partitionIndex < partitions.length

    def next(): DataPartition = {
      val partition = partitions(partitionIndex)

      println(
        s"""
           |############################################################
           |partitionIndex: $partitionIndex
           |curTuples     : $curTuples
           |$partition""".stripMargin)

      partitionIndex += 1
      val counts        = new Array[Int](curTuples.length)
      val nextTuples    = new ListBuffer[Product]
      val it            = curTuples.iterator
      var i             = 0
      val next          = partition.dataKind match {
        case HasNestedTuples =>
          while (it.hasNext) {
            val tpl          = it.next()
            val nestedTuples = tpl.productElement(partition.tupleIndex).asInstanceOf[Seq[Product]]
            counts(i) = nestedTuples.size
            nextTuples ++= nestedTuples
            i += 1
          }
          nextTuples.toSeq

        case HasNestedValues =>
          while (it.hasNext) {
            val tpl          = it.next()
            val nestedValues = tpl.productElement(partition.tupleIndex).asInstanceOf[Seq[Any]]
            counts(i) = nestedValues.size
            nestedValues.foreach(v => nextTuples += Tuple1(v))
            i += 1
          }
          nextTuples.toSeq

        case HasOptTuple =>
          while (it.hasNext) {
            val optTuple = it.next()
            optTuple.productElement(partition.tupleIndex).asInstanceOf[Option[Product]].fold {
              counts(i) = 0
            } { tpl =>
              counts(i) = 1
              nextTuples += tpl
            }
            i += 1
          }
          nextTuples.toSeq

        case HasOptValue =>
          while (it.hasNext) {
            val optValue = it.next()
            optValue.productElement(partition.tupleIndex).asInstanceOf[Option[Any]].fold {
              counts(i) = 0
            } { v =>
              counts(i) = 1
              nextTuples += Tuple1(v)
            }
            i += 1
          }
          nextTuples.toSeq

        case Tpl =>
          while (it.hasNext) {
            val tpl = it.next()
            counts(i) = 1
            i += 1
          }
          curTuples
      }
      val dataPartition = DataPartition(curTuples, childCounts)

      println(dataPartition)

      curTuples = next
      childCounts = counts.toVector
      dataPartition
    }
  }

  /* Topological sort of table inserts within a single partition (Kahn's algorithm).
   *
   * We need to run INSERTs so that when a table has foreign keys to another table,
   * the referenced table is inserted first (so its generated IDs exist).
   *
   * Node     = refPath (List[String]) identifying a TableInsert in this partition.
   * Edge     = dependsOnRefPath -> refPath if the current TableInsert has a foreign key
   *            to dependsOnRefPath. This enforces that parents are inserted before children.
   * Shape    = Intended to be a DAG (Directed Acyclic Graph) per partition; if a cycle exists,
   *            there is no valid insert order and we fail with a clear error.
   * Props    = Multiple disconnected components supported; stable order among nodes that
   *            are simultaneously available (we preserve the original input order).
   * Cost     = O(V + E) time; low allocation via mutable Map/ListBuffer/Queue.
   * Ref      = https://en.wikipedia.org/wiki/Topological_sorting#Kahn's_algorithm
   */
  def sortTableInserts(tableInserts: List[TableInsert]): List[TableInsert] = {
    // Collect the nodes (refPaths) present at this partition and a lookup to the original inserts
    val refPaths             = tableInserts.map(_.refPath).toSet
    val tableInsertByRefPath = tableInserts.map(tableInsert => tableInsert.refPath -> tableInsert).toMap

    // In-degrees: number of prerequisites per node (incoming edges)
    // Adjacency: for each node, the children that depend on it (outgoing edges)
    val inDegrees = mutable.Map.from(tableInserts.map(tableInsert => tableInsert.refPath -> 0))
    val adjacency = mutable.Map.empty[List[String], mutable.ListBuffer[List[String]]]

    // Build the dependency graph (within this partition only)
    tableInserts.foreach { tableInsert =>
      tableInsert.foreignKeys.foreach { case (_, dependsOnRefPath) =>
        // We only add edges for dependencies that are present in the same partition
        if (refPaths.contains(dependsOnRefPath)) {
          // Get/create the children list for the parent dependsOnRefPath and append current refPath.
          // getOrElseUpdate returns the existing ListBuffer for the key or inserts a new one if absent.
          val buffer = adjacency.getOrElseUpdate(dependsOnRefPath, mutable.ListBuffer.empty[List[String]])
          buffer += tableInsert.refPath
          // Current node has one more prerequisite (incoming edge)
          inDegrees.update(tableInsert.refPath, inDegrees(tableInsert.refPath) + 1)
        }
      }
    }

    // Start with all nodes that have no prerequisites (in-degree 0), in original order for stability
    val zeroInDegreeRefPaths = tableInserts.map(_.refPath).filter(refPath => inDegrees(refPath) == 0)
    val queue                = mutable.Queue[List[String]](zeroInDegreeRefPaths: _*)

    // Kahn's algorithm: repeatedly remove nodes with in-degree 0 and "release" their children
    val orderedInserts = mutable.ListBuffer.empty[TableInsert]
    while (queue.nonEmpty) {
      val refPath = queue.dequeue()
      orderedInserts += tableInsertByRefPath(refPath)

      // "Remove" outgoing edges by decrementing children's in-degree;
      // enqueue children that become ready (in-degree drops to 0)
      adjacency.getOrElse(refPath, mutable.ListBuffer.empty[List[String]]).foreach { nextRefPath =>
        inDegrees.update(nextRefPath, inDegrees(nextRefPath) - 1)
        if (inDegrees(nextRefPath) == 0) queue.enqueue(nextRefPath)
      }
    }

    // If not all nodes were produced, there must be a cycle (no valid topological order)
    if (orderedInserts.size != tableInserts.size) {
      val cyclic = inDegrees.collect {
        case (refPath, degree) if degree > 0 => refPath.mkString(".")
      }.toList.sorted
      throw ModelError(
        "Circular foreign key references detected among table inserts in the same partition: " +
          cyclic.mkString(", ")
      )
    }
    orderedInserts.toList
  }

  /* Produce tuples for each nested level starting from the top-level tuples,
   * and compute per-parent child counts for the next level.
   *
   * Level 0 = the input tuples as-is.
   * Level N+1 = all nested tuples found in the last element of each tuple from level N.
   *
   * Assumptions:
   * - The last product element is either a Seq[Product] holding children or not present/not a Seq (leaf).
   * - We do not strip the last element; tuples are kept intact.
   * - Stops when a level has no children.
   *
   * This is iterative (not recursive) to minimize allocations and stack usage.
   */

  private def tuplesByLevel(topLevelTuples: Seq[Product]): Iterator[DataPartition] = new Iterator[DataPartition] {
    private var currentLevel: Seq[Product] = topLevelTuples
    private var exhausted   : Boolean      = false

    override def hasNext: Boolean = !exhausted

    override def next(): DataPartition = {
      if (exhausted) throw new NoSuchElementException("No more levels")
      val out = currentLevel

      // Collect children from the last element of each tuple in the current level.
      // Also gather how many children each parent has to drive FK id replication at the next level.
      val childrenBuffer = ListBuffer.empty[Product]
      val countsBuffer   = ListBuffer.empty[Int]
      var i              = 0
      val n              = currentLevel.length
      while (i < n) {
        val tpl       = currentLevel(i)
        val lastIndex = tpl.productArity - 1
        var count     = 0
        if (lastIndex >= 0) {
          tpl.productElement(lastIndex) match {
            case seq: Seq[?] =>
              //              val s = seq.asInstanceOf[Seq[Product]]
              val s = seq.asInstanceOf[Seq[Any]]
              count = s.size
              //              s.foreach(childrenBuffer += _)
              s.foreach(childrenBuffer += Tuple1(_))

            case Some(seq: Seq[?]) =>
              val s = seq.asInstanceOf[Seq[Product]]
              count = s.size
              s.foreach(childrenBuffer += _)

            case _ =>
              () // no children for this tuple
          }
        }
        countsBuffer += count
        i += 1
      }

      val childCounts = countsBuffer.toVector

      if (childrenBuffer.isEmpty) {
        exhausted = true
      } else {
        currentLevel = childrenBuffer.result()
      }
      DataPartition(out, childCounts)
    }
  }


  def insert_transact2(insert: Insert)(using conn0: Conn): TxReport = {
    val conn           = conn0.asInstanceOf[JdbcConn_JVM]
    //    if (insert.printInspect)
    //      insert_inspect(insert)
    val cleanElements  = keywordsSuffixed(insert.dataModel.elements, conn.proxy)
    val cleanDataModel = insert.dataModel.copy(elements = cleanElements)
    val insertClean    = insert.copy(dataModel = cleanDataModel)
    val errors         = insert_validate(insert) // validate original elements against meta model
    if (errors.isEmpty) {
      if (insertClean.tpls.isEmpty) {
        TxReport(Nil)
      } else {
        //        // Choose engine: planned path for simple linear scalar inserts; otherwise legacy graph
        //        val txReport = if (isPlannableLinearScalar(cleanElements)) {
        //          // Use the new planner/executor path via db-specific SqlOps provider
        //          val engine = getInsertEngine(conn)
        //          if (insert.printInspect) {
        //            // todo: use renderInspectTx instead
        //            println(PlanRenderer.render(cleanDataModel))
        //          }
        //          conn.atomicTransaction(() => engine.run(cleanElements, insert.tpls))
        //        } else {
        //          // Fallback to legacy InsertAction graph
        //          conn.transact_sync(insert_getAction(insertClean, conn))
        //        }
        // Use the new planner/executor path via db-specific SqlOps provider
        val engine = getInsertEngine(conn)
        if (insert.printInspect) {
          // todo: use renderInspectTx instead
          println(PlanRenderer.render(cleanDataModel))
        }
        val txReport = conn.atomicTransaction(() => engine.run(cleanElements, insert.tpls))
        await(conn.callback(cleanDataModel))
        txReport
      }
    } else {
      throw InsertErrors(errors)
    }
  }

  // Implement for each sql database
  def getInsertEngine(conn: JdbcConn_JVM): InsertEngine


  override def insert_inspect(insert: Insert)(using conn: Conn): String = {
    tryInspect("insert", insert.dataModel) {
      val jdbcConn       = conn.asInstanceOf[JdbcConn_JVM]
      val cleanElements  = keywordsSuffixed(insert.dataModel.elements, conn.proxy)
      val cleanDataModel = insert.dataModel.copy(elements = cleanElements)
      val insertClean    = insert.copy(dataModel = cleanDataModel)

      // Choose which action to render: planned plan (if supported) or legacy
      //      val action: SqlAction =
      //        if (isPlannableLinearScalar(cleanElements)) {
      //          insert_getActionNEW(cleanElements, insert.tpls, jdbcConn)
      //        } else {
      //          insert_getAction(insertClean, jdbcConn)
      //        }
      val action: SqlAction = insert_getAction(insertClean, jdbcConn)

      renderInspectTx("INSERT", insert.dataModel, action, insert.tpls)
    }
  }

  // Implement for each sql database
  def insert_getAction(insert: Insert, conn: JdbcConn_JVM): InsertAction
  //  def insert_getTableInserts(insert: Insert): List[TableInsert]


  // Simple capability check for planned engine (Phase 1):
  // - Allow AttrOne (mandatory/optional) scalars and simple Ref/BackRef links
  // - Disallow optional refs/entities, nested data and collections (to be added later)
  private def isPlannableLinearScalar(elements: List[Element]): Boolean = {
    elements.forall {
      case a: molecule.core.dataModel.Attr    =>
        a match {
          case _: molecule.core.dataModel.AttrOneMan => true
          case _: molecule.core.dataModel.AttrOneOpt => true
          case _                                     => false
        }
      case _: molecule.core.dataModel.Ref     => true
      case _: molecule.core.dataModel.BackRef => true
      case _                                  => false
    }
  }

  override def insert_validate(insert: Insert)(using conn: Conn): Seq[(Int, Seq[InsertError])] = {
    if (insert.doValidate) {
      InsertValidation.validate(conn, insert.dataModel.elements, insert.tpls)
    } else {
      Seq.empty[(Int, Seq[InsertError])]
    }
  }


  // Update --------------------------------------------------------

  override def update_transact(update: Update)(using conn0: Conn): TxReport = {
    val conn = conn0.asInstanceOf[JdbcConn_JVM]
    if (update.printInspect)
      update_inspect(update)
    val cleanElements  = keywordsSuffixed(update.dataModel.elements, conn.proxy)
    val cleanDataModel = update.dataModel.copy(elements = cleanElements)
    val updateClean    = update.copy(dataModel = cleanDataModel)
    val errors         = update_validate(update) // validate original elements against meta model
    if (errors.isEmpty) {
      val action   = update_getAction(updateClean, conn)
      val txReport = conn.transact_sync(action)
      await(conn.callback(cleanDataModel))
      txReport
    } else {
      throw ValidationErrors(errors)
    }
  }

  override def update_inspect(update: Update)(using conn0: Conn): String = {
    val conn   = conn0.asInstanceOf[JdbcConn_JVM]
    val action = if (update.isUpsert) "UPSERT" else "UPDATE"
    tryInspect(action, update.dataModel) {
      val cleanElements  = keywordsSuffixed(update.dataModel.elements, conn.proxy)
      val cleanDataModel = update.dataModel.copy(elements = cleanElements)
      val updateClean    = update.copy(dataModel = cleanDataModel)
      renderInspectTx(action, update.dataModel, update_getAction(updateClean, conn))
    }
  }

  // Implement for each sql database
  def update_getAction(update: Update, conn: JdbcConn_JVM): UpdateAction


  override def update_validate(
    update: Update
  )(using conn0: Conn): Map[String, Seq[String]] = {
    if (update.doValidate) {
      val conn            = conn0.asInstanceOf[JdbcConn_JVM]
      val query2resultSet = (query: String) => {
        val ps = conn.sqlConn.prepareStatement(
          query, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY
        )
        conn.resultSet(ps.executeQuery())
      }
      validateUpdateSet(conn.proxy, update.dataModel.elements, query2resultSet)
    } else {
      Map.empty[String, Seq[String]]
    }
  }

  def validateUpdateSet(
    proxy: ConnProxy,
    elements: List[Element],
    query2resultSet: String => RS
  ): Map[String, Seq[String]]


  // Delete --------------------------------------------------------

  override def delete_transact(delete: Delete)(using conn0: Conn): TxReport = {
    val conn = conn0.asInstanceOf[JdbcConn_JVM]
    if (delete.printInspect)
      delete_inspect(delete)
    val cleanElements  = keywordsSuffixed(delete.dataModel.elements, conn.proxy)
    val cleanDataModel = delete.dataModel.copy(elements = cleanElements)
    val deleteClean    = delete.copy(dataModel = cleanDataModel)
    val action         = delete_getAction(deleteClean, conn)
    val txReport       = conn.transact_sync(action)
    await(conn.callback(cleanDataModel, true))
    txReport
  }

  override def delete_inspect(delete: Delete)(using conn0: Conn): String = {
    val conn = conn0.asInstanceOf[JdbcConn_JVM]
    tryInspect("delete", delete.dataModel) {
      val cleanElements  = keywordsSuffixed(delete.dataModel.elements, conn.proxy)
      val cleanDataModel = delete.dataModel.copy(elements = cleanElements)
      val deleteClean    = delete.copy(dataModel = cleanDataModel)
      renderInspectTx("DELETE", delete.dataModel, delete_getAction(deleteClean, conn))
    }
  }

  def delete_getAction(delete: Delete, conn: JdbcConn_JVM): DeleteAction = ???


  // Inspect --------------------------------------------------------

  private def tryInspect(action: String, dataModel: DataModel)
                        (body: => String): String = try {
    body
  } catch {
    case NonFatal(e) =>
      println(
        s"""
           |------------------ Error inspecting $action -----------------------
           |$dataModel""".stripMargin)
      throw e
  }

  private def renderInspectTx(
    label: String,
    dataModel: DataModel,
    action: SqlAction,
    tpls: Seq[Product] = Nil
  ): String = {
    renderInspection(label, dataModel, action.toString, tpls.mkString("\n"))
  }


  // Fallback --------------------------------------

  override def fallback_rawTransact(
    stmt: String,
    doPrint: Boolean = false
  )(using conn0: Conn): TxReport = {
    val conn  = conn0.asInstanceOf[JdbcConn_JVM]
    val debug = if (doPrint) (s: String) => println(s) else (_: String) => ()
    debug("\n=============================================================================")
    debug(stmt)

    val ps = conn.sqlConn.prepareStatement(
      stmt, Statement.RETURN_GENERATED_KEYS
    )
    ps.execute()

    val resultSet = ps.getGeneratedKeys // is empty if no nested data
    var ids       = List.empty[Long]
    while (resultSet.next()) {
      ids = ids :+ resultSet.getLong(1)
    }
    ps.close()

    debug("---------------")
    debug("Ids: " + ids)
    TxReport(ids)
  }

  override def fallback_rawQuery(
    query: String,
    debug: Boolean = false,
  )(using conn: Conn): List[List[Any]] = {
    val c            = conn.asInstanceOf[JdbcConn_JVM].sqlConn
    val statement    = c.createStatement()
    val resultSet    = statement.executeQuery(query)
    val metaData     = resultSet.getMetaData
    val columnsCount = metaData.getColumnCount
    val rows         = ListBuffer.empty[List[Any]]
    val row          = ListBuffer.empty[Any]
    val types        = new Array[String](columnsCount)
    while (resultSet.next()) {
      var n = 1
      row.clear()
      while (n <= columnsCount) {
        resultSet.getObject(n) match {
          case null => row += null
          case v    =>
            row += v
            types(n - 1) = v.getClass.toString
        }
        n += 1
      }
      rows += row.toList
    }
    if (debug) {
      renderRawQueryData(query, rows, types, metaData)
    }
    resultSet.close()
    rows.toList
  }


  // Util --------------------------------------

  def getModel2SqlQuery(elements: List[Element]): Model2SqlQuery & SqlQueryBase


  def getResultSetAndRowResolver[Tpl](q0: Query[Tpl], conn0: Conn): (RS, RS => Any) = {
    val conn          = conn0.asInstanceOf[JdbcConn_JVM]
    val cleanElements = keywordsSuffixed(q0.dataModel.elements, conn.proxy)
    val queryClean    = q0.dataModel.copy(elements = cleanElements)
    val m2q           = getModel2SqlQuery(queryClean.elements)
    val castStrategy  = m2q.castStrategy match {
      case c: CastTuple     => c
      case c: CastOptRefs   => c
      case c: CastOptEntity => c
      case _                => throw ModelError("Nested data not allowed for streaming.")
    }
    val query         = m2q.getSqlQuery(Nil, None, None, Some(conn.proxy))
    val ps            = conn.queryStmt(query)
    val ps1           = new PrepStmtImpl(ps)
    val inputs        = m2q.binders.toList
    inputs.foreach(_(ps1)) // set input values corresponding to '?' in queries
    (conn.resultSet(ps.executeQuery()), castStrategy.rs2row)
  }

  private def renderRawQueryData(
    query: String,
    rows: ListBuffer[List[Any]],
    types: Array[String],
    metaData: ResultSetMetaData
  ): Unit = {
    println("\n=============================================================================")
    println(query.trim)
    val max      = 10
    val showRows = rows.length - max match {
      case 1          => rows.take(max) :+ "1 more row..."
      case n if n > 1 => rows.take(max) :+ s"$n more rows..."
      case _          => rows
    }

    val (col, tpe, dbTpe) = ("Column", "Raw type", "Db type")
    var maxCol            = col.length
    var maxTpe            = tpe.length
    var maxDbTpe          = dbTpe.length
    val sep               = 4

    val typeData = types.zipWithIndex.map {
      case (null, i) =>
        val col   = metaData.getColumnName(i + 1)
        val dbTpe = metaData.getColumnTypeName(i + 1)
        (col, "null", dbTpe)

      case (tpe, i) =>
        val col    = metaData.getColumnName(i + 1)
        val dbType = metaData.getColumnTypeName(i + 1)
        maxCol = maxCol.max(col.length)
        maxTpe = maxTpe.max(tpe.length)
        maxDbTpe = maxDbTpe.max(dbType.length)
        (col, tpe, dbType)
    }

    def tpeLine(col: String, tpe: String, dbTpe: String) = {
      println(col + padS(maxCol + sep, col) + tpe + padS(maxTpe + sep, tpe) + dbTpe)
    }

    println("\n" + showRows.mkString("List(\n  ", ",\n  ", "\n)\n"))
    tpeLine(col, tpe, dbTpe)
    println("-" * (maxCol + sep + maxTpe + sep + maxDbTpe))
    typeData.foreach {
      case (col, tpe, dbTpe) => tpeLine(col, tpe, dbTpe)
    }
  }
}