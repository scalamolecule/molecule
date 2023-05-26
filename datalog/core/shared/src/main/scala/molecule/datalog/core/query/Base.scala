package molecule.datalog.core.query

import java.util.{Iterator => jIterator, List => jList}
import molecule.base.error._
import molecule.base.util.BaseHelpers
import molecule.boilerplate.ast.Model._
import molecule.core.query.Model2Query
import molecule.core.util.JavaConversions
import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer


trait Base[Tpl] extends BaseHelpers with JavaConversions { self: Model2Query =>

  // Datomic row type
  type Row = jList[AnyRef]

  // Datomic variable (ex ?a)
  type Var = String

  // Datomic attribute name (ex :Person/name)
  type Att = String

  // Query clause optimization weights
  final protected val wGround         = 1
  final protected val wEqOne          = 2
  final protected val wEqMany         = 3
  final protected val wRange          = 4
  final protected val wNeqOne         = 5
  final protected val wFulltextSearch = 6
  final protected val wClauseOne      = 7
  final protected val wClauseMany     = 8
  final protected val wClause         = 9

  // Pre-query only
  final protected var preFind  = "?a"
  final protected val preIn    = new ArrayBuffer[String]
  final protected val preWhere = new ArrayBuffer[(String, Int)]
  final protected val preRules = new ArrayBuffer[String]

  // Main query
  final protected var isFree        = false
  final protected var isNested      = false
  final protected var isNestedOpt   = false
  final protected var isComposite   = false
  final protected var isTxData  = false
  final protected var isTxComposite = false

  final protected val nestedIds    = new ArrayBuffer[String]
  final protected val nestedOptIds = new ArrayBuffer[String]
  final protected val find         = new ArrayBuffer[String]
  final protected val widh         = new ArrayBuffer[String]
  final protected val in           = new ArrayBuffer[String]
  final protected val where        = new ArrayBuffer[(String, Int)]
  final protected val rules        = new ArrayBuffer[String]

  // In variables and where clauses not shared with pre-query. To be added lastly to main query
  final protected val inPost    = new ArrayBuffer[String]
  final protected val wherePost = new ArrayBuffer[(String, Int)]

  // Input args and cast lambdas
  final protected val preArgs  = new ArrayBuffer[AnyRef]
  final protected val args     = new ArrayBuffer[AnyRef]
  final protected var castss   = List(List.empty[AnyRef => AnyRef])
  final protected var aritiess = List(List.empty[List[Int]])

  // Sorting
  final protected var sortss    = List(List.empty[(Int, Int => (Row, Row) => Int)])
  final protected var attrIndex = -1

  // Pull coordinates
  final protected val pullCasts  = new ArrayBuffer[jIterator[_] => Any]
  final protected var pullCastss = List.empty[List[jIterator[_] => Any]]
  final protected val pullSorts  = new ArrayBuffer[(Int, Int => (Row, Row) => Int)]
  final protected var pullSortss = List.empty[List[Int => (Row, Row) => Int]]
  final protected var refDepths  = List(0, 0)

  // Ensure distinct result set when possible redundant optional values can occur
  final protected var hasOptAttr = false

  // Query variables
  private         val vars           : Array[Var]                      = Array("?a", "?b", "?c", "?d", "?e", "?f", "?g", "?h", "?i", "?j", "?k", "?l", "?m", "?n", "?o", "?p", "?q", "?r", "?s", "?t", "?u", "?v", "?w", "?x", "?y", "?z", "?aa", "?ab", "?ac", "?ad", "?ae", "?af", "?ag", "?ah", "?ai", "?aj", "?ak", "?al", "?am", "?an", "?ao", "?ap", "?aq", "?ar", "?as", "?at", "?au", "?av", "?aw", "?ax", "?ay", "?az", "?ba", "?bb", "?bc", "?bd", "?be", "?bf", "?bg", "?bh", "?bi", "?bj", "?bk", "?bl", "?bm", "?bn", "?bo", "?bp", "?bq", "?br", "?bs", "?bt", "?bu", "?bv", "?bw", "?bx", "?by", "?bz", "?ca", "?cb", "?cc", "?cd", "?ce", "?cf", "?cg", "?ch", "?ci", "?cj", "?ck", "?cl", "?cm", "?cn", "?co", "?cp", "?cq", "?cr", "?cs", "?ct", "?cu", "?cv", "?cw", "?cx", "?cy", "?cz", "?da", "?db", "?dc", "?dd", "?de", "?df", "?dg", "?dh", "?di", "?dj", "?dk", "?dl", "?dm", "?dn", "?do", "?dp", "?dq", "?dr", "?ds", "?dt", "?du", "?dv", "?dw", "?dx", "?dy", "?dz", "?ea", "?eb", "?ec", "?ed", "?ee", "?ef", "?eg", "?eh", "?ei", "?ej", "?ek", "?el", "?em", "?en", "?eo", "?ep", "?eq", "?er", "?es", "?et", "?eu", "?ev", "?ew", "?ex", "?ey", "?ez", "?fa", "?fb", "?fc", "?fd", "?fe", "?ff", "?fg", "?fh", "?fi", "?fj", "?fk", "?fl", "?fm", "?fn", "?fo", "?fp", "?fq", "?fr", "?fs", "?ft", "?fu", "?fv", "?fw", "?fx", "?fy", "?fz", "?ga", "?gb", "?gc", "?gd", "?ge", "?gf", "?gg", "?gh", "?gi", "?gj", "?gk", "?gl", "?gm", "?gn", "?go", "?gp", "?gq", "?gr", "?gs", "?gt", "?gu", "?gv", "?gw", "?gx", "?gy", "?gz", "?ha", "?hb", "?hc", "?hd", "?he", "?hf", "?hg", "?hh", "?hi", "?hj", "?hk", "?hl", "?hm", "?hn", "?ho", "?hp", "?hq", "?hr", "?hs", "?ht", "?hu", "?hv", "?hw", "?hx", "?hy", "?hz", "?ia", "?ib", "?ic", "?id", "?ie", "?if", "?ig", "?ih", "?ii", "?ij", "?ik", "?il", "?im", "?in", "?io", "?ip", "?iq", "?ir", "?is", "?it", "?iu", "?iv", "?iw", "?ix", "?iy", "?iz", "?ja", "?jb", "?jc", "?jd", "?je", "?jf", "?jg", "?jh", "?ji", "?jj", "?jk", "?jl", "?jm", "?jn", "?jo", "?jp", "?jq", "?jr", "?js", "?jt", "?ju", "?jv", "?jw", "?jx", "?jy", "?jz", "?ka", "?kb", "?kc", "?kd", "?ke", "?kf", "?kg", "?kh", "?ki", "?kj", "?kk", "?kl", "?km", "?kn", "?ko", "?kp", "?kq", "?kr", "?ks", "?kt", "?ku", "?kv", "?kw", "?kx", "?ky", "?kz", "?la", "?lb", "?lc", "?ld", "?le", "?lf", "?lg", "?lh", "?li", "?lj", "?lk", "?ll", "?lm", "?ln", "?lo", "?lp", "?lq", "?lr", "?ls", "?lt", "?lu", "?lv", "?lw", "?lx", "?ly", "?lz", "?ma", "?mb", "?mc", "?md", "?me", "?mf", "?mg", "?mh", "?mi", "?mj", "?mk", "?ml", "?mm", "?mn", "?mo", "?mp", "?mq", "?mr", "?ms", "?mt", "?mu", "?mv", "?mw", "?mx", "?my", "?mz", "?na", "?nb", "?nc", "?nd", "?ne", "?nf", "?ng", "?nh", "?ni", "?nj", "?nk", "?nl", "?nm", "?nn", "?no", "?np", "?nq", "?nr", "?ns", "?nt", "?nu", "?nv", "?nw", "?nx", "?ny", "?nz", "?oa", "?ob", "?oc", "?od", "?oe", "?of", "?og", "?oh", "?oi", "?oj", "?ok", "?ol", "?om", "?on", "?oo", "?op", "?oq", "?or", "?os", "?ot", "?ou", "?ov", "?ow", "?ox", "?oy", "?oz", "?pa", "?pb", "?pc", "?pd", "?pe", "?pf", "?pg", "?ph", "?pi", "?pj", "?pk", "?pl", "?pm", "?pn", "?po", "?pp", "?pq", "?pr", "?ps", "?pt", "?pu", "?pv", "?pw", "?px", "?py", "?pz", "?qa", "?qb", "?qc", "?qd", "?qe", "?qf", "?qg", "?qh", "?qi", "?qj", "?qk", "?ql", "?qm", "?qn", "?qo", "?qp", "?qq", "?qr", "?qs", "?qt", "?qu", "?qv", "?qw", "?qx", "?qy", "?qz", "?ra", "?rb", "?rc", "?rd", "?re", "?rf", "?rg", "?rh", "?ri", "?rj", "?rk", "?rl", "?rm", "?rn", "?ro", "?rp", "?rq", "?rr", "?rs", "?rt", "?ru", "?rv", "?rw", "?rx", "?ry", "?rz", "?sa", "?sb", "?sc", "?sd", "?se", "?sf", "?sg", "?sh", "?si", "?sj", "?sk", "?sl", "?sm", "?sn", "?so", "?sp", "?sq", "?sr", "?ss", "?st", "?su", "?sv", "?sw", "?sx", "?sy", "?sz", "?ta", "?tb", "?tc", "?td", "?te", "?tf", "?tg", "?th", "?ti", "?tj", "?tk", "?tl", "?tm", "?tn", "?to", "?tp", "?tq", "?tr", "?ts", "?tt", "?tu", "?tv", "?tw", "?tx", "?ty", "?tz", "?ua", "?ub", "?uc", "?ud", "?ue", "?uf", "?ug", "?uh", "?ui", "?uj", "?uk", "?ul", "?um", "?un", "?uo", "?up", "?uq", "?ur", "?us", "?ut", "?uu", "?uv", "?uw", "?ux", "?uy", "?uz", "?va", "?vb", "?vc", "?vd", "?ve", "?vf", "?vg", "?vh", "?vi", "?vj", "?vk", "?vl", "?vm", "?vn", "?vo", "?vp", "?vq", "?vr", "?vs", "?vt", "?vu", "?vv", "?vw", "?vx", "?vy", "?vz", "?wa", "?wb", "?wc", "?wd", "?we", "?wf", "?wg", "?wh", "?wi", "?wj", "?wk", "?wl", "?wm", "?wn", "?wo", "?wp", "?wq", "?wr", "?ws", "?wt", "?wu", "?wv", "?ww", "?wx", "?wy", "?wz", "?xa", "?xb", "?xc", "?xd", "?xe", "?xf", "?xg", "?xh", "?xi", "?xj", "?xk", "?xl", "?xm", "?xn", "?xo", "?xp", "?xq", "?xr", "?xs", "?xt", "?xu", "?xv", "?xw", "?xx", "?xy", "?xz", "?ya", "?yb", "?yc", "?yd", "?ye", "?yf", "?yg", "?yh", "?yi", "?yj", "?yk", "?yl", "?ym", "?yn", "?yo", "?yp", "?yq", "?yr", "?ys", "?yt", "?yu", "?yv", "?yw", "?yx", "?yy", "?yz", "?za", "?zb", "?zc", "?zd", "?ze", "?zf", "?zg", "?zh", "?zi", "?zj", "?zk", "?zl", "?zm", "?zn", "?zo", "?zp", "?zq", "?zr", "?zs", "?zt", "?zu", "?zv", "?zw", "?zx", "?zy", "?zz")
  private         var varIndex       : Int                             = -1
  final protected var filterAttrVars : Map[String, Var]                = Map.empty[String, Var]
  final protected var filterAttrVars1: Map[String, (Var, Var)]         = Map.empty[String, (Var, Var)]
  final protected var filterAttrVars2: Map[String, (Var, Var) => Unit] = Map.empty[String, (Var, Var) => Unit]
  final protected val expectedFilterAttrs                              = mutable.Set.empty[String]
  final protected val availableAttrs                                   = mutable.Set.empty[String]

  final protected var firstEid: String = ""
  final protected val txVar   : String = "?tx"

  // Add 4th tx var to first attribute datom if tx value is needed
  final protected var addTxVar: Boolean = false

  final protected def addCast(cast: AnyRef => AnyRef): Unit = {
    if (isTxData)
      castss = (castss.head :+ cast) :: castss.tail
    else
      castss = castss.init :+ (castss.last :+ cast)
  }

  final protected def removeLastCast(): Unit = {
    if (isTxData)
      castss = castss.head.init :: castss.tail
    else {
      castss = castss.init :+ castss.last.init
    }
  }
  final protected def replaceCast(cast: AnyRef => AnyRef): Unit = {
    removeLastCast()
    addCast(cast)
  }

  final protected def aritiesNested(): Unit = {
    val newLevel = Nil
    val curLevel = aritiess.last
    if (isComposite) {
      val curLevelCompositWithNested = curLevel.init :+ (curLevel.last :+ -1)
      aritiess = (aritiess.init :+ curLevelCompositWithNested) :+ newLevel
    } else {
      val curLevelWithNested = curLevel :+ List(-1)
      aritiess = (aritiess.init :+ curLevelWithNested) :+ newLevel
    }
  }

  final protected def aritiesComposite(): Unit = {
    if (isTxData) {
      isTxComposite = true
    } else {
      isComposite = true
    }
    if (isTxData) {
      aritiess = (aritiess.head :+ Nil) :: aritiess.tail
    } else {
      aritiess = aritiess.init :+ (aritiess.last :+ Nil)
    }
  }

  final protected def aritiesAttr(): Unit = {
    if (isTxData) {
      // Top level
      if (isTxComposite) {
        // Increase last arity
        val topLevel: List[List[Int]] = aritiess.head
        val arities                   = if (topLevel.isEmpty)
          List(List(1))
        else {
          topLevel.init :+ (topLevel.last :+ 1)
        }
        aritiess = arities :: aritiess.tail
      } else {
        // Add new arity of 1
        aritiess = (aritiess.head :+ List(1)) :: aritiess.tail
      }
    } else {
      // Current level
      if (isComposite) {
        // Increase last arity
        val lastLevel = aritiess.last
        val arities   = if (lastLevel.isEmpty)
          List(List(1))
        else
          lastLevel.init :+ (lastLevel.last :+ 1)
        aritiess = aritiess.init :+ arities
      } else {
        // Add new arity of 1
        aritiess = aritiess.init :+ (aritiess.last :+ List(1))
      }
    }
  }

  final protected def getFlatSorters(
    sortss: List[List[(Int, Int => (Row, Row) => Int)]]
  ): List[Int => (Row, Row) => Int] = {
    sortss.flatMap { sorts =>
      val sortsOrdered = sorts.sortBy(_._1)
      // Index 6s are for entity ids on each nested level.
      // Is always last on each level to allow user sort indexes to sort first.
      sortsOrdered.map(_._1) match {
        case Nil                    =>
        case List(6)                =>
        case List(1)                =>
        case List(1, 6)             =>
        case List(1, 2)             =>
        case List(1, 2, 6)          =>
        case List(1, 2, 3)          =>
        case List(1, 2, 3, 6)       =>
        case List(1, 2, 3, 4)       =>
        case List(1, 2, 3, 4, 6)    =>
        case List(1, 2, 3, 4, 5)    =>
        case List(1, 2, 3, 4, 5, 6) =>
        case other                  => throw ModelError(
          s"Sort index 1 should be present and additional indexes continuously increase (in any order). " +
            s"Found sort index(es): " + other.mkString(", ")
        )
      }
      sortsOrdered.map(_._2)
    }
  }

  final protected def unexpectedElement(element: Element) = throw ModelError("Unexpected element: " + element)
  final protected def unexpectedOp(op: Op) = throw ModelError("Unexpected operation: " + op)
  final protected def unexpectedKw(kw: String) = throw ModelError("Unexpected keyword: " + kw)

  final protected def noMixedNestedModes = throw ModelError(
    "Can't mix mandatory/optional nested data structures."
  )

  final protected def datomicFreePullBooleanBug: Nothing = {
    throw ExecutionError("Datomic has a bug that pulls boolean `false` values as nil.")
  }

  final protected def vv: Var = {
    varIndex += 1
    vars(varIndex)
  }
  def getVar(attr: Attr) = {
    filterAttrVars.getOrElse(attr.name, vv)
  }

  final protected def tx: String = {
    if (addTxVar) {
      addTxVar = false
      s" $txVar"
    } else ""
  }
}