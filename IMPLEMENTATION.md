Vending Machine Kata Implementation
===================================

Asynchronous Behavior
---------------------
A real vending machine would display THANK YOU or SOLD OUT for some small
length of time before returning to INSERT COIN (or EXACT CHANGE ONLY).
This requires asynchronous code (which I can write) and asyncronous tests
(which I did not have time to familiarize myself with).

As written, the exercise permits "subsequent checks of the display" to display
the new message. This could be handled by setting and clearing a flag, but
that's not how a real vending machine operates, so I deferred implementing that.


Exact Change Only
-----------------
As written, the requirements are incomplete - 
they don't specify how the machine gets into or out of the 'exact change only' state.
This implementation just models this with an internal flag,
which is administratively set.

A real vending machine would need to track coins available.
(That would also enable giving change with whatever is available, 
instead of the current quarters, then dimes, then nickles strategy.)
