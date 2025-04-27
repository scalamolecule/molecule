package molecule.db.datalog.core.query

import java.util.{Iterator as jIterator, List as jList}
import molecule.base.util.BaseHelpers
import molecule.core.ast.DataModel.*
import molecule.core.query.Model2Query
import molecule.core.util.JavaConversions
import scala.collection.mutable.{ArrayBuffer, ListBuffer}

trait DatomicQueryBase
  extends Model2Query
    with BaseHelpers
    with JavaConversions {

  // Datomic row type
  type Row = jList[AnyRef]
  type AttrIndex = Int
  type NestedTpls = List[Any]

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
  final val nestedIds = new ArrayBuffer[String]
  final val find      = new ArrayBuffer[String]
  final val widh      = new ArrayBuffer[String]
  final val in        = new ArrayBuffer[String]
  final val where     = new ArrayBuffer[(String, Int)]
  final val rules     = new ArrayBuffer[String]

  // In variables and where clauses not shared with pre-query. To be added lastly to main query
  final val inPost    = new ArrayBuffer[String]
  final val wherePost = new ArrayBuffer[(String, Int)]

  // Input args
  final val preArgs = new ArrayBuffer[AnyRef]
  final val args    = new ArrayBuffer[AnyRef]

  // Sorting
  final var sortss    = List(List.empty[(Int, Int => (Row, Row) => Int)])
  final var attrIndex = -1

  final protected def addSort(sorter: Option[(Int, Int => (Row, Row) => Int)]): Unit = {
    sorter.foreach(s => sortss = sortss.init :+ (sortss.last :+ s))
  }
  final protected def replaceSort(sorter: Option[(Int, Int => (Row, Row) => Int)]): Unit = {
    sorter.foreach(s => sortss = sortss.init :+ (sortss.last.init :+ s))
  }

  final def getFlatSorters(
    sortss: List[List[(Int, Int => (Row, Row) => Int)]]
  ): List[Int => (Row, Row) => Int] = {
    sortss.flatMap(_.sortBy(_._1).map(_._2))
  }

  // Query variables
  final var es                  = List.empty[Var]
  final val vars   : Array[Var] = Array("?a", "?b", "?c", "?d", "?e", "?f", "?g", "?h", "?i", "?j", "?k", "?l", "?m", "?n", "?o", "?p", "?q", "?r", "?s", "?t", "?u", "?v", "?w", "?x", "?y", "?z", "?aa", "?ab", "?ac", "?ad", "?ae", "?af", "?ag", "?ah", "?ai", "?aj", "?ak", "?al", "?am", "?an", "?ao", "?ap", "?aq", "?ar", "?as", "?at", "?au", "?av", "?aw", "?ax", "?ay", "?az", "?ba", "?bb", "?bc", "?bd", "?be", "?bf", "?bg", "?bh", "?bi", "?bj", "?bk", "?bl", "?bm", "?bn", "?bo", "?bp", "?bq", "?br", "?bs", "?bt", "?bu", "?bv", "?bw", "?bx", "?by", "?bz", "?ca", "?cb", "?cc", "?cd", "?ce", "?cf", "?cg", "?ch", "?ci", "?cj", "?ck", "?cl", "?cm", "?cn", "?co", "?cp", "?cq", "?cr", "?cs", "?ct", "?cu", "?cv", "?cw", "?cx", "?cy", "?cz", "?da", "?db", "?dc", "?dd", "?de", "?df", "?dg", "?dh", "?di", "?dj", "?dk", "?dl", "?dm", "?dn", "?do", "?dp", "?dq", "?dr", "?ds", "?dt", "?du", "?dv", "?dw", "?dx", "?dy", "?dz", "?ea", "?eb", "?ec", "?ed", "?ee", "?ef", "?eg", "?eh", "?ei", "?ej", "?ek", "?el", "?em", "?en", "?eo", "?ep", "?eq", "?er", "?es", "?et", "?eu", "?ev", "?ew", "?ex", "?ey", "?ez", "?fa", "?fb", "?fc", "?fd", "?fe", "?ff", "?fg", "?fh", "?fi", "?fj", "?fk", "?fl", "?fm", "?fn", "?fo", "?fp", "?fq", "?fr", "?fs", "?ft", "?fu", "?fv", "?fw", "?fx", "?fy", "?fz", "?ga", "?gb", "?gc", "?gd", "?ge", "?gf", "?gg", "?gh", "?gi", "?gj", "?gk", "?gl", "?gm", "?gn", "?go", "?gp", "?gq", "?gr", "?gs", "?gt", "?gu", "?gv", "?gw", "?gx", "?gy", "?gz", "?ha", "?hb", "?hc", "?hd", "?he", "?hf", "?hg", "?hh", "?hi", "?hj", "?hk", "?hl", "?hm", "?hn", "?ho", "?hp", "?hq", "?hr", "?hs", "?ht", "?hu", "?hv", "?hw", "?hx", "?hy", "?hz", "?ia", "?ib", "?ic", "?id", "?ie", "?if", "?ig", "?ih", "?ii", "?ij", "?ik", "?il", "?im", "?in", "?io", "?ip", "?iq", "?ir", "?is", "?it", "?iu", "?iv", "?iw", "?ix", "?iy", "?iz", "?ja", "?jb", "?jc", "?jd", "?je", "?jf", "?jg", "?jh", "?ji", "?jj", "?jk", "?jl", "?jm", "?jn", "?jo", "?jp", "?jq", "?jr", "?js", "?jt", "?ju", "?jv", "?jw", "?jx", "?jy", "?jz", "?ka", "?kb", "?kc", "?kd", "?ke", "?kf", "?kg", "?kh", "?ki", "?kj", "?kk", "?kl", "?km", "?kn", "?ko", "?kp", "?kq", "?kr", "?ks", "?kt", "?ku", "?kv", "?kw", "?kx", "?ky", "?kz", "?la", "?lb", "?lc", "?ld", "?le", "?lf", "?lg", "?lh", "?li", "?lj", "?lk", "?ll", "?lm", "?ln", "?lo", "?lp", "?lq", "?lr", "?ls", "?lt", "?lu", "?lv", "?lw", "?lx", "?ly", "?lz", "?ma", "?mb", "?mc", "?md", "?me", "?mf", "?mg", "?mh", "?mi", "?mj", "?mk", "?ml", "?mm", "?mn", "?mo", "?mp", "?mq", "?mr", "?ms", "?mt", "?mu", "?mv", "?mw", "?mx", "?my", "?mz", "?na", "?nb", "?nc", "?nd", "?ne", "?nf", "?ng", "?nh", "?ni", "?nj", "?nk", "?nl", "?nm", "?nn", "?no", "?np", "?nq", "?nr", "?ns", "?nt", "?nu", "?nv", "?nw", "?nx", "?ny", "?nz", "?oa", "?ob", "?oc", "?od", "?oe", "?of", "?og", "?oh", "?oi", "?oj", "?ok", "?ol", "?om", "?on", "?oo", "?op", "?oq", "?or", "?os", "?ot", "?ou", "?ov", "?ow", "?ox", "?oy", "?oz", "?pa", "?pb", "?pc", "?pd", "?pe", "?pf", "?pg", "?ph", "?pi", "?pj", "?pk", "?pl", "?pm", "?pn", "?po", "?pp", "?pq", "?pr", "?ps", "?pt", "?pu", "?pv", "?pw", "?px", "?py", "?pz", "?qa", "?qb", "?qc", "?qd", "?qe", "?qf", "?qg", "?qh", "?qi", "?qj", "?qk", "?ql", "?qm", "?qn", "?qo", "?qp", "?qq", "?qr", "?qs", "?qt", "?qu", "?qv", "?qw", "?qx", "?qy", "?qz", "?ra", "?rb", "?rc", "?rd", "?re", "?rf", "?rg", "?rh", "?ri", "?rj", "?rk", "?rl", "?rm", "?rn", "?ro", "?rp", "?rq", "?rr", "?rs", "?rt", "?ru", "?rv", "?rw", "?rx", "?ry", "?rz", "?sa", "?sb", "?sc", "?sd", "?se", "?sf", "?sg", "?sh", "?si", "?sj", "?sk", "?sl", "?sm", "?sn", "?so", "?sp", "?sq", "?sr", "?ss", "?st", "?su", "?sv", "?sw", "?sx", "?sy", "?sz", "?ta", "?tb", "?tc", "?td", "?te", "?tf", "?tg", "?th", "?ti", "?tj", "?tk", "?tl", "?tm", "?tn", "?to", "?tp", "?tq", "?tr", "?ts", "?tt", "?tu", "?tv", "?tw", "?tx", "?ty", "?tz", "?ua", "?ub", "?uc", "?ud", "?ue", "?uf", "?ug", "?uh", "?ui", "?uj", "?uk", "?ul", "?um", "?un", "?uo", "?up", "?uq", "?ur", "?us", "?ut", "?uu", "?uv", "?uw", "?ux", "?uy", "?uz", "?va", "?vb", "?vc", "?vd", "?ve", "?vf", "?vg", "?vh", "?vi", "?vj", "?vk", "?vl", "?vm", "?vn", "?vo", "?vp", "?vq", "?vr", "?vs", "?vt", "?vu", "?vv", "?vw", "?vx", "?vy", "?vz", "?wa", "?wb", "?wc", "?wd", "?we", "?wf", "?wg", "?wh", "?wi", "?wj", "?wk", "?wl", "?wm", "?wn", "?wo", "?wp", "?wq", "?wr", "?ws", "?wt", "?wu", "?wv", "?ww", "?wx", "?wy", "?wz", "?xa", "?xb", "?xc", "?xd", "?xe", "?xf", "?xg", "?xh", "?xi", "?xj", "?xk", "?xl", "?xm", "?xn", "?xo", "?xp", "?xq", "?xr", "?xs", "?xt", "?xu", "?xv", "?xw", "?xx", "?xy", "?xz", "?ya", "?yb", "?yc", "?yd", "?ye", "?yf", "?yg", "?yh", "?yi", "?yj", "?yk", "?yl", "?ym", "?yn", "?yo", "?yp", "?yq", "?yr", "?ys", "?yt", "?yu", "?yv", "?yw", "?yx", "?yy", "?yz", "?za", "?zb", "?zc", "?zd", "?ze", "?zf", "?zg", "?zh", "?zi", "?zj", "?zk", "?zl", "?zm", "?zn", "?zo", "?zp", "?zq", "?zr", "?zs", "?zt", "?zu", "?zv", "?zw", "?zx", "?zy", "?zz")
  final var varIndex            = -1
  final var firstId: String     = ""

  final protected def vv: Var = {
    varIndex += 1
    vars(varIndex)
  }

  def getVar(attr: Attr, path: List[String] = path, cache: Boolean = true) = {
    val pathAttr = path :+ attr.attr
    filterAttrVars.getOrElse(pathAttr, {
      val w = vv
      if (cache) {
        filterAttrVars = filterAttrVars + (pathAttr -> w)
      }
      w
    })
  }

  // Filter variables
  final var filterAttrVars  = Map.empty[List[String], Var]
  final var filterAttrVars1 = Map.empty[String, (Var, Var)]
  final var filterAttrVars2 = Map.empty[String, (Var, Var) => Unit]
  final var refConfirmed    = true

  // Paths
  final var varPath: List[String] = List.empty[String]
  final var path                  = List.empty[String]

  // Pull coordinates
  final val pullCasts  = new ListBuffer[jIterator[?] => Any]
  final var pullCastss = List.empty[List[jIterator[?] => Any]]

  final var pullCastsss = List.empty[List[List[jIterator[?] => Any]]]

  final val pullSorts  = new ListBuffer[(Int, Int => (Row, Row) => Int)]
  final var pullSortss = List.empty[List[Int => (Row, Row) => Int]]
  final var refDepths  = List(0, 0)

  // Casting
  final var castss = List(List.empty[AnyRef => AnyRef])

  final protected def addCast(cast: AnyRef => AnyRef): Unit = {
    castss = castss.init :+ (castss.last :+ cast)
  }

  final protected def removeLastCast(): Unit = {
    castss = castss.init :+ castss.last.init
  }

  final protected def replaceCast(cast: AnyRef => AnyRef): Unit = {
    removeLastCast()
    addCast(cast)
  }
}