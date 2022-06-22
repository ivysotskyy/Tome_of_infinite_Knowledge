# Git:

**[git glossary](https://git-scm.com/docs/gitglossary)** 

---

## git commands:

---

[//]: # (config)
<details>

<summary> <b>git config</b> <a href="https://git-scm.com/docs/git-config">docs</a> </summary>

---
+ ``--system``, ``--global``, ``--local``, ``--worktree`` or ``--file \<filename>``
  + **config file** location. when writing defaults to ``--local``
+ ``--add <key> <value>``
  + Adds new line to the option
+ ``--get <key>``
+ ``-l`` or ``--list``
+ ``--unset-all``
  +  Remove all lines matching the key from config file.
+ ``--unset <key>`` or ``--unset <key> <value>``

**SETUP:**
```bash
  git config --global user.name "<firstname lasname>"
  git config --global user.email "<valid-email>"
  git config --color.ui auto
  git config --global core.editor code
```

---
</details>

[//]: # (add)
<details>

<summary><b>git add</b> <a href="https://git-scm.com/docs/git-add">docs</a> </summary>

---
+ ``<path>``
  + Files to add content from
+ ``-f`` ``--force``
  + Allow adding otherwise ignored files
+ ``-n`` ``--dry-run``
  + Don’t actually add the file(s), just show if they exist and/or will be ignored.
+ ``-v`` ``--verbose`` Be verbose
+ ``-u`` ``--update``
  + Update the index just where it already has an entry.
+ ``-i`` ``--interactive`` 
+ ``-p`` ``--patch``
  + Interactively choose hunks of patch between the index and the work tree and add them to the index
+ ``-e`` ``--edit`` 
  + Open the diff vs. the index in an editor and let the user edit it.

**Examples:**

Adds content from all *.txt files under Documentation directory and its subdirectories:
````bash
  git add Documentation/\*.txt
````

Considers adding content from all git-*.sh scripts:
````bash
  git add git-*.sh
````

---
</details>

[//]: # (rm)
<details>

<summary><b>git rm</b> <a href="https://git-scm.com/docs/git-rm">docs</a> </summary>

---
+ ``<path>``
  + Files to remove
+ ``-r``
  + Allow recursive removal
+ ``-f`` ``--force``
  + Override the up-tp-date check.
+ ``-q`` ``--quiet``
+ ``--cached``
  + Use this option to unstage and remove paths only from the index.
+ ``--sparse``
  + Allow updating index entries outside of the *[sparse-checkout](https://git-scm.com/docs/git-sparse-checkout)* cone.
---
</details>

[//]: # (commit)
<details>

<summary><b>git commit</b> <a href="https://git-scm.com/docs/git-commit">docs</a> </summary>

---
+ ``-a`` ``--all``
  + Automatically stages *deleted* and *modified* files before **commit**.
+ ``-p`` ``--patch``
---
</details>

[//]: # (stash)
<details>

<summary><b>git stash</b> <a href="https://git-scm.com/docs/git-stash">docs</a> </summary>

---

---
</details>

[//]: # (status)
<details>

<summary><b>git </b> <a href="https://git-scm.com/docs/git-">docs</a> </summary>

---

---
</details>

[//]: # (log)
<details>

<summary><b>git </b> <a href="https://git-scm.com/docs/git-log">docs</a> </summary>

---

---
</details>

[//]: # (branch)
<details>

<summary><b>git </b> <a href="https://git-scm.com/docs/git-">docs</a> </summary>

---

---
</details>

[//]: # (diff)
<details>

<summary><b>git </b> <a href="https://git-scm.com/docs/git-">docs</a> </summary>

---

---
</details>

----

## Ignoring patterns:

```gitignore
  logs/
  *.notes
  .idea/
  pattern*/
```
Save a file with desired patterns as .gitignore with either direct string
matches or wildcard globs.

```bash
  git config --global core.excludesfile [file]
```
system wide ignore pattern for all local repositories
