# Parsers

Parsers use Context-Free Grammars. Here is a brief introduction.

A _Context-Free Grammar_ consists of

* A set `V` of _variables_ or _nonterminals_
* A set `T` of terminals
* A relation `R` over `V` and `(V ∪ T)<sup>*</sup>` where "star" denotes the Kleene star
* An element `S` of `V` called the _start symbol_.

`(V ∪ T)*` is the set of strings of 0 or more variables and terminals.
A relation over `V` and `(V ∪ T)*` is just a subset of `V x (V ∪ T)*`.

The relation `R` is called the set of _production rules_.

Now we:

* define a relation on `(V ∪ T)*` and
* take its reflexive transitive closure

Our new relation `RR` is given by

* `u ~ v` if there exist `a,b` in `R` and `x,y` in `(V ∪ T)*`
  such that `u = xay` and `v = xby`.

We write this as `u => v` and call it a _derivation_.
Note that this says that we get `v` from `u`
by replacing `a` in `V` by `b` in `(V ∪ T)*`.

We then take the reflexive, transitive closure of this so a pair `(u,v)` is
in our relation if there are 0 or more derivations that get us from `u` to `v`.

The _langauge_ definfed by this context-free grammar is the set

```L(G) = { w in T* | S => w }```

That is, writing `[S]` for the equivalence class of `S` in `(V ∪ T)*` under `RR`,

```L(G) = T* ∩ [S] ⊆  (V ∪ T)*```

so it is the subset of strings of terminals that can be derived from `S`.

TODO: example from Hopcraft and Ullman where
* `V = {E, I}`
* `T = {*, +, (, ), a, b, 0, 1}`
* `R = {(E, I), (E, E+E), (E, E*E), (I, a), (I, b), (I, Ia), (I, Ib), (I, I0), (I, I1) }
* `S = E`

Note: there are clearly some assumptions about the production rules because we
expect to be able to eventually arrive at strings of terminals.
