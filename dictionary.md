<h3>volatile</h3>
<p>
  In computer programming, particularly in the C, C++, C#, and Java programming languages, the <b>volatile</b> 
  <a href="/wiki/Keyword_(computer_programming)" class="mw-redirect" title="Keyword (computer programming)">keyword</a> indicates that a <a href="/wiki/Value_(computer_science)" title="Value (computer science)">value</a> may change between different accesses, even if it does not appear to be modified. This keyword prevents an <a href="/wiki/Optimizing_compiler" title="Optimizing compiler">optimizing compiler</a> from optimizing away subsequent reads or writes and thus incorrectly reusing a stale value or omitting writes. Volatile values primarily arise in hardware access (<a href="/wiki/Memory-mapped_I/O" title="Memory-mapped I/O">memory-mapped I/O</a>), where reading from or writing to memory is used to communicate with <a href="/wiki/Peripheral_device" class="mw-redirect" title="Peripheral device">peripheral devices</a>, and in <a href="/wiki/Thread_(computing)" title="Thread (computing)">threading</a>, where a different thread may have modified a value.
</p>
<p>
  Despite being a common keyword, the behavior of <code>volatile</code> differs significantly between programming 
  languages, and is easily misunderstood. In C and C++, it is a <a href="/wiki/Type_qualifier" title="Type qualifier">type 
  qualifier</a>, like <code>const</code>, and is a property of the <i>type</i>. Furthermore, in C and C++ 
  it does <i>not</i> work in most threading scenarios, and that use is discouraged. In Java and C#, it is a property of a 
  <a href="/wiki/Variable_(computer_science)" title="Variable (computer science)">variable</a> and indicates that the 
  <a href="/wiki/Object_(computer_science)" title="Object (computer science)">object</a> to which the variable is bound may 
  mutate, and is specifically intended for threading. In the <a href="/wiki/D_(programming_language)" title="D 
  (programming language)">D</a> programming language, there is a separate keyword <code>shared</code> for the threading 
  usage, but no <code>volatile</code> keyword exists.<br>
  <a href="https://en.wikipedia.org/wiki/Volatile_(computer_programming)">source</a>
</p>
