// GENERATED CODE ********************************
package molecule.core.marshalling.pack

import molecule.boilerplate.ast.Model._

trait Packers_  { self: Tpls2DTO =>

  def getPacker(elements: List[Element], level: Int): Product => Unit = {
    val packers: List[Product => Unit] = resolvePackers(elements, Nil, level, 0)
    packers.length match {
      case 1 => resolve1(packers)
      case 2 => resolve2(packers)
      case 3 => resolve3(packers)
      case 4 => resolve4(packers)
      case 5 => resolve5(packers)
      case 6 => resolve6(packers)
      case 7 => resolve7(packers)
      case 8 => resolve8(packers)
      case 9 => resolve9(packers)
      case 10 => resolve10(packers)
      case 11 => resolve11(packers)
      case 12 => resolve12(packers)
      case 13 => resolve13(packers)
      case 14 => resolve14(packers)
      case 15 => resolve15(packers)
      case 16 => resolve16(packers)
      case 17 => resolve17(packers)
      case 18 => resolve18(packers)
      case 19 => resolve19(packers)
      case 20 => resolve20(packers)
      case 21 => resolve21(packers)
      case 22 => resolve22(packers)
    }
  }

  final private def resolve1(packers: List[Product => Unit]): Product => Unit = {
    val List(p1) = packers
    (tpl: Product) => {
      p1(tpl)
    }
  }

  final private def resolve2(packers: List[Product => Unit]): Product => Unit = {
    val List(p1, p2) = packers
    (tpl: Product) => {
      p1(tpl)
      p2(tpl)
    }
  }

  final private def resolve3(packers: List[Product => Unit]): Product => Unit = {
    val List(p1, p2, p3) = packers
    (tpl: Product) => {
      p1(tpl)
      p2(tpl)
      p3(tpl)
    }
  }

  final private def resolve4(packers: List[Product => Unit]): Product => Unit = {
    val List(p1, p2, p3, p4) = packers
    (tpl: Product) => {
      p1(tpl)
      p2(tpl)
      p3(tpl)
      p4(tpl)
    }
  }

  final private def resolve5(packers: List[Product => Unit]): Product => Unit = {
    val List(p1, p2, p3, p4, p5) = packers
    (tpl: Product) => {
      p1(tpl)
      p2(tpl)
      p3(tpl)
      p4(tpl)
      p5(tpl)
    }
  }

  final private def resolve6(packers: List[Product => Unit]): Product => Unit = {
    val List(p1, p2, p3, p4, p5, p6) = packers
    (tpl: Product) => {
      p1(tpl)
      p2(tpl)
      p3(tpl)
      p4(tpl)
      p5(tpl)
      p6(tpl)
    }
  }

  final private def resolve7(packers: List[Product => Unit]): Product => Unit = {
    val List(p1, p2, p3, p4, p5, p6, p7) = packers
    (tpl: Product) => {
      p1(tpl)
      p2(tpl)
      p3(tpl)
      p4(tpl)
      p5(tpl)
      p6(tpl)
      p7(tpl)
    }
  }

  final private def resolve8(packers: List[Product => Unit]): Product => Unit = {
    val List(p1, p2, p3, p4, p5, p6, p7, p8) = packers
    (tpl: Product) => {
      p1(tpl)
      p2(tpl)
      p3(tpl)
      p4(tpl)
      p5(tpl)
      p6(tpl)
      p7(tpl)
      p8(tpl)
    }
  }

  final private def resolve9(packers: List[Product => Unit]): Product => Unit = {
    val List(p1, p2, p3, p4, p5, p6, p7, p8, p9) = packers
    (tpl: Product) => {
      p1(tpl)
      p2(tpl)
      p3(tpl)
      p4(tpl)
      p5(tpl)
      p6(tpl)
      p7(tpl)
      p8(tpl)
      p9(tpl)
    }
  }

  final private def resolve10(packers: List[Product => Unit]): Product => Unit = {
    val List(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10) = packers
    (tpl: Product) => {
      p1(tpl)
      p2(tpl)
      p3(tpl)
      p4(tpl)
      p5(tpl)
      p6(tpl)
      p7(tpl)
      p8(tpl)
      p9(tpl)
      p10(tpl)
    }
  }

  final private def resolve11(packers: List[Product => Unit]): Product => Unit = {
    val List(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11) = packers
    (tpl: Product) => {
      p1(tpl)
      p2(tpl)
      p3(tpl)
      p4(tpl)
      p5(tpl)
      p6(tpl)
      p7(tpl)
      p8(tpl)
      p9(tpl)
      p10(tpl)
      p11(tpl)
    }
  }

  final private def resolve12(packers: List[Product => Unit]): Product => Unit = {
    val List(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12) = packers
    (tpl: Product) => {
      p1(tpl)
      p2(tpl)
      p3(tpl)
      p4(tpl)
      p5(tpl)
      p6(tpl)
      p7(tpl)
      p8(tpl)
      p9(tpl)
      p10(tpl)
      p11(tpl)
      p12(tpl)
    }
  }

  final private def resolve13(packers: List[Product => Unit]): Product => Unit = {
    val List(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13) = packers
    (tpl: Product) => {
      p1(tpl)
      p2(tpl)
      p3(tpl)
      p4(tpl)
      p5(tpl)
      p6(tpl)
      p7(tpl)
      p8(tpl)
      p9(tpl)
      p10(tpl)
      p11(tpl)
      p12(tpl)
      p13(tpl)
    }
  }

  final private def resolve14(packers: List[Product => Unit]): Product => Unit = {
    val List(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14) = packers
    (tpl: Product) => {
      p1(tpl)
      p2(tpl)
      p3(tpl)
      p4(tpl)
      p5(tpl)
      p6(tpl)
      p7(tpl)
      p8(tpl)
      p9(tpl)
      p10(tpl)
      p11(tpl)
      p12(tpl)
      p13(tpl)
      p14(tpl)
    }
  }

  final private def resolve15(packers: List[Product => Unit]): Product => Unit = {
    val List(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15) = packers
    (tpl: Product) => {
      p1(tpl)
      p2(tpl)
      p3(tpl)
      p4(tpl)
      p5(tpl)
      p6(tpl)
      p7(tpl)
      p8(tpl)
      p9(tpl)
      p10(tpl)
      p11(tpl)
      p12(tpl)
      p13(tpl)
      p14(tpl)
      p15(tpl)
    }
  }

  final private def resolve16(packers: List[Product => Unit]): Product => Unit = {
    val List(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16) = packers
    (tpl: Product) => {
      p1(tpl)
      p2(tpl)
      p3(tpl)
      p4(tpl)
      p5(tpl)
      p6(tpl)
      p7(tpl)
      p8(tpl)
      p9(tpl)
      p10(tpl)
      p11(tpl)
      p12(tpl)
      p13(tpl)
      p14(tpl)
      p15(tpl)
      p16(tpl)
    }
  }

  final private def resolve17(packers: List[Product => Unit]): Product => Unit = {
    val List(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17) = packers
    (tpl: Product) => {
      p1(tpl)
      p2(tpl)
      p3(tpl)
      p4(tpl)
      p5(tpl)
      p6(tpl)
      p7(tpl)
      p8(tpl)
      p9(tpl)
      p10(tpl)
      p11(tpl)
      p12(tpl)
      p13(tpl)
      p14(tpl)
      p15(tpl)
      p16(tpl)
      p17(tpl)
    }
  }

  final private def resolve18(packers: List[Product => Unit]): Product => Unit = {
    val List(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18) = packers
    (tpl: Product) => {
      p1(tpl)
      p2(tpl)
      p3(tpl)
      p4(tpl)
      p5(tpl)
      p6(tpl)
      p7(tpl)
      p8(tpl)
      p9(tpl)
      p10(tpl)
      p11(tpl)
      p12(tpl)
      p13(tpl)
      p14(tpl)
      p15(tpl)
      p16(tpl)
      p17(tpl)
      p18(tpl)
    }
  }

  final private def resolve19(packers: List[Product => Unit]): Product => Unit = {
    val List(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19) = packers
    (tpl: Product) => {
      p1(tpl)
      p2(tpl)
      p3(tpl)
      p4(tpl)
      p5(tpl)
      p6(tpl)
      p7(tpl)
      p8(tpl)
      p9(tpl)
      p10(tpl)
      p11(tpl)
      p12(tpl)
      p13(tpl)
      p14(tpl)
      p15(tpl)
      p16(tpl)
      p17(tpl)
      p18(tpl)
      p19(tpl)
    }
  }

  final private def resolve20(packers: List[Product => Unit]): Product => Unit = {
    val List(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20) = packers
    (tpl: Product) => {
      p1(tpl)
      p2(tpl)
      p3(tpl)
      p4(tpl)
      p5(tpl)
      p6(tpl)
      p7(tpl)
      p8(tpl)
      p9(tpl)
      p10(tpl)
      p11(tpl)
      p12(tpl)
      p13(tpl)
      p14(tpl)
      p15(tpl)
      p16(tpl)
      p17(tpl)
      p18(tpl)
      p19(tpl)
      p20(tpl)
    }
  }

  final private def resolve21(packers: List[Product => Unit]): Product => Unit = {
    val List(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21) = packers
    (tpl: Product) => {
      p1(tpl)
      p2(tpl)
      p3(tpl)
      p4(tpl)
      p5(tpl)
      p6(tpl)
      p7(tpl)
      p8(tpl)
      p9(tpl)
      p10(tpl)
      p11(tpl)
      p12(tpl)
      p13(tpl)
      p14(tpl)
      p15(tpl)
      p16(tpl)
      p17(tpl)
      p18(tpl)
      p19(tpl)
      p20(tpl)
      p21(tpl)
    }
  }

  final private def resolve22(packers: List[Product => Unit]): Product => Unit = {
    val List(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22) = packers
    (tpl: Product) => {
      p1(tpl)
      p2(tpl)
      p3(tpl)
      p4(tpl)
      p5(tpl)
      p6(tpl)
      p7(tpl)
      p8(tpl)
      p9(tpl)
      p10(tpl)
      p11(tpl)
      p12(tpl)
      p13(tpl)
      p14(tpl)
      p15(tpl)
      p16(tpl)
      p17(tpl)
      p18(tpl)
      p19(tpl)
      p20(tpl)
      p21(tpl)
      p22(tpl)
    }
  }
}