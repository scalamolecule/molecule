// GENERATED CODE ********************************
package molecule.db.datomic.query

import molecule.base.util.BaseHelpers
import molecule.base.util.exceptions.MoleculeException
import molecule.boilerplate.ast.MoleculeModel._
import molecule.core.query.Model2Query
import scala.collection.mutable.ArrayBuffer


trait Base[Tpl] extends BaseHelpers { self: Model2Query[Tpl] =>

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

  final protected val sortIds = new ArrayBuffer[String]
  final protected val find    = new ArrayBuffer[String]
  final protected val widh    = new ArrayBuffer[String]
  final protected val in      = new ArrayBuffer[String]
  final protected val where   = new ArrayBuffer[(String, Int)]
  final protected val rules   = new ArrayBuffer[String]
  final protected val inputs  = new ArrayBuffer[AnyRef]
  final protected val typers  = new ArrayBuffer[AnyRef => AnyRef]

  type Row = java.util.List[AnyRef]

  // Sorting
  protected var attrIndex: Int = -1
  final protected val sorts    = new ArrayBuffer[(Int, (Row, Row) => Int)]

  protected def unexpected(element: Element) = throw MoleculeException("Unexpected element: " + element)
  protected def unexpected(op: Op) = throw MoleculeException("Unexpected operation: " + op)

  // Datomic entity id variable
  type Var = String

  private val vars              = Array("?a", "?b", "?c", "?d", "?e", "?f", "?g", "?h", "?i", "?j", "?k", "?l", "?m", "?n", "?o", "?p", "?q", "?r", "?s", "?t", "?u", "?v", "?w", "?x", "?y", "?z", "?aa", "?ab", "?ac", "?ad", "?ae", "?af", "?ag", "?ah", "?ai", "?aj", "?ak", "?al", "?am", "?an", "?ao", "?ap", "?aq", "?ar", "?as", "?at", "?au", "?av", "?aw", "?ax", "?ay", "?az", "?ba", "?bb", "?bc", "?bd", "?be", "?bf", "?bg", "?bh", "?bi", "?bj", "?bk", "?bl", "?bm", "?bn", "?bo", "?bp", "?bq", "?br", "?bs", "?bt", "?bu", "?bv", "?bw", "?bx", "?by", "?bz", "?ca", "?cb", "?cc", "?cd", "?ce", "?cf", "?cg", "?ch", "?ci", "?cj", "?ck", "?cl", "?cm", "?cn", "?co", "?cp", "?cq", "?cr", "?cs", "?ct", "?cu", "?cv", "?cw", "?cx", "?cy", "?cz", "?da", "?db", "?dc", "?dd", "?de", "?df", "?dg", "?dh", "?di", "?dj", "?dk", "?dl", "?dm", "?dn", "?do", "?dp", "?dq", "?dr", "?ds", "?dt", "?du", "?dv", "?dw", "?dx", "?dy", "?dz", "?ea", "?eb", "?ec", "?ed", "?ee", "?ef", "?eg", "?eh", "?ei", "?ej", "?ek", "?el", "?em", "?en", "?eo", "?ep", "?eq", "?er", "?es", "?et", "?eu", "?ev", "?ew", "?ex", "?ey", "?ez", "?fa", "?fb", "?fc", "?fd", "?fe", "?ff", "?fg", "?fh", "?fi", "?fj", "?fk", "?fl", "?fm", "?fn", "?fo", "?fp", "?fq", "?fr", "?fs", "?ft", "?fu", "?fv", "?fw", "?fx", "?fy", "?fz", "?ga", "?gb", "?gc", "?gd", "?ge", "?gf", "?gg", "?gh", "?gi", "?gj", "?gk", "?gl", "?gm", "?gn", "?go", "?gp", "?gq", "?gr", "?gs", "?gt", "?gu", "?gv", "?gw", "?gx", "?gy", "?gz", "?ha", "?hb", "?hc", "?hd", "?he", "?hf", "?hg", "?hh", "?hi", "?hj", "?hk", "?hl", "?hm", "?hn", "?ho", "?hp", "?hq", "?hr", "?hs", "?ht", "?hu", "?hv", "?hw", "?hx", "?hy", "?hz", "?ia", "?ib", "?ic", "?id", "?ie", "?if", "?ig", "?ih", "?ii", "?ij", "?ik", "?il", "?im", "?in", "?io", "?ip", "?iq", "?ir", "?is", "?it", "?iu", "?iv", "?iw", "?ix", "?iy", "?iz", "?ja", "?jb", "?jc", "?jd", "?je", "?jf", "?jg", "?jh", "?ji", "?jj", "?jk", "?jl", "?jm", "?jn", "?jo", "?jp", "?jq", "?jr", "?js", "?jt", "?ju", "?jv", "?jw", "?jx", "?jy", "?jz", "?ka", "?kb", "?kc", "?kd", "?ke", "?kf", "?kg", "?kh", "?ki", "?kj", "?kk", "?kl", "?km", "?kn", "?ko", "?kp", "?kq", "?kr", "?ks", "?kt", "?ku", "?kv", "?kw", "?kx", "?ky", "?kz", "?la", "?lb", "?lc", "?ld", "?le", "?lf", "?lg", "?lh", "?li", "?lj", "?lk", "?ll", "?lm", "?ln", "?lo", "?lp", "?lq", "?lr", "?ls", "?lt", "?lu", "?lv", "?lw", "?lx", "?ly", "?lz", "?ma", "?mb", "?mc", "?md", "?me", "?mf", "?mg", "?mh", "?mi", "?mj", "?mk", "?ml", "?mm", "?mn", "?mo", "?mp", "?mq", "?mr", "?ms", "?mt", "?mu", "?mv", "?mw", "?mx", "?my", "?mz", "?na", "?nb", "?nc", "?nd", "?ne", "?nf", "?ng", "?nh", "?ni", "?nj", "?nk", "?nl", "?nm", "?nn", "?no", "?np", "?nq", "?nr", "?ns", "?nt", "?nu", "?nv", "?nw", "?nx", "?ny", "?nz", "?oa", "?ob", "?oc", "?od", "?oe", "?of", "?og", "?oh", "?oi", "?oj", "?ok", "?ol", "?om", "?on", "?oo", "?op", "?oq", "?or", "?os", "?ot", "?ou", "?ov", "?ow", "?ox", "?oy", "?oz", "?pa", "?pb", "?pc", "?pd", "?pe", "?pf", "?pg", "?ph", "?pi", "?pj", "?pk", "?pl", "?pm", "?pn", "?po", "?pp", "?pq", "?pr", "?ps", "?pt", "?pu", "?pv", "?pw", "?px", "?py", "?pz", "?qa", "?qb", "?qc", "?qd", "?qe", "?qf", "?qg", "?qh", "?qi", "?qj", "?qk", "?ql", "?qm", "?qn", "?qo", "?qp", "?qq", "?qr", "?qs", "?qt", "?qu", "?qv", "?qw", "?qx", "?qy", "?qz", "?ra", "?rb", "?rc", "?rd", "?re", "?rf", "?rg", "?rh", "?ri", "?rj", "?rk", "?rl", "?rm", "?rn", "?ro", "?rp", "?rq", "?rr", "?rs", "?rt", "?ru", "?rv", "?rw", "?rx", "?ry", "?rz", "?sa", "?sb", "?sc", "?sd", "?se", "?sf", "?sg", "?sh", "?si", "?sj", "?sk", "?sl", "?sm", "?sn", "?so", "?sp", "?sq", "?sr", "?ss", "?st", "?su", "?sv", "?sw", "?sx", "?sy", "?sz", "?ta", "?tb", "?tc", "?td", "?te", "?tf", "?tg", "?th", "?ti", "?tj", "?tk", "?tl", "?tm", "?tn", "?to", "?tp", "?tq", "?tr", "?ts", "?tt", "?tu", "?tv", "?tw", "?tx", "?ty", "?tz", "?ua", "?ub", "?uc", "?ud", "?ue", "?uf", "?ug", "?uh", "?ui", "?uj", "?uk", "?ul", "?um", "?un", "?uo", "?up", "?uq", "?ur", "?us", "?ut", "?uu", "?uv", "?uw", "?ux", "?uy", "?uz", "?va", "?vb", "?vc", "?vd", "?ve", "?vf", "?vg", "?vh", "?vi", "?vj", "?vk", "?vl", "?vm", "?vn", "?vo", "?vp", "?vq", "?vr", "?vs", "?vt", "?vu", "?vv", "?vw", "?vx", "?vy", "?vz", "?wa", "?wb", "?wc", "?wd", "?we", "?wf", "?wg", "?wh", "?wi", "?wj", "?wk", "?wl", "?wm", "?wn", "?wo", "?wp", "?wq", "?wr", "?ws", "?wt", "?wu", "?wv", "?ww", "?wx", "?wy", "?wz", "?xa", "?xb", "?xc", "?xd", "?xe", "?xf", "?xg", "?xh", "?xi", "?xj", "?xk", "?xl", "?xm", "?xn", "?xo", "?xp", "?xq", "?xr", "?xs", "?xt", "?xu", "?xv", "?xw", "?xx", "?xy", "?xz", "?ya", "?yb", "?yc", "?yd", "?ye", "?yf", "?yg", "?yh", "?yi", "?yj", "?yk", "?yl", "?ym", "?yn", "?yo", "?yp", "?yq", "?yr", "?ys", "?yt", "?yu", "?yv", "?yw", "?yx", "?yy", "?yz", "?za", "?zb", "?zc", "?zd", "?ze", "?zf", "?zg", "?zh", "?zi", "?zj", "?zk", "?zl", "?zm", "?zn", "?zo", "?zp", "?zq", "?zr", "?zs", "?zt", "?zu", "?zv", "?zw", "?zx", "?zy", "?zz")
  private var varIndex: Int     = -1
  private var addTxVar: Boolean = false

  protected def vv: String = {
    varIndex += 1
    vars(varIndex)
  }

  protected def tx: String = {
    if (addTxVar) {
      addTxVar = false
      " ?tx"
    } else ""
  }

  final override lazy protected val row2tpl: Row => Tpl = {
    typers.length match {
      case 1  => resolve1
      case 2  => resolve2
      case 3  => resolve3
      case 4  => resolve4
      case 5  => resolve5
      case 6  => resolve6
      case 7  => resolve7
      case 8  => resolve8
      case 9  => resolve9
      case 10 => resolve10
      case 11 => resolve11
      case 12 => resolve12
      case 13 => resolve13
      case 14 => resolve14
      case 15 => resolve15
      case 16 => resolve16
      case 17 => resolve17
      case 18 => resolve18
      case 19 => resolve19
      case 20 => resolve20
      case 21 => resolve21
      case 22 => resolve22
    }
  }

  final private def resolve1: Row => Tpl = {
    val t1 = typers(0)
    (row: Row) =>
      (
        t1(row.get(0))
        ).asInstanceOf[Tpl]
  }

  final private def resolve2: Row => Tpl = {
    val t1 = typers(0)
    val t2 = typers(1)
    (row: Row) =>
      (
        t1(row.get(0)),
        t2(row.get(1))
        ).asInstanceOf[Tpl]
  }

  final private def resolve3: Row => Tpl = {
    val t1 = typers(0)
    val t2 = typers(1)
    val t3 = typers(2)
    (row: Row) =>
      (
        t1(row.get(0)),
        t2(row.get(1)),
        t3(row.get(2))
        ).asInstanceOf[Tpl]
  }

  final private def resolve4: Row => Tpl = {
    val t1 = typers(0)
    val t2 = typers(1)
    val t3 = typers(2)
    val t4 = typers(3)
    (row: Row) =>
      (
        t1(row.get(0)),
        t2(row.get(1)),
        t3(row.get(2)),
        t4(row.get(3))
        ).asInstanceOf[Tpl]
  }

  final private def resolve5: Row => Tpl = {
    val t1 = typers(0)
    val t2 = typers(1)
    val t3 = typers(2)
    val t4 = typers(3)
    val t5 = typers(4)
    (row: Row) =>
      (
        t1(row.get(0)),
        t2(row.get(1)),
        t3(row.get(2)),
        t4(row.get(3)),
        t5(row.get(4))
        ).asInstanceOf[Tpl]
  }

  final private def resolve6: Row => Tpl = {
    val t1 = typers(0)
    val t2 = typers(1)
    val t3 = typers(2)
    val t4 = typers(3)
    val t5 = typers(4)
    val t6 = typers(5)
    (row: Row) =>
      (
        t1(row.get(0)),
        t2(row.get(1)),
        t3(row.get(2)),
        t4(row.get(3)),
        t5(row.get(4)),
        t6(row.get(5))
        ).asInstanceOf[Tpl]
  }

  final private def resolve7: Row => Tpl = {
    val t1 = typers(0)
    val t2 = typers(1)
    val t3 = typers(2)
    val t4 = typers(3)
    val t5 = typers(4)
    val t6 = typers(5)
    val t7 = typers(6)
    (row: Row) =>
      (
        t1(row.get(0)),
        t2(row.get(1)),
        t3(row.get(2)),
        t4(row.get(3)),
        t5(row.get(4)),
        t6(row.get(5)),
        t7(row.get(6))
        ).asInstanceOf[Tpl]
  }

  final private def resolve8: Row => Tpl = {
    val t1 = typers(0)
    val t2 = typers(1)
    val t3 = typers(2)
    val t4 = typers(3)
    val t5 = typers(4)
    val t6 = typers(5)
    val t7 = typers(6)
    val t8 = typers(7)
    (row: Row) =>
      (
        t1(row.get(0)),
        t2(row.get(1)),
        t3(row.get(2)),
        t4(row.get(3)),
        t5(row.get(4)),
        t6(row.get(5)),
        t7(row.get(6)),
        t8(row.get(7))
        ).asInstanceOf[Tpl]
  }

  final private def resolve9: Row => Tpl = {
    val t1 = typers(0)
    val t2 = typers(1)
    val t3 = typers(2)
    val t4 = typers(3)
    val t5 = typers(4)
    val t6 = typers(5)
    val t7 = typers(6)
    val t8 = typers(7)
    val t9 = typers(8)
    (row: Row) =>
      (
        t1(row.get(0)),
        t2(row.get(1)),
        t3(row.get(2)),
        t4(row.get(3)),
        t5(row.get(4)),
        t6(row.get(5)),
        t7(row.get(6)),
        t8(row.get(7)),
        t9(row.get(8))
        ).asInstanceOf[Tpl]
  }

  final private def resolve10: Row => Tpl = {
    val t1  = typers(0)
    val t2  = typers(1)
    val t3  = typers(2)
    val t4  = typers(3)
    val t5  = typers(4)
    val t6  = typers(5)
    val t7  = typers(6)
    val t8  = typers(7)
    val t9  = typers(8)
    val t10 = typers(9)
    (row: Row) =>
      (
        t1(row.get(0)),
        t2(row.get(1)),
        t3(row.get(2)),
        t4(row.get(3)),
        t5(row.get(4)),
        t6(row.get(5)),
        t7(row.get(6)),
        t8(row.get(7)),
        t9(row.get(8)),
        t10(row.get(9))
        ).asInstanceOf[Tpl]
  }

  final private def resolve11: Row => Tpl = {
    val t1  = typers(0)
    val t2  = typers(1)
    val t3  = typers(2)
    val t4  = typers(3)
    val t5  = typers(4)
    val t6  = typers(5)
    val t7  = typers(6)
    val t8  = typers(7)
    val t9  = typers(8)
    val t10 = typers(9)
    val t11 = typers(10)
    (row: Row) =>
      (
        t1(row.get(0)),
        t2(row.get(1)),
        t3(row.get(2)),
        t4(row.get(3)),
        t5(row.get(4)),
        t6(row.get(5)),
        t7(row.get(6)),
        t8(row.get(7)),
        t9(row.get(8)),
        t10(row.get(9)),
        t11(row.get(10))
        ).asInstanceOf[Tpl]
  }

  final private def resolve12: Row => Tpl = {
    val t1  = typers(0)
    val t2  = typers(1)
    val t3  = typers(2)
    val t4  = typers(3)
    val t5  = typers(4)
    val t6  = typers(5)
    val t7  = typers(6)
    val t8  = typers(7)
    val t9  = typers(8)
    val t10 = typers(9)
    val t11 = typers(10)
    val t12 = typers(11)
    (row: Row) =>
      (
        t1(row.get(0)),
        t2(row.get(1)),
        t3(row.get(2)),
        t4(row.get(3)),
        t5(row.get(4)),
        t6(row.get(5)),
        t7(row.get(6)),
        t8(row.get(7)),
        t9(row.get(8)),
        t10(row.get(9)),
        t11(row.get(10)),
        t12(row.get(11))
        ).asInstanceOf[Tpl]
  }

  final private def resolve13: Row => Tpl = {
    val t1  = typers(0)
    val t2  = typers(1)
    val t3  = typers(2)
    val t4  = typers(3)
    val t5  = typers(4)
    val t6  = typers(5)
    val t7  = typers(6)
    val t8  = typers(7)
    val t9  = typers(8)
    val t10 = typers(9)
    val t11 = typers(10)
    val t12 = typers(11)
    val t13 = typers(12)
    (row: Row) =>
      (
        t1(row.get(0)),
        t2(row.get(1)),
        t3(row.get(2)),
        t4(row.get(3)),
        t5(row.get(4)),
        t6(row.get(5)),
        t7(row.get(6)),
        t8(row.get(7)),
        t9(row.get(8)),
        t10(row.get(9)),
        t11(row.get(10)),
        t12(row.get(11)),
        t13(row.get(12))
        ).asInstanceOf[Tpl]
  }

  final private def resolve14: Row => Tpl = {
    val t1  = typers(0)
    val t2  = typers(1)
    val t3  = typers(2)
    val t4  = typers(3)
    val t5  = typers(4)
    val t6  = typers(5)
    val t7  = typers(6)
    val t8  = typers(7)
    val t9  = typers(8)
    val t10 = typers(9)
    val t11 = typers(10)
    val t12 = typers(11)
    val t13 = typers(12)
    val t14 = typers(13)
    (row: Row) =>
      (
        t1(row.get(0)),
        t2(row.get(1)),
        t3(row.get(2)),
        t4(row.get(3)),
        t5(row.get(4)),
        t6(row.get(5)),
        t7(row.get(6)),
        t8(row.get(7)),
        t9(row.get(8)),
        t10(row.get(9)),
        t11(row.get(10)),
        t12(row.get(11)),
        t13(row.get(12)),
        t14(row.get(13))
        ).asInstanceOf[Tpl]
  }

  final private def resolve15: Row => Tpl = {
    val t1  = typers(0)
    val t2  = typers(1)
    val t3  = typers(2)
    val t4  = typers(3)
    val t5  = typers(4)
    val t6  = typers(5)
    val t7  = typers(6)
    val t8  = typers(7)
    val t9  = typers(8)
    val t10 = typers(9)
    val t11 = typers(10)
    val t12 = typers(11)
    val t13 = typers(12)
    val t14 = typers(13)
    val t15 = typers(14)
    (row: Row) =>
      (
        t1(row.get(0)),
        t2(row.get(1)),
        t3(row.get(2)),
        t4(row.get(3)),
        t5(row.get(4)),
        t6(row.get(5)),
        t7(row.get(6)),
        t8(row.get(7)),
        t9(row.get(8)),
        t10(row.get(9)),
        t11(row.get(10)),
        t12(row.get(11)),
        t13(row.get(12)),
        t14(row.get(13)),
        t15(row.get(14))
        ).asInstanceOf[Tpl]
  }

  final private def resolve16: Row => Tpl = {
    val t1  = typers(0)
    val t2  = typers(1)
    val t3  = typers(2)
    val t4  = typers(3)
    val t5  = typers(4)
    val t6  = typers(5)
    val t7  = typers(6)
    val t8  = typers(7)
    val t9  = typers(8)
    val t10 = typers(9)
    val t11 = typers(10)
    val t12 = typers(11)
    val t13 = typers(12)
    val t14 = typers(13)
    val t15 = typers(14)
    val t16 = typers(15)
    (row: Row) =>
      (
        t1(row.get(0)),
        t2(row.get(1)),
        t3(row.get(2)),
        t4(row.get(3)),
        t5(row.get(4)),
        t6(row.get(5)),
        t7(row.get(6)),
        t8(row.get(7)),
        t9(row.get(8)),
        t10(row.get(9)),
        t11(row.get(10)),
        t12(row.get(11)),
        t13(row.get(12)),
        t14(row.get(13)),
        t15(row.get(14)),
        t16(row.get(15))
        ).asInstanceOf[Tpl]
  }

  final private def resolve17: Row => Tpl = {
    val t1  = typers(0)
    val t2  = typers(1)
    val t3  = typers(2)
    val t4  = typers(3)
    val t5  = typers(4)
    val t6  = typers(5)
    val t7  = typers(6)
    val t8  = typers(7)
    val t9  = typers(8)
    val t10 = typers(9)
    val t11 = typers(10)
    val t12 = typers(11)
    val t13 = typers(12)
    val t14 = typers(13)
    val t15 = typers(14)
    val t16 = typers(15)
    val t17 = typers(16)
    (row: Row) =>
      (
        t1(row.get(0)),
        t2(row.get(1)),
        t3(row.get(2)),
        t4(row.get(3)),
        t5(row.get(4)),
        t6(row.get(5)),
        t7(row.get(6)),
        t8(row.get(7)),
        t9(row.get(8)),
        t10(row.get(9)),
        t11(row.get(10)),
        t12(row.get(11)),
        t13(row.get(12)),
        t14(row.get(13)),
        t15(row.get(14)),
        t16(row.get(15)),
        t17(row.get(16))
        ).asInstanceOf[Tpl]
  }

  final private def resolve18: Row => Tpl = {
    val t1  = typers(0)
    val t2  = typers(1)
    val t3  = typers(2)
    val t4  = typers(3)
    val t5  = typers(4)
    val t6  = typers(5)
    val t7  = typers(6)
    val t8  = typers(7)
    val t9  = typers(8)
    val t10 = typers(9)
    val t11 = typers(10)
    val t12 = typers(11)
    val t13 = typers(12)
    val t14 = typers(13)
    val t15 = typers(14)
    val t16 = typers(15)
    val t17 = typers(16)
    val t18 = typers(17)
    (row: Row) =>
      (
        t1(row.get(0)),
        t2(row.get(1)),
        t3(row.get(2)),
        t4(row.get(3)),
        t5(row.get(4)),
        t6(row.get(5)),
        t7(row.get(6)),
        t8(row.get(7)),
        t9(row.get(8)),
        t10(row.get(9)),
        t11(row.get(10)),
        t12(row.get(11)),
        t13(row.get(12)),
        t14(row.get(13)),
        t15(row.get(14)),
        t16(row.get(15)),
        t17(row.get(16)),
        t18(row.get(17))
        ).asInstanceOf[Tpl]
  }

  final private def resolve19: Row => Tpl = {
    val t1  = typers(0)
    val t2  = typers(1)
    val t3  = typers(2)
    val t4  = typers(3)
    val t5  = typers(4)
    val t6  = typers(5)
    val t7  = typers(6)
    val t8  = typers(7)
    val t9  = typers(8)
    val t10 = typers(9)
    val t11 = typers(10)
    val t12 = typers(11)
    val t13 = typers(12)
    val t14 = typers(13)
    val t15 = typers(14)
    val t16 = typers(15)
    val t17 = typers(16)
    val t18 = typers(17)
    val t19 = typers(18)
    (row: Row) =>
      (
        t1(row.get(0)),
        t2(row.get(1)),
        t3(row.get(2)),
        t4(row.get(3)),
        t5(row.get(4)),
        t6(row.get(5)),
        t7(row.get(6)),
        t8(row.get(7)),
        t9(row.get(8)),
        t10(row.get(9)),
        t11(row.get(10)),
        t12(row.get(11)),
        t13(row.get(12)),
        t14(row.get(13)),
        t15(row.get(14)),
        t16(row.get(15)),
        t17(row.get(16)),
        t18(row.get(17)),
        t19(row.get(18))
        ).asInstanceOf[Tpl]
  }

  final private def resolve20: Row => Tpl = {
    val t1  = typers(0)
    val t2  = typers(1)
    val t3  = typers(2)
    val t4  = typers(3)
    val t5  = typers(4)
    val t6  = typers(5)
    val t7  = typers(6)
    val t8  = typers(7)
    val t9  = typers(8)
    val t10 = typers(9)
    val t11 = typers(10)
    val t12 = typers(11)
    val t13 = typers(12)
    val t14 = typers(13)
    val t15 = typers(14)
    val t16 = typers(15)
    val t17 = typers(16)
    val t18 = typers(17)
    val t19 = typers(18)
    val t20 = typers(19)
    (row: Row) =>
      (
        t1(row.get(0)),
        t2(row.get(1)),
        t3(row.get(2)),
        t4(row.get(3)),
        t5(row.get(4)),
        t6(row.get(5)),
        t7(row.get(6)),
        t8(row.get(7)),
        t9(row.get(8)),
        t10(row.get(9)),
        t11(row.get(10)),
        t12(row.get(11)),
        t13(row.get(12)),
        t14(row.get(13)),
        t15(row.get(14)),
        t16(row.get(15)),
        t17(row.get(16)),
        t18(row.get(17)),
        t19(row.get(18)),
        t20(row.get(19))
        ).asInstanceOf[Tpl]
  }

  final private def resolve21: Row => Tpl = {
    val t1  = typers(0)
    val t2  = typers(1)
    val t3  = typers(2)
    val t4  = typers(3)
    val t5  = typers(4)
    val t6  = typers(5)
    val t7  = typers(6)
    val t8  = typers(7)
    val t9  = typers(8)
    val t10 = typers(9)
    val t11 = typers(10)
    val t12 = typers(11)
    val t13 = typers(12)
    val t14 = typers(13)
    val t15 = typers(14)
    val t16 = typers(15)
    val t17 = typers(16)
    val t18 = typers(17)
    val t19 = typers(18)
    val t20 = typers(19)
    val t21 = typers(20)
    (row: Row) =>
      (
        t1(row.get(0)),
        t2(row.get(1)),
        t3(row.get(2)),
        t4(row.get(3)),
        t5(row.get(4)),
        t6(row.get(5)),
        t7(row.get(6)),
        t8(row.get(7)),
        t9(row.get(8)),
        t10(row.get(9)),
        t11(row.get(10)),
        t12(row.get(11)),
        t13(row.get(12)),
        t14(row.get(13)),
        t15(row.get(14)),
        t16(row.get(15)),
        t17(row.get(16)),
        t18(row.get(17)),
        t19(row.get(18)),
        t20(row.get(19)),
        t21(row.get(20))
        ).asInstanceOf[Tpl]
  }

  final private def resolve22: Row => Tpl = {
    val t1  = typers(0)
    val t2  = typers(1)
    val t3  = typers(2)
    val t4  = typers(3)
    val t5  = typers(4)
    val t6  = typers(5)
    val t7  = typers(6)
    val t8  = typers(7)
    val t9  = typers(8)
    val t10 = typers(9)
    val t11 = typers(10)
    val t12 = typers(11)
    val t13 = typers(12)
    val t14 = typers(13)
    val t15 = typers(14)
    val t16 = typers(15)
    val t17 = typers(16)
    val t18 = typers(17)
    val t19 = typers(18)
    val t20 = typers(19)
    val t21 = typers(20)
    val t22 = typers(21)
    (row: Row) =>
      (
        t1(row.get(0)),
        t2(row.get(1)),
        t3(row.get(2)),
        t4(row.get(3)),
        t5(row.get(4)),
        t6(row.get(5)),
        t7(row.get(6)),
        t8(row.get(7)),
        t9(row.get(8)),
        t10(row.get(9)),
        t11(row.get(10)),
        t12(row.get(11)),
        t13(row.get(12)),
        t14(row.get(13)),
        t15(row.get(14)),
        t16(row.get(15)),
        t17(row.get(16)),
        t18(row.get(17)),
        t19(row.get(18)),
        t20(row.get(19)),
        t21(row.get(20)),
        t22(row.get(21))
        ).asInstanceOf[Tpl]
  }
}