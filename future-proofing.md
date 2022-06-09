# Future-proof code design

---

### How does one *"proof"* one's future

***future proofing = change proofing***

Changes can fall in a number of different categories:

+ Change to **<u>scale</u>** - to handle more traffic/work items.
+ Change to **<u>requirements</u>** - new features or logic.
+ Change to **<u>technology stack</u>** - switching tp a different data store or programming language.
+ Change to **<u>integrations</u>** - the project needs to talk to a new third-party application, either on top of or
  instead of an existing one.
+ Change to **<u>schemas</u>** - we want to change the fields that define our data objects.

---

## Future-proofing strategies:

+ ### **Modularization**:<br>

**Modularization** is the act of splitting your code up in to chunks. Allows you to <u>isolate</u> your changes to a
single module , and/or to <u>swap</u> modules.<br>
You can also have modularization at different levels. Extracting your code in to functions is a simple act of
modularization that rarely has a downside until you're at the point where your functions are o small and numerous that
they're hard to keep track of. However, you can extract things into a class, a sub-application, a microservice, or even
a separate cluster; and at each level, while the isolation and flexibility goes up, so does the cognitive and
administrative overhead of managing your different components.

+ ### **Abstraction**:<br>

**Abstraction** is the mental model you have of the parts of your code. A module, function, class, etc. with **low
abstraction** (or higher **specificity**) more closely models the action it takes or object it represents. A thing
with **higher abstraction** brings your reasoning up a level.<br>
For example, instead of your code working with ``Cars``, it could work with ``Vehicles``. That way, if you ever need to
start handling ``Motorcacles`` , the changes necessary to do so is mitigated by the fact that you were never really
talking about ``Cars`` to begin with.


The higher you go with abstraction, the more flexible you make your code, but also the harder it is to understand.
Everyone knows what a ``Car`` is, but when you start working with ``Vehicles``, it becomes more difficult to work with
``Car``-specific things like booster seats and steering wheels. And if you keep going higher up the abstraction tree,
you might find yourself coding around ``Transportation``, ``ManufacturedGoods``, even plain  old ``Objects``,
and have a very hard time figuring out how to fill up your tank.
---
    A mantainable system is determenistic, meaning there 
    should be an obviusly correct wy to extend your system

