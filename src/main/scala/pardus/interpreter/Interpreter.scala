package pardus.interpreter

import Types._

object Interpreter {

  def maxArgs(stm: Stm): Int = stm match {
    case Stm.CompoundStm(left, right) => maxArgs(left).max(maxArgs(right))
    case Stm.AssignStm(_, exp) => maxArgsE(exp)
    case Stm.PrintStm(exprs) => exprs.map(maxArgsE).foldLeft(0)(_ max _)
  }

  def maxArgsE(exp: Exp): Int = exp match {
    case Exp.IdExp(_) => 0
    case Exp.NumExp(_) => 0
    case Exp.OpExp(l,_,r) => maxArgsE(l).max(maxArgsE(r))
    case Exp.EseqExp(stm, exp) => maxArgs(stm).max(maxArgsE(exp))
  }

  def interp(stm: Stm): Unit = ???
  def interpStm(stm: Stm, table: Table): Table = ???
  def interpExp = ???
  def update(table: Table, key: Id, value: Int) =
    table.updated(key, value)
}
