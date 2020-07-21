Vending Machine Kata Implementation
===================================

Exact Change Only
-----------------
As written, the requirements are incomplete - 
they don't specify how the machine gets into or out of the 'exact change only' state.
This implementation just models this with an internal flag,
which is administratively set.

A real vending machine would need to track coins available.
(That would also enable giving change with whatever is available, 
instead of the current quarters, then dimes, then nickles strategy.)
