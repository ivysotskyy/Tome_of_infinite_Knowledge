# GIT <img src="./git/logo.png" alt="logo" height="50" />

| [git glossary](https://git-scm.com/docs/gitglossary) | [commands](#git-commands) | [ignoring patterns](#ignoring-patterns) | [Tips&Tricks](#tips-&-tricks) | [detached head](#detached-head) |
|:----------------------------------------------------:|---------------------------|-----------------------------------------|-------------------------------|---------------------------------|

---

![git basic workflow](git/git_basic_workflow.png)

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
  git config color.ui auto
  git config --global core.editor code
```

---
</details>

[//]: # (init)
<details>

<summary><b>git init</b> <a href="https://git-scm.com/docs/git-init">docs</a> </summary>

---
+ ``--bare``
  + Create a bare repository. If ``GIT_DIR`` environment is not set, it is set to the current working directory.
+ ``-b <branchname>``
  + Name initial branch
+ ``<perm>``
  + ``<perm>`` is a 3-digit octal number prefixed with 0 and each file will have mode ``<perm>``.

EXAMPLE
```bash
  cd /path/to/my/codebase
  git init      (1)
  git add .     (2)
  git commit    (3)
```
---
</details>

[//]: # (remote)
<details>

<summary><b>git remote</b> <a href="https://git-scm.com/docs/git-remote">docs</a> </summary>

---

COMMANDS
+ ``add <name>``
  + Add a remote named ``<name>`` for the repository at ``<URL>``. The command git fetch <name> can then be used to create and 
  update remote-tracking branches ``<name>/<branch>``.
  + ``-f`` option, ``git fetch <name>`` is run immediately after the remote information is set up.
+ ``rename`` ``rm``
  + Removes the remote named ``<name>``. All remote-tracking branches and configuration settings for the remote are removed.
+ ``sest-head``
  + Sets or deletes the default branch (i.e. the target of the ``symbolic-ref refs/remotes/<name>/HEAD)`` for the named remote.
+ ``set-branches`` 
  + Changes the list of branches tracked by the named remote. This can be used to track a subset of the available remote 
  branches after the initial setup for a remote.
+ ``get-url``
  + Retrieves the URLs for a remote.
+ ``set-url``
  + Changes URLs for the remote.
+ ``show``
  + Gives some information about the remote ``<name>``.
+ ``update``
  + Fetch updates for remotes or remote groups in the repository.
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
  + Use the interactive patch selection interface to choose which changes to commit.
+ ``-m <msg>`` ``--message=<msg>``
  + Commit message
+ ``<path>``
  + commit the contents of the files that match the pathspec without recording the changes already added to the index.
---
</details>

[//]: # (stash)
<details>

<summary><b>git stash</b> <a href="https://git-scm.com/docs/git-stash">docs</a> </summary>

---
COMMANDS
+ ``push [-p|--patch] [-u|--include-untracked] [-a|--all] [-q|--quiet] [-m|--message <message>] <path>``
  + Save your local modifications to a new stash entry and roll them back to HEAD (in the working tree and in the index). 
  The <message> part is optional and gives the description along with the stashed state.
+ ``list``
  + List the stash entries that you currently have.
+ ``show [-u|--include-untracked|--only-untracked] [<diff-options>] [<stash>]``
  + Show the changes recorded in the stash entry as a diff between the stashed contents and the commit back when the stash 
  entry was first created.
+ ``pop [--index] [-q|--quiet] [<stash>]``
  + Remove a single stashed state from the stash list and apply it on top of the current working tree state.
+ ``clear``
+ ``drop``

OPTIONS
+ ``-a`` ``-all``
+ ``-u`` ``--include-untracked`` ``--no-include-untracked``
+ ``--index``
  + This option is only valid for ``pop`` and ``apply`` commands.
+ ``-p`` ``--patch``
  + This option is only valid for ``push`` and ``save`` commands. Interactively select hunks from the diff between HEAD and the 
  working tree to be stashed.
---
</details>

[//]: # (status)
<details>

<summary><b>git status</b> <a href="https://git-scm.com/docs/git-status">docs</a> </summary>

---
+ ``-s`` ``--short``
+ ``-b`` ``--branch``
+ ``--show-stash``
+ ``--porcelain[=<version]``
  + Give the output in an easy-to-parse format for scripts. This is similar to the short output, but will remain stable 
  across Git versions and regardless of user configuration. See below for details.
+ ``-v -vv`` ``--verbose``
+ ``<pathspec>``
---
</details>

[//]: # (switch)
<details>

<summary><b>git switch</b> <a href="https://git-scm.com/docs/git-switch">docs</a></summary>
Switch to a specified branch. The working tree and the index are updated to match the branch. All new commits will be 
added to the tip of this branch.

---
+ ``<branch>``
+ ``<new-branch>``
+ ``<start-point``
  + The starting point for the new branch. Specifying a <start-point> allows you to create a branch based on some other 
  point in history than where HEAD currently points.
+ ``-c <new-branch>`` ``-c <new-branch>``
  + Create a new branch named ``<new-branch>`` starting before switching to the branch.
+ ``-C <new-branch`` ``--force-create <new-branch>``
  + Similar to ``--create`` except that if <new-branch> already exists, it will be reset to ``<start-point>``.
+ ``-d`` ``--detach``
  + Switch to a commit for inspection and discardable experiments. See the "[DETACHED HEAD](#detached-head)"  section.
+ ``-t`` ``--track``
  + When creating a new branch, set up "upstream" configuration. ``-c`` is implied.

---
</details>

[//]: # (log)
<details>

<summary><b>git log</b> <a href="https://git-scm.com/docs/git-log">docs</a> </summary>

https://www.atlassian.com/git/tutorials/git-log

---
+ ``--short``
+ ``--graph``
+ ``--oneline``
+ ``--follow``
  + Continue listing the history of a file beyond renames (works only for a single file)
+ ``--decorate[=short|full|auto|no]``
+ ``--full-diff``
+ ``--all``
  + Pretend as if all the refs in refs/, along with HEAD, are listed on the command line as <commit>
+ ``<path>``
  + Show only commits that are enough to explain how the files that match the specified paths came to be.
+ ``-n <number>``
  + Limit the number of commits to output
---
</details>

[//]: # (branch)
<details>

<summary><b>git branch</b> <a href="https://git-scm.com/docs/git-branch">docs</a> </summary>

---
+ ``-d`` ``-delete``
+ ``-D``
  + Shortcut for ``--delete --force``
+ ``-m`` ``--move``
  + Move/rename a branches, together with its config and reflog.
+ ``-M``
  + Shortcut for ``--move --force``
+ ``-c`` ``--copy``
+ ``-r`` ``--remotes``
  + List  the remote-tracking branches.
+ ``-a`` ``--all``
+ ``-v`` ``-vv`` ``-verbose``
+ ``-q`` ``-quiet``
+ ``--contains [<commit>]``
  + Only list branches which contain the specified commit (HEAD if not specified). Implies ``--list.``
+ ``<branchname>``
---
</details>

[//]: # (diff)
<details>

<summary><b>git diff</b> <a href="https://git-scm.com/docs/git-diff">docs</a> </summary>

---
````bash
git diff [<options>] [<commit>] [--] [<path>…​]
git diff [<options>] --cached [--merge-base] [<commit>] [--] [<path>…​]
git diff [<options>] [--merge-base] <commit> [<commit>…​] <commit> [--] [<path>…​]
git diff [<options>] <commit>…​<commit> [--] [<path>…​]
git diff [<options>] <blob> <blob>
git diff [<options>] --no-index [--] <path> <path> 
````
+ ``--raw``
  + Generate the diff in raw format.
+ ``--minimal``
  + Spend extra time to make sure the smallest possible diff is produced.
+ ``--patience`` ``--histogram`` ``--diff-algorithm={patience|minimal|histogram|myers}``
  + Generate a diff using the "patience diff"/"histogram/... diff" algorithm.
---
</details>

[//]: # (clone)
<details>

<summary><b>git clone</b> <a href="https://git-scm.com/docs/git-clone">docs</a> </summary>

---
+ ``-l`` ``--local``
  + When the repository is on a local machine.
+ ``-v`` ``--vebose``
+ ``-o <name>`` ``--origin <name>``
  + Override the default remote name (origin)
+ ``-b <name>`` ``--branch <name>``
  + Instead of pointing the newly created HEAD, point to ``<name>``
+ ``--depth <depth>``
  + Create a shallow clone with a history truncated to the specified number of commits. Implies ``--single-branch``
+ ``<repository>``
+ ``<directory>``
---
</details>

[//]: # (checkout)
<details>

<summary><b>git checkout</b> <a href="https://git-scm.com/docs/git-checkout">docs</a> </summary>

---
+ ``-f`` ``--focre``
  + When switching branches, proceed even if the index or the working tree differs from ``HEAD``
+ ``-b <new-branch>`` ``-B <new-branch>``
  + Create a new branch named ``<new-branch>`` and start it at ``<start-point>``
+ ``--ours`` ``--theirs``
  + When checking out paths from the index, check out stage #2 (ours) or #3 (theirs) for unmerged paths.
+ ``-d`` ``--detach``
  + Rather than checking out a branch to work on it, check out a commit for inspection and discardable experiments.
+ ``--orphan <new-branch>``
  + The first commit made on this new branch will have no parents and it will be the root of a new history totally 
  disconnected from all the other branches and commits.
+ ``-m`` ``--merge``
  + With this option, a three-way merge between the current branch, your working tree contents, and the new branch is done.
+ ``<branch>``
+ ``<new_branch>``
+ ``<start-point>``
+ ``<commit>``

EXAMPLE
+ ``git checkout [<branch>]``
  + To prepare for working on ``<branch>``.
+ ``git checkout -b|-B <new-branch> [<start-point>]``
  + Specifying ``-b`` causes a new branch to be created as if ``git-branch`` were called and then checked out.
+ ``git checkout --detach [<commit>]``
  + Prepare to work on top of ``<commit>``, by detaching HEAD at it.
+ ``git checkout [-f|--ours|--theirs|-m|--conflict=<style>] [<tree-ish>] [--] <pathspec>``
  + Overwrite the contents of the files that match the pathspec. When the ``<tree-ish>`` (most often a commit) is not given, 
  overwrite working tree with the contents in the index.
---
</details>

[//]: # (pull)
<details>

<summary><b>git pull</b> <a href="https://git-scm.com/docs/git-pull">docs</a> </summary>

---
Merging options 
+ ``--commit`` ``--no-commit``
  + Perform the merge and commit the result.
+ ``--stat`` ``-n``
  + Show a diffstat at the end of the merge.
+ ``--edit`` ``-e``
  + Invoke an editor before committing successful mechanical merge to further edit the auto-generated merge message.

Fetching options
+ ``--all`` 
  + Fetch all remotes
+ ``-a`` ``--append``
  + Append ref names and object names of fetched refs to the existing content of ``.git/FETCH_HEAD``
+ ``--atomic``
  + Use an atomic transaction to update local refs. Either all refs are updated, or on error, no refs are updated.

---
</details>

[//]: # (push)
<details>

<summary><b>git push</b> <a href="https://git-scm.com/docs/git-push">docs</a> </summary>

---
+ ``<repository>``
  + The "remote" repository that is destination of a push operation. This parameter can be either a URL or the name of a remote.
+ ``<refspec>``
---
</details>

[//]: # (merge)
<details>

<summary><b>git merge</b> <a href="https://git-scm.com/docs/git-merge">docs</a> </summary>

---
todo
---
</details>

[//]: # (fetch)
<details>

<summary><b>git fetch</b> <a href="https://git-scm.com/docs/git-fetch">docs</a> </summary>

---
todo
---
</details>

[//]: # (rebase)
<details>

<summary><b>git rebase</b> <a href="https://git-scm.com/docs/git-rebase">docs</a> </summary>

---
todo
---
</details>

[//]: # (revert)
<details>

<summary><b>git revert</b> <a href="https://git-scm.com/docs/git-revert">docs</a> </summary>

---
todo
---
</details>

[//]: # ()
<details>

<summary><b>git </b> <a href="https://git-scm.com/docs/git-">docs</a> </summary>

---
todo
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

---

## Tips & Tricks

### Beautiful ``git log``
````bash
  git log --all --graph --decorate --oneline --simplify-by-decoration
````

---

## DETACHED HEAD