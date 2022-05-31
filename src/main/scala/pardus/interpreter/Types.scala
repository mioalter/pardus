package pardus.interpreter

object Types {

  type Id = String

  case class Table(map: Map[Id, Int]) {
    def update(key: Id, value: Int): Table =
      Table(map.updated(key, value))
    def lookup(key: Id): Int = map(key)
  }

  val emptyTable = Table(Map.empty)

  sealed trait BinOp
  object BinOp {
    final case object Plus extends BinOp
    final case object Minus extends BinOp
    final case object Times extends BinOp
    final case object Div extends BinOp
  }

  sealed trait Stm
  object Stm {
    final case class CompoundStm(left: Stm, right: Stm) extends Stm
    final case class AssignStm(id: Id, exp: Exp) extends Stm
    final case class PrintStm(exprs: List[Exp]) extends Stm
  }

  sealed trait Exp
  object Exp {
    final case class IdExp(value: Id) extends Exp
    final case class NumExp(value: Int) extends Exp
    final case class OpExp(left: Exp, op: BinOp, right: Exp) extends Exp
    final case class EseqExp(stm: Stm, exp: Exp) extends Exp
  }

}
