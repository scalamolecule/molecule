// GENERATED CODE ********************************
package molecule.db.core.marshalling.serialize

import molecule.core.dataModel.*

trait PickleTpl_ { self: PickleTpls =>

  def getPickler(elements: List[Element]): Product => Unit = {
    val picklers: List[Product => Unit] = resolvePicklers(elements, Nil, 0)
    picklers.length match {
      case 1  => resolve1(picklers)
      case 2  => resolve2(picklers)
      case 3  => resolve3(picklers)
      case 4  => resolve4(picklers)
      case 5  => resolve5(picklers)
      case 6  => resolve6(picklers)
      case 7  => resolve7(picklers)
      case 8  => resolve8(picklers)
      case 9  => resolve9(picklers)
      case 10 => resolve10(picklers)
      case 11 => resolve11(picklers)
      case 12 => resolve12(picklers)
      case 13 => resolve13(picklers)
      case 14 => resolve14(picklers)
      case 15 => resolve15(picklers)
      case 16 => resolve16(picklers)
      case 17 => resolve17(picklers)
      case 18 => resolve18(picklers)
      case 19 => resolve19(picklers)
      case 20 => resolve20(picklers)
      case 21 => resolve21(picklers)
      case 22 => resolve22(picklers)
    }
  }

  final private def resolve1(picklers: List[Product => Unit]): Product => Unit = {
    val List(p1) = picklers
    (tpl: Product) => {
      p1(tpl)
    }
  }

  final private def resolve2(picklers: List[Product => Unit]): Product => Unit = {
    val List(p1, p2) = picklers
    (tpl: Product) => {
      p1(tpl)
      p2(tpl)
    }
  }

  final private def resolve3(picklers: List[Product => Unit]): Product => Unit = {
    val List(p1, p2, p3) = picklers
    (tpl: Product) => {
      p1(tpl)
      p2(tpl)
      p3(tpl)
    }
  }

  final private def resolve4(picklers: List[Product => Unit]): Product => Unit = {
    val List(p1, p2, p3, p4) = picklers
    (tpl: Product) => {
      p1(tpl)
      p2(tpl)
      p3(tpl)
      p4(tpl)
    }
  }

  final private def resolve5(picklers: List[Product => Unit]): Product => Unit = {
    val List(p1, p2, p3, p4, p5) = picklers
    (tpl: Product) => {
      p1(tpl)
      p2(tpl)
      p3(tpl)
      p4(tpl)
      p5(tpl)
    }
  }

  final private def resolve6(picklers: List[Product => Unit]): Product => Unit = {
    val List(p1, p2, p3, p4, p5, p6) = picklers
    (tpl: Product) => {
      p1(tpl)
      p2(tpl)
      p3(tpl)
      p4(tpl)
      p5(tpl)
      p6(tpl)
    }
  }

  final private def resolve7(picklers: List[Product => Unit]): Product => Unit = {
    val List(p1, p2, p3, p4, p5, p6, p7) = picklers
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

  final private def resolve8(picklers: List[Product => Unit]): Product => Unit = {
    val List(p1, p2, p3, p4, p5, p6, p7, p8) = picklers
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

  final private def resolve9(picklers: List[Product => Unit]): Product => Unit = {
    val List(p1, p2, p3, p4, p5, p6, p7, p8, p9) = picklers
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

  final private def resolve10(picklers: List[Product => Unit]): Product => Unit = {
    val List(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10) = picklers
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

  final private def resolve11(picklers: List[Product => Unit]): Product => Unit = {
    val List(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11) = picklers
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

  final private def resolve12(picklers: List[Product => Unit]): Product => Unit = {
    val List(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12) = picklers
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

  final private def resolve13(picklers: List[Product => Unit]): Product => Unit = {
    val List(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13) = picklers
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

  final private def resolve14(picklers: List[Product => Unit]): Product => Unit = {
    val List(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14) = picklers
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

  final private def resolve15(picklers: List[Product => Unit]): Product => Unit = {
    val List(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15) = picklers
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

  final private def resolve16(picklers: List[Product => Unit]): Product => Unit = {
    val List(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16) = picklers
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

  final private def resolve17(picklers: List[Product => Unit]): Product => Unit = {
    val List(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17) = picklers
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

  final private def resolve18(picklers: List[Product => Unit]): Product => Unit = {
    val List(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18) = picklers
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

  final private def resolve19(picklers: List[Product => Unit]): Product => Unit = {
    val List(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19) = picklers
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

  final private def resolve20(picklers: List[Product => Unit]): Product => Unit = {
    val List(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20) = picklers
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

  final private def resolve21(picklers: List[Product => Unit]): Product => Unit = {
    val List(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21) = picklers
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

  final private def resolve22(picklers: List[Product => Unit]): Product => Unit = {
    val List(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22) = picklers
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